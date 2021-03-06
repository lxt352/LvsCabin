package com.wiseme.lvscabin.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import com.wiseme.lvscabin.R;
import com.wiseme.lvscabin.SinApplication;
import com.wiseme.lvscabin.di.component.ApplicationComponent;
import com.wiseme.lvscabin.utils.ValueUtils;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static final String TAG_FRAGMENT = "contain_pane";

    private Toolbar mToolbar;

    private TextView mTitleView;

    private int mTitleColor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleView = new TextView(this);
        setContentView(R.layout.include_container);
        insertFragment(savedInstanceState);
    }

    public ApplicationComponent getApplicationComponent() {
        return ((SinApplication) getApplication()).getApplicationComponent();
    }

    public void setToolbar(Toolbar toolbar) {
        mToolbar = toolbar;
    }

    /**
     * 绑定toolbar
     */
    public void bindToolbar(boolean homeAsUp) {
        if (mToolbar == null)
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar == null)
            return;
        mToolbar.setNavigationIcon(R.drawable.ic_vec_navigation_left_white);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(homeAsUp);
        actionBar.setDisplayShowTitleEnabled(false);
    }

    public void setToolbarColor(int colorId) {
        if (mToolbar == null)
            return;
        mToolbar.setBackgroundColor(ValueUtils.getColor(colorId));
    }

    public void setToolbarDrawable(Drawable drawable) {
        if (mToolbar == null)
            return;
        ValueUtils.setBackgroundDrawable(mToolbar, drawable);
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
        ValueUtils.setTextAppearance(mTitleView, R.style.TextAppearance_App_Title);
        if (mTitleColor != 0)
            mTitleView.setTextColor(ValueUtils.getColor(mTitleColor));
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(
                Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = gravity;
        mToolbar.addView(mTitleView, layoutParams);
    }

    public void setToolbarTitleColor(int colorId) {
        if (mToolbar == null)
            return;
        mTitleColor = colorId;
        mTitleView.setTextColor(ValueUtils.getColor(mTitleColor));
        mToolbar.removeView(mTitleView);
        mToolbar.addView(mTitleView);
    }

    private void insertFragment(Bundle savedInstanceState) {
        Fragment fragment;
        if (savedInstanceState == null) {
            fragment = onCreateFragment();
            Bundle arguments = new Bundle();
            Intent intent = getIntent();
            if (intent == null)
                return;
            Uri uri = intent.getData();
            String KEY_URI = "_uri";
            if (uri != null)
                arguments.putParcelable(KEY_URI, uri);
            Bundle extras = intent.getExtras();
            if (extras != null)
                arguments.putAll(extras);
            if (fragment != null) {
                fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root_container, fragment, TAG_FRAGMENT).commit();
            }
        } else {
            fragment = getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
        }
    }

    public abstract Fragment onCreateFragment();
}
