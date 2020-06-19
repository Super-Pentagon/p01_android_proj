package com.pentagon.p01_android_proj.search;

import com.pentagon.p01_android_proj.model.Product;

import java.util.List;

interface IProductSearchPresenter {
    void tryToSearch();

    void searchProducts(String inputString);

    void clearReference();

    void initSearchRecords();

    void saveSearchRecord(String inputString);

    void sortWithSales(List<Product> list, boolean isAscending);

    void sortWithPrice(List<Product> list, boolean isAscending);
}
