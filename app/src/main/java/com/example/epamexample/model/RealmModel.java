package com.example.epamexample.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.epamexample.pojo.ListApi;

import io.reactivex.Flowable;
import io.realm.Realm;

public class RealmModel {

    private Realm realm;
    private GetObservable getDataRetrofit;

    public RealmModel(GetObservable getDataRetrofit) {
        realm = Realm.getDefaultInstance();
        this.getDataRetrofit = getDataRetrofit;
    }

    public void addListPhoto(final ListApi photoList) {
        Log.i("MainPresenter", "2");
        realm.executeTransaction(new Realm.Transaction() {

            @Override
            public void execute(@NonNull Realm realm) {
                realm.delete(ListApi.class);
                realm.insertOrUpdate(photoList);
            }
        });
    }


    public void getRealm() {
        ListApi listApi = realm.where(ListApi.class).findFirst();
        Flowable<ListApi> flowable = null;
        if (listApi != null) {
            //flowable=Flowable.just(listApi);
            flowable = listApi.asFlowable();
        }
        getDataRetrofit.getBody(flowable, false);
    }
}
