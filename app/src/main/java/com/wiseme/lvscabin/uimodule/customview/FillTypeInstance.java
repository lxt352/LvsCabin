package com.wiseme.lvscabin.uimodule.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import static com.wiseme.lvscabin.utils.LayoutUtils.measureSize;

/**
 * @author lxt <lxt352@gmail.com>
 * @since 2017/10/17
 */

public class FillTypeInstance extends View {

    private int desiredSize = 1000;

    private Path path;

    private Paint paint;

    public FillTypeInstance(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        path = new Path();
        paint = new Paint();
        paint.setAntiAlias(true);
        path.addCircle(100, 100, 80, Direction.CW);
        path.addCircle(200, 100, 80, Direction.CW);
        path.addCircle(400, 210, 80, Direction.CCW);
        path.addRect(new RectF(300, 20, 500, 400), Direction.CW);
        path.setFillType(FillType.EVEN_ODD);
//        paint.setStyle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureSize(desiredSize, widthMeasureSpec);
        int height = measureSize(desiredSize, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }
}
