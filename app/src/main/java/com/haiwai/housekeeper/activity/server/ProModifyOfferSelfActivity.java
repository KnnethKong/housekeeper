package com.haiwai.housekeeper.activity.server;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.ConPopBig11View;
import com.haiwai.housekeeper.view.ConPopBig12View;
import com.haiwai.housekeeper.view.ConPopView;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.CustomDialog;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.HashMap;
import java.util.Map;

public class ProModifyOfferSelfActivity extends BaseProActivity {
    private TopViewNormalBar mModifyOfferBar;
    private TextView tv_sfm_m, tv_price_count, tv_hj_price, et_fwdj_input;
    private ImageButton ib_smf_remove;
    private EditText et_zgs_input, et_clf_input;
    private Button btn_confirm_price;
    private String strFwdj, strZgs;
    private ImageView iv_btn_a, iv_btn_c, iv_btn_d;
    String id = "";
    String uid = "";
    String oid = "";
    String home_fee = "";
    // String inspection = "";
    String hourly = "";
    String hour = "";
    String general = "";
    String material = "";
    String message = "";
    String mService_type = "";
    private float mCountPr;
    private String isZhorEn = "";
    private boolean flag = false;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            LoadDialog.closeProgressDialog();
            if (msg.what == 0) {
                if (flag) {
                    mOnPriceChangeListener.changeData();
                }
                setResult(RESULT_OK);
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
        setContentView(R.layout.activity_pro_modify_oo_offer);
        mModifyOfferBar = (TopViewNormalBar) findViewById(R.id.top_modify_offer_bar);
        mModifyOfferBar.setTitle(R.string.btn_modify_pri);
        mModifyOfferBar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
        initData();
    }

