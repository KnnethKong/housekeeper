package com.haiwai.housekeeper.fragment.user;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.utils.AnimUtil;

public class HomeNewMenuTab extends PopupWindow implements OnClickListener {
    Context mContext;
    private int mWidth;
    private int mHeight;
    private String isZhorEn;
    private LayoutInflater mInflater;
    private RelativeLayout rl_tab_layout;
    public ImageView home_menu_tab_ib_search,
            home_menu_tab_iv_translate,
            home_menu_tab_iv_img3,
            home_menu_tab_iv_img1,
            home_menu_tab_iv_img2,
            home_menu_tab_iv_close;
    private View mView;

    public HomeNewMenuTab(Context context, String isZhorEn) {
        this.mContext = context;
        this.isZhorEn = isZhorEn;
        calWidthAndHeight(context);
        mInflater = LayoutInflater.from(mContext);
        iniView();
    }

    private void iniView() {
        mView = mInflater.inflate(R.layout.home_new_new_menu_tab, null);
        rl_tab_layout = (RelativeLayout) mView.findViewById(R.id.rl_bottom_layout);
        initview(mView);
        this.setContentView(mView);
        this.setWidth(mWidth);
        this.setHeight(mHeight);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
//        this.setBackgroundDrawable(new BitmapDrawable());
        this.setAnimationStyle(R.style.AlphaAnimation);
    }

    private void initview(View view) {
        home_menu_tab_ib_search = (ImageView) view.findViewById(R.id.home_menu_tab_ib_search);
        home_menu_tab_iv_translate = (ImageView) view.findViewById(R.id.home_menu_tab_iv_translate);
        home_menu_tab_iv_img3 = (ImageView) view.findViewById(R.id.home_menu_tab_iv_img3);
        home_menu_tab_iv_img1 = (ImageView) view.findViewById(R.id.home_menu_tab_iv_img1);
        home_menu_tab_iv_img2 = (ImageView) view.findViewById(R.id.home_menu_tab_iv_img2);
        home_menu_tab_iv_close = (ImageView) view.findViewById(R.id.home_menu_tab_iv_close);
//        if ("en".equals(isZhorEn)) {
//            home_menu_tab_iv_translate.setImageResource(R.mipmap.icon_fy_en);
//            home_menu_tab_iv_img3.setImageResource(R.mipmap.icon_dcfw_en);
//            home_menu_tab_iv_img1.setImageResource(R.mipmap.inco_zqbg_en);
//            home_menu_tab_iv_img2.setImageResource(R.mipmap.icon_zqgl_en);
//        } else {
            home_menu_tab_iv_translate.setImageResource(R.mipmap.icon_fy);
            home_menu_tab_iv_img3.setImageResource(R.mipmap.icon_dcfw);
            home_menu_tab_iv_img1.setImageResource(R.mipmap.inco_zqbg);
            home_menu_tab_iv_img2.setImageResource(R.mipmap.icon_zqgl);
//        }
        home_menu_tab_ib_search.setOnClickListener(this);
        home_menu_tab_iv_translate.setOnClickListener(this);
        home_menu_tab_iv_img3.setOnClickListener(this);
        home_menu_tab_iv_img1.setOnClickListener(this);
        home_menu_tab_iv_img2.setOnClickListener(this);
        home_menu_tab_iv_close.setOnClickListener(this);

    }

    public void setViwShow() {
        AnimUtil.showMenu(rl_tab_layout, 200);
    }

    public void setViewHide() {
        AnimUtil.closeMenu(rl_tab_layout, 200);

    }

    public void showPopUpWindow(View parent) {
        if (!this.isShowing()) {
            this.showAtLocation(parent, Gravity.CENTER, mWidth, mHeight);
        } else {
            this.dismiss();
        }
    }

    /**
     * 设置PopupWindow的大小
     *
     * @param context
     */
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_menu_tab_ib_search://客服
                mOnMenuClickListener.tabMenuClick(0);
                break;
            case R.id.home_menu_tab_iv_translate://翻译
                mOnMenuClickListener.tabMenuClick(1);
                break;
            case R.id.home_menu_tab_iv_img1://情况报告
                mOnMenuClickListener.tabMenuClick(3);
                break;
            case R.id.home_menu_tab_iv_img2://订单管理
                mOnMenuClickListener.tabMenuClick(4);
                break;
            case R.id.home_menu_tab_iv_img3://详细定制
                mOnMenuClickListener.tabMenuClick(2);
                break;
            case R.id.home_menu_tab_iv_close://关闭
                mOnMenuClickListener.tabMenuClick(5);
                break;
        }
    }

    private static OnMenuClickListener mOnMenuClickListener;

    public interface OnMenuClickListener {
        void tabMenuClick(int type);
    }

    public static void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        mOnMenuClickListener = onMenuClickListener;
    }
}
