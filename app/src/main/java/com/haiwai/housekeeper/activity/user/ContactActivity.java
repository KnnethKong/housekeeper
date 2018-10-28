package com.haiwai.housekeeper.activity.user;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.NeedPagerAdapter;
import com.haiwai.housekeeper.base.BaseFragmentActivity;
import com.haiwai.housekeeper.fragment.user.contact.IWatchFragment;
import com.haiwai.housekeeper.fragment.user.contact.WatchBothFragment;
import com.haiwai.housekeeper.fragment.user.contact.WatchMeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ihope007 on 2016/9/21.
 * 联系人界面
 */
public class ContactActivity extends BaseFragmentActivity {
    private TextView tviwatch, tvwatchme, tvwatchboth;

//    private IWatchFragment iWatchFragment;
//    private WatchMeFragment watchMeFragment;
//    private WatchBothFragment watchBothFragment;

    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;
    private ImageView mTabLineIv;

    /*
    * 屏幕的宽度
    */
    private int screenWidth;

    private ViewPager viewPager;
    private List<Fragment> list_fragment;
    IWatchFragment mIWatchFragment;
    WatchMeFragment mWatchMeFragment;
    WatchBothFragment mWatchBothFragment;
    private NeedPagerAdapter adapter;
    boolean isJpush = false;

    @Override
    protected void initView() {
        setContentView(R.layout.user_contact);
        findViewById(R.id.first_ib_back).setOnClickListener(this);
        findViewById(R.id.first_v_line).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.first_tv_title)).setText(getString(R.string.contact_title));

        viewPager = (ViewPager) findViewById(R.id.contact_vp);
        mTabLineIv = (ImageView) findViewById(R.id.user_iv_bottom_line);

        list_fragment = new ArrayList<>();
        mIWatchFragment = new IWatchFragment();
        mWatchMeFragment = new WatchMeFragment();
        mWatchBothFragment = new WatchBothFragment();

        list_fragment.add(mIWatchFragment);
        list_fragment.add(mWatchMeFragment);
        list_fragment.add(mWatchBothFragment);
        adapter = new NeedPagerAdapter(getSupportFragmentManager(), list_fragment);
        viewPager.setAdapter(adapter);

        tviwatch = (TextView) findViewById(R.id.contact_tv_i_watch);
        tvwatchme = (TextView) findViewById(R.id.contact_tv_watch_me);
        tvwatchboth = (TextView) findViewById(R.id.contact_tv_watch_both);
        tviwatch.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
        tvwatchme.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
        tvwatchboth.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
        tviwatch.setOnClickListener(this);
        tvwatchme.setOnClickListener(this);
        tvwatchboth.setOnClickListener(this);

        initTabLineWidth();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }

            /**
             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
             * offsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float offset,
                                       int offsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                        .getLayoutParams();

                /**
                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
                 * 设置mTabLineIv的左边距 滑动场景：
                 * 记3个页面,
                 * 从左到右分别为0,1,2
                 * 0->1; 1->2; 2->1; 1->0
                 */

                if (currentIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3)) + screenWidth / 18;

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3)) + screenWidth / 18;

                } else if (currentIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3)) + screenWidth / 18;
                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3)) + screenWidth / 18;
                }
                mTabLineIv.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                initSelect();
                switch (position) {
                    case 0:
                        tviwatch.setSelected(true);
                        break;
                    case 1:
                        tvwatchme.setSelected(true);
                        break;
                    case 2:
                        tvwatchboth.setSelected(true);
                        break;
                }
                currentIndex = position;
            }
        });

        if(getIntent().getBooleanExtra("isWatch",false)){
            tviwatch.setSelected(true);
            viewPager.setCurrentItem(0);
            ((IWatchFragment) viewPager.getAdapter().instantiateItem(viewPager, 0)).getData();
        }
    }

    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                .getLayoutParams();
        lp.width = screenWidth * 2 / 9;
        lp.leftMargin = screenWidth / 18 + (int) (currentIndex * (screenWidth * 1.0 / 3));
        mTabLineIv.setLayoutParams(lp);
    }

    public void initSelect() {
        tviwatch.setSelected(false);
        tvwatchme.setSelected(false);
        tvwatchboth.setSelected(false);
    }

    @Override
    protected void initData() {
        isJpush = getIntent().getBooleanExtra("flag", false);
        String type = getIntent().getStringExtra("type");
        if (isJpush) {
            if ("6".equals(type)) {
                viewPager.setCurrentItem(2);
                mWatchMeFragment.setRefData();
            } else if ("17".equals(type)) {
                viewPager.setCurrentItem(2);
                mWatchBothFragment.setRefData();
            }

        }
    }


    @Override
    protected void click(View v) {
        initSelect();
        switch (v.getId()) {
            case R.id.first_ib_back:
                finish();
                break;
            case R.id.contact_tv_i_watch:
                tviwatch.setSelected(true);
                viewPager.setCurrentItem(0);
                ((IWatchFragment) viewPager.getAdapter().instantiateItem(viewPager, 0)).getData();
                break;
            case R.id.contact_tv_watch_me:
                tvwatchme.setSelected(true);
                viewPager.setCurrentItem(1);
                ((WatchMeFragment) viewPager.getAdapter().instantiateItem(viewPager, 1)).getData();
                break;
            case R.id.contact_tv_watch_both:
                tvwatchboth.setSelected(true);
                viewPager.setCurrentItem(2);
                ((WatchBothFragment) viewPager.getAdapter().instantiateItem(viewPager, 2)).getData();
                break;
        }
    }
}
