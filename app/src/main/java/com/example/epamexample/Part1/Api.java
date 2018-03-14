package com.example.epamexample.Part1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Yauheni_Shcharbuk on 3/14/2018.
 */

public interface Api {
    @GET("upt7z")
    Call<ListApi> listData();
}
