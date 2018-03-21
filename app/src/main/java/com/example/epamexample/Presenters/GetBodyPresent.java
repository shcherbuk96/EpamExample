package com.example.epamexample.Presenters;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.epamexample.Part1.Api;
import com.example.epamexample.Part1.ListApi;
import com.example.epamexample.Part1.Photo;
import com.example.epamexample.Part1.RecyclerAdapter;
import com.example.epamexample.R;
import com.example.epamexample.Views.GetBodyView;
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
 * Created by Yauheni_Shcharbuk on 3/20/2018.
 */

@InjectViewState
public class GetBodyPresent extends MvpPresenter<GetBodyView> {
    Realm realm;
    public GetBodyPresent(){
        realm=Realm.getDefaultInstance();
        retrofitCall();
    }

    void retrofitCall(){
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
                if (response.isSuccessful()) {
                    getViewState().showRetrofit(response.body().getList());
                } else {
                    Log.i("else", "response code " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ListApi> call, Throwable t) {
                Log.i("onFailure", t.toString());
                if (realm.where(Photo.class).findFirst() != null) {
                    Log.i("DB", "DB!=null");
                    RealmResults<Photo> result = realm.where(Photo.class).findAll();
                    getViewState().showRetrofit(realm.copyFromRealm(result));
                }
            }
        });
    }
}


