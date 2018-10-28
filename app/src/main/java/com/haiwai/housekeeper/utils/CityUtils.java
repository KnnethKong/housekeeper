package com.haiwai.housekeeper.utils;

import com.haiwai.housekeeper.entity.CityEntity;
import com.haiwai.housekeeper.entity.CityLevelEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ihope006 on 2017/1/23.
 */

public class CityUtils {

    public static CityEntity parseCity(String json) {
        CityEntity cityEntity = new CityEntity();
        try {
            JSONObject jsonObject = new JSONObject(json);
            String status = jsonObject.optString("status");
            if ("200".equals(status)) {
                List<CityEntity.DataBean> dataBeanList = new ArrayList<>();

                JSONArray dataArray = jsonObject.optJSONArray("data");
                if (dataArray != null && dataArray.length() > 0) {//第一层
                    for (int i = 0; i < dataArray.length(); i++) {
                        CityEntity.DataBean dataBean = new CityEntity.DataBean();
                        JSONObject gJsonObject = dataArray.optJSONObject(i);
                        dataBean.setId(gJsonObject.optString("id"));
                        dataBean.setName(gJsonObject.optString("name"));
                        dataBean.setYname(gJsonObject.optString("yname"));
                        dataBean.setSort(gJsonObject.optString("sort"));
                        dataBean.setPid(gJsonObject.optString("pid"));
                        dataBean.setLevel(gJsonObject.optString("level"));

                        List<CityEntity.DataBean.ClassBean> sclassBeanList = new ArrayList<>();
                        JSONArray sJsonArray = gJsonObject.optJSONArray("class");
                        if (sJsonArray != null && sJsonArray.length() > 0) {//第二层

                            for (int j = 0; j < sJsonArray.length(); j++) {
                                CityEntity.DataBean.ClassBean classBean = new CityEntity.DataBean.ClassBean();
                                JSONObject sJsonObject = sJsonArray.optJSONObject(j);
                                classBean.setId(sJsonObject.optString("id"));
                                classBean.setName(sJsonObject.optString("name"));
                                classBean.setYname(sJsonObject.optString("yname"));
                                classBean.setSort(sJsonObject.optString("sort"));
                                classBean.setPid(sJsonObject.optString("pid"));
                                classBean.setLevel(sJsonObject.optString("level"));

                                List<CityEntity.DataBean.ClassBean.ClasssBean> cclassBeanList = new ArrayList<>();
                                JSONArray cJsonArray = sJsonObject.optJSONArray("class");
                                if (cJsonArray != null && cJsonArray.length() > 0) {//第三层
                                    for (int k = 0; k < cJsonArray.length(); k++) {

                                        CityEntity.DataBean.ClassBean.ClasssBean cclassBean = new CityEntity.DataBean.ClassBean.ClasssBean();
                                        JSONObject cJsonObject = cJsonArray.optJSONObject(k);
                                        cclassBean.setId(cJsonObject.optString("id"));
                                        cclassBean.setName(cJsonObject.optString("name"));
                                        cclassBean.setYname(cJsonObject.optString("yname"));
                                        cclassBean.setSort(cJsonObject.optString("sort"));
                                        cclassBean.setPid(cJsonObject.optString("pid"));
                                        cclassBean.setLevel(cJsonObject.optString("level"));

                                        cclassBeanList.add(cclassBean);
                                    }
                                    classBean.setClassxs(cclassBeanList);
                                }
                                sclassBeanList.add(classBean);

                            }
                            dataBean.setClassx(sclassBeanList);
                        }
                        dataBeanList.add(dataBean);
                    }

                    cityEntity.setStatus(status);
                    cityEntity.setDataBeans(dataBeanList);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cityEntity;
    }


    public static ArrayList<CityLevelEntity> getLevelList(String level, String id, CityEntity cityEntity) {
        ArrayList<CityLevelEntity> list = new ArrayList<>();
        list.clear();
        if (cityEntity!=null&&cityEntity.getDataBeans() != null && cityEntity.getDataBeans().size() > 0) {
            for (int i = 0, count = cityEntity.getDataBeans().size(); i < count; i++) {
                CityLevelEntity cityLevelEntity = new CityLevelEntity();
                if ("1".equals(level)) {
                    cityLevelEntity.setId(cityEntity.getDataBeans().get(i).getId());
                    cityLevelEntity.setLevel(cityEntity.getDataBeans().get(i).getLevel());
                    cityLevelEntity.setName(cityEntity.getDataBeans().get(i).getName());
                    cityLevelEntity.setPid(cityEntity.getDataBeans().get(i).getPid());
                    cityLevelEntity.setSort(cityEntity.getDataBeans().get(i).getSort());
                    cityLevelEntity.setYname(cityEntity.getDataBeans().get(i).getYname());
                    list.add(cityLevelEntity);
                } else if ("2".equals(level)) {
                    if (id.equals(cityEntity.getDataBeans().get(i).getId())) {
                        List<CityEntity.DataBean.ClassBean> classx = cityEntity.getDataBeans().get(i).getClassx();
                        if (classx != null && classx.size() > 0) {
                            for (int j = 0; j < classx.size(); j++) {
                                cityLevelEntity = new CityLevelEntity();
                                cityLevelEntity.setId(classx.get(j).getId());
                                cityLevelEntity.setLevel(classx.get(j).getLevel());
                                cityLevelEntity.setName(classx.get(j).getName());
                                cityLevelEntity.setPid(classx.get(j).getPid());
                                cityLevelEntity.setSort(classx.get(j).getSort());
                                cityLevelEntity.setYname(classx.get(j).getYname());
                                list.add(cityLevelEntity);
                            }
                        }
                    }
                } else if ("3".equals(level)) {
                    List<CityEntity.DataBean.ClassBean> classxs = cityEntity.getDataBeans().get(i).getClassx();
                    if (classxs != null && classxs.size() > 0) {
                        for (int k = 0; k < classxs.size(); k++) {
                            if (id.equals(classxs.get(k).getId())) {
                                List<CityEntity.DataBean.ClassBean.ClasssBean> classxss = classxs.get(k).getClassxs();
                                if (classxss != null && classxss.size() > 0) {
                                    for (int m = 0; m < classxss.size(); m++) {
                                        cityLevelEntity = new CityLevelEntity();
                                        cityLevelEntity.setId(classxss.get(m).getId());
                                        cityLevelEntity.setLevel(classxss.get(m).getLevel());
                                        cityLevelEntity.setName(classxss.get(m).getName());
                                        cityLevelEntity.setPid(classxss.get(m).getPid());
                                        cityLevelEntity.setSort(classxss.get(m).getSort());
                                        cityLevelEntity.setYname(classxss.get(m).getYname());
                                        list.add(cityLevelEntity);
                                    }
                                }
                            }

                        }
                    }


                }
            }
        }else{

        }
        return list;
    }
}
