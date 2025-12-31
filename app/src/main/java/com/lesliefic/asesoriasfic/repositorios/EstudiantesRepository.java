package com.lesliefic.asesoriasfic.repositorios;

import com.lesliefic.asesoriasfic.modelo.AsesorPar;
import com.lesliefic.asesoriasfic.modelo.Estudiante;
import com.lesliefic.asesoriasfic.modelo.EstudianteRepository;
import com.lesliefic.asesoriasfic.network.ApiService;
import com.lesliefic.asesoriasfic.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstudiantesRepository {

    private final ApiService api;

    public EstudiantesRepository(){
        api = RetrofitClient.getClient().create(ApiService.class);
    }

    public interface ResultCallback<T> {
        void onSuccess(T data);
        void onError(String error);
    }

    public void obtenerEstudiantes(EstudiantesRepository.ResultCallback<List<Estudiante>> cb) {

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
}
