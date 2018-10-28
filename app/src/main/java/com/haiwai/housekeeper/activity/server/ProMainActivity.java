package com.haiwai.housekeeper.activity.server;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.LoginActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.Tab;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.fragment.server.OrderFragment;
import com.haiwai.housekeeper.fragment.server.SkillFragment;
import com.haiwai.housekeeper.fragment.user.ConversationListStaticFragment;
import com.haiwai.housekeeper.fragment.user.MineFragment;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.service.polling.PollingService;
import com.haiwai.housekeeper.service.polling.PollingUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.FragmentTabHost;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;


public class ProMainActivity extends BaseProActivity {
    private LayoutInflater mInflater;
    private FragmentTabHost mTabhost;
    private OrderFragment orderFragment;
    private SkillFragment skillFragment;
    private ConversationListStaticFragment messageFragment;
    private MineFragment mineFragment;
    private String tabTag;
    private String preTabTag;
    private boolean isLat = false;
    User user;
    private List<Tab> mTabs = new ArrayList<>(4);
    private List<ImageView> tobs = new LinkedList<>();
    private ImageView iv_pot;
    private String isZhorEn = "";

    public static ProMainActivity activity;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    tobs.get(msg.arg1).setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    //    OrderFragment.ProOderReceiver mProOderReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_activity_main);
        activity = this;
        isZhorEn = AppGlobal.getInstance().getLagStr();
        if (isServiceRunning(this, PollingService.ACTION) == false) {
            PollingUtils.startPollingService(ProMainActivity.this, 5, PollingService.class, PollingService.ACTION, AppGlobal.getInstance().getUser().getUid());
        }
        initTab();
        initData();
    }

    public void setFragment() {
        mTabhost.setCurrentTab(1);
    }


    private void initData() {
        user = AppGlobal.getInstance().getUser();
        String str = getIntent().getStringExtra("skill");

        if ("123".equals(str)) {
            mTabhost.setCurrentTab(1);
            refData(R.string.skill, 0);
        } else if ("cycle".equals(str)) {
            mTabhost.setCurrentTab(0);
            refData(R.string.order, 1);
        } else if ("orderyes".equals(str)) {
            mTabhost.setCurrentTab(0);
            refData(R.string.order, 0);
        } else if ("proJpush".equals(str)) {
            mTabhost.setCurrentTab(0);
            Intent refresh_intent = new Intent();
            refresh_intent.setAction("proJpush");
            sendBroadcast(refresh_intent);
        } else if ("order_msg".equals(str)) {
            mTabhost.setCurrentTab(2);
            refData(R.string.skill, 0);
        } else if ("History".equals(str)) {
            mTabhost.setCurrentTab(0);
            refData(R.string.order, 2);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(IMKitService.IM_Token)) {
                    connect(IMKitService.IM_Token);
                } else {
                    IMKitService.IM_Token = SPUtils.getString(ProMainActivity.this, "IM_Token", "");
                    if (!TextUtils.isEmpty(IMKitService.IM_Token)) {
                        connect(IMKitService.IM_Token);
                    } else {
                        ToastUtil.longToast(ProMainActivity.this, getString(R.string.login_ry_warn));
                    }
                }
            }
        }).start();
        initUnreadCountListener();
    }


    private Tab tab_order, tab_skill, tab_message, tab_mine;

    private void initTab() {
        tab_order = null;
        tab_skill = null;
        tab_message = null;
        tab_mine = null;
        if ("en".equals(isZhorEn)) {
            tab_order = new Tab(OrderFragment.class, R.string.order, R.drawable.pro_selector_icon_en_order, 0, "order");
            tab_skill = new Tab(SkillFragment.class, R.string.skill, R.drawable.pro_selector_icon_en_skill, 1, "skill");
            tab_message = new Tab(ConversationListStaticFragment.class, R.string.message, R.drawable.pro_selector_icon_en_message, 2, "message");
            tab_mine = new Tab(MineFragment.class, R.string.mine, R.drawable.pro_selector_icon_en_mine, 3, "mine");
        } else {
            tab_order = new Tab(OrderFragment.class, R.string.order, R.drawable.pro_selector_icon_order, 0, "order");
            tab_skill = new Tab(SkillFragment.class, R.string.skill, R.drawable.pro_selector_icon_skill, 1, "skill");
            tab_message = new Tab(ConversationListStaticFragment.class, R.string.message, R.drawable.pro_selector_icon_message, 2, "message");
            tab_mine = new Tab(MineFragment.class, R.string.mine, R.drawable.pro_selector_icon_mine, 3, "mine");
        }

        mTabs.add(tab_order);
        mTabs.add(tab_skill);
        mTabs.add(tab_message);
        mTabs.add(tab_mine);

        mInflater = LayoutInflater.from(this);
        mTabhost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (Tab tab : mTabs) {
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(tab.getTag());
            tabSpec.setIndicator(buildIndicator(tab));
            mTabhost.addTab(tabSpec, tab.getFragment(), null);
        }
//        mTabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
//            @Override
//            public void onTabChanged(String tabId) {
//                tabTag = mTabhost.getCurrentTabTag();
//                if (tabId == getString(R.string.order)) {
//                    refData(R.string.order, 0);
//                } else if (tabId == getString(R.string.skill)) {
//                    refData(R.string.skill, 0);
//                } else if (tabId == getString(R.string.message)) {
//                    refData(R.string.message, 0);
//                } else if (tabId == getString(R.string.mine)) {
//                    refData(R.string.mine, 0);
//                }
//                } else {//判断跳转前的上一个现实内容
//                    startActivity(new Intent(ProMainActivity.this, LoginActivity.class));
//                    isLogined = true;
//                    if (preTabTag == getString(R.string.order)) {
//                        mTabhost.setCurrentTabByTag(preTabTag);
//                    } else if (preTabTag == getString(R.string.skill)) {
//                        mTabhost.setCurrentTabByTag(preTabTag);
//                    } else if (preTabTag == getString(R.string.message)) {
//                        mTabhost.setCurrentTabByTag(preTabTag);
//                    } else if (preTabTag == getString(R.string.mine)) {
//                        mTabhost.setCurrentTabByTag(preTabTag);
//                    }
//
//                }
//
//            }
//        });
//        tabTag = mTabhost.getCurrentTabTag();
//        preTabTag = tabTag;
        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
//        mTabhost.setCurrentTabByTag(tabTag);
        mTabhost.setCurrentTab(0);
    }

    private static String TAG = ProMainActivity.class.getName();

    private void refData(int tabId, int tab) {

        if (R.string.order == tabId) {
            if (tab == 0) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tab_order.getTag());
                        if (fragment != null) {
                            orderFragment = (OrderFragment) fragment;
                            orderFragment.selectYes();
                        }
                    }
                }, 200);
            } else if (tab == 2) {//////
                Log.e(TAG, "refData: 2222222");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tab_order.getTag());
                        if (fragment != null) {
                            orderFragment = (OrderFragment) fragment;
                            orderFragment.selectYes();
                            orderFragment.setCurrent();
                        }
                    }
                }, 200);
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tab_order.getTag());
                        if (fragment != null) {
                            orderFragment = (OrderFragment) fragment;
                            orderFragment.selectYes1();
                        }
                    }
                }, 500);

            }

