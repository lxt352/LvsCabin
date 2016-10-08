package com.wiseme.lvscabin.api;

import com.wiseme.lvscabin.api.response.ExpressInfoResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public interface ApiService {

    /**
     * 获取快递信息
     */
    @FormUrlEncoded
    @POST(ApiConfig.PATH_EXPRESS_100)
    Call<ExpressInfoResponse> fetchExpressInfo(@Field("type") String exCompany, @Field("postid") String exId);

}
