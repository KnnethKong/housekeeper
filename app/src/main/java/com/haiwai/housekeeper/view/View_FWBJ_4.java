package com.haiwai.housekeeper.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.IssueRequireBActivity;
import com.haiwai.housekeeper.utils.ToastUtil;

/**
 * Created by ihope007 on 2016/10/18.
 */
public class View_FWBJ_4 extends LinearLayout {
    private TextView tvtitle;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7;
    private String answer = "";
    private EditText et_num_room, et_num_room2, et_num_room3;
    private View line;
    private boolean isTextEmpty = false;

    public View_FWBJ_4(Context context) {
        this(context, null);
    }

    public View_FWBJ_4(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_FWBJ_4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_fwbj_4, this);
        initView();
    }

    private void initView() {
        et_num_room2 = ((EditText) findViewById(R.id.et_input_num_room2));
        line = findViewById(R.id.fwbj_line1);
        tvtitle = (TextView) findViewById(R.id.view_fwbj_4_tv_title);
        radioGroup = (RadioGroup) findViewById(R.id.view_fwbj_4_rg);
        rb1 = (RadioButton) findViewById(R.id.view_fwbj_4_rb_1);
        rb2 = (RadioButton) findViewById(R.id.view_fwbj_4_rb_2);
        rb3 = (RadioButton) findViewById(R.id.view_fwbj_4_rb_3);
        rb4 = (RadioButton) findViewById(R.id.view_fwbj_4_rb_4);
        rb5 = (RadioButton) findViewById(R.id.view_fwbj_4_rb_5);
        rb6 = (RadioButton) findViewById(R.id.view_fwbj_4_rb_6);
        rb7 = (RadioButton) findViewById(R.id.view_fwbj_4_rb_7);
        et_num_room = ((EditText) findViewById(R.id.et_input_num_room1));
        et_num_room3 = ((EditText) findViewById(R.id.et_input_num_room3));

//        et_num_room.setOnFocusChangeListener(new OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if(b){
//                    rb5.setChecked(true);
//                }else{
//                    rb5.setChecked(false);
//                }
//            }
//        });
        et_num_room3.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb7.setChecked(true);
                return false;
            }
        });
        et_num_room.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb5.setChecked(true);
                return false;
            }
        });
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String r5) {
        tvtitle.setText(title);
        if (title.contains("How big is the lawn") || title.contains("草坪有多大")) {
            line.setVisibility(View.GONE);
            isTextEmpty = true;
            et_num_room2.setVisibility(View.VISIBLE);
            et_num_room.setVisibility(View.GONE);
        } else {
            isTextEmpty = false;
            line.setVisibility(View.VISIBLE);
            et_num_room2.setVisibility(View.GONE);
        }
        rb1.setChecked(true);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
        rb6.setVisibility(View.GONE);
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String r5, String answer) {
        rb1.setChecked(true);
        tvtitle.setText(title);
        if (title.contains("How big is the lawn") || title.contains("草坪有多大")) {
            line.setVisibility(View.GONE);
            et_num_room2.setVisibility(View.VISIBLE);
            et_num_room.setVisibility(View.GONE);
        } else {
            line.setVisibility(View.VISIBLE);
            et_num_room2.setVisibility(View.GONE);
        }
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
        rb6.setVisibility(View.GONE);
        switch (answer.substring(0, 1)) {
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
        }
    }


    public void setData3(String title, String r1, String r2, String r3, String r4, String r5, String r6) {
        tvtitle.setText(title);
        et_num_room2.setVisibility(View.GONE);
        findViewById(R.id.fwbj_line31).setVisibility(View.VISIBLE);
        line.setVisibility(View.VISIBLE);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
//        rb5.setText(r6);
        rb6.setText(r5);
        et_num_room.setHint(r6);
        rb7.setVisibility(View.GONE);
    }

    public void setData3(String title, String r1, String r2, String r3, String r4, String r5, String r6, String answer) {
        rb1.setChecked(true);
        tvtitle.setText(title);
        findViewById(R.id.fwbj_line31).setVisibility(View.VISIBLE);
        et_num_room2.setVisibility(View.GONE);
        line.setVisibility(View.VISIBLE);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
//        rb5.setText(r6);
        rb6.setText(r5);
        et_num_room.setHint(r6);
        rb7.setVisibility(View.GONE);
        switch (answer.substring(0, 1)) {
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
                rb6.setChecked(true);
                break;
            case "6":
                rb5.setChecked(true);
                et_num_room.setText(answer.substring(2, answer.length()));
                break;
        }
    }


    public void setData1(String title, String r1, String r2, String r3, String r4, String r5) {
        tvtitle.setText(title);

        et_num_room.setVisibility(View.GONE);

        et_num_room2.setVisibility(View.GONE);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);

        rb6.setVisibility(View.GONE);
        rb7.setVisibility(View.GONE);
        findViewById(R.id.fwbj_line32).setVisibility(View.GONE);
    }

    public void setData1(String title, String r1, String r2, String r3, String r4, String r5, String answer) {
        rb1.setChecked(true);
        tvtitle.setText(title);
        et_num_room.setVisibility(View.GONE);

        et_num_room2.setVisibility(View.GONE);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
        rb6.setVisibility(View.GONE);
        rb7.setVisibility(View.GONE);
        findViewById(R.id.fwbj_line32).setVisibility(View.GONE);
        et_num_room.setFocusable(false);
        switch (answer.substring(0, 1)) {
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
        }
    }


    public void setData2(String title, String r1, String r2, String r3, String r4, String r5) {
        tvtitle.setText(title);

        et_num_room.setVisibility(View.GONE);

        et_num_room2.setVisibility(View.GONE);
        rb1.setChecked(true);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
    }

    public void setData2(String title, String r1, String r2, String r3, String r4, String r5, String answer) {
        rb1.setChecked(true);
        et_num_room.setVisibility(View.GONE);

        et_num_room2.setVisibility(View.GONE);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
        et_num_room.setFocusable(false);
        switch (answer.substring(0, 1)) {
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
        }
    }


    public void setData5(String title, String r1, String r2, String r3, String r4, String r5, String r6, String r7) {
        tvtitle.setText(title);
        et_num_room2.setVisibility(View.GONE);
        findViewById(R.id.fwbj_line31).setVisibility(View.VISIBLE);
        line.setVisibility(View.VISIBLE);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r7);
        rb6.setText(r5);
