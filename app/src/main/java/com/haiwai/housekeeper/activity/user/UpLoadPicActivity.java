package com.haiwai.housekeeper.activity.user;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.okhttp.OKRequestCallback;
import com.haiwai.housekeeper.https.okhttp.OkHttpUtils;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.BimpUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.FileUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by ihope007 on 2016/9/6.
 * 上传照片
 */
public class UpLoadPicActivity extends BaseActivity {
    private static final int REQUESTCODE_A = 0;
    private static final int REQUESTCODE_B = 1;
    private String path;
    private ImageView ivupload, ivdelete;
    private LinearLayout llcommit;
    private EditText etcontent;
    PopupWindow popWindow;
    LinearLayout ll_popup;
    private User user;
    InputMethodManager imm;

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_upload_pic, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        setTitle(getString(R.string.upload_pic_title), Color.parseColor("#FF3C3C3C"));
        ivupload = (ImageView) findViewById(R.id.upload_pic_iv_upload);
        ivdelete = (ImageView) findViewById(R.id.upload_pic_iv_delete);
        etcontent = (EditText) findViewById(R.id.upload_pic_et_content);
        llcommit = (LinearLayout) findViewById(R.id.upload_pic_ll_bottom);
        ivupload.setOnClickListener(this);
        ivdelete.setOnClickListener(this);
        llcommit.setOnClickListener(this);
    }

    private void ShowPopupWindow() {
        View view = View
                .inflate(UpLoadPicActivity.this, R.layout.item_popupwindows, null);
        view.startAnimation(AnimationUtils.loadAnimation(UpLoadPicActivity.this,
                R.anim.alpha_fade_in));
        ll_popup = (LinearLayout) view
                .findViewById(R.id.ll_popup);
        ll_popup.startAnimation(AnimationUtils.loadAnimation(UpLoadPicActivity.this,
                R.anim.translate_bottom_in));
        TextView bt1 = (TextView) view
                .findViewById(R.id.item_popupwindows_camera);
        TextView bt2 = (TextView) view
                .findViewById(R.id.item_popupwindows_Photo);
        TextView bt3 = (TextView) view
                .findViewById(R.id.item_popupwindows_cancel);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        popWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    @Override
    protected void initData() {
        user = AppGlobal.getInstance().getUser();
    }

    private void takePhoto() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, REQUESTCODE_A);
    }

    public void upLoadPic() {
        if (user == null) {
            ToastUtil.shortToast(this, getString(R.string.main_not_login));
            return;
        } else if (PlatUtils.getEditStr(etcontent)) {
            ToastUtil.shortToast(this, getString(R.string.upload_pic_add_pic_describe));
            return;
        } else if (path == null) {
            ToastUtil.shortToast(this, getString(R.string.upload_pic_add_pic));
        } else {
            LoadDialog.showProgressDialog(UpLoadPicActivity.this);
            Map<String, String> mapstr = new HashMap<>();
            mapstr.put("uid", user.getUid());
            mapstr.put("desc", etcontent.getText().toString().trim());
            mapstr.put("secret_key", SPUtils.getString(UpLoadPicActivity.this, "secret", ""));
            mapstr.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
            Map<String, String> mapFile = new HashMap<>();
            mapFile.put("img", path);
            RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(UpLoadPicActivity.this,mapstr, mapFile);
            Request request = new Request.Builder()
                    .url(Contants.photo_add)
                    .post(body)
                    .build();
            OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
                @Override
                public void onResponse(String response) {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        LoadDialog.closeProgressDialog();
                        ToastUtil.shortToast(UpLoadPicActivity.this, getString(R.string.upload_pic_success));
                        String flag = getIntent().getExtras().getString("flag");
                        if ("prodetail".equals(flag)) {
                            Intent intent = new Intent();
                            intent.setAction("newData");
                            sendBroadcast(intent);
                            finish();
                        } else {
                            setResult(RESULT_OK);
                            finish();
                        }
                    } else {
                        LoadDialog.closeProgressDialog();
                        ToastUtil.shortToast(UpLoadPicActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE_A && resultCode == RESULT_OK && data != null) {
            String fileName = String.valueOf(System.currentTimeMillis());
            Bitmap bm = (Bitmap) data.getExtras().get("data");
            FileUtils.saveBitmap(bm, fileName);
            path = FileUtils.SDPATH + fileName + ".JPEG";
            ivupload.setImageBitmap(bm);
            ivdelete.setVisibility(View.VISIBLE);
        } else if (requestCode == REQUESTCODE_B && resultCode == RESULT_OK && data != null) {
            ContentResolver con = getContentResolver();
            Uri uri = data.getData();
            path = FileUtils.getPath(UpLoadPicActivity.this, uri);
            try {
                ivupload.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(path), 140, 140));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ivdelete.setVisibility(View.VISIBLE);
        }
    }

    //此方法只是关闭软键盘
    private void hintKeyBoard() {
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.upload_pic_iv_upload:
                hintKeyBoard();
                ShowPopupWindow();
                break;
            case R.id.upload_pic_iv_delete:
                path = null;
                ivupload.setImageResource(R.mipmap.pic_add);
                ivdelete.setVisibility(View.INVISIBLE);
                break;
            case R.id.item_popupwindows_camera://调用相机拍照
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA},
                            0);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                1);
                    } else {
                        takePhoto();
                        popWindow.dismiss();
                        ll_popup.clearAnimation();
                    }
                }
                break;
            case R.id.item_popupwindows_Photo://调用系统相册
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                } else {
                    chosePhoto();
                    popWindow.dismiss();
                    ll_popup.clearAnimation();
                }
                break;
            case R.id.item_popupwindows_cancel://取消
                popWindow.dismiss();
                ll_popup.clearAnimation();
                break;
            case R.id.upload_pic_ll_bottom://提交
                upLoadPic();
                break;
        }
    }

    private void chosePhoto() {
        Intent local = new Intent();
        local.setType("image/*");
        local.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(local, REQUESTCODE_B);
    }
}
