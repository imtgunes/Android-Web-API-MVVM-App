package com.example.webapimvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.webapimvvm.model.Cart;
import com.example.webapimvvm.model.ShoppingCart;
import com.example.webapimvvm.model.ToDo;
import com.example.webapimvvm.repository.ToDoRepository;

import java.io.IOException;
import java.util.ArrayList;

public class TodoActivityViewModel extends ViewModel {
    private MutableLiveData<ArrayList<ToDo>> todos;
    private MutableLiveData<ArrayList<Cart>> todoDetail;
    ToDoRepository toDoRepository = new ToDoRepository();
    public TodoActivityViewModel(){
        todos = toDoRepository.getTodoMutableLiveData();
        todoDetail = toDoRepository.getTodoDetailMutableLiveData();
    }

    public MutableLiveData<ArrayList<ToDo>> getTodos() {
        return todos;
    }

    public MutableLiveData<ArrayList<Cart>> getTodoDetail() {
        return todoDetail;
    }

    public void getTodoList(int userID, int state){
        toDoRepository.getTodoList(userID,state);

    }
    public void addToDo(ToDo toDo, int shoppingCartID, ShoppingCart shoppingCart) throws IOException {
        toDoRepository.addToDo(toDo,shoppingCartID,shoppingCart);
    }

    public void getTodoDetailList(int shoppingCartID){
        toDoRepository.getTodoDetail(shoppingCartID);
    }

    public void deleteTodo(int todoShoppingCartID){
        toDoRepository.deleteTodo(todoShoppingCartID);
    }

    public void updateToDo(int todoID,ToDo toDo){
        toDoRepository.updateToDo(todoID,toDo);
    }
}
