package com.example.webapimvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.webapimvvm.model.Product;
import com.example.webapimvvm.repository.ProdcutRepository;

import java.util.ArrayList;

public class ProductActivityViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Product>> products;
    private MutableLiveData<Integer> shoppingCartID;
    private ProdcutRepository prodcutRepository = new ProdcutRepository();
    public ProductActivityViewModel() {
        products = prodcutRepository.getProductMutableLiveData();
        shoppingCartID = prodcutRepository.getShoppingCartIDMutableLiveData();
    }

    public MutableLiveData<ArrayList<Product>> getProducts() {
        return products;
    }

    public MutableLiveData<Integer> getShoppingCartID() {
        return shoppingCartID;
    }

    public void getProductList(int categoryID){
        prodcutRepository.getProductsList(categoryID);

    }
    public void getShoppingCart(int userID){
        prodcutRepository.getShoppingCart(userID);
    }
}
