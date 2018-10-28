package com.haiwai.housekeeper.utils;

import com.haiwai.housekeeper.entity.VipHouseDesignEntity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ihope006 on 2016/11/24.
 */

public class VipParseDataUtils {

    /**
     * //租赁管理
     *
     * @param billObject
     * @return
     */
    public static VipHouseDesignEntity.DataBean.BillBean parseBillBean(JSONObject billObject) {
        VipHouseDesignEntity.DataBean.BillBean billBean = new VipHouseDesignEntity.DataBean.BillBean();
        billBean.setAvatar(billObject.optString("avatar"));
        billBean.setCtime(billObject.optString("ctime"));
        billBean.setEmonthtime(billObject.optString("emonthtime"));
        billBean.setH_id(billObject.optString("h_id"));
        billBean.setId(billObject.optString("id"));


        billBean.setIs_hou(billObject.optString("is_hou"));
        billBean.setS(billObject.optString("s"));
        billBean.setMobile(billObject.optString("mobile"));
        billBean.setPro_score(billObject.optString("pro_score"));
        billBean.setUser_xing(billObject.optString("pro_xing"));
        billBean.setUser_onum(billObject.optString("pro_onum"));

        billBean.setIs_ren(billObject.optString("is_ren"));

        billBean.setIs_que(billObject.optString("is_que"));

        billBean.setJ_num(billObject.optString("j_num"));
        billBean.setJ_uid(billObject.optString("j_uid"));
        billBean.setMath_num(billObject.optString("math_num"));
        billBean.setMoney(billObject.optString("money"));
        billBean.setMonthtime(billObject.optString("monthtime"));
        billBean.setNickname(billObject.optString("nickname"));
        billBean.setNum(billObject.optString("num"));
        billBean.setOrder_id(billObject.optString("order_id"));
        billBean.setSid(billObject.optString("sid"));
        billBean.setStaticX(billObject.optString("static"));
        billBean.setType(billObject.optString("type"));
        billBean.setUid(billObject.optString("uid"));
        VipHouseDesignEntity.DataBean.BillBean.FeedbackBean feedbackBean = new VipHouseDesignEntity.DataBean.BillBean.FeedbackBean();
        JSONObject billFeadBackObejct = billObject.optJSONObject("feedback");
        if(billFeadBackObejct != null){
            feedbackBean.setCtime(billFeadBackObejct.optString("ctime"));
            feedbackBean.setGrent(billFeadBackObejct.optString("grent"));
            feedbackBean.setH_id(billFeadBackObejct.optString("h_id"));
            feedbackBean.setHome_remark(billFeadBackObejct.optString("home_remark"));
            feedbackBean.setId(billFeadBackObejct.optString("id"));
            feedbackBean.setRen_nickname(billFeadBackObejct.optString("ren_nickname"));
            feedbackBean.setIs_da(billFeadBackObejct.optString("is_da"));
            feedbackBean.setIs_static(billFeadBackObejct.optString("is_static"));
            feedbackBean.setMonthtime(billFeadBackObejct.optString("monthtime"));
            feedbackBean.setOrder_id(billFeadBackObejct.optString("order_id"));
            feedbackBean.setPrice_remark(billFeadBackObejct.optString("price_remark"));
            feedbackBean.setRent(billFeadBackObejct.optString("rent"));
            feedbackBean.setTenant_remark(billFeadBackObejct.optString("tenant_remark"));
            feedbackBean.setTenant_static(billFeadBackObejct.optString("tenant_static"));
            feedbackBean.setUid(billFeadBackObejct.optString("uid"));
            feedbackBean.setWrent(billFeadBackObejct.optString("wrent"));
            feedbackBean.setWtime1(billFeadBackObejct.optString("wtime1"));
            feedbackBean.setWtime2(billFeadBackObejct.optString("wtime2"));
            feedbackBean.setWtime3(billFeadBackObejct.optString("wtime3"));
        }
        billBean.setFeedback(feedbackBean);
        return billBean;
    }

