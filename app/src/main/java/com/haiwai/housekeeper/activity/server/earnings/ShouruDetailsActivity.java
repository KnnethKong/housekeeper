package com.haiwai.housekeeper.activity.server.earnings;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.MyAdapter;
import com.haiwai.housekeeper.adapter.ShouruAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.CityLevelEntity;
import com.haiwai.housekeeper.entity.ShouruDetailsEntity;
import com.haiwai.housekeeper.entity.ShouruEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.https.okhttp.OKRequestCallback;
import com.haiwai.housekeeper.https.okhttp.OkHttpUtils;
import com.haiwai.housekeeper.libs.ui.PullToRefreshListView;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.DateUtil;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.TopViewNormalBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

public class ShouruDetailsActivity extends AppCompatActivity {
    TopViewNormalBar top_contact_bar;
    private PullToRefreshListView mPullListView;
    private ListView mListView;
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");
    private ArrayList<ShouruDetailsEntity.DataBean> list = new ArrayList();
    private MyAdapter adapter;
    private int page = 1;
    boolean hasMoreData = true;
    private RequestQueue mRequestQueue;
    private String id;
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouru_details);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        mRequestQueue = MyApp.getTingtingApp().getRequestQueue();
        id = getIntent().getStringExtra("id");
        initView();
        initData();
    }

    public String TimeStamp2Date(String timestampString, String formats) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new java.text.SimpleDateFormat(formats).format(new java.util.Date(timestamp));
        return date;
    }

    private void initData() {
        list = new ArrayList<>();
        adapter = new MyAdapter<ShouruDetailsEntity.DataBean>(list, R.layout.item_shouru_details) {
            @Override
            public void bindView(ViewHolder holder, ShouruDetailsEntity.DataBean obj) {
//                if ("en".equals(isZhorEn)) {
//                    holder.setText(R.id.tv_content, obj.getYname());
//                    country_name = mCityLevelEntityList.get(holder.getItemPosition()).getYname();
//                } else {
//                    holder.setText(R.id.tv_content, obj.getName());
//                    country_name = mCityLevelEntityList.get(holder.getItemPosition()).getName();
//                }
//                country = mCityLevelEntityList.get(holder.getItemPosition()).getId();
                //  for (int i = 0; i < list.size(); i++) {
                holder.setText(R.id.tv_user_order_date, obj.getOrder_id());
                holder.setText(R.id.tv_shouru_dateils, obj.getPro_money());

                holder.setText(R.id.tv_shouru_state, TimeStamp2Date(obj.getCtime(), "yyyy-MM-dd HH:mm:ss"));
                String str = AssetsUtils.getSkillName(obj.getType(), isZhorEn);
                holder.setText(R.id.tv_shouru_money, str);
            }
        };
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        mPullListView.setOnRefreshListener(new com.haiwai.housekeeper.libs.ui.PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(com.haiwai.housekeeper.libs.ui.PullToRefreshBase<ListView> refreshView) {
                page = 1;
                requestHouseList(1, page);
            }

            @Override
            public void onPullUpToRefresh(com.haiwai.housekeeper.libs.ui.PullToRefreshBase<ListView> refreshView) {
                page++;
                requestHouseList(2, page);
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

    private void initView() {
        top_contact_bar = (TopViewNormalBar) findViewById(R.id.top_prob_bar);
        if (isZhorEn.equals("en")) {
            top_contact_bar.setTitle("Details");
        } else {
            top_contact_bar.setTitle("详情");
        }

        top_contact_bar.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == top_contact_bar.getBackView()) {
                    finish();
                }
            }
        });
        mPullListView = (PullToRefreshListView) findViewById(R.id.listview);
        mListView = mPullListView.getRefreshableView();
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

    public void requestHouseList(final int isSwipe, final int page) {
        hasMoreData = true;
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("page", page + "");
        map.put("page_size", "10");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(this, map, null);
        Request request = new Request.Builder()
                .url(Contants.My_income_details)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        if (page == 1) {
                            list.clear();
                            ShouruDetailsEntity entity = new Gson().fromJson(response, ShouruDetailsEntity.class);
                            if (entity.getData() == null || entity.getData().size() == 0) {
                                hasMoreData = false;
                                mPullListView.setVisibility(View.GONE);

                            } else {
                                mPullListView.setVisibility(View.VISIBLE);
                                list.addAll(entity.getData());
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            mPullListView.setVisibility(View.VISIBLE);
                            ShouruDetailsEntity entity = new Gson().fromJson(response, ShouruDetailsEntity.class);
                            if (entity.getData() == null || entity.getData().size() == 0) {
                                hasMoreData = false;
                            }
                            list.addAll(entity.getData());
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        ToastUtil.shortToast(ShouruDetailsActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
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
        });
    }
}
