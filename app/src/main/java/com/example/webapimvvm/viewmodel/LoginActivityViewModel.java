package com.example.webapimvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.webapimvvm.model.User;
import com.example.webapimvvm.repository.LoginRepository;

import java.util.ArrayList;

public class LoginActivityViewModel extends ViewModel {
    private MutableLiveData<ArrayList<User>> userList;
    private LoginRepository loginRepository = new LoginRepository();
    public LoginActivityViewModel() {
        userList = loginRepository.getUserMutableLiveData();
    }

    public MutableLiveData<ArrayList<User>> getUserList() {
        return userList;
    }

    public void loging(User user){
       loginRepository.loging(user);
    }
}
