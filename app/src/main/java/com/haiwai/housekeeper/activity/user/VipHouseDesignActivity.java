package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.entity.VipBusinessOrderEntity;
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
import com.haiwai.housekeeper.view.DatePicker2PopView;
import com.haiwai.housekeeper.view.HouseManageProgress;
import com.haiwai.housekeeper.widget.LoadDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/19.
 * VIP——房屋管理——房屋配置
 */
public class VipHouseDesignActivity extends BaseActivity {
    private LinearLayout lladdr;
    private TextView tvaddr;
    private TextView bt_commit;
    Map<String, String> map = new HashMap<>();
    private String uid = "";
    private String h_id = "";
    private String month = "";
    User user;
    //-----------------------------------历史---
    TextView tv_history_month;
    //------------房屋巡视-----------
    HouseManageProgress hmp_fwgl;
    TextView tv_fwgl_1, tv_fwgl_2;//巡视日期
    LinearLayout ll_1_fwgl, ll_2_fwgl;//隐藏或显示
    boolean isShow1 = false, isShow2 = false;
    LinearLayout ll_fwgl_cqxs_1, ll_fwgl_zlxj_1, ll_fwgl_tsqkfk_1, ll_fwgl_cqxs_2, ll_fwgl_zlxj_2, ll_fwgl_tsqkfk_2;
    ImageButton ib_fwgl_1, ib_fwgl_2;
    TextView tv_fwgl_cqxs_1, tv_fwgl_zlxj_1, tv_fwgl_tsqkfk_1, tv_fwgl_cqxs_2, tv_fwgl_zlxj_2, tv_fwgl_tsqkfk_2;//每一步日期
    //-----------------------租赁招租----------
    LinearLayout ll_zl_zz;
    TextView tv_zl_zzing;
    //-----------------------租赁管理--------------------
    HouseManageProgress hmp_zl;
    LinearLayout ll_zl_gl;
    LinearLayout ll_zl_gl_zjsq, ll_zl_gl_fwzfzt, ll_zl_gl_jgqd;
    TextView tv_zl_gl_zjsq, tv_zl_gl_fwzfzt, tv_zl_gl_jgqd;
    //----------园艺----------

    private LinearLayout ll_yygl_view;
    //---------------------家政-------------------
    HouseManageProgress hmp_jzfw;
    LinearLayout ll_jzfw_1, ll_jzfw_2, ll_jzfw_3, ll_jzfw_4;
    TextView tv_jzfw_1, tv_jzfw_2, tv_jzfw_3, tv_jzfw_4;
    private VipHouseDesignEntity.DataBean.VacancyBean.FeedbackBeanXXX mFeedbackBeanXXX;
    private VipHouseDesignEntity.DataBean.VacancyBean.FeedbackBeanXXX mFeedbackBeanXXX1;
    private VipHouseDesignEntity.DataBean.BillBean.FeedbackBean mFeedback;
    private List<VipHouseDesignEntity.DataBean.HomeBean.FeedbackBeanX> mFeedback1;

