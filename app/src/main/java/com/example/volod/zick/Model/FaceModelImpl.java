package com.example.volod.zick.Model;

import android.util.Log;

import com.example.volod.zick.Retrofit.ZickServer;
import com.example.volod.zick.UserData;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class FaceModelImpl implements Models.FaceModel {
    @Override
    public Observable<UserData> getCandidate() {
        Log.e("FACE MODEL TAG", Thread.currentThread().getName() + "  getCandidate");

        return Observable.just(ZickServer.getInstance().getApi())
                .observeOn(Schedulers.computation())
                .flatMap(x -> {
                    Log.i("FACE MODEL TAG", Thread.currentThread().getName());
                    UserData userData = (UserData) x.getCandidate().execute().body().get(0);
                    return Observable.just(userData);
                });
       /* try {
            return Observable.just(ZickServer.getInstance().getApi().getCandidate().execute().body());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }*/
    }
}
