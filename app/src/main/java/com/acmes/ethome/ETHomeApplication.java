package com.acmes.ethome;

import com.acmes.simpleandroid.imp.Square.SquareNetwork;
import com.acmes.simpleandroid.mvc.SimpleApplication;
import com.squareup.picasso.Picasso;

import hugo.weaving.DebugLog;
import retrofit2.Retrofit;

/**
 * Created by fishyu on 2018/1/2.
 */

public class ETHomeApplication extends SimpleApplication {

    public static ETHomeApplication getInstance() {
        return (ETHomeApplication) SimpleApplication.getInstance();
    }

    @DebugLog
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        mSquareNetwork = new SquareNetwork(this, getBaseUrl());
        updateProgress(20);
        while (getInitializeProgress() < 100) {
            try {
                Thread.sleep((long) (Math.random() * 200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateProgress(getInitializeProgress() + 1);
        }


    }

    protected String getBaseUrl() {
        return "http://10.226.66.204:5000/";
    }

    protected SquareNetwork mSquareNetwork;

    public Picasso getPicasso() {
        return mSquareNetwork.getPicasso();
    }

    public Retrofit getRetrofit() {
        return mSquareNetwork.getRetrofit();
    }


}
