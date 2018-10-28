package com.haiwai.housekeeper.entity;

/**
 * Created by ihope006 on 2016/12/19.
 */

public class EvnEntity {

    /**
     * data : {"j_money":0,"j_month":1483200000,"k_money":"40","k_month":1483200000,"y_money":0,"y_month":0,"z_month":1483200000}
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

        private String j_money;
        private String j_month;
        private String k_money;
        private String k_month;
        private String y_money;
        private String y_month;
        private String z_month;

        public String getJ_money() {
            return j_money;
        }

        public void setJ_money(String j_money) {
            this.j_money = j_money;
        }

        public String getJ_month() {
            return j_month;
        }

        public void setJ_month(String j_month) {
            this.j_month = j_month;
        }

        public String getK_money() {
            return k_money;
        }

        public void setK_money(String k_money) {
            this.k_money = k_money;
        }

        public String getK_month() {
            return k_month;
        }

        public void setK_month(String k_month) {
            this.k_month = k_month;
        }

        public String getY_money() {
            return y_money;
        }

        public void setY_money(String y_money) {
            this.y_money = y_money;
        }

        public String getY_month() {
            return y_month;
        }

        public void setY_month(String y_month) {
            this.y_month = y_month;
        }

        public String getZ_month() {
            return z_month;
        }

        public void setZ_month(String z_month) {
            this.z_month = z_month;
        }
    }
}
