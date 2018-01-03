package com.acmes.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.acmes.simpleandroid.mvc.SimpleActivity;
import com.acmes.simpleandroid.mvc.model.SimpleModel;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;
import com.acmes.simpleandroid.view.TriangleView;

/**
 * Created by fishyu on 2017/9/12.
 */

public class MainActivity5 extends SimpleActivity {

    private int mDirection = TriangleView.UP;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TriangleView view = new TriangleView(this);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setDirection(mDirection++ % 4 + 1);
                view.setColor(Color.RED);
            }
        });

        setContentView(view);
    }

    @Override
    protected SimpleModel createMode() {
        return null;
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
