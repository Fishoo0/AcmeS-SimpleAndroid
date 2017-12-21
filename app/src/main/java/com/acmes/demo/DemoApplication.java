package com.acmes.demo;

import com.acmes.simpleandroid.imp.Square.SquareNetwork;
import com.acmes.simpleandroid.mvc.SimpleApplication;
import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;


/**
 * Created by fishyu on 2017/9/6.
 */

public class DemoApplication extends SimpleApplication {

    public static DemoApplication getInstance() {
        return (DemoApplication) SimpleApplication.getInstance();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSquareNetwork = new SquareNetwork(this, getBaseUrl());
    }

    protected String getBaseUrl() {
        return "http://45.77.47.182/";
    }

    protected SquareNetwork mSquareNetwork;


    public Picasso getPicasso() {
        return mSquareNetwork.getPicasso();
    }

    public Retrofit getRetrofit() {
        return mSquareNetwork.getRetrofit();
    }


}
