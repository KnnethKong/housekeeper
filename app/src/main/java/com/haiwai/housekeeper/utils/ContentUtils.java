//package com.haiwai.housekeeper.utils;
//
//import android.content.Context;
//import android.content.res.AssetManager;
//
//import com.haiwai.housekeeper.entity.SkillProItem;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by ihope10 on 2016/10/26.
// */
//
//public class ContentUtilsss {
//
//    public static String getJson(Context context) {
//        StringBuilder stringBuilder = new StringBuilder();
//        try {
//            AssetManager assetManager = context.getAssets();
//            BufferedReader bf = new BufferedReader(new InputStreamReader(
//                    assetManager.open("skill_content.json")));
//            String line;
//            while ((line = bf.readLine()) != null) {
//                stringBuilder.append(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return stringBuilder.toString();
//    }
//
//
//    public static SkillProItem getSkillContentItem(Context context, int type) {
//        SkillProItem skillProItem = null;
//        String str = getJson(context);
//        JSONArray jsonArray = null;
//        try {
//            jsonArray = new JSONArray(str);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        System.out.println(">>>>"+str);
//        switch (type) {
//            case 1://房屋保洁
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(5);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 2://草坪修剪
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(3);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 3://地毯清洗
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(4);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 4://高压清洗
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(7);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 5://垃圾处理
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(9);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 6://搬运搬家
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(1);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 7://安保系统
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(0);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 8://除害
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(2);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 9://维修技工
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(13);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 10://屋顶维修
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(12);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 12://家电
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(8);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 13://改建翻新
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(6);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 14://室内粉刷
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(10);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 15://室外粉刷
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(11);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 19://接机用车
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(14);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 20://陪同代办
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(15);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 21://保险
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(16);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 22://移民咨询
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(17);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 23://税务服务
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(18);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 24://驾驶教练
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(19);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 25://翻译公证
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(20);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 26://法律咨询
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(21);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 27://房产买卖
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(22);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 28://旅游代理
//                skillProItem = new SkillProItem();
//                try {
//                    JSONObject proObject = jsonArray.getJSONObject(23);
//                    parseData(skillProItem, proObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            default:
//                break;
//        }
//        return skillProItem;
//    }
//
//
//    private static void parseData(SkillProItem skillProItem, JSONObject fwbjObject) throws JSONException {
//        skillProItem.setProtype(fwbjObject.optString("protype"));
//        skillProItem.setPronamecn(fwbjObject.optString("pronamecn"));
//        skillProItem.setPronameen(fwbjObject.optString("pronameen"));
//        JSONArray cnArray = fwbjObject.optJSONArray("procn");
//        if (cnArray != null) {
//            List<SkillProItem.ProCn> proCns = new ArrayList<>();
//            for (int i = 0; i < cnArray.length(); i++) {
//                JSONObject cnObject = cnArray.getJSONObject(i);
//                SkillProItem.ProCn proCn = new SkillProItem.ProCn();
//                proCn.setProtitlecn(cnObject.optString("protitlecn"));
//                JSONArray strObject = cnObject.optJSONArray("proanscn");
//                if (strObject != null) {
//                    List<String> strs = new ArrayList<>();
//                    for (int j = 0; j < strObject.length(); j++) {
//                        strs.add(strObject.optString(j));
//                    }
//                    proCn.setProanscn(strs);
//                }
//                proCns.add(proCn);
//            }
//            skillProItem.setProCns(proCns);
//        }
//        JSONArray enArray = fwbjObject.optJSONArray("proen");
//        if (enArray != null) {
//            List<SkillProItem.ProEn> proEns = new ArrayList<>();
//            for (int i = 0; i < enArray.length(); i++) {
//                JSONObject enObject = enArray.getJSONObject(i);
//                SkillProItem.ProEn proEn = new SkillProItem.ProEn();
//                proEn.setProtitleen(enObject.optString("protitleen"));
//                JSONArray strObject = enObject.optJSONArray("proansen");
//                if (strObject != null) {
//                    List<String> strs = new ArrayList<>();
//                    for (int j = 0; j < strObject.length(); j++) {
//                        strs.add(strObject.optString(j));
//                    }
//                    proEn.setProansen(strs);
//                }
//                proEns.add(proEn);
//            }
//            skillProItem.setProEns(proEns);
//        }
//    }
//
//}
