package com.wiseme.lvscabin.uimodule.customview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

/**
 * @author lxt <lxt352@gmail.com>
 * @since 2017/9/11
 */

public class ScanCircle extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;

    private Canvas mCanvas;

    private ObjectAnimator mBCRotateAnimator1;

    private ObjectAnimator mBCRotateAnimator2;

    private ObjectAnimator mBCRotateAnimator3;

    private AnimatorSet mBCRotateAnimatorSet;

    public ScanCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initAnimator();
    }

    private void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        mCanvas = mHolder.lockCanvas();
        new Thread(this).start();
    }

    private void initAnimator() {
        if (null == mBCRotateAnimator1) {
            mBCRotateAnimator1 = ObjectAnimator.ofFloat(this, "bCRotate", 0f, 360f);
            mBCRotateAnimator1.setInterpolator(new LinearInterpolator());
            mBCRotateAnimator1.setDuration(866);
        }
        if (null == mBCRotateAnimator2) {
            mBCRotateAnimator2 = ObjectAnimator.ofFloat(this, "bCRotate", 360f, 720f);
            mBCRotateAnimator2.setInterpolator(new LinearInterpolator());
            mBCRotateAnimator2.setDuration(334);
        }
        if (null == mBCRotateAnimator3) {
            mBCRotateAnimator3 = ObjectAnimator.ofFloat(this, "bCRotate", 720f, 360f);
            mBCRotateAnimator3.setInterpolator(new LinearInterpolator());
            mBCRotateAnimator3.setDuration(12000);
            mBCRotateAnimator3.setRepeatCount(ValueAnimator.INFINITE);
        }
        if (null == mBCRotateAnimatorSet) {
            mBCRotateAnimatorSet = new AnimatorSet();
            ArrayList<Animator> animatorArrayList = new ArrayList<Animator>();
            animatorArrayList.add(mBCRotateAnimator1);
            animatorArrayList.add(mBCRotateAnimator2);
            animatorArrayList.add(mBCRotateAnimator3);
            mBCRotateAnimatorSet.playSequentially(animatorArrayList);
            mBCRotateAnimatorSet.setStartDelay(800);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mHolder.unlockCanvasAndPost(mCanvas);
    }

    @Override
    public void run() {
    }
}
