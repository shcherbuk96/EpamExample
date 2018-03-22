package com.example.epamexample.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.epamexample.model.GetDataRetrofit;
import com.example.epamexample.model.ModelPart1;
import com.example.epamexample.part1.Photo;
import com.example.epamexample.views.GetBodyView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Yauheni_Shcharbuk on 3/20/2018.
 */

@InjectViewState
public class GetBodyPresent extends MvpPresenter<GetBodyView> implements GetDataRetrofit {
    Realm realm;
    private ModelPart1 modelPart1;

    public GetBodyPresent() {
        modelPart1 = new ModelPart1(this);
        modelPart1.retrofitCall();
    }

    @Override
    public void getBody(List<Photo> list) {
        realm = Realm.getDefaultInstance();
        if (list != null) {
            getViewState().showRetrofit(list);
            modelPart1.realmClose();
        }
        if (list == null) {
            if (realm.where(Photo.class).findFirst() != null) {
                RealmResults<Photo> result = realm.where(Photo.class).findAll();
                getViewState().showRetrofit(realm.copyFromRealm(result));
                realm.close();
            } else getViewState().fail();
        }
    }
}


