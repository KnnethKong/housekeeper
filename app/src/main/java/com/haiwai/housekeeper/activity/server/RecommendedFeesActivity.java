package com.haiwai.housekeeper.activity.server;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.WebViewActivity;
import com.haiwai.housekeeper.activity.user.GoPayActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.entity.WxEntity;
import com.haiwai.housekeeper.entity.ZfbEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.Event;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.PayResult;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;

public class RecommendedFeesActivity extends AppCompatActivity {
    private TopViewNormalBar top_recommend_bar;
    private TextView tv_zj, tv_gsf, tv_no_confirm;
    private EditText et_input_my_advatage, et_zj, et_gsf;
    private TextView tv_jdq, tv_pp, tv_wx, tv_zfb, tv_pp_pri, tv_wx_pri, tv_zfb_pri;
    private Button btn_recommond_next;
    private List<TextView> zfList = new ArrayList<>();
    private String hv = "";
    User user;
    private String oid = "";
    private String type = "";
    private String hourly = "";
    private String general = "";
    private String message = "";
    private String service_type="";
    private Map<String, String> cmap = new HashMap<>();
    private Map<String, String> map = null;
    private int pdCount = 0;
    private String payMoney = "0";
    private static final int SDK_PAY_FLAG = 1;
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
                        Toast.makeText(RecommendedFeesActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(RecommendedFeesActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_fees);
        top_recommend_bar = (TopViewNormalBar) findViewById(R.id.top_recommend_bar);
        top_recommend_bar.setTitle(getString(R.string.tujsfd));
        if (!isWeixinAvilible("com.eg.android.AlipayGphone")) {
            findViewById(R.id.ll_zhifu_bao).setVisibility(View.GONE);
        }
        if (!isWeixinAvilible("com.tencent.mm")) {
            findViewById(R.id.ll_we_chat).setVisibility(View.GONE);
        }
        top_recommend_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
        initData();
    }


