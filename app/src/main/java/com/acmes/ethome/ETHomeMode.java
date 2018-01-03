package com.acmes.ethome;

import com.acmes.simpleandroid.imp.Square.RetrofitSimpleModel;

import retrofit2.Retrofit;

/**
 * Created by fishyu on 2018/1/2.
 */

public class ETHomeMode extends RetrofitSimpleModel<ETHomeAPI> {

    static Retrofit sRetrofit = ETHomeApplication.getInstance().buildRetrofit(ETHomeAPI.BASE_URL);

    public ETHomeMode() {
        super(sRetrofit, ETHomeAPI.class);
    }
}
