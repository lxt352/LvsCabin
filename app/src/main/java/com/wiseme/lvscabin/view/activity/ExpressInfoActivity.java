package com.wiseme.lvscabin.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;

import com.wiseme.lvscabin.view.BaseActivity;
import com.wiseme.lvscabin.view.fragment.ExpressFragment;
import com.wiseme.lvscabin.view.fragment.ExpressInfoFragment;

/**
 * Created by lvsin
 * lxt352@gmail.com
 */

public class ExpressInfoActivity extends BaseActivity {

    private String mExId;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mExId = getIntent().getStringExtra(ExpressFragment.ID_EXPRESS);
    }

    @Override
    public Fragment onCreateFragment() {
        return ExpressInfoFragment.newInstance(mExId);
    }
}
