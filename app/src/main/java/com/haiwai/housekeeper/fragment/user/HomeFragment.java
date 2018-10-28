package com.haiwai.housekeeper.fragment.user;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.LoginActivity;
import com.haiwai.housekeeper.activity.server.ProMainActivity;
import com.haiwai.housekeeper.activity.user.AllBusinessActivity;
import com.haiwai.housekeeper.activity.user.IssueRequireMakeSureActivity;
import com.haiwai.housekeeper.activity.user.O2ODetailActivity;
import com.haiwai.housekeeper.activity.user.O2ODetailActivity2;
import com.haiwai.housekeeper.activity.user.ShowWebActivity;
import com.haiwai.housekeeper.activity.user.VipNewHouseChooseActivity;
import com.haiwai.housekeeper.activity.user.message.ConversationListStaticActivity;
import com.haiwai.housekeeper.adapter.GlideImageLoader;
import com.haiwai.housekeeper.adapter.HomeHomeServiceAdapter;
import com.haiwai.housekeeper.adapter.HomeHotAdapter;
import com.haiwai.housekeeper.adapter.HomeHouseMaintenanceAdapter;
import com.haiwai.housekeeper.adapter.HomeLifeServiceAdapter;
import com.haiwai.housekeeper.adapter.HomeSelfServiceAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseFragment;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.HomeEntity;
import com.haiwai.housekeeper.entity.HomeTwoEntity;
import com.haiwai.housekeeper.entity.ZiYBean;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.https.okhttp.OKRequestCallback;
import com.haiwai.housekeeper.https.okhttp.OkHttpUtils;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.EvmUtil;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SizeUtil;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.utils.UpdateUtils;
import com.haiwai.housekeeper.view.PullToZoomScrollView2;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

public class HomeFragment extends BaseFragment implements HomeNewMenuTab.OnMenuClickListener {
    private int ivMenuHeight = 0;
    private HomeMenuTab menuTab;
    private LinearLayout llunderpic;
    //第一次登录时提示按钮 通知用户此按钮可点
//    private ImageButton iv_menu;
    private RelativeLayout rltitle0, rltitle;
    private PullToZoomScrollView2 pullscrollview;
    private LinearLayout llcontainer;
    private RelativeLayout rl_bg;
    private int llHeight;
    private int rlhight;

    //自营分类
    private RecyclerView recyclerView1;
    private HomeSelfServiceAdapter homeSelfServiceAdapter;
    private List<ZiYBean> list1 = new ArrayList<>();

    //家居服务
    private RecyclerView recyclerView2;
    private HomeHomeServiceAdapter homeHomeServiceAdapter;
    private List<HomeEntity.DataBean.JiajBean> list2 = new ArrayList<>();
    private List<Integer> picList2 = new ArrayList<>();
    //房屋维修
    private RecyclerView recyclerView3;
    private HomeHouseMaintenanceAdapter homeHouseMaintenanceAdapter;
    private List<HomeEntity.DataBean.FanwBean> list3 = new ArrayList<>();
    private List<Integer> picList3 = new ArrayList<>();
    //生活服务
    private RecyclerView recyclerView4;
    private HomeLifeServiceAdapter homeLifeServiceAdapter;
    private List<HomeEntity.DataBean.ShenhBean> list4 = new ArrayList<>();
    private List<Integer> picList4 = new ArrayList<>();
    //热门
    private RecyclerView recyclerView5;
    private HomeHotAdapter homeHotAdapter;
    private List<HomeEntity.DataBean.RemBean> list5 = new ArrayList<>();

    LDReceiver receiver;
    private HomeNewMenuTab mHb;
    private ImageView home_iv_title;
    private TextView tv_home_desc;

