package com.example.webapimvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cart {
    @SerializedName("shoppingCartsID")
    @Expose
    private Integer shoppingCartsID;
    @SerializedName("shoppingCartsCartID")
    @Expose
    private Integer shoppingCartsCartID;
    @SerializedName("shoppingCartsPiece")
    @Expose
    private Integer shoppingCartsPiece;
    @SerializedName("shoppingCartsNote")
    @Expose
    private String shoppingCartsNote;
    @SerializedName("productID")
    @Expose
    private Integer productID;
    @SerializedName("product")
    @Expose
    private Product product;

    public Cart(Integer shoppingCartsCartID, Integer shoppingCartsPiece, String shoppingCartsNote, Integer productID, Product product) {
        this.shoppingCartsCartID = shoppingCartsCartID;
        this.shoppingCartsPiece = shoppingCartsPiece;
        this.shoppingCartsNote = shoppingCartsNote;
        this.productID = productID;
        this.product = product;
    }

    public Cart(Integer shoppingCartsID, Integer shoppingCartsCartID, Integer shoppingCartsPiece, String shoppingCartsNote, Integer productID, Product product) {
        this.shoppingCartsID = shoppingCartsID;
        this.shoppingCartsCartID = shoppingCartsCartID;
        this.shoppingCartsPiece = shoppingCartsPiece;
        this.shoppingCartsNote = shoppingCartsNote;
        this.productID = productID;
        this.product = product;
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

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
