package com.acmes.demo.dagger2Demo;

import javax.inject.Inject;

/**
 * Created by fishyu on 2017/8/25.
 */

public class CoffeeMaker {

    @Inject
    Heater heater;

    @Inject
    Pump pump;



    public void make() {
        heater.heat();
        pump.pump();
    }



}
