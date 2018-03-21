package com.example.epamexample.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.epamexample.model.GetDataRetrofit;
import com.example.epamexample.model.ModelPart1;
import com.example.epamexample.part1.Photo;
import com.example.epamexample.views.GetBodyView;

import java.util.List;

/**
 * Created by Yauheni_Shcharbuk on 3/20/2018.
 */

@InjectViewState
public class GetBodyPresent extends MvpPresenter<GetBodyView> implements GetDataRetrofit {

    private ModelPart1 modelPart1;

    public GetBodyPresent() {
        modelPart1 = new ModelPart1(this);
    }

    @Override
    public void getBody(List<Photo> list) {
//        Log.i("getBody", String.valueOf(list.size()));
        if(list!=null) {
            getViewState().showRetrofit(list);
        }
        else getViewState().fail();
    }
}


