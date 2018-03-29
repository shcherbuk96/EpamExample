package com.example.epamexample.model;

import com.example.epamexample.pojo.ListApi;

import io.reactivex.Flowable;


public interface GetObservable {
    void getBody(Flowable<ListApi> observable, boolean check);
}
