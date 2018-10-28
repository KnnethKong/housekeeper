package com.haiwai.housekeeper.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.MyAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.CodesEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ActivityManagerUtil;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.NetUtil;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.TopViewNormalBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 忘记密码
 */
public class GetPassActivity extends BaseProActivity {
    private EditText etTel, etCode, etNewP;
    private TextView tvGetCode;
    private Button ibConfirm;
    private TopViewNormalBar top_getpass_bar;
    private String isZhorEn = "";
    private Spinner mspinner;
    MyAdapter mAdapter;
    CodesEntity codesEntity;
    private String code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_pass);
        top_getpass_bar = (TopViewNormalBar) findViewById(R.id.top_getpass_bar);
        top_getpass_bar.setTitle(getString(R.string.title_forget_pass));
        top_getpass_bar.setOnBackListener(mOnClickListener);
        initView();
        initData();
    }

    private void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        Map<String, String> map = new HashMap<>();
        map.put("secret_key", SPUtils.getString(GetPassActivity.this, "secret", ""));
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(GetPassActivity.this, Contants.country_code, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    codesEntity = new Gson().fromJson(response, CodesEntity.class);
                    bindData(codesEntity);
                }
            }
        }));

    }

    private void bindData(final CodesEntity codesEntity) {
        mAdapter = new MyAdapter<CodesEntity.DataBean>((ArrayList<CodesEntity.DataBean>) codesEntity.getData(), R.layout.hous_adapter_text_item) {
            @Override
            public void bindView(ViewHolder holder, CodesEntity.DataBean obj) {
                if ("en".equals(isZhorEn)) {
                    holder.setText(R.id.tv_content, obj.getNameen());
                } else {
                    holder.setText(R.id.tv_content, obj.getNamecn());
                }

            }
        };
        mspinner.setAdapter(mAdapter);
        mspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                code = codesEntity.getData().get(i).getCode();
//                ToastUtil.longToast(GetPassActivity.this, code);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mAdapter.notifyDataSetChanged();
    }

    private void initView() {
        etTel = (EditText) findViewById(R.id.et_tel);
        etCode = (EditText) findViewById(R.id.et_code);
        etNewP = (EditText) findViewById(R.id.et_new_pass);
        tvGetCode = (TextView) findViewById(R.id.tv_get_code);
        ibConfirm = (Button) findViewById(R.id.ib_btn_confirm);
        tvGetCode.setOnClickListener(mOnClickListener);
        ibConfirm.setOnClickListener(mOnClickListener);
        mspinner = (Spinner) findViewById(R.id.mspinner);

    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == top_getpass_bar.getBackView()) {
                finish();
            } else if (v.getId() == R.id.tv_get_code) {
                if (!NetUtil.isNetworkAvailable(GetPassActivity.this)) {
                    return;
                }
                if (PlatUtils.getEditStr(etTel)) {
                    ToastUtil.shortToast(GetPassActivity.this, getString(R.string.login_input_phone));
                    return;
                }
                Map<String, String> map = new HashMap<>();
                map.put("secret_key", SPUtils.getString(GetPassActivity.this, "secret", ""));
                map.put("type", "2");
                map.put("area", code);
                map.put("mobile", etTel.getText().toString().trim());
                MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(GetPassActivity.this, Contants.get_verify_code, map, null, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        LogUtil.e("yzm", response);
                        int code = JsonUtils.getJsonInt(response, "status");
                        if (code == 200) {
//                            ToastUtil.shortToast(GetPassActivity.this, getString(R.string.code_tis));
                            PlatUtils.startTimer(tvGetCode);
                        } else {
                            ToastUtil.shortToast(GetPassActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                }));
            } else if (v.getId() == R.id.tv_get_pass) {
                startActivity(new Intent(GetPassActivity.this, GetPassActivity.class));
            } else if (v.getId() == R.id.ib_btn_confirm) {
                if (!NetUtil.isNetworkAvailable(GetPassActivity.this)) {
                    return;
                }
                if (PlatUtils.getEditStr(etTel)) {
                    ToastUtil.shortToast(GetPassActivity.this, getString(R.string.login_input_phone));
                    return;
                }
                if (PlatUtils.getEditStr(etCode)) {
                    ToastUtil.shortToast(GetPassActivity.this, getString(R.string.inp_code));
                    return;
                }
                if (PlatUtils.getEditStr(etNewP)) {
                    ToastUtil.shortToast(GetPassActivity.this, getString(R.string.login_input_pass));
                    return;
                }
                Map<String, String> map = new HashMap<>();
                map.put("secret_key", SPUtils.getString(GetPassActivity.this, "secret", ""));
                map.put("mobile", etTel.getText().toString().trim());
                map.put("code", etCode.getText().toString().trim());
                map.put("pwd", etNewP.getText().toString().trim());
                MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(GetPassActivity.this, Contants.find_back_password, map, null, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        LogUtil.e("zc", response);
                        int code = JsonUtils.getJsonInt(response, "status");
                        if (code == 200) {
                            startActivity(new Intent(GetPassActivity.this, LoginActivity.class));
                            finish();
                        } else {
                            ToastUtil.shortToast(GetPassActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                }));
            }
        }
    };
}
