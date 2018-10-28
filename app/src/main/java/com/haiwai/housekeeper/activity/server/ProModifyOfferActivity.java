package com.haiwai.housekeeper.activity.server;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.PriceEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.ConPopView;
import com.haiwai.housekeeper.view.ConPopView1;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.CustomDialog;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.HashMap;
import java.util.Map;

public class ProModifyOfferActivity extends BaseProActivity {
    private TopViewNormalBar mModifyOfferBar;
    private TextView tv_sfm_m, tv_jcf_m, tv_count_hj;
    private EditText et_input_rgf, et_input_fjf;
    private ImageButton ib_smf_removes, ib_jcf_removes;
    private Button btn_offer_confirm;
    private ImageView iv_icon_a, iv_icon_b, iv_icon_c, iv_icon_d;
    String id = "";
    String uid = "";
    String oid = "";
    String home_fee = "";
    //  String inspection = "";
    String hourly = "";
    String hour = "";
    String general = "";
    String material = "";
    String message = "";
    String mService_type = "";
    private float mNumCount;
    private String isZhorEn = "";
    private boolean flag = false;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            LoadDialog.closeProgressDialog();
            if (msg.what == 0) {
                if (flag) {
                    mOnPriceChangeListener.changeDatas();
                }
                finish();
            } else if (msg.what == 1) {
                flag = false;
                bindData();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_modify_offer);
        mModifyOfferBar = (TopViewNormalBar) findViewById(R.id.top_modify_offer_bar);
        mModifyOfferBar.setTitle(R.string.btn_modify_pri);
        mModifyOfferBar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initData();
        initView();
    }


    private void initData() {
        flag = getIntent().getBooleanExtra("flag", false);
        if (flag) {
            LoadDialog.showProgressDialog(ProModifyOfferActivity.this);
            oid = getIntent().getStringExtra("id");
            uid = getIntent().getStringExtra("uid");
            Map<String, String> map = new HashMap<>();
            map.put("oid", oid);
            map.put("uid", uid);
            map.put("secret_key", SPUtils.getString(ProModifyOfferActivity.this, "secret", ""));
            map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProModifyOfferActivity.this, Contants.user_offer, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        PriceEntity priceEntity = new Gson().fromJson(response, PriceEntity.class);
                        id = priceEntity.getData().getId();
                        home_fee = priceEntity.getData().getHome_fee();
                        // inspection = priceEntity.getData().getInspection();
                        mService_type = priceEntity.getData().getService_type();
                        hourly = priceEntity.getData().getHourly();
                        hour = priceEntity.getData().getHour();
                        general = priceEntity.getData().getGeneral();
                        material = priceEntity.getData().getMaterial();
                        message = priceEntity.getData().getMessage();
                        Message msg = Message.obtain();
                        msg.what = 1;
                        mHandler.sendMessage(msg);
                    } else {
                        LoadDialog.closeProgressDialog();
                        ToastUtil.shortToast(ProModifyOfferActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }));
        } else {
            id = getIntent().getStringExtra("id");
            uid = getIntent().getStringExtra("uid");
            oid = getIntent().getStringExtra("oid");//订单id
            home_fee = getIntent().getStringExtra("home_fee");
            //inspection = getIntent().getStringExtra("inspection");
            mService_type = getIntent().getStringExtra("service_type");
            hourly = getIntent().getStringExtra("hourly");
            hour = getIntent().getStringExtra("hour");
            general = getIntent().getStringExtra("general");
            material = getIntent().getStringExtra("material");
            message = getIntent().getStringExtra("message");
        }

    }

    private void initView() {
        btn_offer_confirm = (Button) findViewById(R.id.btn_offer_confirm);
        btn_offer_confirm.setOnClickListener(mOnClickListener);
        tv_count_hj = (TextView) findViewById(R.id.tv_count_hj);
        tv_sfm_m = (TextView) findViewById(R.id.tv_sfm_m);
//        tv_sfm_m.addTextChangedListener(new mTextWatcher(0));
        // tv_jcf_m = (TextView) findViewById(R.id.tv_jcf_m);
//        tv_jcf_m.addTextChangedListener(new mTextWatcher(1));
        et_input_rgf = (EditText) findViewById(R.id.et_input_rgf);
        et_input_rgf.addTextChangedListener(new mTextWatcher(2));
        et_input_fjf = (EditText) findViewById(R.id.et_input_fjf);
//        et_input_fjf.addTextChangedListener(new mTextWatcher(3));
        ib_smf_removes = (ImageButton) findViewById(R.id.ib_smf_removes);
        ib_smf_removes.setOnClickListener(mOnClickListener);
        //  ib_jcf_removes = (ImageButton) findViewById(R.id.ib_jcf_removes);
//        ib_jcf_removes.setOnClickListener(mOnClickListener);
        iv_icon_a = (ImageView) findViewById(R.id.iv_icon_a);
        // iv_icon_b = (ImageView) findViewById(R.id.iv_icon_b);
        iv_icon_c = (ImageView) findViewById(R.id.iv_icon_c);
        iv_icon_d = (ImageView) findViewById(R.id.iv_icon_d);
        iv_icon_a.setOnClickListener(mOnClickListener);
        //iv_icon_b.setOnClickListener(mOnClickListener);
        iv_icon_c.setOnClickListener(mOnClickListener);
        iv_icon_d.setOnClickListener(mOnClickListener);
        bindData();
    }

    private void bindData() {
        if (!flag) {
            mNumCount = Float.valueOf(home_fee) + Float.valueOf(general);
            tv_count_hj.setText(getString(R.string.offer_hj) + mNumCount);
            tv_sfm_m.setText(getString(R.string.jy_dw) + home_fee);
            // tv_jcf_m.setText(getString(R.string.jy_dw) + inspection);
            et_input_rgf.setText(general);
        }
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mModifyOfferBar.getBackView()) {
                finish();
            }
