package com.pentagon.p01_android_proj.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ShoppingCart {
    private static final ShoppingCart ourInstance = new ShoppingCart();
    private BigDecimal subtotal=new BigDecimal(0);
    private List<OrderItem> mOrderItems = new ArrayList<>();

    private ShoppingCart() {
    }

    public static ShoppingCart getInstance() {
        return ourInstance;
    }

    public List<OrderItem> getOrderItems() {
        return mOrderItems;
    }

    public void updateOrderItem(String productId, int quantity,BigDecimal price) {
        for (int i = 0; i < mOrderItems.size(); i++) {
            OrderItem item=mOrderItems.get(i);
            if (item.getProductId().equals(productId)) {
                if(quantity==0){
                    mOrderItems.remove(item);
                    //just remove one product
                    subtotal=subtotal.subtract(price);
                }else{
                    //_quantity=newQuantity-oldQuantity
                    int _quantity=quantity-item.getQuantity();
                    item.setQuantity(quantity);
                    //subtotal=subtotal+(_quantity*price)
                    subtotal=subtotal.add(new BigDecimal(_quantity).multiply(price));
                }
                return;
            }
        }
        OrderItem newItem=new OrderItem(UUID.randomUUID().toString(),productId,quantity);
        mOrderItems.add(newItem);
        //just add one product
        subtotal=subtotal.add(price);
    }

    public int getKindQuantity(){
        return mOrderItems.size();
    }

    public BigDecimal getSubtotal(){
        return subtotal;
    }

    public int getProductQuantityById(String productId){
        for (OrderItem item : mOrderItems) {
            if (item.getProductId().equals(productId)) {
                return item.getQuantity();
            }
        }
        return 0;
    }
}
