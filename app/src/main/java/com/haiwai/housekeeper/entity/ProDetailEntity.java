package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ihope007 on 2016/10/19.
 */
public class ProDetailEntity {


    /**
     * status : 200
     * data : {"info":{"uid":"26","sex":"0","avatar":"http://hwgj.zai0312.com/Uploads/2017-02-14/58a2a5f8b2be2.png","nickname":"绿萝","mobile":"15933975930","name":"Fiona","introduction":"我是卖报的小行家","is_ren":"1","is_hou":"0","only_label":"d71488","balance":"0.00","address":"","pro_quci":"0","user_quci":"0","pro_score":"13","user_score":"0","pro_xing":"10","pro_onum":"2","user_xing":"5","user_onum":"1","follow_num":"6","fans_num":"3","lat":"40.0483","long":"116.294253","is_don":"0","ltime":"1487828682","registrationid":"18071adc030240f2b2b","ctime":"1480667575","version":"2","system":"0","pro_lat":"49.2829167","pro_long":"-122.7978345","login_key":"###e0786b3f67bcbae5094c0ebc77e2c31e","km":"9455.616319064544","is_guan":0},"skill":{"id":"135","uid":"26","type":"1","order_num":"12","xing_num":"0","class":"专业保洁保洁员的话也可以的呢你了光的事儿了我的手机哦移民亏哦之类好哦。","advantage":"你低估lz咯going咯挺累knives。","is_recommend":"0","is_audit":"1","v":"2","is_ji":"1","is_ren":"0","wen1":"0","wen2":"0","wen3":"0","wen4":"0","wen5":"0","wen6":"0","wen7":"0","wen8":"0","wen9":"0","wen10":"0","wen11":"0","wen12":"0","wen13":"0","wen14":"0","wen15":"0","wen16":"0","wen17":"0","wen18":"0","wen19":"0","wen20":"0","wen21":"0","wen22":"0","wen23":"0","wen24":"0","wen25":"0","wen26":"0","wen27":"0","wen28":"0","wen29":"0","wen30":"0","wen31":"0","wen32":"0","wen33":"0","wen34":"0","ctime":"1484038693","money_p":0},"idcard":{"id":"8","uid":"26","idcard_shou":"0","idcard_zheng":"0","idcard_fan":"0","idcard_ren":"1"},"photo":[{"id":"61","uid":"26","img":"http://hwgj.zai0312.com/Uploads/2017-01-11/5875d6dcbdfc8.png","desc":"专业的服务专家","ctime":"1484117724"}],"comment":[{"id":"34","type":"2","oid":"280","ouid":"26","uid":"26","content":"非常棒","xin":"5","l_id":"31,32,34","ctime":"1484532786"},{"id":"42","type":"2","oid":"424","ouid":"38","uid":"26","content":"阿富汗","xin":"5","l_id":"35,36","ctime":"1485004073"},{"id":"54","type":"2","oid":"452","ouid":"38","uid":"26","content":"","xin":"5","l_id":"31,35","ctime":"1485223832"},{"id":"57","type":"2","oid":"454","ouid":"38","uid":"26","content":"棒棒哒","xin":"5","l_id":"34,36","ctime":"1485236592"},{"id":"62","type":"2","oid":"472","ouid":"38","uid":"26","content":"很棒","xin":"4","l_id":"24,26","ctime":"1487058295"},{"id":"63","type":"2","oid":"422","ouid":"38","uid":"26","content":"很棒","xin":"5","l_id":"34,36","ctime":"1487062512"},{"id":"69","type":"2","oid":"479","ouid":"38","uid":"26","content":"很棒，灰常负责任","xin":"5","l_id":"31,32,35","ctime":"1487142979"},{"id":"70","type":"2","oid":"503","ouid":"38","uid":"26","content":"厉害，服务周到","xin":"5","l_id":"","ctime":"1487149025"},{"id":"76","type":"2","oid":"510","ouid":"38","uid":"26","content":"棒棒哒~","xin":"4","l_id":"27,28","ctime":"1487212933"},{"id":"80","type":"2","oid":"518","ouid":"38","uid":"26","content":"","xin":"4","l_id":"24,25,27","ctime":"1487302793"}],"liao":1}
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
         * info : {"uid":"26","sex":"0","avatar":"http://hwgj.zai0312.com/Uploads/2017-02-14/58a2a5f8b2be2.png","nickname":"绿萝","mobile":"15933975930","name":"Fiona","introduction":"我是卖报的小行家","is_ren":"1","is_hou":"0","only_label":"d71488","balance":"0.00","address":"","pro_quci":"0","user_quci":"0","pro_score":"13","user_score":"0","pro_xing":"10","pro_onum":"2","user_xing":"5","user_onum":"1","follow_num":"6","fans_num":"3","lat":"40.0483","long":"116.294253","is_don":"0","ltime":"1487828682","registrationid":"18071adc030240f2b2b","ctime":"1480667575","version":"2","system":"0","pro_lat":"49.2829167","pro_long":"-122.7978345","login_key":"###e0786b3f67bcbae5094c0ebc77e2c31e","km":"9455.616319064544","is_guan":0}
         * skill : {"id":"135","uid":"26","type":"1","order_num":"12","xing_num":"0","class":"专业保洁保洁员的话也可以的呢你了光的事儿了我的手机哦移民亏哦之类好哦。","advantage":"你低估lz咯going咯挺累knives。","is_recommend":"0","is_audit":"1","v":"2","is_ji":"1","is_ren":"0","wen1":"0","wen2":"0","wen3":"0","wen4":"0","wen5":"0","wen6":"0","wen7":"0","wen8":"0","wen9":"0","wen10":"0","wen11":"0","wen12":"0","wen13":"0","wen14":"0","wen15":"0","wen16":"0","wen17":"0","wen18":"0","wen19":"0","wen20":"0","wen21":"0","wen22":"0","wen23":"0","wen24":"0","wen25":"0","wen26":"0","wen27":"0","wen28":"0","wen29":"0","wen30":"0","wen31":"0","wen32":"0","wen33":"0","wen34":"0","ctime":"1484038693","money_p":0}
         * idcard : {"id":"8","uid":"26","idcard_shou":"0","idcard_zheng":"0","idcard_fan":"0","idcard_ren":"1"}
         * photo : [{"id":"61","uid":"26","img":"http://hwgj.zai0312.com/Uploads/2017-01-11/5875d6dcbdfc8.png","desc":"专业的服务专家","ctime":"1484117724"}]
         * comment : [{"id":"34","type":"2","oid":"280","ouid":"26","uid":"26","content":"非常棒","xin":"5","l_id":"31,32,34","ctime":"1484532786"},{"id":"42","type":"2","oid":"424","ouid":"38","uid":"26","content":"阿富汗","xin":"5","l_id":"35,36","ctime":"1485004073"},{"id":"54","type":"2","oid":"452","ouid":"38","uid":"26","content":"","xin":"5","l_id":"31,35","ctime":"1485223832"},{"id":"57","type":"2","oid":"454","ouid":"38","uid":"26","content":"棒棒哒","xin":"5","l_id":"34,36","ctime":"1485236592"},{"id":"62","type":"2","oid":"472","ouid":"38","uid":"26","content":"很棒","xin":"4","l_id":"24,26","ctime":"1487058295"},{"id":"63","type":"2","oid":"422","ouid":"38","uid":"26","content":"很棒","xin":"5","l_id":"34,36","ctime":"1487062512"},{"id":"69","type":"2","oid":"479","ouid":"38","uid":"26","content":"很棒，灰常负责任","xin":"5","l_id":"31,32,35","ctime":"1487142979"},{"id":"70","type":"2","oid":"503","ouid":"38","uid":"26","content":"厉害，服务周到","xin":"5","l_id":"","ctime":"1487149025"},{"id":"76","type":"2","oid":"510","ouid":"38","uid":"26","content":"棒棒哒~","xin":"4","l_id":"27,28","ctime":"1487212933"},{"id":"80","type":"2","oid":"518","ouid":"38","uid":"26","content":"","xin":"4","l_id":"24,25,27","ctime":"1487302793"}]
         * liao : 1
         */

