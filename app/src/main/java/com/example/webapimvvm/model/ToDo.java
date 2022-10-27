package com.example.webapimvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ToDo {

    @SerializedName("todoID")
    @Expose
    private Integer todoID;
    @SerializedName("todoShoppingCartID")
    @Expose
    private Integer todoShoppingCartID;
    @SerializedName("todoSaveDate")
    @Expose
    private String todoSaveDate;
    @SerializedName("todoUserID")
    @Expose
    private Integer todoUserID;
    @SerializedName("todoState")
    @Expose
    private Integer todoState;

    public ToDo(Integer todoID, Integer todoShoppingCartID, String todoSaveDate, Integer todoUserID, Integer todoState) {
        this.todoID = todoID;
        this.todoShoppingCartID = todoShoppingCartID;
        this.todoSaveDate = todoSaveDate;
        this.todoUserID = todoUserID;
        this.todoState = todoState;
    }

    public Integer getTodoID() {
        return todoID;
    }

    public void setTodoID(Integer todoID) {
        this.todoID = todoID;
    }

    public Integer getTodoShoppingCartID() {
        return todoShoppingCartID;
    }

    public void setTodoShoppingCartID(Integer todoShoppingCartID) {
        this.todoShoppingCartID = todoShoppingCartID;
    }

    public String getTodoSaveDate() {
        return todoSaveDate;
    }

    public void setTodoSaveDate(String todoSaveDate) {
        this.todoSaveDate = todoSaveDate;
    }

    public Integer getTodoUserID() {
        return todoUserID;
    }

    public void setTodoUserID(Integer todoUserID) {
        this.todoUserID = todoUserID;
    }

    public Integer getTodoState() {
        return todoState;
    }

    public void setTodoState(Integer todoState) {
        this.todoState = todoState;
    }

}