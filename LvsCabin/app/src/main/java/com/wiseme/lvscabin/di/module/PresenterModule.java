package com.wiseme.lvscabin.di.module;

import com.wiseme.lvscabin.presenter.contract.ExpressQueryContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author lxt <lxt352@gmail.com>
 * @since nice on 16/12/26
 */
@Module
public class PresenterModule {

    private ExpressQueryContract.View mEQCView;

    public PresenterModule(ExpressQueryContract.View view) {
        mEQCView = view;
    }

    @Provides
    ExpressQueryContract.View providesExpressQueryView() {
        return mEQCView;
    }

}
