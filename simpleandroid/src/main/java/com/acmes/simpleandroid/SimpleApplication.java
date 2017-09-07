package com.acmes.simpleandroid;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import com.acmes.simpleandroid.model.Square.SquareNetwork;
import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;

/**
 * Created by fishyu on 2017/9/5.
 */

public class SimpleApplication extends Application {

    protected final String TAG = getClass().getSimpleName();

    protected SquareNetwork mSquareNetwork;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "onCreate");

        mInstance = this;
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


    protected String getBaseUrl() {
        return "http://45.77.47.182/";
    }

    /**
     * Getting the square network series
     *
     * @return
     */
    public SquareNetwork getSquareNetworkSeries() {
        if (mSquareNetwork == null) {
            mSquareNetwork = new SquareNetwork(this, getBaseUrl());
        }
        return mSquareNetwork;
    }

    private static SimpleApplication mInstance;


    public static final SimpleApplication getSimpleApplicationInstance() {
        return mInstance;
    }


    public Picasso getPicasso() {
        return getSquareNetworkSeries().getPicasso();
    }

    public Retrofit getRetrofit() {
        return getSquareNetworkSeries().getRetrofit();
    }


}
