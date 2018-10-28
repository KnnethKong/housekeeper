package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by ihope006 on 2016/12/12.
 */

public class RuleView extends LinearLayout {
    public RuleView(Context context) {
        this(context,null);
    }
    public RuleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RuleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