    private void initData() {
        flag = getIntent().getBooleanExtra("flag", false);
        if (flag) {
            LoadDialog.showProgressDialog(ProModifyOfferSelfActivity.this);
            oid = getIntent().getStringExtra("id");
            uid = getIntent().getStringExtra("uid");
            Map<String, String> map = new HashMap<>();
            map.put("oid", oid);
            map.put("uid", uid);
            map.put("secret_key", SPUtils.getString(ProModifyOfferSelfActivity.this, "secret", ""));
            map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProModifyOfferSelfActivity.this, Contants.user_offer, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        PriceEntity priceEntity = new Gson().fromJson(response, PriceEntity.class);
                        id = priceEntity.getData().getId();
                        home_fee = priceEntity.getData().getHome_fee();
                        //inspection = priceEntity.getData().getInspection();
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
                        ToastUtil.shortToast(ProModifyOfferSelfActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }));
        } else {
            id = getIntent().getStringExtra("id");
            uid = getIntent().getStringExtra("uid");
            oid = getIntent().getStringExtra("oid");
            home_fee = getIntent().getStringExtra("home_fee");
            //inspection = getIntent().getStringExtra("inspection");
            mService_type = getIntent().getStringExtra("service_type");
            hourly = getIntent().getStringExtra("hourly");
            hour = getIntent().getStringExtra("hour");
            general = getIntent().getStringExtra("general");
            material = getIntent().getStringExtra("material");
            message = getIntent().getStringExtra("message");
        }
        bindData();
    }

    private void initView() {
        tv_hj_price = (TextView) findViewById(R.id.tv_hj_price);
        tv_sfm_m = (TextView) findViewById(R.id.tv_sfm_m);
//        tv_sfm_m.addTextChangedListener(new mTextWatcher(2));
        //tv_jcf_m = (TextView) findViewById(R.id.tv_jcf_m);
//        tv_jcf_m.addTextChangedListener(new mTextWatcher(3));
        tv_price_count = (TextView) findViewById(R.id.tv_price_count);
        ib_smf_remove = (ImageButton) findViewById(R.id.ib_smf_remove);
//        ib_smf_remove.setOnClickListener(mOnClickListener);
        // ib_jcf_remove = (ImageButton) findViewById(R.id.ib_jcf_remove);
//        ib_jcf_remove.setOnClickListener(mOnClickListener);
        et_fwdj_input = (TextView) findViewById(R.id.et_fwdj_input);
//        et_fwdj_input.addTextChangedListener(new mTextWatcher(0));
        et_zgs_input = (EditText) findViewById(R.id.et_zgs_input);
        strFwdj = et_fwdj_input.getText().toString().trim();
        strZgs = et_zgs_input.getText().toString().trim();
        et_zgs_input.addTextChangedListener(new mTextWatcher(1));
        et_clf_input = (EditText) findViewById(R.id.et_clf_input);
//        et_clf_input.addTextChangedListener(new mTextWatcher(4));
        btn_confirm_price = (Button) findViewById(R.id.btn_confirm_price);
        btn_confirm_price.setOnClickListener(mOnClickListener);
        iv_btn_a = (ImageView) findViewById(R.id.iv_btn_a);
        // iv_btn_b = (ImageView) findViewById(R.id.iv_btn_b);
        iv_btn_c = (ImageView) findViewById(R.id.iv_btn_c);
        iv_btn_d = (ImageView) findViewById(R.id.iv_btn_d);
        iv_btn_a.setOnClickListener(mOnClickListener);
        //  iv_btn_b.setOnClickListener(mOnClickListener);
        iv_btn_c.setOnClickListener(mOnClickListener);
        iv_btn_d.setOnClickListener(mOnClickListener);


    }

    private void bindData() {
        tv_sfm_m.setText(getString(R.string.jy_dw) + home_fee);
        //tv_jcf_m.setText(getString(R.string.jy_dw)+ inspection);
        et_fwdj_input.setText(getString(R.string.jy_dw) + hourly);
        if (!TextUtils.isEmpty(hour)) {
            mCountPr = Float.valueOf(hour) * Float.valueOf(hourly);
            tv_price_count.setText(getString(R.string.jy_dw) + mCountPr);
        } else {
            tv_price_count.setText(R.string.dd);
        }
        if (!TextUtils.isEmpty(hour)) {
            et_zgs_input.setText(hour);
            et_zgs_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    et_zgs_input.setText("");
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
                }
            });
        }
        et_clf_input.setText(material);
        if (!TextUtils.isEmpty(hour)) {
            tv_hj_price.setText(getString(R.string.offer_l_hj) + String.format("%.2f", Float.valueOf(mCountPr)));
        } else {
            tv_hj_price.setText(R.string.dd);
        }
    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mModifyOfferBar.getBackView()) {
                finish();
            }
