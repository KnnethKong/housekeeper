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
public class View_JJYC_1 extends LinearLayout {
    private TextView tvtitle;
    private EditText etother;
    private String answer = "";

    public View_JJYC_1(Context context) {
        this(context, null);
    }

    public View_JJYC_1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_JJYC_1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_jjyc_1, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_jjyc_1_tv_title);
        etother = (EditText) findViewById(R.id.view_jjyc_1_et_other);
    }

    public void setData(String title, String r1) {
        tvtitle.setText(title);
        etother.setHint(r1);
    }
    public void setData(String title, String r1,String answer) {
        tvtitle.setText(title);
        etother.setText(answer);
    }

    public String getQuestion() {
        return tvtitle.getText().toString().trim();
    }

    public boolean getIsEmpty() {
        if (PlatUtils.getEditStr(etother)) {
            return true;
        } else {
            return false;
        }
    }

    public String getAnswer() {
        answer = "1|str" + etother.getText().toString();
        return answer;
    }
}
