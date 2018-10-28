package com.haiwai.housekeeper.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.MapBoxMapActivity;
import com.haiwai.housekeeper.utils.PlatUtils;

/**
 * Created by ihope007 on 2016/10/18.
 */
public class View_JJYC_4 extends LinearLayout {
    private TextView tvtitle;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3;
    private EditText etother;
    private String answer = "";

    public View_JJYC_4(Context context) {
        this(context, null);
    }

    public View_JJYC_4(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_JJYC_4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_jjyc_4, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_jjyc_4_tv_title);
        radioGroup = (RadioGroup) findViewById(R.id.view_jjyc_4_rg);
        rb1 = (RadioButton) findViewById(R.id.view_jjyc_4_rb_1);
        rb2 = (RadioButton) findViewById(R.id.view_jjyc_4_rb_2);
        rb3 = (RadioButton) findViewById(R.id.view_jjyc_4_rb_3);
        etother = (EditText) findViewById(R.id.view_jjyc_4_et_other);
//        etother.setVisibility(GONE);
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == rb2.getId()) {
//                    etother.setVisibility(VISIBLE);
////                    getContext().startActivity(new Intent(getContext(), MapBoxMapActivity.class));
//                } else {
//                    etother.setVisibility(GONE);
//                }
//            }
//        });
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
                etother.setText(strs[1].substring(3, strs[1].length()));
                break;
            case "3":
                rb3.setChecked(true);
                break;
        }
    }


    public void setData1(String title, String r1, String r2) {
        tvtitle.setText(title);
        rb1.setText(r1);
//        rb2.setText(r2);
        etother.setHint(r2);
        etother.setVisibility(View.VISIBLE);
        rb3.setVisibility(View.GONE);
//        etother.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                rb2.setChecked(true);
//            }
//        });
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb2.setChecked(true);
                return false;
            }
        });
    }

    public void setData1(String title, String r1, String r2, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
//        rb2.setText(r2);
        etother.setHint(r2);
        etother.setVisibility(View.VISIBLE);
        rb3.setVisibility(View.GONE);;
        String[] strs = answer.split("\\|");
        switch (strs[0]) {
            case "1":
                rb1.setChecked(true);
                break;
            case "2":
                rb2.setChecked(true);
                etother.setText(strs[1].substring(3, strs[1].length()));
                break;
            case "3":
                rb3.setChecked(true);
                break;
        }
//        etother.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                rb2.setChecked(true);
//            }
//        });
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb2.setChecked(true);
                return false;
            }
        });
    }




    public boolean getIsEmpty() {
        if (radioGroup.getCheckedRadioButtonId() == rb2.getId()) {
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
            answer = "2|str" + etother.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb3.getId()) {
            answer = "3|" + rb3.getText().toString().trim();
        }
        return answer;
    }
}
