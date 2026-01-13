package com.lesliefic.asesoriasfic.network.request.admin;

import java.io.Serializable;

public class EditarAsesoriaRequest implements Serializable {

    private int id_asesoria;
    private Integer id_materia;
    private Integer id_modalidad;
    private String fecha_inicio;
    private Integer id_razon;
    private Integer sesiones_tomadas;
    private String observaciones;
    private Integer id_horario;

    public EditarAsesoriaRequest(
            int id_asesoria,
            Integer id_materia,
            Integer id_modalidad,
            String fecha_inicio,
            Integer id_razon,
            Integer sesiones_tomadas,
            String observaciones,
            Integer id_horario
    ) {
        this.id_asesoria = id_asesoria;
        this.id_materia = id_materia;
        this.id_modalidad = id_modalidad;
        this.fecha_inicio = fecha_inicio;
        this.id_razon = id_razon;
        this.sesiones_tomadas = sesiones_tomadas;
        this.observaciones = observaciones;
        this.id_horario = id_horario;
    }

}