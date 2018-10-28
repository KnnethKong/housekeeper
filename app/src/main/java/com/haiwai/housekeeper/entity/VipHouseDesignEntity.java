package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ihope006 on 2016/11/24.
 */

public class VipHouseDesignEntity {

    /**
     * data : {"bill":{"avatar":"http://hwgj.zai0312.com/Uploads/2016-10-12/57fdf81d07c44.png","ctime":"1479269515","emonthtime":"0","feedback":{"ctime":"1479269515","grent":"0","h_id":"30","home_remark":"","id":"2","is_da":"0","is_static":"0","monthtime":"1480521600","order_id":"20161116147926951554489","price_remark":"","rent":"0","tenant_remark":"","tenant_static":"0","uid":"2","wrent":"0","wtime1":"0","wtime2":"0","wtime3":"0"},"h_id":"30","id":"16","is_que":"0","is_ren":"1","j_num":"0","j_uid":"3","math_num":"1","money":"0","monthtime":"1480521600","nickname":"张三丰","num":"0","order_id":"20161116147926951554489","sid":"4","static":"4","type":"5","uid":"2"},"forrent":[],"home":{"ctime":"1479269515","emonthtime":"0","feedback":[{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"34","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"1","order_id":"20161116147926951576887","stime":"1481299200","wtime1":"0","wtime2":"0","wtime3":"0"},{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"35","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"2","order_id":"20161116147926951576887","stime":"1482163200","wtime1":"0","wtime2":"0","wtime3":"0"},{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"36","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"3","order_id":"20161116147926951576887","stime":"1483027200","wtime1":"0","wtime2":"0","wtime3":"0"}],"h_id":"30","id":"15","is_que":"0","j_num":"0","j_uid":"0","math_num":"1","money":"40","monthtime":"1480521600","num":"3","order_id":"20161116147926951576887","sid":"4","static":"1","type":"2","uid":"2","wan_num":0},"horticulture":{"ctime":"1479269515","emonthtime":"0","feedback":[{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"30","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"1","order_id":"20161116147926951595451","stime":"1481040000","wtime1":"0","wtime2":"0","wtime3":"0"},{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"31","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"2","order_id":"20161116147926951595451","stime":"1481644800","wtime1":"0","wtime2":"0","wtime3":"0"},{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"32","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"3","order_id":"20161116147926951595451","stime":"1482249600","wtime1":"0","wtime2":"0","wtime3":"0"},{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"33","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"4","order_id":"20161116147926951595451","stime":"1482854400","wtime1":"0","wtime2":"0","wtime3":"0"}],"h_id":"30","id":"14","is_que":"0","j_num":"0","j_uid":"0","math_num":"1","money":"40","monthtime":"1480521600","num":"4","order_id":"20161116147926951595451","sid":"4","static":"1","type":"3","uid":"2","wan_num":0},"hous":{"address_info":"分居老K","alternate_contact":"13245784679","built_area":"100","cement_area":"30","city":"1","ctime":"1478498947","green_area":"30","housing_type":"别墅","id":"30","is_del":"0","land_area":"200","lat":"116.3192876558","long":"40.0592732065","uid":"6"},"vacancy":{"ctime":"1479269515","emonthtime":"0","feedback":[{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"28","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"1","order_id":"20161116147926951589571","stime":"1481731200","wtime1":"0","wtime2":"0","wtime3":"0"},{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"29","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"2","order_id":"20161116147926951589571","stime":"1483027200","wtime1":"0","wtime2":"0","wtime3":"0"}],"h_id":"30","id":"13","is_que":"0","j_num":"0","j_uid":"0","math_num":"1","money":"40","monthtime":"1480521600","num":"2","order_id":"20161116147926951589571","sid":"4","static":"1","type":"1","uid":"2","wan_num":0}}
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
         * bill : {"avatar":"http://hwgj.zai0312.com/Uploads/2016-10-12/57fdf81d07c44.png","ctime":"1479269515","emonthtime":"0","feedback":{"ctime":"1479269515","grent":"0","h_id":"30","home_remark":"","id":"2","is_da":"0","is_static":"0","monthtime":"1480521600","order_id":"20161116147926951554489","price_remark":"","rent":"0","tenant_remark":"","tenant_static":"0","uid":"2","wrent":"0","wtime1":"0","wtime2":"0","wtime3":"0"},"h_id":"30","id":"16","is_que":"0","is_ren":"1","j_num":"0","j_uid":"3","math_num":"1","money":"0","monthtime":"1480521600","nickname":"张三丰","num":"0","order_id":"20161116147926951554489","sid":"4","static":"4","type":"5","uid":"2"}
         * forrent : []
         * home : {"ctime":"1479269515","emonthtime":"0","feedback":[{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"34","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"1","order_id":"20161116147926951576887","stime":"1481299200","wtime1":"0","wtime2":"0","wtime3":"0"},{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"35","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"2","order_id":"20161116147926951576887","stime":"1482163200","wtime1":"0","wtime2":"0","wtime3":"0"},{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"36","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"3","order_id":"20161116147926951576887","stime":"1483027200","wtime1":"0","wtime2":"0","wtime3":"0"}],"h_id":"30","id":"15","is_que":"0","j_num":"0","j_uid":"0","math_num":"1","money":"40","monthtime":"1480521600","num":"3","order_id":"20161116147926951576887","sid":"4","static":"1","type":"2","uid":"2","wan_num":0}
         * horticulture : {"ctime":"1479269515","emonthtime":"0","feedback":[{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"30","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"1","order_id":"20161116147926951595451","stime":"1481040000","wtime1":"0","wtime2":"0","wtime3":"0"},{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"31","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"2","order_id":"20161116147926951595451","stime":"1481644800","wtime1":"0","wtime2":"0","wtime3":"0"},{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"32","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"3","order_id":"20161116147926951595451","stime":"1482249600","wtime1":"0","wtime2":"0","wtime3":"0"},{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"33","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"4","order_id":"20161116147926951595451","stime":"1482854400","wtime1":"0","wtime2":"0","wtime3":"0"}],"h_id":"30","id":"14","is_que":"0","j_num":"0","j_uid":"0","math_num":"1","money":"40","monthtime":"1480521600","num":"4","order_id":"20161116147926951595451","sid":"4","static":"1","type":"3","uid":"2","wan_num":0}
         * hous : {"address_info":"分居老K","alternate_contact":"13245784679","built_area":"100","cement_area":"30","city":"1","ctime":"1478498947","green_area":"30","housing_type":"别墅","id":"30","is_del":"0","land_area":"200","lat":"116.3192876558","long":"40.0592732065","uid":"6"}
         * vacancy : {"ctime":"1479269515","emonthtime":"0","feedback":[{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"28","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"1","order_id":"20161116147926951589571","stime":"1481731200","wtime1":"0","wtime2":"0","wtime3":"0"},{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"29","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"2","order_id":"20161116147926951589571","stime":"1483027200","wtime1":"0","wtime2":"0","wtime3":"0"}],"h_id":"30","id":"13","is_que":"0","j_num":"0","j_uid":"0","math_num":"1","money":"40","monthtime":"1480521600","num":"2","order_id":"20161116147926951589571","sid":"4","static":"1","type":"1","uid":"2","wan_num":0}
         */

