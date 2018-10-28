package com.haiwai.housekeeper.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.haiwai.housekeeper.utils.ActivityManagerUtil;

public abstract class BaseFragmentActivity extends FragmentActivity implements View.OnClickListener {

    protected static final String TAG = "BaseFragmentActivity";

    protected MyApp mApp;
    protected RequestQueue mRequestQueue;
    private RelativeLayout mLayoutRoot;
    private ActivityManagerUtil activityManagerUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = MyApp.getTingtingApp();
        mRequestQueue = mApp.getRequestQueue();
        initView();
        initData();
        activityManagerUtil = ActivityManagerUtil.getInstance();
        activityManagerUtil.pushOneActivity(this);
    }

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 加载数据
     */
    protected abstract void initData();

    /**
     * 各种监听，点击事件
     */
    protected abstract void click(View v);

    @Override
    public void onClick(View v) {
        click(v);
    }

    /**
     * 判断是否有网
     *
     * @param
     * @return
     */
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (null != connectivity) {
//            NetworkInfo info = connectivity.getActiveNetworkInfo();
//            if (null != info && info.isConnected()) {
//                if (info.getState() == NetworkInfo.State.CONNECTED) {
//                    return true;
//                }
//            }
//        }
//        ToastUtil.shortToast(this, CommonConfig.NO_NETWORK);
//        return false;
        NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityManagerUtil.popOneActivity(this);
    }
}
