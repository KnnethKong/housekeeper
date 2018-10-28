package com.haiwai.housekeeper.activity.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.MyAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.SysmsgEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.view.xlistview.XListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SysMessageActivity extends AppCompatActivity implements XListView.IXListViewListener {
    private TopViewNormalBar top_sys_bar;
    private XListView mXListView;
    private MyAdapter mMyAdapter;
    private int page = 1;
    private static final String PAGE_SIZE = "10";
    private Map<String, String> map = new HashMap<>();
    private boolean isLoadMore = false;
    private ArrayList<SysmsgEntity.DataBean> list = new ArrayList<>();
    private String isZhorEn = "";

    private int itemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sys_message);
        top_sys_bar = (TopViewNormalBar) findViewById(R.id.top_sys_bar);
        top_sys_bar.setTitle(getString(R.string.sys_msg));
        top_sys_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
        initData(page);
    }

    private void initData(int page) {
        map.put("page", String.valueOf(page));
        map.put("page_size", PAGE_SIZE);
        map.put("uid",AppGlobal.getInstance().getUser().getUid());
        map.put("secret_key", SPUtils.getString(SysMessageActivity.this, "secret", ""));
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(SysMessageActivity.this, Contants.sys_push, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("系统消息............" + response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    String data = JsonUtils.getJsonStr(response,"data");
                    if(!data.equals("")&&!data.equals("{}")){
                        SysmsgEntity sysmsgEntity = new Gson().fromJson(response, SysmsgEntity.class);
                        bindDataView(sysmsgEntity);
                    }
                } else {
                    ToastUtil.longToast(SysMessageActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void bindDataView(SysmsgEntity sysmsgEntity) {
        if (isLoadMore) {
            list.addAll(sysmsgEntity.getData());
        } else {
            list.clear();
            list.addAll(sysmsgEntity.getData());
        }
        mMyAdapter = new MyAdapter(list, R.layout.sys_xlistview_item) {
            @Override
            public void bindView(ViewHolder holder, Object obj) {
                ((TextView) holder.getView(R.id.tv_sys_title)).setText(((SysmsgEntity.DataBean) obj).getTitle());
                ((TextView) holder.getView(R.id.tv_sys_time)).setText(TimeUtils.getDate(((SysmsgEntity.DataBean) obj).getCtime()));
                ((TextView) holder.getView(R.id.tv_sys_content)).setText(((SysmsgEntity.DataBean) obj).getTitle());
            }
        };
        mXListView.stopRefresh();
        mXListView.stopLoadMore();
        mXListView.setAdapter(mMyAdapter);
        mMyAdapter.notifyDataSetChanged();
        if(isLoadMore){
            mXListView.setSelection(itemCount-1);
        }
    }


    private void initView() {
        mXListView = (XListView) findViewById(R.id.xlistview_sys_msg);
        mXListView.setXListViewListener(this);
        mXListView.setPullRefreshEnable(true);
        mXListView.setPullLoadEnable(true);
        mXListView.setRefreshTime(System.currentTimeMillis());
    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_sys_bar.getBackView()) {
                finish();
            }
        }
    };

    @Override
    public void onRefresh() {
        page = 1;
        isLoadMore = false;
        mXListView.setPullLoadEnable(true);
        initData(page);
    }

    @Override
    public void onLoadMore() {
        page++;
        isLoadMore = true;
        itemCount = mMyAdapter.getCount();
        initData(page);
    }
}
