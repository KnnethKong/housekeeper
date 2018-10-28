package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.VipHouseManageAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.entity.HouseListEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.libs.ui.PullToRefreshListView;
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
 * VIP——房屋管理
 */
public class VipHouseManageActivity extends BaseActivity {
    private PullToRefreshListView mPullListView;
    private ListView mListView;
    private List<HouseListEntity.DataBean> list;
    private int page = 1;
    private VipHouseManageAdapter adapter;
    private TextView tv_empty;
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");
    boolean hasMoreData = true;
    private String isZhorEn = "";

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_vip_house_manage, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.my_ser_addr), Color.parseColor("#FF3C3C3C"));
        mPullListView = (PullToRefreshListView) findViewById(R.id.vip_house_manage_listview);
        findViewById(R.id.vip_house_manage_ll_add_house).setOnClickListener(this);
        tv_empty = (TextView) findViewById(R.id.tv_empty);
        mPullListView.setPullLoadEnabled(false);
        mPullListView.setScrollLoadEnabled(false);
        mListView = mPullListView.getRefreshableView();

    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        list = new ArrayList<>();
        adapter = new VipHouseManageAdapter(this, list, isZhorEn);
        mListView.setAdapter(adapter);
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
        mListView.setDivider(null);
        mListView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        setLastUpdateTime();
        mPullListView.doPullRefreshing(true, 500);
    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_BACK){
//            Intent intent = new Intent(VipHouseManageActivity.this, MainActivity.class);
//            intent.putExtra("flag", "home");
//            startActivity(intent);
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

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
            case R.id.vip_house_manage_ll_add_house://添加房产
//                Bundle bundle = new Bundle();
//                bundle.putString("flag", "add");
//                ActivityTools.goNextActivityForResult(this, AddHouseActivity.class, bundle, 200);
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

    @Override
    protected void onResume() {
        mListView.setSelection(0);
        mPullListView.doPullRefreshing(true, 500);
        super.onResume();
    }

    public void requestDeleteHouse(String house_id, String city, String houseType, int isWeek) {
        if (isWeek == 1) {
            ToastUtil.longToast(VipHouseManageActivity.this, getString(R.string.can_not_detele));
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("id", house_id);
            map.put("uid", AppGlobal.getInstance().getUser().getUid());
            map.put("city", city);
            map.put("housing_type", houseType);
            map.put("secret_key", SPUtils.getString(VipHouseManageActivity.this, "secret", ""));
            map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
            mRequestQueue.add(new PlatRequest(this, Contants.hous_del, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    LogUtil.e("zc", response);
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        ToastUtil.shortToast(VipHouseManageActivity.this, getString(R.string.commit_success));
                        mPullListView.doPullRefreshing(true, 500);
                    } else {
                        ToastUtil.shortToast(VipHouseManageActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }));
        }

    }

    public void requestHouseList(final int isSwipe) {
        hasMoreData = true;
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("secret_key", SPUtils.getString(VipHouseManageActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(this, Contants.hous_lst, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        if (page == 1) {
                            list.clear();
                            HouseListEntity entity = new Gson().fromJson(response, HouseListEntity.class);
                            if (entity.getData() == null || entity.getData().size() == 0) {
                                hasMoreData = false;
                                mPullListView.setVisibility(View.GONE);
                                tv_empty.setVisibility(View.VISIBLE);
                            } else {
                                mPullListView.setVisibility(View.VISIBLE);
                                tv_empty.setVisibility(View.GONE);
                                list.addAll(entity.getData());
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            mPullListView.setVisibility(View.VISIBLE);
                            tv_empty.setVisibility(View.GONE);
                            HouseListEntity entity = new Gson().fromJson(response, HouseListEntity.class);
                            if (entity.getData() == null || entity.getData().size() == 0) {
                                hasMoreData = false;
                            }
                            list.addAll(entity.getData());
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        ToastUtil.shortToast(VipHouseManageActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
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

}
