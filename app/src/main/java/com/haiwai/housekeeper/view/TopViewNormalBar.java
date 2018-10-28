package com.haiwai.housekeeper.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.MyApp;

/**
 * Created by lyj on 2016/9/13.
 */
public class TopViewNormalBar extends RelativeLayout {
    private ImageView mImageView;
    private TextView mTextView, tv_finish;
    private ImageButton tv_img;
    public ImageButton getImgView;

    public TopViewNormalBar(Context context) {
        this(context, null);
    }

    public TopViewNormalBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.pro_top_normal_layout, this);
        mImageView = (ImageView) findViewById(R.id.iv_back_arrow);
        mTextView = (TextView) findViewById(R.id.tv_title_center);
        tv_finish = (TextView) findViewById(R.id.tv_finish);
        tv_img = (ImageButton) findViewById(R.id.tv_img);
    }

    public void setOnBackListener(OnClickListener listener) {
        mImageView.setOnClickListener(listener);
    }


    public void setTitle(String title) {
        if (title != null)
            mTextView.setTypeface(MyApp.getTf(), Typeface.NORMAL);
            mTextView.setText(title);
    }

    public void setTitle(int titleId) {
        mTextView.setText(titleId);
    }

    public void setRightText(String str) {
        tv_finish.setText(str);
    }

    public ImageView getBackView() {
        return mImageView;
    }

    public ImageButton getButtonView() {
        return tv_img;
    }

    public void setVisible(boolean flag) {
        if (flag) {
            tv_finish.setVisibility(View.VISIBLE);
        } else {
            tv_finish.setVisibility(View.GONE);
        }
    }

    public void setImgVisible(boolean flag, int img) {
        if (flag) {
            tv_img.setVisibility(View.VISIBLE);
            tv_img.setImageDrawable(getResources().getDrawable(img));
        } else {
            tv_img.setVisibility(View.GONE);
        }
    }

    public void setImgListener(OnClickListener onClickListener) {
        tv_img.setOnClickListener(onClickListener);
    }

    public void setFinishListener(OnClickListener onClickListener) {
        tv_finish.setOnClickListener(onClickListener);
    }

    public TextView getFinishTextView() {
        return tv_finish;
    }
}