//            else if (v.getId() == R.id.ib_smf_removes) {
//                home_fee = "0";
//                tv_sfm_m.setText(home_fee);
//            } else if (v.getId() == R.id.ib_jcf_removes) {
//                inspection = "0";
//                tv_jcf_m.setText(inspection);
//            }
            else if (v.getId() == R.id.btn_offer_confirm) {
                CustomDialog.Builder customBuilder = new CustomDialog.Builder(ProModifyOfferActivity.this);
                customBuilder.setMessage(getString(R.string.offer_tis)).setPositiveButton(getString(R.string.message_alert_yes),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                submitData();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(getString(R.string.message_alert_no),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                        .create().show();

            } else if (v.getId() == R.id.iv_icon_a) {
                ConPopView cpv = new ConPopView(ProModifyOfferActivity.this, getString(R.string.off_2));
                cpv.showPopUpWindow(v, -30, 0);
            }
//            else if (v.getId() == R.id.iv_icon_b) {
//                ConPopView cpv = new ConPopView(ProModifyOfferActivity.this, getString(R.string.off_3));
//                cpv.showPopUpWindow(v, -30, 0);
//            }
            else if (v.getId() == R.id.iv_icon_c) {
                ConPopView1 cpv = new ConPopView1(ProModifyOfferActivity.this);
                cpv.showPopUpWindow(v, -30, 0);
            }
//            else if (v.getId() == R.id.iv_icon_d) {
//                ConPopView cpv = new ConPopView(ProModifyOfferActivity.this, "材料费材料费材料费这是材料费");
//                cpv.showPopUpWindow(v, -30, 0);
//            }
        }
    };


    class mTextWatcher implements TextWatcher {
        private int iws;

        public mTextWatcher(int i) {
            iws = i;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
//            if (iws == 0) {
//                home_fee = tv_sfm_m.getText().toString().trim();
//                if (home_fee.contains("$")) {
//                    home_fee = home_fee.substring(1, home_fee.length());
//                }
//                changeHjValue();
//            } else if (iws == 1) {
//                inspection = tv_jcf_m.getText().toString().trim();
//                if (inspection.contains("$")) {
//                    inspection = inspection.substring(1, inspection.length());
//                }
//                changeHjValue();
//            } else
            if (iws == 2) {
                general = et_input_rgf.getText().toString().trim();
                tv_count_hj.setText(getString(R.string.offer_hj) + general);
            }
//            else if (iws == 3) {
//                material = et_input_fjf.getText().toString().trim();
//                if (TextUtils.isEmpty(material)) {
//                    material = "0";
//                }
//                changeHjValue();
//            }
        }
    }

//    private void changeHjValue() {
//        mNumCount = Float.valueOf(home_fee) + Float.valueOf(inspection) + Float.valueOf(general);
//        tv_count_hj.setText("合计 CAD$" + mNumCount);
//    }

    private void submitData() {
        LoadDialog.showProgressDialog(ProModifyOfferActivity.this);
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("uid", uid);
        map.put("oid", oid);
        map.put("home_fee", home_fee);
        //  map.put("inspection", inspection);
        map.put("service_type", mService_type);
        map.put("hourly", hourly);
        map.put("hour", hour);
        map.put("general", general);
        map.put("material", material);
        map.put("message", message);
        map.put("secret_key", SPUtils.getString(ProModifyOfferActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProModifyOfferActivity.this, Contants.offer_save, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(ProModifyOfferActivity.this, getString(R.string.skill_ti4));
                    Message msg = Message.obtain();
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.shortToast(ProModifyOfferActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    public static OnPriceChangeListener mOnPriceChangeListener;

    public interface OnPriceChangeListener {
        void changeDatas();
    }

    public static void setOnPriceChangeListener(OnPriceChangeListener oPriceChangeListener) {
        mOnPriceChangeListener = oPriceChangeListener;
    }
}
