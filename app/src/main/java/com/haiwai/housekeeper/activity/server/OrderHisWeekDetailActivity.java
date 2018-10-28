package com.haiwai.housekeeper.activity.server;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.MapBoxMapActivity;
import com.haiwai.housekeeper.activity.user.ProDetailActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.CityEntity;
import com.haiwai.housekeeper.entity.CityLevelEntity;
import com.haiwai.housekeeper.entity.OrderNewWeekEntity;
import com.haiwai.housekeeper.entity.OrderZlZZEntity;
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

public class OrderHisWeekDetailActivity extends BaseProActivity {
    private TopViewNormalBar top_week_bar;
    private TextView tv_order_num, tv_order_title, tv_order_location_l, tv_order_time_l, tv_order_state, tv_week_num;
    private CircleImageView civ_detail_img;
    private TextView tv_detial_name, tv_title_tel, tv_by_tel, tv_title_location;
    private ImageView iv_detail_chat, iv_detail_phone;
    //--------------------------家政和园艺------------
    LinearLayout ll_ser_week_layout;
    RelativeLayout rl_ser_1_layout, rl_ser_2_layout, rl_ser_3_layout, rl_ser_4_layout;
    TextView ser_ll1_tv_time, ser_ll2_tv_time, ser_ll3_tv_time, ser_ll4_tv_time;
    //----------------------房屋巡视-------------------
    boolean is1Flag = false;
    boolean is2Flag = false;
    LinearLayout ll_xs_week_layout;
    ImageView ib_1_sh, ib_2_sh;
    LinearLayout ll_1_layout, ll_2_layout;
    RelativeLayout rl_1_fwxs_layout, rl_1_sqxj_layout, rl_1_tsqkfk_layout, rl_2_fwxs_layout, rl_2_sqxj_layout, rl_2_tsqkfk_layout;
    TextView tv_1_fwxs_date, tv_1_sqxj_date, tv_1_tsqkfk_date, tv_2_fwxs_date, tv_2_sqxj_date, tv_2_tsqkfk_date;
    //-----------租赁招租-------
    LinearLayout ll_zl_zz_layout;
    TextView tv_zfmc, tv_zhzj;
    String id = "";
    String uid = "";
    String type = "";
    private User user;
    private int is_jie;
    //    private OrderWeekEntity mOrderWeekEntity;
    private OrderNewWeekEntity mOrderNewWeekEntity;
    private OrderZlZZEntity mOrderZlZZEntity;
    ImageLoader imageLoader;
    private OrderNewWeekEntity.DataBean.DateBean mDate;
    private OrderZlZZEntity.DataBean.DateBean mDate1;
    private OrderZlZZEntity.DataBean.UserBean mUserBean1;
    private OrderNewWeekEntity.DataBean.UserBean mUserBean;
    private OrderNewWeekEntity.DataBean.FeedbackBean mFeedbackBean;
    private OrderNewWeekEntity.DataBean.FeedbackBean mFeedback2Bean;
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
    private List<OrderNewWeekEntity.DataBean.FeedbackBean> mFeedback;
    private LinearLayout ll_his_week_detail_layout;
    private ImageView iv_order_show_or_no;
    private boolean isShow = false;
    private List<OrderNewWeekEntity.DataBean.ProblemwBean> mProblemw;
    private List<OrderZlZZEntity.DataBean.ProblemwBean> mProblemw1;
    private String isZhorEn = "";

