package com.acmes.demo.ObjectLesson;

/**
 * Created by fishyu on 2017/9/8.
 */

public interface WaterProvider {


    /**
     * 加热水
     */
    void heat();


    /**
     * 放水
     *
     * @return
     */
    Water getWater();


}
