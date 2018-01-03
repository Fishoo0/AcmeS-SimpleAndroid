package com.acmes.ethome.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.acmes.ethome.ETHomeActivity;
import com.acmes.ethome.R;
import com.acmes.ethome.mode.request.LoginRequest;
import com.acmes.ethome.mode.request.RegisterRequest;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;
import com.acmes.simpleandroid.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fishyu on 2018/1/2.
 */

public class RegisterActivity extends ETHomeActivity<LoginMode> implements View.OnClickListener {

    @Override
    protected LoginMode createMode() {
        return new LoginMode();
    }

    @BindView(R.id.user_name)
    EditText mUserName;


    @BindView(R.id.user_password)
    EditText mUserPassword;


    @BindView(R.id.submit_button)
    View mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }


    @Override
    public void onRequestStart(SimpleRequest requestTag) {
        super.onRequestStart(requestTag);
        if (requestTag instanceof LoginRequest) {
            mButton.setClickable(false);
        }
    }


    @Override
    public void onResponse(SimpleRequest requestTag, SimpleResponse response) {
        super.onResponse(requestTag, response);
        if (requestTag instanceof LoginRequest) {
            mButton.setClickable(true);
            if (response.isSuccess()) {

            }
        }

        Utils.showToast(response.getMessage() + " " + response.getData());
    }


    @Override
    public void onFailure(SimpleRequest requestTag, Throwable exception) {
        super.onFailure(requestTag, exception);
        if (requestTag instanceof LoginRequest) {
            mButton.setClickable(true);
        }
    }

    @OnClick(R.id.submit_button)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_button:
                getModel().register(new RegisterRequest(mUserName.getText().toString(), mUserPassword.getText().toString()));
                break;
        }
    }

}
