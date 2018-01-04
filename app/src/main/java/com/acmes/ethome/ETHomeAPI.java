package com.acmes.ethome;

import com.acmes.ethome.mode.bean.DUser;
import com.acmes.ethome.mode.request.LoginRequest;
import com.acmes.ethome.mode.request.LogoutRequest;
import com.acmes.ethome.mode.request.RegisterRequest;
import com.acmes.ethome.mode.response.ETHomeResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by fishyu on 2018/1/2.
 */

public interface ETHomeAPI {

    String BASE_URL = "http://10.226.66.204:5000";

    @POST("register")
    Observable<ETHomeResponse<DUser>> register(@Body RegisterRequest loginRequest);

    @POST("login")
    Observable<ETHomeResponse<DUser>> login(@Body LoginRequest loginRequest);

    @POST("logout")
    Observable<ETHomeResponse> logout(@Body LogoutRequest request);

}
