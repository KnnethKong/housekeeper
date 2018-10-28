package com.haiwai.housekeeper.entity;

import java.util.List;

/**
 * Created by ihope007 on 2016/12/2.
 */
public class WalletPayEntity {

    /**
     * status : 200
     * data : {"balance":"10388.55","date":[{"id":"4","uid":"95","sid":"289","type1":"1","type":"29","order_id":"20170419149261253718","zhi_money":"150.00","zhiq_money":"10000","zhih_money":"9850","ctime":"1492613583"},{"id":"5","uid":"95","sid":"289","type1":"1","type":"32","order_id":"20170419149261253722","zhi_money":"67.50","zhiq_money":"9850","zhih_money":"9782.5","ctime":"1492613583"}]}
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
         * balance : 10388.55
         * date : [{"id":"4","uid":"95","sid":"289","type1":"1","type":"29","order_id":"20170419149261253718","zhi_money":"150.00","zhiq_money":"10000","zhih_money":"9850","ctime":"1492613583"},{"id":"5","uid":"95","sid":"289","type1":"1","type":"32","order_id":"20170419149261253722","zhi_money":"67.50","zhiq_money":"9850","zhih_money":"9782.5","ctime":"1492613583"}]
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
             * id : 4
             * uid : 95
             * sid : 289
             * type1 : 1
             * type : 29
             * order_id : 20170419149261253718
             * zhi_money : 150.00
             * zhiq_money : 10000
             * zhih_money : 9850
             * ctime : 1492613583
             */

            private String id;
            private String uid;
            private String sid;
            private String type1;
            private String type;
            private String order_id;
            private String zhi_money;
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

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }

            public String getType1() {
                return type1;
            }

            public void setType1(String type1) {
                this.type1 = type1;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getZhi_money() {
                return zhi_money;
            }

            public void setZhi_money(String zhi_money) {
                this.zhi_money = zhi_money;
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
