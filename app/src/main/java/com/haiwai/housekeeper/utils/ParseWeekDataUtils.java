package com.haiwai.housekeeper.utils;

import com.haiwai.housekeeper.entity.OrderNewWeekEntity;
import com.haiwai.housekeeper.entity.VipHouseDesignEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ihope006 on 2016/12/15.
 */

public class ParseWeekDataUtils {
    public static OrderNewWeekEntity getOrderNewWeekEntity(String json) {
        OrderNewWeekEntity orderNewWeekEntity = new OrderNewWeekEntity();
        try {
            JSONObject jsonObject = new JSONObject(json);
            int status = jsonObject.optInt("status");
            if (status == 200) {
                OrderNewWeekEntity.DataBean dataBean = new OrderNewWeekEntity.DataBean();
                JSONObject dataObject = jsonObject.optJSONObject("data");
                OrderNewWeekEntity.DataBean.DateBean dateBean = new OrderNewWeekEntity.DataBean.DateBean();
                JSONObject dateObject = dataObject.optJSONObject("date");
                dateBean.setEmonthtime(dateObject.optString("ctime"));
                dateBean.setH_id(dateObject.optString("h_id"));
                dateBean.setId(dateObject.optString("id"));
                dateBean.setIs_que(dateObject.optString("is_que"));
                dateBean.setJ_num(dateObject.optString("j_num"));
                dateBean.setJ_uid(dateObject.optString("j_uid"));
                dateBean.setMath_num(dateObject.optString("math_num"));
                dateBean.setMoney(dateObject.optString("money"));
                dateBean.setMonthtime(dateObject.optString("monthtime"));
                dateBean.setNum(dateObject.optString("num"));
                dateBean.setOrder_id(dateObject.optString("order_id"));
                dateBean.setStaticX(dateObject.optString("static"));
                dateBean.setType(dateObject.optString("type"));
                dateBean.setUid(dateObject.optString("uid"));
                dateBean.setWen1(dateObject.optString("wen1"));
                dateBean.setWen2(dateObject.optString("wen2"));
                dateBean.setWen3(dateObject.optString("wen3"));
                dateBean.setWen4(dateObject.optString("wen4"));
                dateBean.setWen5(dateObject.optString("wen5"));
                dateBean.setWen6(dateObject.optString("wen6"));
                dataBean.setDate(dateBean);
                OrderNewWeekEntity.DataBean.UserBean userBean = new OrderNewWeekEntity.DataBean.UserBean();
                JSONObject userObject = dataObject.optJSONObject("user");
                userBean.setAddress(userObject.optString("address"));
                userBean.setAvatar(userObject.optString("avatar"));
                userBean.setBalance(userObject.optString(""));
                userBean.setCtime(userObject.optString(""));
                userBean.setFans_num(userObject.optString(""));
                userBean.setFollow_num(userObject.optString(""));
                userBean.setIntroduction(userObject.optString(""));
                userBean.setIs_don(userObject.optString(""));
                userBean.setIs_hou(userObject.optString(""));
                userBean.setIs_ren(userObject.optString(""));
                userBean.setLat(userObject.optString(""));
                userBean.setLongX(userObject.optString(""));
                userBean.setLtime(userObject.optString(""));
                userBean.setMobile(userObject.optString(""));
                userBean.setName(userObject.optString(""));
                userBean.setNickname(userObject.optString(""));
                userBean.setOnly_label(userObject.optString(""));
                userBean.setPro_onum(userObject.optString(""));
                userBean.setPro_quci(userObject.optString(""));
                userBean.setPro_score(userObject.optString(""));
                userBean.setPro_xing(userObject.optString(""));
                userBean.setSex(userObject.optString(""));
                userBean.setUid(userObject.optString(""));
                userBean.setUser_onum(userObject.optString(""));
                userBean.setUser_quci(userObject.optString(""));
                userBean.setUser_score(userObject.optString(""));
                userBean.setUser_xing(userObject.optString(""));
                userBean.setWd_content(userObject.optString(""));
                userBean.setWd_num(userObject.optString(""));
                userBean.setWd_time(userObject.optString(""));
                dataBean.setUser(userBean);
                OrderNewWeekEntity.DataBean.HousBean housBean = new OrderNewWeekEntity.DataBean.HousBean();
                JSONObject housObject = dataObject.optJSONObject("hous");
                housBean.setAddress_info(housObject.optString(""));
                housBean.setAlternate_contact(housObject.optString(""));
                housBean.setArea(housObject.optString(""));
                housBean.setCity(housObject.optString(""));
                housBean.setContact_number(housObject.optString(""));
                housBean.setContacts(housObject.optString(""));
                housBean.setCtime(housObject.optString(""));
                housBean.setEmail(housObject.optString(""));
                housBean.setHousing_type(housObject.optString(""));
                housBean.setId(housObject.optString(""));
                housBean.setIs_bill(housObject.optString(""));
                housBean.setIs_del(housObject.optString(""));
                housBean.setLat(housObject.optString(""));
                housBean.setLongX(housObject.optString(""));
                housBean.setUid(housObject.optString(""));
                dataBean.setHous(housBean);
                OrderNewWeekEntity.DataBean.OfferBean offerBean = new OrderNewWeekEntity.DataBean.OfferBean();
                JSONObject offerObject = dataObject.optJSONObject("offer");
                offerBean.setUid(offerObject.optString(""));
                offerBean.setCtime(offerObject.optString(""));
                offerBean.setId(offerObject.optString(""));
                offerBean.setIs_jie(offerObject.optInt(""));
                offerBean.setIs_xuan(offerObject.optString(""));
                offerBean.setMessage(offerObject.optString(""));
                offerBean.setOid(offerObject.optString(""));
                dataBean.setOffer(offerBean);
                List<OrderNewWeekEntity.DataBean.FeedbackBean> feedbackBeens = new ArrayList<>();
                JSONArray feedbackArray = dataObject.optJSONArray("feedback");
                if (feedbackArray != null) {
                    for (int i = 0; i < feedbackArray.length(); i++) {
                        OrderNewWeekEntity.DataBean.FeedbackBean feedbackBean = new OrderNewWeekEntity.DataBean.FeedbackBean();
                        JSONObject feedbackObject = feedbackArray.optJSONObject(i);
                        feedbackBean.setId(feedbackObject.optString("id"));
                        feedbackBean.setCtime(feedbackObject.optString("ctime"));
                        feedbackBean.setContent1(feedbackObject.optString("content1"));
                        feedbackBean.setContent2(feedbackObject.optString("content2"));
                        feedbackBean.setContent3(feedbackObject.optString("content3"));
                        feedbackBean.setImages1(feedbackObject.optString("images1"));
                        feedbackBean.setImages2(feedbackObject.optString("images2"));
                        feedbackBean.setImages3(feedbackObject.optString("images3"));
                        feedbackBean.setIs_new(feedbackObject.optString("is_new"));
                        feedbackBean.setIs_special(feedbackObject.optString("is_special"));
                        feedbackBean.setIs_wan(feedbackObject.optString("is_wan"));
                        feedbackBean.setNumber(feedbackObject.optString("number"));
                        feedbackBean.setOrder_id(feedbackObject.optString("order_id"));
                        feedbackBean.setStime(feedbackObject.optString("stime"));
                        feedbackBean.setWtime1(feedbackObject.optString("wtime1"));
                        feedbackBean.setWtime2(feedbackObject.optString("wtime2"));
                        feedbackBean.setWtime3(feedbackObject.optString("wtime3"));
                        feedbackBeens.add(feedbackBean);
                    }
                }
                dataBean.setFeedback(feedbackBeens);
                List<OrderNewWeekEntity.DataBean.ProblemwBean> problemwBeens = new ArrayList<>();
                JSONArray problemwArray = dataObject.optJSONArray("problemw");
                if (problemwArray != null) {
                    for (int i = 0; i < problemwArray.length(); i++) {
                        OrderNewWeekEntity.DataBean.ProblemwBean problemwBean = new OrderNewWeekEntity.DataBean.ProblemwBean();
                        JSONObject problemwObject = problemwArray.optJSONObject(i);
                        problemwBean.setDoubleX(problemwObject.optString("double"));
                        problemwBean.setId(problemwObject.optString("id"));
                        problemwBean.setPid(problemwObject.optString("pid"));
                        problemwBean.setRemark(problemwObject.optString("remark"));
                        problemwBean.setType(problemwObject.optString("type"));
                        problemwBean.setType1(problemwObject.optString("type1"));
                        problemwBean.setType2(problemwObject.optString("type2"));
                        problemwBean.setValue(problemwObject.optString("value"));
                        problemwBean.setYremark(problemwObject.optString("yremark"));
                        problemwBean.setYvalue(problemwObject.optString("yvalue"));
                        List<OrderNewWeekEntity.DataBean.ProblemBean> problemBeens = new ArrayList<>();
                        JSONArray problemArray = problemwObject.optJSONArray("problem");
                        if (problemArray != null) {
                            for (int j = 0; j < problemArray.length(); j++) {
                                OrderNewWeekEntity.DataBean.ProblemBean problemBean = new OrderNewWeekEntity.DataBean.ProblemBean();
                                JSONObject problemObject = problemArray.optJSONObject(j);
                                problemBean.setYvalue(problemObject.optString("yvalue"));
                                problemBean.setYremark(problemObject.optString("yremark"));
                                problemBean.setValue(problemObject.optString("value"));
                                problemBean.setType2(problemObject.optString("type2"));
                                problemBean.setType(problemObject.optString("type"));
                                problemBean.setDoubleX(problemObject.optString("double"));
                                problemBean.setId(problemObject.optString("id"));
                                problemBean.setPid(problemObject.optString("pid"));
                                problemBean.setRemark(problemObject.optString("remark"));
                                problemBean.setType1(problemObject.optString("type1"));
                                problemBean.setZhi(problemObject.optString("zhi"));
                                problemBeens.add(problemBean);
                            }
                        }
                        problemwBean.setProblem(problemBeens);
                        problemwBeens.add(problemwBean);
                    }
                }
                dataBean.setProblemw(problemwBeens);
                orderNewWeekEntity.setData(dataBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return orderNewWeekEntity;
    }
}
