package com.wiseme.lvscabin.utils;

import android.os.Build;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class CompatUtils {

    /**
     * version 23
     */
    public static final boolean IS_ANDROID_M_LATER = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;

    /**
     * version 21
     */
    public static final boolean IS_ANDROID_L_LATER = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;

    /**
     * version 16
     */
    public static final boolean IS_ANDROID_J_LATER = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;

    /**
     * version 18.
     */
    public static final boolean IS_ANDROID_18_LATER = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;

    public static final boolean IS_ANDROID_19_LATER = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

    public static final boolean IS_ANDROID_17_LATER = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;

    public static final boolean IS_ANDROID_16_LATER = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
}
