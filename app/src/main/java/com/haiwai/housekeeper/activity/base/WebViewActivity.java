package com.haiwai.housekeeper.activity.base;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;


import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.io.IOException;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class WebViewActivity extends AppCompatActivity {
    private TopViewNormalBar topWebBar;
    private WebView webView;
    private String url1 = "";
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        topWebBar = (TopViewNormalBar) findViewById(R.id.top_web_bar);
        topWebBar.setTitle(getString(R.string.py_cz));
        topWebBar.setOnBackListener(mOnClickListener);
        initData();
        initView();
    }

    private boolean isjie;
    private String key;
    private void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        url1 = getIntent().getStringExtra("url");
        isjie = getIntent().getBooleanExtra("isJie",false);
        if(!isjie){
            key = url1.substring(url1.lastIndexOf("=")+1,url1.length());
        }
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.web_view);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //webView.addJavascriptInterface(new InJavaScriptLocalObj(), "control");

//        settings.setSupportZoom(true); // 支持缩放
//        settings.setBuiltInZoomControls(true); // 设置出现缩放工具
//        settings.setUseWideViewPort(true);// 将图片调整到适合webview的大小
//        // 自适应屏幕
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        settings.setLoadWithOverviewMode(true);
//
//        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //webView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
//        setCookiesToURL(url1);

        WebSettings webSetting = webView.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        //webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        //webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0)
                .getPath());


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
//                ToastUtil.longToast(WebViewActivity.this, url);
                System.out.println(">>>>>>>>>>>>>>>>>>" + url);
                if(url.contains("success")) {
                    if(!isjie){
                        url = url + "?pay_key=" + key;
                    }
                    System.out.println(">>>>>>>>>>>>>>>>>>1111111" + url);
                    OkHttpClient mOkHttpClient = new OkHttpClient();
                    final Request request = new Request.Builder()
                            .url(url)
                            .build();
                    mOkHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, okhttp3.Response response) throws IOException {
//                            Log.i("reponseInformation",response.body().string());
                            String strRes = response.body().string();
                            Log.i("reponseInformation",strRes);
                            if(strRes.substring(0,7).contains("success")){
                                ToastUtil.shortToast(WebViewActivity.this,getString(R.string.zhifu_s));
                                finish();
                            }else{
                                ToastUtil.shortToast(WebViewActivity.this,getString(R.string.zhifu_f));
                                finish();
                            }
                        }
                    });
                }
//                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                super.onReceivedError(webView, i, s, s1);
                //webView.loadUrl(url1);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                view.loadUrl(url1);
            }

        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成
                    LoadDialog.closeProgressDialog();
                } else {
                    // 加载中
                    LoadDialog.showProgressDialog(WebViewActivity.this);
                }

            }

        });

        webView.loadUrl(url1);

    }



    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == topWebBar.getBackView()) {
                finish();
            }
        }
    };
//    final class InJavaScriptLocalObj {
//        @JavascriptInterface
//        public void showSource(String html) {
//
//        }
//    }

    private CookieSyncManager cookieSyncManager;
    public CookieStore cookieStore = null;
    public  String JSESSIONID = null;
    public  String result = null;

    public  void setCookiesToURL(String url) {
        try {
            List<HttpCookie> cookies1 = cookieStore.getCookies();
            if (cookies1 != null && !cookies1.isEmpty()) {
                if (null == cookieSyncManager) {
                    cookieSyncManager = CookieSyncManager.createInstance(getApplication());
                }
                CookieManager cookieManager1 = CookieManager.getInstance();
                cookieManager1.setAcceptCookie(true);
                cookieManager1.removeSessionCookie();// 移除
                for (HttpCookie cookie1 : cookies1) {
                    HttpCookie sessionInfo = cookie1;
                    String cookieString = sessionInfo.getName() + "=" + JSESSIONID + ";";
                    cookieSyncManager.startSync();
                    new Thread().sleep(500);// 刷新 webview.db,不然不会得到实时效果
                    cookieManager1.setCookie(url, cookieString);
                }

                String cook = cookieManager1.getCookie(url);
                System.out.println("getCookie:  -----------" + cook);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
