package com.baidu.translate;

import android.os.AsyncTask;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;

/**
 * Created by tangao on 2016/7/24.
 */
public class SyncRequestUtils {

    private static final String UTF8 = "utf-8";

    //申请者开发者id，实际使用时请修改成开发者自己的appid
    private static final String APP_ID = "20161118000032142";

    //申请成功后的证书token，实际使用时请修改成开发者自己的token (密钥)
    private static final String SECRET_KEY = "CACFKuNwGZJw6bpdTM_J";
    //翻译API HTTP地址：
    private static final String baseURL = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    //随机数，用于生成md5值，开发者使用时请激活下边第四行代码
    private static final Random random = new Random();

    public SyncRequestUtils() {

    }

    public String translate(final String needToTransString, final String from, final String to) {
        String text = null;
        //用于md5加密生成签名sign
        int salt = random.nextInt(10000);
        // 对appId+源文+随机数+token计算md5值(签名sign),官方demo提供的下面这种计算为什么不行？？？
//        StringBuilder md5String = new StringBuilder();
//        md5String.append(APP_ID).append(needToTransString).append(salt).append(SECRET_KEY);
//        String sign = DigestUtils.md5Hex(md5String.toString());
        //应该对 appid+needToTransString+salt+密钥 拼接成的字符串做MD5加密得到32位小写的sign。确保要翻译的文本needToTransString为UTF-8编码。
        try {
            String md5String = APP_ID + new String(needToTransString.getBytes(), "utf-8") + salt + SECRET_KEY;
            final String sign = MD5Encoder.encode(md5String.toString());
            //注意在生成签名拼接 appid+needToTransString+salt+密钥 字符串时，needToTransString不需要做URL encode，
            // 在生成签名之后，发送HTTP请求之前才需要对要发送的待翻译文本字段needToTransString做URL encode。
            final URL urlFinal = new URL(baseURL + "?q=" + URLEncoder.encode(needToTransString, UTF8) +
                    "&from=" + from + "&to=" + to + "&appid=" + APP_ID + "&salt=" + salt + "&sign=" + sign);
//       URLEncoder.encode(needToTransString, UTF8);//%E4%BD%A0%E5%A5%BD
            HttpURLConnection conn = null;
            conn = (HttpURLConnection) urlFinal.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(10000);
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                InputStream is = conn.getInputStream();
                String response = getStringFromInputStream(is);
                JSONObject resultJson = new JSONObject(response);
                try {
                    String error_code = resultJson.getString("error_code");
                    if (error_code != null) {
                        System.out.println("出错代码:" + error_code);
                        System.out.println("出错信息:" + resultJson.getString("error_msg"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //获取翻译成功的结果
                JSONArray jsonArray = (JSONArray) resultJson.get("trans_result");
                JSONObject dstJson = (JSONObject) jsonArray.get(0);
                text = dstJson.getString("dst");
                text = URLDecoder.decode(text, UTF8);//utf-8译码
//                    System.out.println("text  ----->   " + text);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    private static String getStringFromInputStream(InputStream is)
            throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // 模板代码 必须熟练
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        is.close();
        String state = os.toString();// 把流中的数据转换成字符串,采用的编码是utf-8(模拟器默认编码)
        os.close();
        return state;
    }

}












