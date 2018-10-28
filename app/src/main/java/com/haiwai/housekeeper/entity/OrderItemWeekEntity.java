package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ihope006 on 2017/1/5.
 */

public class OrderItemWeekEntity {

    /**
     * data : [{"address_info":"njjxkddhdjd","avatar":"http://hwgj.zai0312.com/Uploads/2016-12-21/585a44b446fa9.png","ctime":"1482373315","h_id":"10","id":"145","is_jie":0,"km":"0","lat":"39.9","long":"116.4","nickname":"rusherwh","num":"1","sex":"0","static":"1","type":"30","uid":"10"},{"address_info":"上地九街","avatar":"http://hwgj.zai0312.com/Uploads/2016-11-29/583d2ffe5bc57.png","ctime":"1482373315","h_id":"5","id":"140","is_jie":0,"km":"0","lat":"40","long":"140","nickname":"小强","num":"2","sex":"0","static":"1","type":"29","uid":"2"},{"address_info":"上地九街","avatar":"http://hwgj.zai0312.com/Uploads/2016-11-29/583d2ffe5bc57.png","ctime":"1482373315","h_id":"5","id":"141","is_jie":0,"km":"0","lat":"40","long":"140","nickname":"小强","num":"1","sex":"0","static":"1","type":"30","uid":"2"},{"address_info":"就像监督局地","avatar":"http://hwgj.zai0312.com/Uploads/2016-12-02/58413d04437e3.png","ctime":"1482373315","h_id":"12","id":"142","is_jie":0,"km":"0","lat":"116.404017","long":"39.915239","nickname":"张三丰123","num":"1","sex":"1","static":"1","type":"30","uid":"3"},{"address_info":"北京大学","avatar":"http://hwgj.zai0312.com/Uploads/2016-12-27/58621e73a149d.jpg","ctime":"1482373315","h_id":"16","id":"143","is_jie":0,"km":"0","lat":"40.047986","long":"116.294219","nickname":"我是谁？","num":"1","sex":"0","static":"1","type":"30","uid":"4"},{"address_info":"北京故宫","avatar":"http://hwgj.zai0312.com/Uploads/2016-12-27/58621e73a149d.jpg","ctime":"1482373315","h_id":"3","id":"144","is_jie":0,"km":"0","lat":"39.904989","long":"116.405285","nickname":"我是谁？","num":"2","sex":"0","static":"1","type":"29","uid":"4"},{"address_info":"呜呜u为","avatar":"http://hwgj.zai0312.com/Uploads/2016-12-02/58413d04437e3.png","ctime":"1482373315","h_id":"14","id":"153","is_jie":0,"km":"0","lat":"116.404017","long":"39.915239","nickname":"张三丰123","num":"1","sex":"1","static":"1","type":"30","uid":"3"},{"address_info":"北京南站","avatar":"http://hwgj.zai0312.com/Uploads/2016-12-27/58621e73a149d.jpg","ctime":"1482373315","h_id":"4","id":"146","is_jie":0,"km":"0","lat":"39.904989","long":"116.405285","nickname":"我是谁？","num":"2","sex":"0","static":"1","type":"29","uid":"4"},{"address_info":"北京南站","avatar":"http://hwgj.zai0312.com/Uploads/2016-12-27/58621e73a149d.jpg","ctime":"1482373315","h_id":"4","id":"147","is_jie":0,"km":"0","lat":"39.904989","long":"116.405285","nickname":"我是谁？","num":"1","sex":"0","static":"1","type":"30","uid":"4"},{"address_info":"对贵","avatar":"http://hwgj.zai0312.com/Uploads/2016-12-02/58413d04437e3.png","ctime":"1482373315","h_id":"13","id":"148","is_jie":0,"km":"0","lat":"116.404017","long":"39.915239","nickname":"张三丰123","num":"2","sex":"1","static":"1","type":"29","uid":"3"}]
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
         * address_info : njjxkddhdjd
         * avatar : http://hwgj.zai0312.com/Uploads/2016-12-21/585a44b446fa9.png
         * ctime : 1482373315
         * h_id : 10
         * id : 145
         * is_jie : 0
         * km : 0
         * lat : 39.9
         * long : 116.4
         * nickname : rusherwh
         * num : 1
         * sex : 0
         * static : 1
         * type : 30
         * uid : 10
         */

        private String address_info;
        private String avatar;
        private String ctime;
        private String h_id;
        private String id;
        private int is_jie;
        private String km;
        private String lat;
        @SerializedName("long")
        private String longX;
        private String nickname;
        private String num;
        private String sex;
        @SerializedName("static")
        private String staticX;
        private String type;
        private String uid;

        public String getAddress_info() {
            return address_info;
        }

        public void setAddress_info(String address_info) {
            this.address_info = address_info;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getH_id() {
            return h_id;
        }

        public void setH_id(String h_id) {
            this.h_id = h_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIs_jie() {
            return is_jie;
        }

        public void setIs_jie(int is_jie) {
            this.is_jie = is_jie;
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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getStaticX() {
            return staticX;
        }

        public void setStaticX(String staticX) {
            this.staticX = staticX;
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
    }
}
