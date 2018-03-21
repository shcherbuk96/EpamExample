package com.example.epamexample.part1;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("upt7z")
    Call<ListApi> listData();
}
