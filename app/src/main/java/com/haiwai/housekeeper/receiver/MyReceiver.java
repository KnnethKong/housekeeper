package com.haiwai.housekeeper.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.haiwai.housekeeper.activity.server.OrderDetailActivity;
import com.haiwai.housekeeper.activity.server.OrderHisDetailActivity;
import com.haiwai.housekeeper.activity.server.OrderYesDetailActivity;
import com.haiwai.housekeeper.activity.server.ProMainActivity;
import com.haiwai.housekeeper.activity.user.ContactActivity;
import com.haiwai.housekeeper.activity.user.EnvelopeActivity;
import com.haiwai.housekeeper.activity.user.EvaluateActivity;
import com.haiwai.housekeeper.activity.user.FwZdActivity;
import com.haiwai.housekeeper.activity.user.MainActivity;
import com.haiwai.housekeeper.activity.user.NeedOrderDetailActivity3;
import com.haiwai.housekeeper.activity.user.OrderConfigActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String data = bundle.getString(JPushInterface.EXTRA_EXTRA);
            System.out.println(">>>>>>>>>>>>>>>>>>jus0>>>" + data);
            String JpushId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            AppGlobal.getInstance().setRegId(JpushId);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            String data = bundle.getString(JPushInterface.EXTRA_EXTRA);
            System.out.println(">>>>>>>>>>>>>>>>>>jus1>>>" + data);
            Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            processCustomMessage(context, bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            String data = bundle.getString(JPushInterface.EXTRA_EXTRA);
            System.out.println(">>>>>>>>>>>>>>>>>>jus2>>>" + data);
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
//            {"n_builder_id":0,"ad_id":"7758549525","n_only":1,"m_content":{"n_extras":{"oid":"361","type":1},"n_content":"您的房屋保洁订单收到专家报价,点击查看详情。","n_flag":1,"ad_t":0}
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            String data = bundle.getString(JPushInterface.EXTRA_EXTRA);
            System.out.println(">>>>>>>>>>>>>>>>>>jus3>>>" + data);//{"type":1,"oid":"366"}
            //            type   用户端(1接单列表 2订单详情 3消息列表 4关注我的人列表)
//            服务端(5订单详情 6订单列表 7技能列表 8消息列表)
//            oid    订单id
            try {
                JSONObject jsonObject = new JSONObject(data);
                String type = jsonObject.optString("type");
                String order_type=jsonObject.optString("order_type");
                String oid = jsonObject.optString("oid");
                String h_id = jsonObject.optString("h_id");
                if ("1".equals(type)) {//用户端 1接单列表
                    Intent i = new Intent(context, MainActivity.class);
                    i.putExtra("flag", "success");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                } else if ("2".equals(type) || "3".equals(type) || "4".equals(type)) {//用户端2订单详情
                    Intent i = new Intent(context, NeedOrderDetailActivity3.class);
                    i.putExtra("id", oid);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                } else if ("5".equals(type)) {//系统消息
                    Intent i = new Intent(context, MainActivity.class);
                    i.putExtra("flag", "order_msg");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                } else if ("6".equals(type)) {//用户端4 关注我的人列表
                    Intent i = new Intent(context, ContactActivity.class);
                    i.putExtra("flag", true);
                    i.putExtra("type", type);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                } else if ("7".equals(type)) {//服务端5订单详情
                    Intent i = new Intent(context, OrderDetailActivity.class);
                    i.putExtra("id", oid);
                    i.putExtra("type",order_type);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                }else if("12".equals(type)){
                    Intent i = new Intent(context, OrderYesDetailActivity.class);
                    i.putExtra("id", oid);
                    i.putExtra("type",type);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                }else if("10".equals(type)){
                    Intent i = new Intent(context, OrderYesDetailActivity.class);
                    i.putExtra("id", oid);
                    i.putExtra("type",type);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                }else if("29".equals(type)){//该订单已经选择其他专家

                    if(ProMainActivity.activity!=null){
                        ProMainActivity.activity.finish();
                    }
                    Intent i = new Intent(context,ProMainActivity.class);
                    i.putExtra("skill","History");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                }else if("11".equals(type)){

                    Intent i = new Intent(context, OrderYesDetailActivity.class);
                    i.putExtra("id", oid);
                    i.putExtra("type",type);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);



//                    Intent i = new Intent(context, NeedOrderDetailActivity3.class);
//                    i.putExtra("flag", true);
//                    i.putExtra("type", type);
//                    i.putExtra("id", oid);
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    context.startActivity(i);


//                    Intent i = new Intent(context, OrderYesDetailActivity.class);
//                    i.putExtra("id", oid);
//                    i.putExtra("type",type);
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    context.startActivity(i);
                }else if( "9".equals(type)){
                    Intent i = new Intent(context, OrderYesDetailActivity.class);
                    i.putExtra("id", oid);
                    i.putExtra("type",type);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);

                }else if("8".equals(type)){
                    Intent i = new Intent(context, OrderYesDetailActivity.class);
                    i.putExtra("id", oid);
                    i.putExtra("type",type);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                } else if ("13".equals(type)) {//被user评价
                    Intent i = new Intent(context, OrderHisDetailActivity.class);
                    i.putExtra("id", oid);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                } else if ("14".equals(type)) {//服务端 被@下单
                    Intent i = new Intent(context, ProMainActivity.class);
                    i.putExtra("skill", "proJpush");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                } else if ("15".equals(type) || "16".equals(type)) {//服务端15/16技能列表
                    Intent i = new Intent(context, ProMainActivity.class);
                    i.putExtra("skill", "123");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                } else if ("17".equals(type)) {//用户端4 关注我的人列表
                    Intent i = new Intent(context, ContactActivity.class);
                    i.putExtra("flag", true);
                    i.putExtra("type", type);
                    i.putExtra("isWatch",true);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                }else if("18".equals(type)){
                    Intent i = new Intent(context, NeedOrderDetailActivity3.class);
                    i.putExtra("flag", true);
                    i.putExtra("type", type);
                    i.putExtra("id", oid);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                }else if("19".equals(type)){
                    Intent i = new Intent(context, EnvelopeActivity.class);
                    i.putExtra("id",oid);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                }else if("21".equals(type)){
                    Intent i = new Intent(context, OrderConfigActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                }else if("22".equals(type)){
                    Intent i = new Intent(context, FwZdActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                }else if("23".equals(type)){
                    Intent i = new Intent(context, FwZdActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                }
                else if("24".equals(type)){
                    Intent i = new Intent(context,ProMainActivity.class);
                    i.putExtra("skill","orderyes");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                }else if("26".equals(type)){
//                    Intent i = new Intent(context,ProMainActivity.class);
//                    i.putExtra("skill","orderyes");
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    context.startActivity(i);
                }else if("25".equals(type)){
                    Intent it = new Intent(context,OrderConfigActivity.class);
                    it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(it);
                }else if("27".equals(type)){
                    SPUtils.saveBoolean(context,"is_h_id",true);
                    SPUtils.saveString(context,"h_id_str",h_id);
                    Intent i = new Intent(context, FwZdActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                }else if("28".equals(type)){
                    Intent i = new Intent(context, NeedOrderDetailActivity3.class);
                    i.putExtra("id", oid);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                }else  if("24".equals(type)){
//                    Intent i = new Intent(context,OrderYesDetailActivity.class);
//                    i.putExtra("id","oid");
//                    i.putExtra("type",type);
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    context.startActivity(i);
                } if("20".equals(type)){
                    if(ProMainActivity.activity!=null){
                        ProMainActivity.activity.finish();
                    }
                    Intent i = new Intent(context,ProMainActivity.class);
                    i.putExtra("skill","cycle");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
//            //打开自定义的Activity
//            Intent i = new Intent(context, MainActivity.class);
//            bundle.putString("flag", "vip_page");
//            i.putExtras(bundle);
//            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            context.startActivity(i);


        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            String data = bundle.getString(JPushInterface.EXTRA_EXTRA);
            System.out.println(">>>>>>>>>>>>>>>>>>jus4>>>" + data);
            Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            String data = bundle.getString(JPushInterface.EXTRA_EXTRA);
            System.out.println(">>>>>>>>>>>>>>>>>>jus5>>>" + data);
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Log.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
        } else {
            String data = bundle.getString(JPushInterface.EXTRA_EXTRA);
            System.out.println(">>>>>>>>>>>>>>>>>>jus6>>>" + data);
            Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());

        }
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }
                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();
                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, Bundle bundle) {
//		if (MainActivity.isForeground) {
//			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//			if (!ExampleUtil.isEmpty(extras)) {
//				try {
//					JSONObject extraJson = new JSONObject(extras);
//					if (extraJson.length() > 0) {
//						msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//					}
//				} catch (JSONException e) {
//
//				}
//
//			}
//			context.sendBroadcast(msgIntent);
//		}
    }
}
