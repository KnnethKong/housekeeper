package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by ihope007 on 2016/8/24.
 */
public class MsgListView  extends android.widget.ListView{
    public MsgListView(Context context) {
        super(context);
    }

    public MsgListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MsgListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }
}
