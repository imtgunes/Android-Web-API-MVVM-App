package com.example.webapimvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.webapimvvm.model.User;
import com.example.webapimvvm.repository.SignInRepository;

public class SignInActivityViewModel extends ViewModel {
    private MutableLiveData<Integer> userMutableLiveData;
    private SignInRepository signInRepository;

    public SignInActivityViewModel() {
        userMutableLiveData = signInRepository.getUserMutableLiveData();
    }

    public MutableLiveData<Integer> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public void signing(User user){
        signInRepository.signing(user);
    }
}
