package com.haiwai.housekeeper.activity.server;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.MapBoxMapActivity;
import com.haiwai.housekeeper.activity.user.O2ODetailActivity;
import com.haiwai.housekeeper.activity.user.ProDetailActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.CityEntity;
import com.haiwai.housekeeper.entity.CityLevelEntity;
import com.haiwai.housekeeper.entity.OrderNewWeekEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.imageloader.ImageLoader;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.CityUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.StaticUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.CircleImageView;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.view.TvOrderView;
import com.haiwai.housekeeper.widget.LoadDialog;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope10 on 2016/11/9.
 */

public class OrderWeekDetailActivity extends BaseProActivity {
    private TopViewNormalBar top_week_bar;
    private TextView tv_order_num, tv_order_title, tv_order_location_l, tv_order_time_l, tv_order_state, tv_week_num, btn_textView;
    private CircleImageView civ_detail_img;
    private TextView tv_detial_name, tv_title_tel, tv_by_tel, tv_title_location;
    private ImageView iv_detail_chat, iv_detail_phone;
    String id = "";
    String uid = "";
    private User user;
    private int is_jie;
    //    private OrderWeekEntity mOrderWeekEntity;
    private OrderNewWeekEntity mOrderNewWeekEntity;
    ImageLoader imageLoader;
    private OrderNewWeekEntity.DataBean.DateBean mDate;
    private ImageView iv_order_show_or_no;
    private LinearLayout ll_week_detail_layout;
    private boolean isShow = false;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                mOrderWeekRefreshDataListener.changeWeekData();
                finish();
            }
            super.handleMessage(msg);
        }
    };
    private OrderNewWeekEntity.DataBean.UserBean mUser;
    private List<OrderNewWeekEntity.DataBean.ProblemwBean> mProblemwBeanList;
    private String isZhorEn = "";

    private ScrollView is_showing;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_order_week_detail_layout);
        top_week_bar = (TopViewNormalBar) findViewById(R.id.top_week_bar);
        top_week_bar.setOnBackListener(mOnClickListener);
        top_week_bar.setTitle(R.string.need_order_detail);
        imageLoader = new ImageLoader(this);
        initView();

    }

    private void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        user = AppGlobal.getInstance().getUser();
        if (user != null) {
            uid = user.getUid();
        }
        LoadDialog.showProgressDialog(this);
        id = getIntent().getStringExtra("id");
        is_jie = getIntent().getIntExtra("is_jie", 0);
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        map.put("secret_key", SPUtils.getString(OrderWeekDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderWeekDetailActivity.this, Contants.self_detail, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(">>>周期订单详情>>>" + response);
                int code = JsonUtils.getJsonInt(response, "status");
               // LoadDialog.closeProgressDialog();
                mHandler1.sendEmptyMessageDelayed(100,1000);
                is_showing.setVisibility(View.VISIBLE);
                if (code == 200) {
//                    mOrderWeekEntity = new Gson().fromJson(response, OrderWeekEntity.class);
                    mOrderNewWeekEntity = new Gson().fromJson(response, OrderNewWeekEntity.class);
//                    bindDataToView(mOrderWeekEntity);
                    bindDataToView(mOrderNewWeekEntity);
                } else {
                    ToastUtil.shortToast(OrderWeekDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }

            }
        }));
    }
    Handler mHandler1 = new Handler() {
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 100:
                    LoadDialog.closeProgressDialog();
                    break;


            }

        }
    };

    //    private void bindDataToView(OrderWeekEntity orderWeekEntity) {
    private void bindDataToView(OrderNewWeekEntity orderWeekEntity) {
        List<OrderNewWeekEntity.DataBean.FeedbackBean> feedback = orderWeekEntity.getData().getFeedback();
        mDate = orderWeekEntity.getData().getDate();
        final OrderNewWeekEntity.DataBean.HousBean hous = orderWeekEntity.getData().getHous();
        OrderNewWeekEntity.DataBean.OfferBean offer = orderWeekEntity.getData().getOffer();
        mProblemwBeanList = orderWeekEntity.getData().getProblemw();
        mUser = orderWeekEntity.getData().getUser();
        tv_order_num.setText(getString(R.string.str_order_code) + mDate.getOrder_id());
        tv_order_title.setText(StaticUtils.getWeekTypeStr(mDate.getType(), isZhorEn));
        if (hous != null) {
            Map<String, String> map = new HashMap<>();
            map.put("secret_key", SPUtils.getString(OrderWeekDetailActivity.this, "secret", ""));
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderWeekDetailActivity.this, Contants.city_lst, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String res) {
                    System.out.println(">>>>>>>>>>城市列表>>" + res);
                    int code = JsonUtils.getJsonInt(res, "status");
                    if (code == 200) {
                        CityEntity mEntity = CityUtils.parseCity(res);
                        ArrayList<CityLevelEntity> mList = CityUtils.getLevelList("3", "23", mEntity);

                        for(int i=0;i<mList.size();i++){
                            if(hous.getCity().equals(mList.get(i).getId())){
                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                    tv_order_location_l.setText("CanadaBritish Columbia "+mList.get(i).getYname()+" " + hous.getAddress_info());
                                }else{
                                    tv_order_location_l.setText("加拿大不列颠哥伦比亚省 "+mList.get(i).getName()+" "+hous.getAddress_info());
                                }
                                break;
                            }
                        }

                    } else {
                        LoadDialog.closeProgressDialog();
                        ToastUtil.longToast(OrderWeekDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }));

            tv_order_location_l.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentMap = new Intent(OrderWeekDetailActivity.this, MapBoxMapActivity.class);
                    intentMap.putExtra("isMap", true);
                    intentMap.putExtra("lat", hous == null ? "" : hous.getLat());
                    intentMap.putExtra("lng", hous == null ? "" : hous.getLongX());
                    startActivity(intentMap);
                }
            });
            if(mDate.getStaticX().equals("1")||mDate.getStaticX().equals("2")||mDate.getStaticX().equals("5")||mDate.getStaticX().equals("6")){
                tv_by_tel.setText(getString(R.string.jd_t));
                tv_by_tel_phone1.setText(getString(R.string.jd_t));
            }else{
                tv_by_tel.setText(hous.getAlternate_contact());
                tv_by_tel_phone1.setText(hous.getAlternate_contact_number());
            }



        }
        Date date = new Date(Long.valueOf(mDate.getMonthtime())*1000L);
        Calendar calendar   =   new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,calendar.getActualMaximum(Calendar.DAY_OF_MONTH)-1);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime();   //这个时间就是日期往后推一天的结果

        tv_order_time_l.setText(TimeUtils.getNewDate(mDate.getMonthtime())+"-"+TimeUtils.getNewDate2(date));
        if(AppGlobal.getInstance().getLagStr().equals("en")){
            if(mDate.getNum().equals("0")){//--0123次/月 分别对应 0/month ;Once/month；Twice/month；Three times/month
                tv_week_num.setText("0" + "/" + getString(R.string.order_ti11));
            }else if(mDate.getNum().equals("1")){
                tv_week_num.setText("Once" + "/" + getString(R.string.order_ti11));
            }else if(mDate.getNum().equals("2")){
                tv_week_num.setText("Twice" + "/" + getString(R.string.order_ti11));
            }else if(mDate.getNum().equals("3")){
                tv_week_num.setText("Three " + getString(R.string.orer_ti2) + "/" + getString(R.string.order_ti11));

            }
        }else{
            tv_week_num.setText(mDate.getNum()+"次/每月");
        }

        if (!TextUtils.isEmpty(mUser.getAvatar())) {
            imageLoader.DisplayImage(mUser.getAvatar(), civ_detail_img);
        } else {
            civ_detail_img.setImageResource(R.mipmap.moren_head);
        }
        tv_detial_name.setText(mUser.getNickname());
        tv_title_tel.setText(mUser.getMobile());
        tv_title_location.setText(mUser.getAddress());
        if (1 == offer.getIs_jie()) {
            btn_textView.setVisibility(View.GONE);
            tv_order_state.setText(R.string.wek_sta1);
        } else if (0 == offer.getIs_jie()) {
            tv_order_state.setText(R.string.zt1);
        }
        ll_week_detail_layout.removeAllViews();
        if (mProblemwBeanList != null && mProblemwBeanList.size() > 0) {
            for (int i = 0; i < mProblemwBeanList.size(); i++) {
                boolean isCar = false;
                TvOrderView tvOrderView = new TvOrderView(this);
                StringBuilder sb = new StringBuilder();
                if (mProblemwBeanList.get(i).getProblem() != null && mProblemwBeanList.get(i).getProblem().size() > 0) {
                    for (int j = 0; j < mProblemwBeanList.get(i).getProblem().size(); j++) {
                        String money = mProblemwBeanList.get(i).getProblem().get(j).getZhi();
                        Log.i("information----",mProblemwBeanList.get(i).getValue()+"---"+money);
                        String id = mProblemwBeanList.get(i).getProblem().get(j).getId();
                        if(id==null){
                           id="";
                        }
                        if(!money.equals("")){
                            if(id.equals("49")){
                                String carValue;
                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                    carValue = mProblemwBeanList.get(i).getProblem().get(j).getYvalue();
                                }else{
                                    carValue = mProblemwBeanList.get(i).getProblem().get(j).getValue() ;
                                }
                                if(id.equals("49")&&mProblemwBeanList.get(i).getProblem().get(j).getZhi().equals("")){
                                    isCar = true;
                                }
                                String carDa = carValue.substring(0,carValue.indexOf("[")+1)+
                                        money
                                        +carValue.substring(carValue.indexOf("]"),carValue.length());
                                sb = sb.append(carDa).append("\n");
                            }else if(id.equals("110")) {
                                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                    sb = sb.append(money).append("sq ft")
                                            .append("\n");
                                } else {
                                    sb = sb.append(money).append("平方英尺")
                                            .append("\n");
                                }
                            }else{
                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                    sb = sb.append(money).append(mProblemwBeanList.get(i).getProblem().get(j).getYvalue())
                                            .append("\n");
                                }else{
                                    sb = sb.append(money).append(mProblemwBeanList.get(i).getProblem().get(j).getValue())
                                            .append("\n");
                                }
                            }
                        }else{

                            if(id.equals("66")){
                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                    money = "I am not sure";
                                }else{
                                    money = "我不确定";
                                }
                                sb = sb.append(money).append("\n");
                            }else{
                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                    if(mProblemwBeanList.get(i).getProblem().get(j).getYvalue()==null){
                                        sb = sb.append("")
                                                .append("\n");
                                    }else {
                                        sb = sb.append(mProblemwBeanList.get(i).getProblem().get(j).getYvalue())
                                                .append("\n");
                                    }

                                }else{
                                    if(mProblemwBeanList.get(i).getProblem().get(j).getValue()==null){
                                        sb = sb.append("")
                                                .append("\n");
                                    }else {
                                        sb = sb.append(mProblemwBeanList.get(i).getProblem().get(j).getValue())
                                                .append("\n");
                                    }

                                }

                            }

                        }

                    }
                    if (sb.toString().endsWith("\n")) {
                        sb = sb.deleteCharAt(sb.lastIndexOf("\n"));
                    }
                }
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    tvOrderView.setViewText(mProblemwBeanList.get(i).getYvalue(), sb.toString());
                }else{
                    tvOrderView.setViewText(mProblemwBeanList.get(i).getValue(), sb.toString());
                }

                if(!isCar){
                    ll_week_detail_layout.addView(tvOrderView);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initView() {
        is_showing= (ScrollView) findViewById(R.id.is_showing);
        is_showing.setVisibility(View.GONE);
        tv_order_num = (TextView) findViewById(R.id.tv_order_num);
        tv_order_title = (TextView) findViewById(R.id.tv_order_title);
        tv_order_location_l = (TextView) findViewById(R.id.tv_order_location_l);
        tv_order_time_l = (TextView) findViewById(R.id.tv_order_time_l);
        tv_order_state = (TextView) findViewById(R.id.tv_order_state);
        tv_week_num = (TextView) findViewById(R.id.tv_week_num);
        btn_textView = (TextView) findViewById(R.id.btn_textView);
        findViewById(R.id.ll_week_view).setVisibility(View.VISIBLE);
        if(AppGlobal.getInstance().getLagStr().equals("en")){
            btn_textView.setText("Take Order");
        }else{
            btn_textView.setText("接单");
        }
        btn_textView.setOnClickListener(mOnClickListener);
        View cardView = findViewById(R.id.ll_layout_card);
        initCardView(cardView);
        ll_week_detail_layout = (LinearLayout) findViewById(R.id.ll_week_detail_layout);
        iv_order_show_or_no = (ImageView) findViewById(R.id.iv_order_show_or_no);
        if ("en".equals(AppGlobal.getInstance().getLagStr())) {
            iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_order_show_en);
        } else {
            iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_order_show);
        }

