package com.wiseme.lvscabin.data;

import android.util.Log;

import com.wiseme.lvscabin.api.ApiConfig;
import com.wiseme.lvscabin.structure.Error;
import com.wiseme.lvscabin.structure.Transaction;
import com.wiseme.lvscabin.api.ApiService;
import com.wiseme.lvscabin.api.response.ExpressInfoResponse;
import com.wiseme.lvscabin.data.contract.ExpressQueryDataSource;
import com.wiseme.lvscabin.utils.NetworkUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class ExpressQueryRepository implements ExpressQueryDataSource {

    private ApiService mApiService;

    public ExpressQueryRepository(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void fetchExpressInfo(String exCompany, String exId, final Transaction<ExpressInfoResponse> callback) {
        if (!NetworkUtils.isConnected()) {
            callback.onFail(new Error(Error.ERROR_CODE_UNCONNECTED));
            return;
        }
        Call<ExpressInfoResponse> call = mApiService.fetchExpressInfo(exCompany, exId);
        call.enqueue(new Callback<ExpressInfoResponse>() {
            @Override
            public void onResponse(Call<ExpressInfoResponse> call, Response<ExpressInfoResponse> response) {
                callback.onSuccess(response.body());
                Log.d("TAG","response = " + response.body().toString());
            }

            @Override
            public void onFailure(Call<ExpressInfoResponse> call, Throwable t) {
                callback.onFail(Error.adapt(t));
                Log.d("TAG"," fail " + t.toString());
            }
        });
    }
}
