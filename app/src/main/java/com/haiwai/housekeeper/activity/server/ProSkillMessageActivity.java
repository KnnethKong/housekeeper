package com.haiwai.housekeeper.activity.server;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.amap.api.maps2d.model.Text;
import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.IdCardAndSkillEntity;
import com.haiwai.housekeeper.entity.ImageItem;
import com.haiwai.housekeeper.entity.SkillEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.https.okhttp.OKRequestCallback;
import com.haiwai.housekeeper.https.okhttp.OkHttpUtils;
import com.haiwai.housekeeper.imageloader.ImageLoader;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.BimpUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.FileUtils;
import com.haiwai.housekeeper.utils.IdCardJsonUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.SkillCertificateItemView;
import com.haiwai.housekeeper.view.SkillCertificateView;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ProSkillMessageActivity extends BaseProActivity {
    private static int REQUESTCODE = 0;
    private static int REQUESTCODEB = 1;
    private static final int REQUESTCODE_A = 100;
    private static final int REQUESTCODE_B = 101;
    private static final int REQUESTCODE_C = 102;
    private static final int REQUESTCODE_D = 103;
    private static final int REQUESTCODE_E = 104;
    private static final int REQUESTCODE_F = 105;
    private String path1, path2, path3;
    private TopViewNormalBar mSkillMessageBar;
    private ImageView scView, zmView, fmView, scdView, zmdView, fmdView;
    private TextView tv_content_state;
    private TextView sfSubBtn;
    PopupWindow popWindow;
    LinearLayout ll_popup;
    private User user;
    private Context pContext;
    //技能认证部分
    private LinearLayout ll_new_skill_layout;
    //模拟数据
//    private List<SkillCertificate> scs = null;
    private List<SkillEntity.DataBean> mSkillEntityList = new ArrayList<>();
    ImageLoader imageLoader;
    private String picPath1, picPath2, picPath3;
    private TextView tv_concon;
    private List<ImageItem> pathList = null;
    private List<List<List<ImageItem>>> listss = new ArrayList<>();
    private GridView picLayout;
    SkillCertificateItemView newItemView;
    private String isZhorEn = "";

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    LoadDialog.closeProgressDialog();
                    break;
                case 1:
                    LoadDialog.closeProgressDialog();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pContext = ProSkillMessageActivity.this;
        imageLoader = new ImageLoader(pContext);
        setContentView(R.layout.activity_pro_skill_message);
        mSkillMessageBar = (TopViewNormalBar) findViewById(R.id.top_skill_messagee_bar);
        mSkillMessageBar.setTitle(getString(R.string.skill_rz));
        mSkillMessageBar.setOnBackListener(mOnClickListener);
        initView();
        initData();

    }


    private void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        user = AppGlobal.getInstance().getUser();
        mSkillEntityList = SPUtils.getList(ProSkillMessageActivity.this, "mList");
        initNetdata();
    }

    private void initNetdata() {
        LoadDialog.showProgressDialog(ProSkillMessageActivity.this);
        Map<String, String> map = new HashMap<>();
        map.put("uid", user.getUid());
        map.put("secret_key", SPUtils.getString(ProSkillMessageActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProSkillMessageActivity.this, Contants.ren_lst, map, null, new Response.Listener<String>() { @Override
            public void onResponse(String response) {
                System.out.println("认证>>>>>>>>>>>>>>>>>>" + response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    Message msg = Message.obtain();
                    msg.what = 1;
                    mHandler.sendMessage(msg);
                    IdCardAndSkillEntity isEntity = IdCardJsonUtils.getIdCardAndSkillEntity(response);
                    bindDataToView(isEntity);
                    initSkillView(isEntity.getData().getUser_skill());
                } else {
                    ToastUtil.shortToast(ProSkillMessageActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }


    private void bindDataToView(IdCardAndSkillEntity isEntity) {
        IdCardAndSkillEntity.DataBean.UserIdcardBean userIcardBean = isEntity.getData().getUser_idcard();
        if (userIcardBean != null) {
            if ("0".equals(userIcardBean.getIdcard_ren()) || "1".equals(userIcardBean.getIdcard_ren())) {//审核中或者是已通过审核，都不可改变
                if (!TextUtils.isEmpty(userIcardBean.getIdcard_shou())) {
                    imageLoader.DisplayImage(userIcardBean.getIdcard_shou(), scView);
                    picPath1 = userIcardBean.getIdcard_shou();
                    scView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return true;
                        }
                    });
                }
            } else {
                if (!TextUtils.isEmpty(userIcardBean.getIdcard_shou())) {
                    imageLoader.DisplayImage(userIcardBean.getIdcard_shou(), scView);
                    scView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return false;
                        }
                    });
                    scdView.setVisibility(View.VISIBLE);
                }
            }
            if ("0".equals(userIcardBean.getIdcard_ren()) || "1".equals(userIcardBean.getIdcard_ren())) {//审核中或者是已通过审核，都不可改变
                if (!TextUtils.isEmpty(userIcardBean.getIdcard_zheng())) {
                    imageLoader.DisplayImage(userIcardBean.getIdcard_zheng(), zmView);
                    picPath2 = userIcardBean.getIdcard_zheng();
                    zmView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return true;
                        }
                    });
                    sfSubBtn.setVisibility(View.GONE);
                }

            } else {
                if (!TextUtils.isEmpty(userIcardBean.getIdcard_zheng())) {
                    imageLoader.DisplayImage(userIcardBean.getIdcard_zheng(), zmView);
                    zmView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return false;
                        }
                    });
                    zmdView.setVisibility(View.VISIBLE);
                    sfSubBtn.setVisibility(View.VISIBLE);
                }

            }
            if ("0".equals(userIcardBean.getIdcard_ren()) || "1".equals(userIcardBean.getIdcard_ren())) {//审核中或者是已通过审核，都不可改变
                if (!TextUtils.isEmpty(userIcardBean.getIdcard_fan())) {
                    imageLoader.DisplayImage(userIcardBean.getIdcard_fan(), fmView);
                    picPath3 = userIcardBean.getIdcard_fan();
                    fmView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return true;
                        }
                    });
                    sfSubBtn.setVisibility(View.GONE);
                }
            } else {
                if (!TextUtils.isEmpty(userIcardBean.getIdcard_fan())) {
                    imageLoader.DisplayImage(userIcardBean.getIdcard_fan(), fmView);
                    fmView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return false;
                        }
                    });
                    fmdView.setVisibility(View.VISIBLE);
                    sfSubBtn.setVisibility(View.VISIBLE);
                }
            }
