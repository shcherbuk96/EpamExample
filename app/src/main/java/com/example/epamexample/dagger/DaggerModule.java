package com.example.epamexample.dagger;

import com.example.epamexample.api.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.epamexample.task.Constants.BASE_URL;

@Module
public class DaggerModule {
    private String url;

    public DaggerModule(String url) {
        this.url = url;
    }

    @Singleton
    @Provides
    public Gson provideGson(){
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

    @Singleton
    @Provides
    public Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }


}
