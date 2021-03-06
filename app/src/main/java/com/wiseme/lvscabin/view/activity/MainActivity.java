package com.wiseme.lvscabin.view.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.wiseme.lvscabin.R;
import com.wiseme.lvscabin.view.BaseActivity;
import com.wiseme.lvscabin.view.BaseFragment;
import com.wiseme.lvscabin.view.fragment.ExpressFragment;
import com.wiseme.lvscabin.view.fragment.MusicFragment;
import com.wiseme.lvscabin.module.test.VideoFragment;
import com.wiseme.lvscabin.view.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private List<BaseFragment> mFragmentList = new ArrayList<>();

    private OnTabSelectListener mOnTabSelectListener = new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {
            switch (tabId) {
                case R.id.tab_music:
                    mViewPager.setCurrentItem(0, false);
                    break;
                case R.id.tab_video:
                    mViewPager.setCurrentItem(1, false);
                    break;
                case R.id.tab_news:
                    mViewPager.setCurrentItem(2, false);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mFragmentList.add(new MusicFragment());
        mFragmentList.add(new VideoFragment());
        mFragmentList.add(new ExpressFragment());

        mViewPager.bindFragments(this, mFragmentList);
    }

    @Override
    public Fragment onCreateFragment() {
        return null;
    }
}
