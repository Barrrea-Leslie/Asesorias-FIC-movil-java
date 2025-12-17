package com.lesliefic.asesoriasfic.modelo;

public class Estudiante {

    private int id;
    private String nombre;
    private String numeroCuenta;
    private String licenciatura;
    private String grupo;
    private String correoInstitucional;
    private String numeroTelefono;
    private String promedio;

    public Estudiante(String nombre) {
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
}
