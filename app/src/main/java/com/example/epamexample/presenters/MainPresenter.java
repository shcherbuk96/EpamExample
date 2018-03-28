package com.example.epamexample.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.epamexample.model.GetObservable;
import com.example.epamexample.model.InternetModel;
import com.example.epamexample.model.RealmModel;
import com.example.epamexample.pojo.ListApi;
import com.example.epamexample.views.GetBodyView;

import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@InjectViewState
public class MainPresenter extends MvpPresenter<GetBodyView> implements GetObservable {
    private InternetModel modelPart1;
    private RealmModel realmModel;
    @Inject
    public MainPresenter() {
        Log.i("MainPresenter", "3");
        modelPart1 = new InternetModel(this);
        realmModel=new RealmModel(this);
        modelPart1.retrofitCall();
    }

    @Override
    public void getBody(Flowable<ListApi> observable, final boolean check) {
        if (observable == null) {
            getViewState().fail();
        } else {
            observable
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new FlowableSubscriber<ListApi>() {
                        @Override
                        public void onSubscribe(Subscription s) {
                            s.request(Long.MAX_VALUE);
                        }

                        @Override
                        public void onNext(ListApi listApi) {
                            if (check) {
                                realmModel.addListPhoto(listApi);
                                getViewState().showRetrofit(listApi.getList());
                            } else {
                                getViewState().showRetrofit(listApi.getList());
                            }
                        }

                        @Override
                        public void onError(Throwable t) {
                            realmModel.getRealm();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}


