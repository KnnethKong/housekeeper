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
public class TranslateENView extends LinearLayout {
    private TextView tven;
    private TextView tvzh;

    public TranslateENView(Context context) {
        this(context,null);
    }

    public TranslateENView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TranslateENView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.translate_en_view, this);
        initView();
    }

    private void initView() {
        tven= (TextView) findViewById(R.id.translate_en_tv_en);
        tvzh= (TextView) findViewById(R.id.translate_en_tv_zh);
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
