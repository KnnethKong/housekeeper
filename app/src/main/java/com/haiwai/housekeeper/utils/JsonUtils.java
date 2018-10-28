/**
 * @author: abner
 * @date: 2015-07-30
 * @Description: json 解析 使用了gson 解析
 */
package com.haiwai.housekeeper.utils;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.entity.CityEntity;
import com.haiwai.housekeeper.entity.HomeEntity;
import com.haiwai.housekeeper.entity.NeedResponseDetailEntity;
import com.haiwai.housekeeper.entity.ProDetailEntity;
import com.haiwai.housekeeper.entity.ProDetailPhotoEntity;
import com.haiwai.housekeeper.entity.SkillProItem;
import com.haiwai.housekeeper.entity.StarEntity;

public class JsonUtils {
    private static final String SEP1 = "#";
    private static final String SEP2 = "|";

    /**
     * 将给定的 JSON 字符串转换成指定的类型对象。
     *
     * @param <T>       要转换的目标类型。
     * @param jsonStr   给定的 JSON 字符串。
     * @param className 要转换的目标类。
     * @return 给定的类型对象。
     */
    public static <T> T fromJson(String jsonStr, Class<T> className) {
        if (isEmpty(jsonStr))
            return null;

        Gson gson = new Gson();
        T t = null;
        try {
            t = gson.fromJson(jsonStr, className);
        } catch (JsonSyntaxException e) {
            throw e;
//			return null;
        }
        return t;
    }

    /**
     * 返回指定类型集合
     *
     * @param jsonStr
     * @param clazz
     * @param key
     */
    public static <T> List<T> fromJson(String jsonStr, Class<T> clazz, String key) {
        if (isEmpty(jsonStr)) {
            return null;
        }
        String json = getJsonStr(jsonStr, key);
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(gson.fromJson(jsonArray.getString(i), clazz));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将给定的 JSON 字符串转换成指定类型的List对象。
     *
     * @param jsonStr 给定的 JSON 字符串。
     * @return HashMap数组 ArrayList
     */
    public static <HashMap> ArrayList<HashMap> fromJsonList(String jsonStr) {
        if (isEmpty(jsonStr))
            return null;

        Gson gson = new Gson();
        TypeToken<ArrayList<HashMap>> token = new TypeToken<ArrayList<HashMap>>() {
        };
        return gson.fromJson(jsonStr, token.getType());
    }

    public static boolean isEmpty(String str) {
        boolean flag = false;
        if (str == null || "".equals(str)) {
            flag = true;
        }
        return flag;
    }

    /**
     * @param jsonStr
     * @param name
     * @return
     */
    public static String getJsonStr(String jsonStr, String name) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.optString(name);
    }

