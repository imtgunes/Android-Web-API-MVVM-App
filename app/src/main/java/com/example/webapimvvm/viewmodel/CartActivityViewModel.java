package com.example.webapimvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.webapimvvm.model.Cart;
import com.example.webapimvvm.repository.CartRepository;

import java.util.ArrayList;

public class CartActivityViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Cart>> cart;
    private MutableLiveData<Cart> addedCart;
    private CartRepository cartRepository = new CartRepository();

    public CartActivityViewModel() {
        cart = cartRepository.getCartMutableLiveData();
        addedCart = cartRepository.getCartAddMutableLiveData();
    }

    public MutableLiveData<Cart> getAddedCart() {
        return addedCart;
    }

    public MutableLiveData<ArrayList<Cart>> getCart() {
        return cart;
    }

    public void getCartList(int shoppingCartID){
        cartRepository.getCart(shoppingCartID);
    }

    public void addCart(Cart cart){
        cartRepository.addCart(cart);
    }

    public void removeItemOnCart(int shoppingCartsID) {
        cartRepository.removeItemOnCart(shoppingCartsID);
    }
}
