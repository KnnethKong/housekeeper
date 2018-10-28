/* 
 * @CustomRatingbar.java - wanghao_158@qq.com - Howard
 */
package com.haiwai.housekeeper.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.haiwai.housekeeper.R;

@SuppressLint({ "DrawAllocation", "ClickableViewAccessibility" })
public class CustomRatingbar extends View {

	private int mWidth, mSize, mMargin = 0;
	private Bitmap mEmptyBmp, mFillBmp;

	private int mMaxCount, mCurCount = 0, mLastCount = 0;

	private float mPressDownX = 0f;
	private int mUnitSize = 0;

	public CustomRatingbar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context, null);
	}

	public CustomRatingbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context, attrs);
	}

	public CustomRatingbar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		if (attrs != null) {
			TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Rating);
			mMargin = a.getDimensionPixelSize(R.styleable.Rating_margin, 10);
			mSize = a.getDimensionPixelSize(R.styleable.Rating_size, 0);
			mMaxCount = a.getInteger(R.styleable.Rating_count, 10);
			int empty = a.getResourceId(R.styleable.Rating_empty, R.mipmap.pro_rating_unselect);
			int fill = a.getResourceId(R.styleable.Rating_fill, R.mipmap.pro_rating_selected);
			mEmptyBmp = BitmapFactory.decodeResource(getResources(), empty);
			mFillBmp = BitmapFactory.decodeResource(getResources(), fill);
			
			mSize = mFillBmp.getWidth() < mSize ? mSize : mFillBmp.getWidth();
			mUnitSize = mSize + mMargin;
			mCurCount = mMaxCount;

			a.recycle();
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		mWidth = mSize * mMaxCount + (mMaxCount - 1) * mMargin;
		setMeasuredDimension(mWidth, mSize);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub

		float halfMargin = mMargin / 2;
		float unit = mSize + halfMargin;
		
		for (int index = 0; index < mMaxCount; index++) {
			Rect scaleRec = new Rect();
			scaleRec.top = 0;
			scaleRec.left = (int) (halfMargin + index * unit);
			scaleRec.right = (int) (halfMargin + index * unit + mSize);
			scaleRec.bottom = (int) mSize;
			
			canvas.drawBitmap(index < mCurCount ? mFillBmp : mEmptyBmp,
					null, scaleRec, null);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			mPressDownX = event.getX();
			getParent().requestDisallowInterceptTouchEvent(true);
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			mLastCount = mCurCount;
			
			mCurCount = (int) (mPressDownX / mUnitSize) + 1;
			
			//如果允许零星，打开下一行，注释掉下下行
			mCurCount = mCurCount == 1 ? Math.round(mPressDownX / mUnitSize) : mCurCount;
//			mCurCount = mCurCount == 0 ? 1 : mCurCount;
			mCurCount = mCurCount > mMaxCount ? mMaxCount : mCurCount;
			break;
		}
		
		refreshView();

		return true;
	}

	/**
	 * 刷新界面
	 */
	private void refreshView() {
		// TODO Auto-generated method stub
		int minCount = Math.min(mLastCount, mCurCount);
		int subCount = Math.abs(mLastCount - mCurCount);
		
		//没有变化，无需刷新
		if(subCount == 0)
			return;
		
		float halfMargin = mMargin / 2;
		float unit = mSize + halfMargin;

		//虽有改变，但只局部刷新即可
		Rect dirtyRec = new Rect();
		dirtyRec.top = 0;
		dirtyRec.bottom = (int) mSize;
		dirtyRec.left = (int) (halfMargin + minCount * unit);
		dirtyRec.right = (int) (halfMargin + (minCount + subCount) * unit);
		
		invalidate(dirtyRec);
	}

	public int getStar() {
		return mCurCount;
	}
}
