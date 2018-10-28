package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ihope007 on 2016/11/23.
 */
public class VipOrderEntity {

    /**
     * status : 200
     * data : [{"id":null,"uid":null,"h_id":"32","city":null,"k_static":"2","k_num":"1","y_area":"234","y_ci":"4","y_static":"2","y_num":"1","j_area":"234","j_ci":"3","j_static":"0","j_num":"1","is_zhao":"0","is_static":"0","is_guan":"1","z_type":"","z_static":"0","static":"1","ctime":null,"housing_type":null,"address_info":null,"land_area":null,"built_area":null,"green_area":null,"cement_area":null,"alternate_contact":null,"lat":null,"long":null,"is_del":null},{"id":"30","uid":"6","h_id":"30","city":"北京","k_static":"2","k_num":"1","y_area":"234","y_ci":"4","y_static":"2","y_num":"1","j_area":"234","j_ci":"3","j_static":"0","j_num":"1","is_zhao":"0","is_static":"0","is_guan":"1","z_type":"","z_static":"0","static":"1","ctime":"1478498947","housing_type":"别墅","address_info":"分居老K","land_area":"200","built_area":"100","green_area":"30","cement_area":"30","alternate_contact":"13245784679","lat":"116.3192876558","long":"40.0592732065","is_del":"0"},{"id":"22","uid":"2","h_id":"22","city":"北京","k_static":"2","k_num":"1","y_area":"234","y_ci":"4","y_static":"2","y_num":"1","j_area":"234","j_ci":"3","j_static":"0","j_num":"1","is_zhao":"0","is_static":"0","is_guan":"1","z_type":"","z_static":"0","static":"1","ctime":"1478145814","housing_type":"商品房","address_info":"北京海淀","land_area":"60","built_area":"60","green_area":"10","cement_area":"10","alternate_contact":"一片枫叶","lat":"116.3192876558","long":"40.0592732065","is_del":"0"},{"id":"35","uid":"2","h_id":"35","city":"安莫尔","k_static":"0","k_num":"1","y_area":"","y_ci":"0","y_static":"0","y_num":"1","j_area":"","j_ci":"0","j_static":"0","j_num":"1","is_zhao":"1","is_static":"0","is_guan":"1","z_type":"土房","z_static":"0","static":"1","ctime":"1479717882","housing_type":"草屋","address_info":"村东头","land_area":"50","built_area":"50","green_area":"50","cement_area":"50","alternate_contact":"13846994646","lat":"39.9","long":"116.4","is_del":"0"}]
     */

    private int status;
    /**
     * id : null
     * uid : null
     * h_id : 32
     * city : null
     * k_static : 2
     * k_num : 1
     * y_area : 234
     * y_ci : 4
     * y_static : 2
     * y_num : 1
     * j_area : 234
     * j_ci : 3
     * j_static : 0
     * j_num : 1
     * is_zhao : 0
     * is_static : 0
     * is_guan : 1
     * z_type :
     * z_static : 0
     * static : 1
     * ctime : null
     * housing_type : null
     * address_info : null
     * land_area : null
     * built_area : null
     * green_area : null
     * cement_area : null
     * alternate_contact : null
     * lat : null
     * long : null
     * is_del : null
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
        private String uid;
        private String h_id;
        private String city;
        private String k_static;
        private String k_num;
        private String y_area;
        private String y_ci;
        private String y_static;
        private String y_num;
        private String j_area;
        private String j_ci;
        private String j_static;
        private String j_num;
        private String is_zhao;
        private String is_static;
        private String is_guan;
        private String z_type;
        private String z_static;
        @SerializedName("static")
        private String staticX;
        private String ctime;
        private String housing_type;
        private String address_info;
        private String land_area;
        private String built_area;
        private String green_area;
        private String cement_area;
        private String alternate_contact;
        private String lat;
        @SerializedName("long")
        private String longX;
        private String is_del;

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

        public String getH_id() {
            return h_id;
        }

        public void setH_id(String h_id) {
            this.h_id = h_id;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getK_static() {
            return k_static;
        }

        public void setK_static(String k_static) {
            this.k_static = k_static;
        }

        public String getK_num() {
            return k_num;
        }

        public void setK_num(String k_num) {
            this.k_num = k_num;
        }

        public String getY_area() {
            return y_area;
        }

        public void setY_area(String y_area) {
            this.y_area = y_area;
        }

        public String getY_ci() {
            return y_ci;
        }

        public void setY_ci(String y_ci) {
            this.y_ci = y_ci;
        }

        public String getY_static() {
            return y_static;
        }

        public void setY_static(String y_static) {
            this.y_static = y_static;
        }

        public String getY_num() {
            return y_num;
        }

        public void setY_num(String y_num) {
            this.y_num = y_num;
        }

        public String getJ_area() {
            return j_area;
        }

        public void setJ_area(String j_area) {
            this.j_area = j_area;
        }

        public String getJ_ci() {
            return j_ci;
        }

        public void setJ_ci(String j_ci) {
            this.j_ci = j_ci;
        }

        public String getJ_static() {
            return j_static;
        }

        public void setJ_static(String j_static) {
            this.j_static = j_static;
        }

        public String getJ_num() {
            return j_num;
        }

        public void setJ_num(String j_num) {
            this.j_num = j_num;
        }

        public String getIs_zhao() {
            return is_zhao;
        }

        public void setIs_zhao(String is_zhao) {
            this.is_zhao = is_zhao;
        }

        public String getIs_static() {
            return is_static;
        }

        public void setIs_static(String is_static) {
            this.is_static = is_static;
        }

        public String getIs_guan() {
            return is_guan;
        }

        public void setIs_guan(String is_guan) {
            this.is_guan = is_guan;
        }

        public String getZ_type() {
            return z_type;
        }

        public void setZ_type(String z_type) {
            this.z_type = z_type;
        }

        public String getZ_static() {
            return z_static;
        }

        public void setZ_static(String z_static) {
            this.z_static = z_static;
        }

        public String getStaticX() {
            return staticX;
        }

        public void setStaticX(String staticX) {
            this.staticX = staticX;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getHousing_type() {
            return housing_type;
        }

        public void setHousing_type(String housing_type) {
            this.housing_type = housing_type;
        }

        public String getAddress_info() {
            return address_info;
        }

        public void setAddress_info(String address_info) {
            this.address_info = address_info;
        }

        public String getLand_area() {
            return land_area;
        }

        public void setLand_area(String land_area) {
            this.land_area = land_area;
        }

        public String getBuilt_area() {
            return built_area;
        }

        public void setBuilt_area(String built_area) {
            this.built_area = built_area;
        }

        public String getGreen_area() {
            return green_area;
        }

        public void setGreen_area(String green_area) {
            this.green_area = green_area;
        }

        public String getCement_area() {
            return cement_area;
        }

        public void setCement_area(String cement_area) {
            this.cement_area = cement_area;
        }

        public String getAlternate_contact() {
            return alternate_contact;
        }

        public void setAlternate_contact(String alternate_contact) {
            this.alternate_contact = alternate_contact;
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

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }
    }
}
