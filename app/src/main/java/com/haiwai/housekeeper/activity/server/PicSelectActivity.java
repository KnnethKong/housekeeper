package com.haiwai.housekeeper.activity.server;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.ImageGridAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.entity.ImageItem;
import com.haiwai.housekeeper.utils.AlbumHelper;
import com.haiwai.housekeeper.utils.BimpUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.TopViewNormalBar;

import java.util.List;

public class PicSelectActivity extends BaseProActivity {
    private TopViewNormalBar top_pic_bar;
    List<ImageItem> dataList;
    AlbumHelper helper;
    GridView gridView;
    ImageGridAdapter adapter;
    int code;
    private String isZhorEn = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_select);
        top_pic_bar = (TopViewNormalBar) findViewById(R.id.top_pic_bar);
        top_pic_bar.setTitle(getString(R.string.per_album));
        top_pic_bar.setOnBackListener(mOnClickListener);
        top_pic_bar.setVisible(true);
        top_pic_bar.setFinishListener(mOnClickListener);
        initData();
        initView();

    }

    private void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        code = getIntent().getIntExtra("reqcode", -1);
        helper = AlbumHelper.getHelper();
        helper.init(getApplicationContext());
        dataList = (List<ImageItem>) getIntent().getSerializableExtra(
                BimpUtils.EXTRA_IMAGE_LIST);
    }

    private void initView() {
        gridView = (GridView) findViewById(R.id.myGrid);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new ImageGridAdapter(PicSelectActivity.this, dataList, mHandler);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                adapter.notifyDataSetChanged();
            }

        });
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
//                    ToastUtil.shortToast(PicSelectActivity.this, "最多选择3张图片");
                    break;

                default:
                    break;
            }
        }
    };

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == top_pic_bar.getBackView()) {
//                BimpUtils.tempSelectBitmap.clear();
                finish();
            } else if (v == top_pic_bar.getFinishTextView()) {
                if (code == 44) {
                    startActivity(new Intent(PicSelectActivity.this, ProWorkFeadBackActivity.class));
                    finish();
                } else if (code == 11) {
                    startActivity(new Intent(PicSelectActivity.this, ProWorkaFeadBackActivity.class));
                    finish();
                } else if (code == 22) {
                    startActivity(new Intent(PicSelectActivity.this, ProWorkbFeadBackActivity.class));
                    finish();
                } else if (code == 33) {
                    startActivity(new Intent(PicSelectActivity.this, ProWorkcFeadBackActivity.class));
                    finish();
                }

            }
        }
    };
}
