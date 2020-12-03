package com.example.android_pulsa.networking;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.android_pulsa.model.PulsaModel;
import com.example.android_pulsa.model.PulsaResponse;
import com.example.android_pulsa.model.PulsasResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PulsaRepository {
    private static PulsaRepository pulsaRepository;

    public static PulsaRepository getInstance() {
        if (pulsaRepository == null) {
            pulsaRepository =new PulsaRepository();
        }
        return pulsaRepository;
    }

    private PulsaApi pulsaApi;

    public PulsaRepository() {
        pulsaApi = RetrofitService.createService(PulsaApi.class);
    }

    public MutableLiveData<PulsaResponse> getProduct(String page, String limit) {
        MutableLiveData<PulsaResponse> productData = new MutableLiveData<>();
        pulsaApi.getProduct(page, limit)
                .enqueue(new Callback<PulsaResponse>() {
                    @Override
                    public void onResponse(Call<PulsaResponse> call, Response<PulsaResponse> response) {
                        if (response.isSuccessful()) {
                            Log.v("Log get data : ", response.body().toString());
                            productData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<PulsaResponse> call, Throwable t) {
                        Log.v("Log get error : ", t.getMessage());
                        productData.setValue(null);
                    }
                });
        return productData;
    }

    public MutableLiveData<PulsasResponse> postPulsa (PulsaModel model) {
        MutableLiveData<PulsaResponse> productData = new MutableLiveData<>();
        pulsaApi.postPulsa(model)
                .enqueue(new Callback<PulsaResponse>() {
                    @Override
                    public void onResponse(Call<PulsaResponse> call, Response<PulsaResponse> response) {
                        if (response.isSuccessful()) {
                            Log.v("Log post pulsa : ", response.body().toString());
                            productData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<PulsaResponse> call, Throwable t) {
                        Log.v("Log post error : ", t.getMessage());
                        productData.setValue(null);
                    }
                });
        return productData;
    }
}
