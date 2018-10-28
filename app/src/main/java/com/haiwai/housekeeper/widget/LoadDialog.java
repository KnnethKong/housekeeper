package com.haiwai.housekeeper.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.haiwai.housekeeper.R;


public class LoadDialog {

    private static Dialog mLoadDialog;

    /**
     * loading 转圈
     */
    public static void showProgressDialog(Context context) {
        if (mLoadDialog != null && mLoadDialog.isShowing()) {
            return;
        }
        mLoadDialog = new Dialog(context, R.style.Dialog_Transparent_Theme);
//        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null, false);
//        mLoadDialog.setContentView(R.layout.dialog_loading);
//        ProgressBar view = new ProgressBar(context);
//        view.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.logo_roate));
        View view = LayoutInflater.from(context).inflate(R.layout.load_progress_view,null);
        mLoadDialog.setContentView(view);
        mLoadDialog.setCanceledOnTouchOutside(false);
        mLoadDialog.setCancelable(true);
        mLoadDialog.show();
    }

    /**
     * 关闭 转圈
     */
    public static void closeProgressDialog() {
        if (mLoadDialog != null && mLoadDialog.isShowing()) {
            try {
                mLoadDialog.dismiss();
            }catch (Exception e){

            }

        }
    }
}
