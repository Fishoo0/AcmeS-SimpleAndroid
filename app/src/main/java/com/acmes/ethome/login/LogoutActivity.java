package com.acmes.ethome.login;

import com.acmes.ethome.ETHomeActivity;

/**
 * Created by fishyu on 2018/1/2.
 */

public class LogoutActivity extends ETHomeActivity<LoginMode> {
    @Override
    protected LoginMode createMode() {
        return LoginMode.getInstance();
    }
}
