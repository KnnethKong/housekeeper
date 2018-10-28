package com.haiwai.housekeeper.entity;

/**
 * Created by ihope10 on 2016/11/8.
 */

public class PriceEntity {

    /**
     * status : 200
     * data : {"id":"23","oid":"18","uid":"3","home_fee":"123","inspection":"123","service_type":"2","hourly":"0","hour":"0","general":"123","material":"0","message":"测试123","is_xuan":"0","ctime":"1478587543"}
     */

    private int status;
    /**
     * id : 23
     * oid : 18
     * uid : 3
     * home_fee : 123
     * inspection : 123
     * service_type : 2
     * hourly : 0
     * hour : 0
     * general : 123
     * material : 0
     * message : 测试123
     * is_xuan : 0
     * ctime : 1478587543
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
        private String id;
        private String oid;
        private String uid;
        private String home_fee;
        private String inspection;
        private String service_type;
        private String hourly;
        private String hour;
        private String general;
        private String material;
        private String message;
        private String is_xuan;
        private String ctime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getHome_fee() {
            return home_fee;
        }

        public void setHome_fee(String home_fee) {
            this.home_fee = home_fee;
        }

        public String getInspection() {
            return inspection;
        }

        public void setInspection(String inspection) {
            this.inspection = inspection;
        }

        public String getService_type() {
            return service_type;
        }

        public void setService_type(String service_type) {
            this.service_type = service_type;
        }

        public String getHourly() {
            return hourly;
        }

        public void setHourly(String hourly) {
            this.hourly = hourly;
        }

        public String getHour() {
            return hour;
        }

        public void setHour(String hour) {
            this.hour = hour;
        }

        public String getGeneral() {
            return general;
        }

        public void setGeneral(String general) {
            this.general = general;
        }

        public String getMaterial() {
            return material;
        }

        public void setMaterial(String material) {
            this.material = material;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getIs_xuan() {
            return is_xuan;
        }

        public void setIs_xuan(String is_xuan) {
            this.is_xuan = is_xuan;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }
    }
}
