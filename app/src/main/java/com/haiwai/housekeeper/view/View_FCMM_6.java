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
public class View_FCMM_6 extends LinearLayout {
    private TextView tvtitle;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10;
    private EditText etother;
    private String answer = "";

    public View_FCMM_6(Context context) {
        this(context, null);
    }

    public View_FCMM_6(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_FCMM_6(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_fcmm_6, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_fcmm_6_tv_title);
        radioGroup = (RadioGroup) findViewById(R.id.view_fcmm_6_rg);
        rb1 = (RadioButton) findViewById(R.id.view_fcmm_6_rb_1);
        rb2 = (RadioButton) findViewById(R.id.view_fcmm_6_rb_2);
        rb3 = (RadioButton) findViewById(R.id.view_fcmm_6_rb_3);
        rb4 = (RadioButton) findViewById(R.id.view_fcmm_6_rb_4);
        rb5 = (RadioButton) findViewById(R.id.view_fcmm_6_rb_5);
        rb6 = (RadioButton) findViewById(R.id.view_fcmm_6_rb_6);
        rb7 = (RadioButton) findViewById(R.id.view_fcmm_6_rb_7);
        rb8 = (RadioButton) findViewById(R.id.view_fcmm_6_rb_8);
        rb9 = (RadioButton) findViewById(R.id.view_fcmm_6_rb_9);
        rb10 = (RadioButton) findViewById(R.id.view_fcmm_6_rb_10);
        etother = (EditText) findViewById(R.id.view_fcmm_6_et_other);
//        etother.setVisibility(INVISIBLE);
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == rb10.getId()) {
//                    etother.setVisibility(VISIBLE);
//                } else {
//                    etother.setVisibility(INVISIBLE);
//                }
//            }
//        });
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String r5, String r6,
                        String r7, String r8, String r9, String r10) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
        rb6.setText(r6);
        rb7.setText(r7);
        rb8.setText(r8);
        rb9.setText(r9);
        rb10.setText(r10);
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == rb10.getId()) {
//                    etother.setVisibility(VISIBLE);
//                } else {
//                    etother.setVisibility(INVISIBLE);
//                }
//            }
//        });
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String r5, String r6,
                        String r7, String r8, String r9, String r10, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
        rb6.setText(r6);
        rb7.setText(r7);
        rb8.setText(r8);
        rb9.setText(r9);
        rb10.setText(r10);
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
                rb6.setChecked(true);
                break;
            case "7":
                rb7.setChecked(true);
                break;
            case "8":
                rb8.setChecked(true);
                break;
            case "9":
                rb9.setChecked(true);
                break;
            case "10":
                rb10.setChecked(true);
                etother.setText(strs[1].substring(3, strs[1].length()));
                break;
        }
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == rb10.getId()) {
//                    etother.setVisibility(VISIBLE);
//                } else {
//                    etother.setVisibility(INVISIBLE);
//                }
//            }
//        });
    }


    public void setData1(String title, String r1, String r2, String r3, String r4, String r5, String r6,
                        String r7) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
        rb6.setText(r6);
        rb7.setText(r7);
        findViewById(R.id.line25).setVisibility(View.GONE);
        findViewById(R.id.line26).setVisibility(View.GONE);
        findViewById(R.id.line27).setVisibility(View.GONE);
        rb8.setVisibility(View.GONE);
        rb9.setVisibility(View.GONE);
        rb10.setVisibility(View.GONE);
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

    public void setData1(String title, String r1, String r2, String r3, String r4, String r5, String r6,
                        String r7, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
        rb6.setText(r6);
        rb7.setText(r7);
        findViewById(R.id.line25).setVisibility(View.GONE);
        findViewById(R.id.line26).setVisibility(View.GONE);
        findViewById(R.id.line27).setVisibility(View.GONE);
        rb8.setVisibility(View.GONE);
        rb9.setVisibility(View.GONE);
        rb10.setVisibility(View.GONE);
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
                rb6.setChecked(true);
                break;
            case "7":
                rb7.setChecked(true);
                break;
            case "8":
                rb8.setChecked(true);
                break;
            case "9":
                rb9.setChecked(true);
                break;
            case "10":
                rb10.setChecked(true);
                etother.setText(strs[1].substring(3, strs[1].length()));
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






    public String getQuestion() {
        return tvtitle.getText().toString().trim();
    }

    public boolean getIsEmpty() {
        if (radioGroup.getCheckedRadioButtonId() == rb10.getId()) {
            if (PlatUtils.getEditStr(etother)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean getIsEmpty1() {
        if (radioGroup.getCheckedRadioButtonId() == rb7.getId()) {
            if (PlatUtils.getEditStr(etother)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
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
            answer = "5|" + rb5.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb6.getId()) {
            answer = "6|" + rb6.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb7.getId()) {
            answer = "7|" + rb7.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb8.getId()) {
            answer = "8|" + rb8.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb9.getId()) {
            answer = "9|" + rb9.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb10.getId()) {
            answer = "0|str" + etother.getText().toString().trim();
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
            answer = "5|" + rb5.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb6.getId()) {
            answer = "6|" + rb6.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb7.getId()) {
            answer = "7|" + etother.getText().toString().trim();
        }
//        else if (radioGroup.getCheckedRadioButtonId() == rb8.getId()) {
//            answer = "8|" + rb8.getText().toString().trim();
//        } else if (radioGroup.getCheckedRadioButtonId() == rb9.getId()) {
//            answer = "9|" + rb9.getText().toString().trim();
//        } else if (radioGroup.getCheckedRadioButtonId() == rb10.getId()) {
//            answer = "0|str" + etother.getText().toString().trim();
//        }
        return answer;
    }
}