        private BillBean bill;
        private HomeBean home;
        private HorticultureBean horticulture;
        private HousBean hous;
        private VacancyBean vacancy;
        private List<?> forrent;

        public BillBean getBill() {
            return bill;
        }

        public void setBill(BillBean bill) {
            this.bill = bill;
        }

        public HomeBean getHome() {
            return home;
        }

        public void setHome(HomeBean home) {
            this.home = home;
        }

        public HorticultureBean getHorticulture() {
            return horticulture;
        }

        public void setHorticulture(HorticultureBean horticulture) {
            this.horticulture = horticulture;
        }

        public HousBean getHous() {
            return hous;
        }

        public void setHous(HousBean hous) {
            this.hous = hous;
        }

        public VacancyBean getVacancy() {
            return vacancy;
        }

        public void setVacancy(VacancyBean vacancy) {
            this.vacancy = vacancy;
        }

        public List<?> getForrent() {
            return forrent;
        }

        public void setForrent(List<?> forrent) {
            this.forrent = forrent;
        }

        public static class BillBean implements Serializable {
            /**
             * avatar : http://hwgj.zai0312.com/Uploads/2016-10-12/57fdf81d07c44.png
             * ctime : 1479269515
             * emonthtime : 0
             * feedback : {"ctime":"1479269515","grent":"0","h_id":"30","home_remark":"","id":"2","is_da":"0","is_static":"0","monthtime":"1480521600","order_id":"20161116147926951554489","price_remark":"","rent":"0","tenant_remark":"","tenant_static":"0","uid":"2","wrent":"0","wtime1":"0","wtime2":"0","wtime3":"0"}
             * h_id : 30
             * id : 16
             * is_que : 0
             * is_ren : 1
             * j_num : 0
             * j_uid : 3
             * math_num : 1
             * money : 0
             * monthtime : 1480521600
             * nickname : 张三丰
             * num : 0
             * order_id : 20161116147926951554489
             * sid : 4
             * static : 4
             * type : 5
             * uid : 2
             */

