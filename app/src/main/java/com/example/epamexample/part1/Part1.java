package com.example.epamexample.part1;


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
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.epamexample.presenters.ActionBarPresenter;
import com.example.epamexample.presenters.GetBodyPresent;
import com.example.epamexample.R;
import com.example.epamexample.views.ActionBarView;
import com.example.epamexample.views.GetBodyView;

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
            recyclerAdapter = new RecyclerAdapter(list, getContext());
        } else {
            recyclerAdapter.updateData(list);
        }

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void fail() {
        Toast.makeText(getContext(),"Проверьте интернет соединение",Toast.LENGTH_SHORT).show();
    }
}
