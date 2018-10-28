package com.haiwai.housekeeper.entity;

import java.util.List;

/**
 * Created by ihope006 on 2017/5/24.
 */

public class CommnetEntity {


    /**
     * status : 200
     * data : [{"id":"218","type":"2","oid":"1299","ouid":"96","uid":"97","content":"","xin":"5","l_id":"32,34","ctime":"1492681544"},{"id":"219","type":"1","oid":"1299","ouid":"96","uid":"97","content":"客户不错","xin":"4","l_id":"","ctime":"1492681564"},{"id":"220","type":"2","oid":"1300","ouid":"96","uid":"97","content":"","xin":"5","l_id":"32,31","ctime":"1492681969"},{"id":"221","type":"1","oid":"1300","ouid":"96","uid":"97","content":"客户很棒","xin":"5","l_id":"","ctime":"1492681992"},{"id":"235","type":"2","oid":"1497","ouid":"102","uid":"97","content":"","xin":"3","l_id":"22,20,19","ctime":"1493805150"},{"id":"236","type":"1","oid":"1497","ouid":"102","uid":"97","content":"","xin":"5","l_id":"","ctime":"1493970372"},{"id":"237","type":"1","oid":"1510","ouid":"102","uid":"97","content":"","xin":"4","l_id":"","ctime":"1493971841"},{"id":"238","type":"2","oid":"1510","ouid":"102","uid":"97","content":"绿萝专家的服务很不错，下次还找她修理","xin":"5","l_id":"32,34,36","ctime":"1493971972"},{"id":"239","type":"1","oid":"1557","ouid":"100","uid":"97","content":"顾客很客气，这是pro对顾客的评价","xin":"5","l_id":"","ctime":"1494568851"},{"id":"240","type":"2","oid":"1557","ouid":"100","uid":"97","content":"这是顾客对专家的评价","xin":"5","l_id":"32,33,34,36","ctime":"1494568899"}]
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

    public static class DataBean {
        /**
         * id : 218
         * type : 2
         * oid : 1299
         * ouid : 96
         * uid : 97
         * content :
         * xin : 5
         * l_id : 32,34
         * ctime : 1492681544
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