//            if ("0".equals(userIcardBean.getIdcard_ren())) {
//                sfSubBtn.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        return false;
//                    }
//                });
//            } else{
//                sfSubBtn.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        return true;
//                    }
//                });
//            }
        }
        if(userIcardBean.getIdcard_fan().equals("")||userIcardBean.getIdcard_zheng().equals("")||userIcardBean.getIdcard_shou().equals("")){
            tv_content_state.setText(getString(R.string.skill_status_r1));
        }else{
            tv_content_state.setText("(" + AssetsUtils.getSFStatus(ProSkillMessageActivity.this,userIcardBean.getIdcard_ren()) + ")");
        }

    }

    private void initView() {
        tv_content_state = (TextView) findViewById(R.id.tv_content_state);
        scView = (ImageView) findViewById(R.id.imageView1);
        scView.setOnClickListener(mOnClickListener);
        zmView = (ImageView) findViewById(R.id.imageView2);
        zmView.setOnClickListener(mOnClickListener);
        fmView = (ImageView) findViewById(R.id.imageView3);
        fmView.setOnClickListener(mOnClickListener);
        scdView = (ImageView) findViewById(R.id.imageView4);
        scdView.setOnClickListener(mOnClickListener);
        zmdView = (ImageView) findViewById(R.id.imageView5);
        zmdView.setOnClickListener(mOnClickListener);
        fmdView = (ImageView) findViewById(R.id.imageView6);
        fmdView.setOnClickListener(mOnClickListener);
        sfSubBtn = (TextView) findViewById(R.id.ib_sf_submit);
        sfSubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subData();
//                ToastUtil.shortToast(ProSkillMessageActivity.this,"caonima");
            }
        });
    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mSkillMessageBar.getBackView()) {
                finish();
            } else if (v.getId() == R.id.imageView1) {
                ShowPopupWindow(REQUESTCODE_A, REQUESTCODE_D);
            } else if (v.getId() == R.id.imageView2) {
                ShowPopupWindow(REQUESTCODE_B, REQUESTCODE_E);
            } else if (v.getId() == R.id.imageView3) {
                ShowPopupWindow(REQUESTCODE_C, REQUESTCODE_F);
            } else if (v.getId() == R.id.imageView4) {
                DeleteView(REQUESTCODE_A);
            } else if (v.getId() == R.id.imageView5) {
                DeleteView(REQUESTCODE_B);
            } else if (v.getId() == R.id.imageView6) {
                DeleteView(REQUESTCODE_C);
            } else if (v.getId() == R.id.ib_sf_submit) {

            } else if (v.getId() == R.id.item_popupwindows_camera) {//调用相机拍照
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(ProSkillMessageActivity.this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ProSkillMessageActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            0);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(ProSkillMessageActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ProSkillMessageActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                1);
                    } else {
                        takePhoto();
                        popWindow.dismiss();
                        ll_popup.clearAnimation();
                    }
                }
            } else if (v.getId() == R.id.item_popupwindows_Photo) {//调用系统相册
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(ProSkillMessageActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ProSkillMessageActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                } else {
                    choosePhoto();
                    popWindow.dismiss();
                    ll_popup.clearAnimation();
                }
            } else if (v.getId() == R.id.item_popupwindows_cancel)

            {//取消
                popWindow.dismiss();
                ll_popup.clearAnimation();
            }
        }
    };

    private void choosePhoto() {
        Intent local = new Intent();
        local.setType("image/*");
        local.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(local, REQUESTCODEB);
    }


    private void ShowPopupWindow(int a, int b) {
        View view = View
                .inflate(ProSkillMessageActivity.this, R.layout.item_popupwindows, null);
        view.startAnimation(AnimationUtils.loadAnimation(ProSkillMessageActivity.this,
                R.anim.alpha_fade_in));
        ll_popup = (LinearLayout) view
                .findViewById(R.id.ll_popup);
        ll_popup.startAnimation(AnimationUtils.loadAnimation(ProSkillMessageActivity.this,
                R.anim.translate_bottom_in));
        TextView bt1 = (TextView) view
                .findViewById(R.id.item_popupwindows_camera);
        TextView bt2 = (TextView) view
                .findViewById(R.id.item_popupwindows_Photo);
        TextView bt3 = (TextView) view
                .findViewById(R.id.item_popupwindows_cancel);
        bt1.setOnClickListener(mOnClickListener);
        bt2.setOnClickListener(mOnClickListener);
        bt3.setOnClickListener(mOnClickListener);
        popWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        REQUESTCODE = a;
        REQUESTCODEB = b;
    }

    private void takePhoto() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, REQUESTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            if (pathList.size() < 3) {
                String fileName = String.valueOf(System.currentTimeMillis());
                String path = FileUtils.SDPATH + fileName + ".JPEG";
                Bitmap bm = (Bitmap) data.getExtras().get("data");
                ImageItem takePhoto = new ImageItem();
                takePhoto.setImagePath(path);
                takePhoto.setBitmap(bm);
                pathList.add(takePhoto);
            }
            newItemView.setPathListAdaper(pathList, false);
        } else if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            if (pathList.size() < 3) {
                Uri uri = data.getData();
                String path = FileUtils.getPath(ProSkillMessageActivity.this, uri);
                Bitmap bm = null;
                try {
                    bm = BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(path), 140, 140);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ImageItem takePhoto = new ImageItem();
                takePhoto.setImagePath(path);
                takePhoto.setBitmap(bm);
                pathList.add(takePhoto);
            }
            newItemView.setPathListAdaper(pathList, false);
        } else if (requestCode == REQUESTCODE && resultCode == RESULT_OK && data != null) {//身份证
            String fileName = String.valueOf(System.currentTimeMillis());
            Bitmap bm = (Bitmap) data.getExtras().get("data");
            FileUtils.saveBitmap(bm, fileName);
            if (REQUESTCODE == REQUESTCODE_A) {
                path1 = FileUtils.SDPATH + fileName + ".JPEG";
                scView.setImageBitmap(bm);
                scdView.setVisibility(View.VISIBLE);
            } else if (REQUESTCODE == REQUESTCODE_B) {
                path2 = FileUtils.SDPATH + fileName + ".JPEG";
                zmView.setImageBitmap(bm);
                zmdView.setVisibility(View.VISIBLE);
            } else if (REQUESTCODE == REQUESTCODE_C) {
                path3 = FileUtils.SDPATH + fileName + ".JPEG";
                fmView.setImageBitmap(bm);
                fmdView.setVisibility(View.VISIBLE);
            }
        } else if (requestCode == REQUESTCODEB && resultCode == RESULT_OK && data != null) {
            ContentResolver con = getContentResolver();
            if (REQUESTCODE == REQUESTCODE_A) {//身份发布
                Uri uri = data.getData();
                path1 = FileUtils.getPath(ProSkillMessageActivity.this, uri);
                try {
                    scView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(path1), 140, 140));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                scdView.setVisibility(View.VISIBLE);
            } else if (REQUESTCODE == REQUESTCODE_B) {
                Uri uri = data.getData();
                path2 = FileUtils.getPath(ProSkillMessageActivity.this, uri);
                try {
                    zmView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(path2), 140, 140));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                zmdView.setVisibility(View.VISIBLE);
            } else if (REQUESTCODE == REQUESTCODE_C) {
                Uri uri = data.getData();
                path3 = FileUtils.getPath(ProSkillMessageActivity.this, uri);
                try {
                    fmView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(path3), 140, 140));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fmdView.setVisibility(View.VISIBLE);
            }
        }
    }


    private void DeleteView(int requestcode) {
        if (requestcode == REQUESTCODE_A) {
            path1 = null;
            picPath1 = null;
            scView.setImageResource(R.mipmap.pic_add);
            scdView.setVisibility(View.INVISIBLE);
        } else if (requestcode == REQUESTCODE_B) {
            path2 = null;
            picPath2 = null;
            zmView.setImageResource(R.mipmap.pic_add);
            zmdView.setVisibility(View.INVISIBLE);
        } else if (requestcode == REQUESTCODE_C) {
            path3 = null;
            picPath3 = null;
            fmView.setImageResource(R.mipmap.pic_add);
            fmdView.setVisibility(View.INVISIBLE);
        }
    }

    private void subData() {
        if (user == null) {
//            ToastUtil.shortToast(pContext, "暂没登录！");
            return;
        } else if (picPath1 == null && path1 == null) {
            ToastUtil.shortToast(pContext, getString(R.string.up_id_card));
        } else if (picPath2 == null && path2 == null) {
            ToastUtil.shortToast(pContext, getString(R.string.up_front_card));
        } else if (picPath3 == null && path3 == null) {
            ToastUtil.shortToast(pContext, getString(R.string.up_back_card));
        } else {
            LoadDialog.showProgressDialog(ProSkillMessageActivity.this);
            Map<String, String> mapstr = new HashMap<>();
            mapstr.put("uid", user.getUid());
            mapstr.put("secret_key", SPUtils.getString(ProSkillMessageActivity.this, "secret", ""));
            mapstr.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
            Map<String, String> mapFile = new HashMap<>();
            if (picPath1 == null) {
                mapFile.put("idcard_shou", path1);
            }
            if (picPath2 == null) {
                mapFile.put("idcard_zheng", path2);
            }
            if (picPath3 == null) {
                mapFile.put("idcard_fan", path3);
            }


            RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(ProSkillMessageActivity.this,mapstr, mapFile);
            Request request = new Request.Builder()
                    .url(Contants.id_card)
                    .post(body)
                    .build();

            OkHttpUtils.getInstance().execCallback(pContext, request, new OKRequestCallback() {
                @Override
                public void onResponse(String response) {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        ToastUtil.shortToast(ProSkillMessageActivity.this, getString(R.string.upload_pic_success));
                        Message msg = Message.obtain();
                        msg.what = 0;
                        initData();
                        mHandler.sendMessage(msg);
                    } else {
                        LoadDialog.closeProgressDialog();
                        ToastUtil.shortToast(pContext, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            });
        }

    }

//======================================认证功能====================================================

    private void ShowPopupWindow() {
        View view = View
                .inflate(ProSkillMessageActivity.this, R.layout.item_popupwindows, null);
        view.startAnimation(AnimationUtils.loadAnimation(ProSkillMessageActivity.this,
                R.anim.alpha_fade_in));
        ll_popup = (LinearLayout) view
                .findViewById(R.id.ll_popup);
        ll_popup.startAnimation(AnimationUtils.loadAnimation(ProSkillMessageActivity.this,
                R.anim.translate_bottom_in));
        TextView bt1 = (TextView) view
                .findViewById(R.id.item_popupwindows_camera);
        TextView bt2 = (TextView) view
                .findViewById(R.id.item_popupwindows_Photo);
        TextView bt3 = (TextView) view
                .findViewById(R.id.item_popupwindows_cancel);
        bt1.setOnClickListener(mOnClickListener);
        bt2.setOnClickListener(mOnClickListener);
        bt3.setOnClickListener(mOnClickListener);
        popWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    boolean flag = false;

    private void initSkillView(List<IdCardAndSkillEntity.DataBean.UserSkillBean> user_skill) {//初始化主布局
        ll_new_skill_layout = (LinearLayout) findViewById(R.id.ll_new_skill_layout);
        if (ll_new_skill_layout.getChildCount() != 0) {
            ll_new_skill_layout.removeAllViews();
        }
        if (mSkillEntityList == null && mSkillEntityList.size() == 0) {
            return;
        }
        for (int i = 0; i < mSkillEntityList.size(); i++) {//根据技能数目初始化二级内容
            SkillCertificateView scw = new SkillCertificateView(pContext);
            scw.setType(mSkillEntityList.get(i).getType());
            scw.setTxtName(AssetsUtils.getSkillName(mSkillEntityList.get(i).getType(), isZhorEn));
            if ("1".equals(mSkillEntityList.get(i).getIs_audit())) {
                scw.setTxtState(AssetsUtils.getStatus1(ProSkillMessageActivity.this,mSkillEntityList.get(i).getIs_ren()));
            } else {
                scw.setTxtState(AssetsUtils.getStatus1(ProSkillMessageActivity.this,"0"));
            }
            ll_new_skill_layout.addView(scw);
        }
        for (int i = 0; i < ll_new_skill_layout.getChildCount(); i++) {
            final SkillCertificateView childView = (SkillCertificateView) ll_new_skill_layout.getChildAt(i);
            childView.switchState(false);
            if (user_skill.get(i).getSkill_ren().size() != 0 && user_skill.get(i).getSkill_ren() != null) {//判断是否有技能认证
                List<List<ImageItem>> lists = new ArrayList<>();
                for (int a = 0; a < user_skill.get(i).getSkill_ren().size(); a++) {//有
                    List<ImageItem> pList = new ArrayList<>();
                    final SkillCertificateItemView skillCertificateItemView = new SkillCertificateItemView(pContext);
                    skillCertificateItemView.setTitleText(user_skill.get(i).getSkill_ren().get(a).getZname());
                    if (!"2".equals(user_skill.get(i).getIs_ren())) {
                        skillCertificateItemView.setContentView(true);
                    }
                    for (int b = 0; b < user_skill.get(i).getSkill_ren().get(a).getZimg().size(); b++) {
                        ImageItem ii = new ImageItem();
                        ii.setStatus(user_skill.get(i).getSkill_ren().get(a).getIs_ren());
                        ii.setImagePath(user_skill.get(i).getSkill_ren().get(a).getZimg().get(b));
                        pList.add(ii);
                    }
                    lists.add(pList);

                    skillCertificateItemView.setPathListAdaper(pList, true);
                    childView.getContentLayout().addView(skillCertificateItemView);
                }
                listss.add(lists);
                for (int j = 0; j < childView.getContentLayout().getChildCount(); j++) {
                    String status = user_skill.get(i).getSkill_ren().get(j).getIs_ren();
                    final SkillCertificateItemView sckiView = (SkillCertificateItemView) childView.getContentLayout().getChildAt(j);
                    TextView ibSubmit = sckiView.getIbSubmit();
                    TextView tvDelLayout = sckiView.getTvDelLayout();
                    TextView tvContent = sckiView.getTvConcon();
                    GridView gridview = sckiView.getImgLayout();
                    final EditText tvTitle = sckiView.getCerName();
                    final int finalJ = j;
                    if (!"2".equals(status)) {
                        tvTitle.setFocusable(false);
                        ibSubmit.setVisibility(View.GONE);
                        tvDelLayout.setVisibility(View.GONE);
                        tvContent.setVisibility(View.GONE);
                        gridview.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                return true;
                            }
                        });
                    } else {
                        ibSubmit.setVisibility(View.VISIBLE);
                        tvDelLayout.setVisibility(View.VISIBLE);
                        tvContent.setVisibility(View.VISIBLE);
                        final int finalI4 = i;
                        ibSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                subSkillData(tvTitle.getText().toString().trim(), childView.getType(), finalI4, finalJ);
                            }
                        });
                        tvDelLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                listss.remove(finalJ);
                                childView.getContentLayout().removeViewAt(finalJ);
                            }
                        });
                        final int finalI = i;
                        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                newItemView = (SkillCertificateItemView) childView.getContentLayout().getChildAt(finalJ);
                                pathList = listss.get(finalI).get(finalJ);
                                ShowPopupWindow();
                            }
                        });
                    }
                }
            } else {
                final SkillCertificateItemView skillCertificateItemView = new SkillCertificateItemView(pContext);

                childView.getContentLayout().addView(skillCertificateItemView);
                for (int j = 0; j < childView.getContentLayout().getChildCount(); j++) {
                    if(j==0){
                        skillCertificateItemView.setTextfirst();
                    }else {
                        skillCertificateItemView.setTextNofirst();
                    }
                    List<ImageItem> paList = new ArrayList<>();
                    List<List<ImageItem>> lists = new ArrayList<>();
                    lists.add(paList);
                    listss.add(lists);
                    final SkillCertificateItemView sckiView = (SkillCertificateItemView) childView.getContentLayout().getChildAt(j);
                    final int finalJ = j;
                    TextView ibSubmit = sckiView.getIbSubmit();
                    final EditText tvTitle = sckiView.getCerName();
                    final int finalI2 = i;
                    ibSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            ToastUtil.shortToast(pContext, "添加");
                            subSkillData(tvTitle.getText().toString().trim(), childView.getType(), finalI2, finalJ);
                        }
                    });
                    TextView tvDelLayout = sckiView.getTvDelLayout();
                    final int finalI1 = i;
                    tvDelLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listss.get(finalI1).remove(finalJ);
                            childView.getContentLayout().removeViewAt(finalJ);
                        }
                    });
                    GridView gridview = sckiView.getImgLayout();
                    gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            newItemView = (SkillCertificateItemView) childView.getContentLayout().getChildAt(finalJ);
                            pathList = listss.get(finalI1).get(finalJ);
                            ShowPopupWindow();
                        }
                    });
                }
            }

            final int finalI3 = i;
            childView.setOnContentClickListener(new View.OnClickListener() {//二级内容的事件处理
                @Override
                public void onClick(View v) {
                    if (v == childView.getArrow()) {
                        if (!flag) {
                            flag = true;
                            childView.switchState(flag);
                        } else {
                            flag = false;
                            childView.switchState(flag);
                        }
                    } else if (v == childView.getAddBtn()) {
                        addLayout(childView.getContentLayout(), childView.getType(), finalI3);//加载三级内容
                    }
                }
            });
        }
    }

    private void subSkillData(String cerName, String type, int i, int j) {
        LoadDialog.showProgressDialog(ProSkillMessageActivity.this);
        List<ImageItem> paLIst = listss.get(i).get(j);
        Map<String, String> strMap = new HashMap<>();
        strMap.put("uid", user.getUid());
        strMap.put("type", type);
        strMap.put("zname", cerName);
        strMap.put("secret_key", SPUtils.getString(ProSkillMessageActivity.this, "secret", ""));
        strMap.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        Map<String, String> fileMap = new HashMap<>();
        if (paLIst != null && paLIst.size() != 0) {
            for (int k = 0; k < paLIst.size(); k++) {
                String key = "zimg[" + k + "]";
                String value = paLIst.get(k).getImagePath();
                fileMap.put(key, value);
            }
        }
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(ProSkillMessageActivity.this,strMap, fileMap);
        Request request = new Request.Builder()
                .url(Contants.skill_ren)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(pContext, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(ProSkillMessageActivity.this, "上传成功");
                    Message msg = Message.obtain();
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.shortToast(pContext, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    private void addLayout(final LinearLayout contentLayout, String type, final int d) {//三级内容
        final SkillCertificateItemView sci = new SkillCertificateItemView(pContext);
        pathList = new ArrayList<>();
        listss.get(d).add(pathList);
        sci.setType(type);
        contentLayout.addView(sci);
        int count = contentLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            final SkillCertificateItemView sciChild = (SkillCertificateItemView) contentLayout.getChildAt(i);
            final int finalI = i;
            TextView ibSubmit = sciChild.getIbSubmit();
            final EditText tvTitle = sciChild.getCerName();
            ibSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    subSkillData(tvTitle.getText().toString().trim(), sciChild.getType(), d, finalI);
                }
            });
            TextView tvDelLayout = sciChild.getTvDelLayout();
            tvDelLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listss.get(d).remove(finalI);
                    contentLayout.removeViewAt(finalI);
                }
            });
            GridView gridview = sciChild.getImgLayout();
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    newItemView = (SkillCertificateItemView) contentLayout.getChildAt(finalI);
                    pathList = listss.get(d).get(finalI);
                    ShowPopupWindow();
                }
            });
        }
    }

}
