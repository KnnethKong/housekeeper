package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ihope006 on 2016/12/28.
 */

public class NeedEntity {

    /**
     * data : [{"address_info":"Guuii","avatar":"","ctime":"1484893208","h_id":"15","id":"402","is_fpin":"0","is_ypin":"0","j_num":"2","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017012014848932083264","service_type":"0","static":"1","type":"1","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484892775","h_id":"15","id":"401","is_fpin":"0","is_ypin":"0","j_num":"1","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017012014848927754740","service_type":"0","static":"1","type":"1","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484892748","h_id":"15","id":"400","is_fpin":"0","is_ypin":"0","j_num":"0","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017012014848927483180","service_type":"0","static":"0","type":"1","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484892529","h_id":"15","id":"399","is_fpin":"0","is_ypin":"0","j_num":"0","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017012014848925294067","service_type":"0","static":"0","type":"1","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484892508","h_id":"15","id":"398","is_fpin":"0","is_ypin":"0","j_num":"1","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017012014848925085397","service_type":"0","static":"1","type":"1","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484892465","h_id":"15","id":"397","is_fpin":"0","is_ypin":"0","j_num":"0","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017012014848924658490","service_type":"0","static":"0","type":"1","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484892325","h_id":"15","id":"396","is_fpin":"0","is_ypin":"0","j_num":"0","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017012014848923258686","service_type":"0","static":"0","type":"1","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484892110","h_id":"15","id":"395","is_fpin":"0","is_ypin":"0","j_num":"0","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017012014848921102441","service_type":"0","static":"0","type":"1","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484892022","h_id":"15","id":"394","is_fpin":"0","is_ypin":"0","j_num":"0","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017012014848920223201","service_type":"0","static":"0","type":"1","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484809495","h_id":"15","id":"377","is_fpin":"0","is_ypin":"0","j_num":"0","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017011914848094952905","service_type":"0","static":"0","type":"1","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484738674","h_id":"15","id":"365","is_fpin":"0","is_ypin":"0","j_num":"1","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017011814847386745336","service_type":"0","static":"1","type":"1","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484737882","h_id":"15","id":"364","is_fpin":"0","is_ypin":"0","j_num":"1","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017011814847378822510","service_type":"0","static":"1","type":"1","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484737783","h_id":"15","id":"363","is_fpin":"0","is_ypin":"0","j_num":"1","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017011814847377837365","service_type":"0","static":"1","type":"1","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484735025","h_id":"15","id":"362","is_fpin":"0","is_ypin":"0","j_num":"1","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017011814847350256661","service_type":"0","static":"1","type":"1","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484733589","h_id":"15","id":"361","is_fpin":"0","is_ypin":"0","j_num":"1","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017011814847335899152","service_type":"0","static":"1","type":"1","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484730104","h_id":"15","id":"341","is_fpin":"0","is_ypin":"0","j_num":"1","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017011814847301049744","service_type":"0","static":"1","type":"1","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484729813","h_id":"15","id":"340","is_fpin":"0","is_ypin":"0","j_num":"1","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017011814847298138016","service_type":"0","static":"1","type":"1","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484729175","h_id":"15","id":"339","is_fpin":"0","is_ypin":"0","j_num":"0","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017011814847291757670","service_type":"0","static":"0","type":"1","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484724849","h_id":"15","id":"318","is_fpin":"0","is_ypin":"0","j_num":"0","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017011814847248496192","service_type":"0","static":"0","type":"18","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484724736","h_id":"15","id":"317","is_fpin":"0","is_ypin":"0","j_num":"0","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017011814847247368573","service_type":"0","static":"0","type":"18","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484718372","h_id":"15","id":"314","is_fpin":"0","is_ypin":"0","j_num":"0","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017011814847183724037","service_type":"0","static":"0","type":"7","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484715050","h_id":"15","id":"313","is_fpin":"0","is_ypin":"0","j_num":"0","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017011814847150505638","service_type":"0","static":"0","type":"2","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484642821","h_id":"15","id":"299","is_fpin":"0","is_ypin":"0","j_num":"0","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017011714846428227081","service_type":"0","static":"0","type":"18","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484557372","h_id":"15","id":"290","is_fpin":"0","is_ypin":"0","j_num":"0","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017011614845573722561","service_type":"0","static":"0","type":"10","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484548336","h_id":"15","id":"287","is_fpin":"0","is_ypin":"0","j_num":"0","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017011614845483366941","service_type":"0","static":"0","type":"10","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484547959","h_id":"15","id":"286","is_fpin":"0","is_ypin":"0","j_num":"0","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017011614845479593106","service_type":"0","static":"0","type":"10","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1484546846","h_id":"15","id":"285","is_fpin":"0","is_ypin":"0","j_num":"0","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2017011614845468466153","service_type":"0","static":"0","type":"10","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1482716075","h_id":"15","id":"205","is_fpin":"0","is_ypin":"0","j_num":"2","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2016122614827160755758","service_type":"0","static":"1","type":"2","uid":"28","zon_price":0},{"address_info":"Guuii","avatar":"","ctime":"1482114102","h_id":"15","id":"144","is_fpin":"0","is_ypin":"0","j_num":"0","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2016121914821141029719","service_type":"0","static":"0","type":"19","uid":"28","zon_price":0},{"address_info":"Hu","avatar":"","ctime":"1481692653","h_id":"18","id":"132","is_fpin":"0","is_ypin":"0","j_num":"0","j_uid":"0","lat":"116.404017","long":"39.915239","nickname":"","order_id":"2016121414816926538397","service_type":"0","static":"0","type":"1","uid":"28","zon_price":0}]
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
         * address_info : Guuii
         * avatar :
         * ctime : 1484893208
         * h_id : 15
         * id : 402
         * is_fpin : 0
         * is_ypin : 0
         * j_num : 2
         * j_uid : 0
         * lat : 116.404017
         * long : 39.915239
         * nickname :
         * order_id : 2017012014848932083264
         * service_type : 0
         * static : 1
         * type : 1
         * uid : 28
         * zon_price : 0
         */

        private String address_info;
        private String avatar;
        private String ctime;
        private String h_id;
        private String id;
        private String is_fpin;
        private String is_ypin;
        private String j_num;
        private String j_uid;
        private String lat;
        @SerializedName("long")
        private String longX;
        private String nickname;
        private String order_id;
        private String service_type;
        @SerializedName("static")
        private String staticX;
        private String type;
        private String uid;
        private float zon_price;

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

        public String getIs_fpin() {
            return is_fpin;
        }

        public void setIs_fpin(String is_fpin) {
            this.is_fpin = is_fpin;
        }

        public String getIs_ypin() {
            return is_ypin;
        }

        public void setIs_ypin(String is_ypin) {
            this.is_ypin = is_ypin;
        }

        public String getJ_num() {
            return j_num;
        }

        public void setJ_num(String j_num) {
            this.j_num = j_num;
        }

        public String getJ_uid() {
            return j_uid;
        }

        public void setJ_uid(String j_uid) {
            this.j_uid = j_uid;
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

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getService_type() {
            return service_type;
        }

        public void setService_type(String service_type) {
            this.service_type = service_type;
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

        public float getZon_price() {
            return zon_price;
        }

        public void setZon_price(float zon_price) {
            this.zon_price = zon_price;
        }
    }
}
