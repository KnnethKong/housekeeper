package com.haiwai.housekeeper.activity.server;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.MyPagerAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.entity.ImageItem;
import com.haiwai.housekeeper.utils.BimpUtils;
import com.haiwai.housekeeper.view.FlowViewPager;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class PicFeadActivity extends BaseProActivity {
    private TopViewNormalBar top_pic_show_bar;
    private FlowViewPager fv_pager;
    MyPagerAdapter adapter;
    // 当前的位置
    private ArrayList<View> listViews = new ArrayList<>();
    private int location;
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_show);
        top_pic_show_bar = (TopViewNormalBar) findViewById(R.id.top_pic_show_bar);
        top_pic_show_bar.setTitle(getString(R.string.pic_show));
        top_pic_show_bar.setOnBackListener(mOnclickListener);
        initData();
        initView();
    }
    private ArrayList<String> imgList  = new ArrayList<>();
    private void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        imgList = getIntent().getStringArrayListExtra("imagePath");
        for (int i = 0; i < imgList.size(); i++) {
            initListViews(imgList.get(i));
        }
    }

    private void initView() {
        fv_pager = (FlowViewPager) findViewById(R.id.fv_pager);
        fv_pager.addOnPageChangeListener(pageChangeListener);
        adapter = new MyPagerAdapter(listViews);
        fv_pager.setAdapter(adapter);
        Intent intent = getIntent();
        int id = intent.getIntExtra("ID", 0);
        fv_pager.setCurrentItem(id);
    }

    private void initListViews(String url) {
        ImageView img = new ImageView(this);
        img.setBackgroundColor(0xff000000);
        ImageLoader.getInstance().displayImage(url,img);
//        img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
        listViews.add(img);
    }

    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            location = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };


    View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == top_pic_show_bar.getBackView()) {
                finish();
            }
        }
    };


}
