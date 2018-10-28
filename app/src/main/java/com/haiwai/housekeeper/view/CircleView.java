package com.haiwai.housekeeper.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
/**
 * Created by carlos on 2015/10/24.
 */
public class CircleView extends View {
    private Paint mPaintBackCircle;
    private Paint mPaintFrontCircle;
    private Paint mPaintText;
    private float mStrokeWith =10;
    private float mHalfStrokeWith = mStrokeWith/2;
    private float mX = 75 + mHalfStrokeWith;
    private float mY = 75 + mHalfStrokeWith;
    private float mRadius = 75;
    private RectF mRectF;
    private int mProgess;
    public CircleView(Context context) {
        super(context);
        init();
    }
    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        mPaintBackCircle = new Paint();
        mPaintBackCircle.setColor(0xFFD6D6D6);
        mPaintBackCircle.setAntiAlias(true);
        mPaintBackCircle.setStyle(Paint.Style.STROKE);
        mPaintBackCircle.setStrokeWidth(mStrokeWith);
        mPaintFrontCircle = new Paint();
        mPaintFrontCircle.setColor(0xFFE50065);
        mPaintFrontCircle.setAntiAlias(true);
        mPaintFrontCircle.setStyle(Paint.Style.STROKE);
        mPaintFrontCircle.setStrokeWidth(mStrokeWith);
        mPaintText = new Paint();
        mPaintText.setColor(0xFFE50065);
        mPaintText.setAntiAlias(true);
        mPaintText.setTextSize(40);
        mPaintText.setTextAlign(Paint.Align.CENTER);
        mRectF = new RectF(mHalfStrokeWith,mHalfStrokeWith,mRadius*2 + mHalfStrokeWith,mRadius*2 + mHalfStrokeWith);
        mProgess =75;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mX, mY, mRadius, mPaintBackCircle);
        canvas.drawArc(mRectF,0,270,false,mPaintFrontCircle);
        canvas.drawText(mProgess+"%",mX,mY+10,mPaintText);
    }

    public void resetDraw(int progess){
        mProgess = progess;

    }
}