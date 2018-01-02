package com.acmes.ethome;

import com.acmes.simpleandroid.imp.Square.SquareNetwork;
import com.acmes.simpleandroid.mvc.SimpleApplication;
import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;

/**
 * Created by fishyu on 2018/1/2.
 */

public class ETHomeApplication extends SimpleApplication {

    public static ETHomeApplication getInstance() {
        return (ETHomeApplication) SimpleApplication.getInstance();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSquareNetwork = new SquareNetwork(this, getBaseUrl());
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
