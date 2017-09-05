package com.acmes.demo.dagger2Demo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fishyu on 2017/8/25.
 */


@Module
public class DripCoffeeModule {

    @Provides
    Heater provideHeater() {
        return new ElectricHeater();
    }


    @Provides
    static Pump providePump(Thermosiphon pump) {
        return pump;
    }
}
