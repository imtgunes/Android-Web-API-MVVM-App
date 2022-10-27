package com.example.webapimvvm.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.webapimvvm.Api.ApiInterface;
import com.example.webapimvvm.Api.ApiUtils;
import com.example.webapimvvm.model.Cart;
import com.example.webapimvvm.model.ShoppingCarts;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepository {
    private MutableLiveData<ArrayList<Cart>> cartMutableLiveData;
    private MutableLiveData<Cart> cartAddMutableLiveData;
    private ApiInterface apiInterface= ApiUtils.getApi();

    public CartRepository() {
        cartMutableLiveData = new MutableLiveData<>();
        cartAddMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Cart> getCartAddMutableLiveData() {
        return cartAddMutableLiveData;
    }

    public MutableLiveData<ArrayList<Cart>> getCartMutableLiveData() {
        return cartMutableLiveData;
    }

    public void getCart(int shoppingCartID) {
        apiInterface.getShoppingCartActivity(shoppingCartID).enqueue(new Callback<ArrayList<Cart>>() {
            @Override
            public void onResponse(Call<ArrayList<Cart>> call, Response<ArrayList<Cart>> response) {
                cartMutableLiveData.postValue(response.body());


            }

            @Override
            public void onFailure(Call<ArrayList<Cart>> call, Throwable t) {
                cartMutableLiveData.postValue(null);
            }
        });
    }

    public void addCart(Cart cart) {
        apiInterface.addShoppingCarts(cart).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                cartAddMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                cartAddMutableLiveData.postValue(null);
            }
        });
    }

    public void removeItemOnCart(int shoppingCartsID) {
        apiInterface.removeItemOnCart(shoppingCartsID).enqueue(new Callback<ShoppingCarts>() {
            @Override
            public void onResponse(Call<ShoppingCarts> call, Response<ShoppingCarts> response) {

            }

            @Override
            public void onFailure(Call<ShoppingCarts> call, Throwable t) {

            }
        });
    }


}
