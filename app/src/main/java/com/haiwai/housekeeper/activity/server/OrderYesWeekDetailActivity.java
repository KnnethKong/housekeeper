package com.haiwai.housekeeper.activity.server;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.entity.ZlOrderEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.imageloader.ImageLoader;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.CityUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.StaticUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.CircleImageView;
import com.haiwai.housekeeper.view.ProServiceView;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.view.TvOrderView;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

/**
 * Created by ihope10 on 2016/11/9.
 */

public class OrderYesWeekDetailActivity extends BaseProActivity implements ProWorkFeadBackActivity.OnRefreshYesWeekDataListener,
        ProWorkaFeadBackActivity.OnARefreshYesWeekDataListener,
        ProWorkbFeadBackActivity.OnBRefreshYesWeekDataListener,
        ProWorkcFeadBackActivity.OnCRefreshYesWeekDataListener {
    private TopViewNormalBar top_week_yes_bar;
    private TextView tv_order_num, tv_order_title, tv_order_location_l, tv_order_time_l, tv_order_state, tv_week_num, btn_textView;
    private CircleImageView civ_detail_img;
    private TextView tv_detial_name, tv_title_tel, tv_by_tel, tv_title_location,tv_by_tel_phone;
    private ImageView iv_detail_chat, iv_detail_phone;
    private TextView tv_sub_zhzj;
    private LinearLayout ll_service_layout, ll_service_o_layout;

    //========================租赁部分==================
    private RelativeLayout rl_zjsq_layout, rl_fwzfzt_layout, rl_jgqd_layout;
    private LinearLayout ll_zjsq_layout, ll_fwzfzt_layout, ll_jgqd_layout;
    private TextView tv_zjsq, tv_fwzfzt, tv_jgqd;
    private TextView tv_zjsq_date, tv_fwzfzt_date, tv_jgqd_date;
    private LinearLayout ib_zjsq, ib_fwzfzt, lb_jgqd;
    //======================巡视部分=========================
    private LinearLayout ll1Layout, ll2Layout;
    private ImageView ib_1_sh, ib_2_sh;
    private RelativeLayout rl_1_fwxs_layout, rl_2_fwxs_layout, rl_1_sqxj_layout, rl_2_sqxj_layout, rl_1_tsqkfk_layout, rl_2_tsqkfk_layout;
    private LinearLayout ll_1_fwxs_layout, ll_2_fwxs_layout, ll_1_sqxj_layout, ll_2_sqxj_layout, ll_1_tsqkfk_layout, ll_2_tsqkfk_layout;
    private TextView tv_1_fwxs_date, tv_2_fwxs_date, tv_1_sqxj_date, tv_2_sqxj_date, tv_1_tsqkfk_date, tv_2_tsqkfk_date;
    private TextView ib_1_fwsh, ib_2_fwsh, ib_1_sqxj, ib_2_sqxj, ib_1_tsqkfk, ib_2_tsqkfk;

    String id = "";
    String uid = "";
    String type = "";//1、房屋巡视2、家政服务3、园艺管理、4、租赁-招租、5、租赁-管理
    private User user;
    //    private OrderWeekEntity mOrderWeekEntity;
    private OrderNewWeekEntity mOrderNewWeekEntity;
    ImageLoader imageLoader;
    private OrderNewWeekEntity.DataBean.DateBean mDate;
    boolean is1Flag = false;
    boolean is2Flag = false;
    private OrderNewWeekEntity.DataBean.FeedbackBean mFeedbackBean;
    private OrderNewWeekEntity.DataBean.FeedbackBean mFeedback2Bean;
    private ZlOrderEntity mZlOrderEntity;
    private OrderNewWeekEntity.DataBean.UserBean mUserBean;
    private ZlOrderEntity.DataBean.BillBean mBill;
    private List<OrderNewWeekEntity.DataBean.FeedbackBean> mFeedback;
    private ZlOrderEntity.DataBean.UserBean mUserBean1;
    private LinearLayout ll_yes_week_detail_layout;
    private ImageView iv_order_show_or_no;
    private boolean isShow = false;
    private List<OrderNewWeekEntity.DataBean.ProblemwBean> mProblemw;
    private List<ZlOrderEntity.DataBean.ProblemwBean> zMProblemw;
    private String isZhorEn = "";
    private ImageView iv_11, iv1_11;
    private ZlOrderEntity.DataBean.DateBean mDate1;


    private TextView tvFirstTime, tvSecondeTime;
    private ScrollView is_showing;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_week_work_yes_layout);
        top_week_yes_bar = (TopViewNormalBar) findViewById(R.id.top_week_yes_bar);
        top_week_yes_bar.setOnBackListener(mOnClickListener);
        top_week_yes_bar.setTitle(getString(R.string.week_ti));
        imageLoader = new ImageLoader(this);

    }

    private boolean isShouBtn = false;

    private void initView() {
        is_showing= (ScrollView) findViewById(R.id.is_showing);
        is_showing.setVisibility(View.GONE);
        tvFirstTime = ((TextView) findViewById(R.id.tv_advice_finish_first_time));
        tvSecondeTime = ((TextView) findViewById(R.id.tv_advice_finish_second_time));
        View topView = findViewById(R.id.ll_content_top_layout);
        initTopView(topView);
        ll_service_layout = (LinearLayout) findViewById(R.id.ll_service_layout);
        ll_service_o_layout = (LinearLayout) findViewById(R.id.ll_service_o_layout);//特殊的房屋和租赁
        tv_sub_zhzj = (TextView) findViewById(R.id.tv_sub_zhzj);//租赁的按钮
        if ("29".equals(type)) {//房屋巡视
            ll_service_layout.setVisibility(View.GONE);
            ll_service_o_layout.setVisibility(View.VISIBLE);
            View xsView = findViewById(R.id.xs_layout);
            xsView.setVisibility(View.VISIBLE);
            initXsView(xsView);
        } else if ("35".equals(type)) {//租赁-招租
            ll_service_layout.setVisibility(View.GONE);
            ll_service_o_layout.setVisibility(View.GONE);
            if (!isShouBtn) {
                tv_sub_zhzj.setVisibility(View.VISIBLE);
            } else {
                tv_sub_zhzj.setVisibility(View.GONE);
            }
            tv_sub_zhzj.setOnClickListener(mOnClickListener);
        } else if ("31".equals(type)) {//租赁-管理
            ll_service_layout.setVisibility(View.GONE);
            ll_service_o_layout.setVisibility(View.VISIBLE);
            View zlView = findViewById(R.id.zl_layout);
            zlView.setVisibility(View.VISIBLE);
            initZlView(zlView);
        } else {//园艺管理与家政服务
            ll_service_layout.setVisibility(View.VISIBLE);
            ll_service_o_layout.setVisibility(View.GONE);
        }
        ll_yes_week_detail_layout = (LinearLayout) findViewById(R.id.ll_yes_week_detail_layout);
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
                    ll_yes_week_detail_layout.setVisibility(View.VISIBLE);
                    if ("en".equals(isZhorEn)) {
                        iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_btn_hide_en);
                    } else {
                        iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_btn_hide);
                    }
                } else {
                    isShow = false;
                    ll_yes_week_detail_layout.setVisibility(View.GONE);
                    if ("en".equals(isZhorEn)) {
                        iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_order_show_en);
                    } else {
                        iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_order_show);
                    }

                }
            }
        });

        if("3".equals(getIntent().getStringExtra("static"))){
            ll_service_layout.setVisibility(View.GONE);
        }else{
            ll_service_layout.setVisibility(View.VISIBLE);
        }

        setFinishTime();
    }

    private void setFinishTime() {
        int month = Calendar.getInstance().get(Calendar.MONTH) + 2;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if (AppGlobal.getInstance().getLagStr().equals("zh")) {
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                if (month == 4 || month == 6 || month == 9 || month == 11) {
                    tvFirstTime.setText("请于" + month + ".01-" + month + ".15完成");
                    tvSecondeTime.setText("请于" + month + ".16-" + month + ".30完成");
                } else if (month == 2) {
                    tvFirstTime.setText("请于2.01-2.15完成");
                    tvSecondeTime.setText("请于2.16-2.29完成");
                } else {
                    tvFirstTime.setText("请于" + month + ".01-" + month + ".15完成");
                    tvSecondeTime.setText("请于" + month + ".16-" + month + ".31完成");
                }
            } else {
                if (month == 4 || month == 6 || month == 9 || month == 11) {
                    tvFirstTime.setText("请于" + month + ".01-" + month + ".15完成");
                    tvSecondeTime.setText("请于" + month + ".16-" + month + ".30完成");
                } else if (month == 2) {
                    tvFirstTime.setText("请于2.01-2.15完成");
                    tvSecondeTime.setText("请于2.16-2.28完成");
                } else {
                    tvFirstTime.setText("请于" + month + ".01-" + month + ".15完成");
                    tvSecondeTime.setText("请于" + month + ".16-" + month + ".31完成");
                }
            }
        } else {
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                if (month == 4 || month == 6 || month == 9 || month == 11) {


                    tvFirstTime.setText("Please finish task\nduring" + month + ".01-" + month + ".15");
                    tvSecondeTime.setText("Please finish task during" + month + ".16-" + month + ".30");
                } else if (month == 2) {
                    tvFirstTime.setText("Please finish task\nduring2.01-2.15");
                    tvSecondeTime.setText("Please finish task\nduring2.16-2.29");
                } else {
                    tvFirstTime.setText("Please finish task\nduring" + month + ".01-" + month + ".15");
                    tvSecondeTime.setText("Please finish task\nduring" + month + ".16-" + month + ".31");
                }
            } else {
                if (month == 4 || month == 6 || month == 9 || month == 11) {
                    tvFirstTime.setText("Please finish task\nduring" + month + ".01-" + month + ".15");
                    tvSecondeTime.setText("Please finish task\nduring" + month + ".16-" + month + ".30");
                } else if (month == 2) {
                    tvFirstTime.setText("Please finish task\nduring2.01-2.15");
                    tvSecondeTime.setText("Please finish task\nduring2.16-2.28");
                } else {
                    tvFirstTime.setText("Please finish task\nduring" + month + ".01-" + month + ".15");
                    tvSecondeTime.setText("Please finish task\nduring" + month + ".16-" + month + ".31");
                }
            }
        }


    }


    private void initZlView(View zlView) {
        rl_zjsq_layout = (RelativeLayout) zlView.findViewById(R.id.rl_zjsq_layout);
        rl_fwzfzt_layout = (RelativeLayout) zlView.findViewById(R.id.rl_fwzfzt_layout);
        rl_jgqd_layout = (RelativeLayout) zlView.findViewById(R.id.rl_jgqd_layout);
        ll_zjsq_layout = (LinearLayout) zlView.findViewById(R.id.ll_zjsq_layout);
        ll_fwzfzt_layout = (LinearLayout) zlView.findViewById(R.id.ll_fwzfzt_layout);
        ll_jgqd_layout = (LinearLayout) zlView.findViewById(R.id.ll_jgqd_layout);
        tv_zjsq = (TextView) zlView.findViewById(R.id.tv_zjsq);
        tv_fwzfzt = (TextView) zlView.findViewById(R.id.tv_fwzfzt);
        tv_jgqd = (TextView) zlView.findViewById(R.id.tv_jgqd);
        tv_zjsq_date = (TextView) zlView.findViewById(R.id.tv_zjsq_date);
        tv_fwzfzt_date = (TextView) zlView.findViewById(R.id.tv_fwzfzt_date);
        tv_jgqd_date = (TextView) zlView.findViewById(R.id.tv_jgqd_date);
        ib_zjsq = (LinearLayout) zlView.findViewById(R.id.ib_zjsq);
        ib_fwzfzt = (LinearLayout) zlView.findViewById(R.id.ib_fwzfzt);
        lb_jgqd = (LinearLayout) zlView.findViewById(R.id.lb_jgqd);

        if("3".equals(getIntent().getStringExtra("static"))){
            findViewById(R.id.zl_layout).setVisibility(View.GONE);
        }else{
            findViewById(R.id.zl_layout).setVisibility(View.VISIBLE);
        }

    }

    private void initXsView(View xsView) {
        ib_1_sh = (ImageView) xsView.findViewById(R.id.ib_1_sh);
        ib_1_sh.setOnClickListener(mOnClickListener);



        if(AppGlobal.getInstance().getLagStr().equals("en")){
            ib_1_sh.setBackgroundResource(R.mipmap.pro_order_show_en);
        }else{
            ib_1_sh.setBackgroundResource(R.mipmap.pro_order_show);
        }


        ib_2_sh = (ImageView) xsView.findViewById(R.id.ib_2_sh);

        if(AppGlobal.getInstance().getLagStr().equals("en")){
            ib_2_sh.setBackgroundResource(R.mipmap.pro_order_show_en);
        }else{
            ib_2_sh.setBackgroundResource(R.mipmap.pro_order_show);
        }


        ib_2_sh.setOnClickListener(mOnClickListener);
        ll1Layout = (LinearLayout) xsView.findViewById(R.id.ll_1_layout);
        ll2Layout = (LinearLayout) xsView.findViewById(R.id.ll_2_layout);
        rl_1_fwxs_layout = (RelativeLayout) xsView.findViewById(R.id.rl_1_fwxs_layout);
        rl_2_fwxs_layout = (RelativeLayout) xsView.findViewById(R.id.rl_2_fwxs_layout);
        rl_1_sqxj_layout = (RelativeLayout) xsView.findViewById(R.id.rl_1_sqxj_layout);
        rl_2_sqxj_layout = (RelativeLayout) xsView.findViewById(R.id.rl_2_sqxj_layout);
        rl_1_tsqkfk_layout = (RelativeLayout) xsView.findViewById(R.id.rl_1_tsqkfk_layout);
        rl_2_tsqkfk_layout = (RelativeLayout) xsView.findViewById(R.id.rl_2_tsqkfk_layout);
        ll_1_fwxs_layout = (LinearLayout) xsView.findViewById(R.id.ll_1_fwxs_layout);
        ll_2_fwxs_layout = (LinearLayout) xsView.findViewById(R.id.ll_2_fwxs_layout);
        ll_1_sqxj_layout = (LinearLayout) xsView.findViewById(R.id.ll_1_sqxj_layout);
        ll_2_sqxj_layout = (LinearLayout) xsView.findViewById(R.id.ll_2_sqxj_layout);
        ll_1_tsqkfk_layout = (LinearLayout) xsView.findViewById(R.id.ll_1_tsqkfk_layout);
        ll_2_tsqkfk_layout = (LinearLayout) xsView.findViewById(R.id.ll_2_tsqkfk_layout);
        tv_1_fwxs_date = (TextView) xsView.findViewById(R.id.tv_1_fwxs_date);
        tv_2_fwxs_date = (TextView) xsView.findViewById(R.id.tv_2_fwxs_date);
        tv_1_sqxj_date = (TextView) xsView.findViewById(R.id.tv_1_sqxj_date);
        tv_2_sqxj_date = (TextView) xsView.findViewById(R.id.tv_2_sqxj_date);
        tv_1_tsqkfk_date = (TextView) xsView.findViewById(R.id.tv_1_tsqkfk_date);
        tv_2_tsqkfk_date = (TextView) xsView.findViewById(R.id.tv_2_tsqkfk_date);
        ib_1_fwsh = (TextView) xsView.findViewById(R.id.ib_1_fwsh);
        ib_2_fwsh = (TextView) xsView.findViewById(R.id.ib_2_fwsh);
        ib_1_sqxj = (TextView) xsView.findViewById(R.id.ib_1_sqxj);
        ib_2_sqxj = (TextView) xsView.findViewById(R.id.ib_2_sqxj);
        ib_1_tsqkfk = (TextView) xsView.findViewById(R.id.ib_1_tsqkfk);
        ib_2_tsqkfk = (TextView) xsView.findViewById(R.id.ib_2_tsqkfk);
        iv_11 = (ImageView) xsView.findViewById(R.id.iv_11);
        iv1_11 = (ImageView) xsView.findViewById(R.id.iv1_11);
        if("3".equals(getIntent().getStringExtra("static"))){
            findViewById(R.id.xs_layout).setVisibility(View.GONE);
        }else{
            findViewById(R.id.xs_layout).setVisibility(View.VISIBLE);
        }
    }


    private void initTopView(View topView) {
        tv_order_num = (TextView) topView.findViewById(R.id.tv_order_num);
        tv_order_title = (TextView) topView.findViewById(R.id.tv_order_title);
        tv_order_location_l = (TextView) topView.findViewById(R.id.tv_order_location_l);
        tv_order_time_l = (TextView) topView.findViewById(R.id.tv_order_time_l);
        tv_order_state = (TextView) topView.findViewById(R.id.tv_order_state);
        tv_week_num = (TextView) topView.findViewById(R.id.tv_week_num);
        topView.findViewById(R.id.ll_yes_week).setVisibility(View.VISIBLE);
        btn_textView = (TextView) topView.findViewById(R.id.btn_textView);
        civ_detail_img = (CircleImageView) topView.findViewById(R.id.civ_detail_img);
        civ_detail_img.setOnClickListener(mOnClickListener);
        tv_detial_name = (TextView) topView.findViewById(R.id.tv_detial_name);
        tv_title_tel = (TextView) topView.findViewById(R.id.tv_title_tel);
        tv_by_tel = (TextView) topView.findViewById(R.id.tv_by_tel);
        tv_by_tel_phone = ((TextView) topView.findViewById(R.id.tv_by_tel_phone));
        tv_title_location = (TextView) topView.findViewById(R.id.tv_title_location);
        iv_detail_chat = (ImageView) topView.findViewById(R.id.iv_detail_chat);
        iv_detail_chat.setOnClickListener(mOnClickListener);
        iv_detail_phone = (ImageView) topView.findViewById(R.id.iv_detail_phone);
        iv_detail_phone.setOnClickListener(mOnClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        initView();
    }

    private void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        ProWorkFeadBackActivity.setOnRefreshYesWeekDataListener(this);
        ProWorkaFeadBackActivity.setOnARefreshYesWeekDataListener(this);
        ProWorkbFeadBackActivity.setOnBRefreshYesWeekDataListener(this);
        ProWorkcFeadBackActivity.setOnCRefreshYesWeekDataListener(this);
        user = AppGlobal.getInstance().getUser();
        if (user != null) {
            uid = user.getUid();
        }
        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");//1、房屋巡视2、家政服务3、园艺管理、4、租赁-招租、5、租赁-管理
        initNetData(id, type);
    }

    public String datae(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日",
                Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }
    private void initNetData(String id, String type) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        LoadDialog.showProgressDialog(this);
        map.put("secret_key", SPUtils.getString(OrderYesWeekDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        if ("31".equals(type)) {
             Calendar c = Calendar.getInstance();
            String      mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
            String     mMonth = String.valueOf(c.get(Calendar.MONTH) +2);// 获取当前月份
            String    mDay = "11";// 获取当前日份的日期号码
            String date =mYear+"年"+mMonth + "月" + mDay + "日";
           // map.put("monthtime", datae(date));
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderYesWeekDetailActivity.this, Contants.guan_detail, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(">>>管理订单详情>>>" + response);
                    int code = JsonUtils.getJsonInt(response, "status");
                  //  LoadDialog.closeProgressDialog();
                    mHandler.sendEmptyMessageDelayed(100,1000);
                    is_showing.setVisibility(View.VISIBLE);
                    if (code == 200) {
                        mZlOrderEntity = new Gson().fromJson(response, ZlOrderEntity.class);
                        bindDataToGuanView(mZlOrderEntity);
                    } else {
                        ToastUtil.shortToast(OrderYesWeekDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }

                }
            }));
        } else if ("35".equals(type)) {
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderYesWeekDetailActivity.this, Contants.zhao_detail, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(">>>招租订单详情>>>" + response);
                    //LoadDialog.closeProgressDialog();
                    mHandler.sendEmptyMessageDelayed(100,1000);
                    is_showing.setVisibility(View.VISIBLE);
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        mOrderNewWeekEntity = new Gson().fromJson(response, OrderNewWeekEntity.class);
                        bindDataToView(mOrderNewWeekEntity);
                    } else {
                        ToastUtil.shortToast(OrderYesWeekDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }

                }
            }));
        } else {
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderYesWeekDetailActivity.this, Contants.self_detail, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(">>>周期订单详情>>>" + response);
                    mHandler.sendEmptyMessageDelayed(100,1000);
                    is_showing.setVisibility(View.VISIBLE);
                    int code = JsonUtils.getJsonInt(response, "status");

                    if (code == 200) {
                        mOrderNewWeekEntity = new Gson().fromJson(response, OrderNewWeekEntity.class);
                        bindDataToView(mOrderNewWeekEntity);
                    } else {
                        ToastUtil.shortToast(OrderYesWeekDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }

                }
            }));
        }
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
    private void bindDataToGuanView(ZlOrderEntity mZlOrderEntity) {
        if (mZlOrderEntity != null) {
            mBill = mZlOrderEntity.getData().getBill();
            List<ZlOrderEntity.DataBean.BillMonthBean> bill_month = mZlOrderEntity.getData().getBill_month();
            mDate1 = mZlOrderEntity.getData().getDate();
            final ZlOrderEntity.DataBean.HousBean hous = mZlOrderEntity.getData().getHous();
            zMProblemw = mZlOrderEntity.getData().getProblemw();
            mUserBean1 = mZlOrderEntity.getData().getUser();
            tv_order_num.setText(getString(R.string.str_order_code) + mDate1.getOrder_id());
            tv_order_title.setText(StaticUtils.getWeekTypeStr(mDate1.getType(), isZhorEn));
            if (hous != null) {
                Map<String, String> map = new HashMap<>();
                map.put("secret_key", SPUtils.getString(OrderYesWeekDetailActivity.this, "secret", ""));
                MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderYesWeekDetailActivity.this, Contants.city_lst, map, null, new Response.Listener<String>() {
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
                            ToastUtil.longToast(OrderYesWeekDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                }));

                tv_order_location_l.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intentMap = new Intent(OrderYesWeekDetailActivity.this, MapBoxMapActivity.class);
                        intentMap.putExtra("isMap", true);
                        intentMap.putExtra("lat", hous == null ? "" : hous.getLat());
                        intentMap.putExtra("lng", hous == null ? "" : hous.getLongX());
                        startActivity(intentMap);
                    }
                });

                tv_by_tel.setText(hous.getAlternate_contact());
                tv_by_tel_phone.setText(hous.getAlternate_contact_number());
            }

            Date date = new Date(Long.valueOf(mDate1.getMonthtime())*1000L);
            Calendar calendar   =   new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.DATE,calendar.getActualMaximum(Calendar.DAY_OF_MONTH)-1);//把日期往后增加一天.整数往后推,负数往前移动
            date=calendar.getTime();   //这个时间就是日期往后推一天的结果

            tv_order_time_l.setText(TimeUtils.getNewDate(mDate1.getMonthtime())+"-"+TimeUtils.getNewDate2(date));

            tv_order_state.setText(StaticUtils.getWeekStaticStr(OrderYesWeekDetailActivity.this, mDate1.getStaticX()));

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
                civ_detail_img.setImageResource(R.mipmap.user_icon);
            }
            tv_detial_name.setText(mUserBean1.getNickname());
            tv_title_tel.setText(mUserBean1.getMobile());
            tv_title_location.setText(mUserBean1.getAddress());
            Log.i("rentInformation----", mBill.getIs_static() + "");
            Log.i("rentInformation----", mBill.toString());
            if (mBill == null || mBill.getRent() == null || Double.valueOf(mBill.getRent() + "") == 0 || mBill.getRent().equals("")) {//
                rl_zjsq_layout.setBackgroundResource(R.color.green);
                ll_zjsq_layout.setVisibility(View.GONE);
                ib_zjsq.setOnClickListener(mOnClickListener);
            } else {
                rl_zjsq_layout.setBackgroundResource(R.color.white);
                rl_zjsq_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(OrderYesWeekDetailActivity.this, RentNewMoneyActivity.class);
                        intent.putExtra("moy", mBill.getRent());
                        intent.putExtra("isHis", true);
                        startActivity(intent);
                    }
                });
                ll_zjsq_layout.setVisibility(View.VISIBLE);
                tv_zjsq_date.setText(TimeUtils.getDate(mBill.getWtime1()));
                findViewById(R.id.ll_zjsq_layout).setVisibility(View.VISIBLE);
                ib_zjsq.setVisibility(View.GONE);
                if (TextUtils.isEmpty(mBill.getHome_remark()) || TextUtils.isEmpty(mBill.getTenant_remark())) {
                    rl_fwzfzt_layout.setBackgroundResource(R.color.green);
                    ll_fwzfzt_layout.setVisibility(View.GONE);
                    ib_fwzfzt.setOnClickListener(mOnClickListener);
                } else {
                    rl_fwzfzt_layout.setBackgroundResource(R.color.white);
                    rl_fwzfzt_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(OrderYesWeekDetailActivity.this, RentStateActivity.class);
                            intent.putExtra("wrent", mBill.getWrent());
                            intent.putExtra("home_remark", mBill.getHome_remark());
                            intent.putExtra("tenant_remark", mBill.getTenant_remark());
                            intent.putExtra("tenant_static", mBill.getTenant_static());
                            intent.putExtra("isHis", true);
                            startActivity(intent);
                        }
                    });
                    ll_fwzfzt_layout.setVisibility(View.VISIBLE);
                    tv_fwzfzt_date.setText(TimeUtils.getDate(mBill.getWtime2()));
                    ib_fwzfzt.setVisibility(View.GONE);
                    if (TextUtils.isEmpty(mBill.getPrice_remark())) {
                        rl_jgqd_layout.setBackgroundResource(R.color.green);
                        ll_jgqd_layout.setVisibility(View.GONE);
                        lb_jgqd.setOnClickListener(mOnClickListener);
                    } else {
                        rl_jgqd_layout.setBackgroundResource(R.color.white);
                        rl_jgqd_layout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(OrderYesWeekDetailActivity.this, RentPriceActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("mBill", mBill);
                                intent.putExtra("bundle", bundle);
                                intent.putExtra("isHis", true);
                                startActivity(intent);
                            }
                        });
                        ll_jgqd_layout.setVisibility(View.VISIBLE);
                        tv_jgqd_date.setText(TimeUtils.getDate(mBill.getWtime3()));
                        lb_jgqd.setVisibility(View.GONE);
                    }

                }
            }


            if (zMProblemw != null && zMProblemw.size() > 0) {
                ll_yes_week_detail_layout.removeAllViews();
                for (int i = 0; i < zMProblemw.size(); i++) {
                    boolean isCar = false;
                    TvOrderView tvOrderView = new TvOrderView(this);
                    StringBuilder sb = new StringBuilder();
                    if (zMProblemw.get(i).getProblem() != null && zMProblemw.get(i).getProblem().size() > 0) {
                        for (int j = 0; j < zMProblemw.get(i).getProblem().size(); j++) {
                            String money = zMProblemw.get(i).getProblem().get(j).getZhi();
                            String id2 = zMProblemw.get(i).getProblem().get(j).getId();
                            Log.i("information----",id2+"--"+zMProblemw.get(i).getValue()+"---"+money);
                            if (!money.equals("")) {
                                if (id2.equals("49")) {
                                    String carValue;
                                    if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                        carValue = zMProblemw.get(i).getProblem().get(j).getYvalue();
                                    } else {
                                        carValue = zMProblemw.get(i).getProblem().get(j).getValue();
                                    }
                                    if (id2.equals("49") && zMProblemw.get(i).getProblem().get(j).getZhi().equals("")) {
                                        isCar = true;
                                    }
                                    String carDa = carValue.substring(0, carValue.indexOf("[") + 1) +
                                            money
                                            + carValue.substring(carValue.indexOf("]"), carValue.length());
                                    sb = sb.append(carDa).append("\n");
                                }else if(id2.equals("110")){
                                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                                        sb = sb.append(money).append("sq ft")
                                                .append("\n");
                                    }else{
                                        sb = sb.append(money).append("平方英尺")
                                                .append("\n");
                                    }
                                } else {
                                    if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                        sb = sb.append(money).append(zMProblemw.get(i).getProblem().get(j).getYvalue())

                                                .append("\n");
                                    } else {
                                        sb = sb.append(money).append(zMProblemw.get(i).getProblem().get(j).getValue())
                                                .append("\n");
                                    }
                                }
                            } else {

                                if (id2.equals("66")) {
                                    if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                        money = "I am not sure";
                                    } else {
                                        money = "我不确定";
                                    }
                                    sb = sb.append(money).append("\n");
                                } else {
                                    if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                        sb = sb.append(zMProblemw.get(i).getProblem().get(j).getYvalue())
                                                .append("\n");
                                    } else {
                                        sb = sb.append(zMProblemw.get(i).getProblem().get(j).getValue())
                                                .append("\n");
                                    }

                                }

                            }

                        }
                        if (sb.toString().endsWith("\n")) {
                            sb = sb.deleteCharAt(sb.lastIndexOf("\n"));
                        }
                    }
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        tvOrderView.setViewText(zMProblemw.get(i).getYvalue(), sb.toString());
//                    .addView(tvOrderView);
                    }else{
                        tvOrderView.setViewText(zMProblemw.get(i).getValue(), sb.toString());
//                    .addView(tvOrderView);
                    }

                    if (!isCar) {
                        ll_yes_week_detail_layout.addView(tvOrderView);
                    }
                }
            }

        }
