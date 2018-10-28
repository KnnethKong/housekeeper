package com.haiwai.housekeeper.utils;




import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.haiwai.housekeeper.R;

/**
 * Toast 
 * @author Vayne
 *
 */
public class TipsToast extends Toast {

	public TipsToast(Context context) {
		super(context);
	}

	public static TipsToast makeText(Context context, CharSequence text, int duration) {
		TipsToast result = new TipsToast(context);

		LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflate.inflate(R.layout.view_tips, null);
		TextView tv = (TextView) v.findViewById(R.id.tips_msg);
		tv.setText(text);

		result.setView(v);
		// setGravity���� ��ʾλ��
		result.setGravity(Gravity.BOTTOM, 0,100);
		result.setDuration(duration);

		return result;
	}

	public static TipsToast makeText(Context context, int resId, int duration) throws Resources.NotFoundException {
		return makeText(context, context.getResources().getText(resId), duration);
	}
	
	@Override
	public void setText(CharSequence s) {
		if (getView() == null) {
			throw new RuntimeException("û�д���Toast");
		}
		TextView tv = (TextView) getView().findViewById(R.id.tips_msg);
		if (tv == null) {
			throw new RuntimeException("û�д���Toast");
		}
		tv.setText(s);
	}
}
