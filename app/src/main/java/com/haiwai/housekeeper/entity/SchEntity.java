package com.haiwai.housekeeper.entity;

import java.io.Serializable;

/**
 * Created by ihope10 on 2016/9/30.
 */
public class SchEntity implements Serializable{
    private String type;
    private String name;
    private String xue;
    private String stime;
    private String etime;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXue() {
        return xue;
    }

    public void setXue(String xue) {
        this.xue = xue;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }
}
