package com.lesliefic.asesoriasfic.network;

import com.lesliefic.asesoriasfic.modelo.AsesorPar;
import com.lesliefic.asesoriasfic.modelo.Estudiante;
import com.lesliefic.asesoriasfic.modelo.Grupo;
import com.lesliefic.asesoriasfic.modelo.Horario;
import com.lesliefic.asesoriasfic.modelo.Licenciatura;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.network.request.CrearAsesorParRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("/asesores-par")
    Call<List<AsesorPar>> obtenerAsesoresPar();

    @GET("/estudiantes")
    Call<List<Estudiante>> obtenerEstudiantes();

    @GET("/catalogos/materias")
    Call<List<Materia>> obtenerMaterias();

    @GET("/catalogos/horarios")
    Call<List<Horario>> obtenerHorarios();

    @GET("/catalogos/grupos")
    Call<List<Grupo>> obtenerGrupos();

    @GET("/catalogos/licenciaturas")
    Call<List<Licenciatura>> obtenerLicenciaturas();

    @POST("/asesores-par")
    Call<Integer> crearAsesorPar(@Body CrearAsesorParRequest request);
}