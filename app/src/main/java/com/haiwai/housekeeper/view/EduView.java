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

public class EduView extends LinearLayout {
    public TextView tvEduTime;
    public TextView tvEduSch;
    public TextView ibEduState;

    public EduView(Context context) {
        this(context, null);
    }

    public EduView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EduView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.skill_detail_edu_layout_item, this);
        initView();
    }

    private void initView() {
        tvEduTime = (TextView) findViewById(R.id.tv_edu_time);
        tvEduSch = (TextView) findViewById(R.id.tv_edu_sch);
        ibEduState = (TextView) findViewById(R.id.ib_edu_state);
    }

    public void setEduTime(String str){
        tvEduTime.setText(str);
    }

    public void setEduSch(String str){
        tvEduSch.setText(str);
    }

    public void setEduState(String str){
        ibEduState.setText(str);
    }
}
