package com.example.webapimvvm.Api;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;
    public static Retrofit getRetrofit(String baseUrl){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }

}
