package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.TopViewNormalBar;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ModifyInfoActivity extends AppCompatActivity {
    TopViewNormalBar topInfoBar;
    private int style;
    private EditText etContent;
    private User user;
    Map<String, String> map = new HashMap<>();
    private String mStr;
    private String isZhorEn = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_info);
        topInfoBar = (TopViewNormalBar) findViewById(R.id.top_info_bar);
        topInfoBar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initData();
        initView();
    }

    private void initView() {
        etContent = (EditText) findViewById(R.id.et_content);
        if (style == PersonInfoActivity.MODIFYC){
            etContent.setInputType(InputType.TYPE_CLASS_PHONE);
        }
        etContent.setText(mStr);
    }

    private void initData() {
        user = AppGlobal.getInstance().getUser();
        mStr = getIntent().getStringExtra("content");
        style = getIntent().getIntExtra("type", -1);
        if (style == PersonInfoActivity.MODIFYA) {//用户名
            topInfoBar.setTitle(getString(R.string.show_user_name));
        } else if (style == PersonInfoActivity.MODIFYB) {//姓名
            topInfoBar.setTitle(getString(R.string.modify_name));
        } else if (style == PersonInfoActivity.MODIFYC) {//手机号
            topInfoBar.setTitle(getString(R.string.modify_phone));
        } else if (style == PersonInfoActivity.MODIFYD) {//简介
            topInfoBar.setTitle(getString(R.string.modify_my_introduce));
        }
        topInfoBar.setVisible(true);
        topInfoBar.setRightText(getString(R.string.save));
        topInfoBar.setFinishListener(mOnClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!etContent.getText().toString().equals("")){
            etContent.setSelection(etContent.getText().toString().length());
        }
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == topInfoBar.getBackView()) {
                finish();
            } else if (v == topInfoBar.getFinishTextView()) {
                switch (style) {
                    case PersonInfoActivity.MODIFYA://用户名
                        map.put("nickname", etContent.getText().toString().trim());
                        break;
                    case PersonInfoActivity.MODIFYB://姓名
                        map.put("name", etContent.getText().toString().trim());
                        break;
                    case PersonInfoActivity.MODIFYC://手机号
                        map.put("mobile", etContent.getText().toString().trim());
                        break;
                    case PersonInfoActivity.MODIFYD://简介
                        map.put("introduction", etContent.getText().toString().trim());
                        break;
                }
                map.put("uid", user.getUid());
                NetData(map);
            }
        }
    };

    private void NetData(Map<String, String> map) {
        if(style == PersonInfoActivity.MODIFYA){
            if(etContent.getText().toString().trim().equals("")){
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    ToastUtil.shortToast(this,"Please input username");
                }else{
                    ToastUtil.shortToast(this,"请输入用户名");
                }
                return ;
            }
        }
        map.put("secret_key", SPUtils.getString(ModifyInfoActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ModifyInfoActivity.this, Contants.change_info, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LogUtil.e("zc", response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    String data = JsonUtils.getJsonStr(response, "data");
                    AppGlobal.getInstance().setUser(new Gson().fromJson(data, User.class));
                    ToastUtil.shortToast(ModifyInfoActivity.this, getString(R.string.commit_success));
                    ModifyInfoActivity.this.setResult(RESULT_OK);
                    finish();
                } else {
                    ToastUtil.shortToast(ModifyInfoActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

}
