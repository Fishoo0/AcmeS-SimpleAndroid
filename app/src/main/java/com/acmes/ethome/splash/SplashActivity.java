package com.acmes.ethome.splash;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.acmes.ethome.ETHomeActivity;
import com.acmes.ethome.ETHomeApplication;
import com.acmes.ethome.R;
import com.acmes.simpleandroid.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    public void onInitializing(int progress) {
        mInitialProgress.setText(progress + " %");
        if (progress == 100) {
            //Jump to home
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ETHomeApplication.getInstance().unregisterInitializeListener(this);
    }
}
