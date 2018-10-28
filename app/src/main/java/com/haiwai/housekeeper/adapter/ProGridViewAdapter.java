package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.utils.BimpUtils;

import java.io.File;

/**
 * Created by lyj on 2016/9/20.
 */
public class ProGridViewAdapter extends BaseAdapter {
    private LayoutInflater inflater; // 视图容器
    private int selectedPosition = -1;// 选中的位置
    private boolean shape;
    private Context context;
    private ProGridViewAdapter adapter;

    private OnImgDelListener mmOnImgDelListener;

    public void setOnImgDelListener(OnImgDelListener onImgDelListener) {
        this.mmOnImgDelListener = onImgDelListener;
    }

    public boolean isShape() {
        return shape;
    }

    public void setShape(boolean shape) {
        this.shape = shape;
    }

    public ProGridViewAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void update(ProGridViewAdapter adapter, String type) {
        this.adapter = adapter;
        loading(type);
    }

    public int getCount() {
//        if (BimpUtils.tempSelectBitmap.size() == 3) {
//            return 3;
//        }
        return (BimpUtils.tempSelectBitmap.size() + 1);
    }

    public Object getItem(int arg0) {

        return null;
    }

    public long getItemId(int arg0) {

        return 0;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    /**
     * ListView Item设置
     */
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_published_grida,
                    parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView
                    .findViewById(R.id.item_grida_image);
            holder.iv_del = (ImageView) convertView.findViewById(R.id.iv_del);
            holder.iv_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mmOnImgDelListener.onDelClick(position);
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Log.e("绑定图片", "图片列表数量>>>" + BimpUtils.tempSelectBitmap.size());
        if (position == BimpUtils.tempSelectBitmap.size()) {
//            holder.image.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.pic_add));
            Log.e("绑定图片", "显示添加图片按钮>>>" + position);
            holder.image.setImageResource(R.mipmap.pic_add);
            holder.image.setBackgroundResource(R.mipmap.pic_add);
            holder.iv_del.setVisibility(View.GONE);
//            if (position == 3) {
//                holder.image.setVisibility(View.GONE);
//            }
        } else {
//            holder.image.setImageBitmap(BimpUtils.tempSelectBitmap.get(position).getBitmap());
            Log.e("绑定图片", BimpUtils.tempSelectBitmap.get(position).getImagePath() + ">>>" + position);
            Glide.with(context)
                    .load(new File(BimpUtils.tempSelectBitmap.get(position).getImagePath()))
                    .placeholder(R.mipmap.pic_add)
                    .into(holder.image);
            holder.iv_del.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    public class ViewHolder {
        public ImageView image, iv_del;
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    adapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public void loading(final String type) {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if ("add".equals(type)) {
                        if (BimpUtils.max == BimpUtils.tempSelectBitmap.size()) {
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                            break;
                        } else {
                            BimpUtils.max += 1;
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                            break;
                        }
                    } else if ("del".equals(type)) {
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                        break;
                    }

                }
            }
        }).start();
    }

    public interface OnImgDelListener {
        void onDelClick(int i);
    }
}
