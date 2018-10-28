package com.haiwai.housekeeper.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope006 on 2017/2/7.
 */

public class HomePopView extends PopupWindow {
    private LayoutInflater mInflater;
    public LinearLayout llTopLayout;
    public RelativeLayout rlBottomLayout;
    private View popView;
    public TextView imgTopBtn, imgBottomBtn;
    private int mWidth;
    private int mHeight;

    public HomePopView(Context context) {
        calWidthAndHeight(context);
        mInflater = LayoutInflater.from(context);
        initView(context);
    }

    private void calWidthAndHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        mWidth = metrics.widthPixels;
        mHeight = metrics.heightPixels - getStatusBarHeight(context);
    }

    private int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Status height:" + height);
        return height;
    }

    private void initView(Context context) {
        popView = mInflater.inflate(R.layout.home_pop_up_layout, null);
        imgTopBtn = (TextView) popView.findViewById(R.id.top_btn);
        imgBottomBtn = (TextView) popView.findViewById(R.id.bottom_btn);
        llTopLayout = (LinearLayout) popView.findViewById(R.id.ll_top_layout);
        rlBottomLayout = (RelativeLayout) popView.findViewById(R.id.rl_bottom_layout);
        this.setContentView(popView);
        this.setWidth(mWidth);
        this.setHeight(mHeight);
        this.setFocusable(true);
        this.setTouchable(true);
        this.setOutsideTouchable(true);
        this.update();
        ColorDrawable cd = new ColorDrawable(0000000000);
        this.setBackgroundDrawable(cd);
        this.setAnimationStyle(R.style.AlphaAnimation);
    }

    public LinearLayout getLlTopLayout() {
        return llTopLayout;
    }

    public RelativeLayout getRlBottomLayout() {
        return rlBottomLayout;
    }

    public TextView getTopBtn() {
        return imgTopBtn;
    }

    public TextView getBottomBtn() {
        return imgBottomBtn;
    }

    public void showPopUpWindow(View parent) {
        if (!this.isShowing()) {
            this.showAtLocation(parent, Gravity.CENTER, mWidth, mHeight);
        } else {
            this.dismiss();
        }
    }


}
