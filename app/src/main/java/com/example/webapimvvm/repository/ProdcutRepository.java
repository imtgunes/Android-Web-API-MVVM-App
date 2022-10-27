package com.example.webapimvvm.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.webapimvvm.Api.ApiInterface;
import com.example.webapimvvm.Api.ApiUtils;
import com.example.webapimvvm.model.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProdcutRepository {
    private MutableLiveData<ArrayList<Product>> productMutableLiveData;
    private MutableLiveData<Integer> shoppingCartIDMutableLiveData;
    private ApiInterface apiInterface= ApiUtils.getApi();

    public ProdcutRepository() {
        productMutableLiveData = new MutableLiveData<>();
        shoppingCartIDMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Product>> getProductMutableLiveData() {
        return productMutableLiveData;
    }

    public MutableLiveData<Integer> getShoppingCartIDMutableLiveData() {
        return shoppingCartIDMutableLiveData;
    }

    public void getProductsList(int categoryID){
        apiInterface.products(categoryID).enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                productMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                productMutableLiveData.postValue(null);
            }
        });
    }

    public void getShoppingCart(int userID){
        apiInterface.getShoppingCartID(userID).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                shoppingCartIDMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                shoppingCartIDMutableLiveData.postValue(0);
            }
        });
    }

}
