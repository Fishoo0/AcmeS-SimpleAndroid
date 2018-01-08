package com.acmes.ethome.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.acmes.ethome.ETHomeActivity;
import com.acmes.ethome.R;
import com.acmes.ethome.mode.bean.DUser;
import com.acmes.ethome.mode.request.LoginRequest;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;
import com.acmes.simpleandroid.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fishyu on 2018/1/2.
 */

public class LoginActivity extends ETHomeActivity<LoginMode> implements View.OnClickListener {

    @Override
    protected LoginMode createMode() {
        return new LoginMode();
    }

    @BindView(R.id.user_name)
    EditText mUserName;


    @BindView(R.id.user_password)
    EditText mUserPassword;


    @BindView(R.id.submit_button)
    Button mButton;


    public static final String LOGIN_INFO = "user";

    public static final void jumpToThis(Context context, DUser user) {
        Intent intent = new Intent(context, LoginActivity.class);
        if (user != null) {
            intent.putExtra(LOGIN_INFO, user);
        }
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        if (getIntent().hasExtra(LOGIN_INFO)) {
            final DUser user = (DUser) getIntent().getSerializableExtra(LOGIN_INFO);
            getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getModel().login(new LoginRequest(user.mUserName, user.mUserPassword));
                }
            }, 500);
        }
    }


    @Override
    public void onRequestStart(SimpleRequest request) {
        super.onRequestStart(request);
        mButton.setText("Login ing ...");
        mButton.setClickable(false);
    }


    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);
        if (response.isSuccess()) {
            ETHomeDispatcherActivity.jumpToThis(this);
            finish();
        }

        Utils.showToast(response.getMessage() + " " + response.getData());

        mButton.setClickable(true);
        mButton.setText("Login");
    }


    @Override
    public void onFailure(SimpleRequest request, Throwable exception) {
        super.onFailure(request, exception);

        mButton.setClickable(true);
        mButton.setText("Login");
    }

    @OnClick({R.id.submit_button, R.id.goto_register_button})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_button:
                getModel().login(new LoginRequest(mUserName.getText().toString(), mUserPassword.getText().toString()));
                break;

            case R.id.goto_register_button:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
        }
    }


}
