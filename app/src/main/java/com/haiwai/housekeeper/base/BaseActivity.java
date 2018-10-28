package com.haiwai.housekeeper.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.utils.ActivityManagerUtil;
import com.haiwai.housekeeper.utils.ToastUtil;

public abstract class BaseActivity extends Activity implements View.OnClickListener {

    protected static final String TAG = "BaseActivity";
    private ActivityManagerUtil activityManagerUtil;
    protected MyApp mApp;
    protected RequestQueue mRequestQueue;
    private RelativeLayout mLayoutRoot;
    public TextView mTvRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mApp = MyApp.getTingtingApp();
        mRequestQueue = mApp.getRequestQueue();
        View view = onCreateLayout(getLayoutInflater(), savedInstanceState);
        mLayoutRoot = (RelativeLayout) findViewById(R.id.layoutRoot);
        if (view != null && view.getParent() == null) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            mLayoutRoot.addView(view, params);
        }
        initView(savedInstanceState);
        initData();
        setBack(true);
        activityManagerUtil = ActivityManagerUtil.getInstance();
        activityManagerUtil.pushOneActivity(this);

    }

    /**
     * 创建完 view 以后的操作  此操作在 oncreate中执行
     *
     * @param inflater
     * @param savedInstanceState
     * @return
     */
    protected abstract View onCreateLayout(LayoutInflater inflater,
                                           Bundle savedInstanceState);

    /**
     * 初始化视图
     */
    protected abstract void initView(Bundle savedInstanceState);

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

    public void setRightVisible(boolean isVisible) {
        mTvRight = (TextView) findViewById(R.id.first_ib_right);
        if (isVisible) {
            mTvRight.setVisibility(View.VISIBLE);
        } else {
            mTvRight.setVisibility(View.GONE);
        }
    }

    public TextView getRightView() {
        return mTvRight;
    }


    public void setBack(boolean isBack) {
        findViewById(R.id.first_ib_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
        String title = getResources().getString(titleId);
        setTitle(title);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        setTitle(title, Color.WHITE);
    }

    public void setTitle(CharSequence title, int color) {
        TextView tvTitle = (TextView) findViewById(R.id.first_tv_title);
        tvTitle.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        if (title == null || TextUtils.isEmpty(title.toString()))
            title = "";
        tvTitle.setText(title);
        tvTitle.setTextColor(color);
    }

    public void setTitlebarHide(boolean isGone) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mLayoutRoot.getLayoutParams();
        if (isGone) {
            findViewById(R.id.first_rl_head).setVisibility(View.GONE);
//            params.topMargin = 0;
        } else {
            findViewById(R.id.first_rl_head).setVisibility(View.VISIBLE);
//            params.topMargin = EvmUtil.dip2px(this,
//                    R.dimen.title_bar_height);
        }
        mLayoutRoot.setLayoutParams(params);
    }

    public void setBottomLineHide(boolean isGone) {
        View bottomview = findViewById(R.id.first_v_line);
        if (isGone) {
            bottomview.setVisibility(View.GONE);
        } else {
            bottomview.setVisibility(View.VISIBLE);
        }
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
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            return true;
        }
        ToastUtil.shortToast(this, CommonConfig.NO_NETWORK);
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityManagerUtil.popOneActivity(this);
    }
}
