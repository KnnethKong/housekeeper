package com.haiwai.housekeeper.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.opengl.Visibility;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.HomeHomeServiceAdapter;
import com.haiwai.housekeeper.entity.ImageItem;
import com.haiwai.housekeeper.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ihope10 on 2016/10/10.
 */
public class SkillCertificateItemView extends LinearLayout {
    public EditText etCertificateName;
    private TextView desc;
    public TextView ibSubmit;
    public TextView tvDelLayout;
    public TextView tv_concon;
    public LinearLayout llCertificateLayout;
    public GridView noScrollgridview;
    public List<ImageItem> pathsList = new ArrayList<>();
    public LayoutInflater mLayoutInfalter;
    public ImageLoader mImageLoader;
    public GridAdapter adapter;
    public String type;
    boolean isRen = false;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SkillCertificateItemView(Context context) {
        this(context, null);
    }

    public SkillCertificateItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkillCertificateItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mImageLoader = new ImageLoader(context);
        mLayoutInfalter = LayoutInflater.from(context);
        View.inflate(context, R.layout.pro_skill_certificate_content_layout, this);
        initView();
    }

    private void initView() {
        etCertificateName = (EditText) findViewById(R.id.et_skill_certificate);
        desc= (TextView) findViewById(R.id.et_skill_desc);
        ibSubmit = (TextView) findViewById(R.id.ib_skill_subm);
        tvDelLayout = (TextView) findViewById(R.id.tv_del_skill);
        noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
        adapter = new GridAdapter();
        noScrollgridview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        tv_concon = (TextView) findViewById(R.id.tv_concon);
        llCertificateLayout = (LinearLayout) findViewById(R.id.ll_certificate_layout);
    }

    public void setPathListAdaper(List<ImageItem> pathList,boolean isflag) {
        pathsList = new ArrayList<>();
        pathsList = pathList;
        adapter = new GridAdapter();
        noScrollgridview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        this.isRen = isflag;
    }

    public TextView getTvConcon() {
        return tv_concon;
    }

    public void setTitleText(String str) {
        etCertificateName.setText(str);

    }
    public void setTextfirst() {
        //etCertificateName.setText(str);
        etCertificateName.setText("WBC保险证书");
        desc.setHint("请上传WBC保险证书");
        tv_concon.setHint(R.string.upload_pic1);
        etCertificateName.setFocusable(false);
        etCertificateName.setFocusableInTouchMode(false);
    }
    public void setTextNofirst() {
        etCertificateName.setText("");
        desc.setHint(R.string.up_load);
        tv_concon.setHint(R.string.upload_pic);
        etCertificateName.setFocusableInTouchMode(true);
        etCertificateName.setFocusable(true);
        etCertificateName.requestFocus();
    }

    public TextView getTvDelLayout() {
        return tvDelLayout;
    }

    public TextView getIbSubmit() {
        return ibSubmit;
    }


    public EditText getCerName() {
        return etCertificateName;
    }

    public LinearLayout getItemLayout() {
        return llCertificateLayout;
    }

    public GridView getImgLayout() {
        return noScrollgridview;
    }

    public void setViewVisible(boolean flag) {
        if (flag) {
            llCertificateLayout.setVisibility(View.VISIBLE);
        } else {
            llCertificateLayout.setVisibility(View.GONE);
        }
    }

    public void setContentView(boolean isHide) {
        if (isHide) {
            etCertificateName.setFocusable(false);
            tv_concon.setVisibility(View.GONE);
            ibSubmit.setVisibility(View.GONE);
            tvDelLayout.setVisibility(View.GONE);
        } else {
            etCertificateName.setFocusable(true);
            tv_concon.setVisibility(View.VISIBLE);
            ibSubmit.setVisibility(View.VISIBLE);
            tvDelLayout.setVisibility(View.VISIBLE);
        }

    }


    class GridAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (pathsList.size() == 3) {
                return 3;
            }
            return (pathsList.size() + 1);
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
            if (convertView == null) {
                convertView = mLayoutInfalter.inflate(R.layout.item_published_grida, null);
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
            if (position == pathsList.size()) {
                if(!isRen){
                    holder.image.setImageBitmap(BitmapFactory.decodeResource(
                            getResources(), R.mipmap.pic_add));
                } else {
                    holder.image.setVisibility(View.GONE);
                }
                holder.iv_del.setVisibility(View.GONE);
                if (position == 3) {
                    holder.image.setVisibility(View.GONE);
                }
            } else {
                if (pathsList.get(position).getBitmap() != null) {
                    holder.image.setImageBitmap(pathsList.get(position).getBitmap());
                } else {
                    if (!"2".equals(pathsList.get(position).getStatus())) {
                        holder.iv_del.setVisibility(View.GONE);
                    }
                    mImageLoader.DisplayImage(pathsList.get(position).getImagePath(), holder.image);
                }
            }
            return convertView;
        }


        class ViewHolder {
            ImageView image, iv_del;
        }
    }

    private void deletLayout(int position) {
        ImageItem bp = pathsList.get(position);
        pathsList.remove(bp);
        adapter.notifyDataSetChanged();
    }

}
