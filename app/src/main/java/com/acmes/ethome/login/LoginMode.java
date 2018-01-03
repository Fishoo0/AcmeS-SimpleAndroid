package com.acmes.ethome.login;

import com.acmes.ethome.ETHomeMode;
import com.acmes.ethome.mode.request.LoginRequest;
import com.acmes.ethome.mode.request.LogoutRequest;
import com.acmes.ethome.mode.request.RegisterRequest;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

/**
 * Created by fishyu on 2018/1/2.
 */

public class LoginMode extends ETHomeMode {

    private static LoginMode mLoginMode = new LoginMode();

    public static LoginMode getInstance() {
        return mLoginMode;
    }

    /**
     * Login
     */
    public void login(LoginRequest loginRequest) {
        performRequest(loginRequest);
    }

    /**
     * Register
     */
    public void register(RegisterRequest request) {
        performRequest(request);
    }

    /**
     * Logout
     *
     * @param userName
     */
    public void logout(String userName) {

    }


    @Override
    public void onResponse(SimpleRequest requestTag, SimpleResponse response) {
        super.onResponse(requestTag, response);
        if (requestTag instanceof LoginRequest) {

        } else if (requestTag instanceof RegisterRequest) {

        } else if (requestTag instanceof LogoutRequest) {

        }
    }

    @Override
    public void onDestroy() {
        //single instance ,we do nothing since this method would
    }
}
