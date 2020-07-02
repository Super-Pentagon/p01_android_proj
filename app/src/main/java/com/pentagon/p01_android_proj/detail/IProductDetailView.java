package com.pentagon.p01_android_proj.detail;

import java.math.BigDecimal;

interface IProductDetailView {
    void onUpdateCartCompleted(int kindQuantity, BigDecimal subtotal);
    void onInitCompleted(int quantity,int kindQuantity, BigDecimal subtotal);
}
