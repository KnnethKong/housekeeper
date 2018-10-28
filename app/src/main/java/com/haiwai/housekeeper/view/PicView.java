package com.haiwai.housekeeper.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.imageloader.ImageLoader;

/**
 * Created by ihope10 on 2016/10/20.
 */

public class PicView extends RelativeLayout {
    private ImageView image;
    private ImageView iv_del;
    private ImageLoader iImageLoader;
    public PicView(Context context) {
        this(context, null);
    }

    public PicView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.pic_view_layout,
                this);
        initView();
    }

    private void initView() {
        image = (ImageView) findViewById(R.id.item_grida_image);
        iv_del = (ImageView) findViewById(R.id.iv_del);
    }

    public void setOnImageDelListener(OnClickListener onClickListener){
        iv_del.setOnClickListener(onClickListener);
    }

    public ImageView getIv_del(){
        return  iv_del;
    }

    public ImageView getImage(){
        return image;
    }

    public void setImagLoader(ImageLoader imagLoader){
        this.iImageLoader = imagLoader;
    }

    public void setImgUrl(String url){
        iImageLoader.DisplayImage(url,image);
    }

    public void setImgBp(Bitmap bp){
        image.setImageBitmap(bp);
    }


}
