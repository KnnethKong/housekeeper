package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.utils.PlatUtils;

/**
 * Created by ihope007 on 2016/10/18.
 */
public class View_BXXD_1 extends LinearLayout {
    private TextView tvtitle;
    private EditText etother;
    private CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9;
    private String answer = "";

    public View_BXXD_1(Context context) {
        this(context, null);
    }

    public View_BXXD_1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_BXXD_1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_bxxd_1, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_bxxd_1_tv_title);
        etother = (EditText) findViewById(R.id.view_bxxd_1_et_other);
        cb1 = (CheckBox) findViewById(R.id.view_bxxd_1_cb_1);
        cb2 = (CheckBox) findViewById(R.id.view_bxxd_1_cb_2);
        cb3 = (CheckBox) findViewById(R.id.view_bxxd_1_cb_3);
        cb4 = (CheckBox) findViewById(R.id.view_bxxd_1_cb_4);
        cb5 = (CheckBox) findViewById(R.id.view_bxxd_1_cb_5);
        cb6 = (CheckBox) findViewById(R.id.view_bxxd_1_cb_6);
        cb7 = (CheckBox) findViewById(R.id.view_bxxd_1_cb_7);
        cb8 = (CheckBox) findViewById(R.id.view_bxxd_1_cb_8);
        cb9 = (CheckBox) findViewById(R.id.view_bxxd_1_cb_9);
//        etother.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cb9.setChecked(true);
//            }
//        });
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                cb9.setChecked(true);
                return false;
            }
        });
