package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.LoginActivity;
import com.haiwai.housekeeper.adapter.O2oDetailAdapter;
import com.haiwai.housekeeper.adapter.O2oDetailAdapter2;
import com.haiwai.housekeeper.adapter.PopGvAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.entity.ProListEntity;
import com.haiwai.housekeeper.entity.SigGogleEntity;
import com.haiwai.housekeeper.entity.TitleEntity;
import com.haiwai.housekeeper.entity.TitleItem;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.libs.ui.PullToRefreshListView;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.EvmUtil;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LocationUtils;
import com.haiwai.housekeeper.utils.OptionUtils;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SizeUtil;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.MyGridView;
import com.haiwai.housekeeper.view.OnScrollYListener;
import com.haiwai.housekeeper.view.xlistview.XListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by ihope007 on 2016/9/9.
 * 020二级页面—pro列表-116.4-39.9
 *
 */
public class O2ODetailActivity2 extends BaseActivity implements XListView.IXListViewListener, PopGvAdapter.OnItemsClickListener {
    // TODO: 2016/9/12 重新设置右侧图片
    private RelativeLayout rltitle0;
    private RelativeLayout rltitle;
    private RelativeLayout pic_container;
    private int llHeight;//顶部图片高度
    private TextView tvbottom;
    private LinearLayout llLine;
    private TextView tvcollpasetitle, tvexpandtitle, tvlinetitle;

    private PopupWindow pop, pop_type, pop_order, pop_all;
    //poptype
    private TextView tvdistance1, tvdistance2, tvdistance3, tvrz1, tvrz2, tvrz3, tvcommit_type, tvreset_type;
    //order
    private ImageView ivorder1, ivorder2, ivorder3, ivorder4;
    private LinearLayout llorder1, llorder2, llorder3, llorder4;
    private TextView tvcommit_order;
    //all
    private MyGridView gvAll1, gvAll2, gvAll3;
    private PopGvAdapter popGvAdapter1, popGvAdapter2, popGvAdapter3;
    private ImageButton tvcommit_all;

    private List<TitleEntity> mTitleList;
    private List<TitleItem> itemList;

    private PullToRefreshListView mPullListView;
    private ListView mListView;
    private List<ProListEntity.DataBean> list;
    private int page = 1;
    private O2oDetailAdapter2 o2oDetailAdapter;
    private View headView;

    private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");
    boolean hasMoreData = true;

    //传过来的技能类型
    private String title = "";
    private String type = "";

