package com.acmes.simpleandroid.dagger2Demo;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by fishyu on 2017/8/25.
 */

public class Thermosiphon implements Pump{


    final Heater heater;

    @Inject
    public Thermosiphon(Heater heater) {
        this.heater = heater;
    }

    @Override
    public void pump() {
        Log.e(Thermosiphon.class.getSimpleName(),"Thermosiphon is pumping ...");
    }
}
