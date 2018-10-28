package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ihope10 on 2016/11/14.
 */

public class AddressEntity implements Serializable {
    /**
     * data : [{"address":"&amp;&amp;","city":"1","country":"22","ctime":"1486521511","id":"78","is_mo":"1","lat":"49.314625","long":"-122.8556688","province":"23","uid":"3"}]
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
         * address : &amp;&amp;
         * city : 1
         * country : 22
         * ctime : 1486521511
         * id : 78
         * is_mo : 1
         * lat : 49.314625
         * long : -122.8556688
         * province : 23
         * uid : 3
         */

        private String address;
        private String zip_code;
        private String city;
        private String country;
        private String ctime;
        public String street;
        public String house_number;
        private String id;
        private String is_mo;
        private String lat;
        @SerializedName("long")
        private String longX;
        private String province;
        private String uid;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getZip_code() {
            return zip_code;
        }

        public void setZip_code(String zip_code) {
            this.zip_code = zip_code;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
