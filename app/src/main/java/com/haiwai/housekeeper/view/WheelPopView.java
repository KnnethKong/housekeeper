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
import com.haiwai.housekeeper.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WheelPopView extends PopupWindow implements View.OnClickListener {
    LayoutInflater mInflater;
    View contentView;
    TextView tvCancel, tvShow, tvConfirm;
    PickerScrollView mPickerScrollView;
    private PickerScrollView pickerscrlllview; // 滚动选择器
    private List<Pickers> list; // 滚动选择器数据
    private String[] id;
    private String[] name;
    private String dateStr = "";
    private String mShowId = "1";

    public WheelPopView(Context context,String isZhorEn) {
        mInflater = LayoutInflater.from(context);
        contentView = mInflater.inflate(R.layout.pop_wheel_view, null);
        initView(contentView);
        this.setContentView(contentView);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        this.update();
        ColorDrawable dw = new ColorDrawable(0000000000);
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.mypopwindow_anim_style);
        initData(isZhorEn);
    }

    private void initData(String isZhorEn) {
        list = new ArrayList<Pickers>();
        id = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};
        if("en".equals(isZhorEn)){
            name = new String[]{"Infringe upon the rights and interests of others", "Illegal advertising", "Harmful political information", "Violent terrorism", "Obscenity and pornography", "Swindle", "Gambling", "Others"};
        } else {
            name = new String[]{"侵害他人权益", "违规广告", "政治有害类", "暴力、恐怖主义", "淫秽色情类", "诈骗类", "赌博类", "其他有害类"};
        }
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        mPickerScrollView.setData(list);
        mPickerScrollView.setSelected(0);
        tvShow.setText(name[0]);
    }

    private void initView(View contentView) {
        tvCancel = (TextView) contentView.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(this);
        tvShow = (TextView) contentView.findViewById(R.id.tv_show);
        tvConfirm = (TextView) contentView.findViewById(R.id.tv_confirm);
        tvConfirm.setOnClickListener(this);
        mPickerScrollView = (PickerScrollView) contentView.findViewById(R.id.psv);
        mPickerScrollView.setOnSelectListener(pickerListener);
    }

    // 滚动选择器选中事件
    PickerScrollView.onSelectListener pickerListener = new PickerScrollView.onSelectListener() {
        @Override
        public void onSelect(Pickers pickers) {
            String str = pickers.getShowConetnt();
            mShowId = pickers.getShowId();
            tvShow.setText(str);
        }
    };

    public void openPopWindow(View v) {
        //从底部显示
        this.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_cancel) {
            this.dismiss();
        } else if (view.getId() == R.id.tv_confirm) {
            mDatePicOnClickListener.datePicker(mShowId);
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
