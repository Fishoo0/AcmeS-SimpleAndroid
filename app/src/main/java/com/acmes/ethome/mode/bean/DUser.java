package com.acmes.ethome.mode.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class DUser implements Serializable {

    @SerializedName("user_id")
    public String mUserId;

    @SerializedName("user_name")
    public String mUserName;

    @SerializedName("user_password")
    public String mUserPassword;

    public DUser(String user_name, String user_password) {
        mUserName = user_name;
        mUserPassword = user_password;
    }
}
