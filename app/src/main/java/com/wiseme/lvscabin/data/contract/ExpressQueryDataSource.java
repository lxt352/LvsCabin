package com.wiseme.lvscabin.data.contract;

import com.wiseme.lvscabin.structure.Transaction;
import com.wiseme.lvscabin.api.response.ExpressInfoResponse;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public interface ExpressQueryDataSource {

    void fetchExpressInfo(String exCompany, String exId, Transaction<ExpressInfoResponse> callback);
}
