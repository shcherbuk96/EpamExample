package com.example.epamexample.views;

import com.arellomobile.mvp.MvpView;
import com.example.epamexample.pojo.Photo;

import java.util.List;

public interface GetBodyView extends MvpView {

    void showRetrofit(List<Photo> list);

    void fail();
}
