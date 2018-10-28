package com.haiwai.housekeeper.https.okhttp;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.JsonObject;
import com.haiwai.housekeeper.utils.ImageCompressUtil;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.widget.LoadDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import id.zelory.compressor.Compressor;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ihope10 on 2016/10/9.
 */
public class OkHttpUtils {
    private static final byte[] LOCKER = new byte[0];
    private static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    private OkHttpUtils() {
        OkHttpClient.Builder ClientBuilder = new OkHttpClient.Builder();
        ClientBuilder.readTimeout(60, TimeUnit.SECONDS);//读取超时
        ClientBuilder.connectTimeout(60, TimeUnit.SECONDS);//连接超时
        ClientBuilder.writeTimeout(60, TimeUnit.SECONDS);//写入超时
        mOkHttpClient = ClientBuilder.build();
    }

    public static OkHttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (LOCKER) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils();
                }
            }
        }
        return mInstance;
    }


    /**
     * 设置请求头
     *
     * @param headersParams
     * @return
     */
    private Headers SetHeaders(Map<String, String> headersParams) {
        Headers headers = null;
        Headers.Builder headersbuilder = new Headers.Builder();

        if (headersParams != null) {
            Iterator<String> iterator = headersParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                headersbuilder.add(key, headersParams.get(key));
                Log.d("get http", "get_headers===" + key + "====" + headersParams.get(key));
            }
        }
        headers = headersbuilder.build();

        return headers;
    }

    /**
     * post请求参数
     *
     * @param BodyParams
     * @return
     */
    public RequestBody SetRequestBody(Map<String, String> BodyParams) {
        RequestBody body = null;
        okhttp3.FormBody.Builder formEncodingBuilder = new okhttp3.FormBody.Builder();
        if (BodyParams != null) {
            Iterator<String> iterator = BodyParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                formEncodingBuilder.add(key, BodyParams.get(key));
                System.out.println("key------>" + key + ">>>>>value............>" + BodyParams.get(key));
                Log.d("post http", "post_Params===" + key + "====" + BodyParams.get(key));
            }
        }
        body = formEncodingBuilder.build();
        return body;

    }

    /**
     * Post上传图片的参数
     *
     * @param BodyParams
     * @param fileParams
     * @return
     */
    public RequestBody SetFileRequestBody(Context context,Map<String, String> BodyParams, Map<String, String> fileParams) {
        //带文件的Post参数
        RequestBody body = null;
        MultipartBody.Builder MultipartBodyBuilder = new MultipartBody.Builder();
        MultipartBodyBuilder.setType(MultipartBody.FORM);
        RequestBody fileBody = null;

        if (BodyParams != null) {
            Iterator<String> iterator = BodyParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                MultipartBodyBuilder.addFormDataPart(key, BodyParams.get(key));
                Log.d("post http", "post_Params===" + key + "====" + BodyParams.get(key));
                System.out.println("post http>>>>>>" + "post_Params===" + key + "====" + BodyParams.get(key));
            }
        }

        if (fileParams != null) {
            Iterator<String> iterator = fileParams.keySet().iterator();
            String key = "";
            int i = 0;
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                i++;
                MultipartBodyBuilder.addFormDataPart(key, fileParams.get(key));
                Log.d("post http", "post_Params===" + key + "====" + fileParams.get(key));
                System.out.println("post http>>>>>>>>>>>>>>>>>>>>>>>>>>>" + "post_Params===" + key + "====" + fileParams.get(key));
//                File file = ImageCompressUtil.getimage(fileParams.get(key));

//                File compressedImage = new Compressor.Builder(context)
//                        .setQuality(70)
//                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
//                        .build()
//                        .compressToFile(new File(fileParams.get(key)));

                File compressedImage = new Compressor.Builder(context)
                        .setMaxWidth(1080)
                        .setMaxHeight(720)
                        .setQuality(80)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .build()
                        .compressToFile(new File(fileParams.get(key)));


                File file  = Compressor.getDefault(context).compressToFile(new File(fileParams.get(key)));
                fileBody = RequestBody.create(MediaType.parse("image/png"), compressedImage);
                MultipartBodyBuilder.addFormDataPart(key, i + ".png", fileBody);
            }
        }
        body = MultipartBodyBuilder.build();
        return body;

    }

    /**
     * get方法连接拼加参数
     *
     * @param mapParams
     * @return
     */
    public String setUrlParams(Map<String, String> mapParams) {
        String strParams = "";
        if (mapParams != null) {
            Iterator<String> iterator = mapParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                strParams += "&" + key + "=" + mapParams.get(key);
            }
        }

        return strParams;
    }

    public void execCallback(final Context context, Request request, final OKRequestCallback callback) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ToastUtil.shortToast(context, "请求失败！");
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
//                System.out.println("返回测试数据...........》" + response.body().toString());
                if (response.code() == 200) {
                    final String result = response.body().string();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onResponse(result);
                        }
                    });
                }
            }
        });
    }


}
