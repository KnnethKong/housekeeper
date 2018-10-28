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
import android.widget.CheckBox;
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
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ihope007 on 2016/10/18.
 */
public class View_GLWX_12 extends LinearLayout {
    private TextView tvtitle;
    private RadioGroup radiogroup;
    private RadioButton rb1, rb2;
    private LinearLayout ll2;
    private EditText etemail, etphone;
    private CheckBox checkBox;

    public View_GLWX_12(Context context) {
        this(context, null);
    }

    public View_GLWX_12(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_GLWX_12(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_glwx_12, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_glwx_12_tv_title);
        radiogroup = (RadioGroup) findViewById(R.id.view_glwx_12_rg);
        rb1 = (RadioButton) findViewById(R.id.view_glwx_12_rb_1);
        rb2 = (RadioButton) findViewById(R.id.view_glwx_12_rb_2);
        etemail = (EditText) findViewById(R.id.view_glwx_12_et_email);
        etphone = (EditText) findViewById(R.id.view_glwx_12_et_phone);
        checkBox = (CheckBox) findViewById(R.id.view_glwx_12_cb);
        ll2 = (LinearLayout) findViewById(R.id.view_glwx_12_ll);
        ll2.setVisibility(INVISIBLE);
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rb2.getId()) {
                    ll2.setVisibility(VISIBLE);
                } else {
                    ll2.setVisibility(INVISIBLE);
                }
            }
        });

    }

    public void setData(String title, String r1, String r2) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
    }

    public String getQuestion() {
        return tvtitle.getText().toString().trim();
    }

    public boolean getIsEmpty() {
        if (PlatUtils.getEditStr(etemail)) {
            return true;
        }
        if (radiogroup.getCheckedRadioButtonId() == rb2.getId()) {
            if (PlatUtils.getEditStr(etphone)) {
                return true;
            }
        }
        return false;
    }

    public String getAnswer() {
        String answer="";
        if (radiogroup.getCheckedRadioButtonId() == rb1.getId()){
            if (checkBox.isChecked()) {
                answer = "11|" + etemail.getText().toString();
            }else{
                answer = "10|" + etemail.getText().toString();
            }
        }else if(radiogroup.getCheckedRadioButtonId() == rb2.getId()){
            if (checkBox.isChecked()) {
                answer = "01|" + etemail.getText().toString()+"|"+etphone.getText().toString();
            }else{
                answer = "00|" + etemail.getText().toString()+"|"+etphone.getText().toString();
            }
        }
        return answer;
    }
}
