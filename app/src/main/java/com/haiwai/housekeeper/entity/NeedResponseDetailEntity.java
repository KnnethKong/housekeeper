package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ihope007 on 2016/9/6.
 */
public class NeedResponseDetailEntity {
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
        private String user_quci;
        private SkillBean skillBean;

        public String getLiao() {
            return liao;
        }

        public void setLiao(String liao) {
            this.liao = liao;
        }

        public String getUser_quci() {
            return user_quci;
        }

        public void setUser_quci(String user_quci) {
            this.user_quci = user_quci;
        }

        public SkillBean getSkillBean() {
            return skillBean;
        }

        public void setSkillBean(SkillBean skillBean) {
            this.skillBean = skillBean;
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

        public OfferBean getOffer() {
            return offer;
        }

        public void setOffer(OfferBean offer) {
            this.offer = offer;
        }

        public SkillBean getSkill() {
            return skillBean;
        }

        public void setSkill(SkillBean skillBean) {
            this.skillBean = skillBean;
        }

        public static class DateBean{
            private String id;
            private String order_id;
            private String uid;
            private String at_uid;
            private String j_uid;
            private String j_num;
            private String type;
            private String h_id;
            private String service_type;
            private String staticx;
            private String is_ypin;
            private String is_fpin;
            private String is_zhi;
            private String wen1;
            private String da1;
            private String wen2;
            private String da2;
            private String wen3;
            private String da3;
            private String wen4;
            private String da4;
            private String wen5;
            private String da5;
            private String wen6;
            private String da6;
            private String wen7;
            private String da7;
            private String wen8;
            private String da8;
            private String wen9;
            private String da9;
            private String wen10;
            private String da10;
            private String wen11;
            private String da11;
            private String wen12;
            private String da12;
            private String wen13;
            private String da13;
            private String wen14;
            private String da14;
            private String wen15;
            private String da15;
            private String wen16;
            private String da16;
            private String wen17;
            private String da17;
            private String wen18;
            private String da18;
            private String wen19;
            private String da19;
            private String ctime;

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

            public String getAt_uid() {
                return at_uid;
            }

            public void setAt_uid(String at_uid) {
                this.at_uid = at_uid;
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

            public String getService_type() {
                return service_type;
            }

            public void setService_type(String service_type) {
                this.service_type = service_type;
            }

            public String getStatics() {
                return staticx;
            }

            public void setStatics(String statics) {
                this.staticx = statics;
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

            public String getIs_zhi() {
                return is_zhi;
            }

            public void setIs_zhi(String is_zhi) {
                this.is_zhi = is_zhi;
            }

            public String getWen1() {
                return wen1;
            }

            public void setWen1(String wen1) {
                this.wen1 = wen1;
            }

            public String getDa1() {
                return da1;
            }

            public void setDa1(String da1) {
                this.da1 = da1;
            }

            public String getWen2() {
                return wen2;
            }

            public void setWen2(String wen2) {
                this.wen2 = wen2;
            }

            public String getDa2() {
                return da2;
            }

            public void setDa2(String da2) {
                this.da2 = da2;
            }

            public String getWen3() {
                return wen3;
            }

            public void setWen3(String wen3) {
                this.wen3 = wen3;
            }

            public String getDa3() {
                return da3;
            }

            public void setDa3(String da3) {
                this.da3 = da3;
            }

            public String getWen4() {
                return wen4;
            }

            public void setWen4(String wen4) {
                this.wen4 = wen4;
            }

            public String getDa4() {
                return da4;
            }

            public void setDa4(String da4) {
                this.da4 = da4;
            }

            public String getWen5() {
                return wen5;
            }

            public void setWen5(String wen5) {
                this.wen5 = wen5;
            }

            public String getDa5() {
                return da5;
            }

            public void setDa5(String da5) {
                this.da5 = da5;
            }

            public String getWen6() {
                return wen6;
            }

            public void setWen6(String wen6) {
                this.wen6 = wen6;
            }

            public String getDa6() {
                return da6;
            }

            public void setDa6(String da6) {
                this.da6 = da6;
            }

            public String getWen7() {
                return wen7;
            }

            public void setWen7(String wen7) {
                this.wen7 = wen7;
            }

            public String getDa7() {
                return da7;
            }

            public void setDa7(String da7) {
                this.da7 = da7;
            }

            public String getWen8() {
                return wen8;
            }

            public void setWen8(String wen8) {
                this.wen8 = wen8;
            }

            public String getDa8() {
                return da8;
            }

            public void setDa8(String da8) {
                this.da8 = da8;
            }

            public String getWen9() {
                return wen9;
            }

            public void setWen9(String wen9) {
                this.wen9 = wen9;
            }

            public String getDa9() {
                return da9;
            }

            public void setDa9(String da9) {
                this.da9 = da9;
            }

            public String getWen10() {
                return wen10;
            }

            public void setWen10(String wen10) {
                this.wen10 = wen10;
            }

            public String getDa10() {
                return da10;
            }

            public void setDa10(String da10) {
                this.da10 = da10;
            }

            public String getWen11() {
                return wen11;
            }

            public void setWen11(String wen11) {
                this.wen11 = wen11;
            }

            public String getDa11() {
                return da11;
            }

            public void setDa11(String da11) {
                this.da11 = da11;
            }

            public String getWen12() {
                return wen12;
            }

            public void setWen12(String wen12) {
                this.wen12 = wen12;
            }

            public String getDa12() {
                return da12;
            }

            public void setDa12(String da12) {
                this.da12 = da12;
            }

            public String getWen13() {
                return wen13;
            }

            public void setWen13(String wen13) {
                this.wen13 = wen13;
            }

            public String getDa13() {
                return da13;
            }

            public void setDa13(String da13) {
                this.da13 = da13;
            }

            public String getWen14() {
                return wen14;
            }

            public void setWen14(String wen14) {
                this.wen14 = wen14;
            }

            public String getDa14() {
                return da14;
            }

            public void setDa14(String da14) {
                this.da14 = da14;
            }

            public String getWen15() {
                return wen15;
            }

            public void setWen15(String wen15) {
                this.wen15 = wen15;
            }

            public String getDa15() {
                return da15;
            }

            public void setDa15(String da15) {
                this.da15 = da15;
            }

            public String getWen16() {
                return wen16;
            }

            public void setWen16(String wen16) {
                this.wen16 = wen16;
            }

            public String getDa16() {
                return da16;
            }

            public void setDa16(String da16) {
                this.da16 = da16;
            }

            public String getWen17() {
                return wen17;
            }

            public void setWen17(String wen17) {
                this.wen17 = wen17;
            }

            public String getDa17() {
                return da17;
            }

            public void setDa17(String da17) {
                this.da17 = da17;
            }

            public String getWen18() {
                return wen18;
            }

            public void setWen18(String wen18) {
                this.wen18 = wen18;
            }

            public String getDa18() {
                return da18;
            }

            public void setDa18(String da18) {
                this.da18 = da18;
            }

            public String getWen19() {
                return wen19;
            }

            public void setWen19(String wen19) {
                this.wen19 = wen19;
            }

            public String getDa19() {
                return da19;
            }

            public void setDa19(String da19) {
                this.da19 = da19;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            @Override
            public String toString() {
                return "DateBean{" +
                        "id='" + id + '\'' +
                        ", order_id='" + order_id + '\'' +
                        ", uid='" + uid + '\'' +
                        ", at_uid='" + at_uid + '\'' +
                        ", j_uid='" + j_uid + '\'' +
                        ", j_num='" + j_num + '\'' +
                        ", type='" + type + '\'' +
                        ", h_id='" + h_id + '\'' +
                        ", service_type='" + service_type + '\'' +
                        ", staticx='" + staticx + '\'' +
                        ", is_ypin='" + is_ypin + '\'' +
                        ", is_fpin='" + is_fpin + '\'' +
                        ", is_zhi='" + is_zhi + '\'' +
                        ", wen1='" + wen1 + '\'' +
                        ", da1='" + da1 + '\'' +
                        ", wen2='" + wen2 + '\'' +
                        ", da2='" + da2 + '\'' +
                        ", wen3='" + wen3 + '\'' +
                        ", da3='" + da3 + '\'' +
                        ", wen4='" + wen4 + '\'' +
                        ", da4='" + da4 + '\'' +
                        ", wen5='" + wen5 + '\'' +
                        ", da5='" + da5 + '\'' +
                        ", wen6='" + wen6 + '\'' +
                        ", da6='" + da6 + '\'' +
                        ", wen7='" + wen7 + '\'' +
                        ", da7='" + da7 + '\'' +
                        ", wen8='" + wen8 + '\'' +
                        ", da8='" + da8 + '\'' +
                        ", wen9='" + wen9 + '\'' +
                        ", da9='" + da9 + '\'' +
                        ", wen10='" + wen10 + '\'' +
                        ", da10='" + da10 + '\'' +
                        ", wen11='" + wen11 + '\'' +
                        ", da11='" + da11 + '\'' +
                        ", wen12='" + wen12 + '\'' +
                        ", da12='" + da12 + '\'' +
                        ", wen13='" + wen13 + '\'' +
                        ", da13='" + da13 + '\'' +
                        ", wen14='" + wen14 + '\'' +
                        ", da14='" + da14 + '\'' +
                        ", wen15='" + wen15 + '\'' +
                        ", da15='" + da15 + '\'' +
                        ", wen16='" + wen16 + '\'' +
                        ", da16='" + da16 + '\'' +
                        ", wen17='" + wen17 + '\'' +
                        ", da17='" + da17 + '\'' +
                        ", wen18='" + wen18 + '\'' +
                        ", da18='" + da18 + '\'' +
                        ", wen19='" + wen19 + '\'' +
                        ", da19='" + da19 + '\'' +
                        ", ctime='" + ctime + '\'' +
                        '}';
            }
        }

        public static class HousBean{
            private String id;
            private String uid;
            private String city;
            private String housing_type;
            private String address_info;
            private String land_area;
            private String built_area;
            private String green_area;
            private String cement_area;
            private String alternate_contact;
            public String email;
            public String alternate_contact_number;
            private String lat;
            private String longx;
            private String ctime;

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

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
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

            public String getLongx() {
                return longx;
            }

            public void setLongx(String longx) {
                this.longx = longx;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            @Override
            public String toString() {
                return "HousBean{" +
                        "id='" + id + '\'' +
                        ", uid='" + uid + '\'' +
                        ", city='" + city + '\'' +
                        ", housing_type='" + housing_type + '\'' +
                        ", address_info='" + address_info + '\'' +
                        ", land_area='" + land_area + '\'' +
                        ", built_area='" + built_area + '\'' +
                        ", green_area='" + green_area + '\'' +
                        ", cement_area='" + cement_area + '\'' +
                        ", alternate_contact='" + alternate_contact + '\'' +
                        ", lat='" + lat + '\'' +
                        ", longx='" + longx + '\'' +
                        ", ctime='" + ctime + '\'' +
                        '}';
            }
        }

        public static class UserBean{
            private String uid;
            private String sex;
            private String avatar;
            private String nickname;
            public String area;
            private String mobile;
            private String name;
            private String introduction;
            private String is_ren;
            private String is_hou;
            private String only_label;
            private String balance;
            private String address;
            private String pro_quci;
            private String user_quci;
            private String pro_score;
            private String user_score;
            private String lat;
            private String longx;
            private String ltime;
            private String ctime;
            private String pro_xing;
            private String pro_onum;

            public String getPro_xing() {
                return pro_xing;
            }

            public void setPro_xing(String pro_xing) {
                this.pro_xing = pro_xing;
            }

            public String getPro_onum() {
                return pro_onum;
            }

            public void setPro_onum(String pro_onum) {
                this.pro_onum = pro_onum;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
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

            public String getIs_hou() {
                return is_hou;
            }

            public void setIs_hou(String is_hou) {
                this.is_hou = is_hou;
            }

            public String getOnly_label() {
                return only_label;
            }

            public void setOnly_label(String only_label) {
                this.only_label = only_label;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getPro_quci() {
                return pro_quci;
            }

            public void setPro_quci(String pro_quci) {
                this.pro_quci = pro_quci;
            }

            public String getUser_quci() {
                return user_quci;
            }

            public void setUser_quci(String user_quci) {
                this.user_quci = user_quci;
            }

            public String getPro_score() {
                return pro_score;
            }

            public void setPro_score(String pro_score) {
                this.pro_score = pro_score;
            }

            public String getUser_score() {
                return user_score;
            }

            public void setUser_score(String user_score) {
                this.user_score = user_score;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLongx() {
                return longx;
            }

            public void setLongx(String longx) {
                this.longx = longx;
            }

            public String getLtime() {
                return ltime;
            }

            public void setLtime(String ltime) {
                this.ltime = ltime;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            @Override
            public String toString() {
                return "UserBean{" +
                        "uid='" + uid + '\'' +
                        ", sex='" + sex + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", mobile='" + mobile + '\'' +
                        ", name='" + name + '\'' +
                        ", introduction='" + introduction + '\'' +
                        ", is_ren='" + is_ren + '\'' +
                        ", is_hou='" + is_hou + '\'' +
                        ", only_label='" + only_label + '\'' +
                        ", balance='" + balance + '\'' +
                        ", address='" + address + '\'' +
                        ", pro_quci='" + pro_quci + '\'' +
                        ", user_quci='" + user_quci + '\'' +
                        ", pro_score='" + pro_score + '\'' +
                        ", user_score='" + user_score + '\'' +
                        ", lat='" + lat + '\'' +
                        ", longx='" + longx + '\'' +
                        ", ltime='" + ltime + '\'' +
                        ", ctime='" + ctime + '\'' +
                        ", pro_xing='" + pro_xing + '\'' +
                        ", pro_onum='" + pro_onum + '\'' +
                        '}';
            }
        }

        public static class OfferBean{
            private String id;
            private String oid;
            private String uid;
            private String home_fee;
            private String inspection;
            private String service_type;
            private String hourly;
            private String hour;
            private String general;
            private String material;
            private String message;
            private String is_xuan;
            private String ctime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOid() {
                return oid;
            }

            public void setOid(String oid) {
                this.oid = oid;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getHome_fee() {
                return home_fee;
            }

            public void setHome_fee(String home_fee) {
                this.home_fee = home_fee;
            }

            public String getInspection() {
                return inspection;
            }

            public void setInspection(String inspection) {
                this.inspection = inspection;
            }

            public String getService_type() {
                return service_type;
            }

            public void setService_type(String service_type) {
                this.service_type = service_type;
            }

            public String getHourly() {
                return hourly;
            }

            public void setHourly(String hourly) {
                this.hourly = hourly;
            }

            public String getHour() {
                return hour;
            }

            public void setHour(String hour) {
                this.hour = hour;
            }

            public String getGeneral() {
                return general;
            }

            public void setGeneral(String general) {
                this.general = general;
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

            public String getIs_xuan() {
                return is_xuan;
            }

            public void setIs_xuan(String is_xuan) {
                this.is_xuan = is_xuan;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            @Override
            public String toString() {
                return "OfferBean{" +
                        "id='" + id + '\'' +
                        ", oid='" + oid + '\'' +
                        ", uid='" + uid + '\'' +
                        ", home_fee='" + home_fee + '\'' +
                        ", inspection='" + inspection + '\'' +
                        ", service_type='" + service_type + '\'' +
                        ", hourly='" + hourly + '\'' +
                        ", hour='" + hour + '\'' +
                        ", general='" + general + '\'' +
                        ", material='" + material + '\'' +
                        ", message='" + message + '\'' +
                        ", is_xuan='" + is_xuan + '\'' +
                        ", ctime='" + ctime + '\'' +
                        '}';
            }
        }

        public static class SkillBean{
            private String id;
            private String uid;
            private String type;
            private String order_num;
            private String xing_num;
            @SerializedName("class")
            private String classX;
            private String advantage;
            private String is_recommend;
            private String is_audit;
            private String v;
            private String is_ji;
            private String is_ren;
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
            private String wen20;
            private String wen21;
            private String wen22;
            private String wen23;
            private String wen24;
            private String wen25;
            private String wen26;
            private String wen27;
            private String wen28;
            private String wen29;
            private String wen30;
            private String wen31;
            private String wen32;
            private String wen33;
            private String wen34;
            private String ctime;

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

            public String getOrder_num() {
                return order_num;
            }

            public void setOrder_num(String order_num) {
                this.order_num = order_num;
            }

            public String getXing_num() {
                return xing_num;
            }

            public void setXing_num(String xing_num) {
                this.xing_num = xing_num;
            }

            public String getClassX() {
                return classX;
            }

            public void setClassX(String classX) {
                this.classX = classX;
            }

            public String getAdvantage() {
                return advantage;
            }

            public void setAdvantage(String advantage) {
                this.advantage = advantage;
            }

            public String getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(String is_recommend) {
                this.is_recommend = is_recommend;
            }

            public String getIs_audit() {
                return is_audit;
            }

            public void setIs_audit(String is_audit) {
                this.is_audit = is_audit;
            }

            public String getV() {
                return v;
            }

            public void setV(String v) {
                this.v = v;
            }

            public String getIs_ji() {
                return is_ji;
            }

            public void setIs_ji(String is_ji) {
                this.is_ji = is_ji;
            }

            public String getIs_ren() {
                return is_ren;
            }

            public void setIs_ren(String is_ren) {
                this.is_ren = is_ren;
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

            public String getWen20() {
                return wen20;
            }

            public void setWen20(String wen20) {
                this.wen20 = wen20;
            }

            public String getWen21() {
                return wen21;
            }

            public void setWen21(String wen21) {
                this.wen21 = wen21;
            }

            public String getWen22() {
                return wen22;
            }

            public void setWen22(String wen22) {
                this.wen22 = wen22;
            }

            public String getWen23() {
                return wen23;
            }

            public void setWen23(String wen23) {
                this.wen23 = wen23;
            }

            public String getWen24() {
                return wen24;
            }

            public void setWen24(String wen24) {
                this.wen24 = wen24;
            }

            public String getWen25() {
                return wen25;
            }

            public void setWen25(String wen25) {
                this.wen25 = wen25;
            }

            public String getWen26() {
                return wen26;
            }

            public void setWen26(String wen26) {
                this.wen26 = wen26;
            }

            public String getWen27() {
                return wen27;
            }

            public void setWen27(String wen27) {
                this.wen27 = wen27;
            }

            public String getWen28() {
                return wen28;
            }

            public void setWen28(String wen28) {
                this.wen28 = wen28;
            }

            public String getWen29() {
                return wen29;
            }

            public void setWen29(String wen29) {
                this.wen29 = wen29;
            }

            public String getWen30() {
                return wen30;
            }

            public void setWen30(String wen30) {
                this.wen30 = wen30;
            }

            public String getWen31() {
                return wen31;
            }

            public void setWen31(String wen31) {
                this.wen31 = wen31;
            }

            public String getWen32() {
                return wen32;
            }

            public void setWen32(String wen32) {
                this.wen32 = wen32;
            }

            public String getWen33() {
                return wen33;
            }

            public void setWen33(String wen33) {
                this.wen33 = wen33;
            }

            public String getWen34() {
                return wen34;
            }

            public void setWen34(String wen34) {
                this.wen34 = wen34;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            @Override
            public String toString() {
                return "SkillBean{" +
                        "id='" + id + '\'' +
                        ", uid='" + uid + '\'' +
                        ", type='" + type + '\'' +
                        ", order_num='" + order_num + '\'' +
                        ", xing_num='" + xing_num + '\'' +
                        ", classX='" + classX + '\'' +
                        ", advantage='" + advantage + '\'' +
                        ", is_recommend='" + is_recommend + '\'' +
                        ", is_audit='" + is_audit + '\'' +
                        ", v='" + v + '\'' +
                        ", is_ji='" + is_ji + '\'' +
                        ", is_ren='" + is_ren + '\'' +
                        ", wen1='" + wen1 + '\'' +
                        ", wen2='" + wen2 + '\'' +
                        ", wen3='" + wen3 + '\'' +
                        ", wen4='" + wen4 + '\'' +
                        ", wen5='" + wen5 + '\'' +
                        ", wen6='" + wen6 + '\'' +
                        ", wen7='" + wen7 + '\'' +
                        ", wen8='" + wen8 + '\'' +
                        ", wen9='" + wen9 + '\'' +
                        ", wen10='" + wen10 + '\'' +
                        ", wen11='" + wen11 + '\'' +
                        ", wen12='" + wen12 + '\'' +
                        ", wen13='" + wen13 + '\'' +
                        ", wen14='" + wen14 + '\'' +
                        ", wen15='" + wen15 + '\'' +
                        ", wen16='" + wen16 + '\'' +
                        ", wen17='" + wen17 + '\'' +
                        ", wen18='" + wen18 + '\'' +
                        ", wen19='" + wen19 + '\'' +
                        ", wen20='" + wen20 + '\'' +
                        ", wen21='" + wen21 + '\'' +
                        ", wen22='" + wen22 + '\'' +
                        ", wen23='" + wen23 + '\'' +
                        ", wen24='" + wen24 + '\'' +
                        ", wen25='" + wen25 + '\'' +
                        ", wen26='" + wen26 + '\'' +
                        ", wen27='" + wen27 + '\'' +
                        ", wen28='" + wen28 + '\'' +
                        ", wen29='" + wen29 + '\'' +
                        ", wen30='" + wen30 + '\'' +
                        ", wen31='" + wen31 + '\'' +
                        ", wen32='" + wen32 + '\'' +
                        ", wen33='" + wen33 + '\'' +
                        ", wen34='" + wen34 + '\'' +
                        ", ctime='" + ctime + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "date=" + date +
                    ", hous=" + hous +
                    ", user=" + user +
                    ", offer=" + offer +
                    ", skillBean=" + skillBean +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NeedResponseDetailEntity{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
