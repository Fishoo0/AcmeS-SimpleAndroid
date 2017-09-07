package com.acmes.demo;

import com.acmes.simpleandroid.SimpleApplication;

/**
 * Created by fishyu on 2017/9/6.
 */

public class DemoApplication extends SimpleApplication {

    public static DemoApplication getInstance() {
        return (DemoApplication) getSimpleApplicationInstance();
    }
}
