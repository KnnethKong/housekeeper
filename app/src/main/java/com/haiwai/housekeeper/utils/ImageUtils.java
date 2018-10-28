package com.haiwai.housekeeper.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;


import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.CommonConfig;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageUtils {

    private final static String SD_Path = CommonConfig.SD_Path;
    private final static String SD_Path_Upload = CommonConfig.SD_Path_Upload;

    /**
     * Check the SD card
     *
     * @return
     */
    public static boolean checkSDCardAvailable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * Get image from newwork
     *
     * @param path The path of image
     * @return byte[]
     * @throws Exception
     */
    public static byte[] getImage(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        InputStream inStream = conn.getInputStream();
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return readStream(inStream);
        }
        return null;
    }

    /**
     * Get image from newwork
     *
     * @param path The path of image
     * @return InputStream
     * @throws Exception
     */
    public static InputStream getImageStream(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return conn.getInputStream();
        }
        return null;
    }

    /**
     * Get data from stream
     *
     * @param inStream
     * @return byte[]
     * @throws Exception
     */
    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }

    /**
     * 保存文件
     *
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public static void saveFile(Bitmap bm, String fileName) throws IOException {
        File dirFile = new File(SD_Path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(SD_Path + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
    }

    /**
     * 选择图片路径对话框
     */
    public static void showSelectDialog(final Activity context) {
        DialogUtil.showSelecListtDialog(context, "请选择", new String[]{"拍照", "从手机相册选择"}, R.color.black, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                createMenuFile(context);
                switch (position) {
                    case 0:
                        camera(context, 0);
                        break;
                    case 1:
                        gallery(context, 1);
                        break;
                }
            }
        });
    }

    /*
   * 从相机获取
   */
    private static void camera(Activity context, int CAMERA) {
//        ImageLoader.getInstance().clearMemoryCache();
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        String fileName = new SimpleDateFormat("yyyyMMdd-hhmmss").format(new Date()) + ".png";
        File file = new File(SD_Path_Upload, "image_temp.png");
        if (file.exists()) {
            file.delete();
        }
        Uri imageUri = Uri.fromFile(file);
        // 指定照片保存路径（SD卡），image_temp.png，每次拍照后这个图片都会被替换
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        openCameraIntent.putExtra("return-data", false);
        openCameraIntent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION,
                Configuration.ORIENTATION_PORTRAIT);
        context.startActivityForResult(openCameraIntent, CAMERA);
    }

    /*
    * 从相册获取
    */
    private static void gallery(Activity context, int GALLERY) {
        Intent openAlbumIntent = new Intent(Intent.ACTION_PICK);
        openAlbumIntent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        context.startActivityForResult(openAlbumIntent, GALLERY);
    }


    private static final int PHOTOSIZE = 1024 * 1024;

    /**
     * 获取相机临时保存本地的照片并且缩小
     *
     * @return
     */
    public static Bitmap getRightBitmap() {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            // 设置inJustDecodeBounds为true
            opts.inJustDecodeBounds = true;
            // 使用decodeFile方法得到图片的宽和高
//            BitmapFactory.decodeFile(SD_Path_Upload
//                    + "image_temp.png", opts);
            // 打印出图片的宽和高
            if (((opts.outWidth * opts.outHeight) / PHOTOSIZE) / 2 > 0) {
                int size = ((opts.outWidth * opts.outHeight) / PHOTOSIZE) / 2;
                opts.inSampleSize = size;
            } else {
                opts.inSampleSize = 1;
            }
            opts.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(SD_Path_Upload + "image_temp.png", opts);
            bitmap = rotateBitmap(bitmap, SD_Path_Upload + "image_temp.png");
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 获取图片角度
     *
     * @param path
     * @return
     */
    private static int getBitmapDegree(String path) {
        int digress = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    digress = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    digress = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    digress = 270;
                    break;
                default:
                    return 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return digress;
    }


    /**
     * 相机图片方向问题
     */
    private static Bitmap rotateBitmap(Bitmap b, String path) {
        Matrix m = new Matrix();
        m.setRotate(getBitmapDegree(path), (float) b.getWidth(),
                (float) b.getHeight());
        try {
            Bitmap b2 = Bitmap.createBitmap(b, 0, 0, b.getWidth(),
                    b.getHeight(), m, true);
            if (b != b2) {
                b.recycle(); // Android开发网再次提示Bitmap操作完应该显示的释放
                b = b2;
            }
        } catch (OutOfMemoryError ex) {
// Android123建议大家如何出现了内存不足异常，最好return 原始的bitmap对象。.
        }
        return b;
    }

    /**
     * 获取相册中原有照片
     *
     * @param originalUri
     * @return
     */
    public static Bitmap getRightBitmap(Activity activity, Uri originalUri) {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options choose_opts = new BitmapFactory.Options();
            // 设置inJustDecodeBounds为true
            choose_opts.inJustDecodeBounds = true;
            // 使用decodeFile方法得到图片的宽和高
            BitmapFactory.decodeFile(
                    getRealFilePath(activity, originalUri),
                    choose_opts);
            // 打印出图片的宽和高
            if (((choose_opts.outWidth * choose_opts.outHeight) / PHOTOSIZE) / 2 > 0) {
                int size = ((choose_opts.outWidth * choose_opts.outHeight) / PHOTOSIZE) / 2;
                choose_opts.inSampleSize = size;
            } else {
                choose_opts.inSampleSize = 1;
            }
            choose_opts.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(
                    getRealFilePath(activity, originalUri),
                    choose_opts);
            // 打印出图片的宽和高

        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * Try to return the absolute file path from the given Uri
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePath(final Activity context, final Uri uri) {
        if (null == uri)
            return null;
        final String scheme = uri.getScheme();
        String path = null;
        if (scheme == null)
            path = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            path = uri.getPath();
            if (path != null && path.endsWith(".gif")) {
                // Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
//				EvmUtil.selPictureFromAlbum(context);
                return null;
            }
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                cursor.moveToFirst();
                path = cursor.getString(cursor
                        .getColumnIndex(MediaStore.Images.ImageColumns.DATA));
                cursor.close();
                if (path != null && path.endsWith(".gif")) {
                    // Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
//					EvmUtil.selPictureFromAlbum(context);
                    return null;
                }
            }
        }
        return path;
    }

    /**
     * Save image to the SD card
     *
     * @param bitmap
     */
    public static File saveBitmap2SDCard(Bitmap bitmap, String fileName) {
        if (bitmap == null)
            return null;

        File file = new File(CommonConfig.SD_Path_Upload, fileName);//将要保存图片的路径
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
//            if (fileName.endsWith(".jpg")) {
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);
//            } else {
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
//            }
            bitmap.compress(Bitmap.CompressFormat.PNG, 30, out);
            out.flush();
            LogUtil.i("bitmap2File", "已经保存");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (Exception e) {
                    LogUtil.e("FileSave", "saveDrawableToFile, close error");
                }
            }
        }
        return file;
    }


    public Bitmap file2Bitmap(Bitmap path) {
//        //图片文件转为Bitmap对象
//        String filePath="c:/01.jpg";
//        Bitmap bitmap=BitmapFactory.decodeFile(filePath);

        //如果图片过大，可能导致Bitmap对象装不下图片
        //解决办法：
        String filePath = "c:/01.jpg";
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, getBitmapOption(2)); //将图片的长和宽缩小味原来的1/2
        return bitmap;
    }

    private BitmapFactory.Options getBitmapOption(int inSampleSize) {
        System.gc();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        options.inSampleSize = inSampleSize;
        return options;
    }

    private static void createMenuFile(Activity context) {
        File upDir1;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // 保存到sd卡
            upDir1 = new File(CommonConfig.SD_Path_Upload);
//                upDir2 = new File(CommonConfig.SD_Path_VIDEO);
        } else {
            // 保存到file目录
            upDir1 = new File(context.getFilesDir().getAbsolutePath()
                    + File.separator + "bangbang/upload/image/");
//                upDir2 = new File(getFilesDir().getAbsolutePath()
//                        + File.separator + "bangbang/upload/video/");
        }

        if (!upDir1.exists()) {
            upDir1.mkdirs();
        }
//            if (!upDir2.exists()) {
//                upDir2.mkdir();
//            }

    }

}
