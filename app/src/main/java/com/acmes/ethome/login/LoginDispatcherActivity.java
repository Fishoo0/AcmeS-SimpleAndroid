package com.acmes.ethome.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.acmes.ethome.ETHomeActivity;
import com.acmes.ethome.main.MainActivity;
import com.acmes.ethome.mode.request.LoginRequest;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

/**
 * Created by fishyu on 2018/1/2.
 */

public class LoginDispatcherActivity extends ETHomeActivity<LoginMode> {

    IAccountManager mAccountManager = SharedPrefAccountManager.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dispatch();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        dispatch();
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    /**
     * Dispatch Activity
     */
    protected void dispatch() {
        Log.e(TAG, "dispatch");
        if (mAccountManager.getCurrentUser() == null) {
            //no current user ,goto login activity
            startActivity(new Intent(this, LogoutActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, MainActivity.class));
            getModel().login(new LoginRequest(mAccountManager.getCurrentUser().mUserName));
            finish();
        }
    }

    @Override
    protected LoginMode createMode() {
        return LoginMode.getInstance();
    }


    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);
        if (request instanceof LoginRequest) {
            if (!(response.isSuccess())) {
                Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
                mAccountManager.removeUser(null);
                jumpToThis(this);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        getModel().removeSimpleCallback(this);
    }

    /**
     * When token expired, call this method for re-dispatch
     *
     * @param context
     */
    public static final void jumpToThis(Context context) {
        jumpToThis(context, getJumpToThisIntent(context));
    }

    public static final void jumpToThis(Context context, Intent intent) {
        context.startActivity(intent);
    }

    public static final Intent getJumpToThisIntent(Context context) {
        Intent intent = new Intent(context, LoginDispatcherActivity.class);
        return intent;
    }

}