    private ImageButton user_home_iv_float_toserve, user_home_iv_talk;
    private View user_home_bottom_line;
    private String isZhorEn = "";
    private String link;
    private Banner banner;
    private ImageView iv_red;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.user_fragment_home, null, false);
        return rootView;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tv_home_desc = (TextView) view.findViewById(R.id.tv_home_desc);
        tv_home_desc.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        ((TextView) view.findViewById(R.id.tv_home_desc1)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        ((TextView) view.findViewById(R.id.tv_cycle_service)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        ((TextView) view.findViewById(R.id.tv_cycle_desc)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        ((TextView) view.findViewById(R.id.tv_home_service)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        ((TextView) view.findViewById(R.id.tv_house_service)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        ((TextView) view.findViewById(R.id.tv_live_service)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        ((TextView) view.findViewById(R.id.tv_app_name)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        isZhorEn = AppGlobal.getInstance().getLagStr();

        rltitle0 = (RelativeLayout) view.findViewById(R.id.home_titlebar_0);
        banner = (Banner) view.findViewById(R.id.banner);
        rltitle = (RelativeLayout) view.findViewById(R.id.user_home_rl_title);
        user_home_iv_float_toserve = (ImageButton) view.findViewById(R.id.user_home_iv_float_toserve);
        user_home_iv_talk = (ImageButton) view.findViewById(R.id.user_home_iv_talk);
        iv_red = (ImageView) view.findViewById(R.id.iv_red);
        view.findViewById(R.id.bar_collapse_ib_search).setVisibility(View.GONE);
        user_home_bottom_line = view.findViewById(R.id.user_home_bottom_line);
        view.findViewById(R.id.user_home_iv_toserve).setOnClickListener(this);
        view.findViewById(R.id.user_home_iv_float_toserve).setOnClickListener(this);
        view.findViewById(R.id.user_home_iv_talk).setOnClickListener(this);
        pullscrollview = (PullToZoomScrollView2) view.findViewById(R.id.user_home_ob_body);
        //llcontainer = (LinearLayout) view.findViewById(R.id.user_home_ll_title_container);
        //llunderpic = (LinearLayout) view.findViewById(R.id.home_ll_underpic);
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);

        View mainBottomView = getActivity().getLayoutInflater().inflate(R.layout.user_activity_main, null);
        View bottomview = mainBottomView.findViewById(R.id.tabbar_bottom_layout);

        int mviewHeight = (metrics.heightPixels - SizeUtil.getViewHeight(bottomview)) / 5;
        LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, mviewHeight);
        //  llunderpic.setLayoutParams(params);

        rl_bg = (RelativeLayout) view.findViewById(R.id.home_rl_title_container);

        //  rl_bg.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, PlatUtils.getImageRario(getActivity(), 5, 4)));
        // rl_bg.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 100));
        Log.e("result---<><>", rl_bg.getHeight() + "aaa");
        //   RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rl_bg.getLayoutParams();
//        ivMenuHeight = metrics.heightPixels / 2 - EvmUtil.dip2px(getActivity(), 155);//暂时取屏幕中间

//        iv_menu = (ImageButton) view.findViewById(R.id.home_iv_menu);//点击弹窗
//        iv_menu.setDrawingCacheEnabled(false);
//        RelativeLayout.LayoutParams menu_param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        menu_param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, -1);
//        menu_param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, -1);
//        menu_param.rightMargin = EvmUtil.dip2px(getActivity(), 30);
//        int x = mviewHeight / 2 - EvmUtil.dip2px(getActivity(), 30);
//        menu_param.bottomMargin = x;
//        ivMenuHeight = x + SizeUtil.getViewHeight(bottomview);
//        iv_menu.setLayoutParams(menu_param);

        pullscrollview.smoothScrollTo(0, 0);
        // 获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = rl_bg.getViewTreeObserver();

        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rl_bg.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);

                rlhight = rl_bg.getLayoutParams().height;
                Log.e("result---<><>", rl_bg.getHeight() + "aaa");
//                LogUtil.e("titlehhhhh", llHeight + "");
                pullscrollview.setScrollViewListener(new PullToZoomScrollView2.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(PullToZoomScrollView2 scrollView, int x, int y, int oldx, int oldy) {
                        if (y <= rlhight / 2) {
                            rltitle0.setVisibility(View.VISIBLE);
                            user_home_iv_float_toserve.setVisibility(View.GONE);
                            user_home_iv_talk.setImageResource(R.mipmap.home_message);
//                            bar_collapse_ib_search.setVisibility(View.GONE);
                            user_home_bottom_line.setVisibility(View.GONE);
                            float scale = (float) y * 2 / rlhight;
                            float alpha = (255 * scale);
                            rltitle.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                        } else if (y <= 0) {
                            rltitle0.setVisibility(View.VISIBLE);
                            user_home_iv_float_toserve.setVisibility(View.GONE);
                            user_home_iv_talk.setImageResource(R.mipmap.home_message);
//                            bar_collapse_ib_search.setVisibility(View.GONE);
                            user_home_bottom_line.setVisibility(View.GONE);
                        } else {
                            rltitle0.setVisibility(View.GONE);
                            user_home_iv_float_toserve.setVisibility(View.VISIBLE);
                            user_home_iv_talk.setImageResource(R.mipmap.home_message_red);
//                            bar_collapse_ib_search.setVisibility(View.GONE);
                            user_home_bottom_line.setVisibility(View.VISIBLE);
                            rltitle.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
                        }
//                        changeTitle(y);
//                        if (y <= 0) {
////                            rltitle.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));//AGB由相关工具获得，或者美工提供
//                            rltitle0.setVisibility(View.VISIBLE);
//                            rltitle.setVisibility(View.GONE);
//                        } else if (y >= 0 && y < rlhight / 2) {
//                            rltitle0.setVisibility(View.VISIBLE);
//                            rltitle.setVisibility(View.GONE);
//                        } else if (y >= rlhight / 2) {
//                            rltitle0.setVisibility(View.GONE);
//                            rltitle.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
//                            rltitle.setVisibility(View.VISIBLE);
//                            rltitle.setVisibility(View.VISIBLE);
////                            float scale = (float) y / llHeight;
////                            float alpha = (255 * scale);
//                            // 只是layout背景透明(仿知乎滑动效果)
////                            rltitle.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
//                        } else {
//                            rltitle.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
//                            rltitle0.setVisibility(View.GONE);
//                            rltitle.setVisibility(View.VISIBLE);
//                        }
                    }
                });
            }
        });

