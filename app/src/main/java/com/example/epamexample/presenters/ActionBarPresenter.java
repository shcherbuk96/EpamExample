package com.example.epamexample.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.epamexample.views.ActionBarView;


@InjectViewState
public class ActionBarPresenter extends MvpPresenter<ActionBarView> {

    public ActionBarPresenter() {
        getViewState().showActionBar("Moxy");
    }

}
