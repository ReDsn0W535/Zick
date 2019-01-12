package com.example.volod.zick.Presenter;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.example.volod.zick.Model.LoginModelImpl;
import com.example.volod.zick.Model.Models;
import com.example.volod.zick.View.LoginInterface;
import com.example.volod.zick.UserData;

import java.io.IOException;

import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenterImpl implements Presenters.LoginPresenter {
    SharedPreferences preferences;
    private UserData UserData = new UserData();
    private Observable<String> auth;
    private Observer<String> onAuth;
    private Models.LoginModel model;
    LoginInterface loginInterface;
    public LoginPresenterImpl(LoginInterface loginInterface) {
        this.loginInterface = loginInterface;
        model = new LoginModelImpl();
        onAuth = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("ONAUTH", "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.e("ONAUTH", "onNext");
                if (s.equals("failed registration")){
                    class errorDialogTask extends AsyncTask<Void,Void,Void>{
                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            loginInterface.getRegistrationErrorWindow();
                        }

                        @Override
                        protected Void doInBackground(Void... voids) {
                            return null;
                        }
                    }

                    errorDialogTask errorDialogTask = new errorDialogTask();
                    errorDialogTask.execute();
                } else if (s.equals("sucess logon")){
                    loginInterface.startFaceActivity();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("ONAUTH", Thread.currentThread().getName());
                Log.e("ONAUTH", "onError:  " + e.toString());
                if (e instanceof IOException){
                    class disconnectDialogTask extends AsyncTask<Void,Void,Void>{
                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            loginInterface.getDisconnectErrorWindow();
                        }

                        @Override
                        protected Void doInBackground(Void... voids) {
                            return null;
                        }
                    }

                    disconnectDialogTask disconnectDialogTask = new disconnectDialogTask();
                    disconnectDialogTask.execute();
                }
            }

            @Override
            public void onComplete() {
                Log.e("ONAUTH", "onComplete");
                Log.e("ONAUTH", Thread.currentThread().getName());

            }
        };
    }

    @Override
    public void sign_up(String username, String pass, String mail) {
        auth = Observable.just("sign_up");
        AtomicBoolean res = new AtomicBoolean(false);
        auth.observeOn(Schedulers.newThread()).flatMap(x -> {
            UserData.setUsername(username);
            UserData.setPassword(pass);
            UserData.setMail(mail);
            res.set(model.sign_up(UserData));
            Log.d("SIGN UP TAG", "regRes = " + res.get());
            if (res.get()) return Observable.just("sucess logon");
            else return Observable.just("invalid logon");
        }).subscribe(onAuth);
    }

    @Override
    public void register(String username, String pass, String mail) {
        auth = Observable.just("register");
        auth.observeOn(Schedulers.newThread()).flatMap(x -> {
            UserData.setUsername(username);
            UserData.setPassword(pass);
            UserData.setMail(mail);
            Boolean regRes = model.register(UserData);
            Log.d("REGISTER TAG", "regRes = " + regRes);
            if (regRes) return Observable.just("success registration");
            else return Observable.just("failed registration");
            }).subscribe(onAuth);
    }

    @Override
    public void saveUserData(SharedPreferences preferences, String key, String data) {
        Observable<String> saveProc = Observable.just(data);
        saveProc.map(x -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, x);
            editor.apply();
            Log.e("SAVEUSERDATA TAG", "Key " + key + " writed");
            return x;
        }).subscribe();
    }

    @Override
    public void loadUserData() {

    }
}