        private InfoBean info;
        private SkillBean skill;
        private IdcardBean idcard;
        private int liao;
        private List<PhotoBean> photo;
        private List<CommentBean> comment;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public SkillBean getSkill() {
            return skill;
        }

        public void setSkill(SkillBean skill) {
            this.skill = skill;
        }

        public IdcardBean getIdcard() {
            return idcard;
        }

        public void setIdcard(IdcardBean idcard) {
            this.idcard = idcard;
        }

        public int getLiao() {
            return liao;
        }

        public void setLiao(int liao) {
            this.liao = liao;
        }

        public List<PhotoBean> getPhoto() {
            return photo;
        }

        public void setPhoto(List<PhotoBean> photo) {
            this.photo = photo;
        }

        public List<CommentBean> getComment() {
            return comment;
        }

        public void setComment(List<CommentBean> comment) {
            this.comment = comment;
        }

        public static class InfoBean {
            /**
             * uid : 26
             * sex : 0
             * avatar : http://hwgj.zai0312.com/Uploads/2017-02-14/58a2a5f8b2be2.png
             * nickname : 绿萝
             * mobile : 15933975930
             * name : Fiona
             * introduction : 我是卖报的小行家
             * is_ren : 1
             * is_hou : 0
             * only_label : d71488
             * balance : 0.00
             * address :
             * pro_quci : 0
             * user_quci : 0
             * pro_score : 13
             * user_score : 0
             * pro_xing : 10
             * pro_onum : 2
             * user_xing : 5
             * user_onum : 1
             * follow_num : 6
             * fans_num : 3
             * lat : 40.0483
             * long : 116.294253
             * is_don : 0
             * ltime : 1487828682
             * registrationid : 18071adc030240f2b2b
             * ctime : 1480667575
             * version : 2
             * system : 0
             * pro_lat : 49.2829167
             * pro_long : -122.7978345
             * login_key : ###e0786b3f67bcbae5094c0ebc77e2c31e
             * km : 9455.616319064544
             * is_guan : 0
             */

