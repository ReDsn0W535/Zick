package com.example.volod.zick.Retrofit;


import com.example.volod.zick.UserData;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ZickApi {
    @POST("register.php")
    Call<Boolean> register(@Query("username") String username, @Query("password") String pass, @Query("sex") String sex,
                        @Query("mail") String mail);

    @POST("login.php")
    Call<Boolean> sign_up(@Query("mail") String mail);

    @POST("getrandomuser.php")
    Call<List<UserData>> getCandidate();
}