    private ScrollView is_showing;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_order_his_week_detail_layout);
        top_week_bar = (TopViewNormalBar) findViewById(R.id.top_week_bar);
        top_week_bar.setOnBackListener(mOnClickListener);
        top_week_bar.setTitle(R.string.need_order_detail);
        imageLoader = new ImageLoader(this);
        initView();
        initData();

    }

    private void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        user = AppGlobal.getInstance().getUser();
        if (user != null) {
            uid = user.getUid();
        }
        LoadDialog.showProgressDialog(this);
        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        map.put("secret_key", SPUtils.getString(OrderHisWeekDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        if ("35".equals(type)) {
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderHisWeekDetailActivity.this, Contants.zhao_detail, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(">>>招租订单详情>>>" + response);
                   // LoadDialog.closeProgressDialog();
                    is_showing.setVisibility(View.VISIBLE);
                    mHandler1.sendEmptyMessageDelayed(100,1000);
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        mOrderZlZZEntity = new Gson().fromJson(response, OrderZlZZEntity.class);
                        bindDataToZZView(mOrderZlZZEntity);
                    } else {
                        ToastUtil.shortToast(OrderHisWeekDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }

                }
            }));
        } else {
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderHisWeekDetailActivity.this, Contants.self_detail, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(">>>周期订单详情>>>" + response);
                    int code = JsonUtils.getJsonInt(response, "status");
                   // LoadDialog.closeProgressDialog();
                    is_showing.setVisibility(View.VISIBLE);
                    mHandler1.sendEmptyMessageDelayed(100,1000);
                    if (code == 200) {
                        mOrderNewWeekEntity = new Gson().fromJson(response, OrderNewWeekEntity.class);
                        bindDataToView(mOrderNewWeekEntity);
                    } else {
                        ToastUtil.shortToast(OrderHisWeekDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }

                }
            }));
        }
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
    private void bindDataToZZView(OrderZlZZEntity mOrderZlZZEntity) {
        OrderZlZZEntity.DataBean.HousBean hous = mOrderZlZZEntity.getData().getHous();
        OrderZlZZEntity.DataBean.ForrentBean forrent = mOrderZlZZEntity.getData().getForrent();
        mUserBean1 = mOrderZlZZEntity.getData().getUser();
        mDate1 = mOrderZlZZEntity.getData().getDate();
        mProblemw1 = mOrderZlZZEntity.getData().getProblemw();
        tv_order_num.setText(getString(R.string.str_order_code) + mDate1.getOrder_id());
        tv_order_title.setText(StaticUtils.getWeekTypeStr(mDate1.getType(), isZhorEn));
        tv_order_location_l.setText(hous.getAddress_info());

        Date date = new Date(Long.valueOf(mDate1.getMonthtime())*1000L);
        Calendar mCalendar = Calendar.getInstance();

        Calendar calendar   =   new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,calendar.getActualMaximum(Calendar.DAY_OF_MONTH)-1);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime();   //这个时间就是日期往后推一天的结果

        tv_order_time_l.setText(TimeUtils.getNewDate(mDate1.getMonthtime())+"-"+TimeUtils.getNewDate2(date));



