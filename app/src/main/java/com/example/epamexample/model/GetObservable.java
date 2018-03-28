package com.example.epamexample.model;

import com.example.epamexample.pojo.ListApi;

import io.reactivex.Observable;


public interface GetObservable {
    void getBody(Observable<ListApi> observable, boolean check);
}
