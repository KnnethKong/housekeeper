package com.haiwai.housekeeper.activity.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.FindNewAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.ProListEntity;
import com.haiwai.housekeeper.entity.SigGogleEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LocationUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.ClearEditText;
import com.haiwai.housekeeper.view.xlistview.XListView;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindSearchActivity extends AppCompatActivity implements View.OnClickListener, XListView.IXListViewListener {
    private ImageButton iv_back_arrow;
    private TextView tv_cancel;
    private ClearEditText et_input_content;
    private XListView find_detail_listview;
    private List<ProListEntity.DataBean> list = new ArrayList<>();
    private FindNewAdapter findAdapter;
    private int page = 1;
    private String km = "";
    private String sf_ren = "";
    private String jn_ren = "";
    private String sort = "";
    private String type = "";
    private String nickName = "";
    User user;
    boolean isRefresh = false;
    private String isZhorEn = "";
    SigGogleEntity mSigGogleEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_search);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
        initLocationData();
        initData(1, nickName);
    }

    private void initLocationData() {
        LocationUtils locationUtils = new LocationUtils(FindSearchActivity.this);
        String str = locationUtils.getGeoStr();
        mSigGogleEntity = LocationUtils.parStr(str);
    }

    private void initData(final int page, String nickname) {
        if (!isRefresh) {
            LoadDialog.showProgressDialog(FindSearchActivity.this);
        }
        user = AppGlobal.getInstance().getUser();
        Map<String, String> map = new HashMap<>();
        map.put("nickname", nickname);
        map.put("uid", user.getUid() == null ? "" : user.getUid());
        map.put("type", type);
        map.put("lat", mSigGogleEntity.getLat());
        map.put("long", mSigGogleEntity.getLng());
        map.put("km", km);
        map.put("sf_ren", sf_ren);
        map.put("jn_ren", jn_ren);
        map.put("sort", sort);
        map.put("page", page + "");
        map.put("page_size", "10");
        map.put("secret_key", SPUtils.getString(FindSearchActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(FindSearchActivity.this, Contants.find_lst, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        ProListEntity entity = new Gson().fromJson(response, ProListEntity.class);
                        bindData(entity.getData(), page);
                        if (isRefresh) {
                            find_detail_listview.stopRefresh();
                            find_detail_listview.stopLoadMore();
                        } else {
                            LoadDialog.closeProgressDialog();
                        }
                    } else {
                        if (isRefresh) {
                            find_detail_listview.stopRefresh();
                            find_detail_listview.stopLoadMore();
                        } else {
                            LoadDialog.closeProgressDialog();
                        }
                        ToastUtil.shortToast(FindSearchActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    private void bindData(List<ProListEntity.DataBean> data, int page) {
        if (page == 1) {
            list.clear();
        }
        if (data.size() < 10) {
            page--;
            find_detail_listview.setPullLoadEnable(false);
        }
        list.addAll(data);
        findAdapter.setList(list);
    }

    private void initView() {
        iv_back_arrow = (ImageButton) findViewById(R.id.iv_back_arrow);
        iv_back_arrow.setOnClickListener(this);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        et_input_content = (ClearEditText) findViewById(R.id.et_input_content);
        find_detail_listview = (XListView) findViewById(R.id.find_detail_listview);
        find_detail_listview.setXListViewListener(this);
        find_detail_listview.setPullRefreshEnable(true);
        find_detail_listview.setPullLoadEnable(true);
        find_detail_listview.setRefreshTime(System.currentTimeMillis());
        findAdapter = new FindNewAdapter(FindSearchActivity.this, list);
        find_detail_listview.setAdapter(findAdapter);
        findAdapter.notifyDataSetChanged();
        find_detail_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("uid", list.get(position - 1).getUid());
                bundle.putString("nickname", list.get(position - 1).getNickname());
                bundle.putString("type", list.get(position - 1).getType());
                bundle.putString("choose", "0");
                bundle.putString("oid", "");
                ActivityTools.goNextActivity(FindSearchActivity.this, ProDetailActivity.class, bundle);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_arrow:
                finish();
                break;
            case R.id.tv_cancel:
                nickName = et_input_content.getText().toString().trim();
                initData(1, nickName);
                break;
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        initData(page, nickName);
        find_detail_listview.setPullLoadEnable(true);
        isRefresh = true;
    }

    @Override
    public void onLoadMore() {
        page++;
        initData(page, nickName);
        isRefresh = true;
    }
}