            private String avatar;
            private String ctime;
            private String emonthtime;
            private FeedbackBean feedback;
            private String h_id;
            private String id;
            private String is_que;
            private String is_ren;
            private String j_num;
            private String j_uid;
            private String math_num;
            public String is_hou;
            public String s;
            public String mobile;
            public String user_xing;
            public String pro_score;
            private String money;
            private String user_onum;
            private String monthtime;
            private String nickname;
            private String num;
            private String order_id;
            private String sid;
            @SerializedName("static")
            private String staticX;
            private String type;
            private String uid;

            public String getUser_onum() {
                return user_onum;
            }

            public void setUser_onum(String user_onum) {
                this.user_onum = user_onum;
            }

            public String getS() {
                return s;
            }

            public void setS(String s) {
                this.s = s;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getIs_hou() {
                return is_hou;
            }

            public void setIs_hou(String is_hou) {
                this.is_hou = is_hou;
            }

            public String getUser_xing() {
                return user_xing;
            }

            public void setUser_xing(String user_xing) {
                this.user_xing = user_xing;
            }

            public String getPro_score() {
                return pro_score;
            }

            public void setPro_score(String pro_score) {
                this.pro_score = pro_score;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

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

            public FeedbackBean getFeedback() {
                return feedback;
            }

            public void setFeedback(FeedbackBean feedback) {
                this.feedback = feedback;
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

            public String getIs_ren() {
                return is_ren;
            }

            public void setIs_ren(String is_ren) {
                this.is_ren = is_ren;
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

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
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

            public static class FeedbackBean implements Serializable {
                /**
                 * ctime : 1479269515
                 * grent : 0
                 * h_id : 30
                 * home_remark :
                 * id : 2
                 * is_da : 0
                 * is_static : 0
                 * monthtime : 1480521600
                 * order_id : 20161116147926951554489
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
                private String ren_nickname;
                private String tenant_remark;
                private String tenant_static;
                private String uid;
                private String wrent;
                private String wtime1;
                private String wtime2;
                private String wtime3;

                @Override
                public String toString() {
                    return "FeedbackBean{" +
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
                            ", ren_nickname='" + ren_nickname + '\'' +
                            ", tenant_remark='" + tenant_remark + '\'' +
                            ", tenant_static='" + tenant_static + '\'' +
                            ", uid='" + uid + '\'' +
                            ", wrent='" + wrent + '\'' +
                            ", wtime1='" + wtime1 + '\'' +
                            ", wtime2='" + wtime2 + '\'' +
                            ", wtime3='" + wtime3 + '\'' +
                            '}';
                }

                public String getRen_nickname() {
                    return ren_nickname;
                }

                public void setRen_nickname(String ren_nickname) {
                    this.ren_nickname = ren_nickname;
                }

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
        }

        public static class HomeBean {
            /**
             * ctime : 1479269515
             * emonthtime : 0
             * feedback : [{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"34","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"1","order_id":"20161116147926951576887","stime":"1481299200","wtime1":"0","wtime2":"0","wtime3":"0"},{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"35","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"2","order_id":"20161116147926951576887","stime":"1482163200","wtime1":"0","wtime2":"0","wtime3":"0"},{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"36","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"3","order_id":"20161116147926951576887","stime":"1483027200","wtime1":"0","wtime2":"0","wtime3":"0"}]
             * h_id : 30
             * id : 15
             * is_que : 0
             * j_num : 0
             * j_uid : 0
             * math_num : 1
             * money : 40
             * monthtime : 1480521600
             * num : 3
             * order_id : 20161116147926951576887
             * sid : 4
             * static : 1
             * type : 2
             * uid : 2
             * wan_num : 0
             */
            private String avatar;
            private String nickname;
            private String ctime;
            private String emonthtime;
            private String h_id;
            private String id;
            private String is_que;

            public String is_ren;
            public String is_hou;
            public String user_xing;
            public String pro_score;
            public String mobile;
            public String s;
            private String user_onum;
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
            private int wan_num;
            private List<FeedbackBeanX> feedback;

            public String getUser_onum() {
                return user_onum;
            }

            public void setUser_onum(String user_onum) {
                this.user_onum = user_onum;
            }

            public String getS() {
                return s;
            }

            public void setS(String s) {
                this.s = s;
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

            public String getUser_xing() {
                return user_xing;
            }

            public void setUser_xing(String user_xing) {
                this.user_xing = user_xing;
            }

            public String getPro_score() {
                return pro_score;
            }

            public void setPro_score(String pro_score) {
                this.pro_score = pro_score;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
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

            public int getWan_num() {
                return wan_num;
            }

            public void setWan_num(int wan_num) {
                this.wan_num = wan_num;
            }

            public List<FeedbackBeanX> getFeedback() {
                return feedback;
            }

            public void setFeedback(List<FeedbackBeanX> feedback) {
                this.feedback = feedback;
            }

            public static class FeedbackBeanX implements Serializable {
                /**
                 * content1 :
                 * content2 :
                 * content3 :
                 * ctime : 1479269515
                 * id : 34
                 * images1 :
                 * images2 :
                 * images3 :
                 * is_new : 0
                 * is_special : 0
                 * is_wan : 0
                 * number : 1
                 * order_id : 20161116147926951576887
                 * stime : 1481299200
                 * wtime1 : 0
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

        public static class HorticultureBean {
            /**
             * ctime : 1479269515
             * emonthtime : 0
             * feedback : [{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"30","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"1","order_id":"20161116147926951595451","stime":"1481040000","wtime1":"0","wtime2":"0","wtime3":"0"},{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"31","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"2","order_id":"20161116147926951595451","stime":"1481644800","wtime1":"0","wtime2":"0","wtime3":"0"},{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"32","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"3","order_id":"20161116147926951595451","stime":"1482249600","wtime1":"0","wtime2":"0","wtime3":"0"},{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"33","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"4","order_id":"20161116147926951595451","stime":"1482854400","wtime1":"0","wtime2":"0","wtime3":"0"}]
             * h_id : 30
             * id : 14
             * is_que : 0
             * j_num : 0
             * j_uid : 0
             * math_num : 1
             * money : 40
             * monthtime : 1480521600
             * num : 4
             * order_id : 20161116147926951595451
             * sid : 4
             * static : 1
             * type : 3
             * uid : 2
             * wan_num : 0
             */
            private String avatar;
            private String nickname;
            private String ctime;
            private String emonthtime;
            private String h_id;
            private String id;
            public String is_ren;
            public String user_xing;
            public String is_hou;
            public String s;
            public String mobile;
            public String pro_score;
            private String is_que;
            private String j_num;
            private String j_uid;
            private String math_num;
            private String money;
            private String user_onum;
            private String monthtime;
            private String num;
            private String order_id;
            private String sid;
            @SerializedName("static")
            private String staticX;
            private String type;
            private String uid;
            private int wan_num;
            private List<FeedbackBeanXX> feedback;

            public String getUser_onum() {
                return user_onum;
            }

            public void setUser_onum(String user_onum) {
                this.user_onum = user_onum;
            }

            public void setIs_ren(String is_ren) {
                this.is_ren = is_ren;
            }

            public void setUser_xing(String user_xing) {
                this.user_xing = user_xing;
            }

            public void setIs_hou(String is_hou) {
                this.is_hou = is_hou;
            }

            public void setS(String s) {
                this.s = s;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public void setPro_score(String pro_score) {
                this.pro_score = pro_score;
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

            public int getWan_num() {
                return wan_num;
            }

            public void setWan_num(int wan_num) {
                this.wan_num = wan_num;
            }

            public List<FeedbackBeanXX> getFeedback() {
                return feedback;
            }

            public void setFeedback(List<FeedbackBeanXX> feedback) {
                this.feedback = feedback;
            }

            public static class FeedbackBeanXX implements Serializable {
                /**
                 * content1 :
                 * content2 :
                 * content3 :
                 * ctime : 1479269515
                 * id : 30
                 * images1 :
                 * images2 :
                 * images3 :
                 * is_new : 0
                 * is_special : 0
                 * is_wan : 0
                 * number : 1
                 * order_id : 20161116147926951595451
                 * stime : 1481040000
                 * wtime1 : 0
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

        public static class HousBean {
            /**
             * address_info : 分居老K
             * alternate_contact : 13245784679
             * built_area : 100
             * cement_area : 30
             * city : 1
             * ctime : 1478498947
             * green_area : 30
             * housing_type : 别墅
             * id : 30
             * is_del : 0
             * land_area : 200
             * lat : 116.3192876558
             * long : 40.0592732065
             * uid : 6
             */
            private String avatar;
            private String nickname;
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

        public static class VacancyBean {
            /**
             * ctime : 1479269515
             * emonthtime : 0
             * feedback : [{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"28","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"1","order_id":"20161116147926951589571","stime":"1481731200","wtime1":"0","wtime2":"0","wtime3":"0"},{"content1":"","content2":"","content3":"","ctime":"1479269515","id":"29","images1":"","images2":"","images3":"","is_new":"0","is_special":"0","is_wan":"0","number":"2","order_id":"20161116147926951589571","stime":"1483027200","wtime1":"0","wtime2":"0","wtime3":"0"}]
             * h_id : 30
             * id : 13
             * is_que : 0
             * j_num : 0
             * j_uid : 0
             * math_num : 1
             * money : 40
             * monthtime : 1480521600
             * num : 2
             * order_id : 20161116147926951589571
             * sid : 4
             * static : 1
             * type : 1
             * uid : 2
             * wan_num : 0
             */
            private String avatar;
            private String nickname;
            private String ctime;
            private String emonthtime;
            private String h_id;
            private String id;
            public String is_ren;
            public String user_xing;
            public String is_hou;
            public String s;
            public String mobile;
            public String pro_score;
            private String is_que;
            private String j_num;
            private String j_uid;
            private String math_num;
            private String money;
            private String user_onum;
            private String monthtime;
            private String num;
            private String order_id;
            private String sid;
            @SerializedName("static")
            private String staticX;
            private String type;
            private String uid;
            private int wan_num;
            private List<FeedbackBeanXXX> feedback;

            public String getUser_onum() {
                return user_onum;
            }

            public void setUser_onum(String user_onum) {
                this.user_onum = user_onum;
            }

            public String getIs_ren() {
                return is_ren;
            }

            public void setIs_ren(String is_ren) {
                this.is_ren = is_ren;
            }

            public String getUser_xing() {
                return user_xing;
            }

            public void setUser_xing(String user_xing) {
                this.user_xing = user_xing;
            }

            public String getIs_hou() {
                return is_hou;
            }

            public void setIs_hou(String is_hou) {
                this.is_hou = is_hou;
            }

            public String getS() {
                return s;
            }

            public void setS(String s) {
                this.s = s;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getPro_score() {
                return pro_score;
            }

            public void setPro_score(String pro_score) {
                this.pro_score = pro_score;
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

            public int getWan_num() {
                return wan_num;
            }

            public void setWan_num(int wan_num) {
                this.wan_num = wan_num;
            }

            public List<FeedbackBeanXXX> getFeedback() {
                return feedback;
            }

            public void setFeedback(List<FeedbackBeanXXX> feedback) {
                this.feedback = feedback;
            }

            public static class FeedbackBeanXXX implements Serializable {
                /**
                 * content1 :
                 * content2 :
                 * content3 :
                 * ctime : 1479269515
                 * id : 28
                 * images1 :
                 * images2 :
                 * images3 :
                 * is_new : 0
                 * is_special : 0
                 * is_wan : 0
                 * number : 1
                 * order_id : 20161116147926951589571
                 * stime : 1481731200
                 * wtime1 : 0
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
}
