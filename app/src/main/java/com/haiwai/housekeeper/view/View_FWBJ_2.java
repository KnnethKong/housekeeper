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
import com.haiwai.housekeeper.utils.ToastUtil;

/**
 * Created by ihope007 on 2016/10/18.
 */
public class View_FWBJ_2 extends LinearLayout {
    private TextView tvtitle;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2,rb3;
    private String answer = "";
//    private TextView tvDes;


    public View_FWBJ_2(Context context) {
        this(context, null);
    }

    public View_FWBJ_2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_FWBJ_2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_fwbj_2, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_fwbj_2_tv_title);
        radioGroup = (RadioGroup) findViewById(R.id.view_fwbj_2_rg);
        rb1 = (RadioButton) findViewById(R.id.view_fwbj_2_rb_1);
        rb2 = (RadioButton) findViewById(R.id.view_fwbj_2_rb_2);
        rb3 = (RadioButton) findViewById(R.id.view_fwbj_2_rb_3);
//        tvDes = ((TextView) findViewById(R.id.tv_fwbj_desc));
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                if (radioGroup.getCheckedRadioButtonId()==rb1.getId()){
//                    tvDes.setText(getContext().getString(R.string.fwbj_describt));
//                }else{
//                    tvDes.setText(getContext().getString(R.string.fwbj_descr));
//                }
//            }
//        });
    }

    public void setData(String title, String r1, String r2) {
        tvtitle.setText(title);
//        if(title.contains("cleaning service would you")||title.contains("清洁服务")){
//            tvDes.setVisibility(View.VISIBLE);
//        }else{
//            tvDes.setVisibility(View.GONE);
//        }
        rb1.setText(r1);
        rb2.setText(r2);
    }

    public void setData(String title, String r1, String r2, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
//        if(title.contains("cleaning service would you")||title.contains("清洁服务")){
//            tvDes.setVisibility(View.VISIBLE);
//        }else{
//            tvDes.setVisibility(View.GONE);
//        }
        switch (answer.substring(0, 1)) {
            case "1":
                rb1.setChecked(true);
                break;
            case "2":
                rb2.setChecked(true);
                break;
        }
    }



    public void setData1(String title, String r1, String r2,String r3) {
        tvtitle.setText(title);
//            tvDes.setVisibility(View.GONE);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb3.setVisibility(View.VISIBLE);
        findViewById(R.id.line29).setVisibility(View.VISIBLE);
    }

    public void setData1(String title, String r1, String r2,String r3, String answer) {
        tvtitle.setText(title);
        rb1.setText(r1);
        rb2.setText(r2);
        rb3.setText(r3);
        rb3.setVisibility(View.VISIBLE);
        findViewById(R.id.line29).setVisibility(View.VISIBLE);
//        tvDes.setVisibility(View.GONE);
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
        }
    }

    public boolean isEmpty(){
        if(rb1.isChecked()||rb2.isChecked()||rb3.isChecked()){
            return true;
        }else{
            return false;
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
        }
        return answer;
    }
    public String getAnswer1() {
        if (radioGroup.getCheckedRadioButtonId() == rb1.getId()) {
            answer = "1|" + rb1.getText().toString().trim();
        } else if (radioGroup.getCheckedRadioButtonId() == rb2.getId()) {
            answer = "2|" + rb2.getText().toString().trim();
        }else if (radioGroup.getCheckedRadioButtonId() == rb3.getId()) {
            answer = "3|" + rb3.getText().toString().trim();
        }
        return answer;
    }
}
