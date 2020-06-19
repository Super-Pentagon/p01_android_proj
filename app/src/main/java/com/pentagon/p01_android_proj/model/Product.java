package com.pentagon.p01_android_proj.model;

import java.math.BigDecimal;

public class Product {
    private String id;
    private String name;
    //描述
    private String des;
    private String sellerId;
    private BigDecimal price;
    //图URL
    private String pictureUrl;
    //月销量
    private int monthlySales;
    //预计送货时间（分钟）
    private int deliveryTime;

    public Product(String id, String name, String des, String sellerId, BigDecimal price
            , String pictureUrl, int monthlySales, int deliveryTime) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.sellerId = sellerId;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.monthlySales = monthlySales;
        this.deliveryTime = deliveryTime;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDes() {
        return des;
    }

    public String getSellerId() {
        return sellerId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public int getMonthlySales() {
        return monthlySales;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }
}
