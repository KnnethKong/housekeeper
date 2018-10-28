package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.WebViewActivity;
import com.haiwai.housekeeper.activity.server.ProRechargeActivity;
import com.haiwai.housekeeper.activity.server.RentPriceActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.OrderConfigEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.entity.ZYEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SerializableMap;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.ConfigView;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/19.
 * 充值
 */
public class RechargeActivity extends BaseActivity {
//    private TextView tv_paypal, tv_money;
//    private LinearLayout recharge_ll_bottom;
    private TextView tv_money;
    private EditText et_je;
    boolean isChecked = false;
    User user;
    Map<String, String> map = new HashMap<>();
    private SeekBar sekbar;
    private SeekBar seek;
    private float tmpMoney=0;
    private TextView tv_user_balance,tv_maintan;
    private double leftMoney;
    private double thisMoney = 0;

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_recharge, null);// TODO: 2016/9/19 seekbar
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.wallet_cz), Color.parseColor("#FF3C3C3C"));
        tv_maintan = ((TextView) findViewById(R.id.tv_user_maintan));
        seek = ((SeekBar) findViewById(R.id.tv_balance_seek));
        tv_user_balance = ((TextView) findViewById(R.id.tv_user_balance));
        seek.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        ((TextView) findViewById(R.id.tv_money_desc)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        tv_user_balance.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        tv_maintan.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        ((TextView) findViewById(R.id.tv_renewal)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        ((TextView) findViewById(R.id.tv_recharge_money_desc)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        ((TextView) findViewById(R.id.tv_service_money)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        ((TextView) findViewById(R.id.tv_service_money)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        ((TextView) findViewById(R.id.tv_service_recharge_money)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        leftMoney = Double.valueOf(getIntent().getStringExtra("moneyLeft"));

        tv_user_balance.setText("$"+leftMoney);

        et_je = (EditText) findViewById(R.id.et_je);
        tv_money = (TextView) findViewById(R.id.tv_money);
        sekbar = (SeekBar) findViewById(R.id.sekbar);
        sekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                et_je.setText((thisMoney * i) + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        findViewById(R.id.tv_renewal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RechargeActivity.this, ProRechargeActivity.class);
                if(et_je.getText().toString().trim().replace("$", "").equals("")){
                    ToastUtil.shortToast(RechargeActivity.this, getString(R.string.no_money));
                    return;
                }
                if(et_je.getText().toString().trim().contains("$")){
                    intent.putExtra("money",et_je.getText().toString().trim().replace("$", ""));
                }else {
                    intent.putExtra("money",et_je.getText().toString().trim());
                }

                startActivity(intent);
            }
        });
    }

    private OrderConfigEntity mOrderConfigEntity;
    @Override
    protected void initData() {
        user = AppGlobal.getInstance().getUser();
        Map<String, String> mawp = new HashMap<>();
        mawp.put("uid", user.getUid());
        mawp.put("secret_key", SPUtils.getString(RechargeActivity.this, "secret", ""));
        mawp.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(RechargeActivity.this, Contants.self_dingzhi, mawp, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.print(">>>>>>>>>>>>>>>>>>" + response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    mOrderConfigEntity = new Gson().fromJson(response, OrderConfigEntity.class);
                    bindDataView(mOrderConfigEntity);
                } else {
                    ToastUtil.longToast(RechargeActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    @Override
    protected void click(View v) {

        LoadDialog.showProgressDialog(RechargeActivity.this);
//        if (v.getId() == R.id.recharge_ll_bottom) {
//            String money = et_je.getText().toString().trim();
//            map.put("uid", user.getUid());
//            map.put("money", money);
//            map.put("secret_key", SPUtils.getString(RechargeActivity.this, "secret", ""));
//            map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
//            PlatRequest request = new PlatRequest(RechargeActivity.this, Contants.paypalzhi, map, null, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    int code = JsonUtils.getJsonInt(response, "status");
//                    System.out.println("充值》》》》》" + response);
//                    if (code == 200) {
//                        LoadDialog.closeProgressDialog();
//                        String url = JsonUtils.getJsonStr(response, "data");
//                        System.out.println("充值url》》》》》" + url);
//                        Intent intent = new Intent(RechargeActivity.this, WebViewActivity.class);
//                        intent.putExtra("url", url);
//                        intent.putExtra("isJie",true);
//                        startActivity(intent);
//                    } else {
//                        LoadDialog.closeProgressDialog();
//                        ToastUtil.longToast(RechargeActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
//                    }
//
//                }
//            });
//            request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
//            MyApp.getTingtingApp().getRequestQueue().add(request);
//        }
    }

    private void bindDataView(OrderConfigEntity orderConfigEntity) {
        final List<OrderConfigEntity.DataBean.DateBean> date = orderConfigEntity.getData().getDate();
        if (date != null && date.size() > 0) {
            for (int i = 0; i < date.size(); i++) {
                if (date.get(i).is_xia.equals("1")) {
                    thisMoney += Double.valueOf(date.get(i).ben_money);
                } else {

                }
            }
            tv_money.setText("$"+thisMoney);
            et_je.setText(thisMoney+"");
            Log.i("fdsfdsbe",thisMoney+"");
            int num = (int)(leftMoney/thisMoney);


            if (AppGlobal.getInstance().getLagStr().equals("en")) {
                if (num >= 12) {
                    tv_maintan.setText("The current balance is enough to cover the service over" + 1 + " year");
                    seek.setProgress(12);
                } else {
                    tv_maintan.setText("The current balance is enough to cover the service" + num + "month");
                    seek.setProgress(num);
                }

            } else {
                if (num >= 12) {
                    tv_maintan.setText("当前月余额可以支付超过1年的服务费用");
                    seek.setProgress(12);
                } else {
                    tv_maintan.setText("当前月余额可以支付超过" + num + "个月的服务费用");
                    seek.setProgress(num);
                }
            }

//
//            if(num>=1&&num<=12){
//                seek.setProgress(num);
//                tv_maintan.setText(getString(R.string.maintain)+num+getString(R.string.main_need_month));
//            }else if(num<1){
//                seek.setProgress(0);
//                tv_maintan.setText(getString(R.string.maintain)+"0"+getString(R.string.main_need_month));
//            }else if(num>12){
//                seek.setProgress(12);
//                tv_maintan.setText(getString(R.string.maintain)+"12"+getString(R.string.main_need_month));
//            }

        }
    }
}
