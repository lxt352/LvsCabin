package com.wiseme.lvscabin.module.test;

import com.airbnb.epoxy.EpoxyAdapter;
import com.wiseme.lvscabin.vo.ExpressInfo;

import java.util.List;

/**
 * Created by lxt on 16/12/22.
 * email:lxt352@gmail.com
 */

public class SayEpoxyAdapter<T> extends EpoxyAdapter {

    List<T> mDatas;

    private final SayEpoxyModle mSayModle = new SayEpoxyModle();
    private final SayEpoxyModle mSayModle1 = new SayEpoxyModle();

    public SayEpoxyAdapter(List<T> data) {
        super();
        mDatas = data;


        mSayModle.setData((ExpressInfo) data.get(0));
        mSayModle1.setData((ExpressInfo) data.get(1));

        addModels(mSayModle, mSayModle1);

    }

    public void notifyDataChanged(List<T> t) {
        mSayModle.setData((ExpressInfo) t.get(0));
    }

}
