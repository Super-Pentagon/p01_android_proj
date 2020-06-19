package com.pentagon.p01_android_proj.search;

import com.pentagon.p01_android_proj.model.Product;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductSearchClient {
    private static final String PRODUCT_SEARCH_BASE_URL
            = "https://run.mocky.io/v3/";

    private static ProductSearchClient instance;
    private ProductSearchService productSearchService;

    private ProductSearchClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(PRODUCT_SEARCH_BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        productSearchService = retrofit.create(ProductSearchService.class);
    }

    public static ProductSearchClient getInstance() {
        if (instance == null) {
            instance = new ProductSearchClient();
        }
        return instance;
    }

    public Observable<List<Product>> getSearchedProducts(String inputString) {
//        return productSearchService.getSearchedProducts(inputString);
        return productSearchService.getSearchedProducts();
    }
}
