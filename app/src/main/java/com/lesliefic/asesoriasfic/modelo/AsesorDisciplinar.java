package com.lesliefic.asesoriasfic.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AsesorDisciplinar implements Serializable {

    @SerializedName("id_persona")
    private int id_persona;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("apellido_paterno")
    private String apellidoPaterno;

    @SerializedName("apellido_materno")
    private String apellidoMaterno;
    @SerializedName("nombre_completo")
    private  String nombre_completo;
    @SerializedName("numero_cuenta")
    private int numeroCuenta;

    @SerializedName("contrasena")
    private String contrasena;
    @SerializedName("correo")
    private String correo;
    @SerializedName("num_cel")
    private String numCel;
    @SerializedName("promedio")
    private double promedio;
    @SerializedName("materiasasesor")
    private List<Materia> materiasAsesor;
    @SerializedName("horariosasesor")
    private List<Horario> horariosAsesor;

    public int getId_persona(){
        return id_persona;
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
        return  nombre_completo;
    }
    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNumCel() {
        return numCel;
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
