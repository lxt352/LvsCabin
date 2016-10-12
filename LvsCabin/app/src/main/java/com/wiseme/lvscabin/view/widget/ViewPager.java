package com.wiseme.lvscabin.view.widget;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.wiseme.lvscabin.view.BaseActivity;
import com.wiseme.lvscabin.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class ViewPager extends android.support.v4.view.ViewPager {

    private List<BaseFragment> mFragmentList = new ArrayList<>();

    public ViewPager(Context context) {
        super(context);
    }

    public ViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }

    public void bindFragments(BaseActivity activity, List<BaseFragment> fragments) {
        if (activity == null || fragments == null) {
            Log.e(getClass().getSimpleName(), "the param came in is null");
            return;
        }
        mFragmentList = fragments;
        FragmentManager mFragmentManager = activity.getSupportFragmentManager();
        if (mFragmentManager == null) {
            Log.e(getClass().getSimpleName(), "fragmentManager is null");
            return;
        }
        setAdapter(new PPAdapter(mFragmentManager));
    }

    public class PPAdapter extends FragmentStatePagerAdapter {

        PPAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }
    }
}
