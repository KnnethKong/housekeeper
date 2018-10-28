package com.haiwai.housekeeper.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope10 on 2016/11/11.
 */

public class ConPopBig5View extends PopupWindow {
    private Context mContext;
    private LayoutInflater mInflater;
    private String mContent;
    private TextFontView tv_content;
    private View popView;
    public ImageView iv_diss;

    public ConPopBig5View(Activity context, String content) {
        this.mContext = context;
        mContent = content;
        mInflater = LayoutInflater.from(context);
        initView(context);
    }

    private void initView(Context context) {
        popView = mInflater.inflate(R.layout.pro_pop_big5_layout, null);
        iv_diss = (ImageView) popView.findViewById(R.id.iv_diss);
        this.setContentView(popView);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        ColorDrawable cd = new ColorDrawable(0000000000);
        this.setBackgroundDrawable(cd);
        this.setAnimationStyle(R.style.AlphaAnimation);
    }

    public ImageView getIv_diss() {
        return iv_diss;
    }

    public void setOnClick(View.OnClickListener onClickListener) {
        iv_diss.setOnClickListener(onClickListener);
    }

    public void showPopUpWindow(View parent) {
        if (!this.isShowing()) {
            this.showAtLocation(parent, Gravity.CENTER, 0, 0);
        } else {
            this.dismiss();
        }
    }


}
