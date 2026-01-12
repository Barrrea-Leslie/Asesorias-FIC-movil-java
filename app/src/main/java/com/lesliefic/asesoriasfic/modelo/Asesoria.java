package com.lesliefic.asesoriasfic.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Asesoria implements Serializable {

    @SerializedName("id_asesoria")
    private int idAsesoria;

    @SerializedName("id_estudiante")
    private int idEstudiante;

    @SerializedName("estudiante")
    private String estudiante;

    @SerializedName("id_asesor")
    private int idAsesor;

    @SerializedName("asesor")
    private String asesor;

    @SerializedName("id_materia")
    private int idMateria;

    @SerializedName("materia")
    private String materia;

    @SerializedName("id_modalidad")
    private int idModalidad;

    @SerializedName("modalidad")
    private String modalidad;

    @SerializedName("fecha_inicio")
    private String fechaInicio;

    @SerializedName("fecha_fin")
    private String fechaFin;

    @SerializedName("id_razon")
    private int idRazon;

    @SerializedName("razon")
    private String razon;

    @SerializedName("id_licenciatura")
    private int idLicenciatura;

    @SerializedName("licenciatura")
    private String licenciatura;

    @SerializedName("sesiones_tomadas")
    private int sesionesTomadas;

    @SerializedName("observaciones")
    private String observaciones;

    @SerializedName("estatus")
    private String estatus;



    public int getIdAsesoria() { return idAsesoria; }

    public int getIdEstudiante() { return idEstudiante; }

    public String getEstudiante() { return estudiante; }

    public int getIdAsesor() { return idAsesor; }

    public String getAsesor() { return asesor; }

    public int getIdMateria() { return idMateria; }

    public String getMateria() { return materia; }

    public int getIdModalidad() { return idModalidad; }

    public String getModalidad() { return modalidad; }

    public String getFechaInicio() {
        return fechaInicio.substring(0,10);
    }

    public String getFechaFin() { return fechaFin; }

    public int getIdRazon() { return idRazon; }

    public String getRazon() { return razon; }

    public int getIdLicenciatura() { return idLicenciatura; }

    public String getLicenciatura() { return licenciatura; }

    public int getSesionesTomadas() { return sesionesTomadas; }

    public String getObservaciones() { return observaciones; }

    public String getEstatus() { return estatus; }
}