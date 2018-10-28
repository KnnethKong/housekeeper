package com.haiwai.housekeeper.fragment.user.contact;

import android.net.Uri;
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
import com.haiwai.housekeeper.adapter.ContactWatchBothAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseFragment;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.PersonEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.CustomSwipeToRefresh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

/**
 * Created by ihope007 on 2016/9/2.
 */
public class WatchBothFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private CustomSwipeToRefresh customSwipeToRefresh;
    private ListView lvbody;
    private String mUid;
    private ContactWatchBothAdapter mWatchBothAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_fragment_contact_watchboth, null);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        lvbody = (ListView) view.findViewById(R.id.contact_watch_both_lv);
        customSwipeToRefresh = (CustomSwipeToRefresh) view.findViewById(R.id.contact_both_watch_cst);
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
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(getActivity(), Contants.mutual_concern, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                System.out.println(">>>>>>>>>>>>>>>....关注..." + response);
                if (code == 200) {
                    PersonEntity personEntity = new Gson().fromJson(response, PersonEntity.class);
                    bintDataView(personEntity.getData());
                    customSwipeToRefresh.setRefreshing(false);
                } else {
                    ToastUtil.longToast(getActivity(), ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void bintDataView(final List<PersonEntity.DataBean> data) {
        mWatchBothAdapter = new ContactWatchBothAdapter(getActivity(), data);
        mWatchBothAdapter.setOnChatClickListener(new ContactWatchBothAdapter.OnChatClickListener() {
            @Override
            public void onChatPosition(int i) {
                String targetId = data.get(i).getFuid();
                String title = data.get(i).getNickname();
                IMKitService.proDetailMap.put("oid", data.get(i).getId());
                IMKitService.proDetailMap.put("type", data.get(i).getType());
                if (RongIM.getInstance() != null) {
                    /**
                     * 启动单聊界面。
                     * @param context      应用上下文。
                     * @param targetUserId 要与之聊天的用户 Id。
                     * @param title        聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
                     */
                    RongIM.getInstance().startPrivateChat(getActivity(), targetId, title);
                    RongIM.getInstance().setCurrentUserInfo(new UserInfo(targetId, title, Uri.parse(data.get(i).getAvatar())));
                    RongIM.getInstance().refreshUserInfoCache(new UserInfo(targetId, title, Uri.parse(data.get(i).getAvatar())));
                }
            }
        });
        lvbody.setAdapter(mWatchBothAdapter);
        mWatchBothAdapter.notifyDataSetChanged();
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
