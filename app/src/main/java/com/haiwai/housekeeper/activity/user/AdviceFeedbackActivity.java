package com.haiwai.housekeeper.activity.user;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/7.
 * 我的——设置——意见反馈
 */
public class AdviceFeedbackActivity extends BaseActivity {
    private EditText etcontent;
    private String isZhorEn = "";

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_advice_feedback, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.advice_feedback_title), Color.parseColor("#FF3C3C3C"));
        etcontent = (EditText) findViewById(R.id.advice_feedback_et_content);
        findViewById(R.id.advice_feedback_tv_commit).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.advice_feedback_tv_commit:
                if (!isNetworkAvailable())
                    return;
                if (!mApp.isLogin())
                    return;
                if (PlatUtils.getEditStr(etcontent)) {
                    ToastUtil.shortToast(this, getString(R.string.advice_feedback_toast_1));
                    return;
                }

                Map<String, String> map = new HashMap<>();
                map.put("uid", AppGlobal.getInstance().getUser().getUid());
                map.put("content", etcontent.getText().toString().trim());
                map.put("secret_key", SPUtils.getString(AdviceFeedbackActivity.this, "secret", ""));
                map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
                mRequestQueue.add(new PlatRequest(this, Contants.feedback_add, map, null, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int code = JsonUtils.getJsonInt(response, "status");
                        if (code == 200) {
                            ToastUtil.shortToast(AdviceFeedbackActivity.this, getString(R.string.commit_success));
                            finish();
                        } else {
                            ToastUtil.shortToast(AdviceFeedbackActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                }));
                break;
        }
    }
}
