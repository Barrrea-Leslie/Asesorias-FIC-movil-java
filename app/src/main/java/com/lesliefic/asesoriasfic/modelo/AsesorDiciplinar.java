package com.lesliefic.asesoriasfic.modelo;

import java.util.List;

public class AsesorDiciplinar {

    private int id;
    private String nombre;
    private String numeroCuenta;
    private String licenciatura;
    private String grupo;
    private String correoInstitucional;
    private String numeroTelefono;
    private String promedio;
    private List<String> materiasAsesora;
    private List<String> horariosAsesora;

    public AsesorDiciplinar(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getLicenciatura() {
        return licenciatura;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public String getPromedio() {
        return promedio;
    }

    public List<String> getMateriasAsesora() {
        return materiasAsesora;
    }

    public List<String> getHorariosAsesora() {
        return horariosAsesora;
    }
}
