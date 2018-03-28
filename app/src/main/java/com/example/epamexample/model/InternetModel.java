package com.example.epamexample.model;

import android.util.Log;

import com.example.epamexample.App;
import com.example.epamexample.api.Api;

import javax.inject.Inject;

import io.realm.Realm;


public class InternetModel {
    @Inject
    Api api;
    private Realm realm;
    private GetObservable getDataRetrofit;

    public InternetModel(GetObservable getDataRetrofit) {
        realm = Realm.getDefaultInstance();
        App.getRetrofitComponent().inject(this);
        this.getDataRetrofit = getDataRetrofit;
    }

    public void retrofitCall() {
        getDataRetrofit.getBody(api.listData(), true);
        Log.i("MainPresenter", "1");
    }
}
