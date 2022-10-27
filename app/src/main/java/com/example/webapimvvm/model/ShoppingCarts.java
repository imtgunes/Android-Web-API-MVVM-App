package com.example.webapimvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShoppingCarts {

    @SerializedName("shoppingCartsID")
    @Expose
    private Integer shoppingCartsID;
    @SerializedName("shoppingCartsCartID")
    @Expose
    private Integer shoppingCartsCartID;
    @SerializedName("shoppingCartsProductID")
    @Expose
    private Integer productID;
    @SerializedName("shoppingCartsPiece")
    @Expose
    private Integer shoppingCartsPiece;
    @SerializedName("shoppingCartsNote")
    @Expose

    private String shoppingCartsNote;

    public ShoppingCarts(Integer shoppingCartsID, Integer shoppingCartsCartID, Integer shoppingCartsProductID, Integer shoppingCartsPiece, String shoppingCartsNote) {
        this.shoppingCartsID = shoppingCartsID;
        this.shoppingCartsCartID = shoppingCartsCartID;
        this.productID = shoppingCartsProductID;
        this.shoppingCartsPiece = shoppingCartsPiece;
        this.shoppingCartsNote = shoppingCartsNote;
    }

    public ShoppingCarts(Integer shoppingCartsCartID, Integer shoppingCartsProductID, Integer shoppingCartsPiece, String shoppingCartsNote) {
        this.shoppingCartsCartID = shoppingCartsCartID;
        this.productID = shoppingCartsProductID;
        this.shoppingCartsPiece = shoppingCartsPiece;
        this.shoppingCartsNote = shoppingCartsNote;
    }

    public Integer getShoppingCartsID() {
        return shoppingCartsID;
    }

    public void setShoppingCartsID(Integer shoppingCartsID) {
        this.shoppingCartsID = shoppingCartsID;
    }

    public Integer getShoppingCartsCartID() {
        return shoppingCartsCartID;
    }

    public void setShoppingCartsCartID(Integer shoppingCartsCartID) {
        this.shoppingCartsCartID = shoppingCartsCartID;
    }

    public Integer getShoppingCartsProductID() {
        return productID;
    }

    public void setShoppingCartsProductID(Integer shoppingCartsProductID) {
        this.productID = shoppingCartsProductID;
    }

    public Integer getShoppingCartsPiece() {
        return shoppingCartsPiece;
    }

    public void setShoppingCartsPiece(Integer shoppingCartsPiece) {
        this.shoppingCartsPiece = shoppingCartsPiece;
    }

    public String getShoppingCartsNote() {
        return shoppingCartsNote;
    }

    public void setShoppingCartsNote(String shoppingCartsNote) {
        this.shoppingCartsNote = shoppingCartsNote;
    }

}