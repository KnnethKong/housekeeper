package com.haiwai.housekeeper.activity.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.haiwai.housekeeper.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ImgViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_view);
        ImageLoader.getInstance().displayImage(getIntent().getStringExtra("img"), ((ImageView) findViewById(R.id.imageView_show)));
    }
}
