package com.acmes.ethome.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.acmes.ethome.ETHomeActivity;
import com.acmes.ethome.main.MainActivity;
import com.acmes.ethome.mode.request.LoginRequest;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;
import com.acmes.simpleandroid.utils.Utils;

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

    /**
     * Dispatch Activity
     */
    protected void dispatch() {
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
        return new LoginMode();
    }


    /**
     * Start activity
     *
     * @param clz
     */
    protected void startActivity(Class clz) {
        Intent intent = new Intent(this, clz);
        intent.putExtras(getIntent());
        startActivity(intent);
        finish();
    }

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);
        if (request instanceof LoginRequest) {
            if (!(response.isSuccess())) {
                Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
                mAccountManager.removeUser(null);
                jumpToThis(this);
            } else {
                Utils.showToast("Now we should jump to MainActivity");
                finish();
            }
        }
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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

}
