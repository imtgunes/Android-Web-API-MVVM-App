package com.example.webapimvvm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.webapimvvm.model.Cart;
import com.example.webapimvvm.R;
import com.example.webapimvvm.model.ShoppingCart;
import com.example.webapimvvm.viewmodel.ShoppingCartViewModel;
import com.example.webapimvvm.viewmodel.TodoActivityViewModel;
import com.example.webapimvvm.adapter.TodoDetailAdapter;
import com.example.webapimvvm.databinding.ActivityToDoListDetailBinding;

import java.util.ArrayList;

public class ToDoListDetailActivity extends AppCompatActivity {
    private TodoActivityViewModel todoActivityViewModel;
    private ActivityToDoListDetailBinding activityToDoListDetailBinding;
    private ShoppingCartViewModel shoppingCartViewModel;

    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Cart> arrayListCart;
    private TodoDetailAdapter adapter;

    private String shoppingCartNote = "";
    private int shoppingCartID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list_detail);

        shoppingCartID = getIntent().getIntExtra("shoppingCartID",0);

        activityToDoListDetailBinding = DataBindingUtil.setContentView(this,R.layout.activity_to_do_list_detail);
        todoActivityViewModel = new ViewModelProvider(this).get(TodoActivityViewModel.class);
        activityToDoListDetailBinding.setToDoListDetailActivityVeriable(this);
        shoppingCartViewModel = new ViewModelProvider(this).get(ShoppingCartViewModel.class);

        activityToDoListDetailBinding.recyclerViewToDoDetail.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        activityToDoListDetailBinding.recyclerViewToDoDetail.setLayoutManager(layoutManager);
        arrayListCart = new ArrayList<>();

        todoActivityViewModel.getTodoDetail().observe(this, new Observer<ArrayList<Cart>>() {
            @Override
            public void onChanged(ArrayList<Cart> carts) {
                if(carts != null){
                    arrayListCart = carts;
                    adapter = new TodoDetailAdapter(arrayListCart,ToDoListDetailActivity.this,shoppingCartNote);
                    activityToDoListDetailBinding.recyclerViewToDoDetail.setAdapter(adapter);
                }else{

                }
            }
        });


        shoppingCartViewModel.getCartNoteMutableLiveData().observe(this, new Observer<ArrayList<ShoppingCart>>() {
            @Override
            public void onChanged(ArrayList<ShoppingCart> shoppingCarts) {
                for (ShoppingCart cart: shoppingCarts){
                    shoppingCartNote = cart.getShoppingCartNote();

                }
                todoActivityViewModel.getTodoDetailList(shoppingCartID);
            }
        });
        shoppingCartViewModel.getShoppingCartNote(shoppingCartID);

    }
}