package com.wiseme.lvscabin.uimodule.customview.loading;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.wiseme.lvscabin.listener.OnInvalidatedListener;

/**
 * @author lxt <lxt352@gmail.com>
 * @since 2017/9/21
 */

public class RotateCircle extends BaseLoadingObject {

    private Circle eyeBound;
    private Circle[] sharingans;
    private int numberOfSharingan =3 ;
    private float rotate;
    private float scale;
    private float eyeBoundRadiusScale = 3;

    private Circle eye;

    private PointF mPointF;

    private OnInvalidatedListener mOnInvalidatedListener;

    private float eyeBoundRadius;

    private int mColor;

    private int mWidth;

    private int mHeight;

    public int mDesiredWidth;

    public int mDesiredHeigth;

    public RotateCircle() {
    }

    public void init() {
        mDesiredWidth = mDesiredHeigth = 150;
        float radius = Math.min(mWidth, mHeight) / 2f;
        eyeBoundRadius = radius / 1.5f;
        eye = new Circle();
        eye.setCenter(mPointF.x, mPointF.y);
        eye.setColor(mColor);
        eye.setRadius(radius / 4);

        eyeBound = new Circle();
        eyeBound.setCenter(mPointF.x, mPointF.y);
        eyeBound.setColor(mColor);
        eyeBound.setRadius(eyeBoundRadius);
        eyeBound.setStyle(Paint.Style.STROKE);
        eyeBound.setWidth(radius / 20f);

        sharingans = new Circle[numberOfSharingan];
        for (int i = 0; i < numberOfSharingan; i++) {
            sharingans[i] = new Circle();
            sharingans[i].setCenter(mPointF.x, mPointF.y - eyeBoundRadius);
            sharingans[i].setColor(mColor);
            sharingans[i].setRadius(radius / 6);
        }
    }

    public void onDraw(Canvas canvas) {
        canvas.save();
        canvas.scale(scale, scale, mPointF.x, mPointF.y);
        canvas.rotate(rotate, mPointF.x, mPointF.y);
        eye.draw(canvas);
        eyeBound.draw(canvas);
        for (int i = 0; i < numberOfSharingan; i++) {
            canvas.save();
            canvas.rotate(i * 120, mPointF.x, mPointF.y);
            sharingans[i].draw(canvas);
            canvas.restore();
        }
        canvas.restore();
    }

    public void measureSize(int width, int height) {
        mWidth = width;
        mHeight = height;
        mPointF = new PointF(width / 2f, height / 2f);
    }

    public void setColor(int color) {
        mColor = color;
    }

    public void excuteAnimator() {
        ValueAnimator rotateAnimator = ValueAnimator.ofFloat(0, 360);
        rotateAnimator.setDuration(1500);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                rotate = (float) animation.getAnimatedValue();
                if (mOnInvalidatedListener != null) {
                    mOnInvalidatedListener.onDraw();
                }
            }
        });

        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(1f, 0.8f, 1f);
        scaleAnimator.setDuration(1000);
        scaleAnimator.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scale = (float) animation.getAnimatedValue();
                if (mOnInvalidatedListener != null) {
                    mOnInvalidatedListener.onDraw();
                }
            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(rotateAnimator).with(scaleAnimator);
        animatorSet.start();
    }

    public void setOnInvalidatedListener(OnInvalidatedListener listener) {
        mOnInvalidatedListener = listener;
    }
}
