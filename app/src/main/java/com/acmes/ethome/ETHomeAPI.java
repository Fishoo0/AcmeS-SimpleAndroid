package com.acmes.ethome;

import com.acmes.ethome.mode.bean.DAccount;
import com.acmes.ethome.mode.request.LoginRequest;
import com.acmes.ethome.mode.response.ETHomeResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * Created by fishyu on 2018/1/2.
 */

public interface ETHomeAPI {

    String BASE_URL = "http://10.226.66.204:5000";

    @POST("register")
    Observable<ETHomeResponse<DAccount>> register(@Field("user_name") String userName, @Field("user_password") String userPassword);

    @POST("login")
    Observable<ETHomeResponse<DAccount>> login(@Body LoginRequest loginRequest);

    @POST("logout")
    Observable<ETHomeResponse> logout(String userName);

}
