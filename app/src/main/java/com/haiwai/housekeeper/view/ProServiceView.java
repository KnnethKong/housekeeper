package com.haiwai.housekeeper.view;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;

/**
 * Created by lyj on 2016/9/23.
 */
public class ProServiceView extends LinearLayout {
    public static final String TYPEONE = "ONE";
    public static final String TYPETWO = "TWO";
    public static final String TYPETHREE = "THREE";

    private FrameLayout flSerLayout;
    private TextView tvSerTite, tvSerWarn, tvSerFinishTime, tvSerRightTime;
    private LinearLayout llSerLayout;
    private TextView ser_ib_btn;

    public ProServiceView(Context context) {
        this(context, null);
    }

    public ProServiceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProServiceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.pro_week_work_common_service_item_layout, this);
        initView();
    }

    private void initView() {
        flSerLayout = (FrameLayout) findViewById(R.id.ser_fl_layout);
        tvSerTite = (TextView) findViewById(R.id.ser_tv_title);//第一服务
        tvSerWarn = (TextView) findViewById(R.id.ser_tv_warn);//请于03.8-03.15完成
        tvSerFinishTime = (TextView) findViewById(R.id.ser_ll_tv_time);//2016.03.07
        tvSerRightTime = (TextView) findViewById(R.id.ser_tv_time);//03.16-03.24
        llSerLayout = (LinearLayout) findViewById(R.id.ser_ll_layout);
        ser_ib_btn = (TextView) findViewById(R.id.ser_ib_btn);
    }

    public void setBtnOnClickListener(OnClickListener onClickListener) {
        ser_ib_btn.setOnClickListener(onClickListener);
    }

    public TextView getClickView() {
        return ser_ib_btn;
    }


    public void showDemo(String type, String tvSerTitleText, String tvSerWarnText,
                         String tvSerFinishTimeText, String tvSerRightTimeText) {
        if (!TextUtils.isEmpty(tvSerTitleText)) {
            if (TYPEONE.equals(type)) {//第一
                flSerLayout.setVisibility(View.GONE);
                tvSerTite.setText(tvSerTitleText);
                tvSerTite.setTextColor(Color.parseColor("#565a5c"));
                tvSerWarn.setVisibility(View.GONE);
                tvSerWarn.setText(tvSerWarnText);
                tvSerWarn.setTextColor(Color.parseColor("#565a5c"));
                llSerLayout.setVisibility(View.VISIBLE);
                tvSerFinishTime.setText(tvSerFinishTimeText);
                tvSerFinishTime.setTextColor(Color.parseColor("#01d1c1"));
                ser_ib_btn.setVisibility(View.GONE);
                tvSerRightTime.setVisibility(View.GONE);
                tvSerRightTime.setText(tvSerRightTimeText);
                tvSerRightTime.setTextColor(Color.parseColor("#565a5c"));
            } else if (TYPETWO.equals(type)) {//第二
                flSerLayout.setVisibility(View.VISIBLE);
                tvSerTite.setText(tvSerTitleText);
                tvSerTite.setTextColor(Color.parseColor("#FFFFFF"));
                tvSerWarn.setVisibility(View.VISIBLE);
                tvSerWarn.setText(tvSerWarnText);
                tvSerWarn.setTextColor(Color.parseColor("#FFFFFF"));
                llSerLayout.setVisibility(View.GONE);
                tvSerFinishTime.setText(tvSerFinishTimeText);
                tvSerFinishTime.setTextColor(Color.parseColor("#01d1c1"));
                ser_ib_btn.setVisibility(View.VISIBLE);
                tvSerRightTime.setVisibility(View.GONE);
                tvSerRightTime.setText(tvSerRightTimeText);
                tvSerRightTime.setTextColor(Color.parseColor("#565a5c"));
            } else if (TYPETHREE.equals(type)) {    //第三
                flSerLayout.setVisibility(View.GONE);
                tvSerTite.setText(tvSerTitleText);
                tvSerTite.setTextColor(Color.parseColor("#565a5c"));
                tvSerWarn.setVisibility(View.GONE);
                tvSerWarn.setText(tvSerWarnText);
                tvSerWarn.setTextColor(Color.parseColor("#565a5c"));
                llSerLayout.setVisibility(View.GONE);
                tvSerFinishTime.setText(tvSerFinishTimeText);
                tvSerFinishTime.setTextColor(Color.parseColor("#01d1c1"));
                ser_ib_btn.setVisibility(View.GONE);
                tvSerRightTime.setVisibility(View.VISIBLE);
                tvSerRightTime.setText(tvSerRightTimeText);
                tvSerRightTime.setTextColor(Color.parseColor("#565a5c"));

            }
        }
    }
}
