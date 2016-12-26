package com.wiseme.lvscabin.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.wiseme.lvscabin.R;
import com.wiseme.lvscabin.presenter.contract.ExpressQueryContract;
import com.wiseme.lvscabin.structure.Error;
import com.wiseme.lvscabin.view.BaseFragment;
import com.wiseme.lvscabin.view.activity.ExpressInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class ExpressFragment extends BaseFragment implements ExpressQueryContract.View {

    public static final String ID_EXPRESS = "expressId";

    @BindView(R.id.expressId)
    EditText mExId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_express, container, false);
        ButterKnife.bind(this, view);
        bindToolbar(view, false);
        setToolbarTitle("express", Gravity.CENTER);
        setToolbarColor(R.color.teal_normal);
        setToolbarTitleColor(R.color.white);
        return view;
    }

    @OnClick({R.id.clear, R.id.query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear:
                mExId.setText("");
                break;

            case R.id.query:
                String exId = mExId.getText().toString();
                if (TextUtils.isEmpty(exId)) {

                } else {
                    Intent intent = new Intent(getActivity(), ExpressInfoActivity.class);
                    intent.putExtra(ID_EXPRESS, exId);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void showProgressIndicator(boolean shown) {

    }

    @Override
    public void loadDone(Object o) {

    }

    @Override
    public void showError(Error error) {

    }
}
