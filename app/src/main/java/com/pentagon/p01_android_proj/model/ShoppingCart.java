package com.pentagon.p01_android_proj.model;

import androidx.annotation.NonNull;

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

    /**
     * 获得购物车单例
     *
     * @return 购物车单例
     */
    public static ShoppingCart getInstance() {
        return ourInstance;
    }

    /**
     * 获得包含所有订单项的List
     *
     * @return 所有订单项的List
     */
    public List<OrderItem> getOrderItems() {
        return mOrderItems;
    }

    /**
     * 更新购物车里指定商品ID的订单项。如果商品ID存在则更新数量，传入新的数量为0表示移除对应订单项。
     * 如果商品ID不存在则新建订单项，订单项ID为UUID随机值
     *
     * 调用时更新购物车小计和商品种类数量
     *
     * 商品价格参数不能为空，每次都需要根据价格修改小计
     *
     * 方便详情页加减商品时调用而写，包括增删改功能
     *
     * @param productId 商品ID
     * @param quantity 新的商品数量
     * @param price 商品单价
     */
    public void updateOrderItem(String productId, int quantity,@NonNull BigDecimal price) {
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

    /**
     * 获得购物车中的商品种类数量
     *
     * @return 购物车中的商品种类数量
     */
    public int getKindQuantity(){
        return mOrderItems.size();
    }

    /**
     * 获得购物车中所有商品的总价（小计）
     *
     * @return 购物车中所有商品的总价（小计）
     */
    public BigDecimal getSubtotal(){
        return subtotal;
    }

    /**
     * 根据商品ID查找指定商品的数量
     *
     * @param productId 商品ID
     * @return 指定商品的数量，不存在则返回0
     */
    public int getProductQuantityById(String productId){
        for (OrderItem item : mOrderItems) {
            if (item.getProductId().equals(productId)) {
                return item.getQuantity();
            }
        }
        return 0;
    }
}
