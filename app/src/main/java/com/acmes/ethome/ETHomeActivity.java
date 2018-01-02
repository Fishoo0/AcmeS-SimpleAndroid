package com.acmes.ethome;

import com.acmes.simpleandroid.mvc.SimpleActivity;

/**
 * Created by fishyu on 2018/1/2.
 */

public class ETHomeActivity extends SimpleActivity<ETHomeMode> {

    @Override
    protected ETHomeMode createMode() {
        return new ETHomeMode();
    }
}
