package com.acmes.simpleandroid.mvc;

import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

/**
 * Created by fishyu on 2017/9/6.
 */

public interface ISimpleModeCallback {

    void onRequestStart(SimpleRequest requestTag);

    void onResponse(SimpleRequest requestTag, SimpleResponse response);

    void onFailure(SimpleRequest requestTag, Throwable exception);

}
