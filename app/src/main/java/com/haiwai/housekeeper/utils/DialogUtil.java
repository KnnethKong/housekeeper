package com.haiwai.housekeeper.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;


import com.haiwai.housekeeper.R;

import java.util.Calendar;

/**
 * dialog 的对话框
 *
 * @author damo
 */
public class DialogUtil {
    public final static int LOADING_ID = 0x01;
    private static SparseArray<Dialog> mManagerDialog;
    /**
     * 城市选择框的 id
     */
    private static int citySelectId = 10010;
    /**
     * 时间选择框 id
     */
    private static int timeSelectId = 12121;
    /**
     * 进度框的 id
     */
    private static int loadingId = 1234567;
    private static NumberPicker radius_picker;
    private static TextView dismiss;
    private static TextView save;
    private static LinearLayout layout;

    /**
     * 底部list选择框
     *
     * @param context
     * @param title           标题(可选)
     * @param args            item内容
     * @param colorid         item字体颜色(可选)
     * @param onClickListener item点击事件(默认把内容设置为view(arg1)的tag,可从view.getTag()取出)
     */

    public static void showSelecListtDialog(final Context context,
                                            String title, final String[] args, int colorid,
                                            final OnItemClickListener onClickListener) {
        final Dialog dlg = new Dialog(context, R.style.Dialog_Transparent_Theme);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(
                R.layout.dialog_bottom_select_list, null);
        final int cFullFillWidth = 10000;
        layout.setMinimumWidth(cFullFillWidth);
        Button cancel = (Button) layout.findViewById(R.id.dialogCancelBtn);
        TextView titleTv = (TextView) layout.findViewById(R.id.dialogTitle);
        ListView lv = (ListView) layout.findViewById(R.id.dialogTitleDivider);
        if (!TextUtils.isEmpty(title)) {
            titleTv.setText(title);
            titleTv.setVisibility(View.VISIBLE);
            lv.setVisibility(View.VISIBLE);
        }
        cancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dlg.dismiss();
            }
        });
        int height = 0;
        if (args != null) {
            if (args.length == 1) {
                height = (int) EvmUtil.dip2px(context, 40f);
            } else if (args.length == 2) {
                height = (int) EvmUtil.dip2px(context, 80);
            } else if (args.length == 3) {
                height = (int) EvmUtil.dip2px(context, 120);
            } else if (args.length >= 4) {
                height = (int) EvmUtil.dip2px(context, 160);
            } else if (args.length == 0) {
                Toast.makeText(context, "没有可用渠道...", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "网络状态不佳或服务器异常......", Toast.LENGTH_SHORT)
                    .show();

        }

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, height);
        lv.setLayoutParams(params);
        Window w = dlg.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = 0;
        final int cMakeBottom = -1000;
        lp.y = cMakeBottom;
        lp.gravity = Gravity.BOTTOM;
        dlg.onWindowAttributesChanged(lp);
        dlg.setCanceledOnTouchOutside(true);
        dlg.setContentView(layout);
        class myAdapter extends BaseAdapter {
            int colorid;

            @Override
            public int getCount() {
                return args == null ? 0 : args.length;
            }

            @Override
            public Object getItem(int arg0) {
                return args[arg0];
            }

            @Override
            public long getItemId(int arg0) {
                return arg0;
            }

            void setcolorid(int color) {
                colorid = color;

            }

            ;

            @Override
            public View getView(int arg0, View arg1, ViewGroup arg2) {
                arg1 = LayoutInflater.from(context).inflate(
                        R.layout.dialog_item_lv, null);
                TextView tv = (TextView) arg1.findViewById(R.id.d_tv);
                if (colorid <= 0) {
                    colorid = R.color.gray;
                }
                tv.setTextColor(context.getResources().getColor(colorid));
                tv.setText(args[arg0]);
                arg1.setTag(args[arg0]);
                return arg1;
            }

        }

        OnItemClickListener clickListener = new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position,
                                    long arg3) {
                if (onClickListener != null) {
                    onClickListener.onItemClick(arg0, v, position, arg3);
                }
                dlg.dismiss();
            }
        };
        myAdapter adapter = new myAdapter();
        adapter.setcolorid(colorid);
        lv.setOnItemClickListener(clickListener);
        lv.setAdapter(adapter);
        if (args == null) {

        } else if (args != null || args.length != 0) {
            dlg.show();
        }

    }

