package com.example.epamexample.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.epamexample.model.GetDataRetrofit;
import com.example.epamexample.model.Model;
import com.example.epamexample.pojo.ListApi;
import com.example.epamexample.views.GetBodyView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@InjectViewState
public class GetBodyPresent extends MvpPresenter<GetBodyView> implements GetDataRetrofit {
    private Model modelPart1;

    public GetBodyPresent() {
        Log.i("GetBodyPresent", "3");
        modelPart1 = new Model(this);
        modelPart1.retrofitCall();
    }

    @Override
    public void getBody(Observable<ListApi> observable, final boolean check) {
        Log.i("GetBodyPresent", "4");

        if (observable == null) {
            getViewState().fail();
        } else {
            observable
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ListApi>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ListApi listApi) {
                            if (check) {
                                modelPart1.addListPhoto(listApi);
                                getViewState().showRetrofit(listApi.getList());
                            } else {
                                getViewState().showRetrofit(listApi.getList());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            modelPart1.getRealm();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}


