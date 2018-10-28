package com.haiwai.housekeeper.activity.user;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenu;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.LoginActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.Parameter;
import com.haiwai.housekeeper.entity.RyTokenEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.fragment.user.ConversationListStaticFragment;
import com.haiwai.housekeeper.fragment.user.FindFragment;
import com.haiwai.housekeeper.fragment.user.HomeFragment;
import com.haiwai.housekeeper.fragment.user.MineFragment;
import com.haiwai.housekeeper.fragment.user.NeedNewFragment;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.Event;
import com.haiwai.housekeeper.utils.HttpManager;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPKey;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.HomePopView;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.umeng.analytics.MobclickAgent;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

/**
 * 个人端
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
    private FragmentManager fragmentManager;
    private Fragment currentFragment = new Fragment();
    private List<Fragment> fragments = new ArrayList<>();

    private int currentIndex = 0;
    //tab布局
    private LinearLayout firstTab;
    private LinearLayout secondTab;
    private LinearLayout thirdTab;
    private LinearLayout fourTab;
    private LinearLayout fiveTab;

    //tab的标题
    private ImageView firstImage;
    private ImageView secondImage;
    private ImageView thirdImage;
    private ImageView fourImage;
    private ImageView fiveImage;
    private ImageView iv_pot;
    User user;
    private MyApp mApp;
    private String id = "";
    private String name = "";
    private String avatarUri = "";
    HomePopView homePopView;
    boolean isTopH = false;
    boolean isBotH = false;
    private String isZhorEn = "";
    private FrameLayout fl;
    private HomeFragment homeFragment;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_first:
                //fl.setVisibility(View.VISIBLE);
                currentIndex = 0;
                break;
            case R.id.tab_second:
                //  fl.setVisibility(View.GONE);
                if (!mApp.isLogin()) {
                    ActivityTools.goNextActivity(MainActivity.this, LoginActivity.class);
                    overridePendingTransition(R.anim.push_up_in,
                            R.anim.push_up_out);
                    return;
                }
                currentIndex = 1;
                break;
            case R.id.tab_third:
                //  fl.setVisibility(View.GONE);
                currentIndex = 2;
                break;
            case R.id.tab_four:
                //   fl.setVisibility(View.GONE);
                if (!mApp.isLogin()) {
                    ActivityTools.goNextActivity(MainActivity.this, LoginActivity.class);
                    overridePendingTransition(R.anim.push_up_in,
                            R.anim.push_up_out);
                    return;
                }
                currentIndex = 3;
                break;
            case R.id.tab_five:
                //  fl.setVisibility(View.GONE);
                if (!mApp.isLogin()) {
                    ActivityTools.goNextActivity(MainActivity.this, LoginActivity.class);
                    overridePendingTransition(R.anim.push_up_in,
                            R.anim.push_up_out);
                    return;
                }
                currentIndex = 4;
                break;
        }
        showFragment();
    }

    private View image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_main);
        EventBus.getDefault().register(this);
        initData();
        fl = ((FrameLayout) findViewById(R.id.fl_menu_view));
        firstTab = (LinearLayout) findViewById(R.id.tab_first);
        secondTab = (LinearLayout) findViewById(R.id.tab_second);
        thirdTab = (LinearLayout) findViewById(R.id.tab_third);
        fourTab = (LinearLayout) findViewById(R.id.tab_four);
        fiveTab = (LinearLayout) findViewById(R.id.tab_five);
        //给tab设置点击事件
        firstTab.setOnClickListener(this);
        secondTab.setOnClickListener(this);
        thirdTab.setOnClickListener(this);
        fourTab.setOnClickListener(this);
        fiveTab.setOnClickListener(this);
        if ("en".equals(isZhorEn)) {
            findViewById(R.id.image_first).setVisibility(View.GONE);
            findViewById(R.id.image_second).setVisibility(View.GONE);
            findViewById(R.id.image_third).setVisibility(View.GONE);
            findViewById(R.id.image_four).setVisibility(View.GONE);
            findViewById(R.id.image_five).setVisibility(View.GONE);
            firstImage = (ImageView) findViewById(R.id.image_first_en);
            secondImage = (ImageView) findViewById(R.id.image_second_en);
            thirdImage = (ImageView) findViewById(R.id.image_third_en);
            fourImage = (ImageView) findViewById(R.id.image_four_en);
            fiveImage = (ImageView) findViewById(R.id.image_five_en);
            findViewById(R.id.image_first_en).setVisibility(View.VISIBLE);
            findViewById(R.id.image_second_en).setVisibility(View.VISIBLE);
            findViewById(R.id.image_third_en).setVisibility(View.VISIBLE);
            findViewById(R.id.image_four_en).setVisibility(View.GONE);
            findViewById(R.id.image_five_en).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.image_first_en).setVisibility(View.GONE);
            findViewById(R.id.image_second_en).setVisibility(View.GONE);
            findViewById(R.id.image_third_en).setVisibility(View.GONE);
            findViewById(R.id.image_four_en).setVisibility(View.GONE);
            findViewById(R.id.image_five_en).setVisibility(View.GONE);
            firstImage = (ImageView) findViewById(R.id.image_first);
            secondImage = (ImageView) findViewById(R.id.image_second);
            thirdImage = (ImageView) findViewById(R.id.image_third);
            fourImage = (ImageView) findViewById(R.id.image_four);
            fiveImage = (ImageView) findViewById(R.id.image_five);
            findViewById(R.id.image_first).setVisibility(View.VISIBLE);
            findViewById(R.id.image_second).setVisibility(View.VISIBLE);
            findViewById(R.id.image_third).setVisibility(View.VISIBLE);
            findViewById(R.id.image_four).setVisibility(View.GONE);
            findViewById(R.id.image_five).setVisibility(View.VISIBLE);


        }
        image = findViewById(R.id.iv_menu_background);
        image.getBackground().setAlpha(200);
        final FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.home_iv_menu);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
//                setBack(0.5f);
                firstTab.setClickable(false);
                secondTab.setClickable(false);
                thirdTab.setClickable(false);
                fourTab.setClickable(false);
                fiveTab.setClickable(false);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        image.setVisibility(View.VISIBLE);
                        image.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.main_in));
                    }
                });
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.inco_zqbg://管理报告
                        if (MyApp.getTingtingApp().isLogin()) {
                            SPUtils.saveBoolean(MainActivity.this, "is_h_id", false);
                            ActivityTools.goNextActivity(MainActivity.this, FwZdActivity.class);
                        } else {
                            ActivityTools.goNextActivity(MainActivity.this, LoginActivity.class);
                        }

                        break;
                    case R.id.icon_zqgl://周期服务
                        if (MyApp.getTingtingApp().isLogin()) {
                            ActivityTools.goNextActivity(MainActivity.this, OrderConfigActivity.class);
                        } else {
                            ActivityTools.goNextActivity(MainActivity.this, LoginActivity.class);
                        }
                        break;
                    case R.id.icon_dcfw://单次服务
                        if (MyApp.getTingtingApp().isLogin()) {
                            Intent intent = new Intent(MainActivity.this, AllBusinessActivity.class);
                            intent.putExtra("isAll", true);
                            startActivity(intent);
                        } else {
                            ActivityTools.goNextActivity(MainActivity.this, LoginActivity.class);
                        }
                        break;
                    case R.id.icon_fy://翻译
                        if (MyApp.getTingtingApp().isLogin()) {
                            ActivityTools.goNextActivity(MainActivity.this, TranslateActivity.class);
                        } else {
                            ActivityTools.goNextActivity(MainActivity.this, LoginActivity.class);
                        }

                        break;
                }
                image.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.main_out));
                image.setVisibility(View.GONE);
                return false;
            }

            @Override
            public void onMenuClosed() {
//                setBack(1.0f);
                image.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.main_out));
                image.setVisibility(View.GONE);
                firstTab.setClickable(true);
                secondTab.setClickable(true);
                thirdTab.setClickable(true);
                fourTab.setClickable(true);
                fiveTab.setClickable(true);
            }
        });

        iv_pot = (ImageView) findViewById(R.id.iv_pot);
        mApp = MyApp.getTingtingApp();
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState != null) { // “内存重启”时调用
            //获取“内存重启”时保存的索引下标
            currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT, 0);
            //注意，添加顺序要跟下面添加的顺序一样！！！！
            fragments.removeAll(fragments);
            fragments.add(fragmentManager.findFragmentByTag(0 + ""));
            fragments.add(fragmentManager.findFragmentByTag(1 + ""));
            fragments.add(fragmentManager.findFragmentByTag(2 + ""));
            fragments.add(fragmentManager.findFragmentByTag(3 + ""));
            fragments.add(fragmentManager.findFragmentByTag(4 + ""));
            //恢复fragment页面
            restoreFragment();
        } else {      //正常启动时调用
            homeFragment = new HomeFragment();
            fragments.add(homeFragment);
            fragments.add(new NeedNewFragment());
            fragments.add(new FindFragment());
            fragments.add(new ConversationListStaticFragment());
            fragments.add(new MineFragment());
            showFragment();
        }

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (!TextUtils.isEmpty(IMKitService.IM_Token)) {
//                    connect(IMKitService.IM_Token);
//                } else {
//                    IMKitService.IM_Token = SPUtils.getString(MainActivity.this, "IM_Token", "");
//                    if (!TextUtils.isEmpty(IMKitService.IM_Token)) {
//                        connect(IMKitService.IM_Token);
//                    } else {
////                        ToastUtil.longToast(MainActivity.this, "尚未连接到融云服务，咱不能沟通，检查网络！");
//                    }
//                }
//            }
//        }).start();
    }

    private void initData() {
        if (SPUtils.getBoolean(MainActivity.this, "isFirstPer", true)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_DENIED
                        || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_PHONE_STATE,
                                    Manifest.permission.RECORD_AUDIO}, 1);
                }
            }
            SPUtils.saveBoolean(MainActivity.this, "isFirstPer", false);
        }

        isZhorEn = AppGlobal.getInstance().getLagStr();
        user = AppGlobal.getInstance().getUser();
        if (user != null) {
            id = user.getUid();
            name = user.getNickname();
            avatarUri = user.getAvatar();
            initUnreadCountListener();
        }
        // initUnreadCountListener();

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //“内存重启”时保存当前的fragment名字
        outState.putInt(CURRENT_FRAGMENT, currentIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String flag = "";
        if (intent.getExtras() != null) {
            flag = intent.getExtras().get("flag").toString();
        }
        if ("vip_page".equals(flag)) {
            if (mApp.isLogin()) {
                if (currentIndex != 3) {
                    currentIndex = 3;
                    showFragment();
                }
            } else {
                ActivityTools.goNextActivity(MainActivity.this, LoginActivity.class);
            }
        } else if ("success".equals(flag)) {
            if (currentIndex != 1) {
                currentIndex = 1;
                showFragment();
            }
            if (currentFragment instanceof NeedNewFragment) {
//                Log.i("infsdnf","we are one you know my lovely gril");
                ((NeedNewFragment) currentFragment).update();
            }


        } else if ("order_success".equals(flag)) {
            if (currentIndex != 1) {
                currentIndex = 1;
                showFragment();
            }
            if (currentFragment instanceof NeedNewFragment) {
//                Log.i("infsdnf","we are one you know my lovely gril");
            }
            Intent refresh_intent = new Intent();
            refresh_intent.setAction("jpush");
            sendBroadcast(refresh_intent);
        } else if ("wn".equals(flag)) {
            if (currentIndex != 1) {
                currentIndex = 1;
                showFragment();
            }
            Intent refresh_intent = new Intent();
            refresh_intent.setAction("wn");
            sendBroadcast(refresh_intent);
        } else if ("service".equals(flag)) {
            if (currentIndex != 1) {
                currentIndex = 1;
                showFragment();
            }
            Intent refresh_intent = new Intent();
            refresh_intent.setAction("service");
            sendBroadcast(refresh_intent);
        } else if ("set".equals(flag)) {
            if (currentIndex != 0) {
                currentIndex = 0;
                showFragment();
            }
        } else if ("home".equals(flag)) {
            if (mApp.isLogin()) {
                if (currentIndex != 4) {
                    currentIndex = 4;
                    showFragment();
                }
            } else {
                ActivityTools.goNextActivity(MainActivity.this, LoginActivity.class);
            }
        } else {
            if (currentIndex != 0) {
                currentIndex = 0;
                showFragment();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        firstTab.setClickable(true);
        secondTab.setClickable(true);
        thirdTab.setClickable(true);
        fourTab.setClickable(true);
        fiveTab.setClickable(true);
//        if(currentIndex!=0){
//            fl.setVisibility(View.GONE);
//        }else{
//            fl.setVisibility(View.VISIBLE);
//        }
        MobclickAgent.onResume(this);
        if (user != null) {
            getTokenData();
        }
    }

    private String mUserId;

    private void getTokenData() {
        Map<String, String> map = new HashMap<>();
        user = AppGlobal.getInstance().getUser();
        if (user != null) {
            mUserId = user.getUid();
        }
        if (mUserId != null) {
            List<Parameter> list = new ArrayList<>();
            list.add(new Parameter("uid", mUserId));
            list.add(new Parameter("secret_key", SPUtils.getString(MainActivity.this, "secret", "")));
            list.add(new Parameter("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey()));
            HttpManager.getInstance().post(list, Contants.getToken, 100, new HttpManager.OnHttpResponseListener() {
                @Override
                public void onHttpRequestSuccess(int requestCode, int resultCode, String resultData, String resultJson) {
                    int code = JsonUtils.getJsonInt(resultJson, "status");
                    LoadDialog.closeProgressDialog();
                    if (code == 200) {
                        Log.e("infomation", resultJson);
                        if (JsonUtils.getJsonStr(resultJson, "data").contains("{")) {
                            RyTokenEntity ryTokenEntity = new Gson().fromJson(resultJson, RyTokenEntity.class);
                            IMKitService.IM_Token = ryTokenEntity.getData().getToken();
                            SPUtils.saveString(MainActivity.this, "IM_Token", IMKitService.IM_Token);
                            user = AppGlobal.getInstance().getUser();
                            connect(SPUtils.getString(MainActivity.this, "IM_Token", ""));
                        }
                    } else {
                        ToastUtil.shortToast(MainActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }

                @Override
                public void onHttpRequestError(int requestCode, Exception exception) {

                }
            });
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    /**
     * 使用show() hide()切换页面
     * 显示fragment
     */
    private void showFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //如果之前没有添加过
        if (!fragments.get(currentIndex).isAdded()) {
            transaction
                    .hide(currentFragment)
                    .add(R.id.main_body, fragments.get(currentIndex), "" + currentIndex);  //第三个参数为添加当前的fragment时绑定一个tag

        } else {
            transaction
                    .hide(currentFragment)
                    .show(fragments.get(currentIndex));
        }

        currentFragment = fragments.get(currentIndex);

        transaction.commit();
        changeTabsState(currentIndex);
    }

    /**
     * 恢复fragment
     */
    private void restoreFragment() {
        FragmentTransaction mBeginTreansaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if (i == currentIndex) {
                mBeginTreansaction.show(fragments.get(i));
            } else {
                mBeginTreansaction.hide(fragments.get(i));
            }
        }

        mBeginTreansaction.commit();
        //把当前显示的fragment记录下来
        currentFragment = fragments.get(currentIndex);

    }

    private void changeTabsState(int index) {
        firstImage.setSelected(index == 0 ? true : false);
        secondImage.setSelected(index == 1 ? true : false);
        thirdImage.setSelected(index == 2 ? true : false);
        // fourImage.setSelected(index == 3 ? true : false);
        fiveImage.setSelected(index == 4 ? true : false);
    }

    private long exitTime = 0;

    private void ExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            if (AppGlobal.getInstance().getLagStr().equals("en")) {
                Toast.makeText(this, "Tap again to exit", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            }
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            ExitApp();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStart() {
        int system = SPUtils.getInt(MainActivity.this, "system", 0);
        int follow = SPUtils.getInt(MainActivity.this, "follow", 0);
        if (system == 0 && follow == 0) {
            iv_pot.setVisibility(View.GONE);
        } else if (system > 0 || follow > 0) {
            iv_pot.setVisibility(View.VISIBLE);
        }
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(Event event) {
        String msg = "onEventMainThread收到了消息：" + event.getMsg();
        Log.d("harvic", msg);

        if (event.getMsg().equals("im_token")) {
            //   Dialog.toast("已下单成功",this);
            user = AppGlobal.getInstance().getUser();
            connect(event.getmOrder());

        }
    }

    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    private void connect(String token) {
        Log.e("sssss--->", "1111");
        if (getApplicationInfo().packageName.equals(MyApp.getCurProcessName(getApplicationContext()))) {
            Log.e("sssss--->", "222");
            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {
                    Log.d("LoginActivity", "--onTokenIncorrect");
//                    LoadDialog.closeProgressDialog();
//                   setResult(RESULT_OK);
//                    finish();
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {
                    LoadDialog.closeProgressDialog();
                    Log.d("LoginActivity", "--onSuccess" + userid);
                    if (RongIM.getInstance() != null) {//显示用户和头像
                        RongIM.getInstance().setCurrentUserInfo(new UserInfo(user.getUid(), user.getNickname(), Uri.parse(user.getAvatar())));
                    }
                    RongIM.getInstance().setMessageAttachedUserInfo(true);


                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    LoadDialog.closeProgressDialog();
                    Log.d("LoginActivity", "--onError" + errorCode);

                }
            });
        }
    }

    private void initUnreadCountListener() {
        final Conversation.ConversationType[] conversationTypes = {Conversation.ConversationType.PRIVATE, Conversation.ConversationType.DISCUSSION,
                Conversation.ConversationType.GROUP, Conversation.ConversationType.SYSTEM,
                Conversation.ConversationType.PUBLIC_SERVICE};

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                RongIM.getInstance().setOnReceiveUnreadCountChangedListener(mCountListener, conversationTypes);
            }
        }, 500);
    }


    public RongIM.OnReceiveUnreadCountChangedListener mCountListener = new RongIM.OnReceiveUnreadCountChangedListener() {
        @Override
        public void onMessageIncreased(int count) {
            Log.e("MainActivity", "count:" + count);
            if (count == 0) {
                homeFragment.RedGone();
            } else if (count > 0) {
                homeFragment.RedVisibility();
            }
        }
    };


//    public void onDragOut() {
//        if (RongIM.getInstance() != null) {
//            List<Conversation> conversationList = RongIM.getInstance().getRongIMClient().getConversationList();
//            if (conversationList != null && conversationList.size() > 0) {
//                for (Conversation c : conversationList) {
//                    RongIM.getInstance().getRongIMClient().clearMessagesUnreadStatus(c.getConversationType(), c.getTargetId());
//                }
//                mUnreadCount.setVisibility(View.GONE);
//            }
//        }
//    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!SPUtils.getBoolean(MainActivity.this, SPKey.FISRT_GUID, false)) {
            homePopView = new HomePopView(getApplicationContext());
            homePopView.showPopUpWindow(firstImage);
            homePopView.getTopBtn().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isTopH = true;
                    homePopView.getLlTopLayout().setVisibility(View.GONE);
                    if (isBotH) {
                        homePopView.dismiss();
                    }
                }
            });
            homePopView.getBottomBtn().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isBotH = true;
                    homePopView.getRlBottomLayout().setVisibility(View.GONE);
                    if (isTopH) {
                        homePopView.dismiss();
                    }
                }
            });
            SPUtils.saveBoolean(MainActivity.this, SPKey.FISRT_GUID, true);
        }
    }

}
