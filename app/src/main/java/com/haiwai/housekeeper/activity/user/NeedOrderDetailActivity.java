package com.haiwai.housekeeper.activity.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.NeedResponseDetailEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.CheckJsonUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.utils.WDUtils2;
import com.haiwai.housekeeper.utils.WenPaseUtils;
import com.haiwai.housekeeper.view.CodeView;
import com.haiwai.housekeeper.view.ConPopBig2View;
import com.haiwai.housekeeper.view.TvOrderView;
import com.haiwai.housekeeper.view.scrollview.MyScrollView;
import com.haiwai.housekeeper.widget.CustomDialog;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.haiwai.housekeeper.widget.RegisterDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/5.
 * 需求fragment订单详情——待确认
 */
public class NeedOrderDetailActivity extends BaseActivity implements MyScrollView.onPullToRefreshListener {
    // TODO: 2016/9/6  beiyong 1最底部换切图  2钱包余额黑字红字
    private LinearLayout llcollpase;
    private TextView tvexpand;
    private String id = "";
    private String proid = "";
    private String j_num = "";
    private boolean flag = true;
    NeedResponseDetailEntity entity;
    private TextView tv_j_num;
    private CodeView nodeview;
    private String isZhorEn = "";
    private MyScrollView ll_sw_layout;
    boolean isRefresh = false;

    private RelativeLayout is_showing;
    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.need_order_detail_daiqueren, null);
    }

    private TextView tv_type,tv_order_num,tv_status;
    @Override
    protected void initView(Bundle savedInstanceState) {
        is_showing= (RelativeLayout) findViewById(R.id.is_showing);
        is_showing.setVisibility(View.GONE);
        setTitle(getString(R.string.need_order_detail), Color.parseColor("#FF3C3C3C"));
        ll_sw_layout = (MyScrollView) findViewById(R.id.ll_sw_layout);
        ll_sw_layout.setOnPullToRefreshListener(this);
        llcollpase = (LinearLayout) findViewById(R.id.need_response_order_detail_ll_collpase);
        llcollpase.setVisibility(View.GONE);
        tvexpand = (TextView) findViewById(R.id.order_detail_daiqueren_tv_expand);
        tvexpand.setText(getString(R.string.need_order_view_all));
        tvexpand.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        tvexpand.setOnClickListener(this);
        tvexpand.performClick();
        ((TextView) findViewById(R.id.tv_buss_detail_word)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        tv_j_num = (TextView) findViewById(R.id.tv_j_num);
        tv_j_num.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        findViewById(R.id.need_response_order_detail_tv_choose_serve).setOnClickListener(this);
        findViewById(R.id.user_order_detail_ll_bottom).setOnClickListener(this);
        findViewById(R.id.user_order_detail_ll_bottom_select).setOnClickListener(this);
        nodeview = (CodeView) findViewById(R.id.nodeview);
        tv_type = ((TextView) findViewById(R.id.need_response_order_detail_tv_type));
        tv_order_num = ((TextView) findViewById(R.id.need_response_order_detail_tv_ordernum));
        tv_status = ((TextView) findViewById(R.id.need_response_order_detail_tv_status));
        tv_type.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        tv_order_num.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        tv_status.setTypeface(MyApp.getTf(), Typeface.NORMAL);

        String language = getResources().getConfiguration().locale.getLanguage();
        if (!"zh".equals(language)) {
            ((TextView) findViewById(R.id.tv_word_alread)).setTextSize(12);
            ((TextView) findViewById(R.id.tv_word_alread_take_order)).setTextSize(12);
            ((TextView) findViewById(R.id.need_response_order_detail_tv_choose_serve)).setTextSize(12);
        }
        nodeview.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ConPopBig2View conpop = new ConPopBig2View(NeedOrderDetailActivity.this,"");
                conpop.showPopUpWindow(view);
                conpop.getIv_diss().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        conpop.dismiss();
                    }
                });
            }
        });

    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        id = getIntent().getExtras().get("id").toString();
        proid = getIntent().getExtras().get("proid").toString();
        j_num = getIntent().getExtras().get("j_num").toString();
        tv_j_num.setText(" " + j_num + " ");
        if(Integer.valueOf(j_num)==0){
            findViewById(R.id.user_order_detail_ll_bottom_select).setVisibility(View.GONE);
        }
        if (isNetworkAvailable()) {
            requestDetail();
        }
    }

