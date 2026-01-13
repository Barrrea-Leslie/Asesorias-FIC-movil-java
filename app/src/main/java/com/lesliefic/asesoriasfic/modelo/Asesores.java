package com.lesliefic.asesoriasfic.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Asesores implements Serializable {
    @SerializedName("id_persona")
    private int id_persona;

    @SerializedName("asesor")
    private String asesor;

    @SerializedName("materiasasesor")
    private List<Materia> materiasAsesor;

    @SerializedName("horariosasesor")
    private List<Horario> horariosAsesor;


    public int getId_persona(){
        return id_persona;
    }

    public String getAsesor(){
        return asesor;
    }

}
