package com.haiwai.housekeeper.activity.server;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import id.zelory.compressor.Compressor;
import okhttp3.Request;
import okhttp3.RequestBody;

import static com.haiwai.housekeeper.entity.Defaultcontent.url;

/**
 * Created by lyj on 2016/9/26.
 */
public class ProWorkcFeadBackActivity extends BaseProActivity implements ProGridViewAdapter.OnImgDelListener {
    private TopViewNormalBar top_feadbackc_bar;
    private TextView ib_feadbackc_btn;
    private EditText tv_finish_time;
    ProGridViewAdapter mProGridViewAdapter;
    LinearLayout ll_popup;
    PopupWindow popWindow;
    private static final int REQUESTCODE = 33;
    private String content3 = "";
    private String wtime3 = "";
    private String order_id = "";
    String number = "";
    private EditText et_feadback_content;
    private GridView mgridview;
    private TextView tv_change_count;
    private CheckBox cb_fwqk, cb_xjqk;
    Map<String, String> map = new LinkedHashMap<>();
    boolean isHis = false;
    String step = "";
    private String isZhorEn = "";
    private OrderNewWeekEntity.DataBean.FeedbackBean mFeedbackBean;
    private ArrayList<String> pathList = new ArrayList<>();
    FeadBackCommonAdapter mMFeadHistAdapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    LoadDialog.closeProgressDialog();
                    BimpUtils.tempSelectBitmap.clear();
                    mOnCRefreshYesWeekDataListener.refreshCData("1");
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_house_check_third_layout);
        top_feadbackc_bar = (TopViewNormalBar) findViewById(R.id.top_feadbackc_bar);
        top_feadbackc_bar.setTitle(R.string.pro_zyfk);
        top_feadbackc_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
        initData();
    }

    private void initView() {
        tv_finish_time = (EditText) findViewById(R.id.tv_finish_time);
        ib_feadbackc_btn = (TextView) findViewById(R.id.ib_feadbackc_btn);
        ib_feadbackc_btn.setOnClickListener(mOnClickListener);
        et_feadback_content = (EditText) findViewById(R.id.et_feadback_content);
        mgridview = (GridView) findViewById(R.id.mgridview);
        mProGridViewAdapter = new ProGridViewAdapter(ProWorkcFeadBackActivity.this);
        Log.e("绑定图片", "initView()");
        mProGridViewAdapter.update(mProGridViewAdapter, "add");
        mProGridViewAdapter.setOnImgDelListener(this);
        mgridview.setAdapter(mProGridViewAdapter);

        tv_change_count = (TextView) findViewById(R.id.tv_change_count);
        tv_change_count.setText(BimpUtils.tempSelectBitmap.size() + "");
        cb_fwqk = (CheckBox) findViewById(R.id.cb_fwqk);
        cb_fwqk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    map.put("is_new", "1");
                } else {
                    map.put("is_new", "2");
                }
            }
        });
        cb_xjqk = (CheckBox) findViewById(R.id.cb_xjqk);
        cb_xjqk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    map.put("is_special", "1");
                } else {
                    map.put("is_special", "2");
                }
            }
        });
        tv_finish_time.setText(TimeUtils.getCurrentDate(System.currentTimeMillis()));
        tv_finish_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new DatePickDialogUtil(ProWorkcFeadBackActivity.this, date2, initDate).dateTimePicKDialog(tv_finish_time);
                DatePickerPopView popView = new DatePickerPopView(ProWorkcFeadBackActivity.this);
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

    private void initData() {
        isHis = getIntent().getBooleanExtra("isHis", false);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (isHis) {
            cb_fwqk.setClickable(false);
            cb_xjqk.setChecked(false);
            step = getIntent().getStringExtra("step");
            if ("1".equals(step)) {
                mFeedbackBean = (OrderNewWeekEntity.DataBean.FeedbackBean) bundle.getSerializable("mFeedbackBean");
            } else if ("2".equals(step)) {
                mFeedbackBean = (OrderNewWeekEntity.DataBean.FeedbackBean) bundle.getSerializable("mFeedback2Bean");
            }
            tv_finish_time.setClickable(false);
            et_feadback_content.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
            tv_finish_time.setText(TimeUtils.getDate(mFeedbackBean.getWtime3()));
            tv_finish_time.setCompoundDrawables(null, null, null, null);
            et_feadback_content.setText(mFeedbackBean.getContent3());
            if ("1".equals(mFeedbackBean.getIs_new())) {
                cb_fwqk.setChecked(true);
            } else if ("2".equals(mFeedbackBean.getIs_new())) {
                cb_fwqk.setChecked(false);
                cb_fwqk.setVisibility(View.GONE);
            }
            if ("1".equals(mFeedbackBean.getIs_special())) {
                cb_xjqk.setChecked(true);
            } else if ("2".equals(mFeedbackBean.getIs_special())) {
                cb_xjqk.setChecked(false);
                cb_xjqk.setVisibility(View.GONE);
            }
            et_feadback_content.setFocusable(false);
            ib_feadbackc_btn.setVisibility(View.GONE);
            if (mFeedbackBean.getImages1() != null && !mFeedbackBean.getImages1().equals("")) {
                try {
                    JSONArray jsonArray = new JSONArray(mFeedbackBean.getImages3());
                    if (jsonArray != null) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            pathList.add((String) jsonArray.get(i));
                        }
                        mMFeadHistAdapter = new FeadBackCommonAdapter(ProWorkcFeadBackActivity.this, pathList);
                        mgridview.setAdapter(mMFeadHistAdapter);
                        tv_change_count.setText(pathList.size() + "");
                        mMFeadHistAdapter.notifyDataSetChanged();

                        mgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(ProWorkcFeadBackActivity.this, PicFeadActivity.class);
                                intent.putExtra("ID", position);
                                intent.putStringArrayListExtra("imagePath", pathList);
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
            order_id = getIntent().getStringExtra("order_id");
            number = getIntent().getStringExtra("number");
            map.put("is_new", "2");
            map.put("is_special", "2");
            mgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position == BimpUtils.tempSelectBitmap.size()) {//添加照片
                        ShowPopupWindow();
                    } else {//预览照片
                        Intent intent = new Intent(ProWorkcFeadBackActivity.this, PicShowActivity.class);
                        intent.putExtra("ID", position);
                        startActivity(intent);
                    }
                }
            });
        }
    }


    private void ShowPopupWindow() {
        View view = View
                .inflate(ProWorkcFeadBackActivity.this, R.layout.item_popupwindows, null);
        view.startAnimation(AnimationUtils.loadAnimation(ProWorkcFeadBackActivity.this,
                R.anim.alpha_fade_in));
        ll_popup = (LinearLayout) view
                .findViewById(R.id.ll_popup);
        ll_popup.startAnimation(AnimationUtils.loadAnimation(ProWorkcFeadBackActivity.this,
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
            if (v == top_feadbackc_bar.getBackView()) {
                finish();
            } else if (v.getId() == R.id.item_popupwindows_camera) {//调用相机拍照
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(ProWorkcFeadBackActivity.this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ProWorkcFeadBackActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            0);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(ProWorkcFeadBackActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ProWorkcFeadBackActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                1);
                    } else {
                        takePhoto();
                        popWindow.dismiss();
                        ll_popup.clearAnimation();
                    }
                }
            } else if (v.getId() == R.id.item_popupwindows_Photo) {//调用系统相册
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(ProWorkcFeadBackActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ProWorkcFeadBackActivity.this,
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
            } else if (v.getId() == R.id.ib_feadbackc_btn) {
                CustomDialog.Builder customBuilder = new CustomDialog.Builder(ProWorkcFeadBackActivity.this);
                customBuilder.setTitle(getString(R.string.app_tip)).setMessage(getString(R.string.sure_submit)).setPositiveButton(getString(R.string.message_alert_yes),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                submitFeadData();
                            }
                        })
                        .setNegativeButton(getString(R.string.message_alert_no),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                        .create().show();
//                submitFeadData();
            }
        }
    };

    private void chosePhoto() {
        Intent intent = new Intent(ProWorkcFeadBackActivity.this, PicAlbumActivity.class);
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
//            if (BimpUtils.tempSelectBitmap.size() < 3) {
            String fileName = String.valueOf(System.currentTimeMillis());
            String path = FileUtils.SDPATH + fileName + ".JPEG";
            Bitmap bm = (Bitmap) data.getExtras().get("data");
            FileUtils.saveBitmap(bm, fileName);
            ImageItem takePhoto = new ImageItem();
            takePhoto.setImagePath(path);
            //takePhoto.setBitmap(bm);
            BimpUtils.tempSelectBitmap.add(takePhoto);
//            }
        } else if (requestCode == 100) {
//
//            Glide.with(this).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
//                @Override
//                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//
//                }
//            }); //方法中设置asBitmap可以设置回调类型


            //Bitmap compressedImageBitmap = Compressor.getDefault(this).compressToBitmap(new File(SDPathUtils.getCachePath(), fileName + ".jpg"));
//            Log.e("拍照文件", SDPathUtils.getCachePath() + fileName + ".jpg");
//            File file = new File(SDPathUtils.getCachePath() + fileName + ".jpg");
//            Bitmap compressedImageBitmap = BitmapFactory.decodeFile(SDPathUtils.getCachePath() + fileName + ".jpg");
            ImageItem takePhoto = new ImageItem();
            if (TextUtils.isEmpty(fileName)) {
                fileName = PreferenceUtils.getPrefString(this, "ImgName", "");
            }
            takePhoto.setImagePath(SDPathUtils.getCachePath() + fileName + ".jpg");
            //takePhoto.setBitmap(compressedImageBitmap);
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
        if (!isHis) {
            Log.e("绑定图片", "onRestart()");
            mProGridViewAdapter.update(mProGridViewAdapter, "add");
            tv_change_count.setText(BimpUtils.tempSelectBitmap.size() + "");
        }
        super.onRestart();
    }

    @Override
    public void onDelClick(int i) {//删除照片
        ImageItem bp = BimpUtils.tempSelectBitmap.get(i);
        BimpUtils.tempSelectBitmap.remove(bp);
        Log.e("绑定图片", "onDelClick()");
        mProGridViewAdapter.update(mProGridViewAdapter, "del");
        tv_change_count.setText(BimpUtils.tempSelectBitmap.size() + "");
    }

    private void submitFeadData() {
//        if(!cb_fwqk.isChecked()&&!cb_xjqk.isChecked()){
//
//            return;
//        }
        Map<String, String> fileMap = new LinkedHashMap<>();
        wtime3 = tv_finish_time.getText().toString().trim();
        content3 = et_feadback_content.getText().toString().trim();
        LoadDialog.showProgressDialog(this);
        if (BimpUtils.tempSelectBitmap != null && BimpUtils.tempSelectBitmap.size() > 0) {
            for (int i = 0; i < BimpUtils.tempSelectBitmap.size(); i++) {
                ImageItem imageItem = BimpUtils.tempSelectBitmap.get(i);
                String str = "images3[" + i + "]";
                fileMap.put(str, imageItem.getImagePath());
            }
        }
//        else{
//            if(getIntent().getStringExtra("id")!=null&&!getIntent().getStringExtra("id").equals("")){
//                Toast.makeText(ProWorkcFeadBackActivity.this,"请上传图片",Toast.LENGTH_SHORT).show();
//                return;
//            }
//        }
        if (content3 == null || content3.equals("")) {
            content3 = et_feadback_content.getHint().toString();
        }

        map.put("content3", content3);
        map.put("order_id", order_id);
        map.put("number", number);
        map.put("wtime3", TimeUtils.getTimeStamp(wtime3, "yyyy-MM-dd"));
        map.put("secret_key", SPUtils.getString(ProWorkcFeadBackActivity.this, "secret", ""));
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(ProWorkcFeadBackActivity.this, map, fileMap);
        Request request = new Request.Builder()
                .url(Contants.vacancy3)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(ProWorkcFeadBackActivity.this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                LoadDialog.closeProgressDialog();
                if (code == 200) {
                    ToastUtil.longToast(ProWorkcFeadBackActivity.this, getString(R.string.zt25));
                    Message msg = Message.obtain();
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                } else {
                    ToastUtil.shortToast(ProWorkcFeadBackActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    public static OnCRefreshYesWeekDataListener mOnCRefreshYesWeekDataListener;

    public interface OnCRefreshYesWeekDataListener {
        void refreshCData(String type);
    }

    public static void setOnCRefreshYesWeekDataListener(OnCRefreshYesWeekDataListener OnCRefreshYesWeekDataListener) {
        mOnCRefreshYesWeekDataListener = OnCRefreshYesWeekDataListener;
    }
}
