package com.haiwai.housekeeper.entity;

import java.io.Serializable;

/**
 * Created by lyj on 2016/9/14.
 */
public class OrderNo implements Serializable {
    private String imgUrl;
    private String title;
    private String skillName;
    private String dinstance;
    private String location;
    private String time;
    private int num;

    public OrderNo() {

    }

    public OrderNo(String imgUrl, String title, String skillName, String dinstance, String location, String time, int num) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.skillName = skillName;
        this.dinstance = dinstance;
        this.location = location;
        this.time = time;
        this.num = num;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getDinstance() {
        return dinstance;
    }

    public void setDinstance(String dinstance) {
        this.dinstance = dinstance;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
