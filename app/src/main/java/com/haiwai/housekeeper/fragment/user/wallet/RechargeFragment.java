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
import com.haiwai.housekeeper.adapter.WalletRechargeAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseFragment;
import com.haiwai.housekeeper.entity.WalletPayEntity;
import com.haiwai.housekeeper.entity.WalletRechargeEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.mylhyl.crlayout.SwipeRefreshRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/6.
 * 我的钱包——充值记录
 */
public class RechargeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshRecyclerView recyclerView;
    private List<WalletRechargeEntity.DataBean.DateBean> list;
    private WalletRechargeAdapter adapter;
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_fragment_wallet_recharge, null);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        recyclerView = (SwipeRefreshRecyclerView) view.findViewById(R.id.wallet_recharge_lv);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setOnRefreshListener(this);
        recyclerView.getSwipeRefreshLayout().setColorSchemeColors(R.color.theme);
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        adapter = new WalletRechargeAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
//        recyclerView.setEmptyText("暂无数据");
        recyclerView.autoRefresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        requestRechargeData();

    }

    private void requestRechargeData() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("secret_key", SPUtils.getString(getActivity(), "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(getActivity(), Contants.chongzhi_record, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        if (page == 1) {
                            list.clear();
                        }
                        WalletRechargeEntity entity = new Gson().fromJson(response, WalletRechargeEntity.class);
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
                requestRechargeData();
            }
        }, 1000);
    }
}
