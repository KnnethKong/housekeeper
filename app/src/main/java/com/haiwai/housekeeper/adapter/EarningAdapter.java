package com.haiwai.housekeeper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by range on 2018/1/18.
 */

public class EarningAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragments;

    public EarningAdapter(FragmentManager fm, List<Fragment> fragments) {

        super(fm);
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
//        if (position == 0) {
//            return new ShangpinFragment();
//        } else if (position == 1) {
//            return new PingjiaFragment();
//        } else if (position == 2) {
//            return new JieshaoFragment();
//        }
//        return new ShangpinFragment();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
