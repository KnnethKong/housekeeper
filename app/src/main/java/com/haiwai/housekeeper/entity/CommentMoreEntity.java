package com.haiwai.housekeeper.entity;

import java.util.List;

/**
 * Created by ihope007 on 2016/11/16.
 */
public class CommentMoreEntity {

    /**
     * status : 200
     * data : [{"id":"3","type":"1","oid":"19","ouid":"2","uid":"3","content":"测试","xin":"1","l_id":"1,2","ctime":"1478601896"},{"id":"8","type":"2","oid":"19","ouid":"2","uid":"3","content":"用户测试","xin":"1","l_id":"1,4","ctime":"1479177869"},{"id":"10","type":"1","oid":"55","ouid":"2","uid":"3","content":"123","xin":"2","l_id":"1","ctime":"1479283984"},{"id":"11","type":"2","oid":"55","ouid":"2","uid":"3","content":"老街机","xin":"5","l_id":"1,3","ctime":"1479284137"}]
     */

    private int status;
    /**
     * id : 3
     * type : 1
     * oid : 19
     * ouid : 2
     * uid : 3
     * content : 测试
     * xin : 1
     * l_id : 1,2
     * ctime : 1478601896
     */

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
