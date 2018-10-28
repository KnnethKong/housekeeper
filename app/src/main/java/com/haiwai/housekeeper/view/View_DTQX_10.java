package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope007 on 2016/10/18.
 */
public class View_DTQX_10 extends LinearLayout {
    private TextView tvtitle;
    private CheckBox cb1, cb2,cb3;
    private String answer = "";

    public View_DTQX_10(Context context) {
        this(context, null);
    }

    public View_DTQX_10(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_DTQX_10(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_dtqx_10, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_dtqx_10_tv_title);
        cb1 = (CheckBox) findViewById(R.id.view_dtqx_10_cb_1);
        cb2 = (CheckBox) findViewById(R.id.view_dtqx_10_cb_2);
        cb3 = (CheckBox) findViewById(R.id.view_dtqx_10_cb_3);
    }

    public void setData(String title, String r1, String r2) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
    }

    public void setData(String title, String r1, String r2, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        if ("1".equals(answer.substring(0, 1))) {
            cb1.setChecked(true);
        }
        if ("1".equals(answer.substring(1, 2))) {
            cb2.setChecked(true);
        }
    }


    public void setData1(String title, String r1, String r2,String r3) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        findViewById(R.id.line28).setVisibility(View.VISIBLE);
        cb3.setVisibility(View.VISIBLE);
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    cb3.setChecked(false);
                    cb2.setChecked(false);
                }
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    cb1.setChecked(false);
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    cb1.setChecked(false);
                }
            }
        });
    }

    public void setData1(String title, String r1, String r2,String r3, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        findViewById(R.id.line28).setVisibility(View.VISIBLE);
        cb3.setVisibility(View.VISIBLE);
        if ("1".equals(answer.substring(0, 1))) {
            cb1.setChecked(true);
        }
        if ("1".equals(answer.substring(1, 2))) {
            cb2.setChecked(true);
        }
        if("1".equals(answer.substring(2, 3))){
            cb3.setChecked(true);
        }
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    cb3.setChecked(false);
                    cb2.setChecked(false);
                }
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    cb1.setChecked(false);
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    cb1.setChecked(false);
                }
            }
        });
    }


    public int booleanToInt(boolean a) {
        int i = a == true ? 1 : 0;
        return i;
    }

    public boolean hasSelected(){
        if(cb1.isChecked()||cb2.isChecked()||cb3.isChecked()){
            return true;
        }else{
            return false;
        }
    }

    public String getQuestion() {
        return tvtitle.getText().toString().trim();
    }

    public String getAnswer() {
        answer = "" + booleanToInt(cb1.isChecked()) + booleanToInt(cb2.isChecked());
        return answer;
    }
    public String getAnswer1() {
        answer = "" + booleanToInt(cb1.isChecked()) + booleanToInt(cb2.isChecked())+ booleanToInt(cb3.isChecked());
        return answer;
    }
}
