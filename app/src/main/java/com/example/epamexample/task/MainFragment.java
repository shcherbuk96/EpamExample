package com.example.epamexample.task;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.epamexample.R;
import com.example.epamexample.adapter.RecyclerAdapter;
import com.example.epamexample.pojo.Photo;
import com.example.epamexample.presenters.MainPresenter;
import com.example.epamexample.views.GetBodyView;

import java.util.List;


public class MainFragment extends MvpAppCompatFragment implements GetBodyView {
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    @InjectPresenter
    MainPresenter getBodyPresent;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Task");
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
        Toast.makeText(getContext(), "Проверьте интернет соединение", Toast.LENGTH_SHORT).show();
    }
}
