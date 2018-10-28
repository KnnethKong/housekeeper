package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ihope006 on 2017/5/23.
 */

public class DingEntity {

    /**
     * status : 200
     * data : {"date":[{"id":"486","uid":"97","h_id":"471","city":"17","k_uid":"0","k_xia":"1","k_static":"0","k_num":"1","k_wen1":"","k_wen2":"","k_wen3":"","k_wen4":"","y_uid":"0","y_xia":"1","y_static":"2","y_num":"3","y_leaf":"1.00","y_snow":"1.00","y_wen1":"29","y_wen2":"#110#10800","y_wen3":"","y_wen4":"44,45","y_wen5":"48","y_wen6":"52","j_uid":"0","j_xia":"1","j_static":"0","j_difficulty":"1.00","j_num":"1","j_wen1":"","j_wen2":"","j_wen3":"","j_wen4":"","z_uid":"0","z_xia":"1","z_static":"0","z_xstatic":"2","z_wen1":"","z_wen2":"","z_wen3":"","z_wen4":"","z_wen5":"","static":"1","ctime":"1495522036","is_bill":"1","is_que":"0","address_info":"8brighouse","housing_type":"5","is_xia":"1","k_proble":[],"y_proble":[{"id":"27","type":"3","type1":"1","type2":"1","double":"0.00","value":"对于草坪修剪我们有两种方案，您的选择是?","yvalue":"What kind of lawn care service would you like?","pid":"0","remark":"","yremark":"","problem":[{"id":"29","type":"3","type1":"2","type2":"1","double":"0.00","value":"维系方案，低耗维持，每月修剪1次以通过市政检查","yvalue":"Maintenance plan- low maintenance. The lawn will be mowed once a month for passing the municipal inspection.","pid":"27","remark":"","yremark":"","zhi":""}]},{"id":"31","type":"3","type1":"1","type2":"1","double":"0.00","value":"您的草坪有多大?","yvalue":"How large is the lawn?","pid":"0","remark":"","yremark":"","problem":[{"id":"110","type":"3","type1":"2","type2":"6","double":"0.00","value":"10000平方英尺以上#xia#请输入具体面积","yvalue":"10,000+ sq ft#xia#Please enter the square feet","pid":"31","remark":"","yremark":"","zhi":"10800"}]},{"id":"37","type":"3","type1":"1","type2":"3","double":"0.00","value":"我们还有其他园艺服务供您选择","yvalue":"Other gardening services for you (Multi-choice)","pid":"0","remark":"","yremark":"","problem":[]},{"id":"43","type":"3","type1":"1","type2":"3","double":"0.00","value":"不同季节我们还提供落叶清扫和清雪服务","yvalue":"Would you like leaf clean-up or snow plowing in different seasons?","pid":"0","remark":"","yremark":"","problem":[{"id":"44","type":"3","type1":"2","type2":"1","double":"0.00","value":"我需要落叶清扫服务（秋季9月-10月，隔周清扫）","yvalue":"I need leaf clean-up \r\n(Sep.-Oct. every other week)","pid":"43","remark":"","yremark":"","zhi":""},{"id":"45","type":"3","type1":"2","type2":"1","double":"0.00","value":"我需要清雪服务（冬季11月-3月，确保行人步道不积雪）","yvalue":"I need snow plowing（Nov.-Mar.The walkway will not be covered with snow.）","pid":"43","remark":"","yremark":"","zhi":""}]},{"id":"46","type":"3","type1":"1","type2":"1","double":"0.00","value":"需要清雪的人行步道有多大？","yvalue":"How large is the walkway ?","pid":"0","remark":"","yremark":"","problem":[{"id":"48","type":"3","type1":"2","type2":"1","double":"1.00","value":"中（面积有2-5辆小汽车般大）","yvalue":"Medium (fits 2 - 5 cars)","pid":"46","remark":"","yremark":"","zhi":""}]},{"id":"51","type":"3","type1":"1","type2":"1","double":"0.00","value":"庭院维护过程中你是否需要在场?","yvalue":"Do you need to be on site while the Pro is working?","pid":"0","remark":"","yremark":"","problem":[{"id":"52","type":"3","type1":"2","type2":"1","double":"0.00","value":"不需要，不介意我不在场的时候工作","yvalue":"No, I don't need to be there","pid":"51","remark":"","yremark":"","zhi":""}]}],"j_proble":[],"z_proble":[],"k_money":0,"j_money":0,"y_money":160,"k_month":1496300400,"y_month":1496300400,"j_month":1496300400,"z_month":1496300400,"money":"160.00"}],"balance":"3980.50"}
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

    public static class DataBean {
        /**
         * date : [{"id":"486","uid":"97","h_id":"471","city":"17","k_uid":"0","k_xia":"1","k_static":"0","k_num":"1","k_wen1":"","k_wen2":"","k_wen3":"","k_wen4":"","y_uid":"0","y_xia":"1","y_static":"2","y_num":"3","y_leaf":"1.00","y_snow":"1.00","y_wen1":"29","y_wen2":"#110#10800","y_wen3":"","y_wen4":"44,45","y_wen5":"48","y_wen6":"52","j_uid":"0","j_xia":"1","j_static":"0","j_difficulty":"1.00","j_num":"1","j_wen1":"","j_wen2":"","j_wen3":"","j_wen4":"","z_uid":"0","z_xia":"1","z_static":"0","z_xstatic":"2","z_wen1":"","z_wen2":"","z_wen3":"","z_wen4":"","z_wen5":"","static":"1","ctime":"1495522036","is_bill":"1","is_que":"0","address_info":"8brighouse","housing_type":"5","is_xia":"1","k_proble":[],"y_proble":[{"id":"27","type":"3","type1":"1","type2":"1","double":"0.00","value":"对于草坪修剪我们有两种方案，您的选择是?","yvalue":"What kind of lawn care service would you like?","pid":"0","remark":"","yremark":"","problem":[{"id":"29","type":"3","type1":"2","type2":"1","double":"0.00","value":"维系方案，低耗维持，每月修剪1次以通过市政检查","yvalue":"Maintenance plan- low maintenance. The lawn will be mowed once a month for passing the municipal inspection.","pid":"27","remark":"","yremark":"","zhi":""}]},{"id":"31","type":"3","type1":"1","type2":"1","double":"0.00","value":"您的草坪有多大?","yvalue":"How large is the lawn?","pid":"0","remark":"","yremark":"","problem":[{"id":"110","type":"3","type1":"2","type2":"6","double":"0.00","value":"10000平方英尺以上#xia#请输入具体面积","yvalue":"10,000+ sq ft#xia#Please enter the square feet","pid":"31","remark":"","yremark":"","zhi":"10800"}]},{"id":"37","type":"3","type1":"1","type2":"3","double":"0.00","value":"我们还有其他园艺服务供您选择","yvalue":"Other gardening services for you (Multi-choice)","pid":"0","remark":"","yremark":"","problem":[]},{"id":"43","type":"3","type1":"1","type2":"3","double":"0.00","value":"不同季节我们还提供落叶清扫和清雪服务","yvalue":"Would you like leaf clean-up or snow plowing in different seasons?","pid":"0","remark":"","yremark":"","problem":[{"id":"44","type":"3","type1":"2","type2":"1","double":"0.00","value":"我需要落叶清扫服务（秋季9月-10月，隔周清扫）","yvalue":"I need leaf clean-up \r\n(Sep.-Oct. every other week)","pid":"43","remark":"","yremark":"","zhi":""},{"id":"45","type":"3","type1":"2","type2":"1","double":"0.00","value":"我需要清雪服务（冬季11月-3月，确保行人步道不积雪）","yvalue":"I need snow plowing（Nov.-Mar.The walkway will not be covered with snow.）","pid":"43","remark":"","yremark":"","zhi":""}]},{"id":"46","type":"3","type1":"1","type2":"1","double":"0.00","value":"需要清雪的人行步道有多大？","yvalue":"How large is the walkway ?","pid":"0","remark":"","yremark":"","problem":[{"id":"48","type":"3","type1":"2","type2":"1","double":"1.00","value":"中（面积有2-5辆小汽车般大）","yvalue":"Medium (fits 2 - 5 cars)","pid":"46","remark":"","yremark":"","zhi":""}]},{"id":"51","type":"3","type1":"1","type2":"1","double":"0.00","value":"庭院维护过程中你是否需要在场?","yvalue":"Do you need to be on site while the Pro is working?","pid":"0","remark":"","yremark":"","problem":[{"id":"52","type":"3","type1":"2","type2":"1","double":"0.00","value":"不需要，不介意我不在场的时候工作","yvalue":"No, I don't need to be there","pid":"51","remark":"","yremark":"","zhi":""}]}],"j_proble":[],"z_proble":[],"k_money":0,"j_money":0,"y_money":160,"k_month":1496300400,"y_month":1496300400,"j_month":1496300400,"z_month":1496300400,"money":"160.00"}]
         * balance : 3980.50
         */

