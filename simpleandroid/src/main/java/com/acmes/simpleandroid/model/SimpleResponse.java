package com.acmes.simpleandroid.model;

/**
 * Created by fishyu on 2017/8/23.
 */

public class SimpleResponse<T> {

    public int mCode;

    public String mMessage;

    public T mData;

    public boolean isSuccess() {
        return true;
    }


    // do nothing ...
    public void onAppendLastResponse(SimpleResponse<T> old) {
        // do nothing
    }


}
