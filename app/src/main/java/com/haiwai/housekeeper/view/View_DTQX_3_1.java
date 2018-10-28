package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.utils.PlatUtils;

/**
 * Created by ihope007 on 2016/10/18.
 */
public class View_DTQX_3_1 extends LinearLayout {
    private TextView tvtitle;
    private CheckBox rb1, rb2, rb3, rb4;
    private EditText etother;
    private String answer = "";
    private ImageView ivgl, ivsrq, ivgd;

    public View_DTQX_3_1(Context context) {
        this(context, null);
    }

    public View_DTQX_3_1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_DTQX_3_1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_dtqx_3, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_dtqx_3_tv_title);
        rb1 = (CheckBox) findViewById(R.id.view_dtqx_3_cb_1);
        rb2 = (CheckBox) findViewById(R.id.view_dtqx_3_cb_2);
        rb3 = (CheckBox) findViewById(R.id.view_dtqx_3_cb_3);
        rb4 = (CheckBox) findViewById(R.id.view_dtqx_3_cb_4);
        etother = (EditText) findViewById(R.id.view_dtqx_3_et_other);
        ivgl = (ImageView) findViewById(R.id.iv_gl);
        ivsrq = (ImageView) findViewById(R.id.iv_srq);
        ivgd = (ImageView) findViewById(R.id.iv_gd);
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb4.setChecked(true);
                return false;
            }
        });


        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rb2.setChecked(false);
                    rb3.setChecked(false);
                    rb4.setChecked(false);
                }
            }
        });
        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rb2.setChecked(false);
                    rb3.setChecked(false);
                    rb4.setChecked(false);
                }
            }
        });
        rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rb1.setChecked(false);
                    rb3.setChecked(false);
                    rb4.setChecked(false);
                }
            }
        });
        rb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rb2.setChecked(false);
                    rb1.setChecked(false);
                    rb4.setChecked(false);
                }
            }
        });
        rb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rb2.setChecked(false);
                    rb3.setChecked(false);
                    rb1.setChecked(false);
                }
            }
        });

    }

    private int select = 0;

    public void setData5(String title, String r1, String r2, String r3, String r4) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
//        etother.setHint(r4);

    }

    public void setData5(String title, String r1, String r2, String r3, String r4, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
//        etother.setHint(r4);
        if ("1".equals(answer.substring(0, 1))) {
            rb1.setChecked(true);
        }
        if ("1".equals(answer.substring(1, 2))) {
            rb2.setChecked(true);
        }
        if ("1".equals(answer.substring(2, 3))) {
            rb3.setChecked(true);
        }
//        if ("1".equals(answer.substring(3, 4))) {
//            rb4.setChecked(true);
//            etother.setText(answer.substring(4, answer.length()));
//        }

    }

    public void setData6(String title, String r1, String r2, String r3, String r4, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
//        etother.setHint(r4);
        if ("1".equals(answer.substring(0, 1))) {
            rb1.setChecked(true);
        }
        if ("2".equals(answer.substring(0, 1))) {
            rb2.setChecked(true);
        }
        if ("3".equals(answer.substring(0, 1))) {
            rb3.setChecked(true);
        }
        if("4".equals(answer.substring(0, 1))){
            rb4.setChecked(true);
            etother.setText(answer.substring(5,answer.length()));
        }
//        if ("1".equals(answer.substring(3, 4))) {
//            rb4.setChecked(true);
//            etother.setText(answer.substring(4, answer.length()));
//        }

    }

    public boolean isEmpty(){
        if(!rb1.isChecked()&&!rb2.isChecked()&&!rb3.isChecked()&&!rb4.isChecked()){
            return false;
        }else{
            return true;
        }
    }


    public int getIsEmptyState() {
        if (rb4.isChecked() && etother.getText().toString().trim().equals("")) {
            return 1;
        } else {
            return 200;
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
        if (rb1.isChecked()) {
            answer = "1|" + rb1.getText().toString().trim();
        } else if (rb2.isChecked()) {
            answer = "2|" + rb2.getText().toString().trim();
        } else if (rb3.isChecked()) {
            answer = "3|" + rb3.getText().toString().trim();
        } else {
            answer = "4|str" + etother.getText().toString().trim();
        }
        return answer;
    }
}
