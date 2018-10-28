package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.VipHouseChooseAdapter;
import com.haiwai.housekeeper.adapter.VipHouseManageAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.entity.CityEntity;
import com.haiwai.housekeeper.entity.HouseListEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.libs.ui.PullToRefreshListView;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/19.
 * VIP下单之前--房屋选择
 */
public class VipHouseChooseActivity extends BaseActivity {
    private PullToRefreshListView mPullListView;
    private ListView mListView;
    private List<HouseListEntity.DataBean> list;
    private int page = 1;
    private VipHouseChooseAdapter adapter;

    private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");
    boolean hasMoreData = true;
    private String pos = "";
    private String flag = "";
    private String isZhorEn = "";


    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_vip_house_choose, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.choose_house), Color.parseColor("#FF3C3C3C"));
        flag = getIntent().getExtras().getString("flag");
        if ("order".equals(flag))
            resetSelfSp();
        if ("design".equals(flag))
            findViewById(R.id.vip_house_choose_ll_user_new).setVisibility(View.GONE);
        if (isNetworkAvailable())
            requestCity();
        findViewById(R.id.vip_house_choose_ll_user_new).setOnClickListener(this);
    }

    private void resetSelfSp() {
        SPUtils.saveVIPString(this, "self", "h_id", "");
        SPUtils.saveVIPString(this, "self", "city", "");
        SPUtils.saveVIPString(this, "self", "k_static", "0");
        SPUtils.saveVIPString(this, "self", "y_area", "");
        SPUtils.saveVIPString(this, "self", "y_ci", "");
        SPUtils.saveVIPString(this, "self", "y_static", "0");
        SPUtils.saveVIPString(this, "self", "j_area", "");
        SPUtils.saveVIPString(this, "self", "j_ci", "");
        SPUtils.saveVIPString(this, "self", "j_static", "0");
        SPUtils.saveVIPString(this, "self", "is_zhao", "0");
        SPUtils.saveVIPString(this, "self", "is_guan", "0");
        SPUtils.saveVIPString(this, "self", "z_city", "");
        SPUtils.saveVIPString(this, "self", "z_type", "");

        SPUtils.saveVIPString(this, "self", "city_name", "");
        SPUtils.saveVIPString(this, "self", "land_area", "");
        SPUtils.saveVIPString(this, "self", "built_area", "");
        SPUtils.saveVIPString(this, "self", "house_type", "");
    }

    private CityEntity cityEntity;

    private void requestCity() {
        Map<String,String> map =new HashMap<>();
        map.put("secret_key", SPUtils.getString(VipHouseChooseActivity.this, "secret", ""));
        mRequestQueue.add(new PlatRequest(this, Contants.city_lst, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    cityEntity = new Gson().fromJson(response, CityEntity.class);
                    init();
                } else {
                    ToastUtil.shortToast(VipHouseChooseActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void init() {
        mPullListView = (PullToRefreshListView) findViewById(R.id.vip_house_choose_listview);
        mPullListView.setPullLoadEnabled(false);
        mPullListView.setScrollLoadEnabled(false);
        mListView = mPullListView.getRefreshableView();
        list = new ArrayList<>();
        adapter = new VipHouseChooseAdapter(VipHouseChooseActivity.this, list);
        mListView.setAdapter(adapter);
        mListView.setDivider(null);
        mListView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        setLastUpdateTime();
        mPullListView.doPullRefreshing(true, 500);
        mPullListView.setOnRefreshListener(new com.haiwai.housekeeper.libs.ui.PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(com.haiwai.housekeeper.libs.ui.PullToRefreshBase<ListView> refreshView) {
                page = 1;
                requestHouseList(1);
            }

            @Override
            public void onPullUpToRefresh(com.haiwai.housekeeper.libs.ui.PullToRefreshBase<ListView> refreshView) {
//                page++;
//                requestHouseList(2);
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if ("order".equals(flag)) {//从homefragment进入将要跳转到自营下单页面
                    pos = getIntent().getExtras().getString("pos");
                    Bundle bundle = new Bundle();
                    bundle.putString("pos", pos);
                    bundle.putString("from", "choose");
                    ActivityTools.goNextActivity(VipHouseChooseActivity.this, SelfSupportDetailActivity.class, bundle);
                    finish();

                    SPUtils.saveVIPString(VipHouseChooseActivity.this, "self", "h_id", list.get(position).getId());
                    SPUtils.saveVIPString(VipHouseChooseActivity.this, "self", "city", list.get(position).getCity());
                    // TODO: 2016/11/24 根据语言环境判断存储中文或英文
                    SPUtils.saveVIPString(VipHouseChooseActivity.this, "self", "city_name", JsonUtils.getCityZhName(cityEntity, list.get(position).getCity()));
//                SPUtils.saveVIPString(VipHouseChooseActivity.this,"self","city_name",JsonUtils.getCityEnName(cityEntity,list.get(position).getCity()));
                    //建筑类型
                    SPUtils.saveVIPString(VipHouseChooseActivity.this, "self", "house_type", list.get(position).getHousing_type());
//                    SPUtils.saveVIPString(VipHouseChooseActivity.this, "self", "land_area", list.get(position).getLand_area());
//                    SPUtils.saveVIPString(VipHouseChooseActivity.this, "self", "built_area", list.get(position).getBuilt_area());
                } else if ("design".equals(flag)) {//从房屋管理进入  选择房屋地址
                    Intent intent = new Intent();
                    intent.putExtra("addr", list.get(position).getAddress_info());
                    intent.putExtra("h_id", list.get(position).getId());
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });
    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
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
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.vip_house_choose_ll_user_new://使用新房产  和添加新房产差不多
                Intent intent = new Intent(this, NewHousActivity.class);
                intent.putExtra("isNew", true);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {//编辑
            if (resultCode == RESULT_OK) {
                mListView.setSelection(0);
                mPullListView.doPullRefreshing(true, 500);
            }
        } else if (requestCode == 200) {//添加房产
            if (resultCode == RESULT_OK) {
                //刷新
                mListView.setSelection(0);
                mPullListView.doPullRefreshing(true, 500);
            }
        }
    }


    public void requestHouseList(final int isSwipe) {
        hasMoreData = true;
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("secret_key", SPUtils.getString(VipHouseChooseActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(this, Contants.hous_lst, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        list.clear();
                        HouseListEntity entity = new Gson().fromJson(response, HouseListEntity.class);
                        if (entity.getData() == null || entity.getData().size() == 0) {
                            hasMoreData = false;
                        }
                        List<HouseListEntity.DataBean> mList = new ArrayList<>();
                        if (entity.getData() != null && entity.getData().size() > 0) {
                            for (int i = 0; i < entity.getData().size(); i++) {
//                                if (1 == entity.getData().get(i).getIs_support()) {
                                    mList.add(entity.getData().get(i));
//                                }
                            }
                        }
                        list.addAll(mList);
                        adapter.setData(mList);
                    } else {
                        ToastUtil.shortToast(VipHouseChooseActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (isSwipe == 1) {
                        mPullListView.onPullDownRefreshComplete();
                    } else if (isSwipe == 2) {
                        mPullListView.onPullUpRefreshComplete();
                    }
//                    mPullListView.setHasMoreData(hasMoreData);
                    setLastUpdateTime();
                }
            }
        }));
    }

}
