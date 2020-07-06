package com.pentagon.p01_android_proj.service;

import com.pentagon.p01_android_proj.util.LogHelper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static final String BASE_URL = "http://personal2.shenzhuo.vip:18019/";

//    private static OkHttpClient client =
//            new OkHttpClient.Builder().addInterceptor(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    LogHelper.log(chain.request().url());
//                    return chain.proceed(chain.request());
//                }
//            }).build();

    private static Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
            .build();

    private ServiceGenerator() {
    }

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
