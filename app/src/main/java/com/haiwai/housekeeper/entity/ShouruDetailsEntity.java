package com.haiwai.housekeeper.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/30.
 */

public class ShouruDetailsEntity {
    private int status;
    private List<ShouruDetailsEntity.DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ShouruDetailsEntity.DataBean> getData() {
        return data;
    }

    public void setData(List<ShouruDetailsEntity.DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private String order_id;
        private String ctime;
        private String type;
        private String pro_money;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPro_money() {
            return pro_money;
        }

        public void setPro_money(String pro_money) {
            this.pro_money = pro_money;
        }
    }
}
