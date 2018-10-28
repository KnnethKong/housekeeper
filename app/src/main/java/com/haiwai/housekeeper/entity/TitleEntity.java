package com.haiwai.housekeeper.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lyj on 2016/9/21.
 */
public class TitleEntity implements Serializable{
    private String name;
    private List<TitleItem> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TitleItem> getItems() {
        return items;
    }

    public void setItems(List<TitleItem> items) {
        this.items = items;
    }
}
