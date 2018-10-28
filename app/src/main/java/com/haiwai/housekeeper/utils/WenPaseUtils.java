package com.haiwai.housekeeper.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.haiwai.housekeeper.entity.SkillProItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ihope10 on 2016/11/2.
 */

public class WenPaseUtils {

    public static String getAllJson(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open("skill_content.json")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    public static SkillProItem getSkillProItem(Context context, int type) {
        SkillProItem skillProItem = null;
        String str = getAllJson(context);
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        switch (type) {
            case 1://房屋保洁
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(0);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 2://草坪修剪
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(1);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 3://地毯清洗
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(2);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 4://高压清洗
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(3);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 5://垃圾处理
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(4);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 6://搬运搬家
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(5);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 7://安保系统
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(6);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 8://除害
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(7);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 9://维修技工
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(8);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 10://屋顶维修
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(9);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 11://园艺维修
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(10);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 12://家电
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(11);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 13://改建翻新
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(12);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 14://室内粉刷
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(13);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 15://室外粉刷
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(14);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 16://电工
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(15);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 17://冷热源
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(16);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 18://锅炉维修
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(17);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 19://接机用车
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(18);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 20://陪同代办
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(19);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 21://保险下单
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(20);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 22://移民咨询
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(21);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 23://税务服务
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(22);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 24://驾驶教练
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(23);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 25://翻译公证
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(24);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 26://法律咨询
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(25);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 27://房产买卖
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(26);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 28://旅游代理
                skillProItem = new SkillProItem();
                try {
                    JSONObject proObject = jsonArray.getJSONObject(27);
                    parseData(skillProItem, proObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        return skillProItem;
    }


    private static void parseData(SkillProItem skillProItem, JSONObject fwbjObject) throws JSONException {
        skillProItem.setProtype(fwbjObject.optString("protype"));
        skillProItem.setPronamecn(fwbjObject.optString("pronamecn"));
        skillProItem.setPronameen(fwbjObject.optString("pronameen"));

        JSONArray cnArray = fwbjObject.optJSONArray("procn");
        if (cnArray != null) {
            List<SkillProItem.ProCn> proCns = new ArrayList<>();
            for (int i = 0; i < cnArray.length(); i++) {
                JSONObject cnObject = cnArray.getJSONObject(i);
                SkillProItem.ProCn proCn = new SkillProItem.ProCn();
                proCn.setProtitlecn(cnObject.optString("protitlecn"));
                JSONArray strObject = cnObject.optJSONArray("proanscn");
                if (strObject != null) {
                    List<String> strs = new ArrayList<>();
                    for (int j = 0; j < strObject.length(); j++) {
                        strs.add(strObject.optString(j));
                    }
                    proCn.setProanscn(strs);
                }
                proCns.add(proCn);
            }
            skillProItem.setProCns(proCns);
        }

        JSONArray enArray = fwbjObject.optJSONArray("proen");
        if (enArray != null) {
            List<SkillProItem.ProEn> proEns = new ArrayList<>();
            for (int i = 0; i < enArray.length(); i++) {
                JSONObject enObject = enArray.getJSONObject(i);
                SkillProItem.ProEn proEn = new SkillProItem.ProEn();
                proEn.setProtitleen(enObject.optString("protitleen"));
                JSONArray strObject = enObject.optJSONArray("proansen");
                if (strObject != null) {
                    List<String> strs = new ArrayList<>();
                    for (int j = 0; j < strObject.length(); j++) {
                        strs.add(strObject.optString(j));
                    }
                    proEn.setProansen(strs);
                }
                proEns.add(proEn);
            }
            skillProItem.setProEns(proEns);
        }
    }

    public static String getWenStr(Context context, String wenN, int type, String isZh) {
        SkillProItem skillProItem = getSkillProItem(context, type);
        String str = null;
        if ("zh".equalsIgnoreCase(isZh)) {
            switch (wenN) {
                case "wen1":
                    str = skillProItem.getProCns().get(0).getProtitlecn();
                    break;
                case "wen2":
                    str = skillProItem.getProCns().get(1).getProtitlecn();
                    break;
                case "wen3":
                    str = skillProItem.getProCns().get(2).getProtitlecn();
                    break;
                case "wen4":
                    str = skillProItem.getProCns().get(3).getProtitlecn();
                    break;
                case "wen5":
                    str = skillProItem.getProCns().get(4).getProtitlecn();
                    break;
                case "wen6":
                    str = skillProItem.getProCns().get(5).getProtitlecn();
                    break;
                case "wen7":
                    str = skillProItem.getProCns().get(6).getProtitlecn();
                    break;
                case "wen8":
                    str = skillProItem.getProCns().get(7).getProtitlecn();
                    break;
                case "wen9":
                    str = skillProItem.getProCns().get(8).getProtitlecn();
                    break;
                case "wen10":
                    str = skillProItem.getProCns().get(9).getProtitlecn();
                    break;
                case "wen11":
                    str = skillProItem.getProCns().get(10).getProtitlecn();
                    break;
                case "wen12":
                    str = skillProItem.getProCns().get(11).getProtitlecn();
                    break;
                case "wen13":
                    str = skillProItem.getProCns().get(12).getProtitlecn();
                    break;
                case "wen14":
                    str = skillProItem.getProCns().get(13).getProtitlecn();
                    break;
                case "wen15":
                    str = skillProItem.getProCns().get(14).getProtitlecn();
                    break;
                case "wen16":
                    str = skillProItem.getProCns().get(15).getProtitlecn();
                    break;
                case "wen17":
                    str = skillProItem.getProCns().get(16).getProtitlecn();
                    break;
                case "wen18":
                    str = skillProItem.getProCns().get(17).getProtitlecn();
                    break;
                case "wen19":
                    str = skillProItem.getProCns().get(18).getProtitlecn();
                    break;
            }
        } else if ("en".equalsIgnoreCase(isZh)) {
            switch (wenN) {
                case "wen1":
                    str = skillProItem.getProEns().get(0).getProtitleen();
                    break;
                case "wen2":
                    str = skillProItem.getProEns().get(1).getProtitleen();
                    break;
                case "wen3":
                    str = skillProItem.getProEns().get(2).getProtitleen();
                    break;
                case "wen4":
                    str = skillProItem.getProEns().get(3).getProtitleen();
                    break;
                case "wen5":
                    str = skillProItem.getProEns().get(4).getProtitleen();
                    break;
                case "wen6":
                    str = skillProItem.getProEns().get(5).getProtitleen();
                    break;
                case "wen7":
                    str = skillProItem.getProEns().get(6).getProtitleen();
                    break;
                case "wen8":
                    str = skillProItem.getProEns().get(7).getProtitleen();
                    break;
                case "wen9":
                    str = skillProItem.getProEns().get(8).getProtitleen();
                    break;
                case "wen10":
                    str = skillProItem.getProEns().get(9).getProtitleen();
                    break;
                case "wen11":
                    str = skillProItem.getProEns().get(10).getProtitleen();
                    break;
                case "wen12":
                    str = skillProItem.getProEns().get(11).getProtitleen();
                    break;
                case "wen13":
                    str = skillProItem.getProEns().get(12).getProtitleen();
                    break;
                case "wen14":
                    str = skillProItem.getProEns().get(13).getProtitleen();
                    break;
                case "wen15":
                    str = skillProItem.getProEns().get(14).getProtitleen();
                    break;
                case "wen16":
                    str = skillProItem.getProEns().get(15).getProtitleen();
                    break;
                case "wen17":
                    str = skillProItem.getProEns().get(16).getProtitleen();
                    break;
                case "wen18":
                    str = skillProItem.getProEns().get(17).getProtitleen();
                    break;
                case "wen19":
                    str = skillProItem.getProEns().get(18).getProtitleen();
                    break;
            }
        }
        return str;
    }


    public static String getDaStr(Context context, String daN, int i, int type, String isZh) {
        i = i - 1;
        SkillProItem skillProItem = getSkillProItem(context, type);
        String str = null;
        if ("zh".equalsIgnoreCase(isZh)) {
            switch (daN) {
                case "da1":
                    str = skillProItem.getProCns().get(0).getProanscn().get(i);
                    break;
                case "da2":
                    str = skillProItem.getProCns().get(1).getProanscn().get(i);
                    break;
                case "da3":
                    str = skillProItem.getProCns().get(2).getProanscn().get(i);
                    break;
                case "da4":
                    str = skillProItem.getProCns().get(3).getProanscn().get(i);
                    break;
                case "da5":
                    str = skillProItem.getProCns().get(4).getProanscn().get(i);
                    Log.i("da5",str+"--"+i);
                    break;
                case "da6":
                    str = skillProItem.getProCns().get(5).getProanscn().get(i);
                    break;
                case "da7":
                    str = skillProItem.getProCns().get(6).getProanscn().get(i);
                    break;
                case "da8":
                    str = skillProItem.getProCns().get(7).getProanscn().get(i);
                    break;
                case "da9":
                    str = skillProItem.getProCns().get(8).getProanscn().get(i);
                    break;
                case "da10":
                    str = skillProItem.getProCns().get(9).getProanscn().get(i);
                    break;
                case "da11":
                    str = skillProItem.getProCns().get(10).getProanscn().get(i);
                    break;
                case "da12":
                    str = skillProItem.getProCns().get(11).getProanscn().get(i);
                    break;
                case "da13":
                    str = skillProItem.getProCns().get(12).getProanscn().get(i);
                    break;
                case "da14":
                    str = skillProItem.getProCns().get(13).getProanscn().get(i);
                    break;
                case "da15":
                    str = skillProItem.getProCns().get(14).getProanscn().get(i);
                    break;
                case "da16":
                    str = skillProItem.getProCns().get(15).getProanscn().get(i);
                    break;
                case "da17":
                    str = skillProItem.getProCns().get(16).getProanscn().get(i);
                    break;
                case "da18":
                    str = skillProItem.getProCns().get(17).getProanscn().get(i);
                    break;
                case "da19":
                    str = skillProItem.getProCns().get(18).getProanscn().get(i);
                    break;
            }
        } else if ("en".equalsIgnoreCase(isZh)) {
            switch (daN) {
                case "da1":
                    str = skillProItem.getProEns().get(0).getProansen().get(i);
                    break;
                case "da2":
                    str = skillProItem.getProEns().get(1).getProansen().get(i);
                    break;
                case "da3":
                    str = skillProItem.getProEns().get(2).getProansen().get(i);
                    break;
                case "da4":
                    str = skillProItem.getProEns().get(3).getProansen().get(i);
                    break;
                case "da5":
                    str = skillProItem.getProEns().get(4).getProansen().get(i);
                    break;
                case "da6":
                    str = skillProItem.getProEns().get(5).getProansen().get(i);
                    break;
                case "da7":
                    str = skillProItem.getProEns().get(6).getProansen().get(i);
                    break;
                case "da8":
                    str = skillProItem.getProEns().get(7).getProansen().get(i);
                    break;
                case "da9":
                    str = skillProItem.getProEns().get(8).getProansen().get(i);
                    break;
                case "da10":
                    str = skillProItem.getProEns().get(9).getProansen().get(i);
                    break;
                case "da11":
                    str = skillProItem.getProEns().get(10).getProansen().get(i);
                    break;
                case "da12":
                    str = skillProItem.getProEns().get(11).getProansen().get(i);
                    break;
                case "da13":
                    str = skillProItem.getProEns().get(12).getProansen().get(i);
                    break;
                case "da14":
                    str = skillProItem.getProEns().get(13).getProansen().get(i);
                    break;
                case "da15":
                    str = skillProItem.getProEns().get(14).getProansen().get(i);
                    break;
                case "da16":
                    str = skillProItem.getProEns().get(15).getProansen().get(i);
                    break;
                case "da17":
                    str = skillProItem.getProEns().get(16).getProansen().get(i);
                    break;
                case "da18":
                    str = skillProItem.getProEns().get(17).getProansen().get(i);
                    break;
                case "da19":
                    str = skillProItem.getProEns().get(18).getProansen().get(i);
                    break;
            }
        }
        return str;
    }
}
