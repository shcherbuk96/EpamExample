package com.example.epamexample.presenters;

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
import io.reactivex.android.schedulers.AndroidSchedulers;


@InjectViewState
public class MainPresenter extends MvpPresenter<GetBodyView> implements GetObservable {
    private InternetModel internetModel;
    private RealmModel realmModel;

    @Inject
    public MainPresenter() {
        internetModel = new InternetModel(this);
        realmModel = new RealmModel(this);
        internetModel.retrofitCall();
    }

    @Override
    public void getBody(Flowable<ListApi> observable, final boolean check) {
        if (observable == null) {
            getViewState().fail();
        } else {
            observable
                    //.subscribeOn(Schedulers.newThread())
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


