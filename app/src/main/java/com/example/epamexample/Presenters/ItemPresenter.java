package com.example.epamexample.Presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.epamexample.Views.ItemView;

/**
 * Created by Yauheni_Shcharbuk on 3/20/2018.
 */
@InjectViewState
public class ItemPresenter extends MvpPresenter<ItemView> {

    public ItemPresenter() {
        getViewState().showName("Moxy");
        getViewState().showPhoto("moxy");
    }
}
