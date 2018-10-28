package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope10 on 2016/10/31.
 */

public class TvMoreOrderView extends LinearLayout {
    private TextView tvWen;
    private LinearLayout tvlayout;

    public TvMoreOrderView(Context context) {
        this(context, null);
    }

    public TvMoreOrderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TvMoreOrderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.order_type_a_layout, this);
        initView();
    }

    private void initView() {
        tvWen = (TextView) findViewById(R.id.tv_order_wen);
        tvlayout = (LinearLayout) findViewById(R.id.tv_layout);
    }

    public void setWenText(String wenStrr) {
        tvWen.setText(wenStrr);
    }

    public LinearLayout getMoreDaView() {
        return tvlayout;
    }
}
