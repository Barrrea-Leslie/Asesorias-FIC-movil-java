package com.lesliefic.asesoriasfic.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Asesores implements Serializable {
    @SerializedName("id_persona")
    private int id_persona;

    @SerializedName("asesor")
    private String asesor;


    public int getId_persona(){
        return id_persona;
    }

    public String getAsesor(){
        return asesor;
    }

}
