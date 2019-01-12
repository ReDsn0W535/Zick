package com.example.volod.zick.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZickServer {
    private static ZickServer ourInstance;
    private Retrofit retrofit;
    private static ZickApi zickApi;
    public static ZickServer getInstance() {
        if (ourInstance == null){
            ourInstance = new ZickServer();
        }
        return ourInstance;
    }

    private ZickServer() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.2/Zick/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setLenient()
                        .create()))
                .client(new OkHttpClient.Builder().
                        addInterceptor(new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .build();
        zickApi = retrofit.create(ZickApi.class);
    }

    public ZickApi getApi(){
        return zickApi;
    }
}
