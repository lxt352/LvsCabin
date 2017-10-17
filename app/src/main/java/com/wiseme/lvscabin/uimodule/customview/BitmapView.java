package com.wiseme.lvscabin.uimodule.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wiseme.lvscabin.R;

import static com.wiseme.lvscabin.utils.LayoutUtils.measureSize;

/**
 * @author lxt <lxt352@gmail.com>
 * @since 2017/10/17
 */

public class BitmapView extends View {

    private Paint paint;

    private int desiredSize = 500;

    int width, height, radius;

    public BitmapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_pok);
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        BitmapShader bitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.REPEAT);
        paint.setShader(bitmapShader);
        radius = Math.min(width, height) / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureSize(radius * 2, widthMeasureSpec);
        int height = measureSize(radius * 2, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(width / 2, height / 2, radius, paint);
    }
}
