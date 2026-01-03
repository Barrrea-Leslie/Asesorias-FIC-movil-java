package com.lesliefic.asesoriasfic.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Grupo implements Serializable {

    @SerializedName("id_grupo")
    private int id_grupo;

    @SerializedName("id_licenciatura")
    private int id_licenciatura;

    @SerializedName("grupo")
    private String grupo;

    public Grupo(int id_grupo, int id_licenciatura, String grupo){
        this.id_grupo = id_grupo;
        this.id_licenciatura = id_licenciatura;
        this.grupo = grupo;
    }

    public int getIdGrupo(){
        return id_grupo;
    }

    public int getIdLicenciatura(){
        return id_licenciatura;
    }

    public String getGrupo(){
        return grupo;
    }

}
