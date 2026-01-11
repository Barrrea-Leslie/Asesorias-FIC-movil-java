package com.lesliefic.asesoriasfic.repositorios;

import com.lesliefic.asesoriasfic.modelo.Grupo;
import com.lesliefic.asesoriasfic.modelo.Licenciatura;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.modelo.Horario;
import com.lesliefic.asesoriasfic.network.ApiService;
import com.lesliefic.asesoriasfic.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogosRepository {

    private final ApiService api;

    public CatalogosRepository(){
        api = RetrofitClient.getClient().create(ApiService.class);
    }

    public interface ResultCallback<T> {
        void onSuccess(T data);
        void onError(String error);
    }

    public void obtenerMaterias(ResultCallback<List<Materia>> cb) {

        api.obtenerMaterias().enqueue(new Callback<List<Materia>>() {
            @Override
            public void onResponse(Call<List<Materia>> call, Response<List<Materia>> response) {

                if (!response.isSuccessful()) {
                    cb.onError("HTTP ERROR: " + response.code());
                    return;
                }

                List<Materia> lista = response.body();
                if (lista == null) {
                    cb.onError("Body null");
                    return;
                }

                cb.onSuccess(lista);
            }

            @Override
            public void onFailure(Call<List<Materia>> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }

    public void obtenerHorarios(ResultCallback<List<Horario>> cb) {

        api.obtenerHorarios().enqueue(new Callback<List<Horario>>() {
            @Override
            public void onResponse(Call<List<Horario>> call, Response<List<Horario>> response) {

                if (!response.isSuccessful()) {
                    cb.onError("HTTP ERROR: " + response.code());
                    return;
                }

                List<Horario> lista = response.body();
                if (lista == null) {
                    cb.onError("Body null");
                    return;
                }

                cb.onSuccess(lista);
            }

            @Override
            public void onFailure(Call<List<Horario>> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }

    public void obtenerGrupos(ResultCallback<List<Grupo>> cb) {

        api.obtenerGrupos().enqueue(new Callback<List<Grupo>>() {
            @Override
            public void onResponse(Call<List<Grupo>> call, Response<List<Grupo>> response) {

                if (!response.isSuccessful()) {
                    cb.onError("HTTP ERROR: " + response.code());
                    return;
                }

                List<Grupo> lista = response.body();
                if (lista == null) {
                    cb.onError("Body null");
                    return;
                }

                cb.onSuccess(lista);
            }

            @Override
            public void onFailure(Call<List<Grupo>> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }

    public void obtenerLicenciaturas(ResultCallback<List<Licenciatura>> cb) {

        api.obtenerLicenciaturas().enqueue(new Callback<List<Licenciatura>>() {
            @Override
            public void onResponse(Call<List<Licenciatura>> call, Response<List<Licenciatura>> response) {

                if (!response.isSuccessful()) {
                    cb.onError("HTTP ERROR: " + response.code());
                    return;
                }

                List<Licenciatura> lista = response.body();
                if (lista == null) {
                    cb.onError("Body null");
                    return;
                }

                cb.onSuccess(lista);
            }

            @Override
            public void onFailure(Call<List<Licenciatura>> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }



}
