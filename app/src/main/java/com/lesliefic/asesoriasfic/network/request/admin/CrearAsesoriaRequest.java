package com.lesliefic.asesoriasfic.network.request.admin;

import java.io.Serializable;

public class CrearAsesoriaRequest implements Serializable {

    private int id_estudiante;
    private int id_asesor;
    private int id_materia;
    private int id_modalidad;
    private String fecha_inicio;
    private int id_razon;
    private int id_horario;
    private int id_licenciatura;

    public CrearAsesoriaRequest(
            int id_estudiante,
            int id_asesor,
            int id_materia,
            int id_modalidad,
            String fecha_inicio,
            int id_razon,
            int id_horario
    ) {
        this.id_estudiante = id_estudiante;
        this.id_asesor = id_asesor;
        this.id_materia = id_materia;
        this.id_modalidad = id_modalidad;
        this.fecha_inicio = fecha_inicio;
        this.id_razon = id_razon;
        this.id_horario = id_horario;
    }


}