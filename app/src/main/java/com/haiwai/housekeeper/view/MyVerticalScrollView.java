package com.haiwai.housekeeper.view;

/**
 * �����϶��ص����Իص���ScrollView��
 */
import java.util.Date;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

public class MyVerticalScrollView extends ScrollView
{
	// ���Իص���������������־��
	private static final int JIA = 0;
	private static final int JIAN = 1;
	private static final int LAST = 2;
	// �������ı߽�״̬
	private static final int AT_TOP_BODER = 0;
	private static final int AT_BUTTOM_BODER = 1;
	private static final int NOT_AT_BODER = -1;
	/** �������ı߽�״̬��־�� */
	private int mBoderTag = AT_TOP_BODER;
	/** �ڲ�View */
	private View mInnerView;
	// ��¼TouchMoveʱ�Ⱥ�����
	private float mPreY = 0;
	private float mNowY = 0;
	// ��㴥��
	private float mPreY2 = 0;
	private float mNowY2 = 0;
	/** �϶��ľ��� */
	private float mDragDistance = 0;
	/** mInnerView�϶�������� */
	private int mMoveDistanceY = 0;
	/** �϶��ص�����ÿһ���ƶ����� */
	private int mEachStep = 0;
	/** ��¼����ʱ������λ�� */
	float startPosition = 0;
	/** ���µ�ʱ�� */
	long mStartTime = 0;
	/** �����������ٶ� */
	float mSlidingSpeed = 0;
	/** ���Իص������߳� */
	private SpringbackThread mThread = null;
	/** ���Իص������Ƿ�����У�true �����У�false �������С� */
	private boolean mThreadState = true;
	/** ���Իص�mInnerView��ʼ������ */
	private int mOneY = 0;
	/** ���Իص��������ִ��� */
	int mTime = 20;
	/** ���Իص�����ÿ���ƶ����� */
	private int mUnitHeight = 20;
	/** �϶��ص�����״̬ */
	private boolean mAnimationRunState = false;

	public MyVerticalScrollView(Context context)
	{
		super(context);
	}

