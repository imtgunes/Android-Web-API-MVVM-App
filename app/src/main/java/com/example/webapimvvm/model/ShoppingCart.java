package com.example.webapimvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShoppingCart {

    @SerializedName("shoppingCartID")
    @Expose
    private Integer shoppingCartID;
    @SerializedName("shoppingCartNote")
    @Expose
    private String shoppingCartNote;
    @SerializedName("shoppingCartState")
    @Expose
    private Integer shoppingCartState;
    @SerializedName("shoppingCartUserID")
    @Expose
    private Integer shoppingCartUserID;

    public ShoppingCart(Integer shoppingCartID, String shoppingCartNote, Integer shoppingCartState, Integer shoppingCartUserID) {
        this.shoppingCartID = shoppingCartID;
        this.shoppingCartNote = shoppingCartNote;
        this.shoppingCartState = shoppingCartState;
        this.shoppingCartUserID = shoppingCartUserID;
    }

    public Integer getShoppingCartID() {
        return shoppingCartID;
    }

    public void setShoppingCartID(Integer shoppingCartID) {
        this.shoppingCartID = shoppingCartID;
    }

    public String getShoppingCartNote() {
        return shoppingCartNote;
    }

    public void setShoppingCartNote(String shoppingCartNote) {
        this.shoppingCartNote = shoppingCartNote;
    }

    public Integer getShoppingCartState() {
        return shoppingCartState;
    }

    public void setShoppingCartState(Integer shoppingCartState) {
        this.shoppingCartState = shoppingCartState;
    }

    public Integer getShoppingCartUserID() {
        return shoppingCartUserID;
    }

    public void setShoppingCartUserID(Integer shoppingCartUserID) {
        this.shoppingCartUserID = shoppingCartUserID;
    }

}