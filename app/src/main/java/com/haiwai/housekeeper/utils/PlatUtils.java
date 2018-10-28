package com.haiwai.housekeeper.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.MainActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.widget.RegisterDialog;

/**
 * Created by ihope006 on 2016/6/29.
 */
public class PlatUtils {
    /**
     * 将数字转换为相对数字，节省版面，比如1234->1.2k，34567->3.5w
     * <p>
     * 注意，0返回null，最大的权值为w
     *
     * @param number
     * @return 数字相对大小字符串
     */
    public static String approximate(int number) {
        if (number == 0) {
            return null;
        }
        if (number < 1000) {
            return String.valueOf(number);
        } else if (number < 10000) {
            float f = number * 1f / 1000;
            return String.valueOf(Math.round(f * 10) / 10.0) + "k";
        } else {
            float f = number * 1f / 10000;
            return String.valueOf(Math.round(f * 10) / 10.0) + "w";
        }
    }

    /**
     * 四舍五入float
     *
     * @param num 浮点数
     * @param bit 多少位
     * @return the  you required
     */
    public static float scaleNumber(float num, int bit) {
        int range = 10 * bit;
        return (float) Math.round(num * range) / range;
    }

    /**
     * 设置某个view里面的textview的text
     *
     * @param parent     父容器
     * @param textViewId textview的id
     * @param text
     * @return that textview
     */
    public static TextView setText(View parent, int textViewId, CharSequence text) {
        TextView textView = (TextView) parent.findViewById(textViewId);
        textView.setText(text);
        return textView;
    }

    /**
     * 判断EditText输入是否为空
     *
     * @param text
     * @return true为空，false不为空
     */
    public static boolean getEditStr(EditText text) {
        if (TextUtils.isEmpty(text.getText().toString().trim().replace(" ", "")))
            return true;
        return false;
    }

    /**
     * 判断TextView是否为空
     *
     * @param text
     * @return true为空，false不为空
     */
    public static boolean getTextViewStr(TextView text) {
        if (TextUtils.isEmpty(text.getText().toString().trim().replace(" ", "")))
            return true;
        return false;
    }

    /**
     * 判断2次输入密码是否一致
     *
     * @param text1
     * @param text2
     * @return true一致，false不一致
     */
    public static boolean equalsEdit(EditText text1, EditText text2) {
        if (text1.getText().toString().trim().replace(" ", "").equals(text2.getText().toString().trim().replace(" ", "")))
            return true;
        return false;
    }

    /**
     * Button倒计时
     *
     * @param btn
     */
    public static void startTimer(final Button btn) {
        CountDownTimer countDownTimer = new CountDownTimer(60 * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                btn.setBackgroundResource(R.mipmap.btn_code_yzm_gray);
                btn.setText(millisUntilFinished / 1000 + "秒");
                btn.setClickable(false);//防止重复点击
            }

            @Override
            public void onFinish() {
                //可以在这做一些操作,如果没有获取到验证码再去请求服务器
                btn.setClickable(true);//防止重复点击
                btn.setBackgroundResource(R.mipmap.btn_code_yzm_pink);
                btn.setText("重新获取");
            }
        };
        countDownTimer.start();
    }

    /**
     * TextView倒计时
     *
     * @param tv
     */
    public static void startTimer(final TextView tv) {
        CountDownTimer countDownTimer = new CountDownTimer(60 * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                tv.setTextColor(Color.parseColor("#B1B1B1"));
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    tv.setText(millisUntilFinished / 1000 + "s");
                }else{
                    tv.setText(millisUntilFinished / 1000 + "秒");
                }

                tv.setClickable(false);//防止重复点击
            }

            @Override
            public void onFinish() {
                //可以在这做一些操作,如果没有获取到验证码再去请求服务器
                tv.setClickable(true);//防止重复点击
                tv.setTextColor(Color.parseColor("#2AD7C9"));
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    tv.setText("Verification Code SMS");
                }else{
                    tv.setText("获取验证码");
                }

            }
        };
        countDownTimer.start();
    }

    /**
     * 格式化0-99为2位数
     *
     * @param i
     * @return
     */
    public static String init2(int i) {
        if (i < 10)
            return "0" + i;

        return i + "";
    }

    /**
     * 判断字符串是否为空，这里只用在预览图的上传和跳转界面
     *
     * @param text
     * @return
     */
    public static String getStrLen(String text) {
        if ("".equals(text) || text == null) {
            return "";
        }
        return text.substring(0, text.length() - 1);
    }

    public static void hideSoftkeyboard(Context context, EditText view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0); //强制隐藏键盘
        }
    }

    public static double str2Double(String text) {
        if ("".equals(text) || text == null) {
            return 1;
        }
        return Double.parseDouble(text);
    }

    public static void dismissActivity(final Context context, final RegisterDialog dialog) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            public void run() {
                dialog.dismiss();
                ActivityTools.goNextActivity(context, MainActivity.class);
            }
        }, 5000);
    }

    public static int getImageRario(Context context, int raHei, int raWid) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay()
                .getWidth();
        return raHei * width / raWid;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
