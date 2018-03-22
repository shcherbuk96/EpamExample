package com.example.epamexample;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Yauheni_Shcharbuk on 3/22/2018.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
