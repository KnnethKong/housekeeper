package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ihope10 on 2016/11/9.
 */

public class OrderWeekEntity {


    /**
     * data : {"date":{"ctime":"1479269455","h_id":"30","id":"1","is_que":"0","j_num":"1","j_uid":"3","math_num":"1","money":"40","monthtime":"1480521600","num":"2","order_id":"20161116147926945579900","sid":"1","static":"4","type":"1","uid":"6"},"feedback":[{"content1":"测试测试测试","content2":"","content3":"","ctime":"1479269455","id":"1","images1":"[]","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"1","order_id":"20161116147926945579900","stime":"1481731200","wtime1":"1479571200","wtime2":"0","wtime3":"0"},{"content1":"1","content2":"","content3":"","ctime":"1479269455","id":"2","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"2","order_id":"20161116147926945579900","stime":"1483027200","wtime1":"0","wtime2":"0","wtime3":"0"}],"hous":{"address_info":"分居老K","alternate_contact":"13245784679","built_area":"100","cement_area":"30","city":"北京","ctime":"1478498947","green_area":"30","housing_type":"别墅","id":"30","is_del":"0","land_area":"200","lat":"116.3192876558","long":"40.0592732065","uid":"6"},"offer":{"ctime":"1479695433","id":"8","is_jie":1,"is_xuan":"0","message":"","oid":"1","uid":"3"},"user":{"address":"","avatar":"","balance":"0","ctime":"1476428088","fans_num":"0","follow_num":"0","introduction":"","is_hou":"0","is_ren":"0","lat":"40.048094","long":"116.294301","ltime":"1479463122","mobile":"13261089009","name":"","nickname":"","only_label":"77184","pro_quci":"0","pro_score":"0","sex":"0","uid":"6","user_quci":"0","user_score":"0"}}
     * status : 200
     */

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

    public static class DataBean {
        /**
         * date : {"ctime":"1479269455","h_id":"30","id":"1","is_que":"0","j_num":"1","j_uid":"3","math_num":"1","money":"40","monthtime":"1480521600","num":"2","order_id":"20161116147926945579900","sid":"1","static":"4","type":"1","uid":"6"}
         * feedback : [{"content1":"测试测试测试","content2":"","content3":"","ctime":"1479269455","id":"1","images1":"[]","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"1","order_id":"20161116147926945579900","stime":"1481731200","wtime1":"1479571200","wtime2":"0","wtime3":"0"},{"content1":"1","content2":"","content3":"","ctime":"1479269455","id":"2","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"2","order_id":"20161116147926945579900","stime":"1483027200","wtime1":"0","wtime2":"0","wtime3":"0"}]
         * hous : {"address_info":"分居老K","alternate_contact":"13245784679","built_area":"100","cement_area":"30","city":"北京","ctime":"1478498947","green_area":"30","housing_type":"别墅","id":"30","is_del":"0","land_area":"200","lat":"116.3192876558","long":"40.0592732065","uid":"6"}
         * offer : {"ctime":"1479695433","id":"8","is_jie":1,"is_xuan":"0","message":"","oid":"1","uid":"3"}
         * user : {"address":"","avatar":"","balance":"0","ctime":"1476428088","fans_num":"0","follow_num":"0","introduction":"","is_hou":"0","is_ren":"0","lat":"40.048094","long":"116.294301","ltime":"1479463122","mobile":"13261089009","name":"","nickname":"","only_label":"77184","pro_quci":"0","pro_score":"0","sex":"0","uid":"6","user_quci":"0","user_score":"0"}
         */

        private DateBean date;
        private HousBean hous;
        private OfferBean offer;
        private UserBean user;
        private List<FeedbackBean> feedback;

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

        public static class DateBean {
            /**
             * ctime : 1479269455
             * h_id : 30
             * id : 1
             * is_que : 0
             * j_num : 1
             * j_uid : 3
             * math_num : 1
             * money : 40
             * monthtime : 1480521600
             * num : 2
             * order_id : 20161116147926945579900
             * sid : 1
             * static : 4
             * type : 1
             * uid : 6
             */

            private String ctime;
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
        }

        public static class HousBean {

            private String address_info;
            private String alternate_contact;
            private String built_area;
            private String cement_area;
            private String city;
            private String ctime;
            private String green_area;
            private String housing_type;
            private String id;
            private String is_del;
            private String land_area;
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

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
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

        public static class OfferBean {
            /**
             * ctime : 1479695433
             * id : 8
             * is_jie : 1
             * is_xuan : 0
             * message :
             * oid : 1
             * uid : 3
             */

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

        public static class UserBean {
            /**
             * address :
             * avatar :
             * balance : 0
             * ctime : 1476428088
             * fans_num : 0
             * follow_num : 0
             * introduction :
             * is_hou : 0
             * is_ren : 0
             * lat : 40.048094
             * long : 116.294301
             * ltime : 1479463122
             * mobile : 13261089009
             * name :
             * nickname :
             * only_label : 77184
             * pro_quci : 0
             * pro_score : 0
             * sex : 0
             * uid : 6
             * user_quci : 0
             * user_score : 0
             */

            private String address;
            private String avatar;
            private String balance;
            private String ctime;
            private String fans_num;
            private String follow_num;
            private String introduction;
            private String is_hou;
            private String is_ren;
            private String lat;
            @SerializedName("long")
            private String longX;
            private String ltime;
            private String mobile;
            private String name;
            private String nickname;
            private String only_label;
            private String pro_quci;
            private String pro_score;
            private String sex;
            private String uid;
            private String user_quci;
            private String user_score;

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
        }

        public static class FeedbackBean implements Serializable{
            /**
             * content1 : 测试测试测试
             * content2 :
             * content3 :
             * ctime : 1479269455
             * id : 1
             * images1 : []
             * images2 :
             * images3 :
             * is_new : 0
             * is_special : 0
             * is_wan : 0
             * number : 1
             * order_id : 20161116147926945579900
             * stime : 1481731200
             * wtime1 : 1479571200
             * wtime2 : 0
             * wtime3 : 0
             */

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
    }
}
