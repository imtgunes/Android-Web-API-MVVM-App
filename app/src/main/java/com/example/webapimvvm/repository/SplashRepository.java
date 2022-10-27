package com.example.webapimvvm.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.webapimvvm.Api.ApiInterface;
import com.example.webapimvvm.Api.ApiUtils;
import com.example.webapimvvm.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashRepository {
    private MutableLiveData<Integer> userTimeOutMutableLiveData;
    private ApiInterface apiInterface= ApiUtils.getApi();

    public SplashRepository() {
        userTimeOutMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Integer> getUserTimeOutMutableLiveData() {
        return userTimeOutMutableLiveData;
    }

    public void getUserTimeOutRemaining(User user){
        apiInterface.chekTimeOutloginUser(user).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                userTimeOutMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

}
