package com.haiwai.housekeeper.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.amap.api.maps2d.model.Text;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.RegisterActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.utils.EvmUtil;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.widget.pickerview.OptionsPickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ihope007 on 2016/10/18.
 */
public class View_FWBJ_8 extends LinearLayout {
    private TextView tvtitle;
    private Spinner spinner;
    private TextView et2, et3;
    private TextView et4;
    private List<String> list = new ArrayList<>();
    private LinearLayout llda4;
    private EditText etda5;
    private String answer = "";// TODO: 2016/8/26
    private ArrayAdapter<String> adapter;
    private String strdate = "";
    private String strtime = "";
    private StringBuilder str = new StringBuilder();
    private StringBuilder str2 = new StringBuilder();
    private int selectPosition = 0;

    public View_FWBJ_8(Context context) {
        this(context, null);
    }

    public View_FWBJ_8(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_FWBJ_8(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_fwbj_8, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_fwbj_8_tv_title);
        spinner = (Spinner) findViewById(R.id.view_fwbj_8_sp);
        et2 = (TextView) findViewById(R.id.fwbj_8_et_2);
        et3 = (TextView) findViewById(R.id.fwbj_8_et_3);
        et4 = (TextView) findViewById(R.id.fwbj_8_et_4);
        et2.setInputType(InputType.TYPE_NULL);
        et3.setInputType(InputType.TYPE_NULL);
        etda5 = (EditText) findViewById(R.id.fwbj_8_et_5);
        etda5.setVisibility(GONE);
        et2.requestFocus();
        et3.requestFocus();
        llda4 = (LinearLayout) findViewById(R.id.fwbj_8_ll_da_4);
        llda4.setVisibility(GONE);
        list.clear();
        String question = JsonUtils.getENQuestion(getContext(), 1, 7);
        tvtitle.setText(question);
        list = JsonUtils.getENAnswerList(getContext(), 1, 7);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setDropDownVerticalOffset(EvmUtil.dip2px(getContext(), 30));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectPosition = position;
                switch (position) {
                    case 0:
                    case 1:
                        llda4.setVisibility(GONE);
                        etda5.setVisibility(GONE);
                        break;
                    case 2:
                        llda4.setVisibility(VISIBLE);
                        etda5.setVisibility(GONE);
                        break;
                    case 3:
                        llda4.setVisibility(GONE);
                        etda5.setVisibility(VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        et2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
// 直接创建一个DatePickerDialog对话框实例，并将它显示出来
                Dialog dateDialog = new DatePickerDialog(getContext(),
// 绑定监听器
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker dp, int year,
                                                  int month, int dayOfMonth) {
//                                et2.setText("");
                                str.delete(0, str.length());
                                str.append(year + "-" + String.format("%02d", month + 1) + "-"
                                        + String.format("%02d", dayOfMonth));
//                                strdate=TimeUtils.getTimeStamp(str+"","yyyy-MM-dd ");
                                et2.setText(str);
                            }
                        }
// 设置初始日期
                        , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                        .get(Calendar.DAY_OF_MONTH));
                dateDialog.setTitle(R.string.date_p);
                dateDialog.show();
            }
        });
//        str = new StringBuilder();
        et3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar time = Calendar.getInstance();
                Dialog timeDialog = new TimePickerDialog(
                        getContext(),
// 绑定监听器
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(
                                    TimePicker tp,
                                    int hourOfDay, int minute) {
//                                et3.setText("");
                                str2.delete(0, str2.length());
                                str2.append(String.format("%02d", hourOfDay) + ":"
                                        + String.format("%02d", minute));
//                                strtime = TimeUtils.getTimeStamp(str2 + "", "hh:mm");
                                LogUtil.e("timestamp", strtime);
                                et3.setText(str2);
                            }
                        }
// 设置初始时间
                        , time.get(Calendar.HOUR_OF_DAY), time
                        .get(Calendar.MINUTE)
