package com.lesliefic.asesoriasfic.network.request.admin;

public class CrearSolicitudPendienteRequest {

    private int id_materia;
    private int id_horario;
    private int id_modalidad;
    private int id_estudiante;
    private int id_asesor;
    private String fecha_inicio;
    private int id_razon;
    private String nota_estudiante;
    private String explicacion_asesor;

    public CrearSolicitudPendienteRequest(
            int id_materia,
            int id_horario,
            int id_modalidad,
            int id_estudiante,
            int id_asesor,
            String fecha_inicio,
            int id_razon,
            String nota_estudiante
    ) {
        this.id_materia = id_materia;
        this.id_horario = id_horario;
        this.id_modalidad = id_modalidad;
        this.id_estudiante = id_estudiante;
        this.id_asesor = id_asesor;
        this.fecha_inicio = fecha_inicio;
        this.id_razon = id_razon;
        this.nota_estudiante = nota_estudiante;
    }
}