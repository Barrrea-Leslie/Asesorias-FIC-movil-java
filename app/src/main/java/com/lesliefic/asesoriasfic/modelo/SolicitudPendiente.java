package com.lesliefic.asesoriasfic.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SolicitudPendiente implements Serializable {
    @SerializedName("id_solicitud")
    private int id_solicitud;

    @SerializedName("id_materia")
    private int id_materia;

    @SerializedName("materia")
    private String materia;

    @SerializedName("id_horario")
    private int id_horario;

    @SerializedName("horario")
    private String horario;

    @SerializedName("id_modalidad")
    private int id_modalidad;

    @SerializedName("modalidad")
    private String modalidad;

    @SerializedName("id_estudiante")
    private int id_estudiante;

    @SerializedName("estudiante")
    private String estudiante;

    @SerializedName("id_asesor")
    private int id_asesor;

    @SerializedName("asesor")
    private String asesor;

    @SerializedName("fecha_inicio")
    private String fecha_inicio_bd;
    private String fecha_inicio = fecha_inicio_bd.substring(0, 10);

    @SerializedName("id_razon")
    private int id_razon;

    @SerializedName("razon")
    private String razon;

    @SerializedName("nota_estudiante")
    private String nota_estudiante;

    @SerializedName("explicacion_asesor")
    private String explicacion_asesor;

    @SerializedName("id_estatus")
    private int id_estatus;

    @SerializedName("estatus")
    private String estatus;

    public int getId_solicitud() {
        return id_solicitud;
    }

    public int getId_materia() {
        return id_materia;
    }

    public String getMateria() {
        return materia;
    }

    public int getId_horario() {
        return id_horario;
    }

    public String getHorario() {
        return horario;
    }

    public int getId_modalidad() {
        return id_modalidad;
    }

    public String getModalidad() {
        return modalidad;
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public int getId_asesor() {
        return id_asesor;
    }

    public String getAsesor(){
        return asesor;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public int getId_razon() {
        return id_razon;
    }

    public String getRazon() {
        return razon;
    }

    public String getNota_estudiante() {
        return nota_estudiante;
    }

    public String getExplicacin_asesor() {
        return explicacion_asesor;
    }

    public int getId_estatus() {
        return id_estatus;
    }

    public String getEstatus() {
        return estatus;
    }


}
