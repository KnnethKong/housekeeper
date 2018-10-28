package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ihope006 on 2017/2/22.
 */

public class RegEntity {
    /**
     * data : {"address":[],"user_info":{"address":"","avatar":"","balance":"0.00","ctime":"1487731206","fans_num":"0","follow_num":"0","introduction":"","is_don":"0","is_hou":"0","is_ren":"0","lat":"","login_key":"###cfdd7e3294db340910cb43b9ffdbe31a","long":"","ltime":"0","mobile":"13966663333","name":"","nickname":"","only_label":"dr1855","pro_lat":"","pro_long":"","pro_onum":"0","pro_quci":"0","pro_score":"0","pro_xing":"0","registrationid":"","sex":"0","system":"0","uid":"56","user_onum":"0","user_quci":"0","user_score":"0","user_xing":"0","version":"2"}}
     * status : 200
     */

    private DataBean data;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * address : []
         * user_info : {"address":"","avatar":"","balance":"0.00","ctime":"1487731206","fans_num":"0","follow_num":"0","introduction":"","is_don":"0","is_hou":"0","is_ren":"0","lat":"","login_key":"###cfdd7e3294db340910cb43b9ffdbe31a","long":"","ltime":"0","mobile":"13966663333","name":"","nickname":"","only_label":"dr1855","pro_lat":"","pro_long":"","pro_onum":"0","pro_quci":"0","pro_score":"0","pro_xing":"0","registrationid":"","sex":"0","system":"0","uid":"56","user_onum":"0","user_quci":"0","user_score":"0","user_xing":"0","version":"2"}
         */

        private UserInfoBean user_info;
        private List<?> address;

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public List<?> getAddress() {
            return address;
        }

        public void setAddress(List<?> address) {
            this.address = address;
        }

        public static class UserInfoBean {
            /**
             * address :
             * avatar :
             * balance : 0.00
             * ctime : 1487731206
             * fans_num : 0
             * follow_num : 0
             * introduction :
             * is_don : 0
             * is_hou : 0
             * is_ren : 0
             * lat :
             * login_key : ###cfdd7e3294db340910cb43b9ffdbe31a
             * long :
             * ltime : 0
             * mobile : 13966663333
             * name :
             * nickname :
             * only_label : dr1855
             * pro_lat :
             * pro_long :
             * pro_onum : 0
             * pro_quci : 0
             * pro_score : 0
             * pro_xing : 0
             * registrationid :
             * sex : 0
             * system : 0
             * uid : 56
             * user_onum : 0
             * user_quci : 0
             * user_score : 0
             * user_xing : 0
             * version : 2
             */

            private String address;
            private String avatar;
            private String balance;
            private String ctime;
            private String fans_num;
            private String follow_num;
            private String introduction;
            private String is_don;
            private String is_hou;
            private String is_ren;
            private String lat;
            private String login_key;
            @SerializedName("long")
            private String longX;
            private String ltime;
            private String mobile;
            private String name;
            private String nickname;
            private String only_label;
            private String pro_lat;
            private String pro_long;
            private String pro_onum;
            private String pro_quci;
            private String pro_score;
            private String pro_xing;
            private String registrationid;
            private String sex;
            private String system;
            private String uid;
            private String user_onum;
            private String user_quci;
            private String user_score;
            private String user_xing;
            private String version;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLogin_key() {
                return login_key;
            }

            public void setLogin_key(String login_key) {
                this.login_key = login_key;
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

            public String getOnly_label() {
                return only_label;
            }

            public void setOnly_label(String only_label) {
                this.only_label = only_label;
            }

            public String getPro_lat() {
                return pro_lat;
            }

            public void setPro_lat(String pro_lat) {
                this.pro_lat = pro_lat;
            }

            public String getPro_long() {
                return pro_long;
            }

            public void setPro_long(String pro_long) {
                this.pro_long = pro_long;
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

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getSystem() {
                return system;
            }

            public void setSystem(String system) {
                this.system = system;
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

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }
        }
    }
}
