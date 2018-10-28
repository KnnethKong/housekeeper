package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope10 on 2016/10/25.
 */

public class SkillSetItemView extends LinearLayout {
    private  TextView tvSkillSetTile;
    private  ToggleButton tbSkillSetTitle;

    public SkillSetItemView(Context context) {
        this(context, null);
    }

    public SkillSetItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkillSetItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.skill_set_item_layout, this);
        initView();
    }

    private void initView() {
        tvSkillSetTile = (TextView) findViewById(R.id.tv_skill_content_set_title);
        tbSkillSetTitle = (ToggleButton) findViewById(R.id.tv_skill_set);
    }


    public void setLeftText(String str) {
        tvSkillSetTile.setText(str);
    }

    public ToggleButton getView() {
        return tbSkillSetTitle;
    }
}
