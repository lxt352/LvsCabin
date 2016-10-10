package com.wiseme.lvscabin.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by lvsin
 * lxt352@gmail.com
 */

public class ToastUtils {

    public static void toastShortly(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    public static void toastLongly(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
