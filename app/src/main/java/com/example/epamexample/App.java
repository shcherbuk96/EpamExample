package com.example.epamexample;

import android.app.Application;

import com.example.epamexample.dagger.DaggerComponent;
import com.example.epamexample.dagger.DaggerDaggerComponent;
import com.example.epamexample.dagger.DaggerModule;
import com.example.epamexample.task.Constants;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class App extends Application {
    static DaggerComponent retrofitComponent;

    public static DaggerComponent getRetrofitComponent() {
        return retrofitComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        retrofitComponent = DaggerDaggerComponent.builder()
                .daggerModule(new DaggerModule(Constants.BASE_URL))
                .build();

        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

    }
}
