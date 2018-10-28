package com.haiwai.housekeeper.activity.base;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.MainActivity;
import com.haiwai.housekeeper.adapter.GuideViewPagerAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.utils.PreferenceUtils;
import com.haiwai.housekeeper.utils.SPKey;
import com.haiwai.housekeeper.utils.SPUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class GuidActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager vp;
    private GuideViewPagerAdapter adapter;
    private List<View> views;
    private Button startBtn;

    // 引导页图片资源
    private static final int[] pics = {R.layout.guid_view1,
            R.layout.guid_view2, R.layout.guid_view3, R.layout.guid_view4};

    private static final int[] pics_en = {R.layout.guid_view_en1,R.layout.guid_view_en2,
            R.layout.guid_view_ne3,R.layout.guid_view_en4};
    // 底部小点图片
    private ImageView[] dots;

    // 记录当前选中位置
    private int currentIndex;
    private LinearLayout mLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);


        views = new ArrayList<View>();
        initDots();
        // 初始化引导页视图列表
        if(getResources().getConfiguration().locale.getLanguage().equals("en")){
            for (int i = 0; i < pics_en.length; i++) {
                View view = LayoutInflater.from(this).inflate(pics_en[i], null);
                if (i == pics_en.length - 1) {
                    mLl.setVisibility(View.GONE);
                    startBtn = (Button) view.findViewById(R.id.btn_login);
                    startBtn.setOnClickListener(this);
                } else {
                    mLl.setVisibility(View.VISIBLE);
                }
                views.add(view);
            }
        }else{
            for (int i = 0; i < pics.length; i++) {
                View view = LayoutInflater.from(this).inflate(pics[i], null);
                if (i == pics.length - 1) {
                    mLl.setVisibility(View.GONE);
                    startBtn = (Button) view.findViewById(R.id.btn_login);
                    startBtn.setOnClickListener(this);
                } else {
                    mLl.setVisibility(View.VISIBLE);
                }
                views.add(view);
            }
        }


        vp = (ViewPager) findViewById(R.id.vp_guide);
        // 初始化adapter
        adapter = new GuideViewPagerAdapter(views);
        vp.setAdapter(adapter);
        vp.setOnPageChangeListener(new PageChangeListener());
    }

    private void initPermission()
    {
        if (Build.VERSION.SDK_INT >= 23) {
            try {

                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_DENIED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_DENIED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_PHONE_STATE,
                                    Manifest.permission.RECORD_AUDIO}, 1);
                    return;
                } else {
                    Intent intent = new Intent(GuidActivity.this,
                            MainActivity.class);

                    startActivity(intent);
                    PreferenceUtils.setPrefBoolean(GuidActivity.this, SPKey.FIRST_OPEN, true);
                    // SPUtils.saveBoolean(GuidActivity.this, SPKey.FIRST_OPEN, true);
                    finish();
                }
            } catch (Exception e) {
                // Dialog.toast("已禁止定位权限，请在应用管理中打开权限", getActivity());
            }
        }else {
            Intent intent = new Intent(GuidActivity.this,
                    MainActivity.class);

            startActivity(intent);
            PreferenceUtils.setPrefBoolean(GuidActivity.this, SPKey.FIRST_OPEN, true);
            // SPUtils.saveBoolean(GuidActivity.this, SPKey.FIRST_OPEN, true);
            finish();
        }
    }
//    private void initPermission() {
//        List<PermissionItem> permissionItems = new ArrayList<>();
//        permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "位置", R.drawable.logo1));
//        permissionItems.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取内存卡", R.drawable.logo1));
//        permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写入内存卡", R.drawable.logo1));
//        HiPermission.create(this).permissions(permissionItems).checkMutiPermission(new PermissionCallback() {
//            @Override
//            public void onClose() {
//              //  showShortToast("取消了权限授权请求");
//            }
//
//            @Override
//            public void onFinish() {
//               // /owShortToast("授权完成的所有权限");
//
//            }
//
//            @Override
//            public void onDeny(String permission, int position) {
//
//            }
//
//            @Override
//            public void onGuarantee(String permission, int position) {
//
//            }
//        });
//    }
//Android6.0申请权限的回调方法
@Override
public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode) {
        // requestCode即所声明的权限获取码，在checkSelfPermission时传入
        case 1:
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(GuidActivity.this,
                        MainActivity.class);

                startActivity(intent);
                PreferenceUtils.setPrefBoolean(GuidActivity.this, SPKey.FIRST_OPEN, true);
                // SPUtils.saveBoolean(GuidActivity.this, SPKey.FIRST_OPEN, true);
                finish();
                //Toast.makeText(this, "获权限成功", Toast.LENGTH_SHORT).show();
            } else {
                // 没有获取到权限，做特殊处理
                Toast.makeText(this, "获权限失败，请手动开启", Toast.LENGTH_SHORT).show();
            }
            break;

        default:
            break;
    }
}
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 如果切换到后台，就设置下次不进入功能引导页
//        SPUtils.saveBoolean(GuidActivity.this, SPKey.FIRST_OPEN, true);
//        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initDots() {
        mLl = (LinearLayout) findViewById(R.id.ll);
        dots = new ImageView[pics.length];
        // 循环取得小点图片
        for (int i = 0; i < pics.length; i++) {
            // 得到一个LinearLayout下面的每一个子元素
            dots[i] = (ImageView) mLl.getChildAt(i);
            dots[i].setEnabled(false);// 都设为灰色
            dots[i].setOnClickListener(this);
            dots[i].setTag(i);// 设置位置tag，方便取出与当前位置对应
        }
        currentIndex = 0;
        dots[currentIndex].setEnabled(true); // 设置为白色，即选中状态
    }

    /**
     * 设置当前view
     *
     * @param position
     */
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        vp.setCurrentItem(position);
    }

    /**
     * 设置当前指示点
     *
     * @param position
     */
    private void setCurDot(int position) {
        if (position < 0 || position > pics.length || currentIndex == position) {
            return;
        }
        dots[position].setEnabled(true);
        dots[currentIndex].setEnabled(false);
        currentIndex = position;
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            enterMainActivity();
            return;
        }
        int position = (Integer) v.getTag();
        setCurView(position);
        setCurDot(position);
    }


    private void enterMainActivity() {
        //initPermission();
        Intent intent = new Intent(GuidActivity.this,
                MainActivity.class);

        startActivity(intent);
        PreferenceUtils.setPrefBoolean(GuidActivity.this, SPKey.FIRST_OPEN, true);
        // SPUtils.saveBoolean(GuidActivity.this, SPKey.FIRST_OPEN, true);
        finish();
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        // 当滑动状态改变时调用
        @Override
        public void onPageScrollStateChanged(int position) {
            // arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。

        }

        // 当前页面被滑动时调用
        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {
            // arg0 :当前页面，及你点击滑动的页面
            // arg1:当前页面偏移的百分比
            // arg2:当前页面偏移的像素位置

        }

        // 当新的页面被选中时调用
        @Override
        public void onPageSelected(int position) {
            // 设置底部小点选中状态
//            setCurDot(position);
        }

    }
}
