package com.wiseme.lvscabin.uimodule.customview;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

/**
 * @author lxt <lxt352@gmail.com>
 * @since 2017/9/7
 */

public class PathMeasureCircleTwo extends View {

    private Paint mPaint;

    private Path mPath;

    private Path mDrawPath;

    private PathMeasure mPathMeasure;

    private float mAnimatorValue;

    private float mLength;

    private int mDesiredSize = 200;

    public PathMeasureCircleTwo(Context context) {
        super(context);
        init();
    }

    public PathMeasureCircleTwo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathMeasureCircleTwo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initPaintAndPath();
        initAnimator();
    }

    private void initPaintAndPath() {
        mPaint = new Paint();
        mDrawPath = new Path();
        mPath = new Path();
        mPathMeasure = new PathMeasure();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Style.STROKE);
        int strokeWidth = 5;
        mPaint.setStrokeWidth(strokeWidth);
        float radius = mDesiredSize / 2;
        mPath.addCircle(radius, radius, radius - strokeWidth, Direction.CW);
        mPathMeasure.setPath(mPath, true);
        mLength = mPathMeasure.getLength();
    }

    private void initAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1)
                .setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatorValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(mDesiredSize, widthSize);
        } else {
            //Be whatever you want
            width = mDesiredSize;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(mDesiredSize, heightSize);
        } else {
            //Be whatever you want
            height = mDesiredSize;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDrawPath.reset();
        mDrawPath.lineTo(0, 0);
        float endLength = mLength * mAnimatorValue;
        float startLength = (float) (endLength - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * mLength));
        mPathMeasure.getSegment(startLength, endLength, mDrawPath, true);
        canvas.drawPath(mDrawPath, mPaint);
    }
}
