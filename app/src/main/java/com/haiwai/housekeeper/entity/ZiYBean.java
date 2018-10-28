package com.haiwai.housekeeper.entity;

/**
 * Created by ihope007 on 2016/12/7.
 */
public class ZiYBean {
    private String name;
    private String yname;
    private String type;
    private int url;
    private String desc;
    private int img;



    public ZiYBean() {
    }

    public ZiYBean(String name, String yname, String type, int url, String desc) {
        this.name = name;
        this.yname = yname;
        this.type = type;
        this.url = url;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYname() {
        return yname;
    }

    public void setYname(String yname) {
        this.yname = yname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ZiYBean{" +
                "name='" + name + '\'' +
                ", yname='" + yname + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
