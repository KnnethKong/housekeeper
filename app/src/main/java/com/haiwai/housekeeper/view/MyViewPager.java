package com.haiwai.housekeeper.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public class MyViewPager extends ViewPager {

    private boolean scrollble = true;

//    private int mTouchSlop;
//    private float mPrevY;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
//        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    /**
     * 事件拦截
     * true 拦截子控件的事件
     * fasle 不拦截子控件的事件
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (!scrollble) {
            return false;
        }
//        switch (arg0.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mPrevY = MotionEvent.obtain(arg0).getY();
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                final float eventY = arg0.getY();
//                float xDiff = Math.abs(eventY - mPrevY);
//
//                if (xDiff < mTouchSlop) {
//                    return false;
//                }
//        }
        return super.onInterceptTouchEvent(arg0);
    }

    /**
     * 事件的处理    重写此方法,根据返回值来判断是否处理触摸事件
     * true 不处理触摸事件
     * fasle 处理触摸事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scrollble) {
            return true;
        }
        return super.onTouchEvent(ev);
    }


    public boolean isScrollble() {
        return scrollble;
    }

    public void setScrollble(boolean scrollble) {
        this.scrollble = scrollble;
    }


}  