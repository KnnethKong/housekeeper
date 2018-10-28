package com.haiwai.housekeeper.activity.user;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.okhttp.OKRequestCallback;
import com.haiwai.housekeeper.https.okhttp.OkHttpUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.FileUtils;
import com.haiwai.housekeeper.utils.ImageLoaderUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.SDPathUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by ihope007 on 2016/9/6.
 * 我的——头像——个人信息
 */
public class PersonInfoActivity extends BaseActivity {
    private CircleImageView ivIcon;
    private ImageView person_page_iv_head_null;
    private EditText etNickName,
//            etUserName,
            et_introduce;
    private TextView tv_mobile;
    public static final int MODIFYA = 0;
    public static final int MODIFYB = 1;
    public static final int MODIFYC = 2;
    public static final int MODIFYD = 3;
    private static final int TAKE_PHOTO_WITH_DATA = 102;
    private static final int ALBUM_ONLY_THROUGH_DATA = 101;
    private static final int CAMERA_WITH_DATA = 100;
    User user;
    PopupWindow popWindow;
    LinearLayout ll_popup;
    private int crop = 180;
    private String isZhorEn = "";
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    initData();
                    break;
            }
        }
    };

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_person_info, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.person_info), Color.parseColor("#FF3C3C3C"));
        RelativeLayout rl_bg = (RelativeLayout) findViewById(R.id.person_page_rl_title);
        rl_bg.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, PlatUtils.getImageRario(this, 1, 2)));
        //liu_yjie addby 20162011
        ivIcon = (CircleImageView) findViewById(R.id.person_page_iv_head);
        ivIcon.setOnClickListener(this);
        person_page_iv_head_null = (ImageView) findViewById(R.id.person_page_iv_head_null);
        person_page_iv_head_null.setOnClickListener(this);
        etNickName = (EditText) findViewById(R.id.et_nickName);
//        etUserName = (EditText) findViewById(R.id.et_userName);
        et_introduce = (EditText) findViewById(R.id.et_introduce);
        tv_mobile = (TextView) findViewById(R.id.tv_mobile);
        etNickName.setOnClickListener(this);
//        etUserName.setOnClickListener(this);
        et_introduce.setOnClickListener(this);
        tv_mobile.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        user = AppGlobal.getInstance().getUser();
        etNickName.setText(user.getNickname());
//        etUserName.setText(user.getName());
        tv_mobile.setText(user.getMobile());
        et_introduce.setText(user.getIntroduction());
        if (TextUtils.isEmpty(user.getAvatar())) {
            person_page_iv_head_null.setVisibility(View.VISIBLE);
            ivIcon.setVisibility(View.GONE);
        } else {
            person_page_iv_head_null.setVisibility(View.GONE);
            ivIcon.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(user.getAvatar(), ivIcon, ImageLoaderUtils.getNewAvatarOptions());
        }

    }

    @Override
    protected void click(View v) {
        Intent intent = new Intent(PersonInfoActivity.this, ModifyInfoActivity.class);
        int flag = MODIFYA;
        if (v.getId() == R.id.person_page_iv_head || v.getId() == R.id.person_page_iv_head_null) {
            ShowPopupWindow();
        } else if (v.getId() == R.id.item_popupwindows_camera) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        CAMERA_WITH_DATA);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            CAMERA_WITH_DATA);
                } else {
                    imgCamera();
                    popWindow.dismiss();
                    ll_popup.clearAnimation();
                }
            }
        } else if (v.getId() == R.id.item_popupwindows_Photo) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        TAKE_PHOTO_WITH_DATA);
            } else {
                picAlbum();
                popWindow.dismiss();
                ll_popup.clearAnimation();
            }
        } else if (v.getId() == R.id.item_popupwindows_cancel) {
            popWindow.dismiss();
            ll_popup.clearAnimation();
        } else if (v.getId() == R.id.et_nickName) {
            intent.putExtra("type", MODIFYA);
            intent.putExtra("content", etNickName.getText().toString().trim());
            flag = MODIFYA;
            startActivityForResult(intent, flag);
        }
