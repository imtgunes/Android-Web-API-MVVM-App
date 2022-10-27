package com.example.webapimvvm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.webapimvvm.model.Cart;
import com.example.webapimvvm.viewmodel.CartActivityViewModel;
import com.example.webapimvvm.adapter.CartAdapter;
import com.example.webapimvvm.R;
import com.example.webapimvvm.model.ShoppingCart;
import com.example.webapimvvm.model.ToDo;
import com.example.webapimvvm.viewmodel.TodoActivityViewModel;

import com.example.webapimvvm.databinding.ActivityCartBinding;
import com.example.webapimvvm.databinding.BottomDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private CartActivityViewModel cartActivityViewModel;
    private TodoActivityViewModel todoActivityViewModel;
    private ActivityCartBinding activityCartBinding;

    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Cart> arrayListCart;
    private CartAdapter adapter;

    private int shoppingCartID = 0;
    private int userID;

    private String toDoNote = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        activityCartBinding = DataBindingUtil.setContentView(this,R.layout.activity_cart);
        cartActivityViewModel = new ViewModelProvider(this).get(CartActivityViewModel.class);
        activityCartBinding.setCartActivityVeriable(this);
        todoActivityViewModel = new ViewModelProvider(this).get(TodoActivityViewModel.class);

        userID = getIntent().getIntExtra("userID",0);
        shoppingCartID = getIntent().getIntExtra("shoppingCartID",0);

        activityCartBinding.textViewCartEmpty.setVisibility(View.GONE);
        activityCartBinding.recyclerViewCart.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        activityCartBinding.recyclerViewCart.setLayoutManager(layoutManager);
        arrayListCart = new ArrayList<>();

        getShoppingCart();

    }

    public void getShoppingCart(){
        cartActivityViewModel.getCart().observe(this, new Observer<ArrayList<Cart>>() {
            @Override
            public void onChanged(ArrayList<Cart> carts) {
                if(carts != null){
                    arrayListCart = carts;
                    adapter = new CartAdapter(arrayListCart,CartActivity.this);
                    activityCartBinding.recyclerViewCart.setAdapter(adapter);
                }else{

                }
            }
        });
        cartActivityViewModel.getCartList(shoppingCartID);
    }

    public void myTask(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    LocalDateTime timeNow = LocalDateTime.now(
                    );
                    ShoppingCart shoppingCart = new ShoppingCart(shoppingCartID,toDoNote,1,userID);
                    ToDo toDo = new ToDo(0,shoppingCartID,String.valueOf(timeNow),userID,0);
                    try {
                        todoActivityViewModel.addToDo(toDo,shoppingCartID,shoppingCart);

                        Snackbar.make(findViewById(android.R.id.content),"Liste kaydedildi",Snackbar.LENGTH_SHORT).show();
                        thread.start();
                    } catch (IOException e) {
                        Snackbar.make(findViewById(android.R.id.content),"Liste kaydedilirken hata oluştu",Snackbar.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }).start();
    }

    Thread thread = new Thread(){
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                CartActivity.this.finish();
                Intent intent = new Intent(CartActivity.this,MainActivity.class);
                startActivity(intent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    public void addNoteButton(){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(CartActivity.this,R.style.MyTransparentBottomSheetDialogTheme);
        BottomDialogBinding bottomDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(CartActivity.this),
                R.layout.bottom_dialog, null, false);
        bottomSheetDialog.setContentView(bottomDialogBinding.getRoot());
        bottomSheetDialog.setCanceledOnTouchOutside(true);

        bottomDialogBinding.imageButtonExitBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialogBinding.editTextCartNoteList.setText("");
                bottomSheetDialog.cancel();
            }
        });

        bottomDialogBinding.buttonAddToCartNoteBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toDoNote = String.valueOf(bottomDialogBinding.editTextCartNoteList.getText());
                bottomSheetDialog.cancel();
            }
        });

        if(bottomSheetDialog.getWindow() != null)
            bottomSheetDialog.getWindow().setDimAmount(0);
        bottomSheetDialog.show();
    }
}