//        tv_order_time_l.setText(TimeUtils.getDate(mDate1.getCtime()));
//        tv_week_num.setText(mDate1.getNum());
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
            tv_week_num.setText(mDate1.getNum()+"次/每月");
        }
        if (!TextUtils.isEmpty(mUserBean1.getAvatar())) {
            imageLoader.DisplayImage(mUserBean1.getAvatar(), civ_detail_img);
        } else {
            civ_detail_img.setImageResource(R.mipmap.moren_head);
        }
        tv_detial_name.setText(mUserBean1.getName());
        tv_title_tel.setText(mUserBean1.getMobile());
        if(mDate1.getStaticX().equals("1")||mDate1.getStaticX().equals("2")||mDate1.getStaticX().equals("5")||mDate1.getStaticX().equals("6")){
            tv_by_tel.setText(getString(R.string.jd_t));
            tv_by_tel_phone1.setText(getString(R.string.jd_t));
        }else{
            tv_by_tel_phone1.setText(hous.getAlternate_contact_number());
            tv_by_tel.setText(hous.getAlternate_contact());
        }

        tv_title_location.setText(mUserBean1.getAddress());
        ll_zl_zz_layout.setVisibility(View.VISIBLE);
        ll_xs_week_layout.setVisibility(View.GONE);
        ll_ser_week_layout.setVisibility(View.GONE);
        tv_zfmc.setText(forrent.getRen_nickname());
        tv_zhzj.setText("$"+forrent.getRent());

        if (mProblemw1 != null && mProblemw1.size() > 0) {
            ll_his_week_detail_layout.removeAllViews();
            for (int i = 0; i < mProblemw1.size(); i++) {
                TvOrderView tvOrderView = new TvOrderView(this);
                boolean isCar = false;
                StringBuilder sb = new StringBuilder();
                if (mProblemw1.get(i).getProblem() != null && mProblemw1.get(i).getProblem().size() > 0) {
                    for (int j = 0; j < mProblemw1.get(i).getProblem().size(); j++) {
                        String money = mProblemw1.get(i).getProblem().get(j).getZhi();
                        Log.i("information----", money);
                        String id = mProblemw1.get(i).getProblem().get(j).getId();
                        if (!money.equals("")) {
                            if (id.equals("49")) {
                                String carValue;
                                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                    carValue = mProblemw1.get(i).getProblem().get(j).getYvalue();
                                } else {
                                    carValue = mProblemw1.get(i).getProblem().get(j).getValue();
                                }
                                if (id.equals("49") && mProblemw1.get(i).getProblem().get(j).getZhi().equals("")) {
                                    isCar = true;
                                }
                                String carDa = carValue.substring(0, carValue.indexOf("[") + 1) +
                                        money
                                        + carValue.substring(carValue.indexOf("]"), carValue.length());
                                sb = sb.append(carDa).append("\n");
                            }else if(id.equals("110")){
                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                    sb = sb.append(money).append("sq ft")
                                            .append("\n");
                                }else{
                                    sb = sb.append(money).append("平方英尺")
                                            .append("\n");
                                }
                            }  else {
                                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                    sb = sb.append(money).append(mProblemw1.get(i).getProblem().get(j).getYvalue())
                                            .append("\n");
                                } else {
                                    sb = sb.append(money).append(mProblemw1.get(i).getProblem().get(j).getValue())
                                            .append("\n");
                                }
                            }
                        } else {

                            if (id.equals("66")) {
                                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                    money = "I am not sure";
                                } else {
                                    money = "我不确定";
                                }
                                sb = sb.append(money).append("\n");
                            } else {
                                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                    sb = sb.append(mProblemw1.get(i).getProblem().get(j).getYvalue())
                                            .append("\n");
                                } else {
                                    sb = sb.append(mProblemw1.get(i).getProblem().get(j).getValue())
                                            .append("\n");
                                }

                            }

                        }
                    }
                }
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    tvOrderView.setViewText(mProblemw1.get(i).getYvalue(), sb.toString());
                }else{
                    tvOrderView.setViewText(mProblemw1.get(i).getValue(), sb.toString());
                }

                if(!isCar){
                    ll_his_week_detail_layout.addView(tvOrderView);
                }
            }
        }
    }


    private void bindDataToView(OrderNewWeekEntity orderWeekEntity) {
        mFeedback = orderWeekEntity.getData().getFeedback();
        if ("29".equals(type)) {
            mFeedbackBean = mFeedback.get(0);
            mFeedback2Bean = mFeedback.get(1);
        }
        final OrderNewWeekEntity.DataBean.HousBean hous = orderWeekEntity.getData().getHous();
        OrderNewWeekEntity.DataBean.OfferBean offer = orderWeekEntity.getData().getOffer();
        mDate = orderWeekEntity.getData().getDate();
        mUserBean = orderWeekEntity.getData().getUser();
        mProblemw = orderWeekEntity.getData().getProblemw();
        tv_order_num.setText(getString(R.string.str_order_code) + mDate.getOrder_id());
        tv_order_title.setText(StaticUtils.getWeekTypeStr(mDate.getType(), isZhorEn));

        if(hous!=null){
            Map<String, String> map = new HashMap<>();
            map.put("secret_key", SPUtils.getString(OrderHisWeekDetailActivity.this, "secret", ""));
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderHisWeekDetailActivity.this, Contants.city_lst, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String res) {
                    System.out.println(">>>>>>>>>>城市列表>>" + res);
                    int code = JsonUtils.getJsonInt(res, "status");
                    if (code == 200) {
                        CityEntity mEntity = CityUtils.parseCity(res);
                        ArrayList<CityLevelEntity> mList = CityUtils.getLevelList("3", "23", mEntity);

                        for(int i=0;i<mList.size();i++){
                            if(hous.getCity().equals(mList.get(i).getId())){

                                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                    tv_order_location_l.setText("CanadaBritish Columbia "+mList.get(i).getYname()+" " + hous.getAddress_info());
                                } else {
                                    tv_order_location_l.setText("加拿大哥伦比亚市 "+mList.get(i).getName()+" " + hous.getAddress_info());
                                }
                                break;
                            }
                        }

                    } else {
                        LoadDialog.closeProgressDialog();
                        ToastUtil.longToast(OrderHisWeekDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }));
        }
        tv_order_location_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMap = new Intent(OrderHisWeekDetailActivity.this, MapBoxMapActivity.class);
                intentMap.putExtra("isMap", true);
                intentMap.putExtra("lat", hous == null ? "" : hous.getLat());
                intentMap.putExtra("lng", hous == null ? "" : hous.getLongX());
                startActivity(intentMap);
            }
        });

        Date date = new Date(Long.valueOf(mDate.getCtime())*1000L);
        Calendar calendar   =   new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,30);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime();   //这个时间就是日期往后推一天的结果

        tv_order_time_l.setText(TimeUtils.getNewDate(mDate.getCtime())+"-"+TimeUtils.getNewDate2(date));


