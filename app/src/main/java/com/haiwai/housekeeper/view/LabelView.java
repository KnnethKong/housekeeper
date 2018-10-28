package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope007 on 2016/11/15.
 */
public class LabelView extends LinearLayout{
    private TextView tv_label;

    public LabelView(Context context) {
        this(context,null);
    }

    public LabelView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.pro_detail_comment_label_view,this);
        initView();
    }

    private void initView() {
        tv_label= (TextView) findViewById(R.id.labelview_tv);
    }

    public void setContent(String str){
        tv_label.setText(str);
    }
}
