package com.haiwai.housekeeper.entity;

/**
 * Created by Administrator on 2018/4/12.
 */

public class ChargeEntity {
    /**
     * status : 200
     * data : {"vacancy":"99.00","bai":"8","price":"10.00","img":"http://hwgj.zai0312.com/Uploads/2017-01-19/58809c62116b7.png","o2o_bai":"50.00","self_bai":"0"}
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
         * vacancy : 99.00
         * bai : 8
         * price : 10.00
         * img : http://hwgj.zai0312.com/Uploads/2017-01-19/58809c62116b7.png
         * o2o_bai : 50.00
         * self_bai : 0
         */

        private String vacancy;
        private String bai;
        private String price;
        private String img;
        private String o2o_bai;
        private String self_bai;

        public String getVacancy() {
            return vacancy;
        }

        public void setVacancy(String vacancy) {
            this.vacancy = vacancy;
        }

        public String getBai() {
            return bai;
        }

        public void setBai(String bai) {
            this.bai = bai;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getO2o_bai() {
            return o2o_bai;
        }

        public void setO2o_bai(String o2o_bai) {
            this.o2o_bai = o2o_bai;
        }

        public String getSelf_bai() {
            return self_bai;
        }

        public void setSelf_bai(String self_bai) {
            this.self_bai = self_bai;
        }
    }

}
