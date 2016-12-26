package com.wiseme.lvscabin.presenter.contract;

import com.wiseme.lvscabin.view.CommonView;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public interface ExpressQueryContract {

    interface View extends CommonView{

        void showProgressIndicator(boolean shown);
    }

    interface Presenter{

        void fetchExpressInfo(String exCompany, String exId);
    }
}
