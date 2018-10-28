package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ihope006 on 2016/11/24.
 */

public class PersonEntity {

    /**
     * data : [{"address":"","avatar":"http://hwgj.zai0312.com/Uploads/2016-11-10/5823ea59a93bc.png","balance":"100000","ctime":"1479899721","fans_num":"1","follow_num":"1","fuid":"3","id":"2","introduction":"","is_hou":"0","is_ren":"0","lat":"","long":"","ltime":"1479893826","mobile":"13846818901","name":"","nickname":"小明","only_label":"50155","pro_quci":"0","pro_score":"0","sex":"0","type":"0","uid":"2","user_quci":"0","user_score":"0"}]
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

    public static class DataBean {
        /**
         * address :
         * avatar : http://hwgj.zai0312.com/Uploads/2016-11-10/5823ea59a93bc.png
         * balance : 100000
         * ctime : 1479899721
         * fans_num : 1
         * follow_num : 1
         * fuid : 3
         * id : 2
         * introduction :
         * is_hou : 0
         * is_ren : 0
         * lat :
         * long :
         * ltime : 1479893826
         * mobile : 13846818901
         * name :
         * nickname : 小明
         * only_label : 50155
         * pro_quci : 0
         * pro_score : 0
         * sex : 0
         * type : 0
         * uid : 2
         * user_quci : 0
         * user_score : 0
         */

        private String address;
        private String avatar;
        private String balance;
        private String ctime;
        private String fans_num;
        private String follow_num;
        private String fuid;
        private String id;
        private String introduction;
        private String is_hou;
        private String is_ren;
        private String lat;
        @SerializedName("long")
        private String longX;
        private String ltime;
        private String mobile;
        private String name;
        private String nickname;
        private String only_label;
        private String pro_quci;
        private String pro_score;
        private String sex;
        private String type;
        private String uid;
        private String user_quci;
        private String user_score;

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

        public String getFuid() {
            return fuid;
        }

        public void setFuid(String fuid) {
            this.fuid = fuid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
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
    }
}
