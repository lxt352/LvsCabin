package com.wiseme.lvscabin.module.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wiseme.lvscabin.R;
import com.wiseme.lvscabin.api.response.ExpressInfoResponse;
import com.wiseme.lvscabin.di.component.DaggerApplicationComponent;
import com.wiseme.lvscabin.di.component.DaggerPresenterComponent;
import com.wiseme.lvscabin.di.module.PresenterModule;
import com.wiseme.lvscabin.presenter.ExpressQueryPresenter;
import com.wiseme.lvscabin.presenter.contract.ExpressQueryContract;
import com.wiseme.lvscabin.structure.Error;
import com.wiseme.lvscabin.view.BaseFragment;
import com.wiseme.lvscabin.vo.ExpressInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class VideoFragment extends BaseFragment implements ExpressQueryContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private View mView;

    private List<ExpressInfo> mDatas;

    SayEpoxyAdapter<ExpressInfo> mAdapter;

    ExpressQueryPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = DaggerPresenterComponent.builder()
                .applicationComponent(getAppComponent())
                .presenterModule(new PresenterModule(this))
                .build()
                .getExpressQueryPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_video, container, false);
            ButterKnife.bind(this, mView);
            initRecyclerView();
        }
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        String[] companies = getResources().getStringArray(R.array.array_express_companies);
        for (String company : companies) {
            mPresenter.fetchExpressInfo(company, "70699008348094");
        }
    }

    private void initRecyclerView() {

    }

    @Override
    public void showProgressIndicator(boolean shown) {

    }

    @Override
    public void loadDone(Object o) {
        if (o == null)
            return;
        ExpressInfoResponse response = (ExpressInfoResponse) o;
        List<ExpressInfo> expInfos = response.getExpInfos();
        if (expInfos.isEmpty())
            return;

        mDatas = new ArrayList<>(expInfos);
        mAdapter = new SayEpoxyAdapter<>(mDatas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showError(Error error) {

    }
}
