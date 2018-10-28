package com.haiwai.housekeeper.entity;

import java.io.Serializable;

/**
 * Created by ihope006 on 2016/12/5.
 */

public class SkillNewEntity implements Serializable{
    private String id;
    private String name;
    private String yname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