    /**
     * //家政服务
     *
     * @param homeObject
     * @return
     */
    public static VipHouseDesignEntity.DataBean.HomeBean parseHomeBean(JSONObject homeObject) {
        VipHouseDesignEntity.DataBean.HomeBean homeBean = new VipHouseDesignEntity.DataBean.HomeBean();
        homeBean.setAvatar(homeObject.optString("avatar"));
        homeBean.setNickname(homeObject.optString("nickname"));
        homeBean.setCtime(homeObject.optString("ctime"));
        homeBean.setEmonthtime(homeObject.optString("emonthtime"));
        homeBean.setH_id(homeObject.optString("h_id"));
        homeBean.setId(homeObject.optString("id"));
        homeBean.setIs_que(homeObject.optString("is_que"));
        homeBean.setJ_num(homeObject.optString("j_num"));


        homeBean.setIs_hou(homeObject.optString("is_hou"));
        homeBean.setS(homeObject.optString("s"));
        homeBean.setMobile(homeObject.optString("mobile"));
        homeBean.setPro_score(homeObject.optString("pro_score"));
        homeBean.setUser_xing(homeObject.optString("user_xing"));
        homeBean.setIs_ren(homeObject.optString("is_ren"));
        homeBean.setUser_onum(homeObject.optString("user_onum"));


        homeBean.setJ_uid(homeObject.optString("j_uid"));
        homeBean.setMath_num(homeObject.optString("math_num"));
        homeBean.setMoney(homeObject.optString("money"));
        homeBean.setMonthtime(homeObject.optString("monthtime"));
        homeBean.setNum(homeObject.optString("num"));
        homeBean.setOrder_id(homeObject.optString("order_id"));
        homeBean.setSid(homeObject.optString("sid"));
        homeBean.setStaticX(homeObject.optString("static"));
        homeBean.setType(homeObject.optString("type"));
        homeBean.setUid(homeObject.optString("uid"));
        homeBean.setWan_num(homeObject.optInt("wan_num"));
        List<VipHouseDesignEntity.DataBean.HomeBean.FeedbackBeanX> homeFeadbackBeans = new ArrayList<>();
        JSONArray homeFeadbackArray = homeObject.optJSONArray("feedback");
        if (homeFeadbackArray != null) {
            for (int i = 0; i < homeFeadbackArray.length(); i++) {
                JSONObject homeFeadbackObject = homeFeadbackArray.optJSONObject(i);
                VipHouseDesignEntity.DataBean.HomeBean.FeedbackBeanX homeFeadbackBean = new VipHouseDesignEntity.DataBean.HomeBean.FeedbackBeanX();
                homeFeadbackBean.setContent1(homeFeadbackObject.optString("content1"));
                homeFeadbackBean.setContent2(homeFeadbackObject.optString("content2"));
                homeFeadbackBean.setContent3(homeFeadbackObject.optString("content3"));
                homeFeadbackBean.setCtime(homeFeadbackObject.optString("ctime"));
                homeFeadbackBean.setId(homeFeadbackObject.optString("id"));
                homeFeadbackBean.setImages1(homeFeadbackObject.optString("images1"));
                homeFeadbackBean.setImages2(homeFeadbackObject.optString("images2"));
                homeFeadbackBean.setImages3(homeFeadbackObject.optString("images3"));
                homeFeadbackBean.setIs_new(homeFeadbackObject.optString("is_new"));
                homeFeadbackBean.setIs_special(homeFeadbackObject.optString("is_special"));
                homeFeadbackBean.setIs_wan(homeFeadbackObject.optString("is_wan"));
                homeFeadbackBean.setNumber(homeFeadbackObject.optString("number"));
                homeFeadbackBean.setOrder_id(homeFeadbackObject.optString("order_id"));
                homeFeadbackBean.setStime(homeFeadbackObject.optString("stime"));
                homeFeadbackBean.setWtime1(homeFeadbackObject.optString("wtime1"));
                homeFeadbackBean.setWtime2(homeFeadbackObject.optString("wtime2"));
                homeFeadbackBean.setWtime3(homeFeadbackObject.optString("wtime3"));
                homeFeadbackBeans.add(homeFeadbackBean);
            }
            homeBean.setFeedback(homeFeadbackBeans);
        }
        return homeBean;
    }


