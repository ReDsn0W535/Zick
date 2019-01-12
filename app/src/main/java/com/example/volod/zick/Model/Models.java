package com.example.volod.zick.Model;

import com.example.volod.zick.UserData;

import java.io.IOException;

import io.reactivex.Observable;

public interface Models {
    interface LoginModel{
        Boolean register(UserData UserData) throws IOException;
        Boolean sign_up(UserData UserData) throws IOException;
    }


    interface FaceModel{
        Observable<UserData> getCandidate();
    }
}
