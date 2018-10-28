package com.haiwai.housekeeper.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ihope006 on 2016/12/6.
 */

public class NewHousEntity implements Serializable {

    /**
     * data : {"date":[{"id":"27","problem":[{"id":"28","remark":"","type2":"1","value":"优选方案，拥有一个漂亮的草坪，六至八月每月修剪3次，四、五和九月每月修剪2次","yremark":"","yvalue":""},{"id":"29","remark":"","type2":"1","value":"维系方案，低耗维持，每月修剪1次以通过市政检查","yremark":"","yvalue":""},{"id":"30","remark":"","type2":"1","value":"不需要修剪草坪","yremark":"","yvalue":""}],"remark":"","type2":"1","value":"对于草坪修剪我们有两种方案，您的选择是?","yremark":"","yvalue":""},{"id":"31","problem":[{"id":"32","remark":"","type2":"1","value":"小(小于1000平方英尺)","yremark":"","yvalue":""},{"id":"33","remark":"","type2":"1","value":"中(1000 - 5000平方英尺)","yremark":"","yvalue":""},{"id":"34","remark":"","type2":"1","value":"大(5000 - 10000平方英尺)","yremark":"","yvalue":""},{"id":"35","remark":"","type2":"4","value":"非常大(10000 +平方英尺)","yremark":"","yvalue":""},{"id":"36","remark":"","type2":"1","value":"我不确定","yremark":"","yvalue":""}],"remark":"","type2":"1","value":"您的草坪有多大?","yremark":"","yvalue":"How big is the lawn?"},{"id":"37","problem":[{"id":"38","remark":"","type2":"1","value":"草坪修边","yremark":"","yvalue":""},{"id":"39","remark":"","type2":"1","value":"树木灌木修剪","yremark":"","yvalue":""},{"id":"40","remark":"","type2":"1","value":"除杂草","yremark":"","yvalue":""},{"id":"41","remark":"","type2":"1","value":"施肥","yremark":"","yvalue":""},{"id":"42","remark":"","type2":"1","value":"补种草籽","yremark":"","yvalue":""},{"id":"90","remark":"","type2":"1","value":"草坪虫害治理","yremark":"","yvalue":""}],"remark":"","type2":"3","value":"我们还有其他园艺服务供您选择","yremark":"","yvalue":""},{"id":"43","problem":[{"id":"44","remark":"","type2":"1","value":"我需要落叶清扫服务（秋季9月-10月，隔周清扫）","yremark":"","yvalue":""},{"id":"45","remark":"","type2":"1","value":"我需要清雪服务（冬季11月-3月，确保行人步道不积雪）","yremark":"","yvalue":""}],"remark":"","type2":"3","value":"不同季节我们还提供落叶清扫和清雪服务","yremark":"","yvalue":""},{"id":"46","problem":[{"id":"47","remark":"","type2":"1","value":"小（面积不到2辆小汽车般大）","yremark":"","yvalue":""},{"id":"48","remark":"","type2":"1","value":"中（面积有2-5辆小汽车般大）","yremark":"","yvalue":""},{"id":"49","remark":"","type2":"4","value":"大（面积大于5辆车）","yremark":"","yvalue":""},{"id":"50","remark":"","type2":"1","value":"其他","yremark":"","yvalue":""}],"remark":"","type2":"1","value":"需要清雪的人行步道有多大？","yremark":"","yvalue":""},{"id":"51","problem":[{"id":"52","remark":"","type2":"1","value":"不需要，不介意我不在场的时候工作","yremark":"","yvalue":""},{"id":"53","remark":"","type2":"1","value":"需要，工作时我必须在场","yremark":"","yvalue":""}],"remark":"","type2":"1","value":"庭院维护过程中你是否需要在场?","yremark":"","yvalue":""}],"img":"http://hwgj.zai0312.com/Uploads/2017-01-19/58809c62116b7.png"}
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
         * date : [{"id":"27","problem":[{"id":"28","remark":"","type2":"1","value":"优选方案，拥有一个漂亮的草坪，六至八月每月修剪3次，四、五和九月每月修剪2次","yremark":"","yvalue":""},{"id":"29","remark":"","type2":"1","value":"维系方案，低耗维持，每月修剪1次以通过市政检查","yremark":"","yvalue":""},{"id":"30","remark":"","type2":"1","value":"不需要修剪草坪","yremark":"","yvalue":""}],"remark":"","type2":"1","value":"对于草坪修剪我们有两种方案，您的选择是?","yremark":"","yvalue":""},{"id":"31","problem":[{"id":"32","remark":"","type2":"1","value":"小(小于1000平方英尺)","yremark":"","yvalue":""},{"id":"33","remark":"","type2":"1","value":"中(1000 - 5000平方英尺)","yremark":"","yvalue":""},{"id":"34","remark":"","type2":"1","value":"大(5000 - 10000平方英尺)","yremark":"","yvalue":""},{"id":"35","remark":"","type2":"4","value":"非常大(10000 +平方英尺)","yremark":"","yvalue":""},{"id":"36","remark":"","type2":"1","value":"我不确定","yremark":"","yvalue":""}],"remark":"","type2":"1","value":"您的草坪有多大?","yremark":"","yvalue":"How big is the lawn?"},{"id":"37","problem":[{"id":"38","remark":"","type2":"1","value":"草坪修边","yremark":"","yvalue":""},{"id":"39","remark":"","type2":"1","value":"树木灌木修剪","yremark":"","yvalue":""},{"id":"40","remark":"","type2":"1","value":"除杂草","yremark":"","yvalue":""},{"id":"41","remark":"","type2":"1","value":"施肥","yremark":"","yvalue":""},{"id":"42","remark":"","type2":"1","value":"补种草籽","yremark":"","yvalue":""},{"id":"90","remark":"","type2":"1","value":"草坪虫害治理","yremark":"","yvalue":""}],"remark":"","type2":"3","value":"我们还有其他园艺服务供您选择","yremark":"","yvalue":""},{"id":"43","problem":[{"id":"44","remark":"","type2":"1","value":"我需要落叶清扫服务（秋季9月-10月，隔周清扫）","yremark":"","yvalue":""},{"id":"45","remark":"","type2":"1","value":"我需要清雪服务（冬季11月-3月，确保行人步道不积雪）","yremark":"","yvalue":""}],"remark":"","type2":"3","value":"不同季节我们还提供落叶清扫和清雪服务","yremark":"","yvalue":""},{"id":"46","problem":[{"id":"47","remark":"","type2":"1","value":"小（面积不到2辆小汽车般大）","yremark":"","yvalue":""},{"id":"48","remark":"","type2":"1","value":"中（面积有2-5辆小汽车般大）","yremark":"","yvalue":""},{"id":"49","remark":"","type2":"4","value":"大（面积大于5辆车）","yremark":"","yvalue":""},{"id":"50","remark":"","type2":"1","value":"其他","yremark":"","yvalue":""}],"remark":"","type2":"1","value":"需要清雪的人行步道有多大？","yremark":"","yvalue":""},{"id":"51","problem":[{"id":"52","remark":"","type2":"1","value":"不需要，不介意我不在场的时候工作","yremark":"","yvalue":""},{"id":"53","remark":"","type2":"1","value":"需要，工作时我必须在场","yremark":"","yvalue":""}],"remark":"","type2":"1","value":"庭院维护过程中你是否需要在场?","yremark":"","yvalue":""}]
         * img : http://hwgj.zai0312.com/Uploads/2017-01-19/58809c62116b7.png
         */

