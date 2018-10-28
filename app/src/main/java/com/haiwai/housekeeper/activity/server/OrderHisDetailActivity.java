package com.haiwai.housekeeper.activity.server;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.MapBoxMapActivity;
import com.haiwai.housekeeper.activity.user.ImgViewActivity;
import com.haiwai.housekeeper.activity.user.ProDetailActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.CityEntity;
import com.haiwai.housekeeper.entity.CityLevelEntity;
import com.haiwai.housekeeper.entity.OrderDetailEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.imageloader.ImageLoader;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.CheckJsonUtils;
import com.haiwai.housekeeper.utils.CityUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.PaseJsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SpanUtil;
import com.haiwai.housekeeper.utils.StaticUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.utils.WDUtils;
import com.haiwai.housekeeper.utils.WDUtils2;
import com.haiwai.housekeeper.utils.WenPaseUtils;
import com.haiwai.housekeeper.view.CircleImageView;
import com.haiwai.housekeeper.view.ConPopBig7View;
import com.haiwai.housekeeper.view.ConPopView;
import com.haiwai.housekeeper.view.ProCodeView;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.view.TvOrderView;
import com.haiwai.housekeeper.view.scrollview.MyScrollView;
import com.haiwai.housekeeper.widget.LoadDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

public class OrderHisDetailActivity extends BaseProActivity implements View.OnClickListener {
    private TopViewNormalBar mNormalBar;
    //    private CircleImageView civ_detail_img;
//    private TextView tv_detial_name, tv_title_tel;
////    private ImageView iv_detail_chat;j
//    private TextView tv_by_tel, tv_by_detial_location;
    private ImageView iv_order_show_or_no;
    private LinearLayout ll_order_detail_layout;
    boolean flag = false;
    String id = "";
    String uid = "";
    String ouid = "";
    private User user;
    private OrderDetailEntity mOrderDetailEntity;
    private TextView order_content_title, tv_order_code, tv_order_status;
    private TextView tv_click_order;
    //    private View mPriceView;
//    private TextView ib_modify_price_btn, tv_jcfy_a, tv_jcf_a, tv_rgf_a, tv_input_zgs, tv_clf_gs, tv_clf_a, tv_hj_a;
    private LinearLayout ll_time;
    private RelativeLayout rl_zgs_layout;
    private int is_jie;
    ImageLoader imageLoader;
    private OrderDetailEntity.DataBean.UserBean mUser;
    private OrderDetailEntity.DataBean.OfferBean mOfferBean;
    private OrderDetailEntity.DataBean.DateBean mDateBean;
    private OrderDetailEntity.DataBean.HousBean mHous;
    private String isZhorEn = "";

    String offid = "";
    String type = "";
    String oid = "";
    String home_fee = "";
    //String inspection = "";
    String hourly = "";
    String hour = "";
    String general = "";
    String material = "";
    String message = "";
    private String mService_type;
    //--------------------------------推荐服务历史-------------------
    private View mTjfwView;
    private CircleImageView civ_tj_img;
    private ImageView tv_addr_sfrz;
    private TextView tv_addr_tj, tv_tj_bjfs, tv_tj_style, tv_tj_money, tv_tj_advantage, tv_tj_phone;
    private LinearLayout ll_tj_advantage_layout, ll_tj_pri_layout;
    private LinearLayout ll_3_layout;
    //    private View mTopView1;
    //-------------------------最新内容------------------
    View topNewView;
    private CircleImageView need_doing_order_detail_iv_head;
    private TextView need_doing_order_detail_tv_name, ib_modify_price_btn, need_doing_order_detail_tv_pf, tv_all_moneys, tv_hour_money, tv_hour, tv_input_zgs, tv_debj, tv_sfm, tv_addr, tv_tel, tv_by_tel;
    private ImageView need_doing_order_detail_iv_sfrz, need_doing_order_detail_phone, need_doing_order_detail_iv_msg;
    private LinearLayout ll_price_layout, ll_hour_layout, ll_addr_layout;
    private RelativeLayout rl_all_layout;
    private LinearLayout ll_code_view;
    private MyScrollView ll_sw_layout;
    boolean isRefresh = false;
    private ImageView iv_location;

