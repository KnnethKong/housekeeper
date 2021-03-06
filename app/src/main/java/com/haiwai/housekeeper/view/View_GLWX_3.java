package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.utils.PlatUtils;

/**
 * Created by ihope007 on 2016/10/18.
 */
public class View_GLWX_3 extends LinearLayout {// TODO: 2016/12/7 pic change
    private TextView tvtitle;
    private CheckBox rb1, rb2, rb3, rb4;
    private EditText etother;
    private String answer = "";

    public View_GLWX_3(Context context) {
        this(context, null);
    }

    public View_GLWX_3(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_GLWX_3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_glwx_3, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_glwx_3_tv_title);
        rb1 = (CheckBox) findViewById(R.id.view_glwx_3_cb_1);
        rb2 = (CheckBox) findViewById(R.id.view_glwx_3_cb_2);
        rb3 = (CheckBox) findViewById(R.id.view_glwx_3_cb_3);
        rb4 = (CheckBox) findViewById(R.id.view_glwx_3_cb_4);
        etother = (EditText) findViewById(R.id.view_glwx_3_et_other);
//        etother.setOnFocusChangeListener(new OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if(b){
//                    rb4.setChecked(true);
//                }else{
//                    rb4.setChecked(false);
//                }
//            }
//        });
//        etother.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                rb4.setChecked(true);
//            }
//        });
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb4.setChecked(true);
                return false;
            }
        });
    }

    public void setData(String title, String r1, String r2, String r3, String r4) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
//        rb4.setText(r4);
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
//        rb4.setText(r4);
        if ("1".equals(answer.substring(0, 1))) {
            rb1.setChecked(true);
        }
        if ("1".equals(answer.substring(1, 2))) {
            rb2.setChecked(true);
        }
        if ("1".equals(answer.substring(2, 3))) {
            rb3.setChecked(true);
        }
        if ("1".equals(answer.substring(3, 4))) {
            rb4.setChecked(true);
            etother.setText(answer.substring(4, answer.length()));
        }
    }

    /**
     * 0:可以通过   1:请至少选择一项   2:请补全信息
     */
    public int getIsEmptyState() {
        if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
            if (rb4.isChecked() && PlatUtils.getEditStr(etother)) {
                return 2;
            }
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
        if (rb4.isChecked()) {
            answer = "" + booleanToInt(rb1.isChecked()) + booleanToInt(rb2.isChecked()) +
                    +booleanToInt(rb3.isChecked()) + booleanToInt(rb4.isChecked()) + etother.getText().toString().trim();
        } else {
            answer = "" + booleanToInt(rb1.isChecked()) + booleanToInt(rb2.isChecked()) +
                    +booleanToInt(rb3.isChecked()) + booleanToInt(rb4.isChecked());
        }
        return answer;
    }
}
