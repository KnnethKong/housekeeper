package com.haiwai.housekeeper.activity.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.RegisterActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.CodesEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChangePhoneActivity extends AppCompatActivity {

    private TextView tvCountry, tvDone, tvCode;
    private EditText etPhone, etCode;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);
        initView();
        initListener();
        getCountryCode();
    }

    private void initListener() {
        findViewById(R.id.iv_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCode();
            }
        });
        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePhone();
            }
        });
        tvCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopu(view);
            }
        });
    }

    private void changePhone(){
        if(etPhone.getText()==null||etPhone.getText().toString().equals("")){
            ToastUtil.shortToast(this,getString(R.string.login_input_phone));
            return;
        }

        if(etCode.getText()==null||etCode.getText().toString().equals("")){
            ToastUtil.shortToast(this,getString(R.string.inp_code));
            return;
        }
        Map<String,String> map = new HashMap<>();
        map.put("mobile", etPhone.getText().toString().trim());
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("code",etCode.getText().toString());
        map.put("secret_key", SPUtils.getString(ChangePhoneActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ChangePhoneActivity.this, Contants.change_info, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LogUtil.e("zc", response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    String data = JsonUtils.getJsonStr(response, "data");
                    AppGlobal.getInstance().setUser(new Gson().fromJson(data, User.class));
                    ToastUtil.shortToast(ChangePhoneActivity.this, getString(R.string.commit_success));
                    ChangePhoneActivity.this.setResult(RESULT_OK);
                    finish();
                } else {
                    ToastUtil.shortToast(ChangePhoneActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }



    private void getCode() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "5");
        map.put("area", code1);
        map.put("mobile", etPhone.getText().toString().trim());
        map.put("secret_key", SPUtils.getString(ChangePhoneActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(this, Contants.get_verify_code, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(ChangePhoneActivity.this, getString(R.string.code_tis));
                    PlatUtils.startTimer(tvCode);
                } else {
                    ToastUtil.shortToast(ChangePhoneActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void initView() {
        tvCountry = ((TextView) findViewById(R.id.tv_change_country));
        tvDone = ((TextView) findViewById(R.id.tv_change_done));
        tvCode = ((TextView) findViewById(R.id.tv_get_code));
        etCode = ((EditText) findViewById(R.id.tv_input_code));
        etPhone = ((EditText) findViewById(R.id.et_phone_number));
        etPhone.setText(getIntent().getStringExtra("phone"));
    }

    private List<String> codeList;

    private List<String> counList;

    private void getCountryCode() {
        if(etPhone.getText()==null||etPhone.getText().toString().equals("")){
            ToastUtil.shortToast(this,getString(R.string.login_input_phone));
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("secret_key", SPUtils.getString(ChangePhoneActivity.this, "secret", ""));
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ChangePhoneActivity.this, Contants.country_code, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int codeRes = JsonUtils.getJsonInt(response, "status");
                if (codeRes == 200) {
                    CodesEntity codesEntity = new Gson().fromJson(response, CodesEntity.class);
                    codeList = new ArrayList<>();
                    counList = new ArrayList<>();
                    for (int i = 0; i < codesEntity.getData().size(); i++) {
                        codeList.add(codesEntity.getData().get(i).getCode());
                        if(AppGlobal.getInstance().getLagStr().equals("en")){
                            counList.add(codesEntity.getData().get(i).getNameen());
                        }else{
                            counList.add(codesEntity.getData().get(i).getNamecn());
                        }

                    }
                    code1 = codeList.get(0);
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        tvCountry.setText(codesEntity.getData().get(0).getNameen());
                    }else{
                        tvCountry.setText(codesEntity.getData().get(0).getNamecn());
                    }

                }
            }
        }));

    }


    private PopupWindow pop;
    private String code1;

    private void showPopu(View view1) {
        View view = LayoutInflater.from(this).inflate(R.layout.code_show_popu_view, null);
        LinearLayout ll = ((LinearLayout) view.findViewById(R.id.ll_code_list_view));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 30);
        params.topMargin = 15;
        params.bottomMargin = 10;
        params.weight = 1;
        for (int i = 0; i < codeList.size(); i++) {
            final TextView tv = new TextView(this);
            tv.setLayoutParams(params);
            tv.setText(counList.get(i));
            tv.setTag(i);
            tv.setGravity(Gravity.CENTER);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hidePopu();
                    code1 = codeList.get(Integer.valueOf(tv.getTag().toString()));
                    tvCountry.setText(counList.get(Integer.valueOf(tv.getTag().toString())));
                }
            });
            ll.addView(tv);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop.dismiss();
            }
        });
        WindowManager manager = ((WindowManager) getSystemService(WINDOW_SERVICE));
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        pop = new PopupWindow(view, metrics.widthPixels-50, metrics.widthPixels / 2);
        pop.setOutsideTouchable(false);
        pop.showAtLocation(view1,Gravity.CENTER,0,0);
    }

    private void hidePopu() {
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(pop!=null&&pop.isShowing()){
                pop.dismiss();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
