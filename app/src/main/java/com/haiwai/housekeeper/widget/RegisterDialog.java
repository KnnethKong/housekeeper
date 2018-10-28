package com.haiwai.housekeeper.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.utils.PlatUtils;

public class RegisterDialog extends Dialog {

    private Context context;
    private String title;
    private String message;
    private String confirmButtonText;
    private String cacelButtonText;

    public RegisterDialog(Context context, String title, String confirmButtonText, String cacelButtonText) {
        super(context, R.style.Dialog_Custom);
        this.context = context;
        this.title = title;
        this.confirmButtonText = confirmButtonText;
        this.cacelButtonText = cacelButtonText;
    }
    public RegisterDialog(Context context, String title, String message) {
        super(context, R.style.Dialog_Custom);
        this.context = context;
        this.title = title;
        this.message = message;
        setCanceledOnTouchOutside(true);
//        PlatUtils.dismissActivity(context, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.register_dialog, null);
        setContentView(view);

        TextView tvTitle = (TextView) view.findViewById(R.id.register_dialog_title);
        TextView tvMessage = (TextView) view.findViewById(R.id.register_dialog_message);
//        TextView tvConfirm = (TextView) view.findViewById(R.id.register_dialog_confirm);
//        TextView tvCancel = (TextView) view.findViewById(R.id.register_dialog_cancel);

        tvTitle.setText(title);
        tvMessage.setText(message);


        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

}