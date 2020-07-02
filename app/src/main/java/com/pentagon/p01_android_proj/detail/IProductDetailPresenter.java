package com.pentagon.p01_android_proj.detail;

interface IProductDetailPresenter {
    void updateShoppingCart(int quantity);
    void initWithShoppingCart();
    void onDestroy();
}
