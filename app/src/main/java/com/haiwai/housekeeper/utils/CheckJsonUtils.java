package com.haiwai.housekeeper.utils;

import android.content.Context;
import android.text.TextUtils;

import com.haiwai.housekeeper.entity.SkillProItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ihope10 on 2016/11/3.
 */

public class CheckJsonUtils {
    public static int getStatuss(String str) {
        int num = 0;
        if (!TextUtils.isEmpty(str)) {
            String s = str.substring(0, 1);
            if (Pattern.compile("^[-\\+]?[\\d]*$").matcher(str).matches()) {//是否为整数
                num = 0;
            } else {
                if ("{".equals(s)) {//是否为Json
                    num = 1;
                } else {//是否为字符串
                    num = 2;
                }
            }
        } else {
            num = 2;
        }

        return num;
    }


    public static int getJsonType(String json) {
        int i = 0;
        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject != null) {
                String type = jsonObject.optString("type");
                String img = jsonObject.optString("img");
                if ("".equals(img)) {
                    if ("".equals(type)) {//纯数字类型
                        i = 0;
                    } else {//指定日期类型
                        i = 1;
                    }
                } else {//图片类型
                    i = 2;
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return i;
    }

    //多选内容处理
    public static List<String> getStrList(Context context, int type, int i, String json, String isZh) {
        i = i - 1;
        List<String> strList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            SkillProItem skillProItem = WenPaseUtils.getSkillProItem(context, type);
            if ("zh".equalsIgnoreCase(isZh)) {
                int count = skillProItem.getProCns().get(i).getProanscn().size();//得到总长
                if ((jsonObject.length() == count + 1) && "1".equals(jsonObject.optString(jsonObject.length() + ""))) {
                    String st = skillProItem.getProCns().get(i).getProanscn().get(1);
                    strList.add(st);
                } else {
                    for (int k = 1; k <= count; k++) {
                        if (Pattern.compile("^[-\\+]?[\\d]*$").matcher(jsonObject.optString(k + "")).matches()) {//是否是整数
                            if ("1".equals(jsonObject.optString(k + ""))) {//是选择的并且为1已选中
                                String str = skillProItem.getProCns().get(i).getProanscn().get(k - 1);
                                strList.add(str);
                            }
                        } else {//是输入的内容

                            String str = jsonObject.optString(k + "");
                            if (!TextUtils.isEmpty(str)) {
                                str = str.substring(3, str.length());
                                strList.add(str);
                            }


                        }
                    }
                }
            } else if ("en".equalsIgnoreCase(isZh)) {
                int count = skillProItem.getProEns().get(i).getProansen().size();//得到总长
                if ((jsonObject.length() == count + 1) && "1".equals(jsonObject.optString(jsonObject.length() + ""))) {
                    String st = skillProItem.getProEns().get(i).getProansen().get(1);
                    strList.add(st);
                } else {
                    for (int k = 1; k <= count; k++) {
                        if (Pattern.compile("^[-\\+]?[\\d]*$").matcher(jsonObject.optString(k + "")).matches()) {//是否是整数
                            if ("1".equals(jsonObject.optString(k + ""))) {//是选择的并且为1已选中
                                String str = skillProItem.getProEns().get(i).getProansen().get(k - 1);
                                strList.add(str);
                            }
                        } else {//是输入的内容
                            String str = jsonObject.optString(k + "");
                            if (!TextUtils.isEmpty(str)) {
                                str = str.substring(3, str.length());
                                strList.add(str);
                            }

                        }
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return strList;
    }

    //日期处理
    public static List<String> getTimeStr(Context context, int type, int i, String json, String isZh) {
        i = i - 1;
        List<String> timeList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            SkillProItem skillProItem = WenPaseUtils.getSkillProItem(context, type);
            if ("zh".equalsIgnoreCase(isZh)) {
                int count = skillProItem.getProCns().get(i).getProanscn().size();//得到总长
                String typeStr = jsonObject.optString("type");
                if ("1".equals(typeStr)) {
                    timeList.clear();
                    String str = skillProItem.getProCns().get(i).getProanscn().get(0);
                    timeList.add(str);
                } else if ("2".equals(typeStr)) {
                    timeList.clear();
                    String str = skillProItem.getProCns().get(i).getProanscn().get(1);
                    timeList.add(str);
                } else if ("3".equals(typeStr)) {
                    timeList.clear();
                    String str = skillProItem.getProCns().get(i).getProanscn().get(2);
                    timeList.add(str);
                } else if ("4".equals(typeStr)) {
                    timeList.clear();
                    String str = skillProItem.getProCns().get(i).getProanscn().get(3);
                    timeList.add(str);
                    String str1 = jsonObject.optString("2");
                    String str2 = jsonObject.optString("3");
                    String str3 = jsonObject.optString("4");
                    if (!TextUtils.isEmpty(str1)) {
                        str1 = str1.substring(3, str1.length());
                        timeList.add(str1);
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        str2 = str2.substring(3, str2.length());
                        timeList.add(str2);
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        str3 = str3.substring(3, str3.length());
                        timeList.add(str3);
                    }
                } else if ("5".equals(typeStr)) {
                    timeList.clear();
                    String str = skillProItem.getProCns().get(i).getProanscn().get(4);
                    timeList.add(str);
                }
            } else if ("en".equalsIgnoreCase(isZh)) {
                int count = skillProItem.getProEns().get(i).getProansen().size();//得到总长
                String typeStr = jsonObject.optString("type");
                if ("1".equals(typeStr)) {
                    timeList.clear();
                    String str = skillProItem.getProEns().get(i).getProansen().get(0);
                    timeList.add(str);
                } else if ("2".equals(typeStr)) {
                    timeList.clear();
                    String str = skillProItem.getProEns().get(i).getProansen().get(1);
                    timeList.add(str);
                } else if ("3".equals(typeStr)) {
                    timeList.clear();
                    String str = skillProItem.getProEns().get(i).getProansen().get(2);
                    timeList.add(str);
                } else if ("4".equals(typeStr)) {
                    timeList.clear();
                    String str = skillProItem.getProEns().get(i).getProansen().get(3);
                    timeList.add(str);
                    String str1 = jsonObject.optString("2");
                    String str2 = jsonObject.optString("3");
                    String str3 = jsonObject.optString("4");
                    if (!TextUtils.isEmpty(str1)) {
                        str1 = str1.substring(3, str1.length());
                        timeList.add(str1);
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        str2 = str2.substring(3, str2.length());
                        timeList.add(str2);
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        str3 = str3.substring(3, str3.length());
                        timeList.add(str3);
                    }
                } else if ("5".equals(typeStr)) {
                    timeList.clear();
                    String str = skillProItem.getProEns().get(i).getProansen().get(4);
                    timeList.add(str);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return timeList;
    }

    //图片处理
    public static List<String> getPicStr(Context context, int type, int i, String json, String isZh) {
        i = i - 1;
        List<String> pathList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            SkillProItem skillProItem = WenPaseUtils.getSkillProItem(context, type);
//            int count = skillProItem.getProCns().get(i).getProanscn().size();//得到总长
            if ("1".equals(jsonObject.optString("type"))) {
                if ("zh".equalsIgnoreCase(isZh)) {
                    String str = skillProItem.getProCns().get(i).getProanscn().get(0);
                    pathList.add(str);
                } else if ("en".equalsIgnoreCase(isZh)) {
                    String str = skillProItem.getProEns().get(i).getProansen().get(0);
                    pathList.add(str);
                }
            } else if ("2".equals(jsonObject.optString("type"))) {
                JSONArray jsonArray = jsonObject.optJSONArray("img");
                if (jsonArray != null) {
                    for (int k = 0; k < jsonArray.length(); k++) {
                        String pathStr = jsonArray.optString(k);
                        pathList.add(pathStr);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pathList;
    }
}