//        rb7.setText(r6);
        findViewById(R.id.fwbj_line1).setVisibility(View.GONE);

        et_num_room3.setVisibility(View.VISIBLE);
        et_num_room.setVisibility(View.GONE);
        et_num_room2.setVisibility(View.GONE);
        et_num_room3.setText(r6);
        et_num_room3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                rb7.setSelected(true);
            }
        });
    }

    public void setData5(String title, String r1, String r2, String r3, String r4, String r5, String r6, String r7, String answer) {
        rb1.setChecked(true);
        tvtitle.setText(title);
        findViewById(R.id.fwbj_line31).setVisibility(View.VISIBLE);
        et_num_room2.setVisibility(View.GONE);
        line.setVisibility(View.VISIBLE);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r7);
        rb6.setText(r5);
//        rb7.setText(r6);
        findViewById(R.id.fwbj_line1).setVisibility(View.GONE);
        et_num_room3.setVisibility(View.VISIBLE);
        et_num_room.setVisibility(View.GONE);
        et_num_room2.setVisibility(View.GONE);
        et_num_room.setFocusable(false);
        switch (answer.substring(0, 1)) {
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
                rb7.setChecked(true);
                et_num_room3.setText(answer.substring(2, answer.length()));
                break;
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
            if (TextUtils.isEmpty(et_num_room2.getText().toString())) {

                return "";
            }
            answer = "4|" + et_num_room2.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb5.getId()) {

            if (TextUtils.isEmpty(et_num_room.getText().toString())) {
                ToastUtil.shortToast(getContext(), getContext().getString(R.string.enter_num));
                return "";
            } else {
                answer = "5|" + et_num_room.getText().toString().trim();
            }
        }
        return answer;
    }


    public String getAnswer5() {
        if (radioGroup.getCheckedRadioButtonId() == rb1.getId()) {
            answer = "1|" + rb1.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb2.getId()) {
            answer = "2|" + rb2.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb3.getId()) {
            answer = "3|" + rb3.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb4.getId()) {
            answer = "4|" + rb4.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb6.getId()) {
            answer = "5|" + rb6.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb7.getId()) {
            if (TextUtils.isEmpty(et_num_room3.getText().toString())) {
                return "";
            }
            answer = "6|" + et_num_room3.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb5.getId()) {
            answer = "7|" + rb5.getText().toString().trim();
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
//            if(TextUtils.isEmpty(et_num_room2.getText().toString())){
//                ToastUtil.shortToast(getContext(),getContext().getString(R.string.large_room));
//                return "";
//            }
            answer = "4|" + rb4.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb6.getId()) {
            answer = "5|" + rb6.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb5.getId()) {

            if (TextUtils.isEmpty(et_num_room.getText().toString())) {
                ToastUtil.shortToast(getContext(), getContext().getString(R.string.enter_num));
                return "";
            } else {
                answer = "6|" + et_num_room.getText().toString().trim();
            }
        }
        return answer;
    }


    public String getAnswer4() {
        if (radioGroup.getCheckedRadioButtonId() == rb1.getId()) {
            answer = "1|" + rb1.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb2.getId()) {
            answer = "2|" + rb2.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb3.getId()) {
            answer = "3|" + rb3.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb4.getId()) {
//            if(TextUtils.isEmpty(et_num_room2.getText().toString())){
//                ToastUtil.shortToast(getContext(),getContext().getString(R.string.large_room));
//                return "";
//            }
            et_num_room2.setText("fdsfsd");
            answer = "4|" + rb4.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb6.getId()) {
            answer = "5|" + rb6.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb7.getId()) {
            answer = "6|" + et_num_room3.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb5.getId()) {

//            if(TextUtils.isEmpty(et_num_room.getText().toString())){
//                ToastUtil.shortToast(getContext(),getContext().getString(R.string.enter_num));
//                return "";
//            }else{
//                answer = "6|" + et_num_room.getText().toString().trim();
//            }
            answer = "7|" + rb7.getText().toString().trim();
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
            answer = "5|" + et_num_room.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb6.getId()) {
            answer = "6|" + et_num_room3.getText().toString().trim();
        }
        return answer;
    }


    public String getAnswer6() {
        if (radioGroup.getCheckedRadioButtonId() == rb1.getId()) {
            answer = "1|" + rb1.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb2.getId()) {
            answer = "2|" + rb2.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb3.getId()) {
            answer = "3|" + rb3.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb4.getId()) {
            answer = "4|" + rb4.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb5.getId()) {
            if(et_num_room.getText().toString().equals("")){
                return "";
            }
            answer = "6|" + et_num_room.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb6.getId()) {
            answer = "5|" + rb6.getText().toString();
        }
        return answer;
    }


}
