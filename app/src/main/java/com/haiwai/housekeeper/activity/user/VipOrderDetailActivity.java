package com.haiwai.housekeeper.activity.user;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.PaseJsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.utils.WDUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/5.
 * vip订单详情自营
 */
public class VipOrderDetailActivity extends BaseActivity {
    private String isZhorEn = "";
    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_order_detail_beiyong, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.need_order_detail), Color.parseColor("#FF3C3C3C"));
//        下面为beiyongxml文件的代码
        TextView tvWalletMoney = (TextView) findViewById(R.id.user_order_detail_tv_wallet_num);
        tvWalletMoney.setText(Html.fromHtml("("+getString(R.string.in_use) + "<font color='#ff0000' size='17'>" + "$2000" + "</font>" + ")"));
        tvWalletMoney.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
        findViewById(R.id.user_order_detail_ll_bottom_beiyong).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        requestVipDesign();
    }

    private void requestVipDesign() {
        String h_id=getIntent().getExtras().getString("h_id");
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("h_id", h_id);
        map.put("month", "");
        map.put("secret_key", SPUtils.getString(VipOrderDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(VipOrderDetailActivity.this, Contants.home_order, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (200 == code) {
                } else {
                    ToastUtil.shortToast(VipOrderDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }

            }
        }));
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.user_order_detail_ll_bottom_beiyong:
                ActivityTools.goNextActivity(this, RechargeActivity.class);
                break;
        }
    }
}
