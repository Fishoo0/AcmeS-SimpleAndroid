package com.acmes.ethome.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.acmes.ethome.ETHomeActivity;
import com.acmes.ethome.ETHomeApplication;
import com.acmes.ethome.R;
import com.acmes.ethome.login.LoginDispatcherActivity;
import com.acmes.simpleandroid.mvc.model.SimpleModel;
import com.acmes.simpleandroid.utils.Utils;

import butterknife.BindView;
import hugo.weaving.DebugLog;

/**
 * Created by fishyu on 2018/1/2.
 */

public class SplashActivity extends ETHomeActivity implements ETHomeApplication.IInitializeListener {

    @BindView(R.id.version)
    TextView mTextView;

    @BindView(R.id.imageview)
    ImageView mImageView;

    @BindView(R.id.progress)
    TextView mInitialProgress;

    @DebugLog
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        mTextView.setText(String.format(getString(R.string.value_splash_version), Utils.getPackageVersionName()));
        ETHomeApplication.getInstance().registerInitializeListener(SplashActivity.this);
    }

    @Override
    protected SimpleModel createMode() {
        return null;
    }

    @Override
    public void onInitializing(int progress) {
        mInitialProgress.setText(progress + " %");
        if (progress == 100) {
            //Jump to home
            ETHomeApplication.getInstance().getHandler().post(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, LoginDispatcherActivity.class));
                    finish();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ETHomeApplication.getInstance().unregisterInitializeListener(this);
    }
}
