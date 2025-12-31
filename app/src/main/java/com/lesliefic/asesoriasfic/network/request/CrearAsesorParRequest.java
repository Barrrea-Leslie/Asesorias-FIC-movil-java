

package com.lesliefic.asesoriasfic.network.request;

import com.lesliefic.asesoriasfic.modelo.Horario;
import com.lesliefic.asesoriasfic.modelo.HorarioId;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.modelo.MateriaId;

import java.util.List;

public class CrearAsesorParRequest {
    private int id_persona;
    private List<MateriaId> materias;
    private List<HorarioId> horarios;

    public CrearAsesorParRequest(int id_persona, List<MateriaId> materias, List<HorarioId> horarios){
        this.id_persona = id_persona;
        this.materias = materias;
        this.horarios = horarios;
    }
}
