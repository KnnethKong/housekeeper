package com.haiwai.housekeeper.activity.server;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.MapBoxMapActivity;
import com.haiwai.housekeeper.activity.user.ImgViewActivity;
import com.haiwai.housekeeper.activity.user.NeedOrderDetailActivity;
import com.haiwai.housekeeper.activity.user.NeedOrderDetailActivity3;
import com.haiwai.housekeeper.activity.user.NewHousActivity;
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
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.CheckJsonUtils;
import com.haiwai.housekeeper.utils.CityUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.PaseJsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SpanUtil;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.utils.WDUtils;
import com.haiwai.housekeeper.utils.WDUtils2;
import com.haiwai.housekeeper.utils.WenPaseUtils;
import com.haiwai.housekeeper.view.CircleImageView;
import com.haiwai.housekeeper.view.ConPopBig2View;
import com.haiwai.housekeeper.view.ConPopBig5View;
import com.haiwai.housekeeper.view.ConPopBig7View;
import com.haiwai.housekeeper.view.ConPopView;
import com.haiwai.housekeeper.view.ProCodeView;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.view.TvOrderView;
import com.haiwai.housekeeper.view.scrollview.MyScrollView;
import com.haiwai.housekeeper.widget.CustomDialog;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.haiwai.housekeeper.widget.RegisterDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

public class OrderDetailActivity extends BaseProActivity implements View.OnClickListener, MyScrollView.onPullToRefreshListener {
    private TopViewNormalBar mNormalBar;
    //    private CircleImageView civ_detail_img;
//    private TextView tv_detial_name, tv_title_tel;
//    private ImageView iv_detail_chat;
//    private TextView tv_by_tel, tv_by_detial_location;
    private ImageView iv_order_show_or_no;
    private LinearLayout ll_order_detail_layout;
    boolean flag = false;
    String id = "";
    String uid = "";
    private User user;
    private OrderDetailEntity mOrderDetailEntity;
    private TextView order_content_title, tv_order_code, tv_order_status;
    private TextView tv_click_order;
    //    private TextView tv_jcfy_b, tv_jcf_b, tv_rgf_c;
//    private View mPriceView;
    private int is_jie;
    private String type = "";
    ImageLoader imageLoader;
    private OrderDetailEntity.DataBean.UserBean mUserBean;
    private OrderDetailEntity.DataBean.HousBean mHous;
    private OrderDetailEntity.DataBean.OfferBean mOffer;
    private OrderDetailEntity.DataBean.DateBean mDate;
    private String isZhorEn = "";
    //---------------------推荐服务------
    private CircleImageView civ_tj_img;
    private ImageView call_phone,tv_addr_sfrz,tv_addr_jnrz;
    private TextView tv_addr_tj, tv_tj_bjfs, tv_tj_style, tv_tj_money, tv_tj_advantage;
    private View mTopView;
    private View mTjTopView;
    private LinearLayout ll_tj_advantage_layout, ll_tj_pri_layout;
    private LinearLayout ll_3_layout;
    //--------------------新界面内容
    View topNewView;
    private CircleImageView need_doing_order_detail_iv_head;
    private TextView need_doing_order_detail_tv_name, need_doing_order_detail_tv_pf, tv_all_moneys, tv_hour_money, tv_hour, tv_input_zgs, tv_debj, tv_sfm, tv_addr, tv_tel, tv_by_tel;
    private ImageView need_doing_order_detail_iv_sfrz, need_doing_order_detail_phone, need_doing_order_detail_iv_msg;
    private LinearLayout ll_price_layout, ll_hour_layout, ll_addr_layout;
    private RelativeLayout rl_all_layout;
    private LinearLayout ll_code_view;
    private TextView tv_click_view;
    private MyScrollView ll_sw_layout;
    boolean isRefresh = false;
    private ImageView iv_location;
    private TextView tv_yb, tv_by;

    private TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_no_detail);
        mNormalBar = (TopViewNormalBar) findViewById(R.id.top_detail_bar);
        mNormalBar.setTitle(R.string.pro_order_detai_a);
        mNormalBar.setOnBackListener(this);
        imageLoader = new ImageLoader(this);
        iniData();
        initView();
    }

    private void iniData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        user = AppGlobal.getInstance().getUser();
        id = getIntent().getStringExtra("id");
