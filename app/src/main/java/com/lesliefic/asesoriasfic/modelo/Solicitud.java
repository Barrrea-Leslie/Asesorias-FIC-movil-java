package com.lesliefic.asesoriasfic.modelo;

public class Solicitud {

    private Estudiante estudiante;
    private String materia;
    private String fecha;
    private String horario;
    private String modalidad;

    public Solicitud(Estudiante estudiante, String materia, String fecha, String horario, String modalidad) {
        this.estudiante = estudiante;
        this.materia = materia;
        this.fecha = fecha;
        this.horario = horario;
        this.modalidad = modalidad;
    }

    public Estudiante getAlumno(){
        return estudiante;
    }

    public String getMateria() {
        return materia;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHorario() {
        return horario;
    }

    public String getModalidad() {
        return modalidad;
    }
}
