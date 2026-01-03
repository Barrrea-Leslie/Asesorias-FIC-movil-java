package com.lesliefic.asesoriasfic.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Horario implements Serializable {
    @SerializedName("id_horario")
    private int id_horario;
    @SerializedName("horario")
    private String horario;



    public Horario(int id_horario, String horario){
        this.id_horario = id_horario;
        this.horario = horario;
    }

    public int getId_horario() { return id_horario; }

    public String getHorario() {return horario;}






}