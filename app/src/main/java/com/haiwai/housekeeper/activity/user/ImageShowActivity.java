package com.haiwai.housekeeper.activity.user;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.ImageListEntity;
import com.haiwai.housekeeper.entity.OrderNewWeekEntity;
import com.haiwai.housekeeper.fragment.user.ImageShowFragment;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.ImageLoaderUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.PinchImageView;
import com.haiwai.housekeeper.view.ZoomOutPageTransformer;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看图片
 */
public class ImageShowActivity extends BaseActivity {

    private PinchImageView imageView;
    private ViewPager viewPager;
    MyPagerAdapter myPagerAdapter;
    MyPageListener mypageListener;
//    private ProgressDialog mSaveDialog;
//    private String mSaveMessage;
//    private Bitmap mBitmap;

    private int type;//判断图片个数

    //    private int[] imageArr;
    private List<String> imageList = new ArrayList<>();
    private List<String> descList = new ArrayList<>();
    private List<String> timeList = new ArrayList<>();
    private List<String> idList = new ArrayList<>();

    private String downUrl;
    private TextView tvtitle, tvdesc, tvtime;
    private ImageButton ibdelete;
    private int currentItem = 0;
    private String isZhorEn = "";

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Intent intent = new Intent();
                    intent.setAction("newData");
                    sendBroadcast(intent);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_image_show, null, false);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitlebarHide(true);
//        ibZan = (ImageButton) findViewById(R.id.image_show_ib_zan);
//        ibZan.setOnClickListener(this);
        tvtitle = (TextView) findViewById(R.id.image_show_tv_title);
        tvdesc = (TextView) findViewById(R.id.image_show_tv_desc);
        tvtime = (TextView) findViewById(R.id.image_show_tv_time);
        ibdelete = (ImageButton) findViewById(R.id.image_show_ib_delete);
//        boolean is_self = getIntent().getExtras().getBoolean("is_self");
        ibdelete.setOnClickListener(this);
        findViewById(R.id.image_show_ib_back).setOnClickListener(this);
//        findViewById(R.id.image_show_ib_share).setOnClickListener(this);
//        findViewById(R.id.image_show_ib_download).setOnClickListener(this);

    }

    private void requestDeletePic(final int i) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid() == null ? "" : AppGlobal.getInstance().getUser().getUid());
        map.put("id", idList.get(currentItem));
        map.put("secret_key", SPUtils.getString(ImageShowActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ImageShowActivity.this, Contants.photo_del, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(ImageShowActivity.this, getResources().getString(R.string.delete_success));
                    if (i == 0) {
                        Message msg = Message.obtain();
                        msg.what = 0;
                        mHandler.sendMessage(msg);
                    }
                } else {
                    ToastUtil.shortToast(ImageShowActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }


    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        type = getIntent().getExtras().getInt("type");
        switch (type) {
            case 0://只有一个图片的时候
                imageView = (PinchImageView) findViewById(R.id.image_show_imageview);
                imageView.setVisibility(View.VISIBLE);
                downUrl = getIntent().getExtras().getString("picPath");
                tvdesc.setVisibility(View.GONE);
                tvtime.setVisibility(View.GONE);
                ibdelete.setVisibility(View.GONE);
                ImageLoader.getInstance().displayImage(downUrl, imageView, ImageLoaderUtils.getOptions());
                break;
            case 1://查看图锦图片
                String uid = getIntent().getExtras().get("uid").toString();
                if (uid.equals(AppGlobal.getInstance().getUser().getUid()))
                    ibdelete.setVisibility(View.VISIBLE);
                viewPager = (ViewPager) findViewById(R.id.image_show_viewpager);
                viewPager.setVisibility(View.VISIBLE);
                viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
                List<ImageListEntity.DataBean> entityData = (List<ImageListEntity.DataBean>) getIntent().getExtras().getSerializable("data");
                idList.clear();
                imageList.clear();
                descList.clear();
                timeList.clear();
                for (int i = 0; i < entityData.size(); i++) {
                    idList.add(entityData.get(i).getId());
                    imageList.add(entityData.get(i).getImg());
                    descList.add(entityData.get(i).getDesc());
                    timeList.add(entityData.get(i).getCtime());
                }
                tvtitle.setText("1" + "/" + imageList.size());
                if (descList.size() > 0)
                    tvdesc.setText(descList.get(0));
                if (timeList.size() > 0)
                    tvtime.setText(TimeUtils.getDate5(timeList.get(0)));
                int position = getIntent().getExtras().getInt("position");
                myPagerAdapter = new MyPagerAdapter();
                mypageListener = new MyPageListener();
                viewPager.setAdapter(myPagerAdapter);
                viewPager.setOnPageChangeListener(mypageListener);
                viewPager.setCurrentItem(position, false);
                break;
        }
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.image_show_ib_delete:
                if (imageList.size() == 1) {
                    requestDeletePic(0);
                    setResult(RESULT_OK);
//                    finish();
                } else {
                    requestDeletePic(1);
                    LogUtil.e("currentitem", currentItem + "");
                    imageList.remove(currentItem);
                    descList.remove(currentItem);
                    timeList.remove(currentItem);
                    idList.remove(currentItem);
                    myPagerAdapter.notifyDataSetChanged();
                    mypageListener.onPageSelected(currentItem);
//                    LogUtil.e("currentitem1", currentItem + "");
                }
                break;
            case R.id.image_show_ib_back:
                if (0 == type) {
                    Intent intent = new Intent();
                    intent.setAction("newData");
                    sendBroadcast(intent);
                    finish();
                } else {
                    setResult(RESULT_OK);
                    finish();
                }
                break;
        }
    }