//    /**
//     * 城市底部选择dialog类似iOS
//     *
//     * @author abner
//     */
//
//    public static void showCityDialog(Context context, final String[] city,
//                                      final TextView textView, final OnClickListener onclick) {
//        if (mManagerDialog == null) {
//            mManagerDialog = new SparseArray<Dialog>();
//        }
//        Dialog dlg = (Dialog) mManagerDialog.get(citySelectId);
//        if (dlg == null) {
//            dlg = new Dialog(context, R.style.Dialog_Transparent_Theme);
//
//            LayoutInflater inflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            layout = (LinearLayout) inflater.inflate(R.layout.radius_diaog,
//                    null);
//            final int cFullFillWidth = 10000;
//            layout.setMinimumWidth(cFullFillWidth);
//            dlg.setCanceledOnTouchOutside(false);
//            mManagerDialog.put(citySelectId, dlg);
//            radius_picker = (NumberPicker) layout
//                    .findViewById(R.id.radius_picker);
//            radius_picker
//                    .setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
//            dismiss = (TextView) layout.findViewById(R.id.dismiss);
//            save = (TextView) layout.findViewById(R.id.save);
//        }
//
//        radius_picker.setDisplayedValues(city);
//        radius_picker.setMinValue(0);
//        radius_picker.setMaxValue(city.length - 1);
//        dismiss.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                dismissDialog(citySelectId);
//            }
//        });
//        save.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                textView.setText(city[radius_picker.getValue()].toString());
//                dismissDialog(citySelectId);
//                if (onclick != null) {
//                    onclick.onClick(arg0);
//                }
//            }
//        });
//        Window w = dlg.getWindow();
//        WindowManager.LayoutParams lp = w.getAttributes();
//        lp.x = 0;
//        final int cMakeBottom = -1000;
//        lp.y = cMakeBottom;
//        lp.gravity = Gravity.BOTTOM;
//        dlg.onWindowAttributesChanged(lp);
//        dlg.setCanceledOnTouchOutside(true);
//        dlg.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                dismissDialog(citySelectId);
//            }
//        });
//        dlg.setContentView(layout);
//
//        if (dlg.isShowing()) {
//            dlg.dismiss();
//        }
//
//        dlg.show();
//    }

