package com.lesliefic.asesoriasfic.network.request.admin;



public class EditarEstudianteRequest {

    private int id_persona;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private int numero_cuenta;
    private String contrasena;
    private int id_licenciatura;
    private int id_grupo;
    private String correo;
    private String num_cel;
    private double promedio;

    public EditarEstudianteRequest(
            int id_persona,
            String nombre,
            String apellido_paterno,
            String apellido_materno,
            int numero_cuenta,
            String contrasena,
            int id_licenciatura,
            int id_grupo,
            String correo,
            String num_cel,
            double promedio
    ) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.numero_cuenta = numero_cuenta;
        this.contrasena = contrasena;
        this.id_licenciatura = id_licenciatura;
        this.id_grupo = id_grupo;
        this.correo = correo;
        this.num_cel = num_cel;
        this.promedio = promedio;
    }
}
