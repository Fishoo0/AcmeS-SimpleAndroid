package com.acmes.simpleandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

import com.acmes.simpleandroid.controller.SimpleController;
import com.acmes.simpleandroid.model.SimpleModel;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;


/**
 * Created by fishyu on 2017/8/23.
 */

public abstract class SimpleActivity<T extends SimpleModel> extends FragmentActivity implements ISimpleModeCallback {

    protected final String TAG = getClass().getSimpleName();


    private SimpleController mController;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mController = new SimpleController(this) {
            @Override
            public SimpleModel createModel() {
                return SimpleActivity.this.createMode();
            }
        };

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
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mController.onDestroy();
    }

    public Handler getHandler() {
        return mController.getHandler();
    }


    public Picasso getPicasso() {
        return ((SimpleApplication) getApplication()).getPicasso();
    }

}
