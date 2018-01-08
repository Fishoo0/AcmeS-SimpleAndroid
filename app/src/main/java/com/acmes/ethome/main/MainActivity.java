package com.acmes.ethome.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.acmes.ethome.ETHomeActivity;
import com.acmes.ethome.R;
import com.acmes.ethome.login.ETHomeDispatcherActivity;
import com.acmes.simpleandroid.mvc.model.SimpleModel;

import butterknife.OnClick;

/**
 * Created by fishyu on 2018/1/2.
 */

public class MainActivity extends ETHomeActivity implements View.OnClickListener {

    @Override
    protected SimpleModel createMode() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @OnClick(R.id.logout_button)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout_button:
                //goto logout activity
                ETHomeDispatcherActivity.jumpToThis(this, ETHomeDispatcherActivity.CMD_LOGOUT);
                break;
        }
    }


}
