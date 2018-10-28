package com.haiwai.housekeeper.fragment.server;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.LoginActivity;
import com.haiwai.housekeeper.activity.base.MapBoxMapActivity;
import com.haiwai.housekeeper.activity.server.ProAddressActivity;
import com.haiwai.housekeeper.activity.server.ProMainActivity;
import com.haiwai.housekeeper.activity.user.MainActivity;
import com.haiwai.housekeeper.adapter.OrderFragmentPagerAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProFragment;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils2;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.PopupMulitiSelectionLayout;
import com.haiwai.housekeeper.view.PopupSelectionLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderFragment extends BaseProFragment {
    private ImageView mTabLineIv;
    /*
     * 屏幕的宽度
     */

    private int screenWidth;
    private TextView tvOrderNo, tvOrderYes, tvOrderHistory;
    private ArrayList<Fragment> mFragmentList = null;
    private ViewPager mPageVp;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;

    /**
     * Fragment
     */
    private OrderNoFragment noFra;
    private OrderYesFragment yesFra;
    private OrderHistoryFragment hisFra;


    ImageView iv_order_switch;
    ImageView iv_center_switch;
    private LinearLayout ll_conditon_layout;
    private TextView tvConditionSelect, tvConditionOrder;
    boolean flag = false;
    PopupWindow pop;
    PopupSelectionLayout psl_stateD, psl_stateE;
    PopupMulitiSelectionLayout psl_cateAA;
    //    PopupMulitiSelectionLayout psl_stateA, psl_stateB, psl_stateC;
    private TextView popup_window_selection_tv_reset, popup_window_selection_tv_confirm;
    private TextView tv_show_near, tv_dis_near;
    private List<String> strList = new ArrayList<>();
    private List<String> strWeekList = new ArrayList<>();
    private String kmStr = "";
    private String is_atStr = "";
    private String sortStr = "1";
    private OrderFragmentPagerAdapter mFragmentPagerAdapter;
    public static String orderFlag = "single";
    public static String myFlag = "";
    public List<String> skillList = new ArrayList<>();
    public List<String> skillWeekList = new ArrayList<>();
    private String isZhorEn = "";
    private View mOrderView;
    ProOderReceiver mProOderReceiver;
    private ImageView iv_zxfb, iv_jlzj;

    private ImageView ivWeekSwitch;

    private View popView1;
    private View popsView1;

    @Override
    protected void init() {
        mProOderReceiver = new ProOderReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("proJpush");
        getActivity().registerReceiver(mProOderReceiver, intentFilter);

        popView1 = LayoutInflater.from(context).inflate(R.layout.pro_pop_window, null);

        popsView1 = LayoutInflater.from(context).inflate(R.layout.pro_pop_right_window, null);


        isZhorEn = AppGlobal.getInstance().getLagStr();
        User user = AppGlobal.getInstance().getUser();
        Map<String, String> map = new HashMap<>();
        map.put("uid", user.getUid());
        map.put("secret_key", SPUtils.getString(getActivity(), "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.skill_o2o, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(">>>020>>>" + response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.optJSONArray("data");
                        if (jsonArray != null) {
                            skillList.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.optJSONObject(i);
                                String type = object.optString("type");
                                if (!TextUtils.isEmpty(AssetsUtils2.getSkillSingleName(type, isZhorEn, "single"))) {
                                    skillList.add(AssetsUtils2.getSkillSingleName(type, isZhorEn, "single"));
                                }

                            }
                        }
                        intSelfData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (code != 1114) {
                        ToastUtil.longToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                    }

                }
            }
        }));
    }

    private void intSelfData() {
        User user = AppGlobal.getInstance().getUser();
        Map<String, String> map = new HashMap<>();
        map.put("uid", user.getUid());
        map.put("secret_key", SPUtils.getString(getActivity(), "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.skill_self, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(">>>self>>>" + response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.optJSONArray("data");
                        if (jsonArray != null) {
                            skillWeekList.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.optJSONObject(i);
                                String type = object.optString("type");
                                if (!TextUtils.isEmpty(AssetsUtils2.getSkillSingleName(type, isZhorEn, "week"))) {
                                    skillWeekList.add(AssetsUtils2.getSkillSingleName(type, isZhorEn, "week"));
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (code != 1114) {
                        ToastUtil.longToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }
        }));
    }

    @Override
    public View initView() {
        mOrderView = View.inflate(context, R.layout.pro_fragment_order, null);
        initView(mOrderView);
        return mOrderView;
    }

    @Override
    protected void initData() {

    }


    public void initView(View orderView) {

        iv_order_switch = (ImageView) orderView.findViewById(R.id.iv_order_switch);
        iv_center_switch = (ImageView) orderView.findViewById(R.id.iv_center_switch);

        ivWeekSwitch = ((ImageView) orderView.findViewById(R.id.iv_week_roder_switch));
        if ("en".equals(isZhorEn)) {
            iv_center_switch.setImageResource(R.mipmap.en_one_selected);
            ivWeekSwitch.setImageResource(R.mipmap.en_week_default);
        } else {

            iv_center_switch.setImageResource(R.mipmap.icon_one_default);
            ivWeekSwitch.setImageResource(R.mipmap.icon_week_default);
        }
        tvOrderNo = (TextView) orderView.findViewById(R.id.tv_order_no);
        tvOrderYes = (TextView) orderView.findViewById(R.id.tv_order_yes);
        tvOrderHistory = (TextView) orderView.findViewById(R.id.tv_order_history);
        tvOrderNo.setTypeface(MyApp.getTf(), Typeface.ITALIC);
        tvOrderYes.setTypeface(MyApp.getTf(), Typeface.ITALIC);
        tvOrderHistory.setTypeface(MyApp.getTf(), Typeface.ITALIC);
        mTabLineIv = (ImageView) orderView.findViewById(R.id.iv_bottom_line);
        tvConditionSelect = (TextView) orderView.findViewById(R.id.tv_condition_select);
        tvConditionOrder = (TextView) orderView.findViewById(R.id.tv_condition_order);
        ll_conditon_layout = (LinearLayout) orderView.findViewById(R.id.ll_conditon_layout);
        mPageVp = (ViewPager) orderView.findViewById(R.id.id_page_vp);
        mPageVp.setOffscreenPageLimit(3);
        ivWeekSwitch.setOnClickListener(new mOnClickListener(100));
        iv_order_switch.setOnClickListener(new mOnClickListener(0));
        iv_center_switch.setOnClickListener(new mOnClickListener(1));
        tvOrderNo.setOnClickListener(new mOnClickListener(2));
        tvOrderYes.setOnClickListener(new mOnClickListener(3));
        tvOrderHistory.setOnClickListener(new mOnClickListener(4));
        tvConditionSelect.setOnClickListener(new mOnClickListener(5));//筛选
        tvConditionOrder.setOnClickListener(new mOnClickListener(6));//排序
        initTabLineWidth();
        initFragmentView();
    }

    private void initFragmentView() {
        mFragmentList = new ArrayList<>();
        noFra = new OrderNoFragment();
        yesFra = new OrderYesFragment();
        hisFra = new OrderHistoryFragment();
        mFragmentList.add(noFra);
        mFragmentList.add(yesFra);
        mFragmentList.add(hisFra);
        mFragmentPagerAdapter = new OrderFragmentPagerAdapter(getChildFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentPagerAdapter);
        //让ViewPager缓存2个页面
        mPageVp.setOffscreenPageLimit(3);
        mPageVp.setCurrentItem(0);
        tvOrderNo.setTextColor(Color.parseColor("#494949"));
        mPageVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }

            /**
             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
             * offsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float offset,
                                       int offsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                        .getLayoutParams();
                /**
                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
                 * 设置mTabLineIv的左边距 滑动场景：
                 * 记3个页面,
                 * 从左到右分别为0,1,2
                 * 0->1; 1->2; 2->1; 1->0
                 */

                if (currentIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3)) + screenWidth / 18;

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3)) + screenWidth / 18;

                } else if (currentIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3)) + screenWidth / 18;
                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3)) + screenWidth / 18;
                }
                mTabLineIv.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        tvOrderNo.setTextColor(Color.parseColor("#494949"));
                        if ("single".equals(orderFlag)) {
                            mOnYesOrderListener.onYesTerm(orderFlag, kmStr, is_atStr, sortStr, strList);
                        } else {
                            mOnYesOrderListener.onYesTerm(orderFlag, kmStr, is_atStr, sortStr, strWeekList);
                        }
                        break;
                    case 1:
                        tvOrderYes.setTextColor(Color.parseColor("#494949"));
                        if ("single".equals(orderFlag)) {
                            mOnYesOrderListener.onYesTerm(orderFlag, kmStr, is_atStr, sortStr, strList);
                        } else {
                            mOnYesOrderListener.onYesTerm(orderFlag, kmStr, is_atStr, sortStr, strWeekList);
                        }
                        break;
                    case 2:
                        tvOrderHistory.setTextColor(Color.parseColor("#494949"));
                        if ("single".equals(orderFlag)) {
                            mOnHisOrderListener.onHisTerm(orderFlag, kmStr, is_atStr, sortStr, strList);
                        } else {
                            mOnHisOrderListener.onHisTerm(orderFlag, kmStr, is_atStr, sortStr, strWeekList);
                        }
                        break;
                }
                currentIndex = position;
            }
        });
    }


    public void selectYes1() {
        currentIndex = 0;
        mPageVp.setCurrentItem(currentIndex);
        if ("en".equals(isZhorEn)) {
            iv_center_switch.setImageResource(R.mipmap.en_one_on_select);
            ivWeekSwitch.setImageResource(R.mipmap.en_week_selected);
        } else {
            iv_center_switch.setImageResource(R.mipmap.icon_one_on_select);
            ivWeekSwitch.setImageResource(R.mipmap.icon_week_selected);
        }
        resetTextView();
        tvOrderYes.setTextColor(Color.parseColor("#494949"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mOnNoOrderListener.onNoTerm("week", kmStr, is_atStr, sortStr, strWeekList);
            }
        }, 300);

