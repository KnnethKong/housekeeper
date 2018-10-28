package com.haiwai.housekeeper.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
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
public class View_FWBJ_7 extends LinearLayout {
    private TextView tvtitle;
    private CheckBox cb1, cb2, cb3, cb4, cb5, cb6,cb7;
    private String answer = "";
    private EditText et_other;

    public View_FWBJ_7(Context context) {
        this(context, null);
    }

    public View_FWBJ_7(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_FWBJ_7(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_fwbj_7, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_fwbj_7_tv_title);
        cb1 = (CheckBox) findViewById(R.id.view_fwbj_7_cb_1);
        cb2 = (CheckBox) findViewById(R.id.view_fwbj_7_cb_2);
        cb3 = (CheckBox) findViewById(R.id.view_fwbj_7_cb_3);
        cb4 = (CheckBox) findViewById(R.id.view_fwbj_7_cb_4);
        cb5 = (CheckBox) findViewById(R.id.view_fwbj_7_cb_5);
        cb6 = (CheckBox) findViewById(R.id.view_fwbj_7_cb_6);
        cb7 = (CheckBox) findViewById(R.id.view_fwbj_7_cb_7);
        et_other = ((EditText) findViewById(R.id.view_fwbj_7_et_other));
//        et_other.setOnFocusChangeListener(new OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if(b){
//                    cb7.setChecked(true);
//                }else{
//                    cb7.setChecked(false);
//                }
//            }
//        });
//        et_other.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cb7.setChecked(true);
//            }
//        });
        et_other.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                cb7.setChecked(true);
                return false;
            }
        });
    }

    public void setData1(String title, String r1, String r2, String r3, String r4, String r5, String r6,String r7) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb5.setText(r5);
        cb6.setText(r6);
        et_other.setHint(r7);
    }

    public void setData1(String title, String r1, String r2, String r3, String r4, String r5, String r6,String r7, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb5.setText(r5);
        cb6.setText(r6);
//        cb7.setText(r7);
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
        if ("1".equals(answer.substring(5, 6))) {
            cb6.setChecked(true);
        }
    }


    public void setData2(String title, String r1, String r2, String r3, String r4, String r5, String r6,String r7, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb5.setText(r5);
        cb6.setText(r6);
//        cb7.setText(r7);
        et_other.setHint(r7);
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
        if ("1".equals(answer.substring(5, 6))) {
            cb6.setChecked(true);
        }
        if ("1".equals(answer.substring(6, 7))) {
            cb7.setChecked(true);
            et_other.setText(answer.substring(7,answer.length()));
        }
    }




    public void setData(String title, String r1, String r2, String r3, String r4, String r5, String r6) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb5.setText(r5);
        cb6.setText(r6);
        cb7.setVisibility(View.GONE);
        et_other.setVisibility(View.GONE);
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String r5, String r6, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb5.setText(r5);
        cb6.setText(r6);
        cb7.setVisibility(View.GONE);
        et_other.setVisibility(View.GONE);
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
        if ("1".equals(answer.substring(5, 6))) {
            cb6.setChecked(true);
        }
    }

    /**
     * 0:可以通过   1:请至少选择一项   2:请补全信息
     */
    public int getIsEmptyState() {

        if(cb7.isChecked()){
            if(TextUtils.isEmpty(et_other.getText().toString())){
                return 2;
            }else{
                return 0;
            }
        }

        if (cb1.isChecked() || cb2.isChecked() || cb3.isChecked() || cb4.isChecked() || cb5.isChecked() ||
                cb6.isChecked()) {
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
        answer = "" + booleanToInt(cb1.isChecked()) + booleanToInt(cb2.isChecked()) +
                booleanToInt(cb3.isChecked()) + booleanToInt(cb4.isChecked()) +
                booleanToInt(cb5.isChecked()) + booleanToInt(cb6.isChecked());
        return answer;
    }

    public String getAnswer1() {
        if(cb7.isChecked()){
            answer = "" + booleanToInt(cb1.isChecked()) + booleanToInt(cb2.isChecked()) +
                    booleanToInt(cb3.isChecked()) + booleanToInt(cb4.isChecked()) +
                    booleanToInt(cb5.isChecked()) + booleanToInt(cb6.isChecked())+booleanToInt(cb7.isChecked())+et_other.getText().toString();
        }else{
            answer = "" + booleanToInt(cb1.isChecked()) + booleanToInt(cb2.isChecked()) +
                    booleanToInt(cb3.isChecked()) + booleanToInt(cb4.isChecked()) +
                    booleanToInt(cb5.isChecked()) + booleanToInt(cb6.isChecked())+booleanToInt(cb7.isChecked());
        }

        return answer;
    }
}
