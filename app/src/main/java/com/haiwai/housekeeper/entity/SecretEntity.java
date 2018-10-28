package com.haiwai.housekeeper.entity;

/**
 * Created by ihope006 on 2017/2/15.
 */

public class SecretEntity {
    /**
     * status : 200
     * data : {"key":"596a41324e32566d4e446869597a637a5a546b774e47466c4d7a4d32596a5a6b5a444a694f4749334d6d4d3d","time":1487001600}
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
         * key : 596a41324e32566d4e446869597a637a5a546b774e47466c4d7a4d32596a5a6b5a444a694f4749334d6d4d3d
         * time : 1487001600
         */

        private String key;
        private int time;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
}
