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
import com.example.epamexample.Presenters.GetBodyPresent;
import com.example.epamexample.R;
import com.example.epamexample.Views.ActionBarView;
import com.example.epamexample.Views.GetBodyView;

import java.util.List;

import io.realm.Realm;


public class Part1 extends MvpAppCompatFragment implements ActionBarView, GetBodyView {
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    Realm realm;

    @InjectPresenter
    ActionBarPresenter actionBarPresenter;

    @InjectPresenter
    GetBodyPresent getBodyPresent;

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

    @Override
    public void showRetrofit(List<Photo> list) {

        if (recyclerAdapter == null) {
            addListPhoto(list);
            Log.i("recyclerAdapter", "null");
            recyclerAdapter = new RecyclerAdapter(list, getContext());

        } else {
            Log.i("recyclerAdapter", "!null");
            recyclerAdapter.updateData(list);
        }

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }
}
