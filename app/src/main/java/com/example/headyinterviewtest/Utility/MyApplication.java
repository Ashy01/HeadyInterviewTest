package com.example.headyinterviewtest.Utility;

import android.app.Application;
import android.content.Context;

import com.example.headyinterviewtest.Dagger.AppComponent;
import com.example.headyinterviewtest.Dagger.AppModule;
import com.example.headyinterviewtest.Dagger.DaggerAppComponent;
import com.example.headyinterviewtest.Dagger.UtilsModule;


public class MyApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).utilsModule(new UtilsModule()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

}
