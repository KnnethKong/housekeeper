package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
public class View_DTQX_7 extends LinearLayout {
    private TextView tvtitle;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3,rb4,rb5;
    private EditText etother;
    private String answer = "";

    public View_DTQX_7(Context context) {
        this(context, null);
    }

    public View_DTQX_7(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_DTQX_7(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_dtqx_7, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_dtqx_7_tv_title);
        radioGroup = (RadioGroup) findViewById(R.id.view_dtqx_7_rg);
        rb1 = (RadioButton) findViewById(R.id.view_dtqx_7_rb_1);
        rb2 = (RadioButton) findViewById(R.id.view_dtqx_7_rb_2);
        rb3 = (RadioButton) findViewById(R.id.view_dtqx_7_rb_3);
        rb4 = (RadioButton) findViewById(R.id.view_dtqx_7_rb_4);
        rb5 = (RadioButton) findViewById(R.id.view_dtqx_7_rb_5);
        etother = (EditText) findViewById(R.id.view_dtqx_7_et_other);

    }

    public void setData1(String title, String r1, String r2, String r3,String r4,String r5) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        etother.setText(r5);
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb5.setChecked(true);
                return false;
            }
        });
    }

    public void setData1(String title, String r1, String r2, String r3,String r4,String r5, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        etother.setText(r5);
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb5.setChecked(true);
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
//                etother.setText(strs[1].substring(3, strs[1].length()));
                break;
            case "4":
                rb4.setChecked(true);
                break;
            case "5":
                rb5.setChecked(true);
                etother.setText(answer.substring(2,answer.length()));
                break;
        }
    }




    public void setData(String title, String r1, String r2, String r3) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
//        rb3.setText(r3);
        etother.setHint(r3);
        rb4.setVisibility(View.GONE);
        rb5.setVisibility(View.GONE);
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb3.setChecked(true);
                return false;
            }
        });
        findViewById(R.id.line10).setVisibility(View.GONE);
        findViewById(R.id.line11).setVisibility(View.GONE);
    }

    public void setData(String title, String r1, String r2, String r3, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
//        rb3.setText(r3);
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb3.setChecked(true);
                return false;
            }
        });
        etother.setHint(r3);
        rb4.setVisibility(View.GONE);
        rb5.setVisibility(View.GONE);
        findViewById(R.id.line10).setVisibility(View.GONE);
        findViewById(R.id.line11).setVisibility(View.GONE);
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
                etother.setText(strs[1].substring(3, strs[1].length()));
                break;
        }
    }


    public void setData2(String title, String r1, String r2, String r3) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
//        rb3.setText(r3);
        rb4.setVisibility(View.GONE);
        rb5.setVisibility(View.GONE);
        etother.setHint(r3);
        etother.setVisibility(View.VISIBLE);
        etother.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                rb3.setChecked(true);
            }
        });
        findViewById(R.id.line10).setVisibility(View.GONE);
        findViewById(R.id.line11).setVisibility(View.GONE);
    }

    public void setData2(final String title, String r1, String r2, String r3, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
//        rb3.setText(r3);
        rb4.setVisibility(View.GONE);
        rb5.setVisibility(View.GONE);

        etother.setHint(r3);
        etother.setVisibility(View.VISIBLE);
        etother.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                rb3.setChecked(true);
            }
        });
        findViewById(R.id.line10).setVisibility(View.GONE);
        findViewById(R.id.line11).setVisibility(View.GONE);
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
                etother.setText(strs[1].substring(3, strs[1].length()));
                break;
        }
    }


    public boolean getIsEmpty() {
        if(!rb1.isChecked()&&!rb2.isChecked()&&!rb3.isChecked()&&!rb4.isChecked()&&!rb5.isChecked()){
            return false;
        }
        if (radioGroup.getCheckedRadioButtonId() == rb3.getId()) {
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

        if(!rb1.isChecked()&&!rb2.isChecked()&&!rb3.isChecked()&&!rb4.isChecked()&&!rb5.isChecked()){
            return false;
        }

        if (radioGroup.getCheckedRadioButtonId() == rb5.getId()) {
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
//            answer = "3|str" + etother.getText().toString().trim();
            answer = "3|"+ rb3.getText().toString().trim();
        }
        return answer;
    }




    public String getAnswer3() {
        if (radioGroup.getCheckedRadioButtonId() == rb1.getId()) {
            answer = "1|" + rb1.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb2.getId()) {
            answer = "2|" + rb2.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb3.getId()) {
            answer = "3|str" + etother.getText().toString().trim();
//            answer = "3|"+ rb3.getText().toString().trim();
        }
        return answer;
    }



    public String getAnswer2() {
        if (radioGroup.getCheckedRadioButtonId() == rb1.getId()) {
            answer = "1|" + rb1.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb2.getId()) {
            answer = "2|" + rb2.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb3.getId()) {
//            answer = "3|str" + etother.getText().toString().trim();
            answer = "3|str"+ etother.getText().toString().trim();
        }
        return answer;
    }

    public String getAnswer1() {
        if (radioGroup.getCheckedRadioButtonId() == rb1.getId()) {
            answer = "1|" + rb1.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb2.getId()) {
            answer = "2|" + rb2.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb3.getId()) {
            answer = "3|" +rb3.getText().toString().trim();
        }else if (radioGroup.getCheckedRadioButtonId() == rb4.getId()) {
            answer = "4|" +rb4.getText().toString().trim();
        }else if (radioGroup.getCheckedRadioButtonId() == rb5.getId()) {
            answer = "5|" +rb5.getText().toString().trim();
        }
        return answer;
    }

    public String getAnswer4() {
        if (radioGroup.getCheckedRadioButtonId() == rb1.getId()) {
            answer = "1|" + rb1.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb2.getId()) {
            answer = "2|" + rb2.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb3.getId()) {
            answer = "3|" +rb3.getText().toString().trim();
        }else if (radioGroup.getCheckedRadioButtonId() == rb4.getId()) {
            answer = "4|" +rb4.getText().toString().trim();
        }else if (radioGroup.getCheckedRadioButtonId() == rb5.getId()) {
            answer = "5|" +etother.getText().toString().trim();
        }
        return answer;
    }


}
