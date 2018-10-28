package com.haiwai.housekeeper.view;

import android.content.Context;
import android.text.TextUtils;
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
public class View_DTQX_4 extends LinearLayout {
    private TextView tvtitle;
    private CheckBox cb1, cb2, cb3, cb4, cb5, cb6;
    private String answer = "";
    private EditText etother;

    public View_DTQX_4(Context context) {
        this(context, null);
    }

    public View_DTQX_4(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_DTQX_4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_dtqx_4, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_dtqx_4_tv_title);
        etother = (EditText) findViewById(R.id.view_dtqx_4_et_other);
        cb1 = (CheckBox) findViewById(R.id.view_dtqx_4_cb_1);
        cb2 = (CheckBox) findViewById(R.id.view_dtqx_4_cb_2);
        cb3 = (CheckBox) findViewById(R.id.view_dtqx_4_cb_3);
        cb4 = (CheckBox) findViewById(R.id.view_dtqx_4_cb_4);
        cb5 = (CheckBox) findViewById(R.id.view_dtqx_4_cb_5);
        cb6 = ((CheckBox) findViewById(R.id.view_dtqx_4_cb_6));
//        etother.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                cb5.setChecked(true);
//            }
//        });
        etother.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                cb5.setChecked(true);
                return false;
            }
        });
    }


    public void setData7(String title, String r1, String r2, String r3, String r4, String r5) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb6.setText(r5);
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb6.isChecked()) {
                        cb6.setChecked(false);
                    }
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
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb1.isChecked()) {
                        cb1.setChecked(false);
                    }
                }
            }
        });
        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb1.isChecked()) {
                        cb1.setChecked(false);
                    }
                }
            }
        });
        cb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb1.isChecked()) {
                        cb1.setChecked(false);
                    }
                }
            }
        });
    }



    public void setData5(String title, String r1, String r2, String r3, String r4, String r5) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb6.setText(r5);
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb1.isChecked()) {
                        cb1.setChecked(false);
                    }
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
                }
            }
        });
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb6.isChecked()) {
                        cb6.setChecked(false);
                    }
                }
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb6.isChecked()) {
                        cb6.setChecked(false);
                    }
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb6.isChecked()) {
                        cb6.setChecked(false);
                    }
                }
            }
        });
        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb6.isChecked()) {
                        cb6.setChecked(false);
                    }
                }
            }
        });
    }

    public void setData5(String title, String r1, String r2, String r3, String r4, String r5, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb6.setText(r5);
        if ("1".equals(answer.substring(0, 1))) {
            cb6.setChecked(true);
        }
        if ("1".equals(answer.substring(1, 2))) {
            cb1.setChecked(true);
        }
        if ("1".equals(answer.substring(2, 3))) {
            cb2.setChecked(true);
        }
        if ("1".equals(answer.substring(3, 4))) {
            cb3.setChecked(true);
        }
        if ("1".equals(answer.substring(4, 5))) {
            cb4.setChecked(true);
        }
        if ("1".equals(answer.substring(5, 6))) {
            cb5.setChecked(true);
            etother.setText(answer.substring(5, answer.length()));
        }
        cb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb1.isChecked()) {
                        cb1.setChecked(false);
                    }
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
                }
            }
        });
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb6.isChecked()) {
                        cb6.setChecked(false);
                    }
                }
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb6.isChecked()) {
                        cb6.setChecked(false);
                    }
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb6.isChecked()) {
                        cb6.setChecked(false);
                    }
                }
            }
        });
        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb6.isChecked()) {
                        cb6.setChecked(false);
                    }
                }
            }
        });
    }


    public void setData1(String title, String r1, String r2, String r3, String r4, String r5) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb6.setText(r5);
        cb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb1.isChecked()) {
                        cb1.setChecked(false);
                    }
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
                }
            }
        });
    }

    public void setData1(String title, String r1, String r2, String r3, String r4, String r5, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb6.setText(r5);
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
            etother.setText(answer.substring(4, answer.length()));
        }
        cb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb1.isChecked()) {
                        cb1.setChecked(false);
                    }
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
                }
            }
        });
    }


    public void setData(String title, String r1, String r2, String r3, String r4) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb6.setVisibility(View.GONE);
        findViewById(R.id.line6).setVisibility(View.GONE);
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
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb1.isChecked()) {
                        cb1.setChecked(false);
                    }
                }
            }
        });
        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb1.isChecked()) {
                        cb1.setChecked(false);
                    }
                }
            }
        });
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb6.setVisibility(View.GONE);
        findViewById(R.id.line6).setVisibility(View.GONE);
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
            etother.setText(answer.substring(5, answer.length()));
        }
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
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb1.isChecked()) {
                        cb1.setChecked(false);
                    }
                }
            }
        });
        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (cb1.isChecked()) {
                        cb1.setChecked(false);
                    }
                }
            }
        });
    }


    public void setData3(String title, String r1, String r2, String r3, String r4) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb6.setVisibility(View.GONE);
        findViewById(R.id.line6).setVisibility(View.GONE);