//            else if (v.getId() == R.id.ib_smf_remove) {//免除上门费
//                home_fee = "0";
//                tv_sfm_m.setText(home_fee);
//            } else if (v.getId() == R.id.ib_jcf_remove) {//免除检测费
//                inspection = "0";
//                tv_jcf_m.setText(inspection);
//            }
            else if (v.getId() == R.id.btn_confirm_price) {//修改价格

                if (Float.valueOf(et_zgs_input.getText().toString().trim()) == 0) {
                    ToastUtil.shortToast(ProModifyOfferSelfActivity.this, getString(R.string.samewithzero));
                    return;
                }

                if (et_zgs_input.getText().toString().contains(".")) {
                    ToastUtil.shortToast(ProModifyOfferSelfActivity.this, getString(R.string.inputWholeNum));
                    return;
                }

                CustomDialog.Builder customBuilder = new CustomDialog.Builder(ProModifyOfferSelfActivity.this);
                customBuilder.setTitle(getString(R.string.app_tip)).setMessage(R.string.offer_tis).setPositiveButton(getString(R.string.message_alert_yes),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                submitOfferData();
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

            } else if (v.getId() == R.id.iv_btn_a) {
                ConPopView cpv = new ConPopView(ProModifyOfferSelfActivity.this, getString(R.string.off_2));
                cpv.showPopUpWindow(v, -30, 0);

//            } else if (v.getId() == R.id.iv_btn_b) {
//                ConPopView cpv = new ConPopView(ProModifyOfferSelfActivity.this, getString(R.string.off_3));
//                cpv.showPopUpWindow(v, -30, 0);
            } else if (v.getId() == R.id.iv_btn_c) {
                ConPopBig12View view = new ConPopBig12View(ProModifyOfferSelfActivity.this, "");
                view.showPopUpWindow(v, -30, 0);
//                ConPopView cpv = new ConPopView(ProModifyOfferSelfActivity.this, getString(R.string.off_1));
//                cpv.showPopUpWindow(v, -30, 0);
            }
//            else if (v.getId() == R.id.iv_btn_d) {
//                ConPopView cpv = new ConPopView(ProModifyOfferSelfActivity.this, "材料费材料费材料费这是材料费");
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
//                strFwdj = editable.toString().trim();
//                hourly = editable.toString().trim();
//                if (!TextUtils.isEmpty(strZgs) && !TextUtils.isEmpty(strFwdj)) {
//                    float a = Float.valueOf(strFwdj);
//                    float b = Float.valueOf(strZgs);
//                    float c = a * b;
//                    tv_price_count.setText(c + "");
//                    mCountPr = Float.valueOf(tv_price_count.getText().toString().trim());
//                    float hjCount = Float.valueOf(home_fee) + Float.valueOf(inspection) + mCountPr;
//                    tv_hj_price.setText("合计$" + hjCount);
//                }
//            } else
            if (iws == 1) {
//                strZgs = editable.toString().trim();
                hour = editable.toString().trim();
                if (!TextUtils.isEmpty(hour)) {
                    float a = Float.valueOf(hourly);
                    float b = Float.valueOf(hour);
                    float c = a * b;
                    tv_price_count.setText(getString(R.string.jy_dw) + c);
                    tv_hj_price.setText(getString(R.string.offer_l_hj) + c);
                }
            }
//            else if (iws == 2) {
//                home_fee = tv_sfm_m.getText().toString().trim();
//                if (home_fee.contains("$")) {
//                    home_fee = home_fee.substring(1, home_fee.length());
//                }
////                changeHjCount();
//            }
//            else if (iws == 3) {
//                inspection = tv_jcf_m.getText().toString().trim();
//                if (inspection.contains("$")) {
//                    inspection = inspection.substring(1, inspection.length());
//                }
////                changeHjCount();
//            } else if (iws == 4) {
//                material = et_clf_input.getText().toString().trim();
//                if (TextUtils.isEmpty(material)) {
//                    material = "0";
//                }
////                changeHjCount();
//            }
        }
    }

//    private void changeHjCount() {
//        if (!TextUtils.isEmpty(hour)) {
//            float hjCount = Float.valueOf(home_fee) + Float.valueOf(inspection) + mCountPr;
//            tv_hj_price.setText("合计$" + hjCount);
//        } else {
//            tv_hj_price.setText("待定");
//        }
//    }


    private void submitOfferData() {
        LoadDialog.showProgressDialog(ProModifyOfferSelfActivity.this);
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("uid", uid);
        map.put("oid", oid);
        map.put("home_fee", home_fee);
        // map.put("inspection", inspection);
        map.put("service_type", mService_type);
        map.put("hourly", hourly);
        map.put("hour", hour);
        map.put("general", general);
        map.put("material", material);
        map.put("message", message);
        map.put("secret_key", SPUtils.getString(ProModifyOfferSelfActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
//        for (Map.Entry mp : map.entrySet()) {
//            System.out.println(">>>>>>>>" + mp.getKey() + "<<<<<<<<<<<" + mp.getValue());
//        }
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProModifyOfferSelfActivity.this, Contants.offer_save, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(ProModifyOfferSelfActivity.this, getString(R.string.skill_ti4));
                    Message msg = Message.obtain();
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.shortToast(ProModifyOfferSelfActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    public static OnPriceChangeListener mOnPriceChangeListener;

    public interface OnPriceChangeListener {
        void changeData();
    }

    public static void setOnPriceChangeListener(OnPriceChangeListener oPriceChangeListener) {
        mOnPriceChangeListener = oPriceChangeListener;
    }

}
