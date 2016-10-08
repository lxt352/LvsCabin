package com.wiseme.lvscabin.rely.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wiseme.lvscabin.api.ApiConfig;
import com.wiseme.lvscabin.api.ApiService;
import com.wiseme.lvscabin.api.HostInteceptor;
import com.wiseme.lvscabin.data.ExpressQueryRepository;
import com.wiseme.lvscabin.data.contract.ExpressQueryDataSource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import static retrofit2.converter.gson.GsonConverterFactory.create;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */
@Module
public class ApplicationModule {

    private Context mContext;

    private HostInteceptor mInterceptor;

    public ApplicationModule(Context context, HostInteceptor interceptor) {
        mContext = context;
        mInterceptor = interceptor;
    }

//    @Provides
//    static ExpressQueryDataSource provideExpressQueryDataSource(ApiService apiService){
//        return new ExpressQueryRepository(apiService);
//    }

    @Provides
    public ApiService getBaseDomainApiService(Gson gson) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(6, TimeUnit.SECONDS).readTimeout(6, TimeUnit.SECONDS);
        return new Retrofit.Builder()
                .baseUrl(ApiConfig.BASE_URL)
                .addConverterFactory(create(gson))
                .client(httpClient.build())
                .build()
                .create(ApiService.class);
    }

    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .create();
    }
}
