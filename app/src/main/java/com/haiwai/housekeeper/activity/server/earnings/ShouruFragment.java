package com.haiwai.housekeeper.activity.server.earnings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.OrderPayActivity;
import com.haiwai.housekeeper.adapter.ShouruAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseFragment;
import com.haiwai.housekeeper.entity.HouseListEntity;
import com.haiwai.housekeeper.entity.ShouruEntity;
import com.haiwai.housekeeper.entity.WxEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.https.okhttp.OKRequestCallback;
import com.haiwai.housekeeper.https.okhttp.OkHttpUtils;
import com.haiwai.housekeeper.libs.ui.PullToRefreshListView;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.mylhyl.crlayout.internal.LoadGridView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by range on 2018/1/18.
 */

public class ShouruFragment extends BaseFragment {
    private PullToRefreshListView mPullListView;
    private ListView mListView;
    private List<ShouruEntity.DataBean> list;
    private int page = 1;
    private ShouruAdapter adapter;

    private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");
    boolean hasMoreData = true;
    private String isZhorEn = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ShouruFragment.class.getName(), "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.shouru_fragment, container, false);

        return rootView;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        mPullListView = (PullToRefreshListView) view.findViewById(R.id.vip_house_manage_listview);

        mPullListView.setPullLoadEnabled(false);
        mPullListView.setScrollLoadEnabled(false);
        mListView = mPullListView.getRefreshableView();
    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        list = new ArrayList<>();
        adapter = new ShouruAdapter(getActivity(), list, isZhorEn);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ShouruDetailsActivity.class);
                intent.putExtra("id", list.get(position).getSelf_batch_id());
                getActivity().startActivity(intent);
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
    public void onResume() {
        super.onResume();
        mListView.setSelection(0);
        mPullListView.doPullRefreshing(true, 500);
    }

    @Override
    protected void click(View v) {

    }

    public void requestHouseList(final int isSwipe, final int page) {
        hasMoreData = true;
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("page", page + "");
        map.put("page_size", "10");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(getActivity(), map, null);
        Request request = new Request.Builder()
                .url(Contants.My_income)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(getActivity(), request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                try {

                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        if (page == 1) {
                            list.clear();
                            ShouruEntity entity = new Gson().fromJson(response, ShouruEntity.class);
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
                            ShouruEntity entity = new Gson().fromJson(response, ShouruEntity.class);
                            if (entity.getData() == null || entity.getData().size() == 0) {
                                hasMoreData = false;
                            }
                            list.addAll(entity.getData());
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        ToastUtil.shortToast(getActivity(), ErrorCodeUtils.getRegisterError(code + ""));
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
