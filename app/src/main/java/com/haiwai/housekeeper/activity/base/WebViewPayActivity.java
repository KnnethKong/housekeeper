/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package com.haiwai.housekeeper.activity.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.ShowWebActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * 通用网页Activity
 *
 * @author Lemon
 * @use toActivity(WebViewActivity.createIntent(...));
 */
public class WebViewPayActivity extends AppCompatActivity {
    public static final String TAG = "WebViewActivity";

    public static final String INTENT_RETURN = "INTENT_RETURN";
    public static final String INTENT_TITLE = "INTENT_TITLE";
    public static final String INTENT_URL = "INTENT_URL";

    public static WebViewPayActivity webViewActivity;

    /**
     * 获取启动这个Activity的Intent
     *
     * @param title
     * @param url
     */

    private TopViewNormalBar topWebBar;
    private String url1 = "";
    private String isZhorEn = "";

    public static Intent createIntent(Context context, String title, String url) {
        return new Intent(context, WebViewPayActivity.class).
                putExtra(INTENT_TITLE, title).
                putExtra(INTENT_URL, url);
    }


    public Activity getActivity() {
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_pay);//传this是为了全局滑动返回
        webViewActivity = this;

        topWebBar = (TopViewNormalBar) findViewById(R.id.top_web_bar);
        topWebBar.setTitle(getString(R.string.py_cz));
        topWebBar.setOnBackListener(mOnClickListener);
        //功能归类分区方法，必须调用<<<<<<<<<<
        initView();
        initData();
        initEvent();
        //功能归类分区方法，必须调用>>>>>>>>>>

    }

    //UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    private ProgressBar pbWebView;
    private WebView wvWebView;
    private boolean isjie;
    private String key;

    public void initView() {

        pbWebView = (ProgressBar) findViewById(R.id.pbWebView);
        wvWebView = (WebView) findViewById(R.id.wvWebView);

        isZhorEn = AppGlobal.getInstance().getLagStr();
        url1 = getIntent().getStringExtra("url");
        isjie = getIntent().getBooleanExtra("isJie", false);
        if (!isjie) {
            key = url1.substring(url1.lastIndexOf("=") + 1, url1.length());
        }

        //pbWebView.setProgressDrawable(this.getResources().getDrawable(R.drawable.color_progressbar));
    }

    //UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //Data数据区(存在数据获取或处理代码，但不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    public void initData() {

        WebSettings webSettings = wvWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);

        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setBuiltInZoomControls(true); // 设置出现缩放工具
        webSettings.setUseWideViewPort(true);// 将图片调整到适合webview的大小

//        String url = getIntent().getStringExtra(INTENT_URL);

        wvWebView.requestFocus();

        // 设置setWebChromeClient对象  
        wvWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                //tvBaseTitle.setText(StringUtil.getTrimedString(title));
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
//                pbWebView.setProgress(newProgress);
                if (newProgress == 100) {
                    LoadDialog.closeProgressDialog();
                } else {
                    LoadDialog.showProgressDialog(WebViewPayActivity.this);
                }
            }
        });

        wvWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.contains("success")) {
                    if (!isjie) {
                        url = url + "?pay_key=" + key;
                    }

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
                            Log.i("reponseInformation", strRes);
                            if (strRes.substring(0, 7).contains("success")) {
                                ToastUtil.shortToast(WebViewPayActivity.this, getString(R.string.zhifu_s));
                                finish();
                            } else {
                                ToastUtil.shortToast(WebViewPayActivity.this, getString(R.string.zhifu_f));
                                finish();
                            }
                            WebViewPayActivity.this.finish();
                        }
                    });
                }
                wvWebView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //tvBaseTitle.setText(StringUtil.getTrimedString(wvWebView.getUrl()));
                pbWebView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //tvBaseTitle.setText(StringUtil.getTrimedString(wvWebView.getTitle()));
                pbWebView.setVisibility(View.GONE);
            }
        });

        wvWebView.loadUrl(url1);
    }

    //Data数据区(存在数据获取或处理代码，但不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //Event事件区(只要存在事件监听代码就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void initEvent() {

    }

    //系统自带监听方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//    @Override
//    public void onBackPressed() {
//        if (wvWebView.canGoBack()) {
//            wvWebView.goBack();
//            return;
//        }
//
//        super.onBackPressed();
//    }

    //类相关监听<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    protected void onPause() {
        super.onPause();
        wvWebView.onPause();
    }

    @Override
    protected void onResume() {
        wvWebView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (wvWebView != null) {
            wvWebView.destroy();
            wvWebView = null;
        }
        wvWebView = null;
    }

    //类相关监听>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //系统自带监听方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //Event事件区(只要存在事件监听代码就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == topWebBar.getBackView()) {
                finish();
            }
        }
    };


}