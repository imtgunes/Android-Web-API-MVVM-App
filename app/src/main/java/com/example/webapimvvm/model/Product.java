package com.example.webapimvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("productID")
    @Expose
    private Integer productID;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("productCategoryID")
    @Expose
    private Integer productCategoryID;
    @SerializedName("productImage")
    @Expose
    private String productImage;
    @SerializedName("productWeight")
    @Expose
    private Integer productWeight;


    public Product(Integer productID, String productName, Integer productCategoryID, String productImage, Integer productWeight) {
        this.productID = productID;
        this.productName = productName;
        this.productCategoryID = productCategoryID;
        this.productImage = productImage;
        this.productWeight = productWeight;
    }


    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductCategoryID() {
        return productCategoryID;
    }

    public void setProductCategoryID(Integer productCategoryID) {
        this.productCategoryID = productCategoryID;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Integer getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(Integer productWeight) {
        this.productWeight = productWeight;
    }

}