package com.pentagon.p01_android_proj.login;

import com.pentagon.p01_android_proj.model.User;

import retrofit2.Call;
import retrofit2.http.POST;
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
    Call<String> login(@Query("username") String username, @Query("password") String password);

    /**
     * POST 用 Query
     */
    @POST("register")
    Call<String> register(@Query("username") String username, @Query("password") String password);

    /**
     * POST 用 Query
     */
    @POST("reset")
    Call<String> reset(@Query("username") String username, @Query("password") String password);

}
