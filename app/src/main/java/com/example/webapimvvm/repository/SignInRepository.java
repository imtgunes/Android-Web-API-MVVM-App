package com.example.webapimvvm.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.webapimvvm.Api.ApiInterface;
import com.example.webapimvvm.Api.ApiUtils;
import com.example.webapimvvm.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInRepository {
    private MutableLiveData<Integer> userMutableLiveData;
    private ApiInterface apiInterface= ApiUtils.getApi();

    public SignInRepository() {
        userMutableLiveData = new MutableLiveData<Integer>();
    }

    public MutableLiveData<Integer> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public void signing(User user){
        apiInterface.signUser(user).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                userMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

}
