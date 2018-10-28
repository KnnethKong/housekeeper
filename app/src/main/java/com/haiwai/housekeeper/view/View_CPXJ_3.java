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
public class View_CPXJ_3 extends LinearLayout {
    private TextView tvtitle;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3, rb4;
    private String answer = "";

    public View_CPXJ_3(Context context) {
        this(context, null);
    }

    public View_CPXJ_3(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_CPXJ_3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_cpxj_3, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_cpxj_3_tv_title);
        radioGroup = (RadioGroup) findViewById(R.id.view_cpxj_3_rg);
        rb1 = (RadioButton) findViewById(R.id.view_cpxj_3_rb_1);
        rb2 = (RadioButton) findViewById(R.id.view_cpxj_3_rb_2);
        rb3 = (RadioButton) findViewById(R.id.view_cpxj_3_rb_3);
        rb4 = (RadioButton) findViewById(R.id.view_cpxj_3_rb_4);
    }

    public void setData(String title, String r1, String r2, String r3, String r4) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
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
            case "4":
                rb4.setChecked(true);
                break;
        }
    }

    public String getQuestion() {
        return tvtitle.getText().toString().trim();
    }


    public boolean isEmpty(){
        if(rb1.isChecked()||rb2.isChecked()||rb3.isChecked()||rb4.isChecked()){
            return true;
        }else{
            return false;
        }
    }

    public String getAnswer() {
        if (radioGroup.getCheckedRadioButtonId() == rb1.getId()) {
            answer = "1|" + rb1.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb2.getId()) {
            answer = "2|" + rb2.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb3.getId()) {
            answer = "3|" + rb3.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb4.getId()) {
            answer = "4|" + rb4.getText().toString().trim();
        }
        return answer;
    }
}