    private String km = "";
    private String sf_ren = "";
    private String jn_ren = "";
    private String sort = "";
    User user;
    private String isZhorEn = "";
    private ImageView o2o_title_pic_iv;
    List<Integer> picList = new ArrayList<>();
    SigGogleEntity mSigGogleEntity;

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_o2o_detail, null);
    }

    private void initLocationData() {
        LocationUtils locationUtils = new LocationUtils(O2ODetailActivity2.this);
        String str = locationUtils.getGeoStr();
        mSigGogleEntity = LocationUtils.parStr(str);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initLocationData();
        setTitlebarHide(true);
        rltitle0 = (RelativeLayout) findViewById(R.id.o2o_detail_rl_title0);
        findViewById(R.id.o2o_detail_expand_iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        headView = getLayoutInflater().inflate(R.layout.o2o_head_view2, null);
        //顶部布局
        rltitle = (RelativeLayout) findViewById(R.id.o2o_detail_rl_collpase_title);
        rltitle.setVisibility(View.GONE);

        tvexpandtitle = (TextView) headView.findViewById(R.id.o2o_detail_expand_tv_title);
        tvcollpasetitle = (TextView) findViewById(R.id.o2o_detail_collapse_tv_title);
        tvlinetitle = (TextView) headView.findViewById(R.id.o2o_line_tv_title);
        tvcollpasetitle.setOnClickListener(this);

        pic_container = (RelativeLayout) headView.findViewById(R.id.o2o_title_pic_ll_container);
        initView(pic_container);
//        pic_container.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, PlatUtils.getImageRario(this, 5, 8)));
        //底部布局文字
        tvbottom = (TextView) findViewById(R.id.o2o_detail_tv_bottom);
        //中央带线文字
        llLine = (LinearLayout) headView.findViewById(R.id.o2o_line_ll_title);

        mPullListView = (PullToRefreshListView) findViewById(R.id.o2o_detail_listview);
        mPullListView.setPullLoadEnabled(true);
        mPullListView.setScrollLoadEnabled(false);
        mListView = mPullListView.getRefreshableView();
        mListView.addHeaderView(headView);
        mListView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        list = new ArrayList<>();
        o2oDetailAdapter = new O2oDetailAdapter2(this, list);
        mListView.setAdapter(o2oDetailAdapter);
        mListView.setDivider(null);
        setLastUpdateTime();
        mPullListView.doPullRefreshing(true, 500);

        mPullListView.setOnRefreshListener(new com.haiwai.housekeeper.libs.ui.PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(com.haiwai.housekeeper.libs.ui.PullToRefreshBase<ListView> refreshView) {
                page = 1;
                requestProList(1);
            }

            @Override
            public void onPullUpToRefresh(com.haiwai.housekeeper.libs.ui.PullToRefreshBase<ListView> refreshView) {
                page++;
                requestProList(2);
            }
        });

        llHeight = SizeUtil.getViewHeight(pic_container);
        mListView.setOnScrollListener(new OnScrollYListener(mListView) {
            @Override
            protected void onScrollY(int scrolledY) {
                if (scrolledY <= 0) {
                    rltitle0.setVisibility(View.VISIBLE);
                    rltitle.setVisibility(View.GONE);
//                            llLine.setVisibility(View.VISIBLE);
                } else if (scrolledY >= 0 && scrolledY < llHeight - EvmUtil.dip2px(O2ODetailActivity2.this, 25)) {
                    rltitle0.setVisibility(View.VISIBLE);
                    rltitle.setVisibility(View.GONE);
//                            llLine.setVisibility(View.VISIBLE);
                    tvbottom.setText(getString(R.string.look_for_your_sercice));
                } else if (scrolledY >= llHeight - EvmUtil.dip2px(O2ODetailActivity2.this, 25)) {
                    rltitle0.setVisibility(View.GONE);
                    rltitle.setVisibility(View.VISIBLE);
                    tvbottom.setText(getString(R.string.begin_look_for_your_sercice));
                }
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(O2ODetailActivity2.this,ProDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("uid", list.get(position - 1).getUid());
                bundle.putString("nickname", list.get(position - 1).getNickname());
                bundle.putString("type", type);
                bundle.putString("choose", "0");
                bundle.putString("oid", "");
                intent.putExtras(bundle);
                intent.putExtra("fromO2O",true);
                startActivity(intent);
            }
        });
        findViewById(R.id.o2o_detail_collapse_iv_back).setOnClickListener(this);

        headView.findViewById(R.id.o2o_expand_iv_type).setOnClickListener(this);
        headView.findViewById(R.id.o2o_expand_iv_order).setOnClickListener(this);
        headView.findViewById(R.id.o2o_expand_iv_all).setOnClickListener(this);
        findViewById(R.id.o2o_collapse_type).setOnClickListener(this);
        findViewById(R.id.o2o_collapse_order).setOnClickListener(this);
        findViewById(R.id.o2o_collapse_all).setOnClickListener(this);

        findViewById(R.id.user_order_detail_ll_bottom).setOnClickListener(this);
    }

    private void initView(RelativeLayout pic_container) {
        o2o_title_pic_iv = (ImageView) pic_container.findViewById(R.id.o2o_title_pic_iv);
    }

    public void requestProList(final int isSwipe) {
        hasMoreData = true;
        Map<String, String> map = new HashMap<>();
        map.put("uid", user.getUid() == null ? "" : user.getUid());
        map.put("type", type);
        map.put("lat", mSigGogleEntity.getLat());
        map.put("long", mSigGogleEntity.getLng());
        map.put("km", km);
        map.put("sf_ren", sf_ren);
        map.put("jn_ren", jn_ren);
        map.put("sort", sort);
        map.put("page", page + "");
        map.put("page_size", "10");
        map.put("secret_key", SPUtils.getString(O2ODetailActivity2.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(O2ODetailActivity2.this, Contants.pro_lst, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        initData(true);
                        if (page == 1) {
                            list.clear();
                        }
                        ProListEntity entity = new Gson().fromJson(response, ProListEntity.class);
                        if (entity.getData() == null || entity.getData().size() == 0) {
                            hasMoreData = false;
                        }
                        list.addAll(entity.getData());
                        o2oDetailAdapter.notifyDataSetChanged();
                    } else {
                        ToastUtil.shortToast(O2ODetailActivity2.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (isSwipe == 1) {
                        mPullListView.onPullDownRefreshComplete();
                    } else if (isSwipe == 2) {
                        mPullListView.onPullUpRefreshComplete();
                    }
                    setLastUpdateTime();
                }
            }
        }));
    }

    private void setLastUpdateTime() {
        String text = formatDateTime(System.currentTimeMillis());
        mPullListView.setLastUpdatedLabel(text);
    }

    private String formatDateTime(long time) {
        if (0 == time) {
            return "";
        }

        return mDateFormat.format(new Date(time));
    }

    @Override
    protected void initData() {

        initData(false);

    }

    private void initData(boolean b) {
        picList.add(R.mipmap.pic_skill_l_fwbj);
        picList.add(R.mipmap.pic_skill_l_cpxj);
        picList.add(R.mipmap.pic_skill_l_dtqx);
        picList.add(R.mipmap.pic_skill_l_gyqx);
        picList.add(R.mipmap.pic_skill_l_ljcl);
        picList.add(R.mipmap.pic_skill_l_bybj);
        picList.add(R.mipmap.pic_skill_l_abxt);
        picList.add(R.mipmap.pic_skill_l_ch);

        picList.add(R.mipmap.pic_skill_l_wxjg);
        picList.add(R.mipmap.pic_skill_l_wdwx);
        picList.add(R.mipmap.pic_skill_l_yyxj);
        picList.add(R.mipmap.pic_skill_l_jd);
        picList.add(R.mipmap.pic_skill_l_gjfx);
        picList.add(R.mipmap.pic_skill_l_snfs);
        picList.add(R.mipmap.pic_skill_l_swfs);
        picList.add(R.mipmap.pic_skill_l_hs);
        picList.add(R.mipmap.pic_skill_l_dg);
        picList.add(R.mipmap.pic_skill_l_lry);

        picList.add(R.mipmap.pic_skill_l_jjyc);
        picList.add(R.mipmap.pic_skill_l_ptdb);
        picList.add(R.mipmap.bxfwpic);
        picList.add(R.mipmap.pic_skill_l_ymzx);
        picList.add(R.mipmap.swfwpic);
        picList.add(R.mipmap.pic_skill_l_jsjl);
        picList.add(R.mipmap.pic_skill_l_fygz);
        picList.add(R.mipmap.flzxpic);
        picList.add(R.mipmap.pic_skill_l_fcmm);
        picList.add(R.mipmap.lydlpic);
        if (b) {
            user = AppGlobal.getInstance().getUser();
            isZhorEn = AppGlobal.getInstance().getLagStr();
            String url = "drawable://"+picList.get(Integer.valueOf(type) - 1);
            ImageLoader.getInstance().displayImage(url, o2o_title_pic_iv);
            IMKitService.lagMap.put("id", type);
            title = AssetsUtils.getSkillName(type, isZhorEn);
            if (!TextUtils.isEmpty(title)) {
                tvcollpasetitle.setText(title);
                tvexpandtitle.setText(title);
                tvlinetitle.setText(title +"  "+ getString(R.string.o2o_expert));
            }
        } else {
            user = AppGlobal.getInstance().getUser();
            isZhorEn = AppGlobal.getInstance().getLagStr();
            type = getIntent().getExtras().get("id").toString();
            String imgUrl = "drawable://"+picList.get(Integer.valueOf(type) - 1);
            ImageLoader.getInstance().displayImage(imgUrl, o2o_title_pic_iv);
            IMKitService.lagMap.put("id", type);

            title = AssetsUtils.getSkillName(type, isZhorEn);
            if (!TextUtils.isEmpty(title)) {
                tvcollpasetitle.setText(title);
                tvexpandtitle.setText(title);
                tvlinetitle.setText(title +"  "+   getString(R.string.o2o_expert));
            }
        }
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.o2o_detail_collapse_iv_back:
            case R.id.o2o_detail_collapse_tv_title:
                finish();
                break;
            case R.id.o2o_expand_iv_type:
            case R.id.o2o_collapse_type:
                showPopWindowType();
                break;
            case R.id.pop_type_distance_1:
                initTypeDistance(1);
                km = "";
                break;
            case R.id.pop_type_distance_2:
                initTypeDistance(2);
                km = "5";
                break;
            case R.id.pop_type_distance_3:
                initTypeDistance(3);
                km = "10";
                break;
            case R.id.pop_type_renzheng_1:
                initTypeRenzheng(1);
                sf_ren = "";
                jn_ren = "";
                break;
            case R.id.pop_type_renzheng_2:
                initTypeRenzheng(2);
                sf_ren = "1";
                jn_ren = "";
                break;
            case R.id.pop_type_renzheng_3:
                initTypeRenzheng(3);
                sf_ren = "";
                jn_ren = "1";
                break;
            case R.id.pop_type_commit:
                if (pop_type != null && pop_type.isShowing()) {
                    pop_type.dismiss();
                    mListView.setSelection(0);
                    mPullListView.doPullRefreshing(true, 500);
                }
                break;
            case R.id.pop_type_reset:
                initTypeDistance(1);
                initTypeRenzheng(1);
                break;
            case R.id.o2o_expand_iv_order:
            case R.id.o2o_collapse_order:
                showPopWindowOrder();
                break;
            case R.id.pop_order_ll_1:
                initOrder(1);
                break;
            case R.id.pop_order_ll_2:
                initOrder(2);
                break;
            case R.id.pop_order_ll_3:
                initOrder(3);
                break;
            case R.id.pop_order_ll_4:
                initOrder(4);
                break;
            case R.id.pop_order_commit:
                if (pop_order != null && pop_order.isShowing()) {
                    pop_order.dismiss();
                    mListView.setSelection(0);
                    mPullListView.doPullRefreshing(true, 500);
                }
                break;
            case R.id.o2o_expand_iv_all:
            case R.id.o2o_collapse_all:
                showPopWindowAll();
                break;
            case R.id.pop_all_commit:
                if (pop_all != null && pop_all.isShowing()) {
                    type = "";
                    pop_all.dismiss();
                    changeView();
                    mListView.setSelection(0);
                    mPullListView.doPullRefreshing(true, 500);
                }
                break;
            case R.id.user_order_detail_ll_bottom:
                if (mApp.isLogin()) {
                    Intent intent = new Intent(O2ODetailActivity2.this,CommonProActivity2.class);
                    intent.putExtra("id",type);
                    startActivity(intent);
                } else {
                    ActivityTools.goNextActivity(this, LoginActivity.class);
                }
                break;

        }
    }

    private void changeView() {
        title = AssetsUtils.getSkillName(type, isZhorEn);
        if (!TextUtils.isEmpty(title)) {
            tvcollpasetitle.setText(title);
            tvexpandtitle.setText(title);
            tvlinetitle.setText(title +"  "+ "专家");
        }
        // TODO: 2016/11/17 pic change
    }

    /**
     * 技能认证弹窗
     */
    public void showPopWindow(View view) {
        View v = LayoutInflater.from(this)
                .inflate(R.layout.pop_skill, null);
        pop = new PopupWindow(v, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.showAsDropDown(view);
    }

    /**
     * type
     */
    private void showPopWindowType() {
        View v = LayoutInflater.from(this)
                .inflate(R.layout.o2o_pop_type, null);
        tvdistance1 = (TextView) v.findViewById(R.id.pop_type_distance_1);
        tvdistance2 = (TextView) v.findViewById(R.id.pop_type_distance_2);
        tvdistance3 = (TextView) v.findViewById(R.id.pop_type_distance_3);
        tvrz1 = (TextView) v.findViewById(R.id.pop_type_renzheng_1);
        tvrz2 = (TextView) v.findViewById(R.id.pop_type_renzheng_2);
        tvrz3 = (TextView) v.findViewById(R.id.pop_type_renzheng_3);
        tvcommit_type = (TextView) v.findViewById(R.id.pop_type_commit);
        tvreset_type = (TextView) v.findViewById(R.id.pop_type_reset);
        initTypeDistance(1);
        initTypeRenzheng(1);
        tvdistance1.setOnClickListener(this);
        tvdistance2.setOnClickListener(this);
        tvdistance3.setOnClickListener(this);
        tvrz1.setOnClickListener(this);
        tvrz2.setOnClickListener(this);
        tvrz3.setOnClickListener(this);
        tvcommit_type.setOnClickListener(this);
        tvreset_type.setOnClickListener(this);
        pop_type = new PopupWindow(v, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        pop_type.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(0.4f);
        pop_type.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        pop_type.setOnDismissListener(new poponDismissListener());

        //初始化参数
        tvdistance1.performClick();
        tvrz1.performClick();
    }

    private void initTypeDistance(int i) {
        tvdistance1.setSelected(i == 1 ? true : false);
        tvdistance2.setSelected(i == 2 ? true : false);
        tvdistance3.setSelected(i == 3 ? true : false);
    }

    private void initTypeRenzheng(int i) {
        tvrz1.setSelected(i == 1 ? true : false);
        tvrz2.setSelected(i == 2 ? true : false);
        tvrz3.setSelected(i == 3 ? true : false);
    }

    /**
     * order
     */
    private void showPopWindowOrder() {
        View v = LayoutInflater.from(this)
                .inflate(R.layout.o2o_pop_order, null);
        ivorder1 = (ImageView) v.findViewById(R.id.pop_order_iv_1);
        ivorder2 = (ImageView) v.findViewById(R.id.pop_order_iv_2);
        ivorder3 = (ImageView) v.findViewById(R.id.pop_order_iv_3);
        ivorder4 = (ImageView) v.findViewById(R.id.pop_order_iv_4);
        llorder1 = (LinearLayout) v.findViewById(R.id.pop_order_ll_1);
        llorder2 = (LinearLayout) v.findViewById(R.id.pop_order_ll_2);
        llorder3 = (LinearLayout) v.findViewById(R.id.pop_order_ll_3);
        llorder4 = (LinearLayout) v.findViewById(R.id.pop_order_ll_4);
        tvcommit_order = (TextView) v.findViewById(R.id.pop_order_commit);
        initOrder(1);
        llorder1.setOnClickListener(this);
        llorder2.setOnClickListener(this);
        llorder3.setOnClickListener(this);
        llorder4.setOnClickListener(this);
        tvcommit_order.setOnClickListener(this);
        pop_order = new PopupWindow(v, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        pop_order.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(0.4f);
        pop_order.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        pop_order.setOnDismissListener(new poponDismissListener());
    }

    private void initOrder(int i) {
        ivorder1.setSelected(i == 1 ? true : false);
        ivorder2.setSelected(i == 2 ? true : false);
        ivorder3.setSelected(i == 3 ? true : false);
        ivorder4.setSelected(i == 4 ? true : false);
        sort = String.valueOf(i);
    }

    /**
     * all
     */
    private void showPopWindowAll() {
        if ("zh".equalsIgnoreCase(isZhorEn))
            initZhType();
        else
            initEnType();
        View v = LayoutInflater.from(this)
                .inflate(R.layout.o2o_pop_all, null);
        gvAll1 = (MyGridView) v.findViewById(R.id.pop_all_gv_1);
        gvAll2 = (MyGridView) v.findViewById(R.id.pop_all_gv_2);
        gvAll3 = (MyGridView) v.findViewById(R.id.pop_all_gv_3);
        ((TextView) v.findViewById(R.id.pop_all_tv_1)).setText(mTitleList.get(0).getName());
        ((TextView) v.findViewById(R.id.pop_all_tv_2)).setText(mTitleList.get(1).getName());
        ((TextView) v.findViewById(R.id.pop_all_tv_3)).setText(mTitleList.get(2).getName());
        tvcommit_all = (ImageButton) v.findViewById(R.id.pop_all_commit);
        if ("en".equals(isZhorEn)) {
            tvcommit_all.setImageResource(R.mipmap.all_btn_icon_en);
        } else {
            tvcommit_all.setImageResource(R.mipmap.all_btn_icon);
        }
        popGvAdapter1 = new PopGvAdapter(this, mTitleList.get(0).getItems());
        popGvAdapter2 = new PopGvAdapter(this, mTitleList.get(1).getItems());
        popGvAdapter3 = new PopGvAdapter(this, mTitleList.get(2).getItems());
        gvAll1.setAdapter(popGvAdapter1);
        gvAll2.setAdapter(popGvAdapter2);
        gvAll3.setAdapter(popGvAdapter3);
        popGvAdapter1.setOnItemsClickListener(this);
        popGvAdapter2.setOnItemsClickListener(this);
        popGvAdapter3.setOnItemsClickListener(this);
        tvcommit_all.setOnClickListener(this);
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        pop_all = new PopupWindow(v, WindowManager.LayoutParams.MATCH_PARENT,
                dm.heightPixels * 4 / 5, true);
        pop_all.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(0.4f);
        pop_all.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        pop_all.setOnDismissListener(new poponDismissListener());
    }

    private void closePopupWindow() {
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
            pop = null;
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        list.clear();
        requestProList(1);
    }

    @Override
    public void onLoadMore() {
        page++;
        requestProList(2);
    }

    @Override
    public void itemClick(String type) {
        if (pop_all != null && pop_all.isShowing()) {
            this.type = type;
            pop_all.dismiss();
            changeView();
            mListView.setSelection(0);
            mPullListView.doPullRefreshing(true, 500);
        }
    }

    private enum State {
        EXPANDED,
        COLLAPSING,
        COLLAPSED,
        IDLE
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        getWindow().setAttributes(lp);
    }

    class poponDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }
    }

    public void initZhType() {
        String json = AssetsUtils.getJson(O2ODetailActivity2.this);
        try {
            JSONArray jsonArray = new JSONArray(json);
            mTitleList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                TitleEntity title = new TitleEntity();
                title.setName((String) jsonObject.opt("hncn"));
                JSONArray jArray = jsonObject.getJSONArray("hccn");
                itemList = new ArrayList<>();
                for (int j = 0; j < jArray.length(); j++) {
                    JSONObject joOb = jArray.getJSONObject(j);
                    TitleItem titleItem = new TitleItem();
                    titleItem.setName((String) joOb.opt("ty"));
                    titleItem.setType((String) joOb.opt("type"));
                    itemList.add(titleItem);
                }
                title.setItems(itemList);
                mTitleList.add(title);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void initEnType() {
        String json = AssetsUtils.getJson(O2ODetailActivity2.this);
        try {
            JSONArray jsonArray = new JSONArray(json);
            mTitleList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                TitleEntity title = new TitleEntity();
                title.setName((String) jsonObject.opt("hnen"));
                JSONArray jArray = jsonObject.getJSONArray("hcen");
                itemList = new ArrayList<>();
                for (int j = 0; j < jArray.length(); j++) {
                    JSONObject joOb = jArray.getJSONObject(j);
                    TitleItem titleItem = new TitleItem();
                    titleItem.setName((String) joOb.opt("ty"));
                    titleItem.setType((String) joOb.opt("type"));
                    itemList.add(titleItem);
                }
                title.setItems(itemList);
                mTitleList.add(title);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
