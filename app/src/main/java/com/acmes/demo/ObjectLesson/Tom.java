package com.acmes.demo.ObjectLesson;

/**
 * Created by fishyu on 2017/9/8.
 */

public class Tom {




    public WaterProvider mWaterProvider;

    void buyWaterHeater() {
        mWaterProvider = new WaterProvider() {
            @Override
            public void heat() {

            }

            @Override
            public Water getWater() {
                return null;
            }
        };
    }


    void drinkWater(Water water) {

    }



    void winProvider(WaterProvider waterProvider) {
        mWaterProvider = waterProvider;
    }



    static void main() {
//
//        Tom tom = new Tom();
//
//        tom.buyWaterHeater();
//
//        tom.mWaterProvider.heat();
//
//        Water water = tom.mWaterProvider.getWater();
//        tom.drinkWater(water);




        Tom tom = new Tom();


        WaterProvider forFree = new WaterProvider() {
            @Override
            public void heat() {

            }

            @Override
            public Water getWater() {
                return null;
            }
        };


        WaterProvider forSale = new WaterProvider() {
            @Override
            public void heat() {

            }

            @Override
            public Water getWater() {
                return null;
            }
        };


        tom.winProvider(forFree);

//        tom.drinkWater(tom.mWaterProvider.getWater());


        Water water = tom.mWaterProvider.getWater();




        boolean bool =  (Boolean) (Object)tom.mWaterProvider.getWater();


        tom.winProvider(forSale);


    }










}
