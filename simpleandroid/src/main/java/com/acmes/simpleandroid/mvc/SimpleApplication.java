package com.acmes.simpleandroid.mvc;

import android.app.Application;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by fishyu on 2017/9/5.
 */

public class SimpleApplication extends Application {

    protected final String TAG = getClass().getSimpleName();

    private Handler mHandlerAsync;
    private Handler mHandler = new Handler();

    protected static SimpleApplication mInstance;

    public static SimpleApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "onCreate");
        mInstance = this;

        if (LeakCanary.isInAnalyzerProcess(this)) {
            Log.e(TAG, "LeakCanary isInAnalyzerProcess , return ");
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        LeakCanary.install(this);
        Log.e(TAG, "LeakCanary installed");

        HandlerThread thread = new HandlerThread("AcmeS$SimpleApplication$HandlerAsyn");
        thread.start();
        mHandlerAsync = new Handler(thread.getLooper());
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.v(TAG, "onLowMemory");

    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.v(TAG, "onTrimMemory");

    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.v(TAG, "onTerminate");

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.v(TAG, "onConfigurationChanged -> " + newConfig);

    }

    /**
     * Getting application scope mainThread Handler
     *
     * @return
     */
    public Handler getHandler() {
        return mHandler;
    }


    /**
     * Getting application scope asyncThread Handler
     *
     * @return
     */
    public Handler getHandlerAsync() {
        return mHandlerAsync;
    }


}
