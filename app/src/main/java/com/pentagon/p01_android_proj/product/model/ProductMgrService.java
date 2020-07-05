package com.pentagon.p01_android_proj.product.model;

import com.pentagon.p01_android_proj.product.bean.ProductResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author: Create by huangyuanhao on 2020-07-04
 * @email: 814484626@qq.com
 */
public interface ProductMgrService {

    /**
     * POST 用 Field
     */
//    @FormUrlEncoded
    @GET("getProductById/{good_id}")
    Call<ProductResponse> getProductById(@Path("good_id") String good_id);


//    @POST("/GetDetailWithMonthWithCode")
//    Call<Object> getLandingPageReport(@Body Report report);

}
