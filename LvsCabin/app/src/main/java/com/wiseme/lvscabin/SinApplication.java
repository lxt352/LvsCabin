package com.wiseme.lvscabin;

import android.app.Application;
import android.content.Context;

import com.wiseme.lvscabin.api.HostInteceptor;
import com.wiseme.lvscabin.rely.component.ApplicationConponent;
import com.wiseme.lvscabin.rely.component.DaggerApplicationConponent;
import com.wiseme.lvscabin.rely.module.ApplicationModule;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class SinApplication extends Application {

    private static SinApplication mApplication;

    private ApplicationConponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        mApplicationComponent = DaggerApplicationConponent.builder()
                .applicationModule(new ApplicationModule(this,new HostInteceptor()))
                .build();
    }

    public ApplicationConponent getApplicationComponent(){
        return mApplicationComponent;
    }

    public static SinApplication getApplication() {
        return mApplication;
    }

    public static Context getGlobalContext() {
        return mApplication.getApplicationContext();
    }
}
