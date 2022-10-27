package com.example.webapimvvm.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.webapimvvm.Api.ApiInterface;
import com.example.webapimvvm.Api.ApiUtils;
import com.example.webapimvvm.model.Category;
import com.example.webapimvvm.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private MutableLiveData<ArrayList<Category>> categoryMutableLiveData;
    private MutableLiveData<Integer> shoppingCartIDMutableLiveData;
    private MutableLiveData<ArrayList<User>> userInfoMutableLiveData;
    private ApiInterface categoryApiInterface;

    public MainRepository() {
        categoryMutableLiveData = new MutableLiveData<>();
        shoppingCartIDMutableLiveData = new MutableLiveData<>();
        userInfoMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Category>> getCategoryMutableLiveData() {
        return categoryMutableLiveData;
    }

    public MutableLiveData<Integer> getShoppingCartIDMutableLiveData() {
        return shoppingCartIDMutableLiveData;
    }

    public MutableLiveData<ArrayList<User>> getUserInfoMutableLiveData() {
        return userInfoMutableLiveData;
    }

    public void getCategories(){
        categoryApiInterface = ApiUtils.getApi();
        categoryApiInterface.categories().enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                categoryMutableLiveData.postValue(response.body());

            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                categoryMutableLiveData.postValue(null);
            }
        });
    }

    public void getShoppingCartID(int userID){
        categoryApiInterface.getShoppingCartID(userID).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                shoppingCartIDMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    public void getUserInfo(int userID){
        categoryApiInterface.getUserInfo(userID).enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                userInfoMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
    }
}
