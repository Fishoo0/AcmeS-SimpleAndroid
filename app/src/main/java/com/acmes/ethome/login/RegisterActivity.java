package com.acmes.ethome.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.acmes.ethome.ETHomeActivity;
import com.acmes.ethome.R;
import com.acmes.ethome.mode.bean.DUser;
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
        setContentView(R.layout.register_activity);
    }


    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);
        if (request instanceof RegisterRequest) {
            if (response.isSuccess()) {
                ETHomeDispatcherActivity.jumpToThis(
                        this,
                        ETHomeDispatcherActivity.CMD_LOGIN,
                        ETHomeDispatcherActivity
                                .getJumpToThisIntent(this)
                                .putExtra(LoginActivity.LOGIN_INFO,
                                        new DUser(((RegisterRequest) request).user_name, ((RegisterRequest) request).user_password))
                );
                finish();
            }
        }
        Utils.showToast(response.getMessage() + " " + response.getData());
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
