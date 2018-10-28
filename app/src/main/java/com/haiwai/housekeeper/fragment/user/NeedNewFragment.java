package com.haiwai.housekeeper.fragment.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.NeedNewPagerAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseFragment;
import com.haiwai.housekeeper.fragment.user.needs.AllFragment;
import com.haiwai.housekeeper.fragment.user.needs.PendingFragment;
import com.haiwai.housekeeper.fragment.user.needs.ServicingFragment;
import com.haiwai.housekeeper.fragment.user.needs.TenderFragment;
import com.haiwai.housekeeper.fragment.user.needs.TobeEvaluatedFragment;
import com.haiwai.housekeeper.fragment.user.needs.TobeHomeFragment;

import java.util.ArrayList;
import java.util.List;

public class NeedNewFragment extends BaseFragment {
    private TabLayout mTabLayout;                            //定义TabLayout
    private ViewPager mViewPager;                             //定义viewPager
    private NeedNewPagerAdapter mNeedNewPagerAdapter;                               //定义adapter

    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表

    private AllFragment mAllFragment;                   //全部
    private TenderFragment mTenderFragment;            //招标中
    private TobeHomeFragment mTobeHomeFragment;        //待上门
    private ServicingFragment mServicingFragment;      //服务中
    private PendingFragment mPendingFragment;          //待支付
    private TobeEvaluatedFragment mTobeEvaluatedFragment;//待评价
    private String isZhorEn = "";
    NeeedReceiver mNeeedReceiver;
    int i = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_need_new, container, false);
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.first_ib_back).setVisibility(View.GONE);
        ((TextView) view.findViewById(R.id.first_tv_title)).setText(getResources().getString(R.string.main_need));
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_pager);

        mNeeedReceiver = new NeeedReceiver();
        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("jpush");
        getActivity().registerReceiver(mNeeedReceiver, intentFilter);
    }

    @Override
    protected void initData() {
        initData(0);
    }

    private void initData(int i) {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        //初始化各fragment
        mAllFragment = new AllFragment();
        mTenderFragment = new TenderFragment();
        mTobeHomeFragment = new TobeHomeFragment();
        mServicingFragment = new ServicingFragment();
        mPendingFragment = new PendingFragment();
        mTobeEvaluatedFragment = new TobeEvaluatedFragment();
        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(mAllFragment);
        list_fragment.add(mTenderFragment);
        list_fragment.add(mTobeHomeFragment);
        list_fragment.add(mServicingFragment);
        list_fragment.add(mPendingFragment);
        list_fragment.add(mTobeEvaluatedFragment);
        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add(getString(R.string.need_all));
        list_title.add(getString(R.string.need_zbz));
        list_title.add(getString(R.string.need_dsm));
        list_title.add(getString(R.string.need_fwz));
        list_title.add(getString(R.string.need_dzf));
        list_title.add(getString(R.string.need_dpj));
        //设置TabLayout的模式
        if(AppGlobal.getInstance().getLagStr().equals("en")){
            mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }else{
            mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        }

        //为TabLayout添加tab名称
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(3)));
        mNeedNewPagerAdapter = new NeedNewPagerAdapter(getActivity().getSupportFragmentManager(), list_fragment, list_title);
        //viewpager加载adapter
        mViewPager.setAdapter(mNeedNewPagerAdapter);
        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager
        mTabLayout.setupWithViewPager(mViewPager);
        //tab_FindFragment_title.set
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    mAllFragment.onRefresh();
                }else if(position==1){
                    mTenderFragment.onRefresh();
                }else if(position==2){
                    mTobeHomeFragment.onRefresh();
                }else if(position==3){
                    mServicingFragment.onRefresh();
                }else if(position==4){
                    mPendingFragment.onRefresh();
                }else{
                    mTobeEvaluatedFragment.onRefresh();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    protected void click(View v) {

    }

    public void update(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mViewPager.setCurrentItem(0);
                ((AllFragment) mViewPager.getAdapter().instantiateItem(mViewPager, 0)).initData(1);
            }
        },300);
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(mNeeedReceiver);
        super.onDestroy();

    }
//    private AllFragment mAllFragment;                   //全部
//    private TenderFragment mTenderFragment;            //招标中
//    private TobeHomeFragment mTobeHomeFragment;        //待上门
//    private ServicingFragment mServicingFragment;      //服务中
//    private PendingFragment mPendingFragment;          //待支付
//    private TobeEvaluatedFragment mTobeEvaluatedFragment;//待评价

    class NeeedReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("jpush".equals(intent.getAction())) {
//                if(intent.getBooleanExtra("out",false)){
//                    mViewPager.setCurrentItem(0);
//                    if(mAllFragment!=null){
//                        mAllFragment.initData(1);
//                    }
//                    if(mTenderFragment!=null){
//                        mTenderFragment.initData(1);
//                    }
//                    if(mTobeHomeFragment!=null){
//                        mTobeHomeFragment.initData(1);
//                    }
//                    if(mServicingFragment!=null){
//                        mServicingFragment.initData(1);
//                    }
//                    if(mPendingFragment!=null){
//                        mPendingFragment.initData(1);
//                    }
//                    if(mTobeEvaluatedFragment!=null){
//                        mTobeEvaluatedFragment.initData(1);
//                    }
//                }else{
//                    mViewPager.setCurrentItem(0);
//                    mAllFragment.initData(1);
//                    mTenderFragment.initData(1);
//                }

            }else if("service".equals(intent.getAction())){
                mViewPager.setCurrentItem(3);
                mServicingFragment.initData(1);
            }else if("wn".equals(intent.getAction())){
                mViewPager.setCurrentItem(2);
                mTobeHomeFragment.initData(1);
            }
        }
    }
}
