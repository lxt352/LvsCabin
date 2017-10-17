package com.wiseme.lvscabin.uimodule.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import static com.wiseme.lvscabin.utils.LayoutUtils.measureSize;

/**
 * @author lxt <lxt352@gmail.com>
 * @since 2017/9/8
 */

public class PathEffectView extends View {

    private Paint mPaint;

    private Path mPath;

    private Paint mCornerPaint;

    private Path mCornerPath;

    public PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initDashPathEffect();
        initCornerPathEffect();
    }

    private void initCornerPathEffect() {
        mCornerPaint = new Paint();
        mCornerPaint.setAntiAlias(true);
        mCornerPaint.setStyle(Style.STROKE);
        mCornerPaint.setStrokeWidth(2);
        mCornerPath = new Path();
        mCornerPath.moveTo(0, 200);
        mCornerPath.lineTo(300, 500);
        mCornerPath.lineTo(450, 100);
        mCornerPath.lineTo(600, 400);
        mCornerPath.lineTo(800, 150);
        //拐角圆弧effect
        CornerPathEffect effect = new CornerPathEffect(20);
        // 偏离effect
        DiscretePathEffect effect1 = new DiscretePathEffect(10,5);
        // 组合effect
        ComposePathEffect composePathEffect = new ComposePathEffect(effect,effect1);
        mCornerPaint.setPathEffect(composePathEffect);
    }

    private void initDashPathEffect() {

        // Create a straight line
        mPath = new Path();
        mPath.moveTo(32, 32);
        mPath.lineTo(232, 32);

        // Stamp a concave arrow along the line
        PathEffect effect = new PathDashPathEffect(
                makeConvexArrow(24.0f, 14.0f),    // "stamp"
                36.0f,                            // advance, or distance between two stamps
                0.0f,                             // phase, or offset before the first stamp
                PathDashPathEffect.Style.ROTATE); // how to transform each stamp

        // Apply the effect and draw the path
        mPaint = new Paint();
        mPaint.setStrokeWidth(20);
        mPaint.setPathEffect(effect);
    }

    private Path makeConvexArrow(float length, float height) {
        Path p = new Path();
        p.moveTo(0.0f, -height / 2.0f);
        p.lineTo(length - height / 4.0f, -height / 2.0f);
        p.lineTo(length, 0.0f);
        p.lineTo(length - height / 4.0f, height / 2.0f);
        p.lineTo(0.0f, height / 2.0f);
        p.lineTo(0.0f + height / 4.0f, 0.0f);
        p.close();
        return p;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureSize(1000, widthMeasureSpec);
        int height = measureSize(500, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
        canvas.drawPath(mCornerPath, mCornerPaint);
    }
}
