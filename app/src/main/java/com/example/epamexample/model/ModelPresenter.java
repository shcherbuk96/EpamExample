package com.example.epamexample.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.epamexample.api.Api;
import com.example.epamexample.pojo.ListApi;
import com.example.epamexample.pojo.Photo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

import io.realm.RealmResults;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.example.epamexample.task.Constants.BASE_URL;


public class ModelPresenter {
    Observer<ListApi> observer;
    Observable<ListApi> observable;
    private Realm realm;
    private GetDataRetrofit getDataRetrofit;

    public ModelPresenter(GetDataRetrofit getDataRetrofit) {
        this.getDataRetrofit = getDataRetrofit;
    }

    public void retrofitCall() {
        realm = Realm.getDefaultInstance();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();



        Api api = retrofit.create(Api.class);
        observable=api.listData();

    }

    private void addListPhoto(final List<Photo> photoList) {

        realm.executeTransaction(new Realm.Transaction() {

            @Override
            public void execute(@NonNull Realm realm) {
                realm.delete(Photo.class);
                realm.insertOrUpdate(photoList);
            }
        });
    }

    public void realmClose() {
        if (realm != null) {
            realm.close();
        }
    }


    public Observable<ListApi> getObservable() {
        return observable;
    }
}
