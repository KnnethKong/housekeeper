package com.haiwai.housekeeper.view;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.utils.PlatUtils;

/**
 * Created by ihope007 on 2016/10/18.
 */
public class View_FCMM_2 extends LinearLayout {
    private TextView tvtitle;
    private EditText etother;
    private RadioButton rb1,rb2;
    private String answer = "";

    public View_FCMM_2(Context context) {
        this(context, null);
    }

    public View_FCMM_2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_FCMM_2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_jjyc_1, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_jjyc_1_tv_title);
        etother = (EditText) findViewById(R.id.view_jjyc_1_et_other);
        rb1 = ((RadioButton) findViewById(R.id.view_fcmm_1_rb_2));
        rb2 = ((RadioButton) findViewById(R.id.view_fcmm_1_rb_1));
        etother.setInputType(InputType.TYPE_CLASS_TEXT);
    }

    public void setData(String title, String r1) {
        tvtitle.setText(title);
        etother.setHint(r1);
    }
    public void setData(String title, String r1,String answer) {
        tvtitle.setText(title);
        etother.setText(answer);

    }

    public void setData1(String title, String r1,String r2) {
        tvtitle.setText(title);
        rb1.setText(r2);
        etother.setHint(r1);
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb2.setChecked(true);
                return false;
            }
        });
    }
    public void setData1(String title, String r1,String r2,String answer) {
        tvtitle.setText(title);
        rb1.setText(r2);
        // etother.setText(answer);
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb2.setChecked(true);
                return false;
            }
        });
        String[] strs = answer.split("\\|");
        switch (strs[0]) {
            case "2":
                rb1.setChecked(true);
                break;
            case "1":
                rb2.setChecked(true);
                etother.setText(strs[1].substring(3, strs[1].length()));
                break;
        }
    }


    public String getQuestion() {
        return tvtitle.getText().toString().trim();
    }

    public boolean getIsEmpty() {

        if(!rb1.isChecked()){
            if (PlatUtils.getEditStr(etother)) {
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }

    }

    public String getAnswer() {
        if(rb1.isChecked()){
            answer = "2|str" + rb1.getText().toString();
        }else{
            answer = "1|str" + etother.getText().toString();
        }
        return answer;
    }
}
