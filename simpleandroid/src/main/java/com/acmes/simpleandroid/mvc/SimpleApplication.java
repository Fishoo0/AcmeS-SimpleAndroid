package com.acmes.simpleandroid.mvc;

import android.app.Application;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.List;

import hugo.weaving.DebugLog;

/**
 * Created by fishyu on 2017/9/5.
 */

public class SimpleApplication extends Application {

    protected final String TAG = getClass().getSimpleName();

    private Handler mHandlerAsync;
    private Handler mHandler = new Handler();

    protected static SimpleApplication mInstance;

    private List<IInitializeListener> mInitializeListener;

    private int mInitializeProgress = 0;

    public static SimpleApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "onCreate");
        mInstance = this;
        HandlerThread thread = new HandlerThread("AcmeS$SimpleApplication$HandlerAsyn");
        thread.start();
        mHandlerAsync = new Handler(thread.getLooper());
        mHandlerAsync.post(new Runnable() {
            @Override
            public void run() {
                onInitialize();
            }
        });
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


    /**
     * Initialize progress
     */
    public interface IInitializeListener {

        void onInitializing(int progress);

    }

    /**
     * Register onInitialize listener
     *
     * @param listener
     */
    public synchronized void registerInitializeListener(IInitializeListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener can not be null");
        }
        if (mInitializeListener == null) {
            mInitializeListener = new ArrayList<>();
        }
        mInitializeListener.remove(listener);
        mInitializeListener.add(listener);

        //refresh status
        listener.onInitializing(mInitializeProgress);
    }

    /**
     * Unregister onInitialize listener
     *
     * @param listener
     */
    public synchronized void unregisterInitializeListener(IInitializeListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener can not be null");
        }
        if (mInitializeListener == null) {
            return;
        }
        mInitializeListener.remove(listener);
    }

    /**
     * Updating progress and notify listeners
     *
     * @param progress
     */
    protected final synchronized void updateProgress(int progress) {
        if (progress >= 0 && progress <= 100) {
            mInitializeProgress = progress;
        } else {
            throw new IllegalArgumentException("Invalid status -> " + progress);
        }
        if (mInitializeListener != null) {
            for (final IInitializeListener listener : mInitializeListener) {
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onInitializing(mInitializeProgress);
                        }
                    });
                } else {
                    listener.onInitializing(mInitializeProgress);
                }
            }
        }
    }

    /**
     * Initialize the params in Async-Thread
     */
    protected void onInitialize() {
        updateProgress(0);
        if (LeakCanary.isInAnalyzerProcess(SimpleApplication.this)) {
            Log.e(TAG, "LeakCanary isInAnalyzerProcess , return ");
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not onInitialize your app in this process.
            return;
        }
        LeakCanary.install(SimpleApplication.this);
        Log.e(TAG, "LeakCanary installed");
        updateProgress(10);
    }


    /**
     * Getting initialize progress
     *
     * @return
     */
    protected int getInitializeProgress() {
        return mInitializeProgress;
    }


}
