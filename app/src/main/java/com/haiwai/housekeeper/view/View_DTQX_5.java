package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.ToastUtil;

/**
 * Created by ihope007 on 2016/10/18.
 */
public class View_DTQX_5 extends LinearLayout {
    private TextView tvtitle;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7;
    private EditText etother;
    private String answer = "";

    public View_DTQX_5(Context context) {
        this(context, null);
    }

    public View_DTQX_5(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_DTQX_5(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_dtqx_5, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_dtqx_5_tv_title);
        radioGroup = (RadioGroup) findViewById(R.id.view_dtqx_5_rg);
        rb1 = (RadioButton) findViewById(R.id.view_dtqx_5_rb_1);
        rb2 = (RadioButton) findViewById(R.id.view_dtqx_5_rb_2);
        rb3 = (RadioButton) findViewById(R.id.view_dtqx_5_rb_3);
        rb4 = (RadioButton) findViewById(R.id.view_dtqx_5_rb_4);
        rb5 = (RadioButton) findViewById(R.id.view_dtqx_5_rb_5);

        rb6 = (RadioButton) findViewById(R.id.view_dtqx_5_rb_6);
        rb7 = (RadioButton) findViewById(R.id.view_dtqx_5_rb_7);

        etother = (EditText) findViewById(R.id.view_dtqx_5_et_other);
//        etother.setVisibility(INVISIBLE);
//        etother.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                rb7.setChecked(true);
//            }
//        });

        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb7.setChecked(true);
                return false;
            }
        });

    }

    public void setData1(String title, String r1, String r2, String r3, String r4, String r5, String r6, String r7) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
        rb6.setText(r6);
//        rb7.setText(r7);
        etother.setHint(r7);
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb7.setChecked(true);
                return false;
            }
        });

//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == rb7.getId()) {
//                    etother.setVisibility(VISIBLE);
//                } else {
//                    etother.setVisibility(INVISIBLE);
//                }
//            }
//        });
    }

    public void setData1(String title, String r1, String r2, String r3, String r4, String r5, String r6, String r7, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
        rb6.setText(r6);
//        rb7.setText(r7);
        etother.setHint(r7);
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb7.setChecked(true);
                return false;
            }
        });

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
                break;
            case "6":
                rb5.setChecked(true);
                break;
            case "7":
                rb7.setChecked(true);
                if (strs[1].contains("平方")) {
                    etother.setText(strs[1].substring(3, strs[1].length() - 4));
                } else if (strs[1].contains("sq")) {
                    etother.setText(strs[1].substring(3, strs[1].length() - 5));
                } else {
                    etother.setText(strs[1].substring(3, strs[1].length()));
                }

                break;
        }
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == rb7.getId()) {
//                    etother.setVisibility(VISIBLE);
//                } else {
//                    etother.setVisibility(INVISIBLE);
//                }
//            }
//        });
    }


    public void setData(String title, String r1, String r2, String r3, String r4, String r5) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
//        rb5.setText(r5);
        rb6.setVisibility(View.GONE);
        rb7.setVisibility(View.GONE);
        findViewById(R.id.line4).setVisibility(View.GONE);
        findViewById(R.id.line5).setVisibility(View.GONE);
        etother.setHint(r5);
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb5.setChecked(true);
                return false;
            }
        });

    }

    public void setData(String title, String r1, String r2, String r3, String r4, String r5, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb5.setChecked(true);
                return false;
            }
        });

//        rb5.setText(r5);
        rb6.setVisibility(View.GONE);
        rb7.setVisibility(View.GONE);
        etother.setHint(r5);
        findViewById(R.id.line4).setVisibility(View.GONE);
        findViewById(R.id.line5).setVisibility(View.GONE);
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
                etother.setText(strs[1].substring(3, strs[1].length()));
                break;
        }
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == rb5.getId()) {
//                    etother.setVisibility(VISIBLE);
//                } else {
//                    etother.setVisibility(INVISIBLE);
//                }
//            }
//        });
    }

    public boolean getIsEmpty() {
        if (!rb6.isChecked() && !rb1.isChecked() && !rb2.isChecked() && !rb3.isChecked() && !rb4.isChecked() && !rb5.isChecked() && !rb7.isChecked()) {
            return false;
        }

        if (rb5.isChecked() || rb7.isChecked()) {
            if (PlatUtils.getEditStr(etother)) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public boolean getIsEmpty1() {
        if (!rb1.isChecked() && !rb2.isChecked() && !rb3.isChecked() && !rb4.isChecked() && !rb5.isChecked() && !rb6.isChecked() && !rb7.isChecked()) {
            return false;
        }

        if (radioGroup.getCheckedRadioButtonId() == rb7.getId()) {
            if (PlatUtils.getEditStr(etother)) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public String getQuestion() {
        return tvtitle.getText().toString().trim();
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
        } else if (radioGroup.getCheckedRadioButtonId() == rb5.getId()) {
            answer = "5|str" + etother.getText().toString().trim();
        }
        return answer;
    }

    public String getAnswer1() {
        if (radioGroup.getCheckedRadioButtonId() == rb1.getId()) {
            answer = "1|" + rb1.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb2.getId()) {
            answer = "2|" + rb2.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb3.getId()) {
            answer = "3|" + rb3.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb4.getId()) {
            answer = "4|" + rb4.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb5.getId()) {
            answer = "5|str" + rb5.getText().toString();
        } else if (radioGroup.getCheckedRadioButtonId() == rb6.getId()) {
            answer = "6|" + rb6.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb7.getId()) {
            answer = "7|str" + etother.getText().toString();
        }
        return answer;
    }
}
