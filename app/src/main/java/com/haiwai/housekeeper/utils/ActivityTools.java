package com.haiwai.housekeeper.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ActivityTools {

	public static void goNextActivity(Context context, Class clazz) {
		Intent intent = new Intent(context, clazz);
		context.startActivity(intent);
	}

	public static void goNextActivityWithFlag(Context context, Class clazz) {
		Intent intent = new Intent(context, clazz);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	public static void goNextActivityInNewTask(Context context, Class clazz) {
		Intent intent = new Intent(context, clazz);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	public static void goNextActivity(Context context, Class clazz,
			Bundle bundle) {
		Intent intent = new Intent(context, clazz);
		intent.putExtras(bundle);
		context.startActivity(intent);
	}

	public static void goNextActivityInNewTask(Context context, Class clazz,
			Bundle bundle) {
		Intent intent = new Intent(context, clazz);
		intent.putExtras(bundle);
		context.startActivity(intent);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	}

	public static void goNextActivityForResult(Context context, Class clazz,
			Bundle bundle, int requestCode) {
		Intent intent = new Intent(context, clazz);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		((Activity) context).startActivityForResult(intent, requestCode);
	}
}
