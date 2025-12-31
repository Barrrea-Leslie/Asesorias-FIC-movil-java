package com.lesliefic.asesoriasfic.modelo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AsesorPar {

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
    private String numeroCuenta;

    @SerializedName("contrasena")
    private String contrasena;

    @SerializedName("id_licenciatura")
    private int idLicenciatura;

    @SerializedName("id_grupo")
    private int idGrupo;

    @SerializedName("correo")
    private String correo;

    @SerializedName("num_cel")
    private String numeroTelefono;

    @SerializedName("promedio")
    private double promedio;


    @SerializedName("materiasAsesor")
    private List<Materia> materiasAsesor;

    @SerializedName("horariosAsesor")
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

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getIdLicenciatura() {
        return idLicenciatura;
    }

    public int getIdGrupo() {
        return idGrupo;
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