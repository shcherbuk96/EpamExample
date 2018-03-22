package com.example.epamexample.views;

import com.arellomobile.mvp.MvpView;

public interface ItemView extends MvpView {

    void showName(String namePhoto);

    void showPhoto(String urlPhoto);

}
