package com.pentagon.p01_android_proj.login;

import com.pentagon.p01_android_proj.login.forget.ForgetResponse;
import com.pentagon.p01_android_proj.login.login.LoginRequest;
import com.pentagon.p01_android_proj.login.register.RegisterRequest;
import com.pentagon.p01_android_proj.login.register.RegisterResponse;
import com.pentagon.p01_android_proj.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author fishinwater-1999
 * @version 2019-12-21
 */
public interface UserMgrService {

    /**
     * POST 用 Query
     */
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    /**
     * POST 用 Query
     */
    @POST("register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);

    /**
     * POST 用 Query
     */
    @GET("updatePswd/{user_id}/{user_password}")
    Call<ForgetResponse> reset(@Path("user_id") String user_id, @Path("user_password") String user_password);

}
