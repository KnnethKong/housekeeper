package com.haiwai.housekeeper.entity;

import java.util.List;

/**
 * Created by ihope007 on 2016/12/2.
 */
public class WalletRechargeEntity {

    /**
     * status : 200
     * data : {"balance":"5000.00","date":[{"id":"24","uid":"94","type":"4","money":"5000.00","orderid":"59007b198b1cc","zhiq_money":"0.00","zhih_money":"-5000","ctime":"1493203737"}]}
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
         * balance : 5000.00
         * date : [{"id":"24","uid":"94","type":"4","money":"5000.00","orderid":"59007b198b1cc","zhiq_money":"0.00","zhih_money":"-5000","ctime":"1493203737"}]
         */

        private String balance;
        private List<DateBean> date;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public List<DateBean> getDate() {
            return date;
        }

        public void setDate(List<DateBean> date) {
            this.date = date;
        }

        public static class DateBean {
            /**
             * id : 24
             * uid : 94
             * type : 4
             * money : 5000.00
             * orderid : 59007b198b1cc
             * zhiq_money : 0.00
             * zhih_money : -5000
             * ctime : 1493203737
             */

            private String id;
            private String uid;
            private String type;
            private String money;
            private String orderid;
            private String zhiq_money;
            private String zhih_money;
            private String ctime;

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

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getOrderid() {
                return orderid;
            }

            public void setOrderid(String orderid) {
                this.orderid = orderid;
            }

            public String getZhiq_money() {
                return zhiq_money;
            }

            public void setZhiq_money(String zhiq_money) {
                this.zhiq_money = zhiq_money;
            }

            public String getZhih_money() {
                return zhih_money;
            }

            public void setZhih_money(String zhih_money) {
                this.zhih_money = zhih_money;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }
        }
    }
}
