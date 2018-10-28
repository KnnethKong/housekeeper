package com.haiwai.housekeeper.entity;

import java.io.Serializable;

/**
 * Created by lyj on 2016/9/21.
 */
public class TitleItem implements Serializable {
    private String type;
    private String name;
    private String status;
    private boolean isSelected;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
