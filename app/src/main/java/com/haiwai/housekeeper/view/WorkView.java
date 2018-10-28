package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope10 on 2016/10/14.
 */

public class WorkView extends LinearLayout {
    public TextView tvWorTime;
    public TextView tvWorSch;
    public TextView ibWorState;
    public WorkView(Context context) {
        this(context,null);
    }

    public WorkView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WorkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.skill_detail_work_layout_item,this);
        initView();
    }

    private void initView() {
        tvWorTime = (TextView) findViewById(R.id.tv_work_time);
        tvWorSch = (TextView) findViewById(R.id.tv_work_gsm);
        ibWorState = (TextView) findViewById(R.id.ib_work_state);
    }

    public void setWorTime(String str){
        tvWorTime.setText(str);
    }

    public void setWorSch(String str){
        tvWorSch.setText(str);
    }

    public void setWorState(String str){
        ibWorState.setText(str);
    }


}
