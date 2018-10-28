package com.haiwai.housekeeper.entity;

import java.util.List;

/**
 * Created by ihope006 on 2016/12/21.
 */

public class VoucherEntity {

    /**
     * status : 200
     * data : [{"id":"3","uid":"3","type":"27","stime":"1480521600","etime":"1483199999"},{"id":"4","uid":"3","type":"28","stime":"1480521600","etime":"1483199999"}]
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
         * id : 3
         * uid : 3
         * type : 27
         * stime : 1480521600
         * etime : 1483199999
         */

        private String id;
        private String uid;
        private String type;
        private String stime;
        private String etime;

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

        public String getStime() {
            return stime;
        }

        public void setStime(String stime) {
            this.stime = stime;
        }

        public String getEtime() {
            return etime;
        }

        public void setEtime(String etime) {
            this.etime = etime;
        }
    }
}
