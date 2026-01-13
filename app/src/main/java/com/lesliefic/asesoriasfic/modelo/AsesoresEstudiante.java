package com.lesliefic.asesoriasfic.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AsesoresEstudiante implements Serializable {

    @SerializedName("id_asesor")
    private int id_asesor;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("apellido_paterno")
    private String apellidoPaterno;

    @SerializedName("apellido_materno")
    private String apellidoMaterno;

    @SerializedName("nombre_completo")
    private String nombre_completo;
    @SerializedName("numero_cuenta")
    private int numeroCuenta;

    @SerializedName("contrasena")
    private String contrasena;

    @SerializedName("id_licenciatura")
    private int idLicenciatura;

    @SerializedName("licenciatura")
    private String licenciatura;

    @SerializedName("id_grupo")
    private int idGrupo;

    @SerializedName("grupo")
    private String grupo;

    @SerializedName("correo")
    private String correo;

    @SerializedName("num_cel")
    private String numeroTelefono;

    @SerializedName("promedio")
    private double promedio;


    @SerializedName("materiasasesor")
    private List<Materia> materiasAsesor;

    @SerializedName("horariosasesor")
    private List<Horario> horariosAsesor;


    public int getIdAsesor() {
        return id_asesor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getNombre_completo(){
        return nombre_completo;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getIdLicenciatura() {
        return idLicenciatura;
    }

    public String getLicenciatura(){
        return licenciatura;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public String getGrupo(){
        return grupo;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public double getPromedio() {
        return promedio;
    }

    public List<Materia> getMateriasAsesor() {
        return materiasAsesor;
    }

    public List<Horario> getHorariosAsesor() {
        return horariosAsesor;
    }
}