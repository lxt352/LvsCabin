package com.wiseme.lvscabin.module.test;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.wiseme.lvscabin.R;
import com.wiseme.lvscabin.vo.ExpressInfo;

import java.util.List;

/**
 * Created by lxt on 16/12/22.
 * email:lxt352@gmail.com
 */

public class SayEpoxyModle extends EpoxyModelWithHolder<SayEpoxyHolder> {

    private ExpressInfo mInfo;

    @Override
    protected int getDefaultLayout() {
        return R.layout.holder_say_epoxy;
    }

    @Override
    public void bind(SayEpoxyHolder holder) {
        if (mInfo == null)
            return;
        holder.bind(mInfo);
    }

    public void setData(ExpressInfo info) {
        mInfo = info;
    }

    @Override
    public void bind(SayEpoxyHolder holder, List<Object> payloads) {
        super.bind(holder, payloads);
    }

    @Override
    protected SayEpoxyHolder createNewHolder() {
        return new SayEpoxyHolder();
    }
}