    private VipHouseDesignEntity.DataBean.BillBean mBillBean;
    private String isZhorEn = "";

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_vip_house_design, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.house_design_title), Color.parseColor("#FF3C3C3C"));
        lladdr = (LinearLayout) findViewById(R.id.vip_house_design_ll_addr);
        tvaddr = (TextView) findViewById(R.id.vip_house_design_tv_addr);
        lladdr.setOnClickListener(this);
        bt_commit = (TextView) findViewById(R.id.vip_house_design_bt_commit);
        bt_commit.setText(SpanUtil.getNewString(getResources().getString(R.string.btn_click_design_scheme), 14, 0, 2, 16, 3, 7));
        bt_commit.setOnClickListener(this);
        //-----------------------------------历史---
        tv_history_month = (TextView) findViewById(R.id.tv_history_month);
        tv_history_month.setOnClickListener(this);
        //------------房屋巡视-----------
        hmp_fwgl = (HouseManageProgress) findViewById(R.id.hmp_fwgl);
        tv_fwgl_1 = (TextView) findViewById(R.id.tv_fwgl_1);
        tv_fwgl_2 = (TextView) findViewById(R.id.tv_fwgl_2);
        ll_1_fwgl = (LinearLayout) findViewById(R.id.ll_1_fwgl);
        ll_2_fwgl = (LinearLayout) findViewById(R.id.ll_2_fwgl);
        ll_fwgl_cqxs_1 = (LinearLayout) findViewById(R.id.ll_fwgl_cqxs_1);
        ll_fwgl_zlxj_1 = (LinearLayout) findViewById(R.id.ll_fwgl_zlxj_1);
        ll_fwgl_tsqkfk_1 = (LinearLayout) findViewById(R.id.ll_fwgl_tsqkfk_1);
        ll_fwgl_cqxs_2 = (LinearLayout) findViewById(R.id.ll_fwgl_cqxs_2);
        ll_fwgl_zlxj_2 = (LinearLayout) findViewById(R.id.ll_fwgl_zlxj_2);
        ll_fwgl_tsqkfk_2 = (LinearLayout) findViewById(R.id.ll_fwgl_tsqkfk_2);
        ib_fwgl_1 = (ImageButton) findViewById(R.id.ib_fwgl_1);
        ib_fwgl_1.setOnClickListener(this);
        ib_fwgl_2 = (ImageButton) findViewById(R.id.ib_fwgl_2);
        ib_fwgl_2.setOnClickListener(this);
        tv_fwgl_cqxs_1 = (TextView) findViewById(R.id.tv_fwgl_cqxs_1);
        tv_fwgl_zlxj_1 = (TextView) findViewById(R.id.tv_fwgl_zlxj_1);
        tv_fwgl_tsqkfk_1 = (TextView) findViewById(R.id.tv_fwgl_tsqkfk_1);
        tv_fwgl_cqxs_2 = (TextView) findViewById(R.id.tv_fwgl_cqxs_2);
        tv_fwgl_zlxj_2 = (TextView) findViewById(R.id.tv_fwgl_zlxj_2);
        tv_fwgl_tsqkfk_2 = (TextView) findViewById(R.id.tv_fwgl_tsqkfk_2);
        //-----------------------租赁招租----------
        ll_zl_zz = (LinearLayout) findViewById(R.id.ll_zl_zz);
        tv_zl_zzing = (TextView) findViewById(R.id.tv_zl_zzing);
        //-----------------------租赁管理--------------------
        hmp_zl = (HouseManageProgress) findViewById(R.id.hmp_zl);
        ll_zl_gl = (LinearLayout) findViewById(R.id.ll_zl_gl);
        ll_zl_gl_zjsq = (LinearLayout) findViewById(R.id.ll_zl_gl_zjsq);
        ll_zl_gl_fwzfzt = (LinearLayout) findViewById(R.id.ll_zl_gl_fwzfzt);
        ll_zl_gl_jgqd = (LinearLayout) findViewById(R.id.ll_zl_gl_jgqd);
        tv_zl_gl_zjsq = (TextView) findViewById(R.id.tv_zl_gl_zjsq);
        tv_zl_gl_fwzfzt = (TextView) findViewById(R.id.tv_zl_gl_fwzfzt);
        tv_zl_gl_jgqd = (TextView) findViewById(R.id.tv_zl_gl_jgqd);
        //----------园艺----------
        ll_yygl_view= (LinearLayout) findViewById(R.id.ll_yygl_view);

        //---------------------家政-------------------
        hmp_jzfw = (HouseManageProgress) findViewById(R.id.hmp_jzfw);
        ll_jzfw_1 = (LinearLayout) findViewById(R.id.ll_jzfw_1);
        ll_jzfw_2 = (LinearLayout) findViewById(R.id.ll_jzfw_2);
        ll_jzfw_3 = (LinearLayout) findViewById(R.id.ll_jzfw_3);
        ll_jzfw_4 = (LinearLayout) findViewById(R.id.ll_jzfw_4);
        tv_jzfw_1 = (TextView) findViewById(R.id.tv_jzfw_1);
        tv_jzfw_2 = (TextView) findViewById(R.id.tv_jzfw_2);
        tv_jzfw_3 = (TextView) findViewById(R.id.tv_jzfw_3);
        tv_jzfw_4 = (TextView) findViewById(R.id.tv_jzfw_4);

        // TODO: 2016/11/29
        //tvaddr显示当前位置？
    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        LoadDialog.showProgressDialog(VipHouseDesignActivity.this);
        user = AppGlobal.getInstance().getUser();
        map.put("uid", user.getUid());
        map.put("h_id", h_id);
        map.put("month", month);
        map.put("secret_key", SPUtils.getString(VipHouseDesignActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(VipHouseDesignActivity.this, Contants.home_order, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(">>>>>>>房屋管理>>>>." + response);
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject dataObject = jsonObject.optJSONObject("data");
                        JSONObject billObject = dataObject.optJSONObject("bill");//租赁管理
                        if (billObject != null) {
                            mBillBean = VipParseDataUtils.parseBillBean(billObject);
                            bindbillDataView(mBillBean);
                        }
                        JSONObject forrentObject = dataObject.optJSONObject("forrent");//租赁招租
                        if (forrentObject != null) {
                            VipParseDataUtils.parseBillBean(forrentObject);
                        }
                        JSONObject homeObject = dataObject.optJSONObject("home");//家政服务
                        if (homeObject != null) {
                            VipHouseDesignEntity.DataBean.HomeBean homeBean = VipParseDataUtils.parseHomeBean(homeObject);
                            bindHomeDataView(homeBean);
                        }
                        JSONArray horticultureObject = dataObject.optJSONArray("horticulture_arr");//园艺
                        if (horticultureObject != null) {
                            List<VipHouseDesignEntity.DataBean.HorticultureBean> horticultureBean = VipParseDataUtils.parseHorticultureBean(horticultureObject);
                            ll_yygl_view.removeAllViews();
                            for(int hr=0;hr<horticultureBean.size();hr++){
                                Log.e("FeedBackInformation--", horticultureBean.get(hr).getFeedback().size() + "");
                                View view=View.inflate(VipHouseDesignActivity.this,R.layout.ll_yygl_view_item,null);
                                TextView ll_yygl_name= (TextView) view.findViewById(R.id.ll_yygl_name);
                                if(horticultureBean.get(hr).getType().equals("34")) {
                                    ll_yygl_name.setText(R.string.yygl134);
                                }else if(horticultureBean.get(hr).getType().equals("32")){
                                    ll_yygl_name.setText(R.string.yygl132);
                                }else if(horticultureBean.get(hr).getType().equals("33")){
                                    ll_yygl_name.setText(R.string.yygl133);
                                }
                                bindHorticultureDataView(horticultureBean.get(hr),view);
                                ll_yygl_view.addView(view);
                            }


                        }
                        JSONObject housObject = dataObject.optJSONObject("hous");//房产
                        if (housObject != null) {
                            VipHouseDesignEntity.DataBean.HousBean housBean = VipParseDataUtils.parseHousBean(housObject);
                        }
                        JSONObject vacancyObject = dataObject.optJSONObject("vacancy");//房屋巡视
                        if (vacancyObject != null) {
                            VipHouseDesignEntity.DataBean.VacancyBean vacancyBean = VipParseDataUtils.parseVacancyBean(vacancyObject);
                            bindVacancyDataView(vacancyBean);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ToastUtil.longToast(VipHouseDesignActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    /**
     * 租赁管理
     *
     * @param billBean
     */
    private void bindbillDataView(VipHouseDesignEntity.DataBean.BillBean billBean) {
        hmp_zl.setNum(3);
        mFeedback = billBean.getFeedback();
        if (!TextUtils.isEmpty(mFeedback.getRent()) && !"0".equals(mFeedback.getRent())) {
            ll_zl_gl_zjsq.setOnClickListener(mOnClickListener);
            tv_zl_gl_zjsq.setText(TimeUtils.getDate(mFeedback.getWtime1()));
            hmp_zl.setView3Progress(1);
            if (!TextUtils.isEmpty(mFeedback.getHome_remark()) && !TextUtils.isEmpty(mFeedback.getTenant_remark())) {
                ll_zl_gl_fwzfzt.setOnClickListener(mOnClickListener);
                tv_zl_gl_fwzfzt.setText(TimeUtils.getDate(mFeedback.getWtime2()));
                hmp_zl.setView3Progress(2);
                if (!TextUtils.isEmpty(mFeedback.getPrice_remark())) {
                    ll_zl_gl_jgqd.setOnClickListener(mOnClickListener);
                    tv_zl_gl_jgqd.setText(TimeUtils.getDate(mFeedback.getWtime3()));
                    hmp_zl.setView3Progress(3);
                }
            }
        }
//        ll_zl_gl = (LinearLayout) findViewById(R.id.ll_zl_gl);
//         = (LinearLayout) findViewById(R.id.ll_zl_gl_zjsq);
//         = (LinearLayout) findViewById(R.id.ll_zl_gl_fwzfzt);
//         = (LinearLayout) findViewById(R.id.ll_zl_gl_jgqd);
//         = (TextView) findViewById(R.id.tv_zl_gl_zjsq);
//         = (TextView) findViewById(R.id.tv_zl_gl_fwzfzt);
//         = (TextView) findViewById(R.id.tv_zl_gl_jgqd);
        //-----------招租内容---------
        ll_zl_zz.setVisibility(View.GONE);
    }

    /**
     * 园艺管理
     *
     * @param horticultureBean
     */
    private void bindHorticultureDataView(VipHouseDesignEntity.DataBean.HorticultureBean horticultureBean,View view) {
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

       final List<VipHouseDesignEntity.DataBean.HorticultureBean.FeedbackBeanXX> mFeedback2;
        mFeedback2 = horticultureBean.getFeedback();
        int num = horticultureBean.getFeedback().size();
        hmp_yygl.setNum(num);
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
        int a = 0;
        for (int i = 0; i < num; i++) {
            if (!TextUtils.isEmpty(horticultureBean.getFeedback().get(i).getContent1())) {
                a = i + 1;
            }
        }
        if (a == 1) {
            ll_yygl_1.setVisibility(View.VISIBLE);
            ll_yygl_2.setVisibility(View.GONE);
            ll_yygl_3.setVisibility(View.GONE);
            ll_yygl_4.setVisibility(View.GONE);
            tv_yygl_1.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(0).getWtime1()));
        } else if (a == 2) {
            ll_yygl_1.setVisibility(View.VISIBLE);
            ll_yygl_2.setVisibility(View.VISIBLE);
            ll_yygl_3.setVisibility(View.GONE);
            ll_yygl_4.setVisibility(View.GONE);
            tv_yygl_1.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(0).getWtime1()));
            tv_yygl_1.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(1).getWtime1()));
        } else if (a == 3) {
            ll_yygl_1.setVisibility(View.VISIBLE);
            ll_yygl_2.setVisibility(View.VISIBLE);
            ll_yygl_3.setVisibility(View.VISIBLE);

            ll_yygl_4.setVisibility(View.GONE);
            tv_yygl_1.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(0).getWtime1()));
            tv_yygl_2.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(1).getWtime1()));
            tv_yygl_3.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(2).getWtime1()));
        } else if (a == 4) {

            tv_yygl_1.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(0).getWtime1()));
            tv_yygl_2.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(1).getWtime1()));
            tv_yygl_3.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(2).getWtime1()));
            tv_yygl_4.setText(TimeUtils.getDate(horticultureBean.getFeedback().get(3).getWtime1()));
        }
        ll_yygl_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VipHouseDesignActivity.this, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback2", (Serializable) mFeedback2);
                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("isYy", true);
                intent.putExtra("step", "0");
                startActivity(intent);
            }
        });
        ll_yygl_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VipHouseDesignActivity.this, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback2", (Serializable) mFeedback2);
                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("isYy", true);
                intent.putExtra("step", "1");
                startActivity(intent);
            }
        });
        ll_yygl_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VipHouseDesignActivity.this, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback2", (Serializable) mFeedback2);
                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("isYy", true);
                intent.putExtra("step", "2");
                startActivity(intent);
            }
        });
        ll_yygl_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VipHouseDesignActivity.this, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback2", (Serializable) mFeedback2);
                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("isYy", true);
                intent.putExtra("step", "3");
                startActivity(intent);
            }
        });
        setHorticultureProgress(hmp_yygl, num, a);
    }

    private void setHorticultureProgress(HouseManageProgress hmp_yygl, int num, int a) {
        if (num == 2) {
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
        int num = homeBean.getFeedback().size();
        hmp_jzfw.setNum(num);
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
        for (int i = 0; i < num; i++) {
            if (!TextUtils.isEmpty(homeBean.getFeedback().get(i).getContent1())) {
                a = i + 1;
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

    private void sethomeProgress(HouseManageProgress hmp_jzfw, int num, int a) {
        if (num == 2) {
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
        hmp_fwgl.setNum(2);
        mFeedbackBeanXXX = vacancyBean.getFeedback().get(0);
        mFeedbackBeanXXX1 = vacancyBean.getFeedback().get(1);
        if (!TextUtils.isEmpty(mFeedbackBeanXXX.getContent1())) {
            hmp_fwgl.setView2Progress(1);
            tv_fwgl_1.setText(TimeUtils.getDate(mFeedbackBeanXXX.getStime()));
            tv_fwgl_cqxs_1.setText(TimeUtils.getDate(mFeedbackBeanXXX.getWtime1()));
            ll_fwgl_cqxs_1.setOnClickListener(mOnClickListener);
            if (!TextUtils.isEmpty(mFeedbackBeanXXX.getContent2())) {
                ll_fwgl_zlxj_1.setOnClickListener(mOnClickListener);
                tv_fwgl_zlxj_1.setText(TimeUtils.getDate(mFeedbackBeanXXX.getWtime2()));
                if (!TextUtils.isEmpty(mFeedbackBeanXXX.getContent3())) {
                    tv_fwgl_tsqkfk_1.setText(TimeUtils.getDate(mFeedbackBeanXXX.getWtime3()));
                    ll_fwgl_tsqkfk_1.setOnClickListener(mOnClickListener);
                }
            }
        }
        if (!TextUtils.isEmpty(mFeedbackBeanXXX1.getContent1())) {
            hmp_fwgl.setView2Progress(2);
            tv_fwgl_2.setText(TimeUtils.getDate(mFeedbackBeanXXX1.getStime()));
            tv_fwgl_cqxs_2.setText(TimeUtils.getDate(mFeedbackBeanXXX1.getWtime1()));
            ll_fwgl_cqxs_2.setOnClickListener(mOnClickListener);
            if (!TextUtils.isEmpty(mFeedbackBeanXXX1.getContent2())) {
                tv_fwgl_zlxj_2.setText(TimeUtils.getDate(mFeedbackBeanXXX1.getWtime2()));
                ll_fwgl_zlxj_2.setOnClickListener(mOnClickListener);
                if (!TextUtils.isEmpty(mFeedbackBeanXXX1.getContent3())) {
                    tv_fwgl_tsqkfk_2.setText(TimeUtils.getDate(mFeedbackBeanXXX1.getWtime3()));
                    ll_fwgl_tsqkfk_2.setOnClickListener(mOnClickListener);
                }
            }
        }
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.ll_fwgl_cqxs_1) {
                Intent intent = new Intent(VipHouseDesignActivity.this, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedbackBeanXXX", mFeedbackBeanXXX);
                intent.putExtra("bundle", bundle);
                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("isXs", true);
                intent.putExtra("time", "1");
                intent.putExtra("step", "A");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_fwgl_zlxj_1) {
                Intent intent = new Intent(VipHouseDesignActivity.this, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedbackBeanXXX", mFeedbackBeanXXX);
                intent.putExtra("bundle", bundle);
                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("isXs", true);
                intent.putExtra("time", "1");
                intent.putExtra("step", "B");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_fwgl_tsqkfk_1) {
                Intent intent = new Intent(VipHouseDesignActivity.this, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedbackBeanXXX", mFeedbackBeanXXX);
                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("isXs", true);
                intent.putExtra("time", "1");
                intent.putExtra("step", "C");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_fwgl_cqxs_2) {
                Intent intent = new Intent(VipHouseDesignActivity.this, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedbackBeanXXX1", mFeedbackBeanXXX1);

                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("isXs", true);
                intent.putExtra("time", "2");
                intent.putExtra("step", "A");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_fwgl_zlxj_2) {
                Intent intent = new Intent(VipHouseDesignActivity.this, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedbackBeanXXX1", mFeedbackBeanXXX1);
                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("isXs", true);
                intent.putExtra("time", "2");
                intent.putExtra("step", "B");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_fwgl_tsqkfk_2) {
                Intent intent = new Intent(VipHouseDesignActivity.this, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedbackBeanXXX1", mFeedbackBeanXXX1);
                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("isXs", true);
                intent.putExtra("time", "2");
                intent.putExtra("step", "C");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_zl_gl_zjsq) {//租赁管理部分
                Intent intent = new Intent(VipHouseDesignActivity.this, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback", mFeedback);
                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("isZl", true);
                intent.putExtra("step", "A");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_zl_gl_fwzfzt) {
                Intent intent = new Intent(VipHouseDesignActivity.this, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback", mFeedback);
                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("isZl", true);
                intent.putExtra("step", "B");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_zl_gl_jgqd) {
                Intent intent = new Intent(VipHouseDesignActivity.this, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback", mFeedback);
                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("isZl", true);
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
                Intent intent = new Intent(VipHouseDesignActivity.this, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback1", (Serializable) mFeedback1);
                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("isJz", true);
                intent.putExtra("step", "0");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_jzfw_2) {
                Intent intent = new Intent(VipHouseDesignActivity.this, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback1", (Serializable) mFeedback1);
                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("isJz", true);
                intent.putExtra("step", "1");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_jzfw_3) {
                Intent intent = new Intent(VipHouseDesignActivity.this, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback1", (Serializable) mFeedback1);
                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("isJz", true);
                intent.putExtra("step", "2");
                startActivity(intent);
            } else if (view.getId() == R.id.ll_jzfw_4) {
                Intent intent = new Intent(VipHouseDesignActivity.this, O2OServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mFeedback1", (Serializable) mFeedback1);
                intent.putExtra("avatar", mBillBean.getAvatar());
                intent.putExtra("nickname", mBillBean.getNickname());
                intent.putExtra("bundle", bundle);
                intent.putExtra("isJz", true);
                intent.putExtra("step", "3");
                startActivity(intent);
            }
        }
    };

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.vip_house_design_ll_addr:
                Bundle bundle = new Bundle();
                bundle.putString("flag","design");
                ActivityTools.goNextActivityForResult(this, VipHouseChooseActivity.class, bundle, 100);
                break;
            case R.id.vip_house_design_bt_commit:
                bundle = new Bundle();
                bundle.putString("flag", "-1");
                bundle.putString("design", "1");
                ActivityTools.goNextActivity(this, SelfSupportManageScheme.class, bundle);
                break;
            case R.id.ib_fwgl_1:
                if (isShow1) {
                    isShow1 = false;
                    ib_fwgl_1.setBackgroundResource(R.mipmap.user_zk);
                    ll_1_fwgl.setVisibility(View.GONE);
                } else {
                    isShow1 = true;
                    ib_fwgl_1.setBackgroundResource(R.mipmap.user_sq);
                    ll_1_fwgl.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ib_fwgl_2:
                if (isShow2) {
                    isShow2 = false;
                    ib_fwgl_2.setBackgroundResource(R.mipmap.user_zk);
                    ll_2_fwgl.setVisibility(View.GONE);
                } else {
                    isShow2 = true;
                    ib_fwgl_2.setBackgroundResource(R.mipmap.user_sq);
                    ll_2_fwgl.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_history_month:
                DatePicker2PopView d2p = new DatePicker2PopView(VipHouseDesignActivity.this);
                d2p.openPopWindow(v);
                d2p.setDatePicOnClickListener(new DatePicker2PopView.DatePicOnClickListener() {
                    @Override
                    public void datePicker(String str) {
                        month = TimeUtils.getMonthStamp(str);
                        initData();
                    }
                });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                tvaddr.setText(data.getStringExtra("addr"));
                h_id = data.getStringExtra("h_id");
                ToastUtil.longToast(VipHouseDesignActivity.this, h_id);
                tvaddr.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }
        }
    }
}
