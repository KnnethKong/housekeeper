package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.WebViewActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.entity.WxEntity;
import com.haiwai.housekeeper.entity.ZfbEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
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

public class GoPayActivity extends AppCompatActivity {
    private TopViewNormalBar top_gopay_bar;
    private TextView tv_gopay_money, tv_gopay_wx, tv_gopay_zfb, tv_gopay_pp;
    private Button btn_gopay_next;
    private String money = "";
    private String hv = "";
    private boolean isX = false;
    User user;
    private Map<String, String> map;
    private String j_uid = "";
    private String oid = "";
    private int payType = 0;
    private IWXAPI api;
    private static final int SDK_PAY_FLAG = 1;
    //    private TextView tv_wx_hv, tv_zfb_hv, tv_pp_hv;
    private String isZhorEn = "";
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
                        Toast.makeText(GoPayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(GoPayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
    private TextView tv_hlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_pay);
        top_gopay_bar = (TopViewNormalBar) findViewById(R.id.top_gopay_bar);
        top_gopay_bar.setTitle(getString(R.string.pay_for_quot));
        top_gopay_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
        initData();
    }

    private void initData() {
        user = AppGlobal.getInstance().getUser();
        isX = getIntent().getBooleanExtra("isX", false);
        if (isX) {
            money = getIntent().getStringExtra("money");
            tv_gopay_money.setText(getString(R.string.zf_jy_dw) + money);
            initHvData(true);
        } else {
            LoadDialog.showProgressDialog(GoPayActivity.this);
            j_uid = getIntent().getExtras().getString("j_uid");//接单用户id
            oid = getIntent().getExtras().getString("oid");//订单id
            map = new HashMap<>();
            map.put("j_uid", j_uid);
            map.put("oid", oid);
            map.put("secret_key", SPUtils.getString(GoPayActivity.this, "secret", ""));
            map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(GoPayActivity.this, Contants.order_paymoney, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {//{"status":200,"data":44}
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            money = jsonObject.optString("data");
                            tv_gopay_money.setText(money + getString(R.string.zf_jy_dw));
                        } catch (org.json.JSONException e) {
                            e.printStackTrace();
                        }
                        initHvData(false);
                    } else {
                        LoadDialog.closeProgressDialog();
                        ToastUtil.longToast(GoPayActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }));
        }
    }

    String mj = "";

    private void initHvData(boolean isFlag) {
        map = new HashMap<>();
        map.put("secret_key", SPUtils.getString(GoPayActivity.this, "secret", ""));
        if (isFlag) {
            LoadDialog.showProgressDialog(GoPayActivity.this);
        }
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(GoPayActivity.this, Contants.exchange_rate, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(">>>>>>>>>>>>>>>>>>ssss" + response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {//{"status":200,"data":44}
                    try {
                        JSONObject jsonObject = new JSONObject(response);
//                        hv = jsonObject.optString("data");
                        hv = jsonObject.getJSONObject("data").getString("rj");
                        tv_hlv.setText(getResources().getString(R.string.wxpay) + hv);
                        //  mj = jsonObject.getJSONObject("data").getString("mj");
                    } catch (org.json.JSONException e) {
                        e.printStackTrace();
                    }
                    LoadDialog.closeProgressDialog();
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.longToast(GoPayActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void initView() {
        tv_gopay_money = (TextView) findViewById(R.id.tv_gopay_money);
        tv_gopay_wx = (TextView) findViewById(R.id.tv_gopay_wx);
        tv_gopay_zfb = (TextView) findViewById(R.id.tv_gopay_zfb);
        tv_gopay_pp = (TextView) findViewById(R.id.tv_gopay_pp);
        btn_gopay_next = (Button) findViewById(R.id.btn_gopay_next);
        tv_hlv = (TextView) findViewById(R.id.hlv);
//        tv_wx_hv = (TextView) findViewById(R.id.tv_wx_hv);
//        tv_zfb_hv = (TextView) findViewById(R.id.tv_zfb_hv);
//        tv_pp_hv = ((TextView) findViewById(R.id.tv_zfb_pp));
        tv_gopay_wx.setOnClickListener(mOnClickListener);
        tv_gopay_zfb.setOnClickListener(mOnClickListener);
        tv_gopay_pp.setOnClickListener(mOnClickListener);
        btn_gopay_next.setOnClickListener(mOnClickListener);
        tv_gopay_pp.performLongClick();

        if (!isWeixinAvilible("com.eg.android.AlipayGphone")) {
            findViewById(R.id.rl_ali_pay).setVisibility(View.GONE);
            findViewById(R.id.ali_line).setVisibility(View.GONE);
        }
        if (!isWeixinAvilible("com.tencent.mm")) {
            findViewById(R.id.rl_we_chat).setVisibility(View.GONE);
            findViewById(R.id.we_chat_line).setVisibility(View.GONE);
        }


    }


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


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_gopay_bar.getBackView()) {
                finish();
            } else if (view.getId() == R.id.tv_gopay_wx) {
                payType = 1;
                tv_gopay_wx.setSelected(true);
                tv_gopay_zfb.setSelected(false);
                tv_gopay_pp.setSelected(false);
//                tv_pp_hv.setVisibility(View.GONE);
//                tv_zfb_hv.setVisibility(View.GONE);
//                tv_wx_hv.setVisibility(View.VISIBLE);
                Double jv = Double.valueOf(hv);
                String m = money;
                if (money.contains("$")) {
                    m = money.replace("$", "");
                }
                Double newDb = mul(Double.valueOf(m), Double.valueOf(hv));
                BigDecimal decimal = BigDecimal.valueOf(newDb);
                double value = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                String result = String.format("%.2f", value);
                String str = getString(R.string.rmb) + result + "(" + getString(R.string.rate) + jv + ")";
                //   tv_wx_hv.setText(str);
            } else if (view.getId() == R.id.tv_gopay_zfb) {
                payType = 2;
                tv_gopay_wx.setSelected(false);
                tv_gopay_zfb.setSelected(true);
                tv_gopay_pp.setSelected(false);
//                tv_wx_hv.setVisibility(View.GONE);
//                tv_zfb_hv.setVisibility(View.VISIBLE);
//                tv_pp_hv.setVisibility(View.GONE);
                Double jv = Double.valueOf(hv);
                String m = money;
                if (money.contains("$")) {
                    m = money.replace("$", "");
                }
                Double newDb = mul(Double.valueOf(m), Double.valueOf(hv));
                BigDecimal decimal = BigDecimal.valueOf(newDb);
                double value = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                String result = String.format("%.2f", value);
                String str = getString(R.string.rmb) + result + "(" + getString(R.string.rate) + jv + ")";
                // tv_zfb_hv.setText(str);
            } else if (view.getId() == R.id.tv_gopay_pp) {
                payType = 3;
                // tv_pp_hv.setVisibility(View.VISIBLE);
                tv_gopay_wx.setSelected(false);
                tv_gopay_zfb.setSelected(false);
                tv_gopay_pp.setSelected(true);
                //tv_wx_hv.setVisibility(View.GONE);
                // tv_zfb_hv.setVisibility(View.GONE);
                String m = money;
                if (money.contains("$")) {
                    m = money.replace("$", "");
                }
                Double newDb = mul(Double.valueOf(m), Double.valueOf(mj));
                BigDecimal decimal = BigDecimal.valueOf(newDb);
                double value = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                String result = String.format("%.2f", value);
                String str = getString(R.string.recharge_dollar) + result + "(" + getString(R.string.rate) + Double.valueOf(mj) + ")";
                // tv_pp_hv.setText(str);
            } else if (view.getId() == R.id.btn_gopay_next) {//充值接口
                LoadDialog.showProgressDialog(GoPayActivity.this);
                if (isX) {
                    map = new HashMap<>();
                    map.put("uid", user.getUid());
                    map.put("secret_key", SPUtils.getString(GoPayActivity.this, "secret", ""));
                    map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
                    if (payType == 1) {
                        String m = money;
                        if (money.contains("$")) {
                            m = money.replace("$", "");
                        }
                        Double newDb = mul(Double.valueOf(m), Double.valueOf(hv));
                        map.put("money", String.valueOf(newDb));
                        PlatRequest request = new PlatRequest(GoPayActivity.this, Contants.wxpay, map, null, new Response.Listener<String>() {
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
                                    ToastUtil.longToast(GoPayActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                                }
                            }
                        });
                        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
                        MyApp.getTingtingApp().getRequestQueue().add(request);
                    } else if (payType == 2) {
                        String m = money;
                        if (money.contains("$")) {
                            m = money.replace("$", "");
                        }
                        Double newDb = mul(Double.valueOf(m), Double.valueOf(hv));
                        map.put("money", String.valueOf(newDb));
                        PlatRequest request = new PlatRequest(GoPayActivity.this, Contants.alipay, map, null, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                int code = JsonUtils.getJsonInt(response, "status");
                                if (code == 200) {
                                    LoadDialog.closeProgressDialog();
                                    ZfbEntity zfbEntity = new Gson().fromJson(response, ZfbEntity.class);
                                    alipay(zfbEntity.getData());
                                } else {
                                    LoadDialog.closeProgressDialog();
                                    ToastUtil.longToast(GoPayActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                                }
                            }
                        });
                        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
                        MyApp.getTingtingApp().getRequestQueue().add(request);
                    } else if (payType == 3) {
                        map.put("money", money + "");
                        PlatRequest request = new PlatRequest(GoPayActivity.this, Contants.paypalzhi, map, null, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                int code = JsonUtils.getJsonInt(response, "status");
                                LoadDialog.closeProgressDialog();
                                if (code == 200) {
                                    String uri = JsonUtils.getJsonStr(response, "data");
                                    Intent intent = new Intent(GoPayActivity.this, WebViewActivity.class);
                                    intent.putExtra("url", uri);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    LoadDialog.closeProgressDialog();
                                    ToastUtil.longToast(GoPayActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                                }
                            }
                        });
                        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
                        MyApp.getTingtingApp().getRequestQueue().add(request);
                    }
                } else {
                    map = new HashMap<>();
                    map.put("uid", user.getUid());
                    map.put("oid", oid);
                    map.put("secret_key", SPUtils.getString(GoPayActivity.this, "secret", ""));
                    map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
                    if (payType == 1) {
                        String m = money;
                        if (money.contains("$")) {
                            m = money.replace("$", "");
                        }
                        Double newDb = mul(Double.valueOf(m), Double.valueOf(hv));
                        map.put("money", String.valueOf(newDb));
                        PlatRequest request = new PlatRequest(GoPayActivity.this, Contants.wxpay_zhifu, map, null, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                int code = JsonUtils.getJsonInt(response, "status");
                                System.out.println(">>>>>>微信>>>>>>>.." + response);
                                if (code == 200) {
                                    LoadDialog.closeProgressDialog();
                                    WxEntity wxEntity = new Gson().fromJson(response, WxEntity.class);
                                    weixinPay(wxEntity.getData());
                                } else {
                                    LoadDialog.closeProgressDialog();
                                    ToastUtil.longToast(GoPayActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                                }
                            }
                        });
                        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
                        MyApp.getTingtingApp().getRequestQueue().add(request);
                    } else if (payType == 2) {
                        String m = money;
                        if (money.contains("$")) {
                            m = money.replace("$", "");
                        }
                        Double newDb = mul(Double.valueOf(m), Double.valueOf(hv));
                        map.put("money", String.valueOf(newDb));
                        PlatRequest request = new PlatRequest(GoPayActivity.this, Contants.alipay_zhifu, map, null, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                int code = JsonUtils.getJsonInt(response, "status");
                                if (code == 200) {
                                    LoadDialog.closeProgressDialog();
                                    ZfbEntity zfbEntity = new Gson().fromJson(response, ZfbEntity.class);
                                    alipay(zfbEntity.getData());
                                } else {
                                    LoadDialog.closeProgressDialog();
                                    ToastUtil.longToast(GoPayActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                                }
                            }
                        });
                        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
                        MyApp.getTingtingApp().getRequestQueue().add(request);
                    } else if (payType == 3) {
                        Map<String, String> payMap = new HashMap<>();
                        payMap.put("money", money + "");
                        payMap.put("uid", AppGlobal.getInstance().getUser().getUid());
                        payMap.put("oid", oid);
                        payMap.put("secret_key", SPUtils.getString(GoPayActivity.this, "secret", ""));
                        payMap.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
                        PlatRequest request = new PlatRequest(GoPayActivity.this, Contants.paypalzhifu, payMap, null, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                int code = JsonUtils.getJsonInt(response, "status");
                                System.out.println(">>>>>>>>>>>>>.." + response);
                                if (code == 200) {
                                    LoadDialog.closeProgressDialog();
//                                    ToastUtil.longToast(GoPayActivity.this, "支付成功");
                                    String uri = JsonUtils.getJsonStr(response, "data");
                                    Intent intent = new Intent(GoPayActivity.this, WebViewActivity.class);
                                    intent.putExtra("url", uri);
                                    startActivity(intent);
                                    finish();
                                } else if (code == 1412) {
                                    LoadDialog.closeProgressDialog();
                                    ToastUtil.shortToast(GoPayActivity.this, getString(R.string.is_bind_paypal));
                                } else if (code == 1411) {
                                    LoadDialog.closeProgressDialog();

                                    ToastUtil.shortToast(GoPayActivity.this, getString(R.string.no_bind_paypal));
                                } else {
                                    LoadDialog.closeProgressDialog();
                                    ToastUtil.longToast(GoPayActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                                }
                            }
                        });
                        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
                        MyApp.getTingtingApp().getRequestQueue().add(request);
                    }
                }
            }
        }
    };


    /**
     * 支付宝
     */
    private void alipay(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(GoPayActivity.this);
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

    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
}
