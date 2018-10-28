package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.AboutUsActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;

/**
 * Created by ihope007 on 2016/9/6.
 * 我的——使用手册
 */
public class UseGuideActivity extends BaseActivity {
    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_user_guide,null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.use_guide_title), Color.parseColor("#FF3C3C3C"));
    }

    @Override
    protected void initData() {
       Bundle bundle= getIntent().getExtras();
        final String where= bundle.getString("flag");
        Log.e("resuslt---->",where);
        findViewById(R.id.rule_of_garee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UseGuideActivity.this,ShowWebActivity.class);

                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    intent.putExtra("url","http://landingbook.net/public/fwpj/index-en.html");
                }else{
                    intent.putExtra("url","http://landingbook.net/public/fwpj/index-zh.html");
                }
                intent.putExtra("title",getString(R.string.use_guide_label_3));
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_prool_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UseGuideActivity.this,ShowWebActivity.class);
                if(where.equals("user")){//【用户端使用协议】
                    intent.putExtra("url","http://landingbook.net/xieyi/userxieyi.html");
                }else {//【pro端使用协议】
                    intent.putExtra("url","http://landingbook.net/xieyi/proxieyi.html");
                }

                intent.putExtra("title",getString(R.string.use_guide_label_1));
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_problem_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UseGuideActivity.this,ShowWebActivity.class);
                if(where.equals("user")){//【用户端常见问题】
                    intent.putExtra("url","http://landingbook.net/xieyi/userqa.html");
                }else {//【pro端常见问题】
                    intent.putExtra("url","http://landingbook.net/xieyi/proqa.html");
                }

                intent.putExtra("title",getString(R.string.use_guide_label_2));
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_about_us).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UseGuideActivity.this,AboutUsActivity.class));
            }
        });
    }

    @Override
    protected void click(View v) {

    }
}
