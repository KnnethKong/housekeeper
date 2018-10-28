package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ihope10 on 2016/10/14.
 */

public class SkillDetailEntity {


    private DataBean data;
    /**
     * data : {"date":[{"advantage":"测试","class":"的的的的的的的的的的的的的的的的额的的的的的的额","ctime":"1476675685","id":"18","is_audit":"0","is_recommend":"0","is_ren":"0","money_p":0,"order_num":"0","type":"16","uid":"3","wen1":"0","wen2":"0","wen3":"0","wen4":"0","wen5":"0","wen6":"0","wen7":"0","wen8":"0","zimg":"","zname":""}],"education":[{"etime":"1490976000000","id":"30","name":"的的","sid":"18","stime":"1475251200000","type":"1","xue":"的的"}],"job":[{"etime":"1490976000000","id":"31","name":"工作","sid":"18","stime":"1475251200000","type":"2","xue":"工作"}]}
     * status : 200
     */

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
         * advantage : 测试
         * class : 的的的的的的的的的的的的的的的的额的的的的的的额
         * ctime : 1476675685
         * id : 18
         * is_audit : 0
         * is_recommend : 0
         * is_ren : 0
         * money_p : 0
         * order_num : 0
         * type : 16
         * uid : 3
         * wen1 : 0
         * wen2 : 0
         * wen3 : 0
         * wen4 : 0
         * wen5 : 0
         * wen6 : 0
         * wen7 : 0
         * wen8 : 0
         * zimg :
         * zname :
         */

        private List<DateBean> date;
        /**
         * etime : 1490976000000
         * id : 30
         * name : 的的
         * sid : 18
         * stime : 1475251200000
         * type : 1
         * xue : 的的
         */

        private List<EducationBean> education;
        /**
         * etime : 1490976000000
         * id : 31
         * name : 工作
         * sid : 18
         * stime : 1475251200000
         * type : 2
         * xue : 工作
         */

        private List<JobBean> job;

        public List<DateBean> getDate() {
            return date;
        }

        public void setDate(List<DateBean> date) {
            this.date = date;
        }

        public List<EducationBean> getEducation() {
            return education;
        }

        public void setEducation(List<EducationBean> education) {
            this.education = education;
        }

        public List<JobBean> getJob() {
            return job;
        }

        public void setJob(List<JobBean> job) {
            this.job = job;
        }

        public static class DateBean implements Serializable {
            private String advantage;
            @SerializedName("class")
            private String classX;
            private String ctime;
            private String id;
            private String is_audit;
            private String is_recommend;
            private String is_ren;
            private int money_p;
            private String order_num;
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
            private String zimg;
            private String zname;
            private String xing_num;
            private String v;
            private String is_ji;

            public String getAdvantage() {
                return advantage;
            }

            public void setAdvantage(String advantage) {
                this.advantage = advantage;
            }

            public String getClassX() {
                return classX;
            }

            public void setClassX(String classX) {
                this.classX = classX;
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

            public String getIs_audit() {
                return is_audit;
            }

            public void setIs_audit(String is_audit) {
                this.is_audit = is_audit;
            }

            public String getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(String is_recommend) {
                this.is_recommend = is_recommend;
            }

            public String getIs_ren() {
                return is_ren;
            }

            public void setIs_ren(String is_ren) {
                this.is_ren = is_ren;
            }

            public int getMoney_p() {
                return money_p;
            }

            public void setMoney_p(int money_p) {
                this.money_p = money_p;
            }

            public String getOrder_num() {
                return order_num;
            }

            public void setOrder_num(String order_num) {
                this.order_num = order_num;
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

            public String getZimg() {
                return zimg;
            }

            public void setZimg(String zimg) {
                this.zimg = zimg;
            }

            public String getZname() {
                return zname;
            }

            public void setZname(String zname) {
                this.zname = zname;
            }

            public String getXing_num() {
                return xing_num;
            }

            public void setXing_num(String xing_num) {
                this.xing_num = xing_num;
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
        }

        public static class EducationBean implements Serializable{
            private String etime;
            private String id;
            private String name;
            private String sid;
            private String stime;
            private String type;
            private String xue;

            public String getEtime() {
                return etime;
            }

            public void setEtime(String etime) {
                this.etime = etime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }

            public String getStime() {
                return stime;
            }

            public void setStime(String stime) {
                this.stime = stime;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getXue() {
                return xue;
            }

            public void setXue(String xue) {
                this.xue = xue;
            }
        }

        public static class JobBean implements Serializable{
            private String etime;
            private String id;
            private String name;
            private String sid;
            private String stime;
            private String type;
            private String xue;

            public String getEtime() {
                return etime;
            }

            public void setEtime(String etime) {
                this.etime = etime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }

            public String getStime() {
                return stime;
            }

            public void setStime(String stime) {
                this.stime = stime;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getXue() {
                return xue;
            }

            public void setXue(String xue) {
                this.xue = xue;
            }
        }
    }
}