//        mOnYesOrderListener.onYesTerm("week", kmStr, is_atStr, sortStr, strWeekList);

    }


    public void selectYes() {
        resetTextView();
        if ("en".equals(isZhorEn)) {
            iv_center_switch.setImageResource(R.mipmap.en_one_on_select);
            ivWeekSwitch.setImageResource(R.mipmap.en_week_selected);
        } else {
            iv_center_switch.setImageResource(R.mipmap.icon_one_on_select);
            ivWeekSwitch.setImageResource(R.mipmap.icon_week_selected);
        }
        tvOrderYes.setTextColor(Color.parseColor("#494949"));
        mOnYesOrderListener.onYesTerm("week", kmStr, is_atStr, sortStr, strList);
        currentIndex = 1;
        mPageVp.setCurrentItem(currentIndex);
    }

    public void selectWeek() {
        Log.i("jkljjl", "走这里了。。");

        mPageVp.setCurrentItem(0);
        currentIndex = 0;
        orderFlag = "week";
        if ("en".equals(isZhorEn)) {
            iv_center_switch.setImageResource(R.mipmap.en_one_on_select);
            ivWeekSwitch.setImageResource(R.mipmap.en_week_selected);
        } else {
            iv_center_switch.setImageResource(R.mipmap.icon_one_on_select);
            ivWeekSwitch.setImageResource(R.mipmap.icon_week_selected);
        }
        if (currentIndex == 0) {
            mOnNoOrderListener.onNoTerm(orderFlag, kmStr, is_atStr, sortStr, strWeekList);
        } else if (currentIndex == 1) {
            mOnYesOrderListener.onYesTerm(orderFlag, kmStr, is_atStr, sortStr, strWeekList);
        } else if (currentIndex == 2) {
            mOnHisOrderListener.onHisTerm(orderFlag, kmStr, is_atStr, sortStr, strWeekList);
        }
    }


    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getActivity().getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                .getLayoutParams();
        lp.width = screenWidth * 2 / 9;
        lp.leftMargin = screenWidth / 18;
        mTabLineIv.setLayoutParams(lp);
    }

    class mOnClickListener implements View.OnClickListener {
        int num;

        public mOnClickListener(int num) {
            this.num = num;
        }

        @Override
        public void onClick(View v) {
            switch (num) {
                case 0:
                    Bundle serve_bundle = new Bundle();
                    serve_bundle.putString("flag", "serve");
                    ActivityTools.goNextActivity(context, MainActivity.class, serve_bundle);
                    break;
                case 100:
//                    SPUtils.saveBoolean(LoginActivity.this,"isHaveServiceAddress",false);
                    if (!SPUtils.getBoolean(getContext(), "isHaveServiceAddress", false)) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage(getString(R.string.set_center))
                                .setNegativeButton(R.string.message_alert_no, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                })
                                .setPositiveButton(R.string.order_ti2, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                        ActivityTools.goNextActivity(getActivity(), ProAddressActivity.class);
                                    }
                                }).create().show();
                        return;
                    }

                    if ("en".equals(isZhorEn)) {
                        iv_center_switch.setImageResource(R.mipmap.en_one_on_select);
                        ivWeekSwitch.setImageResource(R.mipmap.en_week_selected);
                    } else {
                        iv_center_switch.setImageResource(R.mipmap.icon_one_on_select);
                        ivWeekSwitch.setImageResource(R.mipmap.icon_week_selected);
                    }

                    ll_conditon_layout.setVisibility(View.VISIBLE);
