package com.pentagon.p01_android_proj.search;

import com.pentagon.p01_android_proj.model.Product;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface ProductSearchService {
//    @GET("ic_product_search/{inputString}")
    @GET("46b38e9a-22ed-4ac7-88c8-bd6380a0b1be")
    Observable<List<Product>> getSearchedProducts(
//            @Path("inputString") String inputString
    );
}
