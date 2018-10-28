package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.WebViewActivity;
import com.haiwai.housekeeper.activity.server.ProRechargeActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.HomeTwoEntity;
import com.haiwai.housekeeper.entity.WxEntity;
import com.haiwai.housekeeper.entity.ZfbEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.https.okhttp.OKRequestCallback;
import com.haiwai.housekeeper.https.okhttp.OkHttpUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.PayResult;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

public class OrderPayActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_paypay, tv_we_chat, tv_ali_pay, tv_should_pay;
    //tv_alipay_money, tv_wechat_money, tv_paypal_money;
    private static final int SDK_PAY_FLAG = 1;
    private String money, hv;
    private String oid;
    private String j_uid;
    private TextView tv_hl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pay);
        TopViewNormalBar top_ordered_bar = (TopViewNormalBar) findViewById(R.id.top_ordered_bar);
        top_ordered_bar.setTitle(getString(R.string.pay_for_quot));
        top_ordered_bar.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (!isWeixinAvilible("com.eg.android.AlipayGphone")) {
            findViewById(R.id.ll_zhifu_bao).setVisibility(View.GONE);
            findViewById(R.id.view_line1).setVisibility(View.GONE);
        }
        if (!isWeixinAvilible("com.tencent.mm")) {
            findViewById(R.id.ll_we_chat).setVisibility(View.GONE);
            findViewById(R.id.view_line2).setVisibility(View.GONE);
        }


        findViewById(R.id.ll_paypay_pay).setOnClickListener(this);
        findViewById(R.id.ll_we_chat).setOnClickListener(this);
        findViewById(R.id.ll_zhifu_bao).setOnClickListener(this);
        findViewById(R.id.tv_recharge_done).setOnClickListener(this);


        tv_ali_pay = ((TextView) findViewById(R.id.tv_ali_pay));
        tv_paypay = ((TextView) findViewById(R.id.tv_btn_pp_re));
        tv_we_chat = ((TextView) findViewById(R.id.tv_we_chat));
        tv_hl = ((TextView) findViewById(R.id.hl));
//        tv_ali_pay.setOnClickListener(this);
//        tv_paypay.setOnClickListener(this);
//        tv_we_chat.setOnClickListener(this);


//        tv_alipay_money = ((TextView) findViewById(R.id.tv_alipay_money));
//        tv_paypal_money = ((TextView) findViewById(R.id.tv_paypay_money));
//        tv_wechat_money = ((TextView) findViewById(R.id.tv_wechat_money));


        oid = getIntent().getStringExtra("oid");
        j_uid = getIntent().getStringExtra("j_uid");
        tv_should_pay = ((TextView) findViewById(R.id.tv_should_pay));

        tv_ali_pay.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        tv_paypay.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        tv_we_chat.setTypeface(MyApp.getTf(), Typeface.NORMAL);
