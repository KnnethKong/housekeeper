package com.wn.library.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 王宁 on 2017/2/14.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = creatFragmentView(inflater, container, savedInstanceState);
        initView(view);
        initListener(view);
        initData();
        return view;
    }


    public abstract void initView(View view);
    public abstract void initListener(View view);
    public abstract void initData();
    public abstract void initClick(View v);
    public abstract View creatFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    @Override
    public void onClick(View view) {
        initClick(view);
    }
}
