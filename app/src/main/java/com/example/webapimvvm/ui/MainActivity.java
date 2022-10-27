package com.example.webapimvvm.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.webapimvvm.model.Category;
import com.example.webapimvvm.adapter.CategoryAdapter;
import com.example.webapimvvm.security.Encryption;
import com.example.webapimvvm.viewmodel.MainActivityViewModel;
import com.example.webapimvvm.R;
import com.example.webapimvvm.model.User;
import com.example.webapimvvm.databinding.ActivityMainBinding;
import com.example.webapimvvm.databinding.NavHeaderMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel mainActivityViewModel;

    private ActivityMainBinding activityMainBinding;
    private NavHeaderMainBinding navHeaderMainBinding;

    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Category> arrayListCategory;
    private CategoryAdapter adapter;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int userID;
    private int shoppingCartID = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        View headerView = activityMainBinding.navigationView.getHeaderView(0);
        navHeaderMainBinding = NavHeaderMainBinding.bind(headerView);


        activityMainBinding.recyclerViewMain.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 3);
        activityMainBinding.recyclerViewMain.setLayoutManager(layoutManager);
        arrayListCategory = new ArrayList<>();

        sharedPreferences = getSharedPreferences("userNo",MODE_PRIVATE);
        userID = Integer.parseInt(Encryption.decrypt(sharedPreferences.getString("userNo","GuZMgQ2zRFt6sFV53NLtnA==").toString()));

        activityMainBinding.toolbar.setTitle("");
        setSupportActionBar(activityMainBinding.toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, activityMainBinding.drawer, activityMainBinding.toolbar, R.string.nav_open, R.string.nav_close);
        activityMainBinding.drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        activityMainBinding.navigationView.setItemIconTintList(null);
        activityMainBinding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.itemCart){
                    mainActivityViewModel.getShoppingCartID().observe(MainActivity.this, new Observer<Integer>() {
                        @Override
                        public void onChanged(Integer integer) {
                            shoppingCartID = integer;
                        }
                    });

                    if(shoppingCartID != 0){
                        Intent intent = new Intent(MainActivity.this,CartActivity.class);
                        intent.putExtra("userID",userID);
                        intent.putExtra("shoppingCartID",shoppingCartID);
                        startActivity(intent);
                    }

                    if (activityMainBinding.drawer.isDrawerOpen(GravityCompat.START)){
                        activityMainBinding.drawer.closeDrawer(GravityCompat.START);
                    }

                }
                else if(item.getItemId() == R.id.itemTodoList){
                    Intent intent = new Intent(MainActivity.this,ToDoActivity.class);
                    intent.putExtra("userID",userID);
                    startActivity(intent);
                    if (activityMainBinding.drawer.isDrawerOpen(GravityCompat.START)){
                        activityMainBinding.drawer.closeDrawer(GravityCompat.START);
                    }
                }
                else if(item.getItemId() == R.id.itemTodoListComplated){
                    Intent intent = new Intent(MainActivity.this,ToDoActivityComplated.class);
                    intent.putExtra("userID",userID);
                    startActivity(intent);
                    if (activityMainBinding.drawer.isDrawerOpen(GravityCompat.START)){
                        activityMainBinding.drawer.closeDrawer(GravityCompat.START);
                    }
                }
                return false;
            }
        });

        mainActivityViewModel.getCategories().observe(this, new Observer<ArrayList<Category>>() {
            @Override
            public void onChanged(ArrayList<Category> categories) {
                if(categories != null){
                    arrayListCategory = categories;
                    adapter = new CategoryAdapter(arrayListCategory,MainActivity.this);
                    activityMainBinding.recyclerViewMain.setAdapter(adapter);
                    mainActivityViewModel.getShoppingCartIDFromApi(userID);
                }else{

                }
            }
        });
        mainActivityViewModel.getMainCategories();

        mainActivityViewModel.getUserInfo().observe(this, new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                if(users != null){
                    String userName = "";
                    String userSurName = "";
                    String userImage = "";
                    for (User user: users){
                        userName = user.getUserName();
                        userSurName = user.getUserSurName();
                        userImage = user.getUserProfileImage();
                    }
                    navHeaderMainBinding.textViewUserName.setText(userName);
                    navHeaderMainBinding.textViewUserSurName.setText(userSurName);
                    Glide.with(MainActivity.this)
                            .asBitmap()
                            .load(userImage)
                            .centerCrop()
                            .into(navHeaderMainBinding.imageViewUserImage);
                }else{

                }
            }
        });
        mainActivityViewModel.getUserInfoList(userID);

        navHeaderMainBinding.imageButtonUserLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("userNo",MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putString("userNo","GuZMgQ2zRFt6sFV53NLtnA==");
                editor.commit();
                Intent intent = new Intent(MainActivity.this,SplashActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void logOut(){
        sharedPreferences = getSharedPreferences("userNo",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("userNo","GuZMgQ2zRFt6sFV53NLtnA==");
        editor.commit();
        Intent intent = new Intent(MainActivity.this,SplashActivity.class);
        startActivity(intent);
        finish();
    }
}