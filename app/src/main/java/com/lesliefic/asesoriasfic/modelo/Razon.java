package com.lesliefic.asesoriasfic.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Razon implements Serializable {

    @SerializedName("id_razon")
    private int id_razon;

    @SerializedName("razon")
    private String razon;

    public Razon(int id_razon, String razon) {
        this.id_razon = id_razon;
        this.razon = razon;
    }

    public int getId_razon() {
        return id_razon;
    }

    public String getRazon() {
        return razon;
    }
}