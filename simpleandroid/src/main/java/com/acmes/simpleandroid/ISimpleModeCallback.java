package com.acmes.simpleandroid;

import com.acmes.simpleandroid.model.SimpleResponse;

/**
 * Created by fishyu on 2017/9/6.
 */

public interface ISimpleModeCallback {

    void onRequestStart(Object requestTag);

    void onResponse(Object requestTag, SimpleResponse response);

    void onFailure(Object requestTag, Throwable exception);


}
