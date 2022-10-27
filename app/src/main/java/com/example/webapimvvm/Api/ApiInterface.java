package com.example.webapimvvm.Api;

import com.example.webapimvvm.model.Cart;
import com.example.webapimvvm.model.Category;
import com.example.webapimvvm.model.Product;
import com.example.webapimvvm.model.ShoppingCart;
import com.example.webapimvvm.model.ShoppingCarts;
import com.example.webapimvvm.model.ToDo;
import com.example.webapimvvm.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("api/Category/Category")
    Call<ArrayList<Category>> categories();

    @GET("api/Product/ProductsByCategory")
    Call<ArrayList<Product>> products(@Query("category") int category);

    @GET("api/ShoppingCarts/ShoppingCarts/")
    Call<ArrayList<Cart>> getShoppingCartActivity(@Query("cartID") int cartID);

    @GET("api/ShoppingCart/ShoppingCartState")
    Call<Integer> getShoppingCartID(@Query("userID") int userID);

    @GET("api/ShoppingCart/ShoppingCart")
    Call<ArrayList<ShoppingCart>> getShoppingCartNote(@Query("shoppingCartID") int shoppingCartID);

    @GET("api/ToDoList/ToDoList/{todoUserID}&{todoState}")
    Call<ArrayList<ToDo>> getTodoNotComplated(@Path("todoUserID") int todoUserID, @Path("todoState") int todoState);

    @GET("/api/Users/User/{userID}")
    Call<ArrayList<User>> getUserInfo(@Path("userID") int userID);

    @POST("api/ShoppingCarts/ShoppingCarts")
    Call<Cart> addShoppingCarts(@Body Cart shoppingCarts);

    @POST("api/Users/Users/User")
    Call<ArrayList<User>> loginUser(@Body User users);

    @POST("api/Users/Users/LoginTimeOut")
    Call<Integer> chekTimeOutloginUser(@Body User users);

    @POST("api/Users/Users")
    Call<Integer> signUser(@Body User users);

    @POST("api/ToDoList/ToDoList")
    Call<ArrayList<ToDo>> addToDo(@Body ToDo toDo);

    @PUT("api/ToDoList/UpdateTodo/{todoID}")
    Call<ToDo> updateToDo(@Path("todoID") int id,@Body ToDo toDo);

    @PUT("api/ShoppingCart/{id}")
    Call<ShoppingCart> updateShoppingCart(@Path("id") int id,@Body ShoppingCart shoppingCart);

    @DELETE("api/ShoppingCart/{id}")
    Call<ShoppingCart> deleteShoppingCart(@Path("id") int id);

    @DELETE("api/ShoppingCarts/{id}")
    Call<ShoppingCarts> removeItemOnCart(@Path("id") int id);


}