    private ScrollView is_showing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_order_no_detail);
        mNormalBar = (TopViewNormalBar) findViewById(R.id.top_detail_bar);
        mNormalBar.setTitle(R.string.need_order_detail);
        mNormalBar.setOnBackListener(this);
        imageLoader = new ImageLoader(this);
        initView();
        iniData();


    }

    private String TAG = this.getClass().getName() + "--kxf";

    private void iniData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        user = AppGlobal.getInstance().getUser();
        if (user != null) {
            uid = user.getUid();
        }
        LoadDialog.showProgressDialog(this);
        id = getIntent().getStringExtra("id");
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        map.put("secret_key", SPUtils.getString(OrderHisDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderHisDetailActivity.this, Contants.order_detail, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                System.out.println(">>>历史订单详情>>>" + response);
                int code = JsonUtils.getJsonInt(response, "status");
                is_showing.setVisibility(View.VISIBLE);
                mHandler.sendEmptyMessageDelayed(100, 1000);
                // LoadDialog.closeProgressDialog();
                if (200 == code) {
                    mOrderDetailEntity = PaseJsonUtils.getOrderDetailEntity(response);
                    WDUtils.getWDMap(mOrderDetailEntity.getData().getDate());
                    bindDataToView(mOrderDetailEntity, response);
                } else {
                    ToastUtil.shortToast(OrderHisDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
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

    private void initView() {
        is_showing = (ScrollView) findViewById(R.id.is_showing);
        is_showing.setVisibility(View.GONE);
        topNewView = findViewById(R.id.pro_new_top_content);
        initView(topNewView);
//        mTopView1 = findViewById(R.id.top_card_layout);
//        initTopView(mTopView1);
        mTjfwView = findViewById(R.id.order_his_tjfw_layout);
        ll_code_view = (LinearLayout) findViewById(R.id.ll_code_view);
        initTjfwView(mTjfwView);
//        mPriceView = findViewById(R.id.order_price_layout);
//        initPriceView(mPriceView);
        iv_order_show_or_no = (ImageView) findViewById(R.id.iv_order_show_or_no);
        if ("en".equals(isZhorEn)) {
            iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_order_show_en);
        } else {
            iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_order_show);
        }
        iv_order_show_or_no.setOnClickListener(this);
        ll_order_detail_layout = (LinearLayout) findViewById(R.id.ll_order_detail_layout);
        order_content_title = (TextView) findViewById(R.id.order_content_title);
        tv_order_code = (TextView) findViewById(R.id.tv_order_code);
        tv_order_status = (TextView) findViewById(R.id.tv_order_status);
        tv_click_order = (TextView) findViewById(R.id.tv_click_order);
        tv_click_order.setText(R.string.ckpj_t);
        tv_click_order.setOnClickListener(this);

    }

    private TextView tv_yb;
    private TextView tv_al;

    private void initView(View topNewView) {
        tv_al = ((TextView) topNewView.findViewById(R.id.tv_by_tel_phone));
        tv_yb = ((TextView) topNewView.findViewById(R.id.tv_code_yb));
        need_doing_order_detail_iv_head = (CircleImageView) topNewView.findViewById(R.id.need_doing_order_detail_iv_head);
        need_doing_order_detail_tv_name = (TextView) topNewView.findViewById(R.id.need_doing_order_detail_tv_name);
        need_doing_order_detail_tv_pf = (TextView) topNewView.findViewById(R.id.need_doing_order_detail_tv_pf);
        tv_all_moneys = (TextView) topNewView.findViewById(R.id.tv_all_moneys);
        tv_hour_money = (TextView) topNewView.findViewById(R.id.tv_hour_money);
        tv_hour = (TextView) topNewView.findViewById(R.id.tv_hour);
        tv_input_zgs = (TextView) topNewView.findViewById(R.id.tv_input_zgs);
        tv_input_zgs.setOnClickListener(this);
        tv_debj = (TextView) topNewView.findViewById(R.id.tv_debj);
        tv_sfm = (TextView) topNewView.findViewById(R.id.tv_sfm);
        // tv_jcf = (TextView) topNewView.findViewById(R.id.tv_jcf);
        tv_addr = (TextView) topNewView.findViewById(R.id.tv_addr);

        tv_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMap = new Intent(OrderHisDetailActivity.this, MapBoxMapActivity.class);
                intentMap.putExtra("isMap", true);
                intentMap.putExtra("lat", mHous == null ? "" : mHous.getLat());
                intentMap.putExtra("lng", mHous == null ? "" : mHous.getLongX());
                startActivity(intentMap);
            }
        });

        tv_tel = (TextView) topNewView.findViewById(R.id.tv_tel);
        tv_by_tel = (TextView) topNewView.findViewById(R.id.tv_by_tel);
        ib_modify_price_btn = (TextView) topNewView.findViewById(R.id.ib_modify_price_btn);
        ib_modify_price_btn.setOnClickListener(this);
        need_doing_order_detail_iv_sfrz = (ImageView) topNewView.findViewById(R.id.need_doing_order_detail_iv_sfrz);
        need_doing_order_detail_phone = (ImageView) topNewView.findViewById(R.id.need_doing_order_detail_phone);
        need_doing_order_detail_phone.setOnClickListener(this);
        need_doing_order_detail_iv_msg = (ImageView) topNewView.findViewById(R.id.need_doing_order_detail_iv_msg);
        need_doing_order_detail_iv_msg.setOnClickListener(this);
        ll_price_layout = (LinearLayout) topNewView.findViewById(R.id.ll_price_layout);
        ll_hour_layout = (LinearLayout) topNewView.findViewById(R.id.ll_hour_layout);
        ll_addr_layout = (LinearLayout) topNewView.findViewById(R.id.ll_addr_layout);
        rl_all_layout = (RelativeLayout) topNewView.findViewById(R.id.rl_all_layout);
        iv_location = (ImageView) topNewView.findViewById(R.id.iv_location);
        iv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderHisDetailActivity.this, MapBoxMapActivity.class);
                intent.putExtra("isMap", true);
                intent.putExtra("lat", mHous == null ? "" : mHous.getLat());
                intent.putExtra("lng", mHous == null ? "" : mHous.getLongX());
                startActivity(intent);
            }
        });
        TextView tv_click_view = (TextView) topNewView.findViewById(R.id.tv_click_view);
        tv_click_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ConPopBig7View cpsv = new ConPopBig7View(OrderHisDetailActivity.this, "");
                cpsv.showPopUpWindow(view);
                cpsv.getIv_diss().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cpsv.dismiss();
                    }
                });
            }
        });

        need_doing_order_detail_iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("uid", mOrderDetailEntity.getData().getUser().getUid());
                bundle.putString("nickname", mOrderDetailEntity.getData().getUser().getNickname());
                bundle.putString("type", "");
                bundle.putString("choose", "0");
                bundle.putString("oid", "");
                bundle.putBoolean("isServer", true);
                ActivityTools.goNextActivity(OrderHisDetailActivity.this, ProDetailActivity.class, bundle);
            }
        });
    }

    private void initTjfwView(View tjfwView) {
        civ_tj_img = (CircleImageView) tjfwView.findViewById(R.id.civ_tj_img);

        ImageView call_phone = (ImageView) tjfwView.findViewById(R.id.tv_addr_phone_call);
        call_phone.setVisibility(View.GONE);
        tv_addr_sfrz = (ImageView) tjfwView.findViewById(R.id.tv_addr_sfrz);
        tv_addr_tj = (TextView) tjfwView.findViewById(R.id.tv_addr_tj);
        tv_tj_bjfs = (TextView) tjfwView.findViewById(R.id.tv_tj_bjfs);
        tv_tj_style = (TextView) tjfwView.findViewById(R.id.tv_tj_style);
        tv_tj_money = (TextView) tjfwView.findViewById(R.id.tv_tj_money);
        tv_tj_phone = (TextView) tjfwView.findViewById(R.id.tv_addr_phone);
        tv_tj_advantage = (TextView) tjfwView.findViewById(R.id.tv_tj_advantage);
        ll_tj_advantage_layout = (LinearLayout) tjfwView.findViewById(R.id.ll_tj_advantage_layout);
        ll_tj_pri_layout = (LinearLayout) tjfwView.findViewById(R.id.ll_tj_pri_layout);
        ll_3_layout = (LinearLayout) tjfwView.findViewById(R.id.ll_3_layout);
    }

