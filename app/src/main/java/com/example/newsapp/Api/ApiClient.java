package com.example.newsapp.Api;

import java.sql.ClientInfoStatus;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static Retrofit retrofit;
    private static ApiClient client;

    private ApiClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ApiClient getInstance(){
        if (client == null){
            client = new ApiClient();
        }
        return client;
    }

    public ApiTokenService getApi(){
        return retrofit.create(ApiTokenService.class);
    }
}
