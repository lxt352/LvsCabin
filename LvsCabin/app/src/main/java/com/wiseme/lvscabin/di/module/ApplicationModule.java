package com.wiseme.lvscabin.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wiseme.lvscabin.BuildConfig;
import com.wiseme.lvscabin.api.ApiConfig;
import com.wiseme.lvscabin.api.ApiService;
import com.wiseme.lvscabin.structure.HostInteceptor;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
    ApiService getBaseDomainApiService(Gson gson) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC );
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.addInterceptor(interceptor);
        httpClient.connectTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS);
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
