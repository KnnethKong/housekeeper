package com.wn.library.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.wn.library.R;

/**
 * Created by 王宁 on 2017/2/14.
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }


    @Override
    public void onClick(View view) {
        clickListenter(view);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        //        //保留状态栏，只改变状态栏颜色
//        /**沉浸式状态栏设置部分**/
//        //Android4.4及以上版本才能设置此效果
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //Android5.0版本
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                //设置状态栏颜色
//                getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));
////				//设置导航栏颜色
////				getWindow().setNavigationBarColor(getResources().getColor(R.color.transparent));
//            } else {
//                //透明状态栏
//                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//                //透明导航栏
//                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//                //创建状态栏的管理实例
//                SystemBarTintManager tintManager = new SystemBarTintManager(this);
//                //激活状态栏设置
//                tintManager.setStatusBarTintEnabled(true);
//                //设置状态栏颜色
//                tintManager.setTintResource(R.color.transparent);
//                //激活导航栏设置
//                tintManager.setNavigationBarTintEnabled(true);
////				//设置导航栏颜色
////				tintManager.setNavigationBarTintResource(R.color.transparent);
//            }
//        }
        //不保留状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //Android5.0版本
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                //设置状态栏颜色
                getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));
            }
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            initView();
            initListener();
            initData();
        }
    }

    public abstract void initView();
    public abstract void initListener();
    public abstract void initData();
    public abstract void clickListenter(View v);

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
