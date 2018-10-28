package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ihope006 on 2016/11/23.
 */

public class VipBusinessOrderEntity {
    /**
     * status : 200
     * data : {"data":{"id":"11","uid":"3","country":"22","province":"23","city":"1","address_info":"读u读","housing_type":"10","area":"10","contacts":"u无","contact_number":"12345678911","alternate_contact":"12345678911","email":"q2467q7q@qq.com","lat":"116.404017","long":"39.915239","is_bill":"0","is_del":"1","ctime":0,"zip_code":"","h_id":"11","water":0,"water_jiao":0,"gas":0,"gas_jiao":0,"property":0,"property_jiao":0,"network":0,"network_jiao":0,"public":0,"public_jiao":0,"fixed":0,"fixed_jiao":0,"cable":0,"cable_jiao":0,"mobile":0,"mobile_jiao":0,"is_cun":0},"histry_time":[1487915930,1487915930]}
     */

    private int status;
    private DataBeanX data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * data : {"id":"11","uid":"3","country":"22","province":"23","city":"1","address_info":"读u读","housing_type":"10","area":"10","contacts":"u无","contact_number":"12345678911","alternate_contact":"12345678911","email":"q2467q7q@qq.com","lat":"116.404017","long":"39.915239","is_bill":"0","is_del":"1","ctime":0,"zip_code":"","h_id":"11","water":0,"water_jiao":0,"gas":0,"gas_jiao":0,"property":0,"property_jiao":0,"network":0,"network_jiao":0,"public":0,"public_jiao":0,"fixed":0,"fixed_jiao":0,"cable":0,"cable_jiao":0,"mobile":0,"mobile_jiao":0,"is_cun":0}
         * histry_time : [1487915930,1487915930]
         */

        private DataBean data;
        private List<Integer> histry_time;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public List<Integer> getHistry_time() {
            return histry_time;
        }

        public void setHistry_time(List<Integer> histry_time) {
            this.histry_time = histry_time;
        }

        public static class DataBean {
            /**
             * id : 11
             * uid : 3
             * country : 22
             * province : 23
             * city : 1
             * address_info : 读u读
             * housing_type : 10
             * area : 10
             * contacts : u无
             * contact_number : 12345678911
             * alternate_contact : 12345678911
             * email : q2467q7q@qq.com
             * lat : 116.404017
             * long : 39.915239
             * is_bill : 0
             * is_del : 1
             * ctime : 0
             * zip_code :
             * h_id : 11
             * water : 0
             * water_jiao : 0
             * gas : 0
             * gas_jiao : 0
             * property : 0
             * property_jiao : 0
             * network : 0
             * network_jiao : 0
             * public : 0
             * public_jiao : 0
             * fixed : 0
             * fixed_jiao : 0
             * cable : 0
             * cable_jiao : 0
             * mobile : 0
             * mobile_jiao : 0
             * is_cun : 0
             */

            private String id;
            private String uid;
            private String country;
            private String province;
            private String city;
            private String address_info;
            private String housing_type;
            private String area;
            private String contacts;
            private String contact_number;
            private String alternate_contact;
            private String email;
            private String lat;
            @SerializedName("long")
            private String longX;
            private String is_bill;
            private String is_del;
            private int ctime;
            private String zip_code;
            private String h_id;
            private int water;
            private int water_jiao;
            private int gas;
            private int gas_jiao;
            private int property;
            private int property_jiao;
            private int network;
            private int network_jiao;
            @SerializedName("public")
            private int publicX;
            private int public_jiao;
            private int fixed;
            private int fixed_jiao;
            private int cable;
            private int cable_jiao;
            private int mobile;
            private int mobile_jiao;
            private int is_cun;

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

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
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

            public String getContacts() {
                return contacts;
            }

            public void setContacts(String contacts) {
                this.contacts = contacts;
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

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
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

            public int getCtime() {
                return ctime;
            }

            public void setCtime(int ctime) {
                this.ctime = ctime;
            }

            public String getZip_code() {
                return zip_code;
            }

            public void setZip_code(String zip_code) {
                this.zip_code = zip_code;
            }

            public String getH_id() {
                return h_id;
            }

            public void setH_id(String h_id) {
                this.h_id = h_id;
            }

            public int getWater() {
                return water;
            }

            public void setWater(int water) {
                this.water = water;
            }

            public int getWater_jiao() {
                return water_jiao;
            }

            public void setWater_jiao(int water_jiao) {
                this.water_jiao = water_jiao;
            }

            public int getGas() {
                return gas;
            }

            public void setGas(int gas) {
                this.gas = gas;
            }

            public int getGas_jiao() {
                return gas_jiao;
            }

            public void setGas_jiao(int gas_jiao) {
                this.gas_jiao = gas_jiao;
            }

            public int getProperty() {
                return property;
            }

            public void setProperty(int property) {
                this.property = property;
            }

            public int getProperty_jiao() {
                return property_jiao;
            }

            public void setProperty_jiao(int property_jiao) {
                this.property_jiao = property_jiao;
            }

            public int getNetwork() {
                return network;
            }

            public void setNetwork(int network) {
                this.network = network;
            }

            public int getNetwork_jiao() {
                return network_jiao;
            }

            public void setNetwork_jiao(int network_jiao) {
                this.network_jiao = network_jiao;
            }

            public int getPublicX() {
                return publicX;
            }

            public void setPublicX(int publicX) {
                this.publicX = publicX;
            }

            public int getPublic_jiao() {
                return public_jiao;
            }

            public void setPublic_jiao(int public_jiao) {
                this.public_jiao = public_jiao;
            }

            public int getFixed() {
                return fixed;
            }

            public void setFixed(int fixed) {
                this.fixed = fixed;
            }

            public int getFixed_jiao() {
                return fixed_jiao;
            }

            public void setFixed_jiao(int fixed_jiao) {
                this.fixed_jiao = fixed_jiao;
            }

            public int getCable() {
                return cable;
            }

            public void setCable(int cable) {
                this.cable = cable;
            }

            public int getCable_jiao() {
                return cable_jiao;
            }

            public void setCable_jiao(int cable_jiao) {
                this.cable_jiao = cable_jiao;
            }

            public int getMobile() {
                return mobile;
            }

            public void setMobile(int mobile) {
                this.mobile = mobile;
            }

            public int getMobile_jiao() {
                return mobile_jiao;
            }

            public void setMobile_jiao(int mobile_jiao) {
                this.mobile_jiao = mobile_jiao;
            }

            public int getIs_cun() {
                return is_cun;
            }

            public void setIs_cun(int is_cun) {
                this.is_cun = is_cun;
            }
        }
    }
}
