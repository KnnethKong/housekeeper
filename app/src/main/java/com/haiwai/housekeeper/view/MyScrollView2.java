package com.haiwai.housekeeper.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView2 extends ScrollView {
	private OnScrollListener onScrollListener;
	/**
	 * 主要是用在用户手指离开MyScrollView，MyScrollView还在继续滑动，我们用来保存Y的距离，然后做比较
	 */
	private int lastScrollY;
	private boolean isScroll=true;
	public MyScrollView2(Context context) {
		this(context, null);
	}

	public MyScrollView2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyScrollView2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * 设置滚动接口
	 * @param onScrollListener
	 */
	public void setOnScrollListener(OnScrollListener onScrollListener) {
		this.onScrollListener = onScrollListener;
	}


	/**
	 * 用于用户手指离开MyScrollView的时候获取MyScrollView滚动的Y距离，然后回调给onScroll方法中
	 */
	private Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			int scrollY = MyScrollView2.this.getScrollY();

			//此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
			if(lastScrollY != scrollY){
				lastScrollY = scrollY;
				Message message = handler.obtainMessage();
				message.what=1;

				handler.sendMessageDelayed(message, 5);
			}else {
				Message message = handler.obtainMessage();
				message.what=0;

				handler.sendMessageDelayed(message, 5);
			}

			//此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
			if(msg.what==1){

				onScrollListener.onScroll(1);
			}else {

				onScrollListener.onScroll(0);
			}

		};

	};

	/**
	 * 重写onTouchEvent， 当用户的手在MyScrollView上面的时候，
	 * 直接将MyScrollView滑动的Y方向距离回调给onScroll方法中，当用户抬起手的时候，
	 * MyScrollView可能还在滑动，所以当用户抬起手我们隔5毫秒给handler发送消息，在handler处理
	 * MyScrollView滑动的距离
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		switch(ev.getAction()){
			case MotionEvent.ACTION_UP:
				int scrollY = MyScrollView2.this.getScrollY();

				//此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
				if(lastScrollY != scrollY){
					lastScrollY = scrollY;
					Message message = handler.obtainMessage();
					message.what=1;

					handler.sendMessageDelayed(message, 5);
				}else {
					Message message = handler.obtainMessage();
					message.what=0;

					handler.sendMessageDelayed(message, 5);
				}
				break;
			case MotionEvent.ACTION_DOWN:
				int scrollY1 = MyScrollView2.this.getScrollY();
				//此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
				if(lastScrollY != scrollY1){
					lastScrollY = scrollY1;
					Message message = handler.obtainMessage();
					message.what=1;

					handler.sendMessageDelayed(message, 5);
				}else {
					Message message = handler.obtainMessage();
					message.what=0;

				handler.sendMessageDelayed(message, 5);
				}
				break;
			default:
				break;
		}
		return super.onTouchEvent(ev);
	}


	/**
	 *
	 * 滚动的回调接口
	 *
	 * @author xiaanming
	 *
	 */
	public interface OnScrollListener{
		/**
		 * 回调方法， 返回MyScrollView滑动的Y方向距离
		 * 				、
		 */
		public void onScroll(int a);
	}

}