//        tv_alipay_money.setTypeface(MyApp.getTf(), Typeface.NORMAL);
//        tv_paypal_money.setTypeface(MyApp.getTf(), Typeface.NORMAL);
//        tv_wechat_money.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        tv_should_pay.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        ((TextView) findViewById(R.id.tv_recharge_done)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        getMoney();
        initHvData();//kxf 新加 之前忘记调用

    }

    private void initHvData() {
        Map<String, String> map = new HashMap<>();
        map.put("secret_key", SPUtils.getString(OrderPayActivity.this, "secret", ""));
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderPayActivity.this, Contants.exchange_rate, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(">>>>>>>>>>>>>>>>>>ssss" + response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {//{"status":200,"data":44}
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        hv = jsonObject.getJSONObject("data").getString("rj");
                        //double zhMoney = mul(Double.valueOf(money), Double.valueOf(hv));
                        tv_hl.setText(getResources().getString(R.string.wxpay) + hv);
                    } catch (org.json.JSONException e) {
                        e.printStackTrace();
                    }
                    LoadDialog.closeProgressDialog();
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.longToast(OrderPayActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }


    private void paypal() {
        LoadDialog.showProgressDialog(this);
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("money", money);
        map.put("secret_key", SPUtils.getString(OrderPayActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        PlatRequest request = new PlatRequest(OrderPayActivity.this, Contants.paypalzhi, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                LoadDialog.closeProgressDialog();
                System.out.println("充值》》》》》" + response);
                if (code == 200) {
                    LoadDialog.closeProgressDialog();
                    String url = JsonUtils.getJsonStr(response, "data");
                    System.out.println("充值url》》》》》" + url);
                    Intent intent = new Intent(OrderPayActivity.this, WebViewActivity.class);
                    intent.putExtra("url", url);
                    intent.putExtra("isJie", true);
                    startActivity(intent);
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.longToast(OrderPayActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }

            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        MyApp.getTingtingApp().getRequestQueue().add(request);
    }

    private void aliPay() {
        LoadDialog.showProgressDialog(this);
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("oid", oid);
        map.put("secret_key", SPUtils.getString(OrderPayActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(this, map, null);
        Request request = new Request.Builder()
                .url(Contants.OrderAliPay)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    LoadDialog.closeProgressDialog();
                    ZfbEntity zfbEntity = new Gson().fromJson(response, ZfbEntity.class);
                    alipay(zfbEntity.getData());
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.longToast(OrderPayActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }


    private void weChat() {
        LoadDialog.showProgressDialog(this);
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("secret_key", SPUtils.getString(OrderPayActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        map.put("oid", oid);
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(this, map, null);
        Request request = new Request.Builder()
                .url(Contants.OrderWXPay)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    LoadDialog.closeProgressDialog();
                    WxEntity wxEntity = new Gson().fromJson(response, WxEntity.class);
                    weixinPay(wxEntity.getData());
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.longToast(OrderPayActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

// com.eg.android.AlipayGphone
    //"com.tencent.mm"

    public boolean isWeixinAvilible(String packerName) {
        final PackageManager packageManager = getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(packerName)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_paypay_pay:
                tv_paypay.setSelected(true);
                tv_we_chat.setSelected(false);
                tv_ali_pay.setSelected(false);
                break;
            case R.id.ll_we_chat:
//                Toast.makeText(this, "ll_we_chat", Toast.LENGTH_SHORT).show();
                tv_paypay.setSelected(false);
                tv_we_chat.setSelected(true);
                tv_ali_pay.setSelected(false);
                break;
            case R.id.ll_zhifu_bao:
//                Toast.makeText(this, "ll_zhifu_bao", Toast.LENGTH_SHORT).show();
                tv_paypay.setSelected(false);
                tv_we_chat.setSelected(false);
                tv_ali_pay.setSelected(true);
                break;
            case R.id.tv_recharge_done:
                if (tv_we_chat.isSelected()) {//微信
                    weChat();
                } else if (tv_ali_pay.isSelected()) {//支付宝
                    aliPay();
                } else {
                    paypal();
                }
                break;
        }
    }

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(OrderPayActivity.this, getString(R.string.zhifu_s), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(OrderPayActivity.this, getString(R.string.zhifu_f), Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case 100:
                    if (AppGlobal.getInstance().getLagStr().equals("en")) {
                        tv_should_pay.setText(money + "CAD$");
                        //   tv_paypal_money.setText("CAD$" + money);
                    } else {
                        tv_should_pay.setText(money + "加元");
                        // tv_paypal_money.setText("(加元" + money + ")");
                    }
                    break;
                default:
                    break;
            }
        }

        ;
    };

    /**
     * 支付宝
     */
    private void alipay(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(OrderPayActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 微信支付
     */
    private void weixinPay(WxEntity.DataBean data) {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        IWXAPI api = WXAPIFactory.createWXAPI(this, data.getAppid());
        // 将该app注册到微信
        api.registerApp(data.getAppid());
        PayReq req = new PayReq();
        req.appId = data.getAppid();
        req.partnerId = data.getPartnerid();
        req.prepayId = data.getPrepayid();
        req.nonceStr = data.getNoncestr();
        req.timeStamp = data.getTimestamp();
        req.packageValue = data.getPackageX();
        req.sign = data.getSign();
        req.extData = "app data"; // optional
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(req);
    }

    private static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    //得到订单需要支付的钱
    public void getMoney() {
        LoadDialog.showProgressDialog(OrderPayActivity.this);
        Map<String, String> map = new HashMap<>();
        map.put("j_uid", j_uid);
        map.put("oid", oid);
        map.put("secret_key", SPUtils.getString(OrderPayActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(this, map, null);
        Request request = new Request.Builder()
                .url(Contants.order_paymoney)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {//{"status":200,"data":44}
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        money = jsonObject.optString("data");
                        mHandler.sendEmptyMessage(100);
                    } catch (org.json.JSONException e) {
                        e.printStackTrace();
                    }
                    //initHvData();
                } else {
                    ToastUtil.longToast(OrderPayActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
                LoadDialog.closeProgressDialog();
            }
        });
    }
}
