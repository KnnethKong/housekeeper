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
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.FeadBackCommonAdapter;
import com.haiwai.housekeeper.adapter.ProGridViewAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.entity.ImageItem;
import com.haiwai.housekeeper.entity.OrderNewWeekEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.okhttp.OKRequestCallback;
import com.haiwai.housekeeper.https.okhttp.OkHttpUtils;
import com.haiwai.housekeeper.utils.BimpUtils;
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
import java.net.URI;
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
public class ProWorkaFeadBackActivity extends BaseProActivity implements ProGridViewAdapter.OnImgDelListener {
    private TopViewNormalBar top_feadbacka_bar;
    private TextView ib_feadbacka_btn;
    private EditText et_feadback_content;
    private GridView mgridview;
    private EditText tv_finish_time;
    //    SimpleDateFormat isdf = new SimpleDateFormat("yyyy年MM月dd日");
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    String initDate = sdf.format(new Date());
//    String date2 = isdf.format(new Date());
    private TextView tv_change_count;
    ProGridViewAdapter mProGridViewAdapter;
    LinearLayout ll_popup, ll_img_layout;
    PopupWindow popWindow;
    private static final int REQUESTCODE = 11;
    private List<String> aList = new ArrayList<>();
    private String content1 = "";
    String number = "";
    private String order_id = "";
    private String wtime1 = "";
    boolean isHis = false;
    String step = "";
    private OrderNewWeekEntity.DataBean.FeedbackBean mFeedbackBean;
    private List<String> pathList = new ArrayList<>();
    private ArrayList<String> imgList = new ArrayList<>();
    private String isZhorEn = "";
    private FeadBackCommonAdapter mMFeadHistAdapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    LoadDialog.closeProgressDialog();
                    BimpUtils.tempSelectBitmap.clear();
                    mOnARefreshYesWeekDataListener.refreshAData("1");
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_house_check_first_layout);
        top_feadbacka_bar = (TopViewNormalBar) findViewById(R.id.top_feadbacka_bar);
        top_feadbacka_bar.setTitle(R.string.pro_gzfk);
        top_feadbacka_bar.setOnBackListener(mOnClickListener);
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
            tv_finish_time.setText(TimeUtils.getDate(mFeedbackBean.getWtime1()));
            tv_finish_time.setCompoundDrawables(null, null, null, null);
            et_feadback_content.setText(mFeedbackBean.getContent1());
            et_feadback_content.setFocusable(false);
            ib_feadbacka_btn.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(mFeedbackBean.getImages1())) {
                try {
                    JSONArray jsonArray = new JSONArray(mFeedbackBean.getImages1());
                    if (jsonArray != null && jsonArray.length() > 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            pathList.add((String) jsonArray.get(i));
                            imgList.add((String) jsonArray.get(i));
                        }
                        mMFeadHistAdapter = new FeadBackCommonAdapter(ProWorkaFeadBackActivity.this, imgList);
                        mgridview.setAdapter(mMFeadHistAdapter);
                        tv_change_count.setText(pathList.size() + "");
                        mMFeadHistAdapter.notifyDataSetChanged();

                        mgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(ProWorkaFeadBackActivity.this, PicFeadActivity.class);
                                intent.putStringArrayListExtra("imagePath", imgList);
                                intent.putExtra("ID", position);
                                startActivity(intent);
                            }
                        });

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                ll_img_layout.setVisibility(View.GONE);
                mgridview.setVisibility(View.GONE);
            }


        } else {
            number = getIntent().getStringExtra("number");
            order_id = getIntent().getStringExtra("order_id");
            mgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position == BimpUtils.tempSelectBitmap.size()) {//添加照片
                        if (!isHis) {
                            ShowPopupWindow();
                        }
                    } else {//预览照片
                        if (!isHis) {
                            Intent intent = new Intent(ProWorkaFeadBackActivity.this, PicShowActivity.class);
                            intent.putExtra("ID", position);
                            startActivity(intent);
                        }

                    }
                }
            });
        }
    }

    private void initView() {
        tv_finish_time = (EditText) findViewById(R.id.tv_finish_time);
        et_feadback_content = (EditText) findViewById(R.id.et_feadback_content);
        ib_feadbacka_btn = (TextView) findViewById(R.id.ib_feadbacka_btn);
        ib_feadbacka_btn.setOnClickListener(mOnClickListener);
        ll_img_layout = (LinearLayout) findViewById(R.id.ll_img_layout);
        mgridview = (GridView) findViewById(R.id.mgridview);
        mProGridViewAdapter = new ProGridViewAdapter(ProWorkaFeadBackActivity.this);
        mProGridViewAdapter.update(mProGridViewAdapter, "add");
        mProGridViewAdapter.setOnImgDelListener(this);
        mgridview.setAdapter(mProGridViewAdapter);

        tv_change_count = (TextView) findViewById(R.id.tv_change_count);
        tv_change_count.setText(BimpUtils.tempSelectBitmap.size() + "");
        tv_finish_time.setText(TimeUtils.getCurrentDate(System.currentTimeMillis()));
        if (!isHis) {
            tv_finish_time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                new DatePickDialogUtil(ProWorkaFeadBackActivity.this, date2, initDate).dateTimePicKDialog(tv_finish_time);
                    DatePickerPopView popView = new DatePickerPopView(ProWorkaFeadBackActivity.this);
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

    }

    private void ShowPopupWindow() {
        View view = View
                .inflate(ProWorkaFeadBackActivity.this, R.layout.item_popupwindows, null);
        view.startAnimation(AnimationUtils.loadAnimation(ProWorkaFeadBackActivity.this,
                R.anim.alpha_fade_in));
        ll_popup = (LinearLayout) view
                .findViewById(R.id.ll_popup);
        ll_popup.startAnimation(AnimationUtils.loadAnimation(ProWorkaFeadBackActivity.this,
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
            if (v == top_feadbacka_bar.getBackView()) {
                finish();
            } else if (v.getId() == R.id.item_popupwindows_camera) {//调用相机拍照
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(ProWorkaFeadBackActivity.this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ProWorkaFeadBackActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            0);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(ProWorkaFeadBackActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ProWorkaFeadBackActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                1);
                    } else {
                        takePhoto();
                        popWindow.dismiss();
                        ll_popup.clearAnimation();
                    }
                }
            } else if (v.getId() == R.id.item_popupwindows_Photo) {//调用系统相册
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(ProWorkaFeadBackActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ProWorkaFeadBackActivity.this,
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
            } else if (v.getId() == R.id.ib_feadbacka_btn) {


                CustomDialog.Builder customBuilder = new CustomDialog.Builder(ProWorkaFeadBackActivity.this);
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
        Intent intent = new Intent(ProWorkaFeadBackActivity.this, PicAlbumActivity.class);
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
//            takePhoto.setBitmap(bm);
            BimpUtils.tempSelectBitmap.add(takePhoto);
//            }
        } else if (requestCode == 100) {
            //Bitmap compressedImageBitmap = Compressor.getDefault(this).compressToBitmap(new File(SDPathUtils.getCachePath(), fileName + ".jpg"));
//            Log.e("拍照文件", SDPathUtils.getCachePath() + fileName + ".jpg");
            //File file = new File(SDPathUtils.getCachePath() + fileName + ".jpg");
//            Bitmap compressedImageBitmap = BitmapFactory.decodeFile(SDPathUtils.getCachePath() + fileName + ".jpg");
            ImageItem takePhoto = new ImageItem();
            if (TextUtils.isEmpty(fileName)) {
                fileName = PreferenceUtils.getPrefString(this, "ImgName", "");
            }
            takePhoto.setImagePath(SDPathUtils.getCachePath() + fileName + ".jpg");
//            takePhoto.setBitmap(compressedImageBitmap);
            BimpUtils.tempSelectBitmap.add(takePhoto);
//            BimpUtils.tempSelectBitmap.add(takePhoto);
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
            mProGridViewAdapter.update(mProGridViewAdapter, "add");
            tv_change_count.setText(BimpUtils.tempSelectBitmap.size() + "");
        }
        super.onRestart();
    }

    @Override
    public void onDelClick(int i) {//删除照片
        ImageItem bp = BimpUtils.tempSelectBitmap.get(i);
        BimpUtils.tempSelectBitmap.remove(bp);
        mProGridViewAdapter.update(mProGridViewAdapter, "del");
        tv_change_count.setText(BimpUtils.tempSelectBitmap.size() + "");
    }

    private void gotoNext() {
        if (BimpUtils.tempSelectBitmap == null && BimpUtils.tempSelectBitmap.size() <= 0) {
            ToastUtil.shortToast(ProWorkaFeadBackActivity.this, getString(R.string.upload_pic_add_pic));
            return;
        }
        LoadDialog.showProgressDialog(ProWorkaFeadBackActivity.this);
        final Map<String, String> map = new HashMap<>();
        Map<String, String> fileMap = new HashMap<>();
        wtime1 = tv_finish_time.getText().toString().trim();
        content1 = et_feadback_content.getText().toString().trim();
        if (TextUtils.isEmpty(content1)) {
            content1 = getString(R.string.fed_back_hint);
        }
        if (BimpUtils.tempSelectBitmap != null && BimpUtils.tempSelectBitmap.size() > 0) {
            for (int i = 0; i < BimpUtils.tempSelectBitmap.size(); i++) {
                ImageItem imageItem = BimpUtils.tempSelectBitmap.get(i);
                String strKey = "images1[" + i + "]";
                String value = imageItem.getImagePath();
                fileMap.put(strKey, value);
            }
        }

        map.put("order_id", order_id);
        map.put("number", number);
        map.put("content1", content1);
        if (wtime1.equals("") || wtime1.contains("1970")) {
            map.put("wtime1", TimeUtils.getTimeStamp(System.currentTimeMillis() + "", "yyyy-MM-dd"));
        }
        map.put("wtime1", TimeUtils.getTimeStamp(wtime1, "yyyy-MM-dd"));
        map.put("secret_key", SPUtils.getString(ProWorkaFeadBackActivity.this, "secret", ""));
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(ProWorkaFeadBackActivity.this, map, fileMap);
        Request request = new Request.Builder()
                .url(Contants.vacancy1)
                .post(body)
                .build();

        OkHttpUtils.getInstance().execCallback(ProWorkaFeadBackActivity.this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                System.out.println(">>>>>>>>>>>>反馈>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + response);
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.longToast(ProWorkaFeadBackActivity.this, getString(R.string.zt25));
                    Message msg = Message.obtain();
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                } else {
                    ToastUtil.longToast(ProWorkaFeadBackActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });

    }

    public static OnARefreshYesWeekDataListener mOnARefreshYesWeekDataListener;

    public interface OnARefreshYesWeekDataListener {
        void refreshAData(String type);
    }

    public static void setOnARefreshYesWeekDataListener(OnARefreshYesWeekDataListener onARefreshYesWeekDataListener) {
        mOnARefreshYesWeekDataListener = onARefreshYesWeekDataListener;
    }
}
