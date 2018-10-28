package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;
import com.haiwai.housekeeper.activity.user.SelfSupportManageScheme;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ihope006 on 2016/11/22.
 */

public class ZlOrderEntity implements Serializable{

    /**
     * data : {"bill":{"ctime":"1479269540","grent":"0","h_id":"22","home_remark":"","id":"3","is_da":"0","is_static":"0","monthtime":"1480521600","order_id":"20161116147926954078930","price_remark":"","rent":"0","tenant_remark":"","tenant_static":"0","uid":"2","wrent":"0","wtime1":"0","wtime2":"0","wtime3":"0"},"bill_month":[{"ctime":"1481877647","grent":"0","h_id":"15","home_remark":"","id":"8","is_da":"0","is_static":"0","monthtime":"1485878399","order_id":"20161216148187764766675","price_remark":"","rent":"0","tenant_remark":"","tenant_static":"0","uid":"28","wrent":"0","wtime1":"0","wtime2":"0","wtime3":"0"}],"date":{"ctime":"1481877647","emonthtime":"0","h_id":"15","id":"65","is_que":"0","j_num":"0","j_uid":"3","math_num":"1","money":"0","monthtime":"1485878399","num":"0","order_id":"20161216148187764766675","sid":"11","static":"4","type":"31","uid":"28","wen1":"69","wen2":"72","wen3":"0","wen4":"0","wen5":"0","wen6":"0"},"hous":{"address_info":"Guuii","alternate_contact":"12312312311","area":"9","city":"1","contact_number":"123456","contacts":"Guus","country":"22","ctime":"1481688066","email":"12456@qq.com","housing_type":"9","id":"15","is_bill":"0","is_del":"0","lat":"116.404017","long":"39.915239","province":"23","uid":"28"},"problemw":[{"double":"0.00","id":"54","pid":"0","problem":[{"double":"0.00","id":"69","pid":"68","remark":"","type":"4","type1":"2","type2":"1","value":"我需要租赁管理服务","yremark":"","yvalue":"","zhi":""}],"remark":"招租是一次性服务，在确认需求后协助您通过最权威的渠道找到合适的租户（招租取费为1个月房租，在租赁协议签订后收取）","type":"4","type1":"1","type2":"1","value":"招租服务","yremark":"","yvalue":""},{"double":"0.00","id":"57","pid":"0","problem":[{"double":"0.00","id":"72","pid":"71","remark":"","type":"4","type1":"2","type2":"1","value":"取信留存","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"4","type1":"1","type2":"1","value":"想出租房屋的哪些部分","yremark":"","yvalue":""},{"double":"0.00","id":"65","pid":"0","problem":[],"remark":"","type":"4","type1":"1","type2":"1","value":"您意向的租金价格是多少？","yremark":"","yvalue":""},{"double":"0.00","id":"68","pid":"0","problem":[],"remark":"招租完成后，您可选择由最权威团队提供的长期租赁管理服务，服务内容如下：向租客收租，收租信息通过客户端推送给客户记录租客及房屋状况并通过客户端反馈给客户（包括维修记录，发生费用、租户状态等）确认租金转至客户账户后通过客户端传达客户（租赁管理取费为每月房屋租金的8%)","type":"4","type1":"1","type2":"1","value":"租赁管理","yremark":"","yvalue":""},{"double":"0.00","id":"71","pid":"0","problem":[],"remark":"","type":"4","type1":"1","type2":"1","value":"我们提供每月一次取信服务，请问您需要怎样处理您的信件？","yremark":"","yvalue":""}],"user":{"address":"","avatar":"http://hwgj.zai0312.com/Uploads/2016-12-14/5850c3bad36cf.png","balance":"0.05","ctime":"0","fans_num":"1","follow_num":"1","introduction":"Djjjee","is_don":"0","is_hou":"0","is_ren":"1","lat":"40.048318","long":"116.29433","ltime":"1487585614","mobile":"13146735367","name":"Cdee","nickname":"Ndjjsdjeje","only_label":"","pro_lat":"","pro_long":"","pro_onum":"11","pro_quci":"0","pro_score":"0","pro_xing":"55","registrationid":"","sex":"0","system":"0","uid":"28","user_onum":"9","user_quci":"0","user_score":"0","user_xing":"45","version":"2"}}
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

    public static class DataBean implements Serializable{
        /**
         * bill : {"ctime":"1479269540","grent":"0","h_id":"22","home_remark":"","id":"3","is_da":"0","is_static":"0","monthtime":"1480521600","order_id":"20161116147926954078930","price_remark":"","rent":"0","tenant_remark":"","tenant_static":"0","uid":"2","wrent":"0","wtime1":"0","wtime2":"0","wtime3":"0"}
         * bill_month : [{"ctime":"1481877647","grent":"0","h_id":"15","home_remark":"","id":"8","is_da":"0","is_static":"0","monthtime":"1485878399","order_id":"20161216148187764766675","price_remark":"","rent":"0","tenant_remark":"","tenant_static":"0","uid":"28","wrent":"0","wtime1":"0","wtime2":"0","wtime3":"0"}]
         * date : {"ctime":"1481877647","emonthtime":"0","h_id":"15","id":"65","is_que":"0","j_num":"0","j_uid":"3","math_num":"1","money":"0","monthtime":"1485878399","num":"0","order_id":"20161216148187764766675","sid":"11","static":"4","type":"31","uid":"28","wen1":"69","wen2":"72","wen3":"0","wen4":"0","wen5":"0","wen6":"0"}
         * hous : {"address_info":"Guuii","alternate_contact":"12312312311","area":"9","city":"1","contact_number":"123456","contacts":"Guus","country":"22","ctime":"1481688066","email":"12456@qq.com","housing_type":"9","id":"15","is_bill":"0","is_del":"0","lat":"116.404017","long":"39.915239","province":"23","uid":"28"}
         * problemw : [{"double":"0.00","id":"54","pid":"0","problem":[{"double":"0.00","id":"69","pid":"68","remark":"","type":"4","type1":"2","type2":"1","value":"我需要租赁管理服务","yremark":"","yvalue":"","zhi":""}],"remark":"招租是一次性服务，在确认需求后协助您通过最权威的渠道找到合适的租户（招租取费为1个月房租，在租赁协议签订后收取）","type":"4","type1":"1","type2":"1","value":"招租服务","yremark":"","yvalue":""},{"double":"0.00","id":"57","pid":"0","problem":[{"double":"0.00","id":"72","pid":"71","remark":"","type":"4","type1":"2","type2":"1","value":"取信留存","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"4","type1":"1","type2":"1","value":"想出租房屋的哪些部分","yremark":"","yvalue":""},{"double":"0.00","id":"65","pid":"0","problem":[],"remark":"","type":"4","type1":"1","type2":"1","value":"您意向的租金价格是多少？","yremark":"","yvalue":""},{"double":"0.00","id":"68","pid":"0","problem":[],"remark":"招租完成后，您可选择由最权威团队提供的长期租赁管理服务，服务内容如下：向租客收租，收租信息通过客户端推送给客户记录租客及房屋状况并通过客户端反馈给客户（包括维修记录，发生费用、租户状态等）确认租金转至客户账户后通过客户端传达客户（租赁管理取费为每月房屋租金的8%)","type":"4","type1":"1","type2":"1","value":"租赁管理","yremark":"","yvalue":""},{"double":"0.00","id":"71","pid":"0","problem":[],"remark":"","type":"4","type1":"1","type2":"1","value":"我们提供每月一次取信服务，请问您需要怎样处理您的信件？","yremark":"","yvalue":""}]
         * user : {"address":"","avatar":"http://hwgj.zai0312.com/Uploads/2016-12-14/5850c3bad36cf.png","balance":"0.05","ctime":"0","fans_num":"1","follow_num":"1","introduction":"Djjjee","is_don":"0","is_hou":"0","is_ren":"1","lat":"40.048318","long":"116.29433","ltime":"1487585614","mobile":"13146735367","name":"Cdee","nickname":"Ndjjsdjeje","only_label":"","pro_lat":"","pro_long":"","pro_onum":"11","pro_quci":"0","pro_score":"0","pro_xing":"55","registrationid":"","sex":"0","system":"0","uid":"28","user_onum":"9","user_quci":"0","user_score":"0","user_xing":"45","version":"2"}
         */

