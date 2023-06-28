package com.asli.rummy;

import android.app.Application;
import android.content.Context;

public class GameApplication extends Application {

    private static GameApplication instance;
    public static Context appLevelContext;

    public static GameApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appLevelContext=getApplicationContext();
    }


}
