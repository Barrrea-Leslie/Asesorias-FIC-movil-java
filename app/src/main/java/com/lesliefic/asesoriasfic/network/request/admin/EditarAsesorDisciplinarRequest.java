package com.lesliefic.asesoriasfic.network.request.admin;

import com.lesliefic.asesoriasfic.modelo.HorarioId;
import com.lesliefic.asesoriasfic.modelo.MateriaId;

import java.util.List;

public class EditarAsesorDisciplinarRequest {
    private int id_persona;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private int numero_cuenta;
    private String contrasena;
    private String correo;
    private String num_cel;
    private List<MateriaId> materias;
    private List<HorarioId> horarios;

    public EditarAsesorDisciplinarRequest(
            int id_persona,
            String nombre,
            String apellido_paterno,
            String apellido_materno,
            int numero_cuenta,
            String contrasena,
            String correo,
            String num_cel,
            List<MateriaId> materias,
            List<HorarioId> horarios
    ) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.numero_cuenta = numero_cuenta;
        this.contrasena = contrasena;
        this.correo = correo;
        this.num_cel = num_cel;
        this.materias = materias;
        this.horarios = horarios;
    }
}
