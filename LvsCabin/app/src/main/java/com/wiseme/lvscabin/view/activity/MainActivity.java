package com.wiseme.lvscabin.view.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.Gravity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.wiseme.lvscabin.R;
import com.wiseme.lvscabin.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottombar)
    BottomBar mBottomBar;

    private OnTabSelectListener mOnTabSelectListener = new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {
            switch (tabId) {
                case R.id.tab_music:
                    setToolbarColor(R.color.brown);
                    break;
                case R.id.tab_video:
                    setToolbarColor(R.color.blue_primary);
                    break;
                case R.id.tab_news:
                    setToolbarColor(R.color.teal_normal);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mBottomBar.setOnTabSelectListener(mOnTabSelectListener);

        bindToolbar();
        setToolbarColor(R.color.brown);
        setToolbarTitleColor(R.color.white);
        setToolbarTitle("Music", Gravity.CENTER);
    }
}
