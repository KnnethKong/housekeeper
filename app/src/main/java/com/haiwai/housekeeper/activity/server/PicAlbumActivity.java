package com.haiwai.housekeeper.activity.server;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.ImageBucketAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.entity.ImageBucket;
import com.haiwai.housekeeper.utils.AlbumHelper;
import com.haiwai.housekeeper.utils.BimpUtils;
import com.haiwai.housekeeper.view.TopViewNormalBar;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PicAlbumActivity extends BaseProActivity {
    private TopViewNormalBar top_pic_album_bar;
    List<ImageBucket> dataList = new ArrayList<>();
    GridView gridView;
    ImageBucketAdapter adapter;// 自定义的适配器
    AlbumHelper helper;
    public static Bitmap bimap;
    private int code = 0;
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_album);
        top_pic_album_bar = (TopViewNormalBar) findViewById(R.id.top_pic_album_bar);
        top_pic_album_bar.setTitle(getString(R.string.pro_xc));
        top_pic_album_bar.setOnBackListener(mOnClickListener);
        helper = AlbumHelper.getHelper();
        helper.init(getApplicationContext());
//        initView();
    }

    private void initData() {
        dataList.clear();
        isZhorEn = AppGlobal.getInstance().getLagStr();
        code = getIntent().getIntExtra("reqcode", -1);
        dataList = helper.getImagesBucketList(true);
//        bimap = BitmapFactory.decodeResource(
//                getResources(),
//                R.mipmap.pic_add);
        gridView = (GridView) findViewById(R.id.gridview);
        adapter = new ImageBucketAdapter(PicAlbumActivity.this, dataList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                /**
                 * 通知适配器，绑定的数据发生了改变，应当刷新视图
                 */
                Intent intent = new Intent(PicAlbumActivity.this,
                        PicSelectActivity.class);
                intent.putExtra("reqcode", code);
                intent.putExtra(BimpUtils.EXTRA_IMAGE_LIST,
                        (Serializable) dataList.get(position).imageList);
                startActivity(intent);
            }

        });
    }

    private void initView() {


    }

    @Override
    protected void onStart() {
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+""))));
        initData();
        super.onStart();
    }

    @Override
    protected void onRestart() {
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+""))));
        initData();
        super.onRestart();
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == top_pic_album_bar.getBackView()) {
                finish();
            }
        }
    };
}
