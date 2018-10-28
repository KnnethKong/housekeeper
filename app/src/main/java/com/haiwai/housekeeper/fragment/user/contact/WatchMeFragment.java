package com.haiwai.housekeeper.fragment.user.contact;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.ContactWatchMeAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseFragment;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.PersonEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.CustomSwipeToRefresh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/2.
 */
public class WatchMeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private CustomSwipeToRefresh customSwipeToRefresh;
    private ListView lvbody;
    private String mUid;
    private ContactWatchMeAdapter mWatchMeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_fragment_contact_watchme, null);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        lvbody = (ListView) view.findViewById(R.id.contact_watch_me_lv);
        customSwipeToRefresh = (CustomSwipeToRefresh) view.findViewById(R.id.contact_me_watch_cst);
        customSwipeToRefresh.setColorSchemeResources(R.color.theme);
        customSwipeToRefresh.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
       getData();
    }

    public void getData(){
        User user = AppGlobal.getInstance().getUser();
        if (user != null) {
            mUid = user.getUid();
        }
        Map<String, String> map = new HashMap<>();
        map.put("uid", mUid);
        map.put("secret_key", SPUtils.getString(getActivity(), "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(getActivity(), Contants.fans_lst, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    PersonEntity personEntity = new Gson().fromJson(response, PersonEntity.class);
                    bindDataVew(personEntity.getData());
                    customSwipeToRefresh.setRefreshing(false);
                } else {
                    ToastUtil.longToast(getActivity(), ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }


    private void bindDataVew(List<PersonEntity.DataBean> data) {
        mWatchMeAdapter = new ContactWatchMeAdapter(getActivity(), data);
        lvbody.setAdapter(mWatchMeAdapter);
        mWatchMeAdapter.notifyDataSetChanged();

    }

    @Override
    protected void click(View v) {

    }

    public void setRefData() {
        initData();
    }

    @Override
    public void onRefresh() {
        initData();
    }
}
