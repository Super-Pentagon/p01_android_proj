package com.pentagon.p01_android_proj.product;

import com.pentagon.p01_android_proj.login.UserMgrService;
import com.pentagon.p01_android_proj.model.Product;
import com.pentagon.p01_android_proj.util.http.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: Create by huangyuanhao on 2020-07-04
 * @email: 814484626@qq.com
 */
public class ProductModel {


    public void getProductById(String productId, Callback<Object> callBack) {
        // baseUrl() 设置路由地址
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ApiUtils.PRODUCT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 设置参数
        Call<Object> call = retrofit.create(ProductMgrService.class)
                .getProductById(productId);

        // 回调
        call.enqueue(callBack);
    }

}
