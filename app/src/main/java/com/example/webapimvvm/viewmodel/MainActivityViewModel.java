package com.example.webapimvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.webapimvvm.model.Category;
import com.example.webapimvvm.model.User;
import com.example.webapimvvm.repository.MainRepository;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Category>> categories;
    private MutableLiveData<Integer> shoppingCartID;
    private MutableLiveData<ArrayList<User>> userInfo;
    private MainRepository mainRepository;

    public MainActivityViewModel(){
        mainRepository = new MainRepository();
        categories = mainRepository.getCategoryMutableLiveData();
        shoppingCartID = mainRepository.getShoppingCartIDMutableLiveData();
        userInfo = mainRepository.getUserInfoMutableLiveData();
    }
    public void getMainCategories(){
        mainRepository.getCategories();

    }
    public MutableLiveData<ArrayList<Category>> getCategories() {
        return categories;
    }

    public MutableLiveData<ArrayList<User>> getUserInfo() {
        return userInfo;
    }

    public MutableLiveData<Integer> getShoppingCartID() {
        return shoppingCartID;
    }



    public void getShoppingCartIDFromApi(int userID){
        mainRepository.getShoppingCartID(userID);
    }

    public void getUserInfoList(int userID){
        mainRepository.getUserInfo(userID);
    }
}
