package com.haiwai.housekeeper.activity.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.RegisterActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.widget.LoadDialog;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class BindPayPalAccount extends AppCompatActivity {

    private EditText et_account, et_re_account, et_bind_code;
    private TextView tvCode;
    private String isZhorEn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_pay_pal_account);
        initView();
    }

    private String type, paypal_url;
    private TextView tv_change;

    private void initView() {
        et_account = ((EditText) findViewById(R.id.input_paypal_account));
        et_re_account = ((EditText) findViewById(R.id.input_re_paypal_account));
        et_bind_code = ((EditText) findViewById(R.id.input_paypal_code));
        tvCode = ((TextView) findViewById(R.id.tv_get_bind_code));
        isZhorEn = AppGlobal.getInstance().getLagStr();
        tv_change = ((TextView) findViewById(R.id.tv_chanage_paypal));
        if (getIntent().getBooleanExtra("isMine", true)) {
            LoadDialog.showProgressDialog(this);
            Map<String, String> map = new HashMap<String, String>();
            map.put("uid", AppGlobal.getInstance().getUser().getUid());
            map.put("secret_key", SPUtils.getString(BindPayPalAccount.this, "secret", ""));
            map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(BindPayPalAccount.this, Contants.get_paypal, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    LoadDialog.closeProgressDialog();
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        try {
                            String paypal = new JSONObject(response).getJSONObject("data").getString("paypal_account");
                            if (paypal != null && !paypal.equals("")&&!paypal.equals("null")) {
                                type = "4";
                                et_re_account.setText(paypal);
                                et_account.setText(paypal);
                                paypal_url = Contants.save_paypal;
                                et_account.setEnabled(false);

                            } else {
                                findViewById(R.id.ll_add_new_account).setVisibility(View.VISIBLE);
                                findViewById(R.id.tv_chanage_paypal).setVisibility(View.GONE);
                                findViewById(R.id.tv_bind_done).setVisibility(View.VISIBLE);
                                type = "3";
                                paypal_url = Contants.add_paypal;
                                et_account.setCursorVisible(true);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }));
        } else {
            findViewById(R.id.ll_add_account).setVisibility(View.VISIBLE);
            findViewById(R.id.ll_add_new_account).setVisibility(View.VISIBLE);
            tv_change.setVisibility(View.GONE);
        }

        initClick();
    }

    private void initClick() {
        findViewById(R.id.iv_finish_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.tv_bind_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(et_account.getText().toString())) {
                    ToastUtil.shortToast(BindPayPalAccount.this, getString(R.string.login_hint_account));
                    return;
                }

                if (TextUtils.isEmpty(et_re_account.getText().toString())) {
                    ToastUtil.shortToast(BindPayPalAccount.this, getString(R.string.re_paypal_account));
                    return;
                }

                if (TextUtils.isEmpty(et_bind_code.getText().toString())) {
                    ToastUtil.shortToast(BindPayPalAccount.this, getString(R.string.inp_code));
                    return;
                }
                if (!et_account.getText().toString().trim().equals(et_re_account.getText().toString().trim())) {
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        ToastUtil.shortToast(BindPayPalAccount.this, "The two entered account is inconsistent");
                    }else{
                        ToastUtil.shortToast(BindPayPalAccount.this, "两次输入的账号不一致");
                    }
                    return;
                }
                LoadDialog.showProgressDialog(BindPayPalAccount.this);
                Map<String, String> map = new HashMap<>();
                map.put("paypal_account", et_account.getText().toString());
                map.put("mobile", AppGlobal.getInstance().getUser().getMobile());
                map.put("code", et_bind_code.getText().toString());
                map.put("secret_key", SPUtils.getString(BindPayPalAccount.this, "secret", ""));
                map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
                MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(BindPayPalAccount.this, paypal_url, map, null, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int code = JsonUtils.getJsonInt(response, "status");
                        LoadDialog.closeProgressDialog();
                        if (code == 200) {
//                            ToastUtil.shortToast(BindPayPalAccount.this, getString(R.string.code_tis));
                            ToastUtil.shortToast(BindPayPalAccount.this, getString(R.string.zt25));
                            finish();
                        } else if (code == 1409) {
                            ToastUtil.shortToast(BindPayPalAccount.this, "Paypal账号已有，不能继续添加");
                        } else if (code == 1413) {
                            ToastUtil.shortToast(BindPayPalAccount.this, getString(R.string.conque_account));
                        } else if (code == 21800) {
                            ToastUtil.shortToast(BindPayPalAccount.this, "您的账号正在审核中");
                        } else {
                            ToastUtil.shortToast(BindPayPalAccount.this, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                }));
            }
        });

        tvCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(TextUtils.isEmpty(et_account.getText().toString())){
//                    ToastUtil.shortToast(BindPayPalAccount.this,getString(R.string.login_hint_account));
//                    return;
//                }
                PlatUtils.startTimer(((TextView) findViewById(R.id.tv_get_bind_code)));
                Map<String, String> map = new HashMap<>();
                map.put("type", type);
                map.put("mobile", AppGlobal.getInstance().getUser().getMobile());
                map.put("area", AppGlobal.getInstance().getUser().getArea());
                map.put("secret_key", SPUtils.getString(BindPayPalAccount.this, "secret", ""));
                map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
                MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(BindPayPalAccount.this, Contants.get_verify_code, map, null, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int code = JsonUtils.getJsonInt(response, "status");
                        if (code == 200) {
                            ToastUtil.shortToast(BindPayPalAccount.this, getString(R.string.code_tis));
                            PlatUtils.startTimer(tvCode);
                        } else {
                            ToastUtil.shortToast(BindPayPalAccount.this, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                }));
            }
        });


        tv_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                findViewById(R.id.ll_add_new_account).setVisibility(View.VISIBLE);
                findViewById(R.id.tv_chanage_paypal).setVisibility(View.GONE);
                findViewById(R.id.tv_bind_done).setVisibility(View.VISIBLE);
                et_account.setEnabled(true);
                et_account.setCursorVisible(true);
            }
        });
        findViewById(R.id.iv_finish_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
