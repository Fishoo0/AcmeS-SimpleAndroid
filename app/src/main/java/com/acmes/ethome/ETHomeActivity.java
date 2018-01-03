package com.acmes.ethome;

import android.widget.Toast;

import com.acmes.simpleandroid.mvc.SimpleActivity;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;

/**
 * Created by fishyu on 2018/1/2.
 */

public abstract class ETHomeActivity<T extends ETHomeMode> extends SimpleActivity<T> {


    @Override
    public void onFailure(SimpleRequest requestTag, Throwable exception) {
        super.onFailure(requestTag, exception);
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
    }
}

