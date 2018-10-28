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
public class View_CPXJ_8 extends LinearLayout {
    private TextView tvtitle;
    private CheckBox cb1, cb2, cb3,cb4,cb5;
    private String answer = "";

    public View_CPXJ_8(Context context) {
        this(context, null);
    }

    public View_CPXJ_8(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_CPXJ_8(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_cpxj_8, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_cpxj_8_tv_title);
        cb1 = (CheckBox) findViewById(R.id.view_cpxj_8_cb_1);
        cb2 = (CheckBox) findViewById(R.id.view_cpxj_8_cb_2);
        cb3 = (CheckBox) findViewById(R.id.view_cpxj_8_cb_3);
        cb4 = (CheckBox) findViewById(R.id.view_cpxj_8_cb_4);
        cb5 = (CheckBox) findViewById(R.id.view_cpxj_8_cb_5);

    }

    public void setData(String title, String r1, String r2, String r3,String r4,String r5) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb5.setText(r5);
    }

    public void setData(String title, String r1, String r2, String r3,String r4,String r5, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb5.setText(r5);
        if ("1".equals(answer.substring(0, 1))) {
            cb1.setChecked(true);
        }
        if ("1".equals(answer.substring(1, 2))) {
            cb2.setChecked(true);
        }
        if ("1".equals(answer.substring(2, 3))) {
            cb3.setChecked(true);
        }
        if ("1".equals(answer.substring(3, 4))) {
            cb4.setChecked(true);
        }
        if ("1".equals(answer.substring(4, 5))) {
            cb5.setChecked(true);
        }
    }



    public void setData1(String title, String r1, String r2, String r3) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setVisibility(View.GONE);
        cb5.setVisibility(View.GONE);
        findViewById(R.id.line1).setVisibility(View.GONE);
        findViewById(R.id.line2).setVisibility(View.GONE);
//        cb4.setText(r4);
//        cb5.setText(r5);

    }

    public void setData1(String title, String r1, String r2, String r3, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setVisibility(View.GONE);
        cb5.setVisibility(View.GONE);
        findViewById(R.id.line1).setVisibility(View.GONE);
        findViewById(R.id.line2).setVisibility(View.GONE);
//        cb4.setText(r4);
//        cb5.setText(r5);
        if ("1".equals(answer.substring(0, 1))) {
            cb1.setChecked(true);
        }
        if ("1".equals(answer.substring(1, 2))) {
            cb2.setChecked(true);
        }
        if ("1".equals(answer.substring(2, 3))) {
            cb3.setChecked(true);
        }

    }


    public void setData2(String title, String r1, String r2, String r3) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setVisibility(View.GONE);
        cb5.setVisibility(View.GONE);
        findViewById(R.id.line1).setVisibility(View.GONE);
        findViewById(R.id.line2).setVisibility(View.GONE);
//        cb4.setText(r4);
//        cb5.setText(r5);
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cb2.setChecked(false);
                    cb1.setChecked(false);
                }
            }
        });
    }

    public void setData2(String title, String r1, String r2, String r3, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setVisibility(View.GONE);
        cb5.setVisibility(View.GONE);
        findViewById(R.id.line1).setVisibility(View.GONE);
        findViewById(R.id.line2).setVisibility(View.GONE);
//        cb4.setText(r4);
//        cb5.setText(r5);
        if ("1".equals(answer.substring(0, 1))) {
            cb1.setChecked(true);
        }
        if ("1".equals(answer.substring(1, 2))) {
            cb2.setChecked(true);
        }
        if ("1".equals(answer.substring(2, 3))) {
            cb3.setChecked(true);
        }
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cb2.setChecked(false);
                    cb1.setChecked(false);
                }
            }
        });
    }



    /**
     * 0:可以通过   1:请至少选择一项   2:请补全信息
     */
    public int getIsEmptyState() {
        if (cb1.isChecked() || cb2.isChecked() || cb3.isChecked()||cb4.isChecked()||cb5.isChecked()) {
            return 0;
        } else {
            return 1;
        }
    }

    public int booleanToInt(boolean a) {
        int i = a == true ? 1 : 0;
        return i;
    }

    public String getQuestion() {
        return tvtitle.getText().toString().trim();
    }

    public String getAnswer() {
        answer = ""
                + booleanToInt(cb1.isChecked())
                + booleanToInt(cb2.isChecked())
                + booleanToInt(cb3.isChecked())
                + booleanToInt(cb4.isChecked())
                + booleanToInt(cb5.isChecked());
        return answer;
    }
}
