package com.pentagon.p01_android_proj.service;

import com.pentagon.p01_android_proj.model.Product;
import com.pentagon.p01_android_proj.model.ProductWrapper;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductSearchService {
    @GET("userservice/product/getProductByStr/{inputString}")
    Observable<ProductWrapper> getSearchedProducts(
            @Path("inputString") String inputString
    );
}
