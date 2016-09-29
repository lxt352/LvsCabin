package com.wiseme.lvscabin.view;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wiseme.lvscabin.R;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public abstract class BaseFragment extends Fragment {

    public void bindToolbar(View view) {
        getBaseActivity().setToolbar((Toolbar)view.findViewById(R.id.toolbar));
        getBaseActivity().bindToolbar();
    }

    public void setToolbarColor(int colorId) {
        getBaseActivity().setToolbarColor(colorId);
    }

    public void setToolbarDrawable(Drawable drawable) {
        getBaseActivity().setToolbarDrawable(drawable);
    }

    public void setToolbarTitle(String title, int gravity) {
        getBaseActivity().setToolbarTitle(title, gravity);
    }

    public void setToolbarTitleColor(int colorId) {
        getBaseActivity().setToolbarColor(colorId);
    }

    private BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }
}
