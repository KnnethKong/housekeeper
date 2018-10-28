package com.haiwai.housekeeper.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/12.
 */

public class ShouKuangxinxiEntity {
    /**
     * status : 200
     * data : [{"uid":"97","mobile":"15933975930","email":"1009012322","bankname":"中国银行","payee":"绿箩","banktransit":"123","institurion":"1234","account_number":"123156"},{"uid":"97","mobile":"15933975930","email":"1009012322","bankname":"中国银行","payee":"绿箩","banktransit":"123","institurion":"1234","account_number":"123156"},{"uid":"97","mobile":"15933975930","email":"1009012322","bankname":"中国银行","payee":"库存","banktransit":"1","institurion":"2","account_number":"123456"},{"uid":"97","mobile":"15933975930","email":"123456","bankname":"银行","payee":"回来了","banktransit":"123","institurion":"123","account_number":"123456789"},{"uid":"97","mobile":"15933975930","email":"123456","bankname":"银行","payee":"回来了","banktransit":"123","institurion":"123","account_number":"123456789"},{"uid":"97","mobile":"15933975930","email":"123456","bankname":"银行","payee":"回来了","banktransit":"123","institurion":"123","account_number":"123456789"},{"uid":"97","mobile":"15933975930","email":"123456","bankname":"146646","payee":"明明","banktransit":"123","institurion":"123","account_number":"456789"},{"uid":"97","mobile":"15933975930","email":"123456","bankname":"123","payee":"绿萝","banktransit":"5867","institurion":"5879","account_number":"8645699"},{"uid":"97","mobile":"13121327262","email":"1223","bankname":"1","payee":"1","banktransit":"1","institurion":"1","account_number":"1"},{"uid":"97","mobile":"11","email":"1","bankname":"1","payee":"1","banktransit":"1","institurion":"1","account_number":"1"},{"uid":"97","mobile":"15933975930","email":"1009012322","bankname":"中国银行","payee":"绿萝","banktransit":"12345","institurion":"123456","account_number":"123456789"},{"uid":"97","mobile":"11","email":"1","bankname":"3","payee":"1","banktransit":"1","institurion":"1","account_number":"1"},{"uid":"97","mobile":"1","email":"1","bankname":"1","payee":"1","banktransit":"1","institurion":"1","account_number":"1"},{"uid":"97","mobile":"1","email":"1","bankname":"1","payee":"1","banktransit":"1","institurion":"1","account_number":"1"}]
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
         * uid : 97
         * mobile : 15933975930
         * email : 1009012322
         * bankname : 中国银行
         * payee : 绿箩
         * banktransit : 123
         * institurion : 1234
         * account_number : 123156
         */

        private String uid;
        private String mobile;
        private String email;
        private String bankname;
        private String payee;
        private String banktransit;
        private String institurion;
        private String account_number;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getBankname() {
            return bankname;
        }

        public void setBankname(String bankname) {
            this.bankname = bankname;
        }

        public String getPayee() {
            return payee;
        }

        public void setPayee(String payee) {
            this.payee = payee;
        }

        public String getBanktransit() {
            return banktransit;
        }

        public void setBanktransit(String banktransit) {
            this.banktransit = banktransit;
        }

        public String getInstiturion() {
            return institurion;
        }

        public void setInstiturion(String institurion) {
            this.institurion = institurion;
        }

        public String getAccount_number() {
            return account_number;
        }

        public void setAccount_number(String account_number) {
            this.account_number = account_number;
        }
    }
}