//        is_jie = getIntent().getIntExtra("is_jie", 0);
        type = getIntent().getStringExtra("type");
        Log.e("type-->",type+"aaaaa");
        initNetData(id);
    }

    @Override
    protected void onResume() {
        super.onResume();

        initNetData(id);
        initView();
    }

    private void initNetData(String id) {
        if (!isRefresh) {
            LoadDialog.showProgressDialog(OrderDetailActivity.this);
        }
        if (user != null) {
            uid = user.getUid();
        }
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        map.put("secret_key", SPUtils.getString(OrderDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderDetailActivity.this, Contants.order_detail, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                ll_sw_layout.setVisibility(View.VISIBLE);
                if (200 == code) {
                    if (isRefresh) {
                        ll_sw_layout.refreshCompleted();
                    } else {
                        mHandler.sendEmptyMessageDelayed(100,1000);

                        //   LoadDialog.closeProgressDialog();
                    }
                    mOrderDetailEntity = PaseJsonUtils.getOrderDetailEntity(response);
                    WDUtils.getWDMap(mOrderDetailEntity.getData().getDate());
                    bindDataToView(mOrderDetailEntity, response);

                } else {
                    if (isRefresh) {
                        ll_sw_layout.refreshCompleted();
                    } else {
                        mHandler.sendEmptyMessageDelayed(100,1000);
                        //   LoadDialog.closeProgressDialog();
                    }
                    ToastUtil.shortToast(OrderDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
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
        tvEmail = ((TextView) findViewById(R.id.tv_email));
        tv_by = ((TextView) findViewById(R.id.tv_by_tel_phone));
        tv_yb = ((TextView) findViewById(R.id.tv_code_yb));
        ll_sw_layout = (MyScrollView) findViewById(R.id.ll_sw_layout);
        ll_sw_layout.setOnPullToRefreshListener(this);
        ll_sw_layout.setVisibility(View.GONE);
        topNewView = findViewById(R.id.pro_new_top_content);

        initView(topNewView);
        mTjTopView = findViewById(R.id.order_tjfw_layout);
        initTjTopView(mTjTopView);
        ll_code_view = (LinearLayout) findViewById(R.id.ll_code_view);

//        mTopView = findViewById(R.id.top_card_layout);
//        initTopView(mTopView);
//        mPriceView = findViewById(R.id.order_price_layout);
//        initPriceView(mPriceView);
        iv_order_show_or_no = (ImageView) findViewById(R.id.iv_order_show_or_nos);
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
//        tv_order_status.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(OrderDetailActivity.this, MainActivity.class);
//                i.putExtra("flag", "order_success");
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(i);
//            }
//        });
        tv_click_order = (TextView) findViewById(R.id.tv_click_order);

        tv_click_order.setOnClickListener(this);

    }

    private void initView(View topNewView) {
        need_doing_order_detail_iv_head = (CircleImageView) topNewView.findViewById(R.id.need_doing_order_detail_iv_head);

        need_doing_order_detail_iv_head.setOnClickListener(this);
        need_doing_order_detail_tv_name = (TextView) topNewView.findViewById(R.id.need_doing_order_detail_tv_name);
        need_doing_order_detail_tv_pf = (TextView) topNewView.findViewById(R.id.need_doing_order_detail_tv_pf);
        tv_all_moneys = (TextView) topNewView.findViewById(R.id.tv_all_moneys);
        tv_hour_money = (TextView) topNewView.findViewById(R.id.tv_hour_money);
        tv_hour = (TextView) topNewView.findViewById(R.id.tv_hour);
        tv_input_zgs = (TextView) topNewView.findViewById(R.id.tv_input_zgs);
        tv_debj = (TextView) topNewView.findViewById(R.id.tv_debj);
        tv_sfm = (TextView) topNewView.findViewById(R.id.tv_sfm);
        //tv_jcf = (TextView) topNewView.findViewById(R.id.tv_jcf);
        tv_addr = (TextView) topNewView.findViewById(R.id.tv_addr);
        tv_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMap = new Intent(OrderDetailActivity.this, MapBoxMapActivity.class);
                intentMap.putExtra("isMap", true);
                intentMap.putExtra("lat", mHous == null ? "" : mHous.getLat());
                intentMap.putExtra("lng", mHous == null ? "" : mHous.getLongX());
                startActivity(intentMap);
            }
        });


        tv_tel = (TextView) topNewView.findViewById(R.id.tv_tel);
        tv_by_tel = (TextView) topNewView.findViewById(R.id.tv_by_tel);
        need_doing_order_detail_iv_sfrz = (ImageView) topNewView.findViewById(R.id.need_doing_order_detail_iv_sfrz);
        need_doing_order_detail_phone = (ImageView) topNewView.findViewById(R.id.need_doing_order_detail_phone);
        need_doing_order_detail_phone.setOnClickListener(this);
        need_doing_order_detail_iv_msg = (ImageView) topNewView.findViewById(R.id.need_doing_order_detail_iv_msg);
        need_doing_order_detail_iv_msg.setOnClickListener(this);
        ll_price_layout = (LinearLayout) topNewView.findViewById(R.id.ll_price_layout);
        ll_hour_layout = (LinearLayout) topNewView.findViewById(R.id.ll_hour_layout);
        ll_addr_layout = (LinearLayout) topNewView.findViewById(R.id.ll_addr_layout);
        rl_all_layout = (RelativeLayout) topNewView.findViewById(R.id.rl_all_layout);
        tv_click_view = (TextView) topNewView.findViewById(R.id.tv_click_view);
        iv_location = (ImageView) topNewView.findViewById(R.id.iv_location);
        iv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderDetailActivity.this, MapBoxMapActivity.class);
                intent.putExtra("isMap", true);
                intent.putExtra("lat", mHous == null ? "" : mHous.getLat());
                intent.putExtra("lng", mHous == null ? "" : mHous.getLongX());
                startActivity(intent);
            }
        });
        tv_click_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ConPopBig7View v = new ConPopBig7View(OrderDetailActivity.this, "");
                v.getIv_diss().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        v.dismiss();
                    }
                });
                v.showPopUpWindow(view);
            }
        });
    }

    private void initTjTopView(View tjTopView) {
        civ_tj_img = (CircleImageView) tjTopView.findViewById(R.id.civ_tj_img);
        call_phone= (ImageView) tjTopView.findViewById(R.id.tv_addr_phone_call);
        call_phone.setVisibility(View.GONE);
        tv_addr_tj = (TextView) tjTopView.findViewById(R.id.tv_addr_tj);
        tv_tj_bjfs = (TextView) tjTopView.findViewById(R.id.tv_tj_bjfs);
        tv_tj_style = (TextView) tjTopView.findViewById(R.id.tv_tj_style);
        tv_tj_money = (TextView) tjTopView.findViewById(R.id.tv_tj_money);
        tv_tj_advantage = (TextView) tjTopView.findViewById(R.id.tv_tj_advantage);
        ll_tj_advantage_layout = (LinearLayout) tjTopView.findViewById(R.id.ll_tj_advantage_layout);
        ll_tj_pri_layout = (LinearLayout) tjTopView.findViewById(R.id.ll_tj_pri_layout);
        ll_3_layout = (LinearLayout) tjTopView.findViewById(R.id.ll_3_layout);
        tv_addr_sfrz= (ImageView) tjTopView.findViewById(R.id.tv_addr_sfrz);
        tv_addr_jnrz= (ImageView) tjTopView.findViewById(R.id.tv_addr_jnrz);
     //   tv_addr_sfrz.setImageResource(R.mipmap.shenfenrenzheng_cardlv);

    }

