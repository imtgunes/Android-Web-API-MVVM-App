package com.example.webapimvvm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.webapimvvm.model.Product;
import com.example.webapimvvm.viewmodel.ProductActivityViewModel;
import com.example.webapimvvm.adapter.ProductAdapter;
import com.example.webapimvvm.R;
import com.example.webapimvvm.databinding.ActivityProductBinding;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    private ProductActivityViewModel productActivityViewModel;
    private ActivityProductBinding activityProductBinding;

    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Product> arrayListProduct;
    private ProductAdapter adapter;

    private int categoryID;
    private int shoppingCartID = 0;
    private int userID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        categoryID = getIntent().getIntExtra("categoryID",0);
        userID = getIntent().getIntExtra("userID",0);

        activityProductBinding = DataBindingUtil.setContentView(this,R.layout.activity_product);
        productActivityViewModel = new ViewModelProvider(this).get(ProductActivityViewModel.class);

        activityProductBinding.setProductActivityVeriable(this);

        activityProductBinding.recyclerViewProduct.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(ProductActivity.this);
        activityProductBinding.recyclerViewProduct.setLayoutManager(layoutManager);
        arrayListProduct = new ArrayList<>();


        productActivityViewModel.getProducts().observe(this, new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> products) {
                if(products != null){
                    arrayListProduct = products;
                    adapter = new ProductAdapter(arrayListProduct,ProductActivity.this, userID, shoppingCartID);
                    activityProductBinding.recyclerViewProduct.setAdapter(adapter);
                }else{

                }
            }
        });


        productActivityViewModel.getShoppingCartID().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                shoppingCartID = integer;
                productActivityViewModel.getProductList(categoryID);

            }
        });
        productActivityViewModel.getShoppingCart(userID);


    }


    public void buttonCartClick(){
        Intent intent = new Intent(ProductActivity.this,CartActivity.class);
        intent.putExtra("shoppingCartID",shoppingCartID);
        intent.putExtra("userID",userID);
        startActivity(intent);
    }

}