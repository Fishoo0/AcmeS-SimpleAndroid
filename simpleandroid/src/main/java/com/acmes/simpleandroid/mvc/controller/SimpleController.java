package com.acmes.simpleandroid.mvc.controller;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.acmes.simpleandroid.mvc.ISimpleModeCallback;
import com.acmes.simpleandroid.mvc.SimpleActivity;
import com.acmes.simpleandroid.mvc.SimpleFragment;
import com.acmes.simpleandroid.mvc.model.SimpleModel;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

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
    public void onRequestStart(SimpleRequest request) {
        Log.v(TAG, "onRequestStart -> " + request);
        mCallback.onRequestStart(request);
    }

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        Log.v(TAG, "onResponse -> " + request);
        mCallback.onResponse(request, response);
    }

    @Override
    public void onFailure(SimpleRequest request, Throwable exception) {
        Log.v(TAG, "onFailure -> " + request);
        mCallback.onFailure(request, exception);
    }

    public Handler getHandler() {
        return mHandler;
    }

    public abstract SimpleModel createModel();

    private SimpleModel mModel;

    public SimpleModel getModel() {
        if (mModel == null) {
            mModel = createModel();
            if (mModel != null) {
                mModel.addSimpleCallback(this);
            }
        }
        return mModel;
    }


    /**
     * Calling when {@link android.app.Activity#onCreate(Bundle)} / {@link android.app.Fragment#onCreate(Bundle)}
     *
     * @param bundle
     */
    public void onCreate(Bundle bundle) {

    }

    /**
     * Calling when {@link Activity#onResume()} / {@link Fragment#onResume()}
     */
    public void onResume() {

    }

    /**
     * Calling when {@link Activity#onPause()} / {@link Fragment#onPause()}
     */
    public void onPause() {

    }

    /**
     * Calling when {@link Activity#onDestroy()} / {@link Fragment#onDestroy()}
     */
    public void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        if (mModel != null) {
            mModel.removeSimpleCallback(this);
            mModel.onDestroy();
        }
    }
}
