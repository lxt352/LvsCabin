package com.wiseme.lvscabin.presenter;

import com.wiseme.lvscabin.api.response.ExpressInfoResponse;
import com.wiseme.lvscabin.data.ExpressQueryRepository;
import com.wiseme.lvscabin.presenter.contract.ExpressQueryContract;
import com.wiseme.lvscabin.structure.Error;
import com.wiseme.lvscabin.structure.Transaction;

import javax.inject.Inject;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class ExpressQueryPresenter implements ExpressQueryContract.Presenter {

    private ExpressQueryContract.View mView;

    private ExpressQueryRepository mRepository;

    @Inject
    public ExpressQueryPresenter(ExpressQueryContract.View view, ExpressQueryRepository repository) {
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