//        iv_menu.setOnClickListener(this);
//        iv_menu.setVisibility(View.VISIBLE);
        view.findViewById(R.id.home_tv_remen).setOnClickListener(this);
        view.findViewById(R.id.home_tv_fwwx).setOnClickListener(this);
        view.findViewById(R.id.home_tv_jjfw).setOnClickListener(this);
        view.findViewById(R.id.home_tv_shfw).setOnClickListener(this);
        //vip
        view.findViewById(R.id.bar_collapse_ib_search).setVisibility(View.GONE);


        //自营
        recyclerView1 = (RecyclerView) view.findViewById(R.id.home_recycleview1);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
//设置布局管理器
        recyclerView1.setLayoutManager(layoutManager);
//设置为垂直布局，这也是默认的
//        gridLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
//设置Adapter
//        recyclerView1.setAdapter(recycleAdapter);
//        设置分隔线
//        recyclerView1.addItemDecoration(new DividerGridItemDecoration(getActivity()));
//设置增加或删除条目的动画
//        recyclerView1.setItemAnimator( new DefaultItemAnimator());

        //家居服务
        recyclerView2 = (RecyclerView) view.findViewById(R.id.home_recycleview2);
        StaggeredGridLayoutManager layoutManager2 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView2.setLayoutManager(layoutManager2);

        //房屋维修
        recyclerView3 = (RecyclerView) view.findViewById(R.id.home_recycleview3);
        StaggeredGridLayoutManager layoutManager3 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView3.setLayoutManager(layoutManager3);

        //生活服务
        recyclerView4 = (RecyclerView) view.findViewById(R.id.home_recycleview4);
        StaggeredGridLayoutManager layoutManager4 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView4.setLayoutManager(layoutManager4);

        //热门
        recyclerView5 = (RecyclerView) view.findViewById(R.id.home_recycleview5);
        StaggeredGridLayoutManager layoutManager5 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView5.setLayoutManager(layoutManager5);


    }

