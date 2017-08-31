package com.acmes.simpleandroid.dagger2Demo;

import dagger.Component;

/**
 * Created by fishyu on 2017/8/25.
 */

@Component(modules = {
        DripCoffeeModule.class
})
interface CoffeeShop {

    CoffeeMaker maker(CoffeeMaker make);

}
