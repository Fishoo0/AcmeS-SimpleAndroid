package com.acmes.simpleandroid.mvc;

import com.acmes.simpleandroid.mvc.model.SimpleResponse;

/**
 * Created by fishyu on 2017/9/6.
 */

public interface ISimpleModeCallback {

    void onRequestStart(Object requestTag);

    void onResponse(Object requestTag, SimpleResponse response);

    void onFailure(Object requestTag, Throwable exception);

}