//    private void initPriceView(View priceView) {
//        tv_jcfy_b = (TextView) priceView.findViewById(R.id.tv_jcfy_b);
//        tv_jcf_b = (TextView) priceView.findViewById(R.id.tv_jcf_b);
//        tv_rgf_c = (TextView) priceView.findViewById(R.id.tv_rgf_c);
//    }
//
//    private void initTopView(View topView) {
//        civ_detail_img = (CircleImageView) topView.findViewById(R.id.civ_detail_img);
//        civ_detail_img.setOnClickListener(this);
//        tv_detial_name = (TextView) topView.findViewById(R.id.tv_detial_name);
//        tv_title_tel = (TextView) topView.findViewById(R.id.tv_title_tel);
//        iv_detail_chat = (ImageView) topView.findViewById(R.id.iv_detail_chat);
//        iv_detail_chat.setOnClickListener(this);
//        tv_by_tel = (TextView) topView.findViewById(R.id.tv_by_tel);
//        tv_by_detial_location = (TextView) topView.findViewById(R.id.tv_title_location);
//    }

    private void bindDataToView(OrderDetailEntity orderDetailEntity, final String response) {
        mUserBean = orderDetailEntity.getData().getUser();
        mHous = orderDetailEntity.getData().getHous();
        mOffer = orderDetailEntity.getData().getOffer();
        mDate = orderDetailEntity.getData().getDate();
        try {
            String user_score = new JSONObject(response).getJSONObject("data").getJSONObject("user").getString("user_xing");
            String order_num = new JSONObject(response).getJSONObject("data").getJSONObject("user").getString("user_onum");
            if(order_num.equals("")||Float.valueOf(order_num)==0){
                need_doing_order_detail_tv_pf.setText(getString(R.string.evaluate_title) + "0.0");

            }else{
                need_doing_order_detail_tv_pf.setText(getString(R.string.evaluate_title) + String.format("%.1f",Float.valueOf(user_score)/Float.valueOf(order_num)));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        order_content_title.setText(AssetsUtils.getSkillName(mDate.getType(), isZhorEn));
        tv_order_code.setText(mDate.getOrder_id());
        is_jie = mOffer.getIs_jie();
        if (is_jie == 1) {
            tv_click_order.setVisibility(View.VISIBLE);
            tv_click_order.setText(getString(R.string.main_need_cancel_order));
        }else{
            if(AppGlobal.getInstance().getLagStr().equals("en")){
                tv_click_order.setText("Quote");
            }else{
                tv_click_order.setText(SpanUtil.getNewString(getResources().getString(R.string.btn_accept_order), 12, 0, 2, 18, 3, 5));
            }

        }
        tv_order_status.setText(R.string.pro_order_detai_c);
        if (is_jie == 1) {

            tv_click_order.setVisibility(View.GONE);
        } else {
            tv_click_order.setVisibility(View.VISIBLE);
        }

//        tv_order_status.setText(R.string.pro_order_detai_c);
        tv_click_order.setVisibility(View.VISIBLE);
        if (Integer.valueOf(type) > 18) {
            call_phone.setOnClickListener(this);
            mTjTopView.setVisibility(View.VISIBLE);
            topNewView.setVisibility(View.GONE);
            tv_addr_tj.setText(mUserBean.getNickname());
            if (!TextUtils.isEmpty(mUserBean.getAvatar())) {
                imageLoader.DisplayImage(mUserBean.getAvatar(), civ_tj_img);
            } else {
                civ_tj_img.setImageResource(R.mipmap.moren_head);

            }

            String sfrz = mUserBean.getIs_ren();
            if ("1".equals(sfrz)) {
                tv_addr_sfrz.setImageResource(R.mipmap.shenfenrenzheng_cardlv);
            } else {
                tv_addr_sfrz.setImageResource(R.mipmap.o2o_item_sf_grey);
            }
            if (is_jie == 1) {
                tv_click_order.setVisibility(View.GONE);
//                if (mHous != null) {
//                    tv_by_tel.setText(mHous.getAlternate_contact());
//                }
//                tv_tel.setText(mUserBean.getMobile());
                ll_tj_advantage_layout.setVisibility(View.VISIBLE);
                ll_tj_pri_layout.setVisibility(View.VISIBLE);
                if ("3".equals(mOffer.getService_type())) {
                    tv_tj_bjfs.setText(R.string.jtqk);
                    ll_3_layout.setVisibility(View.GONE);
                } else if ("2".equals(mOffer.getService_type())) {
                    ll_3_layout.setVisibility(View.VISIBLE);
                    tv_tj_bjfs.setText(R.string.all_counts);
                    tv_tj_style.setText(R.string.zj_m);
                    tv_tj_money.setText(mOffer.getGeneral());
                } else if ("1".equals(mOffer.getService_type())) {
                    ll_3_layout.setVisibility(View.VISIBLE);
                    tv_tj_bjfs.setText(R.string.gsfss);
                    tv_tj_style.setText(R.string.gsf_m);
                    tv_tj_money.setText(mOffer.getHourly());
                }
                tv_tj_advantage.setText(mOffer.getMessage());
                tv_tel.setText(R.string.jd_t);
                tv_by_tel.setText(R.string.jd_t);
                tv_by.setText(R.string.jd_t);
                tvEmail.setText(R.string.jd_t);
                tv_yb.setText(R.string.jd_t);
                findViewById(R.id.need_doing_order_detail_phone).setVisibility(View.GONE);
                findViewById(R.id.need_doing_order_detail_iv_msg).setVisibility(View.GONE);
//                ll_tj_advantage_layout.setVisibility(View.GONE);
//                ll_tj_pri_layout.setVisibility(View.GONE);
            } else {
                tv_tel.setText(R.string.jd_t);
                tv_by_tel.setText(R.string.jd_t);
                tvEmail.setText(R.string.jd_t);
                tv_yb.setText(R.string.jd_t);
                tv_by.setText(R.string.jd_t);

                findViewById(R.id.need_doing_order_detail_phone).setVisibility(View.GONE);
                findViewById(R.id.need_doing_order_detail_iv_msg).setVisibility(View.GONE);
                ll_tj_advantage_layout.setVisibility(View.GONE);
                ll_tj_pri_layout.setVisibility(View.GONE);
            }
        } else {
            mTjTopView.setVisibility(View.GONE);
            topNewView.setVisibility(View.VISIBLE);
            ProCodeView proCodeView = new ProCodeView(OrderDetailActivity.this);
            ll_code_view.removeAllViews();
            proCodeView.getNotice().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final ConPopBig5View cpsv = new ConPopBig5View(OrderDetailActivity.this, "");
                    cpsv.showPopUpWindow(view);
                    cpsv.getIv_diss().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cpsv.dismiss();
                        }
                    });
                }
            });
            proCodeView.setNode(mDate.getStaticX());
            ll_code_view.addView(proCodeView);
            if (!TextUtils.isEmpty(mUserBean.getAvatar())) {
                imageLoader.DisplayImage(mUserBean.getAvatar(), need_doing_order_detail_iv_head);
            }
