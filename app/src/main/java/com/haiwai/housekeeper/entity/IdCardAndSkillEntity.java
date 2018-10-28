package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ihope10 on 2016/10/17.
 */

public class IdCardAndSkillEntity {

    /**
     * status : 200
     * data : {"user_skill":[{"id":"38","uid":"3","type":"1","order_num":"0","class":"测试的的的的的的的的的的的的的的的的的的的的的额","advantage":"测试","is_recommend":"0","is_audit":"1","is_ji":"1","is_ren":"0","wen1":"0","wen2":"1","wen3":"1","wen4":"0","wen5":"0","wen6":"0","wen7":"0","wen8":"0","wen9":"0","wen10":"0","wen11":"0","wen12":"0","wen13":"0","wen14":"0","wen15":"0","wen16":"0","wen17":"0","wen18":"0","wen19":"0","wen20":"0","wen21":"0","wen22":"0","wen23":"0","wen24":"0","wen25":"0","wen26":"0","wen27":"0","wen28":"0","wen29":"0","wen30":"0","wen31":"0","wen32":"0","wen33":"0","wen34":"0","ctime":"1477726752","skill_ren":[{"id":"19","sid":"38","is_ren":"1","zname":"测试","zimg":["http://hwgj.zai0312.com/Uploads/2016-10-29/5814153f42b2e.png"],"ctime":"0"},{"id":"21","sid":"38","is_ren":"1","zname":"","zimg":["http://hwgj.zai0312.com/Uploads/2016-10-29/581466029d602.png"],"ctime":"0"}]},{"id":"39","uid":"3","type":"2","order_num":"0","class":"的的的的的的的的的的的的的的的的的的的的的的","advantage":"测试","is_recommend":"0","is_audit":"1","is_ji":"1","is_ren":"0","wen1":"0","wen2":"1","wen3":"0","wen4":"0","wen5":"0","wen6":"0","wen7":"0","wen8":"0","wen9":"0","wen10":"0","wen11":"0","wen12":"0","wen13":"0","wen14":"0","wen15":"0","wen16":"0","wen17":"0","wen18":"0","wen19":"0","wen20":"0","wen21":"0","wen22":"0","wen23":"0","wen24":"0","wen25":"0","wen26":"0","wen27":"0","wen28":"0","wen29":"0","wen30":"0","wen31":"0","wen32":"0","wen33":"0","wen34":"0","ctime":"1477731581","skill_ren":[{"id":"20","sid":"39","is_ren":"1","zname":"","zimg":["http://hwgj.zai0312.com/Uploads/2016-10-29/581465e7081f6.png"],"ctime":"0"}]},{"id":"41","uid":"3","type":"32","order_num":"0","class":"","advantage":"","is_recommend":"0","is_audit":"1","is_ji":"1","is_ren":"0","wen1":"0","wen2":"0","wen3":"0","wen4":"0","wen5":"0","wen6":"0","wen7":"0","wen8":"0","wen9":"0","wen10":"0","wen11":"0","wen12":"0","wen13":"0","wen14":"0","wen15":"0","wen16":"0","wen17":"0","wen18":"0","wen19":"0","wen20":"0","wen21":"0","wen22":"0","wen23":"0","wen24":"0","wen25":"0","wen26":"0","wen27":"0","wen28":"0","wen29":"0","wen30":"0","wen31":"0","wen32":"0","wen33":"0","wen34":"0","ctime":"0","skill_ren":[]},{"id":"42","uid":"3","type":"33","order_num":"0","class":"","advantage":"","is_recommend":"0","is_audit":"1","is_ji":"1","is_ren":"0","wen1":"0","wen2":"0","wen3":"0","wen4":"0","wen5":"0","wen6":"0","wen7":"0","wen8":"0","wen9":"0","wen10":"0","wen11":"0","wen12":"0","wen13":"0","wen14":"0","wen15":"0","wen16":"0","wen17":"0","wen18":"0","wen19":"0","wen20":"0","wen21":"0","wen22":"0","wen23":"0","wen24":"0","wen25":"0","wen26":"0","wen27":"0","wen28":"0","wen29":"0","wen30":"0","wen31":"0","wen32":"0","wen33":"0","wen34":"0","ctime":"0","skill_ren":[]},{"id":"43","uid":"3","type":"34","order_num":"0","class":"","advantage":"","is_recommend":"0","is_audit":"1","is_ji":"1","is_ren":"0","wen1":"0","wen2":"0","wen3":"0","wen4":"0","wen5":"0","wen6":"0","wen7":"0","wen8":"0","wen9":"0","wen10":"0","wen11":"0","wen12":"0","wen13":"0","wen14":"0","wen15":"0","wen16":"0","wen17":"0","wen18":"0","wen19":"0","wen20":"0","wen21":"0","wen22":"0","wen23":"0","wen24":"0","wen25":"0","wen26":"0","wen27":"0","wen28":"0","wen29":"0","wen30":"0","wen31":"0","wen32":"0","wen33":"0","wen34":"0","ctime":"0","skill_ren":[]}],"user_idcard":{"uid":"3","idcard_shou":"http://hwgj.zai0312.com/Uploads/2016-10-17/5804a67c30105.png","idcard_zheng":"http://hwgj.zai0312.com/Uploads/2016-10-17/5804a67c3094f.png","idcard_fan":"http://hwgj.zai0312.com/Uploads/2016-10-17/5804a6df75624.png","idcard_ren":"2"}}
     */

