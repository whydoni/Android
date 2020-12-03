package com.example.android_pulsa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_pulsa.model.PulsaModel;
import com.example.android_pulsa.model.PulsaResponse;
import com.example.android_pulsa.model.PulsasResponse;
import com.example.android_pulsa.networking.PulsaRepository;

public class PulsaViewModel extends ViewModel {
    private MutableLiveData<PulsaResponse> mutableLiveData;
    private PulsaRepository pulsaRepository;
    private MutableLiveData<PulsasResponse> mutablePulsaLiveData;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        pulsaRepository = PulsaRepository.getInstance();
        mutableLiveData = pulsaRepository.getProduct("1", "10");
    }

    public LiveData<PulsaResponse> getProduct() {
        return mutableLiveData;
    }

    public LiveData<PulsasResponse> postPulsa(PulsaModel model) {
        if (mutablePulsaLiveData == null) {
            pulsaRepository = PulsaRepository.getInstance();
        }
        mutablePulsaLiveData = pulsaRepository.postPulsa(model);
        return mutablePulsaLiveData;
    }

}