    /**
     * @param jsonStr
     * @param name
     * @return
     */
    public static int getJsonInt(String jsonStr, String name) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.optInt(name);
    }

    public static ProDetailEntity parseProDetail(String json) {
        ProDetailEntity entity = new ProDetailEntity();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            entity.setStatus(jsonObject.optInt("status"));
            ProDetailEntity.DataBean dataBean = new ProDetailEntity.DataBean();
            JSONObject data = jsonObject.optJSONObject("data");
            JSONObject info = data.optJSONObject("info");
            int liao = data.optInt("liao");
            dataBean.setLiao(liao);
            ProDetailEntity.DataBean.InfoBean infoBean = new ProDetailEntity.DataBean.InfoBean();
            if (info != null) {
                infoBean.setAddress(info.optString("address"));
                infoBean.setAvatar(info.optString("avatar"));
                infoBean.setBalance(info.optString("balance"));
                infoBean.setCtime(info.optString("ctime"));
                infoBean.setFans_num(info.optString("fans_num"));
                infoBean.setFollow_num(info.optString("follow_num"));
                infoBean.setIntroduction(info.optString("introduction"));
                infoBean.setIs_don(info.optString("is_don"));
                infoBean.setIs_guan(info.optInt("is_guan"));
                infoBean.setIs_hou(info.optString("is_hou"));
                infoBean.setIs_ren(info.optString("is_ren"));
                infoBean.setKm(info.optString("km"));
                infoBean.setLat(info.optString("lat"));
                infoBean.setLogin_key(info.optString("login_key"));
                infoBean.setLongX(info.optString("long"));
                infoBean.setLtime(info.optString("ltime"));
                infoBean.setMobile(info.optString("mobile"));
                infoBean.setName(info.optString("name"));
                infoBean.setNickname(info.optString("nickname"));
                infoBean.setOnly_label(info.optString("only_label"));
                infoBean.setPro_lat(info.optString("pro_lat"));
                infoBean.setPro_long(info.optString("pro_long"));
                infoBean.setPro_onum(info.optString("pro_onum"));
                infoBean.setPro_quci(info.optString("pro_quci"));
                infoBean.setPro_score(info.optString("pro_score"));
                infoBean.setPro_xing(info.optString("pro_xing"));
                infoBean.setRegistrationid(info.optString("registrationid"));
                infoBean.setSex(info.optString("sex"));
                infoBean.setSystem(info.optString("system"));
                infoBean.setUid(info.optString("uid"));
                infoBean.setUser_onum(info.optString("user_onum"));
                infoBean.setUser_quci(info.optString("user_quci"));
                infoBean.setUser_score(info.optString("user_score"));
                infoBean.setUser_xing(info.optString("user_xing"));
                infoBean.setVersion(info.optString("version"));
                dataBean.setInfo(infoBean);
            }
            JSONObject skill = data.optJSONObject("skill");
            ProDetailEntity.DataBean.SkillBean skillBean = new ProDetailEntity.DataBean.SkillBean();
            if (skill != null) {
                skillBean.setId(skill.optString("id"));
                skillBean.setUid(skill.optString("uid"));
                skillBean.setType(skill.optString("type"));
                skillBean.setOrder_num(skill.optString("pro_onum"));
                skillBean.setXing_num(skill.optString("pro_xing"));
                skillBean.setClassX(skill.optString("class"));
                skillBean.setAdvantage(skill.optString("advantage"));
                skillBean.setIs_recommend(skill.optString("is_recommend"));
                skillBean.setIs_audit(skill.optString("is_audit"));
                skillBean.setV(skill.optString("v"));
                skillBean.setIs_ji(skill.optString("is_ji"));
                skillBean.setIs_ren(skill.optString("is_ren"));
                skillBean.setWen1(skill.optString("wen1"));
                skillBean.setWen2(skill.optString("wen2"));
                skillBean.setWen3(skill.optString("wen3"));
                skillBean.setWen4(skill.optString("wen4"));
                skillBean.setWen5(skill.optString("wen5"));
                skillBean.setWen6(skill.optString("wen6"));
                skillBean.setWen7(skill.optString("wen7"));
                skillBean.setWen8(skill.optString("wen8"));
                skillBean.setWen9(skill.optString("wen9"));
                skillBean.setWen10(skill.optString("wen10"));
                skillBean.setWen11(skill.optString("wen11"));
                skillBean.setWen12(skill.optString("wen12"));
                skillBean.setWen13(skill.optString("wen13"));
                skillBean.setWen14(skill.optString("wen14"));
                skillBean.setWen15(skill.optString("wen15"));
                skillBean.setWen16(skill.optString("wen16"));
                skillBean.setWen17(skill.optString("wen17"));
                skillBean.setWen18(skill.optString("wen18"));
                skillBean.setWen19(skill.optString("wen19"));
                skillBean.setWen20(skill.optString("wen20"));
                skillBean.setWen21(skill.optString("wen21"));
                skillBean.setWen22(skill.optString("wen22"));
                skillBean.setWen23(skill.optString("wen23"));
                skillBean.setWen24(skill.optString("wen24"));
                skillBean.setWen25(skill.optString("wen25"));
                skillBean.setWen26(skill.optString("wen26"));
                skillBean.setWen27(skill.optString("wen27"));
                skillBean.setWen28(skill.optString("wen28"));
                skillBean.setWen29(skill.optString("wen29"));
                skillBean.setWen30(skill.optString("wen30"));
                skillBean.setWen31(skill.optString("wen31"));
                skillBean.setWen32(skill.optString("wen32"));
                skillBean.setWen33(skill.optString("wen33"));
                skillBean.setWen34(skill.optString("wen34"));
                skillBean.setCtime(skill.optString("ctime"));
                skillBean.setMoney_p(skill.optInt("money_p"));
                dataBean.setSkill(skillBean);
            }
            JSONObject idcard = data.optJSONObject("idcard");
            ProDetailEntity.DataBean.IdcardBean idcardBean = new ProDetailEntity.DataBean.IdcardBean();
            if (idcard != null) {
                idcardBean.setUid(idcard.optString("uid"));
                idcardBean.setIdcard_shou(idcard.optString("idcard_shou"));
                idcardBean.setIdcard_zheng(idcard.optString("idcard_zheng"));
                idcardBean.setIdcard_fan(idcard.optString("idcard_fan"));
                idcardBean.setIdcard_ren(idcard.optString("idcard_ren"));
                dataBean.setIdcard(idcardBean);
            }
            JSONArray photo = data.optJSONArray("photo");
            List<ProDetailEntity.DataBean.PhotoBean> photoBeanList = new ArrayList<>();
            if (photo != null && photo.length() > 0) {
                for (int i = 0; i < photo.length(); i++) {
                    JSONObject jsphoto = photo.optJSONObject(i);
                    ProDetailEntity.DataBean.PhotoBean photoBean = new ProDetailEntity.DataBean.PhotoBean();
                    photoBean.setId(jsphoto.optString("id"));
                    photoBean.setUid(jsphoto.optString("uid"));
                    photoBean.setImg(jsphoto.optString("img"));
                    photoBean.setDesc(jsphoto.optString("desc"));
                    photoBean.setCtime(jsphoto.optString("ctime"));
                    photoBeanList.add(photoBean);
                }
                dataBean.setPhoto(photoBeanList);
            }
            JSONArray comment = data.optJSONArray("comment");
            List<ProDetailEntity.DataBean.CommentBean> commentBeanList = new ArrayList<>();
            if (comment != null && comment.length() > 0) {
                for (int i = 0; i < comment.length(); i++) {
                    JSONObject jocomment = comment.getJSONObject(i);
                    ProDetailEntity.DataBean.CommentBean commentBean = new ProDetailEntity.DataBean.CommentBean();
                    commentBean.setId(jocomment.optString("id"));
                    commentBean.setType(jocomment.optString("type"));
                    commentBean.setOid(jocomment.optString("oid"));
                    commentBean.setOuid(jocomment.optString("ouid"));
                    commentBean.setUid(jocomment.optString("uid"));
                    commentBean.setContent(jocomment.optString("content"));
                    commentBean.setXin(jocomment.optString("xin"));
                    commentBean.setL_id(jocomment.optString("l_id"));
                    commentBean.setCtime(jocomment.optString("ctime"));
                    commentBeanList.add(commentBean);
                }
                dataBean.setComment(commentBeanList);
            }
            entity.setData(dataBean);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public static List<ProDetailEntity.DataBean.SkillBean> parseProSkill(String response) {
        String data = JsonUtils.getJsonStr(response, "data");
        JSONObject jdata = null;
        List<ProDetailEntity.DataBean.SkillBean> skillBeanList = new ArrayList<>();
        try {
            jdata = new JSONObject(data);
            JSONArray skill = jdata.optJSONArray("skill");
            for (int i = 0; i < skill.length(); i++) {
                JSONObject jskill = skill.optJSONObject(i);
                ProDetailEntity.DataBean.SkillBean skillBean = new ProDetailEntity.DataBean.SkillBean();
                skillBean.setId(jskill.optString("id"));
                skillBean.setUid(jskill.optString("uid"));
                skillBean.setType(jskill.optString("type"));
                skillBean.setOrder_num(jskill.optString("pro_onum"));
                skillBean.setXing_num(jskill.optString("pro_xing"));
                skillBean.setClassX(jskill.optString("class"));
                skillBean.setAdvantage(jskill.optString("advantage"));
                skillBean.setIs_recommend(jskill.optString("is_recommend"));
                skillBean.setIs_audit(jskill.optString("is_audit"));
                skillBean.setV(jskill.optString("v"));
                skillBean.setIs_ji(jskill.optString("is_ji"));
                skillBean.setIs_ren(jskill.optString("is_ren"));
                skillBean.setWen1(jskill.optString("wen1"));
                skillBean.setWen2(jskill.optString("wen2"));
                skillBean.setWen3(jskill.optString("wen3"));
                skillBean.setWen4(jskill.optString("wen4"));
                skillBean.setWen5(jskill.optString("wen5"));
                skillBean.setWen6(jskill.optString("wen6"));
                skillBean.setWen7(jskill.optString("wen7"));
                skillBean.setWen8(jskill.optString("wen8"));
                skillBean.setWen9(jskill.optString("wen9"));
                skillBean.setWen10(jskill.optString("wen10"));
                skillBean.setWen11(jskill.optString("wen11"));
                skillBean.setWen12(jskill.optString("wen12"));
                skillBean.setWen13(jskill.optString("wen13"));
                skillBean.setWen14(jskill.optString("wen14"));
                skillBean.setWen15(jskill.optString("wen15"));
                skillBean.setWen16(jskill.optString("wen16"));
                skillBean.setWen17(jskill.optString("wen17"));
                skillBean.setWen18(jskill.optString("wen18"));
                skillBean.setWen19(jskill.optString("wen19"));
                skillBean.setWen20(jskill.optString("wen20"));
                skillBean.setWen21(jskill.optString("wen21"));
                skillBean.setWen22(jskill.optString("wen22"));
                skillBean.setWen23(jskill.optString("wen23"));
                skillBean.setWen24(jskill.optString("wen24"));
                skillBean.setWen25(jskill.optString("wen25"));
                skillBean.setWen26(jskill.optString("wen26"));
                skillBean.setWen27(jskill.optString("wen27"));
                skillBean.setWen28(jskill.optString("wen28"));
                skillBean.setWen29(jskill.optString("wen29"));
                skillBean.setWen30(jskill.optString("wen30"));
                skillBean.setWen31(jskill.optString("wen31"));
                skillBean.setWen32(jskill.optString("wen32"));
                skillBean.setWen33(jskill.optString("wen33"));
                skillBean.setWen34(jskill.optString("wen34"));
                skillBean.setCtime(jskill.optString("ctime"));
                skillBean.setMoney_p(jskill.optInt("money_p"));
                skillBeanList.add(skillBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return skillBeanList;
    }

    public static Object selectSingleValue(Map<String, Object> map, String key) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static String getSingleChoiceAnswer(String answer) {
        String[] ss = answer.split("\\|");
        if(ss.length>1){
            if (ss[1].length() > 3) {
                if ("str".equals(ss[1].substring(0, 3))) {
                    ss[1] = ss[1].substring(3, ss[1].length());
                }
            }
        }else{
            return ss[0];
        }

        return ss[1];
    }

    /**
     * 用户端给选择题赋值--问题（英文）
     *
     * @param context
     * @param type    业务类型
     * @param quesId  第几道问题   第一道传0  第二道传1
     * @return
     */
    public static String getENQuestion(Context context, int type, int quesId) {
        SkillProItem skillContentItem = WenPaseUtils.getSkillProItem(context, type);
        List<SkillProItem.ProEn> proEns = skillContentItem.getProEns();
        SkillProItem.ProEn proEn = proEns.get(quesId);
//		List<String> proansen = proEn.getProansen();
        String protitleen = proEn.getProtitleen();
        return protitleen;
    }

    /**
     * 用户端给选择题赋值--答案（英文）
     *
     * @param context
     * @param type    业务类型
     * @param quesId  第几道问题   第一道传0  第二道传1
     * @return
     */
    public static List<String> getENAnswerList(Context context, int type, int quesId) {
        SkillProItem skillContentItem = WenPaseUtils.getSkillProItem(context, type);
        List<SkillProItem.ProEn> proEns = skillContentItem.getProEns();
        SkillProItem.ProEn proEn = proEns.get(quesId);
        List<String> proansen = proEn.getProansen();
//		String protitleen = proEn.getProtitleen();
        return proansen;
    }

    /**
     * 用户端给选择题赋值--问题（中文）
     *
     * @param context
     * @param type    业务类型
     * @param quesId  第几道问题   第一道传0  第二道传1
     * @return
     */
    public static String getZhQuestion(Context context, int type, int quesId) {
        SkillProItem skillContentItem = WenPaseUtils.getSkillProItem(context, type);
        List<SkillProItem.ProCn> proCns = skillContentItem.getProCns();
        SkillProItem.ProCn proCn = proCns.get(quesId);
        String protitlecn = proCn.getProtitlecn();
        return protitlecn;
    }

    /**
     * 用户端给选择题赋值--答案（中文）
     *
     * @param context
     * @param type    业务类型
     * @param quesId  第几道问题   第一道传0  第二道传1
     * @return
     */
    public static List<String> getZhAnswerList(Context context, int type, int quesId) {
        SkillProItem skillContentItem = WenPaseUtils.getSkillProItem(context, type);
        List<SkillProItem.ProCn> proCns = skillContentItem.getProCns();
        SkillProItem.ProCn proCn = proCns.get(quesId);
        List<String> proanscn = proCn.getProanscn();
        return proanscn;
    }

    public static NeedResponseDetailEntity parseNeedResponseDetail(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            NeedResponseDetailEntity entity = new NeedResponseDetailEntity();
            entity.setStatus(jsonObject.optInt("status"));
            JSONObject data = jsonObject.optJSONObject("data");
            NeedResponseDetailEntity.DataBean dataBean = new NeedResponseDetailEntity.DataBean();
            JSONObject date = data.optJSONObject("date");
            NeedResponseDetailEntity.DataBean.DateBean dateBean = new NeedResponseDetailEntity.DataBean.DateBean();
            if (date != null) {
                dateBean.setId(date.optString("id"));
                dateBean.setOrder_id(date.optString("order_id"));
                dateBean.setUid(date.optString("uid"));
                dateBean.setAt_uid(date.optString("at_uid"));
                dateBean.setJ_uid(date.optString("j_uid"));
                dateBean.setJ_num(date.optString("j_num"));
                dateBean.setType(date.optString("type"));
                dateBean.setH_id(date.optString("h_id"));
                dateBean.setService_type(date.optString("service_type"));
                dateBean.setStatics(date.optString("static"));
                dateBean.setIs_ypin(date.optString("is_ypin"));
                dateBean.setIs_fpin(date.optString("is_fpin"));
                dateBean.setIs_zhi(date.optString("is_zhi"));
                dateBean.setWen1(date.optString("wen1"));
                dateBean.setDa1(date.optString("da1"));
                dateBean.setWen2(date.optString("wen2"));
                dateBean.setDa2(date.optString("da2"));
                dateBean.setWen3(date.optString("wen3"));
                dateBean.setDa3(date.optString("da3"));
                dateBean.setWen4(date.optString("wen4"));
                dateBean.setDa4(date.optString("da4"));
                dateBean.setWen5(date.optString("wen5"));
                dateBean.setDa5(date.optString("da5"));
                dateBean.setWen6(date.optString("wen6"));
                dateBean.setDa6(date.optString("da6"));
                dateBean.setWen7(date.optString("wen7"));
                dateBean.setDa7(date.optString("da7"));
                dateBean.setWen8(date.optString("wen8"));
                dateBean.setDa8(date.optString("da8"));
                dateBean.setWen9(date.optString("wen9"));
                dateBean.setDa9(date.optString("da9"));
                dateBean.setWen10(date.optString("wen10"));
                dateBean.setDa10(date.optString("da10"));
                dateBean.setWen11(date.optString("wen11"));
                dateBean.setDa11(date.optString("da11"));
                dateBean.setWen12(date.optString("wen12"));
                dateBean.setDa12(date.optString("da12"));
                dateBean.setWen13(date.optString("wen13"));
                dateBean.setDa13(date.optString("da13"));
                dateBean.setWen14(date.optString("wen14"));
                dateBean.setDa14(date.optString("da14"));
                dateBean.setWen15(date.optString("wen15"));
                dateBean.setDa15(date.optString("da15"));
                dateBean.setWen16(date.optString("wen16"));
                dateBean.setDa16(date.optString("da16"));
                dateBean.setWen17(date.optString("wen17"));
                dateBean.setDa17(date.optString("da17"));
                dateBean.setWen18(date.optString("wen18"));
                dateBean.setDa18(date.optString("da18"));
                dateBean.setWen19(date.optString("wen19"));
                dateBean.setDa19(date.optString("da19"));
                dateBean.setCtime(date.optString("ctime"));
                dataBean.setDate(dateBean);
            }
            JSONObject hous = data.optJSONObject("hous");
            NeedResponseDetailEntity.DataBean.HousBean housBean = new NeedResponseDetailEntity.DataBean.HousBean();
            if (hous != null) {
                housBean.setId(hous.optString("id"));
                housBean.setUid(hous.optString("uid"));
                housBean.setCity(hous.optString("city"));
                housBean.setHousing_type(hous.optString("housing_type"));
                housBean.setAddress_info(hous.optString("address_info"));
                housBean.setLand_area(hous.optString("land_area"));
                housBean.setBuilt_area(hous.optString("built_area"));
                housBean.setGreen_area(hous.optString("green_area"));
                housBean.setCement_area(hous.optString("cement_area"));
                housBean.setAlternate_contact(hous.optString("alternate_contact"));
                housBean.setLat(hous.optString("lat"));
                housBean.setLongx(hous.optString("long"));
                housBean.setCtime(hous.optString("ctime"));
                dataBean.setHous(housBean);
            }
            JSONObject user = data.optJSONObject("user");
            NeedResponseDetailEntity.DataBean.UserBean userBean = new NeedResponseDetailEntity.DataBean.UserBean();
            if (user != null) {
                userBean.setUid(user.optString("uid"));
                userBean.setSex(user.optString("sex"));
                userBean.setAvatar(user.optString("avatar"));
                userBean.setNickname(user.optString("nickname"));
                userBean.setMobile(user.optString("mobile"));
                userBean.setName(user.optString("name"));
                userBean.setIntroduction(user.optString("introduction"));
                userBean.setIs_ren(user.optString("is_ren"));
                userBean.setIs_hou(user.optString("is_hou"));
                userBean.setOnly_label(user.optString("only_label"));
                userBean.setBalance(user.optString("balance"));
                userBean.setAddress(user.optString("address"));
                userBean.setPro_quci(user.optString("pro_quci"));
                userBean.setUser_quci(user.optString("user_quci"));
                userBean.setPro_score(user.optString("pro_score"));
                userBean.setUser_score(user.optString("user_score"));
                userBean.setLat(user.optString("lat"));
                userBean.setLongx(user.optString("long"));
                userBean.setLtime(user.optString("ltime"));
                userBean.setCtime(user.optString("ctime"));
                dataBean.setUser(userBean);
            }

            JSONObject offer = data.optJSONObject("offer");
            NeedResponseDetailEntity.DataBean.OfferBean offerBean = new NeedResponseDetailEntity.DataBean.OfferBean();
            if (offer != null) {
                offerBean.setId(offer.optString("id"));
                offerBean.setOid(offer.optString("oid"));
                offerBean.setUid(offer.optString("uid"));
                offerBean.setHome_fee(offer.optString("home_fee"));
                offerBean.setInspection(offer.optString("inspection"));
                offerBean.setService_type(offer.optString("service_type"));
                offerBean.setHourly(offer.optString("hourly"));
                offerBean.setHour(offer.optString("hour"));
                offerBean.setGeneral(offer.optString("general"));
                offerBean.setMaterial(offer.optString("material"));
                offerBean.setMessage(offer.optString("message"));
                offerBean.setIs_xuan(offer.optString("is_xuan"));
                offerBean.setCtime(offer.optString("ctime"));
                dataBean.setOffer(offerBean);
            }

            JSONObject skill = data.optJSONObject("skill");
            NeedResponseDetailEntity.DataBean.SkillBean skillBean = new NeedResponseDetailEntity.DataBean.SkillBean();
            if (skill != null) {
                skillBean.setIs_audit(skill.optString("is_audit"));
                dataBean.setSkill(skillBean);
            }
            dataBean.setLiao(data.optString("liao"));
            entity.setData(dataBean);
            return entity;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parseOrderStatus(int i,Context context) {//此为用户端  服务端不一样
        String s = "";
        switch (i) {
            case 0:
                s = context.getString(R.string.ten_p1);
                break;
            case 1:
                s = context.getString(R.string.ten_p1);
                break;
            case 2:
                s = "待上门";
                break;
            case 3:
                s = context.getString(R.string.serv_p11);
                break;
            case 4:
                s = "确认报价";
                break;
            case 5:
                s = "确认报价";
                break;
            case 6:
                s = context.getString(R.string.serv_p11);
                break;
            case 7:
                s = "确认开工";
                break;
            case 8:
                s = context.getString(R.string.serv_p11);
                break;
            case 9:
                s = context.getString(R.string.pro_codes_dzf);
                break;
            case 10:
                s = context.getString(R.string.all_st23r);
                break;
            case 11:
                s = context.getString(R.string.pro_codes_dpj);
                break;
            case 12:
                s = "自己取消订单";
                break;
            case 13:
                s = "服务商取消订单";
                break;
        }
        return s;
    }

    public static Map<String, String> parseEvaluateLabel(StarEntity starEntity) {
        Map<String, String> map = new HashMap<>();
        List<StarEntity.DataBean.Str1Bean> str1 = starEntity.getData().getStr1();
        List<StarEntity.DataBean.Str2Bean> str2 = starEntity.getData().getStr2();
        List<StarEntity.DataBean.Str3Bean> str3 = starEntity.getData().getStr3();
        List<StarEntity.DataBean.Str4Bean> str4 = starEntity.getData().getStr4();
        List<StarEntity.DataBean.Str5Bean> str5 = starEntity.getData().getStr5();
        if(AppGlobal.getInstance().getLagStr().equals("zh")){
            for (int i = 0; i < str1.size(); i++) {
                map.put(str1.get(i).getId(), str1.get(i).getName());
            }
            for (int i = 0; i < str2.size(); i++) {
                map.put(str2.get(i).getId(), str2.get(i).getName());
            }
            for (int i = 0; i < str3.size(); i++) {
                map.put(str3.get(i).getId(), str3.get(i).getName());
            }
            for (int i = 0; i < str4.size(); i++) {
                map.put(str4.get(i).getId(), str4.get(i).getName());
            }
            for (int i = 0; i < str5.size(); i++) {
                map.put(str5.get(i).getId(), str5.get(i).getName());
            }
        }else{
            for (int i = 0; i < str1.size(); i++) {
                map.put(str1.get(i).getId(), str1.get(i).yname);
            }
            for (int i = 0; i < str2.size(); i++) {
                map.put(str2.get(i).getId(), str2.get(i).yname);
            }
            for (int i = 0; i < str3.size(); i++) {
                map.put(str3.get(i).getId(), str3.get(i).yname);
            }
            for (int i = 0; i < str4.size(); i++) {
                map.put(str4.get(i).getId(), str4.get(i).yname);
            }
            for (int i = 0; i < str5.size(); i++) {
                map.put(str5.get(i).getId(), str5.get(i).yname);
            }
        }

        return map;
    }

    public static Integer selectSingleKey(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() != -1) {
                return entry.getValue();
            }
        }
        return null;
    }

    //中文
    public static String getCityZhName(CityEntity entity, String id) {
//        List<CityEntity.DataBean> data = entity.getData();
//        for (int i = 0; i < data.size(); i++) {
//            if (id.equals(data.get(i).getId())) {
//                return data.get(i).getName();
//            }
//        }
        return null;
    }

    //英文
    public static String getCityEnName(CityEntity entity, String id) {
//        List<CityEntity.DataBean> data = entity.getData();
//        for (int i = 0; i < data.size(); i++) {
//            if (id.equals(data.get(i).getId())) {
//                return data.get(i).getYname();
//            }
//        }
        return null;
    }

    //根据城市中文名得到城市id
    public static String selectZhCityid(Map<String, String> map, String value) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    //根据城市英文名得到城市id
    public static String selectEnCityid(Map<String, String> map, String value) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

}
