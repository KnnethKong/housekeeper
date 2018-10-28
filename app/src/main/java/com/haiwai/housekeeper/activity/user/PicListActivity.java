package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.PicNewDetailAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.entity.ImageListEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/14.
 */
public class PicListActivity extends BaseActivity {
    private GridView myGridView;
    private TextView tvempty;
    private PicNewDetailAdapter picDetailAdapter;
    private List<ImageListEntity.DataBean> list = new ArrayList<>();
    private ImageButton ibright;
    ImageListEntity entity;
    private String data = "";
    private String uid = "";
    private String isZhorEn = "";

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_pic_detail, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitlebarHide(true);
        TextView tvtitle = (TextView) findViewById(R.id.pic_tv_title);
        tvtitle.setTextColor(Color.parseColor("#FF3C3C3C"));
        tvtitle.setText(getString(R.string.person_pic));
        findViewById(R.id.pic_ib_back).setOnClickListener(this);
        ibright = (ImageButton) findViewById(R.id.pic_ib_right);
        ibright.setImageResource(R.mipmap.shangchuan);
        uid = getIntent().getExtras().getString("uid");
        if (uid.equals(AppGlobal.getInstance().getUser().getUid()))
            ibright.setVisibility(View.VISIBLE);
        ibright.setOnClickListener(this);
        myGridView = (GridView) findViewById(R.id.user_pic_detail_gv_container);
        tvempty = (TextView) findViewById(R.id.user_pic_detail_tv_empty);
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//照片展示内容
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);
                bundle.putInt("position", position);
                bundle.putString("uid", uid);
                bundle.putSerializable("data", (Serializable) list);
                ActivityTools.goNextActivityForResult(PicListActivity.this, ImageShowActivity.class, bundle, 100);
            }
        });
    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        picDetailAdapter = new PicNewDetailAdapter(this, list);
        myGridView.setAdapter(picDetailAdapter);
        myGridView.setEmptyView(tvempty);
        if (isNetworkAvailable())
            requestImageList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                requestImageList();
            }
        }
    }

    public void requestImageList() {
        LoadDialog.showProgressDialog(PicListActivity.this);
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("page", "1");
        map.put("page_size", "10");
        map.put("secret_key", SPUtils.getString(PicListActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(PicListActivity.this, Contants.photo_list, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    entity = new Gson().fromJson(response, ImageListEntity.class);
                    list.clear();
                    list.addAll(entity.getData());
                    picDetailAdapter.setList(list);
                    LoadDialog.closeProgressDialog();
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.shortToast(PicListActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }


    public void sendRefreshReceiver() {
        Intent intent = new Intent();
        intent.setAction("newData");
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        sendRefreshReceiver();
        super.onDestroy();
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.pic_ib_right:
                Bundle bundle = new Bundle();
                ActivityTools.goNextActivityForResult(this, UpLoadPicActivity.class, bundle, 100);
                break;
            case R.id.pic_ib_back:
                finish();
                break;
        }
    }
}
