package com.haiwai.housekeeper.activity.server.earnings;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.server.ProMainActivity;
import com.haiwai.housekeeper.activity.server.ProSkillShoweActivity;
import com.haiwai.housekeeper.activity.user.message.ConversationListStatci1Fragment;
import com.haiwai.housekeeper.adapter.EarningAdapter;
import com.haiwai.housekeeper.adapter.MyPagerAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.utils.TabLayoutUtils;
import com.haiwai.housekeeper.view.TopViewNormalBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by range on 2018/1/18.
 */

public class MyEarningsActivity extends AppCompatActivity {
    TopViewNormalBar top_contact_bar;
    private TabLayout tl_display;
    private ViewPager vpStatus;
    public List<Fragment> fragments;
    private FragmentManager manager;
    private EarningAdapter adapter;
    private String isZhorEn = "";
    private String isJump;

    ShouruFragment shouruFragment;
    ShoukuanxinxiFragment shoukuanxinxiFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myearning);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
        initData();
    }


    private void initView() {
        top_contact_bar = (TopViewNormalBar) findViewById(R.id.top_prob_bar);
        if (isZhorEn.equals("en")) {
            top_contact_bar.setTitle("My Earnings");
        } else {
            top_contact_bar.setTitle("我的收入");
        }
        tl_display = (TabLayout) findViewById(R.id.tl_display);
        vpStatus = (ViewPager) findViewById(R.id.vp_display);
        try {
            isJump = getIntent().getStringExtra("isJump");
        } catch (Exception e) {

        }
        top_contact_bar.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == top_contact_bar.getBackView()) {
                    if (isJump != null) {
                        Intent intent = new Intent(MyEarningsActivity.this, ProMainActivity.class);
                        intent.putExtra("skill", "123");
                        startActivity(intent);
                        finish();
                    } else {
                        finish();
                    }
                }
            }
        });

    }

    private void initData() {
        fragments = new ArrayList<>();

        if (fragments != null && fragments.size() > 0) {
            fragments.clear();
        }
        shouruFragment = new ShouruFragment();
        fragments.add(shouruFragment);
        shoukuanxinxiFragment = new ShoukuanxinxiFragment();
        fragments.add(shoukuanxinxiFragment);

        vpStatus.setOffscreenPageLimit(2);
        manager = getSupportFragmentManager();
        adapter = new EarningAdapter(manager, fragments);
        vpStatus.setAdapter(adapter);
        tl_display.setupWithViewPager(vpStatus);

        tl_display.getTabAt(0).setText(getResources().getString(R.string.MyEarning));
        tl_display.getTabAt(1).setText(getResources().getString(R.string.AccountInformation));
        new TabLayoutUtils().reflex(tl_display);
        tl_display.setTabMode(TabLayout.MODE_FIXED);
        if (isJump != null) {
            vpStatus.setCurrentItem(1);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isJump != null) {
                Intent intent = new Intent(MyEarningsActivity.this, ProMainActivity.class);
                intent.putExtra("skill", "123");
                startActivity(intent);
                finish();
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
