package com.haiwai.housekeeper.utils;

import com.haiwai.housekeeper.entity.OrderDetailEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ihope10 on 2016/10/31.
 */

public class PaseJsonUtils {
    public static OrderDetailEntity getOrderDetailEntity(String json) {
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        try {
            JSONObject jsonObject = new JSONObject(json);
            int code = jsonObject.optInt("status");
            orderDetailEntity.setStatus(code);
            if (code == 200) {
                OrderDetailEntity.DataBean dataBean = new OrderDetailEntity.DataBean();
                JSONObject dataObject = jsonObject.optJSONObject("data");
                if (dataObject != null) {
                    JSONObject dateObject = dataObject.optJSONObject("date");
                    if (dateObject != null) {
                        OrderDetailEntity.DataBean.DateBean dateBean = new OrderDetailEntity.DataBean.DateBean();
                        dateBean.setDa1(dateObject.optString("da1"));
                        dateBean.setWen1(dateObject.optString("wen1"));
                        dateBean.setDa2(dateObject.optString("da2"));
                        dateBean.setWen2(dateObject.optString("wen2"));
                        dateBean.setDa3(dateObject.optString("da3"));
                        dateBean.setWen3(dateObject.optString("wen3"));
                        dateBean.setDa4(dateObject.optString("da4"));
                        dateBean.setWen4(dateObject.optString("wen4"));
                        dateBean.setDa5(dateObject.optString("da5"));
                        dateBean.setWen5(dateObject.optString("wen5"));
                        dateBean.setDa6(dateObject.optString("da6"));
                        dateBean.setWen6(dateObject.optString("wen6"));
                        dateBean.setDa7(dateObject.optString("da7"));
                        dateBean.setWen7(dateObject.optString("wen7"));
                        dateBean.setDa8(dateObject.optString("da8"));
                        dateBean.setWen8(dateObject.optString("wen8"));
                        dateBean.setDa9(dateObject.optString("da9"));
                        dateBean.setWen9(dateObject.optString("wen9"));
                        dateBean.setDa10(dateObject.optString("da10"));
                        dateBean.setWen10(dateObject.optString("wen10"));
                        dateBean.setDa11(dateObject.optString("da11"));
                        dateBean.setWen11(dateObject.optString("wen11"));
                        dateBean.setDa12(dateObject.optString("da12"));
                        dateBean.setWen12(dateObject.optString("wen12"));
                        dateBean.setDa13(dateObject.optString("da13"));
                        dateBean.setWen13(dateObject.optString("wen13"));
                        dateBean.setDa14(dateObject.optString("da14"));
                        dateBean.setWen14(dateObject.optString("wen14"));
                        dateBean.setDa15(dateObject.optString("da15"));
                        dateBean.setWen15(dateObject.optString("wen15"));
                        dateBean.setDa16(dateObject.optString("da16"));
                        dateBean.setWen16(dateObject.optString("wen16"));
                        dateBean.setDa17(dateObject.optString("da17"));
                        dateBean.setWen17(dateObject.optString("wen17"));
                        dateBean.setDa18(dateObject.optString("da18"));
                        dateBean.setWen18(dateObject.optString("wen18"));
                        dateBean.setDa19(dateObject.optString("da19"));
                        dateBean.setWen19(dateObject.optString("wen19"));
                        dateBean.setAt_uid(dateObject.optString("at_uid"));
                        dateBean.setCtime(dateObject.optString("ctime"));
                        dateBean.setH_id(dateObject.optString("h_id"));
                        dateBean.setId(dateObject.optString("id"));
                        dateBean.setJ_uid(dateObject.optString("j_uid"));
                        dateBean.setOrder_id(dateObject.optString("order_id"));
                        dateBean.setStaticX(dateObject.optString("static"));
                        dateBean.setType(dateObject.optString("type"));
                        dateBean.setUid(dateObject.optString("uid"));
                        dataBean.setDate(dateBean);
                    }

                    JSONObject housObject = dataObject.optJSONObject("hous");
                    if (housObject != null) {
                        OrderDetailEntity.DataBean.HousBean housBean = new OrderDetailEntity.DataBean.HousBean();
                        housBean.setAddress_info(housObject.optString("address_info"));
                        housBean.setAlternate_contact(housObject.optString("alternate_contact"));
                        housBean.setBuilt_area(housObject.optString("built_area"));
                        housBean.setCement_area(housObject.optString("cement_area"));
                        housBean.setCity(housObject.optString("city"));
                        housBean.setCtime(housObject.optString("ctime"));
                        housBean.setGreen_area(housObject.optString("green_area"));
                        housBean.setHousing_type(housObject.optString("housing_type"));
                        housBean.setId(housObject.optString("id"));
                        housBean.setLand_area(housObject.optString("land_area"));
                        housBean.setLat(housObject.optString("lat"));
                        housBean.setLongX(housObject.optString("long"));
                        housBean.setUid(housObject.optString("uid"));
                        dataBean.setHous(housBean);
                    }
                    JSONObject offerObject = dataObject.optJSONObject("offer");
                    if (offerObject != null) {
                        OrderDetailEntity.DataBean.OfferBean offerBean = new OrderDetailEntity.DataBean.OfferBean();
                        int is_jie = offerObject.optInt("is_jie");
                        if (is_jie == 1) {
                            offerBean.setIs_jie(offerObject.optInt("is_jie"));
                            offerBean.setCtime(offerObject.optString("ctime"));
                            offerBean.setGeneral(offerObject.optString("general"));
                            offerBean.setHome_fee(offerObject.optString("home_fee"));
                            offerBean.setHour(offerObject.optString("hour"));
                            offerBean.setHourly(offerObject.optString("hourly"));
                            offerBean.setId(offerObject.optString("id"));
                            offerBean.setInspection(offerObject.optString("inspection"));
                            offerBean.setIs_xuan(offerObject.optString("is_xuan"));
                            offerBean.setMaterial(offerObject.optString("material"));
                            offerBean.setMessage(offerObject.optString("message"));
                            offerBean.setOid(offerObject.optString("oid"));
                            offerBean.setService_type(offerObject.optString("service_type"));
                            offerBean.setUid(offerObject.optString("uid"));
                            dataBean.setOffer(offerBean);
                        } else {
                            offerBean.setIs_jie(is_jie);
                            dataBean.setOffer(offerBean);
                        }
                    }

                    JSONObject userObject = dataObject.optJSONObject("user");
                    if (userObject != null) {
                        OrderDetailEntity.DataBean.UserBean userBean = new OrderDetailEntity.DataBean.UserBean();
                        userBean.setAddress(userObject.optString("address"));
                        userBean.setAvatar(userObject.optString("avatar"));
                        userBean.setBalance(userObject.optString("balance"));
                        userBean.setCtime(userObject.optString("ctime"));
                        userBean.setIntroduction(userObject.optString("introduction"));
                        userBean.setIs_ren(userObject.optString("is_ren"));
                        userBean.setLat(userObject.optString("lat"));
                        userBean.setLongX(userObject.optString("long"));
                        userBean.setLtime(userObject.optString("ltime"));
                        userBean.setMobile(userObject.optString("mobile"));
                        userBean.setName(userObject.optString("name"));
                        userBean.setNickname(userObject.optString("nickname"));
                        userBean.setOnly_label(userObject.optString("only_label"));
                        userBean.setPro_quci(userObject.optString("pro_quci"));
                        userBean.setSex(userObject.optString("sex"));
                        userBean.setUid(userObject.optString("uid"));
                        userBean.setUser_quci(userObject.optString("user_quci"));
                        dataBean.setUser(userBean);
                    }
                }
                dataBean.setLiao(dataObject.optString("liao"));
                orderDetailEntity.setData(dataBean);
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return orderDetailEntity;
    }
}
