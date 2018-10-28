package com.haiwai.housekeeper.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;

/**
 * ��ȡ��Ļ��С�ռ��ߵĹ�����
 * @author Vayne
 *
 */
public class SizeUtil {

	private static int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);  
	private static  int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);  

	/**
	 * ��ÿؼ��Ŀ�
	 * @param view
	 * @return
	 */
	public static int getViewWidth(View view){
		view.measure(w, h);  
		int width =view.getMeasuredWidth();  
		return width;
	}

	/**
	 * ��ÿؼ��ĸ�
	 * @param view
	 * @return
	 */
	public static int getViewHeight(View view){
		view.measure(w, h);  
		int height =view.getMeasuredHeight();  
		return height;
	}

	/**
	 * �����Ļ�Ŀ�
	 * @param
	 * @return
	 */
	public static int getScreenWidth(Activity activity){
		int screenWidth = activity.getWindowManager().getDefaultDisplay().getWidth(); // ��Ļ�����أ��磺480px��  
		return screenWidth;
	}

	/**
	 * �����Ļ�ĸ�
	 * @param
	 * @return
	 */
	public static int getScreenHeight(Activity activity){
		//		int screenHeight = activity.getWindowManager().getDefaultDisplay().getHeight(); // ��Ļ�ߣ����أ��磺800p��  
		//		return screenHeight;

		DisplayMetrics dm = new DisplayMetrics();  
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);  
		float density = dm.density; // ��Ļ�ܶȣ����ر�����0.75/1.0/1.5/2.0��  
		float densityDPI = dm.densityDpi; // ��Ļ�ܶȣ�ÿ�����أ�120/160/240/320��  
		float xdpi = dm.xdpi;  
		float ydpi = dm.ydpi;  
		int screenHeightDip = dm.heightPixels; // ��Ļ��dip���磺533dip��
		return screenHeightDip;
	}

	/**
	 *��ȡ״̬���߶�
	 *@param
	 *@return
	 **/
	public static int getStateHeight(Context context){ 

		int statusHeight = -1;
		try {
			Class clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height")
					.get(object).toString());
			statusHeight = context.getResources().getDimensionPixelSize(height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusHeight;
	} 

	/**
	 * ��ȡ�������߶�
	 * @param activity
	 * @return
	 */
	public static int getTitleHeight(Activity activity) {
		Rect rect = new Rect();
		Window window = activity.getWindow();
		window.getDecorView().getWindowVisibleDisplayFrame(rect);
		int statusBarHeight = rect.top;
		int contentViewTop = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
		int titleBarHeight = contentViewTop - statusBarHeight;
		return titleBarHeight;
	}
}