//    private void setBack(float alph) {
//        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
//        params.alpha = alph;
//        getActivity().getWindow().setAttributes(params);
//    }


    private boolean isChooseHouse() {
        int day = Calendar.DAY_OF_MONTH;
        if (day >= 24 && day <= 31) {
            return true;
        } else if (day == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void initData() {
        //////////
        //app判断更新
        //////////
        requestUpdateVersion();


        HomeNewMenuTab.setOnMenuClickListener(this);
        receiver = new LDReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("refresh");
        getActivity().registerReceiver(receiver, intentFilter);

        //自营
        setData();
        homeSelfServiceAdapter = new HomeSelfServiceAdapter(getActivity(), list1);
        homeSelfServiceAdapter.setOnItemClickListener(new HomeSelfServiceAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                if (mApp.isLogin()) {
                    if (!isChooseHouse()) {
                        IMKitService.isConfig = false;
                        AppGlobal.getInstance().setIsFirst(true);
                        AppGlobal.getInstance().setIsHome(true);
                        Intent intent = new Intent(getActivity(), VipNewHouseChooseActivity.class);
                        intent.putExtra("type", list1.get(position).getType());
                        startActivity(intent);
                    } else {

                    }
                } else {
                    ActivityTools.goNextActivity(getActivity(), LoginActivity.class);
                }
            }

            @Override
            public void onLongClick(int position) {

            }
        });
        recyclerView1.setAdapter(homeSelfServiceAdapter);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());

        //家居服务
        homeHomeServiceAdapter = new HomeHomeServiceAdapter(getActivity(), list2, picList2);
        homeHomeServiceAdapter.setOnItemClickListener(new HomeHomeServiceAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", list2.get(position).getId());
                ActivityTools.goNextActivity(getActivity(), O2ODetailActivity.class, bundle);
            }

            @Override
            public void onLongClick(int position) {

            }
        });
        recyclerView2.setAdapter(homeHomeServiceAdapter);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());

        //房屋维修
        homeHouseMaintenanceAdapter = new HomeHouseMaintenanceAdapter(getActivity(), list3, picList3);
        homeHouseMaintenanceAdapter.setOnItemClickListener(new HomeHouseMaintenanceAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", list3.get(position).getId());
                ActivityTools.goNextActivity(getActivity(), O2ODetailActivity.class, bundle);
            }

            @Override
            public void onLongClick(int position) {

            }
        });
        recyclerView3.setAdapter(homeHouseMaintenanceAdapter);
        recyclerView3.setItemAnimator(new DefaultItemAnimator());

        //生活服务
        homeLifeServiceAdapter = new HomeLifeServiceAdapter(getActivity(), list4, picList4);
        homeLifeServiceAdapter.setOnItemClickListener(new HomeLifeServiceAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", list4.get(position).getId());
                ActivityTools.goNextActivity(getActivity(), O2ODetailActivity2.class, bundle);
            }

            @Override
            public void onLongClick(int position) {

            }
        });
        recyclerView4.setAdapter(homeLifeServiceAdapter);
        recyclerView4.setItemAnimator(new DefaultItemAnimator());

        //热门
        homeHotAdapter = new HomeHotAdapter(getActivity(), list5);
        homeHotAdapter.setOnItemClickListener(new HomeHotAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", list5.get(position).getId());
                ActivityTools.goNextActivity(getActivity(), O2ODetailActivity.class, bundle);
            }

            @Override
            public void onLongClick(int position) {

            }
        });
        recyclerView5.setAdapter(homeHotAdapter);
        recyclerView5.setItemAnimator(new DefaultItemAnimator());
        if (isNetworkAvailable()) {
            requestBusiness(0);
        }

    }

    private void requestUpdateVersion() {
        Map<String, String> map = new HashMap<>();
        // map.put("secret_key", SPUtils.getString(getActivity(), "secret", ""));
        mRequestQueue.add(new PlatRequest(getActivity(), Contants.update_version, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                System.out.println(">>>>>td>>>>" + response);
                if (code == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject dataObject = jsonObject.optJSONObject("data");
                        if (dataObject != null) {
                            int ver = Integer.parseInt(dataObject.getString("title"));
                            // int ver=0;
                            String con = dataObject.getString("content");
                            String url = dataObject.getString("url");
                            UpdateUtils.checkUpdate(getActivity(), true, ver, con, url);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    ToastUtil.shortToast(getActivity(), ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void requestTitlePic() {
        Map<String, String> map = new HashMap<>();
        map.put("secret_key", SPUtils.getString(getActivity(), "secret", ""));
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(getActivity(), map, null);
        Request request = new Request.Builder()
                .url(Contants.home_page)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(getActivity(), request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                HomeTwoEntity homeTwoEntity = new Gson().fromJson(response, HomeTwoEntity.class);
                if (homeTwoEntity.getStatus() == 200) {
                    try {
                        HomeTwoEntity.DataBean data = homeTwoEntity.getData();
                        if (homeTwoEntity != null) {
                            SPUtils.saveString(getContext(), "share_link", data.getHome_page().getShare_link());
                            if ("en".equals(isZhorEn)) {
                                bindData(data.getBanner());
                            } else {
                                bindData(data.getBanner());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    ToastUtil.shortToast(getActivity(), ErrorCodeUtils.getRegisterError("请求失败"));
                }
            }
        });
    }

    private void bindData(final List<HomeTwoEntity.DataBean.BannerBean> bannerBeanList) {
//        if (!TextUtils.isEmpty(content)) {
//            tv_home_desc.setText(content);
//        }
        if (bannerBeanList != null) {
            List<String> imagelist = new ArrayList<>();
            for (int i = 0; i < bannerBeanList.size(); i++) {
                imagelist.add(bannerBeanList.get(i).getImage());
            }
            banner.setImages(imagelist)//添加图片集合或图片url集合
                    .setDelayTime(3000)//设置轮播时间
                    .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)//指示器样式
                    .setImageLoader(new GlideImageLoader())//加载图片
                    .setIndicatorGravity(BannerConfig.RIGHT)//设置指示器位置
                    .start();
        }
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getContext(), ShowWebActivity.class);
                intent.putExtra("url", bannerBeanList.get(position).getLink());
                intent.putExtra("title", getString(R.string.house_design_detail));
                startActivity(intent);
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    private void setData() {
        list1.clear();
        ZiYBean bean2;
        ZiYBean bean3;
        ZiYBean bean4;
        if (!isZhorEn.equals("zh")) {
            bean2 = new ZiYBean("RENTAL\nMANAGEMENT", "Courtyard Management", "3", R.mipmap.pic_tygl, "");
            bean3 = new ZiYBean("MULTI SERVICE\nLAWN CARE", "Rental Management", "4", R.mipmap.pic_zlgl, "");
            bean4 = new ZiYBean("HOUSERKEEPING", "Empty House Management", "5", R.mipmap.pic_jzfw, "");
        } else {
            bean2 = new ZiYBean("庭院管理", "Courtyard Management", "3", R.mipmap.pic_tygl, "");
            bean3 = new ZiYBean("租赁管理", "Rental Management", "4", R.mipmap.pic_zlgl, "");
            bean4 = new ZiYBean("信件管理", "Mails Management", "5", R.mipmap.pic_jzfw, "");
        }

        list1.add(bean4);
        list1.add(bean3);
        list1.add(bean2);

        picList2.add(R.mipmap.pic_skill_fwbj);
        picList2.add(R.mipmap.pic_skill_cpxj);
        picList2.add(R.mipmap.pic_skill_dtqx);
//        picList2.add(R.mipmap.pic_skill_gyqx);
//        picList2.add(R.mipmap.pic_skill_ljcl);
        //////////////////////////////////////////////////三星手机   图片比例太大

        // picList2.add(R.mipmap.pic_skill_dtqx);
        picList2.add(R.mipmap.pic_skill_bybj);


//        picList2.add(R.mipmap.pic_skill_abxt);
//        picList2.add(R.mipmap.pic_skill_ch);

        picList3.add(R.mipmap.pic_skill_wxjg);
        picList3.add(R.mipmap.pic_skill_wdwx);
//        picList3.add(R.mipmap.pic_skill_yyxj);
        picList3.add(R.mipmap.pic_skill_jd);
//        picList3.add(R.mipmap.pic_skill_gjfx);
        picList3.add(R.mipmap.pic_skill_snfs);
        picList3.add(R.mipmap.pic_skill_swfs);
        picList3.add(R.mipmap.pic_skill_hs);
//        picList3.add(R.mipmap.pic_skill_dg);
//        picList3.add(R.mipmap.pic_skill_lry);

//        picList4.add(R.mipmap.pic_skill_l_jjyc);0
//        picList4.add(R.mipmap.pic_skill_l_ptdb);1
        picList4.add(R.mipmap.pic_skill_l_bx);//2
//        picList4.add(R.mipmap.pic_skill_l_ymzx);3
        picList4.add(R.mipmap.pic_skill_l_fwfw);//4
//        picList4.add(R.mipmap.pic_skill_l_jsjl);5
//        picList4.add(R.mipmap.pic_skill_l_fygz);6
        picList4.add(R.mipmap.pic_skill_l_flzx);//7
        picList4.add(R.mipmap.pic_skill_l_fcmm);//8
        picList4.add(R.mipmap.pic_skill_l_lydl);//9

    }

    /**
     * 业务信息
     */
    private void requestBusiness(final int i) {
        LoadDialog.showProgressDialog(getContext());
        Map<String, String> map = new HashMap<>();
        map.put("secret_key", SPUtils.getString(getActivity(), "secret", ""));
        mRequestQueue.add(new PlatRequest(getActivity(), Contants.service_type, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                LoadDialog.closeProgressDialog();
                if (code == 200) {
                    requestTitlePic();
                    if (i == 1)
                        ToastUtil.shortToast(getActivity(), getString(R.string.refres_suucess));
                    final HomeEntity entity = new Gson().fromJson(response, HomeEntity.class);
                    AssetsUtils.initSkillData(entity);
                    SPUtils.saveString(getActivity(), "home", response);
                    homeSelfServiceAdapter.notifyDataSetChanged();

                    list2.clear();
                    for (int i = 0; i < entity.getData().getJiaj().size(); i++) {
                        if (i != 3 && i != 4 && i != 6 && i != 7) {
                            list2.add(entity.getData().getJiaj().get(i));
                        }
                    }
//                    list2.addAll(entity.getData().getJiaj());
                    homeHomeServiceAdapter.notifyDataSetChanged();

                    list3.clear();
                    for (int i = 0; i < entity.getData().getFanw().size(); i++) {
                        if (i != 2 && i != 4 && i != 8 && i != 9) {
                            list3.add(entity.getData().getFanw().get(i));
                        }
                    }

                    homeHouseMaintenanceAdapter.notifyDataSetChanged();

                    list4.clear();
                    List<HomeEntity.DataBean.ShenhBean> mList = entity.getData().getShenh();


                    for (int i = 0; i < mList.size(); i++) {
                        Log.i("nameInforamtion", mList.get(i).getName());
                    }
//                    mList.remove(1);
//                    mList.remove(2);
//                    mList.remove(5);
//
//                    list4.addAll(mList);

                    for (int i = 0; i < mList.size(); i++) {
                        if (i != 0 && i != 3 && i != 6 && i != 5 && i != 1) {
                            Log.i("---nameInforamtion", mList.get(i).getName());
                            list4.add(mList.get(i));
                        }

                    }


//                    list4.addAll(mList);
//                    list4.remove(1);
//                    list4.remove(3);
//                    list4.remove(6);
                    homeLifeServiceAdapter.notifyDataSetChanged();

                    list5.clear();
                    list5.addAll(entity.getData().getRem());
                    homeHotAdapter.notifyDataSetChanged();
                } else {
                    ToastUtil.shortToast(getActivity(), ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void showHelpWindow(View view) {
//        iv_menu.setVisibility(View.GONE);
//
//
//        fragment.show(getFragmentManager(), "123");
//        mHb = new HomeNewMenuTab(getActivity(),isZhorEn);
//        mHb.showPopUpWindow(view);
//        mHb.setViwShow();


    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
//            case R.id.home_iv_menu://弹窗
//                if (mApp.isLogin()) {
//                    showHelpWindow(v);
//                } else {
//                    ActivityTools.goNextActivity(getActivity(), LoginActivity.class);
//                    getActivity().overridePendingTransition(R.anim.push_up_in,
//                            R.anim.push_up_out);
//                }
//                break;
            case R.id.user_home_iv_talk:
                if (mApp.isLogin()) {
                    ActivityTools.goNextActivity(getActivity(), ConversationListStaticActivity.class);
                } else {
                    iv_red.setVisibility(View.GONE);
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, Activity.RESULT_FIRST_USER);
                    getActivity().overridePendingTransition(R.anim.push_up_in,
                            R.anim.push_up_out);
                }
                break;
            case R.id.user_home_iv_toserve:
            case R.id.user_home_iv_float_toserve://跳转到服务端
                if (mApp.isLogin()) {
                    ActivityTools.goNextActivity(getActivity(), ProMainActivity.class);
                } else {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, Activity.RESULT_FIRST_USER);
                    getActivity().overridePendingTransition(R.anim.push_up_in,
                            R.anim.push_up_out);
                }
                break;
            case R.id.home_tv_remen:
                break;
            case R.id.home_tv_fwwx:
                Intent intent = new Intent(getActivity(), AllBusinessActivity.class);
                intent.putExtra("isAll", true);
                startActivity(intent);
                break;
            case R.id.home_tv_jjfw:
                Intent intent1 = new Intent(getActivity(), AllBusinessActivity.class);
                intent1.putExtra("isAll", true);
                startActivity(intent1);
                break;
            case R.id.home_tv_shfw:
                Intent intent2 = new Intent(getActivity(), AllBusinessActivity.class);
                intent2.putExtra("isAll", true);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Activity.RESULT_FIRST_USER) {
            if (resultCode == -1) {
                ActivityTools.goNextActivity(getActivity(), ProMainActivity.class);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }


    @Override
    public void tabMenuClick(int type) {
//        if (0 == type) {//客服
//            mHb.dismiss();
//            iv_menu.setVisibility(View.VISIBLE);
//            if (mApp.isLogin()) {
//                Information info = new Information();
//                info.setSysNum(Contants.SYSNUM);/* 必填 */
//                info.setInitModeType(1);//*****************************暂时之调用机器人-1
//                info.setArtificialIntelligence(false);//转为人工true
//                info.setArtificialIntelligenceNum(10);//只有人工true时起作用
//                info.setUseVoice(true);//使用语音
//                info.setColor("#FF0000");/* 选填，默认为"#09aeb0". 可以设置头部背景，提交评价背景，相似问题字体颜色和富文本类型中“阅读全文”字体颜色*/
//                info.setUid(AppGlobal.getInstance().getUser().getUid());/* 选填，设置用户唯一标识 */
//                info.setArtificialIntelligence(false);/* 智能转人工按钮，选填. 默认为false. 机器人客服优先模式时, false:显示转人工按钮；true:机器人有未知问题、引导回答时才显示转人工按钮 */
//                SobotApi.startSobotChat(getActivity(), info);
//            } else {
//                ActivityTools.goNextActivity(getActivity(), LoginActivity.class);
//                getActivity().overridePendingTransition(R.anim.push_up_in,
//                        R.anim.push_up_out);
//            }
//        } else if (1 == type) {//翻译
//            mHb.dismiss();
//            iv_menu.setVisibility(View.VISIBLE);
//            if (mApp.isLogin()) {
//                ActivityTools.goNextActivity(getActivity(), TranslateActivity.class);
//            } else {
//                ActivityTools.goNextActivity(getActivity(), LoginActivity.class);
//                getActivity().overridePendingTransition(R.anim.push_up_in,
//                        R.anim.push_up_out);
//            }
//        } else if (2 == type) {//详细定制  -->全部业务
//            mHb.dismiss();
//            iv_menu.setVisibility(View.VISIBLE);
//            ActivityTools.goNextActivity(getActivity(), AllBusinessActivity.class);
//        } else if (3 == type) {//情况报告-->房屋管理
//            mHb.dismiss();
//            iv_menu.setVisibility(View.VISIBLE);
//            ActivityTools.goNextActivity(getActivity(), FwZdActivity.class);
//        } else if (4 == type) {//定制管理--订单配置
//            mHb.dismiss();
//            iv_menu.setVisibility(View.VISIBLE);
//            ActivityTools.goNextActivity(getActivity(), OrderConfigActivity.class);
//        } else if (5 == type) {//关闭
//            mHb.setViewHide();
//            new Handler().postDelayed(new Runnable() {
//                public void run() {
//                    mHb.dismiss();
//                    iv_menu.setVisibility(View.VISIBLE);
//                }
//            }, 500);
//        }

    }

    public class LDReceiver extends BroadcastReceiver {
        public LDReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("refresh".equals(intent.getAction())) {
                if (intent.getBooleanExtra("do", true)) {
                    requestBusiness(1);
                } else {
                }
            }

        }
    }

    @SuppressLint("WrongConstant")
    public void RedVisibility() {
        if (iv_red.getVisibility() == 8) {
            iv_red.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("WrongConstant")
    public void RedGone() {
        if (iv_red.getVisibility() == 0) {
            iv_red.setVisibility(View.GONE);
        }
    }
}
