package com.wn.library.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.wn.library.R;
import com.wn.library.base.BaseActivity;

import java.io.File;

/**
 * Created by 王宁 on 2017/2/15.
 */

public class ImgSelectWindow extends PopupWindow {

    public static final String IMGURL = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/icon.jpg";
    public static final int REQUESFROMTAKINGPHONE = 40;
    public static final int REQUESTFROMABLUM = 30;


    private Context context;
    private BaseActivity activity;
    private View view;
    private Intent intent;


    public ImgSelectWindow(Context context, BaseActivity activity) {
        this.context = context;
        this.activity = activity;
        initView();
        initListener();
    }

    private void initView() {
        view = LayoutInflater.from(context).inflate(R.layout.image_select_window_view, null);
        this.setContentView(view);
        this.setWidth(activity.getWindowManager().getDefaultDisplay().getWidth());
        this.setHeight(activity.getWindowManager().getDefaultDisplay().getHeight());
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.tv_white_shape_back_none));
    }

    public void initListener() {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        view.findViewById(R.id.ll_from_taking_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = new File(IMGURL);
                if (!file.exists()) {
                    file.delete();
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                intent.putExtra("return-data", false);
                activity.startActivityForResult(intent, REQUESFROMTAKINGPHONE);
            }
        });
        view.findViewById(R.id.ll_from_ablum).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");//相片类型
                activity.startActivityForResult(intent, REQUESTFROMABLUM);
            }
        });
        view.findViewById(R.id.ll_cancel_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


    public void show(View view) {
        if (this.isShowing()) {
            this.dismiss();
        } else {
            this.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }
    }
}
