package com.example.epamexample.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;


public class ListApi extends RealmObject{
    @SerializedName("photos")
    public RealmList<Photo> list;

    public RealmList<Photo> getList() {
        return list;
    }

    public void setList(RealmList<Photo> list) {
        this.list = list;
    }
}
