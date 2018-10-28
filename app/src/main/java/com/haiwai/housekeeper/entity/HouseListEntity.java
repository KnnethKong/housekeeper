package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;
import com.haiwai.housekeeper.activity.user.SelfSupportManageScheme;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ihope007 on 2016/10/12.
 */
public class HouseListEntity implements Serializable {

    /**
     * data : [{"address_info":"加拿大BC安莫尔","alternate_contact":"12345678911","area":"11","city":"1","city_name":"安莫尔","city_yname":"Anmore","contact_number":"15136499449","contacts":"极少数","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1486985520","email":"q282288@qq.com","housing_type":"3","id":"12","is_bill":"0","is_del":"0","is_support":1,"lat":"49.314625","long":"-122.8556688","province":"23","province_name":"BC","province_yname":"bc","uid":"3"},{"address_info":"对贵","alternate_contact":"32165498711","area":"12","city":"1","city_name":"安莫尔","city_yname":"Anmore","contact_number":"12345678911","contacts":"放UI","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1481532020","email":"12446@qq.com","housing_type":"12","id":"13","is_bill":"0","is_del":"0","is_support":1,"lat":"116.404017","long":"39.915239","province":"23","province_name":"BC","province_yname":"bc","uid":"3"},{"address_info":"呜呜u为","alternate_contact":"1389499794","area":"10","city":"1","city_name":"安莫尔","city_yname":"Anmore","contact_number":"32145673649","contacts":"江苏网I","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1481532577","email":"q882882@qq.com","housing_type":"10","id":"14","is_bill":"0","is_del":"0","is_support":1,"lat":"116.404017","long":"39.915239","province":"23","province_name":"BC","province_yname":"bc","uid":"3"},{"address_info":"头都","alternate_contact":"12345678912","area":"8","city":"1","city_name":"安莫尔","city_yname":"Anmore","contact_number":"12345678912","contacts":"阿里","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1481694103","email":"134678@qq.com","housing_type":"8","id":"19","is_bill":"0","is_del":"0","is_support":1,"lat":"116.404017","long":"39.915239","province":"23","province_name":"BC","province_yname":"bc","uid":"3"},{"address_info":"红米","alternate_contact":"46764497997","area":"8","city":"1","city_name":"安莫尔","city_yname":"Anmore","contact_number":"123464679479","contacts":"接地","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1483511414","email":"xhdjisisjs","housing_type":"8","id":"52","is_bill":"0","is_del":"0","is_support":1,"lat":"116.404017","long":"39.915239","province":"23","province_name":"BC","province_yname":"bc","uid":"3"},{"address_info":"测试恩没","alternate_contact":"87673376-949467","area":"8","city":"1","city_name":"安莫尔","city_yname":"Anmore","contact_number":"6733767979776","contacts":"就肯定","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1483582706","email":"zbbzjzuisi","housing_type":"8","id":"53","is_bill":"0","is_del":"0","is_support":0,"lat":"116.404017","long":"39.915239","province":"23","province_name":"BC","province_yname":"bc","uid":"3"},{"address_info":"匿名","alternate_contact":"67676434646","area":"8","city":"1","city_name":"安莫尔","city_yname":"Anmore","contact_number":"1646494799779","contacts":"愤怒","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1483584050","email":"xnxnksiwiw","housing_type":"8","id":"54","is_bill":"0","is_del":"0","is_support":1,"lat":"116.404017","long":"39.915239","province":"23","province_name":"BC","province_yname":"bc","uid":"3"},{"address_info":"痛的","alternate_contact":"8633478","area":"8","city":"1","city_name":"安莫尔","city_yname":"Anmore","contact_number":"2556658","contacts":"咯的","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1484651654","email":"huutf","housing_type":"8","id":"69","is_bill":"0","is_del":"0","is_support":0,"lat":"116.404017","long":"39.915239","province":"23","province_name":"BC","province_yname":"bc","uid":"3"},{"address_info":"加拿大BC高贵林港","alternate_contact":"12332145611","area":"8","city":"15","city_name":"高贵林港","city_yname":"Port Coquitlam","contact_number":"12345678911","contacts":"测试123","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1487053144","email":"1233@123.com","housing_type":"3","id":"98","is_bill":"0","is_del":"0","is_support":0,"lat":"49.2628382","long":"-122.7810708","province":"23","province_name":"BC","province_yname":"bc","uid":"3"},{"address_info":"加拿大BC匹特草原","alternate_contact":"4336","area":"9","city":"14","city_name":"匹特草原","city_yname":"Pitt Meadows","contact_number":"1234","contacts":"测试12","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1487054220","email":"xg","housing_type":"4","id":"100","is_bill":"0","is_del":"0","is_support":0,"lat":"49.2190648","long":"-122.6895165","province":"23","province_name":"BC","province_yname":"bc","uid":"3"},{"address_info":"加拿大BC列治文","alternate_contact":"12345678998","area":"10","city":"17","city_name":"列治文","city_yname":"Richmond","contact_number":"12345632112","contacts":"测试123","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1487054724","email":"2579","housing_type":"4","id":"101","is_bill":"0","is_del":"0","is_support":0,"lat":"49.1665898","long":"-123.133569","province":"23","province_name":"BC","province_yname":"bc","uid":"3"},{"address_info":"加拿大BC西温","alternate_contact":"67646131361","area":"8","city":"20","city_name":"西温","city_yname":"West Vancouver","contact_number":"12333466467979","contacts":"测试","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1487054971","email":"jx","housing_type":"5","id":"102","is_bill":"0","is_del":"0","is_support":1,"lat":"49.33489729999999","long":"-123.1667847","province":"23","province_name":"BC","province_yname":"bc","uid":"3"},{"address_info":"加拿大BC西温","alternate_contact":"67646131361","area":"8","city":"20","city_name":"西温","city_yname":"West Vancouver","contact_number":"12333466467979","contacts":"测试","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1487055248","email":"jx","housing_type":"5","id":"103","is_bill":"0","is_del":"0","is_support":0,"lat":"49.33489729999999","long":"-123.1667847","province":"23","province_name":"BC","province_yname":"bc","uid":"3"},{"address_info":"加拿大BC西温","alternate_contact":"67646131361","area":"8","city":"20","city_name":"西温","city_yname":"West Vancouver","contact_number":"12333466467979","contacts":"测试","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1487055251","email":"jx","housing_type":"5","id":"104","is_bill":"0","is_del":"0","is_support":0,"lat":"49.33489729999999","long":"-123.1667847","province":"23","province_name":"BC","province_yname":"bc","uid":"3"},{"address_info":"加拿大BC白石","alternate_contact":"57646461","area":"9","city":"21","city_name":"白石","city_yname":"White Rock","contact_number":"576464464","contacts":"测试321123456797","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1487055293","email":"","housing_type":"4","id":"105","is_bill":"0","is_del":"0","is_support":0,"lat":"49.0253085","long":"-122.802962","province":"23","province_name":"BC","province_yname":"bc","uid":"3"},{"address_info":"加拿大BC高贵林港","alternate_contact":"123","area":"8","city":"15","city_name":"高贵林港","city_yname":"Port Coquitlam","contact_number":"123","contacts":"测试123","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1487055572","email":"123","housing_type":"3","id":"106","is_bill":"0","is_del":"0","is_support":0,"lat":"49.2628382","long":"-122.7810708","province":"23","province_name":"BC","province_yname":"bc","uid":"3"},{"address_info":"加拿大BC温哥华","alternate_contact":"53644","area":"9","city":"19","city_name":"温哥华","city_yname":"Vancouver","contact_number":"12355685","contacts":"测试","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1487056007","email":"vhu","housing_type":"3","id":"107","is_bill":"0","is_del":"0","is_support":0,"lat":"49.2827291","long":"-123.1207375","province":"23","province_name":"BC","province_yname":"bc","uid":"3"},{"address_info":"加拿大BC三角洲","alternate_contact":"76764646","area":"9","city":"6","city_name":"三角洲","city_yname":"Delta","contact_number":"1234646677","contacts":"测试321","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1487056705","email":"hxusi","housing_type":"3","id":"108","is_bill":"0","is_del":"0","is_support":0,"lat":"49.09521549999999","long":"-123.0264759","province":"23","province_name":"BC","province_yname":"bc","uid":"3"},{"address_info":"加拿大BC匹特草原","alternate_contact":"76766464","area":"9","city":"14","city_name":"匹特草原","city_yname":"Pitt Meadows","contact_number":"646466464","contacts":"测试123","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1487057434","email":"","housing_type":"6","id":"109","is_bill":"0","is_del":"0","is_support":0,"lat":"49.2190648","long":"-122.6895165","province":"23","province_name":"BC","province_yname":"bc","uid":"3"},{"address_info":"加拿大BC素里","alternate_contact":"4233","area":"8","city":"18","city_name":"素里","city_yname":"Surrey","contact_number":"123456","contacts":"测试","country":"22","country_name":"加拿大","country_yname":"Canada","ctime":"1487057997","email":"bji","housing_type":"3","id":"110","is_bill":"0","is_del":"0","is_support":1,"lat":"49.1044302","long":"-122.801094","province":"23","province_name":"BC","province_yname":"bc","uid":"3"}]
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

    public static class DataBean implements Serializable{
        /**
         * address_info : 加拿大BC安莫尔
         * zip_code
         * alternate_contact : 12345678911
         * area : 11
         * city : 1
         * city_name : 安莫尔
         * city_yname : Anmore
         * contact_number : 15136499449
         * contacts : 极少数
         * country : 22
         * country_name : 加拿大
         * country_yname : Canada
         * ctime : 1486985520
         * email : q282288@qq.com
         * housing_type : 3
         * id : 12
         * is_bill : 0
         * is_del : 0
         * is_support : 1
         * lat : 49.314625
         * long : -122.8556688
         * province : 23
         * province_name : BC
         * province_yname : bc
         * uid : 3
         */

