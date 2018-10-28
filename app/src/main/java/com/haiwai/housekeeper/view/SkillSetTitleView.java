package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.haiwai.housekeeper.R;

import java.util.List;

/**
 * Created by ihope10 on 2016/10/25.
 */

public class SkillSetTitleView extends LinearLayout {
    public static TextView tv_skill_title;


    public SkillSetTitleView(Context context) {
        this(context, null);
    }

    public SkillSetTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkillSetTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.skill_set_title_layout, this);
        initView();
    }

    private void initView() {
        tv_skill_title = (TextView) findViewById(R.id.tv_skill_title);
    }

    public void setTitleText(String str) {
        tv_skill_title.setText(str);
    }

}
