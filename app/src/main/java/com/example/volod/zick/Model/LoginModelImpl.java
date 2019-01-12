package com.example.volod.zick.Model;

import android.util.Log;

import com.example.volod.zick.Retrofit.ZickServer;
import com.example.volod.zick.UserData;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModelImpl implements Models.LoginModel {

    public LoginModelImpl(){
    }
    @Override
    public Boolean register(UserData UserData) throws IOException {
        Log.e("TAG", Thread.currentThread().getName() + "  register");
        Call<Boolean> netAnswer = ZickServer.getInstance().getApi().register(UserData.getUsername(), UserData.getPassword(), "", UserData.getMail());
        Response response = null;
        try {
            response = netAnswer.execute();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return response.body().equals(true);
    }

    @Override
    public Boolean sign_up(UserData UserData) throws IOException{
        Log.e("TAG", Thread.currentThread().getName() + "  sign_up");
        prepareUserDataForTransfer(UserData);
        Call<Boolean> netAnswer = ZickServer.getInstance().getApi().sign_up(UserData.getMail());
        Response response = null;
        try {
            response = netAnswer.execute();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        Boolean bool = (Boolean) response.body();
        return bool;
    }





    void prepareUserDataForTransfer(UserData userData){
        userData.setMail("'" + userData.getMail() + "'");
    }
}
