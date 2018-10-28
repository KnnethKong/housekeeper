package com.haiwai.housekeeper.fragment.user.other;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.server.RentMoneyActivity;
import com.haiwai.housekeeper.activity.user.O2OServeDetailActivity;
import com.haiwai.housekeeper.activity.user.SelfSupportManageScheme;
import com.haiwai.housekeeper.activity.user.VipHouseChooseActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProFragment;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.Pickers;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.entity.VipHouseDesignEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SpanUtil;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.utils.VipParseDataUtils;
import com.haiwai.housekeeper.view.DateHisPopView;
import com.haiwai.housekeeper.view.HouseManageProgress;
import com.haiwai.housekeeper.view.scrollview.MyScrollView;
import com.haiwai.housekeeper.widget.LoadDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import static android.app.Activity.RESULT_OK;

public class FwglFragment extends BaseProFragment implements View.OnClickListener,MyScrollView.onPullToRefreshListener {
    private LinearLayout lladdr;
    private TextView tvaddr;
    private TextView bt_commit;
    Map<String, String> map = null;
    private String uid = "";
    private String h_id = "";
    private Long month ;

    private View hisList,thisLine;
    User user;
    //-----------------------------------历史---
    TextView tv_history_month;
    //------------房屋巡视-----------
    HouseManageProgress hmp_fwgl;
    TextView tv_fwgl_1, tv_fwgl_2;//巡视日期
    LinearLayout ll_1_fwgl, ll_2_fwgl;//隐藏或显示
    boolean isShow1 = false, isShow2 = false;
    LinearLayout ll_fwgl_cqxs_1, ll_fwgl_zlxj_1, ll_fwgl_tsqkfk_1, ll_fwgl_cqxs_2, ll_fwgl_zlxj_2, ll_fwgl_tsqkfk_2;
    private LinearLayout ib_fwgl_1,ib_fwgl_2;
    TextView tv_fwgl_cqxs_1, tv_fwgl_zlxj_1, tv_fwgl_tsqkfk_1, tv_fwgl_cqxs_2, tv_fwgl_zlxj_2, tv_fwgl_tsqkfk_2;//每一步日期
    //-----------------------租赁招租----------
    LinearLayout ll_zl_zz;
    TextView tv_zl_zzing;
    //-----------------------租赁管理--------------------
    HouseManageProgress hmp_zl,hmp_zl1;
    LinearLayout ll_zl_gl;
    LinearLayout ll_zl_gl_zjsq, ll_zl_gl_fwzfzt, ll_zl_gl_jgqd;
    TextView tv_zl_gl_zjsq, tv_zl_gl_fwzfzt, tv_zl_gl_jgqd;
    //----------园艺----------

    //---------------------家政-------------------
    HouseManageProgress hmp_jzfw;
    LinearLayout ll_jzfw_1, ll_jzfw_2, ll_jzfw_3, ll_jzfw_4,ll_zl_view;
    TextView tv_jzfw_1, tv_jzfw_2, tv_jzfw_3, tv_jzfw_4;

    private TextView tv_order_empty;
    private LinearLayout is_showing;
    private MyScrollView msv_pull_to_refresh;
    private boolean isRefresing = false;
    private boolean isHisMonth = false;

    private VipHouseDesignEntity.DataBean.VacancyBean.FeedbackBeanXXX mFeedbackBeanXXX;
    private VipHouseDesignEntity.DataBean.VacancyBean.FeedbackBeanXXX mFeedbackBeanXXX1;
    private VipHouseDesignEntity.DataBean.BillBean.FeedbackBean mFeedback;
    private List<VipHouseDesignEntity.DataBean.HomeBean.FeedbackBeanX> mFeedback1;

    private VipHouseDesignEntity.DataBean.BillBean mBillBean;
    private List<Pickers> list = new ArrayList<>();
    private VipHouseDesignEntity.DataBean.HomeBean mHomeBean;
    private List<VipHouseDesignEntity.DataBean.HorticultureBean> mHorticultureBean;
    private VipHouseDesignEntity.DataBean.HousBean mHousBean;
    private VipHouseDesignEntity.DataBean.VacancyBean mVacancyBean;

