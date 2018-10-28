package com.haiwai.housekeeper.utils;

import com.haiwai.housekeeper.base.AppGlobal;

/**
 * Created by ihope006 on 2016/12/15.
 */

public class ParseTypesUtils {


    /**
     * 庭院管理中的服务概要类型
     *
     * @param type
     * @return
     */
    public static String getOutlineTyglType(String type) {
        String str = "";
        if(AppGlobal.getInstance().getLagStr().equals("en")){
            str = "No order";
        }else{
            str = "本月无服务";
        }
        switch (type) {
            case "1":
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    str = "mowing lawn";
                }else{
                    str = "修剪草坪";
                }

                break;
            case "2":
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    str = "Trimming and edging";
                }else{
                    str = "草坪修边";
                }

                break;
            case "3":
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    str = "Shrub Pruning and Trimming";
                }else{
                    str = "树/灌木修剪";
                }

                break;
            case "4":
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    str = "Weeding and prevention";
                }else{
                    str = "除杂草";
                }

                break;
            case "5":
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    str = "Fertilizer application";
                }else{
                    str = "施肥";
                }

                break;
            case "6":
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    str = "Seeding or overseeding";
                }else{
                    str = "补种草籽";
                }

                break;
            case "7":
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    str = "Pest contral";
                }else{
                    str = "(草坪)虫害治理";
                }

                break;
            case "8":
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    str = "Leaf clean-up";
                }else{
                    str = "秋季落叶清扫";
                }

                break;
            case "9":
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    str = "Snow plowing";
                }else{
                    str = "冬季扫雪";
                }

                break;
        }
        return str;
    }
}
