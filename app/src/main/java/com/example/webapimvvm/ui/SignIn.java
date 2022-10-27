package com.example.webapimvvm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.webapimvvm.model.User;
import com.example.webapimvvm.R;
import com.example.webapimvvm.viewmodel.SignInActivityViewModel;
import com.example.webapimvvm.databinding.ActivitySignInBinding;
import com.google.android.material.snackbar.Snackbar;

public class SignIn extends AppCompatActivity {
    private ActivitySignInBinding signInBinding;
    private SignInActivityViewModel signInActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signInBinding = DataBindingUtil.setContentView(this,R.layout.activity_sign_in);
        signInActivityViewModel = new ViewModelProvider(this).get(SignInActivityViewModel.class);
    }
    public void signIn(){
        if(TextUtils.isEmpty(signInBinding.editTextTextSignInName.getText().toString())){
            Snackbar.make(findViewById(android.R.id.content),"Ad boş bırakılamaz",Snackbar.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(signInBinding.editTextTextSignInSurName.getText().toString())){
            Snackbar.make(findViewById(android.R.id.content),"Soyad boş bırakılamaz",Snackbar.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(signInBinding.editTextTextSignInEmailAddress.getText().toString())){
            Snackbar.make(findViewById(android.R.id.content),"E-posta boş bırakılamaz",Snackbar.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(signInBinding.editTextTextSignInPassword.getText().toString())){
            Snackbar.make(findViewById(android.R.id.content),"Şifre boş bırakılamaz",Snackbar.LENGTH_SHORT).show();
        }else {
            try{
                User user = new User(0, signInBinding.editTextTextSignInName.getText().toString(), signInBinding.editTextTextSignInSurName.getText().toString(), signInBinding.editTextTextSignInPassword.getText().toString(), signInBinding.editTextTextSignInEmailAddress.getText().toString(),null,"");
                signInActivityViewModel.getUserMutableLiveData().observe(this, new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        if(integer != null){
                            int checkUserSignIn = integer;
                            if (checkUserSignIn == 1) {
                                Snackbar.make(findViewById(android.R.id.content),"Kayıt olundu",Snackbar.LENGTH_SHORT).show();
                                thread.start();
                            }else if(checkUserSignIn == 2){
                                Snackbar.make(findViewById(android.R.id.content),"Bu mail ile üyelik bulunmaktadır",Snackbar.LENGTH_SHORT).show();
                            }
                            else{
                                Snackbar.make(findViewById(android.R.id.content),"Kayıt olunurken bir hatayla karşılaşıldı",Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                signInActivityViewModel.signing(user);
            }catch (Exception e){
                e.printStackTrace();
                Snackbar.make(findViewById(android.R.id.content),"Kayıt olunurken bir hatayla karşılaşıldı",Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    Thread thread = new Thread(){
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                SignIn.this.finish();
                Intent intent = new Intent(SignIn.this,Login.class);
                startActivity(intent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}