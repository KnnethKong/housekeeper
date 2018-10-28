package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.entity.ProListEntity;
import com.haiwai.housekeeper.fragment.user.FindFragment;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.ImageLoaderUtils;
import com.haiwai.housekeeper.view.CircleImageView;
import com.haiwai.housekeeper.view.ProGradeView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class FindNewAdapter extends BaseAdapter {

    private Context context;
    private List<ProListEntity.DataBean> list;
    private String isZhorEn = "";

    private Map<Integer,String> strMap = new HashMap<>();

    public FindNewAdapter(Context context, List<ProListEntity.DataBean> list) {
        this.context = context;
        this.list = list;
        isZhorEn = AppGlobal.getInstance().getLagStr();
    }

    public void setList(List<ProListEntity.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
    public View getView(final int position, final View convertView, ViewGroup parent) {
        ViewHolder vh = ViewHolder.get(context, parent, R.layout.item_o2o_detail, position, convertView);
        CircleImageView imageView = vh.getview(R.id.item_o2o_detail_iv_head);
        String avatar = list.get(position).getAvatar();
        final ImageView iv_user_level = vh.getview(R.id.iv_user_level);
        ImageLoader.getInstance().displayImage(avatar, imageView, ImageLoaderUtils.getAvatarOptions());
        LinearLayout llskill = vh.getview(R.id.item_o2o_detail_ll_skill);
        llskill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                context.showPopWindow(v);
            }
        });
        vh.setTextview(R.id.item_o2o_detail_tv_name, list.get(position).getNickname());
        vh.setTextview(R.id.item_o2o_detail_tv_skill_describe, list.get(position).getClassX());
        if (list.get(position).getKm() != null) {
            vh.setTextview(R.id.item_o2o_detail_tv_km, new DecimalFormat("###.0").format(Double.valueOf(list.get(position).getKm())) + "km");
        } else {
            vh.setTextview(R.id.item_o2o_detail_tv_km, "0.0km");
        }

        strMap.put(position,list.get(position).getS());


        if (strMap.get(position).equals("1")) {
            final String url = "drawable://" +R.mipmap.v_icon;
            ImageLoader.getInstance().displayImage(url,iv_user_level);
//            map1.get(position).setVisibility(View.VISIBLE);
        } else if (strMap.get(position).equals("2")) {
//            map2.get(position).setVisibility(View.VISIBLE);
            final String url = "drawable://" +R.mipmap.s_icon;
            ImageLoader.getInstance().displayImage(url,iv_user_level);
        }else{
//            map1.get(position).setVisibility(View.GONE);
//            map2.get(position).setVisibility(View.GONE);
            final String url = "";
            ImageLoader.getInstance().displayImage(url,iv_user_level);
        }


        ProGradeView proGradeView = vh.getview(R.id.item_o2o_detail_v_prograde);
//        proGradeView.setNum(300);
        proGradeView.setNum(Integer.valueOf(list.get(position).getPro_score()));
        int ordernum = Integer.valueOf(list.get(position).getOrder_num());
        if (ordernum == 0)
            vh.setTextview(R.id.item_o2o_detail_tv_danping, context.getString(R.string.o2o_detail_has_done) + "0" + context.getString(R.string.o2o_detail_dan_pingjia) + "0");// TODO: 2016/10/20 评价
        else
            vh.setTextview(R.id.item_o2o_detail_tv_danping, context.getString(R.string.o2o_detail_has_done) + list.get(position).getOrder_num() + context.getString(R.string.o2o_detail_dan_pingjia) + Float.valueOf(list.get(position).getXing_num()) / Integer.valueOf(list.get(position).getOrder_num()));// TODO: 2016/10/20 评价
        vh.setTextview(R.id.item_o2o_detail_tv_skill, AssetsUtils.getSkillName(list.get(position).getType(), isZhorEn));
        String sf_ren = list.get(position).getIdcard_ren();
        Log.i("sf_ren",sf_ren);
        ImageView iv_sf = vh.getview(R.id.item_o2o_detail_iv_sf);

        if(sf_ren.equals("0")){
            String url = "drawable://"+R.mipmap.o2o_item_sf_grey;
            ImageLoader.getInstance().displayImage(url,iv_sf);
        }else if(sf_ren.equals("1")){
            String url = "drawable://"+R.mipmap.shenfenrenzheng_card;
            ImageLoader.getInstance().displayImage(url,iv_sf);
        }else{
            String url = "drawable://"+R.mipmap.o2o_item_sf_grey;
            ImageLoader.getInstance().displayImage(url,iv_sf);
        }
        int jn_ren = Integer.valueOf(list.get(position).getIs_audit());
        ImageView iv_jn = vh.getview(R.id.item_o2o_detail_iv_jn);
        int is_skill = Integer.valueOf(list.get(position).getIs_skill());
        Log.i("jn_ren",jn_ren+"");

        if(jn_ren==1&&is_skill==1){
            String url = "drawable://"+R.mipmap.shenfenrenzheng_jiangbei;
            ImageLoader.getInstance().displayImage(url,iv_jn);
        }else{
            String url = "drawable://"+R.mipmap.o2o_item_jn_grey;
            ImageLoader.getInstance().displayImage(url,iv_jn);
        }

        return vh.getContentView();
    }
}
