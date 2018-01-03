package com.acmes.ethome.login;

import com.acmes.ethome.ETHomeMode;
import com.acmes.ethome.mode.bean.DUser;
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
    private IAccountManager mAccountManager = SharedPrefAccountManager.getInstance();

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
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        if (response.isSuccess() && response.getData() != null) {
            if (request instanceof LoginRequest && response.getData() instanceof DUser) {
                DUser user = new DUser(((LoginRequest) request).user_name, ((LoginRequest) request).user_password);
                mAccountManager.addUser(user);
            } else if (request instanceof RegisterRequest && response.getData() instanceof DUser) {
                //do nothing
            } else if (request instanceof LogoutRequest) {
                mAccountManager.removeUser((DUser) response.getData());
            }
        }
        super.onResponse(request, response);
    }

    @Override
    public void onDestroy() {
        //single instance ,we do nothing since this method would
    }
}
