package com.haiwai.housekeeper.activity.server;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.entity.ImageItem;
import com.haiwai.housekeeper.utils.BimpUtils;
import com.haiwai.housekeeper.utils.FileUtils;
import com.haiwai.housekeeper.utils.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends BaseProActivity {
    private GridView noScrollgridview;
    private GridAdapter adapter;
    PopupWindow popWindow;
    LinearLayout ll_popup;
    private static int REQUESTCODE = 0;
    private static int REQUESTCODEB = 1;
    private List<ImageItem> pathList = new ArrayList<>();
    private LayoutInflater mLayoutInfalter;
    private int selectedPosition = -1;// 选中的位置
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mLayoutInfalter = LayoutInflater.from(this);
        initView();
    }

    private void initView() {
        noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
        adapter =new GridAdapter();
        noScrollgridview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == pathList.size()) {
                    ShowPopupWindow();
                }
            }
        });
    }

    private void ShowPopupWindow() {
        View view = View
                .inflate(TestActivity.this, R.layout.item_popupwindows, null);
        view.startAnimation(AnimationUtils.loadAnimation(TestActivity.this,
                R.anim.alpha_fade_in));
        ll_popup = (LinearLayout) view
                .findViewById(R.id.ll_popup);
        ll_popup.startAnimation(AnimationUtils.loadAnimation(TestActivity.this,
                R.anim.translate_bottom_in));
        TextView bt1 = (TextView) view
                .findViewById(R.id.item_popupwindows_camera);
        TextView bt2 = (TextView) view
                .findViewById(R.id.item_popupwindows_Photo);
        TextView bt3 = (TextView) view
                .findViewById(R.id.item_popupwindows_cancel);
        bt1.setOnClickListener(mOnClickListener);
        bt2.setOnClickListener(mOnClickListener);
        bt3.setOnClickListener(mOnClickListener);
        popWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.item_popupwindows_camera) {//调用相机拍照
                takePhoto();
                popWindow.dismiss();
                ll_popup.clearAnimation();
            } else if (v.getId() == R.id.item_popupwindows_Photo) {//调用系统相册
                Intent local = new Intent();
                local.setType("image/*");
                local.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(local, REQUESTCODEB);
                popWindow.dismiss();
                ll_popup.clearAnimation();
            } else if (v.getId() == R.id.item_popupwindows_cancel) {//取消
                popWindow.dismiss();
                ll_popup.clearAnimation();
            }

        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && resultCode == RESULT_OK && data != null) {
            if (pathList.size() < 3) {
                String fileName = String.valueOf(System.currentTimeMillis());
                String path = FileUtils.SDPATH + fileName + ".JPEG";
                Bitmap bm = (Bitmap) data.getExtras().get("data");
                ImageItem takePhoto = new ImageItem();
                takePhoto.setImagePath(path);
                takePhoto.setBitmap(bm);
                pathList.add(takePhoto);
            }
            adapter.notifyDataSetChanged();
        } else if (requestCode == REQUESTCODEB && resultCode == RESULT_OK && data != null) {
            if (pathList.size() < 3) {
                Uri uri = data.getData();
                String path = FileUtils.getPath(TestActivity.this, uri);
                Bitmap bm = null;
                try {
                    bm = BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(path), 140, 140);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ImageItem takePhoto = new ImageItem();
                takePhoto.setImagePath(path);
                takePhoto.setBitmap(bm);
                pathList.add(takePhoto);
            }
            adapter.notifyDataSetChanged();
        }
    }



    private void takePhoto() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, REQUESTCODE);
    }

    class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (pathList.size() == 3) {
                return 3;
            }
            return (pathList.size() + 1);
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null){
                convertView = mLayoutInfalter.inflate(R.layout.item_published_grida,null);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView
                        .findViewById(R.id.item_grida_image);
                holder.iv_del = (ImageView) convertView.findViewById(R.id.iv_del);
                holder.iv_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deletLayout(position);
                    }
                });
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (position == pathList.size()) {
                holder.image.setImageBitmap(BitmapFactory.decodeResource(
                        getResources(), R.mipmap.pic_add));
                holder.iv_del.setVisibility(View.GONE);
                if (position == 3) {
                    holder.image.setVisibility(View.GONE);
                }
            } else {
                holder.image.setImageBitmap(pathList.get(position).getBitmap());
                holder.iv_del.setVisibility(View.VISIBLE);
            }
            return convertView;
        }


        class ViewHolder {
            ImageView image,iv_del;
        }
    }

    private void deletLayout(int position) {
        ImageItem bp = pathList.get(position);
        pathList.remove(bp);
        adapter.notifyDataSetChanged();
    }
}
