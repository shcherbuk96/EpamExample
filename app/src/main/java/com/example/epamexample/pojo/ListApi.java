package com.example.epamexample.pojo;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;


public class ListApi extends RealmObject {
    @SerializedName("photos")
    private RealmList<Photo> list;

    public RealmList<Photo> getList() {
        return list;
    }

    public void setList(RealmList<Photo> list) {
        this.list = list;
    }
}
