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
import com.haiwai.housekeeper.utils.ToastUtil;

import org.w3c.dom.Text;

/**
 * Created by ihope007 on 2016/10/18.
 */
public class View_FWBJ_5 extends LinearLayout {
    private TextView tvtitle;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7
//            , rb8
            ;

    private EditText et_num_room;
    private String answer = "";

    public View_FWBJ_5(Context context) {
        this(context, null);
    }

    public View_FWBJ_5(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_FWBJ_5(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_fwbj_5, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_fwbj_5_tv_title);
        radioGroup = (RadioGroup) findViewById(R.id.view_fwbj_5_rg);
        rb1 = (RadioButton) findViewById(R.id.view_fwbj_5_rb_1);
        rb2 = (RadioButton) findViewById(R.id.view_fwbj_5_rb_2);
        rb3 = (RadioButton) findViewById(R.id.view_fwbj_5_rb_3);
        rb4 = (RadioButton) findViewById(R.id.view_fwbj_5_rb_4);
        rb5 = (RadioButton) findViewById(R.id.view_fwbj_5_rb_5);
        rb6 = (RadioButton) findViewById(R.id.view_fwbj_5_rb_6);
        rb7 = (RadioButton) findViewById(R.id.view_fwbj_5_rb_7);
//        rb8 = (RadioButton) findViewById(R.id.view_fwbj_5_rb_8);
        et_num_room = ((EditText) findViewById(R.id.et_input_num_room2));
        et_num_room.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                rb7.setChecked(true);
            }
        });
//        et_num_room.setOnFocusChangeListener(new OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if(b){
//                    rb7.setChecked(true);
//                }else{
//                    rb7.setChecked(false);
//                }
//            }
//        });
        et_num_room.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rb7.setChecked(true);
                return false;
            }
        });
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String r5, String r6, String r7) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
        rb6.setText(r6);
        //        rb7.setText(r7);
        et_num_room.setHint(r7);
//        rb8.setText(r8);
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String r5, String r6, String r7, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb4.setText(r4);
        rb5.setText(r5);
        rb6.setText(r6);
//        rb7.setText(r7);
        et_num_room.setHint(r7);
//        rb8.setText(r8);
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
                rb6.setChecked(true);
                break;
            case "7":
                rb7.setChecked(true);
                et_num_room.setText(answer.substring(2,answer.length()));
                break;
//            case "8":
//                rb8.setChecked(true);
//                break;

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
            answer = "5|" + rb5.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb6.getId()) {
            answer = "6|" + rb6.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb7.getId()) {
            if (TextUtils.isEmpty(et_num_room.getText().toString())) {
                ToastUtil.shortToast(getContext(), getContext().getString(R.string.input_big));
                return "";
            } else {
                answer = "7|" + et_num_room.getText().toString().trim();
            }
        }
//        else if (radioGroup.getCheckedRadioButtonId() == rb8.getId()) {
//            answer = "8|" + rb8.getText().toString().trim();
//        }
        return answer;
    }
}
