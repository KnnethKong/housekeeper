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
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.VipBusinessOrderActivity;
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
import com.haiwai.housekeeper.imageloader.ImageLoader;
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
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.zelory.compressor.Compressor;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by lyj on 2016/9/19.
 */
public class ProWorkFeadBackActivity extends BaseProActivity implements ProGridViewAdapter.OnImgDelListener {
    private static final int REQUESTCODE = 44;
    PopupWindow popWindow;
    ProGridViewAdapter mProGridViewAdapter;
    private TopViewNormalBar top_feadback_bar;
    private TextView tvChangeCount;
    private EditText tvFinishTime, etContent;
    private GridView mGridView;
    LinearLayout ll_popup;
    private TextView ib_feadback_btn;
    private String order_id = "";
    private String content1 = "";
    private String wtime1 = "";
    private int number = -1;
    private String type = "";
    private boolean isHis = false;
    private String isZhorEn = "";
    OrderNewWeekEntity.DataBean.FeedbackBean feedbackBean;
    private ArrayList<String> pathList = new ArrayList<>();
    private FeadBackCommonAdapter mFeadHistAdapter;
    ImageLoader mImageLoader;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    LoadDialog.closeProgressDialog();
                    BimpUtils.tempSelectBitmap.clear();
                    mOnRefreshYesWeekDataListener.refreshData("2");
                    finish();
                case 1:
                    LoadDialog.closeProgressDialog();
                    BimpUtils.tempSelectBitmap.clear();
                    mOnRefreshYesWeekDataListener.refreshData("3");
                    finish();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_work_feadback_layout);
        top_feadback_bar = (TopViewNormalBar) findViewById(R.id.top_feadback_bar);
        top_feadback_bar.setTitle(getString(R.string.pro_zyfk));
        top_feadback_bar.setOnBackListener(mOnClickListener);
        mImageLoader = new ImageLoader(this);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
        initData();
    }

    private void initData() {
        isHis = getIntent().getBooleanExtra("isHis", false);
        if (isHis) {
            etContent.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
            tvFinishTime.setClickable(false);
            Bundle bundle = getIntent().getBundleExtra("bundle");
            feedbackBean = (OrderNewWeekEntity.DataBean.FeedbackBean) bundle.getSerializable("feedbackBean");
            if (feedbackBean != null) {
                bindDataView(feedbackBean);
            }
            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ProWorkFeadBackActivity.this, PicFeadActivity.class);
                    intent.putExtra("ID", position);
                    intent.putStringArrayListExtra("imagePath",pathList);
                    startActivity(intent);
                }
            });
            tvChangeCount.setText(pathList.size()+"");
        } else {
            order_id = getIntent().getStringExtra("order_id");
            number = getIntent().getIntExtra("number", -1);
            type = getIntent().getStringExtra("type");
            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position == BimpUtils.tempSelectBitmap.size()) {//添加照片
                        ShowPopupWindow();
                    } else {//预览照片
                        Intent intent = new Intent(ProWorkFeadBackActivity.this, PicShowActivity.class);
                        intent.putExtra("ID", position);
                        startActivity(intent);
                    }
                }
            });
        }

    }

    private void bindDataView(OrderNewWeekEntity.DataBean.FeedbackBean feedbackBean) {
        ib_feadback_btn.setVisibility(View.GONE);
        tvFinishTime.setCompoundDrawables(null, null, null, null);
        tvFinishTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        tvFinishTime.setText(TimeUtils.getDate(feedbackBean.getWtime1()));
        tvFinishTime.setCompoundDrawables(null, null, null, null);
        etContent.setText(feedbackBean.getContent1());
        etContent.setFocusable(false);
        ib_feadback_btn.setVisibility(View.GONE);
        if (feedbackBean.getImages1() != null) {
            try {
                JSONArray jsonArray = new JSONArray(feedbackBean.getImages1());
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        pathList.add((String) jsonArray.get(i));
                        mFeadHistAdapter = new FeadBackCommonAdapter(ProWorkFeadBackActivity.this, pathList);
                        mGridView.setAdapter(mFeadHistAdapter);
                    }
                    tvChangeCount.setText(pathList.size()+"");
                    mFeadHistAdapter.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            mGridView.setVisibility(View.GONE);
        }
    }

    public void initView() {
        tvFinishTime = (EditText) findViewById(R.id.tv_finish_time);
        etContent = (EditText) findViewById(R.id.et_feadback_content);
        mGridView = (GridView) findViewById(R.id.mgridview);
        ib_feadback_btn = (TextView) findViewById(R.id.ib_feadback_btn);
        ib_feadback_btn.setOnClickListener(mOnClickListener);
        mProGridViewAdapter = new ProGridViewAdapter(ProWorkFeadBackActivity.this);
        mProGridViewAdapter.update(mProGridViewAdapter, "add");
        mProGridViewAdapter.setOnImgDelListener(this);
        mGridView.setAdapter(mProGridViewAdapter);

        tvChangeCount = (TextView) findViewById(R.id.tv_change_count);
        tvChangeCount.setText(BimpUtils.tempSelectBitmap.size()+"");
        tvFinishTime.setText(TimeUtils.getCurrentDate(System.currentTimeMillis()));
        tvFinishTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerPopView popView = new DatePickerPopView(ProWorkFeadBackActivity.this);
                popView.openPopWindow(v);
                popView.setDatePicOnClickListener(new DatePickerPopView.DatePicOnClickListener() {
                    @Override
                    public void datePicker(String str) {
                        tvFinishTime.setText(str);
                    }
                });
            }
        });
    }

    private void ShowPopupWindow() {
        View view = View
                .inflate(ProWorkFeadBackActivity.this, R.layout.item_popupwindows, null);
        view.startAnimation(AnimationUtils.loadAnimation(ProWorkFeadBackActivity.this,
                R.anim.alpha_fade_in));
        ll_popup = (LinearLayout) view
                .findViewById(R.id.ll_popup);
        ll_popup.startAnimation(AnimationUtils.loadAnimation(ProWorkFeadBackActivity.this,
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
            if (v == top_feadback_bar.getBackView()) {
                finish();
            } else if (v.getId() == R.id.item_popupwindows_camera) {//调用相机拍照
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(ProWorkFeadBackActivity.this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ProWorkFeadBackActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            0);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(ProWorkFeadBackActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ProWorkFeadBackActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                1);
                    } else {
                        takePhoto();
                        popWindow.dismiss();
                        ll_popup.clearAnimation();
                    }
                }
            } else if (v.getId() == R.id.item_popupwindows_Photo) {//调用系统相册
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(ProWorkFeadBackActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ProWorkFeadBackActivity.this,
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
            } else if (v.getId() == R.id.ib_feadback_btn) {
                CustomDialog.Builder customBuilder = new CustomDialog.Builder(ProWorkFeadBackActivity.this);
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
    private String fileName;
    private void chosePhoto() {
        Intent intent = new Intent(ProWorkFeadBackActivity.this, PicAlbumActivity.class);
        intent.putExtra("reqcode", REQUESTCODE);
        startActivityForResult(intent, REQUESTCODE);
    }


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
//            Bitmap compressedImageBitmap = Compressor.getDefault(this).compressToBitmap(new File(SDPathUtils.getCachePath(), fileName + ".jpg"));
//            //Log.e("拍照文件", SDPathUtils.getCachePath() + fileName + ".jpg");
            //File file = new File(SDPathUtils.getCachePath() + fileName + ".jpg");
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
            tvChangeCount.setText(BimpUtils.tempSelectBitmap.size()+"");
        }

        super.onRestart();
    }

    @Override
    public void onDelClick(int i) {//删除照片
        ImageItem bp = BimpUtils.tempSelectBitmap.get(i);
        BimpUtils.tempSelectBitmap.remove(bp);
        mProGridViewAdapter.update(mProGridViewAdapter, "del");
        tvChangeCount.setText(BimpUtils.tempSelectBitmap.size()+"");
    }

    private void submitFeadData() {
        if (BimpUtils.tempSelectBitmap == null && BimpUtils.tempSelectBitmap.size() <= 0) {
            ToastUtil.shortToast(ProWorkFeadBackActivity.this, getString(R.string.upload_pic_add_pic));
            return;
        }
        LoadDialog.showProgressDialog(ProWorkFeadBackActivity.this);
        wtime1 = tvFinishTime.getText().toString().trim();
        content1 = etContent.getText().toString().trim();
        if (TextUtils.isEmpty(content1)) {
            content1 = etContent.getHint().toString();
        }
        if (!TextUtils.isEmpty(wtime1)) {
            wtime1 = TimeUtils.getTimeStamp(wtime1, "yyyy-MM-dd");
        }
        Map<String, String> map = new HashMap<>();
        map.put("order_id", order_id);
        map.put("number", number + "");
        map.put("content1", content1);
        map.put("wtime1", wtime1);
        map.put("secret_key", SPUtils.getString(ProWorkFeadBackActivity.this, "secret", ""));
        Map<String, String> fileMap = new HashMap<>();
        if (BimpUtils.tempSelectBitmap != null && BimpUtils.tempSelectBitmap.size() > 0) {
            for (int i = 0; i < BimpUtils.tempSelectBitmap.size(); i++) {
                ImageItem imageItem = BimpUtils.tempSelectBitmap.get(i);
                String str = "images1[" + i + "]";
                String path = imageItem.getImagePath();
                fileMap.put(str, path);
            }
        }
//        else{
//            Toast.makeText(ProWorkFeadBackActivity.this,getString(R.string.upload_pic_add_pic),Toast.LENGTH_SHORT).show();
//            return;
//        }
        LoadDialog.showProgressDialog(ProWorkFeadBackActivity.this);
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(ProWorkFeadBackActivity.this,map, fileMap);
        if ("30".equals(type)) {//家政反馈
            Request request = new Request.Builder()
                    .url(Contants.home)
                    .post(body)
                    .build();
            OkHttpUtils.getInstance().execCallback(ProWorkFeadBackActivity.this, request, new OKRequestCallback() {
                @Override
                public void onResponse(String response) {
                    System.out.println(">>>>>>>>>家政>>>>>>>>>>>>>" + response);
                    LoadDialog.closeProgressDialog();
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        ToastUtil.longToast(ProWorkFeadBackActivity.this, getString(R.string.zt25));
                        Message msg = Message.obtain();
                        msg.what = 0;
                        mHandler.sendMessage(msg);
                    } else {
                        ToastUtil.longToast(ProWorkFeadBackActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            });
        } else if ("32".equals(type) || "33".equals(type) || "34".equals(type)) {//园艺反馈
            Request request = new Request.Builder()
                    .url(Contants.horticulture)
                    .post(body)
                    .build();
            OkHttpUtils.getInstance().execCallback(ProWorkFeadBackActivity.this, request, new OKRequestCallback() {
                @Override
                public void onResponse(String response) {
                    System.out.println(">>>>>>>>>园艺>>>>>>>>>>>>>" + response);
                    LoadDialog.closeProgressDialog();
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        ToastUtil.longToast(ProWorkFeadBackActivity.this, getString(R.string.zt25));
                        Message msg = Message.obtain();
                        msg.what = 1;
                        mHandler.sendMessage(msg);
                    } else {
                        ToastUtil.longToast(ProWorkFeadBackActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            });
        }


    }

    public static OnRefreshYesWeekDataListener mOnRefreshYesWeekDataListener;

    public interface OnRefreshYesWeekDataListener {
        void refreshData(String type);
    }

    public static void setOnRefreshYesWeekDataListener(OnRefreshYesWeekDataListener onRefreshYesWeekDataListener) {
        mOnRefreshYesWeekDataListener = onRefreshYesWeekDataListener;
    }

}
