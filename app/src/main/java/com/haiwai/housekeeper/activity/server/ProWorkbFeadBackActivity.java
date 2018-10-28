package com.haiwai.housekeeper.activity.server;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.FeadBackCommonAdapter;
import com.haiwai.housekeeper.adapter.ProGridViewAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.entity.ImageItem;
import com.haiwai.housekeeper.entity.OrderNewWeekEntity;
import com.haiwai.housekeeper.entity.OrderWeekEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.okhttp.OKRequestCallback;
import com.haiwai.housekeeper.https.okhttp.OkHttpUtils;
import com.haiwai.housekeeper.utils.BimpUtils;
import com.haiwai.housekeeper.utils.DatePickDialogUtil;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.FileUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.PreferenceUtils;
import com.haiwai.housekeeper.utils.SDPathUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.DatePickerPopView;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.CustomDialog;
import com.haiwai.housekeeper.widget.LoadDialog;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.zelory.compressor.Compressor;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by lyj on 2016/9/26.
 */
public class ProWorkbFeadBackActivity extends BaseProActivity implements ProGridViewAdapter.OnImgDelListener {
    private TopViewNormalBar top_feadbackb_bar;
    private TextView ib_feadbackb_btn;
    private EditText et_feadback_content;
    private GridView mgridview;
    private TextView tv_change_count;
    private EditText tv_finish_time;
    //    SimpleDateFormat isdf = new SimpleDateFormat("yyyy年MM月dd日");
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    String initDate = sdf.format(new java.util.Date());
//    String date2 = isdf.format(new java.util.Date());
    ProGridViewAdapter mProGridViewAdapter;
    LinearLayout ll_popup;
    PopupWindow popWindow;
    private static final int REQUESTCODE = 22;
    private String content2 = "";
    private String wtime2 = "";
    String number = "";
    private String order_id = "";
    boolean isHis = false;
    String step = "";
    private String isZhorEn = "";
    private OrderNewWeekEntity.DataBean.FeedbackBean mFeedbackBean;
    private ArrayList<String> pathList = new ArrayList<>();
    private FeadBackCommonAdapter mMMFeadHistAdapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    LoadDialog.closeProgressDialog();
                    BimpUtils.tempSelectBitmap.clear();
                    mOnBRefreshYesWeekDataListener.refreshBData("1");
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_house_check_second_layout);
        top_feadbackb_bar = (TopViewNormalBar) findViewById(R.id.top_feadbackb_bar);
        top_feadbackb_bar.setTitle(getString(R.string.pro_gzfk));
        top_feadbackb_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
        initData();
    }


    private void initData() {
        isHis = getIntent().getBooleanExtra("isHis", false);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (isHis) {
            step = getIntent().getStringExtra("step");
            if ("1".equals(step)) {
                mFeedbackBean = (OrderNewWeekEntity.DataBean.FeedbackBean) bundle.getSerializable("mFeedbackBean");
            } else if ("2".equals(step)) {
                mFeedbackBean = (OrderNewWeekEntity.DataBean.FeedbackBean) bundle.getSerializable("mFeedback2Bean");
            }
            et_feadback_content.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
            tv_finish_time.setClickable(false);
            tv_finish_time.setText(TimeUtils.getDate(mFeedbackBean.getWtime2()));
            tv_finish_time.setCompoundDrawables(null, null, null, null);
            et_feadback_content.setText(mFeedbackBean.getContent2());
            et_feadback_content.setFocusable(false);
            ib_feadbackb_btn.setVisibility(View.GONE);

            if (mFeedbackBean.getImages1() != null) {
                try {
                    JSONArray jsonArray = new JSONArray(mFeedbackBean.getImages2());
                    if (jsonArray != null) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            pathList.add((String) jsonArray.get(i));
                        }
                        mMMFeadHistAdapter = new FeadBackCommonAdapter(ProWorkbFeadBackActivity.this, pathList);
                        mgridview.setAdapter(mMMFeadHistAdapter);
                        tv_change_count.setText(pathList.size()+"");
                        mMMFeadHistAdapter.notifyDataSetChanged();

                        mgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(ProWorkbFeadBackActivity.this, PicFeadActivity.class);
                                intent.putExtra("ID", position);
                                intent.putStringArrayListExtra("imagePath",pathList);
                                startActivity(intent);
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                mgridview.setVisibility(View.GONE);
            }



        } else {
            number = getIntent().getStringExtra("number");
            order_id = getIntent().getStringExtra("order_id");
            mgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position == BimpUtils.tempSelectBitmap.size()) {//添加照片
                        ShowPopupWindow();
                    } else {//预览照片
                        Intent intent = new Intent(ProWorkbFeadBackActivity.this, PicShowActivity.class);
                        intent.putExtra("ID", position);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    private void initView() {
        tv_finish_time = (EditText) findViewById(R.id.tv_finish_time);
        et_feadback_content = (EditText) findViewById(R.id.et_feadback_content);
        ib_feadbackb_btn = (TextView) findViewById(R.id.ib_feadbackb_btn);
        ib_feadbackb_btn.setOnClickListener(mOnClickListener);
        mgridview = (GridView) findViewById(R.id.mgridview);
        mProGridViewAdapter = new ProGridViewAdapter(ProWorkbFeadBackActivity.this);
        mProGridViewAdapter.update(mProGridViewAdapter, "add");
        mProGridViewAdapter.setOnImgDelListener(this);
        mgridview.setAdapter(mProGridViewAdapter);

        tv_change_count = (TextView) findViewById(R.id.tv_change_count);
        tv_change_count.setText(BimpUtils.tempSelectBitmap.size()+"");
        tv_finish_time.setText(TimeUtils.getCurrentDate(System.currentTimeMillis()));
        tv_finish_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new DatePickDialogUtil(ProWorkbFeadBackActivity.this, date2, initDate).dateTimePicKDialog(tv_finish_time);
                DatePickerPopView popView = new DatePickerPopView(ProWorkbFeadBackActivity.this);
                popView.openPopWindow(v);
                popView.setDatePicOnClickListener(new DatePickerPopView.DatePicOnClickListener() {
                    @Override
                    public void datePicker(String str) {
                        tv_finish_time.setText(str);
                    }
                });
            }
        });
    }


    private void ShowPopupWindow() {
        View view = View
                .inflate(ProWorkbFeadBackActivity.this, R.layout.item_popupwindows, null);
        view.startAnimation(AnimationUtils.loadAnimation(ProWorkbFeadBackActivity.this,
                R.anim.alpha_fade_in));
        ll_popup = (LinearLayout) view
                .findViewById(R.id.ll_popup);
        ll_popup.startAnimation(AnimationUtils.loadAnimation(ProWorkbFeadBackActivity.this,
                R.anim.translate_bottom_in));
        TextView bt1 = (TextView) view
                .findViewById(R.id.item_popupwindows_camera);
        TextView bt2 = (TextView) view
                .findViewById(R.id.item_popupwindows_Photo);
        TextView bt3 = (TextView) view
                .findViewById(R.id.item_popupwindows_cancel);
        bt1.setOnClickListener(mOnClickListener);
        bt2.setOnClickListener(mOnClickListener);
        bt3.setOnClickListener(mOnClickListener);
        popWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == top_feadbackb_bar.getBackView()) {
                finish();
            } else if (v.getId() == R.id.item_popupwindows_camera) {//调用相机拍照
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(ProWorkbFeadBackActivity.this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ProWorkbFeadBackActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            0);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(ProWorkbFeadBackActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ProWorkbFeadBackActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                1);
                    } else {
                        takePhoto();
                        popWindow.dismiss();
                        ll_popup.clearAnimation();
                    }
                }
            } else if (v.getId() == R.id.item_popupwindows_Photo) {//调用系统相册
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(ProWorkbFeadBackActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ProWorkbFeadBackActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                } else {
                    chosePhoto();
                    popWindow.dismiss();
                    ll_popup.clearAnimation();
                }
            } else if (v.getId() == R.id.item_popupwindows_cancel) {//取消
                popWindow.dismiss();
                ll_popup.clearAnimation();
            } else if (v.getId() == R.id.ib_feadbackb_btn) {
//                gotoNext();
                CustomDialog.Builder customBuilder = new CustomDialog.Builder(ProWorkbFeadBackActivity.this);
                customBuilder.setTitle(getString(R.string.app_tip)).setMessage(getString(R.string.sure_submit)).setPositiveButton(getString(R.string.message_alert_yes),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                gotoNext();
                            }
                        })
                        .setNegativeButton(getString(R.string.message_alert_no),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                        .create().show();
            }
        }
    };

    private void chosePhoto() {
        Intent intent = new Intent(ProWorkbFeadBackActivity.this, PicAlbumActivity.class);
        intent.putExtra("reqcode", REQUESTCODE);
        startActivityForResult(intent, REQUESTCODE);
    }


    private String fileName;
    private void takePhoto() {//相机
        fileName = String.valueOf(System.currentTimeMillis());
        PreferenceUtils.setPrefString(this, "ImgName", fileName);
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(SDPathUtils.getCachePath(), fileName + ".jpg")));

        startActivityForResult(openCameraIntent, 100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && resultCode == RESULT_OK && data != null) {
            if (BimpUtils.tempSelectBitmap.size() < 3) {
                String fileName = String.valueOf(System.currentTimeMillis());
                String path = FileUtils.SDPATH + fileName + ".JPEG";
                Bitmap bm = (Bitmap) data.getExtras().get("data");
                FileUtils.saveBitmap(bm, fileName);
                ImageItem takePhoto = new ImageItem();
                takePhoto.setImagePath(path);
//                takePhoto.setBitmap(bm);
                BimpUtils.tempSelectBitmap.add(takePhoto);
            }
        }else if(requestCode==100){
            //Bitmap compressedImageBitmap = Compressor.getDefault(this).compressToBitmap(new File(SDPathUtils.getCachePath(), fileName + ".jpg"));
//            Log.e("拍照文件", SDPathUtils.getCachePath() + fileName + ".jpg");
//            File file = new File(SDPathUtils.getCachePath() + fileName + ".jpg");
//            Bitmap compressedImageBitmap = BitmapFactory.decodeFile(SDPathUtils.getCachePath() + fileName + ".jpg");
            ImageItem takePhoto = new ImageItem();
            if (TextUtils.isEmpty(fileName)) {
                fileName = PreferenceUtils.getPrefString(this, "ImgName", "");
            }
            takePhoto.setImagePath(SDPathUtils.getCachePath() + fileName + ".jpg");
//            takePhoto.setBitmap(compressedImageBitmap);
            BimpUtils.tempSelectBitmap.add(takePhoto);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mProGridViewAdapter.notifyDataSetChanged();
                }
            }, 500);
        }
    }

    protected void onRestart() {
        if(!isHis){
            mProGridViewAdapter.update(mProGridViewAdapter, "add");
            tv_change_count.setText(BimpUtils.tempSelectBitmap.size()+"");
        }
        super.onRestart();
    }

    @Override
    public void onDelClick(int i) {//删除照片
        ImageItem bp = BimpUtils.tempSelectBitmap.get(i);
        BimpUtils.tempSelectBitmap.remove(bp);
        mProGridViewAdapter.update(mProGridViewAdapter, "del");
        tv_change_count.setText(BimpUtils.tempSelectBitmap.size()+"");
    }

    private void gotoNext() {
        if (BimpUtils.tempSelectBitmap == null && BimpUtils.tempSelectBitmap.size() <= 0) {
            ToastUtil.shortToast(ProWorkbFeadBackActivity.this, getString(R.string.upload_pic_add_pic));
            return;
        }
        LoadDialog.showProgressDialog(ProWorkbFeadBackActivity.this);
        wtime2 = tv_finish_time.getText().toString().trim();
        content2 = et_feadback_content.getText().toString().trim();
        Map<String, String> map = new HashMap<>();
        Map<String, String> fileMap = new HashMap<>();
        if (BimpUtils.tempSelectBitmap != null && BimpUtils.tempSelectBitmap.size() > 0) {
            for (int i = 0; i < BimpUtils.tempSelectBitmap.size(); i++) {
                ImageItem imageItem = BimpUtils.tempSelectBitmap.get(i);
                String strKey = "images2[" + i + "]";
                String value = imageItem.getImagePath();
                fileMap.put(strKey, value);
            }
        }
//        else{
//            Toast.makeText(ProWorkbFeadBackActivity.this,getString(R.string.upload_pic_add_pic),Toast.LENGTH_SHORT).show();
//            return;
//        }
//        LoadDialog.showProgressDialog(this);

        if(content2==null||content2.equals("")){
            content2 = et_feadback_content.getHint().toString();
        }


        map.put("order_id", order_id);
        map.put("number", number);
        map.put("content2", content2);
        if(wtime2.equals("")||wtime2.contains("1970")){
            map.put("wtime2", TimeUtils.getTimeStamp(System.currentTimeMillis()+"", "yyyy-MM-dd"));
        }
        map.put("wtime2", TimeUtils.getTimeStamp(wtime2, "yyyy-MM-dd"));
        map.put("secret_key", SPUtils.getString(ProWorkbFeadBackActivity.this, "secret", ""));
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(ProWorkbFeadBackActivity.this,map, fileMap);
        Request request = new Request.Builder()
                .url(Contants.vacancy2)
                .post(body)
                .build();

        OkHttpUtils.getInstance().execCallback(ProWorkbFeadBackActivity.this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                System.out.println(">>>>>第二次反馈>>>>>>>>>>>>>>" + response);
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.longToast(ProWorkbFeadBackActivity.this, getString(R.string.zt25));
                    Message msg = Message.obtain();
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                } else {
                    ToastUtil.longToast(ProWorkbFeadBackActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    public static OnBRefreshYesWeekDataListener mOnBRefreshYesWeekDataListener;

    public interface OnBRefreshYesWeekDataListener {
        void refreshBData(String type);
    }

    public static void setOnBRefreshYesWeekDataListener(OnBRefreshYesWeekDataListener onBRefreshYesWeekDataListener) {
        mOnBRefreshYesWeekDataListener = onBRefreshYesWeekDataListener;
    }
}
