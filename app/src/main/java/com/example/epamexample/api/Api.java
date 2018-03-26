package com.example.epamexample.api;

import com.example.epamexample.pojo.ListApi;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("upt7z")
    Observable<ListApi> listData();
}
