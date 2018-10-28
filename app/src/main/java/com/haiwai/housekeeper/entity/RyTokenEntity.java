package com.haiwai.housekeeper.entity;

/**
 * Created by ihope006 on 2016/11/22.
 */

public class RyTokenEntity {

    /**
     * data : {"code":200,"token":"ihEr434CEUnPG8u3NyQtw1iF6c7PiB8alMd+UAGb2XlnPv6Y37lbhCbOM6rPrwJw31mfTeAIf6xnmF/y5NLSCg==","userId":"3"}
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
        /**
         * code : 200
         * token : ihEr434CEUnPG8u3NyQtw1iF6c7PiB8alMd+UAGb2XlnPv6Y37lbhCbOM6rPrwJw31mfTeAIf6xnmF/y5NLSCg==
         * userId : 3
         */

        private int code;
        private String token;
        private String userId;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