//    private void initPriceView(View priceView) {
//        ib_modify_price_btn = (TextView) priceView.findViewById(R.id.ib_modify_price_btn);
//        ib_modify_price_btn.setVisibility(View.GONE);
//        tv_jcfy_a = (TextView) priceView.findViewById(R.id.tv_jcfy_a);
//        tv_jcf_a = (TextView) priceView.findViewById(R.id.tv_jcf_a);
//        tv_rgf_a = (TextView) priceView.findViewById(R.id.tv_rgf_a);
//        tv_clf_gs = (TextView) priceView.findViewById(R.id.tv_clf_gs);
//        tv_input_zgs = (TextView) priceView.findViewById(R.id.tv_input_zgs);
//        tv_input_zgs.setVisibility(View.GONE);
//        tv_clf_a = (TextView) priceView.findViewById(R.id.tv_clf_a);
//        tv_hj_a = (TextView) priceView.findViewById(R.id.tv_hj_a);
//        ll_time = (LinearLayout) priceView.findViewById(R.id.ll_time);
//        rl_zgs_layout = (RelativeLayout) priceView.findViewById(R.id.rl_zgs_layout);
//    }
//
//    private void initTopView(View topView) {
//        civ_detail_img = (CircleImageView) topView.findViewById(R.id.civ_detail_img);
//        civ_detail_img.setOnClickListener(this);
//        tv_detial_name = (TextView) topView.findViewById(R.id.tv_detial_name);
//        tv_title_tel = (TextView) topView.findViewById(R.id.tv_title_tel);
//        iv_detail_chat = (ImageView) topView.findViewById(R.id.iv_detail_chat);
//        tv_by_tel = (TextView) topView.findViewById(R.id.tv_by_tel);
//        tv_by_detial_location = (TextView) topView.findViewById(R.id.tv_title_location);
//    }

    private void bindDataToView(OrderDetailEntity orderDetailEntity, final String response) {
        mUser = orderDetailEntity.getData().getUser();

        if (mUser != null) {
            if (!TextUtils.isEmpty(mUser.getNickname())) {
                need_doing_order_detail_tv_name.setText(mUser.getNickname());
            }

            tv_addr_tj.setText(mUser.getNickname());
            tv_tj_phone.setText(mUser.getMobile());
            if (!mUser.getIs_ren().equals("1")) {
                need_doing_order_detail_iv_sfrz.setImageResource(R.mipmap.o2o_item_sf_grey);
            }
        }
        try {
            String user_score = new JSONObject(response).getJSONObject("data").getJSONObject("user").getString("user_xing");
            String order_num = new JSONObject(response).getJSONObject("data").getJSONObject("user").getString("user_onum");
            if (order_num.equals("") || Float.valueOf(order_num) == 0) {
                need_doing_order_detail_tv_pf.setText(getString(R.string.evaluate_title) + "0.0");

            } else {
                need_doing_order_detail_tv_pf.setText(getString(R.string.evaluate_title) + String.format("%.1f", Float.valueOf(user_score) / Float.valueOf(order_num)));

            }
        } catch (Exception e) {

        }

        mOfferBean = orderDetailEntity.getData().getOffer();
        mDateBean = orderDetailEntity.getData().getDate();
        mHous = orderDetailEntity.getData().getHous();
        if (mUser != null) {
            ouid = mUser.getUid();
        }
        order_content_title.setText(AssetsUtils.getSkillName(mDateBean.getType(), isZhorEn));
        tv_order_code.setText(mDateBean.getOrder_id());
        if (orderDetailEntity.getData().getDate().getStaticX().equals("11")) {
            tv_order_status.setText(getString(R.string.all_str34));
        } else {
            tv_order_status.setText(getString(R.string.zt23));
        }


        if (Integer.valueOf(mDateBean.getType()) > 18) {
            topNewView.setVisibility(View.GONE);
            mTjfwView.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(mUser.getAvatar())) {
                imageLoader.DisplayImage(mUser.getAvatar(), civ_tj_img);


            } else {
                civ_tj_img.setImageResource(R.mipmap.moren_head);
            }
            String sfrz = mUser.getIs_ren();//认证
            if ("1".equals(sfrz)) {
                tv_addr_sfrz.setImageResource(R.mipmap.shenfenrenzheng_cardlv);
            } else {
                tv_addr_sfrz.setImageResource(R.mipmap.o2o_item_sf_grey);
            }

            if ("3".equals(mOfferBean.getService_type())) {
                tv_tj_bjfs.setText(R.string.jtqk);
                ll_3_layout.setVisibility(View.GONE);
            } else if ("2".equals(mOfferBean.getService_type())) {
                ll_3_layout.setVisibility(View.VISIBLE);
                tv_tj_bjfs.setText(R.string.all_counts);
                tv_tj_style.setText(R.string.zj_m);
                tv_tj_money.setText(mOfferBean.getGeneral());
            } else if ("1".equals(mOfferBean.getService_type())) {
                ll_3_layout.setVisibility(View.VISIBLE);
                tv_tj_bjfs.setText(R.string.gsfss);
                tv_tj_style.setText(R.string.gsf_m);
                tv_tj_money.setText(mOfferBean.getHourly());
            }
            tv_tj_advantage.setText(mOfferBean.getMessage());
        } else {
            topNewView.setVisibility(View.VISIBLE);
            mTjfwView.setVisibility(View.GONE);
///小圆点

            ProCodeView proCodeView = new ProCodeView(OrderHisDetailActivity.this);
            ll_code_view.removeAllViews();
            //kxf 新增，A发布活动，BC订购，A确认使用B，C这里没有对订单详情进行处理仍沿用B的逻辑。18-4-20
            if ("1".equals(mOfferBean.getIs_xuan()))
                proCodeView.setNode("101");
            else
                proCodeView.setNode("102");
            ll_code_view.addView(proCodeView);

            if (!TextUtils.isEmpty(mUser.getAvatar())) {
                imageLoader.DisplayImage(mUser.getAvatar(), need_doing_order_detail_iv_head);
            } else {
                need_doing_order_detail_iv_head.setImageResource(R.mipmap.moren_head);
            }
            offid = mOfferBean.getId();
            home_fee = mOfferBean.getHome_fee();//上门费
            mService_type = mOfferBean.getService_type();
            //   inspection = mOfferBean.getInspection();//检测费
            message = mOfferBean.getMessage();
            general = mOfferBean.getGeneral();//定额费用
            if (!TextUtils.isEmpty(mOfferBean.getHour())) {
                hour = mOfferBean.getHour();//小时
            }
            hourly = mOfferBean.getHourly();//时薪
            is_jie = mOfferBean.getIs_jie();
            String staticX = mDateBean.getStaticX();

            if ("12".equals(staticX)) {
                ll_code_view.setVisibility(View.GONE);
                tv_click_order.setVisibility(View.GONE);//已报价接单
            } else if ("13".equals(staticX)) {
                ll_code_view.setVisibility(View.GONE);
                tv_click_order.setVisibility(View.GONE);
            }


            Map<String, String> map = new HashMap<>();
            map.put("secret_key", SPUtils.getString(OrderHisDetailActivity.this, "secret", ""));
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderHisDetailActivity.this, Contants.city_lst, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String res) {
                    System.out.println(">>>>>>>>>>城市列表>>" + res);
                    int code = JsonUtils.getJsonInt(res, "status");
                    if (code == 200) {
                        CityEntity mEntity = CityUtils.parseCity(res);
                        ArrayList<CityLevelEntity> mList = CityUtils.getLevelList("3", "23", mEntity);

                        for (int i = 0; i < mList.size(); i++) {
                            if (mHous.getCity().equals(mList.get(i).getId())) {

                                try {
                                    String stree = new JSONObject(response).getJSONObject("data").getJSONObject("hous").getString("street");
                                    String house_number = new JSONObject(response).getJSONObject("data").getJSONObject("hous").getString("house_number");
                                    if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                        tv_addr.setText("CanadaBritish Columbia " + mList.get(i).getYname() + " " + house_number + stree);
                                    } else {
                                        tv_addr.setText("加拿大不列颠哥伦比亚省 " + mList.get(i).getYname() + " " + stree + house_number);
                                    }
                                } catch (Exception e) {

                                }
                                break;
                            }
                        }

                    } else {
                        LoadDialog.closeProgressDialog();
                        ToastUtil.longToast(OrderHisDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }));


            if (is_jie == 1) {
                if (mOfferBean.getIs_jie() == 1) {

                    if (mHous != null) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            String email = obj.getJSONObject("data").getJSONObject("hous").getString("email");
                            String contact_number = obj.getJSONObject("data").getJSONObject("hous").getString("contact_number");
                            String alternate_contact = obj.getJSONObject("data").getJSONObject("hous").getString("alternate_contact");
                            String yb = obj.getJSONObject("data").getJSONObject("hous").getString("zip_code");
                            String al = obj.getJSONObject("data").getJSONObject("hous").getString("alternate_contact_number");
                            tv_yb.setText(yb);
                            tv_al.setText(al);
                            ((TextView) findViewById(R.id.tv_email)).setText(email);
                            tv_tel.setText(contact_number);
                            ((TextView) findViewById(R.id.tv_by_tel)).setText(alternate_contact);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }


                    tv_sfm.setText("$" + home_fee);
                    //  tv_jcf.setText("$" + inspection);
                    if ("1".equals(mService_type)) {
                        ll_price_layout.setVisibility(View.VISIBLE);
                        ll_hour_layout.setVisibility(View.VISIBLE);
                        rl_all_layout.setVisibility(View.GONE);
                        tv_input_zgs.setVisibility(View.GONE);
                        tv_hour_money.setText("$" + hourly + getString(R.string.time));
                        if (TextUtils.isEmpty(hour)) {
                            tv_hour.setText(R.string.dd);
                            tv_all_moneys.setText(R.string.dd);
                        } else {
                            tv_hour.setText(hour);
                            float f1 = 0.0f, f2 = 0.0f, f3 = 0.0f, f4 = 0.0f, f5 = 0.0f;
//                            if (!TextUtils.isEmpty(home_fee)) {
//                                f1 = Float.valueOf(home_fee);
//                            }
//                            if (!TextUtils.isEmpty(inspection)) {
//                                f2 = Float.valueOf(inspection);
//                            }
                            if (!TextUtils.isEmpty(hourly)) {
                                f3 = Float.valueOf(hourly);
                            }
                            if (!TextUtils.isEmpty(hour)) {
                                f4 = Float.valueOf(hour);
                            }
                            f5 = f3 * f4;
                            tv_all_moneys.setText("$" + f5);
                        }
                    } else if ("2".equals(mService_type)) {
                        float smf = 0.0f, jcf = 0.0f, debj = 0.0f;
                        ll_price_layout.setVisibility(View.VISIBLE);
                        ll_hour_layout.setVisibility(View.GONE);
                        rl_all_layout.setVisibility(View.VISIBLE);
                        tv_input_zgs.setVisibility(View.GONE);
                        tv_debj.setText("$" + general);
//                        if (!TextUtils.isEmpty(home_fee)) {
//                            smf = Float.valueOf(home_fee);
//                        }
//                        if (!TextUtils.isEmpty(inspection)) {
//                            jcf = Float.valueOf(inspection);
//                        }
//                        if (!TextUtils.isEmpty(general)) {
//                            debj = Float.valueOf(general);
//                        }
                        float count = smf + jcf + debj;
                        tv_all_moneys.setText("$" + general);
                    }
                } else {
                    tv_sfm.setText("$0.00");
                    // tv_jcf.setText("$0.00");
                    tv_all_moneys.setText("$0.00");
                    tv_debj.setText("$0.00");
                }
            }
        }

        ll_order_detail_layout.removeAllViews();
        String skillType = mOrderDetailEntity.getData().getDate().getType();
        List<TvOrderView> viewList = new ArrayList<>();
        for (int i = 1; i <= WDUtils.count; i++) {
            TvOrderView tvOrderView = new TvOrderView(this);
            String strKey = "wen" + i;
            String valueStr = "da" + i;
            if (!TextUtils.isEmpty(WDUtils.getWenStr(strKey))) {
//                    Log.i("statusInformation",WDUtils.getWenStr(strKey)+"----"+WenPaseUtils.getWenStr(NeedOrderDetailActivity.this, strKey,
//                            Integer.valueOf(entity.getData().getDate().getType()), isZhorEn)+"---"+WDUtils.getDaStr(valueStr));
                if (0 == CheckJsonUtils.getStatuss(WDUtils.getDaStr(valueStr))) {//整数处理
//                    tvOrderView.setViewText(WDUtils.getWenStr(strKey),
//                            WenPaseUtils.getDaStr(OrderDetailActivity.this, valueStr,
//                                    Integer.valueOf(WDUtils.getDaStr(valueStr)),
//                                    Integer.valueOf(orderDetailEntity.getData().getDate().getType()),"zh"));
                    tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderHisDetailActivity.this, strKey,
                            Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn),
                            WenPaseUtils.getDaStr(OrderHisDetailActivity.this, valueStr,
                                    Integer.valueOf(WDUtils.getDaStr(valueStr)),
                                    Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn));
                } else if (1 == CheckJsonUtils.getStatuss(WDUtils.getDaStr(valueStr))) {//Json处理
//                    tvOrderView.setViewText(WDUtils.getWenStr(strKey),
//                            "json");
                    int a = CheckJsonUtils.getJsonType(WDUtils.getDaStr(valueStr));
                    if (0 == a) {//纯数字json
                        List<String> strList = CheckJsonUtils.getStrList(OrderHisDetailActivity.this, Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), i, WDUtils.getDaStr(valueStr), isZhorEn);

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
//                        tvOrderView.setViewText(WDUtils.getWenStr(strKey), str.toString());
                        tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderHisDetailActivity.this, strKey,
                                Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn), str.toString());


                    } else if (1 == a) {//指定日期类型json

                        List<String> timeList = CheckJsonUtils.getTimeStr(OrderHisDetailActivity.this, Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), i, WDUtils.getDaStr(valueStr), isZhorEn);
                        StringBuilder str = new StringBuilder();
                        Log.i("time_information", a + "--" + this.mOrderDetailEntity.getData().getDate().getType() + "----" + WDUtils.getDaStr(valueStr) + "---" + timeList.size() + "---" + i);

                        if (timeList != null && timeList.size() > 1) {
//                                str = str.append(TimeUtils.getStr2Time(timeList.get(2))).append("\n");
                            str = str.append(timeList.get(1)).append("\n");
                            if (timeList.size() > 2) {
                                str = str.append(timeList.get(2)).append("\n");
                            }
                            if (str.toString().endsWith("\n")) {
                                str = str.deleteCharAt(str.lastIndexOf("\n"));
                            }
                            Log.i("str__imofrmation", str.toString());
                        } else {
                            for (int m = 0; m < timeList.size(); m++) {
                                str = str.append(timeList.get(m)).append("\n");
                                Log.i("str__imofrmation__size", str.toString());
                            }
                            if (str.toString().endsWith("\n")) {
                                str = str.deleteCharAt(str.lastIndexOf("\n"));
                            }

                            try {
                                String mData = new JSONObject(WDUtils.getDaStr(valueStr)).optString("2");
                                String mTime = new JSONObject(WDUtils.getDaStr(valueStr)).optString("3");
                                String longTime = new JSONObject(WDUtils.getDaStr(valueStr)).optString("4");
                                str.append("\n" + getString(R.string.o2o_detail_fwbj8_choose_date) + ":" + TimeUtils.getDate(mData.substring(3, mData.length())) + "\n");
                                str.append(getString(R.string.o2o_detail_fwbj8_choose_time) + ":" + TimeUtils.getTime(mTime.substring(3, mTime.length())) + "\n");
                                str.append(getString(R.string.o2o_detail_fwbj8_for_long) + ":" + longTime);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
//                        tvOrderView.setViewText(WDUtils.getWenStr(strKey), str.toString());
                        tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderHisDetailActivity.this, strKey,
                                Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn), str.toString());
                    } else if (2 == a) {//图片类型json
                        final List<String> pathList = CheckJsonUtils.getPicStr(OrderHisDetailActivity.this, Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), i, WDUtils.getDaStr(valueStr), isZhorEn);
                        if (pathList != null && pathList.size() > 0) {
                            tvOrderView.setTvWenText(WenPaseUtils.getWenStr(OrderHisDetailActivity.this, strKey,
                                    Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn));
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
                                            Intent intent = new Intent(OrderHisDetailActivity.this, ImgViewActivity.class);
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
//                            tvOrderView.setViewText(WDUtils.getWenStr(strKey), str.toString());
                            tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderHisDetailActivity.this, strKey,
                                    Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn), str.toString());
                        }

                    }
                } else if (2 == CheckJsonUtils.getStatuss(WDUtils.getDaStr(valueStr))) {//字符处理
                    String str = WDUtils.getDaStr(valueStr);

                    if (str.length() > 3) {
//                        tvOrderView.setViewText(WDUtils.getWenStr(strKey),
//                                str.substring(3, str.length()));

                        tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderHisDetailActivity.this, strKey,
                                Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn),
                                str.substring(3, str.length()));
                    } else {
                        tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderHisDetailActivity.this, strKey,
                                Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn),
                                "");
                    }
                }
                if (!skillType.equals("9")) {
                    ll_order_detail_layout.addView(tvOrderView);
                } else {
                    viewList.add(tvOrderView);
                }
            }
        }

        if (skillType.equals("9")) {
            for (int i = 0; i < 2; i++) {
                ll_order_detail_layout.addView(viewList.get(i));
            }
            ll_order_detail_layout.addView(viewList.get(3));
            ll_order_detail_layout.addView(viewList.get(2));
            ll_order_detail_layout.addView(viewList.get(5));
            ll_order_detail_layout.addView(viewList.get(4));

//            ll_order_detail_layout.addView(viewList.get(3));
//            ll_order_detail_layout.addView(viewList.get(2));
            for (int i = 6; i < viewList.size(); i++) {
                ll_order_detail_layout.addView(viewList.get(i));
            }
        }


