package com.example.epamexample.dagger;

import com.example.epamexample.model.InternetModel;
import com.example.epamexample.task.MapGoogleActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DaggerModule.class})
public interface DaggerComponent {
    void inject(InternetModel model);
    void inject(MapGoogleActivity model);
}
