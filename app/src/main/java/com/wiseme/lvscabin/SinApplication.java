package com.wiseme.lvscabin;

import android.app.Application;
import android.content.Context;

import com.wiseme.lvscabin.kotlin.KotlineRunner;
import com.wiseme.lvscabin.kotlin.view.OutlineProvider;
import com.wiseme.lvscabin.structure.HostInteceptor;
import com.wiseme.lvscabin.di.component.ApplicationComponent;
import com.wiseme.lvscabin.di.component.DaggerApplicationComponent;
import com.wiseme.lvscabin.di.module.ApplicationModule;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class SinApplication extends Application {

    private static SinApplication mApplication;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, new HostInteceptor()))
                .build();
        KotlineRunner runner = new KotlineRunner();
        runner.main(null);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static SinApplication getApplication() {
        return mApplication;
    }

    public static Context getGlobalContext() {
        return mApplication.getApplicationContext();
    }
}