//        ll_order_detail_layout.removeAllViews();
//        String skillType = mOrderDetailEntity.getData().getDate().getType();
//        List<TvOrderView> viewList = new ArrayList<>();
//        for (int i = 1; i <= WDUtils.count; i++) {
//            TvOrderView tvOrderView = new TvOrderView(this);
//            String strKey = "wen" + i;
//            String valueStr = "da" + i;
//            if (!TextUtils.isEmpty(WDUtils.getWenStr(strKey))) {
////                    Log.i("statusInformation",WDUtils.getWenStr(strKey)+"----"+WenPaseUtils.getWenStr(NeedOrderDetailActivity.this, strKey,
////                            Integer.valueOf(entity.getData().getDate().getType()), isZhorEn)+"---"+WDUtils2.getDaStr(valueStr));
//                if (0 == CheckJsonUtils.getStatuss(WDUtils.getDaStr(valueStr))) {//整数处理
////                    tvOrderView.setViewText(WDUtils2.getWenStr(strKey),
////                            WenPaseUtils.getDaStr(OrderDetailActivity.this, valueStr,
////                                    Integer.valueOf(WDUtils2.getDaStr(valueStr)),
////                                    Integer.valueOf(orderDetailEntity.getData().getDate().getType()),"zh"));
//                    tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderHisDetailActivity.this, strKey,
//                            Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn),
//                            WenPaseUtils.getDaStr(OrderHisDetailActivity.this, valueStr,
//                                    Integer.valueOf(WDUtils.getDaStr(valueStr)),
//                                    Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn));
//                } else if (1 == CheckJsonUtils.getStatuss(WDUtils.getDaStr(valueStr))) {//Json处理
////                    tvOrderView.setViewText(WDUtils2.getWenStr(strKey),
////                            "json");
//                    int a = CheckJsonUtils.getJsonType(WDUtils.getDaStr(valueStr));
//                    if (0 == a) {//纯数字json
//                        List<String> strList = CheckJsonUtils.getStrList(OrderHisDetailActivity.this, Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), i, WDUtils.getDaStr(valueStr), isZhorEn);
//
//                        StringBuilder str = new StringBuilder();
//                        for (int m = 0; m < strList.size(); m++) {
//                            if (strList.size() > 1) {
//                                str = str.append(strList.get(m)).append("\n");
//                            } else {
//                                str = str.append(strList.get(m));
//                            }
//                        }
//                        if (str.toString().endsWith("\n")) {
//                            str = str.deleteCharAt(str.lastIndexOf("\n"));
//                        }
////                        tvOrderView.setViewText(WDUtils2.getWenStr(strKey), str.toString());
//                        tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderHisDetailActivity.this, strKey,
//                                Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn), str.toString());
//
//
//                    } else if (1 == a) {//指定日期类型json
//
//                        List<String> timeList = CheckJsonUtils.getTimeStr(OrderHisDetailActivity.this, Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), i, WDUtils.getDaStr(valueStr), isZhorEn);
//                        StringBuilder str = new StringBuilder();
//                        Log.i("time_information", a + "--" + this.mOrderDetailEntity.getData().getDate().getType() + "----" + WDUtils.getDaStr(valueStr) + "---" + timeList.size() + "---" + i);
//
//                        if (timeList != null && timeList.size() > 1) {
//                            str = str.append(TimeUtils.getStr2Time(timeList.get(2))).append("\n");
//
//                            if (timeList.size() > 2) {
//                                str = str.append(timeList.get(3)).append("\n");
//                            }
//                            if (str.toString().endsWith("\n")) {
//                                str = str.deleteCharAt(str.lastIndexOf("\n"));
//                            }
//                            Log.i("str__imofrmation", str.toString());
//                        } else {
//                            for (int m = 0; m < timeList.size(); m++) {
//                                str = str.append(timeList.get(m)).append("\n");
//                                Log.i("str__imofrmation__size", str.toString());
//                            }
//                            if (str.toString().endsWith("\n")) {
//                                str = str.deleteCharAt(str.lastIndexOf("\n"));
//                            }
//
//                            try {
//                                String mData = new JSONObject(WDUtils.getDaStr(valueStr)).optString("2");
//                                String mTime = new JSONObject(WDUtils.getDaStr(valueStr)).optString("3");
//                                String longTime = new JSONObject(WDUtils.getDaStr(valueStr)).optString("4");
//                                str.append("\n" + getString(R.string.o2o_detail_fwbj8_choose_date) + ":" + TimeUtils.getDate(mData.substring(3, mData.length())) + "\n");
//                                str.append(getString(R.string.o2o_detail_fwbj8_choose_time) + ":" + TimeUtils.getTime(mTime.substring(3, mTime.length())) + "\n");
//                                str.append(getString(R.string.o2o_detail_fwbj8_for_long) + ":" + longTime);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//
//                        }
////                        tvOrderView.setViewText(WDUtils2.getWenStr(strKey), str.toString());
//                        tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderHisDetailActivity.this, strKey,
//                                Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn), str.toString());
//                    } else if (2 == a) {//图片类型json
//                        final List<String> pathList = CheckJsonUtils.getPicStr(OrderHisDetailActivity.this, Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), i, WDUtils.getDaStr(valueStr), isZhorEn);
//                        if (pathList != null && pathList.size() > 0) {
//                            tvOrderView.setTvWenText(WDUtils.getWenStr(strKey));
//                            for (int m = 0; m < pathList.size(); m++) {
//                                if (pathList.get(m).contains(".png") || pathList.get(m).contains(".jpg") || pathList.get(m).contains(".jpeg")) {
//                                    tvOrderView.setLayoutVisible(true);
//                                    ImageView img = new ImageView(this);
//                                    final int imgPosition = m;
//                                    LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(120, 120);
//                                    img.setPadding(32, 10, 0, 10);
//                                    img.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            Intent intent = new Intent(OrderHisDetailActivity.this, ImgViewActivity.class);
//                                            intent.putExtra("img", pathList.get(imgPosition));
//                                            startActivity(intent);
//                                        }
//                                    });
//                                    com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(pathList.get(m), img);
//                                    tvOrderView.getImgLayout().addView(img, layoutParam);
//                                } else {
//                                    tvOrderView.setLayoutVisible(false);
//                                    tvOrderView.setTvDaText(pathList.get(0));
//                                }
//                            }
//
//                        } else {
//                            StringBuilder str = new StringBuilder();
//                            for (int m = 0; m < pathList.size(); m++) {
//                                str = str.append(pathList.get(m)).append("\n");
//                            }
//                            if (str.toString().endsWith("\n")) {
//                                str = str.deleteCharAt(str.lastIndexOf("\n"));
//                            }
//                            tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderHisDetailActivity.this, strKey,
//                                    Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn), str.toString());
//                        }
//
//                    }
//                } else if (2 == CheckJsonUtils.getStatuss(WDUtils.getDaStr(valueStr))) {//字符处理
//                    String str = WDUtils.getDaStr(valueStr);
//
//                    if (str.length() > 3) {
////                        tvOrderView.setViewText(WDUtils.getWenStr(strKey),
////                                str.substring(3, str.length()));
//
//                        tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderHisDetailActivity.this, strKey,
//                                Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn),
//                                str.substring(3, str.length()));
//                    } else {
//                        tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderHisDetailActivity.this, strKey,
//                                Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn),
//                                "");
//                    }
//                }
//                if (!skillType.equals("9")) {
//                    ll_order_detail_layout.addView(tvOrderView);
//                } else {
//                    viewList.add(tvOrderView);
//                }
//            }
//        }
//
//        if (skillType.equals("9")) {
//            for (int i = 0; i < 2; i++) {
//                ll_order_detail_layout.addView(viewList.get(i));
//            }
//            ll_order_detail_layout.addView(viewList.get(3));
//            ll_order_detail_layout.addView(viewList.get(2));
//            for (int i = 4; i < viewList.size(); i++) {
//                ll_order_detail_layout.addView(viewList.get(i));
//            }
//        }
    }


    @Override
    public void onClick(View v) {
        if (v == mNormalBar.getBackView()) {
            finish();
        } else if (v.getId() == R.id.iv_order_show_or_no) {
            if (!flag) {
                flag = true;
                ll_order_detail_layout.setVisibility(View.VISIBLE);
                if ("en".equals(isZhorEn)) {
                    iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_btn_hide_en);
                } else {
                    iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_btn_hide);
                }
            } else {
                flag = false;
                ll_order_detail_layout.setVisibility(View.GONE);
                if ("en".equals(isZhorEn)) {
                    iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_order_show_en);
                } else {
                    iv_order_show_or_no.setBackgroundResource(R.mipmap.pro_order_show);
                }
            }
        } else if (v.getId() == R.id.tv_click_order) {//
            Intent intent = new Intent(OrderHisDetailActivity.this, ProEvaluateShowActivity.class);
            intent.putExtra("oid", mDateBean.getId());
            intent.putExtra("ouid", mDateBean.getUid());
            startActivity(intent);
        } else if (v.getId() == R.id.need_doing_order_detail_iv_msg) {
            if (!"0".equals(mOrderDetailEntity.getData().getLiao())) {
                String targetId = mUser.getUid();
                String title = mUser.getNickname();
                IMKitService.proDetailMap.put("oid", mDateBean.getId());
                IMKitService.proDetailMap.put("type", mDateBean.getType());
                if (RongIM.getInstance() != null) {
                    /**
                     * 启动单聊界面。
                     *
                     * @param context      应用上下文。
                     * @param targetUserId 要与之聊天的用户 Id。
                     * @param title        聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
                     */
                    RongIM.getInstance().startPrivateChat(this, targetId, title);
                    RongIM.getInstance().setCurrentUserInfo(new UserInfo(targetId, title, Uri.parse(mUser.getAvatar())));
                    RongIM.getInstance().refreshUserInfoCache(new UserInfo(targetId, title, Uri.parse(mUser.getAvatar())));
                }
            } else {
                ToastUtil.longToast(OrderHisDetailActivity.this, getString(R.string.nede_ti_user));
                return;
            }
        } else if (v.getId() == R.id.need_doing_order_detail_phone) {
            if (!"0".equals(mOrderDetailEntity.getData().getLiao())) {
                if (!TextUtils.isEmpty(mUser.getMobile()))
                    if (!TextUtils.isEmpty(mUser.area)) {
                        call(mUser.area + mUser.getMobile());
                    } else {
                        call(mUser.getMobile());
                    }

            } else {
                ToastUtil.longToast(OrderHisDetailActivity.this, getString(R.string.nede_ti_user));
            }
        } else {
            gotoProDetailInfo();
        }
    }

    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void gotoProDetailInfo() {
        Intent intent = new Intent(OrderHisDetailActivity.this, ProDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("uid", mDateBean.getUid());//发布订单id
        bundle.putString("nickname", mUser.getNickname());
        bundle.putString("type", "");
        bundle.putString("choose", "");
        bundle.putString("oid", mDateBean.getId());//订单id
        bundle.putBoolean("isServer", true);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