//            rl_zjsq_layout
//            rl_fwzfzt_layout
//            rl_jgqd_layout
//            ll_zjsq_layout
//            ll_fwzfzt_layout
//            ll_jgqd_layout
//            tv_zjsq
//            tv_fwzfzt
//            tv_jgqd
//
//
//
//            ib_zjsq
//            ib_fwzfzt
//            lb_jgqd
    }

    private void bindDataToView(OrderNewWeekEntity orderWeekEntity) {
        mFeedback = orderWeekEntity.getData().getFeedback();
        mDate = orderWeekEntity.getData().getDate();
        final OrderNewWeekEntity.DataBean.HousBean hous = orderWeekEntity.getData().getHous();
        OrderNewWeekEntity.DataBean.OfferBean offer = orderWeekEntity.getData().getOffer();
        mUserBean = orderWeekEntity.getData().getUser();
        mProblemw = orderWeekEntity.getData().getProblemw();
        tv_order_num.setText(getString(R.string.str_order_code) + mDate.getOrder_id());
        tv_order_title.setText(StaticUtils.getWeekTypeStr(mDate.getType(), isZhorEn));
        if (hous != null) {
            if (AppGlobal.getInstance().getLagStr().equals("en")) {
                tv_order_location_l.setText("CanadaBritish ColumbiaAnmore " + hous.getAddress_info());
            } else {
                tv_order_location_l.setText("加拿大哥伦比亚市 " + hous.getAddress_info());
            }
            tv_by_tel.setText(hous.getAlternate_contact());
            tv_by_tel_phone.setText(hous.getAlternate_contact_number());

        }
        tv_order_location_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMap = new Intent(OrderYesWeekDetailActivity.this, MapBoxMapActivity.class);
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
        tv_order_state.setText(StaticUtils.getWeekStaticStr(OrderYesWeekDetailActivity.this, mDate.getStaticX()));
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
        tv_title_location.setText(mUserBean.getAddress());
        type = mDate.getType();
        if ("29".equals(type)) {
            mFeedbackBean = mFeedback.get(0);
            if ("0".equals(mFeedbackBean.getIs_wan())) {//第一次巡视未完成
                ll1Layout.setVisibility(View.VISIBLE);
                ll2Layout.setVisibility(View.GONE);
                is1Flag = false;
                is2Flag = false;
                iv_11.setVisibility(View.GONE);
                iv1_11.setVisibility(View.GONE);
                if (TextUtils.isEmpty(mFeedbackBean.getContent1())) {
                    rl_1_fwxs_layout.setBackgroundResource(R.color.green);
                    ll_1_fwxs_layout.setVisibility(View.GONE);
                    if(!"3".equals(getIntent().getStringExtra("static"))){
                        ib_1_fwsh.setOnClickListener(mOnClickListener);
                    }


//                rl_2_fwxs_layout
//                rl_1_sqxj_layout
//                rl_2_sqxj_layout
//                rl_1_tsqkfk_layout
//                rl_2_tsqkfk_layout
//                ll_2_fwxs_layout
//                ll_1_sqxj_layout
//                ll_2_sqxj_layout
//                ll_1_tsqkfk_layout
//                ll_2_tsqkfk_layout
//                tv_1_fwxs_date
//                tv_2_fwxs_date
//                tv_1_sqxj_date
//                tv_2_sqxj_date
//                tv_1_tsqkfk_date
//                tv_2_tsqkfk_date
//                ib_2_fwsh
//                ib_1_sqxj
//                ib_2_sqxj
//                ib_1_tsqkfk ;
//                ib_2_tsqkfk
                } else {
                    rl_1_fwxs_layout.setBackgroundResource(R.color.white);
                    rl_1_fwxs_layout.setOnClickListener(mmOnClickListener);
                    ll_1_fwxs_layout.setVisibility(View.VISIBLE);
                    tv_1_fwxs_date.setText(TimeUtils.getDate(mFeedbackBean.getWtime1()));
                    ib_1_fwsh.setVisibility(View.GONE);
//                rl_2_fwxs_layout
//                rl_1_sqxj_layout
//                rl_2_sqxj_layout
//                rl_1_tsqkfk_layout
//                rl_2_tsqkfk_layout
//                ll_2_fwxs_layout
//                ll_1_sqxj_layout
//                ll_2_sqxj_layout
//                ll_1_tsqkfk_layout
//                ll_2_tsqkfk_layout
//                tv_1_fwxs_date
//                tv_2_fwxs_date
//                tv_1_sqxj_date
//                tv_2_sqxj_date
//                tv_1_tsqkfk_date
//                tv_2_tsqkfk_date

//                ib_2_fwsh
//                ib_1_sqxj
//                ib_2_sqxj
//                ib_1_tsqkfk ;
//                ib_2_tsqkfk

                    //整理信件
                    if (TextUtils.isEmpty(mFeedbackBean.getContent2())) {
                        rl_1_sqxj_layout.setBackgroundResource(R.color.green);
                        ll_1_sqxj_layout.setVisibility(View.GONE);
                        ib_1_sqxj.setOnClickListener(mOnClickListener);
//                    rl_1_fwxs_layout
//                    ll_1_fwxs_layout
//                    ib_1_fwsh
//                rl_2_fwxs_layout

//                rl_2_sqxj_layout
//                rl_1_tsqkfk_layout
//                rl_2_tsqkfk_layout
//                ll_2_fwxs_layout

//                ll_2_sqxj_layout
//                ll_1_tsqkfk_layout
//                ll_2_tsqkfk_layout
//                tv_1_fwxs_date
//                tv_2_fwxs_date
//                tv_1_sqxj_date
//                tv_2_sqxj_date
//                tv_1_tsqkfk_date
//                tv_2_tsqkfk_date

//                ib_2_fwsh

//                ib_2_sqxj
//                ib_1_tsqkfk ;
//                ib_2_tsqkfk
                    } else {
                        rl_1_sqxj_layout.setBackgroundResource(R.color.white);
                        rl_1_sqxj_layout.setOnClickListener(mmOnClickListener);
                        ll_1_sqxj_layout.setVisibility(View.VISIBLE);
                        tv_1_sqxj_date.setText(TimeUtils.getDate(mFeedbackBean.getWtime2()));
                        ib_1_sqxj.setVisibility(View.GONE);
//                    rl_1_fwxs_layout
//                    ll_1_fwxs_layout
//                    ib_1_fwsh
//                rl_2_fwxs_layout

//                rl_2_sqxj_layout
//                rl_1_tsqkfk_layout
//                rl_2_tsqkfk_layout
//                ll_2_fwxs_layout

//                ll_2_sqxj_layout
//                ll_1_tsqkfk_layout
//                ll_2_tsqkfk_layout
//                tv_1_fwxs_date
//                tv_2_fwxs_date
//
//                tv_2_sqxj_date
//                tv_1_tsqkfk_date
//                tv_2_tsqkfk_date

//                ib_2_fwsh

//                ib_2_sqxj
//                ib_1_tsqkfk ;
//                ib_2_tsqkfk

                        //特殊反馈
                        if (TextUtils.isEmpty(mFeedbackBean.getContent3())) {
                            rl_1_tsqkfk_layout.setBackgroundResource(R.color.green);
                            ll_1_tsqkfk_layout.setVisibility(View.GONE);
                            ib_1_tsqkfk.setOnClickListener(mOnClickListener);
//                    rl_1_fwxs_layout
//                    ll_1_fwxs_layout
//                    ib_1_fwsh
//                rl_2_fwxs_layout
//                rl_1_sqxj_layout
//                rl_2_sqxj_layout

//                rl_2_tsqkfk_layout
//                ll_2_fwxs_layout
//                ll_1_sqxj_layout
//                ll_2_sqxj_layout

//                ll_2_tsqkfk_layout
//                tv_1_fwxs_date
//                tv_2_fwxs_date
//                tv_1_sqxj_date
//                tv_2_sqxj_date
//                tv_1_tsqkfk_date
//                tv_2_tsqkfk_date

//                ib_2_fwsh
//                ib_1_sqxj
//                ib_2_sqxj
//                ib_2_tsqkfk
                        } else {
                            rl_1_tsqkfk_layout.setBackgroundResource(R.color.white);
                            rl_1_tsqkfk_layout.setOnClickListener(mmOnClickListener);
                            ll_1_tsqkfk_layout.setVisibility(View.VISIBLE);
                            tv_1_tsqkfk_date.setText(TimeUtils.getDate(mFeedbackBean.getWtime3()));
                            ib_1_tsqkfk.setVisibility(View.GONE);
//                    rl_1_fwxs_layout
//                    ll_1_fwxs_layout
//                    ib_1_fwsh
//                rl_2_fwxs_layout
//                rl_1_sqxj_layout
//                rl_2_sqxj_layout

//                rl_2_tsqkfk_layout
//                ll_2_fwxs_layout
//                ll_1_sqxj_layout
//                ll_2_sqxj_layout

//                ll_2_tsqkfk_layout
//                tv_1_fwxs_date
//                tv_2_fwxs_date
//                tv_1_sqxj_date
//                tv_2_sqxj_date

//                tv_2_tsqkfk_date

//                ib_2_fwsh
//                ib_1_sqxj
//                ib_2_sqxj
//                ib_2_tsqkfk
                        }
                    }
                }
            } else if ("1".equals(mFeedbackBean.getIs_wan())) {//第一次巡视完成一次
                ll1Layout.setVisibility(View.GONE);
                ll2Layout.setVisibility(View.VISIBLE);
                is1Flag = true;
                is2Flag = true;
                iv_11.setVisibility(View.VISIBLE);
                iv1_11.setVisibility(View.GONE);
                rl_1_fwxs_layout.setBackgroundResource(R.color.white);
                rl_1_fwxs_layout.setOnClickListener(mmOnClickListener);
                ll_1_fwxs_layout.setVisibility(View.VISIBLE);
                tv_1_fwxs_date.setText(TimeUtils.getDate(mFeedbackBean.getWtime1()));
                ib_1_fwsh.setVisibility(View.GONE);

                rl_1_sqxj_layout.setBackgroundResource(R.color.white);
                rl_1_sqxj_layout.setOnClickListener(mmOnClickListener);
                ll_1_sqxj_layout.setVisibility(View.VISIBLE);
                tv_1_sqxj_date.setText(TimeUtils.getDate(mFeedbackBean.getWtime2()));
                ib_1_sqxj.setVisibility(View.GONE);

                rl_1_tsqkfk_layout.setBackgroundResource(R.color.white);
                rl_1_tsqkfk_layout.setOnClickListener(mmOnClickListener);
                ll_1_tsqkfk_layout.setVisibility(View.VISIBLE);
                tv_1_tsqkfk_date.setText(TimeUtils.getDate(mFeedbackBean.getWtime3()));
                ib_1_tsqkfk.setVisibility(View.GONE);


                mFeedback2Bean = mFeedback.get(1);
                if ("0".equals(mFeedback2Bean.getIs_wan())) {//第二次巡视一次没完成
                    ll1Layout.setVisibility(View.GONE);
                    ll2Layout.setVisibility(View.VISIBLE);
                    is1Flag = true;
                    is2Flag = true;
                    if (TextUtils.isEmpty(mFeedback2Bean.getContent1())) {
                        rl_2_fwxs_layout.setBackgroundResource(R.color.green);
                        ll_2_fwxs_layout.setVisibility(View.GONE);
                        ib_2_fwsh.setOnClickListener(mOnClickListener);
//                rl_2_fwxs_layout
//                rl_1_sqxj_layout
//                rl_2_sqxj_layout
//                rl_1_tsqkfk_layout
//                rl_2_tsqkfk_layout
//                ll_2_fwxs_layout
//                ll_1_sqxj_layout
//                ll_2_sqxj_layout
//                ll_1_tsqkfk_layout
//                ll_2_tsqkfk_layout
//                tv_1_fwxs_date
//                tv_2_fwxs_date
//                tv_1_sqxj_date
//                tv_2_sqxj_date
//                tv_1_tsqkfk_date
//                tv_2_tsqkfk_date
//                ib_2_fwsh
//                ib_1_sqxj
//                ib_2_sqxj
//                ib_1_tsqkfk ;
//                ib_2_tsqkfk
                    } else {
                        rl_2_fwxs_layout.setBackgroundResource(R.color.white);
                        rl_2_fwxs_layout.setOnClickListener(mmOnClickListener);
                        ll_2_fwxs_layout.setVisibility(View.VISIBLE);
                        tv_2_fwxs_date.setText(TimeUtils.getDate(mFeedback2Bean.getWtime1()));
                        ib_2_fwsh.setVisibility(View.GONE);
//                rl_2_fwxs_layout
//                rl_1_sqxj_layout
//                rl_2_sqxj_layout
//                rl_1_tsqkfk_layout
//                rl_2_tsqkfk_layout
//                ll_2_fwxs_layout
//                ll_1_sqxj_layout
//                ll_2_sqxj_layout
//                ll_1_tsqkfk_layout
//                ll_2_tsqkfk_layout
//                tv_1_fwxs_date
//                tv_2_fwxs_date
//                tv_1_sqxj_date
//                tv_2_sqxj_date
//                tv_1_tsqkfk_date
//                tv_2_tsqkfk_date

//                ib_2_fwsh
//                ib_1_sqxj
//                ib_2_sqxj
//                ib_1_tsqkfk ;
//                ib_2_tsqkfk
                        //整理信件
                        if (TextUtils.isEmpty(mFeedback2Bean.getContent2())) {
                            rl_2_sqxj_layout.setBackgroundResource(R.color.green);
                            ll_2_sqxj_layout.setVisibility(View.GONE);
                            ib_2_sqxj.setOnClickListener(mOnClickListener);
//                    rl_1_fwxs_layout
//                    ll_1_fwxs_layout
//                    ib_1_fwsh
//                rl_2_fwxs_layout

//                rl_2_sqxj_layout
//                rl_1_tsqkfk_layout
//                rl_2_tsqkfk_layout
//                ll_2_fwxs_layout

//                ll_2_sqxj_layout
//                ll_1_tsqkfk_layout
//                ll_2_tsqkfk_layout
//                tv_1_fwxs_date
//                tv_2_fwxs_date
//                tv_1_sqxj_date
//                tv_2_sqxj_date
//                tv_1_tsqkfk_date
//                tv_2_tsqkfk_date

//                ib_2_fwsh

//                ib_2_sqxj
//                ib_1_tsqkfk ;
//                ib_2_tsqkfk
                        } else {
                            rl_2_sqxj_layout.setBackgroundResource(R.color.white);
                            rl_2_sqxj_layout.setOnClickListener(mmOnClickListener);
                            ll_2_sqxj_layout.setVisibility(View.VISIBLE);
                            tv_2_sqxj_date.setText(TimeUtils.getDate(mFeedback2Bean.getWtime2()));
                            ib_2_sqxj.setVisibility(View.GONE);
//                    rl_1_fwxs_layout
//                    ll_1_fwxs_layout
//                    ib_1_fwsh
//                rl_2_fwxs_layout

//                rl_2_sqxj_layout
//                rl_1_tsqkfk_layout
//                rl_2_tsqkfk_layout
//                ll_2_fwxs_layout

//                ll_2_sqxj_layout
//                ll_1_tsqkfk_layout
//                ll_2_tsqkfk_layout
//                tv_1_fwxs_date
//                tv_2_fwxs_date
//
//                tv_2_sqxj_date
//                tv_1_tsqkfk_date
//                tv_2_tsqkfk_date

//                ib_2_fwsh

//                ib_2_sqxj
//                ib_1_tsqkfk ;
//                ib_2_tsqkfk
                            //特殊反馈
                            if (TextUtils.isEmpty(mFeedback2Bean.getContent3())) {
                                rl_2_tsqkfk_layout.setBackgroundResource(R.color.green);
                                ll_2_tsqkfk_layout.setVisibility(View.GONE);
                                ib_2_tsqkfk.setOnClickListener(mOnClickListener);
//                    rl_1_fwxs_layout
//                    ll_1_fwxs_layout
//                    ib_1_fwsh
//                rl_2_fwxs_layout
//                rl_1_sqxj_layout
//                rl_2_sqxj_layout

//                rl_2_tsqkfk_layout
//                ll_2_fwxs_layout
//                ll_1_sqxj_layout
//                ll_2_sqxj_layout

//                ll_2_tsqkfk_layout
//                tv_1_fwxs_date
//                tv_2_fwxs_date
//                tv_1_sqxj_date
//                tv_2_sqxj_date
//                tv_1_tsqkfk_date
//                tv_2_tsqkfk_date

//                ib_2_fwsh
//                ib_1_sqxj
//                ib_2_sqxj
//                ib_2_tsqkfk
                            } else {
                                rl_2_tsqkfk_layout.setBackgroundResource(R.color.white);
                                rl_2_tsqkfk_layout.setOnClickListener(mmOnClickListener);
                                ll_2_tsqkfk_layout.setVisibility(View.VISIBLE);
                                tv_2_tsqkfk_date.setText(TimeUtils.getDate(mFeedback2Bean.getWtime3()));
                                ib_2_tsqkfk.setVisibility(View.GONE);
//                    rl_1_fwxs_layout
//                    ll_1_fwxs_layout
//                    ib_1_fwsh
//                rl_2_fwxs_layout
//                rl_1_sqxj_layout
//                rl_2_sqxj_layout

//                rl_2_tsqkfk_layout
//                ll_2_fwxs_layout
//                ll_1_sqxj_layout
//                ll_2_sqxj_layout

//                ll_2_tsqkfk_layout
//                tv_1_fwxs_date
//                tv_2_fwxs_date
//                tv_1_sqxj_date
//                tv_2_sqxj_date

//                tv_2_tsqkfk_date

//                ib_2_fwsh
//                ib_1_sqxj
//                ib_2_sqxj
//                ib_2_tsqkfk
                            }
                        }
                    }

                } else if ("1".equals(mFeedback2Bean.getIs_wan())) {//第二次巡视完成
                    ll1Layout.setVisibility(View.GONE);
                    ll2Layout.setVisibility(View.GONE);
                    is1Flag = true;
                    is2Flag = false;
                    iv_11.setVisibility(View.VISIBLE);
                    iv1_11.setVisibility(View.VISIBLE);
                    rl_2_fwxs_layout.setBackgroundResource(R.color.white);
                    rl_2_fwxs_layout.setOnClickListener(mmOnClickListener);
                    ll_2_fwxs_layout.setVisibility(View.VISIBLE);
                    tv_2_fwxs_date.setText(TimeUtils.getDate(mFeedback2Bean.getWtime1()));
                    ib_2_fwsh.setVisibility(View.GONE);

                    rl_2_sqxj_layout.setBackgroundResource(R.color.white);
                    rl_2_sqxj_layout.setOnClickListener(mmOnClickListener);
                    ll_2_sqxj_layout.setVisibility(View.VISIBLE);
                    tv_2_sqxj_date.setText(TimeUtils.getDate(mFeedback2Bean.getWtime2()));
                    ib_2_sqxj.setVisibility(View.GONE);

                    rl_2_tsqkfk_layout.setBackgroundResource(R.color.white);
                    rl_2_tsqkfk_layout.setOnClickListener(mmOnClickListener);
                    ll_2_tsqkfk_layout.setVisibility(View.VISIBLE);
                    tv_2_tsqkfk_date.setText(TimeUtils.getDate(mFeedback2Bean.getWtime3()));
                    ib_2_tsqkfk.setVisibility(View.GONE);
                }
            }

        } else {
            if (mFeedback != null) {
                ll_service_layout.removeAllViews();
                boolean flag = true;
                for (int i = 0; i < mFeedback.size(); i++) {
                    String is_wan = mFeedback.get(i).getIs_wan();
                    if ("0".equals(is_wan)) {//未完成
                        ProServiceView pv = new ProServiceView(this);
                        int a = i + 1;
                        if (flag) {
                            flag = false;
                            pv.showDemo(ProServiceView.TYPETWO, getRoomNum(a), getComTime(a, 1), "", "03.16-03.24");
                            ll_service_layout.addView(pv);
                        } else {
                            pv.showDemo(ProServiceView.TYPETHREE, getRoomNum(a), getComTime(a, 1), getComTime(a, 1), getComTime(a, 1));
                            ll_service_layout.addView(pv);
                        }
                    } else {
                        ProServiceView pv = new ProServiceView(this);
                        int a = i + 1;
                        pv.showDemo(ProServiceView.TYPEONE, getRoomNum(a), getComTime(a, 2), getComTime(a, 2), getComTime(a, 2));
                        ll_service_layout.addView(pv);
                    }

                }
            }
            for (int i = 0; i < ll_service_layout.getChildCount(); i++) {
                ProServiceView psv = (ProServiceView) ll_service_layout.getChildAt(i);
                final int finalI = i;
                psv.getClickView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gotoFeedBack(finalI);
                    }
                });
                psv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String is_wan = mFeedback.get(finalI).getIs_wan();
                        if ("1".equals(is_wan)) {
                            gotoHisFeedBack(finalI);
                        }
                    }
                });
            }

        }

        if (mProblemw != null && mProblemw.size() > 0) {
            ll_yes_week_detail_layout.removeAllViews();
            for (int i = 0; i < mProblemw.size(); i++) {
                boolean isCar = false;
                TvOrderView tvOrderView = new TvOrderView(this);
                StringBuilder sb = new StringBuilder();
                if (mProblemw.get(i).getProblem() != null && mProblemw.get(i).getProblem().size() > 0) {
                    for (int j = 0; j < mProblemw.get(i).getProblem().size(); j++) {
                        String money = mProblemw.get(i).getProblem().get(j).getZhi();
                        String id3 = mProblemw.get(i).getProblem().get(j).getId();
                        if(id3==null){
                           id3="";
                        }
                        Log.i("information----",id3+"--"+mProblemw.get(i).getValue()+"---"+money);
                        if (!money.equals("")) {
                            if (id3.equals("49")) {
                                String carValue;
                                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                    carValue = mProblemw.get(i).getProblem().get(j).getYvalue();
                                } else {
                                    carValue = mProblemw.get(i).getProblem().get(j).getValue();
                                }
                                if (id3.equals("49") && mProblemw.get(i).getProblem().get(j).getZhi().equals("")) {
                                    isCar = true;
                                }
                                String carDa = carValue.substring(0, carValue.indexOf("[") + 1) +
                                        money
                                        + carValue.substring(carValue.indexOf("]"), carValue.length());
                                sb = sb.append(carDa).append("\n");
                            }else if(id3.equals("110")){
                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                    sb = sb.append(money).append("sq ft")
                                            .append("\n");
                                }else{
                                    sb = sb.append(money).append("平方英尺")
                                            .append("\n");
                                }
                            } else {
                                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                    sb = sb.append(money).append(mProblemw.get(i).getProblem().get(j).getYvalue())
                                            .append("\n");
                                } else {
                                    sb = sb.append(money).append(mProblemw.get(i).getProblem().get(j).getValue())
                                            .append("\n");
                                }
                            }
                        } else {

                            if (id3.equals("66")) {
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
                    if (sb.toString().endsWith("\n")) {
                        sb = sb.deleteCharAt(sb.lastIndexOf("\n"));
                    }
                }
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    tvOrderView.setViewText(mProblemw.get(i).getYvalue(), sb.toString());
                }else{
                    tvOrderView.setViewText(mProblemw.get(i).getValue(), sb.toString());
                }

                if (!isCar) {
                    ll_yes_week_detail_layout.addView(tvOrderView);
                }
            }
        }
    }

    private String getComTime(int x, int flag) {
        int curMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int nextMonth;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int day3;
        if (curMonth == 12) {
            nextMonth = 1;
        } else {
            nextMonth = curMonth + 1;
        }
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            if (nextMonth == 4 || nextMonth == 6 || nextMonth == 9 || nextMonth == 11) {
                day3 = 30;
            } else if (nextMonth == 2) {
                day3 = 29;
            } else {
                day3 = 31;
            }
        } else {
            if (nextMonth == 4 || nextMonth == 6 || nextMonth == 9 || nextMonth == 11) {
                day3 = 30;
            } else if (nextMonth == 2) {
                day3 = 28;
            } else {
                day3 = 31;
            }
        }
        if (flag == 1) {
            if (x == 1) {
                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                    return "Please complete the task during \n" + nextMonth + ".01-" + nextMonth + ".10";
                } else {
                    return "请于" + nextMonth + ".01-" + nextMonth + ".10完成";
                }
            } else if (x == 2) {
                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                    return "Please complete the task during \n" + nextMonth + ".11-" + (Calendar.MONTH + 1) + ".20";
                } else {
                    return "请于" + nextMonth + ".11-" + nextMonth + ".20完成";
                }
            } else {
                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                    return "Please complete the task during \n" + nextMonth + ".21-" + nextMonth + "." + day3;
                } else {
                    return "请于" + nextMonth + ".21-" + nextMonth + "." + day3 + "完成";
                }
            }
        } else {
            if (AppGlobal.getInstance().getLagStr().equals("en")) {
                return "Complete the task during \n" + new SimpleDateFormat("yyyy.MM.dd").format(System.currentTimeMillis());
            } else {
                return "于" + new SimpleDateFormat("yyyy.MM.dd").format(System.currentTimeMillis()) + "完成";
            }
        }

    }


    View.OnClickListener mmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.rl_1_fwxs_layout) {
                Intent intent = new Intent(OrderYesWeekDetailActivity.this, ProWorkaFeadBackActivity.class);
                intent.putExtra("isHis", true);
                intent.putExtra("step", "1");
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedbackBean", mFeedbackBean);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            } else if (view.getId() == R.id.rl_1_sqxj_layout) {
                Intent intent = new Intent(OrderYesWeekDetailActivity.this, ProWorkbFeadBackActivity.class);
                intent.putExtra("isHis", true);
                intent.putExtra("step", "1");
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedbackBean", mFeedbackBean);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            } else if (view.getId() == R.id.rl_1_tsqkfk_layout) {
                Intent intent = new Intent(OrderYesWeekDetailActivity.this, ProWorkcFeadBackActivity.class);
                intent.putExtra("isHis", true);
                intent.putExtra("step", "1");
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedbackBean", mFeedbackBean);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            } else if (view.getId() == R.id.rl_2_fwxs_layout) {
                Intent intent = new Intent(OrderYesWeekDetailActivity.this, ProWorkaFeadBackActivity.class);
                intent.putExtra("isHis", true);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback2Bean", mFeedback2Bean);
                intent.putExtra("bundle", bundle);
                intent.putExtra("step", "2");
                startActivity(intent);
            } else if (view.getId() == R.id.rl_2_sqxj_layout) {
                Intent intent = new Intent(OrderYesWeekDetailActivity.this, ProWorkbFeadBackActivity.class);
                intent.putExtra("isHis", true);
                intent.putExtra("step", "2");
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback2Bean", mFeedback2Bean);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            } else if (view.getId() == R.id.rl_2_tsqkfk_layout) {
                Intent intent = new Intent(OrderYesWeekDetailActivity.this, ProWorkcFeadBackActivity.class);
                intent.putExtra("isHis", true);
                intent.putExtra("step", "2");
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback2Bean", mFeedback2Bean);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        }
    };

    private void gotoHisFeedBack(int finalI) {
        OrderNewWeekEntity.DataBean.FeedbackBean feedbackBean = mFeedback.get(finalI);
        finalI = finalI + 1;
        Intent intent = new Intent(OrderYesWeekDetailActivity.this, ProWorkFeadBackActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("feedbackBean", feedbackBean);
        intent.putExtra("bundle", bundle);
        intent.putExtra("isHis", true);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 200) {
                findViewById(R.id.ll_zl_zz_layout1).setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.tv_zfmc1)).setText(data.getStringExtra("name"));
                ((TextView) findViewById(R.id.tv_zhzj1)).setText(data.getStringExtra("rent"));
                tv_sub_zhzj.setVisibility(View.GONE);
                isShouBtn = true;
            }
        }
    }

    private void gotoFeedBack(int number) {
        number = number + 1;
        Intent intent = new Intent(OrderYesWeekDetailActivity.this, ProWorkFeadBackActivity.class);
        intent.putExtra("number", number);
        intent.putExtra("isHis", false);
        intent.putExtra("order_id", mDate.getOrder_id());
        intent.putExtra("type", mDate.getType());
        startActivity(intent);
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_week_yes_bar.getBackView()) {
                finish();
            } else if (view.getId() == R.id.tv_sub_zhzj) {
                Intent intent = new Intent(OrderYesWeekDetailActivity.this, RentMoneyActivity.class);
                intent.putExtra("order_id", mDate.getOrder_id());
                intent.putExtra("uid", mDate.getUid());
                intent.putExtra("h_id", mDate.getH_id());
                startActivityForResult(intent, 200);
            } else if (view.getId() == R.id.ib_1_sh) {
                if (is1Flag) {
                    is1Flag = false;
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        ib_1_sh.setBackgroundResource(R.mipmap.pro_btn_hide_en);
                    }else{
                        ib_1_sh.setBackgroundResource(R.mipmap.pro_btn_hide);
                    }

                    ll1Layout.setVisibility(View.VISIBLE);
                } else {
                    is1Flag = true;
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        ib_1_sh.setBackgroundResource(R.mipmap.pro_order_show_en);
                    }else{
                        ib_1_sh.setBackgroundResource(R.mipmap.pro_order_show);
                    }
                    ll1Layout.setVisibility(View.GONE);
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
                    ll2Layout.setVisibility(View.VISIBLE);
                } else {
                    is2Flag = false;
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        ib_2_sh.setBackgroundResource(R.mipmap.pro_order_show_en);
                    }else{
                        ib_2_sh.setBackgroundResource(R.mipmap.pro_order_show);
                    }
