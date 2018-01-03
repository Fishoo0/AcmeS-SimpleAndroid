package com.acmes.ethome.mode.request;

import com.acmes.ethome.mode.response.ETHomeResponse;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;

/**
 * Created by fishyu on 2018/1/2.
 */

public class ETHomeRequest<T extends ETHomeResponse> extends SimpleRequest<T> {

    @Override
    public void buildParams(Object someNetEngineMaybe) {
        //do nothing
    }

}
