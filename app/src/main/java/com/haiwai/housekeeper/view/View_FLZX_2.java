package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.utils.PlatUtils;

/**
 * Created by ihope007 on 2016/10/18.
 */
public class View_FLZX_2 extends LinearLayout {
    private TextView tvtitle;
    private CheckBox cb1, cb2, cb3;
    private String answer = "";
    private EditText etother;

    public View_FLZX_2(Context context) {
        this(context, null);
    }

    public View_FLZX_2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_FLZX_2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_flzx_2, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_flzx_2_tv_title);
        etother = (EditText) findViewById(R.id.view_flzx_2_et_other);
        cb1 = (CheckBox) findViewById(R.id.view_flzx_2_cb_1);
        cb2 = (CheckBox) findViewById(R.id.view_flzx_2_cb_2);
        cb3 = (CheckBox) findViewById(R.id.view_flzx_2_cb_3);
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                cb3.setChecked(true);
                return false;
            }
        });
    }

    public void setData(String title, String r1, String r2, String r3) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        etother.setHint(r3);
    }

    public void setData(String title, String r1, String r2, String r3, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        etother.setHint(r3);
        if ("1".equals(answer.substring(0, 1))) {
            cb1.setChecked(true);
        }
        if ("1".equals(answer.substring(1, 2))) {
            cb2.setChecked(true);
        }
        if ("1".equals(answer.substring(2, 3))) {
            cb3.setChecked(true);
            etother.setText(answer.substring(3, answer.length()));
        }
    }


    public void setData1(String title, String r1, String r2, String r3) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        etother.setHint(r3);
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    if(cb2.isChecked()){
                        cb2.setChecked(false);
                    }
                    if(cb3.isChecked()){
                        cb3.setChecked(false);
                    }
                }
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    if(cb1.isChecked()){
                        cb1.setChecked(false);
                    }
                    if(cb3.isChecked()){
                        cb3.setChecked(false);
                    }
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    if(cb2.isChecked()){
                        cb2.setChecked(false);
                    }
                    if(cb1.isChecked()){
                        cb1.setChecked(false);
                    }
                }
            }
        });
    }

    public void setData1(String title, String r1, String r2, String r3, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        etother.setHint(r3);
        if ("1".equals(answer.substring(0, 1))) {
            cb1.setChecked(true);
        }
        if ("1".equals(answer.substring(1, 2))) {
            cb2.setChecked(true);
        }
        if ("1".equals(answer.substring(2, 3))) {
            cb3.setChecked(true);
            etother.setText(answer.substring(3, answer.length()));
        }
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    if(cb2.isChecked()){
                        cb2.setChecked(false);
                    }
                    if(cb3.isChecked()){
                        cb3.setChecked(false);
                    }
                }
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    if(cb1.isChecked()){
                        cb1.setChecked(false);
                    }
                    if(cb3.isChecked()){
                        cb3.setChecked(false);
                    }
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    if(cb2.isChecked()){
                        cb2.setChecked(false);
                    }
                    if(cb1.isChecked()){
                        cb1.setChecked(false);
                    }
                }
            }
        });
    }

    public int booleanToInt(boolean a) {
        int i = a == true ? 1 : 0;
        return i;
    }

    /**
     * 0:可以通过   1:请至少选择一项   2:请补全信息
     * type:0表示必填  1表示可以跳过
     */
    public int getIsEmptyState() {
        if (cb1.isChecked() || cb2.isChecked() || cb3.isChecked()) {
            if (cb3.isChecked() && PlatUtils.getEditStr(etother)) {
                return 2;
            }
            return 0;
        } else {
            return 1;
        }
    }

    public String getQuestion() {
        return tvtitle.getText().toString().trim();
    }

    public String getAnswer() {
        if (cb3.isChecked()) {
            answer = "" + booleanToInt(cb1.isChecked()) + booleanToInt(cb2.isChecked())
                    + booleanToInt(cb3.isChecked()) + etother.getText().toString().trim();
        } else {
            answer = "" + booleanToInt(cb1.isChecked()) + booleanToInt(cb2.isChecked())
                    + booleanToInt(cb3.isChecked());
        }
        return answer;
    }
}
