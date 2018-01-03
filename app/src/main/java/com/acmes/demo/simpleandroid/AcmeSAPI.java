package com.acmes.demo.simpleandroid;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by fishyu on 2017/9/5.
 */

public interface AcmeSAPI {

    @GET("acmes.php")
    Call<DAcmeS> acmes();

    @GET("acmes.php")
    Flowable<DAcmeS> acmesRx();

    @GET("acmes.php")
    Observable<DAcmeS> acmesRxObservable();

    @POST("login")
    Call<DTest> test();

}