//            preTabTag = getString(R.string.order);
        } else if (R.string.skill == tabId) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag(tab_skill.getTag());
                    if (fragment != null) {
                        skillFragment = (SkillFragment) fragment;
                        skillFragment.refData();
                    }
                }
            }, 200);
//            if (skillFragment == null) {
//                Fragment fragment = getSupportFragmentManager().findFragmentByTag(tab_skill.getTag());
//                if (fragment != null) {
//                    skillFragment = (SkillFragment) fragment;
//                    skillFragment.refData();
//                }
//            } else {
//                skillFragment.refData();
//            }
//            preTabTag = getString(R.string.skill);
        } else if (R.string.message == tabId) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag(tab_message.getTag());
                    if (messageFragment != null) {
                        messageFragment = (ConversationListStaticFragment) fragment;
//                    messageFragment.refData();
                    }
                }
            }, 200);

//            if (messageFragment == null) {
//                Fragment fragment = getSupportFragmentManager().findFragmentByTag(tab_message.getTag());
//                if (messageFragment != null) {
//                    messageFragment = (ConversationListStaticFragment) fragment;
////                    messageFragment.refData();
//                }
//            } else {
////                messageFragment.refData();
//            }
//            preTabTag = getString(R.string.message);
        } else if (R.string.mine == tabId) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag(tab_mine.getTag());
                    if (fragment != null) {
                        mineFragment = (MineFragment) fragment;
                    }
                }
            }, 200);
