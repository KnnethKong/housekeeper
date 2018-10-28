package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope006 on 2016/12/13.
 */

public class ConfigView extends LinearLayout {
    public TextView tv_conf_addr, tv_prc_month, tv_prc_state;
    public LinearLayout ib_order_btn;

    public ConfigView(Context context) {
        this(context, null);
    }

    public ConfigView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ConfigView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.address_order_layout, this);
        initView();
    }

    private void initView() {
        tv_conf_addr = (TextView) findViewById(R.id.tv_conf_addr);
        tv_prc_month = (TextView) findViewById(R.id.tv_prc_month);
        ib_order_btn = (LinearLayout) findViewById(R.id.ib_order_btn);
        tv_prc_state = (TextView) findViewById(R.id.tv_prc_state);
    }

    public void setTexts(String str1, String str2) {
        tv_conf_addr.setText(str1);
        tv_prc_month.setText(str2 + getContext().getString(R.string.conf_dw));
    }

    public void setConfAddr(String str) {
        tv_conf_addr.setText(str);
    }

    public void setConfPrc(String str) {
        tv_prc_month.setText(str);

    }

    public void setState(String text){
        tv_prc_state.setText(text);
    }

    public void setVisView(boolean isVis){
        if(isVis){
            tv_prc_state.setVisibility(View.GONE);
            tv_prc_month.setText(getContext().getString(R.string.do_next_month));
        }else{
            tv_prc_state.setVisibility(View.VISIBLE);
//            tv_prc_month.setText("新订单下月开始执行");
        }
    }

    public void setState(String num, String isZhorEn) {
        tv_prc_state.setText(getNumState(num, isZhorEn));
        if ("0".equals(num)) {
            tv_prc_state.setTextColor(getResources().getColor(R.color.gray));
        } else if ("1".equals(num)) {
            tv_prc_state.setTextColor(getResources().getColor(R.color.gray));
        } else if ("2".equals(num)) {
            tv_prc_state.setTextColor(getResources().getColor(R.color.black));
        } else if ("3".equals(num)) {
            tv_prc_state.setTextColor(getResources().getColor(R.color.red));
        }

    }

    public LinearLayout getImgBtn() {
        return ib_order_btn;
    }

    public String getNumState(String num, String isZhorEn) {
        String str = null;
        switch (num) {
            case "0":
                if ("en".equals(isZhorEn)) {
                    str = "To be endorsed";
                } else {
                    str = "待审核";
                }

                break;
            case "1":
                if ("en".equals(isZhorEn)) {
                    str = "In service";
                } else {
                    str = "进行中";
                }
                break;
            case "2":
                if ("en".equals(isZhorEn)) {
                    str = "The audit did not pass";
                } else {
                    str = "审核未通过";
                }
                break;
            case "3":
                if ("en".equals(isZhorEn)) {
                    str = "Suspended";
                } else {
                    str = "已暂停";
                }
                break;
        }
        return str;
    }
}
