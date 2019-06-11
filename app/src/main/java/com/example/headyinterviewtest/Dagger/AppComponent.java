package com.example.headyinterviewtest.Dagger;

import com.example.headyinterviewtest.Activities.HomeActivity;


import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void doInjection(HomeActivity homeActivity);

}