        private String balance;
        private List<DateBean> date;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public List<DateBean> getDate() {
            return date;
        }

        public void setDate(List<DateBean> date) {
            this.date = date;
        }

        public static class DateBean {
            /**
             * id : 486
             * uid : 97
             * h_id : 471
             * city : 17
             * k_uid : 0
             * k_xia : 1
             * k_static : 0
             * k_num : 1
             * k_wen1 :
             * k_wen2 :
             * k_wen3 :
             * k_wen4 :
             * y_uid : 0
             * y_xia : 1
             * y_static : 2
             * y_num : 3
             * y_leaf : 1.00
             * y_snow : 1.00
             * y_wen1 : 29
             * y_wen2 : #110#10800
             * y_wen3 :
             * y_wen4 : 44,45
             * y_wen5 : 48
             * y_wen6 : 52
             * j_uid : 0
             * j_xia : 1
             * j_static : 0
             * j_difficulty : 1.00
             * j_num : 1
             * j_wen1 :
             * j_wen2 :
             * j_wen3 :
             * j_wen4 :
             * z_uid : 0
             * z_xia : 1
             * z_static : 0
             * z_xstatic : 2
             * z_wen1 :
             * z_wen2 :
             * z_wen3 :
             * z_wen4 :
             * z_wen5 :
             * static : 1
             * ctime : 1495522036
             * is_bill : 1
             * is_que : 0
             * address_info : 8brighouse
             * housing_type : 5
             * is_xia : 1
             * k_proble : []
             * y_proble : [{"id":"27","type":"3","type1":"1","type2":"1","double":"0.00","value":"对于草坪修剪我们有两种方案，您的选择是?","yvalue":"What kind of lawn care service would you like?","pid":"0","remark":"","yremark":"","problem":[{"id":"29","type":"3","type1":"2","type2":"1","double":"0.00","value":"维系方案，低耗维持，每月修剪1次以通过市政检查","yvalue":"Maintenance plan- low maintenance. The lawn will be mowed once a month for passing the municipal inspection.","pid":"27","remark":"","yremark":"","zhi":""}]},{"id":"31","type":"3","type1":"1","type2":"1","double":"0.00","value":"您的草坪有多大?","yvalue":"How large is the lawn?","pid":"0","remark":"","yremark":"","problem":[{"id":"110","type":"3","type1":"2","type2":"6","double":"0.00","value":"10000平方英尺以上#xia#请输入具体面积","yvalue":"10,000+ sq ft#xia#Please enter the square feet","pid":"31","remark":"","yremark":"","zhi":"10800"}]},{"id":"37","type":"3","type1":"1","type2":"3","double":"0.00","value":"我们还有其他园艺服务供您选择","yvalue":"Other gardening services for you (Multi-choice)","pid":"0","remark":"","yremark":"","problem":[]},{"id":"43","type":"3","type1":"1","type2":"3","double":"0.00","value":"不同季节我们还提供落叶清扫和清雪服务","yvalue":"Would you like leaf clean-up or snow plowing in different seasons?","pid":"0","remark":"","yremark":"","problem":[{"id":"44","type":"3","type1":"2","type2":"1","double":"0.00","value":"我需要落叶清扫服务（秋季9月-10月，隔周清扫）","yvalue":"I need leaf clean-up \r\n(Sep.-Oct. every other week)","pid":"43","remark":"","yremark":"","zhi":""},{"id":"45","type":"3","type1":"2","type2":"1","double":"0.00","value":"我需要清雪服务（冬季11月-3月，确保行人步道不积雪）","yvalue":"I need snow plowing（Nov.-Mar.The walkway will not be covered with snow.）","pid":"43","remark":"","yremark":"","zhi":""}]},{"id":"46","type":"3","type1":"1","type2":"1","double":"0.00","value":"需要清雪的人行步道有多大？","yvalue":"How large is the walkway ?","pid":"0","remark":"","yremark":"","problem":[{"id":"48","type":"3","type1":"2","type2":"1","double":"1.00","value":"中（面积有2-5辆小汽车般大）","yvalue":"Medium (fits 2 - 5 cars)","pid":"46","remark":"","yremark":"","zhi":""}]},{"id":"51","type":"3","type1":"1","type2":"1","double":"0.00","value":"庭院维护过程中你是否需要在场?","yvalue":"Do you need to be on site while the Pro is working?","pid":"0","remark":"","yremark":"","problem":[{"id":"52","type":"3","type1":"2","type2":"1","double":"0.00","value":"不需要，不介意我不在场的时候工作","yvalue":"No, I don't need to be there","pid":"51","remark":"","yremark":"","zhi":""}]}]
             * j_proble : []
             * z_proble : []
             * k_money : 0
             * j_money : 0
             * y_money : 160
             * k_month : 1496300400
             * y_month : 1496300400
             * j_month : 1496300400
             * z_month : 1496300400
             * money : 160.00
             */

