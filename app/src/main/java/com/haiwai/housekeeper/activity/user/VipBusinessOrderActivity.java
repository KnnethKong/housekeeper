package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.entity.VipBusinessOrderEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.DatePicker2PopView;
import com.haiwai.housekeeper.view.DatePickerPopView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/19.
 * VIP——物业账单
 */
public class VipBusinessOrderActivity extends BaseActivity {
    private TextView tv_fy_zj, tv_ydtxf, tv_yxdsf, tv_gdhf, tv_wsf, tv_wltxf, tv_wyf, tv_rqf, tv_sdf;
    private TextView tv_month_history, tv_home_id;
    private String uid = "";
    private String h_id = "";
    private String month = "";
    User user;
    Map<String, String> map = new HashMap<>();
    private String isZhorEn = "";

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_vip_business_order, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.business_order_title), Color.parseColor("#FF3C3C3C"));
        tv_fy_zj = (TextView) findViewById(R.id.tv_fy_zj);
        tv_ydtxf = (TextView) findViewById(R.id.tv_ydtxf);
        tv_yxdsf = (TextView) findViewById(R.id.tv_yxdsf);
        tv_gdhf = (TextView) findViewById(R.id.tv_gdhf);
        tv_wsf = (TextView) findViewById(R.id.tv_wsf);
        tv_wltxf = (TextView) findViewById(R.id.tv_wltxf);
        tv_wyf = (TextView) findViewById(R.id.tv_wyf);
        tv_rqf = (TextView) findViewById(R.id.tv_rqf);
        tv_sdf = (TextView) findViewById(R.id.tv_sdf);
        tv_month_history = (TextView) findViewById(R.id.tv_month_history);
        tv_month_history.setOnClickListener(this);
        tv_home_id = (TextView) findViewById(R.id.tv_home_id);
        tv_home_id.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        isZhorEn = AppGlobal.getInstance().getLagStr();
        user = AppGlobal.getInstance().getUser();
        uid = user.getUid();
        map.put("uid", uid);
        map.put("h_id", h_id);
        map.put("month", month);
        map.put("secret_key", SPUtils.getString(VipBusinessOrderActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(VipBusinessOrderActivity.this, Contants.utility_bill, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("物业账单-------------》" + response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    VipBusinessOrderEntity orderEntity = new Gson().fromJson(response, VipBusinessOrderEntity.class);
                    bintDataToView(orderEntity);
                } else {
                    ToastUtil.longToast(VipBusinessOrderActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void bintDataToView(VipBusinessOrderEntity orderEntity) {
        VipBusinessOrderEntity.DataBeanX.DataBean data = orderEntity.getData().getData();
        if (data != null) {
            if (0 == data.getWater_jiao()) {
                tv_sdf.setText("$" + data.getWater() + getString(R.string.business_order_own_money));
            } else if (1 == data.getWater_jiao()) {
                tv_sdf.setText("$" + data.getWater());
            }
            if (0 == data.getGas_jiao()) {
                tv_rqf.setText("$" + data.getGas() +  getString(R.string.business_order_own_money));
            } else if (1 == data.getGas_jiao()) {
                tv_rqf.setText("$" + data.getGas());
            }
            if (0 == data.getProperty_jiao()) {
                tv_wyf.setText("$" + data.getProperty() + getString(R.string.business_order_own_money));
            } else if (1 == data.getProperty_jiao()) {
                tv_wyf.setText("$" + data.getProperty());
            }
            if (0 == data.getNetwork_jiao()) {
                tv_wltxf.setText("$" + data.getNetwork() +  getString(R.string.business_order_own_money));
            } else if (1 == data.getNetwork_jiao()) {
                tv_wltxf.setText("$" + data.getNetwork());
            }
            if (0 == data.getPublic_jiao()) {
                tv_wsf.setText("$" + data.getPublicX() +  getString(R.string.business_order_own_money));
            } else if (1 == data.getPublic_jiao()) {
                tv_wsf.setText("$" + data.getPublicX());
            }
            if (0 == data.getFixed_jiao()) {
                tv_gdhf.setText("$" + data.getFixed() +  getString(R.string.business_order_own_money));
            } else if (1 == data.getFixed_jiao()) {
                tv_gdhf.setText("$" + data.getFixed());
            }
            if (0 == data.getCable_jiao()) {
                tv_yxdsf.setText("$" + data.getCable() +  getString(R.string.business_order_own_money));
            } else if (1 == data.getCable_jiao()) {
                tv_yxdsf.setText("$" + data.getCable());
            }
            if (0 == data.getMobile_jiao()) {
                tv_ydtxf.setText("$" + data.getMobile() +  getString(R.string.business_order_own_money));
            } else if (1 == data.getMobile_jiao()) {
                tv_ydtxf.setText("$" + data.getMobile());
            }
            int count = data.getWater() + data.getGas() +
                    data.getProperty() + data.getNetwork() +
                    data.getPublicX() + data.getFixed() +
                    data.getCable() + data.getMobile();
            tv_fy_zj.setText("$" + count);
        }
    }

    @Override
    protected void click(View v) {
        if (v.getId() == R.id.tv_month_history) {

            DatePicker2PopView popView = new DatePicker2PopView(VipBusinessOrderActivity.this);
            popView.openPopWindow(v);
            popView.setDatePicOnClickListener(new DatePicker2PopView.DatePicOnClickListener() {
                @Override
                public void datePicker(String str) {
                    month = TimeUtils.getMonthStampMore(str);
                    //month = TimeUtils.getMonthStamp("2017-08-18 17:32:51");
                    //Log.e("result----->",month+"");
                    initData();
                }
            });
        } else if (v.getId() == R.id.tv_home_id) {
            Bundle bundle = new Bundle();
            bundle.putString("flag","design");
            ActivityTools.goNextActivityForResult(this, VipHouseChooseActivity.class, bundle, 99);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99) {
            if (resultCode == RESULT_OK) {
                tv_home_id.setText(data.getStringExtra("addr"));
                h_id = data.getStringExtra("h_id");
                ToastUtil.longToast(VipBusinessOrderActivity.this, h_id);
                tv_home_id.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }
        }
    }
}