//    private NeedResponseDetailEntity entity;


    private String user_quci;
    private String user_quci_num;
    public void requestDetail() {
        if (!isRefresh) {
            LoadDialog.showProgressDialog(NeedOrderDetailActivity.this);
        }
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("proid", proid);
        map.put("secret_key", SPUtils.getString(NeedOrderDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(this, Contants.user_order_detail, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
//                    user_quci = JsonUtils.getJsonInt(response,"user_quci");
                    int code = JsonUtils.getJsonInt(response, "status");
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>" + response);
                    is_showing.setVisibility(View.VISIBLE);
                    if (code == 200) {
                        if (isRefresh) {
                            ll_sw_layout.refreshCompleted();
                        } else {
                            mHandler.sendEmptyMessageDelayed(100,1000);
                           // LoadDialog.closeProgressDialog();
                        }
                        NeedResponseDetailEntity entity = JsonUtils.parseNeedResponseDetail(response);
                        user_quci = new JSONObject(response).getJSONObject("data").getString("user_quci");
                        user_quci_num = new JSONObject(response).getJSONObject("data").getString("user_quci_num");
                        Log.i("user_cancel",user_quci);
                        WDUtils2.getWDMap(entity.getData().getDate());
                        bindData(entity);
                    } else {
                        if (isRefresh) {
                            ll_sw_layout.refreshCompleted();
                        } else {
                            mHandler.sendEmptyMessageDelayed(100,1000);
                          //  LoadDialog.closeProgressDialog();
                        }
                        ToastUtil.shortToast(NeedOrderDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 100:
                    LoadDialog.closeProgressDialog();
                    break;


            }

        }
    };
    private void bindData(final NeedResponseDetailEntity entity) {
        this.entity = entity;
       // LoadDialog.closeProgressDialog();
        if (entity != null) {
            tv_j_num.setText(entity.getData().getDate().getJ_num()+"");
            LogUtil.e("entity", entity + "");
            tv_type.setText(AssetsUtils.getSkillName(entity.getData().getDate().getType(), isZhorEn));
            tv_order_num.setText(getString(R.string.main_need_order_num) + entity.getData().getDate().getOrder_id());
            tv_status.setText(JsonUtils.parseOrderStatus(Integer.valueOf(entity.getData().getDate().getStatics()),NeedOrderDetailActivity.this));




            if ("12".equals(entity.getData().getDate().getStatics())) {
                findViewById(R.id.user_order_detail_ll_bottom).setVisibility(View.GONE);
            }
            if(Integer.parseInt(entity.getData().getDate().getType())>18){
                nodeview.setVisibility(View.GONE);
            };
            nodeview.setNode(entity.getData().getDate().getStatics());
            nodeview.getTv_tch().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ("0".equals(entity.getData().getDate().getStatics())) {
//                        ConPopView cpv = new ConPopView(NeedOrderDetailActivity.this, "订单已提交，等待pro报价");
//                        cpv.showPopUpWindow(view, 100, 0);
                        final ConPopBig2View cpsv = new ConPopBig2View(NeedOrderDetailActivity.this, "");
                        cpsv.showPopUpWindow(view);
                        cpsv.getIv_diss().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                cpsv.dismiss();
                            }
                        });
                    } else if ("1".equals(entity.getData().getDate().getStatics())) {
//                        ConPopView cpv = new ConPopView(NeedOrderDetailActivity.this, "请查看报价并选择pro为您服务");
//                        cpv.showPopUpWindow(view, 100, 0);
                        final ConPopBig2View cpsv = new ConPopBig2View(NeedOrderDetailActivity.this, "");
                        cpsv.showPopUpWindow(view);
                        cpsv.getIv_diss().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                cpsv.dismiss();
                            }
                        });
                    }
                }
            });
            llcollpase.removeAllViews();
            String skillType = entity.getData().getDate().getType();
            List<TvOrderView> viewList = new ArrayList<>();
            for (int i = 1; i <= WDUtils2.count; i++) {
                TvOrderView tvOrderView = new TvOrderView(this);
                String strKey = "wen" + i;
                String valueStr = "da" + i;
//                Log.i("ls2DaInformaton--",WDUtils2.getWenStr(strKey));
                if (!TextUtils.isEmpty(WDUtils2.getWenStr(strKey))) {
//                    Log.i("statusInformation",WDUtils2.getWenStr(strKey)+"----"+WenPaseUtils.getWenStr(NeedOrderDetailActivity.this, strKey,
//                            Integer.valueOf(entity.getData().getDate().getType()), isZhorEn)+"---"+WDUtils2.getDaStr(valueStr));
                    if (0 == CheckJsonUtils.getStatuss(WDUtils2.getDaStr(valueStr))) {//整数处理
//                    tvOrderView.setViewText(WDUtils2.getWenStr(strKey),
//                            WenPaseUtils.getDaStr(OrderDetailActivity.this, valueStr,
//                                    Integer.valueOf(WDUtils2.getDaStr(valueStr)),
//                                    Integer.valueOf(orderDetailEntity.getData().getDate().getType()),"zh"));
                        tvOrderView.setViewText(WenPaseUtils.getWenStr(NeedOrderDetailActivity.this, strKey,
                                Integer.valueOf(entity.getData().getDate().getType()), isZhorEn),
                                WenPaseUtils.getDaStr(NeedOrderDetailActivity.this, valueStr,
                                        Integer.valueOf(WDUtils2.getDaStr(valueStr)),
                                        Integer.valueOf(entity.getData().getDate().getType()), isZhorEn));
                    } else if (1 == CheckJsonUtils.getStatuss(WDUtils2.getDaStr(valueStr))) {//Json处理
//                    tvOrderView.setViewText(WDUtils2.getWenStr(strKey),
//                            "json");
                        int a = CheckJsonUtils.getJsonType(WDUtils2.getDaStr(valueStr));
                        if (0 == a) {//纯数字json
                            List<String> strList = CheckJsonUtils.getStrList(NeedOrderDetailActivity.this, Integer.valueOf(entity.getData().getDate().getType()), i, WDUtils2.getDaStr(valueStr), isZhorEn);

                            StringBuilder str = new StringBuilder();
                            for (int m = 0; m < strList.size(); m++) {
                                if (strList.size() > 1) {
                                    str = str.append(strList.get(m)).append("\n");
                                } else {
                                    str = str.append(strList.get(m));
                                }
                            }
                            if (str.toString().endsWith("\n")) {
                                str = str.deleteCharAt(str.lastIndexOf("\n"));
                            }
//                        tvOrderView.setViewText(WDUtils2.getWenStr(strKey), str.toString());
                            tvOrderView.setViewText(WenPaseUtils.getWenStr(NeedOrderDetailActivity.this, strKey,
                                    Integer.valueOf(entity.getData().getDate().getType()), isZhorEn), str.toString());


                        } else if (1 == a) {//指定日期类型json

                            List<String> timeList = CheckJsonUtils.getTimeStr(NeedOrderDetailActivity.this, Integer.valueOf(entity.getData().getDate().getType()), i, WDUtils2.getDaStr(valueStr), isZhorEn);
                            StringBuilder str = new StringBuilder();
                            Log.i("time_information",a+"--"+this.entity.getData().getDate().getType()+"----"+WDUtils2.getDaStr(valueStr)+"---"+timeList.size()+"---"+i);

                            if (timeList != null && timeList.size() > 1) {
//                                str = str.append(TimeUtils.getStr2Time(timeList.get(2))).append("\n");
                                str = str.append(timeList.get(1)).append("\n");
                                if (timeList.size() > 2) {
                                    str = str.append(timeList.get(2)).append("\n");
                                }
                                if (str.toString().endsWith("\n")) {
                                    str = str.deleteCharAt(str.lastIndexOf("\n"));
                                }
                                Log.i("str__imofrmation",str.toString());
                            } else {
                                for (int m = 0; m < timeList.size(); m++) {
                                    str = str.append(timeList.get(m)).append("\n");
                                    Log.i("str__imofrmation__size",str.toString());
                                }
                                if (str.toString().endsWith("\n")) {
                                    str = str.deleteCharAt(str.lastIndexOf("\n"));
                                }

                                try {
                                    String mData = new JSONObject(WDUtils2.getDaStr(valueStr)).optString("2");
                                    String mTime = new JSONObject(WDUtils2.getDaStr(valueStr)).optString("3");
                                    String longTime = new JSONObject(WDUtils2.getDaStr(valueStr)).optString("4");
                                    str.append("\n"+getString(R.string.o2o_detail_fwbj8_choose_date)+":"+TimeUtils.getDate(mData.substring(3,mData.length()))+"\n");
                                    str.append(getString(R.string.o2o_detail_fwbj8_choose_time)+":"+TimeUtils.getTime(mTime.substring(3,mTime.length()))+"\n");
                                    str.append(getString(R.string.o2o_detail_fwbj8_for_long)+":"+longTime);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
//                        tvOrderView.setViewText(WDUtils2.getWenStr(strKey), str.toString());
                            tvOrderView.setViewText(WenPaseUtils.getWenStr(NeedOrderDetailActivity.this, strKey,
                                    Integer.valueOf(entity.getData().getDate().getType()), isZhorEn), str.toString());
                        } else if (2 == a) {//图片类型json
                            final List<String> pathList = CheckJsonUtils.getPicStr(NeedOrderDetailActivity.this, Integer.valueOf(entity.getData().getDate().getType()), i, WDUtils2.getDaStr(valueStr), isZhorEn);
                            if (pathList != null && pathList.size() > 0) {
                                tvOrderView.setTvWenText(WDUtils2.getWenStr(strKey));
                                for (int m = 0; m < pathList.size(); m++) {
                                    if (pathList.get(m).contains(".png") || pathList.get(m).contains(".jpg") || pathList.get(m).contains(".jpeg")) {
                                        tvOrderView.setLayoutVisible(true);
                                        ImageView img = new ImageView(this);
                                        final int imgPosition = m;
                                        LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(120, 120);
                                        img.setPadding(32, 10, 0, 10);
                                        img.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent intent = new Intent(NeedOrderDetailActivity.this, ImgViewActivity.class);
                                                intent.putExtra("img", pathList.get(imgPosition));
                                                startActivity(intent);
                                            }
                                        });
                                        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(pathList.get(m), img);
                                        tvOrderView.getImgLayout().addView(img, layoutParam);
                                    } else {
                                        tvOrderView.setLayoutVisible(false);
                                        tvOrderView.setTvDaText(pathList.get(0));
                                    }
                                }

                            } else {
                                StringBuilder str = new StringBuilder();
                                for (int m = 0; m < pathList.size(); m++) {
                                    str = str.append(pathList.get(m)).append("\n");
                                }
                                if (str.toString().endsWith("\n")) {
                                    str = str.deleteCharAt(str.lastIndexOf("\n"));
                                }
//                            tvOrderView.setViewText(WDUtils2.getWenStr(strKey), str.toString());
                                tvOrderView.setViewText(WenPaseUtils.getWenStr(NeedOrderDetailActivity.this, strKey,
                                        Integer.valueOf(entity.getData().getDate().getType()), isZhorEn), str.toString());
                            }

                        }
                    } else if (2 == CheckJsonUtils.getStatuss(WDUtils2.getDaStr(valueStr))) {//字符处理
                        String str = WDUtils2.getDaStr(valueStr);
//                        Log.i("WDUtils2DaInformaton",str);
                        if (str.length() > 3) {
//                        tvOrderView.setViewText(WDUtils2.getWenStr(strKey),
//                                str.substring(3, str.length()));

                            tvOrderView.setViewText(WenPaseUtils.getWenStr(NeedOrderDetailActivity.this, strKey,
                                    Integer.valueOf(entity.getData().getDate().getType()), isZhorEn),
                                    str.substring(3, str.length()));
                        }else{
                            tvOrderView.setViewText(WenPaseUtils.getWenStr(NeedOrderDetailActivity.this, strKey,
                                    Integer.valueOf(entity.getData().getDate().getType()), isZhorEn),
                                    "");
                        }
                    }
                    if(!skillType.equals("9")){
                        llcollpase.addView(tvOrderView);
                    }else{
                        viewList.add(tvOrderView);
                    }
                }
            }

            if(skillType.equals("9")){
                for(int i=0;i<2;i++){
                    llcollpase.addView(viewList.get(i));
                }
                llcollpase.addView(viewList.get(3));
                llcollpase.addView(viewList.get(2));
                llcollpase.addView(viewList.get(5));
                llcollpase.addView(viewList.get(4));
                for(int i=6;i<viewList.size();i++){
                    llcollpase.addView(viewList.get(i));
                }
            }

        }
    }

    public void requestCancelOrder(String id, final String type) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("id", id);
        map.put("type", type);
        map.put("secret_key", SPUtils.getString(NeedOrderDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(this, Contants.order_ydel, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        final RegisterDialog dialog = new RegisterDialog(NeedOrderDetailActivity.this, getString(R.string.message_alert), getString(R.string.commit_success));
                        dialog.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                setResult(RESULT_OK);
                                finish();
                            }
                        }, 1500);
                    } else {
                        ToastUtil.shortToast(NeedOrderDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(SPUtils.getBoolean(this,"isSelectPro",false)){
            finish();
        }
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.order_detail_daiqueren_tv_expand:
                if (flag) {
                    flag = false;
                    llcollpase.setVisibility(View.VISIBLE);
                    tvexpand.setText(getString(R.string.need_order_hide));
                    tvexpand.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.arrow_up_white, 0);
                    tvexpand.setCompoundDrawablePadding(5);
                } else {
                    flag = true;
                    llcollpase.setVisibility(View.GONE);
                    tvexpand.setText(getString(R.string.need_order_view_all));
                    tvexpand.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.arrow_down_white, 0);
                    tvexpand.setCompoundDrawablePadding(5);
                }
                break;
            case R.id.need_response_order_detail_tv_choose_serve://选择专家
                gotoSelectServer();
                break;
            case R.id.user_order_detail_ll_bottom:
                getCancelOrderTime();