            private String id;
            private String uid;
            private String h_id;
            private String city;
            private String k_uid;
            private String k_xia;
            private String k_static;
            private String k_num;
            private String k_wen1;
            private String k_wen2;
            private String k_wen3;
            private String k_wen4;
            private String y_uid;
            private String y_xia;
            private String y_static;
            private String y_num;
            private String y_leaf;
            private String y_snow;
            private String y_wen1;
            private String y_wen2;
            private String y_wen3;
            private String y_wen4;
            private String y_wen5;
            private String y_wen6;
            private String j_uid;
            private String j_xia;
            private String j_static;
            private String j_difficulty;
            private String j_num;
            private String j_wen1;
            private String j_wen2;
            private String j_wen3;
            private String j_wen4;
            private String z_uid;
            private String z_xia;
            private String z_static;
            private String z_xstatic;
            private String z_wen1;
            private String z_wen2;
            private String z_wen3;
            private String z_wen4;
            private String z_wen5;
            @SerializedName("static")
            private String staticX;
            private String ctime;
            private String is_bill;
            private String is_que;
            private String address_info;
            private String housing_type;
            private String is_xia;
            private int k_money;
            private int j_money;
            private float y_money;
            private int k_month;
            private int y_month;
            private int j_month;
            private int z_month;
            private String money;
            private List<?> k_proble;
            private List<YProbleBean> y_proble;
            private List<?> j_proble;
            private List<?> z_proble;

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