//
//    @Override
//    protected void click(View v) {
//        switch (v.getId()) {
//            case R.id.image_show_ib_back:
//                finish();
//                break;
////            case R.id.image_show_ib_download:
////                if (!isNetworkAvailable()) {
////                    return;
////                }
////                new Thread(connectNet).start();
////                mSaveDialog = ProgressDialog.show(this, "下载图片", "图片正在下载，请稍等……", true);
////                break;
//            case R.id.image_show_ib_delete:
//                //删除
//
//                break;
//        }
//    }

//    private Handler messageHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            mSaveDialog.dismiss();
//            ToastUtil.shortToast(ImageShowActivity.this, mSaveMessage);
//        }
//    };

//    /*
//     * 连接网络
//     * 由于在4.0中不允许在主线程中访问网络，所以需要在子线程中访问
//     */
//    private Runnable connectNet = new Runnable() {
//        @Override
//        public void run() {
//            try {
//
//                //以下是取得图片的两种方法
//                //////////////// 方法1：取得的是byte数组, 从byte数组生成bitmap
//                byte[] data = ImageUtils.getImage(downUrl);
//                if (data != null) {
//                    mBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);// bitmap
//                } else {
//                    ToastUtil.shortToast(ImageShowActivity.this, "Image error!");
//                }
//
//                // 发送消息，通知handler在主线程中更新UI
//                switch (type) {
//                    case 0:
//                        connectHanlder.sendEmptyMessage(0);
//                        break;
//                }
//
//                //保存图片
//                try {
//                    String mFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".jpg";
//                    ImageUtils.saveFile(mBitmap, mFileName);
//                    mSaveMessage = "图片保存成功！";
//                } catch (IOException e) {
//                    mSaveMessage = "图片保存失败！";
//                    e.printStackTrace();
//                }
//                messageHandler.sendMessage(messageHandler.obtainMessage());
//
//            } catch (Exception e) {
//                ToastUtil.shortToast(ImageShowActivity.this, "无法链接网络！");
//                e.printStackTrace();
//            }
//
//        }
//
//    };

//    private Handler connectHanlder = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            // 更新UI，显示图片
//            imageView.setImageBitmap(mBitmap);
//        }
//    };

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
//    }

    class MyPagerAdapter extends PagerAdapter {
        public MyPagerAdapter() {
        }

        @Override
        public int getItemPosition(Object object) {
//            return super.getItemPosition(object);
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            container.addView(views.get(position));
//            ImageLoader.getInstance().displayImage(imageArr[position], views.get(position), ImageLoaderUtils.getOptions());
//            return views.get(position);

            View imagelayout = LayoutInflater.from(ImageShowActivity.this)
                    .inflate(R.layout.item_image_show_viewpage, null);
            PinchImageView imageview = (PinchImageView) imagelayout
                    .findViewById(R.id.item_image_show_viewpage_imageview);
            ImageLoader.getInstance().displayImage(imageList.get(position), imageview, ImageLoaderUtils.getOptions());
//            imageview.setImageResource(imageArr[position]);
//            viewPager.setTag(position,imagelayout);
            container.addView(imagelayout, 0);
            return imagelayout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(((View) object));
//            container.removeAllViews();
        }

    }

    private class MyPageListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
//            downUrl = imageArr[position];
            tvtitle.setText((position + 1) + "/" + imageList.size());
            tvdesc.setText(descList.get(position));
            tvtime.setText(TimeUtils.getDate5(timeList.get(position)));
            currentItem = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
