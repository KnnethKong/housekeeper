package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ihope006 on 2016/12/20.
 */

public class LoginEntity {

    /**
     * data : {"address":{"address":"","city":"1","ctime":"1480988701","id":"56","is_mo":"1","lat":"1","long":"1","uid":"3"},"user_info":{"address":"","avatar":"http://hwgj.zai0312.com/Uploads/2016-12-02/58413d04437e3.png","balance":"101065.78","ctime":"0","fans_num":"4","follow_num":"5","introduction":"测试133","is_don":"0","is_hou":"0","is_ren":"1","lat":"","long":"","ltime":"1482222470","mobile":"15011548330","name":"杰克逊123","nickname":"张三丰123","only_label":"86835","pro_onum":"0","pro_quci":"0","pro_score":"2","pro_xing":"0","sex":"1","uid":"3","user_onum":"0","user_quci":"0","user_score":"0","user_xing":"0","wd_content":"我打上大所大","wd_num":"11","wd_time":"1482221184"}}
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
         * address : {"address":"","city":"1","ctime":"1480988701","id":"56","is_mo":"1","lat":"1","long":"1","uid":"3"}
         * user_info : {"address":"","avatar":"http://hwgj.zai0312.com/Uploads/2016-12-02/58413d04437e3.png","balance":"101065.78","ctime":"0","fans_num":"4","follow_num":"5","introduction":"测试133","is_don":"0","is_hou":"0","is_ren":"1","lat":"","long":"","ltime":"1482222470","mobile":"15011548330","name":"杰克逊123","nickname":"张三丰123","only_label":"86835","pro_onum":"0","pro_quci":"0","pro_score":"2","pro_xing":"0","sex":"1","uid":"3","user_onum":"0","user_quci":"0","user_score":"0","user_xing":"0","wd_content":"我打上大所大","wd_num":"11","wd_time":"1482221184"}
         */

        private AddressBean address;
        private UserInfoBean user_info;

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public static class AddressBean {
            /**
             * address :
             * city : 1
             * ctime : 1480988701
             * id : 56
             * is_mo : 1
             * lat : 1
             * long : 1
             * uid : 3
             */

            private String address;
            private String city;
            private String ctime;
            private String id;
            private String is_mo;
            private String lat;
            @SerializedName("long")
            private String longX;
            private String uid;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIs_mo() {
                return is_mo;
            }

            public void setIs_mo(String is_mo) {
                this.is_mo = is_mo;
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

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }
        }

        public static class UserInfoBean {
            /**
             * address :
             * avatar : http://hwgj.zai0312.com/Uploads/2016-12-02/58413d04437e3.png
             * balance : 101065.78
             * ctime : 0
             * fans_num : 4
             * follow_num : 5
             * introduction : 测试133
             * is_don : 0
             * is_hou : 0
             * is_ren : 1
             * lat :
             * long :
             * ltime : 1482222470
             * mobile : 15011548330
             * name : 杰克逊123
             * nickname : 张三丰123
             * only_label : 86835
             * pro_onum : 0
             * pro_quci : 0
             * pro_score : 2
             * pro_xing : 0
             * sex : 1
             * uid : 3
             * user_onum : 0
             * user_quci : 0
             * user_score : 0
             * user_xing : 0
             * wd_content : 我打上大所大
             * wd_num : 11
             * wd_time : 1482221184
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
            @SerializedName("long")
            private String longX;
            private String ltime;
            private String mobile;
            private String name;
            private String nickname;
            private String only_label;
            private String pro_onum;
            private String pro_quci;
            private String pro_score;
            private String pro_xing;
            private String sex;
            private String uid;
            private String user_onum;
            private String user_quci;
            private String user_score;
            private String user_xing;
            private String wd_content;
            private String wd_num;
            private String wd_time;

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

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
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
        }
    }
}
