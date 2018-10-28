package com.haiwai.housekeeper.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
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
public class View_WXJG_3 extends LinearLayout {
    private TextView tvtitle;
    private EditText etother;
    private String answer = "";

    public View_WXJG_3(Context context) {
        this(context, null);
    }

    public View_WXJG_3(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_WXJG_3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_wxjg_3, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_wxjg_3_tv_title1);
        etother = (EditText) findViewById(R.id.view_wxjg_3_et_other);
    }

    /**
     * 0:可以通过   1:请至少选择一项   2:请补全信息
     * type:0表示必填  1表示可以跳过
     */
    public int getIsEmptyState(int type) {
        if (0 == type) {
            if (PlatUtils.getEditStr(etother)) {
                return 2;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public void setData(String title, String hint) {
        tvtitle.setText(title);
        etother.setHint(hint);
    }

    public void setData(String title, String hint, String answer) {
        tvtitle.setText(title);
        etother.setText(answer);
    }
    public void setData1(String title, String hint) {
        etother.setHint(hint);
        tvtitle.setText(title);
    }
    public void setData1(String title, String hint, String answer) {
        tvtitle.setText(title);
        etother.setHint(hint);
//        etother.setText(answer);
    }

    public void setData2(String title, String hint) {
        etother.setHint(hint);
        tvtitle.setText(title);
    }
    public void setData2(String title, String hint, String answer) {
        tvtitle.setText(title);
        etother.setHint(hint);
//        etother.setText(answer);
    }


    public String getQuestion() {
        return tvtitle.getText().toString().trim();
    }

    public String getAnswer() {
        if(TextUtils.isEmpty(etother.getText().toString().trim())){
            answer="";
        }else{
            answer = etother.getText().toString().trim();
        }
        return answer;
    }
}
