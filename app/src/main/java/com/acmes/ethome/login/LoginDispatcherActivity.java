package com.acmes.ethome.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.acmes.ethome.ETHomeActivity;
import com.acmes.ethome.mode.request.LoginRequest;
import com.acmes.ethome.mode.response.ETHomeResponse;
import com.acmes.ethome.main.MainActivity;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

/**
 * Created by fishyu on 2018/1/2.
 */

public class LoginDispatcherActivity extends ETHomeActivity<LoginMode> {

    IAccountManager mAccountManager = SharedPrefAccountManager.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initialize();
    }


    protected void initialize() {
        if (mAccountManager.getCurrentUser() == null) {
            //no current user ,goto login activity
            startActivity(LoginActivity.class);
        } else {
            startActivity(MainActivity.class);
            getModel().login(new LoginRequest(mAccountManager.getCurrentUser().mUserName));
        }
    }

    @Override
    protected LoginMode createMode() {
        return LoginMode.getInstance();
    }


    protected void startActivity(Class clz) {
        startActivity(new Intent(this, clz));
        finish();
    }


    @Override
    public void onResponse(Object requestTag, SimpleResponse response) {
        super.onResponse(requestTag, response);
        if (response instanceof ETHomeResponse) {
            if (!((ETHomeResponse) response).isSuccess()) {
                Toast.makeText(this, ((ETHomeResponse) response).getMessage(), Toast.LENGTH_SHORT).show();
                jumpToThisWhenTokenExpired(this);
            }
        }
    }


    /**
     * When token expired, call this method for re-dispatch
     *
     * @param context
     */
    public static final void jumpToThisWhenTokenExpired(Context context) {
        Intent intent = new Intent(context, LoginDispatcherActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

}
