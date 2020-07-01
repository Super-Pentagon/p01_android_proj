package com.pentagon.p01_android_proj.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShoppingCart {
    private static final ShoppingCart ourInstance = new ShoppingCart();

    public static ShoppingCart getInstance() {
        return ourInstance;
    }

    private ShoppingCart() {
    }

    private List<OrderItem> mOrderItems = new ArrayList<>();

    public List<OrderItem> getOrderItems() {
        return mOrderItems;
    }

    private void addOrderItem(OrderItem item) {
        mOrderItems.add(item);
    }

    public void setOrderItem(OrderItem item) {
        if(item.getQuantity()==0) {
            removeItem(item.getOrderId());
        }
        for (int i = 0; i < mOrderItems.size(); i++) {
            if (mOrderItems.get(i).getOrderId().equals(item.getOrderId())) {
                mOrderItems.set(i, item);
                return;
            }
        }
        addOrderItem(item);
    }

    private void removeItem(String itemId) {
        for (int i = 0; i < mOrderItems.size(); i++) {
            OrderItem item = mOrderItems.get(i);
            if (item.getOrderId().equals(itemId)) {
                mOrderItems.remove(item);
                return;
            }
        }
    }
}
