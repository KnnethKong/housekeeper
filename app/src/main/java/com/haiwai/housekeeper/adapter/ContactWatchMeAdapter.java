package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.ProDetailActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.PersonEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.imageloader.ImageLoader;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.CircleImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/2.
 */
public class ContactWatchMeAdapter extends BaseAdapter {
    private Context context;
    List<PersonEntity.DataBean> list;
    ImageLoader mImageLoader;

    public ContactWatchMeAdapter(Context context, List<PersonEntity.DataBean> data) {
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
        final int pos = position;
        final ViewHolder vh = ViewHolder.get(context, parent, R.layout.user_item_contact_watch_me, position, convertView);
        TextView tvname = vh.getview(R.id.item_contact_watch_me_iv_name);
        tvname.setText(list.get(position).getNickname());
        CircleImageView imageView = vh.getview(R.id.item_contact_watch_me_iv_head);
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
        final ImageButton img = ((ImageButton) vh.getview(R.id.item_contact_watch_me_ib_cancel));
        img.setTag(position);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,String> map = new HashMap<>();
                map.put("fuid", AppGlobal.getInstance().getUser().getUid());
                map.put("uid", list.get(Integer.valueOf(img.getTag().toString())).getFuid());
                map.put("secret_key", SPUtils.getString(context, "secret", ""));
                map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());

//                if(img.getTag().toString().equals("guanzhu")){
                    MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.follow_add, map, null, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            int code = JsonUtils.getJsonInt(response, "status");
                            if (code == 200) {
                                img.setTag("no");
                                img.setImageResource(R.mipmap.icon_cancel_watch);
//                                ToastUtil.longToast(context, context.getString(R.string.pro_detail_watch_success));
                                list.remove(position);
                                notifyDataSetChanged();
                            } else {
                                ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                            }
                        }
                    }));

            }
        });
        return vh.getContentView();
    }
}
