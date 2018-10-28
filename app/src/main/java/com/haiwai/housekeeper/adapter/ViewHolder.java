package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * viewholder的封装类
 * 
 * @author abner
 * 
 */
@SuppressWarnings("unchecked")
public class ViewHolder {
	private View contentview;
	private SparseArray<View> mViews;
	private int position;

	public ViewHolder(Context context, ViewGroup parent, int LayoutId,
					  int position) {
		mViews = new SparseArray<View>();
		this.position = position;
		contentview = LayoutInflater.from(context).inflate(LayoutId, parent,
				false);
		contentview.setTag(this);
	}

	public static ViewHolder get(Context context, ViewGroup parent,
			int LayoutId, int position, View convertView) {
		if (convertView == null) {
			return new ViewHolder(context, parent, LayoutId, position);

		}
		return (ViewHolder) convertView.getTag();
	}

	/**
	 * 根据传入的控件ID去找控件。如果没有去findviewByid
	 * 
	 * @param layoutId
	 * @return
	 */
	public <T extends View> T getview(int layoutId) {
		View view = mViews.get(layoutId);
		if (view == null) {
			view = contentview.findViewById(layoutId);
			mViews.put(layoutId, view);
		}
		return (T) view;
	}

	public View getContentView() {
		return contentview;

	}

	/**
	 * 为textview设置文字
	 * 
	 * @param layouId
	 *            textviewID
	 * @param string
	 *            字符串
	 * 
	 *  以下方法可根据自己的需要完善
	 * @return ViewHolder
	 */
	public ViewHolder setTextview(int layouId, String string) {
		TextView view = (TextView) mViews.get(layouId);
		if (view == null) {
			view = (TextView) contentview.findViewById(layouId);
			mViews.put(layouId, view);
		}
		if (string==null) {
			return this;
		}
		view.setText(string);
		return this;
	}
	/**
	 * 设置imageview方法
	 * @param layouId
	 * @param imgId
	 * @return
	 */
	public ViewHolder setImageResource(int layouId, int imgId){
		
		ImageView view = (ImageView) mViews.get(layouId);
		if (view == null) {
			view = (ImageView) contentview.findViewById(layouId);
			mViews.put(layouId, view);
		}
		view.setImageResource(imgId);
		return this;
		
	}


}
