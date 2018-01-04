package com.acmes.ethome.mode.request;

import com.acmes.ethome.ETHomeAPI;

import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/3.
 */

public class LogoutRequest extends ETHomeRequest {

    public String user_name;
    public String user_password;

    public LogoutRequest(String userName) {
        user_name = userName;
    }

    @Override
    public Observable callAPI(ETHomeAPI api) {
        return api.logout(this);
    }

}
