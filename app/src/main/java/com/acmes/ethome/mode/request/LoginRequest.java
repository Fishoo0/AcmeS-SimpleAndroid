package com.acmes.ethome.mode.request;

/**
 * Created by fishyu on 2018/1/2.
 */

public class LoginRequest extends ETHomeRequest {

    public String user_name;
    public String user_password;


    public LoginRequest(String userName, String userPassword) {
        user_name = userName;
        user_password = userPassword;
    }

    public LoginRequest(String userName) {
        user_name = userName;
    }

}
