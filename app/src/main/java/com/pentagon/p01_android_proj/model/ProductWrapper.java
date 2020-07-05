package com.pentagon.p01_android_proj.model;

import java.util.ArrayList;
import java.util.List;

public class ProductWrapper {
    private ListWrapper data;

    public static class ListWrapper{
        private ArrayList<Product> productlist;

        public ArrayList<Product> getProductList() {
            return productlist;
        }
    }

    public ListWrapper getData() {
        return data;
    }
}
