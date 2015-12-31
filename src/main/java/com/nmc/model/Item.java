/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.model;

/**
 *
 * @author Poleschuk Ivan
 */
public class Item {
    private String productId;
    private String productTypeId;
    private String productName;
    private String productRegPrice;
    private String productSalePrice;    
    private String productImage;
    private String playListOrder;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductRegPrice() {
        return productRegPrice;
    }

    public void setProductRegPrice(String productRegPrice) {
        this.productRegPrice = productRegPrice;
    }

    public String getProductSalePrice() {
        return productSalePrice;
    }

    public void setProductSalePrice(String productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getPlayListOrder() {
        return playListOrder;
    }

    public void setPlayListOrder(String playListOrder) {
        this.playListOrder = playListOrder;
    }
    
    
    
}
