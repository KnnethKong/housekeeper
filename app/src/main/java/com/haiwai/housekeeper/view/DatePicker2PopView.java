package com.haiwai.housekeeper.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.entity.Pickers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatePicker2PopView extends PopupWindow implements View.OnClickListener {
    LayoutInflater mInflater;
    View contentView;
    TextView tvCancel, tvShow, tvConfirm;
    PickerScrollView mPickerScrollView;
    private PickerScrollView pickerscrlllview; // 滚动选择器
    private List<Pickers> list; // 滚动选择器数据
    private String[] id;
    private String[] name;
    private String dateStr = "";

    public DatePicker2PopView(Context context) {
        mInflater = LayoutInflater.from(context);
        contentView = mInflater.inflate(R.layout.pop_date_wheel_view, null);
        initView(contentView);
        this.setContentView(contentView);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        this.update();
        ColorDrawable dw = new ColorDrawable(0000000000);
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.mypopwindow_anim_style);
    }

    private void initData() {
        list = new ArrayList<Pickers>();
        id = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        name = new String[]{"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        mPickerScrollView.setData(list);
        mPickerScrollView.setSelected(0);
    }

    private void initView(View contentView) {
        tvCancel = (TextView) contentView.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(this);
        tvShow = (TextView) contentView.findViewById(R.id.tv_show);
        tvShow.setText(getCurrentDate(System.currentTimeMillis()));
        tvConfirm = (TextView) contentView.findViewById(R.id.tv_confirm);
        tvConfirm.setOnClickListener(this);
        mPickerScrollView = (PickerScrollView) contentView.findViewById(R.id.psv);
        mPickerScrollView.setOnSelectListener(pickerListener);
    }

    // 滚动选择器选中事件
    PickerScrollView.onSelectListener pickerListener = new PickerScrollView.onSelectListener() {
        @Override
        public void onSelect(Pickers pickers) {
            String str = tvShow.getText().toString().trim();
            String[] strs = str.split("-");
            str = strs[0] + "-" + pickers.getShowId();
            tvShow.setText(str);
        }
    };

    public void openPopWindow(View v) {
        //从底部显示
        this.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
    }


    public static String getCurrentDate(long cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        re_StrTime = sdf.format(new Date(cc_time));
        return re_StrTime;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_cancel) {
            this.dismiss();
        } else if (view.getId() == R.id.tv_confirm) {
            dateStr = tvShow.getText().toString().trim();
            mDatePicOnClickListener.datePicker(dateStr);
            this.dismiss();
        }
    }

    /**
     * 初始化，将日期传出去
     */
    public DatePicOnClickListener mDatePicOnClickListener;

    public interface DatePicOnClickListener {
        void datePicker(String str);
    }

    public void setDatePicOnClickListener(DatePicOnClickListener datePicOnClickListener) {
        this.mDatePicOnClickListener = datePicOnClickListener;
    }

}
