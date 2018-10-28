package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/30.
 */

public class ShouruEntity {
    private int status;
    private List<ShouruEntity.DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ShouruEntity.DataBean> getData() {
        return data;
    }

    public void setData(List<ShouruEntity.DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private String id;
        private String self_batch_id;
        private String zhi_type;
        private String water;
        @SerializedName("static")
        private String staticx;
        private String proid;
        private String order_num;
        private String dai_money;
        private String tiao_money;
        private String yin_money;
        private String oid;
        private String callback;
        private String utime;
        private String token;
        private String email;
        private String transactionid;
        private String sendertransactionid;
        private String ctime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSelf_batch_id() {
            return self_batch_id;
        }

        public void setSelf_batch_id(String self_batch_id) {
            this.self_batch_id = self_batch_id;
        }

        public String getZhi_type() {
            return zhi_type;
        }

        public void setZhi_type(String zhi_type) {
            this.zhi_type = zhi_type;
        }

        public String getWater() {
            return water;
        }

        public void setWater(String water) {
            this.water = water;
        }

        public String getProid() {
            return proid;
        }

        public void setProid(String proid) {
            this.proid = proid;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getDai_money() {
            return dai_money;
        }

        public void setDai_money(String dai_money) {
            this.dai_money = dai_money;
        }

        public String getTiao_money() {
            return tiao_money;
        }

        public void setTiao_money(String tiao_money) {
            this.tiao_money = tiao_money;
        }

        public String getYin_money() {
            return yin_money;
        }

        public void setYin_money(String yin_money) {
            this.yin_money = yin_money;
        }

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }

        public String getCallback() {
            return callback;
        }

        public void setCallback(String callback) {
            this.callback = callback;
        }

        public String getUtime() {
            return utime;
        }

        public void setUtime(String utime) {
            this.utime = utime;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTransactionid() {
            return transactionid;
        }

        public void setTransactionid(String transactionid) {
            this.transactionid = transactionid;
        }

        public String getSendertransactionid() {
            return sendertransactionid;
        }

        public void setSendertransactionid(String sendertransactionid) {
            this.sendertransactionid = sendertransactionid;
        }

        public String getStaticx() {
            return staticx;
        }

        public void setStaticx(String staticx) {
            this.staticx = staticx;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }
    }
}
