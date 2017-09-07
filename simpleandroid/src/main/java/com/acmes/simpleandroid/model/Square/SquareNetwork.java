package com.acmes.simpleandroid.model.Square;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fishyu on 2017/9/6.
 * <p>
 * <p>
 * Single Instance
 * <p>
 * <p>
 * My God ,u are amazing <url>https://github.com/JakeWharton<url/>
 */
public class SquareNetwork {

    private HttpLoggingInterceptor mHttpLoggingInterceptor;
    private OkHttpClient mOkHttpClient;

    private Picasso mPicasso;

    private Retrofit mRetrofit;

    public SquareNetwork(Application application, String baseUrl) {
        mHttpLoggingInterceptor = initHttpLoggingInterceptor();
        mOkHttpClient = initOkHttp(mHttpLoggingInterceptor);

        mRetrofit = initRetrofit(mOkHttpClient, baseUrl);

        mPicasso = initPicasso(application, mOkHttpClient);
    }


    private HttpLoggingInterceptor initHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("OKHttp", message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return httpLoggingInterceptor;
    }


    private OkHttpClient initOkHttp(HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        return mOkHttpClient;
    }


    public HttpLoggingInterceptor getHttpLoggingInterceptor() {
        return mHttpLoggingInterceptor;
    }


    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }


    private Retrofit initRetrofit(OkHttpClient okHttpClient, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .build();
        return retrofit;
    }


    private Picasso initPicasso(Application application, OkHttpClient okHttpClient) {
        Picasso picasso = new Picasso.Builder(application)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .memoryCache(new LruCache(application))
                .build();
        return picasso;
    }


    /**
     * Getting the picture processor
     *
     * @return
     */
    public Picasso getPicasso() {
        return mPicasso;
    }


    /**
     * Getting the http processor
     *
     * @return
     */
    public Retrofit getRetrofit() {
        return mRetrofit;
    }


}
