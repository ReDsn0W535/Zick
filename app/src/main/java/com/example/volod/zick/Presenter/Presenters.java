package com.example.volod.zick.Presenter;

import android.content.SharedPreferences;

public interface Presenters {
    interface LoginPresenter{

        void sign_up(String username, String pass, String mail);
        void register(String name, String pass, String mail);
        void saveUserData(SharedPreferences preferences, String key, String data);
        void loadUserData();
    }

    interface FacePresenter{
        void getNextCandidate();
    }
}
