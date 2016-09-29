package com.wiseme.lvscabin.data.contract;

import com.wiseme.lvscabin.structure.Transaction;
import com.wiseme.lvscabin.api.response.ExpressInfoResponse;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public interface ExpressQueryContract {

    void fetchExpressInfo(String exCompany, long exId, Transaction<ExpressInfoResponse> callback);
}