//        if ("en".equals(isZhorEn)) {
//            iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_order_show_en);
//        } else {
//            iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_order_show);
//        }

        iv_order_show_or_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isShow) {
                    isShow = true;
                    ll_week_detail_layout.setVisibility(View.VISIBLE);
                    if ("en".equals(isZhorEn)) {
                        iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_btn_hide_en);
                    } else {
                        iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_btn_hide);
                    }

                } else {
                    isShow = false;
                    ll_week_detail_layout.setVisibility(View.GONE);
                    if ("en".equals(isZhorEn)) {
                        iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_order_show_en);
                    } else {
                        iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_order_show);
                    }

                }

            }
        });
    }

    private TextView tv_by_tel_phone1;
    private void initCardView(View cardView) {
        civ_detail_img = (CircleImageView) cardView.findViewById(R.id.civ_detail_img);
        civ_detail_img.setOnClickListener(mOnClickListener);
        tv_detial_name = (TextView) cardView.findViewById(R.id.tv_detial_name);
        tv_title_tel = (TextView) cardView.findViewById(R.id.tv_title_tel);
        tv_title_location = (TextView) cardView.findViewById(R.id.tv_title_location);
        tv_by_tel = (TextView) cardView.findViewById(R.id.tv_by_tel);
        tv_title_location = (TextView) cardView.findViewById(R.id.tv_title_location);
        iv_detail_chat = (ImageView) cardView.findViewById(R.id.iv_detail_chat);
        iv_detail_chat.setOnClickListener(mOnClickListener);
        iv_detail_phone = (ImageView) cardView.findViewById(R.id.iv_detail_phone);
        iv_detail_phone.setVisibility(View.GONE);
        iv_detail_phone.setOnClickListener(mOnClickListener);

        tv_by_tel_phone1 = ((TextView) cardView.findViewById(R.id.tv_by_tel_phone1));
        civ_detail_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("uid", mUser.getUid());
                bundle.putString("nickname", mUser.getNickname());
                bundle.putString("type", "");
                bundle.putString("choose", "0");
                bundle.putString("oid", "");
                bundle.putBoolean("isServer", true);
                ActivityTools.goNextActivity(OrderWeekDetailActivity.this, ProDetailActivity.class, bundle);
            }
        });
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_week_bar.getBackView()) {
                finish();
            } else if (view.getId() == R.id.btn_textView) {
                Map<String, String> map = new HashMap<>();
                map.put("oid", mDate.getId());
                map.put("uid", uid);
                map.put("message", "");
                map.put("secret_key", SPUtils.getString(OrderWeekDetailActivity.this, "secret", ""));
                map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
                MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderWeekDetailActivity.this, Contants.self_offer, map, null, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(">>>周期接单>>>>>>" + response);
                        int code = JsonUtils.getJsonInt(response, "status");
                        if (code == 200) {
                            Message msg = Message.obtain();
                            msg.what = 0;
                            mHandler.sendMessage(msg);
                        } else {
                            ToastUtil.shortToast(OrderWeekDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                }));
            } else if (view.getId() == R.id.iv_detail_chat) {
                ToastUtil.longToast(OrderWeekDetailActivity.this, getString(R.string.nede_ti_user));
                return;
            } else if (view.getId() == R.id.iv_detail_phone) {
                ToastUtil.longToast(OrderWeekDetailActivity.this, getString(R.string.nede_ti_user));
                return;
            } else if (view.getId() == R.id.civ_detail_img) {
                gotoProDetailInfo();
            }
        }
    };

    private void gotoProDetailInfo() {
        Intent intent = new Intent(OrderWeekDetailActivity.this, ProDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("uid", mDate.getUid());//发布订单id
        bundle.putString("nickname", mUser.getNickname());
        bundle.putString("type", "");
        bundle.putString("choose", "");
        bundle.putString("oid", mDate.getId());//订单id
        bundle.putBoolean("isServer", true);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public static OrderWeekRefreshDataListener mOrderWeekRefreshDataListener;

    public interface OrderWeekRefreshDataListener {
        void changeWeekData();
    }

    public static void setOrderWeekRefreshDataListener(OrderWeekRefreshDataListener orderWeekRefreshDataListener) {
        mOrderWeekRefreshDataListener = orderWeekRefreshDataListener;
    }
}
