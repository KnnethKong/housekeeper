package com.haiwai.housekeeper.activity.server;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.LoginActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.ChargeEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.https.okhttp.OKRequestCallback;
import com.haiwai.housekeeper.https.okhttp.OkHttpUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.ConPopBig7View;
import com.haiwai.housekeeper.view.ConPopBig8View;
import com.haiwai.housekeeper.view.ConPopView;
import com.haiwai.housekeeper.view.ConPopView1;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.CustomDialog;
import com.haiwai.housekeeper.widget.LoadDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

public class ProOfferActivity extends BaseProActivity implements View.OnClickListener {
    private TopViewNormalBar mOfferBar;
    private TextView tv_fwbj_btn, tv_all_money, tv_hour_money, tv_offer_wc, tv_offer_yc, tv_pric_content, tv_sub_price, tv_offer_content;
    private EditText et_input_price, et_offer_content;
    private ImageView iv_smf;
    private String oidStr = "";
    private String homeFeeStr = "";
    private String inspectionStr = "";
    private static final String SERVICE_TYPE_1 = "1";//工人时薪
    private static final String SERVIEC_TYPE_2 = "2";//总体报价
    private String hourlyStr = "";
    private String generalStr = "";
    private String messageStr = "";
    private String isZhorEn = "";
    private Map<String, String> map = new HashMap<>();
    boolean isFlag = false;
    User user;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            float price = bundle.getFloat("price");
            switch (msg.what) {
                case 0:
                    mOrderRefreshDataListener.orderPriceRefresh();
                    finish();
                    break;
                case 1:
                    tv_realincome.setText(getString(R.string.jy_dw) + price);
                    break;
                case 2:
                    tv_realincome.setText(getString(R.string.jy_dw) + 0);
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private TextView tv_realincome;
    private ImageView iv_smf2;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_offer);
        mOfferBar = (TopViewNormalBar) findViewById(R.id.top_offer_bar);
        mOfferBar.setTitle(getString(R.string.pro_code_bj));
        mOfferBar.setOnBackListener(this);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initData();
        initView();
    }

    private void initData() {
        user = AppGlobal.getInstance().getUser();
        map.put("uid", user.getUid());
        oidStr = getIntent().getStringExtra("oid");
        map.put("oid", oidStr);
        getCommission();
    }

    private void initView() {
        tv_fwbj_btn = (TextView) findViewById(R.id.tv_fwbj_btn);
        iv_smf = (ImageView) findViewById(R.id.iv_smf);
        iv_smf.setOnClickListener(this);
        tv_all_money = (TextView) findViewById(R.id.tv_all_money);
        tv_hour_money = (TextView) findViewById(R.id.tv_hour_money);
        tv_offer_wc = (TextView) findViewById(R.id.tv_offer_wc);
        tv_offer_yc = (TextView) findViewById(R.id.tv_offer_yc);
        tv_pric_content = (TextView) findViewById(R.id.tv_pric_content);
        tv_sub_price = (TextView) findViewById(R.id.tv_sub_price);
        et_input_price = (EditText) findViewById(R.id.et_input_price);
        et_offer_content = (EditText) findViewById(R.id.et_offer_content);
        tv_offer_content = (TextView) findViewById(R.id.tv_offer_content);
        //实际收入
        tv_realincome = (TextView) findViewById(R.id.tv_realincome);
        //实际收入介绍
        iv_smf2 = (ImageView) findViewById(R.id.iv_smf2);

        tv_offer_content.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        tv_fwbj_btn.setOnClickListener(this);
        tv_all_money.setOnClickListener(this);
        tv_hour_money.setOnClickListener(this);
        tv_offer_wc.setOnClickListener(this);
        tv_offer_yc.setOnClickListener(this);
        tv_sub_price.setOnClickListener(this);
        iv_smf2.setOnClickListener(this);
        tv_all_money.setSelected(true);
        tv_offer_wc.setSelected(true);
        et_input_price.addTextChangedListener(new mTextWatcher(0));
        et_offer_content.addTextChangedListener(new mTextWatcher(1));

    }

    private void getCommission() {
        LoadDialog.showProgressDialog(this);
        RequestBody body = OkHttpUtils.getInstance().SetRequestBody(null);
        Request request = new Request.Builder()
                .url(Contants.ServiceCharge)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(ProOfferActivity.this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
//data是百分之几
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        Gson gson = new Gson();
                        ChargeEntity chargeEntity = gson.fromJson(response, ChargeEntity.class);
                        data = chargeEntity.getData().getO2o_bai();
                        LoadDialog.closeProgressDialog();
                    }
                } catch (Exception e) {

                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_fwbj_btn) {
            ConPopView1 cpv = new ConPopView1(ProOfferActivity.this);
            cpv.showPopUpWindow(v, 120, 0);
        } else if (v.getId() == R.id.iv_smf) {
            ConPopView cpv = new ConPopView(ProOfferActivity.this, getString(R.string.off_2));
            cpv.showPopUpWindow(v, 100, 0);
        } else if (v == mOfferBar.getBackView()) {
            finish();
        } else if (v.getId() == R.id.tv_all_money) {
            isFlag = false;
            tv_all_money.setSelected(true);
            tv_hour_money.setSelected(false);
            tv_offer_wc.setSelected(true);
            tv_offer_yc.setSelected(false);
            et_input_price.setText("");
            et_offer_content.setText("");
            et_input_price.setHint(R.string.hin1);
            et_offer_content.setHint(R.string.hin2);
            et_offer_content.setVisibility(View.GONE);
            tv_offer_content.setVisibility(View.VISIBLE);
        } else if (v.getId() == R.id.tv_hour_money) {
            isFlag = true;
            tv_all_money.setSelected(false);
            tv_hour_money.setSelected(true);
            tv_offer_wc.setSelected(true);
            tv_offer_yc.setSelected(false);
            et_input_price.setText("");
            et_offer_content.setText("");
            et_input_price.setHint(R.string.hin1);
            et_offer_content.setHint(R.string.hin2);

            et_offer_content.setVisibility(View.GONE);

            tv_offer_content.setVisibility(View.VISIBLE);

            tv_pric_content.setText(R.string.dd);
        } else if (v.getId() == R.id.tv_offer_wc) {
            tv_offer_wc.setSelected(true);
            tv_offer_yc.setSelected(false);
            et_offer_content.setText("");
            et_offer_content.setVisibility(View.GONE);
            tv_offer_content.setVisibility(View.VISIBLE);
        } else if (v.getId() == R.id.tv_offer_yc) {
            tv_offer_wc.setSelected(false);
            tv_offer_yc.setSelected(true);
            et_offer_content.setVisibility(View.VISIBLE);
            tv_offer_content.setVisibility(View.GONE);
        } else if (v.getId() == R.id.tv_sub_price) {//抢单按钮tv

            if (tv_all_money.isSelected() && (et_input_price.getText() == null || "".equals(et_input_price.getText().toString().trim()))) {
                ToastUtil.shortToast(ProOfferActivity.this, getString(R.string.enter_num) + "" + getString(R.string.tv_debj));
                return;
            }


            if (tv_hour_money.isSelected() && (et_input_price.getText().toString().trim().equals("") || et_input_price.getText() == null)) {
                ToastUtil.shortToast(ProOfferActivity.this, getString(R.string.enter_num) + "" + getString(R.string.hourly_price));
                return;
            }

            if (tv_all_money.isSelected() && Float.valueOf(et_input_price.getText().toString().trim()) == 0) {
                ToastUtil.shortToast(ProOfferActivity.this, getString(R.string.tv_debj) + "" + getString(R.string.not_zero));
                return;
            }

            if (tv_hour_money.isSelected() && Float.valueOf(et_input_price.getText().toString().trim()) == 0) {
                ToastUtil.shortToast(ProOfferActivity.this, getString(R.string.hourly_price) + "" + getString(R.string.not_zero));
                return;
            }

            if (tv_offer_yc.isSelected()) {
                if (et_offer_content.getText() == null || et_offer_content.getText().toString().equals("")) {
                    Toast.makeText(ProOfferActivity.this, getString(R.string.hin2), Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            CustomDialog.Builder customBuilder = new CustomDialog.Builder(ProOfferActivity.this);
            customBuilder.setTitle(getString(R.string.app_tip)).setMessage(getString(R.string.sda)).setPositiveButton(getString(R.string.message_alert_yes),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            qdbj();
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
        } else if (v.getId() == R.id.iv_smf2) {
            ConPopView cpv = new ConPopView(ProOfferActivity.this, getString(R.string.tv_RealIncomedetails));
            cpv.showPopUpWindow(v, 100, 0);
        }
    }

    private void qdbj() {
        homeFeeStr = et_offer_content.getText().toString().trim();
        if (isFlag) {
            map.put("service_type", SERVICE_TYPE_1);
            generalStr = "";
            hourlyStr = et_input_price.getText().toString().trim();
        } else {
            map.put("service_type", SERVIEC_TYPE_2);
            hourlyStr = "";
            generalStr = et_input_price.getText().toString().trim();
        }
        LoadDialog.showProgressDialog(this);
        map.put("general", generalStr);
        map.put("hourly", hourlyStr);
        map.put("message", "");
        map.put("home_fee", homeFeeStr);
        map.put("secret_key", SPUtils.getString(ProOfferActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProOfferActivity.this, Contants.order_offer, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(">>>>>>>报价成功>>>>>>>" + response);
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(ProOfferActivity.this, getString(R.string.ti_bj));
                    mOrderRefreshDataListener.orderPriceRefresh();
                    finish();
//                    Message msg = Message.obtain();
//                    msg.what = 0;
//                    mHandler.sendMessage(msg);
                } else {
                    mOrderRefreshDataListener.orderPriceRefresh();
                    finish();
//                    ToastUtil.shortToast(ProOfferActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    public class mTextWatcher implements TextWatcher {
        int i = 0;

        public mTextWatcher(int num) {
            i = num;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (i == 0) {
                if (isFlag) {
                    if (!TextUtils.isEmpty(editable.toString())) {
                        tv_pric_content.setText(getString(R.string.jy_dw) + editable.toString());
                        if (editable.toString().equals("")) {
                            mHandler.sendEmptyMessage(2);
                        } else {
                            Float v = (Float.valueOf(editable.toString()) * (100 - Float.valueOf(data))) / 100;
                            Message message = new Message();
                            message.what = 1;
                            Bundle bundle = new Bundle();
                            bundle.putFloat("price", v);
                            message.setData(bundle);
                            mHandler.sendMessage(message);
                        }

                    } else {
                        mHandler.sendEmptyMessage(2);
                        tv_pric_content.setText(R.string.dd);
                    }
                } else {
                    if (!TextUtils.isEmpty(editable.toString())) {
                        tv_pric_content.setText(getString(R.string.jy_dw) + editable.toString());
                        if (editable.toString().equals("")) {
                            mHandler.sendEmptyMessage(2);
                        } else {
                            Float v = (Float.valueOf(editable.toString()) * (100 - Float.valueOf(data))) / 100;
                            Message message = new Message();
                            message.what = 1;
                            Bundle bundle = new Bundle();
                            bundle.putFloat("price", v);
                            message.setData(bundle);
                            mHandler.sendMessage(message);
                        }

                    } else {
                        tv_pric_content.setText(R.string.dd);
                        mHandler.sendEmptyMessage(2);
                    }
                }
            } else if (i == 1) {
                float fcount = 0.0f, f1 = 0.0f;
                if (!isFlag && !TextUtils.isEmpty(et_input_price.getText().toString().trim())) {
                    fcount = Float.valueOf(et_input_price.getText().toString().trim());
                    if (!TextUtils.isEmpty(et_offer_content.getText().toString().trim())) {
                        f1 = Float.valueOf(et_offer_content.getText().toString().trim());
                        if (f1 > fcount) {
                            ToastUtil.longToast(ProOfferActivity.this, getString(R.string.ti12));
                            et_offer_content.setText("");
                        }
                    }
                }
            }

        }
    }

    ;

    public static OrderRefreshDataListener mOrderRefreshDataListener;


    public interface OrderRefreshDataListener {
        void orderPriceRefresh();

    }

    public static void setOrderRefreshDataListener(OrderRefreshDataListener orderRefreshDataListener) {
        mOrderRefreshDataListener = orderRefreshDataListener;
    }


}