//                CustomDialog.Builder customBuilder = new CustomDialog.Builder(this);
//                customBuilder.setMessage(getString(R.string.main_need_is_cancel)).setPositiveButton(getString(R.string.message_alert_yes),
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                requestCancelOrder(id, entity.getData().getDate().getType());
//                                dialog.dismiss();
//                            }
//                        })
//                        .setNegativeButton(getString(R.string.message_alert_no),
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.dismiss();
//                                    }
//                                })
//                        .create().show();
                break;
            case R.id.user_order_detail_ll_bottom_select://选择专家
                gotoSelectServer();
                break;
        }
    }

    private boolean isFirstDayOfMonth() {
        SimpleDateFormat format = new SimpleDateFormat("dd");
        String day = format.format(System.currentTimeMillis());
        Log.i("dayInforamtion", day);
        if (day.equals("01")) {
            return true;
        } else {
            return false;
        }
    }

    private void getCancelOrderTime() {


        CustomDialog.Builder customBuilder = new CustomDialog.Builder(this);
        customBuilder.setTitle(getString(R.string.app_tip)).setMessage(getString(R.string.main_need_is_cancel)).setPositiveButton(getString(R.string.message_alert_yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        requestCancelOrder(id, entity.getData().getDate().getType());
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.message_alert_no),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                .create().show();

//        CustomDialog.Builder customBuilder = new CustomDialog.Builder(this);
//
////        int cancel_time = entity.getData().getUser_quci();
//        int can_cancel_times = Integer.valueOf(user_quci_num)-Integer.valueOf(user_quci);
//
//
//
//        if(can_cancel_times==Integer.valueOf(user_quci_num)){
//            customBuilder.setTitle(getString(R.string.tstitle)).setMessage(getString(R.string.can_canle_time)+user_quci_num+getString(R.string.can_not_cancel_time)+"\n"+getString(R.string.aleardy_cancel_time)+can_cancel_times+getString(R.string.orer_ti2))
//                    .setPositiveButton(getString(R.string.message_alert_yes), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.dismiss();
//                        }
//                    }).create().show();
//        }else{
//            customBuilder.setTitle(getString(R.string.tstitle)).setMessage(getString(R.string.can_canle_time)+user_quci_num+getString(R.string.can_canle_time1)+"\n"+getString(R.string.aleardy_cancel_time)+can_cancel_times+getString(R.string.orer_ti2))
//                    .setNegativeButton(getString(R.string.message_alert_no), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.dismiss();
//                        }
//                    })
//                    .setPositiveButton(getString(R.string.message_alert_yes), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            requestCancelOrder(id, entity.getData().getDate().getType());
//                            dialogInterface.dismiss();
//                        }
//                    }).create().show();
//        }
    }

    private void gotoSelectServer() {
        if (entity.getData().getDate() != null) {
            Bundle bundle = new Bundle();
            bundle.putString("id", id);
            bundle.putString("lat", entity.getData().getHous().getLat());
            bundle.putString("long", entity.getData().getHous().getLongx());
            bundle.putString("type", entity.getData().getDate().getType());
            bundle.putString("addr", entity.getData().getHous().getAddress_info());
            bundle.putString("date", entity.getData().getDate().getCtime());
            bundle.putString("proid",getIntent().getExtras().getString("proid"));
            ActivityTools.goNextActivity(this, TakeOrderServeListActivity.class, bundle);
        }
    }



    @Override
    public void onPullToRefresh() {
        requestDetail();
        isRefresh = true;
    }
}
