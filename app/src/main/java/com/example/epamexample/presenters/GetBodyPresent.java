package com.example.epamexample.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.epamexample.model.GetDataRetrofit;
import com.example.epamexample.model.ModelPresenter;
import com.example.epamexample.pojo.Photo;
import com.example.epamexample.views.GetBodyView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


@InjectViewState
public class GetBodyPresent extends MvpPresenter<GetBodyView> implements GetDataRetrofit {
    private ModelPresenter modelPart1;

    public GetBodyPresent() {
        modelPart1 = new ModelPresenter(this);
        modelPart1.retrofitCall();
        modelPart1.getObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelPart1.getObserver());
    }

    @Override
    public void getBody(List<Photo> list) {

        if (list != null) {
            Log.i("list",String.valueOf(list.size()));
            getViewState().showRetrofit(list);
            modelPart1.realmClose();
        }
        if (list == null) {
            getViewState().fail();
        }
    }
}


