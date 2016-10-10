package com.wiseme.lvscabin.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyachi.stepview.VerticalStepView;
import com.dodola.listview.extlib.ListViewExt;
import com.wiseme.lvscabin.R;
import com.wiseme.lvscabin.api.ApiService;
import com.wiseme.lvscabin.api.response.ExpressInfoResponse;
import com.wiseme.lvscabin.data.ExpressQueryRepository;
import com.wiseme.lvscabin.presenter.ExpressQueryPresenter;
import com.wiseme.lvscabin.presenter.contract.ExpressQueryC;
import com.wiseme.lvscabin.structure.Error;
import com.wiseme.lvscabin.utils.ValueUtils;
import com.wiseme.lvscabin.view.BaseFragment;
import com.wiseme.lvscabin.vo.ExpressInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class ExpressQueryFragment extends BaseFragment implements ExpressQueryC.View {

    @BindView(R.id.step_view)
    VerticalStepView mStepView;

    private ExpressQueryPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiService apiService = getAppComponent().getApiService();
        mPresenter = new ExpressQueryPresenter(this, new ExpressQueryRepository(apiService));
        mPresenter.fetchExpressInfo("yuantong", "600230538853");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_express_query, container, false);
        ButterKnife.bind(this, view);
        bindToolbar(view);
        setToolbarTitle("express", Gravity.CENTER);
        setToolbarColor(R.color.teal_normal);
        setToolbarTitleColor(R.color.white);
        return view;
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
        List<String> infos = new ArrayList<>();
        for (ExpressInfo expInfo : expInfos) {
            infos.add(expInfo.getContent());
        }
        Collections.reverse(infos);
        mStepView.setStepsViewIndicatorComplectingPosition(expInfos.size())//设置完成的步数
                .reverseDraw(true)//default is true  倒叙否
                .setStepViewTexts(infos)//总步骤
                .setLinePaddingProportion(0.85f)//设置indicator线与线间距的比例系数
                .setStepsViewIndicatorCompletedLineColor(ValueUtils.getColor(R.color.white))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ValueUtils.getColor(R.color.white))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ValueUtils.getColor(R.color.white))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ValueUtils.getColor(R.color.white))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ValueUtils.getDrawable(R.drawable.complted))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ValueUtils.getDrawable(R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ValueUtils.getDrawable(R.drawable.default_icon));//设置StepsViewIndicator AttentionIcon
    }

    @Override
    public void showError(Error error) {

    }
}
