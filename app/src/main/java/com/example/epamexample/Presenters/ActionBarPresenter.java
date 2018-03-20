package com.example.epamexample.Presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.epamexample.Views.ActionBarView;

/**
 * Created by Yauheni_Shcharbuk on 3/20/2018.
 */
@InjectViewState
public class ActionBarPresenter extends MvpPresenter<ActionBarView>{

    public ActionBarPresenter(){
        getViewState().showActionBar("Moxy");
    }

}
