package com.example.epamexample.Part1;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Yauheni_Shcharbuk on 3/12/2018.
 */

public class ListRealm extends RealmObject{

    public RealmList<Photo> realmList;

    public ListRealm(){

    }

    public ListRealm(RealmList<Photo> realmList) {
        this.realmList = realmList;
    }

    public RealmList<Photo> getRealmList() {
        return realmList;
    }

    public void setRealmList(RealmList<Photo> realmList) {
        this.realmList = realmList;
    }
}
