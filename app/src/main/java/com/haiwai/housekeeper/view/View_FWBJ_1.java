package com.haiwai.housekeeper.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.utils.EvmUtil;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ihope007 on 2016/10/18.
 */
public class View_FWBJ_1 extends LinearLayout {
    private TextView tvtitle;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3, rb4,rb5;
    private EditText etother;
    private String answer = "";

    public View_FWBJ_1(Context context) {
        this(context, null);
    }

    public View_FWBJ_1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_FWBJ_1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_fwbj_1, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_fwbj_1_tv_title);
        radioGroup = (RadioGroup) findViewById(R.id.view_fwbj_1_rg);
        rb1 = (RadioButton) findViewById(R.id.view_fwbj_1_rb_1);
        rb2 = (RadioButton) findViewById(R.id.view_fwbj_1_rb_2);
        rb3 = (RadioButton) findViewById(R.id.view_fwbj_1_rb_3);
        rb4 = (RadioButton) findViewById(R.id.view_fwbj_1_rb_4);
        rb5 = ((RadioButton) findViewById(R.id.view_fwbj_1_rb_5));
        etother = (EditText) findViewById(R.id.view_fwbj_1_et_other);
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb5.setChecked(true);
                return false;
            }
        });
    }

    public void setData(String title, String r1, String r2, String r3, String r4,String r5) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
//        rb5.setText(r5);
        etother.setHint(r5);
    }

    public void setData(String title, String r1, String r2, String r3, String r4,String r5, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
//        rb5.setText(r5);
        etother.setHint(r5);
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
            case "5":
                rb5.setChecked(true);
                etother.setText(answer.substring(5,answer.length()));
                break;
        }

    }

    public void setData1(String title, String r1, String r2, String r3, String r4) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
//        rb5.setText(r5);
        rb5.setVisibility(View.GONE);
        findViewById(R.id.line3).setVisibility(View.GONE);
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb4.setChecked(true);
                return false;
            }
        });
    }

    public void setData1(String title, String r1, String r2, String r3, String r4,String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
//        rb5.setText(r5);
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb4.setChecked(true);
                return false;
            }
        });
        rb5.setVisibility(View.GONE);
        findViewById(R.id.line3).setVisibility(View.GONE);
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
                etother.setText(answer.substring(5,answer.length()));
                break;
        }

    }
    public void setData2(String title, String r1, String r2, String r3, String r4) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        findViewById(R.id.line35).setVisibility(View.GONE);

        rb4.setVisibility(View.GONE);
//        rb5.setText(r5);
        etother.setVisibility(View.GONE);
        rb5.setVisibility(View.GONE);
        findViewById(R.id.line3).setVisibility(View.GONE);
    }

    public void setData2(String title, String r1, String r2, String r3, String r4,String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb4.setVisibility(View.GONE);
        findViewById(R.id.line35).setVisibility(View.GONE);
        etother.setVisibility(View.GONE);
//        rb5.setText(r5);
        rb5.setVisibility(View.GONE);
        findViewById(R.id.line3).setVisibility(View.GONE);
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


    public boolean getIsEmpty() {
        if(!rb1.isChecked()&&!rb2.isChecked()&&!rb3.isChecked()&&!rb4.isChecked()&&!rb5.isChecked()){
            return false;
        }
        if (radioGroup.getCheckedRadioButtonId() == rb5.getId()) {
            if (PlatUtils.getEditStr(etother)) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
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
        }else if(radioGroup.getCheckedRadioButtonId()==rb5.getId()){
            if(TextUtils.isEmpty(etother.getText().toString())){
                return "";
            }
            answer = "5|str" +etother.getText().toString().trim();
        }
        return answer;
    }

    public String getAnswer2() {
        if (radioGroup.getCheckedRadioButtonId() == rb1.getId()) {
            answer = "1|" + rb1.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb2.getId()) {
            answer = "2|" + rb2.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb3.getId()) {
            answer = "3|" + rb3.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb4.getId()) {
//            answer = "4|str" + rb4.getText().toString().trim();
//            if(TextUtils.isEmpty(etother.getText().toString())){
//                return "";
//            }
            answer = "4|str" + etother.getText().toString().trim();
        }
        else if(radioGroup.getCheckedRadioButtonId()==rb5.getId()){
            if(TextUtils.isEmpty(etother.getText().toString())){
                return "";
            }
            answer = "5|str" +etother.getText().toString().trim();
        }
        return answer;
    }
}
