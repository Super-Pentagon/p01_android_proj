package com.pentagon.p01_android_proj.detail;

import com.pentagon.p01_android_proj.model.ShoppingCart;

import java.math.BigDecimal;

class ProductDetailPresenter implements IProductDetailPresenter {
    private String mProductId;
    private BigDecimal mPrice;
    private IProductDetailView mProductDetailView;

    public ProductDetailPresenter(String productId, BigDecimal price, IProductDetailView productDetailView) {
        mProductId = productId;
        mPrice = price;
        mProductDetailView = productDetailView;
    }

    @Override
    public void updateShoppingCart(int quantity) {
        ShoppingCart cart = ShoppingCart.getInstance();
        cart.updateOrderItem(mProductId, quantity, mPrice);
        int kindQuantity = cart.getKindQuantity();
        BigDecimal subtotal = cart.getSubtotal();
        mProductDetailView.onUpdateCartCompleted(kindQuantity, subtotal);
    }

    @Override
    public void initWithShoppingCart() {
        ShoppingCart cart = ShoppingCart.getInstance();
        int quantity = cart.getProductQuantityById(mProductId);
        int kindQuantity = cart.getKindQuantity();
        BigDecimal subtotal = cart.getSubtotal();
        mProductDetailView.onInitCompleted(quantity, kindQuantity, subtotal);
    }

    @Override
    public void onDestroy() {
        mProductDetailView = null;
    }
}
