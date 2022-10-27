package com.example.webapimvvm.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.webapimvvm.Api.ApiInterface;
import com.example.webapimvvm.Api.ApiUtils;
import com.example.webapimvvm.model.Cart;
import com.example.webapimvvm.model.ShoppingCart;
import com.example.webapimvvm.model.ToDo;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToDoRepository {
    private MutableLiveData<ArrayList<ToDo>> todoMutableLiveData;
    private MutableLiveData<ArrayList<Cart>> todoDetailMutableLiveData;
    private ApiInterface todoApiInterface = ApiUtils.getApi();

    public ToDoRepository() {
        todoMutableLiveData = new MutableLiveData<>();
        todoDetailMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<ToDo>> getTodoMutableLiveData() {
        return todoMutableLiveData;
    }

    public MutableLiveData<ArrayList<Cart>> getTodoDetailMutableLiveData() {
        return todoDetailMutableLiveData;
    }

    public void getTodoList(int userID, int state){
        todoApiInterface.getTodoNotComplated(userID,state).enqueue(new Callback<ArrayList<ToDo>>() {
            @Override
            public void onResponse(Call<ArrayList<ToDo>> call, Response<ArrayList<ToDo>> response) {
                todoMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<ToDo>> call, Throwable t) {
                todoMutableLiveData.postValue(null);
            }
        });
    }

    public void addToDo(ToDo toDo, int shoppingCartID, ShoppingCart shoppingCart) throws IOException {
        todoApiInterface.addToDo(toDo).execute();
        todoApiInterface.updateShoppingCart(shoppingCartID,shoppingCart).execute();
    }

    public void getTodoDetail(int shoppingCartID){
        todoApiInterface.getShoppingCartActivity(shoppingCartID).enqueue(new Callback<ArrayList<Cart>>() {
            @Override
            public void onResponse(Call<ArrayList<Cart>> call, Response<ArrayList<Cart>> response) {
                todoDetailMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Cart>> call, Throwable t) {
                todoDetailMutableLiveData.postValue(null);
            }
        });
    }

    public void deleteTodo(int todoShoppingCartID){
        todoApiInterface.deleteShoppingCart(todoShoppingCartID).enqueue(new Callback<ShoppingCart>() {
            @Override
            public void onResponse(Call<ShoppingCart> call, Response<ShoppingCart> response) {

            }

            @Override
            public void onFailure(Call<ShoppingCart> call, Throwable t) {

            }
        });
    }
    public void updateToDo(int todoID,ToDo toDo){
        todoApiInterface.updateToDo(todoID,toDo).enqueue(new Callback<ToDo>() {
            @Override
            public void onResponse(Call<ToDo> call, Response<ToDo> response) {

            }

            @Override
            public void onFailure(Call<ToDo> call, Throwable t) {

            }
        });
    }
}
