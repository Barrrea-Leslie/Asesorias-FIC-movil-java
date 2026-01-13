package com.lesliefic.asesoriasfic.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Modalidad implements Serializable {
    @SerializedName("id_modalidad")
    private int id_modalidad;
    @SerializedName("modalidad")
    private String modalidad;

    public Modalidad(int id_modalidad, String modalidad) {
        this.id_modalidad = id_modalidad;
        this.modalidad = modalidad;
    }


    public int getId_modalidad(){
        return id_modalidad;
    }

    public String getModalidad(){
        return  modalidad;
    }


}
