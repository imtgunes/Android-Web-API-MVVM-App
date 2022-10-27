package com.example.webapimvvm.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.webapimvvm.Api.ApiInterface;
import com.example.webapimvvm.Api.ApiUtils;
import com.example.webapimvvm.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private MutableLiveData<ArrayList<User>> userMutableLiveData;
    private ApiInterface apiInterface= ApiUtils.getApi();

    public LoginRepository() {
        userMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<User>> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public void loging(User user){
        apiInterface.loginUser(user).enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                userMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
    }
}