    private int status;
    /**
     * user_skill : [{"id":"38","uid":"3","type":"1","order_num":"0","class":"测试的的的的的的的的的的的的的的的的的的的的的额","advantage":"测试","is_recommend":"0","is_audit":"1","is_ji":"1","is_ren":"0","wen1":"0","wen2":"1","wen3":"1","wen4":"0","wen5":"0","wen6":"0","wen7":"0","wen8":"0","wen9":"0","wen10":"0","wen11":"0","wen12":"0","wen13":"0","wen14":"0","wen15":"0","wen16":"0","wen17":"0","wen18":"0","wen19":"0","wen20":"0","wen21":"0","wen22":"0","wen23":"0","wen24":"0","wen25":"0","wen26":"0","wen27":"0","wen28":"0","wen29":"0","wen30":"0","wen31":"0","wen32":"0","wen33":"0","wen34":"0","ctime":"1477726752","skill_ren":[{"id":"19","sid":"38","is_ren":"1","zname":"测试","zimg":["http://hwgj.zai0312.com/Uploads/2016-10-29/5814153f42b2e.png"],"ctime":"0"},{"id":"21","sid":"38","is_ren":"1","zname":"","zimg":["http://hwgj.zai0312.com/Uploads/2016-10-29/581466029d602.png"],"ctime":"0"}]},{"id":"39","uid":"3","type":"2","order_num":"0","class":"的的的的的的的的的的的的的的的的的的的的的的","advantage":"测试","is_recommend":"0","is_audit":"1","is_ji":"1","is_ren":"0","wen1":"0","wen2":"1","wen3":"0","wen4":"0","wen5":"0","wen6":"0","wen7":"0","wen8":"0","wen9":"0","wen10":"0","wen11":"0","wen12":"0","wen13":"0","wen14":"0","wen15":"0","wen16":"0","wen17":"0","wen18":"0","wen19":"0","wen20":"0","wen21":"0","wen22":"0","wen23":"0","wen24":"0","wen25":"0","wen26":"0","wen27":"0","wen28":"0","wen29":"0","wen30":"0","wen31":"0","wen32":"0","wen33":"0","wen34":"0","ctime":"1477731581","skill_ren":[{"id":"20","sid":"39","is_ren":"1","zname":"","zimg":["http://hwgj.zai0312.com/Uploads/2016-10-29/581465e7081f6.png"],"ctime":"0"}]},{"id":"41","uid":"3","type":"32","order_num":"0","class":"","advantage":"","is_recommend":"0","is_audit":"1","is_ji":"1","is_ren":"0","wen1":"0","wen2":"0","wen3":"0","wen4":"0","wen5":"0","wen6":"0","wen7":"0","wen8":"0","wen9":"0","wen10":"0","wen11":"0","wen12":"0","wen13":"0","wen14":"0","wen15":"0","wen16":"0","wen17":"0","wen18":"0","wen19":"0","wen20":"0","wen21":"0","wen22":"0","wen23":"0","wen24":"0","wen25":"0","wen26":"0","wen27":"0","wen28":"0","wen29":"0","wen30":"0","wen31":"0","wen32":"0","wen33":"0","wen34":"0","ctime":"0","skill_ren":[]},{"id":"42","uid":"3","type":"33","order_num":"0","class":"","advantage":"","is_recommend":"0","is_audit":"1","is_ji":"1","is_ren":"0","wen1":"0","wen2":"0","wen3":"0","wen4":"0","wen5":"0","wen6":"0","wen7":"0","wen8":"0","wen9":"0","wen10":"0","wen11":"0","wen12":"0","wen13":"0","wen14":"0","wen15":"0","wen16":"0","wen17":"0","wen18":"0","wen19":"0","wen20":"0","wen21":"0","wen22":"0","wen23":"0","wen24":"0","wen25":"0","wen26":"0","wen27":"0","wen28":"0","wen29":"0","wen30":"0","wen31":"0","wen32":"0","wen33":"0","wen34":"0","ctime":"0","skill_ren":[]},{"id":"43","uid":"3","type":"34","order_num":"0","class":"","advantage":"","is_recommend":"0","is_audit":"1","is_ji":"1","is_ren":"0","wen1":"0","wen2":"0","wen3":"0","wen4":"0","wen5":"0","wen6":"0","wen7":"0","wen8":"0","wen9":"0","wen10":"0","wen11":"0","wen12":"0","wen13":"0","wen14":"0","wen15":"0","wen16":"0","wen17":"0","wen18":"0","wen19":"0","wen20":"0","wen21":"0","wen22":"0","wen23":"0","wen24":"0","wen25":"0","wen26":"0","wen27":"0","wen28":"0","wen29":"0","wen30":"0","wen31":"0","wen32":"0","wen33":"0","wen34":"0","ctime":"0","skill_ren":[]}]
     * user_idcard : {"uid":"3","idcard_shou":"http://hwgj.zai0312.com/Uploads/2016-10-17/5804a67c30105.png","idcard_zheng":"http://hwgj.zai0312.com/Uploads/2016-10-17/5804a67c3094f.png","idcard_fan":"http://hwgj.zai0312.com/Uploads/2016-10-17/5804a6df75624.png","idcard_ren":"2"}
     */

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
         * uid : 3
         * idcard_shou : http://hwgj.zai0312.com/Uploads/2016-10-17/5804a67c30105.png
         * idcard_zheng : http://hwgj.zai0312.com/Uploads/2016-10-17/5804a67c3094f.png
         * idcard_fan : http://hwgj.zai0312.com/Uploads/2016-10-17/5804a6df75624.png
         * idcard_ren : 2
         */

