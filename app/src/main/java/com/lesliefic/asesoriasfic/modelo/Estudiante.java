package com.lesliefic.asesoriasfic.modelo;

import com.google.gson.annotations.SerializedName;

public class Estudiante {

    @SerializedName("id_persona")
    private int idPersona;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("numero_cuenta")
    private int numeroCuenta;
    @SerializedName("contrasena")
    private String contrasena;
    @SerializedName("apellido_paterno")
    private String apellidoPaterno;
    @SerializedName("apellido_materno")
    private String apellidoMaterno;

    @SerializedName("nombre_completo")
    private String nombreCompleto;
    @SerializedName("correo")
    private String correo;
    @SerializedName("num_cel")
    private String numCel;
    @SerializedName("id_licenciatura")
    private int idLicenciatura;
    @SerializedName("promedio")
    private double promedio;
    @SerializedName("id_grupo")
    private int idGrupo;


    public Estudiante() {
    }


    public Estudiante(int idPersona, String nombre) {
        this.idPersona = idPersona;
        this.nombre = nombre;
    }


    public int getIdPersona() {
        return idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNumCel() {
        return numCel;
    }

    public String getNombreCompleto(){
        return nombreCompleto;
    }

    public int getIdLicenciatura() {
        return idLicenciatura;
    }

    public double getPromedio() {
        return promedio;
    }

    public int getIdGrupo() {
        return idGrupo;
    }
}