package com.haiwai.housekeeper.fragment.user;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.LoginActivity;
import com.haiwai.housekeeper.activity.user.AllBusinessActivity;
import com.haiwai.housekeeper.activity.user.TranslateActivity;
import com.haiwai.housekeeper.activity.user.VIPpageActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.CommonConfig;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.EvmUtil;
import com.haiwai.housekeeper.utils.FastBlur;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;

public class HomeMenuTab extends PopupWindow implements OnClickListener {

    private String TAG = HomeMenuTab.class.getSimpleName();
    Activity mContext;
    private int mWidth;
    private int mHeight;
    private int statusBarHeight;
    private Bitmap mBitmap = null;
    private Bitmap overlay = null;

    private Handler mHandler = new Handler();

    public HomeMenuTab(Activity context) {
        mContext = context;
    }

    public void init() {
        Rect frame = new Rect();
        mContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        statusBarHeight = frame.top;
        DisplayMetrics metrics = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay()
                .getMetrics(metrics);
        mWidth = metrics.widthPixels;
        mHeight = metrics.heightPixels;

        setWidth(mWidth);
        setHeight(mHeight);
    }

    private Bitmap blur() {
//        if (null != overlay) {
//            return overlay;
//        }
        long startMs = System.currentTimeMillis();

        View view = mContext.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        mBitmap = view.getDrawingCache();

        float scaleFactor = 8;//图片缩放比例；
        float radius = 2;//模糊程度
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();

        overlay = Bitmap.createBitmap((int) (width / scaleFactor), (int) (height / scaleFactor), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(mBitmap, 0, -statusBarHeight, paint);

        overlay = FastBlur.doBlur(overlay, (int) radius, true);
        LogUtil.i(TAG, "blur time is:" + (System.currentTimeMillis() - startMs));
        return overlay;
    }

//    private Animation showAnimation1(final View view, int fromY, int toY) {
//        AnimationSet set = new AnimationSet(true);
//        TranslateAnimation go = new TranslateAnimation(0, 0, fromY, toY);
//        go.setDuration(300);
//        TranslateAnimation go1 = new TranslateAnimation(0, 0, -10, 2);
//        go1.setDuration(100);
//        go1.setStartOffset(250);
//        set.addAnimation(go1);
//        set.addAnimation(go);
//
//        set.setAnimationListener(new AnimationListener() {
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//        });
//        return set;
//    }

    private RelativeLayout layout;

//    public void showMoreWindow(View anchor, int bottomMargin) {
//        layout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.home_new_menu_tab, null);
//        setContentView(layout);
//
////        RelativeLayout rlcontent= (RelativeLayout) layout.findViewById(R.id.home_menu_tab_rl_content);
//        ImageView ibsearch = (ImageView) layout.findViewById(R.id.home_menu_tab_ib_search);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, -1);
//        ibsearch.setLayoutParams(params);
//        int searchpad = EvmUtil.dip2px(mContext, 30);
//        ibsearch.setPadding(searchpad, searchpad, searchpad, searchpad);
//        ibsearch.setOnClickListener(this);
////        LinearLayout ll_all= (LinearLayout) layout.findViewById(R.id.home_menu_tab_ll_all);
////        RelativeLayout.LayoutParams params_ll_all = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
////        params_ll_all.topMargin=bottomMargin;
////        params_ll_all.leftMargin=EvmUtil.dip2px(mContext,120);
////        params_ll_all.bottomMargin=EvmUtil.dip2px(mContext,50);
////        ll_all.setLayoutParams(params_ll_all);
////
//        ImageView ivimg3 = (ImageView) layout.findViewById(R.id.home_menu_tab_iv_img3);
//        RelativeLayout.LayoutParams params_ll_xxdz = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        params_ll_xxdz.bottomMargin = bottomMargin + statusBarHeight + EvmUtil.dip2px(mContext, 85);
//        params_ll_xxdz.rightMargin = EvmUtil.dip2px(mContext, 50);
//        params_ll_xxdz.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, -1);
//        params_ll_xxdz.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, -1);
//        ivimg3.setLayoutParams(params_ll_xxdz);
//        ivimg3.setOnClickListener(this);
//
//        ImageView ivimg1 = (ImageView) layout.findViewById(R.id.home_menu_tab_iv_img1);
//        RelativeLayout.LayoutParams params_ll_qkbg = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        params_ll_qkbg.bottomMargin = bottomMargin + statusBarHeight + EvmUtil.dip2px(mContext, 85);
//        params_ll_qkbg.leftMargin = EvmUtil.dip2px(mContext, 50);
//        params_ll_qkbg.addRule(RelativeLayout.ALIGN_PARENT_LEFT, -1);
//        params_ll_qkbg.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, -1);
//        ivimg1.setLayoutParams(params_ll_qkbg);
//        ivimg1.setOnClickListener(this);
//
//        ImageView ivimg2 = (ImageView) layout.findViewById(R.id.home_menu_tab_iv_img2);
//        RelativeLayout.LayoutParams params_ll_dzgl = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        params_ll_dzgl.bottomMargin = bottomMargin + statusBarHeight + EvmUtil.dip2px(mContext, 120);
//        params_ll_dzgl.addRule(RelativeLayout.CENTER_HORIZONTAL, -1);
//        params_ll_dzgl.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, -1);
//        ivimg2.setLayoutParams(params_ll_dzgl);
//        ivimg2.setOnClickListener(this);
//
//
//        ImageView close = (ImageView) layout.findViewById(R.id.home_menu_tab_iv_close);
//        RelativeLayout.LayoutParams close_params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        close_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, R.id.home_menu_tab_rl_content);
//        close_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, R.id.home_menu_tab_rl_content);
//        close_params.bottomMargin = bottomMargin + statusBarHeight;
//        close_params.rightMargin = EvmUtil.dip2px(mContext, 30);
//        close.setLayoutParams(close_params);
//
//        close.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if (isShowing()) {
//                    closeAnimation(layout);
//                }
//            }
//        });
//
//        ImageView ivtrl = (ImageView) layout.findViewById(R.id.home_menu_tab_iv_translate);
//        RelativeLayout.LayoutParams trl_params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        trl_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, -1);
//        trl_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, -1);
//        trl_params.bottomMargin = bottomMargin + statusBarHeight;
//        trl_params.leftMargin = EvmUtil.dip2px(mContext, 30);
//        ivtrl.setLayoutParams(trl_params);
//        ivtrl.setOnClickListener(this);
//
//
//        showAnimation(layout);
//        setBackgroundDrawable(new BitmapDrawable(mContext.getResources(), blur()));
//        setOutsideTouchable(true);
//        setFocusable(true);
//        showAtLocation(anchor, Gravity.CENTER, 0, 0);
//    }

    private void showAnimation(ViewGroup layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            final View child = layout.getChildAt(i);
            if (child.getId() == R.id.home_menu_tab_iv_close) {
                continue;
            }
            child.setOnClickListener(this);
            child.setVisibility(View.INVISIBLE);
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 600, 0);
                    fadeAnim.setDuration(200);
//                    KickBackAnimator kickAnimator = new KickBackAnimator();
//                    kickAnimator.setDuration(100);
//                    fadeAnim.setEvaluator(kickAnimator);
                    fadeAnim.start();
                }
            }, i * 50);
        }

    }

    private void closeAnimation(ViewGroup layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            final View child = layout.getChildAt(i);
            if (child.getId() == R.id.home_menu_tab_iv_close) {
                mHandler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        dismiss();
                    }
                }, (layout.getChildCount() - i) * 30 + 80);
                continue;
            }
            child.setOnClickListener(this);
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 0, 600);
                    fadeAnim.setDuration(160);
