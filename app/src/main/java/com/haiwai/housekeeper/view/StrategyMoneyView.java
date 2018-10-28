package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope006 on 2016/11/29.
 */
public class StrategyMoneyView extends LinearLayout {
    public TextView tv_title_arrow;
    public TextView tv_content;

    public StrategyMoneyView(Context context) {
        this(context, null);
    }

    public StrategyMoneyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StrategyMoneyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.pro_money_strategy_item_layout, this);
        initView();
    }

    private void initView() {
        tv_title_arrow = (TextView) findViewById(R.id.tv_title_arrow);
        tv_content = (TextView) findViewById(R.id.tv_content);
    }


}
