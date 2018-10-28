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
import com.haiwai.housekeeper.utils.EvmUtil;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.ToastUtil;

/**
 * Created by ihope007 on 2016/10/18.
 */
public class View_DTQX_2 extends LinearLayout {
    private TextView tvtitle;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7;
    private EditText etother;
    private String answer = "";

    public View_DTQX_2(Context context) {
        this(context, null);
    }

    public View_DTQX_2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_DTQX_2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_dtqx_2, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_dtqx_2_tv_title);
        radioGroup = (RadioGroup) findViewById(R.id.view_dtqx_2_rg);
        etother = (EditText) findViewById(R.id.view_dtqx_2_et_other);
        rb1 = (RadioButton) findViewById(R.id.view_dtqx_2_rb_1);
        rb2 = (RadioButton) findViewById(R.id.view_dtqx_2_rb_2);
        rb3 = (RadioButton) findViewById(R.id.view_dtqx_2_rb_3);
        rb4 = (RadioButton) findViewById(R.id.view_dtqx_2_rb_4);
        rb5 = (RadioButton) findViewById(R.id.view_dtqx_2_rb_5);
        rb6 = (RadioButton) findViewById(R.id.view_dtqx_2_rb_6);
        rb7 = (RadioButton) findViewById(R.id.view_dtqx_2_rb_7);
//        etother.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                rb7.setChecked(true);
//            }
//        });
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb6.setChecked(true);
                return false;
            }
        });
//        etother.setVisibility(INVISIBLE);
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == rb7.getId() || checkedId == rb6.getId()) {
//                    etother.setVisibility(VISIBLE);
//                } else {
//                    etother.setVisibility(INVISIBLE);
////                    radioGroup.setPadding(EvmUtil.dip2px(getContext(),50),0,EvmUtil.dip2px(getContext(),50),EvmUtil.dip2px(getContext(),40));
//                }
//            }
//        });
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String r5, String r6) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
        //        rb6.setText(r6);
        etother.setHint(r6);
//        rb7.setText(r7);
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String r5, String r6, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
        rb6.setText(r6);
        etother.setVisibility(View.GONE);
//        etother.setHint(r6);
//        rb7.setText(r7);
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
                etother.setText(strs[1].substring(3, strs[1].length()));
                break;

        }
    }

    public void setDataMake(String title, String r1, String r2, String r3, String r4, String r5, String r6, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
        rb6.setText(r6);
        etother.setVisibility(View.VISIBLE);
//        etother.setHint(r6);
//        rb7.setText(r7);
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
                etother.setText(answer.substring(2,answer.length()));
                break;
//            case "7":
//                rb7.setChecked(true);
//                etother.setText(strs[1].substring(3, strs[1].length()));
//                break;

        }
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
        rb7.setVisibility(View.VISIBLE);
        findViewById(R.id.line4).setVisibility(View.VISIBLE);
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb7.setChecked(true);
                return false;
            }
        });
    }

    public void setData1(String title, String r1, String r2, String r3, String r4, String r5, String r6, String r7, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
        rb6.setText(r6);
        rb7.setText(r7);
        etother.setHint(r7);
//        rb7.setText(r7);
        rb7.setVisibility(View.VISIBLE);
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb7.setChecked(true);
                return false;
            }
        });
        findViewById(R.id.line4).setVisibility(View.VISIBLE);
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
                etother.setText(strs[1].substring(3, strs[1].length()));
                break;

        }
    }


    public void setData2(String title, String r1, String r2, String r3, String r4, String r5, String r6, String r7, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
        rb6.setText(r6);
        rb7.setText(r7);
        etother.setVisibility(View.GONE);
        etother.setHint(r7);
//        rb7.setText(r7);
        rb7.setVisibility(View.GONE);
//        rb7.setVisibility(View.VISIBLE);
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb7.setChecked(true);
                return false;
            }
        });
        findViewById(R.id.line4).setVisibility(View.VISIBLE);
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
                etother.setText(strs[1].substring(3, strs[1].length()));
                break;

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


    public boolean getIsEmpty2() {
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


    public boolean getIsEmpty() {
        if(!rb1.isChecked()&&!rb2.isChecked()&&!rb3.isChecked()&&!rb4.isChecked()&&!rb5.isChecked()&&!rb6.isChecked()&&!rb7.isChecked()){
            return false;
        }
        if (rb7.isChecked()) {
            if (PlatUtils.getEditStr(etother)) {
                return false;
            } else {
                return true;
            }
        } else if (rb6.isChecked()) {
            if (PlatUtils.getEditStr(etother)) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    public boolean getIsEmpty11() {
        if(!rb1.isChecked()&&!rb2.isChecked()&&!rb3.isChecked()&&!rb4.isChecked()&&!rb5.isChecked()&&!rb6.isChecked()&&!rb7.isChecked()){
            return false;
        }
        if (rb7.isChecked()) {
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
            answer = "6|" + etother.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb7.getId()) {
            answer = "7|str" + etother.getText().toString().trim();
        }
        return answer;
    }

    public String getAnswer3() {
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
            answer = "6|" + etother.getText().toString().trim();
        }
        return answer;
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
            answer = "7|str" + etother.getText().toString().trim();
        }
        return answer;
    }

    public String getAnswer2() {
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
            answer = "7|str" + rb7.getText().toString().trim();
        }
        return answer;
    }


}
