package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ihope007 on 2016/9/6.
 */
public class NeedResponseEntity {

    /**
     * status : 200
     * data : [{"id":"32","order_id":"2016110814786030163104","uid":"2","type":"15","h_id":"20","static":"1","is_ypin":"0","is_fpin":"0","ctime":"1478603016","service_type":"0","j_num":"1","avatar":"","nickname":"","address_info":"上地九街","lat":"39.9","long":"116.4"},{"id":"30","order_id":"2016110814786001207574","uid":"2","type":"17","h_id":"20","static":"0","is_ypin":"0","is_fpin":"0","ctime":"1478600120","service_type":"0","j_num":"0","avatar":"","nickname":"","address_info":"上地九街","lat":"39.9","long":"116.4"},{"id":"29","order_id":"2016110814785968903104","uid":"2","type":"16","h_id":"20","static":"0","is_ypin":"0","is_fpin":"0","ctime":"1478596890","service_type":"0","j_num":"0","avatar":"","nickname":"","address_info":"上地九街","lat":"39.9","long":"116.4"},{"id":"27","order_id":"2016110814785881419760","uid":"2","type":"14","h_id":"20","static":"0","is_ypin":"0","is_fpin":"0","ctime":"1478588141","service_type":"0","j_num":"0","avatar":"","nickname":"","address_info":"上地九街","lat":"39.9","long":"116.4"},{"id":"26","order_id":"2016110814785736372547","uid":"2","type":"13","h_id":"20","static":"0","is_ypin":"0","is_fpin":"0","ctime":"1478573637","service_type":"0","j_num":"0","avatar":"","nickname":"","address_info":"上地九街","lat":"39.9","long":"116.4"},{"id":"25","order_id":"2016110714785111574487","uid":"2","type":"12","h_id":"20","static":"0","is_ypin":"0","is_fpin":"0","ctime":"1478511157","service_type":"0","j_num":"0","avatar":"","nickname":"","address_info":"上地九街","lat":"39.9","long":"116.4"},{"id":"24","order_id":"2016110714785075801051","uid":"2","type":"11","h_id":"20","static":"0","is_ypin":"0","is_fpin":"0","ctime":"1478507580","service_type":"0","j_num":"0","avatar":"","nickname":"","address_info":"上地九街","lat":"39.9","long":"116.4"}]
     */

    private int status;
    /**
     * id : 32
     * order_id : 2016110814786030163104
     * uid : 2
     * type : 15
     * h_id : 20
     * static : 1
     * is_ypin : 0
     * is_fpin : 0
     * ctime : 1478603016
     * service_type : 0
     * j_num : 1
     * avatar :
     * nickname :
     * address_info : 上地九街
     * lat : 39.9
     * long : 116.4
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

    public static class DataBean {
        private String id;
        private String order_id;
        private String uid;
        private String type;
        private String h_id;
        @SerializedName("static")
        private String staticX;
        private String is_ypin;
        private String is_fpin;
        private String ctime;
        private String service_type;
        private String j_uid;
        private String j_num;
        private String avatar;
        private String nickname;
        private String address_info;
        private String lat;
        @SerializedName("long")
        private String longX;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
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

        public String getJ_uid() {
            return j_uid;
        }

        public void setJ_uid(String j_uid) {
            this.j_uid = j_uid;
        }

        public String getJ_num() {
            return j_num;
        }

        public void setJ_num(String j_num) {
            this.j_num = j_num;
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
    }
}
