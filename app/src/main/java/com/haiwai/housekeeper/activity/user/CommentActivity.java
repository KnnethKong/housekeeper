package com.haiwai.housekeeper.activity.user;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.CommentMoreAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.entity.CommentMoreEntity;
import com.haiwai.housekeeper.entity.StarEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
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
 * Created by ihope007 on 2016/8/21.
 * ProDetail评论列表
 */
public class CommentActivity extends BaseActivity {
    private PullToRefreshListView mPullListView;
    private ListView mListView;
    private int page = 1;
    private CommentMoreAdapter commentMoreAdapter;
    private List<CommentMoreEntity.DataBean> list;

    private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");
    //    boolean hasMoreData = true;
    private String isZhorEn = "";
    private String uid;

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_comment, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.more_comment_title), Color.parseColor("#FF3C3C3C"));
        uid = getIntent().getExtras().get("uid").toString();
        if (isNetworkAvailable())
            requestLabel();
        mPullListView = (PullToRefreshListView) findViewById(R.id.comment_more_listview);
        mPullListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView = mPullListView.getRefreshableView();

//        mListView.setDivider(null);
        setLastUpdateTime();
        mPullListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                requestCommentList(1);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                requestCommentList(2);
            }
        });
    }

    private void requestLabel() {
        Map<String,String> map =new HashMap<>();
        map.put("secret_key", SPUtils.getString(CommentActivity.this, "secret", ""));
        mRequestQueue.add(new PlatRequest(this, Contants.order_label, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");// TODO: 2016/9/12 标签 更换
                if (code == 200) {
                    StarEntity starEntity = new Gson().fromJson(response, StarEntity.class);
                    Map<String, String> starMap = JsonUtils.parseEvaluateLabel(starEntity);
                    bindDataToView(starMap);
                } else {
                    ToastUtil.shortToast(CommentActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void bindDataToView(Map<String, String> map) {
        list = new ArrayList<>();
        commentMoreAdapter = new CommentMoreAdapter(this, list, map);
        mListView.setAdapter(commentMoreAdapter);
        mPullListView.setRefreshing(true);
    }

    public void requestCommentList(final int isSwipe) {
//        hasMoreData = true;
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("page", page + "");
        map.put("page_size", "10");
        map.put("secret_key", SPUtils.getString(CommentActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(CommentActivity.this, Contants.user_comment, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        if (page == 1) {
                            list.clear();
                        }
                        CommentMoreEntity entity = new Gson().fromJson(response, CommentMoreEntity.class);
                        if (entity.getData() == null || entity.getData().size() == 0) {
//                            hasMoreData = false;
                        }
                        list.addAll(entity.getData());
                        commentMoreAdapter.notifyDataSetChanged();
                    } else {
                        ToastUtil.shortToast(CommentActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
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

    private void setLastUpdateTime() {
        String text = formatDateTime(System.currentTimeMillis());
        mPullListView.setLastUpdatedLabel(getString(R.string.last_update_time) + text);
    }

    private String formatDateTime(long time) {
        if (0 == time) {
            return "";
        }

        return mDateFormat.format(new Date(time));
    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
    }

    @Override
    protected void click(View v) {

    }
}
