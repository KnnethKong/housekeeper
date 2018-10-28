package com.haiwai.housekeeper.activity.base;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;

import com.baidu.translate.SyncRequestUtils;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.ProDetailActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.utils.EnOrCnUtils;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.plugin.location.AMapLocationActivity;
import io.rong.imkit.plugin.location.LocationManager;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.LocationMessage;
import io.rong.message.TextMessage;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by ihope10 on 2016/11/15.
 */

public class ConversationActivity extends FragmentActivity implements RongIM.LocationProvider {
    /**
     * 目标 Id
     */
    public static String targetId;
    /**
     * 刚刚创建完讨论组后获得讨论组的id 为targetIds，需要根据 为targetIds 获取 targetId
     */
    private String mTargetIds;
    /**
     * 会话类型
     */
    private Conversation.ConversationType mConversationType;
    private TopViewNormalBar top_conversation_bar;
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);
        top_conversation_bar = (TopViewNormalBar) findViewById(R.id.top_conversation_bar);
        top_conversation_bar.setOnBackListener(mOnClickListener);
//        StrictMode.setThreadPolicy(new
//                StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
//        StrictMode.setVmPolicy(
//                new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        initData();
    }

    private void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        RongIM.setLocationProvider(this);
        RongIM.setConversationBehaviorListener(new MyConversationBehaviorListener());
        RongIM.getInstance().setSendMessageListener(new MySendMessageListener());
        targetId = getIntent().getData().getQueryParameter("targetId");
        String title = getIntent().getData().getQueryParameter("title");
        if (!TextUtils.isEmpty(title)) {
            top_conversation_bar.setTitle(title);
        } else {
//            top_conversation_bar.setTitle("正在回话");
        }

    }




    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_conversation_bar.getBackView()) {
                finish();
            }
        }
    };

    private LocationCallback mCallback;
    @Override
    public void onStartLocation(final Context context, final LocationCallback locationCallback) {
        mCallback = locationCallback;
//        Intent intent = new Intent(context, AMapLocationActivity.class);
//        intent.putExtra("fromchart",true);
//        startActivityForResult(intent,900);
        mCallback = locationCallback;
        Intent intent = new Intent(ConversationActivity.this, AMapLocationActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(intent,100);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(data!=null){
                if(requestCode==100){
                    double lat = data.getDoubleExtra("lat",0);
                    double lng = data.getDoubleExtra("lng",0);
                    String poi = data.getStringExtra("poi");
                    Uri uri = Uri.parse(data.getStringExtra("thumb"));
                    LocationMessage msg = LocationMessage.obtain(lat,lng,poi,uri);
                    mCallback.onSuccess(msg);
                    mCallback.onFailure("Fail");
                }
            }
    }

    private class MyConversationBehaviorListener implements RongIM.ConversationBehaviorListener {

        /**
         * 当点击用户头像后执行。
         *
         * @param context          上下文。
         * @param conversationType 会话类型。
         * @param userInfo         被点击的用户的信息。
         * @return 如果用户自己处理了点击后的逻辑，则返回 true，否则返回 false，false 走融云默认处理方式。
         */
        @Override
        public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
//            ToastUtil.longToast(context, "头像被点击");
            System.out.println(conversationType + ">>>>>>>>>>>>>>>>>>>>>>>>" + userInfo.getUserId() + userInfo.getName());
            Intent intent = new Intent(getApplicationContext(), ProDetailActivity.class);
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
            Bundle bundle = new Bundle();
            bundle.putString("nickname", userInfo.getName());
            bundle.putString("uid", userInfo.getUserId());
            intent.putExtra("isServer",true);
//            if (AppGlobal.getInstance().getUser().getUid().equals(userInfo.getUserId())) {
////                bundle.putString("oid", TextUtils.isEmpty(IMKitService.proDetailMap.get("oid")) ? "" : IMKitService.proDetailMap.get("oid"));
////                bundle.putString("type", TextUtils.isEmpty(IMKitService.proDetailMap.get("type")) ? "" : IMKitService.proDetailMap.get("type"));
//                bundle.putString("choose", "1");
//            } else {
//                bundle.putString("choose", "0");
//            }
            bundle.putString("choose", "0");
            bundle.putString("oid", "");
            bundle.putString("type", "");
            intent.putExtras(bundle);
            startActivity(intent);
            return true;
        }

        /**
         * 当长按用户头像后执行。
         *
         * @param context          上下文。
         * @param conversationType 会话类型。
         * @param userInfo         被点击的用户的信息。
         * @return 如果用户自己处理了点击后的逻辑，则返回 true，否则返回 false，false 走融云默认处理方式。
         */
        @Override
        public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
            return false;
        }

        @Override
        public boolean onMessageClick(Context context, View view, Message message) {
            return false;
        }

        /**
         * 当长按消息时执行。
         *
         * @param context 上下文。
         * @param view    触发点击的 View。
         * @param message 被长按的消息的实体信息。
         * @return 如果用户自己处理了长按后的逻辑，则返回 true，否则返回 false，false 走融云默认处理方式。
         */
        @Override
        public boolean onMessageLongClick(final Context context, final View view, Message message) {
//            if (message.getContent() instanceof TextMessage) {
//                String str = ((TextMessage) message.getContent()).getContent();
//                String isEnOrZh = EnOrCnUtils.checkString(str);
//                if ("en".equals(isEnOrZh)) {
//                    RequestUtils requestUtils = new RequestUtils();
//                    try {
//                        requestUtils.translate(str, "en", "zh", new HttpCallBack() {
//                            @Override
//                            public void onSuccess(String result) {
//                                Con2PopView con2PopView = new Con2PopView(context, result);
//                                con2PopView.showPopUpWindow(view);
//                            }
//
//                            @Override
//                            public void onFailure(String exception) {
//                                ToastUtil.shortToast(context, getString(R.string.translate_fail));
//                            }
//                        });
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                } else if ("zh".equals(isEnOrZh)) {
//                    RequestUtils requestUtils = new RequestUtils();
//                    try {
//                        requestUtils.translate(str, "zh", "en", new HttpCallBack() {
//                            @Override
//                            public void onSuccess(String result) {
////                                ToastUtil.longToast(context, result);
//                                Con2PopView con2PopView = new Con2PopView(context, result);
//                                con2PopView.showPopUpWindow(view);
//                            }
//
//                            @Override
//                            public void onFailure(String exception) {
//                                ToastUtil.shortToast(context, getString(R.string.translate_fail));
//                            }
//                        });
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//            return true;
            return false;
        }

        /**
         * 当点击链接消息时执行。
         *
         * @param context 上下文。
         * @param link    被点击的链接。
         * @return 如果用户自己处理了点击后的逻辑处理，则返回 true， 否则返回 false, false 走融云默认处理方式。
         */
        @Override
        public boolean onMessageLinkClick(Context context, String link) {
            return false;
        }
    }


    class MySendMessageListener implements RongIM.OnSendMessageListener {
//        String line = "\n--------\n";
//        String msg = "";

        @Override
        public Message onSend(final Message message) {//判断是否是文本消息后进行处理后在发送
//            if (message.getContent() instanceof TextMessage) {//文本信息
//                final String content = ((TextMessage) message.getContent()).getContent();
//                String isEnOrZh = EnOrCnUtils.checkString(content);
//                if ("en".equals(isEnOrZh)) {
//                    SyncRequestUtils requestUtils = new SyncRequestUtils();
//                    try {
//                        msg = content + line + requestUtils.translate(content, "en", "zh");
//                        ((TextMessage) message.getContent()).setContent(msg);
//                        return message;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                } else if ("zh".equals(isEnOrZh)) {
//                    SyncRequestUtils requestUtils = new SyncRequestUtils();
//                    try {
//                        msg = content + line + requestUtils.translate(content, "zh", "en");
//                        ((TextMessage) message.getContent()).setContent(msg);
//                        return message;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
            return message;
        }

        @Override
        public boolean onSent(Message message, RongIM.SentMessageErrorCode sentMessageErrorCode) {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        RongIM.setConversationBehaviorListener(null);
        RongIM.getInstance().setSendMessageListener(null);
        super.onDestroy();
    }
}