//            if (mHous != null) {
//                tv_addr.setText(mHous.getAddress_info());
//            }
            if (mHous != null) {
                Map<String, String> map = new HashMap<>();
                map.put("secret_key", SPUtils.getString(OrderDetailActivity.this, "secret", ""));
                MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderDetailActivity.this, Contants.city_lst, map, null, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String res) {
                        System.out.println(">>>>>>>>>>城市列表>>" + res);
                        int code = JsonUtils.getJsonInt(res, "status");
                        if (code == 200) {
                            CityEntity mEntity = CityUtils.parseCity(res);
                            ArrayList<CityLevelEntity> mList = CityUtils.getLevelList("3", "23", mEntity);

                            for(int i=0;i<mList.size();i++){
                                if(mHous.getCity().equals(mList.get(i).getId())){
                                    try {
                                        String stree = new JSONObject(response).getJSONObject("data").getJSONObject("hous").getString("street");
                                        String house_number = new JSONObject(response).getJSONObject("data").getJSONObject("hous").getString("house_number");
                                        if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                            tv_addr.setText(house_number + stree+" " + mList.get(i).getYname() + " BC,C.A.");
                                        } else {
                                            tv_addr.setText("加拿大不列颠哥伦比亚省 "+mList.get(i).getName()+" " + stree + house_number);
                                        }
                                    } catch (Exception e) {

                                    }
                                    break;
                                }
                            }

                        } else {
                            LoadDialog.closeProgressDialog();
                            ToastUtil.longToast(OrderDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                }));
            }
            need_doing_order_detail_tv_name.setText(mUserBean.getNickname());
            if (is_jie == 1) {
//                tv_tel.setText(mUserBean.getMobile());
//                if (mHous != null) {
//                    tv_by_tel.setText(mHous.getAlternate_contact());
//                }
                ll_price_layout.setVisibility(View.VISIBLE);
                if (mOffer.getIs_jie() == 1) {
                    float smf = 0.0f, jcf = 0.0f, debj = 0.0f;
                  //  tv_jcf.setText(getString(R.string.jy_dw) + mOffer.getInspection());
                    tv_sfm.setText(getString(R.string.jy_dw) + mOffer.getHome_fee());
                    if ("2".equals(mOffer.getService_type())) {
                        ll_hour_layout.setVisibility(View.GONE);
                        rl_all_layout.setVisibility(View.VISIBLE);
                        tv_debj.setText(getString(R.string.jy_dw) + mOffer.getGeneral());
//                        if (!TextUtils.isEmpty(mOffer.getInspection())) {
//                            smf = Float.valueOf(mOffer.getInspection());
//                        }
//                        if (!TextUtils.isEmpty(mOffer.getHome_fee())) {
//                            jcf = Float.valueOf(mOffer.getHome_fee());
//                        }
//                        if (!TextUtils.isEmpty(mOffer.getGeneral())) {
//                            debj = Float.valueOf(mOffer.getGeneral());
//                        }
//                        float count = smf + jcf + debj;
                        tv_all_moneys.setText(getString(R.string.jy_dw) + mOffer.getGeneral());
                    } else if ("1".equals(mOffer.getService_type())) {
                        ll_hour_layout.setVisibility(View.VISIBLE);
                        tv_input_zgs.setVisibility(View.GONE);
                        rl_all_layout.setVisibility(View.GONE);
                        tv_hour_money.setText(mOffer.getHourly() + getString(R.string.time));
                        tv_all_moneys.setText(R.string.dd);
                    }

                }
                tv_click_order.setVisibility(View.VISIBLE);//已报价接单
                tv_tel.setText(R.string.jd_t);
                tv_by_tel.setText(R.string.jd_t);
                tvEmail.setText(R.string.jd_t);
                tv_by.setText(R.string.jd_t);
                tv_yb.setText(R.string.jd_t);
                findViewById(R.id.need_doing_order_detail_phone).setVisibility(View.GONE);
                findViewById(R.id.need_doing_order_detail_iv_msg).setVisibility(View.GONE);
            } else if (is_jie == 0) {
                tv_tel.setText(R.string.jd_t);
                tv_by_tel.setText(R.string.jd_t);
                tvEmail.setText(R.string.jd_t);
                tv_by.setText(R.string.jd_t);
                tv_yb.setText(R.string.jd_t);
                ll_price_layout.setVisibility(View.GONE);
                findViewById(R.id.need_doing_order_detail_phone).setVisibility(View.GONE);
                findViewById(R.id.need_doing_order_detail_iv_msg).setVisibility(View.GONE);
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
                    tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderDetailActivity.this, strKey,
                            Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn),
                            WenPaseUtils.getDaStr(OrderDetailActivity.this, valueStr,
                                    Integer.valueOf(WDUtils.getDaStr(valueStr)),
                                    Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn));
                } else if (1 == CheckJsonUtils.getStatuss(WDUtils.getDaStr(valueStr))) {//Json处理
//                    tvOrderView.setViewText(WDUtils.getWenStr(strKey),
//                            "json");
                    int a = CheckJsonUtils.getJsonType(WDUtils.getDaStr(valueStr));
                    if (0 == a) {//纯数字json
                        List<String> strList = CheckJsonUtils.getStrList(OrderDetailActivity.this, Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), i, WDUtils.getDaStr(valueStr), isZhorEn);

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
                        tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderDetailActivity.this, strKey,
                                Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn), str.toString());


                    } else if (1 == a) {//指定日期类型json

                        List<String> timeList = CheckJsonUtils.getTimeStr(OrderDetailActivity.this, Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), i, WDUtils.getDaStr(valueStr), isZhorEn);
                        StringBuilder str = new StringBuilder();
                        Log.i("time_information",a+"--"+this.mOrderDetailEntity.getData().getDate().getType()+"----"+WDUtils.getDaStr(valueStr)+"---"+timeList.size()+"---"+i);

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
                                String mData = new JSONObject(WDUtils.getDaStr(valueStr)).optString("2");
                                String mTime = new JSONObject(WDUtils.getDaStr(valueStr)).optString("3");
                                String longTime = new JSONObject(WDUtils.getDaStr(valueStr)).optString("4");
                                str.append("\n"+getString(R.string.o2o_detail_fwbj8_choose_date)+":"+TimeUtils.getDate(mData.substring(3,mData.length()))+"\n");
                                str.append(getString(R.string.o2o_detail_fwbj8_choose_time)+":"+TimeUtils.getTime(mTime.substring(3,mTime.length()))+"\n");
                                str.append(getString(R.string.o2o_detail_fwbj8_for_long)+":"+longTime);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
