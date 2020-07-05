package com.pentagon.p01_android_proj.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {
    private String id;
    private String name;
    //描述
    private String des;
    private String sellerId;
    private BigDecimal price;
    //缩略图URL
    private String thumbnailUrl;
    //图URL
    private String pictureUrl;
    //月销量
    private int monthlySales;

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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public int getMonthlySales() {
        return monthlySales;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setMonthlySales(int monthlySales) {
        this.monthlySales = monthlySales;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        long temp;
        temp = Double.doubleToLongBits(price.doubleValue());
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
