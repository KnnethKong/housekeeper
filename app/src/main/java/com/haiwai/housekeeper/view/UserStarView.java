package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope10 on 2016/11/4.
 */

public class UserStarView extends LinearLayout {
    public TextView tvBad, tvGood;

    public UserStarView(Context context) {
        this(context, null);
    }

    public UserStarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UserStarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.user_evaluate_common_star_layout, this);
        initView();
    }

    private void initView() {
        tvBad = (TextView) findViewById(R.id.tv_ev1_bad);
        tvGood = (TextView) findViewById(R.id.tv_ev2_good);
    }

    public void setViewVisible(boolean b) {
        if (b) {
            tvGood.setVisibility(View.VISIBLE);
            tvBad.setVisibility(View.GONE);
        } else {
            tvBad.setVisibility(View.VISIBLE);
            tvGood.setVisibility(View.GONE);
        }
    }

    public void setBadText(String str) {
        tvBad.setText(str);
    }

    public void setGoodText(String str) {
        tvGood.setText(str);
    }

    public TextView getTvBadorGood(int num) {
        if (num <= 3) {
            return tvBad;
        } else {
            return tvGood;
        }
    }
}
