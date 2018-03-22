package com.example.epamexample.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.epamexample.views.ItemView;

@InjectViewState
public class ItemPresenter extends MvpPresenter<ItemView> {

    public ItemPresenter() {
        getViewState().showName("Moxy");
        getViewState().showPhoto("moxy");
    }
}
