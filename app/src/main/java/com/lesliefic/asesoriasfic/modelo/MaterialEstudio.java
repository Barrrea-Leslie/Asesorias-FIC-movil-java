package com.lesliefic.asesoriasfic.modelo;

public class MaterialEstudio {
    private String nombre;
    private String tipo; // PDF, DOCX, etc.
    private String url;

    public MaterialEstudio(String nombre, String tipo, String url) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.url = url;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public String getUrl() { return url; }
}
