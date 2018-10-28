package com.haiwai.housekeeper.entity;

import java.util.List;

/**
 * Created by ihope006 on 2016/11/16.
 */

public class EvaDataEntity {
    /**
     * data : [{"content":"测试","ctime":"1478601896","id":"3","l_id":"1,2","oid":"19","ouid":"2","type":"1","uid":"3","xin":"1"},{"content":"用户测试","ctime":"1479177869","id":"8","l_id":"1,4","oid":"19","ouid":"2","type":"2","uid":"3","xin":"1"}]
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

    public static class DataBean {
        /**
         * content : 测试
         * ctime : 1478601896
         * id : 3
         * l_id : 1,2
         * oid : 19
         * ouid : 2
         * type : 1
         * uid : 3
         * xin : 1
         */

        private String content;
        private String ctime;
        private String id;
        private String l_id;
        private String oid;
        private String ouid;
        private String type;
        private String uid;
        private String xin;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public String getL_id() {
            return l_id;
        }

        public void setL_id(String l_id) {
            this.l_id = l_id;
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

        public String getXin() {
            return xin;
        }

        public void setXin(String xin) {
            this.xin = xin;
        }
    }
}
