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
import com.haiwai.housekeeper.adapter.ContactIWatchAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/2.
 */
public class IWatchFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private CustomSwipeToRefresh customSwipeToRefresh;
    private ListView lvbody;
    private String mUid;
    private List<PersonEntity.DataBean> data=new ArrayList<>();
    private ContactIWatchAdapter mIWatchAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_fragment_contact_iwatch, null);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        lvbody = (ListView) view.findViewById(R.id.contact_i_watch_lv);
        customSwipeToRefresh= (CustomSwipeToRefresh) view.findViewById(R.id.contact_i_watch_cst);
        customSwipeToRefresh.setColorSchemeResources(R.color.theme);
        customSwipeToRefresh.setOnRefreshListener(this);
        mIWatchAdapter=new ContactIWatchAdapter(getActivity(),data);
        lvbody.setAdapter(mIWatchAdapter);
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
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(getActivity(), Contants.follow_lst, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                System.out.println(">>>>>>>>>>>>>>>....我关注..." + response);
                if (code == 200) {
                    PersonEntity personEntity = new Gson().fromJson(response, PersonEntity.class);
                    bindDataToView(personEntity.getData());
                    customSwipeToRefresh.setRefreshing(false);
                } else {
                    ToastUtil.longToast(getActivity(), ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void bindDataToView(List<PersonEntity.DataBean> datas) {
        data.clear();
        data.addAll(datas);
        mIWatchAdapter.notifyDataSetChanged();
    }

    @Override
    protected void click(View v) {

    }

    @Override
    public void onRefresh() {
        initData();
    }
}
