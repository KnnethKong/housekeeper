package com.haiwai.housekeeper.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
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
public class View_FWBJ_9 extends LinearLayout {
    private TextView tvtitle;
    private EditText et_yes;
    private RadioGroup radioGroup;
    private String answer = "";
    private RadioButton rb_no, rb_yes;

    public View_FWBJ_9(Context context) {
        this(context, null);
    }

    public View_FWBJ_9(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_FWBJ_9(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.issue_require_view_fwbj_9, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_fwbj_9_tv_title);
        radioGroup = (RadioGroup) findViewById(R.id.view_fwbj_9_rg);
        rb_no = (RadioButton) findViewById(R.id.view_fwbj_9_rb_no);
        rb_yes = (RadioButton) findViewById(R.id.view_fwbj_9_rb_yes);
        et_yes = (EditText) findViewById(R.id.view_fwbj_9_et);
        et_yes.setVisibility(GONE);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rb_no.getId()) {
                    et_yes.setVisibility(GONE);
                } else if (checkedId == rb_yes.getId()) {
                    et_yes.setVisibility(VISIBLE);
                }
            }
        });
    }

    public void setData(String title, String r1, String r2, String r3) {
        tvtitle.setText(title);
        rb_no.setText(r1);
        rb_yes.setText(r2);
        et_yes.setHint(r3);
    }

    public void setData(String title, String r1, String r2, String r3, String answer) {
        tvtitle.setText(title);
        rb_no.setText(r1);
        rb_yes.setText(r2);
        et_yes.setHint(r3);
        String[] strs = answer.split("\\|");
        switch (strs[0]) {
            case "1":
                rb_no.setChecked(true);
                break;
            case "2":
                rb_yes.setChecked(true);
                et_yes.setText(strs[1].substring(3, strs[1].length()));
                break;
        }
    }

    public String getQuestion() {
        return tvtitle.getText().toString().trim();
    }

    public boolean getIsEmpty() {
        if(!rb_yes.isChecked()&&!rb_no.isChecked()){
            return false;
        }

        if (radioGroup.getCheckedRadioButtonId() == rb_yes.getId()) {
            if (PlatUtils.getEditStr(et_yes)) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public String getAnswer() {
        if (radioGroup.getCheckedRadioButtonId() == rb_no.getId()) {
            answer = "1|" + rb_no.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb_yes.getId()) {
            answer = "2|str" + et_yes.getText().toString().trim();
        }
        return answer;
    }
}
