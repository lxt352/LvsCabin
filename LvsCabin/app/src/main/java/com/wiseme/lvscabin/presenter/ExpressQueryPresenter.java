package com.wiseme.lvscabin.presenter;

import com.wiseme.lvscabin.api.response.ExpressInfoResponse;
import com.wiseme.lvscabin.data.ExpressQueryRepository;
import com.wiseme.lvscabin.presenter.contract.ExpressQueryC;
import com.wiseme.lvscabin.structure.Error;
import com.wiseme.lvscabin.structure.Transaction;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class ExpressQueryPresenter implements ExpressQueryC.Presenter {

    private ExpressQueryC.View mView;

    private ExpressQueryRepository mRepository;

    public ExpressQueryPresenter(ExpressQueryC.View view,ExpressQueryRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void fetchExpressInfo(String exCompany, String exId) {
        mView.showProgressIndicator(true);
        mRepository.fetchExpressInfo(exCompany, exId, new Transaction<ExpressInfoResponse>() {
            @Override
            public void onSuccess(ExpressInfoResponse expressInfoResponse) {
                mView.showProgressIndicator(false);
                mView.loadDone(expressInfoResponse);
            }

            @Override
            public void onFail(Error error) {
                mView.showProgressIndicator(false);
                mView.showError(error);
            }
        });
    }
}