    /**
     * //园艺
     *
     * @param horticultureObjectLst
     * @return
     */
    public static List<VipHouseDesignEntity.DataBean.HorticultureBean> parseHorticultureBean(JSONArray horticultureObjectLst) {
        List<VipHouseDesignEntity.DataBean.HorticultureBean> horticultureBeanList=new ArrayList<>();
        for(int h=0;h<horticultureObjectLst.length();h++) {
            JSONObject horticultureObject = horticultureObjectLst.optJSONObject(h);
            VipHouseDesignEntity.DataBean.HorticultureBean horticultureBean = new VipHouseDesignEntity.DataBean.HorticultureBean();
            horticultureBean.setAvatar(horticultureObject.optString("avatar"));
            horticultureBean.setNickname(horticultureObject.optString("nickname"));
            horticultureBean.setCtime(horticultureObject.optString("ctime"));
            horticultureBean.setEmonthtime(horticultureObject.optString("emonthtime"));
            horticultureBean.setH_id(horticultureObject.optString("h_id"));
            horticultureBean.setId(horticultureObject.optString("id"));

            horticultureBean.setIs_ren(horticultureObject.optString("is_ren"));
            horticultureBean.setIs_hou(horticultureObject.optString("is_hou"));
            horticultureBean.setS(horticultureObject.optString("s"));
            horticultureBean.setMobile(horticultureObject.optString("mobile"));
            horticultureBean.setPro_score(horticultureObject.optString("pro_score"));
            horticultureBean.setUser_xing(horticultureObject.optString("pro_xing"));
            horticultureBean.setUser_onum(horticultureObject.optString("pro_onum"));
            horticultureBean.setIs_que(horticultureObject.optString("is_que"));
            horticultureBean.setJ_num(horticultureObject.optString("j_num"));
            horticultureBean.setJ_uid(horticultureObject.optString("j_uid"));
            horticultureBean.setMath_num(horticultureObject.optString("math_num"));
            horticultureBean.setMoney(horticultureObject.optString("money"));
            horticultureBean.setMonthtime(horticultureObject.optString("monthtime"));
            horticultureBean.setNum(horticultureObject.optString("num"));
            horticultureBean.setOrder_id(horticultureObject.optString("order_id"));
            horticultureBean.setSid(horticultureObject.optString("sid"));
            horticultureBean.setStaticX(horticultureObject.optString("static"));
            horticultureBean.setType(horticultureObject.optString("type"));
            horticultureBean.setUid(horticultureObject.optString("uid"));
            horticultureBean.setWan_num(horticultureObject.optInt("wan_num"));
            List<VipHouseDesignEntity.DataBean.HorticultureBean.FeedbackBeanXX> horticultureFeadbackBeans = new ArrayList<>();
            JSONArray horticultureFeadbackArray = horticultureObject.optJSONArray("feedback");
            if (horticultureFeadbackArray != null) {
                for (int i = 0; i < horticultureFeadbackArray.length(); i++) {
                    JSONObject horticultureFeadbackObject = horticultureFeadbackArray.optJSONObject(i);
                    VipHouseDesignEntity.DataBean.HorticultureBean.FeedbackBeanXX horticultureFeadbackBean = new VipHouseDesignEntity.DataBean.HorticultureBean.FeedbackBeanXX();
                    horticultureFeadbackBean.setContent1(horticultureFeadbackObject.optString("content1"));
                    horticultureFeadbackBean.setContent2(horticultureFeadbackObject.optString("content2"));
                    horticultureFeadbackBean.setContent3(horticultureFeadbackObject.optString("content3"));
                    horticultureFeadbackBean.setCtime(horticultureFeadbackObject.optString("ctime"));
                    horticultureFeadbackBean.setId(horticultureFeadbackObject.optString("id"));
                    horticultureFeadbackBean.setImages1(horticultureFeadbackObject.optString("images1"));
                    horticultureFeadbackBean.setImages2(horticultureFeadbackObject.optString("images2"));
                    horticultureFeadbackBean.setImages3(horticultureFeadbackObject.optString("images3"));
                    horticultureFeadbackBean.setIs_new(horticultureFeadbackObject.optString("is_new"));
                    horticultureFeadbackBean.setIs_special(horticultureFeadbackObject.optString("is_special"));
                    horticultureFeadbackBean.setIs_wan(horticultureFeadbackObject.optString("is_wan"));
                    horticultureFeadbackBean.setNumber(horticultureFeadbackObject.optString("number"));
                    horticultureFeadbackBean.setOrder_id(horticultureFeadbackObject.optString("order_id"));
                    horticultureFeadbackBean.setStime(horticultureFeadbackObject.optString("stime"));
                    horticultureFeadbackBean.setWtime1(horticultureFeadbackObject.optString("wtime1"));
                    horticultureFeadbackBean.setWtime2(horticultureFeadbackObject.optString("wtime2"));
                    horticultureFeadbackBean.setWtime3(horticultureFeadbackObject.optString("wtime3"));
                    horticultureFeadbackBeans.add(horticultureFeadbackBean);
                }
                horticultureBean.setFeedback(horticultureFeadbackBeans);
            }
            horticultureBeanList.add(horticultureBean);
        }
        return horticultureBeanList;
    }

