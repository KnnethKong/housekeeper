package com.haiwai.housekeeper.base;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.volley.RequestQueue;
import com.haiwai.housekeeper.utils.ToastUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected MyApp mApp;
    protected RequestQueue mRequestQueue;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_base, container, false);
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mApp = MyApp.getTingtingApp();
        mRequestQueue = mApp.getRequestQueue();
        initView(view, savedInstanceState);
        initData();
    }

    /**
     * 初始化视图
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

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
        ConnectivityManager connectivity = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivity) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        ToastUtil.shortToast(getActivity(), CommonConfig.NO_NETWORK);
        return false;
    }
}
