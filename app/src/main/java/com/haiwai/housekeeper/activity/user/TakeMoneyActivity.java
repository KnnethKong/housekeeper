package com.haiwai.housekeeper.activity.user;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/7.
 * 我的——我的钱包——提现
 */
public class TakeMoneyActivity extends BaseActivity {
    private EditText et_input_money, et_input_account;
    User user;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    LoadDialog.closeProgressDialog();
                    break;
            }
        }
    };

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_take_money, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.take_money_title), Color.parseColor("#FF3C3C3C"));
        et_input_money = (EditText) findViewById(R.id.et_input_money);
        et_input_account = (EditText) findViewById(R.id.et_input_account);
        findViewById(R.id.take_money_commit).setOnClickListener(this);
        ((TextView) findViewById(R.id.tv_withdraw_momey)).setText(getIntent().getStringExtra("money"));
    }

    @Override
    protected void initData() {
        user = AppGlobal.getInstance().getUser();
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.take_money_commit:
                LoadDialog.showProgressDialog(TakeMoneyActivity.this);
                String moneyStr = et_input_money.getText().toString().trim();
                String accountStr = et_input_account.getText().toString().trim();
                Map<String, String> map = new HashMap<>();
                map.put("uid", user.getUid());
                map.put("money", moneyStr);
                map.put("email", accountStr);
                map.put("secret_key", SPUtils.getString(TakeMoneyActivity.this, "secret", ""));
                map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
                PlatRequest platRequest = new PlatRequest(TakeMoneyActivity.this, Contants.paypalti, map, null, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int code = JsonUtils.getJsonInt(response, "status");
                        System.out.print("》》》》提现>>>>>>" + response);
                        if (code == 200) {
                            ToastUtil.longToast(TakeMoneyActivity.this, "提现成功");
                            Message msg = Message.obtain();
                            msg.what = 0;
                            mHandler.sendMessage(msg);
                        } else {
                            ToastUtil.longToast(TakeMoneyActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                });
                platRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
                MyApp.getTingtingApp().getRequestQueue().add(platRequest);
                break;
        }

    }
}
