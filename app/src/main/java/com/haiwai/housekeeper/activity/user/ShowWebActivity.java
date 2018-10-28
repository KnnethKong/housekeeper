package com.haiwai.housekeeper.activity.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.widget.LoadDialog;

public class ShowWebActivity extends AppCompatActivity {

    private WebView show_html_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_web);
        show_html_view = ((WebView) findViewById(R.id.show_html_view));
        ((TextView) findViewById(R.id.tv_web_title)).setText(getIntent().getStringExtra("title"));
        initWeb();
    }

    private void initWeb() {
        WebSettings setings = show_html_view.getSettings();
        setings.setSupportZoom(true);
        show_html_view.loadUrl(getIntent().getStringExtra("url"));

        show_html_view.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                show_html_view.loadUrl(url);
                return true;
            }
        });

        show_html_view.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(newProgress==100){
                    LoadDialog.closeProgressDialog();
                }else{
                    LoadDialog.showProgressDialog(ShowWebActivity.this);
                }
            }
        });
        findViewById(R.id.iv_back_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
