package com.haiwai.housekeeper.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.MyApp;

/**
 * Created by ihope10 on 2016/10/31.
 */

public class TvOrderView extends LinearLayout {
    private TextView tvWen, tvDa;
    public LinearLayout ll_img_layout;

    public TvOrderView(Context context) {
        this(context, null);
    }

    public TvOrderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TvOrderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.order_type_a_layout, this);
        initView();
    }

    private void initView() {
        tvWen = (TextView) findViewById(R.id.tv_order_wen);
        tvDa = (TextView) findViewById(R.id.tv_order_da);

        ll_img_layout = (LinearLayout) findViewById(R.id.ll_img_layout);
    }

    public void setViewText(String wenStr, String daStr) {
        tvWen.setText(wenStr);
        tvDa.setText(daStr);

    }

    public void setLayoutVisible(boolean b) {
        if (b) {
            tvDa.setVisibility(View.GONE);
            ll_img_layout.setVisibility(View.VISIBLE);
        } else{
            tvDa.setVisibility(View.VISIBLE);
            ll_img_layout.setVisibility(View.GONE);
        }
    }

    public LinearLayout getImgLayout() {
        return ll_img_layout;
    }

    public void setTvWenText(String str) {
        tvWen.setText(str);
    }

    public void setTvDaText(String str) {
        tvDa.setText(str);
    }
}
