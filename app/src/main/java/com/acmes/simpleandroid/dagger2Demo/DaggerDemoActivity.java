package com.acmes.simpleandroid.dagger2Demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by fishyu on 2017/8/25.
 */

public class DaggerDemoActivity extends Activity {

    static final String TAG = DaggerDemoActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"onCreate");

        CoffeeMaker make = DaggerCoffeeShop.builder()
                .build()
                .maker(new CoffeeMaker());


        make.make();


    }


}
