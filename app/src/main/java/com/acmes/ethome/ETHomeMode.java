package com.acmes.ethome;

import com.acmes.simpleandroid.imp.Square.RetrofitSimpleModel;

/**
 * Created by fishyu on 2018/1/2.
 */

public class ETHomeMode extends RetrofitSimpleModel<ETHomeAPI> {

    public ETHomeMode() {
        super(ETHomeApplication.getInstance().getRetrofit(), ETHomeAPI.class);
    }
}
