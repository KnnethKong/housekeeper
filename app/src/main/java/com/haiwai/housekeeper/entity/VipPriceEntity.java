package com.haiwai.housekeeper.entity;

/**
 * Created by 王宁 on 2016/11/15.
 */
public class VipPriceEntity {

    private String status;

    public VipPriceEntity() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        private String horticulture;
        private String vacancy;
        private String home;
        private String bai;

        public String getHorticulture() {
            return horticulture;
        }

        public void setHorticulture(String horticulture) {
            this.horticulture = horticulture;
        }

        public String getVacancy() {
            return vacancy;
        }

        public void setVacancy(String vacancy) {
            this.vacancy = vacancy;
        }

        public String getHome() {
            return home;
        }

        public void setHome(String home) {
            this.home = home;
        }

        public String getBai() {
            return bai;
        }

        public void setBai(String bai) {
            this.bai = bai;
        }
    }
}
