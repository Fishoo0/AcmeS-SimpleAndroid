package com.acmes.demo.simpleandroid;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by fishyu on 2017/9/5.
 */

public interface AcmesService {

    @GET("acmes.php")
    Call<DAcmeS> acmes();
}
