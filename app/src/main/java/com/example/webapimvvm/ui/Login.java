package com.example.webapimvvm.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.webapimvvm.security.Encryption;
import com.example.webapimvvm.viewmodel.LoginActivityViewModel;
import com.example.webapimvvm.model.User;
import com.example.webapimvvm.R;
import com.example.webapimvvm.databinding.ActivityLoginBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding loginBinding;
    private LoginActivityViewModel loginActivityViewModel;

    private int userID;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        loginBinding.setLoginActivityVeriable(this);

        loginActivityViewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);

    }

    public void login(){
        if(TextUtils.isEmpty(loginBinding.editTextTextLoginEmailAddress.getText().toString())){
            Snackbar.make(findViewById(android.R.id.content),"E-posta boş bırakılamaz",Snackbar.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(loginBinding.editTextTextLoginPassword.getText().toString())){
            Snackbar.make(findViewById(android.R.id.content),"Şifre boş bırakılamaz",Snackbar.LENGTH_SHORT).show();
        }
        else{
            try{
                User userLogin = new User(0,"","", loginBinding.editTextTextLoginPassword.getText().toString(), loginBinding.editTextTextLoginEmailAddress.getText().toString()
                        ,null,"" );

                loginActivityViewModel.getUserList().observe(this, new Observer<ArrayList<User>>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onChanged(ArrayList<User> users) {
                        if(users != null) {
                            User userInfo = null;
                            for (User userItem : users) {
                                userInfo = new User(userItem.getUserID(), userItem.getUserName(), userItem.getUserSurName(), userItem.getUserProfileImage());

                            }
                            if (userInfo.getUserID() == null && userInfo.getUserName().isEmpty()) {
                                Snackbar.make(findViewById(android.R.id.content), "E-posta veya şifreniz hatalı", Snackbar.LENGTH_SHORT).show();
                            } else {
                                String userName = userInfo.getUserName();
                                String userSurName = userInfo.getUserSurName();
                                String userImage = userInfo.getUserProfileImage();

                                sharedPreferences = getSharedPreferences("userNo", MODE_PRIVATE);
                                editor = sharedPreferences.edit();

                                editor.putString("userNo", Encryption.encrypt(String.valueOf(userInfo.getUserID())));
                                editor.putString("userName", Encryption.encrypt(String.valueOf(userName)));
                                editor.putString("userSurName", Encryption.encrypt(String.valueOf(userSurName)));
                                editor.putString("userImage", Encryption.encrypt(String.valueOf(userImage)));
                                editor.commit();

                                Intent intent = new Intent(Login.this, MainActivity.class);
                                intent.putExtra("userImage", userImage);
                                intent.putExtra("userName", userName);
                                intent.putExtra("userSurName", userSurName);
                                startActivity(intent);
                                Login.this.finish();
                            }

                           }
                        }

                    });
                    loginActivityViewModel.loging(userLogin);

                }catch (Exception e){
                    Snackbar.make(findViewById(android.R.id.content),"Bir hatta oluştu lütfen tekrar deneyiniz",Snackbar.LENGTH_SHORT).show();
                }

            }
        }
    public void signInClick(){
        Intent signIn = new Intent(Login.this,SignIn.class);
        startActivity(signIn);
        Login.this.finish();
    }
}