// true表示采用24小时制
                        , true);
                timeDialog.setTitle(R.string.date_p);
                timeDialog.show();
            }
        });

        et4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showPicker();
            }
        });
    }


    private OptionsPickerView picker;
    private void showPicker(){
        picker = new OptionsPickerView(getContext());
        final ArrayList<String> mList = new ArrayList<>();
        float hours = 0.5f;
        for(int i=1;i<=24;i++){
            if(AppGlobal.getInstance().getLagStr().equals("en")){
                mList.add(hours+"hours");
            }else{
                mList.add(hours+"小时");
            }
            hours+=0.5f;
        }
        picker.setPicker(mList);
        picker.setTitle("");
        picker.setCyclic(false);
        picker.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                et4.setText(mList.get(options1));
            }
        });
        picker.show();
    }


    public void setData(String title, List<String> list2) {
        tvtitle.setText(title);
        list.clear();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public void setData(String title, List<String> list2, String answer) {
        tvtitle.setText(title);
        list.clear();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        String[] strs = answer.split("\\|");
        Log.i("answerInforamtion",answer);
        switch (strs[0]) {
            case "1":
                spinner.setSelection(0, true);
                llda4.setVisibility(GONE);
                etda5.setVisibility(GONE);
                break;
            case "2":
                spinner.setSelection(1, true);
                llda4.setVisibility(GONE);
                etda5.setVisibility(GONE);
                break;
            case "3":
                spinner.setSelection(2, true);

                String[] da = answer.split("\n");
                String mDate = da[1].substring(da[1].indexOf(":")+1,da[1].length());
                String mTime = da[2].substring(da[2].indexOf(":")+1,da[2].length());
                String mLength = da[3].substring(da[3].indexOf(":")+1,da[3].length());


//                String[] times = strs[1].split(":");
                Log.i("strsInformation--",mDate + "--" + mTime + "--" + mLength);
                et2.setText(mDate);
                et3.setText(mTime);
                et4.setText(mLength);
                llda4.setVisibility(GONE);
                etda5.setVisibility(GONE);
                selectPosition = 2;
                break;
            case "4":
                spinner.setSelection(3, true);
//                et2.setText(TimeUtils.getDate(strs[1]));
                llda4.setVisibility(GONE);
                etda5.setVisibility(VISIBLE);
                etda5.setText(strs[1]);
                break;
            case "5":
                spinner.setSelection(4, true);
                etda5.setText(strs[1]);
                llda4.setVisibility(GONE);
                etda5.setVisibility(VISIBLE);
                break;
        }
    }

    public String getQuestion() {
        return tvtitle.getText().toString().trim();
    }

    public boolean getIsEmpty() {
//        Log.i("et2TextInformation--",et2.getText().toString().trim());
        if (2 == spinner.getSelectedItemPosition() || 3 == spinner.getSelectedItemPosition()) {
            if ((et2.getText().toString().trim().equals(getContext().getString(R.string.choose_date)) ||
                    et3.getText().toString().trim().equals(getContext().getString(R.string.choose_time)))
                    && (PlatUtils.getEditStr(etda5))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String getAnswer() {
        if (0 == selectPosition) {
            answer = 1 + "|" + spinner.getSelectedItem();
        } else if (1 == selectPosition) {
            answer = 2 + "|" + spinner.getSelectedItem();
        } else if (2 == selectPosition) {
            answer = 3 + "|" + spinner.getSelectedItem() + "\n"
                    + getContext().getString(R.string.o2o_detail_fwbj8_choose_date) + ":" + et2.getText().toString().trim()
                    + "\n" + getContext().getString(R.string.o2o_detail_fwbj8_choose_time) + ":" + et3.getText().toString().trim()
                    + "\n" + getContext().getString(R.string.o2o_detail_fwbj8_for_long) + ":" + et4.getText().toString().trim();
        } else {
            answer = 4 + "|" + etda5.getText().toString();
        }
        return answer;
    }

//    public String getAnswer() {
//        if (3 == spinner.getSelectedItemPosition()) {
//            String e2 = TimeUtils.getTimeStamp(str + "", "yyyy-MM-dd");
//            String e3 = TimeUtils.getTimeStamp(str.toString() + " " + str2.toString(), "yyyy-MM-dd hh:mm");
//            LogUtil.e("str++", str.toString() + " " + str2.toString());
//            answer = 4 + "|" + e2 + "|" + e3 + "|" + et4.getText().toString().trim();
//        } else if (4 == spinner.getSelectedItemPosition()) {
//            answer = 5 + "|" + etda5.getText().toString().trim();
//        } else if (0 == spinner.getSelectedItemPosition()) {
//            answer = 1 + "|" + spinner.getSelectedItem();
//        } else if (1 == spinner.getSelectedItemPosition()) {
//            answer = 2 + "|" + spinner.getSelectedItem();
//        } else if (2 == spinner.getSelectedItemPosition()) {
//            answer = 3 + "|" + spinner.getSelectedItem();
//        }
//        return answer;
//    }
}
