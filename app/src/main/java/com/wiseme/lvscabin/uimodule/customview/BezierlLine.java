package com.wiseme.lvscabin.uimodule.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wiseme.lvscabin.R;

import static com.wiseme.lvscabin.utils.LayoutUtils.measureSize;

/**
 * @author lxt <lxt352@gmail.com>
 * @since 2017/10/16
 */

public class BezierlLine extends View {

    private Paint mQuadPaint;

    private Path mQuadPath;

    private int mDesiredSize = 300;

    public BezierlLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mQuadPath = new Path();
        mQuadPaint = new Paint();
        mQuadPaint.setStyle(Style.STROKE);
        mQuadPaint.setStrokeWidth(5);
        mQuadPaint.setAntiAlias(true);
        mQuadPaint.setColor(context.getResources().getColor(R.color.red));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureSize(mDesiredSize, widthMeasureSpec);
        int height = measureSize(mDesiredSize, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mQuadPath.quadTo(100, 0, 100, 100);
        mQuadPath.rQuadTo(0, 200, 200, 200);
        canvas.drawPath(mQuadPath, mQuadPaint);
    }
}
