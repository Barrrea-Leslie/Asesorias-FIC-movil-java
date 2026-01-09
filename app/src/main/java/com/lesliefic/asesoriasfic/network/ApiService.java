package com.lesliefic.asesoriasfic.network;

import com.lesliefic.asesoriasfic.modelo.AsesorDisciplinar;
import com.lesliefic.asesoriasfic.modelo.AsesorPar;
import com.lesliefic.asesoriasfic.modelo.Estudiante;
import com.lesliefic.asesoriasfic.modelo.Grupo;
import com.lesliefic.asesoriasfic.modelo.Horario;
import com.lesliefic.asesoriasfic.modelo.Licenciatura;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.network.request.admin.CrearAsesorDisciplinarRequest;
import com.lesliefic.asesoriasfic.network.request.admin.CrearAsesorParRequest;
import com.lesliefic.asesoriasfic.network.request.admin.CrearEstudianteRequest;
import com.lesliefic.asesoriasfic.network.request.admin.EditarAsesorParRequest;
import com.lesliefic.asesoriasfic.network.request.admin.EditarEstudianteRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/asesores-par")
    Call<List<AsesorPar>> obtenerAsesoresPar();

    @GET("/estudiantes")
    Call<List<Estudiante>> obtenerEstudiantes();

    @POST("/estudiantes")
    Call<Integer> CrearEstudiante(@Body CrearEstudianteRequest request);

    @GET("/estudiantes/buscar")
    Call<List<Estudiante>> buscarEstudiantes(@Query("p_busqueda") String busqueda);

    @POST("/estudiantes/editar")
    Call<Integer> editarEstudiante(@Body EditarEstudianteRequest request);

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

    @POST("/asesores-par/editar")
    Call<Integer> editarAsesorPar(@Body EditarAsesorParRequest request);

    @GET("/asesores-disciplinar")
    Call<List<AsesorDisciplinar>> buscarAsesorDisciplinar(@Query("p_busqueda") String busqueda);

    @POST("/asesores-disciplinar")
    Call<Integer> crearAsesorDisciplinar(@Body CrearAsesorDisciplinarRequest request);
}