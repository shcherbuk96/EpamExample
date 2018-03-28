package com.example.epamexample.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.epamexample.pojo.ListApi;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by Администратор on 28.03.2018.
 */

public class RealmModel {

    private Realm realm;
    private GetObservable getDataRetrofit;

    public RealmModel(GetObservable getDataRetrofit){
        realm = Realm.getDefaultInstance();
        this.getDataRetrofit=getDataRetrofit;
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

    public void realmClose() {
        if (realm != null) {
            realm.close();
        }
    }

    public void getRealm() {
        ListApi listApi = realm.where(ListApi.class).findFirst();
        Observable<ListApi> listApiObservable = null;
        if (listApi != null) {
            listApiObservable = Observable.just(listApi);
        }
        getDataRetrofit.getBody(listApiObservable, false);
    }
}