//                    KickBackAnimator kickAnimator = new KickBackAnimator();
//                    kickAnimator.setDuration(80);
//                    fadeAnim.setEvaluator(kickAnimator);
                    fadeAnim.start();
                    fadeAnim.addListener(new AnimatorListener() {

                        @Override
                        public void onAnimationStart(Animator animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                        }
                    });
                }
            }, (layout.getChildCount() - i - 1) * 30);

            if (child.getId() == R.id.home_menu_tab_iv_close) {
                mHandler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        dismiss();
                    }
                }, (layout.getChildCount() - i) * 30 + 80);
            }
        }

    }

    @Override
    public void onClick(final View v) {
        MyApp mApp = MyApp.getTingtingApp();
        if (!mApp.isLogin()) {
            ActivityTools.goNextActivity(mContext, LoginActivity.class);
            return;
        }

        ConnectivityManager connectivity = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
        if (!(activeNetwork != null && activeNetwork.isConnectedOrConnecting())) {
            ToastUtil.shortToast(mContext, CommonConfig.NO_NETWORK);
            return;
        }
        switch (v.getId()) {
            case R.id.home_menu_tab_iv_img1:
                ToastUtil.shortToast(mContext,"img1");
                break;
            case R.id.home_menu_tab_iv_img2:
                ToastUtil.shortToast(mContext,"img2");
                break;
            case R.id.home_menu_tab_iv_img3:
                ToastUtil.shortToast(mContext,"img3");
                break;
            case R.id.home_menu_tab_iv_translate:
                ActivityTools.goNextActivity(mContext, TranslateActivity.class);
                break;
            case R.id.home_menu_tab_ib_search:
                Information info = new Information();
                info.setSysNum(Contants.SYSNUM);/* 必填 */
                info.setInitModeType(1);//*****************************暂时之调用机器人-1
                info.setArtificialIntelligence(false);//转为人工true
                info.setArtificialIntelligenceNum(10);//只有人工true时起作用
                info.setUseVoice(true);//使用语音
                info.setColor("#FF0000");/* 选填，默认为"#09aeb0". 可以设置头部背景，提交评价背景，相似问题字体颜色和富文本类型中“阅读全文”字体颜色*/
                info.setUid(AppGlobal.getInstance().getUser().getUid());/* 选填，设置用户唯一标识 */
                info.setArtificialIntelligence(false);/* 智能转人工按钮，选填. 默认为false. 机器人客服优先模式时, false:显示转人工按钮；true:机器人有未知问题、引导回答时才显示转人工按钮 */
                SobotApi.startSobotChat(mContext, info);
                break;
        }
        dismiss();
    }


    public void destroy() {
        if (null != overlay) {
            overlay.recycle();
            overlay = null;
            System.gc();
        }
        if (null != mBitmap) {
            mBitmap.recycle();
            mBitmap = null;
            System.gc();
        }
    }

}
