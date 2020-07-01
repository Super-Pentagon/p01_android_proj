package com.pentagon.p01_android_proj.login;

import android.util.Log;

import com.pentagon.p01_android_proj.login.UserMgrService;
import com.pentagon.p01_android_proj.model.User;
import com.pentagon.p01_android_proj.util.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginModel {

    public void login(Callback<String> callback, User user) throws Exception {
        // baseUrl() 设置路由地址
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ApiUtils.BUYER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 设置参数
        Call<String> call = retrofit.create(UserMgrService.class)
                .login(user.getUsername(), user.getPassword());

        // 回调
        call.enqueue(callback);

    }

    public void register(Callback<String> callback, User user) throws Exception {
        // baseUrl() 设置路由地址
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ApiUtils.BUYER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 设置参数
        Call<String> call = retrofit.create(UserMgrService.class)
                .register(user.getUsername(), user.getPassword());

        // 回调
        call.enqueue(callback);

    }

    public void reset(Callback<String> callback, User user) throws Exception {
        // baseUrl() 设置路由地址
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ApiUtils.BUYER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 设置参数
        Call<String> call = retrofit.create(UserMgrService.class)
                .reset(user.getUsername(), user.getPassword());

        // 回调
        call.enqueue(callback);

    }
}
