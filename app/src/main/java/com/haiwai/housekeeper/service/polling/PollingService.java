package com.haiwai.housekeeper.service.polling;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.SyncStateContract;
import android.support.v4.app.NotificationCompat;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.MainActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.entity.SysNumEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.haiwai.housekeeper.utils.ParseVipJsonUtils.parseJson;

/**
 * Created by ihope006 on 2017/2/8.
 */

public class PollingService extends Service {
    public static final String ACTION = "com.haiwai.housekeeper.service.polling.PollingService";
    //    private Notification mNotification;
//    private NotificationManager mManager;
    private String id;

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart(Intent intent, int startId) {
        id = intent.getStringExtra("id");
        new PollingThread().start();
    }

    //弹出Notification
//    private void showNotification() {
//        mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        NotificationCompat.Builder mBuilder =
//                new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.drawable.ic_launcher)
//                        .setContentTitle("New Message")
//                        .setContentText("You hava New Message!");
//        Intent i = new Intent(this, MainActivity.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(
//                this,
//                0,
//                i,
//                PendingIntent.FLAG_UPDATE_CURRENT
//        );
//        mManager.notify(0, mBuilder.build());
//    }


    class PollingThread extends Thread {
        @Override
        public void run() {
            try {
                String uri = Contants.unread_message + "uid=" + id + "registrationid=" + AppGlobal.getInstance().getRegId() == null ? "" : AppGlobal.getInstance().getRegId() + "login_key=" + AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey();
                URL url = new URL(uri);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(1000);
                conn.setReadTimeout(2000);
                conn.connect();
                int code = conn.getResponseCode();
                if (200 == code) {
                    InputStream is = conn.getInputStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    while ((len = is.read(buffer)) != -1) {
                        bos.write(buffer, 0, len);
                    }
                    is.close();
                    String str = bos.toString();
                    bos.close();
                    parseStr(str);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseStr(String str) {
        //{"status":200,"data":{"uid":"3","follow":0,"system":0,"pro_system":0}}
        try {
            JSONObject jsonObject = new JSONObject(str);
            int code = jsonObject.optInt("status");
            if (code == 200) {
                JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                int system = jsonObject1.optInt("system");
                int pro_system = jsonObject1.optInt("pro_system");
                int follow = jsonObject1.optInt("follow");
                SPUtils.putInt(this, "system", system);
                SPUtils.putInt(this, "pro_system", pro_system);
                SPUtils.putInt(this, "follow", follow);
            } else {
                ToastUtil.longToast(PollingService.this, ErrorCodeUtils.getRegisterError(code + ""));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Service:onDestroy");
    }


    /**
     * public class MainActivity extends Activity {
    @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //Start polling service
    System.out.println("Start polling service...");
    PollingUtils.startPollingService(this, 5, PollingService.class, PollingService.ACTION);
    }

    @Override protected void onDestroy() {
    super.onDestroy();
    //Stop polling service
    System.out.println("Stop polling service...");
    PollingUtils.stopPollingService(this, PollingService.class, PollingService.ACTION);
    }
    }
     */
}