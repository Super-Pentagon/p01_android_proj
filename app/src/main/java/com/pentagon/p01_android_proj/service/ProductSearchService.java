package com.pentagon.p01_android_proj.service;

import com.pentagon.p01_android_proj.model.Product;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ProductSearchService {
//    @GET("ic_product_search/{inputString}")
    @GET("314d9fba-9245-4e85-af36-761d1c6e9d02")
    Observable<List<Product>> getSearchedProducts(
//            @Path("inputString") String inputString
    );
}
