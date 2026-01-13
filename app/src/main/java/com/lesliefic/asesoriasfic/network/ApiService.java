package com.lesliefic.asesoriasfic.network;

import com.lesliefic.asesoriasfic.modelo.AsesorDisciplinar;
import com.lesliefic.asesoriasfic.modelo.AsesorPar;
import com.lesliefic.asesoriasfic.modelo.Asesores;
import com.lesliefic.asesoriasfic.modelo.AsesoresEstudiante;
import com.lesliefic.asesoriasfic.modelo.Asesoria;
import com.lesliefic.asesoriasfic.modelo.Estudiante;
import com.lesliefic.asesoriasfic.modelo.Grupo;
import com.lesliefic.asesoriasfic.modelo.Horario;
import com.lesliefic.asesoriasfic.modelo.Licenciatura;
import com.lesliefic.asesoriasfic.modelo.LoginResponse;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.modelo.Modalidad;
import com.lesliefic.asesoriasfic.modelo.Razon;
import com.lesliefic.asesoriasfic.modelo.SolicitudPendiente;
import com.lesliefic.asesoriasfic.network.request.admin.CrearAsesorDisciplinarRequest;
import com.lesliefic.asesoriasfic.network.request.admin.CrearAsesorParRequest;
import com.lesliefic.asesoriasfic.network.request.admin.CrearAsesoriaRequest;
import com.lesliefic.asesoriasfic.network.request.admin.CrearEstudianteRequest;
import com.lesliefic.asesoriasfic.network.request.admin.CrearSolicitudPendienteRequest;
import com.lesliefic.asesoriasfic.network.request.admin.EditarAsesorDisciplinarRequest;
import com.lesliefic.asesoriasfic.network.request.admin.EditarAsesorParRequest;
import com.lesliefic.asesoriasfic.network.request.admin.EditarAsesoriaRequest;
import com.lesliefic.asesoriasfic.network.request.admin.EditarEstudianteRequest;
import com.lesliefic.asesoriasfic.network.request.admin.EliminarSolicitudRequest;
import com.lesliefic.asesoriasfic.network.request.admin.IniciarSesionRequest;
import com.lesliefic.asesoriasfic.network.request.estudiante.FiltrarSolicitarRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    @GET("/catalogos/modalidades")
    Call<List<Modalidad>> obtenerModalidades();

    @GET("/catalogos/razones")
    Call<List<Razon>> obtenerRazones();

    @POST("/asesores-par")
    Call<Integer> crearAsesorPar(@Body CrearAsesorParRequest request);

    @POST("/asesores-par/editar")
    Call<Integer> editarAsesorPar(@Body EditarAsesorParRequest request);

    @GET("/asesores-disciplinar")
    Call<List<AsesorDisciplinar>> buscarAsesorDisciplinar(@Query("p_busqueda") String busqueda);

    @POST("/asesores-disciplinar")
    Call<Integer> crearAsesorDisciplinar(@Body CrearAsesorDisciplinarRequest request);

    @POST("/asesores-disciplinar/editar")
    Call<Integer> editarAsesorDisciplinar(@Body EditarAsesorDisciplinarRequest request);

    @DELETE("/usuarios")
    Call<Integer> eliminarUsuario(@Query("id_persona") int id_persona);

    @GET("/solicitudes")
    Call<List<SolicitudPendiente>> obtenerSolicitudes();

    @POST("/solicitudes/eliminar")
    Call<Integer> eliminarSolicitud(@Body EliminarSolicitudRequest request);

    @POST("/solicitudes/aceptar")
    Call<Integer> aceptarSolicitud(@Query("id_solicitud") int id_solicitud);

    @GET("/asesorias")
    Call<List<Asesoria>> obtenerAsesorias();

    @GET("/asesorias/asesores")
    Call<List<Asesores>> obtenerTodosAsesores();

    @POST("/asesorias")
    Call<Integer> crearAsesoria(@Body CrearAsesoriaRequest request);

    @POST("/asesorias/editar")
    Call<Integer> editarAsesoria(@Body EditarAsesoriaRequest request);

    @DELETE("/asesorias")
    Call<Integer> eliminarAsesoria(@Query("id_asesoria") int id_asesoria);

    @POST("/usuarios")
    Call<LoginResponse> iniciarSesion(@Body IniciarSesionRequest request);

    @POST("/solicitudes")
    Call<Integer> crearSolicitud(@Body CrearSolicitudPendienteRequest request);

    @GET("/solicitar")
    Call<List<AsesoresEstudiante>> obtenerAsesoresEstudiante();

    @POST("/solicitar/filtros")
    Call<List<AsesoresEstudiante>> obtenerAsesoresFiltros(@Body FiltrarSolicitarRequest request);




}