    private TextView tvFwxsEmpty,tv_fwxs_empty1, tvTyglEmpty, tvJzfwEmpty, tvZlzzEmpty,tvXunshi,tvSecond; //没有反馈信息
    private LinearLayout ll_jzfw_his, ll_yygl_his, ll_zlgl_his,ll_zlgl_his1, ll_fwgl_his;//条目标题部分布局
    private LinearLayout ll_jzgl_layouts, ll_yygl_layouts, ll_zlgl_layouts, ll_fwgl_layouts;//总的条目布局
    private LinearLayout ll_fwgl_b_layout, ll_zlgl_b_layout, ll_yygl_b_layout, ll_jzfw_b_layout;
    private LinearLayout ll_yygl_view;//庭院管理多条数据 放置，动态生成
    @Override
    protected void init() {

    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_fwgl, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ll_zl_view = ((LinearLayout) view.findViewById(R.id.ll_zl_view));
        msv_pull_to_refresh = ((MyScrollView) view.findViewById(R.id.msv_pull_to_refresh));
        msv_pull_to_refresh.setOnPullToRefreshListener(this);
        lladdr = (LinearLayout) view.findViewById(R.id.vip_house_design_ll_addr);
        tvaddr = (TextView) view.findViewById(R.id.vip_house_design_tv_addr);
        lladdr.setOnClickListener(this);
        bt_commit = (TextView) view.findViewById(R.id.vip_house_design_bt_commit);
        bt_commit.setText(SpanUtil.getNewString(getResources().getString(R.string.btn_click_design_scheme), 14, 0, 2, 16, 3, 7));
        bt_commit.setOnClickListener(this);
        //-----------------------------------历史---
        tv_history_month = (TextView) view.findViewById(R.id.tv_history_month);

        hisList = view.findViewById(R.id.view_his_line);
        thisLine = view.findViewById(R.id.view_this_month_line);

        tvXunshi = ((TextView) view.findViewById(R.id.tv_xushi_house));
        tvXunshi.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
        tvSecond = ((TextView) view.findViewById(R.id.tv_xunshi_second));
        tvSecond.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
        view.findViewById(R.id.ll_history_month).setOnClickListener(this);
        view.findViewById(R.id.ll_fwgl_this_motht).setOnClickListener(this);
//        view.findViewById(R.id.ll_fwgl_this_motht).performClick();
        //------------房屋巡视-----------
        hmp_fwgl = (HouseManageProgress) view.findViewById(R.id.hmp_fwgl);
        tv_fwgl_1 = (TextView) view.findViewById(R.id.tv_fwgl_1);
        tv_fwgl_2 = (TextView) view.findViewById(R.id.tv_fwgl_2);
        ll_1_fwgl = (LinearLayout) view.findViewById(R.id.ll_1_fwgl);
        ll_2_fwgl = (LinearLayout) view.findViewById(R.id.ll_2_fwgl);
        ll_fwgl_cqxs_1 = (LinearLayout) view.findViewById(R.id.ll_fwgl_cqxs_1);
        ll_fwgl_zlxj_1 = (LinearLayout) view.findViewById(R.id.ll_fwgl_zlxj_1);
        ll_fwgl_tsqkfk_1 = (LinearLayout) view.findViewById(R.id.ll_fwgl_tsqkfk_1);
        ll_fwgl_cqxs_2 = (LinearLayout) view.findViewById(R.id.ll_fwgl_cqxs_2);
        ll_fwgl_zlxj_2 = (LinearLayout) view.findViewById(R.id.ll_fwgl_zlxj_2);
        ll_fwgl_tsqkfk_2 = (LinearLayout) view.findViewById(R.id.ll_fwgl_tsqkfk_2);
        ib_fwgl_1 = (LinearLayout) view.findViewById(R.id.ib_fwgl_1);
        ib_fwgl_1.setOnClickListener(this);
        ib_fwgl_2 = (LinearLayout) view.findViewById(R.id.ib_fwgl_2);
        ib_fwgl_2.setOnClickListener(this);
        tv_fwgl_cqxs_1 = (TextView) view.findViewById(R.id.tv_fwgl_cqxs_1);
        tv_fwgl_zlxj_1 = (TextView) view.findViewById(R.id.tv_fwgl_zlxj_1);
        tv_fwgl_tsqkfk_1 = (TextView) view.findViewById(R.id.tv_fwgl_tsqkfk_1);
        tv_fwgl_cqxs_2 = (TextView) view.findViewById(R.id.tv_fwgl_cqxs_2);
        tv_fwgl_zlxj_2 = (TextView) view.findViewById(R.id.tv_fwgl_zlxj_2);
        tv_fwgl_tsqkfk_2 = (TextView) view.findViewById(R.id.tv_fwgl_tsqkfk_2);
        //-----------------------租赁招租----------
        ll_zl_zz = (LinearLayout) view.findViewById(R.id.ll_zl_zz);
        tv_zl_zzing = (TextView) view.findViewById(R.id.tv_zl_zzing);
        //-----------------------租赁管理--------------------
        hmp_zl = (HouseManageProgress) view.findViewById(R.id.hmp_zl);
        hmp_zl1 = (HouseManageProgress) view.findViewById(R.id.hmp_zl1);
        hmp_zl.setZlView();
        hmp_zl1.setZlView1();
        ll_zl_gl = (LinearLayout) view.findViewById(R.id.ll_zl_gl);
        ll_zl_gl = (LinearLayout) view.findViewById(R.id.ll_zl_gl);
        ll_zl_gl_zjsq = (LinearLayout) view.findViewById(R.id.ll_zl_gl_zjsq);
        ll_zl_gl_fwzfzt = (LinearLayout) view.findViewById(R.id.ll_zl_gl_fwzfzt);
        ll_zl_gl_jgqd = (LinearLayout) view.findViewById(R.id.ll_zl_gl_jgqd);
        tv_zl_gl_zjsq = (TextView) view.findViewById(R.id.tv_zl_gl_zjsq);
        tv_zl_gl_fwzfzt = (TextView) view.findViewById(R.id.tv_zl_gl_fwzfzt);
        tv_zl_gl_jgqd = (TextView) view.findViewById(R.id.tv_zl_gl_jgqd);

        //---------------------家政-------------------
        hmp_jzfw = (HouseManageProgress) view.findViewById(R.id.hmp_jzfw);
        ll_jzfw_1 = (LinearLayout) view.findViewById(R.id.ll_jzfw_1);
        ll_jzfw_2 = (LinearLayout) view.findViewById(R.id.ll_jzfw_2);
        ll_jzfw_3 = (LinearLayout) view.findViewById(R.id.ll_jzfw_3);
        ll_jzfw_4 = (LinearLayout) view.findViewById(R.id.ll_jzfw_4);
        tv_jzfw_1 = (TextView) view.findViewById(R.id.tv_jzfw_1);
        tv_jzfw_2 = (TextView) view.findViewById(R.id.tv_jzfw_2);
        tv_jzfw_3 = (TextView) view.findViewById(R.id.tv_jzfw_3);
        tv_jzfw_4 = (TextView) view.findViewById(R.id.tv_jzfw_4);

        is_showing= (LinearLayout) view.findViewById(R.id.is_showing);
        tv_order_empty = (TextView) view.findViewById(R.id.tv_order_empty);
        ll_jzfw_his = (LinearLayout) view.findViewById(R.id.ll_jzfw_his);
        ll_zlgl_his = (LinearLayout) view.findViewById(R.id.ll_zlgl_his);
        ll_zlgl_his1 = (LinearLayout) view.findViewById(R.id.ll_zlgl_his1);
        ll_fwgl_his = (LinearLayout) view.findViewById(R.id.ll_fwgl_his);


        tvFwxsEmpty = (TextView) view.findViewById(R.id.tv_fwxs_empty);
        tv_fwxs_empty1 = (TextView) view.findViewById(R.id.tv_fwxs_empty1);
        tvJzfwEmpty = (TextView) view.findViewById(R.id.tv_jzfw_empy);
        tvZlzzEmpty = (TextView) view.findViewById(R.id.tv_zlzz_empty);

        ll_jzgl_layouts = (LinearLayout) view.findViewById(R.id.ll_jzgl_layouts);
        ll_yygl_view= (LinearLayout) view.findViewById(R.id.ll_yygl_view);
        ll_yygl_layouts= (LinearLayout) view.findViewById(R.id.ll_yygl_layouts);
        ll_yygl_layouts.setVisibility(View.GONE);
        ll_zlgl_layouts = (LinearLayout) view.findViewById(R.id.ll_zlgl_layouts);
        ll_fwgl_layouts = (LinearLayout) view.findViewById(R.id.ll_fwgl_layouts);

        ll_fwgl_b_layout = (LinearLayout) view.findViewById(R.id.ll_fwgl_b_layout);
        ll_zlgl_b_layout = (LinearLayout) view.findViewById(R.id.ll_zlgl_b_layout);


        ll_jzfw_b_layout = (LinearLayout) view.findViewById(R.id.ll_jzfw_b_layout);

        hmp_zl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AppGlobal.getInstance().getLagStr().equals("zh")){
                    ToastUtil.shortToast(getContext(),"每月有1次");
                }else{
                    ToastUtil.shortToast(getContext(),"once/month");
                }

            }
        });


        hmp_fwgl.setNum(2);
        hmp_fwgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AppGlobal.getInstance().getLagStr().equals("zh")){
                    ToastUtil.shortToast(getContext(),"每月有"+2+"次");
                }else{
                    ToastUtil.shortToast(getContext(),2+"times a month");
                }
            }
        });
        month=getTime();
    }


    @Override
    public void onResume() {
        super.onResume();
        getData();
    }
    //字符串转时间戳
    public static Long getTime(){
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM");
        dff.setTimeZone(TimeZone.getTimeZone("GMT-8"));
        String ee = dff.format(new Date());
        Log.e("jDTime---->","---"+ ee);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date d = new Date();
        long l = 0;
        try{
            d = sdf.parse(ee);
            l = d.getTime();

        } catch(ParseException e){
            e.printStackTrace();
        }
        return l ;
    }
    private void getData(){

        LoadDialog.showProgressDialog(context);
        user = AppGlobal.getInstance().getUser();
        map = new HashMap<>();
        map.put("uid", user.getUid());

        if(SPUtils.getBoolean(getContext(),"is_h_id",false)){
            map.put("h_id",SPUtils.getString(getContext(),"h_id_str",""));
        }

        if(!TextUtils.isEmpty(h_id)){
            map.put("h_id", h_id);
        }
        map.put("month", (month/1000+864000)+"");
        Log.e("jDTime---->","---"+  (month/1000+864000)+"");
        map.put("secret_key", SPUtils.getString(getActivity(), "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.home_order, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(">>>>>>>房屋管理>>>>." + response);
                LoadDialog.closeProgressDialog();
                if(isRefresing){
                    msv_pull_to_refresh.refreshCompleted();
                    msv_pull_to_refresh.smoothScrollTo(0,0-(lladdr.getHeight()));
                }
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject dataObject = jsonObject.optJSONObject("data");
                        JSONObject billObject = dataObject.optJSONObject("bill");//租赁管理
                        final JSONObject forrentObject = dataObject.optJSONObject("forrent");//租赁招租
                        if (forrentObject == null) {
                            ll_zlgl_layouts.setVisibility(View.GONE);
                            ll_zl_gl.setVisibility(View.GONE);
                            ll_zl_zz.setVisibility(View.VISIBLE);
                            ll_zlgl_his1.setVisibility(View.GONE);
                            tv_fwxs_empty1.setVisibility(View.GONE);
                            ll_zl_view.setVisibility(View.GONE);
                            Log.i("zhaozuInformation----","forrent没值。。。");
                            view.findViewById(R.id.tv_zl_zzing_detail).setVisibility(View.GONE);
                        } else {
                            ll_zl_view.setVisibility(View.GONE);
                            ll_zlgl_layouts.setVisibility(View.VISIBLE);
                            view.findViewById(R.id.tv_zl_zzing_detail).setVisibility(View.GONE);
                            final VipHouseDesignEntity.DataBean.BillBean bill = VipParseDataUtils.parseBillBean(forrentObject);
                            Log.i("zhaozuInformation----","forrent有值。。。"+forrentObject.optInt("wan_num")+"---"+bill.getFeedback().toString());
                            if(bill!=null&&bill.getFeedback()!=null&&bill.getFeedback().getRen_nickname()!=null&&!bill.getFeedback().getRen_nickname().equals("")){
                                hmp_zl.setZlPor(1);
                                tv_zl_zzing.setText(getString(R.string.wczz1));
                                view.findViewById(R.id.tv_zl_zzing_detail).setVisibility(View.VISIBLE);
                                ll_zl_zz.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(getContext(), RentMoneyActivity.class);
                                        intent.putExtra("name", bill.getFeedback().getRen_nickname());
                                        intent.putExtra("money",bill.getFeedback().getRent());
                                        startActivity(intent);
                                    }
                                });
                            }

                            if(forrentObject.optInt("wan_num")>0){
                                ll_zlgl_his.setVisibility(View.VISIBLE);
                                ll_zl_zz.setVisibility(View.VISIBLE);
//                                tv_zl_zzing.setText(getString(R.string.wait_ro_feed_back));
                            }else{
                                ll_zlgl_his.setVisibility(View.VISIBLE);
                                ll_zl_zz.setVisibility(View.VISIBLE);
                            }
                        }
                        if (billObject != null) {
                            Log.i("zhaozuInformation----","mBillBean有值。。。");
                            ll_zlgl_his1.setVisibility(View.VISIBLE);
                            tv_fwxs_empty1.setVisibility(View.VISIBLE);
                            mBillBean = VipParseDataUtils.parseBillBean(billObject);
                            bindbillDataView(mBillBean);
                            Log.i("is_static",mBillBean.getFeedback().getIs_static()+"");
                        }else{
                            view.findViewById(R.id.ll_zl_view).setVisibility(View.GONE);
                            ll_zl_gl.setVisibility(View.GONE);
                            Log.i("zhaozuInformation----","mBillBean没值。。。");
                        }


                        JSONObject homeObject = dataObject.optJSONObject("home");//家政服务
                        if (homeObject != null) {
                            ll_jzgl_layouts.setVisibility(View.VISIBLE);
                            mHomeBean = VipParseDataUtils.parseHomeBean(homeObject);
                            if (mHomeBean.getFeedback().size() > 0) {
                                tvJzfwEmpty.setVisibility(View.GONE);
                                ll_jzfw_b_layout.setVisibility(View.VISIBLE);
                                bindHomeDataView(mHomeBean);
                            } else {
                                tvJzfwEmpty.setVisibility(View.VISIBLE);
                                ll_jzfw_b_layout.setVisibility(View.GONE);
                            }
                        } else {
                            ll_jzgl_layouts.setVisibility(View.GONE);
                        }
                        JSONArray horticultureObject = dataObject.optJSONArray("horticulture_arr");//园艺
                        if (horticultureObject != null) {
                            ll_yygl_view.removeAllViews();
                            ll_yygl_view.setVisibility(View.VISIBLE);
                            mHorticultureBean = VipParseDataUtils.parseHorticultureBean(horticultureObject);
                            for(int hr=0;hr<mHorticultureBean.size();hr++) {
                                Log.e("FeedBackInformation--", mHorticultureBean.get(hr).getFeedback().size() + "");
                                    View view=View.inflate(getActivity(),R.layout.ll_yygl_view_item,null);
                                TextView ll_yygl_name= (TextView) view.findViewById(R.id.ll_yygl_name);
                                ll_yygl_layouts = (LinearLayout) view.findViewById(R.id.ll_yygl_layouts);
                              LinearLayout  ll_yygl_b_layout = (LinearLayout) view.findViewById(R.id.ll_yygl_b_layout);
                               // ll_yygl_his = (LinearLayout) view.findViewById(R.id.ll_yygl_his);
                              TextView  tvTyglEmpty = (TextView) view.findViewById(R.id.tv_yy_empty);
                                //----------园艺----------
                                if(mHorticultureBean.get(hr).getType().equals("34")) {
                                    ll_yygl_name.setText(R.string.yygl134);
                                }else if(mHorticultureBean.get(hr).getType().equals("32")){
                                    ll_yygl_name.setText(R.string.yygl132);
                                }else if(mHorticultureBean.get(hr).getType().equals("33")){
                                    ll_yygl_name.setText(R.string.yygl133);
                                }
                                if (mHorticultureBean.get(hr).getFeedback().size() > 0) {
                                    ll_yygl_b_layout.setVisibility(View.VISIBLE);
                                    tvTyglEmpty.setVisibility(View.GONE);
                                    bindHorticultureDataView(mHorticultureBean.get(hr),view,tvTyglEmpty,ll_yygl_b_layout);
                                } else {
                                    tvTyglEmpty.setVisibility(View.VISIBLE);
                                    ll_yygl_b_layout.setVisibility(View.GONE);
                                }
                                ll_yygl_view.addView(view);
                            }
                            if(mHorticultureBean.size()==0){
                                ll_yygl_view.setVisibility(View.GONE);
                            }
                        } else {
                            ll_yygl_layouts.setVisibility(View.GONE);
                        }
                        JSONObject housObject = dataObject.optJSONObject("hous");//房产
                        if (housObject != null) {
                            mHousBean = VipParseDataUtils.parseHousBean(housObject);
                            tvaddr.setText(mHousBean.getAddress_info());
                            h_id = mHousBean.getId();
                            SPUtils.saveString(getContext(),"fw_h_id",h_id);
                            tvaddr.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                        }
                        JSONObject vacancyObject = dataObject.optJSONObject("vacancy");//房屋巡视
                        if (vacancyObject != null) {
                            ll_fwgl_layouts.setVisibility(View.VISIBLE);
                            mVacancyBean = VipParseDataUtils.parseVacancyBean(vacancyObject);
                            if (mVacancyBean.getFeedback().size() > 0) {
                                ll_fwgl_b_layout.setVisibility(View.VISIBLE);
                                tvFwxsEmpty.setVisibility(View.GONE);
                                if(mVacancyBean.getFeedback().size()==1){
                                    ll_fwgl_tsqkfk_1.setVisibility(View.GONE);
                                    ll_fwgl_zlxj_1.setVisibility(View.GONE);
                                }else if(mVacancyBean.getFeedback().size()==2){
                                    ll_fwgl_tsqkfk_1.setVisibility(View.GONE);
                                }
                                bindVacancyDataView(mVacancyBean);
                            } else {
                                tvFwxsEmpty.setVisibility(View.VISIBLE);
                                ll_fwgl_b_layout.setVisibility(View.GONE);
                            }
                        } else {
                            ll_fwgl_layouts.setVisibility(View.GONE);
                        }
                        if (ll_fwgl_layouts.getVisibility() == View.GONE && ll_zlgl_layouts.getVisibility() == View.GONE && ll_zl_view.getVisibility() == View.GONE && ll_yygl_view.getVisibility()==View.GONE
                                &&ll_jzgl_layouts.getVisibility()==View.GONE) {
                            tv_order_empty.setVisibility(View.GONE);
                            is_showing.setVisibility(View.VISIBLE);
                            Log.e("result-->","aaa");
                        } else {
                            tv_order_empty.setVisibility(View.GONE);
                            is_showing.setVisibility(View.GONE);
                            Log.e("result-->","aaa"+ll_fwgl_layouts.getVisibility()+ll_zlgl_layouts.getVisibility()+ll_zl_view.getVisibility()+ll_yygl_view.getVisibility()+
                                    ll_jzgl_layouts.getVisibility());
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ToastUtil.longToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }
    @Override
    protected void initData() {
        getData();
    }



    private void initHisMonth() {
        map = new HashMap<>();
        map.put("uid", user.getUid());
        if (TextUtils.isEmpty(h_id)) {
            ToastUtil.shortToast(context, "先选择房产");
            return;
        } else {
            LoadDialog.showProgressDialog(context);
            map.put("h_id", h_id);
            map.put("secret_key", SPUtils.getString(getActivity(), "secret", ""));
            map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.histry_month, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    int code = JsonUtils.getJsonInt(response, "status");
                    System.out.print(">>>>>月" + response);
                    if(isRefresing){
                        msv_pull_to_refresh.refreshCompleted();
                        msv_pull_to_refresh.smoothScrollTo(0,0-(lladdr.getHeight()));
                    }
                    if (code == 200) {
                        ParseData(response);
                    } else {
                        LoadDialog.closeProgressDialog();
                        ToastUtil.longToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }));
        }
    }

    private void ParseData(String response) {
        list.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.optJSONArray("data");
            if (jsonArray != null && jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    Pickers pic = new Pickers(TimeUtils.getStr12Time(jsonArray.optString(i)), i + "");
                    list.add(pic);
                }
            }
            if (list.size() > 0) {
                LoadDialog.closeProgressDialog();
                DateHisPopView d2p = new DateHisPopView(context);
                d2p.initData(list);
                d2p.openPopWindow(view);
                d2p.setDatePicOnClickListener(new DateHisPopView.DatePicOnClickListener() {
                    @Override
                    public void datePicker(String str) {
                        month = Long.parseLong(TimeUtils.getMonthStampMore(str))*1000;
                        Log.e("result----->",month+"");
                        tv_history_month.setText(str);
                        initData();
                    }
                });
            } else {
                LoadDialog.closeProgressDialog();
                ToastUtil.longToast(context, getString(R.string.no_his));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * 租赁管理
     *
     * @param billBean
     */
    private void bindbillDataView(VipHouseDesignEntity.DataBean.BillBean billBean) {
        if(billBean.getFeedback().getIs_static()!=null){
        if(billBean.getFeedback().getIs_static().equals("3")){
            ll_zl_zz.setVisibility(View.VISIBLE);
            ll_zl_gl.setVisibility(View.VISIBLE);
            ll_zl_view.setVisibility(View.VISIBLE);
            tv_fwxs_empty1.setVisibility(View.GONE);
        }else if(billBean.getFeedback().getIs_static().equals("2")){
            ll_zl_zz.setVisibility(View.VISIBLE);
            ll_zl_gl_zjsq.setVisibility(View.VISIBLE);
            ll_zl_gl_fwzfzt.setVisibility(View.VISIBLE);
            ll_zl_gl_jgqd.setVisibility(View.GONE);
            ll_zl_gl.setVisibility(View.VISIBLE);
            tv_fwxs_empty1.setVisibility(View.GONE);
            ll_zl_view.setVisibility(View.VISIBLE);
        }else if(billBean.getFeedback().getIs_static().equals("1")){
            ll_zl_zz.setVisibility(View.VISIBLE);
            ll_zl_gl_zjsq.setVisibility(View.VISIBLE);
            ll_zl_gl_fwzfzt.setVisibility(View.GONE);
            ll_zl_gl_jgqd.setVisibility(View.GONE);
            ll_zl_gl.setVisibility(View.VISIBLE);
            ll_zl_view.setVisibility(View.VISIBLE);
            tv_fwxs_empty1.setVisibility(View.GONE);
        }else{
            ll_zl_zz.setVisibility(View.VISIBLE);
            tv_fwxs_empty1.setVisibility(View.VISIBLE);
            ll_zl_view.setVisibility(View.GONE);
        }
        }else {
            ll_zl_zz.setVisibility(View.VISIBLE);
            tv_fwxs_empty1.setVisibility(View.VISIBLE);
            ll_zl_view.setVisibility(View.GONE);
        }




        mFeedback = billBean.getFeedback();
//        if(mFeedback==null){
//            view.findViewById(R.id.ll_zl_gl).setVisibility(View.VISIBLE);
//            view.findViewById(R.id.ll_zl_zz).setVisibility(View.VISIBLE);
////            ll_zl_zz.setVisibility(View.VISIBLE);
//            return;
//        }
        Log.i("mFeedbackinformation---",mFeedback.getRent()+"--"+mFeedback.getHome_remark()+"---"+mFeedback.getHome_remark()+"--"+mFeedback.getPrice_remark());

        if (!TextUtils.isEmpty(mFeedback.getRent()) && Double.valueOf(mFeedback.getRent())!=0) {
            ll_zl_zz.setVisibility(View.VISIBLE);
            hmp_zl1.setZlPor(1);
            ll_zl_gl_zjsq.setOnClickListener(mOnClickListener);
            tv_zl_gl_zjsq.setText(TimeUtils.getDate(mFeedback.getWtime1()));
            hmp_zl.setView3Progress(1);
            if (!TextUtils.isEmpty(mFeedback.getHome_remark()) && !TextUtils.isEmpty(mFeedback.getTenant_remark())) {
                ll_zl_gl_fwzfzt.setOnClickListener(mOnClickListener);
                tv_zl_gl_fwzfzt.setText(TimeUtils.getDate(mFeedback.getWtime2()));
                hmp_zl.setView3Progress(2);
                if (!TextUtils.isEmpty(mFeedback.getPrice_remark())) {
                    ll_zl_gl_jgqd.setVisibility(View.VISIBLE);
                    ll_zl_gl_jgqd.setOnClickListener(mOnClickListener);
                    tv_zl_gl_jgqd.setText(TimeUtils.getDate(mFeedback.getWtime3()));
                    hmp_zl.setView3Progress(3);
                }else{
                    ll_zl_gl_jgqd.setVisibility(View.GONE);
                    ll_zl_zz.setVisibility(View.VISIBLE);
                }
            }else{
                ll_zl_gl_fwzfzt.setVisibility(View.GONE);
                ll_zl_gl_jgqd.setVisibility(View.GONE);
                ll_zl_zz.setVisibility(View.VISIBLE);
            }
        }else{
            ll_zl_gl_zjsq.setVisibility(View.GONE);
            ll_zl_gl_fwzfzt.setVisibility(View.GONE);
            ll_zl_gl_jgqd.setVisibility(View.GONE);
            ll_zl_zz.setVisibility(View.VISIBLE);
//            tv_zl_zzing.setText(getString(R.string.wait_ro_feed_back));
        }

//        if (!TextUtils.isEmpty(mFeedback.getRent()) && Double.valueOf(mFeedback.getRent())!=0) {
//            ll_zl_gl_zjsq.setOnClickListener(mOnClickListener);
//            tv_zl_gl_zjsq.setText(TimeUtils.getDate(mFeedback.getWtime1()));
//            hmp_zl.setView3Progress(1);
//            if (!TextUtils.isEmpty(mFeedback.getHome_remark()) && !TextUtils.isEmpty(mFeedback.getTenant_remark())) {
//                ll_zl_gl_fwzfzt.setOnClickListener(mOnClickListener);
//                tv_zl_gl_fwzfzt.setText(TimeUtils.getDate(mFeedback.getWtime2()));
//                hmp_zl.setView3Progress(2);
//                if (!TextUtils.isEmpty(mFeedback.getPrice_remark())) {
//                    ll_zl_gl_jgqd.setOnClickListener(mOnClickListener);
//                    tv_zl_gl_jgqd.setText(TimeUtils.getDate(mFeedback.getWtime3()));
//                    hmp_zl.setView3Progress(3);
//                }else{
//                    ll_zl_gl_zjsq.setVisibility(View.GONE);
//                    ll_zl_gl_fwzfzt.setVisibility(View.GONE);
//                    ll_zl_gl_jgqd.setVisibility(View.GONE);
//                }
//            }else{
//                ll_zl_gl_zjsq.setVisibility(View.GONE);
//                ll_zl_gl_fwzfzt.setVisibility(View.GONE);
//            }
//        }else{
//            ll_zl_gl_zjsq.setVisibility(View.GONE);
//        }
//        ll_zl_gl = (LinearLayout) findViewById(R.id.ll_zl_gl);
//         = (LinearLayout) findViewById(R.id.ll_zl_gl_zjsq);
//         = (LinearLayout) findViewById(R.id.ll_zl_gl_fwzfzt);
//         = (LinearLayout) findViewById(R.id.ll_zl_gl_jgqd);
//         = (TextView) findViewById(R.id.tv_zl_gl_zjsq);
//         = (TextView) findViewById(R.id.tv_zl_gl_fwzfzt);
//         = (TextView) findViewById(R.id.tv_zl_gl_jgqd);
        //-----------招租内容---------
//        ll_zl_zz.setVisibility(View.GONE);
    }

    /**
     * 园艺管理
     *
     * @param horticultureBean
     */
    private void bindHorticultureDataView(final VipHouseDesignEntity.DataBean.HorticultureBean horticultureBean,View view,TextView tvTyglEmpty,LinearLayout ll_yygl_b_layout) {
        HouseManageProgress hmp_yygl;
        LinearLayout ll_yygl_1, ll_yygl_2, ll_yygl_3, ll_yygl_4;
        TextView tv_yygl_1, tv_yygl_2, tv_yygl_3, tv_yygl_4;
        hmp_yygl = (HouseManageProgress) view.findViewById(R.id.hmp_yygl);
        ll_yygl_1 = (LinearLayout) view.findViewById(R.id.ll_yygl_1);
        ll_yygl_2 = (LinearLayout) view.findViewById(R.id.ll_yygl_2);
        ll_yygl_3 = (LinearLayout) view.findViewById(R.id.ll_yygl_3);
        ll_yygl_4 = (LinearLayout) view.findViewById(R.id.ll_yygl_4);
        tv_yygl_1 = (TextView) view.findViewById(R.id.tv_yygl_1);
        tv_yygl_2 = (TextView) view.findViewById(R.id.tv_yygl_2);
        tv_yygl_3 = (TextView) view.findViewById(R.id.tv_yygl_3);
        tv_yygl_4 = (TextView) view.findViewById(R.id.tv_yygl_4);
       final    List<VipHouseDesignEntity.DataBean.HorticultureBean.FeedbackBeanXX> mFeedback2;
        mFeedback2 = horticultureBean.getFeedback();
        final int num = horticultureBean.getFeedback().size();
        hmp_yygl.setNum(num);
        hmp_yygl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    ToastUtil.shortToast(getContext(),num+"times every month");
                }else{
                    ToastUtil.shortToast(getContext(),"每月有"+num+"次");
                }

            }
        });
        switch (num) {
            case 3:
                ll_yygl_4.setVisibility(View.GONE);
                break;
            case 2:
                ll_yygl_3.setVisibility(View.GONE);
                ll_yygl_4.setVisibility(View.GONE);
                break;
            case 1:
                ll_yygl_2.setVisibility(View.GONE);
                ll_yygl_3.setVisibility(View.GONE);
                ll_yygl_4.setVisibility(View.GONE);
                break;
        }
        for (int i = 0; i < num; i++) {
//            if (TextUtils.isEmpty(mFeedback2.get(i).getContent1())) {

//            } else {
                tvTyglEmpty.setVisibility(View.GONE);
                ll_yygl_b_layout.setVisibility(View.VISIBLE);

                int a = 0;
                for (int j = 0; j < num; j++) {
                    if (!TextUtils.isEmpty(horticultureBean.getFeedback().get(j).getContent1())) {
                        a = j + 1;
                    }
                    Log.i("horticultureBean--",horticultureBean.getFeedback().get(j).getContent1()+"--"+a);
                }
                if (a == 1) {
                    ll_yygl_b_layout.setVisibility(View.VISIBLE);
                    ll_yygl_1.setVisibility(View.VISIBLE);

                    ll_yygl_2.setVisibility(View.GONE);
                    ll_yygl_3.setVisibility(View.GONE);
                    ll_yygl_4.setVisibility(View.GONE);
                    tv_yygl_1.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(0).getWtime1()));
                }else if(a==0){
                    tvTyglEmpty.setVisibility(View.VISIBLE);
                    ll_yygl_b_layout.setVisibility(View.GONE);
                } else if (a == 2) {
                    ll_yygl_b_layout.setVisibility(View.VISIBLE);
                    ll_yygl_1.setVisibility(View.VISIBLE);
                    ll_yygl_2.setVisibility(View.VISIBLE);

                    ll_yygl_3.setVisibility(View.GONE);
                    ll_yygl_4.setVisibility(View.GONE);
                    tv_yygl_1.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(0).getWtime1()));
                    tv_yygl_2.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(1).getWtime1()));
                } else if (a == 3) {
                    ll_yygl_b_layout.setVisibility(View.VISIBLE);
                    ll_yygl_1.setVisibility(View.VISIBLE);

                    ll_yygl_2.setVisibility(View.VISIBLE);

                    ll_yygl_3.setVisibility(View.VISIBLE);

                    ll_yygl_4.setVisibility(View.GONE);
                    tv_yygl_1.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(0).getWtime1()));
                    tv_yygl_2.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(1).getWtime1()));
                    tv_yygl_3.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(2).getWtime1()));
                } else if (a == 4) {
                    ll_yygl_b_layout.setVisibility(View.VISIBLE);


                    tv_yygl_1.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(0).getWtime1()));
                    tv_yygl_2.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(1).getWtime1()));
                    tv_yygl_3.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(2).getWtime1()));
                    tv_yygl_4.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(3).getWtime1()));
                }

                setHorticultureProgress(hmp_yygl, num, a);
            }
        ll_yygl_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback2", (Serializable) mFeedback2);
                intent.putExtra("avatar", horticultureBean.getAvatar());
                intent.putExtra("nickname", horticultureBean.getNickname());
                intent.putExtra("is_ren",horticultureBean.is_ren);
                intent.putExtra("type",horticultureBean.getType());
                intent.putExtra("is_hou",horticultureBean.is_hou);
                intent.putExtra("xing",horticultureBean.user_xing);
                intent.putExtra("s",horticultureBean.s);
                intent.putExtra("user_onum",horticultureBean.getUser_onum());
                intent.putExtra("pro_score",horticultureBean.pro_score);
                intent.putExtra("mobile",horticultureBean.mobile);
                intent.putExtra("uid",horticultureBean.getJ_uid());
                intent.putExtra("bundle", bundle);
                intent.putExtra("isYy", true);
                intent.putExtra("step", "0");
                startActivity(intent);
            }
        });
        ll_yygl_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback2", (Serializable) mFeedback2);
                intent.putExtra("avatar", horticultureBean.getAvatar());
                intent.putExtra("nickname", horticultureBean.getNickname());
                intent.putExtra("is_ren",horticultureBean.is_ren);
                intent.putExtra("is_hou",horticultureBean.is_hou);
                intent.putExtra("xing",horticultureBean.user_xing);
                intent.putExtra("mobile",horticultureBean.mobile);
                intent.putExtra("user_onum",horticultureBean.getUser_onum());
                intent.putExtra("s",horticultureBean.s);
                intent.putExtra("pro_score",horticultureBean.pro_score);
                intent.putExtra("bundle", bundle);
                intent.putExtra("isYy", true);
                intent.putExtra("type",horticultureBean.getType());
                intent.putExtra("uid",horticultureBean.getJ_uid());
                intent.putExtra("step", "1");
                startActivity(intent);
            }
        });
        ll_yygl_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback2", (Serializable) mFeedback2);
                intent.putExtra("avatar", horticultureBean.getAvatar());
                intent.putExtra("nickname", horticultureBean.getNickname());
                intent.putExtra("is_ren",horticultureBean.is_ren);
                intent.putExtra("is_hou",horticultureBean.is_hou);
                intent.putExtra("xing",horticultureBean.user_xing);
                intent.putExtra("user_onum",horticultureBean.getUser_onum());
                intent.putExtra("s",horticultureBean.s);
                intent.putExtra("type",horticultureBean.getType());
                intent.putExtra("pro_score",horticultureBean.pro_score);
                intent.putExtra("uid",horticultureBean.getJ_uid());
                intent.putExtra("mobile",horticultureBean.mobile);
                intent.putExtra("bundle", bundle);
                intent.putExtra("isYy", true);
                intent.putExtra("step", "2");
                startActivity(intent);
            }
        });
        ll_yygl_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback2", (Serializable) mFeedback2);
                intent.putExtra("avatar", horticultureBean.getAvatar());
                intent.putExtra("nickname", horticultureBean.getNickname());
                intent.putExtra("type",horticultureBean.getType());
                intent.putExtra("is_ren",horticultureBean.is_ren);
                intent.putExtra("user_onum",horticultureBean.getUser_onum());
                intent.putExtra("s",horticultureBean.s);
                intent.putExtra("is_hou",horticultureBean.is_hou);
                intent.putExtra("xing",horticultureBean.user_xing);
                intent.putExtra("pro_score",horticultureBean.pro_score);
                intent.putExtra("mobile",horticultureBean.mobile);
                intent.putExtra("uid",horticultureBean.getJ_uid());
                intent.putExtra("bundle", bundle);

                intent.putExtra("isYy", true);
                intent.putExtra("step", "3");
                startActivity(intent);
            }
        });
