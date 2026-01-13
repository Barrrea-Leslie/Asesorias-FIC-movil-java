package com.lesliefic.asesoriasfic.network.request.estudiante;

public class FiltrarSolicitarRequest {

    private int id_materia;
    private int id_horario;

    public FiltrarSolicitarRequest(int id_materia, int id_horario){
        this.id_materia = id_materia;
        this.id_horario = id_horario;
    }
}
