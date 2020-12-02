package com.example.berita.networking;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.berita.model.BeritaResponse;
import com.example.berita.model.BeritaModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeritaRepository {
    private static BeritaRepository beritaRepository;

    public static BeritaRepository getInstance(){
        if (beritaRepository==null){
            beritaRepository=new BeritaRepository();
        }
        return beritaRepository;
    }

    private BeritaApi beritaApi;

    public BeritaRepository(){
        beritaApi=RetrofitService.createService(BeritaApi.class);
    }

    public MutableLiveData<BeritaResponse> getBerita(String page, String limit){
        MutableLiveData<BeritaResponse> mutableLiveData = new MutableLiveData<>();
        beritaApi.getBerita(page,limit)
                .enqueue(new Callback<BeritaResponse>() {
                    @Override
                    public void onResponse(Call<BeritaResponse> call, Response<BeritaResponse> response) {
                        if (response.isSuccessful()){
                            Log.v("Success fetch: ",response.body().toString());
                            mutableLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<BeritaResponse> call, Throwable t) {
                        Log.v("Error fetch: ",t.getMessage());
                        mutableLiveData.setValue(null);
                    }
                });
        return mutableLiveData;
    }

    public MutableLiveData<BeritaResponse> postBerita(BeritaModel model){
        MutableLiveData<BeritaResponse> mutableLiveData=new MutableLiveData<>();
        beritaApi.addBerita(model)
                .enqueue(new Callback<BeritaResponse>() {
                    @Override
                    public void onResponse(Call<BeritaResponse> call, Response<BeritaResponse> response) {
                        if (response.isSuccessful()){
                            Log.v("Success post : ",response.body().toString());
                            mutableLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<BeritaResponse> call, Throwable t) {
                        Log.v("Error post : ",t.getMessage());
                        mutableLiveData.setValue(null);
                    }
                });
        return  mutableLiveData;
    }
}
