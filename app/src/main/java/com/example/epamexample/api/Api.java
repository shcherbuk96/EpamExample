package com.example.epamexample.api;

import com.example.epamexample.pojo.ListApi;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface Api {
    @GET("upt7z")
    Flowable<ListApi> listData();
}
