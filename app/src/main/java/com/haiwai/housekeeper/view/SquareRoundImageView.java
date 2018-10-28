package com.haiwai.housekeeper.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SquareRoundImageView extends ImageView {

    private static final String TAG = "SquareImageView";

    public SquareRoundImageView(Context context) {
        super(context);
    }

    public SquareRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareRoundImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//        int h = this.getMeasuredHeight();
//        int w = this.getMeasuredWidth();
//
//        setMeasuredDimension(w, w);
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));

        int childWidthSize = getMeasuredWidth();
        //高度和宽度一样
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Path clipPath = new Path();
        float radius = 20.0f;
        float padding = radius / 2;
        int w = this.getWidth();
        int h = this.getHeight();

        clipPath.addRoundRect(new RectF(padding, padding, w - padding, h - padding), radius, radius, Path.Direction.CW);
        canvas.clipPath(clipPath);
        canvas.drawColor(Color.RED);

        super.onDraw(canvas);
    }
}