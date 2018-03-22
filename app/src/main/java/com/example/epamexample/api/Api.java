package com.example.epamexample.api;

import com.example.epamexample.pojo.ListApi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("upt7z")
    Call<ListApi> listData();
}