//                    ib_2_sh.setImageDrawable(getResources().getDrawable(R.mipmap.pro_order_show));
                    ll2Layout.setVisibility(View.GONE);
                }
            } else if (view.getId() == R.id.ib_1_fwsh) {
                Intent intent = new Intent(OrderYesWeekDetailActivity.this, ProWorkaFeadBackActivity.class);
                intent.putExtra("number", mFeedbackBean.getNumber());
                intent.putExtra("order_id", mFeedbackBean.getOrder_id());
                startActivity(intent);
            } else if (view.getId() == R.id.ib_1_sqxj) {
                Intent intent = new Intent(OrderYesWeekDetailActivity.this, ProWorkbFeadBackActivity.class);
                intent.putExtra("number", mFeedbackBean.getNumber());
                intent.putExtra("order_id", mFeedbackBean.getOrder_id());
                startActivity(intent);
            } else if (view.getId() == R.id.ib_1_tsqkfk) {
                Intent intent = new Intent(OrderYesWeekDetailActivity.this, ProWorkcFeadBackActivity.class);
                intent.putExtra("number", mFeedbackBean.getNumber());
                intent.putExtra("id", mFeedbackBean.getId());
                intent.putExtra("order_id", mFeedbackBean.getOrder_id());
                startActivity(intent);
            } else if (view.getId() == R.id.ib_2_fwsh) {
                Intent intent = new Intent(OrderYesWeekDetailActivity.this, ProWorkaFeadBackActivity.class);
                intent.putExtra("number", mFeedback2Bean.getNumber());
                intent.putExtra("order_id", mFeedback2Bean.getOrder_id());
                startActivity(intent);
            } else if (view.getId() == R.id.ib_2_sqxj) {
                Intent intent = new Intent(OrderYesWeekDetailActivity.this, ProWorkbFeadBackActivity.class);
                intent.putExtra("number", mFeedback2Bean.getNumber());
                intent.putExtra("order_id", mFeedback2Bean.getOrder_id());
                startActivity(intent);
            } else if (view.getId() == R.id.ib_2_tsqkfk) {
                Intent intent = new Intent(OrderYesWeekDetailActivity.this, ProWorkcFeadBackActivity.class);
                intent.putExtra("number", mFeedback2Bean.getNumber());
                intent.putExtra("id", mFeedbackBean.getId());
                intent.putExtra("order_id", mFeedback2Bean.getOrder_id());
                startActivity(intent);

            } else if (view.getId() == R.id.ib_zjsq) {
                Intent intent = new Intent(OrderYesWeekDetailActivity.this, RentNewMoneyActivity.class);
                intent.putExtra("order_id", mDate1.getOrder_id());
                intent.putExtra("uid", mDate1.getUid());
                intent.putExtra("h_id", mDate1.getH_id());
                intent.putExtra("monthtime", mDate1.getMonthtime());
                startActivity(intent);
            } else if (view.getId() == R.id.ib_fwzfzt) {
                Intent intent = new Intent(OrderYesWeekDetailActivity.this, RentStateActivity.class);
                intent.putExtra("order_id", mDate1.getOrder_id());
                intent.putExtra("uid", mBill.getUid());
                intent.putExtra("h_id", mBill.getH_id());
                intent.putExtra("monthtime", mBill.getMonthtime());
                startActivity(intent);
            } else if (view.getId() == R.id.lb_jgqd) {
                Intent intent = new Intent(OrderYesWeekDetailActivity.this, RentPriceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mBill", mBill);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            } else if (view.getId() == R.id.iv_detail_chat) {
                String targetId = "";
                String title = "";
                if ("31".equals(type)) {
                    targetId = mUserBean1.getUid();
                    title = mUserBean1.getNickname();
                } else {
                    targetId = mUserBean.getUid();
                    title = mUserBean.getNickname();
                }
                if(mDate==null||mDate.getId()==null){
                    IMKitService.proDetailMap.put("oid", "");
                }else{
                    IMKitService.proDetailMap.put("oid", mDate.getId()+"");
                }
                if(mDate==null||mDate.getType()==null){
                    IMKitService.proDetailMap.put("type", "");
                }else{
                    IMKitService.proDetailMap.put("type", mDate.getType()+"");
                }

                if (RongIM.getInstance() != null) {
                    /**
                     * 启动单聊界面。
                     *
                     * @param context      应用上下文。
                     * @param targetUserId 要与之聊天的用户 Id。
                     * @param title        聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
                     */
                    RongIM.getInstance().startPrivateChat(OrderYesWeekDetailActivity.this, targetId, title);
                    if ("31".equals(type)) {
                        RongIM.getInstance().setCurrentUserInfo(new UserInfo(targetId, title, Uri.parse(mUserBean1.getAvatar())));
                    } else {
                        RongIM.getInstance().setCurrentUserInfo(new UserInfo(targetId, title, Uri.parse(mUserBean.getAvatar())));
                    }

                }
            } else if (view.getId() == R.id.iv_detail_phone) {
                if ("31".equals(type)) {
                    if (!TextUtils.isEmpty(mUserBean1.area)) {
                        call(mUserBean1.area + mUserBean1.getMobile());
                    } else {
                        call(mUserBean1.getMobile());
                    }
                } else {
                    if (!TextUtils.isEmpty(mUserBean.area)) {
                        call(mUserBean.area + mUserBean.getMobile());
                    } else {
                        call(mUserBean.getMobile());
                    }
                    call(mUserBean.getMobile());
                }

            } else if (view.getId() == R.id.civ_detail_img) {
                gotoProDetailInfo();
            }
        }
    };

    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void gotoProDetailInfo() {
        Intent intent = new Intent(OrderYesWeekDetailActivity.this, ProDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("choose", "");
        bundle.putBoolean("isServer", true);
        if ("31".equals(type)) {
            bundle.putString("uid", mZlOrderEntity.getData().getDate().getUid());//发布订单id
            bundle.putString("nickname", mUserBean.getNickname());
            bundle.putString("type", "");
            bundle.putString("oid", mZlOrderEntity.getData().getDate().getId());//订单id
        } else {
            bundle.putString("uid", mOrderNewWeekEntity.getData().getDate().getUid());//发布订单id
            bundle.putString("nickname", mOrderNewWeekEntity.getData().getUser().getNickname());
            bundle.putString("type", "");
            bundle.putString("oid", mOrderNewWeekEntity.getData().getDate().getId());//订单id
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public String getRoomNum(int num) {
        String str = "";
        String isZH = AppGlobal.getInstance().getLagStr();
        if (isZH.equals("zh")) {
            switch (num) {

                case 1:
                    str = "第一次服务";
                    break;
                case 2:
                    str = "第二次服务";
                    break;
                case 3:
                    str = "第三次服务";
                    break;
                case 4:
                    str = "第四次服务";
                    break;
                case 5:
                    str = "第五次服务";
                    break;
                case 6:
                    str = "第六次服务";
                    break;
            }
        } else {
            switch (num) {

                case 1:
                    str = "The first time";
                    break;
                case 2:
                    str = "The second time";
                    break;
                case 3:
                    str = "The third time";
                    break;
                case 4:
                    str = "The fourth time";
                    break;
                case 5:
                    str = "The fifth time";
                    break;
                case 6:
                    str = "The sixth time";
                    break;
            }
        }

        return str;
    }

    @Override
    public void refreshData(String type) {
        this.type = type;
        initNetData(id, type);
    }

    @Override
    public void refreshAData(String type) {
        this.type = type;
        initNetData(id, type);
    }

    @Override
    public void refreshBData(String type) {
        this.type = type;
        initNetData(id, type);
    }

    @Override
    public void refreshCData(String type) {
        this.type = type;
        initNetData(id, type);
    }
}
