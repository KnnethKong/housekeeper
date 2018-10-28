package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.haiwai.housekeeper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ihope007 on 2016/11/23.
 */
public class HouseManageProgress extends LinearLayout {
    private ImageView iv1, iv2, iv3, iv4;

    public HouseManageProgress(Context context) {
        this(context, null);
    }

    public HouseManageProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HouseManageProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.house_manage_progress, this);
        initView();
    }

    private void initView() {
        iv1 = (ImageView) findViewById(R.id.house_manage_iv_1);
        iv2 = (ImageView) findViewById(R.id.house_manage_iv_2);
        iv3 = (ImageView) findViewById(R.id.house_manage_iv_3);
        iv4 = (ImageView) findViewById(R.id.house_manage_iv_4);
    }

    public void setZlView(){
        findViewById(R.id.house_manage_iv_2).setVisibility(View.GONE);
        findViewById(R.id.house_manage_iv_3).setVisibility(View.GONE);
        findViewById(R.id.house_manage_iv_4).setVisibility(View.GONE);
    }
    public void setZlView1(){
        findViewById(R.id.house_manage_iv_2).setVisibility(View.GONE);
        findViewById(R.id.house_manage_iv_3).setVisibility(View.GONE);
        findViewById(R.id.house_manage_iv_4).setVisibility(View.GONE);
    }

    public void setZlPor(int i){
        if(i==1){
            iv1.setVisibility(VISIBLE);
            iv1.setImageResource(R.mipmap.house_manage_red_left);
            iv2.setVisibility(GONE);
            iv3.setVisibility(GONE);
        }else if(i==2){
            iv1.setVisibility(VISIBLE);
            iv2.setVisibility(VISIBLE);
            iv3.setVisibility(GONE);
            iv1.setImageResource(R.mipmap.house_manage_red_left);
            iv2.setImageResource(R.mipmap.house_manage_red_left);
        }else{
            iv1.setVisibility(VISIBLE);
            iv2.setVisibility(VISIBLE);
            iv3.setVisibility(VISIBLE);
            iv1.setImageResource(R.mipmap.house_manage_red_left);
            iv2.setImageResource(R.mipmap.house_manage_red_left);
            iv3.setImageResource(R.mipmap.house_manage_red_left);
        }
    }

    /**
     * 设置进度条总数
     *
     * @param i
     */
    public void setNum(int i) {
        switch (i) {
            case 1://租赁中的招租使用
                iv1.setVisibility(View.VISIBLE);
                iv2.setVisibility(View.GONE);
                iv3.setVisibility(View.GONE);
                iv4.setVisibility(View.GONE);
                break;
            case 2://房屋巡视使用或其他
                iv1.setVisibility(View.VISIBLE);
                iv2.setVisibility(View.GONE);
                iv3.setVisibility(View.GONE);
                iv4.setVisibility(View.VISIBLE);
                break;
            case 3://租赁管理使用或其他
                iv1.setVisibility(View.VISIBLE);
                iv2.setVisibility(View.GONE);
                iv3.setVisibility(View.VISIBLE);
                iv4.setVisibility(View.VISIBLE);
                break;
            case 4:
                iv1.setVisibility(View.VISIBLE);
                iv2.setVisibility(View.VISIBLE);
                iv3.setVisibility(View.VISIBLE);
                iv4.setVisibility(View.VISIBLE);
                break;
        }
    }


    public void setView4Progress(int i) {
        switch (i) {
            case 1:
                iv1.setImageResource(R.mipmap.house_manage_red_left);
                iv2.setImageResource(R.mipmap.house_manage_grey_middle);
                iv3.setImageResource(R.mipmap.house_manage_grey_middle);
                iv4.setImageResource(R.mipmap.house_manage_grey_right);
                break;
            case 2:
                iv1.setImageResource(R.mipmap.house_manage_red_left);
                iv3.setImageResource(R.mipmap.house_manage_grey_middle);
                iv4.setImageResource(R.mipmap.house_manage_grey_right);
                break;
            case 3:
                iv1.setImageResource(R.mipmap.house_manage_red_left);
                iv2.setImageResource(R.mipmap.house_manage_red_middle);
                iv3.setImageResource(R.mipmap.house_manage_red_middle);
                iv4.setImageResource(R.mipmap.house_manage_grey_right);
                break;
            case 4:
                iv1.setImageResource(R.mipmap.house_manage_red_left);
                iv2.setImageResource(R.mipmap.house_manage_red_middle);
                iv3.setImageResource(R.mipmap.house_manage_red_middle);
                iv4.setImageResource(R.mipmap.house_manage_red_right);
                break;
        }
    }

    public void setView3Progress(int i) {
        switch (i) {
            case 1:
                iv1.setImageResource(R.mipmap.house_manage_red_left);
                iv3.setImageResource(R.mipmap.house_manage_grey_middle);
                iv4.setImageResource(R.mipmap.house_manage_grey_right);
                break;
            case 2:
                iv1.setImageResource(R.mipmap.house_manage_red_left);
                iv3.setImageResource(R.mipmap.house_manage_red_middle);
                iv4.setImageResource(R.mipmap.house_manage_grey_right);
                break;
            case 3:
                iv1.setImageResource(R.mipmap.house_manage_red_left);
                iv3.setImageResource(R.mipmap.house_manage_red_middle);
                iv4.setImageResource(R.mipmap.house_manage_red_right);
                break;
        }
    }

    public void setView2Progress(int i) {
        switch (i) {
            case 1:
                iv1.setImageResource(R.mipmap.house_manage_red_left);
                iv4.setImageResource(R.mipmap.house_manage_grey_right);
                break;
            case 2:
                iv1.setImageResource(R.mipmap.house_manage_red_left);
                iv4.setImageResource(R.mipmap.house_manage_red_right);
                break;
        }
    }
}
