package com.wiseme.lvscabin.utils;

import android.view.View.MeasureSpec;

/**
 * @author lxt <lxt352@gmail.com>
 * @since 2017/10/16
 */

public class LayoutUtils {

    private static final String TAG = LayoutUtils.class.getSimpleName();

    public static int measureSize(int size, int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int measureSize = MeasureSpec.getSize(measureSpec);
        android.util.Log.d(TAG, "Measure mode = " + mode + " size = " + measureSize);
        int length;
        if (mode == MeasureSpec.EXACTLY) {
            length = measureSize;
        } else if (mode == MeasureSpec.AT_MOST) {
            length = Math.min(size, measureSize);
        } else {
            length = size;
        }
        return length;
    }

}
