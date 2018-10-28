package com.haiwai.housekeeper.fragment.user.wallet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.WalletPayAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseFragment;
import com.haiwai.housekeeper.entity.NeedResponseEntity;
import com.haiwai.housekeeper.entity.WalletPayEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.DividerItemDecoration;
import com.mylhyl.crlayout.SwipeRefreshRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/6.
 * 我的钱包——支付记录
 */
public class PayFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshRecyclerView recyclerView;
    private List<WalletPayEntity.DataBean.DateBean> list;
    private WalletPayAdapter adapter;
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_fragment_wallet_pay, null);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        recyclerView = (SwipeRefreshRecyclerView) view.findViewById(R.id.wallet_pay_lv);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setOnRefreshListener(this);
        recyclerView.getScrollView().addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST) {
        });
        recyclerView.getSwipeRefreshLayout().setColorSchemeColors(R.color.theme);
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        adapter = new WalletPayAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setEmptyText(getString(R.string.no_data));
        recyclerView.autoRefresh();
    }

    private void requestPayData() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("secret_key", SPUtils.getString(getActivity(), "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(getActivity(), Contants.zhi_record, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        if (page == 1) {
                            list.clear();
                        }
                        WalletPayEntity entity = new Gson().fromJson(response, WalletPayEntity.class);
                        list.addAll(entity.getData().getDate());
                        adapter.notifyDataSetChanged();
                    } else {
                        ToastUtil.shortToast(getActivity(), ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    recyclerView.setRefreshing(false);
                }
            }
        }));
    }

    @Override
    protected void click(View v) {

    }

    @Override
    public void onRefresh() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                requestPayData();
            }
        }, 1000);
    }
}
