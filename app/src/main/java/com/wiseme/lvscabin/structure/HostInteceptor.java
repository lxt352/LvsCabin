package com.wiseme.lvscabin.structure;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class HostInteceptor implements Interceptor {

    public String mBaseUrl;

    public void setBaseUrl(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String baseUrl = mBaseUrl;
        if (!TextUtils.isEmpty(baseUrl)) {
            HttpUrl newHttpUrl = HttpUrl.parse(mBaseUrl);
            HttpUrl httpUrl = request.url().newBuilder().scheme(newHttpUrl.scheme()).host(newHttpUrl.host()).port(newHttpUrl.port()).build();
            request = request.newBuilder().url(httpUrl).build();
        }
        //每次请求使用之后，注意清空之前的baseUrl，避免修改下次请求的url。
        mBaseUrl = null;
        return chain.proceed(request);
    }
}
