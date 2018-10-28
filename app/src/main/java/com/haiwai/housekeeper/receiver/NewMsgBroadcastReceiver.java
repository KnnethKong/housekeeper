package com.haiwai.housekeeper.receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.LoginActivity;
import com.haiwai.housekeeper.activity.user.MainActivity;
import com.haiwai.housekeeper.activity.user.SetActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.service.polling.PollingService;
import com.haiwai.housekeeper.service.polling.PollingUtils;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.ZeroPop;
import com.haiwai.housekeeper.widget.CustomDialog;
import com.sobot.chat.SobotApi;

/**
 * Created by ihope006 on 2017/2/27.
 */

public class NewMsgBroadcastReceiver extends BroadcastReceiver {
    MyApp mApp;

    @Override
    public void onReceive(final Context context, Intent intent) {
        mApp = MyApp.getTingtingApp();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle(R.string.tstitle);
        dialogBuilder.setMessage(R.string.tiscontent);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton(context.getString(R.string.done), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mApp.setLoginState(false);
                SobotApi.disSobotChannel(context);
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
                bundle.putString("flag", "set");
                intent.putExtras(bundle);
                PollingUtils.stopPollingService(context, PollingService.class, PollingService.ACTION);
                context.startActivity(intent);
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        try {
            alertDialog.show();
        }catch (Exception e){

        }

    }
}