//                    if ("en".equals(isZhorEn)) {
//                        iv_center_switch.setImageResource(R.mipmap.pro_tobbar_week_en);
//                    } else {
//                        iv_center_switch.setImageResource(R.mipmap.pro_tobbar_week);
//                    }
                    orderFlag = "week";
                    if (currentIndex == 0) {
                        mOnNoOrderListener.onNoTerm(orderFlag, kmStr, is_atStr, sortStr, strWeekList);
                    } else if (currentIndex == 1) {
                        mOnYesOrderListener.onYesTerm(orderFlag, kmStr, is_atStr, sortStr, strWeekList);
                    } else if (currentIndex == 2) {
                        mOnHisOrderListener.onHisTerm(orderFlag, kmStr, is_atStr, sortStr, strWeekList);
                    }

                    break;
                case 1:


                    ll_conditon_layout.setVisibility(View.VISIBLE);


                    if ("en".equals(isZhorEn)) {
                        iv_center_switch.setImageResource(R.mipmap.en_one_selected);
                        ivWeekSwitch.setImageResource(R.mipmap.en_week_default);
                    } else {
                        iv_center_switch.setImageResource(R.mipmap.icon_one_default);
                        ivWeekSwitch.setImageResource(R.mipmap.icon_week_default);
                    }
                    orderFlag = "single";
                    if (currentIndex == 0) {
                        mOnNoOrderListener.onNoTerm(orderFlag, kmStr, is_atStr, sortStr, strList);
                    } else if (currentIndex == 1) {
                        mOnYesOrderListener.onYesTerm(orderFlag, kmStr, is_atStr, sortStr, strList);
                    } else if (currentIndex == 2) {
                        mOnHisOrderListener.onHisTerm(orderFlag, kmStr, is_atStr, sortStr, strList);
                    }
                    break;
                case 2:
                    mPageVp.setCurrentItem(0);
                    ((OrderNoFragment) mPageVp.getAdapter().instantiateItem(mPageVp, 0)).initData();
                    currentIndex = 0;
                    break;
                case 3:
                    mPageVp.setCurrentItem(1);
                    currentIndex = 1;
                    ((OrderYesFragment) mPageVp.getAdapter().instantiateItem(mPageVp, 1)).initData();
                    break;
                case 4:
                    mPageVp.setCurrentItem(2);
                    currentIndex = 2;
                    ((OrderHistoryFragment) mPageVp.getAdapter().instantiateItem(mPageVp, 2)).initData();
                    break;
                case 5:
                    showPopUpWidow(0, v);
                    tvConditionSelect.setSelected(true);
                    tvConditionOrder.setSelected(false);
                    break;
                case 6:
                    showPopUpWidow(1, v);
                    tvConditionSelect.setSelected(false);
                    tvConditionOrder.setSelected(true);
                    break;
                case 21://重置
                    psl_cateAA.setAllSelectFalse();
                    psl_cateAA.setAllSelectFalse();
                    psl_stateD.setAllSelectFalse();
                    psl_stateE.setAllSelectFalse();
                    break;
                case 22://确认
                    if ("single".equals(orderFlag)) {
                        strList.clear();
                        strList.addAll(psl_cateAA.getSelectList());
                    } else {
                        strWeekList.clear();
                        strWeekList.addAll(psl_cateAA.getSelectList());
                    }
                    kmStr = AssetsUtils2.getDis(psl_stateD.getSelectItem(), isZhorEn);
                    is_atStr = AssetsUtils2.getIsAt(psl_stateE.getSelectItem(), isZhorEn);
                    if (currentIndex == 0) {//待接单
                        pop.dismiss();
                        setAlpha(1.0f);
                        tvConditionSelect.setSelected(false);
                        tvConditionOrder.setSelected(false);
                        if ("single".equals(orderFlag)) {
                            mOnNoOrderListener.onNoTerm(orderFlag, kmStr, is_atStr, sortStr, strList);
                        } else {
                            mOnNoOrderListener.onNoTerm(orderFlag, kmStr, is_atStr, sortStr, strWeekList);
                        }
                    } else if (currentIndex == 1) {//已接单
                        pop.dismiss();
                        setAlpha(1.0f);
                        tvConditionSelect.setSelected(false);
                        tvConditionOrder.setSelected(false);
                        if ("single".equals(orderFlag)) {
                            mOnYesOrderListener.onYesTerm(orderFlag, kmStr, is_atStr, sortStr, strList);
                        } else {
                            mOnYesOrderListener.onYesTerm(orderFlag, kmStr, is_atStr, sortStr, strWeekList);
                        }
                    } else if (currentIndex == 2) {//历史
                        pop.dismiss();
                        setAlpha(1.0f);
                        tvConditionSelect.setSelected(false);
                        tvConditionOrder.setSelected(false);
                        if ("single".equals(orderFlag)) {
                            mOnHisOrderListener.onHisTerm(orderFlag, kmStr, is_atStr, sortStr, strList);
                        } else {
                            mOnHisOrderListener.onHisTerm(orderFlag, kmStr, is_atStr, sortStr, strWeekList);
                        }
                    }
                    break;
                case 31://最近发布
                    sortStr = "1";
                    iv_zxfb.setVisibility(View.VISIBLE);
                    iv_jlzj.setVisibility(View.INVISIBLE);
                    if (currentIndex == 0) {//待接单
                        pop.dismiss();
                        setAlpha(1.0f);
                        tvConditionSelect.setSelected(false);
                        tvConditionOrder.setSelected(false);
                        if ("single".equals(orderFlag)) {
                            mOnNoOrderListener.onNoTerm(orderFlag, kmStr, is_atStr, sortStr, strList);
                        } else {
                            mOnNoOrderListener.onNoTerm(orderFlag, kmStr, is_atStr, sortStr, strWeekList);
                        }
                    } else if (currentIndex == 1) {//已接单
                        pop.dismiss();
                        setAlpha(1.0f);
                        tvConditionSelect.setSelected(false);
                        tvConditionOrder.setSelected(false);
                        if ("single".equals(orderFlag)) {
                            mOnYesOrderListener.onYesTerm(orderFlag, kmStr, is_atStr, sortStr, strList);
                        } else {
                            mOnYesOrderListener.onYesTerm(orderFlag, kmStr, is_atStr, sortStr, strWeekList);
                        }
                    } else if (currentIndex == 2) {//历史
                        pop.dismiss();
                        setAlpha(1.0f);
                        tvConditionSelect.setSelected(false);
                        tvConditionOrder.setSelected(false);
                        if ("single".equals(orderFlag)) {
                            mOnHisOrderListener.onHisTerm(orderFlag, kmStr, is_atStr, sortStr, strList);
                        } else {
                            mOnHisOrderListener.onHisTerm(orderFlag, kmStr, is_atStr, sortStr, strWeekList);
                        }
                    }
                    break;
                case 32://距离最近
                    sortStr = "2";
                    iv_zxfb.setVisibility(View.INVISIBLE);
                    iv_jlzj.setVisibility(View.VISIBLE);
                    if (currentIndex == 0) {//待接单
                        pop.dismiss();
                        setAlpha(1.0f);
                        tvConditionSelect.setSelected(false);
                        tvConditionOrder.setSelected(false);
                        if ("single".equals(orderFlag)) {
                            mOnNoOrderListener.onNoTerm(orderFlag, kmStr, is_atStr, sortStr, strList);
                        } else {
                            mOnNoOrderListener.onNoTerm(orderFlag, kmStr, is_atStr, sortStr, strWeekList);
                        }
                    } else if (currentIndex == 1) {//已接单
                        pop.dismiss();
                        setAlpha(1.0f);
                        tvConditionSelect.setSelected(false);
                        tvConditionOrder.setSelected(false);
                        if ("single".equals(orderFlag)) {
                            mOnYesOrderListener.onYesTerm(orderFlag, kmStr, is_atStr, sortStr, strList);
                        } else {
                            mOnYesOrderListener.onYesTerm(orderFlag, kmStr, is_atStr, sortStr, strWeekList);
                        }
                    } else if (currentIndex == 2) {//历史
                        pop.dismiss();
                        setAlpha(1.0f);
                        tvConditionSelect.setSelected(false);
                        tvConditionOrder.setSelected(false);
                        if ("single".equals(orderFlag)) {
                            mOnHisOrderListener.onHisTerm(orderFlag, kmStr, is_atStr, sortStr, strList);
                        } else {
                            mOnHisOrderListener.onHisTerm(orderFlag, kmStr, is_atStr, sortStr, strWeekList);
                        }
                    }
                    break;
            }

        }
    }


    private void showPopUpWidow(int i, View v) {
        if (i == 0) {
            if (!myFlag.equals(orderFlag)) {
                popView1 = LayoutInflater.from(context).inflate(R.layout.pro_pop_window, null);
                myFlag = orderFlag;
            }

            initPopsView(popView1);
            iv_zxfb = (ImageView) popView1.findViewById(R.id.iv_zxfb);
            iv_jlzj = (ImageView) popView1.findViewById(R.id.iv_jlzj);
            if ("1".equals(sortStr)) {
                iv_zxfb.setVisibility(View.VISIBLE);
                iv_jlzj.setVisibility(View.INVISIBLE);
            } else if ("2".equals(sortStr)) {
                iv_zxfb.setVisibility(View.INVISIBLE);
                iv_jlzj.setVisibility(View.VISIBLE);
            } else {
                iv_zxfb.setVisibility(View.INVISIBLE);
                iv_jlzj.setVisibility(View.INVISIBLE);
            }
            pop = new PopupWindow(popView1, WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT, true);
            pop.setAnimationStyle(R.style.drop_down_menu_anim_style);
            pop.setBackgroundDrawable(new BitmapDrawable());
            pop.setFocusable(true);
            pop.setOutsideTouchable(true);
            setAlpha(0.5f);
            pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    setAlpha(1.0f);
                }
            });
            pop.setTouchInterceptor(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE && !pop.isFocusable()) {
                        //如果焦点不在popupWindow上，且点击了外面，不再往下dispatch事件：
                        //不做任何响应,不 dismiss popupWindow
                        tvConditionSelect.setSelected(false);
                        tvConditionOrder.setSelected(false);

                        return true;
                    }
                    tvConditionSelect.setSelected(false);
                    tvConditionOrder.setSelected(false);
                    //否则default，往下dispatch事件:关掉popupWindow，
                    return false;
                }
            });
            pop.showAsDropDown(v);
        } else if (i == 1) {
            if (!myFlag.equals(orderFlag)) {
                popsView1 = LayoutInflater.from(context).inflate(R.layout.pro_pop_right_window, null);
                myFlag = orderFlag;
            }
            initPopView(popsView1);
            int w = getActivity().getWindowManager().getDefaultDisplay().getWidth();
            pop = new PopupWindow(popsView1, w / 11 * 10,
                    WindowManager.LayoutParams.WRAP_CONTENT, true);
            pop.setAnimationStyle(R.style.drop_right_anim_style);
            pop.setFocusable(true);
            pop.setOutsideTouchable(true);
            pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    setAlpha(1.0f);
                }
            });
            pop.setBackgroundDrawable(new BitmapDrawable());
            setAlpha(0.5f);
            pop.setTouchInterceptor(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE && !pop.isFocusable()) {
                        //如果焦点不在popupWindow上，且点击了外面，不再往下dispatch事件：
                        //不做任何响应,不 dismiss popupWindow
                        tvConditionSelect.setSelected(false);
                        tvConditionOrder.setSelected(false);

                        return true;
                    }
                    tvConditionSelect.setSelected(false);
                    tvConditionOrder.setSelected(false);
                    //否则default，往下dispatch事件:关掉popupWindow，
                    return false;
                }
            });
            pop.showAsDropDown(v);
        }

    }

    private void setAlpha(float alpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = alpha;
        getActivity().getWindow().setAttributes(lp);
    }

    private void initPopsView(View popView) {
        tv_show_near = (TextView) popView.findViewById(R.id.tv_show_near);
        tv_show_near.setOnClickListener(new mOnClickListener(31));
        tv_dis_near = (TextView) popView.findViewById(R.id.tv_dis_near);
        tv_dis_near.setOnClickListener(new mOnClickListener(32));
    }

    private void initPopView(View popView) {
        popup_window_selection_tv_reset = (TextView) popView.findViewById(R.id.popup_window_selection_tv_reset);
        popup_window_selection_tv_reset.setOnClickListener(new mOnClickListener(21));
        popup_window_selection_tv_confirm = (TextView) popView.findViewById(R.id.popup_window_selection_tv_confirm);
        popup_window_selection_tv_confirm.setOnClickListener(new mOnClickListener(22));
        psl_cateAA = (PopupMulitiSelectionLayout) popView.findViewById(R.id.psl_cateAA);
        if ("single".equals(orderFlag)) {
            String[] strA = (String[]) skillList.toArray(new String[skillList.size()]);
            psl_cateAA.setItemName(strA);
            if (strA.length % 3 == 0) {
                psl_cateAA.setVisibleNum(strA.length / 3, strA.length);
            } else {
                psl_cateAA.setVisibleNum(strA.length / 3 + 1, strA.length);
            }
        } else {
            String[] strA = (String[]) skillWeekList.toArray(new String[skillWeekList.size()]);
            psl_cateAA.setItemName(strA);
            if (strA.length % 3 == 0) {
                psl_cateAA.setVisibleNum(strA.length / 3, strA.length);
            } else {
                psl_cateAA.setVisibleNum(strA.length / 3 + 1, strA.length);
            }
        }
        psl_stateD = (PopupSelectionLayout) popView.findViewById(R.id.psl_cateD);
        String[] strD = {getString(R.string.pop_bx), getString(R.string.pop_5p), getString(R.string.pop_10p)};
        psl_stateD.setItemName(strD);
        psl_stateD.setVisibleNum(1, 3);
        psl_stateE = (PopupSelectionLayout) popView.findViewById(R.id.psl_stateE);
        String[] strE = {getString(R.string.pop_bx), getString(R.string.pop_yes), getString(R.string.pop_no)};
        if (!orderFlag.equals("week")) {
            psl_stateE.setItemName(strE);
            popView.findViewById(R.id.tv_is_at).setVisibility(View.VISIBLE);
        } else {
            psl_stateE.setVisibility(View.GONE);
            popView.findViewById(R.id.tv_is_at).setVisibility(View.GONE);
        }
        psl_stateE.setVisibleNum(1, 3);
    }


    /**
     * 重置颜色
     */
    private void resetTextView() {
        tvOrderNo.setTextColor(Color.parseColor("#A7A8AA"));
        tvOrderYes.setTextColor(Color.parseColor("#A7A8AA"));
        tvOrderHistory.setTextColor(Color.parseColor("#A7A8AA"));
    }

    //---------------设置条件回调接口------------------------
    //待接单
    public static OnNoOrderListener mOnNoOrderListener;

    public interface OnNoOrderListener {
        void onNoTerm(String orderFlag, String km, String is_at, String sort, List<String> strList);
    }

    public static void setOnNoOrderListener(OnNoOrderListener onNoOrderListener) {
        mOnNoOrderListener = onNoOrderListener;
    }

    //已接单
    public static OnYesOrderListener mOnYesOrderListener;

    public interface OnYesOrderListener {
        void onYesTerm(String orderFlag, String km, String is_at, String sort, List<String> strList);
    }

    public static void setOnYesOrderListener(OnYesOrderListener onYesOrderListener) {
        mOnYesOrderListener = onYesOrderListener;
    }

    //历史
    public static OnHisOrderListener mOnHisOrderListener;

    public interface OnHisOrderListener {
        void onHisTerm(String orderFlag, String km, String is_at, String sort, List<String> strList);
    }

    public static void setOnHisOrderListener(OnHisOrderListener onHisOrderListener) {
        mOnHisOrderListener = onHisOrderListener;
    }

    public class ProOderReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("proJpush".equals(intent.getAction())) {
                if (intent.getBooleanExtra("cycle", false)) {
                    if ("en".equals(isZhorEn)) {
                        iv_center_switch.setImageResource(R.mipmap.en_one_on_select);
                        ivWeekSwitch.setImageResource(R.mipmap.en_week_selected);
                    } else {
                        iv_center_switch.setImageResource(R.mipmap.icon_one_on_select);
                        ivWeekSwitch.setImageResource(R.mipmap.icon_week_selected);
                    }
                    resetTextView();
                    tvOrderYes.setTextColor(Color.parseColor("#494949"));
                    mOnYesOrderListener.onYesTerm("week", kmStr, is_atStr, sortStr, strWeekList);
                } else {
                    if ("en".equals(isZhorEn)) {
                        iv_center_switch.setImageResource(R.mipmap.en_one_on_select);
                        ivWeekSwitch.setImageResource(R.mipmap.en_week_selected);
                    } else {
                        iv_center_switch.setImageResource(R.mipmap.icon_one_on_select);
                        ivWeekSwitch.setImageResource(R.mipmap.icon_week_selected);
                    }
                    resetTextView();
                    tvOrderYes.setTextColor(Color.parseColor("#494949"));
                    mOnYesOrderListener.onYesTerm(orderFlag, kmStr, is_atStr, sortStr, strWeekList);
                }

                currentIndex = 0;
            }
        }
    }

    public void setCurrent() {
        mPageVp.setCurrentItem(2);
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(mProOderReceiver);
        super.onDestroy();
    }
}
