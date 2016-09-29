package com.wiseme.lvscabin.api.response;

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

    List<ExpressInfo> mExpInfos;

}
