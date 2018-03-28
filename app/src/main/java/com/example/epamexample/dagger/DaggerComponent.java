package com.example.epamexample.dagger;

import com.example.epamexample.model.InternetModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DaggerModule.class})
public interface DaggerComponent {
    void inject(InternetModel model);
}