//        tv_order_time_l.setText(TimeUtils.getDate(mDate.getCtime()));
//        tv_week_num.setText(mDate.getNum());
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
        if (!TextUtils.isEmpty(mUserBean.getAvatar())) {
            imageLoader.DisplayImage(mUserBean.getAvatar(), civ_detail_img);
        } else {
            civ_detail_img.setImageResource(R.mipmap.img_default);
        }
        tv_detial_name.setText(mUserBean.getNickname());
        tv_title_tel.setText(mUserBean.getMobile());

        if(mDate.getStaticX().equals("1")||mDate.getStaticX().equals("2")||mDate.getStaticX().equals("5")||mDate.getStaticX().equals("6")){
            tv_by_tel.setText(getString(R.string.jd_t));
            tv_by_tel_phone1.setText(getString(R.string.jd_t));
        }else{
            tv_by_tel_phone1.setText(hous.getAlternate_contact_number());
            tv_by_tel.setText(hous.getAlternate_contact());
        }
//        tv_by_tel.setText(hous.getAlternate_contact());
        tv_title_location.setText(mUserBean.getAddress());
        if ("29".equals(type)) {
            ll_xs_week_layout.setVisibility(View.VISIBLE);
            ll_zl_zz_layout.setVisibility(View.GONE);
            ll_ser_week_layout.setVisibility(View.GONE);
            ib_1_sh.setOnClickListener(mOnClickListener);
            ib_2_sh.setOnClickListener(mOnClickListener);
            rl_1_fwxs_layout.setOnClickListener(mOnClickListener);
            rl_1_sqxj_layout.setOnClickListener(mOnClickListener);
            rl_1_tsqkfk_layout.setOnClickListener(mOnClickListener);
            rl_2_fwxs_layout.setOnClickListener(mOnClickListener);
            rl_2_sqxj_layout.setOnClickListener(mOnClickListener);
            rl_2_tsqkfk_layout.setOnClickListener(mOnClickListener);
            tv_1_fwxs_date.setText(TimeUtils.getDate(mFeedback.get(0).getWtime1()));
            tv_1_sqxj_date.setText(TimeUtils.getDate(mFeedback.get(0).getWtime2()));
            tv_1_tsqkfk_date.setText(TimeUtils.getDate(mFeedback.get(0).getWtime3()));
            tv_2_fwxs_date.setText(TimeUtils.getDate(mFeedback.get(1).getWtime1()));
            tv_2_sqxj_date.setText(TimeUtils.getDate(mFeedback.get(1).getWtime2()));
            tv_2_tsqkfk_date.setText(TimeUtils.getDate(mFeedback.get(1).getWtime3()));
        } else {
            ll_ser_week_layout.setVisibility(View.VISIBLE);
            ll_zl_zz_layout.setVisibility(View.GONE);
            ll_xs_week_layout.setVisibility(View.GONE);
            if (mFeedback != null) {
                int count = mFeedback.size();
                if (count == 4) {
                    rl_ser_1_layout.setOnClickListener(mOnClickListener);
                    rl_ser_2_layout.setOnClickListener(mOnClickListener);
                    rl_ser_3_layout.setOnClickListener(mOnClickListener);
                    rl_ser_4_layout.setOnClickListener(mOnClickListener);
                    ser_ll1_tv_time.setText(TimeUtils.getDate(mFeedback.get(0).getWtime1()));
                    ser_ll2_tv_time.setText(TimeUtils.getDate(mFeedback.get(1).getWtime1()));
                    ser_ll3_tv_time.setText(TimeUtils.getDate(mFeedback.get(2).getWtime1()));
                    ser_ll4_tv_time.setText(TimeUtils.getDate(mFeedback.get(3).getWtime1()));
                } else if (count == 3) {
                    rl_ser_1_layout.setOnClickListener(mOnClickListener);
                    rl_ser_2_layout.setOnClickListener(mOnClickListener);
                    rl_ser_3_layout.setOnClickListener(mOnClickListener);
                    rl_ser_4_layout.setVisibility(View.GONE);
                    ser_ll1_tv_time.setText(TimeUtils.getDate(mFeedback.get(0).getWtime1()));
                    ser_ll2_tv_time.setText(TimeUtils.getDate(mFeedback.get(1).getWtime1()));
                    ser_ll3_tv_time.setText(TimeUtils.getDate(mFeedback.get(2).getWtime1()));
                } else if (count == 2) {
                    rl_ser_1_layout.setOnClickListener(mOnClickListener);
                    rl_ser_2_layout.setOnClickListener(mOnClickListener);
                    rl_ser_3_layout.setVisibility(View.GONE);
                    rl_ser_4_layout.setVisibility(View.GONE);
                    ser_ll1_tv_time.setText(TimeUtils.getDate(mFeedback.get(0).getWtime1()));
                    ser_ll2_tv_time.setText(TimeUtils.getDate(mFeedback.get(1).getWtime1()));
                } else if (count == 1) {
                    rl_ser_1_layout.setOnClickListener(mOnClickListener);
                    rl_ser_2_layout.setVisibility(View.GONE);
                    rl_ser_3_layout.setVisibility(View.GONE);
                    rl_ser_4_layout.setVisibility(View.GONE);
                    ser_ll1_tv_time.setText(TimeUtils.getDate(mFeedback.get(0).getWtime1()));
                }
            }
        }

        if (mProblemw != null && mProblemw.size() > 0) {
            ll_his_week_detail_layout.removeAllViews();
            for (int i = 0; i < mProblemw.size(); i++) {
                boolean isCar =false;
                TvOrderView tvOrderView = new TvOrderView(this);
                StringBuilder sb = new StringBuilder();
                if (mProblemw.get(i).getProblem() != null && mProblemw.get(i).getProblem().size() > 0) {
                    for (int j = 0; j < mProblemw.get(i).getProblem().size(); j++) {
                        String money = mProblemw.get(i).getProblem().get(j).getZhi();
                        Log.i("information----", money);
                        String id = mProblemw.get(i).getProblem().get(j).getId();
                        if(id==null){
                            id="";
                        }
                        if (!money.equals("")) {
                            if (id.equals("49")) {
                                String carValue;
                                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                    carValue = mProblemw.get(i).getProblem().get(j).getYvalue();
                                } else {
                                    carValue = mProblemw.get(i).getProblem().get(j).getValue();
                                }
                                if (id.equals("49") && mProblemw.get(i).getProblem().get(j).getZhi().equals("")) {
                                    isCar = true;
                                }
                                String carDa = carValue.substring(0, carValue.indexOf("[") + 1) +
                                        money
                                        + carValue.substring(carValue.indexOf("]"), carValue.length());
                                sb = sb.append(carDa).append("\n");
                            }else if(id.equals("110")){
                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                    sb = sb.append(money).append("sq ft")
                                            .append("\n");
                                }else{
                                    sb = sb.append(money).append("平方英尺")
                                            .append("\n");
                                }
                            }  else {
                                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                    sb = sb.append(money).append(mProblemw.get(i).getProblem().get(j).getYvalue())
                                            .append("\n");
                                } else {
                                    sb = sb.append(money).append(mProblemw.get(i).getProblem().get(j).getValue())
                                            .append("\n");
                                }
                            }
                        } else {

                            if (id.equals("66")) {
                                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                    money = "I am not sure";
                                } else {
                                    money = "我不确定";
                                }
                                sb = sb.append(money).append("\n");
                            } else {
                                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                    if(mProblemw.get(i).getProblem().get(j).getYvalue()==null){
                                        sb = sb.append("")
                                                .append("\n");
                                    }else {
                                        sb = sb.append(mProblemw.get(i).getProblem().get(j).getYvalue())
                                                .append("\n");
                                    }

                                } else {
                                    if(mProblemw.get(i).getProblem().get(j).getValue()==null){
                                        sb = sb.append("")
                                                .append("\n");
                                    }else {
                                        sb = sb.append(mProblemw.get(i).getProblem().get(j).getValue())
                                                .append("\n");
                                    }

                                }

                            }

                        }
                    }
                }
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    tvOrderView.setViewText(mProblemw.get(i).getYvalue(), sb.toString());
                }else{
                    tvOrderView.setViewText(mProblemw.get(i).getValue(), sb.toString());
                }

                if(!isCar){
                    ll_his_week_detail_layout.addView(tvOrderView);
                }
            }
        }
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
        View cardView = findViewById(R.id.ll_layout_card);
        initCardView(cardView);
        //--------------------------家政和园艺------------
        ll_ser_week_layout = (LinearLayout) findViewById(R.id.ll_ser_week_layout);
        rl_ser_1_layout = (RelativeLayout) findViewById(R.id.rl_ser_1_layout);
        rl_ser_2_layout = (RelativeLayout) findViewById(R.id.rl_ser_2_layout);
        rl_ser_3_layout = (RelativeLayout) findViewById(R.id.rl_ser_3_layout);
        rl_ser_4_layout = (RelativeLayout) findViewById(R.id.rl_ser_4_layout);
        ser_ll1_tv_time = (TextView) findViewById(R.id.ser_ll1_tv_time);
        ser_ll2_tv_time = (TextView) findViewById(R.id.ser_ll2_tv_time);
        ser_ll3_tv_time = (TextView) findViewById(R.id.ser_ll3_tv_time);
        ser_ll4_tv_time = (TextView) findViewById(R.id.ser_ll4_tv_time);
        //----------------------房屋巡视-------------------
        ll_xs_week_layout = (LinearLayout) findViewById(R.id.ll_xs_week_layout);
        ib_1_sh = (ImageView) findViewById(R.id.ib_1_sh);


        if(AppGlobal.getInstance().getLagStr().equals("en")){
            ib_1_sh.setBackgroundResource(R.mipmap.pro_order_show_en);
        }else{
            ib_1_sh.setBackgroundResource(R.mipmap.pro_order_show);
        }

        ib_2_sh = (ImageView) findViewById(R.id.ib_2_sh);

        if(AppGlobal.getInstance().getLagStr().equals("en")){
            ib_2_sh.setBackgroundResource(R.mipmap.pro_order_show_en);
        }else{
            ib_2_sh.setBackgroundResource(R.mipmap.pro_order_show);
        }


        ll_1_layout = (LinearLayout) findViewById(R.id.ll_1_layout);
        ll_2_layout = (LinearLayout) findViewById(R.id.ll_2_layout);
        rl_1_fwxs_layout = (RelativeLayout) findViewById(R.id.rl_1_fwxs_layout);
        rl_1_sqxj_layout = (RelativeLayout) findViewById(R.id.rl_1_sqxj_layout);
        rl_1_tsqkfk_layout = (RelativeLayout) findViewById(R.id.rl_1_tsqkfk_layout);
        rl_2_fwxs_layout = (RelativeLayout) findViewById(R.id.rl_2_fwxs_layout);
        rl_2_sqxj_layout = (RelativeLayout) findViewById(R.id.rl_2_sqxj_layout);
        rl_2_tsqkfk_layout = (RelativeLayout) findViewById(R.id.rl_2_tsqkfk_layout);
        tv_1_fwxs_date = (TextView) findViewById(R.id.tv_1_fwxs_date);
        tv_1_sqxj_date = (TextView) findViewById(R.id.tv_1_sqxj_date);
        tv_1_tsqkfk_date = (TextView) findViewById(R.id.tv_1_tsqkfk_date);
        tv_2_fwxs_date = (TextView) findViewById(R.id.tv_2_fwxs_date);
        tv_2_sqxj_date = (TextView) findViewById(R.id.tv_2_sqxj_date);
        tv_2_tsqkfk_date = (TextView) findViewById(R.id.tv_2_tsqkfk_date);
        //-----------租赁招租-------
        ll_zl_zz_layout = (LinearLayout) findViewById(R.id.ll_zl_zz_layout);
        tv_zfmc = (TextView) findViewById(R.id.tv_zfmc);
        tv_zhzj = (TextView) findViewById(R.id.tv_zhzj);

        ll_his_week_detail_layout = (LinearLayout) findViewById(R.id.ll_his_week_detail_layout);
        iv_order_show_or_no = (ImageView) findViewById(R.id.iv_order_show_or_no);
        if ("en".equals(isZhorEn)) {
            iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_order_show_en);
        } else {
            iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_order_show);
        }
        iv_order_show_or_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isShow) {
                    isShow = true;
                    ll_his_week_detail_layout.setVisibility(View.VISIBLE);
                    if ("en".equals(isZhorEn)) {
                        iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_btn_hide_en);
                    } else {
                        iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_btn_hide);
                    }

                } else {
                    isShow = false;
                    ll_his_week_detail_layout.setVisibility(View.GONE);
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
        tv_by_tel_phone1 = ((TextView) cardView.findViewById(R.id.tv_by_tel_phone1));
        tv_title_location = (TextView) cardView.findViewById(R.id.tv_title_location);
        iv_detail_chat = (ImageView) cardView.findViewById(R.id.iv_detail_chat);
        iv_detail_chat.setOnClickListener(mOnClickListener);
        iv_detail_phone = (ImageView) cardView.findViewById(R.id.iv_detail_phone);
        iv_detail_phone.setOnClickListener(mOnClickListener);
        civ_detail_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("uid", mOrderNewWeekEntity.getData().getUser().getUid());
                bundle.putString("nickname", mOrderNewWeekEntity.getData().getUser().getNickname());
                bundle.putString("type", "");
                bundle.putString("choose", "0");
                bundle.putString("oid", "");
                bundle.putBoolean("isServer", true);
                ActivityTools.goNextActivity(OrderHisWeekDetailActivity.this, ProDetailActivity.class, bundle);
            }
        });
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_week_bar.getBackView()) {
                finish();
            } else if (view.getId() == R.id.iv_detail_chat) {
                ToastUtil.longToast(OrderHisWeekDetailActivity.this, getString(R.string.nede_ti_user));
                return;
            } else if (view.getId() == R.id.iv_detail_phone) {
                ToastUtil.longToast(OrderHisWeekDetailActivity.this, getString(R.string.nede_ti_user));
                return;
            } else if (view.getId() == R.id.civ_detail_img) {
                gotoProDetailInfo();
            } else if (view.getId() == R.id.rl_ser_1_layout) {
                OrderNewWeekEntity.DataBean.FeedbackBean feedbackBean = mFeedback.get(0);
                Intent intent = new Intent(OrderHisWeekDetailActivity.this, ProWorkFeadBackActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("feedbackBean", feedbackBean);
                intent.putExtra("bundle", bundle);
                intent.putExtra("isHis", true);
                startActivity(intent);
            } else if (view.getId() == R.id.rl_ser_2_layout) {
                OrderNewWeekEntity.DataBean.FeedbackBean feedbackBean = mFeedback.get(1);
                Intent intent = new Intent(OrderHisWeekDetailActivity.this, ProWorkFeadBackActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("feedbackBean", feedbackBean);
                intent.putExtra("bundle", bundle);
                intent.putExtra("isHis", true);
                startActivity(intent);
            } else if (view.getId() == R.id.rl_ser_3_layout) {
                OrderNewWeekEntity.DataBean.FeedbackBean feedbackBean = mFeedback.get(2);
                Intent intent = new Intent(OrderHisWeekDetailActivity.this, ProWorkFeadBackActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("feedbackBean", feedbackBean);
                intent.putExtra("bundle", bundle);
                intent.putExtra("isHis", true);
                startActivity(intent);
            } else if (view.getId() == R.id.rl_ser_4_layout) {
                OrderNewWeekEntity.DataBean.FeedbackBean feedbackBean = mFeedback.get(3);
                Intent intent = new Intent(OrderHisWeekDetailActivity.this, ProWorkFeadBackActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("feedbackBean", feedbackBean);
                intent.putExtra("bundle", bundle);
                intent.putExtra("isHis", true);
                startActivity(intent);
            } else if (view.getId() == R.id.ib_1_sh) {
                if (!is1Flag) {
                    is1Flag = true;

                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        ib_1_sh.setBackgroundResource(R.mipmap.pro_btn_hide_en);
                    }else{
                        ib_1_sh.setBackgroundResource(R.mipmap.pro_btn_hide);
                    }

//                    ib_1_sh.setImageDrawable(getResources().getDrawable(R.mipmap.pro_btn_hide));
                    ll_1_layout.setVisibility(View.VISIBLE);
                } else {
                    is1Flag = false;

                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        ib_1_sh.setBackgroundResource(R.mipmap.pro_order_show_en);
                    }else{
                        ib_1_sh.setBackgroundResource(R.mipmap.pro_order_show);
                    }

//                    ib_1_sh.setImageDrawable(getResources().getDrawable(R.mipmap.pro_btn_show));
                    ll_1_layout.setVisibility(View.GONE);
                }
            } else if (view.getId() == R.id.ib_2_sh) {
                if (!is2Flag) {
                    is2Flag = true;
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        ib_2_sh.setBackgroundResource(R.mipmap.pro_btn_hide_en);
                    }else{
                        ib_2_sh.setBackgroundResource(R.mipmap.pro_btn_hide);
                    }
//                    ib_2_sh.setImageDrawable(getResources().getDrawable(R.mipmap.pro_btn_hide));
                    ll_2_layout.setVisibility(View.VISIBLE);
                } else {
                    is2Flag = false;
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        ib_2_sh.setBackgroundResource(R.mipmap.pro_order_show_en);
                    }else{
                        ib_2_sh.setBackgroundResource(R.mipmap.pro_order_show);
                    }
//                    ib_2_sh.setImageDrawable(getResources().getDrawable(R.mipmap.pro_btn_show));
                    ll_2_layout.setVisibility(View.GONE);
                }
            } else if (view.getId() == R.id.rl_1_fwxs_layout) {
                Intent intent = new Intent(OrderHisWeekDetailActivity.this, ProWorkaFeadBackActivity.class);
                intent.putExtra("isHis", true);
                intent.putExtra("step", "1");
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedbackBean", mFeedbackBean);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            } else if (view.getId() == R.id.rl_1_sqxj_layout) {
                Intent intent = new Intent(OrderHisWeekDetailActivity.this, ProWorkbFeadBackActivity.class);
                intent.putExtra("isHis", true);
                intent.putExtra("step", "1");
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedbackBean", mFeedbackBean);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            } else if (view.getId() == R.id.rl_1_tsqkfk_layout) {
                Intent intent = new Intent(OrderHisWeekDetailActivity.this, ProWorkcFeadBackActivity.class);
                intent.putExtra("isHis", true);
                intent.putExtra("step", "1");
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedbackBean", mFeedbackBean);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            } else if (view.getId() == R.id.rl_2_fwxs_layout) {
                Intent intent = new Intent(OrderHisWeekDetailActivity.this, ProWorkaFeadBackActivity.class);
                intent.putExtra("isHis", true);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback2Bean", mFeedback2Bean);
                intent.putExtra("bundle", bundle);
                intent.putExtra("step", "2");
                startActivity(intent);
            } else if (view.getId() == R.id.rl_2_sqxj_layout) {
                Intent intent = new Intent(OrderHisWeekDetailActivity.this, ProWorkbFeadBackActivity.class);
                intent.putExtra("isHis", true);
                intent.putExtra("step", "2");
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback2Bean", mFeedback2Bean);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            } else if (view.getId() == R.id.rl_2_tsqkfk_layout) {
                Intent intent = new Intent(OrderHisWeekDetailActivity.this, ProWorkcFeadBackActivity.class);
                intent.putExtra("isHis", true);
                intent.putExtra("step", "2");
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback2Bean", mFeedback2Bean);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        }
    };

    private void gotoProDetailInfo() {
        Intent intent = new Intent(OrderHisWeekDetailActivity.this, ProDetailActivity.class);
        Bundle bundle = new Bundle();
        if ("35".equals(type)) {
            bundle.putString("uid", mUserBean1.getUid());//发布订单id
            bundle.putString("nickname", mUserBean1.getNickname());
            bundle.putString("type", "");
            bundle.putString("choose", "");
            bundle.putString("oid", mDate1.getId());//订单id
            bundle.putBoolean("isServer", true);
        } else {
            bundle.putString("uid", mDate.getUid());//发布订单id
            bundle.putString("nickname", mUserBean.getNickname());
            bundle.putString("type", "");
            bundle.putString("choose", "");
            bundle.putString("oid", mDate.getId());//订单id
            bundle.putBoolean("isServer", true);
        }

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
