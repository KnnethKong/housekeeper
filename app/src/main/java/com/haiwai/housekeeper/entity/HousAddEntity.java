package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ihope006 on 2017/1/5.
 */

public class HousAddEntity implements Serializable{

    /**
     * status : 200
     * data : {"uid":99,"country":22,"province":23,"city":15,"street":"去了","house_number":"456","address_info":"去了456","housing_type":"4","area":"","contact_area":"86","contact_number":"13261089009","alternate_contact":"","alternate_contact_area":"","alternate_contact_number":"","email":"","zip_code":"","lat":"49.256","long":"-122.7652","ctime":1498705272,"id":"570"}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * uid : 99
         * country : 22
         * province : 23
         * city : 15
         * street : 去了
         * house_number : 456
         * address_info : 去了456
         * housing_type : 4
         * area :
         * contact_area : 86
         * contact_number : 13261089009
         * alternate_contact :
         * alternate_contact_area :
         * alternate_contact_number :
         * email :
         * zip_code :
         * lat : 49.256
         * long : -122.7652
         * ctime : 1498705272
         * id : 570
         */

        private int uid;
        private int country;
        private int province;
        private int city;
        private String street;
        private String house_number;
        private String address_info;
        private String housing_type;
        private String area;
        private String contact_area;
        private String contact_number;
        private String alternate_contact;
        private String alternate_contact_area;
        private String alternate_contact_number;
        private String email;
        private String zip_code;
        private String lat;
        @SerializedName("long")
        private String longX;
        private int ctime;
        private String id;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getCountry() {
            return country;
        }

        public void setCountry(int country) {
            this.country = country;
        }

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getHouse_number() {
            return house_number;
        }

        public void setHouse_number(String house_number) {
            this.house_number = house_number;
        }

        public String getAddress_info() {
            return address_info;
        }

        public void setAddress_info(String address_info) {
            this.address_info = address_info;
        }

        public String getHousing_type() {
            return housing_type;
        }

        public void setHousing_type(String housing_type) {
            this.housing_type = housing_type;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getContact_area() {
            return contact_area;
        }

        public void setContact_area(String contact_area) {
            this.contact_area = contact_area;
        }

        public String getContact_number() {
            return contact_number;
        }

        public void setContact_number(String contact_number) {
            this.contact_number = contact_number;
        }

        public String getAlternate_contact() {
            return alternate_contact;
        }

        public void setAlternate_contact(String alternate_contact) {
            this.alternate_contact = alternate_contact;
        }

        public String getAlternate_contact_area() {
            return alternate_contact_area;
        }

        public void setAlternate_contact_area(String alternate_contact_area) {
            this.alternate_contact_area = alternate_contact_area;
        }

        public String getAlternate_contact_number() {
            return alternate_contact_number;
        }

        public void setAlternate_contact_number(String alternate_contact_number) {
            this.alternate_contact_number = alternate_contact_number;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getZip_code() {
            return zip_code;
        }

        public void setZip_code(String zip_code) {
            this.zip_code = zip_code;
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

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
