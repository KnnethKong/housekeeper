package com.haiwai.housekeeper.entity;

import java.util.List;

/**
 * Created by ihope006 on 2017/2/6.
 */

public class CodesEntity {
    /**
     * status : 200
     * data : [{"namecn":"加拿大","nameen":"Canada","code":"1"},{"namecn":"中国大陆","nameen":"China","code":"86"},{"namecn":"香港","nameen":"Hong Kong","code":"852"},{"namecn":"澳门","nameen":"Macao","code":"853"},{"namecn":"台湾","nameen":"Taiwan","code":"886"}]
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
         * namecn : 加拿大
         * nameen : Canada
         * code : 1
         */

        private String namecn;
        private String nameen;
        private String code;

        public String getNamecn() {
            return namecn;
        }

        public void setNamecn(String namecn) {
            this.namecn = namecn;
        }

        public String getNameen() {
            return nameen;
        }

        public void setNameen(String nameen) {
            this.nameen = nameen;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
