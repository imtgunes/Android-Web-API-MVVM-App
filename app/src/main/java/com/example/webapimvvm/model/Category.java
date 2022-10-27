package com.example.webapimvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("categoryID")
    @Expose
    private int categoryID;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("categoryImage")
    @Expose
    private String categoryImage;

    public Category(int categoryID, String categoryName, String categoryImage) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

}
