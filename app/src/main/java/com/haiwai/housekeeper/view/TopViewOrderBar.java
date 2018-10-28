package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;

/**
 * Created by lyj on 2016/9/13.
 */
public class TopViewOrderBar extends RelativeLayout {
    private ImageView mImageView;
    private ImageView ivCenter;
    public TopViewOrderBar(Context context) {
        this(context, null);
    }

    public TopViewOrderBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.pro_top_order_layout, this);
        mImageView = (ImageView) findViewById(R.id.iv_back_arrow);
        ivCenter = (ImageView) findViewById(R.id.iv_center);
    }

    public void setBackVisibility(boolean show) {
        mImageView.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    public void setOnBackListener(OnClickListener listener) {
        mImageView.setOnClickListener(listener);
    }


    public ImageView getBackView() {
        return mImageView;
    }



}
