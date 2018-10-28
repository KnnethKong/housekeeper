package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.utils.PlatUtils;

/**
 * Created by ihope007 on 2016/10/18.
 */
public class View_JSJL_11 extends LinearLayout {
    private TextView tvtitle;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3, rb4, rb5;
    private EditText etother4, etother5;
    private String answer = "";

    public View_JSJL_11(Context context) {
        this(context, null);
    }

    public View_JSJL_11(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_JSJL_11(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_jsjl_11, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_jsjl_11_tv_title);
        radioGroup = (RadioGroup) findViewById(R.id.view_jsjl_11_rg);
        rb1 = (RadioButton) findViewById(R.id.view_jsjl_11_rb_1);
        rb2 = (RadioButton) findViewById(R.id.view_jsjl_11_rb_2);
        rb3 = (RadioButton) findViewById(R.id.view_jsjl_11_rb_3);
        rb4 = (RadioButton) findViewById(R.id.view_jsjl_11_rb_4);
        rb5 = (RadioButton) findViewById(R.id.view_jsjl_11_rb_5);
        etother4 = (EditText) findViewById(R.id.view_jsjl_11_et_other4);
        etother5 = (EditText) findViewById(R.id.view_jsjl_11_et_other5);
        etother4.setVisibility(GONE);
        etother5.setVisibility(INVISIBLE);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rb4.getId()) {
                    etother4.setVisibility(VISIBLE);
                    etother5.setVisibility(INVISIBLE);
                } else if (checkedId == rb5.getId()) {
                    etother4.setVisibility(GONE);
                    etother5.setVisibility(VISIBLE);
                } else {
                    etother4.setVisibility(GONE);
                    etother5.setVisibility(INVISIBLE);
                }
            }
        });
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String r5) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String r5, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
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
                etother4.setText(strs[1].substring(3, strs[1].length()));
                break;
            case "5":
                rb5.setChecked(true);
                etother5.setText(strs[1].substring(3, strs[1].length()));
                break;
        }
    }

    public String getQuestion() {
        return tvtitle.getText().toString().trim();
    }

    public boolean getIsEmpty() {
        if (radioGroup.getCheckedRadioButtonId() == rb4.getId()) {
            if (PlatUtils.getEditStr(etother4)) {
                return true;
            } else {
                return false;
            }
        } else if (radioGroup.getCheckedRadioButtonId() == rb5.getId()) {
            if (PlatUtils.getEditStr(etother5)) {
                return true;
            } else {
                return false;
            }
        } else {
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
            answer = "4|str" + etother4.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb5.getId()) {
            answer = "5|str" + etother5.getText().toString().trim();
        }
        return answer;
    }
}