        private String address_info;
        private String zip_code;
        private String alternate_contact;
        private String area;
        private String city;

        private String city_name;
        private String city_yname;
        private String contact_number;
        private String contacts;
        private String country;
        private String country_name;
        private String country_yname;
        private String ctime;
        private String email;
        private String housing_type;
        private String id;
        public String house_number;
        public String street;
        public String alternate_contact_area;
        public String contact_area;
        public String alternate_contact_number;
        private String is_bill;
        private String is_del;
        private int is_support;
        private String lat;
        @SerializedName("long")
        private String longX;
        private String province;
        private String province_name;
        private String province_yname;
        private String uid;

        public String getAddress_info() {
            return address_info;
        }

        public void setAddress_info(String address_info) {
            this.address_info = address_info;
        }

        public String getZip_code() {
            return zip_code;
        }

        public void setZip_code(String zip_code) {
            this.zip_code = zip_code;
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

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getCity_yname() {
            return city_yname;
        }

        public void setCity_yname(String city_yname) {
            this.city_yname = city_yname;
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

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCountry_name() {
            return country_name;
        }

        public void setCountry_name(String country_name) {
            this.country_name = country_name;
        }

        public String getCountry_yname() {
            return country_yname;
        }

        public void setCountry_yname(String country_yname) {
            this.country_yname = country_yname;
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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getProvince_yname() {
            return province_yname;
        }

        public void setProvince_yname(String province_yname) {
            this.province_yname = province_yname;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
