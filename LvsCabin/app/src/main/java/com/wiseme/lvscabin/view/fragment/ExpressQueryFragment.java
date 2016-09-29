package com.wiseme.lvscabin.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dodola.listview.extlib.ListViewExt;
import com.wiseme.lvscabin.R;
import com.wiseme.lvscabin.data.ExpressQueryRepository;
import com.wiseme.lvscabin.presenter.ExpressQueryPresenter;
import com.wiseme.lvscabin.view.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class ExpressQueryFragment extends BaseFragment {

    @BindView(R.id.listview)
    ListViewExt mListView;

    private ExpressQueryPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_express_query, container, false);
        ButterKnife.bind(this, view);
        bindToolbar(view);
        setToolbarTitle("express", Gravity.CENTER);
        setToolbarColor(R.color.white);
        return view;
    }


}
