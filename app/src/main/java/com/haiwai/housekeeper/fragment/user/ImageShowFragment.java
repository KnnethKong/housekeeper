package com.haiwai.housekeeper.fragment.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.BaseFragment;
import com.haiwai.housekeeper.utils.ImageLoaderUtils;
import com.haiwai.housekeeper.view.PinchImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by ihope007 on 2016/9/1.
 */
public class ImageShowFragment extends BaseFragment {
    PinchImageView imageview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_image_show_viewpage, container, false);
        return rootView;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        imageview = (PinchImageView) view
                .findViewById(R.id.item_image_show_viewpage_imageview);
        imageview.setOnClickListener(this);
    }

    @Override
    protected void initData() {
}

    public void initImage(String url){
        if (imageview!=null)
        ImageLoader.getInstance().displayImage(url, imageview, ImageLoaderUtils.getOptions());
    }

    @Override
    protected void click(View v) {
        switch (v.getId()){
            case R.id.item_image_show_viewpage_imageview:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
