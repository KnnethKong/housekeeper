package com.haiwai.housekeeper.utils;

import com.haiwai.housekeeper.entity.IdCardAndSkillEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ihope10 on 2016/10/18.
 */

public class IdCardJsonUtils {

    public static IdCardAndSkillEntity getIdCardAndSkillEntity(String json) {
        IdCardAndSkillEntity idCardAndSkillEntity = new IdCardAndSkillEntity();
        try {
            JSONObject jsonObject = new JSONObject(json);
            String status = jsonObject.optString("status");
            JSONObject dataObject = jsonObject.optJSONObject("data");
            IdCardAndSkillEntity.DataBean skillDaDataBean = new IdCardAndSkillEntity.DataBean();

            JSONObject userIdcardObject = dataObject.optJSONObject("user_idcard");
            IdCardAndSkillEntity.DataBean.UserIdcardBean userIdcardBean = new IdCardAndSkillEntity.DataBean.UserIdcardBean();
            userIdcardBean.setUid(userIdcardObject.optString("uid"));
            userIdcardBean.setIdcard_fan(userIdcardObject.optString("idcard_fan"));
            userIdcardBean.setIdcard_ren(userIdcardObject.optString("idcard_ren"));
            userIdcardBean.setIdcard_shou(userIdcardObject.optString("idcard_shou"));
            userIdcardBean.setIdcard_zheng(userIdcardObject.optString("idcard_zheng"));
            skillDaDataBean.setUser_idcard(userIdcardBean);
            JSONArray userSkillArray = dataObject.optJSONArray("user_skill");
            List<IdCardAndSkillEntity.DataBean.UserSkillBean> userSkillBeanList = new ArrayList<>();
            for (int i = 0; i < userSkillArray.length(); i++) {
                JSONObject jObject = userSkillArray.optJSONObject(i);
                IdCardAndSkillEntity.DataBean.UserSkillBean userSkillBean = new IdCardAndSkillEntity.DataBean.UserSkillBean();
                userSkillBean.setAdvantage(jObject.optString("advantage"));
                userSkillBean.setClassX(jObject.optString("class"));
                userSkillBean.setCtime(jObject.optString("ctime"));
                userSkillBean.setId(jObject.optString("id"));
                userSkillBean.setIs_audit(jObject.optString("is_audit"));
                userSkillBean.setIs_recommend(jObject.optString("is_recommend"));
                userSkillBean.setIs_ren(jObject.optString("is_ren"));
                userSkillBean.setOrder_num(jObject.optString("order_num"));
                userSkillBean.setType(jObject.optString("type"));
                userSkillBean.setUid(jObject.optString("uid"));
                userSkillBean.setWen1(jObject.optString("wen1"));
                userSkillBean.setWen2(jObject.optString("wen2"));
                userSkillBean.setWen3(jObject.optString("wen3"));
                userSkillBean.setWen4(jObject.optString("wen4"));
                userSkillBean.setWen5(jObject.optString("wen5"));
                userSkillBean.setWen6(jObject.optString("wen6"));
                userSkillBean.setWen7(jObject.optString("wen7"));
                userSkillBean.setWen8(jObject.optString("wen8"));
                userSkillBean.setWen9(jObject.optString("wen9"));
                userSkillBean.setWen10(jObject.optString("wen10"));
                userSkillBean.setWen11(jObject.optString("wen11"));
                userSkillBean.setWen12(jObject.optString("wen12"));
                userSkillBean.setWen13(jObject.optString("wen13"));
                userSkillBean.setWen14(jObject.optString("wen14"));
                userSkillBean.setWen15(jObject.optString("wen15"));
                userSkillBean.setWen16(jObject.optString("wen16"));
                userSkillBean.setWen17(jObject.optString("wen17"));
                userSkillBean.setWen18(jObject.optString("wen18"));
                userSkillBean.setWen19(jObject.optString("wen19"));
                userSkillBean.setWen20(jObject.optString("wen20"));
                userSkillBean.setWen21(jObject.optString("wen21"));
                userSkillBean.setWen22(jObject.optString("wen22"));
                userSkillBean.setWen23(jObject.optString("wen23"));
                userSkillBean.setWen24(jObject.optString("wen24"));
                userSkillBean.setWen25(jObject.optString("wen25"));
                userSkillBean.setWen26(jObject.optString("wen26"));
                userSkillBean.setWen27(jObject.optString("wen27"));
                userSkillBean.setWen28(jObject.optString("wen28"));
                userSkillBean.setWen29(jObject.optString("wen29"));
                userSkillBean.setWen30(jObject.optString("wen30"));
                userSkillBean.setWen31(jObject.optString("wen31"));
                userSkillBean.setWen32(jObject.optString("wen32"));
                userSkillBean.setWen33(jObject.optString("wen33"));
                userSkillBean.setWen34(jObject.optString("wen34"));
                JSONArray jSkillArray = jObject.optJSONArray("skill_ren");
                List<IdCardAndSkillEntity.DataBean.UserSkillBean.SkillRenBean> skillRenBeanList = new ArrayList<>();
                for (int j = 0; j < jSkillArray.length(); j++) {
                    JSONObject skillObject = jSkillArray.getJSONObject(j);
                    IdCardAndSkillEntity.DataBean.UserSkillBean.SkillRenBean skillRenBean = new IdCardAndSkillEntity.DataBean.UserSkillBean.SkillRenBean();
                    skillRenBean.setCtime(skillObject.optString("ctime"));
                    skillRenBean.setId(skillObject.optString("id"));
                    skillRenBean.setIs_ren(skillObject.optString("is_ren"));
                    skillRenBean.setSid(skillObject.optString("sid"));
                    skillRenBean.setZname(skillObject.optString("zname"));
                    JSONArray jsonArray = skillObject.optJSONArray("zimg");
                    if (jsonArray != null) {
                        List<String> strList = new ArrayList<>();
                        for (int k = 0; k < jsonArray.length(); k++) {
                            String str = (String) jsonArray.opt(k);
                            strList.add(str);
                        }
                        skillRenBean.setZimg(strList);
                    }
                    skillRenBeanList.add(skillRenBean);
                }
                userSkillBean.setSkill_ren(skillRenBeanList);
                userSkillBeanList.add(userSkillBean);
            }
            skillDaDataBean.setUser_skill(userSkillBeanList);
            idCardAndSkillEntity.setData(skillDaDataBean);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return idCardAndSkillEntity;
    }
}
