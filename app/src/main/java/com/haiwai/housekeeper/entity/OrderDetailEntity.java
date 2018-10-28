package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ihope10 on 2016/10/31.
 */

public class OrderDetailEntity {
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

    public static class DataBean {
        private DateBean date;
        private HousBean hous;
        private UserBean user;
        private OfferBean offer;
        private String liao;

        public String getLiao() {
            return liao;
        }

        public void setLiao(String liao) {
            this.liao = liao;
        }

        public OfferBean getOffer() {
            return offer;
        }

        public void setOffer(OfferBean offer) {
            this.offer = offer;
        }

        public DateBean getDate() {
            return date;
        }

        public void setDate(DateBean date) {
            this.date = date;
        }

        public HousBean getHous() {
            return hous;
        }

        public void setHous(HousBean hous) {
            this.hous = hous;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class DateBean implements Serializable {
            private String at_uid;
            private String ctime;
            private String h_id;
            private String id;
            private String j_uid;
            private String order_id;
            @SerializedName("static")
            private String staticX;
            private String type;
            private String uid;
            private String wen1;
            private String wen2;
            private String wen3;
            private String wen4;
            private String wen5;
            private String wen6;
            private String wen7;
            private String wen8;
            private String wen9;
            private String wen10;
            private String wen11;
            private String wen12;
            private String wen13;
            private String wen14;
            private String wen15;
            private String wen16;
            private String wen17;
            private String wen18;
            private String wen19;
            private String da1;
            private String da2;
            private String da3;
            private String da4;
            private String da5;
            private String da6;
            private String da7;
            private String da8;
            private String da9;
            private String da10;
            private String da11;
            private String da12;
            private String da13;
            private String da14;
            private String da15;
            private String da16;
            private String da17;
            private String da18;
            private String da19;

            public String getAt_uid() {
                return at_uid;
            }

            public void setAt_uid(String at_uid) {
                this.at_uid = at_uid;
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

            public String getJ_uid() {
                return j_uid;
            }

            public void setJ_uid(String j_uid) {
                this.j_uid = j_uid;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
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

            public String getWen1() {
                return wen1;
            }

            public void setWen1(String wen1) {
                this.wen1 = wen1;
            }

            public String getWen2() {
                return wen2;
            }

            public void setWen2(String wen2) {
                this.wen2 = wen2;
            }

            public String getWen3() {
                return wen3;
            }

            public void setWen3(String wen3) {
                this.wen3 = wen3;
            }

            public String getWen4() {
                return wen4;
            }

            public void setWen4(String wen4) {
                this.wen4 = wen4;
            }

            public String getWen5() {
                return wen5;
            }

            public void setWen5(String wen5) {
                this.wen5 = wen5;
            }

            public String getWen6() {
                return wen6;
            }

            public void setWen6(String wen6) {
                this.wen6 = wen6;
            }

            public String getWen7() {
                return wen7;
            }

            public void setWen7(String wen7) {
                this.wen7 = wen7;
            }

            public String getWen8() {
                return wen8;
            }

            public void setWen8(String wen8) {
                this.wen8 = wen8;
            }

            public String getWen9() {
                return wen9;
            }

            public void setWen9(String wen9) {
                this.wen9 = wen9;
            }

            public String getWen10() {
                return wen10;
            }

            public void setWen10(String wen10) {
                this.wen10 = wen10;
            }

            public String getWen11() {
                return wen11;
            }

            public void setWen11(String wen11) {
                this.wen11 = wen11;
            }

            public String getWen12() {
                return wen12;
            }

            public void setWen12(String wen12) {
                this.wen12 = wen12;
            }

            public String getWen13() {
                return wen13;
            }

            public void setWen13(String wen13) {
                this.wen13 = wen13;
            }

            public String getWen14() {
                return wen14;
            }

            public void setWen14(String wen14) {
                this.wen14 = wen14;
            }

            public String getWen15() {
                return wen15;
            }

            public void setWen15(String wen15) {
                this.wen15 = wen15;
            }

            public String getWen16() {
                return wen16;
            }

            public void setWen16(String wen16) {
                this.wen16 = wen16;
            }

            public String getWen17() {
                return wen17;
            }

            public void setWen17(String wen17) {
                this.wen17 = wen17;
            }

            public String getWen18() {
                return wen18;
            }

            public void setWen18(String wen18) {
                this.wen18 = wen18;
            }

            public String getWen19() {
                return wen19;
            }

            public void setWen19(String wen19) {
                this.wen19 = wen19;
            }

            public String getDa1() {
                return da1;
            }

            public void setDa1(String da1) {
                this.da1 = da1;
            }

            public String getDa2() {
                return da2;
            }

            public void setDa2(String da2) {
                this.da2 = da2;
            }

            public String getDa3() {
                return da3;
            }

            public void setDa3(String da3) {
                this.da3 = da3;
            }

            public String getDa4() {
                return da4;
            }

            public void setDa4(String da4) {
                this.da4 = da4;
            }

            public String getDa5() {
                return da5;
            }

            public void setDa5(String da5) {
                this.da5 = da5;
            }

            public String getDa6() {
                return da6;
            }

            public void setDa6(String da6) {
                this.da6 = da6;
            }

            public String getDa7() {
                return da7;
            }

            public void setDa7(String da7) {
                this.da7 = da7;
            }

            public String getDa8() {
                return da8;
            }

            public void setDa8(String da8) {
                this.da8 = da8;
            }

            public String getDa9() {
                return da9;
            }

            public void setDa9(String da9) {
                this.da9 = da9;
            }

            public String getDa10() {
                return da10;
            }

            public void setDa10(String da10) {
                this.da10 = da10;
            }

            public String getDa11() {
                return da11;
            }

            public void setDa11(String da11) {
                this.da11 = da11;
            }

            public String getDa12() {
                return da12;
            }

            public void setDa12(String da12) {
                this.da12 = da12;
            }

            public String getDa13() {
                return da13;
            }

            public void setDa13(String da13) {
                this.da13 = da13;
            }

            public String getDa14() {
                return da14;
            }

            public void setDa14(String da14) {
                this.da14 = da14;
            }

            public String getDa15() {
                return da15;
            }

            public void setDa15(String da15) {
                this.da15 = da15;
            }

            public String getDa16() {
                return da16;
            }

            public void setDa16(String da16) {
                this.da16 = da16;
            }

            public String getDa17() {
                return da17;
            }

            public void setDa17(String da17) {
                this.da17 = da17;
            }

            public String getDa18() {
                return da18;
            }

            public void setDa18(String da18) {
                this.da18 = da18;
            }

            public String getDa19() {
                return da19;
            }

            public void setDa19(String da19) {
                this.da19 = da19;
            }
        }

        public static class HousBean {
            private String address_info;
            private String alternate_contact;
            private String built_area;
            private String cement_area;
            private String city;
            private String ctime;
            private String country;
            private String province;
            private String house_number;
            private String street;
            private String green_area;
            public String alternate_contact_number;
            private String housing_type;
            public String contact_number;
            private String id;
            public String email;
            private String land_area;
            private String lat;
            @SerializedName("long")
            private String longX;
            private String uid;

            public String getHouse_number() {
                return house_number;
            }

            public void setHouse_number(String house_number) {
                this.house_number = house_number;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
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

            public String getBuilt_area() {
                return built_area;
            }

            public void setBuilt_area(String built_area) {
                this.built_area = built_area;
            }

            public String getCement_area() {
                return cement_area;
            }

            public void setCement_area(String cement_area) {
                this.cement_area = cement_area;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getGreen_area() {
                return green_area;
            }

            public void setGreen_area(String green_area) {
                this.green_area = green_area;
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

            public String getLand_area() {
                return land_area;
            }

            public void setLand_area(String land_area) {
                this.land_area = land_area;
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

        public static class UserBean {
            private String address;
            private String avatar;
            private String balance;
            private String ctime;
            private String introduction;
            private String is_ren;
            private String lat;
            @SerializedName("long")
            private String longX;
            private String ltime;
            public String area;
            private String mobile;
            private String name;
            public String user_score;
            private String nickname;
            private String only_label;
            private String pro_quci;
            private String sex;
            private String uid;
            private String user_quci;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getIs_ren() {
                return is_ren;
            }

            public void setIs_ren(String is_ren) {
                this.is_ren = is_ren;
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

            public String getLtime() {
                return ltime;
            }

            public void setLtime(String ltime) {
                this.ltime = ltime;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getOnly_label() {
                return only_label;
            }

            public void setOnly_label(String only_label) {
                this.only_label = only_label;
            }

            public String getPro_quci() {
                return pro_quci;
            }

            public void setPro_quci(String pro_quci) {
                this.pro_quci = pro_quci;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getUser_quci() {
                return user_quci;
            }

            public void setUser_quci(String user_quci) {
                this.user_quci = user_quci;
            }
        }

        public static class OfferBean {
            private String ctime;
            private String general;
            private String home_fee;
            private String hour;
            private String hourly;
            private String id;
            private String inspection;
            private int is_jie;
            private String is_xuan;
            private String material;
            private String message;
            private String oid;
            private String service_type;
            private String uid;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getGeneral() {
                return general;
            }

            public void setGeneral(String general) {
                this.general = general;
            }

            public String getHome_fee() {
                return home_fee;
            }

            public void setHome_fee(String home_fee) {
                this.home_fee = home_fee;
            }

            public String getHour() {
                return hour;
            }

            public void setHour(String hour) {
                this.hour = hour;
            }

            public String getHourly() {
                return hourly;
            }

            public void setHourly(String hourly) {
                this.hourly = hourly;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getInspection() {
                return inspection;
            }

            public void setInspection(String inspection) {
                this.inspection = inspection;
            }

            public int getIs_jie() {
                return is_jie;
            }

            public void setIs_jie(int is_jie) {
                this.is_jie = is_jie;
            }

            public String getIs_xuan() {
                return is_xuan;
            }

            public void setIs_xuan(String is_xuan) {
                this.is_xuan = is_xuan;
            }

            public String getMaterial() {
                return material;
            }

            public void setMaterial(String material) {
                this.material = material;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getOid() {
                return oid;
            }

            public void setOid(String oid) {
                this.oid = oid;
            }

            public String getService_type() {
                return service_type;
            }

            public void setService_type(String service_type) {
                this.service_type = service_type;
            }
        }
    }

}
