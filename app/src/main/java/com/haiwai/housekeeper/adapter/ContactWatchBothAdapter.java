package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.ProDetailActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.PersonEntity;
import com.haiwai.housekeeper.imageloader.ImageLoader;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.view.CircleImageView;

import java.util.List;

/**
 * Created by ihope007 on 2016/9/2.
 */
public class ContactWatchBothAdapter extends BaseAdapter {
    private Context context;
    List<PersonEntity.DataBean> list;
    ImageLoader mImageLoader;

    public ContactWatchBothAdapter(Context context, List<PersonEntity.DataBean> data) {
        this.context = context;
        mImageLoader = new ImageLoader(context);
        this.list = data;
    }

    @Override
    public int getCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh = ViewHolder.get(context, parent, R.layout.user_item_contact_watch_both, position, convertView);
        TextView tvname = vh.getview(R.id.item_contact_watch_both_iv_name);
        ImageButton ibChat = vh.getview(R.id.item_contact_watch_both_ib_cancel);
        ibChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnChatClickListener.onChatPosition(position);
            }
        });
        tvname.setText(list.get(position).getNickname());
        CircleImageView imageView = vh.getview(R.id.item_contact_watch_both_iv_head);
        if (TextUtils.isEmpty(list.get(position).getAvatar())) {
            imageView.setImageResource(R.mipmap.moren_head);
        } else {
            MyApp.getImageLoader().displayImage(list.get(position).getAvatar(), imageView);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("oid", "");
                bundle.putString("nickname", list.get(position).getNickname());
                bundle.putString("uid", list.get(position).getFuid());
                bundle.putString("type", "");
                bundle.putString("choose", "0");
                bundle.putString("isFromContact","1");
                ActivityTools.goNextActivity(context, ProDetailActivity.class, bundle);
            }
        });
        return vh.getContentView();
    }

    public OnChatClickListener mOnChatClickListener;

    public interface OnChatClickListener {
        void onChatPosition(int i);
    }

    public void setOnChatClickListener(OnChatClickListener onChatClickListener) {
        mOnChatClickListener = onChatClickListener;
    }
}
