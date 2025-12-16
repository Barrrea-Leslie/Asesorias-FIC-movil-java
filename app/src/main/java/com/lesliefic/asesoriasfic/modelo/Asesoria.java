package com.lesliefic.asesoriasfic.modelo;

public class Asesoria {

    public Alumno alumno;
    private String materia;
    private String fecha;
    private String horario;
    private String modalidad;

    public Asesoria(Alumno alumno, String materia, String fecha, String horario, String modalidad) {
        this.alumno = alumno;
        this.materia = materia;
        this.fecha = fecha;
        this.horario = horario;
        this.modalidad = modalidad;
    }

    public Alumno getAlumno() {
        return alumno;
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
