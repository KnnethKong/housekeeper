package com.haiwai.housekeeper.fragment.user.need;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.NeedOrderDetailActivity3;
import com.haiwai.housekeeper.adapter.NeedToResponseAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseFragment;
import com.haiwai.housekeeper.entity.NeedResponseEntity;
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
 * Created by ihope007 on 2016/9/2.
 */
public class HistoryFragment extends BaseFragment {
    private PullToRefreshListView mPullListView;
    private ListView mListView;
    private int page = 1;
    private List<NeedResponseEntity.DataBean> list;
    private NeedToResponseAdapter adapter;
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");

    public static HistoryFragment getNewInstance() {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_fragment_need_history, null);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mPullListView = (PullToRefreshListView) view.findViewById(R.id.need_history_lv);
        mPullListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullListView.setLoadingDrawable(getResources().getDrawable(R.drawable.loading_01));
        mListView = mPullListView.getRefreshableView();
        list = new ArrayList<>();
        adapter = new NeedToResponseAdapter(getActivity(), list,3);
        mListView.setAdapter(adapter);
        if (isNetworkAvailable()) {
            requestHistoryList(1);
            mPullListView.setRefreshing();
        }
        mPullListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                requestHistoryList(1);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                requestHistoryList(2);
            }
        });
        TextView tvempty = (TextView) view.findViewById(R.id.need_history_tv_empty);
        tvempty.setOnClickListener(this);
        mListView.setEmptyView(tvempty);
        mListView.setDivider(null);
        mListView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        setLastUpdateTime();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),NeedOrderDetailActivity3.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", list.get(position-1).getId());
                intent.putExtras(bundle);
                startActivityForResult(intent, Activity.RESULT_FIRST_USER);
            }
        });
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Activity.RESULT_FIRST_USER){
            if (resultCode==-1){
                mListView.setSelection(0);
                mPullListView.setRefreshing(true);
            }
        }
    }


    @Override
    protected void initData() {
    }

    public void requestHistoryList(final int isSwipe) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("static", "3");
        map.put("page", page + "");
        map.put("page_size", "10");
        map.put("secret_key", SPUtils.getString(getActivity(), "secret", ""));
        mRequestQueue.add(new PlatRequest(getActivity(), Contants.user_order_lst, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        if (page == 1) {
                            list.clear();
                        }
                        NeedResponseEntity entity = new Gson().fromJson(response, NeedResponseEntity.class);
                        list.addAll(entity.getData());
                        adapter.notifyDataSetChanged();
                    } else {
                        ToastUtil.shortToast(getActivity(), ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (isSwipe == 1) {
                        mPullListView.onRefreshComplete();
                    } else if (isSwipe == 2) {
                        mPullListView.onRefreshComplete();
                    }
                    setLastUpdateTime();
                }
            }
        }));
    }

    @Override
    protected void click(View v) {
        switch (v.getId()){
            case R.id.need_history_tv_empty:
                requestHistoryList(1);
                break;
        }
    }
}
