package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
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
public class View_GJFX_2 extends LinearLayout {
    private TextView tvtitle;
    private EditText etother1, etother2, etother3;
    private CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10, cb11, cb12, cb13;
    private String answer = "";

    public View_GJFX_2(Context context) {
        this(context, null);
    }

    public View_GJFX_2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_GJFX_2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_gjfx_2, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_gjfx_2_tv_title);
        etother1 = (EditText) findViewById(R.id.view_gjfx_2_et_other1);
        etother2 = (EditText) findViewById(R.id.view_gjfx_2_et_other2);
        etother3 = (EditText) findViewById(R.id.view_gjfx_2_et_other3);
        cb1 = (CheckBox) findViewById(R.id.view_gjfx_2_cb_1);
        cb2 = (CheckBox) findViewById(R.id.view_gjfx_2_cb_2);
        cb3 = (CheckBox) findViewById(R.id.view_gjfx_2_cb_3);
        cb4 = (CheckBox) findViewById(R.id.view_gjfx_2_cb_4);
        cb5 = (CheckBox) findViewById(R.id.view_gjfx_2_cb_5);
        cb6 = (CheckBox) findViewById(R.id.view_gjfx_2_cb_6);
        cb7 = (CheckBox) findViewById(R.id.view_gjfx_2_cb_7);
        cb8 = (CheckBox) findViewById(R.id.view_gjfx_2_cb_8);
        cb9 = (CheckBox) findViewById(R.id.view_gjfx_2_cb_9);
        cb10 = (CheckBox) findViewById(R.id.view_gjfx_2_cb_10);
        cb11 = (CheckBox) findViewById(R.id.view_gjfx_2_cb_11);
        cb12 = (CheckBox) findViewById(R.id.view_gjfx_2_cb_12);
        cb13 = (CheckBox) findViewById(R.id.view_gjfx_2_cb_13);
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String r5,
                        String r6, String r7, String r8, String r9, String r10, String r11,
                        String r12, String r13) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb5.setText(r5);
        etother1.setHint(r6);
        cb7.setText(r7);
        etother2.setHint(r8);
        cb9.setText(r9);
        cb10.setText(r10);
        cb11.setText(r11);
        cb12.setText(r12);
        etother3.setHint(r13);
    }

    public void setData(String title, String r1, String r2, String r3, String r4, String r5,
                        String r6, String r7, String r8, String r9, String r10, String r11,
                        String r12, String r13, String answer) {
        tvtitle.setText(title);
        cb1.setText(r1);
        cb2.setText(r2);
        cb3.setText(r3);
        cb4.setText(r4);
        cb5.setText(r5);
        etother1.setHint(r6);
        cb7.setText(r7);
        etother2.setHint(r8);
        cb9.setText(r9);
        cb10.setText(r10);
        cb11.setText(r11);
        cb12.setText(r12);
        etother3.setHint(r13);
        String[] strs = answer.split("\\|");
        if ("1".equals(strs[0].substring(0, 1))) {
            cb1.setChecked(true);
        }
        if ("1".equals(strs[0].substring(1, 2))) {
            cb2.setChecked(true);
        }
        if ("1".equals(strs[0].substring(2, 3))) {
            cb3.setChecked(true);
        }
        if ("1".equals(strs[0].substring(3, 4))) {
            cb4.setChecked(true);
        }
        if ("1".equals(strs[0].substring(4, 5))) {
            cb5.setChecked(true);
        }
        if ("1".equals(strs[0].substring(5, 6))) {
            cb6.setChecked(true);
            etother1.setText(strs[1].substring(3, strs[1].length()));
        }
        if ("1".equals(strs[0].substring(6, 7))) {
            cb7.setChecked(true);
        }
        if ("1".equals(strs[0].substring(7, 8))) {
            cb8.setChecked(true);
            etother2.setText(strs[2].substring(3, strs[2].length()));
        }
        if ("1".equals(strs[0].substring(8, 9))) {
            cb9.setChecked(true);
        }
        if ("1".equals(strs[0].substring(9, 10))) {
            cb10.setChecked(true);
        }
        if ("1".equals(strs[0].substring(10, 11))) {
            cb11.setChecked(true);
        }
        if ("1".equals(strs[0].substring(11, 12))) {
            cb12.setChecked(true);
        }
        if ("1".equals(strs[0].substring(12, 13))) {
            cb13.setChecked(true);
            etother3.setText(strs[3].substring(3, strs[3].length()));
        }

    }

    /**
     * 0:可以通过   1:请至少选择一项   2:请补全信息
     */
    public int getIsEmptyState() {
        if (cb1.isChecked() || cb2.isChecked() || cb3.isChecked() || cb4.isChecked() || cb5.isChecked() ||
                cb6.isChecked() || cb7.isChecked() || cb8.isChecked() || cb9.isChecked() || cb10.isChecked() ||
                cb11.isChecked() || cb12.isChecked() || cb13.isChecked()) {
            if ((cb6.isChecked() && PlatUtils.getEditStr(etother1)) || (cb8.isChecked() && PlatUtils.getEditStr(etother2)) ||
                    (cb13.isChecked() && PlatUtils.getEditStr(etother3))) {
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
        answer = "" + booleanToInt(cb1.isChecked()) + booleanToInt(cb2.isChecked()) +
                booleanToInt(cb3.isChecked()) + booleanToInt(cb4.isChecked()) + booleanToInt(cb5.isChecked()) +
                booleanToInt(cb6.isChecked()) + booleanToInt(cb7.isChecked()) + booleanToInt(cb8.isChecked()) +
                booleanToInt(cb9.isChecked()) + booleanToInt(cb10.isChecked()) + booleanToInt(cb11.isChecked()) +
                booleanToInt(cb12.isChecked()) + booleanToInt(cb13.isChecked()) + "|str" + etother1.getText().toString().trim() + "|str" +
                etother2.getText().toString().trim() + "|str" + etother3.getText().toString().trim();
        return answer;
    }
}
