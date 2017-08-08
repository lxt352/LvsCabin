package com.wiseme.lvscabin.di.component;

import com.wiseme.lvscabin.di.module.PresenterModule;
import com.wiseme.lvscabin.presenter.ExpressQueryPresenter;
import com.wiseme.lvscabin.presenter.contract.ExpressQueryContract;

import dagger.Component;

/**
 * @author lxt <lxt352@gmail.com>
 * @day nice at 16/12/26
 */
@Component(dependencies = ApplicationComponent.class, modules = {PresenterModule.class})
public interface PresenterComponent {

    ExpressQueryPresenter getExpressQueryPresenter();
}
