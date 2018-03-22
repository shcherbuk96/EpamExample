package com.example.epamexample.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ListApi {
    @SerializedName("photos")
    public List<Photo> list;

    public List<Photo> getList() {
        return list;
    }

    public void setList(List<Photo> list) {
        this.list = list;
    }
}
