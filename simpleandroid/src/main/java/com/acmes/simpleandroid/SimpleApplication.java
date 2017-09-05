package com.acmes.simpleandroid;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

/**
 * Created by fishyu on 2017/9/5.
 */

public class SimpleApplication extends Application {

    protected final String TAG = getClass().getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "onCreate");

    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.v(TAG, "onLowMemory");

    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.v(TAG, "onTrimMemory");

    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.v(TAG, "onTerminate");

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.v(TAG, "onConfigurationChanged -> " + newConfig);

    }

}
