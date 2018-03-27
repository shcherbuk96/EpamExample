package com.example.epamexample.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.epamexample.model.GetDataRetrofit;
import com.example.epamexample.model.ModelPresenter;
import com.example.epamexample.pojo.ListApi;
import com.example.epamexample.pojo.Photo;
import com.example.epamexample.views.GetBodyView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmResults;


@InjectViewState
public class GetBodyPresent extends MvpPresenter<GetBodyView> implements GetDataRetrofit {
    private ModelPresenter modelPart1;
    Observer<ListApi> observer;
    public GetBodyPresent() {
        modelPart1 = new ModelPresenter(this);

    }

    @Override
    public void getBody(List<Photo> list) {
        modelPart1.retrofitCall();

        observer=new Observer<ListApi>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ListApi listApi) {
                getViewState().showRetrofit(listApi.getList());
            }

            @Override
            public void onError(Throwable e) {
                /*RealmResults<Photo> result = realm.where(Photo.class).findAll();
                getDataRetrofit.getBody(realm.copyFromRealm(result));*/
            }

            @Override
            public void onComplete() {

            }
        };
        modelPart1.getObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        /*if (list != null) {
            Log.i("list",String.valueOf(list.size()));
            getViewState().showRetrofit(list);
            modelPart1.realmClose();
        }
        if (list == null) {
            getViewState().fail();
        }*/
    }
}


