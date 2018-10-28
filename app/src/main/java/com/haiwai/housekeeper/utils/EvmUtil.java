package com.haiwai.housekeeper.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

/**
 * dip与像素的转换工具
 * 
 * @author abner
 * 
 */
public class EvmUtil {

	public static final String TAG = "EvmUtil";

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int[] getWindow(Context context) {
		int[] windows = new int[3];
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		int width = metric.widthPixels; // 屏幕宽度（像素）
		int height = metric.heightPixels; // 屏幕高度（像素）

		float density = metric.density; // 屏幕密度（0.75 / 1.0 / 1.5）
		int densityDpi = metric.densityDpi; // 屏幕密度DPI（120 / 160 / 240）
		windows[0] = width;
		windows[1] = height;
		windows[2] = densityDpi;
		return windows;
	}

	/**
	 * 按UI详情设置一些距离
	 */
	public static void setViewHeight(Context context, View view, int sizeOf1080) {
		int[] a = EvmUtil.getWindow(context);
		int height = (int) ((double) a[0] / 1080 * sizeOf1080);
		LayoutParams params = view.getLayoutParams();
		params.height = height;
		view.setLayoutParams(params);
	}

	/**
	 * 只是在引导页底部控制白点处用到
	 */
	public static void setViewHeight1(Context context, View view, int sizeOf1080) {
		int[] a = EvmUtil.getWindow(context);
		int height = (int) ((double) a[0] / 1080 * sizeOf1080);
		LayoutParams params = view.getLayoutParams();
		params.height = height + 40;
		view.setLayoutParams(params);
	}

	/**
	 * 按图形原比例显示view
	 * 
	 * @param view
	 * @param screenWidth
	 * @param OriginalWidth
	 * @param OriginalHeight
	 */
	public static void setViewForOriginal(View view, int screenWidth,
			int OriginalWidth, int OriginalHeight) {
		int height = (int) ((double) screenWidth / OriginalWidth * OriginalHeight);
		LayoutParams params = view.getLayoutParams();
		params.height = height;
		view.setLayoutParams(params);
	}

}
