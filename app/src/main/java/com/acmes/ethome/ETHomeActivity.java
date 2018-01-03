package com.acmes.ethome;

import android.widget.Toast;

import com.acmes.simpleandroid.mvc.SimpleActivity;

/**
 * Created by fishyu on 2018/1/2.
 */

public abstract class ETHomeActivity<T extends ETHomeMode> extends SimpleActivity<T> {


    @Override
    public void onFailure(Object requestTag, Throwable exception) {
        super.onFailure(requestTag, exception);
        Toast.makeText(this, R.string.value_common_net_error, Toast.LENGTH_SHORT).show();
    }
}

