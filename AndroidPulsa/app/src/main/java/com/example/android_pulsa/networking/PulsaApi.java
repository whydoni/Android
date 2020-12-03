package com.example.android_pulsa.networking;

import com.example.android_pulsa.model.PulsaModel;
import com.example.android_pulsa.model.PulsaResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PulsaApi {

    @GET("product")
    Call<PulsaResponse> getProduct(@Query("page") String page, @Query("limit") String limit);

    @POST("pulsa")
    Call<PulsaResponse> postPulsa(@Body PulsaModel body);
}
