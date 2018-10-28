package com.haiwai.housekeeper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ihope006 on 2016/12/6.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;
    private List<String> mStringList;

    public SimpleFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> strList) {
        super(fm);
        this.mFragmentList = fragments;
        this.mStringList = strList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return mStringList.get(position % mStringList.size());
    }
}
