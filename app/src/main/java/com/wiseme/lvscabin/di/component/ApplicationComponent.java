package com.wiseme.lvscabin.di.component;

import com.wiseme.lvscabin.api.ApiService;
import com.wiseme.lvscabin.data.ExpressQueryRepository;
import com.wiseme.lvscabin.di.module.ApplicationModule;

import dagger.Component;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    ApiService getApiService();

    ExpressQueryRepository getExpressQueryRepository();

}