//    /**
//     * 时间底部选择dialog类似iOS
//     *
//     * @author abner
//     */
//    public static void showTimeDialog(Context context, final TextView textView) {
//        if (mManagerDialog == null) {
//            mManagerDialog = new SparseArray<Dialog>();
//        }
//        Dialog dlg = (Dialog) mManagerDialog.get(timeSelectId);
//        if (dlg == null) {
//            dlg = new Dialog(context, R.style.Dialog_Transparent_Theme);
//
//            LayoutInflater inflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            LinearLayout layout = (LinearLayout) inflater.inflate(
//                    R.layout.time_dialog, null);
//            final int cFullFillWidth = 10000;
//            layout.setMinimumWidth(cFullFillWidth);
//            dlg.setCanceledOnTouchOutside(false);
//            mManagerDialog.put(timeSelectId, dlg);
//            // 找控件
//            final DatePicker dialogView = (DatePicker) layout
//                    .findViewById(R.id.calendar_view);
//            dialogView
//                    .setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
//            final TextView dismiss = (TextView) layout
//                    .findViewById(R.id.dismiss);
//            final TextView save = (TextView) layout.findViewById(R.id.save);
//
//            dialogView.init(dialogView.getYear(), dialogView.getMonth(),
//                    dialogView.getDayOfMonth(), new OnDateChangedListener() {
//
//                        @Override
//                        public void onDateChanged(DatePicker view,
//                                                  final int year, final int monthOfYear,
//                                                  final int dayOfMonth) {
//                            //  自动生成的方法存根
//                            save.setOnClickListener(new OnClickListener() {
//
//                                @Override
//                                public void onClick(View arg0) {
//                                    textView.setText(year + "-"
//                                            + (PlatUtils.init2(monthOfYear + 1)) + "-"
//                                            + PlatUtils.init2(dayOfMonth));
//                                    dismissDialog(timeSelectId);
//                                }
//                            });
//                        }
//                    });
//            dismiss.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View arg0) {
//                    dismissDialog(timeSelectId);
//                }
//            });
//            save.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View arg0) {
//                    Calendar c = Calendar.getInstance();
//                    String selecterTime = c.get(Calendar.YEAR) + "-"
//                            + (PlatUtils.init2(c.get(Calendar.MONTH) + 1)) + "-"
//                            + (PlatUtils.init2(c.get(Calendar.DAY_OF_MONTH)));
//                    textView.setText(selecterTime);
//                    dismissDialog(timeSelectId);
//
//                }
//            });
//            Window w = dlg.getWindow();
//            WindowManager.LayoutParams lp = w.getAttributes();
//            lp.x = 0;
//            final int cMakeBottom = -1000;
//            lp.y = cMakeBottom;
//            lp.gravity = Gravity.BOTTOM;
//            dlg.onWindowAttributesChanged(lp);
//            dlg.setCanceledOnTouchOutside(true);
//            dlg.setContentView(layout);
//        }
//        if (dlg.isShowing()) {
//            dlg.dismiss();
//        }
//        dlg.show();
//
//    }

    /**
     * 初始化对话框(类似对话框)中动态view
     *
     * @param context
     * @param drawableId 需要的icon资源
     * @param textid     文本资源
     * @param listener   点击事件
     * @return
     */
    public static LinearLayout initDialogLayout(Context context,
                                                int drawableId, int textid, OnClickListener listener) {

        LinearLayout layout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -1);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                EvmUtil.dip2px(context, 60), EvmUtil.dip2px(context, 60));
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(-2,
                -2);
        params1.gravity = Gravity.CENTER;

        Button btn = new Button(context);
        btn.setBackgroundResource(drawableId);

        TextView textView = new TextView(context);
        textView.setText(textid);
        textView.setWidth(EvmUtil.dip2px(context, 80));
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);

        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(params);
        layout.addView(btn, params1);
        layout.addView(textView, params3);

        btn.setOnClickListener(listener);
        btn.setId(textid);
        // layout.setOnClickListener(listener);
        return layout;
    }

    public static void dismissLoadDialog() {
        dismissDialog(loadingId);
    }

    /**
     * 取消Dialog
     *
     * @param id 对话框唯一id,对应某一个展示对话框方法
     */
    public static void dismissDialog(int id) {
        try {
            if (mManagerDialog == null) {
                throw missingDialog(id);
            }
            Dialog dialog = mManagerDialog.get(id);
            if (dialog == null) {
                throw missingDialog(id);
            }
            dialog.dismiss();
            mManagerDialog.remove(id);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private static IllegalArgumentException missingDialog(int id) {
        return new IllegalArgumentException("no dialog with id " + id
                + " was ever " + "shown via Activity#showDialog");
    }

    // /**
    // * 时间底部选择器（高仿IOS）
    // *
    // * @param context
    // * @param OnWheelScrollListener
    // * item滑动事件(默认把内容设置为view的tag,可从view.getTag()取出) 有待优化。
    // * @author
    // * @return String 当前日期
    // */
    // public static String showTimeDialog(Context context, String date,
    // final OnWheelScrollListener scrollListener) {
    // final Dialog dlg = new Dialog(context, R.style.Dialog_Transparent_Theme);
    //
    // LayoutInflater inflater = (LayoutInflater) context
    // .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    // LinearLayout layout = (LinearLayout) inflater.inflate(
    // R.layout.dialog_bottom_select_view, null);
    // View titleDivider = layout.findViewById(R.id.dialogTitleDivider);
    // final int cFullFillWidth = 10000;
    // layout.setMinimumWidth(cFullFillWidth);
    // LinearLayout contentList = (LinearLayout) layout
    // .findViewById(R.id.dialogContent);
    // LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
    // ViewGroup.LayoutParams.FILL_PARENT,
    // ViewGroup.LayoutParams.FILL_PARENT);
    // layoutParams.setMargins(0, 0, 0, 0);
    // contentList.setLayoutParams(layoutParams);
    // Button cancel = (Button) layout.findViewById(R.id.dialogCancelBtn);
    // TextView titleTv = (TextView) layout.findViewById(R.id.dialogTitle);
    // titleTv.setVisibility(View.GONE);
    // cancel.setOnClickListener(new OnClickListener() {
    // @Override
    // public void onClick(View arg0) {
    // dlg.dismiss();
    // }
    // });
    // cancel.setVisibility(View.GONE);
    // Calendar c = Calendar.getInstance();
    // int norYear = 0;
    // int norMonth = 0;
    // int noDay = 0;
    // if (date == null || date.equals("")||date.equals("**请选择日期**")) {
    // norYear = c.get(Calendar.YEAR);
    // norMonth = c.get(Calendar.MONTH);
    // noDay = c.get(Calendar.DAY_OF_MONTH);
    // } else {
    // String[] split = date.split("-");
    // norYear=Integer.parseInt(split[0]);
    // norMonth=Integer.parseInt(split[1])-1;
    // noDay=Integer.parseInt(split[2]);
    // }
    // final View view = inflater.inflate(
    // R.layout.item_diakog_wheel_date_picker, null);
    // final WheelView year = (WheelView) view.findViewById(R.id.year);
    // year.setAdapter(new NumericWheelAdapter(1950, norYear));
    // year.setLabel("年");
    // year.setCyclic(true);
    // final WheelView month = (WheelView) view.findViewById(R.id.month);
    // month.setAdapter(new NumericWheelAdapter(1, 12, "%02d"));
    // month.setLabel("月");
    // month.setCyclic(true);
    // final WheelView day = (WheelView) view.findViewById(R.id.day);
    // initDay(day, norYear, norMonth);
    // day.setLabel("日");
    // day.setCyclic(true);
    // year.setCurrentItem(norYear - 1950);
    // month.setCurrentItem(norMonth);
    // day.setCurrentItem(noDay - 1);
    //
    // OnWheelScrollListener listener = new OnWheelScrollListener() {
    //
    // @Override
    // public void onScrollingStarted(WheelView wheel) {
    // }
    //
    // @Override
    // public void onScrollingFinished(WheelView wheel) {
    // int n_year = year.getCurrentItem() + 1950;//
    // int n_month = month.getCurrentItem() + 1;//
    // int n_day = day.getCurrentItem() + 1;
    // initDay(day, n_year, n_month);
    // day.setTag(n_year + "-" + n_month + "-" + n_day);
    // year.setTag(n_year + "-" + n_month + "-" + n_day);
    // month.setTag(n_year + "-" + n_month + "-" + n_day);
    //
    // if (scrollListener != null) {
    // scrollListener.onScrollingFinished(wheel);
    // }
    //
    // }
    // };
    // int n_year = year.getCurrentItem() + 1950;//
    // int n_month = month.getCurrentItem() + 1;//
    // int n_day = day.getCurrentItem() + 1;
    //
    // String datee = n_year + "-" + n_month + "-" + n_day;
    // day.addScrollingListener(listener);
    // month.addScrollingListener(listener);
    // year.addScrollingListener(listener);
    // contentList.addView(view);
    // contentList.setBackgroundColor(Color.parseColor("#E9E9E9"));
    // Window w = dlg.getWindow();
    // WindowManager.LayoutParams lp = w.getAttributes();
    // lp.x = 0;
    // final int cMakeBottom = -1000;
    // lp.y = cMakeBottom;
    // lp.gravity = Gravity.BOTTOM;
    // dlg.onWindowAttributesChanged(lp);
    // dlg.setCanceledOnTouchOutside(true);
    // dlg.setContentView(layout);
    // dlg.show();
    // return datee;
    // }
    //
    // private static void initDay(WheelView day, int norYear, int norMonth) {
    // day.setAdapter(new NumericWheelAdapter(1, getDay(norYear, norMonth),
    // "%02d"));
    //
    // }

}
