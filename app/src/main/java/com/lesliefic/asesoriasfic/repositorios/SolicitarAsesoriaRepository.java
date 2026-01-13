package com.lesliefic.asesoriasfic.repositorios;

import com.lesliefic.asesoriasfic.modelo.Asesores;
import com.lesliefic.asesoriasfic.modelo.AsesoresEstudiante;
import com.lesliefic.asesoriasfic.modelo.Asesoria;
import com.lesliefic.asesoriasfic.modelo.Estudiante;
import com.lesliefic.asesoriasfic.modelo.SolicitudPendiente;
import com.lesliefic.asesoriasfic.network.ApiService;
import com.lesliefic.asesoriasfic.network.RetrofitClient;
import com.lesliefic.asesoriasfic.network.request.admin.CrearAsesorParRequest;
import com.lesliefic.asesoriasfic.network.request.admin.CrearAsesoriaRequest;
import com.lesliefic.asesoriasfic.network.request.admin.CrearEstudianteRequest;
import com.lesliefic.asesoriasfic.network.request.admin.EditarAsesoriaRequest;
import com.lesliefic.asesoriasfic.network.request.admin.EditarEstudianteRequest;
import com.lesliefic.asesoriasfic.network.request.admin.EliminarSolicitudRequest;
import com.lesliefic.asesoriasfic.network.request.estudiante.FiltrarSolicitarRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SolicitarAsesoriaRepository {

    private final ApiService api;

    public SolicitarAsesoriaRepository(){
        api = RetrofitClient.getClient().create(ApiService.class);
    }

    public interface ResultCallback<T> {
        void onSuccess(T data);
        void onError(String error);
    }

    public void obtenerAsesoresEstudiante(
            SolicitarAsesoriaRepository.ResultCallback<List<AsesoresEstudiante>> cb
    ) {

        api.obtenerAsesoresEstudiante().enqueue(new Callback<List<AsesoresEstudiante>>() {
            @Override
            public void onResponse(
                    Call<List<AsesoresEstudiante>> call,
                    Response<List<AsesoresEstudiante>> response
            ) {

                if (!response.isSuccessful()) {
                    cb.onError("HTTP ERROR: " + response.code());
                    return;
                }

                List<AsesoresEstudiante> lista = response.body();
                if (lista == null) {
                    cb.onError("Body null");
                    return;
                }

                cb.onSuccess(lista);
            }

            @Override
            public void onFailure(Call<List<AsesoresEstudiante>> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }

    public void obtenerAsesoresFiltros(FiltrarSolicitarRequest request,
            SolicitarAsesoriaRepository.ResultCallback<List<AsesoresEstudiante>> cb
    ) {

        api.obtenerAsesoresFiltros(request).enqueue(new Callback<List<AsesoresEstudiante>>() {
            @Override
            public void onResponse(
                    Call<List<AsesoresEstudiante>> call,
                    Response<List<AsesoresEstudiante>> response
            ) {

                if (!response.isSuccessful()) {
                    cb.onError("HTTP ERROR: " + response.code());
                    return;
                }

                List<AsesoresEstudiante> lista = response.body();
                if (lista == null) {
                    cb.onError("Body null");
                    return;
                }

                cb.onSuccess(lista);
            }

            @Override
            public void onFailure(Call<List<AsesoresEstudiante>> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }

    public void obtenerTodosAsesores(AsesoriasCursoRepository.ResultCallback<List<Asesores>> cb) {

        api.obtenerTodosAsesores().enqueue(new Callback<List<Asesores>>() {
            @Override
            public void onResponse(Call<List<Asesores>> call, Response<List<Asesores>> response) {

                if (!response.isSuccessful()) {
                    cb.onError("HTTP ERROR: " + response.code());
                    return;
                }

                List<Asesores> lista = response.body();
                if (lista == null) {
                    cb.onError("Body null");
                    return;
                }

                cb.onSuccess(lista);
            }

            @Override
            public void onFailure(Call<List<Asesores>> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }



    public void eliminarAsesoria(int id_asesoria, AsesoriasCursoRepository.ResultCallback<Integer> cb) {
        api.eliminarAsesoria(id_asesoria).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

                if (!response.isSuccessful()) {
                    String msg = "HTTP " + response.code();

                    try {
                        if (response.errorBody() != null) {
                            msg += " - " + response.errorBody().string();
                        } else {
                            msg += " (sin cuerpo)";
                        }
                    } catch (Exception ignored) {}

                    cb.onError(msg);
                    return;
                }

                Integer r = response.body();
                if (r == null) {
                    cb.onError("Body null");
                    return;
                }

                cb.onSuccess(r);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable throwable) {
                cb.onError(throwable.getMessage());
            }
        });
    }
    public void aceptarSolicitud(int id_solicitud, SolicitudesRepository.ResultCallback<Integer> cb){
        api.aceptarSolicitud(id_solicitud).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(!response.isSuccessful()){
                    String msg = "HTTP " + response.code();
                    try{
                        if (response.errorBody() != null){
                            msg = response.errorBody().string();
                        }
                    } catch (Exception ignored) {
                        cb.onError(msg);
                        return;
                    }
                }
                Integer r = response.body();
                if(r == null){
                    cb.onError("Body null");
                    return;
                }

                cb.onSuccess(r);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable throwable) {
                cb.onError(throwable.getMessage());
            }
        });
    }



    public void crearAsesoria(CrearAsesoriaRequest request, AsesorParRepository.ResultCallback<Integer> cb){
        api.crearAsesoria(request).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(!response.isSuccessful()){
                    String msg = "HTTP " + response.code();
                    try{
                        if (response.errorBody() != null){
                            msg = response.errorBody().string();
                        }
                    } catch (Exception ignored) {
                        cb.onError(msg);
                        return;
                    }
                }
                Integer r = response.body();
                if(r == null){
                    cb.onError("Body null");
                    return;
                }

                cb.onSuccess(r);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable throwable) {
                cb.onError(throwable.getMessage());
            }
        });
    }

    public void editarAsesoria(
            EditarAsesoriaRequest request,
            AsesorParRepository.ResultCallback<Integer> cb
    ) {
        api.editarAsesoria(request).enqueue(new Callback<Integer>() {

            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

                if (!response.isSuccessful()) {
                    String msg = "HTTP " + response.code();
                    try {
                        if (response.errorBody() != null) {
                            msg = response.errorBody().string();
                        }
                    } catch (Exception ignored) {}

                    cb.onError(msg);
                    return;
                }

                Integer r = response.body();
                if (r == null) {
                    cb.onError("Body null");
                    return;
                }

                cb.onSuccess(r);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable throwable) {
                cb.onError(throwable.getMessage());
            }
        });
    }

}
