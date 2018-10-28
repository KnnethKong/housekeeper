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
public class View_JDXD_2 extends LinearLayout {
    private TextView tvtitle;
    private EditText etother;
    private CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8;
    private String answer = "";

    public View_JDXD_2(Context context) {
        this(context, null);
    }

    public View_JDXD_2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_JDXD_2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_jdxd_2, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_jdxd_2_tv_title);
        etother = (EditText) findViewById(R.id.view_jdxd_2_et_other);
        cb1 = (CheckBox) findViewById(R.id.view_jdxd_2_cb_1);
        cb2 = (CheckBox) findViewById(R.id.view_jdxd_2_cb_2);
        cb3 = (CheckBox) findViewById(R.id.view_jdxd_2_cb_3);
        cb4 = (CheckBox) findViewById(R.id.view_jdxd_2_cb_4);
        cb5 = (CheckBox) findViewById(R.id.view_jdxd_2_cb_5);
        cb6 = (CheckBox) findViewById(R.id.view_jdxd_2_cb_6);
        cb7 = (CheckBox) findViewById(R.id.view_jdxd_2_cb_7);
        cb8 = (CheckBox) findViewById(R.id.view_jdxd_2_cb_8);
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                cb8.setChecked(true);
                return false;
            }
        });
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String r5,
                        String r6, String r7, String r8) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb5.setText(r5);
        cb6.setText(r6);
        cb7.setText(r7);
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String r5,
                        String r6, String r7, String r8, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb5.setText(r5);
        cb6.setText(r6);
        cb7.setText(r7);
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
        }
        if ("1".equals(answer.substring(7, 8))) {
            cb8.setChecked(true);
            etother.setText(answer.substring(8, answer.length()));
        }
    }


    /**
     * 0:可以通过   1:请至少选择一项   2:请补全信息
     */
    public int getIsEmptyState() {
        if (cb1.isChecked() || cb2.isChecked() || cb3.isChecked() || cb4.isChecked() || cb5.isChecked() ||
                cb6.isChecked() || cb7.isChecked() || cb8.isChecked()) {
            if (cb8.isChecked() && PlatUtils.getEditStr(etother)) {
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
        if (cb8.isChecked()) {
            answer = "" + booleanToInt(cb1.isChecked()) + booleanToInt(cb2.isChecked()) +
                    booleanToInt(cb3.isChecked()) + booleanToInt(cb4.isChecked()) + booleanToInt(cb5.isChecked()) +
                    booleanToInt(cb6.isChecked()) + booleanToInt(cb7.isChecked()) + booleanToInt(cb8.isChecked()) + etother.getText().toString().trim();
        } else {
            answer = "" + booleanToInt(cb1.isChecked()) + booleanToInt(cb2.isChecked()) +
                    booleanToInt(cb3.isChecked()) + booleanToInt(cb4.isChecked()) + booleanToInt(cb5.isChecked()) +
                    booleanToInt(cb6.isChecked()) + booleanToInt(cb7.isChecked()) + booleanToInt(cb8.isChecked());
        }
        return answer;
    }
}
