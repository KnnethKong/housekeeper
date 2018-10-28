package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ihope10 on 2016/10/31.
 */

public class OrderItemEntry {


    /**
     * status : 200
     * data : [{"id":"12","uid":"2","type":"1","h_id":"20","static":"6","is_ypin":"0","is_fpin":"0","ctime":"1478256899","service_type":"0","sex":"0","avatar":"","nickname":"小明","address_info":"上地九街","lat":"39.9","long":"116.4","km":"0","is_jie":0},{"id":"14","uid":"2","type":"2","h_id":"20","static":"2","is_ypin":"0","is_fpin":"0","ctime":"1478328099","service_type":"0","sex":"0","avatar":"","nickname":"小明","address_info":"上地九街","lat":"39.9","long":"116.4","km":"0","is_jie":1},{"id":"15","uid":"2","type":"4","h_id":"20","static":"2","is_ypin":"0","is_fpin":"0","ctime":"1478331961","service_type":"0","sex":"0","avatar":"","nickname":"小明","address_info":"上地九街","lat":"39.9","long":"116.4","km":"0","is_jie":1},{"id":"17","uid":"2","type":"6","h_id":"20","static":"2","is_ypin":"0","is_fpin":"0","ctime":"1478332239","service_type":"0","sex":"0","avatar":"","nickname":"小明","address_info":"上地九街","lat":"39.9","long":"116.4","km":"0","is_jie":1},{"id":"18","uid":"2","type":"7","h_id":"20","static":"6","is_ypin":"0","is_fpin":"0","ctime":"1478332374","service_type":"2","sex":"0","avatar":"","nickname":"小明","address_info":"上地九街","lat":"39.9","long":"116.4","km":"0","is_jie":1},{"id":"20","uid":"2","type":"9","h_id":"20","static":"7","is_ypin":"0","is_fpin":"0","ctime":"1478486154","service_type":"1","sex":"0","avatar":"","nickname":"小明","address_info":"上地九街","lat":"39.9","long":"116.4","km":"0","is_jie":1}]
     */

    private int status;
    /**
     * id : 12
     * uid : 2
     * type : 1
     * h_id : 20
     * static : 6
     * is_ypin : 0
     * is_fpin : 0
     * ctime : 1478256899
     * service_type : 0
     * sex : 0
     * avatar :
     * nickname : 小明
     * address_info : 上地九街
     * lat : 39.9
     * long : 116.4
     * km : 0
     * is_jie : 0
     */

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
        private String id;
        private String uid;
        private String at_uid;
        private String type;
        private String h_id;
        @SerializedName("static")
        private String staticX;
        private String is_ypin;
        private String is_fpin;
        private String ctime;
        private String service_type;
        private String sex;
        private String avatar;
        private String nickname;
        private String address_info;
        private String lat;
        @SerializedName("long")
        private String longX;
        private String km;
        private int is_jie;
        private String num;

        public String getAt_uid() {
            return at_uid;
        }

        public void setAt_uid(String at_uid) {
            this.at_uid = at_uid;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getH_id() {
            return h_id;
        }

        public void setH_id(String h_id) {
            this.h_id = h_id;
        }

        public String getStaticX() {
            return staticX;
        }

        public void setStaticX(String staticX) {
            this.staticX = staticX;
        }

        public String getIs_ypin() {
            return is_ypin;
        }

        public void setIs_ypin(String is_ypin) {
            this.is_ypin = is_ypin;
        }

        public String getIs_fpin() {
            return is_fpin;
        }

        public void setIs_fpin(String is_fpin) {
            this.is_fpin = is_fpin;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
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

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAddress_info() {
            return address_info;
        }

        public void setAddress_info(String address_info) {
            this.address_info = address_info;
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

        public String getKm() {
            return km;
        }

        public void setKm(String km) {
            this.km = km;
        }

        public int getIs_jie() {
            return is_jie;
        }

        public void setIs_jie(int is_jie) {
            this.is_jie = is_jie;
        }
    }
}
