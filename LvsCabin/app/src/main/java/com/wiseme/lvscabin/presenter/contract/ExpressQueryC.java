package com.wiseme.lvscabin.presenter.contract;

import com.wiseme.lvscabin.structure.CommonView;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public interface ExpressQueryC {

    interface View extends CommonView{

        void showProgressIndicator(boolean shown);
    }

    interface Presenter{

        void fetchExpressInfo(String exCompany, long exId);
    }
}