        private BillBean bill;
        private DateBean date;
        private HousBean hous;
        private UserBean user;
        private List<BillMonthBean> bill_month;
        private List<ProblemwBean> problemw;

        public BillBean getBill() {
            return bill;
        }

        public void setBill(BillBean bill) {
            this.bill = bill;
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

        public List<BillMonthBean> getBill_month() {
            return bill_month;
        }

        public void setBill_month(List<BillMonthBean> bill_month) {
            this.bill_month = bill_month;
        }

        public List<ProblemwBean> getProblemw() {
            return problemw;
        }

        public void setProblemw(List<ProblemwBean> problemw) {
            this.problemw = problemw;
        }

        public static class BillBean implements Serializable{
            /**
             * ctime : 1479269540
             * grent : 0
             * h_id : 22
             * home_remark :
             * id : 3
             * is_da : 0
             * is_static : 0
             * monthtime : 1480521600
             * order_id : 20161116147926954078930
             * price_remark :
             * rent : 0
             * tenant_remark :
             * tenant_static : 0
             * uid : 2
             * wrent : 0
             * wtime1 : 0
             * wtime2 : 0
             * wtime3 : 0
             */

            private String ctime;
            private String grent;
            private String h_id;
            private String home_remark;
            private String id;
            private String is_da;
            private String is_static;
            private String monthtime;
            private String order_id;
            private String price_remark;
            private String rent;
            private String tenant_remark;
            private String tenant_static;
            private String uid;
            private String wrent;
            private String wtime1;
            private String wtime2;
            private String wtime3;

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getGrent() {
                return grent;
            }

            public void setGrent(String grent) {
                this.grent = grent;
            }

            public String getH_id() {
                return h_id;
            }

            public void setH_id(String h_id) {
                this.h_id = h_id;
            }

            public String getHome_remark() {
                return home_remark;
            }

            public void setHome_remark(String home_remark) {
                this.home_remark = home_remark;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIs_da() {
                return is_da;
            }

            public void setIs_da(String is_da) {
                this.is_da = is_da;
            }

            public String getIs_static() {
                return is_static;
            }

            public void setIs_static(String is_static) {
                this.is_static = is_static;
            }

            public String getMonthtime() {
                return monthtime;
            }

            public void setMonthtime(String monthtime) {
                this.monthtime = monthtime;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getPrice_remark() {
                return price_remark;
            }

            public void setPrice_remark(String price_remark) {
                this.price_remark = price_remark;
            }

            public String getRent() {
                return rent;
            }

            public void setRent(String rent) {
                this.rent = rent;
            }

            public String getTenant_remark() {
                return tenant_remark;
            }

            public void setTenant_remark(String tenant_remark) {
                this.tenant_remark = tenant_remark;
            }

            public String getTenant_static() {
                return tenant_static;
            }

            public void setTenant_static(String tenant_static) {
                this.tenant_static = tenant_static;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getWrent() {
                return wrent;
            }

            public void setWrent(String wrent) {
                this.wrent = wrent;
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

            @Override
            public String toString() {
                return "BillBean{" +
                        "ctime='" + ctime + '\'' +
                        ", grent='" + grent + '\'' +
                        ", h_id='" + h_id + '\'' +
                        ", home_remark='" + home_remark + '\'' +
                        ", id='" + id + '\'' +
                        ", is_da='" + is_da + '\'' +
                        ", is_static='" + is_static + '\'' +
                        ", monthtime='" + monthtime + '\'' +
                        ", order_id='" + order_id + '\'' +
                        ", price_remark='" + price_remark + '\'' +
                        ", rent='" + rent + '\'' +
                        ", tenant_remark='" + tenant_remark + '\'' +
                        ", tenant_static='" + tenant_static + '\'' +
                        ", uid='" + uid + '\'' +
                        ", wrent='" + wrent + '\'' +
                        ", wtime1='" + wtime1 + '\'' +
                        ", wtime2='" + wtime2 + '\'' +
                        ", wtime3='" + wtime3 + '\'' +
                        '}';
            }
        }

        public static class DateBean implements Serializable{
            /**
             * ctime : 1481877647
             * emonthtime : 0
             * h_id : 15
             * id : 65
             * is_que : 0
             * j_num : 0
             * j_uid : 3
             * math_num : 1
             * money : 0
             * monthtime : 1485878399
             * num : 0
             * order_id : 20161216148187764766675
             * sid : 11
             * static : 4
             * type : 31
             * uid : 28
             * wen1 : 69
             * wen2 : 72
             * wen3 : 0
             * wen4 : 0
             * wen5 : 0
             * wen6 : 0
             */

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

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getEmonthtime() {
                return emonthtime;
            }

            public void setEmonthtime(String emonthtime) {
                this.emonthtime = emonthtime;
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
            /**
             * address_info : Guuii
             * alternate_contact : 12312312311
             * area : 9
             * city : 1
             * contact_number : 123456
             * contacts : Guus
             * country : 22
             * ctime : 1481688066
             * email : 12456@qq.com
             * housing_type : 9
             * id : 15
             * is_bill : 0
             * is_del : 0
             * lat : 116.404017
             * long : 39.915239
             * province : 23
             * uid : 28
             */

            private String address_info;
            private String alternate_contact;
            private String area;
            private String city;
            private String contact_number;
            private String contacts;
            private String country;
            private String ctime;
            private String email;
            private String housing_type;
            private String alternate_contact_number;
            private String id;
            private String is_bill;
            private String is_del;
            private String lat;
            @SerializedName("long")
            private String longX;
            private String province;
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

        public static class UserBean implements Serializable{
            /**
             * address :
             * avatar : http://hwgj.zai0312.com/Uploads/2016-12-14/5850c3bad36cf.png
             * balance : 0.05
             * ctime : 0
             * fans_num : 1
             * follow_num : 1
             * introduction : Djjjee
             * is_don : 0
             * is_hou : 0
             * is_ren : 1
             * lat : 40.048318
             * long : 116.29433
             * ltime : 1487585614
             * mobile : 13146735367
             * name : Cdee
             * nickname : Ndjjsdjeje
             * only_label :
             * pro_lat :
             * pro_long :
             * pro_onum : 11
             * pro_quci : 0
             * pro_score : 0
             * pro_xing : 55
             * registrationid :
             * sex : 0
             * system : 0
             * uid : 28
             * user_onum : 9
             * user_quci : 0
             * user_score : 0
             * user_xing : 45
             * version : 2
             */

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
            private String pro_lat;
            private String pro_long;
            private String pro_onum;
            private String pro_quci;
            private String pro_score;
            private String pro_xing;
            private String registrationid;
            private String sex;
            private String system;
            private String uid;
            private String user_onum;
            private String user_quci;
            private String user_score;
            private String user_xing;
            private String version;

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

            public String getIs_don() {
                return is_don;
            }

            public void setIs_don(String is_don) {
                this.is_don = is_don;
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

            public String getPro_lat() {
                return pro_lat;
            }

            public void setPro_lat(String pro_lat) {
                this.pro_lat = pro_lat;
            }

            public String getPro_long() {
                return pro_long;
            }

            public void setPro_long(String pro_long) {
                this.pro_long = pro_long;
            }

            public String getPro_onum() {
                return pro_onum;
            }

            public void setPro_onum(String pro_onum) {
                this.pro_onum = pro_onum;
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

            public String getPro_xing() {
                return pro_xing;
            }

            public void setPro_xing(String pro_xing) {
                this.pro_xing = pro_xing;
            }

            public String getRegistrationid() {
                return registrationid;
            }

            public void setRegistrationid(String registrationid) {
                this.registrationid = registrationid;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getSystem() {
                return system;
            }

            public void setSystem(String system) {
                this.system = system;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getUser_onum() {
                return user_onum;
            }

            public void setUser_onum(String user_onum) {
                this.user_onum = user_onum;
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

            public String getUser_xing() {
                return user_xing;
            }

            public void setUser_xing(String user_xing) {
                this.user_xing = user_xing;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }
        }

        public static class BillMonthBean implements Serializable{
            /**
             * ctime : 1481877647
             * grent : 0
             * h_id : 15
             * home_remark :
             * id : 8
             * is_da : 0
             * is_static : 0
             * monthtime : 1485878399
             * order_id : 20161216148187764766675
             * price_remark :
             * rent : 0
             * tenant_remark :
             * tenant_static : 0
             * uid : 28
             * wrent : 0
             * wtime1 : 0
             * wtime2 : 0
             * wtime3 : 0
             */

            private String ctime;
            private String grent;
            private String h_id;
            private String home_remark;
            private String id;
            private String is_da;
            private String is_static;
            private String monthtime;
            private String order_id;
            private String price_remark;
            private String rent;
            private String tenant_remark;
            private String tenant_static;
            private String uid;
            private String wrent;
            private String wtime1;
            private String wtime2;
            private String wtime3;

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getGrent() {
                return grent;
            }

            public void setGrent(String grent) {
                this.grent = grent;
            }

            public String getH_id() {
                return h_id;
            }

            public void setH_id(String h_id) {
                this.h_id = h_id;
            }

            public String getHome_remark() {
                return home_remark;
            }

            public void setHome_remark(String home_remark) {
                this.home_remark = home_remark;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIs_da() {
                return is_da;
            }

            public void setIs_da(String is_da) {
                this.is_da = is_da;
            }

            public String getIs_static() {
                return is_static;
            }

            public void setIs_static(String is_static) {
                this.is_static = is_static;
            }

            public String getMonthtime() {
                return monthtime;
            }

            public void setMonthtime(String monthtime) {
                this.monthtime = monthtime;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getPrice_remark() {
                return price_remark;
            }

            public void setPrice_remark(String price_remark) {
                this.price_remark = price_remark;
            }

            public String getRent() {
                return rent;
            }

            public void setRent(String rent) {
                this.rent = rent;
            }

            public String getTenant_remark() {
                return tenant_remark;
            }

            public void setTenant_remark(String tenant_remark) {
                this.tenant_remark = tenant_remark;
            }

            public String getTenant_static() {
                return tenant_static;
            }

            public void setTenant_static(String tenant_static) {
                this.tenant_static = tenant_static;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getWrent() {
                return wrent;
            }

            public void setWrent(String wrent) {
                this.wrent = wrent;
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
            /**
             * double : 0.00
             * id : 54
             * pid : 0
             * problem : [{"double":"0.00","id":"69","pid":"68","remark":"","type":"4","type1":"2","type2":"1","value":"我需要租赁管理服务","yremark":"","yvalue":"","zhi":""}]
             * remark : 招租是一次性服务，在确认需求后协助您通过最权威的渠道找到合适的租户（招租取费为1个月房租，在租赁协议签订后收取）
             * type : 4
             * type1 : 1
             * type2 : 1
             * value : 招租服务
             * yremark :
             * yvalue :
             */

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

            public static class ProblemBean {
                /**
                 * double : 0.00
                 * id : 69
                 * pid : 68
                 * remark :
                 * type : 4
                 * type1 : 2
                 * type2 : 1
                 * value : 我需要租赁管理服务
                 * yremark :
                 * yvalue :
                 * zhi :
                 */

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
}
