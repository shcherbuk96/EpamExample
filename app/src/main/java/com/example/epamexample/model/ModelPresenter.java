package com.example.epamexample.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.epamexample.api.Api;
import com.example.epamexample.pojo.ListApi;
import com.example.epamexample.pojo.Photo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ModelPresenter {

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
                .baseUrl("https://api.myjson.com/bins/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);

        Call<ListApi> listApiCall = api.listData();
        listApiCall.enqueue(new Callback<ListApi>() {
            @Override
            public void onResponse(@NonNull Call<ListApi> call, @NonNull Response<ListApi> response) {
                if (response.body()!=null && response.body().getList() != null) {
                    addListPhoto(response.body().list);
                    getDataRetrofit.getBody(response.body().getList());
                }

            }

            @Override
            public void onFailure(@NonNull Call<ListApi> call, @NonNull Throwable t) {
                RealmResults<Photo> result = realm.where(Photo.class).findAll();
                getDataRetrofit.getBody(realm.copyFromRealm(result));
            }
        });
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



}
