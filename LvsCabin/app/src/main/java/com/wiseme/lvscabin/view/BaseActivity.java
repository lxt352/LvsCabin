package com.wiseme.lvscabin.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import com.wiseme.lvscabin.R;
import com.wiseme.lvscabin.utils.ValueUtils;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private TextView mTitleView;

    private int mTitleColor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleView = new TextView(this);
    }

    /**
     * 绑定toolbar
     */
    public void bindToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar == null)
            return;
        mTitleView = new TextView(this);
        mToolbar.addView(mTitleView);
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
}
