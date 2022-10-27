package com.example.webapimvvm.Api;

public class ApiUtils {
    public static final String BASE_URL = "https://448a-195-177-206-66.eu.ngrok.io/";

    public static ApiInterface getApi(){

        return  ApiClient.getRetrofit(BASE_URL).create(ApiInterface.class);
    }
}
