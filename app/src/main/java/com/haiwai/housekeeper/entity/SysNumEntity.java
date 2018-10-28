package com.haiwai.housekeeper.entity;

/**
 * Created by ihope006 on 2017/2/10.
 */

public class SysNumEntity {
    /**
     * status : 200
     * data : {"uid":"3","follow":0,"system":0,"pro_system":0}
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
         * uid : 3
         * follow : 0
         * system : 0
         * pro_system : 0
         */

        private String uid;
        private int follow;
        private int system;
        private int pro_system;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public int getSystem() {
            return system;
        }

        public void setSystem(int system) {
            this.system = system;
        }

        public int getPro_system() {
            return pro_system;
        }

        public void setPro_system(int pro_system) {
            this.pro_system = pro_system;
        }
    }
}
