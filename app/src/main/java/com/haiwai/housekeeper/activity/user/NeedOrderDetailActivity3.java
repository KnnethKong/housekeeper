package com.haiwai.housekeeper.activity.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.MapBoxMapActivity;
import com.haiwai.housekeeper.activity.base.WebViewActivity;
import com.haiwai.housekeeper.activity.base.WebViewPayActivity;
import com.haiwai.housekeeper.activity.server.RentMoneyActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.CityEntity;
import com.haiwai.housekeeper.entity.CityLevelEntity;
import com.haiwai.housekeeper.entity.NeedResponseDetailEntity;
import com.haiwai.housekeeper.entity.Parameter;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.CheckJsonUtils;
import com.haiwai.housekeeper.utils.CityUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.HttpManager;
import com.haiwai.housekeeper.utils.ImageLoaderUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.utils.WDUtils;
import com.haiwai.housekeeper.utils.WDUtils2;
import com.haiwai.housekeeper.utils.WenPaseUtils;
import com.haiwai.housekeeper.view.CircleImageView;
import com.haiwai.housekeeper.view.CodeView;
import com.haiwai.housekeeper.view.ConPopBig3View;
import com.haiwai.housekeeper.view.ConPopBig4View;
import com.haiwai.housekeeper.view.ConPopBig4View_1;
import com.haiwai.housekeeper.view.ConPopBig7View;
import com.haiwai.housekeeper.view.ConPopBigView;
import com.haiwai.housekeeper.view.ConPopView;
import com.haiwai.housekeeper.view.TvOrderView;
import com.haiwai.housekeeper.view.scrollview.MyScrollView;
import com.haiwai.housekeeper.widget.CustomDialog;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.haiwai.housekeeper.widget.RegisterDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

import static com.haiwai.housekeeper.base.MyApp.context;

/**
 * Created by ihope007 on 2016/9/5.
 * 需求fragment订单详情——待支付，已支付（未评价），已支付（评价）
 */
public class NeedOrderDetailActivity3 extends BaseActivity implements MyScrollView.onPullToRefreshListener {
    // TODO: 2016/9/6  缺少红色手机图标
    private LinearLayout llcollpase, llbottomleft, llbottomright, lltotalprice;
    private View llbottomline;
    private TextView tvexpand, tvcommit, tvcommit2, tvstate;
    private TextView tv_dian;
    private String id = "";
    private String proid = "";
    private boolean flag = true;

    private ImageView ivmsg;
    private LinearLayout llhour;
    private CodeView nodeview;
    NeedResponseDetailEntity entity;
    private NeedResponseDetailEntity.DataBean.DateBean mDate;
    private NeedResponseDetailEntity.DataBean.HousBean mHous;
    private NeedResponseDetailEntity.DataBean.UserBean mUser;
    private NeedResponseDetailEntity.DataBean.OfferBean mOffer;
    private NeedResponseDetailEntity.DataBean.SkillBean mSkillBean;
    int statics;

    //------------------处理推荐服务详情--------
    private LinearLayout need_order_detail_lluser;
    private View topNewView;
    private View user_tjfw_layout;
    private ImageView civ_tj_img, iv_map;
    private TextView tv_addr_tj, tv_tj_bjfs, tv_tj_money, tv_tj_advantage, tv_tj_style;
    private TextView user_order_detail_ll_bottom;
    private LinearLayout ll_3_layout;
    private ImageView tv_addr_sfrz, tv_addr_jnrz, tv_addr_msg, tv_addr_call;
    private TextView tv_addr_phone;
    //------------------新的ui内容
    private CircleImageView need_doing_order_detail_iv_head;
    private TextView need_doing_order_detail_tv_name, need_doing_order_detail_tv_pf, tv_all_moneys, tv_hour_money, tv_hour, tv_debj, tv_sfm, tv_addr, tv_tel, tv_by_tel;
    private LinearLayout ll_hour_layout, ll_price_layout, ll_addr_layout;
    private RelativeLayout rl_all_layout;
    private RatingBar watch_evaluate_he;
    private ImageView need_doing_order_detail_phone, need_doing_order_detail_iv_msg, ivsf, ivjn;
    private TextView tv_js, textView;
    private String isZhorEn = "";
    private MyScrollView ll_sw_layout;
    private LinearLayout ll_des_layout;
    boolean isRefresh = false;
    private ImageView iv_detail_degree, iv_detail_degree1;

    private TextView tvUserEmail, tvByPhone;

    private RelativeLayout is_showing;

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.need_order_detail_daizhifu, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        is_showing = (RelativeLayout) findViewById(R.id.is_showing);
        is_showing.setVisibility(View.GONE);
        tvUserEmail = ((TextView) findViewById(R.id.tv_user_email));
        tvByPhone = ((TextView) findViewById(R.id.tv_by_tel_phone1));
        setTitle(getString(R.string.need_order_detail), Color.parseColor("#FF3C3C3C"));

        ll_sw_layout = (MyScrollView) findViewById(R.id.ll_sw_layout);
        ll_sw_layout.setOnPullToRefreshListener(this);
        need_order_detail_lluser = (LinearLayout) findViewById(R.id.need_order_detail_lluser);
        topNewView = findViewById(R.id.user_top_new_layout);
        initTopNewView(topNewView);
        user_tjfw_layout = findViewById(R.id.user_tjfw_layout);
        initTjfwView(user_tjfw_layout);
        ivmsg = (ImageView) findViewById(R.id.need_doing_order_detail_iv_msg);
        ivmsg.setOnClickListener(this);
        llhour = (LinearLayout) findViewById(R.id.need_doing_order_detail_ll_hour);
        llcollpase = (LinearLayout) findViewById(R.id.order_detail_daizhifu_ll_collpase);
//        llcollpase.setVisibility(View.GONE);
        tvexpand = (TextView) findViewById(R.id.order_detail_daizhifu_tv_expand);
        tvcommit = (TextView) findViewById(R.id.order_detail_daizhifu_tv_commit);
        tvcommit2 = (TextView) findViewById(R.id.order_detail_daizhifu_tv_commit2);
        tv_dian = (TextView) findViewById(R.id.tv_dian);
        tvstate = (TextView) findViewById(R.id.need_doing_order_detail_tv_status);