        private String img;
        private List<DateBean> date;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public List<DateBean> getDate() {
            return date;
        }

        public void setDate(List<DateBean> date) {
            this.date = date;
        }

        public static class DateBean implements Serializable{
            /**
             * id : 27
             * problem : [{"id":"28","remark":"","type2":"1","value":"优选方案，拥有一个漂亮的草坪，六至八月每月修剪3次，四、五和九月每月修剪2次","yremark":"","yvalue":""},{"id":"29","remark":"","type2":"1","value":"维系方案，低耗维持，每月修剪1次以通过市政检查","yremark":"","yvalue":""},{"id":"30","remark":"","type2":"1","value":"不需要修剪草坪","yremark":"","yvalue":""}]
             * remark :
             * type2 : 1
             * value : 对于草坪修剪我们有两种方案，您的选择是?
             * yremark :
             * yvalue :
             */

            private String id;
            private String remark;
            private String type2;
            private String value;
            private String yremark;
            private String yvalue;
            private List<ProblemBean> problem;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
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

            public static class ProblemBean implements Serializable{
                /**
                 * id : 28
                 * remark :
                 * type2 : 1
                 * value : 优选方案，拥有一个漂亮的草坪，六至八月每月修剪3次，四、五和九月每月修剪2次
                 * yremark :
                 * yvalue :
                 */

                private String id;
                private String remark;
                private String type2;
                private String value;
                private String yremark;
                private String yvalue;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
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
            }
        }
    }
}
