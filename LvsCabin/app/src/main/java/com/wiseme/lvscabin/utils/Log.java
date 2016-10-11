package com.wiseme.lvscabin.utils;

/**
 * Created by lvsin
 * lxt352@gmail.com
 */

public class Log {

    public static final String TAG = "TAG";

    /**
     * @param message      message will be printed
     * @param whetherInfo printed info when whetherInfo is true or printed red texts when not info
     */
    public static void I(String message, boolean whetherInfo) {
        if (whetherInfo) {
            android.util.Log.i(TAG, message);
        } else {
            android.util.Log.e(TAG, message);
        }
    }
}
