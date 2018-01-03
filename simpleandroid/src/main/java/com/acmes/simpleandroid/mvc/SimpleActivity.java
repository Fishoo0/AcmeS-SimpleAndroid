package com.acmes.simpleandroid.mvc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.acmes.simpleandroid.mvc.controller.SimpleController;
import com.acmes.simpleandroid.mvc.model.SimpleModel;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

import butterknife.ButterKnife;


/**
 * Created by fishyu on 2017/8/23.
 */
public abstract class SimpleActivity<T extends SimpleModel> extends FragmentActivity implements ISimpleModeCallback {

    protected final String TAG = getClass().getSimpleName();
    protected final boolean DEBUG = SimpleUtils.DEBUG;

    private SimpleController mController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");
        mController = new SimpleController(this) {
            @Override
            public SimpleModel createModel() {
                return SimpleActivity.this.createMode();
            }
        };
        mController.onCreate(savedInstanceState);
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
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.v(TAG, "onNewIntent");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume");
        mController.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause");
        mController.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy");
        mController.onDestroy();
    }

    public final Handler getHandler() {
        return mController.getHandler();
    }

    @Override
    public void onRequestStart(SimpleRequest requestTag) {

    }

    @Override
    public void onResponse(SimpleRequest requestTag, SimpleResponse response) {

    }

    @Override
    public void onFailure(SimpleRequest requestTag, Throwable exception) {

    }
}
