package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

/**
 * Created by ihope006 on 2017/3/14.
 */

public class MyHorizontalScrollView extends HorizontalScrollView {

    private AddScrollListener mListener;



    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyHorizontalScrollView(Context context) {
        super(context);
    }

    public void setOnScrollListener(AddScrollListener mListener) {
        this.mListener = mListener;
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
            mListener.scrollChange(l, t, oldl, oldt);
    }



    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    public interface AddScrollListener {
        void scrollChange(int i, int j, int k, int x);
    }




}