        llbottomleft = (LinearLayout) findViewById(R.id.order_detail_daizhifu_ll_bottom_left);
        llbottomright = (LinearLayout) findViewById(R.id.order_detail_daizhifu_ll_bottom_right);
        llbottomline = (View) findViewById(R.id.order_detail_daizhifu_ll_bottom_line);
        llbottomleft.setOnClickListener(this);
        llbottomright.setOnClickListener(this);
        lltotalprice = (LinearLayout) findViewById(R.id.order_detail_daizhifu_ll_total_price);
        tvexpand.setOnClickListener(this);
        tvexpand.performClick();
        llbottomright.setVisibility(View.GONE);
        llbottomline.setVisibility(View.GONE);
        user_order_detail_ll_bottom = (TextView) findViewById(R.id.user_order_detail_ll_bottom);
        user_order_detail_ll_bottom.setOnClickListener(this);
        nodeview = (CodeView) findViewById(R.id.nodeview);
    }

    private void initTopNewView(View topNewView) {
        need_doing_order_detail_iv_head = (CircleImageView) topNewView.findViewById(R.id.need_doing_order_detail_iv_head);
        need_doing_order_detail_tv_name = (TextView) topNewView.findViewById(R.id.need_doing_order_detail_tv_name);
        need_doing_order_detail_tv_pf = (TextView) topNewView.findViewById(R.id.need_doing_order_detail_tv_pf);
        tv_all_moneys = (TextView) topNewView.findViewById(R.id.tv_all_moneys);
        tv_hour_money = (TextView) topNewView.findViewById(R.id.tv_hour_money);
        tv_hour = (TextView) topNewView.findViewById(R.id.tv_hour);
        tv_debj = (TextView) topNewView.findViewById(R.id.tv_debj);
        tv_sfm = (TextView) topNewView.findViewById(R.id.tv_sfm);
        //tv_jcf = (TextView) topNewView.findViewById(R.id.tv_jcf);
        tv_addr = (TextView) topNewView.findViewById(R.id.tv_addr);
        iv_detail_degree = ((ImageView) topNewView.findViewById(R.id.iv_detail_degree));
        iv_detail_degree1 = ((ImageView) topNewView.findViewById(R.id.iv_detail_degree1));
        tv_addr.setOnClickListener(this);
        tv_tel = (TextView) topNewView.findViewById(R.id.tv_tel);
        tv_by_tel = (TextView) topNewView.findViewById(R.id.tv_by_tel);
        ll_hour_layout = (LinearLayout) topNewView.findViewById(R.id.ll_hour_layout);
        rl_all_layout = (RelativeLayout) topNewView.findViewById(R.id.rl_all_layout);
        watch_evaluate_he = (RatingBar) topNewView.findViewById(R.id.watch_evaluate_he);
        need_doing_order_detail_phone = (ImageView) topNewView.findViewById(R.id.need_doing_order_detail_phone);
        need_doing_order_detail_phone.setOnClickListener(this);
        need_doing_order_detail_iv_msg = (ImageView) topNewView.findViewById(R.id.need_doing_order_detail_iv_msg);
        need_doing_order_detail_iv_msg.setOnClickListener(this);
        ll_price_layout = (LinearLayout) topNewView.findViewById(R.id.ll_price_layout);
        ll_addr_layout = (LinearLayout) topNewView.findViewById(R.id.ll_addr_layout);
        ivsf = (ImageView) topNewView.findViewById(R.id.need_doing_order_detail_iv_sfrz);
        ivjn = (ImageView) topNewView.findViewById(R.id.need_doing_order_detail_iv_jnrz);
        tv_js = (TextView) topNewView.findViewById(R.id.tv_js);
        tv_js.setOnClickListener(this);
        textView = (TextView) topNewView.findViewById(R.id.textView);
        ll_des_layout = (LinearLayout) topNewView.findViewById(R.id.ll_des_layout);
        topNewView.findViewById(R.id.ll_money_desc).setOnClickListener(this);
        iv_map = (ImageView) topNewView.findViewById(R.id.iv_map);
        iv_map.setOnClickListener(this);


        need_doing_order_detail_iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUser.getUid() != null) {
                    Intent intent = new Intent(NeedOrderDetailActivity3.this, ProDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("uid", mUser.getUid());
                    bundle.putString("liao", entity.getData().getLiao());
                    bundle.putString("nickname", mUser.getNickname());
                    bundle.putString("type", mDate.getType());
                    bundle.putString("choose", "");
                    bundle.putString("oid", mDate.getId());//订单id
                    bundle.putBoolean("isServer", true);
                    intent.putExtra("fromO2O", true);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    private void initTjfwView(View user_tjfw_layout) {
        civ_tj_img = (ImageView) user_tjfw_layout.findViewById(R.id.civ_tj_img);
        tv_addr_tj = (TextView) user_tjfw_layout.findViewById(R.id.tv_addr_tj);
        tv_tj_bjfs = (TextView) user_tjfw_layout.findViewById(R.id.tv_tj_bjfs);
        tv_tj_style = (TextView) user_tjfw_layout.findViewById(R.id.tv_tj_style);
        tv_tj_money = (TextView) user_tjfw_layout.findViewById(R.id.tv_tj_money);
        tv_tj_advantage = (TextView) user_tjfw_layout.findViewById(R.id.tv_tj_advantage);
        ll_3_layout = (LinearLayout) user_tjfw_layout.findViewById(R.id.ll_3_layout);
//        findViewById(R.id.need_offer_order_detail_iv_msg).setOnClickListener(this);
        tv_addr_sfrz = (ImageView) user_tjfw_layout.findViewById(R.id.tv_addr_sfrz);
        tv_addr_jnrz = (ImageView) user_tjfw_layout.findViewById(R.id.tv_addr_jnrz);
        tv_addr_msg = (ImageView) user_tjfw_layout.findViewById(R.id.tv_addr_msg);
        tv_addr_msg.setOnClickListener(this);
        tv_addr_call = (ImageView) user_tjfw_layout.findViewById(R.id.tv_addr_phone_call);
        tv_addr_call.setOnClickListener(this);
        tv_addr_sfrz.setVisibility(View.VISIBLE);
        tv_addr_jnrz.setVisibility(View.VISIBLE);
        tv_addr_msg.setVisibility(View.VISIBLE);
        tv_addr_phone = (TextView) user_tjfw_layout.findViewById(R.id.tv_addr_phone);
        civ_tj_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NeedOrderDetailActivity3.this, ProDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("uid", mUser.getUid());
                bundle.putString("liao", entity.getData().getLiao());
                bundle.putString("nickname", mUser.getNickname());
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

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        id = getIntent().getExtras().get("id").toString();
        if (getIntent().getExtras() != null && getIntent().getExtras().get("proid") != null) {
            proid = getIntent().getExtras().get("proid").toString();
        } else {
            proid = "";
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNetworkAvailable()) {
            requestDetail();
        }
    }

    public void requestDetail() {
        LoadDialog.showProgressDialog(this);
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("proid", proid);
        map.put("secret_key", SPUtils.getString(NeedOrderDetailActivity3.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(this, Contants.user_order_detail, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    mHandler.sendEmptyMessageDelayed(100, 1000);
                    // LoadDialog.closeProgressDialog();
                    is_showing.setVisibility(View.VISIBLE);
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        if (isRefresh) {
                            ll_sw_layout.refreshCompleted();
                        } else {
                            mHandler.sendEmptyMessageDelayed(100, 1000);
                            // LoadDialog.closeProgressDialog();
                        }
                        NeedResponseDetailEntity entity = JsonUtils.parseNeedResponseDetail(response);
                        LogUtil.e("entity", entity + "");
                        WDUtils2.getWDMap(entity.getData().getDate());
                        bindData(entity, response);
                    } else {
                        if (isRefresh) {
                            ll_sw_layout.refreshCompleted();
                        } else {
                            mHandler.sendEmptyMessageDelayed(100, 1000);
                            // LoadDialog.closeProgressDialog();
                        }
                        ToastUtil.shortToast(NeedOrderDetailActivity3.this, ErrorCodeUtils.getRegisterError(code + ""));
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

    private void bindData(NeedResponseDetailEntity entity, String response) {
        this.entity = entity;
        if (this.entity != null) {
            LogUtil.e("entity", this.entity + "");// TODO: 2016/11/24 融云聊天
            mDate = this.entity.getData().getDate();
            mHous = this.entity.getData().getHous();
            mOffer = this.entity.getData().getOffer();
            mSkillBean = this.entity.getData().getSkillBean();
            mUser = this.entity.getData().getUser();

            try {
                String pro_score = new JSONObject(response).getJSONObject("data").getJSONObject("user").getString("pro_xing");
                String pro_onum = new JSONObject(response).getJSONObject("data").getJSONObject("user").getString("pro_onum");
                String s = new JSONObject(response).getJSONObject("data").getJSONObject("user").getString("s");
                if (Float.valueOf(pro_onum) == 0) {
                    need_doing_order_detail_tv_pf.setText(getString(R.string.evaluate_title) + "0.0");
                } else {
                    float source = Float.valueOf(pro_score) / Float.valueOf(pro_onum);
                    need_doing_order_detail_tv_pf.setText(getString(R.string.evaluate_title) + String.format("%.1f", source));
                }

                user_quci = new JSONObject(response).getJSONObject("data").getString("user_quci");
                user_quci_num = new JSONObject(response).getJSONObject("data").getString("user_quci_num");

                if (s.equals("1")) {
                    final String url = "drawable://" + R.mipmap.v_icon;
                    ImageLoader.getInstance().displayImage(url, iv_detail_degree);
                } else if (s.equals("2")) {
                    final String url = "drawable://" + R.mipmap.s_icon;
                    ImageLoader.getInstance().displayImage(url, iv_detail_degree);
                } else {
                    iv_detail_degree.setVisibility(View.GONE);
                    final String url = "drawable://" + R.mipmap.grade1;
                    ImageLoader.getInstance().displayImage(url, iv_detail_degree1);
                    iv_detail_degree1.setVisibility(View.VISIBLE);
                }


//                watch_evaluate_he.setRating(Float.valueOf(pro_score));
            } catch (Exception e) {
                e.printStackTrace();
            }
            //头部
            ((TextView) findViewById(R.id.need_doing_order_detail_tv_type)).setText(AssetsUtils.getSkillName(mDate.getType(), isZhorEn));
            ((TextView) findViewById(R.id.need_doing_order_detail_tv_ordernum)).setText(getString(R.string.main_need_order_num) + mDate.getOrder_id());
            tvstate.setText(getString(R.string.need_fwz));
            String mStatic = mDate.getStatics();
            if (mStatic.equals("2")) {
                tvstate.setText(getString(R.string.need_dsm));
            } else if (mStatic.equals("4")) {
                tvstate.setText(getString(R.string.need_fwz));
            } else if (mStatic.equals("9")) {
                tvstate.setText(getString(R.string.need_dzf));
            } else if (mStatic.equals("11") && mDate.getIs_ypin().equals("1")) {
                tvstate.setText(getString(R.string.all_str34));
            } else if (mStatic.equals("11")) {
                tvstate.setText(getString(R.string.need_dpj));
            }
            nodeview.setNode(mDate.getStatics());
            nodeview.getTv_xcqr().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final ConPopBig4View_1 cpsv = new ConPopBig4View_1(NeedOrderDetailActivity3.this, "");
                    cpsv.showPopUpWindow(view);
                    cpsv.getIv_diss().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cpsv.dismiss();
                        }
                    });
                }
            });
            nodeview.getTv_dzf().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final ConPopBig4View cpsv = new ConPopBig4View(NeedOrderDetailActivity3.this, "");
                    cpsv.showPopUpWindow(view);
                    cpsv.getIv_diss().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cpsv.dismiss();
                        }
                    });
                }
            });
            nodeview.getTv_fwz().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final ConPopBig3View cpsv = new ConPopBig3View(NeedOrderDetailActivity3.this, "");
                    cpsv.showPopUpWindow(view);
                    cpsv.getIv_diss().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cpsv.dismiss();
                        }
                    });
                }
            });
            if (Integer.valueOf(mDate.getType()) > 18) {
                nodeview.setVisibility(View.GONE);
                lltotalprice.setVisibility(View.GONE);
                need_order_detail_lluser.setVisibility(View.GONE);
                topNewView.setVisibility(View.GONE);//新的
                user_tjfw_layout.setVisibility(View.VISIBLE);
                if (mUser != null) {
                    //  tv_addr_phone.setText(mUser.getMobile());
                    ImageLoader.getInstance().displayImage(mUser.getAvatar(), civ_tj_img, ImageLoaderUtils.getAvatarOptions());
                    String sfrz = mUser.getIs_ren();
                    if ("1".equals(sfrz)) {
                        tv_addr_sfrz.setImageResource(R.mipmap.shenfenrenzheng_card);
                    } else {
                        tv_addr_sfrz.setImageResource(R.mipmap.o2o_item_sf_grey);
                    }
                }
                try {
                    String ordernum1 = new JSONObject(response).getJSONObject("data").getJSONObject("user").getString("pro_onum");
                    String xing = new JSONObject(response).getJSONObject("data").getJSONObject("user").getString("pro_xing");

                    int ordernum = Integer.valueOf(ordernum1);
                    Log.e("kjkljkjk--->", ordernum + "a");
                    if (ordernum == 0) {
                        tv_addr_phone.setText(getString(R.string.o2o_detail_has_done) + "0" + getString(R.string.o2o_detail_dan_pingjia) + "0");// TODO: 2016/10/20 评价
                    } else {
                        tv_addr_phone.setText(getString(R.string.o2o_detail_has_done) + ordernum + getString(R.string.o2o_detail_dan_pingjia) + new DecimalFormat("###.0").format(Double.parseDouble(xing) / Double.parseDouble(ordernum1)));// TODO: 2016/10/20 评价
                    }
                    ImageView iv_diamond = (ImageView) findViewById(R.id.item_take_order_serve_iv_diamond);// TODO: 2016/9/8


                    String rz = new JSONObject(response).getJSONObject("data").getJSONObject("skill").getString("is_ren");
                    if ("1".equals(rz)) {
                        tv_addr_jnrz.setImageResource(R.mipmap.shenfenrenzheng_jiangbei);
                    } else {
                        tv_addr_jnrz.setImageResource(R.mipmap.o2o_item_jn_grey);
                    }
                    String s = new JSONObject(response).getJSONObject("data").getJSONObject("user").getString("s");
                    iv_diamond.setVisibility(View.VISIBLE);
                    if (s.equals("1")) {
                        final String url = "drawable://" + R.mipmap.v_icon;
                        ImageLoader.getInstance().displayImage(url, iv_diamond);
                    } else if (s.equals("2")) {
                        final String url = "drawable://" + R.mipmap.s_icon;
                        ImageLoader.getInstance().displayImage(url, iv_diamond);
                    } else {
                        final String url = "";
                        ImageLoader.getInstance().displayImage(url, iv_diamond);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // if (mHous != null) {
                tv_addr_tj.setText(mUser.getNickname());
                //  }
                if (mOffer != null) {
                    tv_tj_bjfs.setTextColor(getResources().getColor(R.color.theme));
                    if ("3".equals(mOffer.getService_type())) {
                        tv_tj_bjfs.setText(R.string.jtqk);
                        ll_3_layout.setVisibility(View.GONE);
                    } else if ("2".equals(mOffer.getService_type())) {
                        ll_3_layout.setVisibility(View.VISIBLE);
                        tv_tj_bjfs.setText(R.string.all_counts);
                        tv_tj_style.setText(R.string.zj_m);
                        tv_tj_money.setText(getString(R.string.jy_dw) + mOffer.getGeneral());
                    } else if ("1".equals(mOffer.getService_type())) {
                        ll_3_layout.setVisibility(View.VISIBLE);
                        tv_tj_bjfs.setText(R.string.gsfss);
                        tv_tj_style.setText(R.string.gsf_m);
                        tv_tj_money.setText(getString(R.string.jy_dw) + mOffer.getHourly() + getString(R.string.time));
                    }
                    tv_tj_advantage.setText(mOffer.getMessage());
                }
            } else {
                lltotalprice.setVisibility(View.GONE);
                need_order_detail_lluser.setVisibility(View.GONE);
                user_tjfw_layout.setVisibility(View.GONE);
                //pro
                if (mUser == null && mSkillBean == null) {
                    need_order_detail_lluser.setVisibility(View.GONE);
                } else {
//                    CircleImageView circleImageView = (CircleImageView) findViewById(R.id.need_doing_order_detail_iv_head);
                    if (mUser != null) {
                        if (TextUtils.isEmpty(mUser.getAvatar())) {
//                            need_doing_order_detail_iv_head.setImageResource(R.mipmap.img_default);
                        } else {
                            ImageLoader.getInstance().displayImage(mUser.getAvatar(), need_doing_order_detail_iv_head, ImageLoaderUtils.getAvatarOptions());
                        }
                        need_doing_order_detail_tv_name.setText(mUser.getNickname());
                        String sfrz = mUser.getIs_ren();
                        if ("1".equals(sfrz)) {
                            ivsf.setImageResource(R.mipmap.shenfenrenzheng_card);
                        } else {
                            ivsf.setImageResource(R.mipmap.o2o_item_sf_grey);
                        }
                    }

                    try {
                        String rz = new JSONObject(response).getJSONObject("data").getJSONObject("skill").getString("is_ren");
                        if ("1".equals(rz)) {
                            ivjn.setImageResource(R.mipmap.shenfenrenzheng_jiangbei);
                        } else {
                            ivjn.setImageResource(R.mipmap.o2o_item_jn_grey);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    String jnrz = mSkillBean.getIs_ren();
////                        String isSkill = mSkillBean.getIs
//                    if ("1".equals(jnrz)) {
//                        ivjn.setImageResource(R.mipmap.shenfenrenzheng_jiangbei);
//                    } else {
//                        ivjn.setImageResource(R.mipmap.o2o_item_jn_grey);
//                    }
                }

                if (mOffer == null) {//费用模块
                    ll_price_layout.setVisibility(View.GONE);
                } else {
                    tv_sfm.setText(getString(R.string.jy_dw) + mOffer.getHome_fee());
                   // tv_jcf.setText(getString(R.string.jy_dw) + mOffer.getInspection());
                    if ("2".equals(mOffer.getService_type())) {
                        rl_all_layout.setVisibility(View.VISIBLE);
                        ll_hour_layout.setVisibility(View.GONE);
                        tv_debj.setText(getString(R.string.jy_dw) + mOffer.getGeneral());
//                        float count = Float.valueOf(entity.getData().getOffer().getHome_fee()) + Float.valueOf(entity.getData().getOffer().getInspection()) + Float.valueOf(entity.getData().getOffer().getGeneral());
                        tv_all_moneys.setText(getString(R.string.jy_dw) + mOffer.getGeneral());
                    } else if ("1".equals(mOffer.getService_type())) {
                        rl_all_layout.setVisibility(View.GONE);
                        ll_hour_layout.setVisibility(View.VISIBLE);
                        //人工费
                        tv_hour_money.setText(getString(R.string.jy_dw) + mOffer.getHourly() + getString(R.string.time));
                        //总工时
                        if ("0".equals(mOffer.getHour()) || TextUtils.isEmpty(mOffer.getHour())) {
                            tv_hour.setText(R.string.dd);
                            tv_all_moneys.setText(R.string.dd);
                        } else {
                            float rgfe = Float.valueOf(mOffer.getHourly()) * Float.valueOf(mOffer.getHour());
//                            float count = Float.valueOf(entity.getData().getOffer().getHome_fee()) + Float.valueOf(entity.getData().getOffer().getInspection()) + rgfe;
                            tv_hour.setText(mOffer.getHour());
                            tv_all_moneys.setText(getString(R.string.jy_dw) + rgfe);
                        }
                    }
                    //附加费
//                    ((TextView) findViewById(R.id.need_doing_order_detail_tv_material_price)).setText(entity.getData().getOffer().getMaterial());
                    //总费
                }
                if (mHous == null) {//地址模块
                    ll_addr_layout.setVisibility(View.GONE);
                } else {

//                    if(AppGlobal.getInstance().getLagStr().equals("en")){
//                        tv_addr.setText("加拿大不列颠哥伦比亚省"+mHous.getAddress_info());
//                    }else{
//                        tv_addr.setText(mHous.getAddress_info());
//                    }


                    Map<String, String> map = new HashMap<>();
                    map.put("secret_key", SPUtils.getString(NeedOrderDetailActivity3.this, "secret", ""));
                    MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(NeedOrderDetailActivity3.this, Contants.city_lst, map, null, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println(">>>>>>>>>>城市列表>>" + response);
                            int code = JsonUtils.getJsonInt(response, "status");
                            if (code == 200) {
                                CityEntity mEntity = CityUtils.parseCity(response);
                                ArrayList<CityLevelEntity> mList = CityUtils.getLevelList("3", "23", mEntity);

                                for (int i = 0; i < mList.size(); i++) {
                                    if (mHous.getCity().equals(mList.get(i).getId())) {
                                        if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                            tv_addr.setText("CanadaBritish Columbia " + mList.get(i).getYname() + " " + mHous.getAddress_info());
                                        } else {
                                            tv_addr.setText("加拿大不列颠哥伦比亚省 " + mList.get(i).getName() + " " + mHous.getAddress_info());
                                        }
                                        break;
                                    }
                                }

                            } else {
                                LoadDialog.closeProgressDialog();
                                ToastUtil.longToast(NeedOrderDetailActivity3.this, ErrorCodeUtils.getRegisterError(code + ""));
                            }
                        }
                    }));

//                    if (mUser != null) {
//
//                    }
                    tv_tel.setText(AppGlobal.getInstance().getUser().getMobile());
                    try {
                        tv_by_tel.setText(mHous.getAlternate_contact());
                        tvUserEmail.setText(mHous.email);
                        tvByPhone.setText(mHous.alternate_contact_number);
                        JSONObject obj = new JSONObject(response);
                        String email = obj.getJSONObject("data").getJSONObject("hous").getString("email");
                        String phone = obj.getJSONObject("data").getJSONObject("hous").getString("alternate_contact_number");
                        String zip_code = obj.getJSONObject("data").getJSONObject("hous").getString("zip_code");
                        tvUserEmail.setText(email);
                        ((TextView) findViewById(R.id.tv_code_yb_user)).setText(zip_code);
                        tvByPhone.setText(phone);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
            //bottom
            statics = Integer.valueOf(mDate.getStatics());
            if ("1".equals(mDate.getIs_ypin())) {
                user_order_detail_ll_bottom.setVisibility(View.GONE);
                tvcommit.setText(getString(R.string.main_need_watch_order));
                findViewById(R.id.order_detail_daizhifu_ll_bottom).setVisibility(View.VISIBLE);

            } else {
                if (statics == 2) {
                    ll_des_layout.setVisibility(View.VISIBLE);
                    tv_dian.setVisibility(View.GONE);
                    tvcommit.setText(R.string.all_str1);
                    findViewById(R.id.order_detail_daizhifu_ll_bottom).setVisibility(View.VISIBLE);
                } else if (statics == 3) {//等待用户最后价格
                    user_order_detail_ll_bottom.setVisibility(View.GONE);
                    findViewById(R.id.order_detail_daizhifu_ll_bottom).setVisibility(View.GONE);
                } else if (statics == 4) {
                    user_order_detail_ll_bottom.setVisibility(View.GONE);
                    tvcommit.setText(getString(R.string.main_need_disagree_order));
                    llbottomright.setVisibility(View.VISIBLE);
                    llbottomline.setVisibility(View.VISIBLE);
                    tvcommit.setVisibility(View.VISIBLE);
                    tvcommit.setText(getString(R.string.main_need_agree_order1));
                    tvcommit2.setText(getString(R.string.main_need_disagree_order1));
                    findViewById(R.id.order_detail_daizhifu_ll_bottom).setVisibility(View.VISIBLE);
                } else if (statics == 5) {
                    user_order_detail_ll_bottom.setVisibility(View.GONE);
                    tvcommit.setText(R.string.start_working);
                    findViewById(R.id.order_detail_daizhifu_ll_bottom).setVisibility(View.VISIBLE);
                } else if (statics == 6 || statics == 7) {
                    user_order_detail_ll_bottom.setVisibility(View.GONE);
                    findViewById(R.id.order_detail_daizhifu_ll_bottom).setVisibility(View.GONE);
                } else if (statics == 8) {
                    user_order_detail_ll_bottom.setVisibility(View.GONE);
                    findViewById(R.id.order_detail_daizhifu_ll_bottom).setVisibility(View.GONE);
//                    tvcommit.setText(getString(R.string.main_need_evaluate_order));
                } else if (statics == 9 || statics == 10) {
                    user_order_detail_ll_bottom.setVisibility(View.GONE);
                    tvcommit.setText(getString(R.string.main_need_pay_order));
                    if (AppGlobal.getInstance().getLagStr().equals("zh")) {
                        findViewById(R.id.tv_dian).setVisibility(View.VISIBLE);
                    }

                    findViewById(R.id.order_detail_daizhifu_ll_bottom).setVisibility(View.VISIBLE);
                } else if (statics == 11) {
                    user_order_detail_ll_bottom.setVisibility(View.GONE);
                    tvcommit.setText(getString(R.string.main_need_evaluate_order));
                    findViewById(R.id.order_detail_daizhifu_ll_bottom).setVisibility(View.VISIBLE);
                } else if (statics == 12 || statics == 13) {
                    user_order_detail_ll_bottom.setVisibility(View.GONE);
                    findViewById(R.id.order_detail_daizhifu_ll_bottom).setVisibility(View.GONE);
                }
            }
            llcollpase.removeAllViews();
            String skillType = entity.getData().getDate().getType();
            List<TvOrderView> viewList = new ArrayList<>();
            for (int i = 1; i <= WDUtils2.count; i++) {
                TvOrderView tvOrderView = new TvOrderView(this);
                String strKey = "wen" + i;
                String valueStr = "da" + i;
                if (!TextUtils.isEmpty(WDUtils2.getWenStr(strKey))) {
//                    Log.i("statusInformation",WDUtils2.getWenStr(strKey)+"----"+WenPaseUtils.getWenStr(NeedOrderDetailActivity.this, strKey,
//                            Integer.valueOf(entity.getData().getDate().getType()), isZhorEn)+"---"+WDUtils2.getDaStr(valueStr));
                    if (0 == CheckJsonUtils.getStatuss(WDUtils2.getDaStr(valueStr))) {//整数处理
//                    tvOrderView.setViewText(WDUtils2.getWenStr(strKey),
//                            WenPaseUtils.getDaStr(OrderDetailActivity.this, valueStr,
//                                    Integer.valueOf(WDUtils2.getDaStr(valueStr)),
//                                    Integer.valueOf(orderDetailEntity.getData().getDate().getType()),"zh"));
                        tvOrderView.setViewText(WenPaseUtils.getWenStr(NeedOrderDetailActivity3.this, strKey,
                                Integer.valueOf(entity.getData().getDate().getType()), isZhorEn),
                                WenPaseUtils.getDaStr(NeedOrderDetailActivity3.this, valueStr,
                                        Integer.valueOf(WDUtils2.getDaStr(valueStr)),
                                        Integer.valueOf(entity.getData().getDate().getType()), isZhorEn));
                    } else if (1 == CheckJsonUtils.getStatuss(WDUtils2.getDaStr(valueStr))) {//Json处理
//                    tvOrderView.setViewText(WDUtils2.getWenStr(strKey),
//                            "json");
                        int a = CheckJsonUtils.getJsonType(WDUtils2.getDaStr(valueStr));
                        if (0 == a) {//纯数字json
                            List<String> strList = CheckJsonUtils.getStrList(NeedOrderDetailActivity3.this, Integer.valueOf(entity.getData().getDate().getType()), i, WDUtils2.getDaStr(valueStr), isZhorEn);

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
                            tvOrderView.setViewText(WenPaseUtils.getWenStr(NeedOrderDetailActivity3.this, strKey,
                                    Integer.valueOf(entity.getData().getDate().getType()), isZhorEn), str.toString());


                        } else if (1 == a) {//指定日期类型json

                            List<String> timeList = CheckJsonUtils.getTimeStr(NeedOrderDetailActivity3.this, Integer.valueOf(entity.getData().getDate().getType()), i, WDUtils2.getDaStr(valueStr), isZhorEn);
                            StringBuilder str = new StringBuilder();
                            Log.i("time_information", a + "--" + this.entity.getData().getDate().getType() + "----" + WDUtils2.getDaStr(valueStr) + "---" + timeList.size() + "---" + i);

                            if (timeList != null && timeList.size() > 1) {
//                                str = str.append(TimeUtils.getStr2Time(timeList.get(2))).append("\n");
//
//                                if (timeList.size() > 2) {
//                                    str = str.append(timeList.get(3)).append("\n");
//                                }
//                                if (str.toString().endsWith("\n")) {
//                                    str = str.deleteCharAt(str.lastIndexOf("\n"));
//                                }
                                Log.i("str__imofrmation", str.toString());

                                str = str.append(timeList.get(1)).append("\n");
                                if (timeList.size() > 2) {
                                    str = str.append(timeList.get(2)).append("\n");
                                }
                                if (str.toString().endsWith("\n")) {
                                    str = str.deleteCharAt(str.lastIndexOf("\n"));
                                }

                            } else {
                                for (int m = 0; m < timeList.size(); m++) {
                                    str = str.append(timeList.get(m)).append("\n");
                                    Log.i("str__imofrmation__size", str.toString());
                                }
                                if (str.toString().endsWith("\n")) {
                                    str = str.deleteCharAt(str.lastIndexOf("\n"));
                                }

                                try {
                                    String mData = new JSONObject(WDUtils2.getDaStr(valueStr)).optString("2");
                                    String mTime = new JSONObject(WDUtils2.getDaStr(valueStr)).optString("3");
                                    String longTime = new JSONObject(WDUtils2.getDaStr(valueStr)).optString("4");
                                    str.append("\n" + getString(R.string.o2o_detail_fwbj8_choose_date) + ":" + TimeUtils.getDate(mData.substring(3, mData.length())) + "\n");
                                    str.append(getString(R.string.o2o_detail_fwbj8_choose_time) + ":" + TimeUtils.getTime(mTime.substring(3, mTime.length())) + "\n");
                                    str.append(getString(R.string.o2o_detail_fwbj8_for_long) + ":" + longTime);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
//                        tvOrderView.setViewText(WDUtils2.getWenStr(strKey), str.toString());
                            tvOrderView.setViewText(WenPaseUtils.getWenStr(NeedOrderDetailActivity3.this, strKey,
                                    Integer.valueOf(entity.getData().getDate().getType()), isZhorEn), str.toString());
                        } else if (2 == a) {//图片类型json
                            final List<String> pathList = CheckJsonUtils.getPicStr(NeedOrderDetailActivity3.this, Integer.valueOf(entity.getData().getDate().getType()), i, WDUtils2.getDaStr(valueStr), isZhorEn);
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
                                                Intent intent = new Intent(NeedOrderDetailActivity3.this, ImgViewActivity.class);
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
                                tvOrderView.setViewText(WenPaseUtils.getWenStr(NeedOrderDetailActivity3.this, strKey,
                                        Integer.valueOf(entity.getData().getDate().getType()), isZhorEn), str.toString());
                            }

                        }
                    } else if (2 == CheckJsonUtils.getStatuss(WDUtils2.getDaStr(valueStr))) {//字符处理
                        String str = WDUtils2.getDaStr(valueStr);

                        if (str.length() > 3) {
//                        tvOrderView.setViewText(WDUtils2.getWenStr(strKey),
//                                str.substring(3, str.length()));

                            tvOrderView.setViewText(WenPaseUtils.getWenStr(NeedOrderDetailActivity3.this, strKey,
                                    Integer.valueOf(entity.getData().getDate().getType()), isZhorEn),
                                    str.substring(3, str.length()));
                        } else {
                            tvOrderView.setViewText(WenPaseUtils.getWenStr(NeedOrderDetailActivity3.this, strKey,
                                    Integer.valueOf(entity.getData().getDate().getType()), isZhorEn),
                                    "");
                        }
                    }
                    if (!skillType.equals("9")) {
                        llcollpase.addView(tvOrderView);
                    } else {
                        viewList.add(tvOrderView);
                    }
                }
            }

            if (skillType.equals("9")) {
                for (int i = 0; i < 2; i++) {
                    llcollpase.addView(viewList.get(i));
                }
                llcollpase.addView(viewList.get(3));
                llcollpase.addView(viewList.get(2));
                llcollpase.addView(viewList.get(5));
                llcollpase.addView(viewList.get(4));
//                llcollpase.addView(viewList.get(3));
//                llcollpase.addView(viewList.get(2));
                for (int i = 6; i < viewList.size(); i++) {
                    llcollpase.addView(viewList.get(i));
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK);
                finish();
            }
        }
    }

    private String user_quci;
    private String user_quci_num;

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.tv_addr:
                Intent inMap = new Intent(NeedOrderDetailActivity3.this, MapBoxMapActivity.class);
                inMap.putExtra("isMap", true);
                inMap.putExtra("lat", mHous == null ? "" : mHous.getLat());
                inMap.putExtra("lng", mHous == null ? "" : mHous.getLongx());
                startActivity(inMap);
                break;
            case R.id.iv_map:
                Intent intentMap = new Intent(NeedOrderDetailActivity3.this, MapBoxMapActivity.class);
                intentMap.putExtra("isMap", true);
                intentMap.putExtra("lat", mHous == null ? "" : mHous.getLat());
                intentMap.putExtra("lng", mHous == null ? "" : mHous.getLongx());
                startActivity(intentMap);
                break;
            case R.id.ll_money_desc:
                final ConPopBig7View conView = new ConPopBig7View(NeedOrderDetailActivity3.this, "");
                conView.getIv_diss().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        conView.dismiss();
                    }
                });
                conView.showPopUpWindow(v);
                break;
            case R.id.tv_js:
                final ConPopBigView cpsv = new ConPopBigView(NeedOrderDetailActivity3.this, "");
                cpsv.showPopUpWindow(v);
                cpsv.getIv_diss().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cpsv.dismiss();
                    }
                });
                break;
            case R.id.need_doing_order_detail_phone:
                if (!"0".equals(entity.getData().getLiao())) {
                    if (!TextUtils.isEmpty(entity.getData().getUser().getMobile()))
                        if (!TextUtils.isEmpty(entity.getData().getUser().area)) {
                            call(entity.getData().getUser().area + entity.getData().getUser().getMobile());
                        } else {
                            call(entity.getData().getUser().getMobile());
                        }

                } else {
                    ToastUtil.longToast(NeedOrderDetailActivity3.this, getString(R.string.nede_ti));
                }
                break;
            case R.id.tv_addr_phone_call:
                ;
                if (!"0".equals(entity.getData().getLiao())) {
                    if (!TextUtils.isEmpty(entity.getData().getUser().getMobile()))
                        if (!TextUtils.isEmpty(entity.getData().getUser().area)) {
                            call(entity.getData().getUser().area + entity.getData().getUser().getMobile());
                        } else {
                            call(entity.getData().getUser().getMobile());
                        }

                } else {
                    ToastUtil.longToast(NeedOrderDetailActivity3.this, getString(R.string.nede_ti));
                }
                break;
            case R.id.tv_addr_msg:
                if (!"0".equals(entity.getData().getLiao())) {
                    String targetId = entity.getData().getUser().getUid();
                    String title = entity.getData().getUser().getNickname();
                    if (RongIM.getInstance() != null) {
                        /**
                         * 启动单聊界面。
                         *
                         * @param context      应用上下文。
                         * @param targetUserId 要与之聊天的用户 Id。
                         * @param title        聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
                         */
//
//                        RongIM.getInstance().startPrivateChat(ProDetailActivity.this, targetId, title);
//                        RongIM.getInstance().setCurrentUserInfo(new UserInfo(targetId, title, Uri.parse(mInfo.getAvatar())));
//                        RongIM.getInstance().refreshUserInfoCache(new UserInfo(targetId, title, Uri.parse(mInfo.getAvatar())));


                        RongIM.getInstance().startConversation(NeedOrderDetailActivity3.this, Conversation.ConversationType.PRIVATE, targetId, title);
                        RongIM.getInstance().setCurrentUserInfo(new UserInfo(targetId, title, Uri.parse(entity.getData().getUser().getAvatar())));
                        RongIM.getInstance().refreshUserInfoCache(new UserInfo(targetId, title, Uri.parse(entity.getData().getUser().getAvatar())));
                    }
                } else {
                    ToastUtil.longToast(NeedOrderDetailActivity3.this, getString(R.string.nede_ti));
                }
                break;
            case R.id.need_doing_order_detail_iv_msg:
                if (!"0".equals(entity.getData().getLiao())) {
                    String targetId = entity.getData().getUser().getUid();
                    String title = entity.getData().getUser().getNickname();
                    if (RongIM.getInstance() != null) {
                        /**
                         * 启动单聊界面。
                         *
                         * @param context      应用上下文。
                         * @param targetUserId 要与之聊天的用户 Id。
                         * @param title        聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
                         */
//
//                        RongIM.getInstance().startPrivateChat(ProDetailActivity.this, targetId, title);
//                        RongIM.getInstance().setCurrentUserInfo(new UserInfo(targetId, title, Uri.parse(mInfo.getAvatar())));
//                        RongIM.getInstance().refreshUserInfoCache(new UserInfo(targetId, title, Uri.parse(mInfo.getAvatar())));


                        RongIM.getInstance().startConversation(NeedOrderDetailActivity3.this, Conversation.ConversationType.PRIVATE, targetId, title);
                        RongIM.getInstance().setCurrentUserInfo(new UserInfo(targetId, title, Uri.parse(entity.getData().getUser().getAvatar())));
                        RongIM.getInstance().refreshUserInfoCache(new UserInfo(targetId, title, Uri.parse(entity.getData().getUser().getAvatar())));
                    }
                } else {
                    ToastUtil.longToast(NeedOrderDetailActivity3.this, getString(R.string.nede_ti));
                }
                break;

            case R.id.order_detail_daizhifu_tv_expand:
                if (flag) {
                    flag = false;
                    llcollpase.setVisibility(View.VISIBLE);
                    tvexpand.setText(getString(R.string.need_order_hide));
                    tvexpand.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.arrow_up_white, 0);
                    tvexpand.setCompoundDrawablePadding(5);
                } else {
                    flag = true;
                    llcollpase.setVisibility(View.GONE);
                    tvexpand.setText(getString(R.string.need_order_expand));
                    tvexpand.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.arrow_down_white, 0);
                    tvexpand.setCompoundDrawablePadding(5);
                }
                break;
            case R.id.user_order_detail_ll_bottom:

                CustomDialog.Builder customBuilder = new CustomDialog.Builder(this);

