package com.acmes.ethome.mode.response;

import com.acmes.simpleandroid.mvc.model.SimpleResponse;

/**
 * Created by fishyu on 2018/1/2.
 */

public class ETHomeResponse<T> extends SimpleResponse {

    public int code;
    public String message;
    public T data;


    public boolean isSuccess() {
        return code == 0;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