//                        tvOrderView.setViewText(WDUtils.getWenStr(strKey), str.toString());
                        tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderDetailActivity.this, strKey,
                                Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn), str.toString());
                    } else if (2 == a) {//图片类型json
                        final List<String> pathList = CheckJsonUtils.getPicStr(OrderDetailActivity.this, Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), i, WDUtils.getDaStr(valueStr), isZhorEn);
                        if (pathList != null && pathList.size() > 0) {
                            tvOrderView.setTvWenText(WenPaseUtils.getWenStr(OrderDetailActivity.this, strKey,
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
                                            Intent intent = new Intent(OrderDetailActivity.this, ImgViewActivity.class);
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
                            tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderDetailActivity.this, strKey,
                                    Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn), str.toString());
                        }

                    }
                } else if (2 == CheckJsonUtils.getStatuss(WDUtils.getDaStr(valueStr))) {//字符处理
                    String str = WDUtils.getDaStr(valueStr);

                    if (str.length() > 3) {
//                        tvOrderView.setViewText(WDUtils.getWenStr(strKey),
//                                str.substring(3, str.length()));

                        tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderDetailActivity.this, strKey,
                                Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn),
                                str.substring(3, str.length()));

                    }else{
                        tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderDetailActivity.this, strKey,
                                Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn),
                                "");
                    }
                }
                if(!skillType.equals("9")){
                    ll_order_detail_layout.addView(tvOrderView);
                }else{
                    viewList.add(tvOrderView);
                }
            }
        }

        if(skillType.equals("9")){
            for(int i=0;i<2;i++){
                ll_order_detail_layout.addView(viewList.get(i));
            }
            ll_order_detail_layout.addView(viewList.get(3));
            ll_order_detail_layout.addView(viewList.get(2));
            ll_order_detail_layout.addView(viewList.get(5));
            ll_order_detail_layout.addView(viewList.get(4));

//            ll_order_detail_layout.addView(viewList.get(3));
//            ll_order_detail_layout.addView(viewList.get(2));
            for(int i=6;i<viewList.size();i++){
                ll_order_detail_layout.addView(viewList.get(i));
            }
        }
    }


    @Override
    public void onClick(View v) {
        if (v == mNormalBar.getBackView()) {
            finish();
        } else if (v.getId() == R.id.iv_order_show_or_nos) {
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
        } else if (v.getId() == R.id.tv_click_order) {//点击接单
            if (Integer.valueOf(mDate.getType()) > 18) {
                CustomDialog.Builder customBuilder = new CustomDialog.Builder(OrderDetailActivity.this);
                customBuilder.setMessage(getString(R.string.kf_t)).setPositiveButton(getResources().getString(R.string.message_alert_yes),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent();
                                intent.putExtra("oid", id);
                                intent.putExtra("type", mDate.getType());
                                intent.setClass(OrderDetailActivity.this, RecommendedFeesActivity.class);
                                startActivityForResult(intent,100);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(OrderDetailActivity.this.getResources().getString(R.string.message_alert_no),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                        .create().show();
            } else {
                if (is_jie == 1) {
                    getCancelOrderTime();
                } else {
                    Intent intent = new Intent(OrderDetailActivity.this, ProOfferActivity.class);
                    intent.putExtra("oid", id);
                    startActivity(intent);
                }
            }
        } else if (v.getId() == R.id.need_doing_order_detail_iv_msg) {//进行通信
            if (!"0".equals(mOrderDetailEntity.getData().getLiao())) {
                String targetId = mUserBean.getUid();
                String title = mUserBean.getNickname();
                IMKitService.proDetailMap.put("oid", mDate.getId());
                IMKitService.proDetailMap.put("type", mDate.getType());
                if (RongIM.getInstance() != null) {
                    /**
                     * 启动单聊界面。
                     *
                     * @param context      应用上下文。
                     * @param targetUserId 要与之聊天的用户 Id。
                     * @param title        聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
                     */
                    RongIM.getInstance().startPrivateChat(this, targetId, title);
                    RongIM.getInstance().setCurrentUserInfo(new UserInfo(targetId, title, Uri.parse(mUserBean.getAvatar())));
                    RongIM.getInstance().refreshUserInfoCache(new UserInfo(targetId, title, Uri.parse(mUserBean.getAvatar())));
                }
            } else {
                ToastUtil.longToast(OrderDetailActivity.this, getString(R.string.nede_ti_user));
                return;
            }
        } else if (v.getId() == R.id.need_doing_order_detail_iv_head) {
            gotoProDetailInfo();
        } else if (v.getId() == R.id.need_doing_order_detail_phone) {
            if (!"0".equals(mOrderDetailEntity.getData().getLiao())) {
                if (!TextUtils.isEmpty(mUserBean.getMobile()))
                    call(mUserBean.getMobile());
            } else {
                ToastUtil.longToast(OrderDetailActivity.this, getString(R.string.nede_ti_user));
            }

        }else if(v.getId()==R.id.tv_addr_phone_call){
            if (!TextUtils.isEmpty(mUserBean.getMobile())){
                call(mUserBean.getMobile());
        } else {
            ToastUtil.longToast(OrderDetailActivity.this, getString(R.string.nede_ti_user));
        }
        }

    }

    private String user_quci;

    private void getCancelOrderTime() {
        CustomDialog.Builder customBuilder = new CustomDialog.Builder(this);

//        int cancel_time = entity.getData().getUser_quci();
        customBuilder.setTitle(getString(R.string.tstitle)).setMessage(getString(R.string.cancle_pro_order))
                .setNegativeButton(getString(R.string.message_alert_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton(getString(R.string.message_alert_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        requestCancelOrder();
                        dialogInterface.dismiss();
                    }
                }).create().show();

    }


    public void requestCancelOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("pro_id", AppGlobal.getInstance().getUser().getUid());
        map.put("oid", mOffer.getOid());
        map.put("secret_key", SPUtils.getString(OrderDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(this, Contants.cancel_take_order, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        final RegisterDialog dialog = new RegisterDialog(OrderDetailActivity.this, getString(R.string.message_alert), getString(R.string.commit_success));
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
                        ToastUtil.shortToast(OrderDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }


    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void gotoProDetailInfo() {
        Intent intent = new Intent(OrderDetailActivity.this, ProDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("uid", mDate.getUid());//发布订单id
        bundle.putInt("isLiao", Integer.valueOf(mOrderDetailEntity.getData().getLiao()));
        bundle.putString("nickname", mUserBean.getNickname());
        bundle.putString("type", "");
        bundle.putString("choose", "");
        bundle.putString("oid", mDate.getId());//订单id
        bundle.putBoolean("isServer", true);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            if(resultCode==100){
                isRefresh = true;
                iniData();
            }
        }
    }

    @Override
    public void onPullToRefresh() {
        isRefresh = true;
        iniData();
    }
}
