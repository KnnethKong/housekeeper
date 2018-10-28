package com.haiwai.housekeeper.fragment.user;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.NeedPagerAdapter;
import com.haiwai.housekeeper.base.BaseFragment;
import com.haiwai.housekeeper.fragment.user.need.DoingFragment;
import com.haiwai.housekeeper.fragment.user.need.HistoryFragment;
import com.haiwai.housekeeper.fragment.user.need.ToResponseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ihope007 on 2016/9/1.
 */
public class NeedFragment extends BaseFragment {
    private TextView tvToResponse, tvDoing, tvHistory;

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
    private NeedPagerAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_fragment_need, container, false);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.first_ib_back).setVisibility(View.GONE);
        ((TextView) view.findViewById(R.id.first_tv_title)).setText(getResources().getString(R.string.main_need));
        viewPager = (ViewPager) view.findViewById(R.id.need_vp);
        mTabLineIv = (ImageView) view.findViewById(R.id.user_iv_bottom_line);

        list_fragment = new ArrayList<>();
        list_fragment.add(ToResponseFragment.getNewInstance());
        list_fragment.add(DoingFragment.getNewInstance());
        list_fragment.add(HistoryFragment.getNewInstance());

        adapter = new NeedPagerAdapter(getChildFragmentManager(), list_fragment);
        viewPager.setAdapter(adapter);
//        viewPager.setPageTransformer(true,new DepthPageTransformer());

        tvToResponse = (TextView) view.findViewById(R.id.need_tv_toresponse);
        tvDoing = (TextView) view.findViewById(R.id.need_tv_doing);
        tvHistory = (TextView) view.findViewById(R.id.need_tv_history);
        tvToResponse.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
        tvDoing.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
        tvHistory.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
        tvToResponse.setOnClickListener(this);
        tvDoing.setOnClickListener(this);
        tvHistory.setOnClickListener(this);

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
                            * (screenWidth / 3))+screenWidth/18;

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3))+screenWidth/18;

                } else if (currentIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3))+screenWidth/18;
                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3))+screenWidth/18;
                }
                mTabLineIv.setLayoutParams(lp);
            }
            @Override
            public void onPageSelected(int position) {
                initSelect();
                switch (position) {
                    case 0:
                        tvToResponse.setSelected(true);
                        break;
                    case 1:
                        tvDoing.setSelected(true);
                        break;
                    case 2:
                        tvHistory.setSelected(true);
                        break;
                }
                currentIndex = position;
            }
        });

    }

    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getActivity().getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                .getLayoutParams();
        lp.width = screenWidth*2 / 9;
        lp.leftMargin=screenWidth/18+(int)(currentIndex*(screenWidth * 1.0 / 3));
        mTabLineIv.setLayoutParams(lp);
    }


    @Override
    protected void initData() {
        tvToResponse.setSelected(true);
    }

    public void initSelect() {
        tvToResponse.setSelected(false);
        tvDoing.setSelected(false);
        tvHistory.setSelected(false);
    }

    @Override
    protected void click(View v) {
        initSelect();
        switch (v.getId()) {
            case R.id.need_tv_toresponse:
                tvToResponse.setSelected(true);
                viewPager.setCurrentItem(0);
                break;
            case R.id.need_tv_doing:
                tvDoing.setSelected(true);
                viewPager.setCurrentItem(1);
                break;
            case R.id.need_tv_history:
                tvHistory.setSelected(true);
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