    /**
     * //房产
     *
     * @param housObject
     * @return
     */
    public static VipHouseDesignEntity.DataBean.HousBean parseHousBean(JSONObject housObject) {
        VipHouseDesignEntity.DataBean.HousBean housBean = new VipHouseDesignEntity.DataBean.HousBean();
        housBean.setAvatar(housObject.optString("avatar"));
        housBean.setNickname(housObject.optString("nickname"));
        housBean.setAddress_info(housObject.optString("address_info"));
        housBean.setAlternate_contact(housObject.optString("alternate_contact"));
        housBean.setBuilt_area(housObject.optString("built_area"));
        housBean.setCement_area(housObject.optString("cement_area"));
        housBean.setCity(housObject.optString("city"));
        housBean.setCtime(housObject.optString("ctime"));
        housBean.setGreen_area(housObject.optString("green_area"));
        housBean.setHousing_type(housObject.optString("housing_type"));
        housBean.setId(housObject.optString("id"));
        housBean.setIs_del(housObject.optString("is_del"));
        housBean.setLand_area(housObject.optString("land_area"));
        housBean.setLat(housObject.optString("lat"));
        housBean.setLongX(housObject.optString("long"));
        housBean.setUid(housObject.optString("uid"));
        return housBean;
    }

    /**
     * //房屋巡视
     *
     * @param vacancyObject
     * @return
     */
    public static VipHouseDesignEntity.DataBean.VacancyBean parseVacancyBean(JSONObject vacancyObject) {
        VipHouseDesignEntity.DataBean.VacancyBean vacancyBean = new VipHouseDesignEntity.DataBean.VacancyBean();
        vacancyBean.setAvatar(vacancyObject.optString("avatar"));
        vacancyBean.setNickname(vacancyObject.optString("nickname"));
        vacancyBean.setCtime(vacancyObject.optString("ctime"));
        vacancyBean.setEmonthtime(vacancyObject.optString("emonthtime"));
        vacancyBean.setH_id(vacancyObject.optString("h_id"));
        vacancyBean.setId(vacancyObject.optString("id"));


        vacancyBean.setIs_ren(vacancyObject.optString("is_ren"));
        vacancyBean.setIs_hou(vacancyObject.optString("is_hou"));
        vacancyBean.setS(vacancyObject.optString("s"));
        vacancyBean.setMobile(vacancyObject.optString("mobile"));
        vacancyBean.setPro_score(vacancyObject.optString("pro_score"));
        vacancyBean.setUser_xing(vacancyObject.optString("pro_xing"));

        vacancyBean.setUser_onum(vacancyObject.optString("pro_onum"));
        vacancyBean.setIs_que(vacancyObject.optString("is_que"));
        vacancyBean.setJ_num(vacancyObject.optString("j_num"));
        vacancyBean.setJ_uid(vacancyObject.optString("j_uid"));
        vacancyBean.setMath_num(vacancyObject.optString("math_num"));
        vacancyBean.setMoney(vacancyObject.optString("money"));
        vacancyBean.setMonthtime(vacancyObject.optString("monthtime"));
        vacancyBean.setNum(vacancyObject.optString("num"));
        vacancyBean.setOrder_id(vacancyObject.optString("order_id"));
        vacancyBean.setSid(vacancyObject.optString("sid"));
        vacancyBean.setStaticX(vacancyObject.optString("static"));
        vacancyBean.setType(vacancyObject.optString("type"));
        vacancyBean.setUid(vacancyObject.optString("uid"));
        vacancyBean.setWan_num(vacancyObject.optInt("wan_num"));
        List<VipHouseDesignEntity.DataBean.VacancyBean.FeedbackBeanXXX> vacancyFeadbackBeans = new ArrayList<>();
        JSONArray vacancyFeadbackArray = vacancyObject.optJSONArray("feedback");
        if (vacancyFeadbackArray != null) {
            for (int i = 0; i < vacancyFeadbackArray.length(); i++) {
                JSONObject vacancyFeadbackObject = vacancyFeadbackArray.optJSONObject(i);
                VipHouseDesignEntity.DataBean.VacancyBean.FeedbackBeanXXX vacancyFeadbackBean = new VipHouseDesignEntity.DataBean.VacancyBean.FeedbackBeanXXX();
                vacancyFeadbackBean.setContent1(vacancyFeadbackObject.optString("content1"));
                vacancyFeadbackBean.setContent2(vacancyFeadbackObject.optString("content2"));
                vacancyFeadbackBean.setContent3(vacancyFeadbackObject.optString("content3"));
                vacancyFeadbackBean.setCtime(vacancyFeadbackObject.optString("ctime"));
                vacancyFeadbackBean.setId(vacancyFeadbackObject.optString("id"));
                vacancyFeadbackBean.setImages1(vacancyFeadbackObject.optString("images1"));
                vacancyFeadbackBean.setImages2(vacancyFeadbackObject.optString("images2"));
                vacancyFeadbackBean.setImages3(vacancyFeadbackObject.optString("images3"));
                vacancyFeadbackBean.setIs_new(vacancyFeadbackObject.optString("is_new"));
                vacancyFeadbackBean.setIs_special(vacancyFeadbackObject.optString("is_special"));
                vacancyFeadbackBean.setIs_wan(vacancyFeadbackObject.optString("is_wan"));
                vacancyFeadbackBean.setNumber(vacancyFeadbackObject.optString("number"));
                vacancyFeadbackBean.setOrder_id(vacancyFeadbackObject.optString("order_id"));
                vacancyFeadbackBean.setStime(vacancyFeadbackObject.optString("stime"));
                vacancyFeadbackBean.setWtime1(vacancyFeadbackObject.optString("wtime1"));
                vacancyFeadbackBean.setWtime2(vacancyFeadbackObject.optString("wtime2"));
                vacancyFeadbackBean.setWtime3(vacancyFeadbackObject.optString("wtime3"));
                vacancyFeadbackBeans.add(vacancyFeadbackBean);
            }
            vacancyBean.setFeedback(vacancyFeadbackBeans);
        }
        return vacancyBean;
    }


}
