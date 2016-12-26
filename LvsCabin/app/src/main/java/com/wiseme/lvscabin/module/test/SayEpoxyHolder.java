package com.wiseme.lvscabin.module.test;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAdapter;
import com.airbnb.epoxy.EpoxyHolder;
import com.wiseme.lvscabin.R;
import com.wiseme.lvscabin.vo.ExpressInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lxt on 16/12/22.
 * email:lxt352@gmail.com
 */

public class SayEpoxyHolder extends EpoxyHolder {

    @BindView(R.id.image_view)
    ImageView mImageView;

    @BindView(R.id.text_view)
    TextView mTextView;

    @Override
    protected void bindView(View itemView) {
        ButterKnife.bind(this, itemView);
    }

    void bind(ExpressInfo info) {
        mTextView.setText(info.getContent());
    }
}
