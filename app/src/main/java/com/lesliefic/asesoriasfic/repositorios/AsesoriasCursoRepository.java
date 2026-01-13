package com.lesliefic.asesoriasfic.repositorios;

import com.lesliefic.asesoriasfic.modelo.Asesores;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsesoriasCursoRepository {

    private final ApiService api;

    public AsesoriasCursoRepository(){
        api = RetrofitClient.getClient().create(ApiService.class);
    }

    public interface ResultCallback<T> {
        void onSuccess(T data);
        void onError(String error);
    }

    public void obtenerAsesorias(AsesoriasCursoRepository.ResultCallback<List<Asesoria>> cb) {

        api.obtenerAsesorias().enqueue(new Callback<List<Asesoria>>() {
            @Override
            public void onResponse(Call<List<Asesoria>> call, Response<List<Asesoria>> response) {

                if (!response.isSuccessful()) {
                    cb.onError("HTTP ERROR: " + response.code());
                    return;
                }

                List<Asesoria> lista = response.body();
                if (lista == null) {
                    cb.onError("Body null");
                    return;
                }

                cb.onSuccess(lista);
            }

            @Override
            public void onFailure(Call<List<Asesoria>> call, Throwable t) {
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



    public void eliminarSolicitud(EliminarSolicitudRequest request, SolicitudesRepository.ResultCallback<Integer> cb){
        api.eliminarSolicitud(request).enqueue(new Callback<Integer>() {
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
