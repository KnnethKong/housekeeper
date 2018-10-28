package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
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
public class View_JJYC_3 extends LinearLayout {
    private TextView tvtitle;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3, rb4;
    private EditText etother;
    private String answer = "";

    public View_JJYC_3(Context context) {
        this(context, null);
    }

    public View_JJYC_3(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_JJYC_3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_jjyc_3, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_jjyc_3_tv_title);
        radioGroup = (RadioGroup) findViewById(R.id.view_jjyc_3_rg);
        rb1 = (RadioButton) findViewById(R.id.view_jjyc_3_rb_1);
        rb2 = (RadioButton) findViewById(R.id.view_jjyc_3_rb_2);
        rb3 = (RadioButton) findViewById(R.id.view_jjyc_3_rb_3);
        rb4 = (RadioButton) findViewById(R.id.view_jjyc_3_rb_4);
        etother = (EditText) findViewById(R.id.view_jjyc_3_et_other);
//        etother.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                rb3.setChecked(true);
//            }
//        });
//        etother.setVisibility(GONE);
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == rb3.getId()) {
//                    etother.setVisibility(VISIBLE);
//                } else {
//                    etother.setVisibility(GONE);
//                }
//            }
//        });
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb3.setChecked(true);
                return false;
            }
        });
    }

    public void setData(String title, String r1, String r2, String r3, String r4) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
//        rb3.setText(r3);
        etother.setHint(r3);
//        etother.setHint(r3);
        rb4.setText(r4);
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
//        rb3.setText(r3);
        etother.setHint(r3);
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
                etother.setText(strs[1].substring(3, strs[1].length()));
                break;
            case "4":
                rb4.setChecked(true);
                break;
        }
    }

    public boolean getIsEmpty() {
        if (radioGroup.getCheckedRadioButtonId() == rb3.getId()) {
            if (PlatUtils.getEditStr(etother)) {
                return true;
            } else {
                return false;
            }
        } else {
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
            answer = "3|str" + etother.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb4.getId()) {
            answer = "4|" + rb4.getText().toString().trim();
        }
        return answer;
    }
}
