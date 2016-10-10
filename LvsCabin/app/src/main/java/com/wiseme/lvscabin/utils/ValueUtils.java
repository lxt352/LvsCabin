package com.wiseme.lvscabin.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wiseme.lvscabin.SinApplication;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class ValueUtils {

    private static Context getContext() {
        return SinApplication.getGlobalContext();
    }

    public static int getColor(int resId) {
        return ContextCompat.getColor(getContext(), resId);
    }

    public static Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(getContext(), resId);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void setTextAppearance(TextView textView, int resId) {
        if (CompatUtils.IS_ANDROID_M_LATER) {
            textView.setTextAppearance(resId);
            return;
        }
        textView.setTextAppearance(getContext(), resId);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setBackgroundDrawable(ViewGroup viewGroup, Drawable drawable) {
        if (CompatUtils.IS_ANDROID_16_LATER) {
            viewGroup.setBackground(drawable);
            return;
        }
        viewGroup.setBackgroundDrawable(drawable);
    }
}
