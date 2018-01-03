package com.acmes.ethome.login;

import android.os.Bundle;

import com.acmes.ethome.ETHomeMode;
import com.acmes.ethome.mode.request.LoginRequest;

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
        performRequestRetrofit(null, loginRequest, getAPI().login(loginRequest));
    }


    /**
     * Logout
     *
     * @param userName
     */
    public void logout(String userName) {

    }


    /**
     * Register
     *
     * @param userName
     * @param userPassword
     * @param extras
     */
    public void register(String userName, String userPassword, Bundle extras) {

    }

}
