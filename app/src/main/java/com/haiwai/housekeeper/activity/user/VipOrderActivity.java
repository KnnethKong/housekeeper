package com.haiwai.housekeeper.activity.user;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.server.OrderDetailActivity;
import com.haiwai.housekeeper.adapter.CommentAdapter;
import com.haiwai.housekeeper.adapter.CommentMoreAdapter;
import com.haiwai.housekeeper.adapter.VipOrderAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.entity.CommentMoreEntity;
import com.haiwai.housekeeper.entity.VipOrderEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/5.
 * 我的——vip订单
 */
public class VipOrderActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private PullToRefreshListView mPullListView;
    private ListView mListView;
    private int page = 1;
    private VipOrderAdapter adapter;
    private List<VipOrderEntity.DataBean> list;
    private String isZhorEn = "";
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_activity_vip_order,null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.vip_order_title), Color.parseColor("#FF3C3C3C"));
        mPullListView= (PullToRefreshListView) findViewById(R.id.vip_order_listview);
        mPullListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        mPullListView.setLoadingDrawable(getResources().getDrawable(R.drawable.loading_01));
        mListView = mPullListView.getRefreshableView();
        mListView.setDivider(null);
        mListView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        setLastUpdateTime();
        mPullListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                requestMyVip(1);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                page++;
//                requestMyVip(2);
            }
        });

        mPullListView.setOnItemClickListener(this);
    }

    public void requestMyVip(final int isSwipe) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
//        map.put("page", page + "");
//        map.put("page_size", "10");
        map.put("secret_key", SPUtils.getString(VipOrderActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(VipOrderActivity.this, Contants.vip_lst, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    System.out.println(">>>>>>>>>>>>>.vip订单" + response);
                    if (code == 200) {
                        if (page == 1) {
                            list.clear();
                        }
                        VipOrderEntity entity = new Gson().fromJson(response, VipOrderEntity.class);
                        list.addAll(entity.getData());
                        adapter.notifyDataSetChanged();
                    } else {
                        ToastUtil.shortToast(VipOrderActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (isSwipe == 1) {
                        mPullListView.onRefreshComplete();
//                    }else if(isSwipe==2){
//                        mPullListView.onRefreshComplete();
                    }
                    setLastUpdateTime();
                }
            }
        }));
    }

    private void setLastUpdateTime() {
        String text = formatDateTime(System.currentTimeMillis());
        mPullListView.setLastUpdatedLabel(getString(R.string.last_update_time)+text);
    }

    private String formatDateTime(long time) {
        if (0 == time) {
            return "";
        }

        return mDateFormat.format(new Date(time));
    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        list = new ArrayList<>();
        adapter=new VipOrderAdapter(this,list);
        mListView.setAdapter(adapter);
        if (isNetworkAvailable())
            requestMyVip(1);
        mPullListView.setRefreshing();
    }

    @Override
    protected void click(View v) {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle=new Bundle();
        bundle.putString("h_id",list.get(position-1).getH_id());
        ActivityTools.goNextActivity(this, VipOrderDetailActivity.class,bundle);
    }
}
