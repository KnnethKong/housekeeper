package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope10 on 2016/9/29.
 */
public class NewWorkVew extends LinearLayout {
    public EditText etWorName, etWor;
    private EditText etStartTime, etEndTime;
    private TextView tvDelBtn;

    public NewWorkVew(Context context) {
        this(context, null);
    }

    public NewWorkVew(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewWorkVew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.pro_skill_show_third_item_layout, this);
        initView();
    }

    private void initView() {
        etWorName = (EditText) findViewById(R.id.et_work_name);
        etWor = (EditText) findViewById(R.id.et_work);
        etStartTime = (EditText) findViewById(R.id.et_start_time);
        etEndTime = (EditText) findViewById(R.id.et_end_time);
        tvDelBtn = (TextView) findViewById(R.id.tv_del_work);
    }

    public void setWorkName(String str){
        etWorName.setText(str);
    }

    public void setWoStr(String str){
        etWor.setText(str);
    }

    public String getNameString() {
        return etWorName.getText().toString().trim();
    }

    public String getSchString() {
        return etWor.getText().toString().trim();
    }

    public String getStartString() {
        return etStartTime.getText().toString().trim();
    }

    public String getEndString() {
        return etEndTime.getText().toString().trim();
    }

    public void setOnClicklister(OnClickListener mOnClickListener) {
        etStartTime.setOnClickListener(mOnClickListener);
        etEndTime.setOnClickListener(mOnClickListener);
        tvDelBtn.setOnClickListener(mOnClickListener);
    }

    public View getStarView() {
        return etStartTime;
    }

    public View getEndView() {
        return etEndTime;
    }

    public View getDelView() {
        return tvDelBtn;
    }

    public void setStaText(String str){
        etStartTime.setText(str);
    }

    public void  setEndText(String str){
        etEndTime.setText(str);
    }

}
