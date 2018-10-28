package com.haiwai.housekeeper.activity.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
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
import com.haiwai.housekeeper.utils.ImageLoaderUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.PaseJsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.StaticUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.utils.WDUtils;
import com.haiwai.housekeeper.utils.WDUtils2;
import com.haiwai.housekeeper.utils.WenPaseUtils;
import com.haiwai.housekeeper.view.CircleImageView;
import com.haiwai.housekeeper.view.ConPopBig5View;
import com.haiwai.housekeeper.view.ConPopBig6View;
import com.haiwai.housekeeper.view.ConPopBig7View;
import com.haiwai.housekeeper.view.ConPopView;
import com.haiwai.housekeeper.view.ProCodeView;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.view.TvOrderView;
import com.haiwai.housekeeper.view.ZeroPop;
import com.haiwai.housekeeper.view.scrollview.MyScrollView;
import com.haiwai.housekeeper.widget.CustomDialog;
import com.haiwai.housekeeper.widget.LoadDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

public class OrderYesDetailActivity extends BaseProActivity implements View.OnClickListener,
        ProModifyOfferSelfActivity.OnPriceChangeListener,
        ProModifyOfferActivity.OnPriceChangeListener,
        ProEvaluateActivity.OnEvaChangeListener,
        MyScrollView.onPullToRefreshListener {
    private LinearLayout ll_content_yes_layout;
    private TopViewNormalBar top_yes_detail_bar;
    private TextView order_content_title, tv_order_code, tv_order_status;
    //------card部分----------
//    private CircleImageView civ_detail_img;
//    private TextView tv_detial_name, tv_title_tel;
//    private ImageView iv_detail_chat;
//    private TextView tv_by_tel, tv_title_location;
    //-------价格
//    private TextView ib_modify_price_btn;
//    private TextView tv_jcfy_a, tv_jcf_a, tv_rgf_a, tv_clf_a, tv_hj_a;
//    private TextView tv_input_zgs;
//    private TextView tv_clf_gs;
//    private RelativeLayout rl_zgs_layout;
//    private LinearLayout ll_time;
    private ImageView iv_yes_order_show_or_no;
    private static final int HOUROFFERREQUESTCODE = 100;
    private static final int COUNTOFFERREQUESTCODE = 101;
    String ouid = "";
    String offid = "";
    String type = "";
    String id = "";
    String uid = "";
    String oid = "";
    String home_fee = "";
   // String inspection = "";
    String hourly = "";
    String hour = "";
    String general = "";
    String material = "";
    String message = "";
    private User user;
    //    private View mMiddleView;
    private String mService_type;
    private EditText mInputServer;
    private boolean isclick = false;
    private String mStaticX;
    ImageLoader imageLoader;
    boolean flag = false;
    //按钮切换
    private LinearLayout ll_init_layout, ll_static4_layout, ll_static5_layout, ll_static6_layout;
    private TextView tv_common_cancel, tv_btn_end_price, tv_btn_final_order;

    private TextView tv_btn_cancel,
            tv_btn_zero, tv_finish_cancel, tv_eva_cancel, tv_common_cancel_zero;
    private OrderDetailEntity.DataBean.UserBean userBean;
    private String isZhorEn = "";
    //=========================推荐服务================
    private CircleImageView civ_tj_img;
    private ImageView call_phone;
    private TextView tv_addr_tj, tv_tj_bjfs, tv_tj_style, tv_tj_money, tv_tj_advantage;
    private LinearLayout ll_tj_advantage_layout, ll_tj_pri_layout;
    private LinearLayout ll_3_layout;
    private View mTjfwView;
    private View mTopView1;
    OrderDetailEntity mOrderDetailEntity;
    private OrderDetailEntity.DataBean.DateBean mDate;
    private OrderDetailEntity.DataBean.HousBean mHous;

    private ImageView tv_addr_sfrz, tv_addr_jnrz, tv_addr_msg;
    private TextView tv_addr_phone;
    //--------------------新界面内容
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
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    LoadDialog.closeProgressDialog();
//                    ll_price_layout.setVisibility(View.VISIBLE);
//                    ll_hour_layout.setVisibility(View.VISIBLE);
//                    rl_all_layout.setVisibility(View.GONE);
//                    tv_input_zgs.setVisibility(View.GONE);
//                    tv_hour.setText(hour);
//                    float f1 = 0.0f, f2 = 0.0f, f3 = 0.0f, f4 = 0.0f, f5 = 0.0f;
//                    if (!TextUtils.isEmpty(home_fee)) {
//                        f1 = Float.valueOf(home_fee);
//                    }
//                    if (!TextUtils.isEmpty(inspection)) {
//                        f2 = Float.valueOf(inspection);
//                    }
//                    if (!TextUtils.isEmpty(hourly)) {
//                        f3 = Float.valueOf(hourly);
//                    }
//                    if (!TextUtils.isEmpty(hour)) {
//                        f4 = Float.valueOf(hour);
//                    }
//                    f5 = f1 + f2 + f3 * f4;
//                    tv_all_moneys.setText("$" + f5);
//                    ib_modify_price_btn.setText("待用户确认");
//                    ib_modify_price_btn.setOnTouchListener(new View.OnTouchListener() {
//                        @Override
//                        public boolean onTouch(View view, MotionEvent motionEvent) {
//                            return true;
//                        }
//                    });
                    mOnRefreshStatusListener.onNewDada();
                    initNetData(id);
//                    ib_modify_price_btn.setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
                    break;
                case 0:
                    LoadDialog.closeProgressDialog();
//                    mOnRefreshStatusListener.onNewDada();
                    finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    public MyBordcast bordcast;

    private TextView tv_confirm_hourly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_single);

        bordcast = new MyBordcast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("evalue");
        registerReceiver(bordcast, filter);
        top_yes_detail_bar = (TopViewNormalBar) findViewById(R.id.top_detail_bar);
        top_yes_detail_bar.setTitle(R.string.pro_order_detai_a);
        top_yes_detail_bar.setOnBackListener(this);
        imageLoader = new ImageLoader(this);
        ProEvaluateActivity.setOnEvaChangeListener(this);
        initView();
        initData();
    }

    private void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        user = AppGlobal.getInstance().getUser();
        if (user != null) {
            uid = user.getUid();
        }
        id = getIntent().getStringExtra("id");
        initNetData(id);
    }

    private String user_quci;
    private String user_quci_num;

    private void initNetData(String id) {
        if (!isRefresh) {
            LoadDialog.showProgressDialog(OrderYesDetailActivity.this);
        }
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        map.put("secret_key", SPUtils.getString(OrderYesDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderYesDetailActivity.this, Contants.order_detail, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                ll_sw_layout.setVisibility(View.VISIBLE);
                if (code == 200) {
                    if (isRefresh) {
                        ll_sw_layout.refreshCompleted();
                    } else {
                        mHandler1.sendEmptyMessageDelayed(100, 1000);
                        // LoadDialog.closeProgressDialog();
                    }
                    System.out.println(">>>已接单订单详情>>>" + response);
                    mOrderDetailEntity = PaseJsonUtils.getOrderDetailEntity(response);
                    WDUtils.getWDMap(mOrderDetailEntity.getData().getDate());
                    bindDataToView(mOrderDetailEntity, response);
                } else {
                    if (isRefresh) {
                        ll_sw_layout.refreshCompleted();
                    } else {
                        mHandler1.sendEmptyMessageDelayed(100, 1000);
                        // LoadDialog.closeProgressDialog();
                    }
                    ToastUtil.shortToast(OrderYesDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
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

    @Override
    protected void onResume() {
        super.onResume();
        initNetData(id);
        initView();
    }

    private void bindDataToView(OrderDetailEntity mOrderDetailEntity, final String response) {
        mDate = mOrderDetailEntity.getData().getDate();
        userBean = mOrderDetailEntity.getData().getUser();
        mHous = mOrderDetailEntity.getData().getHous();
        OrderDetailEntity.DataBean.OfferBean offer = mOrderDetailEntity.getData().getOffer();
        type = mDate.getType();
        oid = mDate.getId();//订单id
        ouid = mDate.getUid();//发布订单用户id
        mStaticX = mDate.getStaticX();
        order_content_title.setText(AssetsUtils.getSkillName(mDate.getType(), isZhorEn));
        tv_order_code.setText(mDate.getOrder_id());
//        tv_order_status.setText(getString(R.string.order_yes));
        try {
            user_quci = new JSONObject(response).getJSONObject("data").getJSONObject("user").getString("pro_quci");
            user_quci_num = new JSONObject(response).getJSONObject("data").getJSONObject("user").getString("pro_quci_num");
        } catch (Exception e) {
            e.printStackTrace();
        }
        tv_order_status.setText(getString(R.string.zt23));
        if (Integer.valueOf(type) > 18) {
            call_phone.setOnClickListener(this);
            tv_common_cancel.setVisibility(View.GONE);
            topNewView.setVisibility(View.GONE);
            mTjfwView.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(userBean.getAvatar())) {
                imageLoader.DisplayImage(userBean.getAvatar(), civ_tj_img);
            }
            tv_addr_phone.setText(userBean.getMobile());
            tv_addr_tj.setText(userBean.getNickname());
            String sfrz = userBean.getIs_ren();
            if ("1".equals(sfrz)) {
                tv_addr_sfrz.setImageResource(R.mipmap.shenfenrenzheng_cardlv);
            } else {
                tv_addr_sfrz.setImageResource(R.mipmap.o2o_item_sf_grey);
            }
            try {
                String rz = new JSONObject(response).getJSONObject("data").getJSONObject("skill").getString("is_ren");
                if ("1".equals(rz)) {
                    tv_addr_jnrz.setImageResource(R.mipmap.shenfenrenzheng_jialv);
                } else {
                    tv_addr_jnrz.setImageResource(R.mipmap.o2o_item_jn_grey);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            ll_tj_advantage_layout.setVisibility(View.VISIBLE);
            ll_tj_pri_layout.setVisibility(View.VISIBLE);
            if ("3".equals(offer.getService_type())) {
                tv_tj_bjfs.setText(R.string.jtqk);
                ll_3_layout.setVisibility(View.GONE);
            } else if ("2".equals(offer.getService_type())) {
                ll_3_layout.setVisibility(View.VISIBLE);
                tv_tj_bjfs.setText(R.string.all_counts);
                tv_tj_style.setText(R.string.zj_m);
                tv_tj_money.setText(offer.getGeneral());
            } else if ("1".equals(offer.getService_type())) {
                ll_3_layout.setVisibility(View.VISIBLE);
                tv_tj_bjfs.setText(R.string.gsfss);
                tv_tj_style.setText(R.string.gsf_m);
                tv_tj_money.setText(offer.getHourly());
            }
            tv_tj_advantage.setText(offer.getMessage());
            ll_static6_layout.setVisibility(View.VISIBLE);
            ll_init_layout.setVisibility(View.GONE);
            ll_static4_layout.setVisibility(View.GONE);
            ll_static5_layout.setVisibility(View.GONE);
        } else {
            ProCodeView proCodeView = new ProCodeView(OrderYesDetailActivity.this);
            ll_code_view.removeAllViews();
            proCodeView.setNode(mDate.getStaticX());
            ll_code_view.addView(proCodeView);
            topNewView.setVisibility(View.VISIBLE);
            mTjfwView.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(userBean.getAvatar())) {
                imageLoader.DisplayImage(userBean.getAvatar(), need_doing_order_detail_iv_head);
            } else {
                need_doing_order_detail_iv_head.setImageResource(R.mipmap.moren_head);
            }
            offid = offer.getId();
            home_fee = offer.getHome_fee();//上门费
            mService_type = offer.getService_type();
           // inspection = offer.getInspection();//检测费
            message = offer.getMessage();
            general = offer.getGeneral();//定额费用
            if (!TextUtils.isEmpty(offer.getHour())) {
                hour = offer.getHour();//小时
            }
//            material = offer.getMaterial();
            hourly = offer.getHourly();//时薪
            if ("2".equals(mStaticX)) {
                tv_sfm.setText(getString(R.string.jy_dw) + home_fee);
             //   tv_jcf.setText(getString(R.string.jy_dw) + inspection);
                if ("1".equals(mService_type)) {
                    ll_price_layout.setVisibility(View.VISIBLE);
                    ll_hour_layout.setVisibility(View.VISIBLE);
                    rl_all_layout.setVisibility(View.GONE);
                    tv_input_zgs.setVisibility(View.GONE);
                    tv_input_zgs.setBackgroundResource(R.drawable.pro_order_zgs_offer_no_edit_bj);
                    tv_hour_money.setText(getString(R.string.jy_dw) + hourly + getString(R.string.time));
                    tv_hour.setText(R.string.dd);
                    tv_all_moneys.setText(R.string.dd);
                } else if ("2".equals(mService_type)) {
                    float smf = 0.0f, jcf = 0.0f, debj = 0.0f;
                    ll_price_layout.setVisibility(View.VISIBLE);
                    ll_hour_layout.setVisibility(View.GONE);
                    rl_all_layout.setVisibility(View.VISIBLE);
                    tv_input_zgs.setVisibility(View.GONE);
                    tv_debj.setText(getString(R.string.jy_dw) + general);
                    tv_all_moneys.setText(getString(R.string.jy_dw) + String.format("%.2f", Float.valueOf(general)));
//                    if (!TextUtils.isEmpty(home_fee)) {
//                        smf = Float.valueOf(home_fee);
//                    }
//                    if (!TextUtils.isEmpty(inspection)) {
//                        jcf = Float.valueOf(inspection);
//                    }
//                    if (!TextUtils.isEmpty(general)) {
//                        debj = Float.valueOf(general);
//                    }
//                    float count = smf + jcf + debj;

                }
            } else if ("3".equals(mStaticX)) {
                tv_sfm.setText(getString(R.string.jy_dw) + home_fee);
               // tv_jcf.setText(getString(R.string.jy_dw) + inspection);
                if ("1".equals(mService_type)) {
                    ll_price_layout.setVisibility(View.VISIBLE);
                    ll_hour_layout.setVisibility(View.VISIBLE);
                    rl_all_layout.setVisibility(View.GONE);
                    tv_input_zgs.setVisibility(View.GONE);
                    tv_hour_money.setText(getString(R.string.jy_dw) + hourly + getString(R.string.time));
                    tv_hour.setText(R.string.dd);
                    tv_all_moneys.setText(R.string.dd);
                } else if ("2".equals(mService_type)) {
                    float smf = 0.0f, jcf = 0.0f, debj = 0.0f;
                    ll_price_layout.setVisibility(View.VISIBLE);
                    ll_hour_layout.setVisibility(View.GONE);
                    rl_all_layout.setVisibility(View.VISIBLE);
                    tv_input_zgs.setVisibility(View.GONE);
                    tv_debj.setText(getString(R.string.jy_dw) + general);
//                    if (!TextUtils.isEmpty(home_fee)) {
//                        smf = Float.valueOf(home_fee);
//                    }
//                    if (!TextUtils.isEmpty(inspection)) {
//                        jcf = Float.valueOf(inspection);
//                    }
//                    if (!TextUtils.isEmpty(general)) {
//                        debj = Float.valueOf(general);
//                    }
//                    float count = smf + jcf + debj;
                    tv_all_moneys.setText(getString(R.string.jy_dw) + String.format("%.2f", Float.valueOf(general)));
                }
            } else {
                tv_sfm.setText(getString(R.string.jy_dw) + home_fee);
              //  tv_jcf.setText(getString(R.string.jy_dw) + inspection);
                if ("1".equals(mService_type)) {
                    ll_price_layout.setVisibility(View.VISIBLE);
                    ll_hour_layout.setVisibility(View.VISIBLE);
                    rl_all_layout.setVisibility(View.GONE);
                    tv_input_zgs.setVisibility(View.GONE);
                    tv_hour.setText(hour);
                    tv_hour_money.setText(getString(R.string.jy_dw) + hourly + getString(R.string.time));
                    float f1 = 0.0f, f2 = 0.0f, f3 = 0.0f, f4 = 0.0f, f5 = 0.0f;
//                    if (!TextUtils.isEmpty(home_fee)) {
//                        f1 = Float.valueOf(home_fee);
//                    }
//                    if (!TextUtils.isEmpty(inspection)) {
//                        f2 = Float.valueOf(inspection);
//                    }
                    if (!TextUtils.isEmpty(hourly)) {
                        f3 = Float.valueOf(hourly);
                    }
                    if (!TextUtils.isEmpty(hour)) {
                        f4 = Float.valueOf(hour);
                    }
                    f5 = f3 * f4;
                    tv_all_moneys.setText(getString(R.string.jy_dw) + f5);
                } else if ("2".equals(mService_type)) {
                    float smf = 0.0f, jcf = 0.0f, debj = 0.0f;
                    ll_price_layout.setVisibility(View.VISIBLE);
                    ll_hour_layout.setVisibility(View.GONE);
                    rl_all_layout.setVisibility(View.VISIBLE);
                    tv_input_zgs.setVisibility(View.GONE);
                    tv_debj.setText(getString(R.string.jy_dw) + general);
                    tv_all_moneys.setText(getString(R.string.jy_dw) + String.format("%.2f", Float.valueOf(general)));
//                    if (!TextUtils.isEmpty(home_fee)) {
//                        smf = Float.valueOf(home_fee);
//                    }
//                    if (!TextUtils.isEmpty(inspection)) {
//                        jcf = Float.valueOf(inspection);
//                    }
//                    if (!TextUtils.isEmpty(general)) {
//                        debj = Float.valueOf(general);
//                    }
//                    float count = smf + jcf + debj;

                }
            }
            need_doing_order_detail_tv_name.setText(userBean.getNickname());
            try {
                String source = new JSONObject(response).getJSONObject("data").getJSONObject("user").getString("user_xing");
                String order_num = new JSONObject(response).getJSONObject("data").getJSONObject("user").getString("user_onum");
                if (order_num.equals("") || Float.valueOf(order_num) == 0) {
                    need_doing_order_detail_tv_pf.setText(getString(R.string.evaluate_title) + "0.0");
                } else {
                    need_doing_order_detail_tv_pf.setText(getString(R.string.evaluate_title) + String.format("%.1f", Float.valueOf(source) / Float.valueOf(order_num)));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            tv_tel.setText(userBean.getMobile());
            if (mHous != null) {
                try {
                    JSONObject obj = new JSONObject(response);
                    String email = obj.getJSONObject("data").getJSONObject("hous").getString("email");
                    String phone = obj.getJSONObject("data").getJSONObject("hous").getString("alternate_contact_number");
                    String alternate_contact = obj.getJSONObject("data").getJSONObject("hous").getString("alternate_contact");
                    String area_code = obj.getJSONObject("data").getJSONObject("hous").getString("zip_code");
                    tv_by_tel.setText(alternate_contact);
                    ((TextView) findViewById(R.id.tv_email)).setText(email);
                    ((TextView) findViewById(R.id.tv_code_yb)).setText(area_code);
                    ((TextView) findViewById(R.id.tv_by_tel_phone)).setText(phone);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                tv_addr.setText(mHous.getAddress_info());
                if (mHous != null) {
                    Map<String, String> map = new HashMap<>();
                    map.put("secret_key", SPUtils.getString(OrderYesDetailActivity.this, "secret", ""));
                    MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderYesDetailActivity.this, Contants.city_lst, map, null, new Response.Listener<String>() {
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
                                                tv_addr.setText(house_number + stree + " " + mList.get(i).getYname() + " BC,C.A.");
                                            } else {
                                                tv_addr.setText("加拿大不列颠哥伦比亚省 " + mList.get(i).getName() + " " + stree + house_number);
                                            }
                                        } catch (Exception e) {

                                        }
                                        break;
                                    }
                                }

                            } else {
                                LoadDialog.closeProgressDialog();
                                ToastUtil.longToast(OrderYesDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                            }
                        }
                    }));
                }


            }
            if ("2".equals(mStaticX)) {
                ib_modify_price_btn.setVisibility(View.GONE);
                ll_init_layout.setVisibility(View.GONE);
                ll_static4_layout.setVisibility(View.GONE);
                ll_static5_layout.setVisibility(View.GONE);
                ll_static6_layout.setVisibility(View.GONE);
            } else if ("3".equals(mStaticX)) {
                ib_modify_price_btn.setText(R.string.modi_price);
                ib_modify_price_btn.setVisibility(View.VISIBLE);
                ib_modify_price_btn.setBackgroundResource(R.drawable.pro_bg_shape_tv_rect_theme_fill_corner);
                tv_common_cancel.setVisibility(View.GONE);
                tv_common_cancel_zero.setVisibility(View.VISIBLE);
                if ("1".equals(mService_type)) {
                    ib_modify_price_btn.setText(R.string.confirm_work_hourly);
                    ll_init_layout.setVisibility(View.GONE);
                    tv_confirm_hourly.setVisibility(View.VISIBLE);
//                    ll_init_layout.setBackgroundResource(R.mipmap.pro_btn_z_bj1);
                } else if ("2".equals(mService_type)) {
                    ll_init_layout.setBackgroundResource(R.mipmap.pro_btn_bj);
                    ll_init_layout.setVisibility(View.VISIBLE);
                }
                ll_static4_layout.setVisibility(View.GONE);
                ll_static5_layout.setVisibility(View.GONE);
                ll_static6_layout.setVisibility(View.GONE);
            } else if ("4".equals(mStaticX) || "5".equals(mStaticX)) {
                tv_common_cancel.setVisibility(View.GONE);
                tv_common_cancel_zero.setVisibility(View.GONE);
                ib_modify_price_btn.setText(R.string.ye_conf);
                ib_modify_price_btn.setVisibility(View.GONE);
                ll_init_layout.setVisibility(View.GONE);
                ll_static4_layout.setVisibility(View.GONE);
                ll_static5_layout.setVisibility(View.GONE);
                ll_static6_layout.setVisibility(View.GONE);
                ib_modify_price_btn.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return true;
                    }
                });
                ib_modify_price_btn.setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
            } else if ("6".equals(mStaticX)) {
                tv_common_cancel.setVisibility(View.GONE);
                ll_init_layout.setVisibility(View.GONE);
                tv_btn_cancel.setText(getString(R.string.str_end_server));
                tv_btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(home_fee)) {
                            CustomDialog.Builder customBuilder = new CustomDialog.Builder(OrderYesDetailActivity.this);
                            customBuilder.setMessage(getString(R.string.ye_cancel)).setPositiveButton(getString(R.string.message_alert_yes),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            cancelZeroOrder("1");
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
                        } else {
                            final ZeroPop zeroPop = new ZeroPop(OrderYesDetailActivity.this);
                            zeroPop.showPopUpWindow(view);
                            zeroPop.setSmf(home_fee);
                            //   zeroPop.setJcf(inspection);
                            zeroPop.setHj(home_fee);
//                            zeroPop.getCb_jcf().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                                @Override
//                                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                                    if (b) {
//                                        zeroPop.setHj(String.valueOf(Float.valueOf(home_fee) + Float.valueOf(inspection)));
//                                    } else {
                            zeroPop.setHj(home_fee);
                            //  }
                            // }
                            // });
                            zeroPop.setOnLLCancelClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    zeroPop.dismiss();
                                }
                            });
                            zeroPop.setOnLLOKClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //if (zeroPop.getCb_jcf().isChecked()) {
                                    // cancelZeroOrder("2");
                                    //  } else {
                                    cancelZeroOrder("1");
                                    // }
                                    zeroPop.dismiss();
                                }
                            });
                        }
                    }
                });
                tv_common_cancel_zero.setVisibility(View.GONE);
                ll_static4_layout.setVisibility(View.VISIBLE);
                ll_static5_layout.setVisibility(View.GONE);
                ll_static6_layout.setVisibility(View.GONE);
                ib_modify_price_btn.setText(R.string.modi_price);
                ib_modify_price_btn.setVisibility(View.VISIBLE);
                ib_modify_price_btn.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return false;
                    }
                });
                ib_modify_price_btn.setBackgroundResource(R.drawable.pro_bg_shape_tv_rect_theme_fill_corner);
            } else if ("7".equals(mStaticX)) {
                tv_common_cancel.setVisibility(View.GONE);
                ll_init_layout.setVisibility(View.GONE);
                tv_common_cancel_zero.setVisibility(View.GONE);
                ll_static4_layout.setVisibility(View.GONE);
                ll_static5_layout.setVisibility(View.GONE);
                ll_static6_layout.setVisibility(View.GONE);
                tv_eva_cancel.setVisibility(View.GONE);
                ib_modify_price_btn.setVisibility(View.GONE);
                ib_modify_price_btn.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return true;
                    }
                });
                ib_modify_price_btn.setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
            } else if ("8".equals(mStaticX)) {
                tv_common_cancel.setVisibility(View.GONE);
                ll_init_layout.setVisibility(View.GONE);
                tv_common_cancel_zero.setVisibility(View.GONE);
                ll_static4_layout.setVisibility(View.GONE);
                ll_static5_layout.setVisibility(View.VISIBLE);
                ll_static6_layout.setVisibility(View.GONE);
                ib_modify_price_btn.setVisibility(View.GONE);
                ib_modify_price_btn.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return true;
                    }
                });
                ib_modify_price_btn.setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
            } else if ("9".equals(mStaticX) || "10".equals(mStaticX)) {
                tv_common_cancel.setVisibility(View.GONE);
                ll_init_layout.setVisibility(View.GONE);
                tv_common_cancel_zero.setVisibility(View.GONE);
                ll_static4_layout.setVisibility(View.GONE);
                ll_static5_layout.setVisibility(View.GONE);
                ll_static6_layout.setVisibility(View.GONE);
                ib_modify_price_btn.setVisibility(View.GONE);
                ib_modify_price_btn.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return true;
                    }
                });
                ib_modify_price_btn.setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
            } else if ("11".equals(mStaticX)) {
                tv_common_cancel.setVisibility(View.GONE);
                tv_common_cancel_zero.setVisibility(View.GONE);
                ll_init_layout.setVisibility(View.GONE);
                ll_static4_layout.setVisibility(View.GONE);
                ll_static5_layout.setVisibility(View.GONE);
                ll_static6_layout.setVisibility(View.VISIBLE);
                ib_modify_price_btn.setVisibility(View.GONE);
                ib_modify_price_btn.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return true;
                    }
                });
                ib_modify_price_btn.setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
            } else if ("12".equals(mStaticX)) {
                tv_common_cancel.setVisibility(View.GONE);
                tv_common_cancel_zero.setVisibility(View.GONE);
                ll_init_layout.setVisibility(View.GONE);
                ll_static4_layout.setVisibility(View.GONE);
                ll_static5_layout.setVisibility(View.GONE);
                ll_static6_layout.setVisibility(View.GONE);
                ib_modify_price_btn.setVisibility(View.GONE);
            }
        }

        ll_content_yes_layout.removeAllViews();
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
                    tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderYesDetailActivity.this, strKey,
                            Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn),
                            WenPaseUtils.getDaStr(OrderYesDetailActivity.this, valueStr,
                                    Integer.valueOf(WDUtils.getDaStr(valueStr)),
                                    Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn));
                } else if (1 == CheckJsonUtils.getStatuss(WDUtils.getDaStr(valueStr))) {//Json处理
//                    tvOrderView.setViewText(WDUtils.getWenStr(strKey),
//                            "json");
                    int a = CheckJsonUtils.getJsonType(WDUtils.getDaStr(valueStr));
                    if (0 == a) {//纯数字json
                        List<String> strList = CheckJsonUtils.getStrList(OrderYesDetailActivity.this, Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), i, WDUtils.getDaStr(valueStr), isZhorEn);

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
                        tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderYesDetailActivity.this, strKey,
                                Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn), str.toString());


                    } else if (1 == a) {//指定日期类型json

                        List<String> timeList = CheckJsonUtils.getTimeStr(OrderYesDetailActivity.this, Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), i, WDUtils.getDaStr(valueStr), isZhorEn);
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
                        tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderYesDetailActivity.this, strKey,
                                Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn), str.toString());
                    } else if (2 == a) {//图片类型json
                        final List<String> pathList = CheckJsonUtils.getPicStr(OrderYesDetailActivity.this, Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), i, WDUtils.getDaStr(valueStr), isZhorEn);
                        if (pathList != null && pathList.size() > 0) {
                            tvOrderView.setTvWenText(WenPaseUtils.getWenStr(OrderYesDetailActivity.this, strKey,
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
                                            Intent intent = new Intent(OrderYesDetailActivity.this, ImgViewActivity.class);
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
                            tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderYesDetailActivity.this, strKey,
                                    Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn), str.toString());
                        }

                    }
                } else if (2 == CheckJsonUtils.getStatuss(WDUtils.getDaStr(valueStr))) {//字符处理
                    String str = WDUtils.getDaStr(valueStr);

                    if (str.length() > 3) {
//                        tvOrderView.setViewText(WDUtils.getWenStr(strKey),
//                                str.substring(3, str.length()));

                        tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderYesDetailActivity.this, strKey,
                                Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn),
                                str.substring(3, str.length()));
                    } else {
                        tvOrderView.setViewText(WenPaseUtils.getWenStr(OrderYesDetailActivity.this, strKey,
                                Integer.valueOf(mOrderDetailEntity.getData().getDate().getType()), isZhorEn),
                                "");
                    }
                }
                if (!skillType.equals("9")) {
                    ll_content_yes_layout.addView(tvOrderView);
                } else {
                    viewList.add(tvOrderView);
                }
            }
        }

        if (skillType.equals("9")) {
            for (int i = 0; i < 2; i++) {
                ll_content_yes_layout.addView(viewList.get(i));
            }
            ll_content_yes_layout.addView(viewList.get(3));
            ll_content_yes_layout.addView(viewList.get(2));
            ll_content_yes_layout.addView(viewList.get(5));
            ll_content_yes_layout.addView(viewList.get(4));
//            ll_content_yes_layout.addView(viewList.get(3));
//            ll_content_yes_layout.addView(viewList.get(2));
            for (int i = 6; i < viewList.size(); i++) {
                ll_content_yes_layout.addView(viewList.get(i));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(bordcast);
    }

    private void initView() {
        tv_confirm_hourly = ((TextView) findViewById(R.id.tv_confirm_hourly));
        ll_sw_layout = (MyScrollView) findViewById(R.id.ll_sw_layout);
        ll_sw_layout.setOnPullToRefreshListener(this);
        ll_sw_layout.setVisibility(View.GONE);
        order_content_title = (TextView) findViewById(R.id.order_content_title);
        tv_order_code = (TextView) findViewById(R.id.tv_order_code);
        tv_order_status = (TextView) findViewById(R.id.tv_order_status);
//        mTopView1 = findViewById(R.id.top_card_yes_layout);
//        initTopView(mTopView1);
//        mMiddleView = findViewById(R.id.top_price_yes_layout);
//        initMiddleView(mMiddleView);
        ll_code_view = (LinearLayout) findViewById(R.id.ll_code_view);
        topNewView = findViewById(R.id.pro_new_top_content);
        initView(topNewView);
        mTjfwView = findViewById(R.id.order_yes_tjfw_layout);
        initTjfwView(mTjfwView);
        ll_init_layout = (LinearLayout) findViewById(R.id.ll_init_layout);
        ll_static4_layout = (LinearLayout) findViewById(R.id.ll_static4_layout);
        ll_static5_layout = (LinearLayout) findViewById(R.id.ll_static5_layout);
        ll_static6_layout = (LinearLayout) findViewById(R.id.ll_static6_layout);
        tv_common_cancel = (TextView) findViewById(R.id.tv_common_cancel);
        tv_common_cancel.setOnClickListener(this);
        tv_btn_zero = (TextView) findViewById(R.id.tv_btn_zero);
        tv_btn_zero.setOnClickListener(this);
        tv_finish_cancel = (TextView) findViewById(R.id.tv_finish_cancel);
        tv_finish_cancel.setOnClickListener(this);
        tv_btn_cancel = (TextView) findViewById(R.id.tv_btn_cancel);
        tv_btn_cancel.setOnClickListener(this);
        tv_eva_cancel = (TextView) findViewById(R.id.tv_eva_cancel);
        tv_eva_cancel.setOnClickListener(this);
        tv_btn_final_order = (TextView) findViewById(R.id.tv_btn_final_order);
        tv_btn_final_order.setOnClickListener(this);
        tv_btn_end_price = (TextView) findViewById(R.id.tv_btn_end_price);
        tv_btn_end_price.setOnClickListener(this);
        iv_yes_order_show_or_no = (ImageView) findViewById(R.id.iv_yes_order_show_or_no);
        if ("en".equals(isZhorEn)) {
            iv_yes_order_show_or_no.setBackgroundResource(R.mipmap.pro_order_show_en);
        } else {
            iv_yes_order_show_or_no.setBackgroundResource(R.mipmap.pro_order_show);
        }
        iv_yes_order_show_or_no.setOnClickListener(this);
        ll_content_yes_layout = (LinearLayout) findViewById(R.id.ll_content_yes_layout);//动态布局
        tv_common_cancel_zero = (TextView) findViewById(R.id.tv_common_cancel_zero);
        tv_common_cancel_zero.setOnClickListener(this);
        tv_confirm_hourly.setOnClickListener(this);
    }

    private void initView(View topNewView) {
        need_doing_order_detail_iv_head = (CircleImageView) topNewView.findViewById(R.id.need_doing_order_detail_iv_head);
        need_doing_order_detail_tv_name = (TextView) topNewView.findViewById(R.id.need_doing_order_detail_tv_name);
        need_doing_order_detail_tv_pf = (TextView) findViewById(R.id.need_doing_order_detail_tv_pf);
        tv_all_moneys = (TextView) topNewView.findViewById(R.id.tv_all_moneys);
        tv_hour_money = (TextView) topNewView.findViewById(R.id.tv_hour_money);
        tv_hour = (TextView) topNewView.findViewById(R.id.tv_hour);
        tv_input_zgs = (TextView) topNewView.findViewById(R.id.tv_input_zgs);
        tv_input_zgs.setOnClickListener(this);
        tv_debj = (TextView) topNewView.findViewById(R.id.tv_debj);
        tv_sfm = (TextView) topNewView.findViewById(R.id.tv_sfm);
        //tv_jcf = (TextView) topNewView.findViewById(R.id.tv_jcf);
        tv_addr = (TextView) topNewView.findViewById(R.id.tv_addr);
        tv_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMap = new Intent(OrderYesDetailActivity.this, MapBoxMapActivity.class);
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
                Intent intent = new Intent(OrderYesDetailActivity.this, MapBoxMapActivity.class);
                intent.putExtra("isMap", true);
                intent.putExtra("lat", mHous == null ? "35.6184395336" : mHous.getLat());
                intent.putExtra("lng", mHous == null ? "112.2816432607" : mHous.getLongX());
                startActivity(intent);
            }
        });
        TextView tv_click_view = (TextView) topNewView.findViewById(R.id.tv_click_view);
        tv_click_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ConPopBig7View cpsv = new ConPopBig7View(OrderYesDetailActivity.this, "");
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
                ActivityTools.goNextActivity(OrderYesDetailActivity.this, ProDetailActivity.class, bundle);
            }
        });
    }

    private void initTjfwView(View tjfwView) {
        civ_tj_img = (CircleImageView) tjfwView.findViewById(R.id.civ_tj_img);
        call_phone = (ImageView) tjfwView.findViewById(R.id.tv_addr_phone_call);
        call_phone.setImageResource(R.mipmap.pro_phone_icon);
        tv_addr_tj = (TextView) tjfwView.findViewById(R.id.tv_addr_tj);
        tv_tj_bjfs = (TextView) tjfwView.findViewById(R.id.tv_tj_bjfs);
        tv_tj_style = (TextView) tjfwView.findViewById(R.id.tv_tj_style);
        tv_tj_money = (TextView) tjfwView.findViewById(R.id.tv_tj_money);
        tv_tj_advantage = (TextView) tjfwView.findViewById(R.id.tv_tj_advantage);
        ll_tj_advantage_layout = (LinearLayout) tjfwView.findViewById(R.id.ll_tj_advantage_layout);
        ll_tj_pri_layout = (LinearLayout) tjfwView.findViewById(R.id.ll_tj_pri_layout);
        ll_3_layout = (LinearLayout) tjfwView.findViewById(R.id.ll_3_layout);

        tv_addr_sfrz = (ImageView) tjfwView.findViewById(R.id.tv_addr_sfrz);
        tv_addr_jnrz = (ImageView) tjfwView.findViewById(R.id.tv_addr_jnrz);
        tv_addr_msg = (ImageView) tjfwView.findViewById(R.id.tv_addr_msg);
        tv_addr_msg.setImageResource(R.mipmap.pro_order_detail_chat);
        tv_addr_msg.setOnClickListener(this);
        tv_addr_sfrz.setVisibility(View.VISIBLE);
        tv_addr_jnrz.setVisibility(View.GONE);
        tv_addr_msg.setVisibility(View.VISIBLE);
        tv_addr_phone = (TextView) tjfwView.findViewById(R.id.tv_addr_phone);
        tv_addr_phone.setVisibility(View.GONE);
        civ_tj_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderYesDetailActivity.this, ProDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("uid", userBean.getUid());
                bundle.putString("liao", mOrderDetailEntity.getData().getLiao());
                bundle.putString("nickname", userBean.getNickname());
                bundle.putString("type", mDate.getType());
                bundle.putString("choose", "");
                bundle.putString("oid", mDate.getId());//订单id
                bundle.putBoolean("isServer", true);
                intent.putExtra("fromO2O", true);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

//    private void initTopView(View topView) {
//        civ_detail_img = (CircleImageView) topView.findViewById(R.id.civ_detail_img);
//        civ_detail_img.setOnClickListener(this);
//        tv_detial_name = (TextView) topView.findViewById(R.id.tv_detial_name);
//        tv_title_tel = (TextView) topView.findViewById(R.id.tv_title_tel);
//        iv_detail_chat = (ImageView) topView.findViewById(R.id.iv_detail_chat);
//        iv_detail_chat.setOnClickListener(this);
//        tv_by_tel = (TextView) topView.findViewById(R.id.tv_by_tel);
//        tv_title_location = (TextView) topView.findViewById(R.id.tv_title_location);
//
//    }
//
//
//    private void initMiddleView(View middleView) {
//        ib_modify_price_btn = (TextView) middleView.findViewById(R.id.ib_modify_price_btn);
//        ib_modify_price_btn.setOnClickListener(this);
//        tv_jcfy_a = (TextView) middleView.findViewById(R.id.tv_jcfy_a);
//        tv_jcf_a = (TextView) middleView.findViewById(R.id.tv_jcf_a);
//        tv_rgf_a = (TextView) middleView.findViewById(R.id.tv_rgf_a);
//        tv_clf_a = (TextView) middleView.findViewById(R.id.tv_clf_a);
//        tv_hj_a = (TextView) middleView.findViewById(R.id.tv_hj_a);
//        tv_input_zgs = (TextView) middleView.findViewById(R.id.tv_input_zgs);
//        tv_input_zgs.setOnClickListener(this);
//        tv_clf_gs = (TextView) middleView.findViewById(R.id.tv_clf_gs);
//        rl_zgs_layout = (RelativeLayout) middleView.findViewById(R.id.rl_zgs_layout);
//        ll_time = (LinearLayout) middleView.findViewById(R.id.ll_time);
//    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.need_doing_order_detail_phone) {
            if (!"0".equals(mOrderDetailEntity.getData().getLiao())) {
                if (!TextUtils.isEmpty(userBean.getMobile()))
                    if (!TextUtils.isEmpty(userBean.area)) {
                        call(userBean.area + userBean.getMobile());
                    } else {
                        call(userBean.getMobile());
                    }

            } else {
                ToastUtil.longToast(OrderYesDetailActivity.this, getString(R.string.nede_ti_user));
            }
        } else if (view.getId() == R.id.tv_confirm_hourly) {
            modifyCountOffer(mService_type);


        } else if (view.getId() == R.id.need_doing_order_detail_iv_msg) {
            if (!"0".equals(mOrderDetailEntity.getData().getLiao())) {
                String targetId = userBean.getUid();
                String title = userBean.getNickname();
                if (RongIM.getInstance() != null) {
                    RongIM.getInstance().startPrivateChat(OrderYesDetailActivity.this, targetId, title);
                    RongIM.getInstance().setCurrentUserInfo(new UserInfo(targetId, title, Uri.parse(userBean.getAvatar())));
                }
            } else {
                ToastUtil.longToast(OrderYesDetailActivity.this, getString(R.string.nede_ti_user));
            }
        } else if (view == top_yes_detail_bar.getBackView()) {
            finish();
        } else if (view.getId() == R.id.ib_modify_price_btn) {
            isclick = true;
            if ("1".equals(mService_type)) {//时薪修改
                modifyCountOffer(mService_type);
            } else if ("2".equals(mService_type)) {//总体修改
                modifyCountOffer(mService_type);
            }
        } else if (view.getId() == R.id.tv_input_zgs) {//输入工时
            if (!"2".equals(mStaticX)) {
                View vw = View.inflate(this, R.layout.pro_dialog_input_hour_layout, null);
                mInputServer = (EditText) vw.findViewById(R.id.et_hour);
                new AlertDialog.Builder(this).setTitle(getString(R.string.pro_srzgs_str)).setView(
                        vw).setPositiveButton(getString(R.string.message_alert_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        hour = mInputServer.getText().toString().trim();
//                        if(Float.valueOf(hour)==0||hour.equals("")){
//
//                            return;
//                        }
                        submitHourData(hour);
                    }
                }).setNegativeButton(getString(R.string.message_alert_no), null).show();
            }
        } else if (view.getId() == R.id.tv_common_cancel) {//取消订单
            CustomDialog.Builder customBuilder = new CustomDialog.Builder(this);
            int can_cancel_times = Integer.valueOf(user_quci_num) - Integer.valueOf(user_quci);


            if (can_cancel_times == Integer.valueOf(user_quci_num)) {
                customBuilder.setTitle(getString(R.string.tstitle)).setMessage(getString(R.string.can_canle_time) + user_quci_num + getString(R.string.can_not_cancel_time) + "\n" + getString(R.string.aleardy_cancel_time) + can_cancel_times + getString(R.string.orer_ti2))
                        .setPositiveButton(getString(R.string.message_alert_yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
            } else {
                customBuilder.setTitle(getString(R.string.tstitle)).setMessage(getString(R.string.can_canle_time) + user_quci_num + getString(R.string.can_canle_time1) + "\n" + getString(R.string.aleardy_cancel_time) + can_cancel_times + getString(R.string.orer_ti2))
                        .setNegativeButton(getString(R.string.message_alert_no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setPositiveButton(getString(R.string.message_alert_yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                requestCancelOrder(id, entity.getData().getDate().getType());
                                cancelOrder();
                                dialogInterface.dismiss();
                            }
                        }).create().show();
            }


//            CustomDialog.Builder customBuilder = new CustomDialog.Builder(this);
//            customBuilder.setMessage(getString(R.string.main_need_is_cancel)).setPositiveButton(getString(R.string.message_alert_yes),
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            cancelOrder();
//                            dialog.dismiss();
//                        }
//                    })
//                    .setNegativeButton(getString(R.string.message_alert_no),
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            })
//                    .create().show();
        } else if (view.getId() == R.id.tv_btn_final_order) {//底部修改价格
            isclick = true;
            if ("1".equals(mService_type)) {//时薪修改
                modifyCountOffer(mService_type);
            } else if ("2".equals(mService_type)) {//总体修改
                modifyCountOffer(mService_type);
            }
        } else if (view.getId() == R.id.tv_btn_end_price) {//原价不变
            if ("1".equals(mService_type)) {

            } else if ("2".equals(mService_type)) {
                CustomDialog.Builder customBuilder = new CustomDialog.Builder(this);
                customBuilder.setTitle(R.string.app_tip).setMessage(getString(R.string.bj_c)).setPositiveButton(getString(R.string.message_alert_yes),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                isclick = true;
                                keepPriceOrder();
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

            }

        } else if (view.getId() == R.id.iv_yes_order_show_or_no) {
            if (!flag) {
                flag = true;
                ll_content_yes_layout.setVisibility(View.VISIBLE);
                if ("en".equals(isZhorEn)) {
                    iv_yes_order_show_or_no.setBackgroundResource(R.mipmap.pro_btn_hide_en);
                } else {
                    iv_yes_order_show_or_no.setBackgroundResource(R.mipmap.pro_btn_hide);
                }

            } else {
                flag = false;
                ll_content_yes_layout.setVisibility(View.GONE);
                if ("en".equals(isZhorEn)) {
                    iv_yes_order_show_or_no.setBackgroundResource(R.mipmap.pro_order_show_en);
                } else {
                    iv_yes_order_show_or_no.setBackgroundResource(R.mipmap.pro_order_show);
                }

            }
        } else if (view.getId() == R.id.tv_btn_zero) {//驳回修改
            if ("1".equals(mService_type)) {//时薪修改
                modifyCountOffer(mService_type);
            } else if ("2".equals(mService_type)) {//总体修改
                modifyCountOffer(mService_type);
            }
        } else if (view.getId() == R.id.tv_finish_cancel) {//完成订单or评价订单
            CustomDialog.Builder customBuilder = new CustomDialog.Builder(this);
            customBuilder.setMessage(getString(R.string.star_t)).setPositiveButton(getString(R.string.message_alert_yes),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finishOrder();
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

        } else if (view.getId() == R.id.tv_eva_cancel) {//去评论
            if ("7".equals(mStaticX)) {
                CustomDialog.Builder customBuilder = new CustomDialog.Builder(this);
                customBuilder.setMessage(getString(R.string.star_ti2)).setPositiveButton(getString(R.string.message_alert_yes),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                submitKgOrder();
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
            } else if ("11".equals(mStaticX)) {
                gotoEvaOrder();
            }
        } else if (view.getId() == R.id.civ_detail_img) {//查看他人信息
            gotoProDetailInfo();
        } else if (view.getId() == R.id.iv_detail_chat) {
            if (!"0".equals(mOrderDetailEntity.getData().getLiao())) {
                String targetId = userBean.getUid();
                String title = userBean.getNickname();
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
                    RongIM.getInstance().setCurrentUserInfo(new UserInfo(targetId, title, Uri.parse(userBean.getAvatar())));
                    RongIM.getInstance().refreshUserInfoCache(new UserInfo(targetId, title, Uri.parse(userBean.getAvatar())));
                }
            } else {
                ToastUtil.longToast(OrderYesDetailActivity.this, getString(R.string.nede_ti_user));
                return;
            }
        } else if (view.getId() == R.id.tv_addr_msg) {
            // if (!"0".equals(mOrderDetailEntity.getData().getLiao())) {
            String targetId = userBean.getUid();
            String title = userBean.getNickname();
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
                RongIM.getInstance().setCurrentUserInfo(new UserInfo(targetId, title, Uri.parse(userBean.getAvatar())));
                RongIM.getInstance().refreshUserInfoCache(new UserInfo(targetId, title, Uri.parse(userBean.getAvatar())));
            }
//            } else {
//                ToastUtil.longToast(OrderYesDetailActivity.this, getString(R.string.nede_ti_user));
//                return;
//            }

        } else if (view.getId() == R.id.tv_common_cancel_zero) {
            if (TextUtils.isEmpty(home_fee)) {
                if (isclick == false) {
                    CustomDialog.Builder customBuilder = new CustomDialog.Builder(OrderYesDetailActivity.this);
                    customBuilder.setMessage(getString(R.string.ye_cancel)).setPositiveButton(getString(R.string.message_alert_yes),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    cancelZeroOrder("1");
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
                } else {
                    ToastUtil.longToast(this, (String) this.getResources().getText(R.string.toast));
                }

            } else {
                final ZeroPop zeroPop = new ZeroPop(OrderYesDetailActivity.this);
                zeroPop.showPopUpWindow(view);
                zeroPop.setSmf(home_fee);
                //  zeroPop.setJcf(inspection);
                //zeroPop.getCb_jcf().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                // @Override
                // public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //  if (b) {
                //     zeroPop.setHj(String.valueOf(Float.valueOf(home_fee) + Float.valueOf(inspection)));
                // } else {
                zeroPop.setHj(home_fee);
                // }
                // }
                // });
                zeroPop.setOnLLCancelClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        zeroPop.dismiss();
                    }
                });
                zeroPop.setOnLLOKClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // if (zeroPop.getCb_jcf().isChecked()) {
                        cancelZeroOrder("2");
//                        } else {
//                            cancelZeroOrder("1");
//                        }
                        zeroPop.dismiss();
                    }
                });
            }
        } else if (view.getId() == R.id.tv_addr_phone_call) {
            if (!TextUtils.isEmpty(userBean.getMobile())) {
                call(userBean.getMobile());
            } else {
                ToastUtil.longToast(OrderYesDetailActivity.this, getString(R.string.nede_ti_user));
            }
        }
    }

    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void keepPriceOrder() {//原价不变接口
        LoadDialog.showProgressDialog(OrderYesDetailActivity.this);
        Map<String, String> map = new HashMap<>();
        map.put("oid", id);
        map.put("secret_key", SPUtils.getString(OrderYesDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderYesDetailActivity.this, Contants.offer_que, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    Message msg = Message.obtain();
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.shortToast(OrderYesDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void gotoProDetailInfo() {
        Intent intent = new Intent(OrderYesDetailActivity.this, ProDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("uid", ouid);
        bundle.putString("nickname", userBean.getNickname());
        bundle.putString("type", "");
        bundle.putString("choose", "");
        bundle.putString("oid", oid);
        bundle.putBoolean("isServer", true);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void gotoEvaOrder() {
        Intent intent = new Intent(OrderYesDetailActivity.this, ProEvaluateActivity.class);
        intent.putExtra("flag", false);
        intent.putExtra("oid", oid);
        intent.putExtra("ouid", ouid);
        intent.putExtra("user_id", getIntent().getStringExtra("id"));
        intent.putExtra("avatar", userBean.getAvatar());
        intent.putExtra("nickname", userBean.getNickname());
        intent.putExtra("address_info", mHous.getAddress_info());
        startActivity(intent);
    }

    private void finishOrder() {
        LoadDialog.showProgressDialog(OrderYesDetailActivity.this);
        Map<String, String> map = new HashMap<>();
        map.put("oid", oid);
        map.put("uid", ouid);
        map.put("j_uid", uid);
        map.put("secret_key", SPUtils.getString(OrderYesDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderYesDetailActivity.this, Contants.order_complete, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(OrderYesDetailActivity.this, getString(R.string.ti4));
                    Message msg = Message.obtain();
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.shortToast(OrderYesDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void cancelZeroOrder(String inspection_type) {
        LoadDialog.showProgressDialog(OrderYesDetailActivity.this);
        Map<String, String> map = new HashMap<>();
        map.put("oid", oid);
        map.put("uid", ouid);
        map.put("j_uid", uid);
        map.put("inspection_type", inspection_type);
        map.put("secret_key", SPUtils.getString(OrderYesDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderYesDetailActivity.this, Contants.order_ql, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(OrderYesDetailActivity.this, getString(R.string.ti3));
                    Message msg = Message.obtain();
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.shortToast(OrderYesDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void submitKgOrder() {
        LoadDialog.showProgressDialog(OrderYesDetailActivity.this);
        Map<String, String> map = new HashMap<>();
        map.put("oid", oid);
        map.put("ouid", ouid);
        map.put("uid", uid);
        map.put("secret_key", SPUtils.getString(OrderYesDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderYesDetailActivity.this, Contants.startg, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(OrderYesDetailActivity.this, getString(R.string.ti2));
                    Message msg = Message.obtain();
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.shortToast(OrderYesDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }


    private void submitHourData(String hour) {//提交总工时
        LoadDialog.showProgressDialog(OrderYesDetailActivity.this);
        Map<String, String> map = new HashMap<>();
        map.put("id", offid);
        map.put("uid", uid);
        map.put("oid", oid);
        map.put("home_fee", home_fee);
      //  map.put("inspection", inspection);
        map.put("service_type", mService_type);
        map.put("hourly", hourly);
        map.put("hour", hour);
        map.put("general", general);
        map.put("material", material);
        map.put("message", message);
        map.put("secret_key", SPUtils.getString(OrderYesDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderYesDetailActivity.this, Contants.offer_save, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(OrderYesDetailActivity.this, getString(R.string.skill_ti4));
                    Message msg = Message.obtain();
                    msg.what = 1;
                    mHandler.sendMessage(msg);
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.shortToast(OrderYesDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));

    }

    private void modifyCountOffer(String service_type) {//总体
        Intent intent = new Intent();
        intent.putExtra("id", offid);
        intent.putExtra("uid", uid);
        intent.putExtra("oid", oid);
        intent.putExtra("home_fee", home_fee);
       // intent.putExtra("inspection", inspection);
        intent.putExtra("service_type", mService_type);
        intent.putExtra("hourly", hourly);
        intent.putExtra("hour", hour);
        intent.putExtra("general", general);
        intent.putExtra("material", material);
        intent.putExtra("message", message);
        if ("1".equals(service_type)) {
            intent.setClass(OrderYesDetailActivity.this, ProModifyOfferSelfActivity.class);
            startActivityForResult(intent, HOUROFFERREQUESTCODE);
        } else if ("2".equals(service_type)) {
            intent.setClass(OrderYesDetailActivity.this, ProModifyOfferActivity.class);
            startActivityForResult(intent, COUNTOFFERREQUESTCODE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HOUROFFERREQUESTCODE) {
            findViewById(R.id.tv_confirm_hourly).setVisibility(View.GONE);
        }
        initData();
    }

    private void cancelOrder() {
        LoadDialog.showProgressDialog(OrderYesDetailActivity.this);
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("oid", oid);
        map.put("type", type);
        map.put("secret_key", SPUtils.getString(OrderYesDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderYesDetailActivity.this, Contants.order_fdel, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(OrderYesDetailActivity.this, getString(R.string.ti1));
                    Message msg = Message.obtain();
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.shortToast(OrderYesDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    public static OnRefreshStatusListener mOnRefreshStatusListener;

    @Override
    public void changeData() {
        initData();
    }

    @Override
    public void changeDatas() {
        initData();
    }

    @Override
    public void changeStatus() {
        finish();
    }

    @Override
    public void onPullToRefresh() {
        isRefresh = true;
        initData();
    }

    public interface OnRefreshStatusListener {
        void onNewDada();
    }

    public static void setOnRefreshStatusListener(OnRefreshStatusListener onRefreshStatusListener) {
        mOnRefreshStatusListener = onRefreshStatusListener;
    }

    public class MyBordcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    }

}
