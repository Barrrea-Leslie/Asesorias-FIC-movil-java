package com.lesliefic.asesoriasfic.network.request.admin;

public class IniciarSesionRequest {

    private int usuario;
    private String contrasena;

    public IniciarSesionRequest(int usuario, String contrasena){
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
}
