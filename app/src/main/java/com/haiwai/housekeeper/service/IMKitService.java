package com.haiwai.housekeeper.service;

import com.haiwai.housekeeper.entity.ZYEntity;
import com.haiwai.housekeeper.utils.SerializableMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ihope10 on 2016/11/15.
 */

public class IMKitService {
    public static String IM_Token;
    public static Map<String, String> addrMap = new HashMap<>();
    public static Map<String, String> map = new LinkedHashMap<>();
    public static Map<String, String> jMap = new LinkedHashMap<>();
    public static Map<String, String> yMap = new LinkedHashMap<>();
    public static Map<String, String> zMap = new LinkedHashMap<>();
    public static Map<String, String> kMap = new LinkedHashMap<>();

    public static Map<String, SerializableMap> sjMap = new HashMap<>();
    public static Map<String, SerializableMap> syMap = new HashMap<>();
    public static Map<String, SerializableMap> szMap = new HashMap<>();
    public static Map<String, SerializableMap> skMap = new HashMap<>();

    public static String str = null;
    public static String addrStr = null;

    public static Map<String, String> lagMap = new HashMap<>();

    public static Map<String, String> proTypeMap = new HashMap<>();
    public static Map<String, String> proDetailMap = new HashMap<>();

    public static Map<String, ZYEntity> weekMap = new LinkedHashMap<>();//单选id

    public static final String APPID = "2016120603923298";
    public static final String PID = "2088421933666163";
    public static final String TARGET_ID = "roknlkblybljte17gl9kty6mciuo74ia";

    public static boolean isConfig = false;
}
