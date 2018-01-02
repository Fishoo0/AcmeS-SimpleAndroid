package com.acmes.ethome.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.acmes.ethome.R;
import com.acmes.ethome.ETHomeActivity;

import butterknife.BindView;

/**
 * Created by fishyu on 2018/1/2.
 */

public class SplashActivity extends ETHomeActivity {


    @BindView(R.id.textview)
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
