package com.lesliefic.asesoriasfic.repositorios;

import com.lesliefic.asesoriasfic.modelo.AsesorDisciplinar;
import com.lesliefic.asesoriasfic.modelo.Estudiante;
import com.lesliefic.asesoriasfic.network.ApiService;
import com.lesliefic.asesoriasfic.network.RetrofitClient;
import com.lesliefic.asesoriasfic.network.request.admin.CrearAsesorDisciplinarRequest;
import com.lesliefic.asesoriasfic.network.request.admin.CrearEstudianteRequest;
import com.lesliefic.asesoriasfic.network.request.admin.EditarAsesorDisciplinarRequest;
import com.lesliefic.asesoriasfic.network.request.admin.EditarEstudianteRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsesorDisciplinarRepository {

    private final ApiService api;

    public AsesorDisciplinarRepository(){
        api = RetrofitClient.getClient().create(ApiService.class);
    }

    public interface ResultCallback<T> {
        void onSuccess(T data);
        void onError(String error);
    }

    public void obtenerAsesorDisciplinar(EstudiantesRepository.ResultCallback<List<Estudiante>> cb) {

        api.obtenerEstudiantes().enqueue(new Callback<List<Estudiante>>() {
            @Override
            public void onResponse(Call<List<Estudiante>> call, Response<List<Estudiante>> response) {

                if (!response.isSuccessful()) {
                    cb.onError("HTTP ERROR: " + response.code());
                    return;
                }

                List<Estudiante> lista = response.body();
                if (lista == null) {
                    cb.onError("Body null");
                    return;
                }

                cb.onSuccess(lista);
            }

            @Override
            public void onFailure(Call<List<Estudiante>> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }

    public void CrearAsesorDisciplinar(CrearAsesorDisciplinarRequest request, AsesorDisciplinarRepository.ResultCallback<Integer> cb){
        api.crearAsesorDisciplinar(request).enqueue(new Callback<Integer>() {
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
    public void buscarAsesorDisci(String busqueda, EstudiantesRepository.ResultCallback<List<AsesorDisciplinar>> cb) {

        api.buscarAsesorDisciplinar(busqueda).enqueue(new Callback<List<AsesorDisciplinar>>() {
            @Override
            public void onResponse(Call<List<AsesorDisciplinar>> call, Response<List<AsesorDisciplinar>> response) {

                if (!response.isSuccessful()) {
                    cb.onError("HTTP ERROR: " + response.code());
                    return;
                }

                List<AsesorDisciplinar> lista = response.body();
                if (lista == null) {
                    cb.onError("Body null");
                    return;
                }

                cb.onSuccess(lista);
            }

            @Override
            public void onFailure(Call<List<AsesorDisciplinar>> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }

    public void editarAsesorDisciplinar(EditarAsesorDisciplinarRequest request, AsesorDisciplinarRepository.ResultCallback<Integer> cb){
        api.editarAsesorDisciplinar(request).enqueue(new Callback<Integer>() {
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

}
