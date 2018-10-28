package com.haiwai.housekeeper.utils;

/**
 * Created by ihope006 on 2017/2/4.
 */

public class EnOrCnUtils {
    public static boolean checkChar(char ch) {//英文占1byte，非英文（可认为是中文）占2byte，根据这个特性来判断字符
        if ((ch + "").getBytes().length == 1) {
            return true;//英文
        } else {
            return false;//中文
        }
    }

    public static String checkString(String str) {
        String res = "";
        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                //只要字符串中有中文则为中文
                if (!checkChar(str.charAt(i))) {
                    res = "zh";
                } else {
                    res = "en";
                }
            }
        }
        return res;
    }

}
