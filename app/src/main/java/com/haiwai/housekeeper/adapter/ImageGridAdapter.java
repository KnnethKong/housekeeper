package com.haiwai.housekeeper.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.entity.ImageItem;
import com.haiwai.housekeeper.utils.BimpUtils;
import com.haiwai.housekeeper.utils.BitmapCache;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageGridAdapter extends BaseAdapter {
    final String TAG = getClass().getSimpleName();
    Activity act;
    List<ImageItem> dataList;
    BitmapCache cache;
    private Handler mHandler;

    private Map<Integer,Boolean> boMap = new HashMap<>();
    private int selectTotal = 0;
    BitmapCache.ImageCallback callback = new BitmapCache.ImageCallback() {
        @Override
        public void imageLoad(ImageView imageView, Bitmap bitmap,
                              Object... params) {
            if (imageView != null && bitmap != null) {
                String url = (String) params[0];
                if (url != null && url.equals((String) imageView.getTag())) {
                    ((ImageView) imageView).setImageBitmap(bitmap);
                } else {
                    Log.e(TAG, "callback, bmp not match");
                }
            } else {
                Log.e(TAG, "callback, bmp null");
            }
        }
    };



    public ImageGridAdapter(Activity act, List<ImageItem> list, Handler mHandler) {
        this.act = act;
        dataList = list;
        cache = new BitmapCache();
        this.mHandler = mHandler;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (dataList != null) {
            count = dataList.size();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class Holder {
        private ImageView iv;
        private ImageView selected;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = View.inflate(act, R.layout.item_image_grid, null);
            holder.iv = (ImageView) convertView.findViewById(R.id.image);
            holder.selected = (ImageView) convertView
                    .findViewById(R.id.isselected);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        final int pos = position;


        if(boMap.get(pos)!=null&&boMap.get(pos)){
            holder.selected.setVisibility(View.VISIBLE);
        }else {
            holder.selected.setVisibility(View.GONE);
        }


        final ImageItem item = dataList.get(position);
        holder.iv.setTag(item.imagePath);
        cache.displayBmp(holder.iv, item.thumbnailPath, item.imagePath,
                callback);
        holder.iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BimpUtils.tempSelectBitmap.size() < Integer.MAX_VALUE) {
                    if (!item.isSelected) {
                        item.setIsSelected(true);
                        holder.selected
                                .setVisibility(View.VISIBLE);
                        boMap.put(pos,true);
//                        try {
//                            item.setBitmap(BimpUtils.revitionImageSize(item.imagePath));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                        BimpUtils.tempSelectBitmap.add(dataList.get(position));
                    } else if (item.isSelected) {
                        item.setIsSelected(false);
                        boMap.put(pos,false);
                        holder.selected.setVisibility(View.GONE);
                        ImageItem item =dataList.get(position);
                        BimpUtils.tempSelectBitmap.remove(item);
                    }
                } else if (BimpUtils.tempSelectBitmap.size() >= Integer.MAX_VALUE){
                    if (item.isSelected == true) {
                        item.isSelected = !item.isSelected;
                        holder.selected.setVisibility(View.GONE);
                        boMap.put(pos,false);
                    } else {
                        Message message = Message.obtain(mHandler, 0);
                        message.sendToTarget();
                    }
                }
            }

        });
        return convertView;
    }
}
