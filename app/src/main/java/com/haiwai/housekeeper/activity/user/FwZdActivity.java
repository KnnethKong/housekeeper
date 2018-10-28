package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.NeedPagerAdapter;
import com.haiwai.housekeeper.fragment.user.other.FwglFragment;
import com.haiwai.housekeeper.fragment.user.other.ZdglFragment;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.TopViewNormalBar;

import java.util.ArrayList;
import java.util.List;

public class FwZdActivity extends FragmentActivity implements View.OnClickListener {
    private TopViewNormalBar tvFZBar;
    private TextView tvToResponse, tvHistory;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex = 0;
    private ImageView mTabLineIv;
    private ImageView mTabLineIv1;
    /*
     * 屏幕的宽度
     */
    private int screenWidth;
    private List<Fragment> list_fragment;
    private NeedPagerAdapter adapter;
//    private ViewPager viewPager;
    private FwglFragment fwglFragment;
    private ZdglFragment zdglFragment;
    private FragmentManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fw_zd);
        tvFZBar = (TopViewNormalBar) findViewById(R.id.tv_fz_bar);
        tvFZBar.setTitle(getString(R.string.recuring_reports));
        tvFZBar.setImgVisible(false, 0);
        tvFZBar.setOnBackListener(mOnClickListener);

//        initLocalData();
        initView();
    }

//    private void initLocalData() {
//        fwglFragment = new FwglFragment();
//        zdglFragment = new ZdglFragment();
//        list_fragment = new ArrayList<>();
//        list_fragment.add(fwglFragment);
//        list_fragment.add(zdglFragment);
//    }

    private void initView() {

        mManager = getSupportFragmentManager();
//        viewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mTabLineIv = (ImageView) findViewById(R.id.user_iv_bottom_line);
        mTabLineIv1 = (ImageView) findViewById(R.id.user_iv_bottom_line1);
//        adapter = new NeedPagerAdapter(getSupportFragmentManager(), list_fragment);
//        viewPager.setAdapter(adapter);
        tvToResponse = (TextView) findViewById(R.id.need_tv_toresponse);
        tvHistory = (TextView) findViewById(R.id.need_tv_history);
        tvToResponse.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
        tvHistory.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
        tvToResponse.setOnClickListener(this);
        tvHistory.setOnClickListener(this);
        tvToResponse.performClick();
//        initTabLineWidth();
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            /**
//             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
//             */
//            @Override
//            public void onPageScrollStateChanged(int state) {
//            }
//
//            /**
//             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
//             * offsetPixels:当前页面偏移的像素位置
//             */
//            @Override
//            public void onPageScrolled(int position, float offset,
//                                       int offsetPixels) {
//                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
//                        .getLayoutParams();
//                /**
//                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
//                 * 设置mTabLineIv的左边距 滑动场景：
//                 * 记2个页面,
//                 * 从左到右分别为0,1
//                 * 0->1; 1->0
//                 */
//
//                if (currentIndex == 0 && position == 0)// 0->1
//                {
//                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 2) + currentIndex
//                            * (screenWidth / 2)) + screenWidth / 7;
//
//                } else if (currentIndex == 1 && position == 0) // 1->0
//                {
//                    lp.leftMargin = (int) (-(1 - offset)
//                            * (screenWidth * 1.0 / 2) + currentIndex
//                            * (screenWidth / 2)) + screenWidth / 7;
//
//                }
//                mTabLineIv.setLayoutParams(lp);
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                initSelect();
//                switch (position) {
//                    case 0:
//                        tvToResponse.setSelected(true);
//                        break;
//                    case 1:
//                        tvHistory.setSelected(true);
//
//                        break;
//                }
//                currentIndex = position;
//            }
//        });
    }

    public void initSelect() {
        mTabLineIv1.setVisibility(View.GONE);
        mTabLineIv.setVisibility(View.GONE);
        tvToResponse.setSelected(false);
        tvHistory.setSelected(false);
    }

//    private void initTabLineWidth() {
//        DisplayMetrics dpMetrics = new DisplayMetrics();
//        getWindow().getWindowManager().getDefaultDisplay()
//                .getMetrics(dpMetrics);
//        screenWidth = dpMetrics.widthPixels;
//        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
//                .getLayoutParams();
//        lp.width = screenWidth * 2 / 9;
//        lp.leftMargin = screenWidth / 7 + (int) (currentIndex * (screenWidth * 1.0 / 2));
//        mTabLineIv.setLayoutParams(lp);
//    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == tvFZBar.getBackView()) {
                finish();
            }
        }
    };
    private boolean isFirstShow = true;

    @Override
    public void onClick(View view) {
        initSelect();
        FragmentTransaction ft = mManager.beginTransaction();
        hideFragment(ft);
        switch (view.getId()) {
            case R.id.need_tv_toresponse:
                isFirstShow = true;
                mTabLineIv.setVisibility(View.VISIBLE);
                tvToResponse.setSelected(true);
//                viewPager.setCurrentItem(0);
                if(fwglFragment==null){
                    fwglFragment = new FwglFragment();
                    ft.add(R.id.fl_fragment_view,fwglFragment);
                }else{
                    ft.show(fwglFragment);
                }
                break;
            case R.id.need_tv_history:
                isFirstShow = false;
                mTabLineIv1.setVisibility(View.VISIBLE);
                tvHistory.setSelected(true);
//                viewPager.setCurrentItem(1);
                if(zdglFragment==null){
                    zdglFragment = new ZdglFragment();
                    ft.add(R.id.fl_fragment_view,zdglFragment);
                }else{
                    ft.show(zdglFragment);
                }
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft){
        if(zdglFragment!=null){
            ft.hide(zdglFragment);
        }
        if(fwglFragment!=null){
            ft.hide(fwglFragment);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(isFirstShow){
            fwglFragment.onActivityResult(requestCode, resultCode, data);
        }else{
            zdglFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
