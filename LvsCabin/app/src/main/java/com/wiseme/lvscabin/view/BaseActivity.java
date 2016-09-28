package com.wiseme.lvscabin.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wiseme.lvscabin.R;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private TextView mTitleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void bindToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar == null)
            return;
        mTitleView = new TextView(this);
        mToolbar.addView(mTitleView);
    }

    public void setToolbarTitle(String title, int gravity) {
        if (mToolbar == null)
            return;
        if (mTitleView != null)
            mToolbar.removeView(mTitleView);
        mTitleView = new TextView(this);
        mTitleView.setSingleLine();
        mTitleView.setEllipsize(TextUtils.TruncateAt.END);
        mTitleView.setText(TextUtils.isEmpty(title) ? "" : title);
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(
                Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = gravity;
        mToolbar.addView(mTitleView, layoutParams);
    }
}
