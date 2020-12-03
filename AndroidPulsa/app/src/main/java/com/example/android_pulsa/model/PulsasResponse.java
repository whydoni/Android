package com.example.android_pulsa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PulsasResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private PulsaModel data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PulsaModel getData() {
        return data;
    }

    public void setData(PulsaModel data) {
        this.data = data;
    }
}
