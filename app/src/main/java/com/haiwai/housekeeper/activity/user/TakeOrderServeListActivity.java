package com.haiwai.housekeeper.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.TakeOrderServeAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.entity.NeedEntity;
import com.haiwai.housekeeper.entity.TakeOrderServeEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.libs.ui.PullToRefreshListView;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.widget.RegisterDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.haiwai.housekeeper.base.MyApp.context;

/**
 * Created by ihope007 on 2016/9/23.
 * 接单服务商列表
 */
public class TakeOrderServeListActivity extends BaseActivity {
    private int page = 1;
    private String id = "";
    private String lat = "";
    private String longx = "";
    private String type = "";
    private String addr = "";
    private String date = "";

    private PullToRefreshListView mPullListView;
    private ListView mListView;
    private List<TakeOrderServeEntity.DataBean> list;
    private TakeOrderServeAdapter adapter;
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");
//    boolean hasMoreData = true;

    private PopupWindow pop;
    private View headView;
    private TextView tvaddr;
    private TextView tvdate;

    private String isZhorEn = "";

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_take_order_serve_list, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mPullListView = (PullToRefreshListView) findViewById(R.id.take_order_serve_lv);
        mPullListView.setPullLoadEnabled(false);
        mPullListView.setScrollLoadEnabled(false);
        mListView = mPullListView.getRefreshableView();
        headView = getLayoutInflater().inflate(R.layout.take_order_serve_headview, null);
        tvaddr = (TextView) headView.findViewById(R.id.take_order_headview_tv_addr);
        tvdate = (TextView) headView.findViewById(R.id.take_order_headview_tv_date);
        addr = getIntent().getExtras().get("addr").toString();
        date = getIntent().getExtras().get("date").toString();
        if (!TextUtils.isEmpty(addr)) {
            tvaddr.setText(addr);
        }else{
            if(AppGlobal.getInstance().getLagStr().equals("zh")){
                tvaddr.setText("无地址");
            }else{
                tvaddr.setText("No Address");
            }
        }
        if (!TextUtils.isEmpty(date))
            tvdate.setText(TimeUtils.getDate3(date));
        mListView.addHeaderView(headView);
        mListView.setCacheColorHint(0);
        mListView.setCacheColorHint(0);
        mListView.setSelector(R.color.transparent);
        mListView.setDrawSelectorOnTop(false);
        list = new ArrayList<>();
        adapter = new TakeOrderServeAdapter(this, list);
        mListView.setAdapter(adapter);
        mPullListView.setOnRefreshListener(new com.haiwai.housekeeper.libs.ui.PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(com.haiwai.housekeeper.libs.ui.PullToRefreshBase<ListView> refreshView) {
                page = 1;
                requestServeList(1);
            }

            @Override
            public void onPullUpToRefresh(com.haiwai.housekeeper.libs.ui.PullToRefreshBase<ListView> refreshView) {
//                page++;
//                requestServeList(2);
            }
        });
        mListView.setDivider(null);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TakeOrderServeListActivity.this, UserProActivity.class);
                Bundle bundle = new Bundle();
                TakeOrderServeEntity.DataBean dataBean = list.get(position - 1);
                bundle.putSerializable("dataBean", dataBean);
                bundle.putString("id", getIntent().getExtras().getString("id"));
                bundle.putString("proid",getIntent().getExtras().getString("proid"));
                intent.putExtras(bundle);
                startActivityForResult(intent,100);
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
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        id = getIntent().getExtras().get("id").toString();
        lat = getIntent().getExtras().get("lat").toString();
        longx = getIntent().getExtras().get("long").toString();
        type = getIntent().getExtras().get("type").toString();
        setTitle(getString(R.string.select_pro),R.color.black2);
    }

    public void requestServeList(final int isSwipe) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("lat", lat);
        map.put("long", longx);
        map.put("page", page + "");
        map.put("page_size", "10");
        map.put("secret_key", SPUtils.getString(TakeOrderServeListActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(this, Contants.single_lst, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        if (page == 1) {
                            list.clear();
                        }
                        TakeOrderServeEntity entity = new Gson().fromJson(response, TakeOrderServeEntity.class);
                        list.addAll(entity.getData());
                        if (list.size() == 0) {
                            new RegisterDialog(TakeOrderServeListActivity.this, getString(R.string.message_alert), getString(R.string.take_order_serve_list_toast)).show();
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        ToastUtil.shortToast(TakeOrderServeListActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (isSwipe == 1) {
                        mPullListView.onPullDownRefreshComplete();
                    } else if (isSwipe == 2) {
//                        mPullListView.onPullUpRefreshComplete();
                    }
//                    mPullListView.setHasMoreData(hasMoreData);
                    setLastUpdateTime();
                }
            }
        }));
    }

    @Override
    protected void click(View v) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setResult(RESULT_OK);
        finish();
    }

    /**
     * 技能认证弹窗
     */
    public void showPopWindow(View view) {
        View v = LayoutInflater.from(this)
                .inflate(R.layout.pop_skill, null);
        pop = new PopupWindow(v, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        ((TextView) v.findViewById(R.id.pop_skill_tv)).setText(getString(R.string.take_order_serve_special_invite));
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.showAsDropDown(view);
    }
}
