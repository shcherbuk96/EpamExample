package com.example.epamexample.Part1;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.epamexample.Presenters.ActionBarPresenter;
import com.example.epamexample.R;
import com.example.epamexample.Views.ActionBarView;
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


public class Part1 extends MvpAppCompatFragment implements ActionBarView {
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    Realm realm;

    @InjectPresenter
    ActionBarPresenter actionBarPresenter;

    static public boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_part1, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        actionBarPresenter.getViewState().showActionBar("Hi, moxy");


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/bins/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);

        if (isOnline(getContext())) {
            Call<ListApi> listApiCall = api.listData();
            listApiCall.enqueue(new Callback<ListApi>() {
                @Override
                public void onResponse(Call<ListApi> call, Response<ListApi> response) {
                    if (response.isSuccessful()) {

                        Log.i("if", "response " + response.body());
                        addListPhoto(response.body().getList());
                        if (recyclerAdapter == null) {
                            Log.i("recyclerAdapter", "null");
                            recyclerAdapter = new RecyclerAdapter(response.body().getList(), getContext());

                        } else {
                            Log.i("recyclerAdapter", "!null");
                            recyclerAdapter.updateData(response.body().getList());
                        }


                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setAdapter(recyclerAdapter);
                    } else {
                        Log.i("else", "response code " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<ListApi> call, Throwable t) {
                    Log.i("onFailure", t.toString());
                }
            });
        } else {
            if (realm.where(Photo.class).findFirst() != null) {
                Log.i("DB", "DB!=null");
                RealmResults<Photo> result = realm.where(Photo.class).findAll();
                recyclerAdapter = new RecyclerAdapter(realm.copyFromRealm(result), getContext());
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(recyclerAdapter);
            } else {
                Log.i("DB", "DB==null");
                view = inflater.inflate(R.layout.activity_internet_connection, container, false);
            }

        }


        return view;
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (realm != null) {
            realm.close();
        }
    }

    @Override
    public void showActionBar(String actionBar) {
        getActivity().setTitle(actionBar);
    }
}
