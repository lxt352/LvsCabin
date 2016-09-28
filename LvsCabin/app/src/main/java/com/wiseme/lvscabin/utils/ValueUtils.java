package com.wiseme.lvscabin.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.wiseme.lvscabin.SinApplication;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class ValueUtils {

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static int getColor(int colorId) {
        return CompatUtils.IS_ANDROID_M_LATER ? SinApplication.getGlobalContext().getColor(colorId)
                : SinApplication.getGlobalContext().getResources().getColor(colorId);
    }
}
