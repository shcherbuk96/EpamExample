package com.example.epamexample.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.epamexample.api.Api;
import com.example.epamexample.pojo.ListApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.realm.Realm;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.epamexample.task.Constants.BASE_URL;


public class Model {
    private Realm realm;
    private GetDataRetrofit getDataRetrofit;

    public Model(GetDataRetrofit getDataRetrofit) {
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
        getDataRetrofit.getBody(api.listData(), true);
        Log.i("GetBodyPresent", "1");
    }

    public void addListPhoto(final ListApi photoList) {
        Log.i("GetBodyPresent", "2");
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
