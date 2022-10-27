package com.example.webapimvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.webapimvvm.model.User;
import com.example.webapimvvm.repository.SplashRepository;

public class SplashActivityViewModel extends ViewModel {
    private MutableLiveData<Integer> userTimeOut;
    private SplashRepository splashRepository = new SplashRepository();

    public SplashActivityViewModel() {
        userTimeOut = splashRepository.getUserTimeOutMutableLiveData();
    }

    public MutableLiveData<Integer> getUserTimeOut() {
        return userTimeOut;
    }

    public void getUserTimeOutRemaining(User user){
        splashRepository.getUserTimeOutRemaining(user);
    }
}
