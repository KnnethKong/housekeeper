package com.haiwai.housekeeper.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ihope006 on 2016/12/9.
 */

public class ZYEntity implements Serializable {
    private String tittleId;
    private String title;
    private String type;
    private int step;
    private List<DaBean> daList;

    public String getTittleId() {
        return tittleId;
    }

    public void setTittleId(String tittleId) {
        this.tittleId = tittleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DaBean> getDaList() {
        return daList;
    }

    public void setDaList(List<DaBean> daList) {
        this.daList = daList;
    }

    public static class DaBean implements Serializable {
        private String strId;
        private String str;

        public String getStrId() {
            return strId;
        }

        public void setStrId(String strId) {
            this.strId = strId;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return "DaBean{" +
                    "strId='" + strId + '\'' +
                    ", str='" + str + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ZYEntity{" +
                "tittleId='" + tittleId + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", step=" + step +
                ", daList=" + daList +
                '}';
    }
}
