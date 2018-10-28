package com.haiwai.housekeeper.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope10 on 2016/11/11.
 */

public class ConPopView1 extends PopupWindow {
    private Context mContext;
    private LayoutInflater mInflater;
    private TextFontView tv_content;
    private View popView;

    public ConPopView1(Activity context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        initView(context);
    }

    private void initView(Context context) {
        popView = mInflater.inflate(R.layout.pro_pop2_con_layout1, null);
        tv_content = (TextFontView) popView.findViewById(R.id.tv_content);
        this.setContentView(popView);
        this.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        ColorDrawable cd = new ColorDrawable(0000000000);
        this.setBackgroundDrawable(cd);
        this.setAnimationStyle(R.style.AlphaAnimation);
    }

    public void showPopUpWindow(View parent,int xoff,int yoff) {
        if (!this.isShowing()) {
            this.showAsDropDown(parent, xoff, yoff);
        } else {
            this.dismiss();
        }
    }


}
