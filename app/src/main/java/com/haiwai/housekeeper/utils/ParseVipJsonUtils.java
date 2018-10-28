package com.haiwai.housekeeper.utils;

import com.haiwai.housekeeper.entity.HousDataEntity;
import com.haiwai.housekeeper.entity.NewHousEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ihope006 on 2016/12/12.
 */

public class ParseVipJsonUtils {
    public static NewHousEntity parseJson(String json) {
        NewHousEntity newHousEntity = new NewHousEntity();
        try {
            JSONObject jsonObject = new JSONObject(json);
            int status = jsonObject.optInt("status");
            newHousEntity.setStatus(status);
            if (status == 200) {
                JSONObject dataObject = jsonObject.optJSONObject("data");
                if (dataObject != null) {
                    NewHousEntity.DataBean dataBean = new NewHousEntity.DataBean();
                    String img = dataObject.optString("img");

                    JSONArray dateArray = dataObject.optJSONArray("date");
                    List<NewHousEntity.DataBean.DateBean> dataBeens = new ArrayList<>();
                    if (dateArray != null) {
                        for (int i = 0; i < dateArray.length(); i++) {
                            JSONObject dateObject = dateArray.optJSONObject(i);
                            NewHousEntity.DataBean.DateBean dateBean = new NewHousEntity.DataBean.DateBean();
                            dateBean.setId(dateObject.optString("id"));
                            dateBean.setRemark(dateObject.optString("remark"));
                            dateBean.setType2(dateObject.optString("type2"));
                            dateBean.setValue(dateObject.optString("value"));
                            dateBean.setYremark(dateObject.optString("yremark"));
                            dateBean.setYvalue(dateObject.optString("yvalue"));
                            JSONArray proArray = dateObject.optJSONArray("problem");
                            List<NewHousEntity.DataBean.DateBean.ProblemBean> problemBeens = new ArrayList<>();
                            if (proArray != null) {
                                for (int j = 0; j < proArray.length(); j++) {
                                    JSONObject proObject = proArray.optJSONObject(j);
                                    NewHousEntity.DataBean.DateBean.ProblemBean problemBean = new NewHousEntity.DataBean.DateBean.ProblemBean();
                                    problemBean.setId(proObject.optString("id"));
                                    problemBean.setType2(proObject.optString("type2"));
                                    problemBean.setValue(proObject.optString("value"));
                                    problemBean.setYvalue(proObject.optString("yvalue"));
                                    problemBean.setRemark(proObject.optString("remark"));
                                    problemBean.setYremark(proObject.optString("yremark"));
                                    problemBeens.add(problemBean);
                                }
                            }
                            dateBean.setProblem(problemBeens);
                            dataBeens.add(dateBean);
                        }
                    }
                    dataBean.setDate(dataBeens);
                    dataBean.setImg(img);
                    newHousEntity.setData(dataBean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newHousEntity;
    }
}
