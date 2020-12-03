package com.example.android_pulsa.model;

import java.io.Serializable;

public class PulsaModel implements Serializable {
    private int id;
    private String code;
    private double nominal;
    private double price;
    private String Nomor;

    public  PulsaModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getNominal() {
        return nominal;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNomor(String nomor) {
        Nomor = nomor;
    }

    public String getNomor() {
        return Nomor;
    }
}
