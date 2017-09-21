package com.wiseme.lvscabin.uimodule.customview.loading;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wiseme.lvscabin.R;
import com.wiseme.lvscabin.listener.OnInvalidatedListener;

/**
 * @author lxt <lxt352@gmail.com>
 * @since 2017/9/21
 */

public class LoadingView extends View implements OnInvalidatedListener {

    private RotateCircle mRotateCircle;

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mRotateCircle = new RotateCircle();
        mRotateCircle.setColor(R.color.white);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = resolveSize(mRotateCircle.mDesiredWidth, widthMeasureSpec);
        int height = resolveSize(mRotateCircle.mDesiredHeigth, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mRotateCircle.measureSize(getWidth(), getHeight());
        mRotateCircle.init();
        mRotateCircle.excuteAnimator();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRotateCircle.onDraw(canvas);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mRotateCircle != null)
            mRotateCircle.setOnInvalidatedListener(this);
    }

    @Override
    public void onDraw() {
        invalidate();
    }
}