            public String getK_uid() {
                return k_uid;
            }

            public void setK_uid(String k_uid) {
                this.k_uid = k_uid;
            }

            public String getK_xia() {
                return k_xia;
            }

            public void setK_xia(String k_xia) {
                this.k_xia = k_xia;
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

            public String getK_wen1() {
                return k_wen1;
            }

            public void setK_wen1(String k_wen1) {
                this.k_wen1 = k_wen1;
            }

            public String getK_wen2() {
                return k_wen2;
            }

            public void setK_wen2(String k_wen2) {
                this.k_wen2 = k_wen2;
            }

            public String getK_wen3() {
                return k_wen3;
            }

            public void setK_wen3(String k_wen3) {
                this.k_wen3 = k_wen3;
            }

            public String getK_wen4() {
                return k_wen4;
            }

            public void setK_wen4(String k_wen4) {
                this.k_wen4 = k_wen4;
            }

            public String getY_uid() {
                return y_uid;
            }

            public void setY_uid(String y_uid) {
                this.y_uid = y_uid;
            }

            public String getY_xia() {
                return y_xia;
            }

            public void setY_xia(String y_xia) {
                this.y_xia = y_xia;
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

            public String getY_leaf() {
                return y_leaf;
            }

            public void setY_leaf(String y_leaf) {
                this.y_leaf = y_leaf;
            }

            public String getY_snow() {
                return y_snow;
            }

            public void setY_snow(String y_snow) {
                this.y_snow = y_snow;
            }

            public String getY_wen1() {
                return y_wen1;
            }

            public void setY_wen1(String y_wen1) {
                this.y_wen1 = y_wen1;
            }

            public String getY_wen2() {
                return y_wen2;
            }

            public void setY_wen2(String y_wen2) {
                this.y_wen2 = y_wen2;
            }

            public String getY_wen3() {
                return y_wen3;
            }

            public void setY_wen3(String y_wen3) {
                this.y_wen3 = y_wen3;
            }

            public String getY_wen4() {
                return y_wen4;
            }

            public void setY_wen4(String y_wen4) {
                this.y_wen4 = y_wen4;
            }

            public String getY_wen5() {
                return y_wen5;
            }

            public void setY_wen5(String y_wen5) {
                this.y_wen5 = y_wen5;
            }

            public String getY_wen6() {
                return y_wen6;
            }

            public void setY_wen6(String y_wen6) {
                this.y_wen6 = y_wen6;
            }

            public String getJ_uid() {
                return j_uid;
            }

            public void setJ_uid(String j_uid) {
                this.j_uid = j_uid;
            }

            public String getJ_xia() {
                return j_xia;
            }

            public void setJ_xia(String j_xia) {
                this.j_xia = j_xia;
            }

            public String getJ_static() {
                return j_static;
            }

            public void setJ_static(String j_static) {
                this.j_static = j_static;
            }

            public String getJ_difficulty() {
                return j_difficulty;
            }

            public void setJ_difficulty(String j_difficulty) {
                this.j_difficulty = j_difficulty;
            }

            public String getJ_num() {
                return j_num;
            }

            public void setJ_num(String j_num) {
                this.j_num = j_num;
            }

            public String getJ_wen1() {
                return j_wen1;
            }

            public void setJ_wen1(String j_wen1) {
                this.j_wen1 = j_wen1;
            }

            public String getJ_wen2() {
                return j_wen2;
            }

            public void setJ_wen2(String j_wen2) {
                this.j_wen2 = j_wen2;
            }

            public String getJ_wen3() {
                return j_wen3;
            }

            public void setJ_wen3(String j_wen3) {
                this.j_wen3 = j_wen3;
            }

            public String getJ_wen4() {
                return j_wen4;
            }

            public void setJ_wen4(String j_wen4) {
                this.j_wen4 = j_wen4;
            }

            public String getZ_uid() {
                return z_uid;
            }

            public void setZ_uid(String z_uid) {
                this.z_uid = z_uid;
            }

            public String getZ_xia() {
                return z_xia;
            }

            public void setZ_xia(String z_xia) {
                this.z_xia = z_xia;
            }

            public String getZ_static() {
                return z_static;
            }

            public void setZ_static(String z_static) {
                this.z_static = z_static;
            }

            public String getZ_xstatic() {
                return z_xstatic;
            }

            public void setZ_xstatic(String z_xstatic) {
                this.z_xstatic = z_xstatic;
            }

            public String getZ_wen1() {
                return z_wen1;
            }

            public void setZ_wen1(String z_wen1) {
                this.z_wen1 = z_wen1;
            }

            public String getZ_wen2() {
                return z_wen2;
            }

            public void setZ_wen2(String z_wen2) {
                this.z_wen2 = z_wen2;
            }

            public String getZ_wen3() {
                return z_wen3;
            }

            public void setZ_wen3(String z_wen3) {
                this.z_wen3 = z_wen3;
            }

            public String getZ_wen4() {
                return z_wen4;
            }

            public void setZ_wen4(String z_wen4) {
                this.z_wen4 = z_wen4;
            }

            public String getZ_wen5() {
                return z_wen5;
            }

            public void setZ_wen5(String z_wen5) {
                this.z_wen5 = z_wen5;
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

            public String getIs_bill() {
                return is_bill;
            }

            public void setIs_bill(String is_bill) {
                this.is_bill = is_bill;
            }

            public String getIs_que() {
                return is_que;
            }

            public void setIs_que(String is_que) {
                this.is_que = is_que;
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

            public String getIs_xia() {
                return is_xia;
            }

            public void setIs_xia(String is_xia) {
                this.is_xia = is_xia;
            }

            public int getK_money() {
                return k_money;
            }

            public void setK_money(int k_money) {
                this.k_money = k_money;
            }

            public int getJ_money() {
                return j_money;
            }

            public void setJ_money(int j_money) {
                this.j_money = j_money;
            }

            public float getY_money() {
                return y_money;
            }

            public void setY_money(float y_money) {
                this.y_money = y_money;
            }

            public int getK_month() {
                return k_month;
            }

            public void setK_month(int k_month) {
                this.k_month = k_month;
            }

            public int getY_month() {
                return y_month;
            }

            public void setY_month(int y_month) {
                this.y_month = y_month;
            }

            public int getJ_month() {
                return j_month;
            }

            public void setJ_month(int j_month) {
                this.j_month = j_month;
            }

            public int getZ_month() {
                return z_month;
            }

            public void setZ_month(int z_month) {
                this.z_month = z_month;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public List<?> getK_proble() {
                return k_proble;
            }

            public void setK_proble(List<?> k_proble) {
                this.k_proble = k_proble;
            }

            public List<YProbleBean> getY_proble() {
                return y_proble;
            }

            public void setY_proble(List<YProbleBean> y_proble) {
                this.y_proble = y_proble;
            }

            public List<?> getJ_proble() {
                return j_proble;
            }

            public void setJ_proble(List<?> j_proble) {
                this.j_proble = j_proble;
            }

            public List<?> getZ_proble() {
                return z_proble;
            }

            public void setZ_proble(List<?> z_proble) {
                this.z_proble = z_proble;
            }

            public static class YProbleBean {
                /**
                 * id : 27
                 * type : 3
                 * type1 : 1
                 * type2 : 1
                 * double : 0.00
                 * value : 对于草坪修剪我们有两种方案，您的选择是?
                 * yvalue : What kind of lawn care service would you like?
                 * pid : 0
                 * remark :
                 * yremark :
                 * problem : [{"id":"29","type":"3","type1":"2","type2":"1","double":"0.00","value":"维系方案，低耗维持，每月修剪1次以通过市政检查","yvalue":"Maintenance plan- low maintenance. The lawn will be mowed once a month for passing the municipal inspection.","pid":"27","remark":"","yremark":"","zhi":""}]
                 */

                private String id;
                private String type;
                private String type1;
                private String type2;
                @SerializedName("double")
                private String doubleX;
                private String value;
                private String yvalue;
                private String pid;
                private String remark;
                private String yremark;
                private List<ProblemBean> problem;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
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

                public String getDoubleX() {
                    return doubleX;
                }

                public void setDoubleX(String doubleX) {
                    this.doubleX = doubleX;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getYvalue() {
                    return yvalue;
                }

                public void setYvalue(String yvalue) {
                    this.yvalue = yvalue;
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

                public String getYremark() {
                    return yremark;
                }

                public void setYremark(String yremark) {
                    this.yremark = yremark;
                }

                public List<ProblemBean> getProblem() {
                    return problem;
                }

                public void setProblem(List<ProblemBean> problem) {
                    this.problem = problem;
                }

                public static class ProblemBean {
                    /**
                     * id : 29
                     * type : 3
                     * type1 : 2
                     * type2 : 1
                     * double : 0.00
                     * value : 维系方案，低耗维持，每月修剪1次以通过市政检查
                     * yvalue : Maintenance plan- low maintenance. The lawn will be mowed once a month for passing the municipal inspection.
                     * pid : 27
                     * remark :
                     * yremark :
                     * zhi :
                     */

                    private String id;
                    private String type;
                    private String type1;
                    private String type2;
                    @SerializedName("double")
                    private String doubleX;
                    private String value;
                    private String yvalue;
                    private String pid;
                    private String remark;
                    private String yremark;
                    private String zhi;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
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

                    public String getDoubleX() {
                        return doubleX;
                    }

                    public void setDoubleX(String doubleX) {
                        this.doubleX = doubleX;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }

                    public String getYvalue() {
                        return yvalue;
                    }

                    public void setYvalue(String yvalue) {
                        this.yvalue = yvalue;
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

                    public String getYremark() {
                        return yremark;
                    }

                    public void setYremark(String yremark) {
                        this.yremark = yremark;
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
}
