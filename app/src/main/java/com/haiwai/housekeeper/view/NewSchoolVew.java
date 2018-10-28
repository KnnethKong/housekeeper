package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.widget.pickerview.OptionsPickerView;
import com.haiwai.housekeeper.widget.pickerview.listener.OnDismissListener;

import java.util.ArrayList;

/**
 * Created by ihope10 on 2016/9/29.
 */
public class NewSchoolVew extends LinearLayout {
    public EditText etSchName;
    private EditText etStartTime, etEndTime;
    private TextView tvDelBtn,etSch;
    private Context mContext;

    public NewSchoolVew(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public NewSchoolVew(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewSchoolVew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        View.inflate(context, R.layout.pro_skill_show_second_item_layout, this);
        initView();
    }

    private void initView() {
        etSchName = (EditText) findViewById(R.id.et_school_name);
        etSch = (TextView) findViewById(R.id.et_school);
        etStartTime = (EditText) findViewById(R.id.et_start_time);
        etEndTime = (EditText) findViewById(R.id.et_end_time);
        tvDelBtn = (TextView) findViewById(R.id.tv_del_school);

        etSch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showWin();
            }
        });
    }
    private void showWin(){
        final ArrayList<String> list = new ArrayList<>();
        if(AppGlobal.getInstance().getLagStr().equals("en")){
            list.add("Middle school and below");
            list.add("Senior high school");
            list.add("Secondary technical school");
            list.add("Junior college");
            list.add("Bachelor degree");
            list.add("Master degree and above");
        }else{
            list.add("初中以下");
            list.add("高中");
            list.add("中专/技能");
            list.add("大专");
            list.add("本科");
            list.add("硕士以上");
        }

        OptionsPickerView pickerView = new OptionsPickerView(mContext);
        pickerView.setPicker(list);
        pickerView.setTitle("");
        pickerView.setCyclic(false);
        pickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                etSch.setText(list.get(options1));
            }
        });
        pickerView.show();
    }

    public void setSchName(String str){
        etSchName.setText(str);
    }

    public void setEduName(String str){
        etSch.setText(str);
    }

    public String getNameString() {
        return etSchName.getText().toString().trim();
    }

    public String getSchString() {
        return etSch.getText().toString().trim();
    }

    public String getStartString() {
        return etStartTime.getText().toString().trim();
    }

    public String getEndString() {
        return etEndTime.getText().toString().trim();
    }

    public void setOnClicklister(OnClickListener mOnClickListener) {
        etStartTime.setOnClickListener(mOnClickListener);
        etEndTime.setOnClickListener(mOnClickListener);
        tvDelBtn.setOnClickListener(mOnClickListener);
    }

    public View getStarView() {
        return etStartTime;
    }

    public View getEndView() {
        return etEndTime;
    }

    public View getDelView() {
        return tvDelBtn;
    }

    public void setStrSting(String str) {
        etStartTime.setText(str);
    }

    public void setEndString(String str) {
        etEndTime.setText(str);
    }

}