    private void initData() {
        oid = getIntent().getStringExtra("oid");
        type = getIntent().getStringExtra("type");
        user = AppGlobal.getInstance().getUser();
        cmap.put("oid", oid);
        cmap.put("type", type);
        cmap.put("uid", user.getUid());
        cmap.put("secret_key", SPUtils.getString(RecommendedFeesActivity.this, "secret", ""));
        cmap.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        map = new HashMap<>();
        map.put("type", type);
        map.put("uid", user.getUid());
        map.put("secret_key", SPUtils.getString(RecommendedFeesActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(RecommendedFeesActivity.this, Contants.coupon, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                System.out.println("....代金券" + response);
                if (code == 200) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject dataObject = jsonObject.optJSONObject("data");
                        if (dataObject != null) {
                            pdCount = dataObject.optInt("pd");
                            payMoney = dataObject.optString("money");

                            initHvData(true,pdCount,payMoney);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ToastUtil.longToast(RecommendedFeesActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private String mj;
    private void initHvData(boolean isFlag,final int count,final String money) {
        map = new HashMap<>();
        map.put("secret_key", SPUtils.getString(RecommendedFeesActivity.this, "secret", ""));
        if (isFlag) {
            LoadDialog.showProgressDialog(RecommendedFeesActivity.this);
        }
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(RecommendedFeesActivity.this, Contants.exchange_rate, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(">>>>>>>>>>>>>>>>>>ssss" + response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {//{"status":200,"data":44}
                    try {
                        JSONObject jsonObject = new JSONObject(response);
//                        hv = jsonObject.optString("data");
                        hv = jsonObject.getJSONObject("data").optString("rj");
                        mj = jsonObject.getJSONObject("data").optString("mj");
                        if(AppGlobal.getInstance().getLagStr().equals("zh")){
                            delView(count, money);

                        }else{
                            delView(count, "$"+Float.valueOf(money));

                        }
                    } catch (org.json.JSONException e) {
                        e.printStackTrace();
                    }
                    LoadDialog.closeProgressDialog();
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.longToast(RecommendedFeesActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void delView(int pdCount, String payMoney) {//是否有接单券
        if (pdCount == 0) {
            tv_jdq.setBackgroundResource(R.color.color_gray);
            tv_jdq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.longToast(RecommendedFeesActivity.this, getString(R.string.tiss));
                }
            });
        } else {
            tv_jdq.setBackgroundResource(R.color.white);
            tv_jdq.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return false;
                }
            });
        }
        Double jv = Double.valueOf(hv);
       String m=payMoney;
        if(payMoney.contains("$")){
           m=payMoney.substring(1,m.length());
        }
        Double newDb = mul(Double.valueOf(m), Double.valueOf(hv));
        BigDecimal decimal = BigDecimal.valueOf(newDb);
        double value = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        String result = String.format("%.2f", value);
        String str = getString(R.string.rmb) + result + "(" + getString(R.string.rate) + jv + ")";

        tv_wx_pri.setText(str);
        tv_zfb_pri.setText(str);

        Double jv1 = Double.valueOf(mj);
        Double newDb1 = mul(Double.valueOf(m), Double.valueOf(mj));
        BigDecimal decimal1 = BigDecimal.valueOf(newDb1);
        double value1 = decimal1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        String result1 = String.format("%.2f", value1);
        String str1 = getString(R.string.recharge_dollarQ) + result1 ;
        tv_pp_pri.setText(str1);
    }

    private void initView() {
        tv_zj = (TextView) findViewById(R.id.tv_zj);
        tv_zj.setOnClickListener(mOnClickListener);
        tv_gsf = (TextView) findViewById(R.id.tv_gsf);
        tv_gsf.setOnClickListener(mOnClickListener);
        tv_no_confirm = (TextView) findViewById(R.id.tv_no_confirm);
        tv_no_confirm.setOnClickListener(mOnClickListener);
        et_input_my_advatage = (EditText) findViewById(R.id.et_input_my_advatage);
        btn_recommond_next = (Button) findViewById(R.id.btn_recommond_next);
        btn_recommond_next.setBackground(getResources().getDrawable(R.mipmap.btn_common_bj));
        btn_recommond_next.setOnClickListener(mOnClickListener);
        tv_pp_pri = (TextView) findViewById(R.id.tv_pp_pri);
        tv_wx_pri = (TextView) findViewById(R.id.tv_wx_pri);
        tv_zfb_pri = (TextView) findViewById(R.id.tv_zfb_pri);
        et_zj = (EditText) findViewById(R.id.et_zj);
        et_gsf = (EditText) findViewById(R.id.et_gsf);
        tv_jdq = (TextView) findViewById(R.id.tv_jdq);
        tv_pp = (TextView) findViewById(R.id.tv_pp);
        tv_wx = (TextView) findViewById(R.id.tv_wx);
        tv_zfb = (TextView) findViewById(R.id.tv_zfb);
        zfList.add(tv_jdq);
        zfList.add(tv_pp);
        zfList.add(tv_wx);
        zfList.add(tv_zfb);
        for (int i = 0; i < zfList.size(); i++) {
            final int finalI = i;
            zfList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setViewSelected(finalI);
                    if (finalI == 0) {
                        tv_pp_pri.setVisibility(View.GONE);
                        tv_wx_pri.setVisibility(View.GONE);
                        tv_zfb_pri.setVisibility(View.GONE);
                    } else if (finalI == 1) {
                        tv_pp_pri.setVisibility(View.VISIBLE);
                        tv_wx_pri.setVisibility(View.GONE);
                        tv_zfb_pri.setVisibility(View.GONE);
                    } else if (finalI == 2) {
                        tv_pp_pri.setVisibility(View.GONE);
                        tv_wx_pri.setVisibility(View.VISIBLE);
                        tv_zfb_pri.setVisibility(View.GONE);
                    } else if (finalI == 3) {
                        tv_pp_pri.setVisibility(View.GONE);
                        tv_wx_pri.setVisibility(View.GONE);
                        tv_zfb_pri.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    private void setViewSelected(int finalI) {
        setAllFalseSelected();
        zfList.get(finalI).setSelected(true);
    }

    private void setAllFalseSelected() {
        for (int i = 0; i < zfList.size(); i++) {
            zfList.get(i).setSelected(false);
        }
    }

    public String getSelectedStr() {
        String str = null;
        for (int i = 0; i < zfList.size(); i++) {
            if (zfList.get(i).isSelected()) {
                str = zfList.get(i).getText().toString().trim();
            }
        }
        return str;
    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_recommend_bar.getBackView()) {
                finish();
            } else if (view.getId() == R.id.tv_zj) {
                tv_zj.setSelected(true);
                tv_gsf.setSelected(false);
                cmap.put("service_type", "2");
                service_type = "2";
                tv_no_confirm.setSelected(false);
                et_zj.setVisibility(View.VISIBLE);
                et_gsf.setText("");
                et_gsf.setVisibility(View.GONE);
            } else if (view.getId() == R.id.tv_gsf) {
                tv_zj.setSelected(false);
                tv_gsf.setSelected(true);
                tv_no_confirm.setSelected(false);
                cmap.put("service_type", "1");
                service_type = "1";
                et_zj.setText("");
                et_zj.setVisibility(View.GONE);
                et_gsf.setVisibility(View.VISIBLE);
            } else if (view.getId() == R.id.tv_no_confirm) {
                tv_zj.setSelected(false);
                tv_gsf.setSelected(false);
                tv_no_confirm.setSelected(true);
                cmap.put("service_type", "3");
                service_type = "3";
                et_zj.setText("");
                et_gsf.setText("");
                et_zj.setVisibility(View.GONE);
                et_gsf.setVisibility(View.GONE);
            } else if (view.getId() == R.id.btn_recommond_next) {
                if(service_type.equals("")){
                    ToastUtil.shortToast(RecommendedFeesActivity.this,"请选择报价方式");
                    return;
                }else if(!service_type.equals("")){
                    if(service_type.equals("2")&&et_zj.getText().toString().trim().equals("")){
                        ToastUtil.shortToast(RecommendedFeesActivity.this,"请输入总价");
                        return;
                    }
                    if(service_type.equals("1")&&et_gsf.getText().toString().trim().equals("")){
                        ToastUtil.shortToast(RecommendedFeesActivity.this,"请输入工时费");
                        return;
                    }
                }
                if(et_input_my_advatage.getText().toString().trim().equals("")){
                    ToastUtil.shortToast(RecommendedFeesActivity.this,"请输入我的优势");
                    return;
                }
                if(getSelectedStr()==null){
                    ToastUtil.shortToast(RecommendedFeesActivity.this,"请选择支付方式");
                    return;
                }
                Log.e("reuslt------>",et_input_my_advatage.getText().toString().trim()+"----"+getSelectedStr());
                LoadDialog.showProgressDialog(RecommendedFeesActivity.this);
                if ("接单券".equals(getSelectedStr()) || "Ticket".equals(getSelectedStr())) {
                    general = et_zj.getText().toString().trim();
                    hourly = et_gsf.getText().toString().trim();
                    message = et_input_my_advatage.getText().toString().trim();
                    cmap.put("general", general);
                    cmap.put("hourly", hourly);
                    cmap.put("message", message);
                    MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(RecommendedFeesActivity.this, Contants.offer_coupon, cmap, null, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            int code = JsonUtils.getJsonInt(response, "status");
                            System.out.println("代金券支付>>>" + response);
                            if (code == 200) {
                                LoadDialog.closeProgressDialog();
                                ToastUtil.longToast(RecommendedFeesActivity.this, "代金券支付成功");
                                EventBus.getDefault().post(
                                        new Event("pay", ""));
                                setResult(100);
                                finish();
                            } else {
                                LoadDialog.closeProgressDialog();
                                ToastUtil.longToast(RecommendedFeesActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                                EventBus.getDefault().post(
                                        new Event("pay", ""));
                                setResult(100);
                                finish();
                            }
                        }
                    }));
                } else if ("paypal".equals(getSelectedStr())) {
                    general = et_zj.getText().toString().trim();
                    hourly = et_gsf.getText().toString().trim();
                    message = et_input_my_advatage.getText().toString().trim();
                    Map<String,String> ppMap = new HashMap<>();
                    ppMap.put("type",type);


                    ppMap.put("message", message);
                    ppMap.put("oid",oid);
                    ppMap.put("service_type",service_type);
                    if(service_type.equals("1")){
                        ppMap.put("hourly", hourly);
                    }else if(service_type.equals("2")){
                        ppMap.put("general", general);
                    }
                    ppMap.put("uid",user.getUid());
                    ppMap.put("money", payMoney+"");
                    ppMap.put("secret_key", SPUtils.getString(RecommendedFeesActivity.this, "secret", ""));
                    ppMap.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
                    PlatRequest request = new PlatRequest(RecommendedFeesActivity.this, Contants.paypaljiedan, ppMap, null, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            int code = JsonUtils.getJsonInt(response, "status");
                            System.out.println("paypal支付>>>" + response);
                            if (code == 200) {
                                LoadDialog.closeProgressDialog();
                                String uri = JsonUtils.getJsonStr(response, "data");
                                Intent intent = new Intent(RecommendedFeesActivity.this, WebViewActivity.class);
                                intent.putExtra("url", uri);
                                intent.putExtra("isJie",true);
                                startActivity(intent);
                                finish();
                            } else {
                                LoadDialog.closeProgressDialog();
                                ToastUtil.longToast(RecommendedFeesActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                            }
                        }
                    });
                    request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
                    MyApp.getTingtingApp().getRequestQueue().add(request);
                } else if ("微信".equals(getSelectedStr()) || "WeChat".equals(getSelectedStr())) {
                    general = et_zj.getText().toString().trim();
                    hourly = et_gsf.getText().toString().trim();
                    message = et_input_my_advatage.getText().toString().trim();
                    cmap.put("general", general);
                    cmap.put("hourly", hourly);
                    cmap.put("message", message);
                    Double newDb = mul(Double.valueOf(payMoney),Double.valueOf(hv));
                    cmap.put("money", String.valueOf(newDb));
                    PlatRequest request = new PlatRequest(RecommendedFeesActivity.this, Contants.wxpay_jiedan, cmap, null, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            int code = JsonUtils.getJsonInt(response, "status");
                            System.out.println("微信>>>" + response);
                            if (code == 200) {
                                LoadDialog.closeProgressDialog();
                                WxEntity wxEntity = new Gson().fromJson(response, WxEntity.class);
                                weixinPay(wxEntity.getData());
                            } else {
                                LoadDialog.closeProgressDialog();
                                ToastUtil.longToast(RecommendedFeesActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                            }
                        }
                    });
                    request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
                    MyApp.getTingtingApp().getRequestQueue().add(request);
                } else if ("支付宝".equals(getSelectedStr()) || "Alipay".equals(getSelectedStr())) {
                    general = et_zj.getText().toString().trim();
                    hourly = et_gsf.getText().toString().trim();
                    message = et_input_my_advatage.getText().toString().trim();
                    cmap.put("general", general);
                    cmap.put("hourly", hourly);
                    cmap.put("message", message);
                    Double newDb = mul(Double.valueOf(payMoney),Double.valueOf(hv));
                    cmap.put("money", String.valueOf(newDb));
                    PlatRequest request = new PlatRequest(RecommendedFeesActivity.this, Contants.alipay_jiedan, cmap, null, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            int code = JsonUtils.getJsonInt(response, "status");
                            System.out.println("支付宝>>>" + response);
                            if (code == 200) {
                                LoadDialog.closeProgressDialog();
                                ZfbEntity zfbEntity = new Gson().fromJson(response, ZfbEntity.class);
                                alipay(zfbEntity.getData());
                            } else {
                                LoadDialog.closeProgressDialog();
                                ToastUtil.longToast(RecommendedFeesActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                            }
                        }
                    });
                    request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
                    MyApp.getTingtingApp().getRequestQueue().add(request);
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
                PayTask alipay = new PayTask(RecommendedFeesActivity.this);
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

}
