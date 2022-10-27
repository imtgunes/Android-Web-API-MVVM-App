package com.example.webapimvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.webapimvvm.model.ShoppingCart;
import com.example.webapimvvm.repository.ShoppingCartRepository;

import java.util.ArrayList;

public class ShoppingCartViewModel extends ViewModel {
    private MutableLiveData<ArrayList<ShoppingCart>> cartNoteMutableLiveData;
    ShoppingCartRepository shoppingCartRepository = new ShoppingCartRepository();

    public ShoppingCartViewModel() {
        cartNoteMutableLiveData = shoppingCartRepository.getCartNoteMutableLiveData();
    }

    public MutableLiveData<ArrayList<ShoppingCart>> getCartNoteMutableLiveData() {
        return cartNoteMutableLiveData;
    }

    public void getShoppingCartNote(int shoppingCartID){
        shoppingCartRepository.shoppingCartNote(shoppingCartID);
    }
}
