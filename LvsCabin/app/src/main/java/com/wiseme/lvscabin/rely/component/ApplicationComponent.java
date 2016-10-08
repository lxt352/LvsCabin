package com.wiseme.lvscabin.rely.component;

import com.wiseme.lvscabin.api.ApiService;
import com.wiseme.lvscabin.data.contract.ExpressQueryDataSource;
import com.wiseme.lvscabin.rely.module.ApplicationModule;

import dagger.Component;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    ApiService getApiService();

//    ExpressQueryDataSource getExpressQueryRepository();


}
