package com.lesliefic.asesoriasfic.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Licenciatura implements Serializable {
    @SerializedName("id_licenciatura")
    private int id_licenciatura;
    @SerializedName("licenciatura")
    private String licenciatura;

    public Licenciatura(int id_licenciatura, String licenciatura){
        this.id_licenciatura = id_licenciatura;
        this.licenciatura = licenciatura;
    }

    public int getId_licenciatura(){
        return id_licenciatura;
    }

    public String getLicenciatura(){
        return  licenciatura;
    }


}
