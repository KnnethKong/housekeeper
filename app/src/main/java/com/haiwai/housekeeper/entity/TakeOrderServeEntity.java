package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ihope007 on 2016/9/8.
 */
public class TakeOrderServeEntity {

    /**
     * data : [{"address":"","at_uid":"0","avatar":"","balance":"99848.00","ctime":"1478744429","fans_num":"1","follow_num":"2","general":"25","home_fee":"2222","hour":"0","hourly":"0","id":"246","inspection":"555","introduction":"","is_don":"0","is_hou":"0","is_ren":"1","is_skill":"1","is_xuan":"0","km":"","lat":"39.904989","long":"116.405285","ltime":"1485049191","material":"0","message":"","mobile":"18510804169","name":"","nickname":"","oid":"402","only_label":"rw6599","order_num":"0","pro_onum":"0","pro_quci":"0","pro_score":"0","pro_xing":"0","registrationid":"","service_type":"2","sex":"0","type":"1","uid":"8","user_onum":"0","user_quci":"0","user_score":"0","user_xing":"0","v":"0","version":"2","wd_content":"ssss","wd_num":"36","wd_time":"1484729256","xing_num":"0"},{"address":"","at_uid":"0","avatar":"","balance":"0.00","ctime":"1480667575","fans_num":"0","follow_num":"4","general":"55","home_fee":"0","hour":"0","hourly":"0","id":"239","inspection":"0","introduction":"我是卖报的小行家","is_don":"0","is_hou":"0","is_ren":"1","is_skill":"0","is_xuan":"0","km":"","lat":"39.9","long":"116.4","ltime":"1485005647","material":"0","message":"","mobile":"15933975930","name":"Fiona","nickname":"绿萝","oid":"402","only_label":"d71488","order_num":"2","pro_onum":"1","pro_quci":"0","pro_score":"2","pro_xing":"5","registrationid":"170976fa8a8c0af8656","service_type":"2","sex":"0","type":"1","uid":"26","user_onum":"0","user_quci":"0","user_score":"0","user_xing":"0","v":"1","version":"2","wd_content":"ssss","wd_num":"36","wd_time":"1484729256","xing_num":"0"}]
     * status : 200
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * address :
         * at_uid : 0
         * avatar :
         * balance : 99848.00
         * ctime : 1478744429
         * fans_num : 1
         * follow_num : 2
         * general : 25
         * home_fee : 2222
         * hour : 0
         * hourly : 0
         * id : 246
         * inspection : 555
         * introduction :
         * is_don : 0
         * is_hou : 0
         * is_ren : 1
         * is_skill : 1
         * is_xuan : 0
         * km :
         * lat : 39.904989
         * long : 116.405285
         * ltime : 1485049191
         * material : 0
         * message :
         * mobile : 18510804169
         * name :
         * nickname :
         * oid : 402
         * only_label : rw6599
         * order_num : 0
         * pro_onum : 0
         * pro_quci : 0
         * pro_score : 0
         * pro_xing : 0
         * registrationid :
         * service_type : 2
         * sex : 0
         * type : 1
         * uid : 8
         * user_onum : 0
         * user_quci : 0
         * user_score : 0
         * user_xing : 0
         * v : 0
         * version : 2
         * wd_content : ssss
         * wd_num : 36
         * wd_time : 1484729256
         * xing_num : 0
         */

        private String address;
        private String at_uid;
        private String avatar;
        private String balance;
        private String ctime;
        private String fans_num;
        private String follow_num;
        private String general;
        private String home_fee;
        private String hour;
        private String hourly;
        private String id;
        private String inspection;
        private String introduction;
        private String is_don;
        private String is_hou;
        private String is_ren;
        private String is_skill;
        private String is_xuan;
        private String km;
        private String lat;
        @SerializedName("long")
        private String longX;
        private String ltime;
        private String material;
        private String message;
        private String mobile;
        private String name;
        private String nickname;
        private String oid;
        private String only_label;
        private String order_num;
        private String pro_onum;
        private String pro_quci;
        private String pro_score;
        private String pro_xing;
        private String registrationid;
        private String service_type;
        private String sex;
        private String type;
        private String uid;
        private String user_onum;
        private String user_quci;
        private String user_score;
        private String user_xing;
        private String v;
        private String version;
        private String wd_content;
        private String wd_num;
        private String wd_time;
        private String xing_num;
        private String s;

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAt_uid() {
            return at_uid;
        }