//        else if (v.getId() == R.id.et_userName) {
//            intent.putExtra("type", MODIFYB);
//            intent.putExtra("content", etUserName.getText().toString().trim());
//            flag = MODIFYB;
//            startActivityForResult(intent, flag);
//        }
    else if (v.getId() == R.id.tv_mobile) {
            Intent mIntent = new Intent(PersonInfoActivity.this,ChangePhoneActivity.class);
            mIntent.putExtra("phone", tv_mobile.getText().toString().trim());
            flag = MODIFYC;
            startActivityForResult(mIntent, flag);
        } else if (v.getId() == R.id.et_introduce) {
            intent.putExtra("content", et_introduce.getText().toString().trim());
            intent.putExtra("type", MODIFYD);
            flag = MODIFYD;
            startActivityForResult(intent, flag);
        }
    }


    private void picAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, ALBUM_ONLY_THROUGH_DATA);


    }

    private void imgCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_WITH_DATA);

    }

    private void ShowPopupWindow() {
        View view = View
                .inflate(PersonInfoActivity.this, R.layout.item_popupwindows, null);
        view.startAnimation(AnimationUtils.loadAnimation(PersonInfoActivity.this,
                R.anim.alpha_fade_in));
        ll_popup = (LinearLayout) view
                .findViewById(R.id.ll_popup);
        ll_popup.startAnimation(AnimationUtils.loadAnimation(PersonInfoActivity.this,
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == MODIFYA || requestCode == MODIFYB || requestCode == MODIFYC || requestCode == MODIFYD) && resultCode == RESULT_OK) {
            initData();
        } else if (requestCode == CAMERA_WITH_DATA && resultCode == RESULT_OK && data != null) {

            String fileName = String.valueOf(System.currentTimeMillis());
            Bitmap bm = (Bitmap) data.getExtras().get("data");
            FileUtils.saveBitmap(bm, fileName);
            String picPath = FileUtils.SDPATH + fileName + ".JPEG";
            File file = new File(picPath);
            Uri uri = Uri.fromFile(file);
            cropImg(uri);
        } else if (requestCode == ALBUM_ONLY_THROUGH_DATA && resultCode == RESULT_OK && data != null) {
            String picPath = FileUtils.getPaths(PersonInfoActivity.this, data.getData());
            File file = new File(picPath);
            Uri uri = Uri.fromFile(file);
            cropImg(uri);
        } else if (requestCode == TAKE_PHOTO_WITH_DATA && resultCode == RESULT_OK) {
            if (data == null) {
                // TODO 如果之前以后有设置过显示之前设置的图片 否则显示默认的图片
                return;
            }
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                String fileName = String.valueOf(System.currentTimeMillis());
                FileUtils.saveBitmap(photo, fileName);
                String picPath = FileUtils.SDPATH + fileName + ".JPEG";
                uploadPic(picPath);
            }

        }
    }

    private void cropImg(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");//调用Android系统自带的一个图片剪裁页面,
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");//进行修剪
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, TAKE_PHOTO_WITH_DATA);
    }

    private void uploadPic(final String picPath) {
        Map<String, String> strMap = new HashMap<>();
        strMap.put("uid", user.getUid());
        strMap.put("secret_key", SPUtils.getString(PersonInfoActivity.this, "secret", ""));
        strMap.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        Map<String, String> fileMap = new HashMap<>();
        fileMap.put("avatar", picPath);
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(PersonInfoActivity.this,strMap, fileMap);
        Request req = new Request.Builder()
                .url(Contants.change_info)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(PersonInfoActivity.this, req, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                Log.i("responseInformation",response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    String data = JsonUtils.getJsonStr(response, "data");
                    AppGlobal.getInstance().setUser(new Gson().fromJson(data, User.class));
                    ToastUtil.shortToast(PersonInfoActivity.this, getString(R.string.commit_success));
                    SPUtils.saveString(PersonInfoActivity.this, "path", picPath);
                    SPUtils.saveString(PersonInfoActivity.this, "local_url", null);
                    Message msg = Message.obtain();
                    msg.arg1 = 0;
                    mHandler.sendMessage(msg);
                } else {
                    ToastUtil.shortToast(PersonInfoActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }
}