	public MyVerticalScrollView(Context context, AttributeSet attrs,
			int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public MyVerticalScrollView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override
	protected void onFinishInflate()
	{
		mInnerView = getChildAt(0);
		super.onFinishInflate();
	}

	@Override
	protected void onScrollChanged(int x, int y, int oldx, int oldy)
	{
		super.onScrollChanged(x, y, oldx, oldy);
		if (y != oldy && mInnerView != null)
		{
			int tag = getAtBoderState();
			if (tag != NOT_AT_BODER)
			{
				inertiaSpringback(tag);
			}
		}
	}

	/**
	 * ���Իص�����
	 * 
	 * @param mBoderTag
	 *            �������ı߽�״̬
	 */
	public void inertiaSpringback(int mBoderTag)
	{
		this.mBoderTag = mBoderTag;
		if (!mThreadState)
		{
			return;
		}
		mThreadState = false;
		mThread = new SpringbackThread();
		mThread.start();
	}

	/**
	 * ���Իص������߳���
	 */
	class SpringbackThread extends Thread
	{
		private boolean mRunState = true;

		public void setRunState(boolean mRunState)
		{
			this.mRunState = mRunState;
		}

		@Override
		public void run()
		{
			for (int i = 0; i < mTime; i++)
			{
				Message msg = Message.obtain();
				if (mBoderTag == AT_TOP_BODER)
				{ // ���º���
					msg.what = JIAN;
				} else
				{
					msg.what = JIA;
				}
				try
				{
					Thread.sleep(15);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
					return;
				}
				if (!mRunState)
				{
					mOneY = 0;
					mThreadState = true;
					return;
				}
				mInertiaSpringbackHandler.sendMessage(msg);
			}

			for (int i = 0; i < mTime; i++)
			{
				Message msg = Message.obtain();
				if (mBoderTag == AT_TOP_BODER)
				{
					msg.what = JIA;
				} else
				{
					msg.what = JIAN;
				}
				try
				{
					Thread.sleep(15);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
					return;
				}
				if (!mRunState)
				{
					mOneY = 0;
					mThreadState = true;
					return;
				}
				mInertiaSpringbackHandler.sendMessage(msg);
			}
			if (mInnerView.getScrollY() != 0)
			{
				Message msg = Message.obtain();
				msg.what = LAST;
				mInertiaSpringbackHandler.sendMessage(msg);
			}
			mThreadState = true;
		}
	}

	private Handler mInertiaSpringbackHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
				case JIA:
					mOneY += mUnitHeight;
					mInnerView.scrollTo(0, mOneY);
					break;
				case JIAN:
					mOneY -= mUnitHeight;
					mInnerView.scrollTo(0, mOneY);
					break;
				case LAST:
					mOneY = 0;
					mInnerView.scrollTo(0, mOneY);
					break;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{

		if (mThread != null && mInnerView.getScrollY() != 0)
		{
			mThread.setRunState(false);
			mAnimationRunState = false;
		}

		return touchEvent(ev);
	}

	public boolean touchEvent(MotionEvent ev)
	{
		int action = ev.getAction();
		switch (action)
		{
			case MotionEvent.ACTION_POINTER_1_DOWN:
				mPreY = ev.getY();
				mPreY2 = ev.getY(1);
				break;
			case MotionEvent.ACTION_POINTER_2_DOWN:
				mPreY2 = ev.getY(1);
				break;
			case MotionEvent.ACTION_DOWN:
				mPreY = ev.getY();
				startPosition = getScrollY();
				mStartTime = new Date().getTime();
				break;
			case MotionEvent.ACTION_POINTER_1_UP:
				mPreY = mPreY2;
				break;
			case MotionEvent.ACTION_MOVE:
				int mBoderTag = getAtBoderState();
				if (mBoderTag != NOT_AT_BODER)
				{
					switch (ev.getPointerCount())
					{
						case 1:
							mNowY = ev.getY();
							mDragDistance = mNowY - mPreY;
							// ����ϵͳ�������ƶ���Touch����
							if ((mBoderTag == AT_TOP_BODER && mDragDistance < 0 && mInnerView
									.getScrollY() == 0)
									|| (mBoderTag == AT_BUTTOM_BODER
											&& mDragDistance > 0 && mInnerView
											.getScrollY() == 0))
							{
								return super.onTouchEvent(ev);
							}
							// ��Ӧ�߽���,��һ����������
							mInnerView
									.scrollBy(0, (int) (-mDragDistance * 0.4));
							mPreY = mNowY;
							break;
						case 2:
							mNowY = ev.getY();
							mDragDistance = mNowY - mPreY;
							mInnerView
									.scrollBy(0, (int) (-mDragDistance * 0.4));
							mPreY = mNowY;
							mNowY2 = ev.getY(1);
							mDragDistance = mNowY2 - mPreY2;
							mInnerView
									.scrollBy(0, (int) (-mDragDistance * 0.4));
							mPreY2 = mNowY2;
							break;
					}
					// ����ϵͳ��������Touch����
					return false;
				}
				break;
			case MotionEvent.ACTION_UP:
				long mTime = new Date().getTime() - mStartTime;
				float journey = Math.abs(getScrollY() - startPosition);
				mSlidingSpeed = journey / (float) mTime * 100;
				initThreadParameters();
				dragSpringbackAnimation();
				break;
		}
		return super.onTouchEvent(ev);
	}

	/**
	 * ��ʼ�����Իص������̲߳�������һ����������
	 */
	void initThreadParameters()
	{
		int s = (int) mSlidingSpeed / 2;
		int x = (int) mSlidingSpeed / 40;
		if (0 == x)
		{
			x = 1;
		}
		mTime = s / x;
		if (0 == mTime)
		{
			mTime = 1;
		}
		mUnitHeight = s / mTime;
	}

	/**
	 * �϶��ص�����
	 */
	private void dragSpringbackAnimation()
	{
		mAnimationRunState = true;
		mMoveDistanceY = mInnerView.getScrollY();
		mEachStep = mMoveDistanceY / 10;
		mDragSpringbackHandler.sendEmptyMessage(0);
	}

	Handler mDragSpringbackHandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			if (mMoveDistanceY != 0 && mAnimationRunState)
			{
				mMoveDistanceY -= mEachStep;
				// mInnerView�����߽�ʱֹͣ
				if ((mEachStep <= 0 && mMoveDistanceY > 0)
						|| (mEachStep >= 0 && mMoveDistanceY < 0))
				{
					mMoveDistanceY = 0;
					mAnimationRunState = false;
				}
				mInnerView.scrollTo(0, mMoveDistanceY);
				this.sendEmptyMessageDelayed(0, 5);
			}
		};
	};

	/**
	 * ��ù������ı߽�״̬
	 * 
	 * @return �������ı߽�״̬
	 */
	public int getAtBoderState()
	{
		int offset = mInnerView.getMeasuredHeight() - getHeight();
		int scrollY = getScrollY();
		if (scrollY == 0)
		{
			return AT_TOP_BODER;
		} else if (scrollY == offset)
		{
			return AT_BUTTOM_BODER;
		}
		return NOT_AT_BODER;
	}
}