        public void setAt_uid(String at_uid) {
            this.at_uid = at_uid;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getFans_num() {
            return fans_num;
        }

        public void setFans_num(String fans_num) {
            this.fans_num = fans_num;
        }

        public String getFollow_num() {
            return follow_num;
        }

        public void setFollow_num(String follow_num) {
            this.follow_num = follow_num;
        }

        public String getGeneral() {
            return general;
        }

        public void setGeneral(String general) {
            this.general = general;
        }

        public String getHome_fee() {
            return home_fee;
        }

        public void setHome_fee(String home_fee) {
            this.home_fee = home_fee;
        }

        public String getHour() {
            return hour;
        }

        public void setHour(String hour) {
            this.hour = hour;
        }

        public String getHourly() {
            return hourly;
        }

        public void setHourly(String hourly) {
            this.hourly = hourly;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInspection() {
            return inspection;
        }

        public void setInspection(String inspection) {
            this.inspection = inspection;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getIs_don() {
            return is_don;
        }

        public void setIs_don(String is_don) {
            this.is_don = is_don;
        }

        public String getIs_hou() {
            return is_hou;
        }

        public void setIs_hou(String is_hou) {
            this.is_hou = is_hou;
        }

        public String getIs_ren() {
            return is_ren;
        }

        public void setIs_ren(String is_ren) {
            this.is_ren = is_ren;
        }

        public String getIs_skill() {
            return is_skill;
        }

        public void setIs_skill(String is_skill) {
            this.is_skill = is_skill;
        }

        public String getIs_xuan() {
            return is_xuan;
        }

        public void setIs_xuan(String is_xuan) {
            this.is_xuan = is_xuan;
        }

        public String getKm() {
            return km;
        }

        public void setKm(String km) {
            this.km = km;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLongX() {
            return longX;
        }

        public void setLongX(String longX) {
            this.longX = longX;
        }

        public String getLtime() {
            return ltime;
        }

        public void setLtime(String ltime) {
            this.ltime = ltime;
        }

        public String getMaterial() {
            return material;
        }

        public void setMaterial(String material) {
            this.material = material;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }

        public String getOnly_label() {
            return only_label;
        }

        public void setOnly_label(String only_label) {
            this.only_label = only_label;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getPro_onum() {
            return pro_onum;
        }

        public void setPro_onum(String pro_onum) {
            this.pro_onum = pro_onum;
        }

        public String getPro_quci() {
            return pro_quci;
        }

        public void setPro_quci(String pro_quci) {
            this.pro_quci = pro_quci;
        }

        public String getPro_score() {
            return pro_score;
        }

        public void setPro_score(String pro_score) {
            this.pro_score = pro_score;
        }

        public String getPro_xing() {
            return pro_xing;
        }

        public void setPro_xing(String pro_xing) {
            this.pro_xing = pro_xing;
        }

        public String getRegistrationid() {
            return registrationid;
        }

        public void setRegistrationid(String registrationid) {
            this.registrationid = registrationid;
        }

        public String getService_type() {
            return service_type;
        }

        public void setService_type(String service_type) {
            this.service_type = service_type;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUser_onum() {
            return user_onum;
        }

        public void setUser_onum(String user_onum) {
            this.user_onum = user_onum;
        }

        public String getUser_quci() {
            return user_quci;
        }

        public void setUser_quci(String user_quci) {
            this.user_quci = user_quci;
        }

        public String getUser_score() {
            return user_score;
        }

        public void setUser_score(String user_score) {
            this.user_score = user_score;
        }

        public String getUser_xing() {
            return user_xing;
        }

        public void setUser_xing(String user_xing) {
            this.user_xing = user_xing;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getWd_content() {
            return wd_content;
        }

        public void setWd_content(String wd_content) {
            this.wd_content = wd_content;
        }

        public String getWd_num() {
            return wd_num;
        }

        public void setWd_num(String wd_num) {
            this.wd_num = wd_num;
        }

        public String getWd_time() {
            return wd_time;
        }

        public void setWd_time(String wd_time) {
            this.wd_time = wd_time;
        }

        public String getXing_num() {
            return xing_num;
        }

        public void setXing_num(String xing_num) {
            this.xing_num = xing_num;
        }
    }
}