            private String uid;
            private String sex;
            private String avatar;
            private String nickname;
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
            private String pro_xing;
//            public String s;
            private String pro_onum;
            private String user_xing;
            private String user_onum;
            private String follow_num;
            private String fans_num;
            private String lat;
            @SerializedName("long")
            private String longX;
            private String is_don;
            private String ltime;
            private String registrationid;
            private String ctime;
            private String version;
            private String system;
            private String pro_lat;
            private String pro_long;
            private String login_key;
            private String km;
            private int is_guan;

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

            public String getUser_xing() {
                return user_xing;
            }

            public void setUser_xing(String user_xing) {
                this.user_xing = user_xing;
            }

            public String getUser_onum() {
                return user_onum;
            }

            public void setUser_onum(String user_onum) {
                this.user_onum = user_onum;
            }

            public String getFollow_num() {
                return follow_num;
            }

            public void setFollow_num(String follow_num) {
                this.follow_num = follow_num;
            }

            public String getFans_num() {
                return fans_num;
            }

            public void setFans_num(String fans_num) {
                this.fans_num = fans_num;
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

            public String getIs_don() {
                return is_don;
            }

            public void setIs_don(String is_don) {
                this.is_don = is_don;
            }

            public String getLtime() {
                return ltime;
            }

            public void setLtime(String ltime) {
                this.ltime = ltime;
            }

            public String getRegistrationid() {
                return registrationid;
            }

            public void setRegistrationid(String registrationid) {
                this.registrationid = registrationid;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getSystem() {
                return system;
            }

            public void setSystem(String system) {
                this.system = system;
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

            public String getLogin_key() {
                return login_key;
            }

            public void setLogin_key(String login_key) {
                this.login_key = login_key;
            }

            public String getKm() {
                return km;
            }

            public void setKm(String km) {
                this.km = km;
            }

            public int getIs_guan() {
                return is_guan;
            }

            public void setIs_guan(int is_guan) {
                this.is_guan = is_guan;
            }
        }

        public static class SkillBean {
            /**
             * id : 135
             * uid : 26
             * type : 1
             * order_num : 12
             * xing_num : 0
             * class : 专业保洁保洁员的话也可以的呢你了光的事儿了我的手机哦移民亏哦之类好哦。
             * advantage : 你低估lz咯going咯挺累knives。
             * is_recommend : 0
             * is_audit : 1
             * v : 2
             * is_ji : 1
             * is_ren : 0
             * wen1 : 0
             * wen2 : 0
             * wen3 : 0
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
             * ctime : 1484038693
             * money_p : 0
             */

            private String id;
            private String uid;
            private String type;
            private String pro_onum;
            private String pro_xing;
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
            private int money_p;

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
                return pro_onum;
            }

            public void setOrder_num(String order_num) {
                this.pro_onum = order_num;
            }

            public String getXing_num() {
                return pro_xing;
            }

            public void setXing_num(String xing_num) {
                this.pro_xing = xing_num;
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

            public int getMoney_p() {
                return money_p;
            }

            public void setMoney_p(int money_p) {
                this.money_p = money_p;
            }
        }

        public static class IdcardBean {
            /**
             * id : 8
             * uid : 26
             * idcard_shou : 0
             * idcard_zheng : 0
             * idcard_fan : 0
             * idcard_ren : 1
             */

            private String id;
            private String uid;
            private String idcard_shou;
            private String idcard_zheng;
            private String idcard_fan;
            private String idcard_ren;

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

        public static class PhotoBean {
            /**
             * id : 61
             * uid : 26
             * img : http://hwgj.zai0312.com/Uploads/2017-01-11/5875d6dcbdfc8.png
             * desc : 专业的服务专家
             * ctime : 1484117724
             */

            private String id;
            private String uid;
            private String img;
            private String desc;
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

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }
        }

        public static class CommentBean {
            /**
             * id : 34
             * type : 2
             * oid : 280
             * ouid : 26
             * uid : 26
             * content : 非常棒
             * xin : 5
             * l_id : 31,32,34
             * ctime : 1484532786
             */

            private String id;
            private String type;
            private String oid;
            private String ouid;
            private String uid;
            private String content;
            private String xin;
            private String l_id;
            private String ctime;

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

            public String getOid() {
                return oid;
            }

            public void setOid(String oid) {
                this.oid = oid;
            }

            public String getOuid() {
                return ouid;
            }

            public void setOuid(String ouid) {
                this.ouid = ouid;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getXin() {
                return xin;
            }

            public void setXin(String xin) {
                this.xin = xin;
            }

            public String getL_id() {
                return l_id;
            }

            public void setL_id(String l_id) {
                this.l_id = l_id;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }
        }
    }
}