        private UserIdcardBean user_idcard;
        /**
         * id : 38
         * uid : 3
         * type : 1
         * order_num : 0
         * class : 测试的的的的的的的的的的的的的的的的的的的的的额
         * advantage : 测试
         * is_recommend : 0
         * is_audit : 1
         * is_ji : 1
         * is_ren : 0
         * wen1 : 0
         * wen2 : 1
         * wen3 : 1
         * wen4 : 0
         * wen5 : 0
         * wen6 : 0
         * wen7 : 0
         * wen8 : 0
         * wen9 : 0
         * wen10 : 0
         * wen11 : 0
         * wen12 : 0
         * wen13 : 0
         * wen14 : 0
         * wen15 : 0
         * wen16 : 0
         * wen17 : 0
         * wen18 : 0
         * wen19 : 0
         * wen20 : 0
         * wen21 : 0
         * wen22 : 0
         * wen23 : 0
         * wen24 : 0
         * wen25 : 0
         * wen26 : 0
         * wen27 : 0
         * wen28 : 0
         * wen29 : 0
         * wen30 : 0
         * wen31 : 0
         * wen32 : 0
         * wen33 : 0
         * wen34 : 0
         * ctime : 1477726752
         * skill_ren : [{"id":"19","sid":"38","is_ren":"1","zname":"测试","zimg":["http://hwgj.zai0312.com/Uploads/2016-10-29/5814153f42b2e.png"],"ctime":"0"},{"id":"21","sid":"38","is_ren":"1","zname":"","zimg":["http://hwgj.zai0312.com/Uploads/2016-10-29/581466029d602.png"],"ctime":"0"}]
         */

