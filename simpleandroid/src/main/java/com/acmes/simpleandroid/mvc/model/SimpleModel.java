package com.acmes.simpleandroid.mvc.model;

import com.acmes.simpleandroid.mvc.ISimpleModeCallback;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fishyu on 2017/8/23.
 */

public abstract class SimpleModel implements ISimpleModeCallback {

    protected final String TAG = getClass().getSimpleName();

    protected List<ISimpleModeCallback> mSimpleCallbacks = new ArrayList<>();

    public SimpleModel() {
    }

    /**
     * Setting simple callback
     *
     * @param callback
     */
    public void addSimpleCallback(ISimpleModeCallback callback) {
        if (callback != null) {
            if (!mSimpleCallbacks.contains(callback)) {
                mSimpleCallbacks.add(callback);
            }
        }
    }


    /**
     * Removing simple callback
     *
     * @param callback
     */
    public void removeSimpleCallback(ISimpleModeCallback callback) {
        if (callback != null) {
            mSimpleCallbacks.remove(callback);
        }
    }


    protected Map<SimpleRequest, Object> mRequests = new LinkedHashMap<>();

    protected Map<SimpleRequest, Object> getRequestsQueen() {
        return mRequests;
    }


    protected abstract void onCancelRequest(Object request, Object callable);

    /**
     * Destroy all requests
     */
    public void onDestroy() {
        Iterator<SimpleRequest> iterator = mRequests.keySet().iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            onCancelRequest(key, mRequests.get(key));
        }
        mRequests.clear();
        mSimpleCallbacks.clear();
    }


    /**
     * Cancel requests
     *
     * @param requestTag
     */
    public void cancelRequest(Object requestTag) {
    }


    /**
     * Perform request
     *
     * @param request
     * @param callback
     */
    public abstract void performRequest(final SimpleRequest request, final ISimpleModeCallback callback);


    /**
     * Perform request
     *
     * @param request
     */
    public void performRequest(final SimpleRequest request) {
        performRequest(request, this);
    }


    public void onRequestStart(SimpleRequest requestTag, Object callable, ISimpleModeCallback callback) {
        if (getRequestsQueen().containsKey(requestTag)) {
            onCancelRequest(requestTag, getRequestsQueen().get(requestTag));
        }
        mRequests.put(requestTag, callable);
        callback.onRequestStart(requestTag);
    }

    public void onResponse(SimpleRequest requestTag, SimpleResponse response, ISimpleModeCallback callback) {
        mRequests.remove(requestTag);
        callback.onResponse(requestTag, response);
    }

    public void onFailure(SimpleRequest requestTag, Throwable exception, ISimpleModeCallback callback) {
        mRequests.remove(requestTag);
        callback.onFailure(requestTag, exception);
    }

    @Override
    public void onRequestStart(SimpleRequest requestTag) {
        for (ISimpleModeCallback callback : mSimpleCallbacks) {
            callback.onRequestStart(requestTag);
        }
    }

    @Override
    public void onResponse(SimpleRequest requestTag, SimpleResponse response) {
        for (ISimpleModeCallback callback : mSimpleCallbacks) {
            callback.onResponse(requestTag, response);
        }
    }

    @Override
    public void onFailure(SimpleRequest requestTag, Throwable exception) {
        for (ISimpleModeCallback callback : mSimpleCallbacks) {
            callback.onFailure(requestTag, exception);
        }
    }

}
