package com.acmes.demo.dagger2Demo;

import android.util.Log;

import javax.inject.Singleton;

/**
 * Created by fishyu on 2017/8/25.
 */

@Singleton
public class ElectricHeater implements Heater {

    @Override
    public void heat() {
        Log.e(ElectricHeater.class.getSimpleName(), "ElectricHeater heating ...");
    }



}
