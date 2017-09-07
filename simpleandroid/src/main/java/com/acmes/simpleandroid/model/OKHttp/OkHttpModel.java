package com.acmes.simpleandroid.model.OKHttp;

import com.acmes.simpleandroid.ISimpleModeCallback;
import com.acmes.simpleandroid.SimpleApplication;
import com.acmes.simpleandroid.model.SimpleModel;
import com.acmes.simpleandroid.model.SimpleRequest;

import okhttp3.OkHttpClient;

/**
 * Created by fishyu on 2017/9/7.
 */

public class OkHttpModel extends SimpleModel {

    OkHttpClient mOkHttpClient;

    public OkHttpModel() {
        super();
        mOkHttpClient = SimpleApplication.getSimpleApplicationInstance()
                .getSquareNetworkSeries().getOkHttpClient();
    }

    @Override
    protected void onCancelRequest(Object request, Object callable) {

    }

    @Override
    public void performRequest(SimpleRequest request, ISimpleModeCallback callback) {

    }

}