//        }
    }

    private void setHorticultureProgress(HouseManageProgress hmp_yygl, int num, int a) {
        if (num == 1) {
            hmp_yygl.setView2Progress(a);
        } else if (num == 2) {
            hmp_yygl.setView2Progress(a);
        } else if (num == 3) {
            hmp_yygl.setView3Progress(a);
        } else if (num == 4) {
            hmp_yygl.setView4Progress(a);
        }
    }

    /**
     * 家政服务
     *
     * @param homeBean
     */
    private void bindHomeDataView(VipHouseDesignEntity.DataBean.HomeBean homeBean) {
        mFeedback1 = homeBean.getFeedback();
        final int num = homeBean.getFeedback().size();
        hmp_jzfw.setNum(num);
        hmp_jzfw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AppGlobal.getInstance().getLagStr().equals("zh")){
                    ToastUtil.shortToast(getContext(),"每月有"+num+"次");
                }else{
                    ToastUtil.shortToast(getContext(),num+"times a month");
                }

            }
        });
        for (int i = 0; i < num; i++) {
            if (TextUtils.isEmpty(mFeedback1.get(i).getContent1())) {
                tvJzfwEmpty.setVisibility(View.VISIBLE);
                ll_jzfw_b_layout.setVisibility(View.GONE);
            } else {
                ll_jzfw_b_layout.setVisibility(View.VISIBLE);
                tvJzfwEmpty.setVisibility(View.GONE);
                switch (num) {
                    case 3:
                        ll_jzfw_4.setVisibility(View.GONE);
                        break;
                    case 2:
                        ll_jzfw_4.setVisibility(View.GONE);
                        ll_jzfw_3.setVisibility(View.GONE);
                        break;
                    case 1:
                        ll_jzfw_4.setVisibility(View.GONE);
                        ll_jzfw_3.setVisibility(View.GONE);
                        ll_jzfw_2.setVisibility(View.GONE);
                        break;
                }
                int a = 0;
                for (int j = 0; j < num; j++) {
                    if (!TextUtils.isEmpty(homeBean.getFeedback().get(i).getContent1())) {
                        a = j + 1;
                    }
                }
                if (a == 1) {
                    ll_jzfw_1.setVisibility(View.VISIBLE);
                    ll_jzfw_1.setOnClickListener(mOnClickListener);
                    ll_jzfw_2.setVisibility(View.GONE);
                    ll_jzfw_3.setVisibility(View.GONE);
                    ll_jzfw_4.setVisibility(View.GONE);
                    tv_jzfw_1.setText(TimeUtils.getDate(homeBean.getFeedback().get(0).getWtime1()));
                } else if (a == 2) {
                    ll_jzfw_1.setVisibility(View.VISIBLE);
                    ll_jzfw_1.setOnClickListener(mOnClickListener);
                    ll_jzfw_2.setVisibility(View.VISIBLE);
                    ll_jzfw_2.setOnClickListener(mOnClickListener);
                    ll_jzfw_3.setVisibility(View.GONE);
                    ll_jzfw_4.setVisibility(View.GONE);
                    tv_jzfw_1.setText(TimeUtils.getDate(homeBean.getFeedback().get(0).getWtime1()));
                    tv_jzfw_2.setText(TimeUtils.getDate(homeBean.getFeedback().get(1).getWtime1()));
                } else if (a == 3) {
                    ll_jzfw_1.setVisibility(View.VISIBLE);
                    ll_jzfw_1.setOnClickListener(mOnClickListener);
                    ll_jzfw_2.setVisibility(View.VISIBLE);
                    ll_jzfw_2.setOnClickListener(mOnClickListener);
                    ll_jzfw_3.setVisibility(View.VISIBLE);
                    ll_jzfw_3.setOnClickListener(mOnClickListener);
                    ll_jzfw_4.setVisibility(View.GONE);
                    tv_jzfw_1.setText(TimeUtils.getDate(homeBean.getFeedback().get(0).getWtime1()));
                    tv_jzfw_2.setText(TimeUtils.getDate(homeBean.getFeedback().get(1).getWtime1()));
                    tv_jzfw_3.setText(TimeUtils.getDate(homeBean.getFeedback().get(2).getWtime1()));
                } else if (a == 4) {
                    ll_jzfw_1.setOnClickListener(mOnClickListener);
                    ll_jzfw_2.setOnClickListener(mOnClickListener);
                    ll_jzfw_3.setOnClickListener(mOnClickListener);
                    ll_jzfw_4.setOnClickListener(mOnClickListener);
                    tv_jzfw_1.setText(TimeUtils.getDate(homeBean.getFeedback().get(0).getWtime1()));
                    tv_jzfw_2.setText(TimeUtils.getDate(homeBean.getFeedback().get(1).getWtime1()));
                    tv_jzfw_3.setText(TimeUtils.getDate(homeBean.getFeedback().get(2).getWtime1()));
                    tv_jzfw_4.setText(TimeUtils.getDate(homeBean.getFeedback().get(3).getWtime1()));
                }
                sethomeProgress(hmp_jzfw, num, a);
            }
        }

    }

    private void sethomeProgress(HouseManageProgress hmp_jzfw, int num, int a) {
        if (num == 1) {
            hmp_jzfw.setView2Progress(a);
        } else if (num == 2) {
            hmp_jzfw.setView2Progress(a);
        } else if (num == 3) {
            hmp_jzfw.setView3Progress(a);
        } else if (num == 4) {
            hmp_jzfw.setView4Progress(a);
        }
    }

    /**
     * 房屋巡视数据绑定
     *
     * @param vacancyBean
     */
    private void bindVacancyDataView(VipHouseDesignEntity.DataBean.VacancyBean vacancyBean) {

        mFeedbackBeanXXX = vacancyBean.getFeedback().get(0);
        mFeedbackBeanXXX1 = vacancyBean.getFeedback().get(1);
        if(mFeedbackBeanXXX1==null){
            view.findViewById(R.id.ll_second_check_feedback).setVisibility(View.GONE);
        }
        Log.i("mFeedbackBeanXXX-",mFeedbackBeanXXX.getContent1()+"--"
                                    +mFeedbackBeanXXX.getContent2()+"--"
                                    +mFeedbackBeanXXX.getContent3());
        if (TextUtils.isEmpty(mFeedbackBeanXXX.getContent1())) {
            tvFwxsEmpty.setVisibility(View.VISIBLE);
            ll_fwgl_b_layout.setVisibility(View.GONE);
        } else {
            tvFwxsEmpty.setVisibility(View.GONE);
            ll_fwgl_b_layout.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(mFeedbackBeanXXX.getContent1())) {
                hmp_fwgl.setView2Progress(1);
                ll_1_fwgl.setVisibility(View.VISIBLE);
                tv_fwgl_1.setText(TimeUtils.getDate(mFeedbackBeanXXX.getStime()));
                tv_fwgl_cqxs_1.setText(TimeUtils.getDate(mFeedbackBeanXXX.getWtime1()));
                ll_fwgl_cqxs_1.setOnClickListener(mOnClickListener);
                if (!TextUtils.isEmpty(mFeedbackBeanXXX.getContent2())) {
                    ll_fwgl_zlxj_1.setVisibility(View.VISIBLE);
                    ll_fwgl_zlxj_1.setOnClickListener(mOnClickListener);
                    tv_fwgl_zlxj_1.setText(TimeUtils.getDate(mFeedbackBeanXXX.getWtime2()));
                    if (!TextUtils.isEmpty(mFeedbackBeanXXX.getContent3())) {
                        ll_fwgl_tsqkfk_1.setVisibility(View.VISIBLE);
                        tvXunshi.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.nike_theme),null,null,null);
                        ll_fwgl_tsqkfk_1.setVisibility(View.VISIBLE);
                        tv_fwgl_tsqkfk_1.setText(TimeUtils.getDate(mFeedbackBeanXXX.getWtime3()));
                        ll_fwgl_tsqkfk_1.setOnClickListener(mOnClickListener);
                    }else{
                        ll_fwgl_tsqkfk_1.setVisibility(View.GONE);
                    }
                }else{
                    ll_fwgl_zlxj_1.setVisibility(View.GONE);
                    ll_fwgl_tsqkfk_1.setVisibility(View.GONE);
                }
            }else{
                ll_fwgl_cqxs_1.setVisibility(View.GONE);
                ll_fwgl_zlxj_1.setVisibility(View.GONE);
                ll_fwgl_tsqkfk_1.setVisibility(View.GONE);
            }

            Log.i("mFeedbackBeanXXX1-",mFeedbackBeanXXX1.getContent1()+"--"
                    +mFeedbackBeanXXX1.getContent2()+"--"
                    +mFeedbackBeanXXX1.getContent3());

            if (!TextUtils.isEmpty(mFeedbackBeanXXX1.getContent1())) {
                hmp_fwgl.setView2Progress(2);
                view.findViewById(R.id.ll_second_check_feedback).setVisibility(View.VISIBLE);
                ll_2_fwgl.setVisibility(View.VISIBLE);
                ll_fwgl_cqxs_2.setVisibility(View.VISIBLE);
                tv_fwgl_2.setText(TimeUtils.getDate(mFeedbackBeanXXX1.getStime()));
                tv_fwgl_cqxs_2.setText(TimeUtils.getDate(mFeedbackBeanXXX1.getWtime1()));
                ll_fwgl_cqxs_2.setOnClickListener(mOnClickListener);
                if (!TextUtils.isEmpty(mFeedbackBeanXXX1.getContent2())) {
                    tv_fwgl_zlxj_2.setText(TimeUtils.getDate(mFeedbackBeanXXX1.getWtime2()));
                    ll_fwgl_zlxj_2.setOnClickListener(mOnClickListener);
                    ll_fwgl_zlxj_2.setVisibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(mFeedbackBeanXXX1.getContent3())) {
                        ll_fwgl_tsqkfk_2.setVisibility(View.VISIBLE);
                        tvSecond.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.nike_theme),null,null,null);
                        tv_fwgl_tsqkfk_2.setText(TimeUtils.getDate(mFeedbackBeanXXX1.getWtime3()));
                        ll_fwgl_tsqkfk_2.setOnClickListener(mOnClickListener);
                    }else{
                        ll_fwgl_tsqkfk_2.setVisibility(View.GONE);
                    }
                }else{
                    ll_fwgl_zlxj_2.setVisibility(View.GONE);
                    ll_fwgl_tsqkfk_2.setVisibility(View.GONE);
                }
            }else{
                ll_fwgl_cqxs_2.setVisibility(View.GONE);
                ll_fwgl_zlxj_2.setVisibility(View.GONE);
                ll_fwgl_tsqkfk_2.setVisibility(View.GONE);
                view.findViewById(R.id.ll_second_check_feedback).setVisibility(View.GONE);
            }
        }

    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.ll_fwgl_cqxs_1) {
                Intent intent = new Intent(context, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedbackBeanXXX", mFeedbackBeanXXX);
                intent.putExtra("bundle", bundle);
                intent.putExtra("type",mVacancyBean.getType());
                intent.putExtra("avatar", mVacancyBean.getAvatar());
                intent.putExtra("nickname", mVacancyBean.getNickname());
                intent.putExtra("is_ren",mVacancyBean.is_ren);
                intent.putExtra("is_hou",mVacancyBean.is_hou);
                intent.putExtra("xing",mVacancyBean.user_xing);
                intent.putExtra("user_onum",mVacancyBean.getUser_onum());
                intent.putExtra("mobile",mVacancyBean.mobile);
                intent.putExtra("s",mVacancyBean.getS());
                intent.putExtra("uid",mVacancyBean.getJ_uid());
                intent.putExtra("pro_score",mVacancyBean.pro_score);
                intent.putExtra("isXs", true);
                intent.putExtra("time", "1");
                intent.putExtra("step", "A");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_fwgl_zlxj_1) {
                Intent intent = new Intent(context, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedbackBeanXXX", mFeedbackBeanXXX);
                intent.putExtra("bundle", bundle);
                intent.putExtra("type",mVacancyBean.getType());
                intent.putExtra("s",mVacancyBean.getS());
                intent.putExtra("avatar", mVacancyBean.getAvatar());
                intent.putExtra("nickname", mVacancyBean.getNickname());
                intent.putExtra("is_ren",mVacancyBean.is_ren);
                intent.putExtra("is_hou",mVacancyBean.is_hou);
                intent.putExtra("user_onum",mVacancyBean.getUser_onum());
                intent.putExtra("xing",mVacancyBean.user_xing);
                intent.putExtra("pro_score",mVacancyBean.pro_score);
                intent.putExtra("mobile",mVacancyBean.mobile);
                intent.putExtra("uid",mVacancyBean.getJ_uid());
                intent.putExtra("isXs", true);
                intent.putExtra("time", "1");
                intent.putExtra("step", "B");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_fwgl_tsqkfk_1) {
                Intent intent = new Intent(context, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedbackBeanXXX", mFeedbackBeanXXX);
                intent.putExtra("avatar", mVacancyBean.getAvatar());
                intent.putExtra("nickname", mVacancyBean.getNickname());
                intent.putExtra("is_ren",mVacancyBean.is_ren);
                intent.putExtra("type",mVacancyBean.getType());
                intent.putExtra("s",mVacancyBean.getS());
                intent.putExtra("is_hou",mVacancyBean.is_hou);
                intent.putExtra("xing",mVacancyBean.user_xing);
                intent.putExtra("user_onum",mVacancyBean.getUser_onum());
                intent.putExtra("pro_score",mVacancyBean.pro_score);
                intent.putExtra("mobile",mVacancyBean.mobile);
                intent.putExtra("uid",mVacancyBean.getJ_uid());
                intent.putExtra("is_new",mVacancyBean.getFeedback().get(0).getIs_new());
                intent.putExtra("is_special",mVacancyBean.getFeedback().get(0).getIs_special());
                intent.putExtra("bundle", bundle);
                intent.putExtra("isXs", true);
                intent.putExtra("time", "1");
                intent.putExtra("step", "C");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_fwgl_cqxs_2) {
                Intent intent = new Intent(context, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedbackBeanXXX1", mFeedbackBeanXXX1);
                intent.putExtra("avatar", mVacancyBean.getAvatar());
                intent.putExtra("nickname", mVacancyBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("user_onum",mVacancyBean.getUser_onum());
                intent.putExtra("type",mVacancyBean.getType());
                intent.putExtra("s",mVacancyBean.getS());
                intent.putExtra("is_ren",mVacancyBean.is_ren);
                intent.putExtra("is_hou",mVacancyBean.is_hou);
                intent.putExtra("xing",mVacancyBean.user_xing);
                intent.putExtra("pro_score",mVacancyBean.pro_score);
                intent.putExtra("mobile",mVacancyBean.mobile);
                intent.putExtra("uid",mVacancyBean.getJ_uid());
                intent.putExtra("isXs", true);
                intent.putExtra("time", "2");
                intent.putExtra("step", "A");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_fwgl_zlxj_2) {
                Intent intent = new Intent(context, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedbackBeanXXX1", mFeedbackBeanXXX1);
                intent.putExtra("avatar", mVacancyBean.getAvatar());
                intent.putExtra("nickname", mVacancyBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("isXs", true);
                intent.putExtra("user_onum",mVacancyBean.getUser_onum());
                intent.putExtra("s",mVacancyBean.getS());
                intent.putExtra("type",mVacancyBean.getType());
                intent.putExtra("is_ren",mVacancyBean.is_ren);
                intent.putExtra("is_hou",mVacancyBean.is_hou);
                intent.putExtra("xing",mVacancyBean.user_xing);
                intent.putExtra("pro_score",mVacancyBean.pro_score);
                intent.putExtra("mobile",mVacancyBean.mobile);

                intent.putExtra("uid",mVacancyBean.getJ_uid());
                intent.putExtra("time", "2");
                intent.putExtra("step", "B");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_fwgl_tsqkfk_2) {
                Intent intent = new Intent(context, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedbackBeanXXX1", mFeedbackBeanXXX1);
                intent.putExtra("avatar", mVacancyBean.getAvatar());
                intent.putExtra("nickname", mVacancyBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("s",mVacancyBean.getS());
                intent.putExtra("type",mVacancyBean.getType());
                intent.putExtra("is_ren",mVacancyBean.is_ren);
                intent.putExtra("is_hou",mVacancyBean.is_hou);
                intent.putExtra("xing",mVacancyBean.user_xing);
                intent.putExtra("pro_score",mVacancyBean.pro_score);
                intent.putExtra("user_onum",mVacancyBean.getUser_onum());
                intent.putExtra("mobile",mVacancyBean.mobile);
                intent.putExtra("is_new",mVacancyBean.getFeedback().get(1).getIs_new());
                intent.putExtra("is_special",mVacancyBean.getFeedback().get(1).getIs_special());
                intent.putExtra("uid",mVacancyBean.getJ_uid());
                intent.putExtra("isXs", true);
                intent.putExtra("time", "2");
                intent.putExtra("step", "C");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_zl_gl_zjsq) {//租赁管理部分
                Intent intent = new Intent(context, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback", mFeedback);
                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("isZl", true);
                intent.putExtra("s",mBillBean.getS());
                intent.putExtra("user_onum",mBillBean.getUser_onum());
                intent.putExtra("type",mBillBean.getType());
                intent.putExtra("is_ren",mBillBean.getIs_ren());
                intent.putExtra("is_hou",mBillBean.is_hou);
                intent.putExtra("xing",mBillBean.user_xing);
                intent.putExtra("pro_score",mBillBean.pro_score);
                intent.putExtra("mobile",mBillBean.mobile);

                intent.putExtra("uid",mBillBean.getJ_uid());
                intent.putExtra("step", "A");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_zl_gl_fwzfzt) {
                Intent intent = new Intent(context, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback", mFeedback);
                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("s",mBillBean.getS());
                intent.putExtra("type",mBillBean.getType());
                intent.putExtra("is_ren",mBillBean.getIs_ren());
                intent.putExtra("is_hou",mBillBean.is_hou);
                intent.putExtra("xing",mBillBean.user_xing);
                intent.putExtra("pro_score",mBillBean.pro_score);
                intent.putExtra("mobile",mBillBean.mobile);
                intent.putExtra("user_onum",mBillBean.getUser_onum());
                intent.putExtra("uid",mBillBean.getJ_uid());
                intent.putExtra("isZl", true);
                intent.putExtra("step", "B");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_zl_gl_jgqd) {
                Intent intent = new Intent(context, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback", mFeedback);
                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("s",mBillBean.getS());
                intent.putExtra("type",mBillBean.getType());
                intent.putExtra("user_onum",mBillBean.getUser_onum());
                intent.putExtra("is_ren",mBillBean.getIs_ren());
                intent.putExtra("is_hou",mBillBean.is_hou);
                intent.putExtra("xing",mBillBean.user_xing);
                intent.putExtra("pro_score",mBillBean.pro_score);
                intent.putExtra("mobile",mBillBean.mobile);

                intent.putExtra("isZl", true);
                intent.putExtra("uid",mBillBean.getJ_uid());
                intent.putExtra("step", "C");
                startActivity(intent);
            }
//            else if (view.getId() == R.id.ll_yygl_1) {//园艺管理
//
//            } else if (view.getId() == R.id.ll_yygl_2) {
//
//            } else if (view.getId() == R.id.ll_yygl_3) {
//
//            } else if (view.getId() == R.id.ll_yygl_4) {
//
//            }
            else if (view.getId() == R.id.ll_jzfw_1) {//家政服务
                Intent intent = new Intent(context, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback1", (Serializable) mFeedback1);
                intent.putExtra("avatar", mHomeBean.getAvatar());
                intent.putExtra("nickname", mHomeBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("type",mHomeBean.getType());
                intent.putExtra("is_ren",mHomeBean.is_ren);
                intent.putExtra("user_onum",mHomeBean.getUser_onum());
                intent.putExtra("is_hou",mHomeBean.is_hou);
                intent.putExtra("xing",mHomeBean.user_xing);
                intent.putExtra("pro_score",mHomeBean.pro_score);
                intent.putExtra("mobile",mHomeBean.mobile);

                intent.putExtra("uid",mHousBean.getUid());
                intent.putExtra("isJz", true);
                intent.putExtra("step", "0");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_jzfw_2) {
                Intent intent = new Intent(context, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback1", (Serializable) mFeedback1);
                intent.putExtra("avatar", mHomeBean.getAvatar());
                intent.putExtra("nickname", mHomeBean.getNickname());
                intent.putExtra("type",mHomeBean.getType());
                intent.putExtra("user_onum",mHomeBean.getUser_onum());
                intent.putExtra("is_ren",mHomeBean.is_ren);
                intent.putExtra("is_hou",mHomeBean.is_hou);
                intent.putExtra("xing",mHomeBean.user_xing);
                intent.putExtra("pro_score",mHomeBean.pro_score);
                intent.putExtra("mobile",mHomeBean.mobile);

                intent.putExtra("bundle", bundle);
                intent.putExtra("uid",mHousBean.getUid());
                intent.putExtra("isJz", true);
                intent.putExtra("step", "1");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_jzfw_3) {
                Intent intent = new Intent(context, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback1", (Serializable) mFeedback1);
                intent.putExtra("avatar", mHomeBean.getAvatar());
                intent.putExtra("nickname", mHomeBean.getNickname());

                intent.putExtra("type",mHomeBean.getType());
                intent.putExtra("user_onum",mHomeBean.getUser_onum());
                intent.putExtra("is_ren",mHomeBean.is_ren);
                intent.putExtra("is_hou",mHomeBean.is_hou);
                intent.putExtra("xing",mHomeBean.user_xing);
                intent.putExtra("pro_score",mHomeBean.pro_score);
                intent.putExtra("mobile",mHomeBean.mobile);

                intent.putExtra("bundle", bundle);
                intent.putExtra("uid",mHousBean.getUid());
                intent.putExtra("isJz", true);
                intent.putExtra("step", "2");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_jzfw_4) {
                Intent intent = new Intent(context, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback1", (Serializable) mFeedback1);
                intent.putExtra("avatar", mHomeBean.getAvatar());
                intent.putExtra("nickname", mHomeBean.getNickname());
                intent.putExtra("type",mHomeBean.getType());
                intent.putExtra("is_ren",mHomeBean.is_ren);
                intent.putExtra("user_onum",mHomeBean.getUser_onum());
                intent.putExtra("is_hou",mHomeBean.is_hou);
                intent.putExtra("xing",mHomeBean.user_xing);
                intent.putExtra("pro_score",mHomeBean.pro_score);
                intent.putExtra("mobile",mHomeBean.mobile);

                intent.putExtra("bundle", bundle);
                intent.putExtra("uid",mHousBean.getUid());
                intent.putExtra("isJz", true);
                intent.putExtra("step", "3");
                startActivity(intent);
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                tvaddr.setText(data.getStringExtra("addr"));
                h_id = data.getStringExtra("h_id");
//                ToastUtil.longToast(context, h_id);
                tvaddr.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                getData();
            }
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.vip_house_design_ll_addr:
                Bundle bundle = new Bundle();
                bundle.putString("flag", "design");
                ActivityTools.goNextActivityForResult(context, VipHouseChooseActivity.class, bundle, 100);
                break;
            case R.id.vip_house_design_bt_commit:
                bundle = new Bundle();
                bundle.putString("flag", "-1");
                bundle.putString("design", "1");
                ActivityTools.goNextActivity(context, SelfSupportManageScheme.class, bundle);
                break;
            case R.id.ib_fwgl_1:
                if (!isShow1) {
                    isShow1 = true;
                    ((TextView) view.findViewById(R.id.tv_is_view_all)).setText(getString(R.string.need_order_expand));
                    ((TextView) view.findViewById(R.id.tv_is_view_all)).setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.mipmap.arrow_down_white),null);
                    ll_1_fwgl.setVisibility(View.GONE);
                } else {
                    isShow1 = false;
                    ((TextView) view.findViewById(R.id.tv_is_view_all)).setText(getString(R.string.need_order_hide));
                    ((TextView) view.findViewById(R.id.tv_is_view_all)).setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.mipmap.arrow_up_white),null);
                    ll_1_fwgl.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ib_fwgl_2:
                if (!isShow2) {
                    isShow2 = true;
                    ((TextView) view.findViewById(R.id.tv_is_view_all2)).setText(getString(R.string.need_order_expand));
                    ((TextView) view.findViewById(R.id.tv_is_view_all2)).setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.mipmap.arrow_down_white),null);
                    ll_2_fwgl.setVisibility(View.GONE);
                } else {
                    isShow2 = false;
                    ((TextView) view.findViewById(R.id.tv_is_view_all2)).setText(getString(R.string.need_order_hide));
                    ((TextView) view.findViewById(R.id.tv_is_view_all2)).setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.mipmap.arrow_up_white),null);
                    ll_2_fwgl.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ll_history_month:
                isHisMonth = true;
                hisList.setVisibility(View.VISIBLE);
                thisLine.setVisibility(View.GONE);
                initHisMonth();

                break;
            case R.id.ll_fwgl_this_motht:
                month=getTime();
                isHisMonth = false;
                hisList.setVisibility(View.GONE);
                thisLine.setVisibility(View.VISIBLE);
                getData();
                break;
        }
    }

    @Override
    public void onPullToRefresh() {
        isRefresing = true;
        if(isHisMonth){
            initHisMonth();
        }else{
            getData();
        }
    }
}