//            if (mineFragment == null) {
//
//            } else {
////            mineFragment.refData();
//            }
//            preTabTag = getString(R.string.mine);
        }

    }


    private View buildIndicator(Tab tab) {
        View view = mInflater.inflate(R.layout.pro_tabbar_view, null);
        ImageView img = (ImageView) view.findViewById(R.id.iv_tab);
        iv_pot = (ImageView) view.findViewById(R.id.iv_pot);
        tobs.add(iv_pot);
        img.setBackgroundResource(tab.getIcon());
        return view;
    }


    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    private void connect(String token) {

        if (getApplicationInfo().packageName.equals(MyApp.getCurProcessName(getApplicationContext()))) {
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
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {
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
                    Log.d("LoginActivity", "--onError" + errorCode);
                }
            });
        }
    }

    private void initUnreadCountListener() {
        final Conversation.ConversationType[] conversationTypes = {Conversation.ConversationType.PRIVATE, Conversation.ConversationType.DISCUSSION,
                Conversation.ConversationType.GROUP, Conversation.ConversationType.SYSTEM,
                Conversation.ConversationType.PUBLIC_SERVICE};

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                RongIM.getInstance().setOnReceiveUnreadCountChangedListener(mCountListener, conversationTypes);
            }
        }, 500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int system = SPUtils.getInt(ProMainActivity.this, "system", 0);
                int pro_system = SPUtils.getInt(ProMainActivity.this, "pro_system", 0);
                if (system > 0 || pro_system > 0) {
                    handler.sendEmptyMessage(0);
                }
            }
        }, 500);
    }


    public RongIM.OnReceiveUnreadCountChangedListener mCountListener = new RongIM.OnReceiveUnreadCountChangedListener() {
        @Override
        public void onMessageIncreased(int count) {
            Log.e("MainActivity", "count:" + count);
            if (count == 0) {
                for (int i = 0; i < mTabs.size(); i++) {
                    tobs.get(i).setVisibility(View.GONE);
                }
            } else if (count > 0) {
                for (int i = 0; i < mTabs.size(); i++) {
                    if (mTabs.get(i).getPosition() == 2) {
                        Message message = new Message();
                        message.arg1 = i;
                        message.what = 0;
                        handler.sendMessage(message);
                    } else {
                        tobs.get(i).setVisibility(View.GONE);
                    }
                }
                ;
            }
        }
    };

    @Override
    protected void onResume() {

        int pro_system = SPUtils.getInt(ProMainActivity.this, "pro_system", 0);
        if (pro_system == 0) {
            for (int i = 0; i < mTabs.size(); i++) {
                tobs.get(i).setVisibility(View.GONE);
            }
        } else if (pro_system > 0) {
            for (int i = 0; i < mTabs.size(); i++) {
                if (mTabs.get(i).getPosition() == 2) {
                    tobs.get(i).setVisibility(View.VISIBLE);
                } else {
                    tobs.get(i).setVisibility(View.GONE);
                }
            }
        }
        super.onResume();
    }
//    @Override
//    public void onDestroy() {
//        unregisterReceiver(mProOderReceiver);
//        super.onDestroy();
//    }

    public static boolean isServiceRunning(Context mContext, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager)
                mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList
                = activityManager.getRunningServices(30);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }
}