        private List<UserSkillBean> user_skill;

        public UserIdcardBean getUser_idcard() {
            return user_idcard;
        }

        public void setUser_idcard(UserIdcardBean user_idcard) {
            this.user_idcard = user_idcard;
        }

        public List<UserSkillBean> getUser_skill() {
            return user_skill;
        }

        public void setUser_skill(List<UserSkillBean> user_skill) {
            this.user_skill = user_skill;
        }

        public static class UserIdcardBean {
            private String uid;
            private String idcard_shou;
            private String idcard_zheng;
            private String idcard_fan;
            private String idcard_ren;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getIdcard_shou() {
                return idcard_shou;
            }

            public void setIdcard_shou(String idcard_shou) {
                this.idcard_shou = idcard_shou;
            }

            public String getIdcard_zheng() {
                return idcard_zheng;
            }

            public void setIdcard_zheng(String idcard_zheng) {
                this.idcard_zheng = idcard_zheng;
            }

            public String getIdcard_fan() {
                return idcard_fan;
            }

            public void setIdcard_fan(String idcard_fan) {
                this.idcard_fan = idcard_fan;
            }

            public String getIdcard_ren() {
                return idcard_ren;
            }

            public void setIdcard_ren(String idcard_ren) {
                this.idcard_ren = idcard_ren;
            }
        }

        public static class UserSkillBean {
            private String id;
            private String uid;
            private String type;
            private String order_num;
            @SerializedName("class")
            private String classX;
            private String advantage;
            private String is_recommend;
            private String is_audit;
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
            /**
             * id : 19
             * sid : 38
             * is_ren : 1
             * zname : 测试
             * zimg : ["http://hwgj.zai0312.com/Uploads/2016-10-29/5814153f42b2e.png"]
             * ctime : 0
             */

            private List<SkillRenBean> skill_ren;

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

            public List<SkillRenBean> getSkill_ren() {
                return skill_ren;
            }

            public void setSkill_ren(List<SkillRenBean> skill_ren) {
                this.skill_ren = skill_ren;
            }

            public static class SkillRenBean {
                private String id;
                private String sid;
                private String is_ren;
                private String zname;
                private String ctime;
                private List<String> zimg;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getSid() {
                    return sid;
                }

                public void setSid(String sid) {
                    this.sid = sid;
                }

                public String getIs_ren() {
                    return is_ren;
                }

                public void setIs_ren(String is_ren) {
                    this.is_ren = is_ren;
                }

                public String getZname() {
                    return zname;
                }

                public void setZname(String zname) {
                    this.zname = zname;
                }

                public String getCtime() {
                    return ctime;
                }

                public void setCtime(String ctime) {
                    this.ctime = ctime;
                }

                public List<String> getZimg() {
                    return zimg;
                }

                public void setZimg(List<String> zimg) {
                    this.zimg = zimg;
                }
            }
        }
    }
}
