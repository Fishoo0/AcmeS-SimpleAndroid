package com.acmes.simpleandroid.mvc.model;

import com.acmes.simpleandroid.mvc.ISimpleModeCallback;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by fishyu on 2017/8/23.
 */

public abstract class SimpleModel {

    protected final String TAG = getClass().getSimpleName();

    protected ISimpleModeCallback mSimpleCallback;

    public SimpleModel() {
    }

    /**
     * Setting simple callback
     *
     * @param callback
     */
    public void setSimpleCallback(ISimpleModeCallback callback) {
        mSimpleCallback = callback;
    }


    protected Map<Object, Object> mRequests = new LinkedHashMap<>();

    protected Map<Object, Object> getRequestsQueen() {
        return mRequests;
    }

    protected abstract void onCancelRequest(Object request, Object callable);

    /**
     * Destroy all requests
     */
    public void onDestroy() {
        Iterator<Object> iterator = mRequests.keySet().iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            onCancelRequest(key, mRequests.get(key));
        }
        mRequests.clear();
    }


    /**
     * Perform request
     *
     * @param request
     * @param callback
     */
    public abstract void performRequest(final SimpleRequest request, final ISimpleModeCallback callback);


    public void onRequestStart(Object requestTag, Object callable, ISimpleModeCallback callback) {
        if (getRequestsQueen().containsKey(requestTag)) {
            onCancelRequest(requestTag, getRequestsQueen().get(requestTag));
        }
        mRequests.put(requestTag, callable);
        callback.onRequestStart(requestTag);
    }

    public void onResponse(Object requestTag, SimpleResponse response, ISimpleModeCallback callback) {
        mRequests.remove(requestTag);
        callback.onResponse(requestTag, response);
    }

    public void onFailure(Object requestTag, Throwable exception, ISimpleModeCallback callback) {
        mRequests.remove(requestTag);
        callback.onFailure(requestTag, exception);
    }


}
