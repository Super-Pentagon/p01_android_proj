package com.pentagon.p01_android_proj.service;

import com.pentagon.p01_android_proj.model.Product;
import com.pentagon.p01_android_proj.model.ProductWrapper;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ProductSearchService {
//    @GET("ic_product_search/{inputString}")
    @GET("8f030a11-693b-4509-aa52-aa55bb8c03ca")
    Observable<ProductWrapper> getSearchedProducts(
//            @Path("inputString") String inputString
    );
}