//        int cancel_time = entity.getData().getUser_quci();
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
                                    requestCancelOrder(id, entity.getData().getDate().getType());
                                    dialogInterface.dismiss();
                                }
                            }).create().show();
                }

//
//                CustomDialog.Builder customBuilder = new CustomDialog.Builder(this);
//                customBuilder.setTitle(getString(R.string.app_tip)).setMessage(getString(R.string.main_need_is_cancel)).setPositiveButton(getString(R.string.message_alert_yes),
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
            case R.id.order_detail_daizhifu_ll_bottom_left:
                if (entity != null) {
                    if ("1".equals(entity.getData().getDate().getIs_ypin())) {
                        Log.i("fdsfdsfds", "fsdfsdfsdfsd");
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("fromO2O", true);
                        bundle.putString("uid", entity.getData().getDate().getJ_uid());
                        bundle.putString("oid", id);
                        ActivityTools.goNextActivity(this, WatchEvaluateActivity.class, bundle);
                    } else if (statics >= 2 && statics <= 8) {
                        if (statics == 2) {
                            customBuilder = new CustomDialog.Builder(NeedOrderDetailActivity3.this);
                            customBuilder.setTitle(getString(R.string.app_tip)).setMessage(getString(R.string.zj_t)).setPositiveButton(getString(R.string.message_alert_yes),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            confirmComeHome();
                                            dialog.dismiss();
                                        }
                                    })
                                    .setNegativeButton(getString(R.string.message_alert_no),
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            }).create().show();

                        } else if (statics == 3) {
                            customBuilder = new CustomDialog.Builder(this);
                            customBuilder.setTitle(getString(R.string.app_tip)).setMessage(getString(R.string.main_need_is_agree)).setPositiveButton(getString(R.string.message_alert_yes),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestAgreeOrder(id);
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
                        } else if (statics == 4) {
                            customBuilder = new CustomDialog.Builder(this);
                            customBuilder.setTitle(getString(R.string.app_tip)).setMessage(getString(R.string.bj_t2)).setPositiveButton(getString(R.string.message_alert_yes),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestAgreeOrder(id);
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
                        } else if (statics == 5) {
                            customBuilder = new CustomDialog.Builder(this);
                            customBuilder.setTitle(getString(R.string.app_tip)).setMessage(getString(R.string.bj_t2)).setPositiveButton(getString(R.string.message_alert_yes),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestAgreeOrder(id);
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
                        } else if (statics == 6 || statics == 7) {
//                        tvcommit.setText("去支付");// TODO: 2016/11/17
                        } else if (statics == 8) {
//                        tvcommit.setText("去评价");

                        }
                    } else if (statics == 9 || statics == 10) {

                        if (getIntent().getExtras().getFloat("zon_price", -0.003f) != 0) {

                            Intent intent = new Intent(this, OrderPayActivity.class);
                            Bundle ev_bundle = new Bundle();
                            ev_bundle.putString("oid", id);
                            ev_bundle.putString("j_uid", proid);
                            intent.putExtras(ev_bundle);
                            startActivity(intent);
                           // payfor(entity.getData().getDate().getId(), entity.getData().getDate().getJ_uid());
//                            paypalPay(getIntent().getExtras().getFloat("zon_price", -0.003f)+"",entity.getData().getDate().getId());
                        } else {
                            CustomDialog.Builder customBuilder2 = new CustomDialog.Builder(NeedOrderDetailActivity3.this);
                            customBuilder2.setTitle(R.string.app_tip)
                                    .setMessage(getString(R.string.notice_no_fee)).setPositiveButton(getString(R.string.message_alert_yes),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {

                                            completeOrder(entity.getData().getDate().getId(), entity.getData().getDate().getUid());
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


                    } else if (statics == 11) {
                        Bundle ev_bundle = new Bundle();
                        ev_bundle.putString("oid", id);
                        ev_bundle.putString("uid", entity.getData().getDate().getJ_uid());
                        ev_bundle.putString("type", entity.getData().getDate().getType());
                        ActivityTools.goNextActivityForResult(this, EvaluateActivity.class, ev_bundle, 100);
                    }
                }
                break;
            case R.id.order_detail_daizhifu_ll_bottom_right://驳回
                customBuilder = new CustomDialog.Builder(this);
                customBuilder.setMessage(getString(R.string.main_need_is_disagree)).setPositiveButton(getString(R.string.message_alert_yes),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                requestDisAgreeOrder(id);
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
                break;
        }
    }


    private void payfor(final String oid, String j_uid) {
        LoadDialog.showProgressDialog(NeedOrderDetailActivity3.this);
        Map<String, String> map = new HashMap<>();
        map.put("j_uid", j_uid);
        map.put("oid", oid);
        map.put("secret_key", SPUtils.getString(NeedOrderDetailActivity3.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(this, Contants.order_paymoney, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {//{"status":200,"data":44}
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        if (jsonObject.optString("data").equals("") || Float.valueOf(jsonObject.optString("data")) == 0) {
                            CustomDialog.Builder customBuilder2 = new CustomDialog.Builder(NeedOrderDetailActivity3.this);
                            customBuilder2.setTitle(R.string.app_tip).setMessage(getString(R.string.notice_no_fee)).setPositiveButton(getString(R.string.message_alert_yes),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {

                                            completeOrder(entity.getData().getDate().getId(), entity.getData().getDate().getUid());
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
                            paypalPay(jsonObject.optString("data"), oid);
                        }
                    } catch (org.json.JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.longToast(NeedOrderDetailActivity3.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }


    private void paypalPay(String money, String oid) {
        Map<String, String> map = new HashMap<>();
        map.put("money", money + "");
        map.put("oid", oid);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("secret_key", SPUtils.getString(NeedOrderDetailActivity3.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        PlatRequest request = new PlatRequest(NeedOrderDetailActivity3.this, Contants.paypalzhifu, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                LoadDialog.closeProgressDialog();
                if (code == 200) {
                    String uri = JsonUtils.getJsonStr(response, "data");
                    Intent intent = new Intent(NeedOrderDetailActivity3.this, WebViewPayActivity.class);
                    intent.putExtra("url", uri);
                    startActivity(intent);
                } else if (code == 1412) {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.shortToast(NeedOrderDetailActivity3.this, getString(R.string.is_bind_paypal));
                } else if (code == 1411) {
                    LoadDialog.closeProgressDialog();

                    ToastUtil.shortToast(NeedOrderDetailActivity3.this, getString(R.string.no_bind_paypal));
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.longToast(NeedOrderDetailActivity3.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        MyApp.getTingtingApp().getRequestQueue().add(request);
    }


    private void completeOrder(String oid, String mUid) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", mUid);
        map.put("oid", oid);
        map.put("secret_key", SPUtils.getString(NeedOrderDetailActivity3.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(NeedOrderDetailActivity3.this, Contants.pay_for, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        finish();
                    } else {
                        ToastUtil.shortToast(NeedOrderDetailActivity3.this, ErrorCodeUtils.getRegisterError(code + ""));
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

    private void confirmComeHome() {
        LoadDialog.showProgressDialog(this);

        List<Parameter> list = new ArrayList<>();
        list.add(new Parameter("oid", id));

        list.add(new Parameter("secret_key", SPUtils.getString(NeedOrderDetailActivity3.this, "secret", "")));
        list.add(new Parameter("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey()));
        list.add(new Parameter("deng_uid", AppGlobal.getInstance().getUser().getUid()));
        HttpManager.getInstance().post(list, Contants.que_sm, 100, new HttpManager.OnHttpResponseListener() {
            @Override
            public void onHttpRequestSuccess(int requestCode, int resultCode, String resultData, String resultJson) {
                int code = JsonUtils.getJsonInt(resultJson, "status");
                LoadDialog.closeProgressDialog();
                if (code == 200) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setResult(RESULT_OK);
                            finish();
                        }
                    }, 1500);
                } else {
                    ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }

            @Override
            public void onHttpRequestError(int requestCode, Exception exception) {

            }
        });

    }

    public void requestCancelOrder(String id, final String type) {

        List<Parameter> list = new ArrayList<>();
        list.add(new Parameter("id", id));
        list.add(new Parameter("type", type));
        list.add(new Parameter("uid", AppGlobal.getInstance().getUser().getUid()));
        list.add(new Parameter("secret_key", SPUtils.getString(NeedOrderDetailActivity3.this, "secret", "")));
        list.add(new Parameter("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey()));
        list.add(new Parameter("deng_uid", AppGlobal.getInstance().getUser().getUid()));
        HttpManager.getInstance().post(list, Contants.order_ydel, 100, new HttpManager.OnHttpResponseListener() {
            @Override
            public void onHttpRequestSuccess(int requestCode, int resultCode, String resultData, String resultJson) {
                int code = JsonUtils.getJsonInt(resultJson, "status");
                LoadDialog.closeProgressDialog();
                if (code == 200) {
                    final RegisterDialog dialog = new RegisterDialog(NeedOrderDetailActivity3.this, getString(R.string.message_alert), getString(R.string.commit_success));
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
                    ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }

            @Override
            public void onHttpRequestError(int requestCode, Exception exception) {

            }
        });
    }

    public void requestAgreeOrder(String id) {//同意报价

        List<Parameter> list = new ArrayList<>();
        list.add(new Parameter("oid", id));
        list.add(new Parameter("uid", AppGlobal.getInstance().getUser().getUid()));
        list.add(new Parameter("secret_key", SPUtils.getString(NeedOrderDetailActivity3.this, "secret", "")));
        list.add(new Parameter("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey()));
        list.add(new Parameter("deng_uid", AppGlobal.getInstance().getUser().getUid()));
        HttpManager.getInstance().post(list, Contants.offer_saveque, 100, new HttpManager.OnHttpResponseListener() {
            @Override
            public void onHttpRequestSuccess(int requestCode, int resultCode, String resultData, String resultJson) {
                int code = JsonUtils.getJsonInt(resultJson, "status");
                LoadDialog.closeProgressDialog();
                if (code == 200) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            final RegisterDialog dialog = new RegisterDialog(NeedOrderDetailActivity3.this, getString(R.string.message_alert), getString(R.string.commit_success));
                            dialog.dismiss();
                            setResult(RESULT_OK);
                            finish();
                        }
                    }, 1500);
                } else {
                    ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }

            @Override
            public void onHttpRequestError(int requestCode, Exception exception) {

            }
        });
    }

    public void requestDisAgreeOrder(String id) {//驳回

        List<Parameter> list = new ArrayList<>();
        list.add(new Parameter("oid", id));
        list.add(new Parameter("uid", AppGlobal.getInstance().getUser().getUid()));
        list.add(new Parameter("secret_key", SPUtils.getString(NeedOrderDetailActivity3.this, "secret", "")));
        list.add(new Parameter("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey()));
        list.add(new Parameter("deng_uid", AppGlobal.getInstance().getUser().getUid()));
        HttpManager.getInstance().post(list, Contants.offer_bh, 100, new HttpManager.OnHttpResponseListener() {
            @Override
            public void onHttpRequestSuccess(int requestCode, int resultCode, String resultData, String resultJson) {
                int code = JsonUtils.getJsonInt(resultJson, "status");
                LoadDialog.closeProgressDialog();
                if (code == 200) {
                    final RegisterDialog dialog = new RegisterDialog(NeedOrderDetailActivity3.this, getString(R.string.message_alert), getString(R.string.commit_success));
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
                    ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }

            @Override
            public void onHttpRequestError(int requestCode, Exception exception) {

            }
        });
    }

    @Override
    public void onPullToRefresh() {
        requestDetail();
        isRefresh = true;
    }
}
