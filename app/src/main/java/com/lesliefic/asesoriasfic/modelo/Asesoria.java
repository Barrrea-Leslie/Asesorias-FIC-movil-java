package com.lesliefic.asesoriasfic.modelo;

public class Asesoria {

    public int id;
    public int estudianteId;
    private String materia;
    private String fecha;
    private String horario;
    private String modalidad;

    private Estudiante estudiante;

    public int getId() {
        return id;
    }

    public int getEstudianteId() {
        return estudianteId;
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

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}
