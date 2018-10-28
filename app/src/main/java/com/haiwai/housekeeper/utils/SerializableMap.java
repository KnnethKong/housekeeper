package com.haiwai.housekeeper.utils;

import com.haiwai.housekeeper.entity.ZYEntity;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by ihope006 on 2016/12/9.
 */

public class SerializableMap implements Serializable {
    private Map<String, ZYEntity> map;//单选id

    public Map<String, ZYEntity> getMap() {
        return map;
    }

    public void setMap(Map<String, ZYEntity> map) {
        this.map = map;
    }

}
