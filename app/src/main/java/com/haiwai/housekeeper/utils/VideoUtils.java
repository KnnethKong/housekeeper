package com.haiwai.housekeeper.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.view.View;
import android.widget.AdapterView;


import com.haiwai.housekeeper.R;

import java.io.File;

public class VideoUtils {

    public static void showSelectDialog(final Activity context, final String fileName) {
        DialogUtil.showSelecListtDialog(context, "请选择", new String[]{"录制新视频", "从手机相册选择"}, R.color.black, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        camera(context, fileName, 0);
                        break;
                    case 1:
                        toSdVideo(context, 1);
                        break;
                }
            }
        });
    }

    private static void camera(Activity context, String fileName, int CAMERA) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.media.action.VIDEO_CAPTURE");
            intent.addCategory("android.intent.category.DEFAULT");
//            File file = new File(fileName);
//            if (file.exists()) {
//                file.delete();
//            }
//            Uri uri = Uri.fromFile(file);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            context.startActivityForResult(intent, CAMERA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void toSdVideo(Activity context, int GALLERY) {
        try {
            Intent intent = new Intent(Intent.ACTION_PICK);
            //选择的格式为视频,图库中就只显示视频（如果图片上传的话可以改为image/*，图库就只显示图片）
            intent.setType("video/*");
            // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
            context.startActivityForResult(intent, GALLERY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getVideoThumbnail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    public static String getVideoTime(File file) {
        String duration = "0";
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        try {
            if (file != null) {
                mmr.setDataSource(file.getPath());
            }

            duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
//            String width = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
//            String height = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mmr.release();
        }

        return duration;
    }
}
