package com.haiwai.housekeeper.activity.server;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.OrderFragmentPagerAdapter;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.fragment.server.ChatFocusMe;
import com.haiwai.housekeeper.fragment.server.ChatMeFocus;
import com.haiwai.housekeeper.fragment.server.ChatMeOther;
import com.haiwai.housekeeper.view.TopViewNormalBar;

import java.util.ArrayList;

public class ContactActivity extends BaseProActivity {
    private ImageView mTabLineIv;
    /**
     * 屏幕的宽度
     */
    private int screenWidth;
    private TextView tvContactA, tvContactB, tvContactC;
    private ArrayList<Fragment> mFragmentList = null;
    private ViewPager id_contact_vp;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;
    /**
     * Fragment
     */
    private ChatMeFocus mMeFocus;
    private ChatFocusMe mFocusMe;
    private ChatMeOther mMeOther;
    TopViewNormalBar top_contact_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        top_contact_bar = (TopViewNormalBar) findViewById(R.id.top_contact_bar);
        top_contact_bar.setTitle("联系人");
        top_contact_bar.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == top_contact_bar.getBackView()) {
                    finish();
                }
            }
        });
        initView();
    }

    private void initView() {
        tvContactA = (TextView) findViewById(R.id.tv_contact_a);
        tvContactB = (TextView) findViewById(R.id.tv_contact_b);
        tvContactC = (TextView) findViewById(R.id.tv_contact_c);
        tvContactA.setOnClickListener(new mOnClickListener(0));
        tvContactB.setOnClickListener(new mOnClickListener(1));
        tvContactC.setOnClickListener(new mOnClickListener(2));
        mTabLineIv = (ImageView) findViewById(R.id.iv_bottom_line);
        tvContactA.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
        tvContactB.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
        tvContactC.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
        id_contact_vp = (ViewPager) findViewById(R.id.id_contact_vp);
        initTabLineWidth();
        initFragmentView();
    }

    private void initFragmentView() {
        mFragmentList = new ArrayList<>();
        mMeFocus = new ChatMeFocus();
        mFocusMe = new ChatFocusMe();
        mMeOther = new ChatMeOther();

        mFragmentList.add(mMeFocus);
        mFragmentList.add(mFocusMe);
        mFragmentList.add(mMeOther);

        id_contact_vp.setAdapter(new OrderFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList));
        //让ViewPager缓存2个页面
        id_contact_vp.setOffscreenPageLimit(3);
        id_contact_vp.setCurrentItem(0);
        tvContactA.setTextColor(Color.parseColor("#494949"));
        id_contact_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

                Log.e("offset:", offset + "");
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
                            * (screenWidth / 3));

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));

                } else if (currentIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                }
                mTabLineIv.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        tvContactA.setTextColor(Color.parseColor("#494949"));
                        break;
                    case 1:
                        tvContactB.setTextColor(Color.parseColor("#494949"));
                        break;
                    case 2:
                        tvContactC.setTextColor(Color.parseColor("#494949"));
                        break;
                }
                currentIndex = position;
            }
        });
    }

    class mOnClickListener implements View.OnClickListener {
        private int index;

        public mOnClickListener(int i) {
            this.index = i;
        }

        @Override
        public void onClick(View v) {
            id_contact_vp.setCurrentItem(index);
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
        lp.width = screenWidth / 3;
        mTabLineIv.setLayoutParams(lp);
    }

    /**
     * 重置颜色
     */
    private void resetTextView() {
        tvContactA.setTextColor(Color.parseColor("#A7A8AA"));
        tvContactB.setTextColor(Color.parseColor("#A7A8AA"));
        tvContactC.setTextColor(Color.parseColor("#A7A8AA"));
    }
}
