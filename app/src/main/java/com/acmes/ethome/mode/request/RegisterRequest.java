package com.acmes.ethome.mode.request;

import com.acmes.ethome.ETHomeAPI;

import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class RegisterRequest extends ETHomeRequest {

    public String user_name;
    public String user_password;


    public RegisterRequest(String userName, String userPassword) {
        user_name = userName;
        user_password = userPassword;
    }


    @Override
    public Observable callAPI(ETHomeAPI api) {
        return api.register(this);
    }
}
