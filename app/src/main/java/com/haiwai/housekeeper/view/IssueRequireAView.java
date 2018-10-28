package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope007 on 2016/10/18.
 */
public class IssueRequireAView extends LinearLayout {
    private TextView tvtitle;
    private TextView tvxuantian;
    private RadioGroup radioGroup;
    public IssueRequireAView(Context context) {
        this(context,null);
    }

    public IssueRequireAView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IssueRequireAView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.issue_require_view_b, this);
        initView();
    }

    private void initView() {
        tvtitle= (TextView) findViewById(R.id.issue_require_view_b_tv_title);
        tvxuantian= (TextView) findViewById(R.id.issue_require_view_b_tv_xuantian);
        radioGroup= (RadioGroup) findViewById(R.id.issue_require_view_b_rg);
    }

    public String getTitleString(){
        return tvtitle.getText().toString().trim();
    }
    public void setStrText(String str){
        tvtitle.setText(str);
    }
    public RadioGroup getRadioGroup(){
        return radioGroup;
    }
    public void setXuanTianVisible(){
        tvxuantian.setVisibility(VISIBLE);
    }

}
