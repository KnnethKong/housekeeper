package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
public class ContactIWatchAdapter extends BaseAdapter {
    private Context context;
    private List<PersonEntity.DataBean> dbList;
    ImageLoader mImageLoader;

    public ContactIWatchAdapter(Context context, List<PersonEntity.DataBean> list) {
        this.context = context;
        mImageLoader = new ImageLoader(context);
        this.dbList = list;
    }

    @Override
    public int getCount() {
        if (dbList != null && dbList.size() > 0) {
            return dbList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return dbList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh = ViewHolder.get(context, parent, R.layout.user_item_contact_i_watch, position, convertView);
        TextView tvname = vh.getview(R.id.item_contact_i_watch_iv_name);
        tvname.setText(dbList.get(position).getNickname());
        CircleImageView imageView = vh.getview(R.id.item_contact_i_watch_iv_head);
        if (TextUtils.isEmpty(dbList.get(position).getAvatar())) {
            imageView.setImageResource(R.mipmap.moren_head);
        } else {
            MyApp.getImageLoader().displayImage(dbList.get(position).getAvatar(), imageView);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("oid", "");
                bundle.putString("nickname", dbList.get(position).getNickname());
                bundle.putString("uid", dbList.get(position).getUid());
                bundle.putString("type", "");
                bundle.putString("choose", "0");
                bundle.putString("isFromContact","1");
                ActivityTools.goNextActivity(context, ProDetailActivity.class, bundle);
            }
        });
        return vh.getContentView();
    }
}
