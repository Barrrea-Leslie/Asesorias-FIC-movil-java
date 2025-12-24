package com.lesliefic.asesoriasfic.modelo;

public class Asesoria {

    public int id;
    public int estudianteId;
    private String materia;
    private String fechaInicio;
    private String fechaFin;
    private String horario;
    private String modalidad;
    private String razon;
    private int es_asesor;

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

    public String getFechaInicio() {
        return fechaInicio;
    }
    public String getFechaFin() {
        return fechaFin;
    }

    public String getHorario() {
        return horario;
    }

    public String getModalidad() {
        return modalidad;
    }

    public String getRazon() {
        return razon;
    }

    public int getEs_asesor() {
        return es_asesor;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}
