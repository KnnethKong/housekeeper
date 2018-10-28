/*
 * The MIT License (MIT)
 * Copyright (c) 2014 longkai
 * The software shall be used for good, not evil.
 */
package com.haiwai.housekeeper.https;

import android.content.Context;
import android.content.Intent;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.PlatAPIError;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.ToastUtil;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * http json object请求抽象，子类需要对请求成功后的json object做进一步处理（如持久化等），该处理会在后台线程中执行。
 * <p/>
 * 如果需要在处理结束后更新ui，请传入非空的{@link com.android.volley.Response.Listener}，否则传入null
 *
 * @author abner
 */

public class PlatRequest extends Request<String> {

    public static final String TAG = "PlatRequest";

    protected static Context mContext;
    protected Response.Listener<String> mListener;
    protected PlatProcessor<String> mProcessor;

    private Map<String, String> params;

    public PlatRequest(int method,
                       Context context, String url, Map<String, String> params, PlatProcessor<String> processor,
                       Response.Listener<String> listener,
                       Response.ErrorListener errorListener) {
        super(method, url, errorListener);
//        LoadDialog.showProgressDialog(context);
        this.mContext = context;
        this.params = params;
        this.mProcessor = processor;
        this.mListener = listener;
        if (params != null) {
            String resp = "";
            for (Map.Entry<String, String> entry : params
                    .entrySet()) {
                resp = entry.getKey() + "="
                        + entry.getValue() + "&"
                        + resp;
            }
            LogUtil.e("Map", url + "&" + resp);
        }
    }

    private static Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            LogUtil.d(TAG, "error loading data from cloud!");
            PlatAPIError platAPIError = PlatAPIError.fromVolleyError(error);
            try {
                ToastUtil.shortToast(mContext, platAPIError.error);
//                ToastUtil.shortToast(mContext, "网络故障");
            } catch (Exception e) {
                // 有些时候，比如没有网络连接的时候，超时什么的，fragment会detach...
            }
        }
    };

    public PlatRequest(Context context, String url,
                       Map<String, String> params, PlatProcessor<String> processor, Response.Listener<String> listener, Response.ErrorListener error) {
        this(Method.POST, context, url, params, processor, listener, error);
    }

    public PlatRequest(Context context, String url,
                       Map<String, String> params, PlatProcessor<String> processor, Response.Listener<String> listener) {
        super(Method.POST, url, errorListener);

        if(MyApp.getTingtingApp().isLogin()){
            params.put("deng_uid", AppGlobal.getInstance().getUser().getUid());
            params.put("login_key",AppGlobal.getInstance().getLoginKey());
        }

        this.mContext = context;
        this.params = params;
        this.mProcessor = processor;
        this.mListener = listener;
        if (params != null) {
            String resp = "";
            for (Map.Entry<String, String> entry : params
                    .entrySet()) {
                resp = entry.getKey() + "="
                        + entry.getValue() + "&"
                        + resp;
            }
            LogUtil.e("Map", url + "&" + resp);
        }
    }



    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        System.out.println(">>>>>>>>>>>>>platrequest返回数据>>>>>>>>>>>>>>" + response);
        try {
            String jsonString = new String(response.data, Charset.forName("UTF-8"));
            System.out.println(">>>>>>>>>>>>>返回数据>>>>>>>>>>>>>>" + jsonString);
            int code = JsonUtils.getJsonInt(jsonString, "status");
            if (code == 1404) {
                Intent intent = new Intent();
                /*  设置Intent对象的action属性  */
                intent.setAction("cn.android.login.out");
				/* 为Intent对象添加附加信息 */
				/* 发布广播 */
                mContext.sendBroadcast(intent);
            }
//            String jsonString =
//                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            LogUtil.e("Response", jsonString);
//            LoadDialog.closeProgressDialog();

            if (mProcessor != null) {
                // do in background...
                mProcessor.asyncProcess(mContext, jsonString);
            }
            return Response.success(jsonString,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (Exception ex) {
            LogUtil.e(TAG, "process error!");
            return Response.error(new VolleyError(response));
        }
    }

    @Override
    protected void deliverResponse(String response) {
        if (mListener == null) {
            LogUtil.d(TAG, "finish http request without response on main-thread!");
        } else {
            mListener.onResponse(response);
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return super.getHeaders();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params == null ? super.getParams() : params;
    }
}
