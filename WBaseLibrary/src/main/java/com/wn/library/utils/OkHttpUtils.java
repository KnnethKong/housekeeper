package com.wn.library.utils;

import com.wn.library.base.BaseApp;
import com.wn.library.model.OnResponseListener;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 王宁 on 2017/3/2.
 */

public class OkHttpUtils implements Callback {

    private static Request.Builder request;

    private static FormBody.Builder builder;

    private StringBuilder buffer;

    private int requestId;

    private String url;


    private static OkHttpUtils okHttp;
    private OnResponseListener response;

    public static OkHttpUtils getInstance() {
        request = new Request.Builder();
        builder = new FormBody.Builder();
        okHttp = new OkHttpUtils();
        return okHttp;
    }

    //请求码和url
    public OkHttpUtils request(int requestId, String url,OnResponseListener response) {
        this.requestId = requestId;
        this.url = url;
        this.response = response;
        return okHttp;
    }

    //get请求的request
    private Request getRequest() {
        return request.url(url).get().build();
    }

    //post请求的
    private Request getRequest(Map<String, String> map) {
        return request.url(url).post(getBody(map)).build();
    }

    //post请求
    public OkHttpUtils excute(Map<String, String> map) {
        BaseApp.getBaseApp().getOkHttpClient().newCall(getRequest(map)).enqueue(this);
        return okHttp;
    }

    //get请求
    public OkHttpUtils excute() {
        BaseApp.getBaseApp().getOkHttpClient().newCall(getRequest()).enqueue(this);
        return okHttp;
    }

    //提交参数
    private RequestBody getBody(Map<String, String> map) {
        buffer = new StringBuilder();
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            builder.add(key, map.get(key));//不能这样使用----->builder.add(it.next(),map.get(it.next)),,it.next()可能没值
            buffer.append(key + ":" + map.get(key)+"\n");
        }
        LogUtils.e("请求码为:" + requestId + "上传的参数" + "---->" + buffer.toString());
        return builder.build();
    }


    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String strRes = response.body().string();
        LogUtils.e("请求码为:" + requestId + "的返回结果" + "---->" + strRes);
        this.response.onResponse(requestId, strRes);
    }
}
