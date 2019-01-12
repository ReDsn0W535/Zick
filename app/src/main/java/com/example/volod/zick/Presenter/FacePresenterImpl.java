package com.example.volod.zick.Presenter;

import android.util.Log;

import com.example.volod.zick.Model.FaceModelImpl;
import com.example.volod.zick.Model.Models;
import com.example.volod.zick.UserData;
import com.example.volod.zick.View.FaceInterface;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FacePresenterImpl implements Presenters.FacePresenter {
    private FaceInterface faceInterface;
    private Models.FaceModel faceModel;



    public FacePresenterImpl(FaceInterface faceInterface){
        this.faceInterface = faceInterface;
        faceModel = new FaceModelImpl();
    }

    public void getNextCandidate() {
        faceModel.getCandidate()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userData -> {
                            Log.i("FACE PRESENTER TAG", "onNext");
                            Log.i("FACE PRESENTER TAG", Thread.currentThread().getName());
                            faceInterface.ViewUserData(userData);
                        },
                        t ->Log.e("FACE PRESENTER TAG", "onError " + t),
                        () -> Log.i("FACE PRESENTER TAG", "onComplete"));

    }



    /*new Observer<UserData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserData userData) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }*/
}
