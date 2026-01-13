package com.lesliefic.asesoriasfic.network.request.admin;

public class EliminarSolicitudRequest {

    private int id_solicitud;

    private String explicacion_asesor;

    public EliminarSolicitudRequest(int id_solicitud, String explicacion_asesor){
        this.id_solicitud = id_solicitud;
        this.explicacion_asesor = explicacion_asesor;
    }
}
