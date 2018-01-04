package com.acmes.ethome.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.acmes.ethome.ETHomeActivity;
import com.acmes.ethome.mode.bean.DUser;
import com.acmes.ethome.mode.request.LogoutRequest;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.utils.Utils;

/**
 * Created by fishyu on 2018/1/2.
 */

public class LogoutActivity extends ETHomeActivity<LoginMode> {


    @Override
    protected LoginMode createMode() {
        return LoginMode.getInstance();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DUser user = SharedPrefAccountManager.getInstance().getCurrentUser();
        if (user != null) {
            getModel().performRequest(new LogoutRequest(user.mUserName));
        } else {
            onRequestStart(null);
        }
    }

    @Override
    public void onRequestStart(SimpleRequest request) {
        super.onRequestStart(request);

        Utils.showToast("U have been logout successfully!");

        // jump to LoginDispatchActivity
        LoginDispatcherActivity.jumpToThis(this);
        finish();
    }

}
