package com.acmes.ethome.mode.request;

import com.acmes.ethome.ETHomeAPI;
import com.acmes.ethome.mode.response.ETHomeResponse;
import com.acmes.simpleandroid.imp.Square.RetrofitSimpleRequest;

import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class ETHomeRequest<T extends ETHomeResponse> extends RetrofitSimpleRequest<T, ETHomeAPI> {

    @Override
    public Observable<T> callAPI(ETHomeAPI api) {
        return null;
    }
}
