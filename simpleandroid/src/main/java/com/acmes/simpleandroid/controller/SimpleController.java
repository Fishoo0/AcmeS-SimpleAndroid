package com.acmes.simpleandroid.controller;

import android.os.Handler;
import android.util.Log;

import com.acmes.simpleandroid.ISimpleModeCallback;
import com.acmes.simpleandroid.SimpleActivity;
import com.acmes.simpleandroid.SimpleFragment;
import com.acmes.simpleandroid.model.SimpleModel;
import com.acmes.simpleandroid.model.SimpleResponse;

/**
 * Created by fishyu on 2017/8/23.
 */

public abstract class SimpleController implements ISimpleModeCallback {

    private String TAG;

    private ISimpleModeCallback mCallback;

    private Handler mHandler;


    public SimpleController(ISimpleModeCallback fragmentOrActivity) {
        if (fragmentOrActivity instanceof SimpleActivity) {
            //check pass
        } else if (fragmentOrActivity instanceof SimpleFragment) {
            //check pass
        } else {
            throw new IllegalArgumentException("fragmentOrActivity must be SimpleActivity or SimpleFragment !");
        }

        TAG = fragmentOrActivity.getClass().getSimpleName();
        mCallback = fragmentOrActivity;
        mHandler = new Handler();
    }


    @Override
    public void onRequestStart(Object requestTag) {
        Log.v(TAG, "onRequestStart -> " + requestTag);

        mCallback.onRequestStart(requestTag);

    }

    @Override
    public void onResponse(Object requestTag, SimpleResponse response) {
        Log.v(TAG, "onResponse -> " + requestTag);

        mCallback.onResponse(requestTag, response);

    }

    @Override
    public void onFailure(Object requestTag, Throwable exception) {
        Log.v(TAG, "onFailure -> " + requestTag);

        mCallback.onFailure(requestTag, exception);
    }


    public Handler getHandler() {
        return mHandler;
    }

    public abstract SimpleModel createModel();

    private SimpleModel mModel;

    public SimpleModel getModel() {
        if (mModel == null) {
            mModel = createModel();
            mModel.setSimpleCallback(this);
        }
        return mModel;
    }

    /**
     * Called when onDestroy
     */
    public void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        getModel().onDestroy();
    }
}
