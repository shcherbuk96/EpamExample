package com.example.epamexample.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;


public class ListApi{
    @SerializedName("photos")
    public List<Photo> list;

    public List<Photo> getList() {
        return list;
    }

    public void setList(List<Photo> list) {
        this.list = list;
    }
}
