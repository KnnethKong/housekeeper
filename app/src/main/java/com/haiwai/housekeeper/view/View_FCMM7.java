package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by range on 2017/10/10.
 */

public class View_FCMM7 extends LinearLayout {//禁止复用
    private TextView tvtitle;
    private TextView tvspinner;
    private Spinner spinner;
    private RadioButton rb1, rb2, rb3;
    private String answer = "";
    private String isZhorEn = "";

    private ArrayAdapter<String> adapter;
    private List<String> list = new ArrayList<>();

    public View_FCMM7(Context context) {
        this(context, null);
    }

    public View_FCMM7(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_FCMM7(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_bxxd_3, this);
        initView();
    }

    private void initView() {
        isZhorEn = AppGlobal.getInstance().getLagStr();

        tvtitle = (TextView) findViewById(R.id.view_bxxd_3_tv_title);
        tvspinner = (TextView) findViewById(R.id.view_bxxd_3_tv_spinner);
        spinner = (Spinner) findViewById(R.id.view_bxxd_3_sp);
        rb1 = (RadioButton) findViewById(R.id.view_bxxd_3_cb_1);
        rb2 = (RadioButton) findViewById(R.id.view_bxxd_3_cb_2);
        rb3 = (RadioButton) findViewById(R.id.view_bxxd_3_cb_3);
        tvspinner.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.performClick();
            }
        });
        list.clear();

        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(getContext(), 27, 6);
            tvtitle.setText(question);
            list.add("5公里");
            list.add("10公里");
            list.add("15公里");
            list.add("25公里");
            list.add("50公里");
            List<String> answerList = JsonUtils.getZhAnswerList(getContext(),27, 6);
            rb1.setText(answerList.get(0));
            rb2.setText(answerList.get(1));
            rb3.setText(answerList.get(2));

        } else {
            String question = JsonUtils.getENQuestion(getContext(), 27, 6);
            tvtitle.setText(question);
            list.add("5miles");
            list.add("10miles");
            list.add("15miles");
            list.add("25miles");
            list.add("50miles");
            List<String> answerList = JsonUtils.getENAnswerList(getContext(), 27, 6);
            rb1.setText(answerList.get(0));
            rb2.setText(answerList.get(1));
            rb3.setText(answerList.get(2));
        }

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
//        spinner.setDropDownVerticalOffset(EvmUtil.dip2px(getContext(), 40));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvspinner.setText(list.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tvspinner.setText(spinner.getSelectedItem() + "");
    }

    public void setData(String answer) {
        if ("1".equals(answer.substring(0, 1))) {
            rb1.setChecked(true);
            setSpinnerItemSelectedByValue(spinner, answer.substring(3, answer.length()));
        }
        if ("1".equals(answer.substring(1, 2))) {
            rb2.setChecked(true);
        }
        if ("1".equals(answer.substring(2, 3))) {
            rb3.setChecked(true);
        }



    }

    /**
     * 0:可以通过   1:请至少选择一项   2:请补全信息
     */
    public int getIsEmptyState() {
        if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
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
        if (rb1.isChecked()) {
            answer = "" + booleanToInt(rb1.isChecked()) + booleanToInt(rb2.isChecked()) +
                    +booleanToInt(rb3.isChecked()) + tvspinner.getText().toString();
        } else {
            answer = "" + booleanToInt(rb1.isChecked()) + booleanToInt(rb2.isChecked()) +
                    +booleanToInt(rb3.isChecked());
        }
        return answer;
    }

    public static void setSpinnerItemSelectedByValue(Spinner spinner, String value) {
        SpinnerAdapter apsAdapter = spinner.getAdapter(); //得到SpinnerAdapter对象
        int k = apsAdapter.getCount();
        for (int i = 0; i < k; i++) {
            if (value.equals(apsAdapter.getItem(i).toString())) {
                spinner.setSelection(i, true);// 默认选中项
                break;
            }
        }
    }
}
