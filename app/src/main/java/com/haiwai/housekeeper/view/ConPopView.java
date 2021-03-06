package com.haiwai.housekeeper.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope10 on 2016/11/11.
 */

public class ConPopView extends PopupWindow {
    private Context mContext;
    private LayoutInflater mInflater;
    private String mContent;
    private TextFontView tv_content;
    private View popView;

    public ConPopView(Activity context, String content) {
        this.mContext = context;
        mContent = content;
        mInflater = LayoutInflater.from(context);
        initView(context);
    }

    private void initView(Context context) {
        popView = mInflater.inflate(R.layout.pro_pop_con_layout, null);
        tv_content = (TextFontView) popView.findViewById(R.id.tv_content);
        tv_content.setText(mContent);
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
