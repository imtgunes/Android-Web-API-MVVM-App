package com.example.webapimvvm.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.webapimvvm.Api.ApiInterface;
import com.example.webapimvvm.Api.ApiUtils;
import com.example.webapimvvm.model.ShoppingCart;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingCartRepository {
    private MutableLiveData<ArrayList<ShoppingCart>> cartNoteMutableLiveData;
    private ApiInterface shoppingCartApiInterface = ApiUtils.getApi();

    public ShoppingCartRepository() {
        cartNoteMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<ShoppingCart>> getCartNoteMutableLiveData() {
        return cartNoteMutableLiveData;
    }

    public void shoppingCartNote(int shoppingCartID){
        shoppingCartApiInterface.getShoppingCartNote(shoppingCartID).enqueue(new Callback<ArrayList<ShoppingCart>>() {
            @Override
            public void onResponse(Call<ArrayList<ShoppingCart>> call, Response<ArrayList<ShoppingCart>> response) {
                cartNoteMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<ShoppingCart>> call, Throwable t) {
                cartNoteMutableLiveData.postValue(null);
            }
        });
    }

}