//        etother.setOnFocusChangeListener(new OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                cb9.setChecked(true);
//            }
//        });
    }

    public void setData2(String title, String r1, String r2, String r3, String r4, String r5,
                         String r6, String r7, String r8) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb5.setText(r5);
        cb6.setText(r6);
        cb7.setText(r7);
        initCheck();
        cb8.setVisibility(View.GONE);
        findViewById(R.id.line12).setVisibility(View.GONE);
        etother.setHint(r8);
    }

    public void setData2(String title, String r1, String r2, String r3, String r4, String r5,
                         String r6, String r7, String r8, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb5.setText(r5);
        cb6.setText(r6);
        cb7.setText(r7);
        initCheck();

        cb8.setVisibility(View.GONE);
        findViewById(R.id.line12).setVisibility(View.GONE);
        etother.setHint(r8);
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
        }
        if ("1".equals(answer.substring(8, 9))) {
            cb9.setChecked(true);
            etother.setText(answer.substring(9, answer.length()));
        }
    }





    public void setData3(String title, String r1, String r2, String r3, String r4, String r5,
                         String r6, String r7, String r8, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb5.setText(r5);
        cb6.setText(r6);
        cb7.setText(r7);
        initCheck();

        cb8.setVisibility(View.GONE);
        findViewById(R.id.line12).setVisibility(View.GONE);
        etother.setHint(r8);
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
            cb9.setChecked(true);
            etother.setText(answer.substring(8, answer.length()));
        }

    }


    public void setData1(String title, String r1, String r2, String r3, String r4, String r5,
                         String r6, String r7, String r8) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb5.setText(r5);
        cb6.setText(r6);
        cb7.setText(r7);
        cb8.setVisibility(View.GONE);
        findViewById(R.id.line12).setVisibility(View.GONE);
        etother.setHint(r8);
    }

    public void setData1(String title, String r1, String r2, String r3, String r4, String r5,
                         String r6, String r7, String r8, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb5.setText(r5);
        cb6.setText(r6);
        cb7.setText(r7);
        cb8.setVisibility(View.GONE);
        findViewById(R.id.line12).setVisibility(View.GONE);
        etother.setHint(r8);
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
        }
        if ("1".equals(answer.substring(8, 9))) {
            cb9.setChecked(true);
            etother.setText(answer.substring(9, answer.length()));
        }
    }


    public void setData(String title, String r1, String r2, String r3, String r4, String r5,
                        String r6, String r7, String r8, String r9) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb5.setText(r5);
        cb6.setText(r6);
        cb7.setText(r7);
        cb8.setText(r8);
        etother.setHint(r9);
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String r5,
                        String r6, String r7, String r8, String r9, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb5.setText(r5);
        cb6.setText(r6);
        cb7.setText(r7);
        cb8.setText(r8);
        etother.setHint(r9);
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
        }
        if ("1".equals(answer.substring(8, 9))) {
            cb9.setChecked(true);
            etother.setText(answer.substring(9, answer.length()));
        }
    }

    /**
     * 0:可以通过   1:请至少选择一项   2:请补全信息
     */
    public int getIsEmptyState() {
        if (cb1.isChecked() || cb2.isChecked() || cb3.isChecked() || cb4.isChecked() || cb5.isChecked() ||
                cb6.isChecked() || cb7.isChecked() || cb8.isChecked() || cb9.isChecked()) {
            if (cb9.isChecked() && PlatUtils.getEditStr(etother)) {
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
        if (cb9.isChecked()) {
            answer = "" + booleanToInt(cb1.isChecked()) + booleanToInt(cb2.isChecked()) +
                    booleanToInt(cb3.isChecked()) + booleanToInt(cb4.isChecked()) + booleanToInt(cb5.isChecked()) +
                    booleanToInt(cb6.isChecked()) + booleanToInt(cb7.isChecked()) + booleanToInt(cb8.isChecked()) +
                    booleanToInt(cb9.isChecked()) + etother.getText().toString().trim();
        } else {
            answer = "" + booleanToInt(cb1.isChecked()) + booleanToInt(cb2.isChecked()) +
                    booleanToInt(cb3.isChecked()) + booleanToInt(cb4.isChecked()) + booleanToInt(cb5.isChecked()) +
                    booleanToInt(cb6.isChecked()) + booleanToInt(cb7.isChecked()) + booleanToInt(cb8.isChecked()) +
                    booleanToInt(cb9.isChecked());
        }
        return answer;
    }

    public String getAnswer1() {
        if (cb9.isChecked()) {
            answer = "" + booleanToInt(cb1.isChecked()) + booleanToInt(cb2.isChecked()) +
                    booleanToInt(cb3.isChecked()) + booleanToInt(cb4.isChecked()) + booleanToInt(cb5.isChecked()) +
                    booleanToInt(cb6.isChecked()) + booleanToInt(cb7.isChecked()) +
                    booleanToInt(cb9.isChecked()) + etother.getText().toString().trim();
        } else {
            answer = "" + booleanToInt(cb1.isChecked()) + booleanToInt(cb2.isChecked()) +
                    booleanToInt(cb3.isChecked()) + booleanToInt(cb4.isChecked()) + booleanToInt(cb5.isChecked()) +
                    booleanToInt(cb6.isChecked()) + booleanToInt(cb7.isChecked()) +
                    booleanToInt(cb9.isChecked());
        }
        return answer;
    }

    private void initCheck() {
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb2.isChecked()) {
                        cb2.setChecked(false);
                    }
                    if (cb3.isChecked()) {
                        cb3.setChecked(false);
                    }
                    if (cb4.isChecked()) {
                        cb4.setChecked(false);
                    }
                    if (cb5.isChecked()) {
                        cb5.setChecked(false);
                    }
                    if (cb6.isChecked()) {
                        cb6.setChecked(false);
                    }
                    if (cb7.isChecked()) {
                        cb7.setChecked(false);
                    }
                    if (cb8.isChecked()) {
                        cb8.setChecked(false);
                    }
                    if (cb9.isChecked()) {
                        cb9.setChecked(false);
                    }

                }
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb1.isChecked()) {
                        cb1.setChecked(false);
                    }
                    if (cb3.isChecked()) {
                        cb3.setChecked(false);
                    }
                    if (cb4.isChecked()) {
                        cb4.setChecked(false);
                    }
                    if (cb5.isChecked()) {
                        cb5.setChecked(false);
                    }
                    if (cb6.isChecked()) {
                        cb6.setChecked(false);
                    }
                    if (cb7.isChecked()) {
                        cb7.setChecked(false);
                    }
                    if (cb8.isChecked()) {
                        cb8.setChecked(false);
                    }
                    if (cb9.isChecked()) {
                        cb9.setChecked(false);
                    }

                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb2.isChecked()) {
                        cb2.setChecked(false);
                    }
                    if (cb1.isChecked()) {
                        cb1.setChecked(false);
                    }
                    if (cb4.isChecked()) {
                        cb4.setChecked(false);
                    }
                    if (cb5.isChecked()) {
                        cb5.setChecked(false);
                    }
                    if (cb6.isChecked()) {
                        cb6.setChecked(false);
                    }
                    if (cb7.isChecked()) {
                        cb7.setChecked(false);
                    }
                    if (cb8.isChecked()) {
                        cb8.setChecked(false);
                    }
                    if (cb9.isChecked()) {
                        cb9.setChecked(false);
                    }

                }
            }
        });
        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb2.isChecked()) {
                        cb2.setChecked(false);
                    }
                    if (cb3.isChecked()) {
                        cb3.setChecked(false);
                    }
                    if (cb1.isChecked()) {
                        cb1.setChecked(false);
                    }
                    if (cb5.isChecked()) {
                        cb5.setChecked(false);
                    }
                    if (cb6.isChecked()) {
                        cb6.setChecked(false);
                    }
                    if (cb7.isChecked()) {
                        cb7.setChecked(false);
                    }
                    if (cb8.isChecked()) {
                        cb8.setChecked(false);
                    }
                    if (cb9.isChecked()) {
                        cb9.setChecked(false);
                    }

                }
            }
        });
        cb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb2.isChecked()) {
                        cb2.setChecked(false);
                    }
                    if (cb3.isChecked()) {
                        cb3.setChecked(false);
                    }
                    if (cb4.isChecked()) {
                        cb4.setChecked(false);
                    }
                    if (cb1.isChecked()) {
                        cb1.setChecked(false);
                    }
                    if (cb6.isChecked()) {
                        cb6.setChecked(false);
                    }
                    if (cb7.isChecked()) {
                        cb7.setChecked(false);
                    }
                    if (cb8.isChecked()) {
                        cb8.setChecked(false);
                    }
                    if (cb9.isChecked()) {
                        cb9.setChecked(false);
                    }

                }
            }
        });
        cb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb2.isChecked()) {
                        cb2.setChecked(false);
                    }
                    if (cb3.isChecked()) {
                        cb3.setChecked(false);
                    }
                    if (cb4.isChecked()) {
                        cb4.setChecked(false);
                    }
                    if (cb5.isChecked()) {
                        cb5.setChecked(false);
                    }
                    if (cb1.isChecked()) {
                        cb1.setChecked(false);
                    }
                    if (cb7.isChecked()) {
                        cb7.setChecked(false);
                    }
                    if (cb8.isChecked()) {
                        cb8.setChecked(false);
                    }
                    if (cb9.isChecked()) {
                        cb9.setChecked(false);
                    }

                }
            }
        });
        cb7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb2.isChecked()) {
                        cb2.setChecked(false);
                    }
                    if (cb3.isChecked()) {
                        cb3.setChecked(false);
                    }
                    if (cb4.isChecked()) {
                        cb4.setChecked(false);
                    }
                    if (cb5.isChecked()) {
                        cb5.setChecked(false);
                    }
                    if (cb6.isChecked()) {
                        cb6.setChecked(false);
                    }
                    if (cb1.isChecked()) {
                        cb1.setChecked(false);
                    }
                    if (cb8.isChecked()) {
                        cb8.setChecked(false);
                    }
                    if (cb9.isChecked()) {
                        cb9.setChecked(false);
                    }

                }
            }
        });
        cb8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb2.isChecked()) {
                        cb2.setChecked(false);
                    }
                    if (cb3.isChecked()) {
                        cb3.setChecked(false);
                    }
                    if (cb4.isChecked()) {
                        cb4.setChecked(false);
                    }
                    if (cb5.isChecked()) {
                        cb5.setChecked(false);
                    }
                    if (cb6.isChecked()) {
                        cb6.setChecked(false);
                    }
                    if (cb7.isChecked()) {
                        cb7.setChecked(false);
                    }
                    if (cb1.isChecked()) {
                        cb1.setChecked(false);
                    }
                    if (cb9.isChecked()) {
                        cb9.setChecked(false);
                    }

                }
            }
        });
        cb9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb2.isChecked()) {
                        cb2.setChecked(false);
                    }
                    if (cb3.isChecked()) {
                        cb3.setChecked(false);
                    }
                    if (cb4.isChecked()) {
                        cb4.setChecked(false);
                    }
                    if (cb5.isChecked()) {
                        cb5.setChecked(false);
                    }
                    if (cb6.isChecked()) {
                        cb6.setChecked(false);
                    }
                    if (cb7.isChecked()) {
                        cb7.setChecked(false);
                    }
                    if (cb8.isChecked()) {
                        cb8.setChecked(false);
                    }
                    if (cb1.isChecked()) {
                        cb1.setChecked(false);
                    }

                }
            }
        });
    }
}
