package com.example.webapimvvm.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.webapimvvm.security.Encryption;
import com.example.webapimvvm.viewmodel.SplashActivityViewModel;
import com.example.webapimvvm.model.User;

import java.net.HttpURLConnection;
import java.net.URL;

public class SplashActivity extends AppCompatActivity {
    private SplashActivityViewModel splashActivityViewModel;
    private boolean result;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        splashActivityViewModel = new ViewModelProvider(this).get(SplashActivityViewModel.class);

        myTask();
    }

    public void myTask(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://google.com");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() == 200) {

                        result =  true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    result =  false;
                }

                runOnUiThread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void run() {

                        if (!result) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
                            builder.setMessage("Bağlantınızı kontrol edip tekrar deneyiniz");
                            builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    myTask();
                                }
                            });
                            builder.setNegativeButton("Çıkış", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                }
                            });

                            AlertDialog dialog = builder.create();
                            dialog.show();

                        } else {
                            sharedPreferences = getSharedPreferences("userNo",MODE_PRIVATE);
                            editor = sharedPreferences.edit();
                            int userNo = Integer.parseInt(Encryption.decrypt(sharedPreferences.getString("userNo","GuZMgQ2zRFt6sFV53NLtnA==").toString()));

                            String userName = Encryption.decrypt(sharedPreferences.getString("userName","GuZMgQ2zRFt6sFV53NLtnA==").toString());
                            String userSurName = Encryption.decrypt(sharedPreferences.getString("userSurName","GuZMgQ2zRFt6sFV53NLtnA==").toString());
                            String userImage = Encryption.decrypt(sharedPreferences.getString("userImage","GuZMgQ2zRFt6sFV53NLtnA==").toString());

                            Intent intentLogin = new Intent(SplashActivity.this, Login.class);
                            Intent intentMain = new Intent(SplashActivity.this,MainActivity.class);

                            if(userNo == 0){
                                startActivity(intentLogin);
                                finish();
                            }else{
                                try {
                                    User userSplash= new User(userNo,"","","","",null,"" );
                                    splashActivityViewModel.getUserTimeOut().observe(SplashActivity.this, new Observer<Integer>() {
                                        @Override
                                        public void onChanged(Integer integer) {
                                            int checkUserTimeOut = integer;
                                            if (checkUserTimeOut == 1) {
                                                intentMain.putExtra("userName",userName);
                                                intentMain.putExtra("userSurName",userSurName);
                                                intentMain.putExtra("userImage",userImage);

                                                startActivity(intentMain);
                                                finish();
                                            }
                                            else{
                                                startActivity(intentLogin);
                                                finish();
                                            }
                                        }
                                    });
                                    splashActivityViewModel.getUserTimeOutRemaining(userSplash);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    }
                });
            }
        }).start();
    }
}
