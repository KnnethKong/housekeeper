package com.haiwai.housekeeper.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope007 on 2016/11/20.
 */
public class TranslateZHView extends LinearLayout {
    private TextView tvzh;
    private TextView tven;

    public TranslateZHView(Context context) {
        this(context,null);
    }

    public TranslateZHView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TranslateZHView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.translate_zh_view, this);
        initView();
    }

    private void initView() {
        tvzh= (TextView) findViewById(R.id.translate_zh_tv_zh);
        tven= (TextView) findViewById(R.id.translate_zh_tv_en);
    }

    public void setZHText(String str){
        if (!TextUtils.isEmpty(str)){
            tvzh.setText(str);
        }
    }

    public void setENText(String str){
        if (!TextUtils.isEmpty(str)){
            tven.setText(str);
        }
    }
}
