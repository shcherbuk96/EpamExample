package com.example.epamexample.views;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Yauheni_Shcharbuk on 3/20/2018.
 */

public interface ItemView extends MvpView {

    void showName(String namePhoto);
    void showPhoto(String urlPhoto);

}
