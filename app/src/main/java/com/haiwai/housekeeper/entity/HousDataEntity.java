package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ihope006 on 2016/12/12.
 */

public class HousDataEntity {


    /**
     * data : [{"address_info":"Guuii","alternate_contact":"12312312311","area":"9","city":"1","contact_number":"123456","contacts":"Guus","ctime":"1481688066","email":"12456@qq.com","housing_type":"9","id":"15","is_bill":"0","is_del":"0","is_support":1,"lat":"116.404017","long":"39.915239","uid":"28"},{"address_info":"Yu","alternate_contact":"12345678911","area":"8","city":"1","contact_number":"12345688911","contacts":"Ty","ctime":"1481692166","email":"12344@qq.com","housing_type":"8","id":"17","is_bill":"0","is_del":"0","is_support":1,"lat":"116.404017","long":"39.915239","uid":"28"},{"address_info":"Hu","alternate_contact":"12345678912","area":"8","city":"1","contact_number":"12345678912","contacts":"Hu","ctime":"1481692616","email":"q2456@qq.com","housing_type":"8","id":"18","is_bill":"0","is_del":"0","is_support":1,"lat":"116.404017","long":"39.915239","uid":"28"},{"address_info":"UII看","alternate_contact":"12345678912","area":"8","city":"1","contact_number":"150123456789","contacts":"诶诶","ctime":"1482393501","email":"2727828@qq.com","housing_type":"8","id":"40","is_bill":"0","is_del":"0","is_support":1,"lat":"116.404017","long":"39.915239","uid":"28"}]
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
         * alternate_contact : 12312312311
         * area : 9
         * city : 1
         * contact_number : 123456
         * contacts : Guus
         * ctime : 1481688066
         * email : 12456@qq.com
         * housing_type : 9
         * id : 15
         * is_bill : 0
         * is_del : 0
         * is_support : 1
         * lat : 116.404017
         * long : 39.915239
         * uid : 28
         */

        private String address_info;
        private String alternate_contact;
        private String area;
        private String city;
        private String contact_number;
        private String contacts;
        private String ctime;
        private String email;
        private String housing_type;
        private String id;
        private String is_bill;
        private String is_del;
        private int is_support;
        private String lat;
        @SerializedName("long")
        private String longX;
        private String uid;

        public String getAddress_info() {
            return address_info;
        }

        public void setAddress_info(String address_info) {
            this.address_info = address_info;
        }

        public String getAlternate_contact() {
            return alternate_contact;
        }

        public void setAlternate_contact(String alternate_contact) {
            this.alternate_contact = alternate_contact;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getContact_number() {
            return contact_number;
        }

        public void setContact_number(String contact_number) {
            this.contact_number = contact_number;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHousing_type() {
            return housing_type;
        }

        public void setHousing_type(String housing_type) {
            this.housing_type = housing_type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIs_bill() {
            return is_bill;
        }

        public void setIs_bill(String is_bill) {
            this.is_bill = is_bill;
        }

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public int getIs_support() {
            return is_support;
        }

        public void setIs_support(int is_support) {
            this.is_support = is_support;
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
}
