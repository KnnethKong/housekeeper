package com.haiwai.housekeeper.entity;

import java.io.Serializable;

/**
 * Created by ihope006 on 2016/11/23.
 */

public class Pickers implements Serializable {
    private static final long serialVersionUID = 1L;
    private String showConetnt;
    private String showId;
    public String getShowConetnt() {
        return showConetnt;
    }
    public String getShowId() {
        return showId;
    }
    public Pickers(String showConetnt, String showId) {
        super();
        this.showConetnt = showConetnt;
        this.showId = showId;
    }
    public Pickers() {
        super();
    }

}
