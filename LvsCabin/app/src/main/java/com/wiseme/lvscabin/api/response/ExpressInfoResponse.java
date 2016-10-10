package com.wiseme.lvscabin.api.response;

import com.google.gson.annotations.SerializedName;
import com.wiseme.lvscabin.vo.ExpressInfo;

import java.util.List;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class ExpressInfoResponse extends BaseResponse{

    /**
     * company
     */
    private String com;
    private String condition;
    private String ischeck;
    private String nu;
    private String state;

    @SerializedName("data")
    List<ExpressInfo> mExpInfos;

    public String getCom() {
        return com;
    }

    public String getCondition() {
        return condition;
    }

    public String getIscheck() {
        return ischeck;
    }

    public String getNu() {
        return nu;
    }

    public String getState() {
        return state;
    }

    public List<ExpressInfo> getExpInfos() {
        return mExpInfos;
    }

    @Override
    public String toString() {
        return "ExpressInfoResponse{" +
                "com='" + com + '\'' +
                ", condition='" + condition + '\'' +
                ", ischeck='" + ischeck + '\'' +
                ", nu='" + nu + '\'' +
                ", state='" + state + '\'' +
                ", mExpInfos=" + mExpInfos +
                '}';
    }
}
