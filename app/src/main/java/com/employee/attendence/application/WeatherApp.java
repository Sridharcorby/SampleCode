package com.employee.attendence.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

public class WeatherApp extends Application {

    private static Context context;
    private static WeatherApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        context = getApplicationContext();
    }

    public static Context getGlobalContext() {
        return context;
    }

    public static synchronized WeatherApp getInstance() {
        return mInstance;
    }

    public static Resources getAppResources() {
        return context.getResources();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

}
