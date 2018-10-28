package com.haiwai.housekeeper.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.haiwai.housekeeper.entity.HomeEntity;
import com.haiwai.housekeeper.entity.SkillEntity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * sharePreferences 工具类
 * <p/>
 * 缓存对象  字符串  boolean
 *
 * @author abner
 */
public class SPUtils {
    public static String SP_NAME = "config";
    private static SharedPreferences sp;
    private static String serStr;
    private static Object deSerialization;

    /**
     * 序列化保存对象
     *
     * @param context
     * @param key
     * @param obj     要保存的对象，只能保存实现了serializable的对象
     *                modified:
     */
    public static void saveObject(Context context, String key, Object obj) {
        String serialize = "";
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, MODE_PRIVATE);
        }
        try {
            serialize = serialize(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sp.edit().putString(key, serialize).commit();
    }

    /**
     * 获得序列化对象
     *
     * @param context
     * @param key
     * @return
     */
    public static Object getObject(Context context, String key) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, MODE_PRIVATE);
        }
        //序
        String string = getString(context, key, "");

        try {
            deSerialization = deSerialization(string);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deSerialization;
    }

    /**
     * 保存 boolead 类型的数据
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveBoolean(Context context, String key, boolean value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * 获取 boolead 类型的保存数据
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);
    }

    /**
     * 保存 string 类型的数据
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveString(Context context, String key, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, MODE_PRIVATE);
        }
        sp.edit().putString(key, value).commit();
    }


    /**
     * 获取 保存的 string 数据
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(Context context, String key, String defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, MODE_PRIVATE);
        }
        return sp.getString(key, defValue);
    }


    /**
     * 保存 string 类型的数据
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveInt(Context context, String key, int value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, MODE_PRIVATE);
        }
        sp.edit().putInt(key, value).commit();
    }


    /**
     * 清除所有缓存数据
     *
     * @param context
     */
    public static void clearAll(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, MODE_PRIVATE);
        }
        sp.edit().clear().commit();
    }

    /**
     * 序列化对象
     *
     * @param obj
     */
    private static String serialize(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        serStr = byteArrayOutputStream.toString("ISO-8859-1");
        serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return serStr;
    }

    /**
     * 反序列化对象
     *
     * @param str
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static Object deSerialization(String str) throws IOException,
            ClassNotFoundException {
        String redStr = java.net.URLDecoder.decode(str, "UTF-8");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                redStr.getBytes("ISO-8859-1"));
        ObjectInputStream objectInputStream = new ObjectInputStream(
                byteArrayInputStream);
        Object person = (Object) objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return person;
    }

    public static void saveList(Context context, String key, List<SkillEntity.DataBean> list) {
        SharedPreferences.Editor editor = context.getSharedPreferences("list_content", MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.commit();
    }

    public static List<SkillEntity.DataBean> getList(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences("list_content", MODE_PRIVATE);
        String json = preferences.getString(key, null);
        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<SkillEntity.DataBean>>() {
            }.getType();
            return gson.fromJson(json, type);
        } else {
            return null;
        }
    }

    /**
     * 保存VIP服务数据
     *
     * @param context
     * @param key
     * @param vipKey
     * @param value
     */
    public static void saveVIPString(Context context, String key, String vipKey, String value) {
        if (sp == null)
            sp = context.getSharedPreferences(key, MODE_PRIVATE);
        sp.edit().putString(vipKey, value).commit();
    }

    /**
     * 获取vip数据
     *
     * @param context
     * @param vipKey
     * @param key
     */
    public static String getVIPString(Context context, String vipKey, String key) {
        if (sp == null)
            sp = context.getSharedPreferences(vipKey, MODE_PRIVATE);
        return sp.getString(key, "none");
    }


    public static int getInt(Context context, String strKey) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                SP_NAME, Context.MODE_PRIVATE);
        int result = setPreferences.getInt(strKey, -1);
        return result;
    }

    public static int getInt(Context context, String strKey, int strDefault) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                SP_NAME, Context.MODE_PRIVATE);
        int result = setPreferences.getInt(strKey, strDefault);
        return result;
    }

    public static void putInt(Context context, String strKey, int strData) {
        SharedPreferences activityPreferences = context.getSharedPreferences(
                SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = activityPreferences.edit();
        editor.putInt(strKey, strData);
        editor.commit();
    }

}
