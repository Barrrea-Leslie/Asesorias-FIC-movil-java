package com.lesliefic.asesoriasfic.repositorios;

import com.lesliefic.asesoriasfic.modelo.Estudiante;
import com.lesliefic.asesoriasfic.modelo.SolicitudPendiente;
import com.lesliefic.asesoriasfic.network.ApiService;
import com.lesliefic.asesoriasfic.network.RetrofitClient;
import com.lesliefic.asesoriasfic.network.request.admin.CrearEstudianteRequest;
import com.lesliefic.asesoriasfic.network.request.admin.CrearSolicitudPendienteRequest;
import com.lesliefic.asesoriasfic.network.request.admin.EditarEstudianteRequest;
import com.lesliefic.asesoriasfic.network.request.admin.EliminarSolicitudRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SolicitudesRepository {

    private final ApiService api;

    public SolicitudesRepository(){
        api = RetrofitClient.getClient().create(ApiService.class);
    }

    public interface ResultCallback<T> {
        void onSuccess(T data);
        void onError(String error);
    }

    public void obtenerSolicitudes(SolicitudesRepository.ResultCallback<List<SolicitudPendiente>> cb) {

        api.obtenerSolicitudes().enqueue(new Callback<List<SolicitudPendiente>>() {
            @Override
            public void onResponse(Call<List<SolicitudPendiente>> call, Response<List<SolicitudPendiente>> response) {

                if (!response.isSuccessful()) {
                    cb.onError("HTTP ERROR: " + response.code());
                    return;
                }

                List<SolicitudPendiente> lista = response.body();
                if (lista == null) {
                    cb.onError("Body null");
                    return;
                }

                cb.onSuccess(lista);
            }

            @Override
            public void onFailure(Call<List<SolicitudPendiente>> call, Throwable t) {
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

    public void crearSolicitud(CrearSolicitudPendienteRequest request,
                               SolicitudesRepository.ResultCallback<Integer> cb) {

        api.crearSolicitud(request).enqueue(new Callback<Integer>() {
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
