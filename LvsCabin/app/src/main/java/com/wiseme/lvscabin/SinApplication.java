package com.wiseme.lvscabin;

import android.app.Application;
import android.content.Context;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class SinApplication extends Application {

    private static SinApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static SinApplication getApplication() {
        return mApplication;
    }

    public static Context getGlobalContext() {
        return mApplication.getApplicationContext();
    }
}
