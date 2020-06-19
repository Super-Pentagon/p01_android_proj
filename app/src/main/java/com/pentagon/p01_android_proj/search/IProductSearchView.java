package com.pentagon.p01_android_proj.search;

import com.pentagon.p01_android_proj.model.Product;

import java.util.List;

interface IProductSearchView {
    void onReadyForSearching();

    void onSearchCompleted(List<Product> products);

    void onInitRecordsCompleted(String[] recordStrings);

    void onSortCompleted();
}
