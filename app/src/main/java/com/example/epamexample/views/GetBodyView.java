package com.example.epamexample.views;

import com.arellomobile.mvp.MvpView;
import com.example.epamexample.part1.Photo;

import java.util.List;

/**
 * Created by Yauheni_Shcharbuk on 3/20/2018.
 */

public interface GetBodyView extends MvpView{

    void showRetrofit(List<Photo> list);
    void fail();
}
