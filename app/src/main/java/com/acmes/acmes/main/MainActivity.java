package com.acmes.acmes.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.acmes.acmes.AcmesActivity;
import com.acmes.acmes.R;
import com.acmes.acmes.login.AcmesDispatcherActivity;
import com.acmes.simpleandroid.mvc.model.SimpleModel;

import butterknife.OnClick;

/**
 * Created by fishyu on 2018/1/2.
 */

public class MainActivity extends AcmesActivity implements View.OnClickListener {

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
                AcmesDispatcherActivity.jumpToThis(this, AcmesDispatcherActivity.CMD_LOGOUT);
                break;
        }
    }


}
