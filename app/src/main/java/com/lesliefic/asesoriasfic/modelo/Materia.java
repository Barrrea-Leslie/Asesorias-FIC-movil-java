package com.lesliefic.asesoriasfic.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Materia implements Serializable {
    @SerializedName("id_materia")
    private int id_materia;
    @SerializedName("materia")
    private String materia;
    @SerializedName("id_semestre")
    private int id_semestre;
    @SerializedName("id_licenciatura")
    private int id_licenciatura;

    public Materia(int id_materia, String materia){
        this.id_materia = id_materia;
        this.materia = materia;
    }

    public int getId_materia() { return id_materia; }

    public String getMateria() {return materia;}

    public int getId_semestre() {return  id_semestre;}

    public int getId_licenciatura() {return  id_licenciatura;}





}