package com.example.epamexample.model;

import android.util.Log;

import com.example.epamexample.part1.Api;
import com.example.epamexample.part1.ListApi;
import com.example.epamexample.part1.Photo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ModelPart1 {

    Realm realm;
    private GetDataRetrofit getDataRetrofit;

    public ModelPart1() {
    }

    public ModelPart1(GetDataRetrofit getDataRetrofit) {
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
            public void onResponse(Call<ListApi> call, Response<ListApi> response) {
                addListPhoto(response.body().list);
                getDataRetrofit.getBody(response.body().getList());
            }

            @Override
            public void onFailure(Call<ListApi> call, Throwable t) {
                Log.i("onFailure", t.toString());
                getDataRetrofit.getBody(null);
            }
        });
    }

    public void addListPhoto(final List<Photo> photoList) {

        realm.executeTransaction(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {
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
