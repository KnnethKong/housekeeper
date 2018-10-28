package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope007 on 2016/10/18.
 */
public class View_DTQX_11 extends LinearLayout {
    private TextView tvtitle;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3;
    private String answer = "";

    public View_DTQX_11(Context context) {
        this(context, null);
    }

    public View_DTQX_11(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_DTQX_11(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_dtqx_11, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_dtqx_11_tv_title);
        radioGroup = (RadioGroup) findViewById(R.id.view_dtqx_11_rg);
        rb1 = (RadioButton) findViewById(R.id.view_dtqx_11_rb_1);
        rb2 = (RadioButton) findViewById(R.id.view_dtqx_11_rb_2);
        rb3 = (RadioButton) findViewById(R.id.view_dtqx_11_rb_3);
    }

    public void setData(String title, String r1, String r2, String r3) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
    }

    public void setData(String title, String r1, String r2, String r3, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        String[] strs = answer.split("\\|");
        switch (strs[0]) {
            case "1":
                rb1.setChecked(true);
                break;
            case "2":
                rb2.setChecked(true);
                break;
            case "3":
                rb3.setChecked(true);
                break;
        }
    }

    public boolean isEmpty(){
        if(rb3.isChecked()||rb2.isChecked()||rb1.isChecked()){
            return true;
        }else{
            return false;
        }
    }

    public String getQuestion() {
        return tvtitle.getText().toString().trim();
    }

    public String getAnswer() {
        if (radioGroup.getCheckedRadioButtonId() == rb1.getId()) {
            answer = "1|" + rb1.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb2.getId()) {
            answer = "2|" + rb2.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb3.getId()) {
            answer = "3|" + rb3.getText().toString().trim();
        }
        return answer;
    }
}
