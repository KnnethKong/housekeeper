package com.haiwai.housekeeper.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.widget.EditText;

import com.haiwai.housekeeper.entity.AddressEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.utils.SPKey;
import com.haiwai.housekeeper.utils.SPUtils;

/**
 * Created by ihope006 on 2016/6/28.
 */
public class AppGlobal {

    private User user;

    private AppGlobal() {
    }

    private static AppGlobal instance;

    public static AppGlobal getInstance() {
        if (instance == null) {
            instance = new AppGlobal();
        }
        return instance;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public User getUser() {
        if (user == null) {
            if (SPUtils.getObject(MyApp.context,
                    SPKey.USERINFO) == null)
                user = new User();
            else
                user = (User) SPUtils.getObject(MyApp.context,
                        SPKey.USERINFO);
        }
        return user;
    }

    /**
     * 用户信息
     *
     * @param info
     */
    public void setUser(User info) {
        // 把传进来的User 缓存起来
        user = info;
        SPUtils.saveObject(MyApp.context, SPKey.USERINFO, info);
    }

    /**
     * 判读是否有地址信息
     */
    public boolean getAddr() {
        return SPUtils.getBoolean(MyApp.context,
                SPKey.ADDRINFO, false);
    }


    public void setAddr(boolean isAddr) {
        // 把传进来的User 缓存起来
        SPUtils.saveBoolean(MyApp.context, SPKey.ADDRINFO, isAddr);
    }

    //获取语言内容
    public String getLagStr() {
        SharedPreferences sp = MyApp.context.getSharedPreferences("sysLanguage", Context.MODE_PRIVATE);
        return sp.getString("language", "zh");
    }

    //设置语言内容
    public void setLagStr(String str) {
        SharedPreferences sp = MyApp.context.getSharedPreferences("sysLanguage", Context.MODE_PRIVATE);
        SharedPreferences.Editor et = sp.edit();
        et.putString("language", str);
        et.commit();
    }

    /**
     * 判读是否第一次
     *
     * @param isFirst
     */
    public void setIsFirst(boolean isFirst) {
        // 把传进来的User 缓存起来
        SPUtils.saveBoolean(MyApp.context, SPKey.ISFRST, isFirst);
    }

    public boolean getIsFirst() {
        // 把传进来的User 缓存起来
        return SPUtils.getBoolean(MyApp.context,
                SPKey.ISFRST, true);
    }


    //获取加密内容
    public String getRegId() {
        SharedPreferences sp = MyApp.context.getSharedPreferences("JPush", Context.MODE_PRIVATE);
        return sp.getString("regid", "");
    }

    //设置加密内容
    public void setRegId(String str) {
        SharedPreferences sp = MyApp.context.getSharedPreferences("JPush", Context.MODE_PRIVATE);
        SharedPreferences.Editor et = sp.edit();
        et.putString("regid", str);
        et.commit();
    }

    //获取唯一登陆
    public String getLoginKey() {
        SharedPreferences sp = MyApp.context.getSharedPreferences("LoginKey", Context.MODE_PRIVATE);
        return sp.getString("loginkey", "");
    }

    //设置唯一登陆
    public void setLoginKey(String str) {
        SharedPreferences sp = MyApp.context.getSharedPreferences("LoginKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor et = sp.edit();
        et.putString("loginkey", str);
        et.commit();
    }


    /**
     * 是否第一次设置pro服务地址
     *
     * @param isProFirst
     */
    public void setIsProFirst(boolean isProFirst) {
        // 把传进来的User 缓存起来
        SPUtils.saveBoolean(MyApp.context, SPKey.PROADDRINFO, isProFirst);
    }

    public boolean getIsProFirst() {
        // 把传进来的User 缓存起来
        return SPUtils.getBoolean(MyApp.context,
                SPKey.PROADDRINFO, true);
    }

    /**
     * 是否第一次周期开始位置地址
     *
     * @param isHome
     */
    public void setIsHome(boolean isHome) {
        // 把传进来的User 缓存起来
        SPUtils.saveBoolean(MyApp.context, SPKey.ISHOME, isHome);
    }

    public boolean getIsHome() {
        // 把传进来的User 缓存起来
        return SPUtils.getBoolean(MyApp.context,
                SPKey.ISHOME, true);
    }

}
