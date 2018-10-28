package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope006 on 2016/12/9.
 */

public class OutlineSimpleView extends LinearLayout {
    private TextView tv_out_s_title, tv_out_s_content;

    public OutlineSimpleView(Context context) {
        this(context, null);
    }

    public OutlineSimpleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OutlineSimpleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.outline_simple_view_layout, this);
        initView();
    }

    private void initView() {
        tv_out_s_title = (TextView) findViewById(R.id.tv_out_s_title);
        tv_out_s_content = (TextView) findViewById(R.id.tv_out_s_content);
    }

    public void setTitle(String title) {
        tv_out_s_title.setText(title);
    }

    public void setContent(String cont) {
        tv_out_s_content.setText(cont);
    }

    public String getTitle(){
        return tv_out_s_title.getText().toString().trim();
    }
}
