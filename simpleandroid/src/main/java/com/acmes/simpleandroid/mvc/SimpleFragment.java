package com.acmes.simpleandroid.mvc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acmes.simpleandroid.mvc.controller.SimpleController;
import com.acmes.simpleandroid.mvc.model.SimpleModel;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

/**
 * Created by fishyu on 2017/8/23.
 */

public abstract class SimpleFragment<T extends SimpleModel> extends Fragment implements ISimpleModeCallback {

    protected final String TAG = getClass().getSimpleName();

    private SimpleController mController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");
        mController = new SimpleController(this) {
            @Override
            public SimpleModel createModel() {
                return SimpleFragment.this.createMode();
            }
        };
        mController.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.v(TAG, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v(TAG, "onResume");
        mController.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v(TAG, "onPause");
        mController.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.v(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy");
    }

    /**
     * Getting model for data processing
     *
     * @return
     */
    public T getModel() {
        return (T) mController.getModel();
    }

    protected abstract T createMode();

    @Override
    public void onRequestStart(SimpleRequest request) {

    }

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {

    }

    @Override
    public void onFailure(SimpleRequest request, Throwable exception) {

    }

}
