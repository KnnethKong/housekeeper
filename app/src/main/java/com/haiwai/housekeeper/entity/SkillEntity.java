package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lyj on 2016/9/14.
 */
public class SkillEntity implements Serializable{
    /**
     * data : [{"advantage":"测试","class":"测试的的的的的的的的的的的的的的的的的的的的的额","ctime":"1477726752","id":"38","is_audit":"1","is_ji":"2","is_recommend":"0","is_ren":"0","order_num":"0","type":"1","uid":"3","v":"0","wen1":"0","wen10":"0","wen11":"0","wen12":"0","wen13":"0","wen14":"0","wen15":"0","wen16":"0","wen17":"0","wen18":"0","wen19":"0","wen2":"1","wen20":"0","wen21":"0","wen22":"0","wen23":"0","wen24":"0","wen25":"0","wen26":"0","wen27":"0","wen28":"0","wen29":"0","wen3":"1","wen30":"0","wen31":"0","wen32":"0","wen33":"0","wen34":"0","wen4":"0","wen5":"0","wen6":"0","wen7":"0","wen8":"0","wen9":"0","xing_num":"0"},{"advantage":"测试","class":"的的的的的的的的的的的的的的的的的的的的的的","ctime":"1477731581","id":"39","is_audit":"1","is_ji":"1","is_recommend":"0","is_ren":"0","order_num":"1","type":"2","uid":"3","v":"0","wen1":"0","wen10":"0","wen11":"0","wen12":"0","wen13":"0","wen14":"0","wen15":"0","wen16":"0","wen17":"0","wen18":"0","wen19":"0","wen2":"1","wen20":"0","wen21":"0","wen22":"0","wen23":"0","wen24":"0","wen25":"0","wen26":"0","wen27":"0","wen28":"0","wen29":"0","wen3":"0","wen30":"0","wen31":"0","wen32":"0","wen33":"0","wen34":"0","wen4":"0","wen5":"0","wen6":"0","wen7":"0","wen8":"0","wen9":"0","xing_num":"0"},{"advantage":"","class":"","ctime":"0","id":"41","is_audit":"1","is_ji":"2","is_recommend":"0","is_ren":"0","order_num":"0","type":"32","uid":"3","v":"0","wen1":"0","wen10":"0","wen11":"0","wen12":"0","wen13":"0","wen14":"0","wen15":"0","wen16":"0","wen17":"0","wen18":"0","wen19":"0","wen2":"0","wen20":"0","wen21":"0","wen22":"0","wen23":"0","wen24":"0","wen25":"0","wen26":"0","wen27":"0","wen28":"0","wen29":"0","wen3":"0","wen30":"0","wen31":"0","wen32":"0","wen33":"0","wen34":"0","wen4":"0","wen5":"0","wen6":"0","wen7":"0","wen8":"0","wen9":"0","xing_num":"0"},{"advantage":"","class":"","ctime":"0","id":"42","is_audit":"1","is_ji":"1","is_recommend":"0","is_ren":"0","order_num":"0","type":"33","uid":"3","v":"0","wen1":"0","wen10":"0","wen11":"0","wen12":"0","wen13":"0","wen14":"0","wen15":"0","wen16":"0","wen17":"0","wen18":"0","wen19":"0","wen2":"0","wen20":"0","wen21":"0","wen22":"0","wen23":"0","wen24":"0","wen25":"0","wen26":"0","wen27":"0","wen28":"0","wen29":"0","wen3":"0","wen30":"0","wen31":"0","wen32":"0","wen33":"0","wen34":"0","wen4":"0","wen5":"0","wen6":"0","wen7":"0","wen8":"0","wen9":"0","xing_num":"0"},{"advantage":"0","class":"0","ctime":"0","id":"43","is_audit":"0","is_ji":"1","is_recommend":"0","is_ren":"0","order_num":"3","type":"41","uid":"3","v":"0","wen1":"2","wen10":"0","wen11":"0","wen12":"0","wen13":"0","wen14":"0","wen15":"0","wen16":"0","wen17":"0","wen18":"0","wen19":"0","wen2":"0","wen20":"0","wen21":"0","wen22":"0","wen23":"0","wen24":"0","wen25":"0","wen26":"0","wen27":"0","wen28":"0","wen29":"0","wen3":"0","wen30":"0","wen31":"0","wen32":"0","wen33":"0","wen34":"0","wen4":"0","wen5":"0","wen6":"0","wen7":"0","wen8":"0","wen9":"0","xing_num":"32"}]
     * status : 200
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * advantage : 测试
         * class : 测试的的的的的的的的的的的的的的的的的的的的的额
         * ctime : 1477726752
         * id : 38
         * is_audit : 1
         * is_ji : 2
         * is_recommend : 0
         * is_ren : 0
         * order_num : 0
         * type : 1
         * uid : 3
         * v : 0
         * wen1 : 0
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
         * wen2 : 1
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
         * wen3 : 1
         * wen30 : 0
         * wen31 : 0
         * wen32 : 0
         * wen33 : 0
         * wen34 : 0
         * wen4 : 0
         * wen5 : 0
         * wen6 : 0
         * wen7 : 0
         * wen8 : 0
         * wen9 : 0
         * xing_num : 0
         */

        private String advantage;
        @SerializedName("class")
        private String classX;
        private String ctime;
        private String id;
        private String is_audit;
        private String is_ji;
        private String is_recommend;
        private String is_ren;
        private String order_num;
        private String type;
        private String uid;
        private String v;
        private String wen1;
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
        private String wen2;
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
        private String wen3;
        private String wen30;
        private String wen31;
        private String wen32;
        private String wen33;
        private String wen34;
        private String wen4;
        private String wen5;
        private String wen6;
        private String wen7;
        private String wen8;
        private String wen9;
        private String xing_num;

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

        public String getIs_ji() {
            return is_ji;
        }

        public void setIs_ji(String is_ji) {
            this.is_ji = is_ji;
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

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }

        public String getWen1() {
            return wen1;
        }

        public void setWen1(String wen1) {
            this.wen1 = wen1;
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

        public String getWen2() {
            return wen2;
        }

        public void setWen2(String wen2) {
            this.wen2 = wen2;
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

        public String getWen3() {
            return wen3;
        }

        public void setWen3(String wen3) {
            this.wen3 = wen3;
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

        public String getXing_num() {
            return xing_num;
        }

        public void setXing_num(String xing_num) {
            this.xing_num = xing_num;
        }
    }
}