//        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b){
//                    if(cb2.isChecked()){
//                        cb2.setChecked(false);
//                    }
//                    if(cb3.isChecked()){
//                        cb3.setChecked(false);
//                    }
//                    if(cb4.isChecked()){
//                        cb4.setChecked(false);
//                    }
//                    if(cb5.isChecked()){
//                        cb5.setChecked(false);
//                    }
//                }
//            }
//        });
    }

    public void setData3(String title, String r1, String r2, String r3, String r4, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb6.setVisibility(View.GONE);
        findViewById(R.id.line6).setVisibility(View.GONE);
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
            etother.setText(answer.substring(4, answer.length()));
        }
    }


    public void setData4(String title, String r1, String r2, String r3, String r4, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb6.setVisibility(View.GONE);
        findViewById(R.id.line6).setVisibility(View.GONE);
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
            etother.setText(answer.substring(5, answer.length()));
        }
    }


    public void setData2(String title, String r1, String r2, String r3, String r4) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb6.setVisibility(View.GONE);
        findViewById(R.id.line6).setVisibility(View.GONE);
//        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b){
//                    if(cb2.isChecked()){
//                        cb2.setChecked(false);
//                    }
//                    if(cb3.isChecked()){
//                        cb3.setChecked(false);
//                    }
//                    if(cb4.isChecked()){
//                        cb4.setChecked(false);
//                    }
//                    if(cb5.isChecked()){
//                        cb5.setChecked(false);
//                    }
//                }
//            }
//        });
    }

    public void setData2(String title, String r1, String r2, String r3, String r4, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb6.setVisibility(View.GONE);
        findViewById(R.id.line6).setVisibility(View.GONE);
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
            etother.setText(answer.substring(4, answer.length()));
        }
    }

    public void setData6(String title, String r1, String r2, String r3, String r4, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb6.setVisibility(View.GONE);
        findViewById(R.id.line6).setVisibility(View.GONE);
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
            etother.setText(answer.substring(5, answer.length()));
        }
    }


    public int booleanToInt(boolean a) {
        int i = a == true ? 1 : 0;
        return i;
    }

    /**
     * 0:可以通过   1:请至少选择一项   2:请补全信息
     * type:0表示必填  1表示可以跳过
     */
    public boolean is6Select() {
        if (cb5.isChecked() && etother.getText().toString().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public int getIsEmptyState(int type) {
        if (cb1.isChecked() || cb2.isChecked() || cb3.isChecked() || cb4.isChecked() || cb5.isChecked() || cb6.isChecked()) {
            if (cb5.isChecked() && TextUtils.isEmpty(etother.getText().toString().trim())) {
                return 2;
            }
            return 0;
        } else {
            return 1;
        }

    }

    public String getQuestion() {
        return tvtitle.getText().toString().trim();
    }

    public String getAnswer() {
        if (cb5.isChecked()) {
            answer = "" + booleanToInt(cb1.isChecked()) + booleanToInt(cb2.isChecked())
                    + booleanToInt(cb3.isChecked()) + booleanToInt(cb4.isChecked())
                    + booleanToInt(cb5.isChecked()) + etother.getText().toString().trim();
        } else {
            answer = "" + booleanToInt(cb1.isChecked()) + booleanToInt(cb2.isChecked())
                    + booleanToInt(cb3.isChecked()) + booleanToInt(cb4.isChecked()) + booleanToInt(cb5.isChecked());
        }
        return answer;
    }

    public String getAnswer1() {
        if (cb5.isChecked()) {
            answer = "" + booleanToInt(cb1.isChecked()) + booleanToInt(cb2.isChecked())
                    + booleanToInt(cb3.isChecked()) + booleanToInt(cb4.isChecked())
                    + booleanToInt(cb6.isChecked()) + booleanToInt(cb5.isChecked()) + etother.getText().toString().trim();
        } else {
            answer = "" + booleanToInt(cb1.isChecked()) + booleanToInt(cb2.isChecked())
                    + booleanToInt(cb3.isChecked()) + booleanToInt(cb4.isChecked()) + booleanToInt(cb6.isChecked()) + booleanToInt(cb5.isChecked());
        }
        return answer;
    }

}
