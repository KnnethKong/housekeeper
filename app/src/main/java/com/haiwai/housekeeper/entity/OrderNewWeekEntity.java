package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ihope006 on 2016/12/15.
 */

public class OrderNewWeekEntity implements Serializable{
    private DataBean data;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean implements Serializable{
        private DateBean date;
        private HousBean hous;
        private OfferBean offer;
        private UserBean user;
        private List<FeedbackBean> feedback;
        private List<ProblemwBean> problemw;

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

        public OfferBean getOffer() {
            return offer;
        }

        public void setOffer(OfferBean offer) {
            this.offer = offer;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public List<FeedbackBean> getFeedback() {
            return feedback;
        }

        public void setFeedback(List<FeedbackBean> feedback) {
            this.feedback = feedback;
        }

        public List<ProblemwBean> getProblemw() {
            return problemw;
        }

        public void setProblemw(List<ProblemwBean> problemw) {
            this.problemw = problemw;
        }

        public static class DateBean implements Serializable{
            private String ctime;
            private String emonthtime;
            private String h_id;
            private String id;
            private String is_que;
            private String j_num;
            private String j_uid;
            private String math_num;
            private String money;
            private String monthtime;
            private String num;
            private String order_id;
            private String sid;
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

            public String getEmonthtime() {
                return emonthtime;
            }

            public void setEmonthtime(String emonthtime) {
                this.emonthtime = emonthtime;
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

            public String getIs_que() {
                return is_que;
            }

            public void setIs_que(String is_que) {
                this.is_que = is_que;
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

            public String getMath_num() {
                return math_num;
            }

            public void setMath_num(String math_num) {
                this.math_num = math_num;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getMonthtime() {
                return monthtime;
            }

            public void setMonthtime(String monthtime) {
                this.monthtime = monthtime;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
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
        }

        public static class HousBean implements Serializable{
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
            private String alternate_contact_number;
            private String is_bill;
            private String is_del;
            private String lat;
            @SerializedName("long")
            private String longX;
            private String uid;

            public String getAlternate_contact_number() {
                return alternate_contact_number;
            }

            public void setAlternate_contact_number(String alternate_contact_number) {
                this.alternate_contact_number = alternate_contact_number;
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

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
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

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getIs_bill() {
                return is_bill;
            }

            public void setIs_bill(String is_bill) {
                this.is_bill = is_bill;
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

        public static class OfferBean implements Serializable{
            private String ctime;
            private String id;
            private int is_jie;
            private String is_xuan;
            private String message;
            private String oid;
            private String uid;

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

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }
        }

        public static class UserBean implements Serializable{
            private String address;
            private String avatar;
            private String balance;
            private String ctime;
            private String fans_num;
            private String follow_num;
            private String introduction;
            private String is_don;
            private String is_hou;
            private String is_ren;
            private String lat;
            @SerializedName("long")
            private String longX;
            private String ltime;
            public String area;
            private String mobile;
            private String name;
            private String nickname;
            private String only_label;
            private String pro_onum;
            private String pro_quci;
            private String pro_score;
            private String pro_xing;
            private String sex;
            private String uid;
            private String user_onum;
            private String user_quci;
            private String user_score;
            private String user_xing;
            private String wd_content;
            private String wd_num;
            private String wd_time;

            public String getPro_xing() {
                return pro_xing;
            }

            public void setPro_xing(String pro_xing) {
                this.pro_xing = pro_xing;
            }

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

            public String getFans_num() {
                return fans_num;
            }

            public void setFans_num(String fans_num) {
                this.fans_num = fans_num;
            }

            public String getFollow_num() {
                return follow_num;
            }

            public void setFollow_num(String follow_num) {
                this.follow_num = follow_num;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getIs_hou() {
                return is_hou;
            }

            public void setIs_hou(String is_hou) {
                this.is_hou = is_hou;
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

            public String getPro_score() {
                return pro_score;
            }

            public void setPro_score(String pro_score) {
                this.pro_score = pro_score;
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

            public String getUser_score() {
                return user_score;
            }

            public void setUser_score(String user_score) {
                this.user_score = user_score;
            }

            public String getIs_don() {
                return is_don;
            }

            public void setIs_don(String is_don) {
                this.is_don = is_don;
            }

            public String getPro_onum() {
                return pro_onum;
            }

            public void setPro_onum(String pro_onum) {
                this.pro_onum = pro_onum;
            }

            public String getUser_onum() {
                return user_onum;
            }

            public void setUser_onum(String user_onum) {
                this.user_onum = user_onum;
            }

            public String getUser_xing() {
                return user_xing;
            }

            public void setUser_xing(String user_xing) {
                this.user_xing = user_xing;
            }

            public String getWd_content() {
                return wd_content;
            }

            public void setWd_content(String wd_content) {
                this.wd_content = wd_content;
            }

            public String getWd_num() {
                return wd_num;
            }

            public void setWd_num(String wd_num) {
                this.wd_num = wd_num;
            }

            public String getWd_time() {
                return wd_time;
            }

            public void setWd_time(String wd_time) {
                this.wd_time = wd_time;
            }
        }

        public static class FeedbackBean implements Serializable {
            private String content1;
            private String content2;
            private String content3;
            private String ctime;
            private String id;
            private String images1;
            private String images2;
            private String images3;
            private String is_new;
            private String is_special;
            private String is_wan;
            private String number;
            private String order_id;
            private String stime;
            private String wtime1;
            private String wtime2;
            private String wtime3;

            public String getContent1() {
                return content1;
            }

            public void setContent1(String content1) {
                this.content1 = content1;
            }

            public String getContent2() {
                return content2;
            }

            public void setContent2(String content2) {
                this.content2 = content2;
            }

            public String getContent3() {
                return content3;
            }

            public void setContent3(String content3) {
                this.content3 = content3;
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

            public String getImages1() {
                return images1;
            }

            public void setImages1(String images1) {
                this.images1 = images1;
            }

            public String getImages2() {
                return images2;
            }

            public void setImages2(String images2) {
                this.images2 = images2;
            }

            public String getImages3() {
                return images3;
            }

            public void setImages3(String images3) {
                this.images3 = images3;
            }

            public String getIs_new() {
                return is_new;
            }

            public void setIs_new(String is_new) {
                this.is_new = is_new;
            }

            public String getIs_special() {
                return is_special;
            }

            public void setIs_special(String is_special) {
                this.is_special = is_special;
            }

            public String getIs_wan() {
                return is_wan;
            }

            public void setIs_wan(String is_wan) {
                this.is_wan = is_wan;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getStime() {
                return stime;
            }

            public void setStime(String stime) {
                this.stime = stime;
            }

            public String getWtime1() {
                return wtime1;
            }

            public void setWtime1(String wtime1) {
                this.wtime1 = wtime1;
            }

            public String getWtime2() {
                return wtime2;
            }

            public void setWtime2(String wtime2) {
                this.wtime2 = wtime2;
            }

            public String getWtime3() {
                return wtime3;
            }

            public void setWtime3(String wtime3) {
                this.wtime3 = wtime3;
            }
        }

        public static class ProblemwBean implements Serializable{
            @SerializedName("double")
            private String doubleX;
            private String id;
            private String pid;
            private String remark;
            private String type;
            private String type1;
            private String type2;
            private String value;
            private String yremark;
            private String yvalue;
            private List<ProblemBean> problem;

            public String getDoubleX() {
                return doubleX;
            }

            public void setDoubleX(String doubleX) {
                this.doubleX = doubleX;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getType1() {
                return type1;
            }

            public void setType1(String type1) {
                this.type1 = type1;
            }

            public String getType2() {
                return type2;
            }

            public void setType2(String type2) {
                this.type2 = type2;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getYremark() {
                return yremark;
            }

            public void setYremark(String yremark) {
                this.yremark = yremark;
            }

            public String getYvalue() {
                return yvalue;
            }

            public void setYvalue(String yvalue) {
                this.yvalue = yvalue;
            }

            public List<ProblemBean> getProblem() {
                return problem;
            }

            public void setProblem(List<ProblemBean> problem) {
                this.problem = problem;
            }
        }

        public static class ProblemBean implements Serializable{
            @SerializedName("double")
            private String doubleX;
            private String id;
            private String pid;
            private String remark;
            private String type;
            private String type1;
            private String type2;
            private String value;
            private String yremark;
            private String yvalue;
            private String zhi;

            public String getDoubleX() {
                return doubleX;
            }

            public void setDoubleX(String doubleX) {
                this.doubleX = doubleX;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getType1() {
                return type1;
            }

            public void setType1(String type1) {
                this.type1 = type1;
            }

            public String getType2() {
                return type2;
            }

            public void setType2(String type2) {
                this.type2 = type2;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getYremark() {
                return yremark;
            }

            public void setYremark(String yremark) {
                this.yremark = yremark;
            }

            public String getYvalue() {
                return yvalue;
            }

            public void setYvalue(String yvalue) {
                this.yvalue = yvalue;
            }

            public String getZhi() {
                return zhi;
            }

            public void setZhi(String zhi) {
                this.zhi = zhi;
            }
        }
    }
}
