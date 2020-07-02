package com.pentagon.p01_android_proj.service;

import com.pentagon.p01_android_proj.model.Product;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ProductSearchService {
//    @GET("ic_product_search/{inputString}")
    @GET("155c0306-bbcd-4d35-92f0-6741065b6334")
    Observable<List<Product>> getSearchedProducts(
//            @Path("inputString") String inputString
    );
}
