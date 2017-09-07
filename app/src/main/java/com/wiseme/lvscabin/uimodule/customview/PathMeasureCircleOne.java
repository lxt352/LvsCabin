package com.wiseme.lvscabin.uimodule.customview;

import android.animation.TimeInterpolator;
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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * @author lxt <lxt352@gmail.com>
 * @since 2017/9/7
 */

public class PathMeasureCircleOne extends View {

    private Paint mPaint;

    private Path mPath;

    private Path mDrawPath;

    private PathMeasure mPathMeasure;

    private float mAnimatorValue;

    private float mLength;

    public PathMeasureCircleOne(Context context) {
        super(context);
        init();
    }

    public PathMeasureCircleOne(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathMeasureCircleOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr, Paint mPaint) {
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
        mPaint.setStrokeWidth(5);
        mPath.addCircle(400, 400, 100, Direction.CW);
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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDrawPath.reset();
        mDrawPath.lineTo(0, 0);
        float endLength = mLength * mAnimatorValue;
        mPathMeasure.getSegment(0, endLength, mDrawPath, true);
        canvas.drawPath(mDrawPath, mPaint);
    }
}
