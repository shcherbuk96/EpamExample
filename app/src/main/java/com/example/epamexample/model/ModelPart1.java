package com.example.epamexample.model;

import android.util.Log;

import com.example.epamexample.part1.Api;
import com.example.epamexample.part1.ListApi;
import com.example.epamexample.part1.Photo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yauheni_Shcharbuk on 3/21/2018.
 */

public class ModelPart1 {

    Realm realm;
    private GetDataRetrofit getDataRetrofit;

    public ModelPart1() {
    }

    public ModelPart1(GetDataRetrofit getDataRetrofit) {
        this.getDataRetrofit = getDataRetrofit;
        retrofitCall();
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
                getDataRetrofit.getBody(response.body().getList());
            }

            @Override
            public void onFailure(Call<ListApi> call, Throwable t) {
                Log.i("onFailure", t.toString());
                if (realm.where(Photo.class).findFirst() != null) {
                    Log.i("DB", "DB!=null");
                    RealmResults<Photo> result = realm.where(Photo.class).findAll();
                    getDataRetrofit.getBody(realm.copyFromRealm(result));
                }
            }
        });
    }


}
