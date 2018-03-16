package com.example.epamexample.Part1;

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
